package com.chemguan.dao;

import com.chemguan.business.core.mapper.Mapper;
import com.chemguan.entity.ActivitySign;

/**
 * @Title: ActivitySignRepository
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@org.apache.ibatis.annotations.Mapper
public interface ActivitySignRepository extends Mapper<ActivitySign> {

    ActivitySign findbyactid(Integer activityId);

    void insertactsign(ActivitySign activitySign);
}

