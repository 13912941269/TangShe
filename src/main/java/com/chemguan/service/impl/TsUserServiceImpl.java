package com.chemguan.service.impl;

import com.chemguan.dao.TsUserRepository;
import com.chemguan.entity.TsUser;
import com.chemguan.service.TsUserService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;


/**
 * @Title: TsUserServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Service
@Transactional
public class TsUserServiceImpl extends AbstractService<TsUser> implements TsUserService {
    @Autowired
    private TsUserRepository TsUserRepository;

    @Override
    public List<TsUser> findByMap(Map map) {
        return TsUserRepository.findByMap(map);
    }

    @Override
    public TsUser findbyphone(String userPhone) {
        return TsUserRepository.findbyphone(userPhone);
    }
}
