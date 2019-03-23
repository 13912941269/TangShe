package com.chemguan.dao;

import com.chemguan.business.core.mapper.Mapper;
import com.chemguan.entity.FollowRecord;
import io.swagger.models.auth.In;

/**
 * @Title: FollowRecordRepository
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@org.apache.ibatis.annotations.Mapper
public interface FollowRecordRepository extends Mapper<FollowRecord> {

    Integer findcountbyuid(Integer userId);
}

