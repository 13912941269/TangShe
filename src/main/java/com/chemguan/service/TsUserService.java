package com.chemguan.service;
import com.chemguan.entity.TsUser;
import com.chemguan.business.core.service.Service;

import java.util.List;
import java.util.Map;


/**
 * @Title: TsUserService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
public interface TsUserService extends Service<TsUser> {

    List<TsUser> findByMap(Map map);
}
