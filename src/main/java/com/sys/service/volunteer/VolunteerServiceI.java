package com.sys.service.volunteer;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.sys.entity.volunteer.VolunteerEntity;
import com.sys.entity.volunteerevent.VolunteereventEntity;

public interface VolunteerServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(VolunteerEntity volunteer,
	        List<VolunteereventEntity> volunteereventList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(VolunteerEntity volunteer,
	        List<VolunteereventEntity> volunteereventList);
	public void delMain (VolunteerEntity volunteer);
}
