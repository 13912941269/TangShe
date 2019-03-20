package com.chemguan.service.impl;

import com.chemguan.dao.TsReadRepository;
import com.chemguan.entity.TsRead;
import com.chemguan.service.TsReadService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: TsReadServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Service
@Transactional
public class TsReadServiceImpl extends AbstractService<TsRead> implements TsReadService {
    @Autowired
    private TsReadRepository TsReadRepository;

}
