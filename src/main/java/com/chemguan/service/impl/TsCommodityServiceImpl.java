package com.chemguan.service.impl;

import com.chemguan.business.core.service.AbstractService;
import com.chemguan.dao.TsCommodityRepository;
import com.chemguan.dao.TsUserRepository;
import com.chemguan.entity.TsCommodity;
import com.chemguan.entity.TsUser;
import com.chemguan.service.TsCommodityService;
import com.chemguan.service.TsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @Title: TsCommodityServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Service
@Transactional
public class TsCommodityServiceImpl extends AbstractService<TsCommodity> implements TsCommodityService {
    @Autowired
    private TsCommodityRepository TsCommodityRepository;

}
