package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.TsActivity;
import com.chemguan.entity.TsCommodity;
import com.chemguan.service.TsActivityService;
import com.chemguan.service.TsCommodityService;
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
 * @Title: TsCommodityController
 * @ProjectName
 * @Description: TODO
 * @author
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@RestController
@RequestMapping("/ts/commodity")
public class TsCommodityController {
    @Autowired
    private TsCommodityService tsCommodityService;


    @PostMapping
    @ApiOperation("添加TsCommodity")
    public Result add(TsCommodity tsCommodity) {
        tsCommodityService.save(tsCommodity);
        return ResultGenerator.genSuccessResult();
    }



    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除TsCommodity")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        tsCommodityService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }


    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改TsCommodity")
    public Result update(TsCommodity tsCommodity) {

        tsCommodityService.update(tsCommodity);
        return ResultGenerator.genSuccessResult();
    }


    @GetMapping("/{id:\\d+}")
    @ApiOperation("TsCommodity根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        TsCommodity tsCommodity = tsCommodityService.findById(id);
        return ResultGenerator.genSuccessResult(tsCommodity);
    }



    @GetMapping
    @ApiOperation("TsCommodity分页查询列表")
    public Result list(
            @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
            @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(TsCommodity.class);
        Example.Criteria criteria = condition.createCriteria();
        List<TsCommodity> list = tsCommodityService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
