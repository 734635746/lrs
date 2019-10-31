package com.sys.controller.repertory;
import java.io.IOException;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sys.entity.repertory.RepertoryEntity;
import com.sys.entity.volunteer.VolunteerEntity;
import com.sys.service.repertory.RepertoryServiceI;

/**   
 * @Title: Controller
 * @Description: 库存表
 * @author zhangdaihao
 * @date 2016-10-18 18:18:58
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/repertoryController")
public class RepertoryController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RepertoryController.class);

	@Autowired
	private RepertoryServiceI repertoryService;
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
	 * 库存表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "repertory")
	public ModelAndView repertory(HttpServletRequest request) {
		return new ModelAndView("com/sys/repertory/repertoryList");
	}
	
	/**
	 * 预警库存表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "warningRepertory")
	public ModelAndView warningRepertory(HttpServletRequest request) {
		return new ModelAndView("com/sys/repertory/warningRepertoryList");
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
	public void datagrid(RepertoryEntity repertory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RepertoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, repertory, request.getParameterMap());
		this.repertoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	@RequestMapping(params = "myDatagrid")
	public void myDatagrid(RepertoryEntity repertory,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RepertoryEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, repertory, request.getParameterMap());
		
		cq.add(Restrictions.leProperty("currentInventory", "quantityStorage"));
		this.repertoryService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除库存表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(RepertoryEntity repertory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		repertory = systemService.getEntity(RepertoryEntity.class, repertory.getId());
		message = "库存表删除成功";
		repertoryService.delete(repertory);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加库存表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(RepertoryEntity repertory, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(repertory.getId())) {
			message = "库存表更新成功";
			RepertoryEntity t = repertoryService.get(RepertoryEntity.class, repertory.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(repertory, t);
				repertoryService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "库存表更新失败";
			}
		} else {
			message = "库存表添加成功";
			
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(currentTime);
			
			String username = ResourceUtil.getSessionUserName().getRealName();
			repertory.setRegister(username);
			repertory.setRegistertime(dateString);
			
			repertoryService.save(repertory);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 库存表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(RepertoryEntity repertory, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(repertory.getId())) {
			repertory = repertoryService.getEntity(RepertoryEntity.class, repertory.getId());
			req.setAttribute("repertoryPage", repertory);
		}
		return new ModelAndView("com/sys/repertory/repertory");
	}
	
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(RepertoryEntity repertory,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "预警库存表";
			// 根据浏览器进行转码，使其支持中文文件名
			if (BrowserUtils.isIE(request)) {
				response.setHeader(
						"content-disposition",
						"attachment;filename="
								+ java.net.URLEncoder.encode(codedFileName,
										"UTF-8") + ".xls");
			} else {
				String newtitle = new String(codedFileName.getBytes("UTF-8"),
						"ISO8859-1");
				response.setHeader("content-disposition",
						"attachment;filename=" + newtitle + ".xls");
			}
			// 产生工作簿对象
			HSSFWorkbook workbook = null;
			CriteriaQuery cq = new CriteriaQuery(RepertoryEntity.class, dataGrid);
			cq.add(Restrictions.leProperty("currentInventory", "quantityStorage"));
			
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, repertory, request.getParameterMap());
			List<RepertoryEntity> repertoryEntitys = this.repertoryService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("预警库存表", "导出人:zzh",
					"导出信息"), RepertoryEntity.class, repertoryEntitys);
			fOut = response.getOutputStream();
			workbook.write(fOut);
		} catch (Exception e) {
		} finally {
			try {
				fOut.flush();
				fOut.close();
			} catch (IOException e) {

			}
		}
	}
}
