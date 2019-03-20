package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.SignTagval;
import com.chemguan.service.SignTagvalService;
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
 * @Title: SignTagvalController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@RestController
@RequestMapping("/sign/tagval")
public class SignTagvalController {
    @Autowired
    private SignTagvalService SignTagvalService;

    @RequiresPermissions("bg:signtagval")
    @PostMapping
    @ApiOperation("添加SignTagval")
    public Result add(SignTagval SignTagval) {
        SignTagvalService.save(SignTagval);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:signtagval")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除SignTagval")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        SignTagvalService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:signtagval")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改SignTagval")
    public Result update(SignTagval SignTagval) {
        SignTagvalService.update(SignTagval);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:signtagval")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("SignTagval根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        SignTagval SignTagval = SignTagvalService.findById(id);
        return ResultGenerator.genSuccessResult(SignTagval);
    }


    @RequiresPermissions("bg:signtagval")
    @GetMapping
    @ApiOperation("SignTagval分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(SignTagval.class);
        Example.Criteria criteria = condition.createCriteria();
        List<SignTagval> list = SignTagvalService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
