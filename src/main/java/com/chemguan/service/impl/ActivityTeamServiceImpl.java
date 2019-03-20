package com.chemguan.service.impl;

import com.chemguan.dao.ActivityTeamRepository;
import com.chemguan.entity.ActivityTeam;
import com.chemguan.service.ActivityTeamService;
import org.springframework.beans.factory.annotation.Autowired;
import com.chemguan.business.core.service.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;



/**
 * @Title: ActivityTeamServiceImpl
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
@Service
@Transactional
public class ActivityTeamServiceImpl extends AbstractService<ActivityTeam> implements ActivityTeamService {
    @Autowired
    private ActivityTeamRepository ActivityTeamRepository;

}
