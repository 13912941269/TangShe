package com.chemguan.util;

import com.chemguan.background.model.ShiroUser;
import org.apache.shiro.SecurityUtils;

public class ShiroExt {


    public static ShiroUser getSubjectInfo() {
        ShiroUser shirouser=(ShiroUser)SecurityUtils.getSubject().getPrincipal();
        return shirouser;
    }

}
