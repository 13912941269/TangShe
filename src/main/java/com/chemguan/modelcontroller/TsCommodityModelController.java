package com.chemguan.modelcontroller;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: TsCommodityModelController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Controller
@RequestMapping("manager/ts/allcommodity")
public class TsCommodityModelController {

    @RequiresPermissions("bg:allcommodity")
    @GetMapping
    public ModelAndView findall() {
        return new ModelAndView("manager/tscommodityfindall");
    }

}
