package com.sys.controller.volunteer;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.demo.entity.test.JeecgDemo;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.servlet.ModelAndView;

import test.Page;

import com.sys.entity.event.EventEntity;
import com.sys.entity.memorial_tablet.Memorial_tabletEntity;
import com.sys.entity.volunteer.VolunteerEntity;
import com.sys.entity.volunteerevent.VolunteereventEntity;
import com.sys.page.volunteer.VolunteerPage;
import com.sys.service.event.EventServiceI;
import com.sys.service.volunteer.VolunteerServiceI;
/**   
 * @Title: Controller
 * @Description: 义工登记信息
 * @author zhangdaihao
 * @date 2015-10-30 15:09:14
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/volunteerController")
public class VolunteerController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(VolunteerController.class);

	@Autowired
	private VolunteerServiceI volunteerService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private EventServiceI eventService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 义工登记信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "volunteer")
	public ModelAndView volunteer(HttpServletRequest request) {
		return new ModelAndView("com/sys/volunteer/volunteerList");
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
	public void datagrid(VolunteerEntity volunteer,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(VolunteerEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, volunteer);
		this.volunteerService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除义工登记信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(VolunteerEntity volunteer, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		volunteer = systemService.getEntity(VolunteerEntity.class, volunteer.getId());
		message = "删除成功";
		volunteerService.delete(volunteer);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加义工登记信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(VolunteerEntity volunteer,VolunteerPage volunteerPage, HttpServletRequest request) {
		List<VolunteereventEntity> volunteereventList =  volunteerPage.getVolunteereventList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(volunteer.getId())) {
			message = "更新成功";
			volunteerService.updateMain(volunteer, volunteereventList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			volunteer.setAverage((float)0);
			volunteer.setJoincount(0);
			volunteerService.addMain(volunteer, volunteereventList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 义工登记信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(VolunteerEntity volunteer, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(volunteer.getId())) {
			volunteer = volunteerService.getEntity(VolunteerEntity.class, volunteer.getId());
			req.setAttribute("volunteerPage", volunteer);
		}
		return new ModelAndView("com/sys/volunteer/volunteer");
	}
	
	
	/**
	 * 加载明细列表[义工事务登记]
	 * 
	 * @return
	 */
	@RequestMapping(params = "volunteereventList")
	public ModelAndView volunteereventList(VolunteerEntity volunteer, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = volunteer.getId();
		//===================================================================================
		//查询-义工事务登记
	    String hql0 = "from VolunteereventEntity where 1 = 1 AND volunteerid = ? ";
		try{
		    List<VolunteereventEntity> volunteereventEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("volunteereventList", volunteereventEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/sys/volunteer/volunteereventList");
	}
	
	/**
	 * 获取义工信息
	 * @param pageNo
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "getVolunteerList")
	public String getVolunteerList(@RequestParam(value = "pageNo",required = false) String pageNo,EventEntity event,String eventid,Model model)
	{		
		if((pageNo == null) || ("".equals(pageNo))) {
			pageNo = "1";
			eventid = event.getId();
		}
		System.out.println(eventid);
		if(((EventEntity) eventService.getEntity(EventEntity.class, eventid)).getIsconfirm().equals("是")){
			model.addAttribute("message", "该事件已经确认了，不能再去分配义工");
			return "com/sys/confirmPage";
		}
		EventEntity ev = eventService.getEntity(EventEntity.class, eventid);
		String eventname = ev.getEventname();
		List<VolunteerEntity> volunteerList = null;
		volunteerList = volunteerService.getList(VolunteerEntity.class);
		int pageNoInt = Integer.parseInt(pageNo);
		Page<VolunteerEntity> page = new Page<VolunteerEntity>(pageNoInt,10,volunteerList.size());
		if(pageNoInt>page.getTotalPage()){
			page.setPageNo((int) page.getTotalPage());
		}else if(pageNoInt <= 0){
			pageNoInt = 1;
		}
		page.setResultOfPage(volunteerList);
		model.addAttribute("page",page);
		model.addAttribute("eventid",eventid);
		model.addAttribute("eventname",eventname);
		return "com/sys/volunteer/volunteerResult";
	}
	
	/**
	 * 获取分配的义工
	 * @param eventname
	 * @param eventid
	 * @param ids
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "giveVolunteer")
	@ResponseBody
	public AjaxJson giveVolunteer(String eventname,String eventid,String ids,HttpServletRequest request) {
		
		String idstemp = null;
		String eventnametemp = null;
		try {
			idstemp = new String(ids.getBytes("ISO-8859-1"), "UTF-8");
			eventnametemp =new String(eventname.getBytes("ISO-8859-1"), "UTF-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String _ids = idstemp.substring(0, idstemp.length() - 1);
		String[] volunteerids = _ids.split(",");
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(eventid)) {
			for(String idname : volunteerids){
				String[] part = idname.split("split");
				VolunteereventEntity volunteerone = new VolunteereventEntity();
				volunteerone.setEventid(eventid);
				volunteerone.setEventname(eventnametemp);
				volunteerone.setVolunteername(part[1]);
				volunteerone.setVolunteerid(part[0]);
				volunteerone.setAtitude((float)0);
				volunteerone.setWorkcondition((float)0);
				systemService.save(volunteerone);
			}
			message = "分配成功";
		} else {
			message = "分配失败";
		}
		j.setMsg(message);
		return j;
	}
	
	/**
	 * 上传
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/sys/volunteer/volunteerUpload");
	}
	
	/**
	 * 导入excel
	 * @param request
	 * @param response
	 * @return
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(params = "importExcel", method = RequestMethod.POST)
	@ResponseBody
	public AjaxJson importExcel(HttpServletRequest request, HttpServletResponse response) {
		AjaxJson j = new AjaxJson();
		
		MultipartHttpServletRequest multipartRequest = (MultipartHttpServletRequest) request;
		Map<String, MultipartFile> fileMap = multipartRequest.getFileMap();
		for (Map.Entry<String, MultipartFile> entity : fileMap.entrySet()) {
			MultipartFile file = entity.getValue();// 获取上传文件对象
			ImportParams params = new ImportParams();
			params.setTitleRows(2);
			params.setSecondTitleRows(2);
			params.setNeedSave(true);
			try {
				List<VolunteerEntity> listVolunteerEntitys = 
					(List<VolunteerEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),VolunteerEntity.class,params);
				for (VolunteerEntity volunteerEntity : listVolunteerEntitys) {
					if(volunteerEntity.getName()!=null){
						volunteerEntity.setAverage((float)0);
						volunteerEntity.setJoincount(0);
						volunteerService.save(volunteerEntity);
					}
				}
				j.setMsg("文件导入成功！");
			} catch (Exception e) {
				j.setMsg("文件导入失败！");
				logger.error(ExceptionUtil.getExceptionMessage(e));
			}finally{
				try {
					file.getInputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return j;
	}

	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(VolunteerEntity volunteerEntity,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "义工列表";
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
			CriteriaQuery cq = new CriteriaQuery(VolunteerEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, volunteerEntity, request.getParameterMap());
			List<VolunteerEntity> volunteerEntitys = this.volunteerService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("义工列表", "导出人:zzh",
					"导出信息"), VolunteerEntity.class, volunteerEntitys);
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
	
	
	@RequestMapping(params = "doDeleteALLSelect")
	@ResponseBody
	public AjaxJson doDeleteALLSelect(VolunteerEntity volunteerEntity, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		String ids = request.getParameter("ids");
		String[] entitys = ids.split(",");
	    List<VolunteerEntity> list = new ArrayList<VolunteerEntity>();
		for(int i=0;i<entitys.length;i++){
			volunteerEntity = systemService.getEntity(VolunteerEntity.class, entitys[i]);
            list.add(volunteerEntity);			
		}
		message = "删除成功";
		volunteerService.deleteAllEntitie(list);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	
}
