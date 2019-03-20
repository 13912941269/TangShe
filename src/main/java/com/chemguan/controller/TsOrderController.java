package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.TsOrder;
import com.chemguan.service.TsOrderService;
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
 * @Title: TsOrderController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@RestController
@RequestMapping("/ts/order")
public class TsOrderController {
    @Autowired
    private TsOrderService TsOrderService;

    @RequiresPermissions("bg:tsorder")
    @PostMapping
    @ApiOperation("添加TsOrder")
    public Result add(TsOrder TsOrder) {
        TsOrderService.save(TsOrder);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:tsorder")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除TsOrder")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        TsOrderService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tsorder")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改TsOrder")
    public Result update(TsOrder TsOrder) {
        TsOrderService.update(TsOrder);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:tsorder")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("TsOrder根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        TsOrder TsOrder = TsOrderService.findById(id);
        return ResultGenerator.genSuccessResult(TsOrder);
    }


    @RequiresPermissions("bg:tsorder")
    @GetMapping
    @ApiOperation("TsOrder分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(TsOrder.class);
        Example.Criteria criteria = condition.createCriteria();
        List<TsOrder> list = TsOrderService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
