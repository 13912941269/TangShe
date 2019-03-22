package com.chemguan.service;
import com.chemguan.entity.SignTag;
import com.chemguan.business.core.service.Service;

import java.util.List;


/**
 * @Title: SignTagService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
public interface SignTagService extends Service<SignTag> {


    List<SignTag> findbysign(Integer signId);

    void deletebysign(Integer signId);
}
