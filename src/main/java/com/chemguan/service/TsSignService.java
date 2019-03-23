package com.chemguan.service;
import com.chemguan.entity.TsSign;
import com.chemguan.business.core.service.Service;

import java.util.List;


/**
 * @Title: TsSignService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
public interface TsSignService extends Service<TsSign> {

    List<TsSign> findcountbyactid(Integer activityId);
}
