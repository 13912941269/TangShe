package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.TsActivity;
import com.chemguan.service.TsActivityService;
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
 * @Title: TsActivityController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@RestController
@RequestMapping("/ts/activity")
public class TsActivityController {
    @Autowired
    private TsActivityService TsActivityService;

    @RequiresPermissions("bg:tsactivity")
    @PostMapping
    @ApiOperation("添加TsActivity")
    public Result add(TsActivity TsActivity) {
        TsActivityService.save(TsActivity);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:tsactivity")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除TsActivity")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        TsActivityService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tsactivity")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改TsActivity")
    public Result update(TsActivity TsActivity) {
        TsActivityService.update(TsActivity);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tsactivity")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("TsActivity根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        TsActivity TsActivity = TsActivityService.findById(id);
        return ResultGenerator.genSuccessResult(TsActivity);
    }


    @RequiresPermissions("bg:tsactivity")
    @GetMapping
    @ApiOperation("TsActivity分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(TsActivity.class);
        Example.Criteria criteria = condition.createCriteria();
        List<TsActivity> list = TsActivityService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
