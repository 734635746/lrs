package com.sys.controller.memorial_tablet;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.CriteriaSpecification;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.ExceptionUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.ExcelImportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.poi.excel.entity.ImportParams;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
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

import com.sys.entity.linkmanlist.LinkmanlistEntity;
import com.sys.entity.m_info.M_infoEntity;
import com.sys.entity.memorial_tablet.Memorial_statusEntity;
import com.sys.entity.memorial_tablet.Memorial_tabletEntity;
import com.sys.entity.namelist.NamelistEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.entity.room.RoomEntity;
import com.sys.entity.staff.StaffEntity;
import com.sys.page.memorial_tablet.Memorial_tabletPage;
import com.sys.service.m_info.M_infoServiceI;
import com.sys.service.memorial_tablet.Memorial_tabletServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;
/**   
 * @Title: Controller
 * @Description: 牌位表
 * @author zhangdaihao
 * @date 2016-01-04 15:08:41
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/memorial_tabletController")
public class Memorial_tabletController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Memorial_tabletController.class);
	public static final String FILE_SEPARATOR = System.getProperties()
			.getProperty("file.separator");

	@Autowired
	private Memorial_tabletServiceI memorial_tabletService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private M_infoServiceI m_infoService;
	@Autowired
	private ReceiptServiceI receiptService;
	@Autowired
	private ReceiptnoServiceI receiptnoService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 牌位表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "memorial_tablet")
	public ModelAndView memorial_tablet(HttpServletRequest request) {
		return new ModelAndView("com/sys/memorial_tablet/memorial_tabletList");
	}
	
	/**
	 * 查询跳转页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "inquiry")
	public ModelAndView inquiry(HttpServletRequest request) {
		return new ModelAndView("com/sys/memorial_tablet/memorialInquiry");
	}
	
	/**
	 * 跳转登记牌位页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "towardRegister")
	public ModelAndView towardRegister(HttpServletRequest request) {
		CriteriaQuery cq2 = new CriteriaQuery(M_infoEntity.class);
		//List<M_infoEntity> minfoEntityList = systemService.getListByCriteriaQuery(cq2, false);
		String sql = "SELECT DISTINCT building_name FROM m_info";
		List<String> minfoEntityList = systemService.findListbySql(sql);//2017-7-22修改
		request.setAttribute("minfoEntityList", minfoEntityList);
		return new ModelAndView("com/sys/memorial_tablet/selecctTablet");
	}
	
	/**
	 * 跳转移位页面
	 * @param request
	 * @return
	 */
	@RequestMapping(params = "towardMove")
	public ModelAndView towardMove(String id,  HttpServletRequest request) {
		CriteriaQuery cq2 = new CriteriaQuery(M_infoEntity.class);
		//List<M_infoEntity> minfoEntityList = systemService.getListByCriteriaQuery(cq2, false);
		String sql = "SELECT DISTINCT building_name FROM m_info";
		List<String> minfoEntityList = systemService.findListbySql(sql);//2017-7-22修改
		request.setAttribute("minfoEntityList", minfoEntityList);
		request.setAttribute("id", id);
		
		return new ModelAndView("com/sys/memorial_tablet/selecctTablet_Move");
	}
	
	
	/**
	* @Title: moveTablet
	* @Description: 移位
	* @param memorial_tablet
	* @param memorial_tabletPage
	* @param request
	* @return   
	* @return ModelAndView    返回类型
	* @throws
	*/ 
	@RequestMapping(params = "moveTablet")
	public ModelAndView moveTablet(Memorial_tabletEntity memorial_tablet,HttpServletRequest request) {
		
		//先查询原信息
		memorial_tablet = memorial_tabletService.getEntity(Memorial_tabletEntity.class, memorial_tablet.getId());
		if (memorial_tablet==null) {
			request.setAttribute("message", "移位失败，记录丢失，请联系系统管理员！");
			return new ModelAndView("com/sys/updateSuccess");
		}
		
		try {
			memorial_tablet.setBuildingName(URLDecoder.decode(memorial_tablet.getBuildingName(), "utf-8"));
			memorial_tablet.setDan(URLDecoder.decode(memorial_tablet.getDan(), "utf-8"));
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		
		//获取行和列
		String selectedTablet = request.getParameter("selectedTablet");
		memorial_tablet.setRow(Integer.parseInt(selectedTablet.split("@")[0]));
		memorial_tablet.setOrdernumber(Integer.parseInt(selectedTablet.split("@")[1]));
		
		// 生成编号
		StringBuilder serial = new StringBuilder(memorial_tablet.getBuildingName());
		serial.append(memorial_tablet.getDan());
		serial.append(memorial_tablet.getRow() + "行");
		serial.append(memorial_tablet.getOrdernumber() + "列");
		
		// 获取牌位内容并显示到页面
		StringBuilder sb = new StringBuilder("您已经把牌位移至：");
		sb.append(serial);

		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date currentTime = new Date();
		serial.append(formatter.format(currentTime));

		memorial_tablet.setSerial(serial.toString());

		//修改牌位信息即可
		memorial_tabletService.updateEntitie(memorial_tablet);
		

		request.setAttribute("message", sb.toString());
		return new ModelAndView("com/sys/updateSuccess");
	}
	
	@RequestMapping(params = "towardExportExcel")
	public ModelAndView towardExportExcel(HttpServletRequest request) {
		CriteriaQuery cq2 = new CriteriaQuery(M_infoEntity.class);
		//List<M_infoEntity> minfoEntityList = systemService.getListByCriteriaQuery(cq2, false);
		String sql = "SELECT DISTINCT building_name FROM m_info";//2017-7-22修改
		List<String> minfoEntityList = systemService.findListbySql(sql);

		request.setAttribute("minfoEntityList", minfoEntityList);
		return new ModelAndView("com/sys/memorial_tablet/excelExport");
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
	public void datagrid(Memorial_tabletEntity memorial_tablet,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Memorial_tabletEntity.class, dataGrid);
		cq.lt("registertime", request.getParameter("registertime_end"));
		cq.gt("registertime", request.getParameter("registertime_begin"));
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, memorial_tablet,request.getParameterMap());
		this.memorial_tabletService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除牌位表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Memorial_tabletEntity memorial_tablet, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		memorial_tablet = systemService.getEntity(Memorial_tabletEntity.class, memorial_tablet.getId());
		message = "删除成功";
		memorial_tabletService.delete(memorial_tablet);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加牌位表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(Memorial_tabletEntity memorial_tablet,Memorial_tabletPage memorial_tabletPage, HttpServletRequest request) {
		List<NamelistEntity> namelistList =  memorial_tabletPage.getNamelistList();
		List<LinkmanlistEntity> linkmanlistList =  memorial_tabletPage.getLinkmanlistList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(memorial_tablet.getId())) {
			message = "更新成功";
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date currentTime = new Date();
			memorial_tablet.setRegistertime(formatter.format(currentTime));
			memorial_tabletService.updateMain(memorial_tablet, namelistList,linkmanlistList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			Date currentTime = new Date();
			memorial_tablet.setRegistertime(formatter.format(currentTime));
			memorial_tabletService.addMain(memorial_tablet, namelistList,linkmanlistList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 牌位表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(Memorial_tabletEntity memorial_tablet, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(memorial_tablet.getId())) {
			memorial_tablet = memorial_tabletService.getEntity(Memorial_tabletEntity.class, memorial_tablet.getId());
			req.setAttribute("memorial_tabletPage", memorial_tablet);
			
			//查询牌位列表，用于下拉框显示
//			CriteriaQuery cq = new CriteriaQuery(M_infoEntity.class);
//			cq.add(Restrictions.and(Restrictions.eq("buildingName", memorial_tablet.getBuildingName()), Restrictions.eq("danName", memorial_tablet.getDan())));
//			M_infoEntity me = (M_infoEntity) m_infoService.getListByCriteriaQuery(cq, false).get(0);
//			req.
		}
		
		
		return new ModelAndView("com/sys/memorial_tablet/memorial_tablet");
	}
	
	/**
	 * 上传
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "upload")
	public ModelAndView upload(HttpServletRequest req) {
		return new ModelAndView("com/sys/memorial_tablet/memorial_tabletUpload");
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
				List<Memorial_tabletEntity> listMemorial_tabletEntitys = 
					(List<Memorial_tabletEntity>)ExcelImportUtil.importExcelByIs(file.getInputStream(),Memorial_tabletEntity.class,params);
				for (Memorial_tabletEntity memorial_tabletEntity : listMemorial_tabletEntitys) {
					if(memorial_tabletEntity.getSerial()!=null){
						memorial_tabletService.addMain(memorial_tabletEntity, memorial_tabletEntity.getNamelistEntityList(), memorial_tabletEntity.getLinkmanlistEntityList());
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
	public void exportXls(Memorial_tabletEntity memorial_tabletEntity,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "牌位信息";
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
			CriteriaQuery cq = new CriteriaQuery(Memorial_tabletEntity.class, dataGrid);
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, memorial_tabletEntity, request.getParameterMap());
			
			List<Memorial_tabletEntity> memorial_tabletEntitys = this.memorial_tabletService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("牌位列表", "导出人:zzh",
					"导出信息"), Memorial_tabletEntity.class, memorial_tabletEntitys);
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
	/**
	 * 导出功德堂销售情况表
	 * @param building_name
	 * @param dan_name
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "exportStatusXls")
	public void exportStatusXls(String building_name,String dan_name, HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
		
		/*try {
			building_name = new String(request.getParameter("building_name").getBytes("iso8859-1"),"utf-8");
			dan_name =  new String(request.getParameter("dan_name").getBytes("iso8859-1"),"utf-8");
		} catch (UnsupportedEncodingException e1) {
			e1.printStackTrace();
		}*/
//		String building_name = "新功德堂";
		
//		String dan_name = "南段";
		/*try {
			building_name = URLDecoder.decode(building_name, "utf-8");
			dan_name = URLDecoder.decode(dan_name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
		
		
		CriteriaQuery cqTablet = new CriteriaQuery(Memorial_tabletEntity.class);
		cqTablet.add(Restrictions.and(Restrictions.eq("buildingName", building_name), Restrictions.eq("dan", dan_name)));
		List<Memorial_tabletEntity> memorial_tabletEntityList = memorial_tabletService.getListByCriteriaQuery(cqTablet,false);
		
		
		
		CriteriaQuery cq = new CriteriaQuery(M_infoEntity.class);
		cq.add(Restrictions.and(Restrictions.eq("buildingName", building_name), Restrictions.eq("danName", dan_name)));
		M_infoEntity me = (M_infoEntity) m_infoService.getListByCriteriaQuery(cq, false).get(0);
		
//		int tablet[][] = new int[me.getRow()+1][me.getColumn()+1];
		List<Memorial_statusEntity> memorial_statusEntities = new ArrayList<>();
		Memorial_statusEntity memorial_statusEntity;
		
//		for(int i = 1;i < me.getRow()+1;i ++){
//			for(int j = 1;j < me.getColumn();j ++){
//				tablet[i][j] = 0;
//
//			}
//		}
		int culumnCount = me.getColumn();
		String[] cnArr = new String[]{"一","二","三","四","五","六","七","八","九","十","十一","十二","十三","十四","十五","十六","十七","十八","十九","二十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","三十"};
		for(int i = 1;i < me.getRow()+1;i ++){
			memorial_statusEntity = new Memorial_statusEntity();
			memorial_statusEntity.setRow(cnArr[i-1]);
			for(int j = 1;j < culumnCount;j ++){
				for(Memorial_tabletEntity m : memorial_tabletEntityList){
					if(i == m.getRow() && j == m.getOrdernumber()){
//						tablet[i][j] = 1;
						try {
							Method setColumn = Memorial_statusEntity.class.getMethod("setColumn" + j, int.class);
							setColumn.invoke(memorial_statusEntity, 1);
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
					}
				}
				
			}
			memorial_statusEntities.add(memorial_statusEntity);
		}
		
//		List<List<int[]>> lists = new ArrayList<>();
//		List<int[]> list;
		
//		for(int i = 0; i　＜　tablet.length; i++){
//			
//		}
//		for(int[] t : tablet){
//			list = new ArrayList<>(Arrays.asList(t));
//			lists.add(list);
//		}
		
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		OutputStream os = null;
		String filePath = null;
		String docsPath = null;
		String fileName = null;
		try {
			codedFileName = "牌位信息";
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
//			CriteriaQuery cq = new CriteriaQuery(Memorial_tabletEntity.class, dataGrid);
//			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, memorial_tabletEntity, request.getParameterMap());
			
//			List<Memorial_tabletEntity> memorial_tabletEntitys = this.memorial_tabletService.getListByCriteriaQuery(cq,false);
			GregorianCalendar now = new GregorianCalendar();
			int year = now.get(Calendar.YEAR);
			int month = now.get(Calendar.MONTH) + 1;
			int day = now.get(Calendar.DATE);
			
			NumberFormat f = new DecimalFormat("00");
			String monthStr = f.format(month);
			String dayStr = f.format(day);
			
			String title = building_name + dan_name + year + "年" + month + "月" + day + "日销售情况表";
			workbook = ExcelExportUtil.exportStatusExcel(new ExcelTitle(title, null,
					"导出信息"), Memorial_statusEntity.class, memorial_statusEntities, culumnCount);
//			fOut = response.getOutputStream();
			
			docsPath = request.getSession().getServletContext()
					.getRealPath("export/excel");
			
			deleteFile(docsPath);
//			System.out.println(docsPath);
			
//			String fileName = "export2007_" + System.currentTimeMillis() + ".xlsx";
			fileName = "gdt" + year + monthStr + dayStr + ".xls";
			filePath = docsPath + FILE_SEPARATOR + fileName;
			// 输出流
			os = new FileOutputStream(filePath);
			
			workbook.write(os);
//			workbook.write(fOut);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				os.flush();
				os.close();
				response.getWriter().write(fileName);
//				fOut.flush();
//				fOut.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
	/**
	 * 删除已下载的Excel
	 * @param notepath
	 */
	private void deleteFile(String notepath) {
		File file = new File(notepath);
		File[] files = file.listFiles();
		System.out.println(files.length);
		for (int i = 0; i < files.length; i++) {
			File tmp = files[i];
			tmp.delete();
		}
	}
	
	/**
	 * 加载明细列表[姓名列表]
	 * 
	 * @return
	 */
	@RequestMapping(params = "namelistList")
	public ModelAndView namelistList(Memorial_tabletEntity memorial_tablet, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = memorial_tablet.getId();
		//===================================================================================
		//查询-姓名列表
	    String hql0 = "from NamelistEntity where 1 = 1 AND tabletId = ? ";
		try{
		    List<NamelistEntity> namelistEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("namelistList", namelistEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/sys/memorial_tablet/namelistList");
	}
	/**
	 * 加载明细列表[联系人列表]
	 * 
	 * @return
	 */
	@RequestMapping(params = "linkmanlistList")
	public ModelAndView linkmanlistList(Memorial_tabletEntity memorial_tablet, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id1 = memorial_tablet.getId();
		//===================================================================================
		//查询-联系人列表
	    String hql1 = "from LinkmanlistEntity where 1 = 1 AND tabletId = ? ";
		try{
		    List<LinkmanlistEntity> linkmanlistEntityList = systemService.findHql(hql1,id1);
			req.setAttribute("linkmanlistList", linkmanlistEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/sys/memorial_tablet/linkmanlistList");
	}
	
	
	@RequestMapping(params = "conditionQuery")
	public String conditionQuery(HttpServletRequest req,@RequestParam(value = "pageNo",required = false) String pageNo,Model model,@RequestParam(value = "searchValue",required = false) String searchValue,@RequestParam(value = "select",required = false) String select){
		if((pageNo == null) || ("".equals(pageNo))) {
			pageNo = "1";
		}
		System.out.println(select + searchValue + "================================");
		return "com/sys/memorial_tablet/queryResult";
	}
	
	/**
	 * 查询空余的牌位
	 * @param building_name
	 * @param dan_name
	 * @param req
	 * @param resp
	 */
	@RequestMapping(params = "queryTablet")
	public void queryTablet(String building_name,String dan_name, HttpServletRequest req,HttpServletResponse resp){
		building_name = req.getParameter("building_name");
		dan_name =  req.getParameter("dan_name");
		try {
			building_name = URLDecoder.decode(building_name, "utf-8");
			dan_name = URLDecoder.decode(dan_name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		CriteriaQuery cqTablet = new CriteriaQuery(Memorial_tabletEntity.class);
		cqTablet.add(Restrictions.and(Restrictions.eq("buildingName", building_name), Restrictions.eq("dan", dan_name)));
		List<Memorial_tabletEntity> memorial_tabletEntityList = memorial_tabletService.getListByCriteriaQuery(cqTablet,false);
		
		
		
		CriteriaQuery cq = new CriteriaQuery(M_infoEntity.class);
		cq.add(Restrictions.and(Restrictions.eq("buildingName", building_name), Restrictions.eq("danName", dan_name)));
		M_infoEntity me = (M_infoEntity) m_infoService.getListByCriteriaQuery(cq, false).get(0);
		
		int tablet[][] = new int[me.getRow()+1][me.getColumn()+1];
		
		for(int i = 1;i < me.getRow()+1;i ++){
			for(int j = 1;j < me.getColumn();j ++){
				tablet[i][j] = 0;
			}
		}
		
		for(int i = 1;i < me.getRow()+1;i ++){
			for(int j = 1;j < me.getColumn()+1;j ++){//20190527 kooking
				for(Memorial_tabletEntity m : memorial_tabletEntityList){
					if(i == m.getRow() && j == m.getOrdernumber()){
						tablet[i][j] = 1;
					}
				}
			}
		}
		String result = "";
		result = getJSONArrayOfTabletArray(tablet).toString();
			 
		try {
			resp.getWriter().write(result);
		    } catch (IOException e) {
		      e.printStackTrace();
		}
		
		
	}
	
	
	
	
	/**
	 * 转换为JSON数据
	 * @param ancestors
	 * @return
	 */
	private static JSONArray getJSONArrayOfTabletArray(int tablet[][]){
		JSONArray json = new JSONArray();
		for(int i = 0;i < tablet.length;i++){
			JSONArray sub_json = new JSONArray();
			for(int j = 0;j < tablet[0].length;j ++){
				sub_json.add(tablet[i][j]);
			}
			json.add(sub_json);
		}
		/*for(RoomEntity room : rooms){
            JSONObject jo = new JSONObject();
            jo.put("id", room.getId());
            jo.put("roomNumber", room.getRoomNumber());
            jo.put("roomKind", room.getRoomKind());
            jo.put("restBeds", room.getRestbeds());
            json.add(jo);
        }*/
		return json;
		
	}
	
	@RequestMapping(params = "registerTabletInfo")
	public ModelAndView registerTabletInfo(HttpServletRequest req) {
		String selectedTablet = req.getParameter("selectedTablet");
		String building_name = req.getParameter("building_name");
		String dan_name =  req.getParameter("dan_name");
		try {
			building_name = URLDecoder.decode(building_name, "utf-8");
			dan_name = URLDecoder.decode(dan_name, "utf-8");
		} catch (UnsupportedEncodingException e) {
			
			e.printStackTrace();
		}
		Memorial_tabletEntity memorial_tablet = new Memorial_tabletEntity();
		memorial_tablet.setBuildingName(building_name);
		memorial_tablet.setDan(dan_name);
		memorial_tablet.setRow(Integer.parseInt(selectedTablet.split("@")[0]));
		memorial_tablet.setOrdernumber(Integer.parseInt(selectedTablet.split("@")[1]));
		
		//生成编号
		StringBuilder serial=new StringBuilder(building_name);
		serial.append(dan_name);
		serial.append(memorial_tablet.getRow()+"行");
		serial.append(memorial_tablet.getOrdernumber()+"列");
		
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		Date currentTime = new Date();
		serial.append(formatter.format(currentTime));
		
		memorial_tablet.setSerial(serial.toString());
		
		req.setAttribute("memorial_tabletPage", memorial_tablet);
		req.setAttribute("selectedTablet", selectedTablet);
		req.setAttribute("building_name", building_name);
		req.setAttribute("dan_name", dan_name);
		return new ModelAndView("com/sys/memorial_tablet/registerTabletInfo");
	}
	
	/**
	 * 添加牌位表
	 *应甲方要求，取消收据页面，也不需要将数据保存到收据表中
	 * @param ids
	 * @return
	 */
	/*@RequestMapping(params = "saveTablet")
	public ModelAndView saveTablet(Memorial_tabletEntity memorial_tablet,Memorial_tabletPage memorial_tabletPage, HttpServletRequest request) {
		List<NamelistEntity> namelistList =  memorial_tabletPage.getNamelistList();
		List<LinkmanlistEntity> linkmanlistList =  memorial_tabletPage.getLinkmanlistList();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currentTime = new Date();
		memorial_tablet.setRegistertime(formatter.format(currentTime));
		memorial_tabletService.addMain(memorial_tablet, namelistList,
				linkmanlistList);
		String str = "已经购买了" + memorial_tablet.getRow() + "行" + memorial_tablet.getOrdernumber() + "列牌位";
		request.setAttribute("summary", str);
		return new ModelAndView("com/sys/memorial_tablet/writeReceipt");
	}*/
	
	
	
	/**
	* @Title: saveTablet
	* @Description: 保存牌位，直接跳转到提示页面提示，不需要收据
	* @param memorial_tablet
	* @param memorial_tabletPage
	* @param request
	* @return   
	* @return ModelAndView    返回类型
	* @throws
	*/ 
	@RequestMapping(params = "saveTablet")
	public ModelAndView saveTablet(Memorial_tabletEntity memorial_tablet,Memorial_tabletPage memorial_tabletPage, HttpServletRequest request) {
		List<NamelistEntity> namelistList =  memorial_tabletPage.getNamelistList();
		List<LinkmanlistEntity> linkmanlistList =  memorial_tabletPage.getLinkmanlistList();

		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date currentTime = new Date();
		memorial_tablet.setRegistertime(formatter.format(currentTime));
		memorial_tabletService.addMain(memorial_tablet, namelistList,
				linkmanlistList);

		//获取牌位内容并显示到页面
		StringBuilder sb=new StringBuilder("您已经购买了");
		sb.append(memorial_tablet.getSerial());
		sb.append(",牌位内容为：");
		sb.append("<br>");
		for (int i = 0; i < namelistList.size(); i++) {
			if (i<namelistList.size()-1) {
				sb.append(namelistList.get(i).getName()+"    ");
			}else {
				sb.append(namelistList.get(i).getName());
			}
			
		}
		
		request.setAttribute("message", sb.toString());
		return new ModelAndView("com/sys/updateSuccess");
	}
	
	@RequestMapping(params = "saveTabletReceipt")
	public ModelAndView saveTabletReceipt(HttpServletRequest req,Model model) {
		ReceiptEntity receipt = new ReceiptEntity();
		
		String paymen = req.getParameter("paymen");
		String money = req.getParameter("money");
		String payway = req.getParameter("payway");
		String address = req.getParameter("address");
		String summary = req.getParameter("summary");
		
		receipt.setPaymen(paymen);
		receipt.setMoney(Integer.valueOf(money));
		receipt.setPayway(payway);
		receipt.setAddress(address);
		receipt.setSummary(summary);
		
		TSUser user = ResourceUtil.getSessionUserName();
		receipt.setRegistrant(user.getRealName());
		Calendar a=Calendar.getInstance();
		ReceiptnoEntity reNo = receiptnoService.findByProperty(ReceiptnoEntity.class, "year",Integer.valueOf(a.get(Calendar.YEAR))).get(0);
		int currentMinCount = reNo.getMincount();
		reNo.setMincount(currentMinCount + 1);
		receiptnoService.updateEntitie(reNo);
		
		NumberFormat f=new DecimalFormat("0000000");
		
		String no = f.format(currentMinCount + 1);
		receipt.setNo("No." + no);
		
		//换时间
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String dateString = formatter.format(currentTime);
		receipt.setSummary(req.getParameter("summary"));
		receipt.setSize("其他");
		receipt.setRegistertime(dateString);
		receipt.setRitualtype("功德堂牌位");
		receipt.setPurpose(req.getParameter("purpose"));
		receipt.setRemark("");
		receiptService.save(receipt);
		
		model.addAttribute("message", "功德堂牌位登记成功");
		model.addAttribute("ritualtype", "功德堂牌位");
		model.addAttribute("returnRe", receipt);
		String bigMoney=ChineseCurrency.toChineseCurrency(new Double(receipt.getMoney()));
		model.addAttribute("bigMoney",bigMoney);
		String smallMoney=ChineseCurrency.toSmall(new Double(receipt.getMoney()));
		model.addAttribute("smallMoney",smallMoney);
		return new ModelAndView("com/sys/success");
	}
	
	@RequestMapping(params = "findDanName")
	@ResponseBody
	public void findDanName(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		AjaxJson j = new AjaxJson();
		String building_name = StringUtil.getEncodePra(req.getParameter("building_name"));
		String names = "";
		List<M_infoEntity> m_infoEntityList = m_infoService.findByProperty(M_infoEntity.class, "buildingName", building_name);
		if (m_infoEntityList.size() > 0) {
			for (M_infoEntity m : m_infoEntityList) {
				names += m.getDanName() +";";
			}
		} else {
			names += "没有合适的段名!";
		}

		resp.getWriter().write(names);
	}
}

