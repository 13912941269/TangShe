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
public class MyOrganizerCenterController {

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
     * 我的主辦方活动
     */
    @RequestMapping("toOrganizerActivities")
    public ModelAndView toOrganizerActivities(HttpSession session,Integer timetype,Integer activitype) {
        ModelAndView mv = new ModelAndView();

        Integer userid = (Integer) session.getAttribute("userid");

        TsUser tsUser = tsUserService.findById(userid);

        //timetype:1:1个月2:3个月3:6个月4.6个月前,activitype:1.未開始2.進行中3.已截止4.已結束

        if(timetype==null){
            timetype = 0;
        }

        if(activitype==null){
            activitype = 0;
        }

        Date times = new Date();

        if(timetype==1){
            times = DateUtils.stepMonth(-1);
        }

        if(timetype==2){
            times = DateUtils.stepMonth(-3);
        }

        if(timetype==3){
            times = DateUtils.stepMonth(-6);
        }

        if(timetype==4){
            times = DateUtils.stepMonth(-6);
        }

            Condition condition = new Condition(ActivityTeam.class);
            Example.Criteria criteria = condition.createCriteria();
            criteria.andEqualTo("userId",userid);

            if(timetype!=0){
                if(timetype==4){
                    criteria.andLessThan("addtime",times);
                }
                if(timetype<4){
                    criteria.andGreaterThan("addtime",times);
                }
            }

            List<ActivityTeam> activityTeams = activityTeamService.findByCondition(condition);


        List<TsActivity> activityList = new ArrayList<>();

        if(activityTeams.size()>0){
            for (ActivityTeam s:activityTeams) {
                TsActivity activity = tsActivityService.findById(s.getActivityId());

                if(s.getUserRole()==1){
                    activity.setActiveUserType("创建者");
                }
                if(s.getUserRole()==2){
                    activity.setActiveUserType("管理者");
                }
                if(s.getUserRole()==3){
                    activity.setActiveUserType("观察者");
                }
                if(s.getUserRole()==4){
                    activity.setActiveUserType("主办方成员");
                }

                Condition condition3 = new Condition(TsSign.class);
                Example.Criteria criteria3 = condition3.createCriteria();
                criteria3.andEqualTo("activityId",s.getActivityId());
                List<TsSign> tsSignList = tsSignService.findByCondition(condition3);
                activity.setActivitySignCount(tsSignList.size());

                if(activitype==0){
                    activityList.add(activity);
                }

                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date now = new Date();

                String nowtime = fmt.format(now);
                //開始時間
                String begintime = fmt.format(activity.getStartTime());
                //結束時間
                String endtime = fmt.format(activity.getEndTime());
                //截止時間
                String signTime = fmt.format(activity.getSignTime());

                int start = compare_date(nowtime, begintime);
                int end = compare_date(endtime, nowtime);
                int sign = compare_date(signTime, nowtime);

                if(start==1){
                    if(end==1){
                        if(sign==1){
                            activity.setActiveType("进行中");
                            if(activitype==2){
                                activityList.add(activity);
                            }
                        }else{
                            activity.setActiveType("已截止");
                            if(activitype==3){
                                activityList.add(activity);
                            }
                        }
                    }else{
                        activity.setActiveType("已结束");
                        if(activitype==4){
                            activityList.add(activity);
                        }
                    }
                }else{
                    activity.setActiveType("未开始");
                    if(activitype==1){
                        activityList.add(activity);
                    }
                }

            }
        }

        mv.addObject("activityList",activityList);

        mv.setViewName("我的主辦方活动");

        return mv;
    }



