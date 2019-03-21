package com.chemguan.dao;

import com.chemguan.business.core.mapper.Mapper;
import com.chemguan.entity.TsActivity;

import java.util.List;
import java.util.Map;

/**
 * @Title: TsActivityRepository
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@org.apache.ibatis.annotations.Mapper
public interface TsActivityRepository extends Mapper<TsActivity> {

    List<TsActivity> findByMap(Map map);

    void insertactivity(TsActivity tsActivity);
}

