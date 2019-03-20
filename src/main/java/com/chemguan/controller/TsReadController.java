package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.TsRead;
import com.chemguan.service.TsReadService;
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
 * @Title: TsReadController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@RestController
@RequestMapping("/ts/read")
public class TsReadController {
    @Autowired
    private TsReadService TsReadService;

    @RequiresPermissions("bg:tsread")
    @PostMapping
    @ApiOperation("添加TsRead")
    public Result add(TsRead TsRead) {
        TsReadService.save(TsRead);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:tsread")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除TsRead")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        TsReadService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tsread")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改TsRead")
    public Result update(TsRead TsRead) {
        TsReadService.update(TsRead);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tsread")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("TsRead根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        TsRead TsRead = TsReadService.findById(id);
        return ResultGenerator.genSuccessResult(TsRead);
    }


    @RequiresPermissions("bg:tsread")
    @GetMapping
    @ApiOperation("TsRead分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(TsRead.class);
        Example.Criteria criteria = condition.createCriteria();
        List<TsRead> list = TsReadService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
