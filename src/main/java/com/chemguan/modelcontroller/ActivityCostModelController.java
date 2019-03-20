package com.chemguan.modelcontroller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: ActivityCostModelController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Controller
@RequestMapping("manager/activity/cost")
public class ActivityCostModelController {


    @RequiresPermissions("bg:activitycost")
    @GetMapping
    public ModelAndView findall() {
        return new ModelAndView("manager/activitycostfindall");
    }
}
