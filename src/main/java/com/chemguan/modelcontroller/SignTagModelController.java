package com.chemguan.modelcontroller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: SignTagModelController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Controller
@RequestMapping("manager/sign/tag")
public class SignTagModelController {


    @RequiresPermissions("bg:signtag")
    @GetMapping
    public ModelAndView findall() {
        return new ModelAndView("manager/signtagfindall");
    }
}
