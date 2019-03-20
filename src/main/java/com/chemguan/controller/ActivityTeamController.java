package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.ActivityTeam;
import com.chemguan.service.ActivityTeamService;
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
 * @Title: ActivityTeamController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@RestController
@RequestMapping("/activity/team")
public class ActivityTeamController {
    @Autowired
    private ActivityTeamService ActivityTeamService;

    @RequiresPermissions("bg:activityteam")
    @PostMapping
    @ApiOperation("添加ActivityTeam")
    public Result add(ActivityTeam ActivityTeam) {
        ActivityTeamService.save(ActivityTeam);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:activityteam")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除ActivityTeam")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        ActivityTeamService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:activityteam")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改ActivityTeam")
    public Result update(ActivityTeam ActivityTeam) {
        ActivityTeamService.update(ActivityTeam);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:activityteam")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("ActivityTeam根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        ActivityTeam ActivityTeam = ActivityTeamService.findById(id);
        return ResultGenerator.genSuccessResult(ActivityTeam);
    }


    @RequiresPermissions("bg:activityteam")
    @GetMapping
    @ApiOperation("ActivityTeam分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(ActivityTeam.class);
        Example.Criteria criteria = condition.createCriteria();
        List<ActivityTeam> list = ActivityTeamService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
