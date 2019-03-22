package com.chemguan.mobilecontroller.shicontroller;

import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.ActivityCost;
import com.chemguan.entity.ActivitySign;
import com.chemguan.entity.TsActivity;
import com.chemguan.service.ActivityCostService;
import com.chemguan.service.ActivitySignService;
import com.chemguan.service.TsActivityService;
import com.chemguan.util.Position;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

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

    @Autowired
    private TsActivityService tsActivityService;

    @Autowired
    private ActivityCostService activityCostService;

    @Autowired
    private ActivitySignService activitySignService;


    /**
     * 添加/修改 活动页面
     * activityId   活动id
     * @return
     */
    @RequestMapping("editactpage")
    public ModelAndView editActPage(HttpServletRequest request,Integer activityId) {
        Integer userId = (Integer) request.getSession().getAttribute("userid");
        ModelAndView mv=new ModelAndView();
        TsActivity tsActivity =null;
        if(activityId==null){
            tsActivity=tsActivityService.findbysaveact(userId);
            if(tsActivity==null){
                tsActivity=new TsActivity();
                tsActivity.setUserId(userId);
                tsActivity.setAddtime(new Date());
                tsActivity.setActivityState(0);
                tsActivityService.insertactivity(tsActivity);
            }
        }else{
            tsActivity=tsActivityService.findById(activityId);
        }
        mv.addObject("tsActivity",tsActivity);

        mv.setViewName("");
        return mv;
    }





    /**
     * 设置封面页面
     * activityId
     * @return
     */
    @RequestMapping("edittitleimg")
    public ModelAndView editTitleImg(Integer activityId,String costIds,Integer signId) {
        ModelAndView mv=new ModelAndView();

        mv.setViewName("");
        return mv;
    }






    /**
     * 发布活动
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
    public Result addAct(HttpServletRequest request,
                         Integer activityId,
                         String activityTitle,
                         String activityAddress,
                         String startTime,
                         String signTime,
                         String endTime,
                         String columName,
                         String titleImg,
                         String contentImg,
                         String costIds,
                         Integer signId,
                         Integer commentType,
                         Integer scoreSwitch,
                         Integer explosionSwitch,
                         Integer groupSwitch) throws ParseException {
        Integer userId = (Integer) request.getSession().getAttribute("userid");
        SimpleDateFormat sim=new SimpleDateFormat("yyyy-MM-dd HH:mm");

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

        TsActivity tsActivity=tsActivityService.findById(activityId);

        tsActivity.setActivityTitle(activityTitle);
        tsActivity.setActivityAddress(activityAddress);
        tsActivity.setStartTime(sim.parse(startTime));
        tsActivity.setSignTime(sim.parse(signTime));
        tsActivity.setEndTime(sim.parse(endTime));
        tsActivity.setUserId(userId);
        tsActivity.setAddtime(new Date());
        tsActivity.setColumName(columName);
        tsActivity.setContentImg(contentImg);
        tsActivity.setTitleImg(titleImg);
        tsActivity.setCommentType(commentType);
        tsActivity.setScoreSwitch(scoreSwitch);
        tsActivity.setExplosionSwitch(explosionSwitch);
        tsActivity.setGroupSwitch(groupSwitch);

        //根据地址计算经纬度
        Map mapAddress = Position.getCoordinate(activityAddress);
        if(mapAddress!=null){
            tsActivity.setActivityLng((String)mapAddress.get("lng"));
            tsActivity.setActivityLat((String)mapAddress.get("lat"));
        }

        tsActivityService.insertactivity(tsActivity);


        //更新报名费用表
        ActivitySign activitySign = activitySignService.findById(signId);
        if(activitySign!=null){
            activitySign.setActivityId(tsActivity.getActivityId());
            activitySignService.update(activitySign);
        }

        //更新报名设置表
        String[] split = costIds.split(",");
        for(int i=0;i<split.length;i++){
            ActivityCost activityCost = activityCostService.findById(split[i]);
            if(activityCost!=null){
                activityCost.setActivityId(tsActivity.getActivityId());
                activityCostService.update(activityCost);
            }
        }

        return ResultGenerator.genSuccessResult();
    }






    /**
     * 添加报名费用页面
     * @return
     */
    @RequestMapping("addactcostpage")
    public ModelAndView addActCostPage(Integer activityId) {
        ModelAndView mv=new ModelAndView();
        mv.addObject("activityId",activityId);
        mv.setViewName("");
        return mv;
    }


    /**
     * 添加报名费用
     * @return
     */
    @RequestMapping("addactcost")
    public Result addActCost(ActivityCost activityCost) {
        if(activityCost.getActivityId()==null){
            return ResultGenerator.genFailResult("未关联活动id！");
        }

        if(activityCost.getCostMoney()==null){
            return ResultGenerator.genFailResult("金额不得为空！");
        }

        if(StringUtils.isEmpty(activityCost.getCostName())){
            return ResultGenerator.genFailResult("门票名称不得为空！");
        }

        if(activityCost.getCostNum()==null){
            return ResultGenerator.genFailResult("人数限制不得为空！");
        }
        activityCostService.save(activityCost);
        return ResultGenerator.genSuccessResult(activityCost.getActivityId());
    }







}
