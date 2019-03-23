package com.chemguan.service.impl;

import com.chemguan.dao.TsCollectRepository;
import com.chemguan.entity.TsCollect;
import com.chemguan.service.TsCollectService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;


/**
 * @Title: TsCollectServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Service
@Transactional
public class TsCollectServiceImpl extends AbstractService<TsCollect> implements TsCollectService {
    @Autowired
    private TsCollectRepository tsCollectRepository;

    @Override
    public Integer findcollectcount(Map map) {
        return tsCollectRepository.findcollectcount(map);
    }
}
