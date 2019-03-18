package com.chemguan.background.service.impl;

import com.chemguan.background.mapper.BackGroundDao;
import com.chemguan.background.model.BackGroundLogin;
import com.chemguan.background.service.BackGroundService;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


@Service
@Transactional(rollbackFor = Exception.class)
public class BackGroundServiceImpl extends AbstractService<BackGroundLogin> implements BackGroundService {

    @Autowired
    private BackGroundDao dao;
}
