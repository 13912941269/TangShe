package com.chemguan.mobilecontroller.shicontroller;

import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.TsActivity;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @Title: TsUserModelController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Controller
@RequestMapping("mobile")
public class ActivityController {


    /**
     * 添加活动页面
     * @return
     */
    @RequestMapping("addactpage")
    public ModelAndView addActPage() {

        return new ModelAndView("");
    }


    /**
     * 添加活动
     * @param activityTitle 活动名称
     * @param activityAddress   活动地址
     * @param startTime 开始时间
     * @param signTime  截止报名时间
     * @param endTime   结束时间
     * @param columName 活动类别
     * @param titleImg   封面图
     * @param contentImg    内容图片
     * @param costIds   报名费用id集合
     * @param signId    报名设置id
     * @param commentType   是否允许评论
     * @return
     */
    @RequestMapping("addact")
    public Result addAct(String activityTitle,
                         String activityAddress,
                         String startTime,
                         String signTime,
                         String endTime,
                         String columName,
                         String titleImg,
                         String contentImg,
                         String costIds,
                         Integer signId,
                         Integer commentType
                               ) {

        if(StringUtils.isEmpty(activityTitle)){
            return ResultGenerator.genFailResult("请输入活动主题内容");
        }

        if(StringUtils.isEmpty(activityAddress)){
            return ResultGenerator.genFailResult("请输入活动地址");
        }

        if(StringUtils.isEmpty(startTime)){
            return ResultGenerator.genFailResult("请选择活动开始时间");
        }

        if(StringUtils.isEmpty(signTime)){
            return ResultGenerator.genFailResult("请选择活动报名截止时间");
        }

        if(StringUtils.isEmpty(endTime)){
            return ResultGenerator.genFailResult("请选择活动结束时间");
        }

        if(StringUtils.isEmpty(columName)){
            return ResultGenerator.genFailResult("请选择活动类别");
        }

        if(StringUtils.isEmpty(titleImg)){
            return ResultGenerator.genFailResult("请上传活动封面图");
        }

        if(StringUtils.isEmpty(contentImg)){
            return ResultGenerator.genFailResult("请上传活动内容");
        }

        if(StringUtils.isEmpty(costIds)){
            return ResultGenerator.genFailResult("请设置活动费用");
        }

        if(signId==null){
            return ResultGenerator.genFailResult("请设置报名信息");
        }

        TsActivity tsActivity=new TsActivity();


        return ResultGenerator.genSuccessResult();
    }
}
