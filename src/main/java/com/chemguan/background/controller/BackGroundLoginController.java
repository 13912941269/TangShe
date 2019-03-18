package com.chemguan.manager.background.controller;


import com.chemguan.background.mapper.BackGroundLoginDao;
import com.chemguan.background.model.*;
import com.chemguan.background.service.BackGroundService;
import com.chemguan.util.CodeUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.commons.lang.StringEscapeUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.UnauthorizedException;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.RenderedImage;
import java.io.IOException;
import java.util.*;

@Controller
@RequestMapping("manager")
public class BackGroundLoginController {

	@Autowired
	private BackGroundService service;


	@Autowired
	private BackGroundLoginDao backGroundLoginDao;


	/**
	 * 查询
	 */
	@RequiresPermissions("bg:allbguser")
	@RequestMapping("allbguser")
	public ModelAndView selectAllUser(ModelMap modelMap) {
		List<SysRole> listrole = backGroundLoginDao.selectallrole();
		for(SysRole sysRole:listrole){
			sysRole.setName(StringEscapeUtils.escapeHtml(sysRole.getName()));
		}
		modelMap.put("listrole", listrole);
		return new ModelAndView("background/selectallbguser");
	}



	/**
	 * 检查用户名
	 *
	 * @param username
	 * @return
	 */
	@RequestMapping("checkbgusername")
	public ResponseEntity<?> checkBgUserName(String username) {
		BackGroundLogin user = (BackGroundLogin)service.findBy("username",username);
		String json = null;
		if (user != null) {
			json = "nook";
		} else {
			json = "ok";
		}
		return ResponseEntity.ok(json);
	}





	/**
	 * 查询出需要修改的数据
	 *
	 * @param id
	 * @param request
	 * @return
	 */
	@RequiresPermissions("bg:selectbgbyid")
	@RequestMapping("selectbgbyid")
	public String selectBgById(int id, HttpServletRequest request, ModelMap map) {
		BackGroundLogin backGroundLogin = (BackGroundLogin)service.findById(id);
		map.put("backGroundLogin", backGroundLogin);
		return "background/updatebguser";
	}







	/**
	 * 根据id检查用户名
	 *
	 * @param username
	 * @param id
	 * @return
	 */
	@RequestMapping("checkusernameandid")
	public ResponseEntity<?> checkUserNameAndId(String username, int id) {
		BackGroundLogin userone = (BackGroundLogin)service.findById(id);
		String json = null;
		if (userone.getUsername().equals(username)) {
			json = "ok";
		} else {
			BackGroundLogin user = (BackGroundLogin)service.findBy("username",username);
			if (user != null) {
				json = "nook";
			} else {
				json = "ok";
			}
		}
		return ResponseEntity.ok(json);
	}


	/**
	 * 修改
	 *
	 * @return
	 */
	@RequestMapping("updatebguser")
	@RequiresPermissions("bg:allbguser")
	public void updateBgUser(HttpServletResponse response, BackGroundLogin bg) throws IOException {
		service.update(bg);
		response.sendRedirect("background/allbguser");
	}

	/**
	 * 返回登录页面
	 *
	 * @return
	 */
	@RequestMapping("login")
	public String returnloginjsp() {
		return "background/login";
	}





