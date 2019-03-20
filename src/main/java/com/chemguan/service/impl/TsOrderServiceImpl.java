package com.chemguan.service.impl;

import com.chemguan.dao.TsOrderRepository;
import com.chemguan.entity.TsOrder;
import com.chemguan.service.TsOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: TsOrderServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:52 CST 2019
 */
@Service
@Transactional
public class TsOrderServiceImpl extends AbstractService<TsOrder> implements TsOrderService {
    @Autowired
    private TsOrderRepository TsOrderRepository;

}
