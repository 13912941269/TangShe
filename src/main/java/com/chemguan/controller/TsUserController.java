package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.TsUser;
import com.chemguan.service.TsUserService;
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
 * @Title: TsUserController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@RestController
@RequestMapping("/ts/user")
public class TsUserController {
    @Autowired
    private TsUserService TsUserService;

    @RequiresPermissions("bg:tsuser")
    @PostMapping
    @ApiOperation("添加TsUser")
    public Result add(TsUser TsUser) {
        TsUserService.save(TsUser);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:tsuser")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除TsUser")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        TsUserService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tsuser")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改TsUser")
    public Result update(TsUser TsUser) {
        TsUserService.update(TsUser);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tsuser")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("TsUser根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        TsUser TsUser = TsUserService.findById(id);
        return ResultGenerator.genSuccessResult(TsUser);
    }


    @RequiresPermissions("bg:tsuser")
    @GetMapping
    @ApiOperation("TsUser分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(TsUser.class);
        Example.Criteria criteria = condition.createCriteria();
        List<TsUser> list = TsUserService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
