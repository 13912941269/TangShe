package com.chemguan.dao;

import com.chemguan.business.core.mapper.Mapper;
import com.chemguan.entity.ActivityCost;

/**
 * @Title: ActivityCostRepository
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@org.apache.ibatis.annotations.Mapper
public interface ActivityCostRepository extends Mapper<ActivityCost> {

    void insertactcost(ActivityCost activityCost);
}