	/**
	 * 验证码
	 *
	 * @return
	 * @throws IOException
	 * @throws ServletException
	 */
	@RequestMapping("codeqr")
	public void code(HttpServletRequest request, HttpServletResponse response){
		Map<String, Object> codeMap = CodeUtil.generateCodeAndPic();
		HttpSession session = request.getSession();
		session.setAttribute("validateCode", codeMap.get("code").toString());
		response.setHeader("Pragma", "no-cache");
		response.setHeader("Cache-Control", "no-cache");
		response.setDateHeader("Expires", -1);
		response.setContentType("image/jpeg");
		ServletOutputStream sos;
		try {
			sos = response.getOutputStream();
			ImageIO.write((RenderedImage) codeMap.get("codePic"), "jpeg", sos);
			sos.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}





	/**
	 * 登陆页面
	 *
	 * @param request
	 * @return
	 */
	@RequestMapping("backmangerlogin")
	public ResponseEntity<?> backMangerLogin(HttpServletRequest request,HttpSession session) {
		Map map = new HashMap();
		String username = request.getParameter("usename");
		String password = request.getParameter("password");
		String codejsp = request.getParameter("codejsp");
		String code=(String) session.getAttribute("validateCode");
		String msg = "";
		if (code.equalsIgnoreCase(codejsp)) {
			UsernamePasswordToken token = new UsernamePasswordToken(username, password);
			Subject subject = SecurityUtils.getSubject();
			try {
				subject.login(token);
				if(subject.isAuthenticated()) {
					msg= "success";
				}else {
					msg= "success";
				}
				String url;
				try {
					url = WebUtils.getSavedRequest(request).getRequestUrl();
					map.put("url", url);
				} catch (Exception e) {
					map.put("url", "allbguser");
				}
			}catch (IncorrectCredentialsException e) {
				e.printStackTrace();
				msg = "登录密码错误";
			} catch (ExcessiveAttemptsException e) {
				msg = "登录失败次数过多";
			} catch (LockedAccountException e) {
				msg = "帐号已被锁定";
			} catch (DisabledAccountException e) {
				msg = "帐号已被禁用";
			} catch (ExpiredCredentialsException e) {
				msg = "帐号已过期";
			} catch (UnknownAccountException e) {
				msg = "帐号不存在";
			} catch (UnauthorizedException e) {
				msg = "您没有得到相应的授权";
			}
		}else{
			msg="验证码错误";
		}
		session.removeAttribute("validateCode");
		map.put("msg", msg);
		return ResponseEntity.ok(map);
	}


	/**
	 * 跳转错误页面
	 *
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping("goerror")
	public String goerror(HttpServletRequest request,HttpServletResponse response) {
		return "background/error";
	}



	/**
	 * 查询角色
	 */
	@RequiresPermissions("bg:allrole")
	@RequestMapping("allrole")
	public ModelAndView allrole(HttpServletRequest request, Integer currentPage, Integer available) {
		PageHelper.startPage(currentPage==null?1:currentPage, 10);
		List<SysRole> list = backGroundLoginDao.selectallrole();
		for(SysRole sysRole:list){
			sysRole.setName(StringEscapeUtils.escapeHtml(sysRole.getName()));
		}
		PageInfo pagebean = new PageInfo(list);
		ModelAndView mav = new ModelAndView();
		mav.addObject("pagebean", pagebean);
		mav.setViewName("background/allrole");
		return mav;
	}


	/**
	 * 添加角色页面
	 */
	@RequiresPermissions("bg:addrolepage")
	@RequestMapping("addrolepage")
	public ModelAndView addrolepage(HttpServletRequest request) {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("background/addrole");
		return mav;
	}




	/**
	 * 添加角色
	 */
	@RequestMapping("addrole")
	@RequiresPermissions("bg:allrole")
	public void addrole(HttpServletResponse response,String name) throws IOException {
		Map map=new HashMap();
		map.put("name", name);
		backGroundLoginDao.insertrole(map);
		response.sendRedirect("allrole");
	}


	/**
	 * 修改角色页面
	 */
	@RequiresPermissions("bg:allrole")
	@RequestMapping("updaterolepage")
	public ModelAndView updaterolepage(HttpServletRequest request,Integer roleid) {
		SysRole role = backGroundLoginDao.selectrolebyid(roleid);
		role.setName(StringEscapeUtils.escapeHtml(role.getName()));
		List<SysPermission> listper = backGroundLoginDao.selectallpermission(new HashMap());
		List<SysRolePermission> listrm = backGroundLoginDao.selectpermissionbyroleid(roleid);
		ModelAndView mav = new ModelAndView();
		mav.addObject("role",role);
		mav.addObject("listper",listper);
		mav.addObject("listrm",listrm);
		mav.setViewName("background/updaterole");
		return mav;
	}

	/**
	 * 修改角色
	 */
	@RequestMapping("updaterole")
	@RequiresPermissions("bg:allrole")
	public void updaterole(HttpServletResponse response,String name,Integer roleid,String[] permission) throws IOException {
		List<String> listpid =null;
		if(permission!=null){
			listpid = Arrays.asList(permission);
		}
		backGroundLoginDao.deletesyspermissionrole(roleid);
		List listp = listpid;
		List list =new ArrayList();
		if(listp!=null&&listp.size()>0){
			for(int i=0;i<listp.size();i++){
				Map maplist=new HashMap();
				maplist.put("sys_role_id", roleid);
				maplist.put("sys_permission_id", listp.get(i));
				list.add(maplist);
			}
			backGroundLoginDao.insertpermissionrole(list);
		}

		Map map=new HashMap();
		map.put("roleid",roleid);
		map.put("name",name);
		backGroundLoginDao.updaterole(map);
		response.sendRedirect("allrole");
	}





	/**
	 * 删除角色
	 */
	@RequestMapping("delterole")
	@RequiresPermissions("bg:allrole")
	public void updaterole(HttpServletResponse response,Integer roleid) throws IOException {
		System.out.println(roleid);
		backGroundLoginDao.deletesyspermissionrole(roleid);
		backGroundLoginDao.deletesysbgrolebyroleid(roleid);
		backGroundLoginDao.deleterole(roleid);
		response.sendRedirect("allrole");
	}




	/**
	 * 检查角色名
	 * @param name
	 * @return
	 */
	@RequestMapping("checkrolename")
	public ResponseEntity checkrolename(String name,Integer roleid) {
		Map map=new HashMap();
		map.put("name", name);
		map.put("roleid", roleid);
		Integer count = backGroundLoginDao.checkrolename(map);
		String json = null;
		if (count>0) {
			json = "nook";
		} else {
			json = "ok";
		}
		return ResponseEntity.ok(json);
	}


	/**
	 * 修改用户角色
	 */
	@RequestMapping("updatebgrole")
	@RequiresPermissions("bg:allrole")
	public ResponseEntity updatebgrole(Integer backgid,Integer roleid) {
		Map map=new HashMap();
		map.put("backgid", backgid);
		map.put("roleid", roleid);
		backGroundLoginDao.deletesysbgrole(backgid);
		SysBgRole sbr=new SysBgRole();
		sbr.setSys_bg_id(backgid);
		sbr.setSys_role_id(roleid);
		backGroundLoginDao.insertsysbgrole(sbr);
		return ResponseEntity.ok(1);
	}



	/**
	 * 退出账号
	 */
	@RequestMapping("logout")
	public void logout(HttpServletResponse response) throws IOException {
		SecurityUtils.getSubject().logout();
		response.sendRedirect("/manager/login");
	}

}
