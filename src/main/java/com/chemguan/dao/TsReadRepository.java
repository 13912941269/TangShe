package com.chemguan.dao;

import com.chemguan.business.core.mapper.Mapper;
import com.chemguan.entity.TsRead;

/**
 * @Title: TsReadRepository
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@org.apache.ibatis.annotations.Mapper
public interface TsReadRepository extends Mapper<TsRead> {

    Integer findreadcount(Integer activityId);
}

