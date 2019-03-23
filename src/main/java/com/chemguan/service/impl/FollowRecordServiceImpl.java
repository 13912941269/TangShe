package com.chemguan.service.impl;

import com.chemguan.dao.FollowRecordRepository;
import com.chemguan.entity.FollowRecord;
import com.chemguan.service.FollowRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: FollowRecordServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Service
@Transactional
public class FollowRecordServiceImpl extends AbstractService<FollowRecord> implements FollowRecordService {
    @Autowired
    private FollowRecordRepository FollowRecordRepository;

    @Override
    public Integer findcountbyuid(Integer userId) {
        return FollowRecordRepository.findcountbyuid(userId);
    }
}
