package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.ActivityCost;
import com.chemguan.service.ActivityCostService;
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
 * @Title: ActivityCostController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@RestController
@RequestMapping("/activity/cost")
public class ActivityCostController {
    @Autowired
    private ActivityCostService ActivityCostService;

    @RequiresPermissions("bg:activitycost")
    @PostMapping
    @ApiOperation("添加ActivityCost")
    public Result add(ActivityCost ActivityCost) {
        ActivityCostService.save(ActivityCost);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:activitycost")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除ActivityCost")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        ActivityCostService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:activitycost")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改ActivityCost")
    public Result update(ActivityCost ActivityCost) {
        ActivityCostService.update(ActivityCost);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:activitycost")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("ActivityCost根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        ActivityCost ActivityCost = ActivityCostService.findById(id);
        return ResultGenerator.genSuccessResult(ActivityCost);
    }


    @RequiresPermissions("bg:activitycost")
    @GetMapping
    @ApiOperation("ActivityCost分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(ActivityCost.class);
        Example.Criteria criteria = condition.createCriteria();
        List<ActivityCost> list = ActivityCostService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
