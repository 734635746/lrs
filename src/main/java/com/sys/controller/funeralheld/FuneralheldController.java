package com.sys.controller.funeralheld;
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

import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.service.funeralheld.FuneralheldServiceI;

/**   
 * @Title: Controller
 * @Description: 法事举行日期安排
 * @author zhangdaihao
 * @date 2016-11-02 09:05:38
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/funeralheldController")
public class FuneralheldController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FuneralheldController.class);

	@Autowired
	private FuneralheldServiceI funeralheldService;
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
	 * 法事举行日期安排列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "funeralheld")
	public ModelAndView funeralheld(HttpServletRequest request) {
		return new ModelAndView("com/sys/funeralheld/funeralheldList");
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
	public void datagrid(FuneralheldEntity funeralheld,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FuneralheldEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, funeralheld, request.getParameterMap());
		this.funeralheldService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除法事举行日期安排
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FuneralheldEntity funeralheld, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		funeralheld = systemService.getEntity(FuneralheldEntity.class, funeralheld.getId());
		message = "法事举行日期安排删除成功";
		funeralheldService.delete(funeralheld);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加法事举行日期安排
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FuneralheldEntity funeralheld, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(funeralheld.getId())) {
			message = "法事举行日期安排更新成功";
			FuneralheldEntity t = funeralheldService.get(FuneralheldEntity.class, funeralheld.getId());
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String endmonth = request.getParameter("endmonth");
			String endday = request.getParameter("endday");
			t.setHoldDate(month + " " + day);
			t.setEndDate(endmonth + " " + endday);
			
			try {
				MyBeanUtils.copyBeanNotNull2Bean(funeralheld, t);
				funeralheldService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "法事举行日期安排更新失败";
			}
		} else {
			message = "法事举行日期安排添加成功";
			String month = request.getParameter("month");
			String day = request.getParameter("day");
			String endmonth = request.getParameter("endmonth");
			String endday = request.getParameter("endday");
			funeralheld.setHoldDate(month + " " + day);
			funeralheld.setEndDate(endmonth + " " + endday);
			funeralheldService.save(funeralheld);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 法事举行日期安排列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FuneralheldEntity funeralheld, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(funeralheld.getId())) {
			funeralheld = funeralheldService.getEntity(FuneralheldEntity.class, funeralheld.getId());
			req.setAttribute("funeralheldPage", funeralheld);
		}
		return new ModelAndView("com/sys/funeralheld/funeralheld");
	}
}
