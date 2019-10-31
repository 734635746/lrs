package com.sys.controller.memorialinfo_view;
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

import com.sys.entity.memorialinfo_view.Memorialinfo_viewEntity;
import com.sys.service.memorialinfo_view.Memorialinfo_viewServiceI;

/**   
 * @Title: Controller
 * @Description: 牌位视图
 * @author zhangdaihao
 * @date 2016-01-14 08:56:25
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/memorialinfo_viewController")
public class Memorialinfo_viewController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Memorialinfo_viewController.class);

	@Autowired
	private Memorialinfo_viewServiceI memorialinfo_viewService;
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
	 * 牌位视图列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "memorialinfo_view")
	public ModelAndView memorialinfo_view(HttpServletRequest request) {
		return new ModelAndView("com/sys/memorialinfo_view/memorialinfo_viewList");
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
	public void datagrid(Memorialinfo_viewEntity memorialinfo_view,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Memorialinfo_viewEntity.class, dataGrid);
		//查询条件组装器
		System.out.println("=============" + dataGrid);
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, memorialinfo_view, request.getParameterMap());
		this.memorialinfo_viewService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除牌位视图
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Memorialinfo_viewEntity memorialinfo_view, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		memorialinfo_view = systemService.getEntity(Memorialinfo_viewEntity.class, memorialinfo_view.getUnionID());
		message = "牌位视图删除成功";
		memorialinfo_viewService.delete(memorialinfo_view);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}



	/**
	 * 牌位视图列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(Memorialinfo_viewEntity memorialinfo_view, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(memorialinfo_view.getUnionID())) {
			memorialinfo_view = memorialinfo_viewService.getEntity(Memorialinfo_viewEntity.class, memorialinfo_view.getUnionID());
			req.setAttribute("memorialinfo_viewPage", memorialinfo_view);
		}
		return new ModelAndView("com/sys/memorialinfo_view/memorialinfo_view");
	}
}
