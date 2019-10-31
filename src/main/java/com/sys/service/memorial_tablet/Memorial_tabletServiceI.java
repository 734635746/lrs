package com.sys.service.memorial_tablet;

import java.util.List;

import org.jeecgframework.core.common.service.CommonService;
import org.jeecgframework.web.demo.entity.test.CourseEntity;

import com.sys.entity.memorial_tablet.Memorial_tabletEntity;
import com.sys.entity.namelist.NamelistEntity;
import com.sys.entity.linkmanlist.LinkmanlistEntity;

public interface Memorial_tabletServiceI extends CommonService{

	/**
	 * 添加一对多
	 * 
	 */
	public void addMain(Memorial_tabletEntity memorial_tablet,
	        List<NamelistEntity> namelistList,List<LinkmanlistEntity> linkmanlistList) ;
	/**
	 * 修改一对多
	 * 
	 */
	public void updateMain(Memorial_tabletEntity memorial_tablet,
	        List<NamelistEntity> namelistList,List<LinkmanlistEntity> linkmanlistList);
	public void delMain (Memorial_tabletEntity memorial_tablet);
	
	
}
