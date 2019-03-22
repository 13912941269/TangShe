package com.chemguan.service.impl;

import com.chemguan.dao.ActivitySignRepository;
import com.chemguan.entity.ActivitySign;
import com.chemguan.service.ActivitySignService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: ActivitySignServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Service
@Transactional
public class ActivitySignServiceImpl extends AbstractService<ActivitySign> implements ActivitySignService {
    @Autowired
    private ActivitySignRepository ActivitySignRepository;

    @Override
    public ActivitySign findbyactid(Integer activityId) {
        return ActivitySignRepository.findbyactid(activityId);
    }

    @Override
    public void insertactsign(ActivitySign activitySign) {
        ActivitySignRepository.insertactsign(activitySign);
    }
}
