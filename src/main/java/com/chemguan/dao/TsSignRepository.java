package com.chemguan.dao;

import com.chemguan.business.core.mapper.Mapper;
import com.chemguan.entity.TsSign;
import io.swagger.models.auth.In;

import java.util.List;

/**
 * @Title: TsSignRepository
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@org.apache.ibatis.annotations.Mapper
public interface TsSignRepository extends Mapper<TsSign> {

    List<TsSign> findcountbyactid(Integer activityId);
}

