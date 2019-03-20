package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.TsCollect;
import com.chemguan.service.TsCollectService;
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
 * @Title: TsCollectController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@RestController
@RequestMapping("/ts/collect")
public class TsCollectController {
    @Autowired
    private TsCollectService TsCollectService;

    @RequiresPermissions("bg:tscollect")
    @PostMapping
    @ApiOperation("添加TsCollect")
    public Result add(TsCollect TsCollect) {
        TsCollectService.save(TsCollect);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:tscollect")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除TsCollect")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        TsCollectService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tscollect")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改TsCollect")
    public Result update(TsCollect TsCollect) {
        TsCollectService.update(TsCollect);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tscollect")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("TsCollect根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        TsCollect TsCollect = TsCollectService.findById(id);
        return ResultGenerator.genSuccessResult(TsCollect);
    }


    @RequiresPermissions("bg:tscollect")
    @GetMapping
    @ApiOperation("TsCollect分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(TsCollect.class);
        Example.Criteria criteria = condition.createCriteria();
        List<TsCollect> list = TsCollectService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
