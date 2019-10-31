package com.sys.controller.attendance;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.BrowserUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import test.AttendanceStatistics;
import test.CalEverySaturday;
import test.CalRegularTime;
import test.FuneralGatherCensus;
import test.Lunar;
import test.LunarCalendar;
import test.LunarToSolar;
import test.Mor_Eve_Pray_Census;
import test.MyExportXls;
import test.TmpFuneralStatistics;

import com.sys.entity.attendance.AttendanceEntity;
import com.sys.entity.eveningpforr.EveningpforrEntity;
import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.morningpforr.MorningpforrEntity;
import com.sys.entity.pravrajanamember.PravrajanamemberEntity;
import com.sys.entity.prayguanyin.PrayguanyinEntity;
import com.sys.service.attendance.AttendanceServiceI;
import com.sys.service.eveningpforr.EveningpforrServiceI;
import com.sys.service.funeralheld.FuneralheldServiceI;
import com.sys.service.morningpforr.MorningpforrServiceI;
import com.sys.service.pravrajanamember.PravrajanamemberServiceI;
import com.sys.service.prayguanyin.PrayguanyinServiceI;

/**   
 * @Title: Controller
 * @Description: 考勤管理
 * @author zhangdaihao
 * @date 2016-10-03 14:54:23
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/attendanceController")
public class AttendanceController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AttendanceController.class);

	@Autowired
	private AttendanceServiceI attendanceService;
	@Autowired
	private PravrajanamemberServiceI pravrajanamemberService;
	@Autowired
	private EveningpforrServiceI eveningpforrService;
	@Autowired
	private FuneralheldServiceI funeralheldService;
	@Autowired
	private MorningpforrServiceI morningpforrService;
	@Autowired
	private PrayguanyinServiceI prayguanyinService;
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
	 * 考勤管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "attendance")
	public ModelAndView attendance(HttpServletRequest request) {
		return new ModelAndView("com/sys/attendance/attendanceList");
	}
	
	/**
	 * 二时临斋考勤管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "twoTimeAttendance")
	public ModelAndView twoTimeAttendance(HttpServletRequest request) {
		return new ModelAndView("com/sys/attendance/twotimelinzhai/twoTimeAttendanceList");
	}
	
	/**
	 * 大悲共修考勤管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "compassionMeditation")
	public ModelAndView compassionMeditation(HttpServletRequest request) {
		return new ModelAndView("com/sys/attendance/compassionMeditation/CompassionMeditationList");
	}

	
	/**
	 *金刚经考勤管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "diamondSutra")
	public ModelAndView diamondSutra(HttpServletRequest request) {
		return new ModelAndView("com/sys/attendance/diamondSutra/diamondSutraAttendanceList");
	}

	
	/**
	 * 二时临斋考勤管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "religiousAttendance")
	public ModelAndView religiousAttendance(HttpServletRequest request) {
		return new ModelAndView("com/sys/attendance/religious/religiousAttendanceList");
	}
	
	
	/**
	 * 考勤统计  页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "attendanceStatistics")
	public ModelAndView attendanceStatistics(HttpServletRequest request) {
		return new ModelAndView("com/sys/attendance/statistics/index");
	}
	
	/**
	 * 考勤统计  页面跳转
	 */
	@RequestMapping(params = "fowardSelect")
	public ModelAndView fowardSelect(HttpServletRequest req) {
		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
		req.setAttribute("pravrajanamemberEntityList", pravrajanamemberEntityList);
		
		return new ModelAndView("com/sys/attendance/statistics/selectCondition");
	}
	
	/**
	 * 早晚普佛上供法事堂数统计  页面跳转
	 */
	@RequestMapping(params = "forwardToSelectCondition")
	public ModelAndView forwardToSelectCondition(HttpServletRequest req) {
		
		return new ModelAndView("com/sys/mor_eve_pray_census/index");
	}
	/**
	 * 早晚普佛上供法事堂数统计  页面跳转
	 */
	@RequestMapping(params = "selectCondition")
	public ModelAndView selectCondition(HttpServletRequest req) {
		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
		req.setAttribute("pravrajanamemberEntityList", pravrajanamemberEntityList);
		
		return new ModelAndView("com/sys/mor_eve_pray_census/selectCondition");
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
	public void datagrid(AttendanceEntity attendance,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AttendanceEntity.class, dataGrid);
		//查询条件组装器
		cq.add(Restrictions.eq("attendanceType", "早晚课诵"));
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, attendance, request.getParameterMap());
		this.attendanceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 二时临斋考勤管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "twoTimeAttendanceDatagrid")
	public void twoTimeAttendanceDatagrid(AttendanceEntity attendance,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AttendanceEntity.class, dataGrid);
		//查询条件组装器
		cq.add(Restrictions.eq("attendanceType", "二时临斋"));
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, attendance, request.getParameterMap());
		this.attendanceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 金刚经考勤管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "diamondSutraDatagrid")
	public void diamondSutraDatagrid(AttendanceEntity attendance,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AttendanceEntity.class, dataGrid);
		//查询条件组装器
		cq.add(Restrictions.eq("attendanceType", "金刚经"));
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, attendance, request.getParameterMap());
		this.attendanceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 考勤管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "compassionMeditationDatagrid")
	public void compassionMeditationDatagrid(AttendanceEntity attendance,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
	
		CriteriaQuery cq = new CriteriaQuery(AttendanceEntity.class, dataGrid);
		//查询条件组装器
		cq.add(Restrictions.eq("attendanceType", "大悲共修"));
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, attendance, request.getParameterMap());
		this.attendanceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	
	/**
	 * 考勤管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "religiousAttendanceDatagrid")
	public void religiousAttendanceDatagrid(AttendanceEntity attendance,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AttendanceEntity.class, dataGrid);
		//查询条件组装器
		cq.add(Restrictions.and(Restrictions.ne("attendanceType", "金刚经"),Restrictions.ne("attendanceType", "大悲共修"),
				Restrictions.ne("attendanceType", "二时临斋"),Restrictions.ne("attendanceType", "早晚课诵")));
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, attendance, request.getParameterMap());
		this.attendanceService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 删除考勤管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AttendanceEntity attendance, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		attendance = systemService.getEntity(AttendanceEntity.class, attendance.getId());
		message = "考勤管理删除成功";
		attendanceService.delete(attendance);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加考勤管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AttendanceEntity attendance, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(attendance.getId())) {
			message = "考勤管理更新成功";
			AttendanceEntity t = attendanceService.get(AttendanceEntity.class, attendance.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(attendance, t);
				attendanceService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "考勤管理更新失败";
			}
		} else {
			message = "考勤管理添加成功";
			attendanceService.save(attendance);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 考勤管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AttendanceEntity attendance, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(attendance.getId())) {
			attendance = attendanceService.getEntity(AttendanceEntity.class, attendance.getId());
			req.setAttribute("attendancePage", attendance);
		}
		return new ModelAndView("com/sys/attendance/attendance");
	}
	
	/**
	 * 跳转到录入考勤页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "towardShowCrowdsRecord")
	public ModelAndView towardMorningAndEveningPuja(HttpServletRequest req) {
/*		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.findByProperty(PravrajanamemberEntity.class, propertyName, value)*/
		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
		String type = req.getParameter("type");
		String message = "";
		if(type.equals("1")){
			message = "早晚课诵";
		}
		if(type.equals("2")){
			message = "二时临斋";
		}
		if(type.equals("3")){
			message = "大悲共修";
		}
		if(type.equals("4")){
			message = "金刚经";
		}
		if(type.equals("5")){
			message = "法事";
		}
		
		int remainder = pravrajanamemberEntityList.size() % 10;
		int divisor  = pravrajanamemberEntityList.size() / 10;
		
		
		
		req.setAttribute("pravrajanamemberEntityList", pravrajanamemberEntityList);
		req.setAttribute("length", pravrajanamemberEntityList.size());
		req.setAttribute("remainder", remainder);
		req.setAttribute("divisor", divisor);
		req.setAttribute("type", type);
		req.setAttribute("message", message);
		return new ModelAndView("com/sys/attendance/showCrowdsRecord");
	}
	
	@RequestMapping(params = "getHoldDate")
	@ResponseBody
	public void getHoldDate(HttpServletRequest req,HttpServletResponse resp,String ritualtype,String year) throws IOException, ParseException {
		
		
		ritualtype = URLDecoder.decode(ritualtype, "utf-8");
		System.out.println("month" + ritualtype);
		AjaxJson j = new AjaxJson();
		
		CriteriaQuery cq = new CriteriaQuery(FuneralheldEntity.class);
		cq.add(Restrictions.and(Restrictions.eq("ritualtype", ritualtype),Restrictions.eq("holdYear", year)));
		FuneralheldEntity fe = (FuneralheldEntity) funeralheldService.getListByCriteriaQuery(cq, false).get(0);
		String result = "";
		
		if(fe != null){
			result += fe.getHoldDate() + ";" + fe.getContinuouDays();
		}
		else{
			result = "查找错误";
		}
		
		
		

		resp.getWriter().write(result);
	}
	/**
	 * 跳转到录入考勤页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "addMorningAndEveningPuja")
	public ModelAndView addMorningAndEveningPuja(HttpServletRequest req,String dharmaname) {
/*		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.findByProperty(PravrajanamemberEntity.class, propertyName, value)*/
		dharmaname = req.getParameter("dharmaname");
		String type = req.getParameter("type");
		if(dharmaname != null){
			try {
				dharmaname = URLDecoder.decode(dharmaname, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		req.setAttribute("dharmaname", dharmaname);
		if(type.equals("1")){
			return new ModelAndView("com/sys/attendance/addMorningAndEveningPujaRecord");
		}
		if(type.equals("2")){
			return new ModelAndView("com/sys/attendance/twotimelinzhai/addTwoTimeLinZhaiRecord");
		}
		if(type.equals("3")){
			return new ModelAndView("com/sys/attendance/compassionMeditation/addCompassionMeditationRecord");
		}
		if(type.equals("4")){
			return new ModelAndView("com/sys/attendance/diamondSutra/addDiamondSutraRecord");
		}
		if(type.equals("5")){
			return new ModelAndView("com/sys/attendance/religious/addReligiousRecord");
		}
		return null;
		
	}
	
	/**
	 * 跳转到录入考勤页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "addMorningAndEveningPujaRecord")
	public ModelAndView addMorningAndEveningPujaRecord(HttpServletRequest req,String allRecords,String dharmaname,String month,String year) {
/*		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.findByProperty(PravrajanamemberEntity.class, propertyName, value)*/
		if(allRecords != null && dharmaname != null){
			try {
				dharmaname = URLDecoder.decode(dharmaname, "utf-8");
				month = URLDecoder.decode(month, "utf-8");
				
				allRecords = URLDecoder.decode(allRecords, "utf-8");
				String[] allRecordsSplitByZero = allRecords.substring(0, allRecords.length() - 1).split("0");
				
				for(int i = 0 ; i < allRecordsSplitByZero.length ; i ++){
					
					String[] tmp = allRecordsSplitByZero[i].split(";");
					String lunarString = year + "#" + month + "#" + tmp[0];
					String solarString = LunarToSolar.LunarToSolar(lunarString);
					
					for(int j = 1;j <= 3;j++){
						String reason = "";
						if(tmp[j].equals("1")){
							reason = "事假" + j;
						}
						if(tmp[j].equals("2")){
							reason = "迟到" + j;
						}
						if(tmp[j].equals("3")){
							reason = "早退" + j;
						}
						if(tmp[j].equals("4")){
							reason = "缺课" + j;
						}
						if(tmp[j].equals("5")){
							reason = "私假" + j;
						}
						if(tmp[j].equals("6")){
							reason = "病假" + j;
						}
						if(tmp[j].equals("7")){
							reason = "外出" + j;
						}
						if(reason != ""){
							AttendanceEntity ae = new AttendanceEntity();
							ae.setAttendanceType("早晚课诵");
							ae.setSolardate(solarString);
							ae.setMemberName(dharmaname);
							ae.setReason(reason);
							ae.setLunardate(lunarString.replace("#",""));
							attendanceService.save(ae);
						}
						
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		req.setAttribute("type", "1");
		return new ModelAndView("com/sys/attendance/jumpingToCrowds");
	}
	
	
	
	/**
	 * 跳转到录入二时供斋考勤页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "addTwoTimeLinZhaiRecord")
	public ModelAndView addTwoTimeLinZhaiRecord(HttpServletRequest req,String allRecords,String dharmaname,String month,String year) {
/*		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.findByProperty(PravrajanamemberEntity.class, propertyName, value)*/
		if(allRecords != null && dharmaname != null){
			try {
				dharmaname = URLDecoder.decode(dharmaname, "utf-8");
				month = URLDecoder.decode(month, "utf-8");
				
				allRecords = URLDecoder.decode(allRecords, "utf-8");
				String[] allRecordsSplitByZero = allRecords.substring(0, allRecords.length() - 1).split("0");
				
				for(int i = 0 ; i < allRecordsSplitByZero.length ; i ++){
					
					String[] tmp = allRecordsSplitByZero[i].split(";");
					String lunarString = year + "#" + month + "#" + tmp[0];
					String solarString = LunarToSolar.LunarToSolar(lunarString);
					
					for(int j = 1;j <= 2;j++){
						String reason = "";
						if(tmp[j].equals("1")){
							reason = "事假" + j;
						}
						if(tmp[j].equals("2")){
							reason = "迟到" + j;
						}
						if(tmp[j].equals("3")){
							reason = "早退" + j;
						}
						if(tmp[j].equals("4")){
							reason = "缺课" + j;
						}
						if(tmp[j].equals("5")){
							reason = "私假" + j;
						}
						if(tmp[j].equals("6")){
							reason = "病假" + j;
						}
						if(tmp[j].equals("7")){
							reason = "外出" + j;
						}
						if(reason != ""){
							AttendanceEntity ae = new AttendanceEntity();
							ae.setAttendanceType("二时临斋");
							ae.setSolardate(solarString);
							ae.setMemberName(dharmaname);
							ae.setReason(reason);
							ae.setLunardate(lunarString.replace("#",""));
							attendanceService.save(ae);
						}
						
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		req.setAttribute("type", "2");
		return new ModelAndView("com/sys/attendance/jumpingToCrowds");
	}
	
	/**
	 * ajax获取每个月的周末
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(params = "calSaturday")
	@ResponseBody
	public void calSaturday(HttpServletRequest req,HttpServletResponse resp,String month,String year) throws IOException, ParseException {
		
		
		month = URLDecoder.decode(month, "utf-8");
		System.out.println("month" + month);
		AjaxJson j = new AjaxJson();
		
		String[] lunarMonth = {"正月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","腊月"};
		
		String lunarBeginString = year + "#" + month + "#" + "初一";
		
		int days = 0;
		for(int i = 0;i < lunarMonth.length;i ++){
			if(lunarMonth[i].equals(month)){
				days = LunarCalendar.daysInMonth(Integer.valueOf(year),i + 1);
				break;
			}
		}
		
		System.out.println("month" + month);
		
		String[] lunarDay = {"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三",
			    "十四","十五","十六", "十七", "十八","十九","廿十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","卅十"};
		
		System.out.println("days ：   "  + lunarDay[days - 1]);
		
		String lunarEndString = year + "#" + month + "#" + lunarDay[days - 1];
		String solarBeginString = LunarToSolar.LunarToSolar(lunarBeginString);
		
		String solarEndString = LunarToSolar.LunarToSolar(lunarEndString);
		
		String eachSaturday = CalEverySaturday.calEverySaturday(solarBeginString, solarEndString);
		
		String[] eachSaturdaySplitBySymbol = eachSaturday.substring(0, eachSaturday.length() - 1).split("#");
		
		String result = "";
		
		for(int i = 0;i < eachSaturdaySplitBySymbol.length; i ++){
			SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy-M-d");
			Calendar saturday = Calendar.getInstance();
			saturday.setTime(chineseDateFormat.parse(eachSaturdaySplitBySymbol[i]));
			Lunar lunar = new Lunar(saturday);
			
			String lunarDate = lunar.toString().split(" ")[2];
			result += lunarDate + ";";
			
		}
		if(result == ""){
			result = "查找错误";
		}

		resp.getWriter().write(result.substring(0, result.length() - 1));
	}
	
	
	
	/**
	 * 跳转到录入大悲共修考勤页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "addCompassionMeditationRecord")
	public ModelAndView addCompassionMeditationRecord(HttpServletRequest req,String allRecords,String dharmaname,String month,String year) {
/*		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.findByProperty(PravrajanamemberEntity.class, propertyName, value)*/
		if(allRecords != null && dharmaname != null){
			try {
				dharmaname = URLDecoder.decode(dharmaname, "utf-8");
				month = URLDecoder.decode(month, "utf-8");
				
				allRecords = URLDecoder.decode(allRecords, "utf-8");
				String[] allRecordsSplitByZero = allRecords.substring(0, allRecords.length() - 1).split("0");
				
				for(int i = 0 ; i < allRecordsSplitByZero.length ; i ++){
					
					String[] tmp = allRecordsSplitByZero[i].split(";");
					String lunarString = year + "#" + month + "#" + tmp[0];
					String solarString = LunarToSolar.LunarToSolar(lunarString);
					
					for(int j = 1;j <= 2;j++){
						String reason = "";
						if(tmp[j].equals("1")){
							reason = "事假" + j;
						}
						if(tmp[j].equals("2")){
							reason = "迟到" + j;
						}
						if(tmp[j].equals("3")){
							reason = "早退" + j;
						}
						if(tmp[j].equals("4")){
							reason = "缺课" + j;
						}
						if(tmp[j].equals("5")){
							reason = "私假" + j;
						}
						if(tmp[j].equals("6")){
							reason = "病假" + j;
						}
						if(tmp[j].equals("7")){
							reason = "外出" + j;
						}
						if(reason != ""){
							AttendanceEntity ae = new AttendanceEntity();
							ae.setAttendanceType("大悲共修");
							ae.setSolardate(solarString);
							ae.setMemberName(dharmaname);
							ae.setReason(reason);
							ae.setLunardate(lunarString.replace("#",""));
							attendanceService.save(ae);
						}
						
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		req.setAttribute("type", "3");
		return new ModelAndView("com/sys/attendance/jumpingToCrowds");
	}
	
	/**
	 * ajax获取每个月的周一，周二，周三，周五
	 * 
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping(params = "calRegularTime")
	@ResponseBody
	public void calRegularTime(HttpServletRequest req,HttpServletResponse resp,String month,String year) throws IOException, ParseException {
		
		
		month = URLDecoder.decode(month, "utf-8");
		System.out.println("month" + month);
		AjaxJson j = new AjaxJson();
		
		String[] lunarMonth = {"正月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","腊月"};
		
		String lunarBeginString = year + "#" + month + "#" + "初一";
		
		int days = 0;
		for(int i = 0;i < lunarMonth.length;i ++){
			if(lunarMonth[i].equals(month)){
				days = LunarCalendar.daysInMonth(Integer.valueOf(year),i + 1);
				break;
			}
		}
		
		
		String[] lunarDay = {"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三",
			    "十四","十五","十六", "十七", "十八","十九","廿十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","卅十"};
		
		System.out.println("days ：   "  + lunarDay[days - 1]);
		
		String lunarEndString = year + "#" + month + "#" + lunarDay[days - 1];
		String solarBeginString = LunarToSolar.LunarToSolar(lunarBeginString);
		
		String solarEndString = LunarToSolar.LunarToSolar(lunarEndString);
		
		String eachRegularTime = CalRegularTime.calEverySaturday(solarBeginString, solarEndString);
		
		String[] eachRegularTimeSplitBySymbol = eachRegularTime.substring(0, eachRegularTime.length() - 1).split("#");
		
		String result = "";
		
		for(int i = 0;i < eachRegularTimeSplitBySymbol.length; i ++){
			SimpleDateFormat chineseDateFormat = new SimpleDateFormat("yyyy-M-d");
			Calendar regularTime = Calendar.getInstance();
			regularTime.setTime(chineseDateFormat.parse(eachRegularTimeSplitBySymbol[i]));
			Lunar lunar = new Lunar(regularTime);
			
			String lunarDate = lunar.toString().split(" ")[2];
			result += lunarDate + ";";
			
		}
		if(result == ""){
			result = "查找错误";
		}

		resp.getWriter().write(result.substring(0, result.length() - 1));
	}
	
	/**
	 * 跳转到录入大悲共修考勤页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "addDiamodSutraRecord")
	public ModelAndView addDiamodSutraRecord(HttpServletRequest req,String allRecords,String dharmaname,String month,String year) {
/*		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.findByProperty(PravrajanamemberEntity.class, propertyName, value)*/
		if(allRecords != null && dharmaname != null){
			try {
				dharmaname = URLDecoder.decode(dharmaname, "utf-8");
				month = URLDecoder.decode(month, "utf-8");
				
				allRecords = URLDecoder.decode(allRecords, "utf-8");
				String[] allRecordsSplitByZero = allRecords.substring(0, allRecords.length() - 1).split("0");
				
				for(int i = 0 ; i < allRecordsSplitByZero.length ; i ++){
					
					String[] tmp = allRecordsSplitByZero[i].split(";");
					String lunarString = year + "#" + month + "#" + tmp[0];
					String solarString = LunarToSolar.LunarToSolar(lunarString);
					
					for(int j = 1;j <= 2;j++){
						String reason = "";
						
						if(tmp[j].equals("1")){
							reason = "事假" + j;
						}
						if(tmp[j].equals("2")){
							reason = "迟到" + j;
						}
						if(tmp[j].equals("3")){
							reason = "早退" + j;
						}
						if(tmp[j].equals("4")){
							reason = "缺课" + j;
						}
						if(tmp[j].equals("5")){
							reason = "私假" + j;
						}
						if(tmp[j].equals("6")){
							reason = "病假" + j;
						}
						if(tmp[j].equals("7")){
							reason = "外出" + j;
						}
						if(reason != ""){
							AttendanceEntity ae = new AttendanceEntity();
							ae.setAttendanceType("金刚经");
							ae.setSolardate(solarString);
							ae.setMemberName(dharmaname);
							ae.setReason(reason);
							ae.setLunardate(lunarString.replace("#",""));
							attendanceService.save(ae);
						}
						
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		req.setAttribute("type", "4");
		return new ModelAndView("com/sys/attendance/jumpingToCrowds");
	}
	
	
	/**
	 * 跳转到录入考勤页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "addReligiousRecord")
	public ModelAndView addReligiousRecord(HttpServletRequest req,String allRecords,String dharmaname,String month,String year,String ritualtype) {
/*		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.findByProperty(PravrajanamemberEntity.class, propertyName, value)*/
		if(allRecords != null && dharmaname != null){
			try {
				dharmaname = URLDecoder.decode(dharmaname, "utf-8");
				month = URLDecoder.decode(month, "utf-8");
				ritualtype = URLDecoder.decode(ritualtype, "utf-8");
				
				allRecords = URLDecoder.decode(allRecords, "utf-8");
				String[] allRecordsSplitByZero = allRecords.substring(0, allRecords.length() - 1).split("0");
				
				for(int i = 0 ; i < allRecordsSplitByZero.length ; i ++){
					
					String[] tmp = allRecordsSplitByZero[i].split(";");
					String lunarString = year + "#" + month + "#" + tmp[0];
					String solarString = LunarToSolar.LunarToSolar(lunarString);
					
					for(int j = 1;j <= 2;j++){
						String reason = "";
						if(tmp[j].equals("1")){
							reason = "事假" + j;
						}
						if(tmp[j].equals("2")){
							reason = "迟到" + j;
						}
						if(tmp[j].equals("3")){
							reason = "早退" + j;
						}
						if(tmp[j].equals("4")){
							reason = "缺课" + j;
						}
						if(tmp[j].equals("5")){
							reason = "私假" + j;
						}
						if(tmp[j].equals("6")){
							reason = "病假" + j;
						}
						if(tmp[j].equals("7")){
							reason = "外出" + j;
						}
						if(reason != ""){
							AttendanceEntity ae = new AttendanceEntity();
							ae.setAttendanceType(ritualtype);
							ae.setSolardate(solarString);
							ae.setMemberName(dharmaname);
							ae.setReason(reason);
							ae.setLunardate(lunarString.replace("#",""));
							attendanceService.save(ae);
						}
						
					}
				}
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		req.setAttribute("type", "5");
		return new ModelAndView("com/sys/attendance/jumpingToCrowds");
	}
	/**
	 * 考勤统计
	 * @param req
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params = "statisticsResult")
	@ResponseBody
	public void statisticsResult(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		AjaxJson j = new AjaxJson();
		
		String month = StringUtil.getEncodePra(req.getParameter("month"));
		String year = req.getParameter("year");
		String attendanceType = StringUtil.getEncodePra(req.getParameter("attendanceType"));
		String name = StringUtil.getEncodePra(req.getParameter("name"));
		
		List<String> nameList = new ArrayList<String>();
		
		List<AttendanceStatistics> attendanceStatisticsList = new ArrayList<AttendanceStatistics>();
		if(name == "" || name == null){
			List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
			for(PravrajanamemberEntity pe : pravrajanamemberEntityList){
				nameList.add(pe.getDharmaname());
			}
		}
		else{
			nameList.add(name);
		}
		
		String[] _enum = {"事假","迟到","早退","缺课","私假","病假","外出"};
		for(String _name : nameList){
			
			AttendanceStatistics as = new AttendanceStatistics();
			as.setName(_name);
			
			for(String kind : _enum){
				CriteriaQuery cq = new CriteriaQuery(AttendanceEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("attendanceType", attendanceType),Restrictions.eq("memberName", _name),Restrictions.like("lunardate", year+month, MatchMode.ANYWHERE),Restrictions.like("reason", kind, MatchMode.ANYWHERE)));
				if(kind.equals("事假")){
					as.set_casual(attendanceService.getListByCriteriaQuery(cq, false).size());
				}
				if(kind.equals("迟到")){
					as.set_late(attendanceService.getListByCriteriaQuery(cq, false).size());
				}
				if(kind.equals("早退")){
					as.set_early(attendanceService.getListByCriteriaQuery(cq, false).size());
				}
				if(kind.equals("缺课")){
					as.set_absent(attendanceService.getListByCriteriaQuery(cq, false).size());
				}
				if(kind.equals("私假")){
					as.set_private(attendanceService.getListByCriteriaQuery(cq, false).size());
				}
				if(kind.equals("病假")){
					as.set_sick(attendanceService.getListByCriteriaQuery(cq, false).size());
				}
				if(kind.equals("外出")){
					as.set_out(attendanceService.getListByCriteriaQuery(cq, false).size());
				}
			}
			attendanceStatisticsList.add(as);
		}
		
		
		String res = getJSONArrayOfAttendanceStatisticsEntity(attendanceStatisticsList).toString();
		resp.getWriter().write(res);
	}
	private static JSONArray getJSONArrayOfAttendanceStatisticsEntity(List<AttendanceStatistics> attendanceStatisticsList){
		JSONArray json = new JSONArray();
		for(AttendanceStatistics as : attendanceStatisticsList){
            JSONObject jo = new JSONObject();
            jo.put("name", as.getName());
            jo.put("casual", as.get_casual());
            jo.put("late", as.get_late());
            jo.put("early", as.get_early());
            jo.put("absent", as.get_absent());
            jo.put("_private", as.get_private());
            jo.put("sick", as.get_sick());
            jo.put("out", as.get_out());
            
            json.add(jo);
        }
		return json;
		
	}
	
	
	@RequestMapping(params = "mor_eve_pray_census")
	@ResponseBody
	public void mor_eve_pray_census(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		AjaxJson j = new AjaxJson();
		
		String month = StringUtil.getEncodePra(req.getParameter("month"));
		String year = req.getParameter("year");
		String name = StringUtil.getEncodePra(req.getParameter("name"));
		
		System.out.println(month + "=======================");
		List<String> nameList = new ArrayList<String>();
		
		List<AttendanceStatistics> attendanceStatisticsList = new ArrayList<AttendanceStatistics>();
		if(name == "" || name == null){
			List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
			for(PravrajanamemberEntity pe : pravrajanamemberEntityList){
				nameList.add(pe.getDharmaname());
			}
		}
		else{
			nameList.add(name);
		}
		
		CriteriaQuery cq = new CriteriaQuery(MorningpforrEntity.class);
		cq.add(Restrictions.like("lunardate", month,MatchMode.ANYWHERE));
		int allMorningPforr = morningpforrService.getListByCriteriaQuery(cq, false).size();
		
		CriteriaQuery cq1 = new CriteriaQuery(EveningpforrEntity.class);
		cq1.add(Restrictions.like("lunardate", month,MatchMode.ANYWHERE));
		int allEveningPforr = eveningpforrService.getListByCriteriaQuery(cq1, false).size();
		
		CriteriaQuery cq2 = new CriteriaQuery(PrayguanyinEntity.class);
		cq2.add(Restrictions.like("lunardate", month,MatchMode.ANYWHERE));
		int allPrayGuanyin = prayguanyinService.getListByCriteriaQuery(cq2, false).size();
		
		List<Mor_Eve_Pray_Census> mepcList = new ArrayList<Mor_Eve_Pray_Census>();
		
		List<MorningpforrEntity> morningpforrEntityList = morningpforrService.getListByCriteriaQuery(cq, false);
		List<EveningpforrEntity> eveningpforrEntityList = eveningpforrService.getListByCriteriaQuery(cq1, false);
		List<PrayguanyinEntity> prayguanyinEntityList = prayguanyinService.getListByCriteriaQuery(cq2, false);
		
		String[] _enum = {"缺课","私假","病假","外出"};
		
		
		for(int i = 0;i < nameList.size();i ++){
			Mor_Eve_Pray_Census mepc = new Mor_Eve_Pray_Census();
			mepc.setName(nameList.get(i));
			int morningPforrAbsent = 0;
			int eveningPforrAbsent = 0;
			int prayguanyinAbsent = 0;
			for(MorningpforrEntity me : morningpforrEntityList){
				
				for(int k = 0; k < _enum.length;k ++){
					CriteriaQuery cqAttend1 = new CriteriaQuery(AttendanceEntity.class);
					cqAttend1.add(Restrictions.and(
							Restrictions.eq("attendanceType","早晚课诵"),Restrictions.eq("memberName", nameList.get(i)),
							Restrictions.eq("lunardate",year + me.getLunardate().replaceAll(" ","")),Restrictions.like("reason",_enum[k] + "1", MatchMode.ANYWHERE)));
					morningPforrAbsent += attendanceService.getListByCriteriaQuery(cqAttend1, false).size();
				}
				
			}
			for(EveningpforrEntity ee : eveningpforrEntityList){
				for(int k = 0; k < _enum.length;k ++){
					CriteriaQuery cqAttend2 = new CriteriaQuery(AttendanceEntity.class);
					cqAttend2.add(Restrictions.and(
							Restrictions.eq("attendanceType","早晚课诵"),Restrictions.eq("memberName", nameList.get(i)),
							Restrictions.eq("lunardate",year + ee.getLunardate().replaceAll(" ","")),Restrictions.like("reason",_enum[k] + "3", MatchMode.ANYWHERE)));
					eveningPforrAbsent += attendanceService.getListByCriteriaQuery(cqAttend2, false).size();
				}
			}
			mepc.setAll_absent_pforr(morningPforrAbsent + eveningPforrAbsent);
			for(PrayguanyinEntity pe : prayguanyinEntityList){
	
				for(int k = 0; k < _enum.length;k ++){
					CriteriaQuery cqAttend3 = new CriteriaQuery(AttendanceEntity.class);
					cqAttend3.add(Restrictions.and(
							Restrictions.eq("attendanceType","早晚课诵"),Restrictions.eq("memberName", nameList.get(i)),
							Restrictions.eq("lunardate",year + pe.getLunardate().replaceAll(" ","")),Restrictions.like("reason",_enum[k] + "2", MatchMode.ANYWHERE)));
					prayguanyinAbsent += attendanceService.getListByCriteriaQuery(cqAttend3, false).size();
				}
				
			}
			mepc.setAll_absent_pray(prayguanyinAbsent);
			mepc.setAll_pforr(allEveningPforr + allMorningPforr);
			mepc.setAll_pray(allPrayGuanyin);
			mepc.setAll_join_pforr((allEveningPforr + allMorningPforr) - (morningPforrAbsent + eveningPforrAbsent));
			mepc.setAll_join_pray(allPrayGuanyin - prayguanyinAbsent);
			mepcList.add(mepc);
			
		}
		
		
		String res = getJSONArrayOfMEPCEntity(mepcList).toString();
		resp.getWriter().write(res);
	}
	
	private static JSONArray getJSONArrayOfMEPCEntity(List<Mor_Eve_Pray_Census> mepcList){
		JSONArray json = new JSONArray();
		for(Mor_Eve_Pray_Census mepc : mepcList){
            JSONObject jo = new JSONObject();
            jo.put("name", mepc.getName());
            jo.put("all_pforr", mepc.getAll_pforr());
            jo.put("all_pray", mepc.getAll_pray());
            jo.put("absent_pforr", mepc.getAll_absent_pforr());
            jo.put("absent_pray", mepc.getAll_absent_pray());
            jo.put("join_pforr", mepc.getAll_join_pforr());
            jo.put("join_pray", mepc.getAll_join_pray());
            
            json.add(jo);
        }
		return json;
		
	}

	/**
	 * 早普佛管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "morningPforrStatistics")
	public ModelAndView morningPforrStatistics(HttpServletRequest request) {
		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
		request.setAttribute("pravrajanamemberEntityList", pravrajanamemberEntityList);
		
		return new ModelAndView("com/sys/funeralStatistics/morningPforrStatistics");
	}
	
	@RequestMapping(params = "morningIndex")
	public ModelAndView morningIndex(HttpServletRequest request) {
		
		return new ModelAndView("com/sys/funeralStatistics/morningIndex");
	}
	/**
	 * 晚普佛管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "eveningPforrStatistics")
	public ModelAndView eveningPforrStatistics(HttpServletRequest request) {
		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
		request.setAttribute("pravrajanamemberEntityList", pravrajanamemberEntityList);
		
		return new ModelAndView("com/sys/funeralStatistics/eveningPforrStatistics");
	}
	@RequestMapping(params = "eveningIndex")
	public ModelAndView eveningIndex(HttpServletRequest request) {
		
		return new ModelAndView("com/sys/funeralStatistics/eveningIndex");
	}
	/**
	 * 晚普佛管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "prayGuanyinStatistics")
	public ModelAndView prayGuanyinStatistics(HttpServletRequest request) {
		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
		request.setAttribute("pravrajanamemberEntityList", pravrajanamemberEntityList);
		
		return new ModelAndView("com/sys/funeralStatistics/prayGuanyinStatistics");
	}
	@RequestMapping(params = "prayGuanyinIndex")
	public ModelAndView prayGuanyinIndex(HttpServletRequest request) {
		
		return new ModelAndView("com/sys/funeralStatistics/prayGuanyinIndex");
	}
	
	/**
	 * 早普佛管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "totalStatistics")
	public ModelAndView totalStatistics(HttpServletRequest request) {
		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
		request.setAttribute("pravrajanamemberEntityList", pravrajanamemberEntityList);
		
		return new ModelAndView("com/sys/funeralStatistics/totalStatistics");
	}
	
	@RequestMapping(params = "totalIndex")
	public ModelAndView totalIndex(HttpServletRequest request) {
		
		return new ModelAndView("com/sys/funeralStatistics/totalIndex");
	}
	/**
	 * 早普佛堂数登记
	 * @param req
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params = "morningPforrStatisticsResult")
	@ResponseBody
	public void morningPforrStatisticsResult(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		AjaxJson j = new AjaxJson();
		
		String month = StringUtil.getEncodePra(req.getParameter("month"));
		String year = req.getParameter("year");
		String name = StringUtil.getEncodePra(req.getParameter("name"));
		
		List<String> nameList = new ArrayList<String>();
		
		if(name == "" || name == null){
			List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
			for(PravrajanamemberEntity pe : pravrajanamemberEntityList){
				nameList.add(pe.getDharmaname());
			}
		}
		else{
			nameList.add(name);
		}
		String[] lunarDay = {"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三",
			    "十四","十五","十六", "十七", "十八","十九","廿十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","卅十"};
		List<TmpFuneralStatistics> tfs = new ArrayList<TmpFuneralStatistics>();
		for(String _name : nameList){
			for(String day : lunarDay){
				String lunardate = month + " " + day;
				CriteriaQuery cq = new CriteriaQuery(MorningpforrEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("registrant", _name),Restrictions.eq("lunardate", lunardate),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
				int count = morningpforrService.getListByCriteriaQuery(cq, false).size();
				System.out.println(lunardate + "   " + count);
				TmpFuneralStatistics tf = new TmpFuneralStatistics();
				tf.setCount(count);
				tf.setName(_name);
				tfs.add(tf);
			}
		}
		String res = getJSONArrayOfTmpFuneralStatisticsEntity(tfs).toString();
		resp.getWriter().write(res);
	}
	
	/**
	 * 早普佛堂数登记
	 * @param req
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params = "eveningPforrStatisticsResult")
	@ResponseBody
	public void eveningPforrStatisticsResult(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		AjaxJson j = new AjaxJson();
		
		String month = StringUtil.getEncodePra(req.getParameter("month"));
		String year = req.getParameter("year");
		String name = StringUtil.getEncodePra(req.getParameter("name"));
		
		List<String> nameList = new ArrayList<String>();
		
		if(name == "" || name == null){
			List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
			for(PravrajanamemberEntity pe : pravrajanamemberEntityList){
				nameList.add(pe.getDharmaname());
			}
		}
		else{
			nameList.add(name);
		}
		String[] lunarDay = {"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三",
			    "十四","十五","十六", "十七", "十八","十九","廿十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","卅十"};
		List<TmpFuneralStatistics> tfs = new ArrayList<TmpFuneralStatistics>();
		for(String _name : nameList){
			for(String day : lunarDay){
				String lunardate = month + " " + day;
				CriteriaQuery cq = new CriteriaQuery(EveningpforrEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("registrant", _name),Restrictions.eq("lunardate", lunardate),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
				int count = eveningpforrService.getListByCriteriaQuery(cq, false).size();
				System.out.println(lunardate + "   " + count);
				TmpFuneralStatistics tf = new TmpFuneralStatistics();
				tf.setCount(count);
				tf.setName(_name);
				tfs.add(tf);
			}
		}
		String res = getJSONArrayOfTmpFuneralStatisticsEntity(tfs).toString();
		resp.getWriter().write(res);
	}
	/**
	 * 早普佛堂数登记
	 * @param req
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params = "prayGuanyinStatisticsResult")
	@ResponseBody
	public void prayGuanyinStatisticsResult(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		AjaxJson j = new AjaxJson();
		
		String month = StringUtil.getEncodePra(req.getParameter("month"));
		String year = req.getParameter("year");
		String name = StringUtil.getEncodePra(req.getParameter("name"));
		
		List<String> nameList = new ArrayList<String>();
		
		if(name == "" || name == null){
			List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
			for(PravrajanamemberEntity pe : pravrajanamemberEntityList){
				nameList.add(pe.getDharmaname());
			}
		}
		else{
			nameList.add(name);
		}
		String[] lunarDay = {"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三",
			    "十四","十五","十六", "十七", "十八","十九","廿十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","卅十"};
		List<TmpFuneralStatistics> tfs = new ArrayList<TmpFuneralStatistics>();
		for(String _name : nameList){
			for(String day : lunarDay){
				String lunardate = month + " " + day;
				CriteriaQuery cq = new CriteriaQuery(PrayguanyinEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("registrant", _name),Restrictions.eq("lunardate", lunardate),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
				int count = prayguanyinService.getListByCriteriaQuery(cq, false).size();
				System.out.println(lunardate + "   " + count);
				TmpFuneralStatistics tf = new TmpFuneralStatistics();
				tf.setCount(count);
				tf.setName(_name);
				tfs.add(tf);
			}
		}
		String res = getJSONArrayOfTmpFuneralStatisticsEntity(tfs).toString();
		resp.getWriter().write(res);
	}
	private static JSONArray getJSONArrayOfTmpFuneralStatisticsEntity(List<TmpFuneralStatistics> tfs){
		JSONArray json = new JSONArray();
		for(TmpFuneralStatistics tf : tfs){
            JSONObject jo = new JSONObject();
            jo.put("name", tf.getName());
            jo.put("count", tf.getCount());
            json.add(jo);
        }
		return json;
		
	}
	
	
	@RequestMapping(params = "exportXls")
	public void exportXls(HttpServletRequest request,HttpServletResponse response) throws IOException {
		
		String month = StringUtil.getEncodePra(request.getParameter("month"));
		String year = request.getParameter("year");
		String name = StringUtil.getEncodePra(request.getParameter("name"));
		String type = request.getParameter("type");
		List<TmpFuneralStatistics> tfs = new ArrayList<TmpFuneralStatistics>();
		
		List<String> nameList = new ArrayList<String>();
		
		if(name == "" || name == null){
			List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
			for(PravrajanamemberEntity pe : pravrajanamemberEntityList){
				nameList.add(pe.getDharmaname());
			}
		}
		else{
			nameList.add(name);
		}
		String[] lunarDay = {"初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三",
			    "十四","十五","十六", "十七", "十八","十九","廿十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","卅十"};
		
		String codedFileName = "";
		if(type.equals("1")){
			codedFileName = year + month + "早普佛堂数登记表";
			
			for(String _name : nameList){
				for(String day : lunarDay){
					String lunardate = month + " " + day;
					CriteriaQuery cq = new CriteriaQuery(MorningpforrEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("registrant", _name),Restrictions.eq("lunardate", lunardate),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
					int count = morningpforrService.getListByCriteriaQuery(cq, false).size();
					System.out.println(lunardate + "   " + count);
					TmpFuneralStatistics tf = new TmpFuneralStatistics();
					tf.setCount(count);
					tf.setName(_name);
					tfs.add(tf);
				}
			}
		}
		if(type.equals("2")){
			codedFileName = year + month + "晚普佛堂数登记表";		
			for(String _name : nameList){
				for(String day : lunarDay){
					String lunardate = month + " " + day;
					CriteriaQuery cq = new CriteriaQuery(EveningpforrEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("registrant", _name),Restrictions.eq("lunardate", lunardate),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
					int count = eveningpforrService.getListByCriteriaQuery(cq, false).size();
					System.out.println(lunardate + "   " + count);
					TmpFuneralStatistics tf = new TmpFuneralStatistics();
					tf.setCount(count);
					tf.setName(_name);
					tfs.add(tf);
				}
			}
		}
		if(type.equals("3")){
			codedFileName = year + month + "上供堂数登记表";
			
			for(String _name : nameList){
				for(String day : lunarDay){
					String lunardate = month + " " + day;
					CriteriaQuery cq = new CriteriaQuery(PrayguanyinEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("registrant", _name),Restrictions.eq("lunardate", lunardate),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
					int count = prayguanyinService.getListByCriteriaQuery(cq, false).size();
					System.out.println(lunardate + "   " + count);
					TmpFuneralStatistics tf = new TmpFuneralStatistics();
					tf.setCount(count);
					tf.setName(_name);
					tfs.add(tf);
				}
			}
		}
		
		
		OutputStream fOut = null;
		if(name == "" || name == null){
			List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
			for(PravrajanamemberEntity pe : pravrajanamemberEntityList){
				nameList.add(pe.getDharmaname());
			}
		}
		else{
			nameList.add(name);
		}
		
		
		response.setContentType("application/vnd.ms-excel");
				response.setHeader("content-disposition",
						"attachment;filename=" + java.net.URLEncoder.encode(codedFileName,
								"UTF-8") + ".xls");
		HSSFWorkbook workbook = MyExportXls.exportXls(tfs);
		fOut = response.getOutputStream();
		try {
			workbook.write(fOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fOut.flush();
		fOut.close();

		
	}
	
	@RequestMapping(params = "totalStatisticsResult")
	@ResponseBody
	public void totalStatisticsResult(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		
		String month = StringUtil.getEncodePra(req.getParameter("month"));
		String year = req.getParameter("year");
		String name = StringUtil.getEncodePra(req.getParameter("name"));
		
		List<String> nameList = new ArrayList<String>();
		
		if(name == "" || name == null){
			List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
			for(PravrajanamemberEntity pe : pravrajanamemberEntityList){
				nameList.add(pe.getDharmaname());
			}
		}
		else{
			nameList.add(name);
		}
		
		List<String> holdDate = new ArrayList<String>();
		//获取总共的堂数
		int sum = 0 ;
				CriteriaQuery cqMorning = new CriteriaQuery(MorningpforrEntity.class);
				cqMorning.add(Restrictions.and(Restrictions.like("lunardate",month,MatchMode.ANYWHERE),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
				List<MorningpforrEntity> mfe = morningpforrService.getListByCriteriaQuery(cqMorning, false);
				int countMorning = mfe.size();
				
				for(int m = 0;m < countMorning;m ++){
					holdDate.add(mfe.get(m).getLunardate());
				}
				
				CriteriaQuery cqEvening = new CriteriaQuery(EveningpforrEntity.class);
				cqEvening.add(Restrictions.and(Restrictions.like("lunardate",month,MatchMode.ANYWHERE),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
				List<EveningpforrEntity> efe = eveningpforrService.getListByCriteriaQuery(cqEvening, false);
				int countEvening = efe.size();
				
				for(int m = 0;m < countEvening;m ++){
					holdDate.add(efe.get(m).getLunardate());
				}
				
				CriteriaQuery cqPray = new CriteriaQuery(PrayguanyinEntity.class);
				cqPray.add(Restrictions.and(Restrictions.like("lunardate",month,MatchMode.ANYWHERE),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
				List<PrayguanyinEntity> pge = prayguanyinService.getListByCriteriaQuery(cqPray, false);
				int countPray = pge.size();
				
				for(int m = 0;m < countPray;m ++){
					holdDate.add(pge.get(m).getLunardate());
				}
				
				sum += countMorning + countEvening + countPray;
		
		List<FuneralGatherCensus> fgcs = new ArrayList<FuneralGatherCensus>();
		String[] _enum = {"缺课","私假","病假","外出"};
		for(String _name : nameList){
			int absent = 0;
			FuneralGatherCensus fgc = new FuneralGatherCensus();
			fgc.setMemberName(_name);
			fgc.setAll(sum);
			CriteriaQuery cq = new CriteriaQuery(AttendanceEntity.class);
			cq.add(Restrictions.and(Restrictions.like("lunardate",month,MatchMode.ANYWHERE),Restrictions.eq("memberName", _name)));
			List<AttendanceEntity> attendanceEntityList = attendanceService.getListByCriteriaQuery(cq, false);
			for(String hd : holdDate){
				for(AttendanceEntity ae : attendanceEntityList){
					String montage = year + month + hd.split(" ")[1];
					if(montage.equals(ae.getLunardate())){
						if(ae.getReason().contains(_enum[0]) || ae.getReason().contains(_enum[1]) || ae.getReason().contains(_enum[2]) || ae.getReason().contains(_enum[3]) ){
							absent = absent + 1;
						}
					}
				}
			}
			fgc.setAbsent(absent);
			fgc.setJoin(sum-absent);
			fgcs.add(fgc);
			
		}
		String res = getJSONArrayOfFuneralGatherCensusEntity(fgcs).toString();
		resp.getWriter().write(res);
	}
	private static JSONArray getJSONArrayOfFuneralGatherCensusEntity(List<FuneralGatherCensus> fgcs){
		JSONArray json = new JSONArray();
		for(FuneralGatherCensus fgc : fgcs){
            JSONObject jo = new JSONObject();
            jo.put("name", fgc.getMemberName());
            jo.put("all", fgc.getAll());
            jo.put("absent", fgc.getAbsent());
            jo.put("join", fgc.getJoin());
            
            json.add(jo);
        }
		return json;
		
	}
	@RequestMapping(params = "exporTotalXls")
	public void exporTotalXls(HttpServletRequest req,HttpServletResponse response) throws IOException {

		String month = StringUtil.getEncodePra(req.getParameter("month"));
		String year = req.getParameter("year");
		String name = StringUtil.getEncodePra(req.getParameter("name"));
		
		List<String> nameList = new ArrayList<String>();
		
		if(name == "" || name == null){
			List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
			for(PravrajanamemberEntity pe : pravrajanamemberEntityList){
				nameList.add(pe.getDharmaname());
			}
		}
		else{
			nameList.add(name);
		}
		
		List<String> holdDate = new ArrayList<String>();
		//获取总共的堂数
		int sum = 0 ;
				CriteriaQuery cqMorning = new CriteriaQuery(MorningpforrEntity.class);
				cqMorning.add(Restrictions.and(Restrictions.like("lunardate",month,MatchMode.ANYWHERE),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
				List<MorningpforrEntity> mfe = morningpforrService.getListByCriteriaQuery(cqMorning, false);
				int countMorning = mfe.size();
				
				for(int m = 0;m < countMorning;m ++){
					holdDate.add(mfe.get(m).getLunardate());
				}
				
				CriteriaQuery cqEvening = new CriteriaQuery(EveningpforrEntity.class);
				cqEvening.add(Restrictions.and(Restrictions.like("lunardate",month,MatchMode.ANYWHERE),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
				List<EveningpforrEntity> efe = eveningpforrService.getListByCriteriaQuery(cqEvening, false);
				int countEvening = efe.size();
				
				for(int m = 0;m < countEvening;m ++){
					holdDate.add(efe.get(m).getLunardate());
				}
				
				CriteriaQuery cqPray = new CriteriaQuery(PrayguanyinEntity.class);
				cqPray.add(Restrictions.and(Restrictions.like("lunardate",month,MatchMode.ANYWHERE),Restrictions.like("registertime", year,MatchMode.ANYWHERE)));
				List<PrayguanyinEntity> pge = prayguanyinService.getListByCriteriaQuery(cqPray, false);
				int countPray = pge.size();
				
				for(int m = 0;m < countPray;m ++){
					holdDate.add(pge.get(m).getLunardate());
				}
				
				sum += countMorning + countEvening + countPray;
		
		List<FuneralGatherCensus> fgcs = new ArrayList<FuneralGatherCensus>();
		String[] _enum = {"缺课","私假","病假","外出"};
		for(String _name : nameList){
			int absent = 0;
			FuneralGatherCensus fgc = new FuneralGatherCensus();
			fgc.setMemberName(_name);
			fgc.setAll(sum);
			CriteriaQuery cq = new CriteriaQuery(AttendanceEntity.class);
			cq.add(Restrictions.and(Restrictions.like("lunardate",month,MatchMode.ANYWHERE),Restrictions.eq("memberName", _name)));
			List<AttendanceEntity> attendanceEntityList = attendanceService.getListByCriteriaQuery(cq, false);
			for(String hd : holdDate){
				for(AttendanceEntity ae : attendanceEntityList){
					String montage = year + month + hd.split(" ")[1];
					if(montage.equals(ae.getLunardate())){
						if(ae.getReason().contains(_enum[0]) || ae.getReason().contains(_enum[1]) || ae.getReason().contains(_enum[2]) || ae.getReason().contains(_enum[3]) ){
							absent = absent + 1;
						}
					}
				}
			}
			fgc.setAbsent(absent);
			fgc.setJoin(sum-absent);
			fgcs.add(fgc);
			
		}
		OutputStream fOut = null;
		String codedFileName = "法事堂数登记表";
		
		response.setContentType("application/vnd.ms-excel");
				response.setHeader("content-disposition",
						"attachment;filename=" + java.net.URLEncoder.encode(codedFileName,
								"UTF-8") + ".xls");
		HSSFWorkbook workbook = MyExportXls.exportTotalXls(fgcs);
		fOut = response.getOutputStream();
		try {
			workbook.write(fOut);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		fOut.flush();
		fOut.close();
		
		
	}

}
