package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.ActivityBrand;
import com.chemguan.service.ActivityBrandService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.web.bind.annotation.*;
import io.swagger.annotations.*;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

/**
 * @Title: ActivityBrandController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@RestController
@RequestMapping("/activity/brand")
public class ActivityBrandController {
    @Autowired
    private ActivityBrandService ActivityBrandService;

    @RequiresPermissions("bg:activitybrand")
    @PostMapping
    @ApiOperation("添加ActivityBrand")
    public Result add(ActivityBrand ActivityBrand) {
        ActivityBrandService.save(ActivityBrand);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:activitybrand")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除ActivityBrand")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        ActivityBrandService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:activitybrand")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改ActivityBrand")
    public Result update(ActivityBrand ActivityBrand) {
        ActivityBrandService.update(ActivityBrand);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:activitybrand")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("ActivityBrand根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        ActivityBrand ActivityBrand = ActivityBrandService.findById(id);
        return ResultGenerator.genSuccessResult(ActivityBrand);
    }


    @RequiresPermissions("bg:activitybrand")
    @GetMapping
    @ApiOperation("ActivityBrand分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(ActivityBrand.class);
        Example.Criteria criteria = condition.createCriteria();
        List<ActivityBrand> list = ActivityBrandService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
