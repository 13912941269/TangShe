package com.chemguan.mobilecontroller.ruancontroller;


import com.chemguan.entity.*;
import com.chemguan.service.*;
import com.chemguan.util.DateUtils;
import com.chemguan.util.Tools;
import org.apache.commons.collections.map.HashedMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import tk.mybatis.mapper.entity.Condition;
import tk.mybatis.mapper.entity.Example;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;


/**
 * @Title: MyCenterController
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Controller
@RequestMapping("mobile")
public class MySignCenterController {

    @Autowired
    private TsUserService tsUserService;

    @Autowired
    private TsSignService tsSignService;

    @Autowired
    private TsActivityService tsActivityService;

    @Autowired
    private ActivityCostService activityCostService;

    @Autowired
    private TsCollectService tsCollectService;

    @Autowired
    private ActivityTeamService activityTeamService;

    @Autowired
    private TsOrderService tsOrderService;

    @RequestMapping("toMyCenter")
    public ModelAndView toMyCenter(HttpSession session,Integer centertype) {

        if(centertype==null){
            centertype = 1;
        }

        ModelAndView mv = new ModelAndView();

        Integer userid = (Integer) session.getAttribute("userid");

        TsUser tsUser = tsUserService.findById(userid);

        mv.addObject("tsUser", tsUser);
        //代付款
        mv.addObject("payCount", 0);
        //代验票
        mv.addObject("codeCount", 0);
        //已完成
        mv.addObject("completedCount", 0);

        if(centertype==0){
            mv.setViewName("参与者");

            String userphone = tsUser.getUserPhone();

            if(!StringUtils.isEmpty(userphone)){

                Condition condition = new Condition(TsSign.class);
                Example.Criteria criteria = condition.createCriteria();
                criteria.andEqualTo("cusPhone", userphone);
                criteria.andEqualTo("payType", 0);
                List<TsSign> list1 = tsSignService.findByCondition(condition);
                mv.addObject("payCount", list1.size());

                condition = new Condition(TsSign.class);
                Example.Criteria criteria2 = condition.createCriteria();
                criteria2.andEqualTo("cusPhone", userphone);
                criteria2.andEqualTo("codeType", 0);
                List<TsSign> list2 = tsSignService.findByCondition(condition);
                mv.addObject("codeCount", list2.size());

                condition = new Condition(TsSign.class);
                Example.Criteria criteria3 = condition.createCriteria();
                criteria3.andEqualTo("cusPhone", userphone);
                criteria3.andEqualTo("codeType", 1);
                List<TsSign> list3 = tsSignService.findByCondition(condition);
                mv.addObject("completedCount", list3.size());

            }

        }

        if(centertype==1){
            mv.setViewName("主办方");
        }

        return mv;
    }

    //进入用户资料
    @RequestMapping("toUserInfo")
    public ModelAndView toUserInfo(HttpSession session,Integer centertype) {

        if(centertype==null){
            centertype = 0;
        }

        ModelAndView mv = new ModelAndView();

        Integer userid = (Integer) session.getAttribute("userid");

        TsUser tsUser = tsUserService.findById(userid);

        mv.addObject("tsUser", tsUser);

        if(centertype==0){
            mv.setViewName("个人资料");
        }

        if(centertype==1){
            mv.setViewName("主办方资料");
        }

        return mv;
    }


    //验证号码是否已被使用
    @RequestMapping("checkPhone")
    public ModelAndView checkPhone(String phone) {

        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());

        Condition condition = new Condition(TsUser.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userPhone", phone);
        criteria.andIsNotNull("openid");
        List<TsUser> list = tsUserService.findByCondition(condition);

        mv.addObject("count", list.size());

        return mv;
    }


    /*
     * 短信发送
     */
    @RequestMapping("sendPhoneNum")
    public ModelAndView sendPhoneNum(HttpServletRequest request, HttpServletResponse response, String phone) throws IOException {
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());

        String phonecode = new Tools().randomcode(4);

        mv.addObject("code", 1);

        mv.addObject("phonecode", phonecode);

        return mv;
    }


    /*
     * 完善用户信息
     */
    @RequestMapping("bindUserInfo")
    public ModelAndView bindUserInfo(HttpServletRequest request, TsUser user) {
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());

        HttpSession session = request.getSession();
        Integer userid = (Integer) session.getAttribute("userid");

        user.setUserId(userid);

        tsUserService.update(user);

        return mv;
    }



    /*
     * 我参与的活动(報名)
     */
    @RequestMapping("toParticipatedActivities")
    public ModelAndView toParticipatedActivities(HttpSession session,Integer timetype,Integer othertype) {
        ModelAndView mv = new ModelAndView();

        Integer userid = (Integer) session.getAttribute("userid");

        TsUser tsUser = tsUserService.findById(userid);

        //timetype:1:3个月2:6个月3:12个月,othertype:1.待付款2.待验票3.已完成

        if(timetype==null){
            timetype = 0;
        }

        if(othertype==null){
            othertype = 0;
        }

        Date times = new Date();

        if(timetype==1){
            times = DateUtils.stepMonth(-3);
        }

        if(timetype==2){
            times = DateUtils.stepMonth(-6);
        }

        if(timetype==3){
            times = DateUtils.stepMonth(-12);
        }

        String userphone = tsUser.getUserPhone();

        List<TsSign> signList = new ArrayList<>();


        if(!StringUtils.isEmpty(userphone)){

            Condition condition = new Condition(TsSign.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("cusPhone", userphone);

            if(timetype!=0){
                criteria.andGreaterThan("addtime",times);
            }

            if(othertype==1){
                criteria.andEqualTo("payType", 0);
            }
            if(othertype==2){
                criteria.andEqualTo("codeType", 0);
            }
            if(othertype==3){
                criteria.andEqualTo("codeType", 1);
            }

            signList = tsSignService.findByCondition(condition);
        }


        List<TsActivity> activityList = new ArrayList<>();

        if(signList.size()>0){
            for (TsSign s:signList) {
                TsActivity activity = tsActivityService.findById(s.getActivityId());
                ActivityCost activityCost = activityCostService.findById(s.getCostId());


                if(s.getPayType()==0){
                    activity.setActiveUserType("待付款");
                }else{
                    if(s.getCodeType()==0){
                        activity.setActiveUserType("待验票");
                    }else{
                        activity.setActiveUserType("已完成");
                    }
                }

                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date now = new Date();

                String nowtime = fmt.format(now);
                String begintime = fmt.format(activity.getStartTime());
                String endtime = fmt.format(activity.getEndTime());

                int start = compare_date(nowtime, begintime);
                int end = compare_date(endtime, nowtime);

                if(start==1){

                    if(end==1){

                        activity.setActiveType("进行中");
                    }else{

                        activity.setActiveType("已结束");
                    }
                }else{

                    activity.setActiveType("未开始");
                }

               s.setActivityCost(activityCost);
                s.setTsActivity(activity);

            }
        }

        mv.addObject("signList",signList);

        mv.setViewName("我参与的活动");

        return mv;
    }



    //时间判断
    public int compare_date(String DATE1, String DATE2) {
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        try {
            Date dt1 = df.parse(DATE1);
            Date dt2 = df.parse(DATE2);
            if (dt1.getTime() > dt2.getTime()) {
                System.out.println("dt1 在dt2前");
                return 1;
            } else if (dt1.getTime() < dt2.getTime()) {
                System.out.println("dt1在dt2后");
                return -1;
            } else {
                return 0;
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        return 0;
    }



    /*
     * 我的关注
     */
    @RequestMapping("toMyFocus")
    public ModelAndView toMyFocus(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        Integer userid = (Integer) session.getAttribute("userid");

        Map map = new HashedMap();
        map.put("userid",userid);

        List<TsUser> userList = tsUserService.findByMap(map);

        for (TsUser u:userList) {

        }

        mv.setViewName("我的关注");

        return mv;
    }

    /*
     * 我的收藏
     */
    @RequestMapping("toMyCollection")
    public ModelAndView toMyCollection(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        Integer userid = (Integer) session.getAttribute("userid");

        Map map = new HashedMap();
        map.put("userid",userid);

        List<TsActivity> activityList = tsActivityService.findByMap(map);

        for (TsActivity a:activityList) {

            DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            Date now = new Date();

            String nowtime = fmt.format(now);
            String begintime = fmt.format(a.getStartTime());
            String endtime = fmt.format(a.getEndTime());

            int start = compare_date(nowtime, begintime);
            int end = compare_date(endtime, nowtime);

            if(start==1){

                if(end==1){

                    a.setActiveType("进行中");
                }else{

                    a.setActiveType("已结束");
                }
            }else{

                a.setActiveType("未开始");
            }

        }

        mv.setViewName("我的收藏");

        return mv;
    }

    /*
     * 取消收藏
     */
    @RequestMapping("toDelCollection")
    public ModelAndView toDelCollection(HttpSession session,Integer activityId) {
        ModelAndView mv = new ModelAndView();

        Integer userid = (Integer) session.getAttribute("userid");

        Condition condition2 = new Condition(TsCollect.class);
        Example.Criteria criteria2 = condition2.createCriteria();
        criteria2.andEqualTo("activityId", activityId);
        criteria2.andEqualTo("userId", userid);
        List<TsCollect> list = tsCollectService.findByCondition(condition2);

        for (TsCollect c:list) {
            tsCollectService.deleteById(c.getCollectId());
        }

        mv.setViewName("redirect:/mobile/toMyCollection");

        return mv;
    }


    /*
     * 报名详情
     */
    @RequestMapping("toSignInfo")
    public ModelAndView toMyCollection(HttpSession session,Integer signId) {
        ModelAndView mv = new ModelAndView();

        Integer userid = (Integer) session.getAttribute("userid");

        TsUser tsUser = tsUserService.findById(userid);

        TsSign tsSign = tsSignService.findById(signId);

        //门票种类
        ActivityCost activityCost = activityCostService.findById(tsSign.getCostId());

        tsSign.setActivityCost(activityCost);

        //活动
        TsActivity activity = tsActivityService.findById(tsSign.getActivityId());
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String nowtime = fmt.format(now);
        String begintime = fmt.format(activity.getStartTime());
        String endtime = fmt.format(activity.getEndTime());
        int start = compare_date(nowtime, begintime);
        int end = compare_date(endtime, nowtime);
        if(start==1){
            if(end==1){

                activity.setActiveType("进行中");
            }else{
                activity.setActiveType("已结束");
            }
        }else{

            activity.setActiveType("未开始");
        }
        tsSign.setTsActivity(activity);


        Condition condition = new Condition(TsOrder.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("signUid", signId);
        List<TsOrder> tsOrderList = tsOrderService.findByCondition(condition);

        if(tsOrderList.size()>0){
            tsSign.setTsOrder(tsOrderList.get(0));

        }


        mv.addObject("tsSign",tsSign);

        mv.setViewName("报名详情");

        return mv;
    }

    /*
     * 电子票信息
     */
    @RequestMapping("toEticket")
    public ModelAndView toEticket(HttpSession session,Integer signId) {
        ModelAndView mv = new ModelAndView();

        Integer userid = (Integer) session.getAttribute("userid");

        TsUser tsUser = tsUserService.findById(userid);

        TsSign tsSign = tsSignService.findById(signId);

        //门票种类
        ActivityCost activityCost = activityCostService.findById(tsSign.getCostId());

        tsSign.setActivityCost(activityCost);

        //活动
        TsActivity activity = tsActivityService.findById(tsSign.getActivityId());
        DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        Date now = new Date();
        String nowtime = fmt.format(now);
        String begintime = fmt.format(activity.getStartTime());
        String endtime = fmt.format(activity.getEndTime());
        int start = compare_date(nowtime, begintime);
        int end = compare_date(endtime, nowtime);
        if(start==1){
            if(end==1){

                activity.setActiveType("进行中");
            }else{
                activity.setActiveType("已结束");
            }
        }else{

            activity.setActiveType("未开始");
        }
        tsSign.setTsActivity(activity);


        Condition condition = new Condition(TsOrder.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("signUid", signId);
        List<TsOrder> tsOrderList = tsOrderService.findByCondition(condition);

        if(tsOrderList.size()>0){
            tsSign.setTsOrder(tsOrderList.get(0));

        }


        mv.addObject("tsSign",tsSign);

        mv.setViewName("电子票");

        return mv;
    }


}
