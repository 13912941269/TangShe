package com.chemguan.service;
import com.chemguan.entity.ActivityTeam;
import com.chemguan.business.core.service.Service;

import java.util.Map;


/**
 * @Title: ActivityTeamService
 * @ProjectName
 * @Description: TODO
 * @author 
 * @date Wed Mar 20 11:32:51 CST 2019
 */
public interface ActivityTeamService extends Service<ActivityTeam> {

    ActivityTeam findteambyuseract(Map map);
}
