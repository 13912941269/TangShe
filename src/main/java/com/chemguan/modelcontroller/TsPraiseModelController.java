package com.chemguan.modelcontroller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: TsPraiseModelController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Controller
@RequestMapping("manager/ts/praise")
public class TsPraiseModelController {


    @RequiresPermissions("bg:tspraise")
    @GetMapping
    public ModelAndView findall() {
        return new ModelAndView("manager/tspraisefindall");
    }
}
