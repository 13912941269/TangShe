package com.chemguan.service;
import com.chemguan.entity.TsActivity;
import com.chemguan.business.core.service.Service;

import java.util.List;
import java.util.Map;


/**
 * @Title: TsActivityService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
public interface TsActivityService extends Service<TsActivity> {

    List<TsActivity> findByMap(Map map);

    void insertactivity(TsActivity tsActivity);

    TsActivity findbysaveact(Integer userId);
}
