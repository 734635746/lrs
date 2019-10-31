package com.sys.controller.purpose;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.entity.purpose.PurposeEntity;
import com.sys.service.purpose.PurposeServiceI;

/**
* @ClassName: PurposeController
* @Description: 善款用途处理
* @author kooking
* @date 2018-3-29 下午12:29:15
*/ 
@Controller
@RequestMapping("/purposeController")
public class PurposeController extends BaseController{

	
	@Autowired
	private PurposeServiceI purposeService;
	
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
	* @Title: purpose
	* @Description: 善款用途列表页面跳转
	* @return   
	* @return ModelAndView    返回类型
	* @throws
	*/ 
	@RequestMapping(params = "purpose")
	public ModelAndView purpose(HttpServletRequest request) {
		return new ModelAndView("com/sys/purpose/purposeList");
	}
	
	
	
	/**
	 * easyuiAJAX请求数据
	 * 
	 * @param request
	 * @param response
	 * @param dataGrid
	 * @param user
	 */

	@RequestMapping(params = "datagrid")
	public void datagrid( HttpServletResponse response, DataGrid dataGrid,PurposeEntity purpose,HttpServletRequest request) {
		CriteriaQuery cq = new CriteriaQuery(PurposeEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,purpose);
		this.purposeService.getDataGridReturn(cq, true);
		
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	* @Title: del
	* @Description: 删除善款用途
	* @param purpose
	* @param request
	* @return   
	* @return AjaxJson    返回类型
	* @throws
	*/ 
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(PurposeEntity purpose, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		purpose = systemService.getEntity(PurposeEntity.class, purpose.getId());
		message ="捐款用途: " + purpose.getPurpose() +  "删除成功";
		purposeService.delete(purpose);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}
	
	
	
	/**
	* @Title: add
	* @Description: 添加捐款用途页面跳转
	* @param purpose
	* @param req
	* @return   
	* @return ModelAndView    返回类型
	* @throws
	*/ 
	@RequestMapping(params = "add")
	public ModelAndView add(HttpServletRequest req) {
		String search = req.getParameter("search");
		req.setAttribute("searchflag", search);
		return new ModelAndView("com/sys/purpose/purpose");
	}
	
	
	
	
	/**
	* @Title: save
	* @Description: 添加捐款用途,从purpose.jsp提交的数据不要提交到这里来，
	* 							会有问题，将请求提交到doritualinfoController.do?savePurpose了
	* @param purpose
	* @return   
	* @return AjaxJson    返回类型
	* @throws
	*/ 
	/*@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(PurposeEntity purpose) {
		AjaxJson j = new AjaxJson();
		message = "添加成功";
		purposeService.save(purpose);
		systemService.addLog(message, Globals.Log_Type_INSERT,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}*/
}
