package com.sys.controller.receiptno;
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

import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.service.receiptno.ReceiptnoServiceI;

/**   
 * @Title: Controller
 * @Description: 收据编号管理
 * @author zhangdaihao
 * @date 2016-11-07 16:04:21
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/receiptnoController")
public class ReceiptnoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReceiptnoController.class);

	@Autowired
	private ReceiptnoServiceI receiptnoService;
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
	 * 收据编号管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "receiptno")
	public ModelAndView receiptno(HttpServletRequest request) {
		return new ModelAndView("com/sys/receiptno/receiptnoList");
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
	public void datagrid(ReceiptnoEntity receiptno,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ReceiptnoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, receiptno, request.getParameterMap());
		this.receiptnoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除收据编号管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ReceiptnoEntity receiptno, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		receiptno = systemService.getEntity(ReceiptnoEntity.class, receiptno.getId());
		message = "收据编号管理删除成功";
		receiptnoService.delete(receiptno);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加收据编号管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ReceiptnoEntity receiptno, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(receiptno.getId())) {
			message = "收据编号管理更新成功";
			ReceiptnoEntity t = receiptnoService.get(ReceiptnoEntity.class, receiptno.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(receiptno, t);
				receiptnoService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "收据编号管理更新失败";
			}
		} else {
			message = "收据编号管理添加成功";
			receiptnoService.save(receiptno);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 收据编号管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ReceiptnoEntity receiptno, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(receiptno.getId())) {
			receiptno = receiptnoService.getEntity(ReceiptnoEntity.class, receiptno.getId());
			req.setAttribute("receiptnoPage", receiptno);
		}
		return new ModelAndView("com/sys/receiptno/receiptno");
	}
}
