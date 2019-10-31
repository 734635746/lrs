package com.sys.service.event;

import java.util.List;
import org.jeecgframework.core.common.service.CommonService;
import com.sys.entity.event.EventEntity;
import com.sys.entity.volunteerevent.VolunteereventEntity;

public interface EventServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(EventEntity event,
	        List<VolunteereventEntity> volunteereventList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(EventEntity event,
	        List<VolunteereventEntity> volunteereventList);
	public void delMain (EventEntity event);
}
