package com.chemguan.service.impl;

import com.chemguan.dao.TsSignRepository;
import com.chemguan.entity.TsSign;
import com.chemguan.service.TsSignService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Title: TsSignServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Service
@Transactional
public class TsSignServiceImpl extends AbstractService<TsSign> implements TsSignService {
    @Autowired
    private TsSignRepository tsSignRepository;

    @Override
    public List<TsSign> findcountbyactid(Integer activityId) {
        return tsSignRepository.findcountbyactid(activityId);
    }
}
