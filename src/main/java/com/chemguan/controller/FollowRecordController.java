package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.FollowRecord;
import com.chemguan.service.FollowRecordService;
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
 * @Title: FollowRecordController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@RestController
@RequestMapping("/follow/record")
public class FollowRecordController {
    @Autowired
    private FollowRecordService FollowRecordService;

    @RequiresPermissions("bg:followrecord")
    @PostMapping
    @ApiOperation("添加FollowRecord")
    public Result add(FollowRecord FollowRecord) {
        FollowRecordService.save(FollowRecord);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:followrecord")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除FollowRecord")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        FollowRecordService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:followrecord")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改FollowRecord")
    public Result update(FollowRecord FollowRecord) {
        FollowRecordService.update(FollowRecord);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:followrecord")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("FollowRecord根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        FollowRecord FollowRecord = FollowRecordService.findById(id);
        return ResultGenerator.genSuccessResult(FollowRecord);
    }


    @RequiresPermissions("bg:followrecord")
    @GetMapping
    @ApiOperation("FollowRecord分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(FollowRecord.class);
        Example.Criteria criteria = condition.createCriteria();
        List<FollowRecord> list = FollowRecordService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
