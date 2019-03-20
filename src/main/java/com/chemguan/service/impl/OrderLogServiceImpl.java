package com.chemguan.service.impl;

import com.chemguan.dao.OrderLogRepository;
import com.chemguan.entity.OrderLog;
import com.chemguan.service.OrderLogService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: OrderLogServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Service
@Transactional
public class OrderLogServiceImpl extends AbstractService<OrderLog> implements OrderLogService {
    @Autowired
    private OrderLogRepository OrderLogRepository;

}
