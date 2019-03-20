package com.chemguan.service.impl;

import com.chemguan.dao.ActivityColumRepository;
import com.chemguan.entity.ActivityColum;
import com.chemguan.service.ActivityColumService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: ActivityColumServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Service
@Transactional
public class ActivityColumServiceImpl extends AbstractService<ActivityColum> implements ActivityColumService {
    @Autowired
    private ActivityColumRepository ActivityColumRepository;

}
