package com.chemguan.dao;

import com.chemguan.business.core.mapper.Mapper;
import com.chemguan.entity.TsUser;

import java.util.List;
import java.util.Map;

/**
 * @Title: TsUserRepository
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@org.apache.ibatis.annotations.Mapper
public interface TsUserRepository extends Mapper<TsUser> {

    List<TsUser> findByMap(Map map);

    TsUser findbyphone(String userPhone);
}

