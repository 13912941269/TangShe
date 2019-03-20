package com.chemguan.service.impl;

import com.chemguan.dao.SignTagvalRepository;
import com.chemguan.entity.SignTagval;
import com.chemguan.service.SignTagvalService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: SignTagvalServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Service
@Transactional
public class SignTagvalServiceImpl extends AbstractService<SignTagval> implements SignTagvalService {
    @Autowired
    private SignTagvalRepository SignTagvalRepository;

}
