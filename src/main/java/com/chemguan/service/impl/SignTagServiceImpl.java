package com.chemguan.service.impl;

import com.chemguan.dao.SignTagRepository;
import com.chemguan.entity.SignTag;
import com.chemguan.service.SignTagService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * @Title: SignTagServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Service
@Transactional
public class SignTagServiceImpl extends AbstractService<SignTag> implements SignTagService {
    @Autowired
    private SignTagRepository SignTagRepository;

    @Override
    public List<SignTag> findbysign(Integer signId) {
        return SignTagRepository.findbysign(signId);
    }

    @Override
    public void deletebysign(Integer signId) {
        SignTagRepository.deletebysign(signId);
    }
}
