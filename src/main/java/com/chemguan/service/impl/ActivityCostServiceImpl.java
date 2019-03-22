package com.chemguan.service.impl;

import com.chemguan.dao.ActivityCostRepository;
import com.chemguan.entity.ActivityCost;
import com.chemguan.service.ActivityCostService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: ActivityCostServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Service
@Transactional
public class ActivityCostServiceImpl extends AbstractService<ActivityCost> implements ActivityCostService {
    @Autowired
    private ActivityCostRepository ActivityCostRepository;

    @Override
    public void insertactcost(ActivityCost activityCost) {
        ActivityCostRepository.insertactcost(activityCost);
    }
}
