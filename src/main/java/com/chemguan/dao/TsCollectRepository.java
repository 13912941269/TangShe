package com.chemguan.dao;

import com.chemguan.business.core.mapper.Mapper;
import com.chemguan.entity.TsCollect;

import java.util.Map;

/**
 * @Title: TsCollectRepository
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@org.apache.ibatis.annotations.Mapper
public interface TsCollectRepository extends Mapper<TsCollect> {

    Integer findcollectcount(Map map);
}

