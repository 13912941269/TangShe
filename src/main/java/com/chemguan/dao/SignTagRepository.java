package com.chemguan.dao;

import com.chemguan.business.core.mapper.Mapper;
import com.chemguan.entity.SignTag;

import java.util.List;

/**
 * @Title: SignTagRepository
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@org.apache.ibatis.annotations.Mapper
public interface SignTagRepository extends Mapper<SignTag> {


    List<SignTag> findbysign(Integer signId);

    void deletebysign(Integer signId);
}

