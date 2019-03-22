package com.chemguan.mobilecontroller.shicontroller;

import com.chemguan.business.core.results.Result;
import com.chemguan.business.core.results.ResultGenerator;
import com.chemguan.entity.ActivityCost;
import com.chemguan.entity.ActivitySign;
import com.chemguan.entity.SignTag;
import com.chemguan.entity.TsActivity;
import com.chemguan.service.ActivityCostService;
import com.chemguan.service.ActivitySignService;
import com.chemguan.service.SignTagService;
import com.chemguan.service.TsActivityService;
import com.chemguan.util.Position;
import io.swagger.models.auth.In;
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
import java.util.List;
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

    @Autowired
    private SignTagService signTagService;


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

        //判断是否设置报名
        ActivitySign activitySign = activitySignService.findbyactid(activityId);
        if(activitySign!=null){
            tsActivity.setSetSignType(1);
        }

        //判断是否设置费用
        List<ActivityCost> listCost = activityCostService.findbyactid(activityId);
        if(listCost==null||listCost.size()==0){
            tsActivity.setSetCostType(1);
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
    @RequestMapping("edittitleimgpage")
    public ModelAndView editTitleImgPage(Integer activityId) {
        ModelAndView mv=new ModelAndView();
        TsActivity tsActivity = tsActivityService.findById(activityId);
        mv.addObject("tsActivity",tsActivity);
        mv.setViewName("");
        return mv;
    }


    /**
     * 设置封面
     * activityId
     * imgs 图片集合
     * type 1:封面图片 2:详情图片
     * @return
     */
    @RequestMapping("edittitleimg")
    public Result editTitleImg(Integer activityId, String imgs,Integer type) {
        if(activityId==null){
            return ResultGenerator.genFailResult("活动参数为空！");
        }
        TsActivity tsActivity = tsActivityService.findById(activityId);
        if(type==1){
            tsActivity.setTitleImg(imgs);
        }else{
            tsActivity.setContentImg(imgs);
        }
        tsActivityService.update(tsActivity);
        return ResultGenerator.genSuccessResult(activityId);
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
        //查询已添加的报名设置信息
        List<ActivityCost> costList = activityCostService.findbyactid(activityId);
        mv.addObject("costList",costList);
        mv.addObject("activityId",activityId);
        mv.setViewName("");
        return mv;
    }


    /**
     * 添加报名费用
     * @return
     */
    @RequestMapping("addactcost")
    public Result addActCost(Integer activityId,String[] costId,String[] costMoney,String[] costName,String[] costNum) {
        if(activityId==null){
            return ResultGenerator.genFailResult("参数有误！");
        }
        if(costMoney==null||costName==null||costNum==null){
            return ResultGenerator.genFailResult("报名参数有误！");
        }
        for(int i=0;i<costName.length;i++){
            ActivityCost activityCost = activityCostService.findById(Integer.parseInt(costId[i]));
            if(activityCost==null){
                activityCost=new ActivityCost();
                activityCost.setCostName(costName[i]);
                activityCost.setCostNum(Integer.parseInt(costNum[i]));
                activityCost.setCostMoney(Double.parseDouble(costMoney[i]));
                activityCost.setActivityId(activityId);
                activityCostService.save(activityCost);
            }else{
                activityCost.setCostName(costName[i]);
                activityCost.setCostNum(Integer.parseInt(costNum[i]));
                activityCost.setCostMoney(Double.parseDouble(costMoney[i]));
                activityCost.setActivityId(activityId);
                activityCostService.update(activityCost);
            }
        }
        return ResultGenerator.genSuccessResult(activityId);
    }





    /**
     * 报名设置页面
     * @return
     */
    @RequestMapping("addactsignpage")
    public ModelAndView addActSignPage(Integer activityId) {
        ModelAndView mv=new ModelAndView();
        //查询报名设置
        if(activityId!=null){
            ActivitySign activitySign = activitySignService.findbyactid(activityId);
            mv.addObject("activitySign",activitySign);
            //查询报名设置标签
            if(activitySign!=null){
                List<SignTag> signList = signTagService.findbysign(activitySign.getSignId());
                mv.addObject("signList",signList);
            }
        }
        mv.addObject("activityId",activityId);
        mv.setViewName("");
        return mv;
    }




    /**
     * 添加报名设置
     * activityId 活动id
     * activitySign 报名参数
     * tags 标签
     * @return
     */
    @RequestMapping("addactsign")
    public Result addActSign(Integer activityId,ActivitySign activitySign,String[] tags) {
        if(activityId==null){
            return ResultGenerator.genFailResult("活动参数为空！");
        }
        if(activitySign.getSignId()!=null){
            activitySignService.update(activitySign);
        }else{
            activitySign.setActivityId(activityId);
            activitySignService.insertactsign(activitySign);
        }
        signTagService.deletebysign(activitySign.getSignId());
        if(tags!=null){
            for(int i=0;i<tags.length;i++){
                SignTag signTag=new SignTag();
                signTag.setTagName(tags[i]);
                signTag.setSignId(activitySign.getSignId());
                signTagService.save(signTag);
            }
        }
        return ResultGenerator.genSuccessResult(activityId);
    }





}
