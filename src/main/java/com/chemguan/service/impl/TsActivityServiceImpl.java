package com.chemguan.service.impl;

import com.chemguan.dao.TsActivityRepository;
import com.chemguan.entity.TsActivity;
import com.chemguan.service.TsActivityService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: TsActivityServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Service
@Transactional
public class TsActivityServiceImpl extends AbstractService<TsActivity> implements TsActivityService {
    @Autowired
    private TsActivityRepository TsActivityRepository;

}
