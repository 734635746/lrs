package com.sys.controller.diamondsutra;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sys.entity.amitabhabirth.AmitabhabirthEntity;
import com.sys.entity.diamondsutra.DiamondsutraEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.service.diamondsutra.DiamondsutraServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;

/**   
 * @Title: Controller
 * @Description: 金刚经管理
 * @author zhangdaihao
 * @date 2016-08-14 15:20:15
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/diamondsutraController")
public class DiamondsutraController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DiamondsutraController.class);

	@Autowired
	private DiamondsutraServiceI diamondsutraService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ReceiptnoServiceI receiptnoService;
	@Autowired
	private ReceiptServiceI receiptService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 金刚经管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "diamondsutra")
	public ModelAndView diamondsutra(HttpServletRequest request) {
		return new ModelAndView("com/sys/diamondsutra/diamondsutraList");
	}
	
	/**
	 * 金刚经管理列表 页面跳转录入
	 * 
	 * @return
	 */
	@RequestMapping(params = "toAddDiamondsutra")
	public ModelAndView toAddDiamondsutra(HttpServletRequest request) {
		return new ModelAndView("com/sys/diamondsutra/diamondsutra");
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
	public void datagrid(DiamondsutraEntity diamondsutra,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DiamondsutraEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, diamondsutra, request.getParameterMap());
		cq.addOrder("days", SortDirection.asc);
		this.diamondsutraService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除金刚经管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DiamondsutraEntity diamondsutra, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		diamondsutra = systemService.getEntity(DiamondsutraEntity.class, diamondsutra.getId());
		message = "金刚经管理删除成功";
		diamondsutraService.delete(diamondsutra);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加金刚经管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DiamondsutraEntity diamondsutra, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(diamondsutra.getId())) {
			message = "金刚经管理更新成功";
			DiamondsutraEntity t = diamondsutraService.get(DiamondsutraEntity.class, diamondsutra.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(diamondsutra, t);
				diamondsutraService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "金刚经管理更新失败";
			}
		} else {
			message = "金刚经管理添加成功";
			diamondsutraService.save(diamondsutra);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 金刚经管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DiamondsutraEntity diamondsutra, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(diamondsutra.getId())) {
			diamondsutra = diamondsutraService.getEntity(DiamondsutraEntity.class, diamondsutra.getId());
			req.setAttribute("diamondsutraPage", diamondsutra);
		}
		return new ModelAndView("com/sys/diamondsutra/diamondsutra");
	}
	
	
	@RequestMapping(params = "saveRecordAndSaveReceipt")
	public ModelAndView saveRecordAndSaveReceipt(HttpServletRequest req,Model model){
		
		TSUser user = ResourceUtil.getSessionUserName();
		
		String prayingobj = req.getParameter("prayingobj");
		
		String money = req.getParameter("money");
		
		String payway = req.getParameter("payway");
		
		String []livingmember = req.getParameterValues("livingmember");
		
		String duration = req.getParameter("duration");
		
		String address = req.getParameter("address");
		
		String summary = req.getParameter("summary");
		
		String starttime = req.getParameter("starttime");
		
		String livingMemberString = "";
		for(int i = 0; i < livingmember.length;i ++){
			livingMemberString  = livingMemberString + livingmember[i] + "#";
		}
		
		DiamondsutraEntity diamondsutraEntity = new DiamondsutraEntity();
		
		//保存收据
		ReceiptEntity re = new ReceiptEntity();
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String dateString = formatter.format(currentTime);
		
		Calendar cal = Calendar.getInstance();
		int year = cal.get(Calendar.YEAR);
		String currentyear = String.valueOf(year);
		
		Calendar a=Calendar.getInstance();
		ReceiptnoEntity reNo = receiptnoService.findByProperty(ReceiptnoEntity.class, "year",Integer.valueOf(a.get(Calendar.YEAR))).get(0);
		int currentMinCount = reNo.getMincount();
		reNo.setMincount(currentMinCount + 1);
		receiptnoService.updateEntitie(reNo);
		
		NumberFormat f=new DecimalFormat("0000000");
		
		String no = f.format(currentMinCount + 1);
		re.setNo("No." + no);
		
		re.setPaymen(prayingobj);
		re.setPayway(payway);
		re.setRegistrant(user.getRealName());
		re.setRegistertime(dateString);
		re.setAddress(address);
		re.setMoney(Integer.valueOf(money));
		re.setSummary(summary);
		re.setObj(livingMemberString);
		re.setRitualtype("金刚经");
		re.setPurpose("金刚经");
		re.setRemark("");
		re.setSize("其他");
		receiptService.save(re);
		
		//保存金刚经记录
		diamondsutraEntity.setAddress(address);
		diamondsutraEntity.setLivingmember(livingMemberString);
		diamondsutraEntity.setMoney(Integer.valueOf(money));
		diamondsutraEntity.setPayway(payway);
		diamondsutraEntity.setSummary(summary);
		diamondsutraEntity.setPrayingobj(prayingobj);
		//换时间
		diamondsutraEntity.setFlag(0);
		diamondsutraEntity.setStarttime(starttime);
		diamondsutraEntity.setRegistertime(dateString);
		diamondsutraEntity.setRegistrant(user.getRealName());
		diamondsutraEntity.setDuration(duration);
		diamondsutraEntity.setReceiptid(re.getId());
		diamondsutraEntity.setReceiptno(re.getNo());
		diamondsutraService.save(diamondsutraEntity);
		
		try {
			calcEndTimeAndRestDay(diamondsutraEntity,starttime,duration);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("message", "金刚经登记成功");
		model.addAttribute("ritualtype", "金刚经");
		ReceiptEntity returnRe = receiptService.get(ReceiptEntity.class, re.getId());
		model.addAttribute("returnRe", returnRe);
		String bigMoney=ChineseCurrency.toChineseCurrency(new Double(returnRe.getMoney()));
		model.addAttribute("bigMoney",bigMoney);
		String smallMoney=ChineseCurrency.toSmall(new Double(returnRe.getMoney()));
		model.addAttribute("smallMoney",smallMoney);
		return new ModelAndView("com/sys/success");
		
		
	}
	
	//计算时间与天数
	private void calcEndTimeAndRestDay(DiamondsutraEntity diamondsutraEntity,String starttime,String duration) throws ParseException{
		SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
		Date now = sdf1.parse(starttime);
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(now);
		String endtime = "";
		if(duration.equals("一个星期")){
			calendar.add(Calendar.DATE, 7);
			endtime = sdf1.format(calendar.getTime());
		}
		if(duration.equals("一个月")){
			calendar.add(Calendar.MONTH, 1);
			endtime = sdf1.format(calendar.getTime());
		}
		if(duration.equals("三个月")){
			calendar.add(Calendar.MONTH, 3);
			endtime = sdf1.format(calendar.getTime());
		}
		if(duration.equals("六个月")){
			calendar.add(Calendar.MONTH, 6);
			endtime = sdf1.format(calendar.getTime());
		}
		if(duration.equals("一年")){
			calendar.add(Calendar.YEAR, 1);
			endtime = sdf1.format(calendar.getTime());
		}
		diamondsutraEntity.setEndtime(endtime);
		
		//获取倒数天数
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(endtime));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(diamondsutraEntity.getStarttime()));    
        long time2 = cal.getTimeInMillis();         
        int between_days=(int) ((time1-time2)/(1000*3600*24));  
        
		
		diamondsutraEntity.setDays(between_days);
		diamondsutraService.updateEntitie(diamondsutraEntity);
		
	}
	
	public Boolean updateRestDays() throws ParseException{
		System.out.println("updateRestDays");
		List<DiamondsutraEntity> diamondsutraList = diamondsutraService.getList(DiamondsutraEntity.class);
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		
		
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(dateString));    
        long current_timestamp = cal.getTimeInMillis();                 
                
		for(DiamondsutraEntity dse : diamondsutraList){
			String starttime = dse.getStarttime();
			
			cal.setTime(sdf.parse(starttime));    
	        long start_timestamp = cal.getTimeInMillis();         
	        int between_days=(int) ((current_timestamp-start_timestamp)/(1000*3600*24));  
	        
	        if(between_days > 0){
	        	dse.setDays(dse.getDays() - between_days);
	        	diamondsutraService.updateEntitie(dse);
	        }

		}
		System.out.println("更新日期完成。。。。。。");
		return true;
		
	}
	
}
