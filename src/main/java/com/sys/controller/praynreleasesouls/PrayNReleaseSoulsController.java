package com.sys.controller.praynreleasesouls;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.sys.entity.memorial_tablet.Memorial_tabletEntity;
import com.sys.entity.pray.PrayEntity;
import com.sys.entity.releasesouls.ReleaseSoulsEntity;



/**
* @ClassName: PrayNReleaseSoulsController
* @Description: 法事登记（祈福和超度）数据共享查询器
* @author kooking
* @date 2018-8-4 下午4:47:09
*/ 
@Controller
@RequestMapping("/prayNReleaseSoulsController")
public class PrayNReleaseSoulsController extends BaseController{
	
	@Autowired
	private SystemService systemService;
	
	/**
	* @Title: pray
	* @Description: 祈福类法事，数据共享查询器
	* @param entity
	* @param response   
	* @return void    返回类型
	* @throws
	*/ 
	@RequestMapping(params = "pray")
	public void pray(PrayEntity entity, HttpServletResponse response,HttpServletRequest request) {
		DataGrid dataGrid=new DataGrid();
		dataGrid.setField("id,paymen,serial,size,registertime,prayingobj,livingmenber,address,summary,");
		// 按照登记时间倒序查询
		dataGrid.setSort("registertime");
		dataGrid.setOrder(SortDirection.desc);

		// 最多取20条记录
		dataGrid.setRows(20);
		datagrid(entity, response, dataGrid,request);
	}
	
	
	@RequestMapping(params = "releaseSouls")
	public void releaseSouls(ReleaseSoulsEntity entity, HttpServletResponse response,HttpServletRequest request) {
		DataGrid dataGrid=new DataGrid();
		dataGrid.setField("id,paymen,serial,size,registertime,prayingobj,ancestor,type,address,summary,");
		//按照登记时间倒序查询
		dataGrid.setSort("registertime");
		dataGrid.setOrder(SortDirection.desc);
		
		//最多取20条记录
		dataGrid.setRows(20);
		datagrid(entity, response, dataGrid,request);
	}
	
	private <T>  void   datagrid(T entity, HttpServletResponse response, DataGrid dataGrid,HttpServletRequest request) {
		CriteriaQuery cq = new CriteriaQuery(entity.getClass(), dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, entity,request.getParameterMap());
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

}
