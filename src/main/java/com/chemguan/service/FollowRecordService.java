package com.chemguan.service;
import com.chemguan.entity.FollowRecord;
import com.chemguan.business.core.service.Service;


/**
 * @Title: FollowRecordService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
public interface FollowRecordService extends Service<FollowRecord> {

    Integer findcountbyuid(Integer userId);
}
