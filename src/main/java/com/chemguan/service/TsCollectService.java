package com.chemguan.service;
import com.chemguan.entity.TsCollect;
import com.chemguan.business.core.service.Service;

import java.util.Map;


/**
 * @Title: TsCollectService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
public interface TsCollectService extends Service<TsCollect> {

    Integer findcollectcount(Map map);
}
