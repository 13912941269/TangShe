package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.ActivitySign;
import com.chemguan.service.ActivitySignService;
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
 * @Title: ActivitySignController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@RestController
@RequestMapping("/activity/sign")
public class ActivitySignController {
    @Autowired
    private ActivitySignService ActivitySignService;

    @RequiresPermissions("bg:activitysign")
    @PostMapping
    @ApiOperation("添加ActivitySign")
    public Result add(ActivitySign ActivitySign) {
        ActivitySignService.save(ActivitySign);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:activitysign")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除ActivitySign")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        ActivitySignService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:activitysign")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改ActivitySign")
    public Result update(ActivitySign ActivitySign) {
        ActivitySignService.update(ActivitySign);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:activitysign")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("ActivitySign根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        ActivitySign ActivitySign = ActivitySignService.findById(id);
        return ResultGenerator.genSuccessResult(ActivitySign);
    }


    @RequiresPermissions("bg:activitysign")
    @GetMapping
    @ApiOperation("ActivitySign分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(ActivitySign.class);
        Example.Criteria criteria = condition.createCriteria();
        List<ActivitySign> list = ActivitySignService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
