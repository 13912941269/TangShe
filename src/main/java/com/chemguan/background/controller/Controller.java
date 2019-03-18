package com.chemguan.background.controller;

import com.chemguan.background.mapper.BackGroundLoginDao;
import com.chemguan.background.model.BackGroundLogin;
import com.chemguan.background.service.BackGroundService;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.business.core.service.Service;
import com.chemguan.util.Encodeutil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/alluser")
public class Controller {

    @Autowired
    private BackGroundService service;

    @Autowired
    private BackGroundLoginDao dao;

    /**
     * 查询
     */
    @GetMapping
    @ApiOperation("分页查询列表")
    public Result AllUser(@ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                          @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "2") Integer size) {
        PageHelper.startPage(page, size);
        //List<BackGroundLogin> list = service.findAll();
        List<BackGroundLogin> list = dao.selectAllBg();
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }


    @PostMapping
    @ApiOperation("添加")
    public Result add(BackGroundLogin backGroundLogin) {
        backGroundLogin.setPassword(Encodeutil.getMD5(backGroundLogin.getPassword()));
        service.save(backGroundLogin);
        return ResultGenerator.genSuccessResult();
    }

    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        service.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改")
    public Result update(BackGroundLogin backGroundLogin) {
        backGroundLogin.setPassword(Encodeutil.getMD5(backGroundLogin.getPassword()));
        service.update(backGroundLogin);
        return ResultGenerator.genSuccessResult();
    }

    @GetMapping("/{id:\\d+}")
    @ApiOperation("根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        BackGroundLogin backGroundLogin = service.findById(id);
        return ResultGenerator.genSuccessResult(backGroundLogin);
    }
}
