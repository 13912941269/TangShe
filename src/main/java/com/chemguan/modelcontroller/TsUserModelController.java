package com.chemguan.modelcontroller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: TsUserModelController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Controller
@RequestMapping("manager/ts/user")
public class TsUserModelController {


    @RequiresPermissions("bg:tsuser")
    @GetMapping
    public ModelAndView findall() {
        return new ModelAndView("manager/tsuserfindall");
    }
}
