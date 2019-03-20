package com.chemguan.controller;
import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.SignTag;
import com.chemguan.service.SignTagService;
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
 * @Title: SignTagController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@RestController
@RequestMapping("/sign/tag")
public class SignTagController {
    @Autowired
    private SignTagService SignTagService;

    @RequiresPermissions("bg:signtag")
    @PostMapping
    @ApiOperation("添加SignTag")
    public Result add(SignTag SignTag) {
        SignTagService.save(SignTag);
        return ResultGenerator.genSuccessResult();
    }


    @RequiresPermissions("bg:signtag")
    @DeleteMapping("/{id:\\d+}")
    @ApiOperation("删除SignTag")
    public Result delete(@ApiParam(value = "id") @PathVariable  Integer id) {
        SignTagService.deleteById(id);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:signtag")
    @PutMapping("/{id:\\d+}")
    @ApiOperation("修改SignTag")
    public Result update(SignTag SignTag) {
        SignTagService.update(SignTag);
        return ResultGenerator.genSuccessResult();
    }

    @RequiresPermissions("bg:signtag")
    @GetMapping("/{id:\\d+}")
    @ApiOperation("SignTag根据id查询详情")
    public Result detail(@ApiParam(value = "id")@PathVariable Integer id) {
        SignTag SignTag = SignTagService.findById(id);
        return ResultGenerator.genSuccessResult(SignTag);
    }


    @RequiresPermissions("bg:signtag")
    @GetMapping
    @ApiOperation("SignTag分页查询列表")
    public Result list(
                        @ApiParam(value = "页数")@RequestParam(name = "page",defaultValue = "1") Integer page,
                        @ApiParam(value = "每页行数")@RequestParam(name = "size",defaultValue = "10") Integer size) {
        PageHelper.startPage(page, size);
        Condition condition=new Condition(SignTag.class);
        Example.Criteria criteria = condition.createCriteria();
        List<SignTag> list = SignTagService.findByCondition(condition);
        PageInfo pageInfo = new PageInfo(list);
        return ResultGenerator.genSuccessResult(pageInfo);
    }
}
