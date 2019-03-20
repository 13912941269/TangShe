package com.chemguan.modelcontroller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: TsOrderModelController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Controller
@RequestMapping("manager/ts/order")
public class TsOrderModelController {


    @RequiresPermissions("bg:tsorder")
    @GetMapping
    public ModelAndView findall() {
        return new ModelAndView("manager/tsorderfindall");
    }
}
