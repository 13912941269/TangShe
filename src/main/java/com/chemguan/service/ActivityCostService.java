package com.chemguan.service;
import com.chemguan.entity.ActivityCost;
import com.chemguan.business.core.service.Service;


/**
 * @Title: ActivityCostService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
public interface ActivityCostService extends Service<ActivityCost> {

    void insertactcost(ActivityCost activityCost);
}