    /*
     * 电子验票详情
     */
    @RequestMapping("toElectronicTicketInfo")
    public ModelAndView toElectronicTicketInfo(HttpSession session) {
        ModelAndView mv = new ModelAndView();

        Integer userid = (Integer) session.getAttribute("userid");

        TsUser tsUser = tsUserService.findById(userid);

        Condition condition = new Condition(ActivityTeam.class);
        Example.Criteria criteria = condition.createCriteria();
        criteria.andEqualTo("userId",userid);
        criteria.andEqualTo("userRole",1);

        List<ActivityTeam> activityTeams = activityTeamService.findByCondition(condition);


        List<TsActivity> activityList = new ArrayList<>();

        if(activityTeams.size()>0){
            for (ActivityTeam s:activityTeams) {
                TsActivity activity = tsActivityService.findById(s.getActivityId());

                if(s.getUserRole()==1){
                    activity.setActiveUserType("创建者");
                }
                if(s.getUserRole()==2){
                    activity.setActiveUserType("管理者");
                }
                if(s.getUserRole()==3){
                    activity.setActiveUserType("观察者");
                }
                if(s.getUserRole()==4){
                    activity.setActiveUserType("主办方成员");
                }

                Condition condition3 = new Condition(TsSign.class);
                Example.Criteria criteria3 = condition3.createCriteria();
                criteria3.andEqualTo("activityId",s.getActivityId());
                criteria3.andEqualTo("codeType",1);
                List<TsSign> tsSignList = tsSignService.findByCondition(condition3);
                //已验票人数
                activity.setActivitySignCount(tsSignList.size());


                DateFormat fmt = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                Date now = new Date();

                String nowtime = fmt.format(now);
                //開始時間
                String begintime = fmt.format(activity.getStartTime());
                //結束時間
                String endtime = fmt.format(activity.getEndTime());
                //截止時間
                String signTime = fmt.format(activity.getSignTime());

                int start = compare_date(nowtime, begintime);
                int end = compare_date(endtime, nowtime);
                int sign = compare_date(signTime, nowtime);

                if(start==1){
                    if(end==1){
                        if(sign==1){
                            activity.setActiveType("进行中");

                                activityList.add(activity);

                        }else{
                            activity.setActiveType("已截止");



                        }
                    }else{
                        activity.setActiveType("已结束");

                    }
                }else{
                    activity.setActiveType("未开始");

                }

            }
        }

        mv.addObject("activityList",activityList);

        mv.setViewName("电子验票");

        return mv;
    }



    /*
     * 扫码手动验票页
     */
    @RequestMapping("toElectronicPage")
    public ModelAndView toElectronicPage(HttpSession session,Integer activityId) {
        ModelAndView mv = new ModelAndView();


        mv.addObject("activityId",activityId);

        mv.setViewName("扫码手动验票页");

        return mv;
    }

    /*
     * 手动验票页
     */
    @RequestMapping("toManualPage")
    public ModelAndView toManualPage(HttpSession session,Integer activityId) {
        ModelAndView mv = new ModelAndView();


        mv.addObject("activityId",activityId);

        mv.setViewName("手动验票");

        return mv;
    }

    /*
     * 手动验票接口
     */
    @RequestMapping("toManualTicket")
    public ModelAndView toManualTicket(HttpSession session,Integer activityId,String signinfo) {
        ModelAndView mv = new ModelAndView(new MappingJackson2JsonView());
        Integer userid = (Integer) session.getAttribute("userid");

        Condition condition3 = new Condition(TsSign.class);
        Example.Criteria criteria3 = condition3.createCriteria();
        criteria3.andEqualTo("activityId",activityId);
        criteria3.andEqualTo("codeType",0);
        criteria3.andEqualTo("payType",1);
        criteria3.andEqualTo("signCode",signinfo).orEqualTo("signNum",signinfo).orEqualTo("cusPhone",signinfo);
        List<TsSign> tsSignList = tsSignService.findByCondition(condition3);

        if(tsSignList.size()>0){
            tsSignList.get(0).setCodeType(1);
            tsSignService.update(tsSignList.get(0));
        }

        //0:输入错误或已经验过1：验票成功
        mv.addObject("signcount",tsSignList);
        mv.addObject("signId",tsSignList.get(0).getSignId());

        return mv;
    }

    /*
     * 验票成功
     */
    @RequestMapping("toTicketSuccess")
    public ModelAndView toTicketSuccess(HttpSession session,Integer signId) {
        ModelAndView mv = new ModelAndView();
        Integer userid = (Integer) session.getAttribute("userid");

        TsSign tsSign = tsSignService.findById(signId);

        TsActivity tsActivity = tsActivityService.findById(tsSign.getActivityId());
        ActivityCost activityCost = activityCostService.findById(tsSign.getCostId());
        tsSign.setActivityCost(activityCost);
        tsSign.setTsActivity(tsActivity);

        mv.addObject("tsSign",tsSign);

        mv.setViewName("验票成功");

        return mv;
    }

}
