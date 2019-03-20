package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.OrderLog;
import com.chemguan.service.OrderLogService;
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
 * @Title: OrderLogController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@RestController
@RequestMapping("/order/log")
public class OrderLogController {
    @Autowired
    private OrderLogService OrderLogService;

    @RequiresPermissions("bg:orderlog")
    @PostMapping
    @ApiOperation("添加OrderLog")
    public Result add(OrderLog OrderLog) {
        OrderLogService.save(OrderLog);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:orderlog")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除OrderLog")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        OrderLogService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:orderlog")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改OrderLog")
    public Result update(OrderLog OrderLog) {
        OrderLogService.update(OrderLog);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:orderlog")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("OrderLog根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        OrderLog OrderLog = OrderLogService.findById(id);
        return ResultGenerator.genSuccessResult(OrderLog);
    }


    @RequiresPermissions("bg:orderlog")
    @GetMapping
    @ApiOperation("OrderLog分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(OrderLog.class);
        Example.Criteria criteria = condition.createCriteria();
        List<OrderLog> list = OrderLogService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
