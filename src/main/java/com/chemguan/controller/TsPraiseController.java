package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.TsPraise;
import com.chemguan.service.TsPraiseService;
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
 * @Title: TsPraiseController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@RestController
@RequestMapping("/ts/praise")
public class TsPraiseController {
    @Autowired
    private TsPraiseService TsPraiseService;

    @RequiresPermissions("bg:tspraise")
    @PostMapping
    @ApiOperation("添加TsPraise")
    public Result add(TsPraise TsPraise) {
        TsPraiseService.save(TsPraise);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:tspraise")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除TsPraise")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        TsPraiseService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tspraise")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改TsPraise")
    public Result update(TsPraise TsPraise) {
        TsPraiseService.update(TsPraise);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tspraise")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("TsPraise根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        TsPraise TsPraise = TsPraiseService.findById(id);
        return ResultGenerator.genSuccessResult(TsPraise);
    }


    @RequiresPermissions("bg:tspraise")
    @GetMapping
    @ApiOperation("TsPraise分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(TsPraise.class);
        Example.Criteria criteria = condition.createCriteria();
        List<TsPraise> list = TsPraiseService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
