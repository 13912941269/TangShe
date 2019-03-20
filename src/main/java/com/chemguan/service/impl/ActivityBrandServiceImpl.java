package com.chemguan.service.impl;

import com.chemguan.dao.ActivityBrandRepository;
import com.chemguan.entity.ActivityBrand;
import com.chemguan.service.ActivityBrandService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: ActivityBrandServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Service
@Transactional
public class ActivityBrandServiceImpl extends AbstractService<ActivityBrand> implements ActivityBrandService {
    @Autowired
    private ActivityBrandRepository ActivityBrandRepository;

}
