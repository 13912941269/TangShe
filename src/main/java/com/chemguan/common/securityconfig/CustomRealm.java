package com.chemguan.common.securityconfig;


import com.chemguan.background.mapper.BackGroundLoginDao;
import com.chemguan.background.model.*;
import com.chemguan.util.Encodeutil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.*;

/**
 * @Title: CustomRealm
 * @ProjectName
 * @Description: TODO
 * @author shiwei
 * @date 2018-10-30
 */
public class CustomRealm extends AuthorizingRealm {

    @Resource
    private BackGroundLoginDao dao;

    Logger logger = LoggerFactory.getLogger(CustomRealm.class);

    /**
     * 授权
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        ShiroUser shirouser=(ShiroUser)SecurityUtils.getSubject().getPrincipal();
        List<SysRolePermission> listrolepermission = dao.selectpermissionbyroleid(shirouser.getRoleid());
        List<String> permissions = new ArrayList<String>();
        for(SysRolePermission srp:listrolepermission){
            permissions.add(srp.getSyspermission().getPercode());
        }
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermissions(permissions);
        principals.getPrimaryPrincipal();
        return info;
    }


    /**
     * 登录验证
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authcToken) throws AuthenticationException {
        UsernamePasswordToken token = (UsernamePasswordToken) authcToken;
        String username=token.getUsername();
        String password = Encodeutil.getMD5(String.valueOf(token.getPassword()));
        Map map=new HashMap(16);
        map.put("username", username);
        map.put("password", password);
        BackGroundLogin loginback = dao.loginback(map);
        if(loginback!=null){
            SysBgRole role = dao.selectrolebybgid(loginback.getId());
            ShiroUser shirouser=new ShiroUser();
            shirouser.setUsername(loginback.getUsername());
            shirouser.setRolename(role.getName());
            shirouser.setRealname(loginback.getRealname());
            shirouser.setRoleid(role.getSys_role_id());
            List<SysRolePermission> listrolepermission = dao.selectpermissionbyroleid(role.getSys_role_id());
            List<SysPermission> listpermission = new ArrayList<SysPermission>();
            List<SysPermission> listpermenumission = new ArrayList<SysPermission>();
            if(listrolepermission.size()>0){
                for(SysRolePermission srp:listrolepermission){
                    listpermission.add(srp.getSyspermission());
                    if(srp.getSyspermission().getType().equals("menu")&&srp.getSyspermission().getParentid()==null){
                        listpermenumission.add(srp.getSyspermission());
                    }
                }
                for(SysPermission spparent:listpermenumission){
                    List<SysPermission> listchildren = new ArrayList<SysPermission>();
                    for(SysPermission sp:listpermission){
                        if(sp.getType().equals("menu")&&spparent.getSys_permission_id().equals(sp.getParentid())){
                            listchildren.add(sp);
                        }
                    }
                    spparent.setListpermission(listchildren);
                }
                shirouser.setPermissionlist(listpermission);
                shirouser.setPermissionmenulist(listpermenumission);
            }
            return new SimpleAuthenticationInfo(shirouser, password, getName());
        }else{
            return null;
        }
    }
}
