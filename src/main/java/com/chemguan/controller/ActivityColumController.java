package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.ActivityColum;
import com.chemguan.service.ActivityColumService;
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
 * @Title: ActivityColumController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@RestController
@RequestMapping("/activity/colum")
public class ActivityColumController {
    @Autowired
    private ActivityColumService ActivityColumService;

    @RequiresPermissions("bg:activitycolum")
    @PostMapping
    @ApiOperation("添加ActivityColum")
    public Result add(ActivityColum ActivityColum) {
        ActivityColumService.save(ActivityColum);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:activitycolum")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除ActivityColum")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        ActivityColumService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:activitycolum")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改ActivityColum")
    public Result update(ActivityColum ActivityColum) {
        ActivityColumService.update(ActivityColum);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:activitycolum")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("ActivityColum根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        ActivityColum ActivityColum = ActivityColumService.findById(id);
        return ResultGenerator.genSuccessResult(ActivityColum);
    }


    @RequiresPermissions("bg:activitycolum")
    @GetMapping
    @ApiOperation("ActivityColum分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(ActivityColum.class);
        Example.Criteria criteria = condition.createCriteria();
        List<ActivityColum> list = ActivityColumService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
