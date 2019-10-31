package com.sys.controller.m_info;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sys.entity.m_info.M_infoEntity;
import com.sys.service.m_info.M_infoServiceI;

/**   
 * @Title: Controller
 * @Description: 牌位信息
 * @author zhangdaihao
 * @date 2017-03-09 09:51:56
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/m_infoController")
public class M_infoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(M_infoController.class);

	@Autowired
	private M_infoServiceI m_infoService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 牌位信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "m_info")
	public ModelAndView m_info(HttpServletRequest request) {
		return new ModelAndView("com/sys/m_info/m_infoList");
	}

	/**
	 * easyui AJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid(M_infoEntity m_info,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(M_infoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, m_info, request.getParameterMap());
		this.m_infoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除牌位信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(M_infoEntity m_info, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		m_info = systemService.getEntity(M_infoEntity.class, m_info.getId());
		message = "牌位信息删除成功";
		m_infoService.delete(m_info);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加牌位信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(M_infoEntity m_info, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(m_info.getId())) {
			message = "牌位信息更新成功";
			M_infoEntity t = m_infoService.get(M_infoEntity.class, m_info.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(m_info, t);
				m_infoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "牌位信息更新失败";
			}
		} else {
			message = "牌位信息添加成功";
			m_infoService.save(m_info);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 牌位信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(M_infoEntity m_info, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(m_info.getId())) {
			m_info = m_infoService.getEntity(M_infoEntity.class, m_info.getId());
			req.setAttribute("m_infoPage", m_info);
		}
		return new ModelAndView("com/sys/m_info/m_info");
	}
}
