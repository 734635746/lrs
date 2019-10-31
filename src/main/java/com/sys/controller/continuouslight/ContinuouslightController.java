package com.sys.controller.continuouslight;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
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

import com.sys.entity.continuouslight.ContinuouslightEntity;
import com.sys.entity.diamondsutra.DiamondsutraEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.service.continuouslight.ContinuouslightServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;

/**   
 * @Title: Controller
 * @Description: 长明灯管理
 * @author zhangdaihao
 * @date 2016-08-16 14:33:26
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/continuouslightController")
public class ContinuouslightController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ContinuouslightController.class);

	@Autowired
	private ContinuouslightServiceI continuouslightService;
	@Autowired
	private SystemService systemService;
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
	 * 长明灯管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "continuouslight")
	public ModelAndView continuouslight(HttpServletRequest request) {
		return new ModelAndView("com/sys/continuouslight/continuouslightList");
	}
	
	/**
	 * 长明灯管理列表 页面跳转录入
	 * 
	 * @return
	 */
	@RequestMapping(params = "toAddContinuouslight")
	public ModelAndView toAddContinuouslight(HttpServletRequest request) {
		return new ModelAndView("com/sys/continuouslight/continuouslight");
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
	public void datagrid(ContinuouslightEntity continuouslight,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ContinuouslightEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, continuouslight, request.getParameterMap());
		cq.addOrder("days", SortDirection.asc);
		this.continuouslightService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除长明灯管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ContinuouslightEntity continuouslight, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		continuouslight = systemService.getEntity(ContinuouslightEntity.class, continuouslight.getId());
		message = "长明灯管理删除成功";
		continuouslightService.delete(continuouslight);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加长明灯管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ContinuouslightEntity continuouslight, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(continuouslight.getId())) {
			message = "长明灯管理更新成功";
			ContinuouslightEntity t = continuouslightService.get(ContinuouslightEntity.class, continuouslight.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(continuouslight, t);
				continuouslightService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "长明灯管理更新失败";
			}
		} else {
			message = "长明灯管理添加成功";
			continuouslightService.save(continuouslight);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 长明灯管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ContinuouslightEntity continuouslight, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(continuouslight.getId())) {
			continuouslight = continuouslightService.getEntity(ContinuouslightEntity.class, continuouslight.getId());
			req.setAttribute("continuouslightPage", continuouslight);
		}
		return new ModelAndView("com/sys/continuouslight/continuouslight");
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
		
		ContinuouslightEntity continuouslightEntity = new ContinuouslightEntity();
		
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
		re.setRitualtype("长明灯");
		re.setPurpose("长明灯");
		re.setRemark("");
		re.setSize("其他");
		receiptService.save(re);
		
		//保存长明灯记录
		continuouslightEntity.setAddress(address);
		continuouslightEntity.setLivingmember(livingMemberString);
		continuouslightEntity.setMoney(Integer.valueOf(money));
		continuouslightEntity.setPayway(payway);
		continuouslightEntity.setSummary(summary);
		continuouslightEntity.setPrayingobj(prayingobj);
		//换时间
		continuouslightEntity.setFlag(0);
		continuouslightEntity.setStarttime(starttime);
		continuouslightEntity.setRegistertime(dateString);
		continuouslightEntity.setRegistrant(user.getRealName());
		continuouslightEntity.setDuration(duration);
		continuouslightEntity.setReceiptid(re.getId());
		continuouslightEntity.setReceiptno(re.getNo());
		continuouslightService.save(continuouslightEntity);
		
		try {
			calcEndTimeAndRestDay(continuouslightEntity,starttime,duration);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		model.addAttribute("message", "长明灯登记成功");
		model.addAttribute("ritualtype", "长明灯");
		ReceiptEntity returnRe = receiptService.get(ReceiptEntity.class, re.getId());
		model.addAttribute("returnRe", returnRe);
		String bigMoney=ChineseCurrency.toChineseCurrency(new Double(returnRe.getMoney()));
		model.addAttribute("bigMoney",bigMoney);
		String smallMoney=ChineseCurrency.toSmall(new Double(returnRe.getMoney()));
		model.addAttribute("smallMoney",smallMoney);
		return new ModelAndView("com/sys/success");
		
		
	}
	
	//计算时间与天数
	private void calcEndTimeAndRestDay(ContinuouslightEntity continuouslightEntity,String starttime,String duration) throws ParseException{
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
		continuouslightEntity.setEndtime(endtime);
		
		//获取倒数天数
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(endtime));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(continuouslightEntity.getStarttime()));    
        long time2 = cal.getTimeInMillis();         
        int between_days=(int) ((time1-time2)/(1000*3600*24));  
        
		
        continuouslightEntity.setDays(between_days);
        continuouslightService.updateEntitie(continuouslightEntity);
		
	}
}
