package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.TsSign;
import com.chemguan.service.TsSignService;
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
 * @Title: TsSignController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@RestController
@RequestMapping("/ts/sign")
public class TsSignController {
    @Autowired
    private TsSignService TsSignService;

    @RequiresPermissions("bg:tssign")
    @PostMapping
    @ApiOperation("添加TsSign")
    public Result add(TsSign TsSign) {
        TsSignService.save(TsSign);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:tssign")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除TsSign")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        TsSignService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tssign")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改TsSign")
    public Result update(TsSign TsSign) {
        TsSignService.update(TsSign);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tssign")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("TsSign根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        TsSign TsSign = TsSignService.findById(id);
        return ResultGenerator.genSuccessResult(TsSign);
    }


    @RequiresPermissions("bg:tssign")
    @GetMapping
    @ApiOperation("TsSign分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(TsSign.class);
        Example.Criteria criteria = condition.createCriteria();
        List<TsSign> list = TsSignService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
