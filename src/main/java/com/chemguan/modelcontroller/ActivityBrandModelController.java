package com.chemguan.modelcontroller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: ActivityBrandModelController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Controller
@RequestMapping("manager/activity/brand")
public class ActivityBrandModelController {


    @RequiresPermissions("bg:activitybrand")
    @GetMapping
    public ModelAndView findall() {
        return new ModelAndView("manager/activitybrandfindall");
    }
}
