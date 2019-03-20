package com.chemguan.service.impl;

import com.chemguan.dao.TsPraiseRepository;
import com.chemguan.entity.TsPraise;
import com.chemguan.service.TsPraiseService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: TsPraiseServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Service
@Transactional
public class TsPraiseServiceImpl extends AbstractService<TsPraise> implements TsPraiseService {
    @Autowired
    private TsPraiseRepository TsPraiseRepository;

}
