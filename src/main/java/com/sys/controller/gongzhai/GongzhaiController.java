package com.sys.controller.gongzhai;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sys.entity.diamondsutra.DiamondsutraEntity;
import com.sys.entity.gongzhai.GongzhaiEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.service.gongzhai.GongzhaiServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;

/**   
 * @Title: Controller
 * @Description: 供斋管理
 * @author zhangdaihao
 * @date 2016-08-17 10:26:52
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/gongzhaiController")
public class GongzhaiController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GongzhaiController.class);

	@Autowired
	private GongzhaiServiceI gongzhaiService;
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
	 * 供斋管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "gongzhai")
	public ModelAndView gongzhai(HttpServletRequest request) {
		return new ModelAndView("com/sys/gongzhai/gongzhaiList");
	}
	
	/**
	 * 供斋管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "toAddGongzhai")
	public ModelAndView toAddGongzhai(HttpServletRequest request) {
		return new ModelAndView("com/sys/gongzhai/gongzhai");
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
	public void datagrid(GongzhaiEntity gongzhai,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(GongzhaiEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, gongzhai, request.getParameterMap());
		this.gongzhaiService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除供斋管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(GongzhaiEntity gongzhai, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		gongzhai = systemService.getEntity(GongzhaiEntity.class, gongzhai.getId());
		message = "供斋管理删除成功";
		gongzhaiService.delete(gongzhai);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加供斋管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(GongzhaiEntity gongzhai, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(gongzhai.getId())) {
			message = "供斋管理更新成功";
			GongzhaiEntity t = gongzhaiService.get(GongzhaiEntity.class, gongzhai.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(gongzhai, t);
				gongzhaiService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "供斋管理更新失败";
			}
		} else {
			message = "供斋管理添加成功";
			gongzhaiService.save(gongzhai);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 供斋管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(GongzhaiEntity gongzhai, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(gongzhai.getId())) {
			gongzhai = gongzhaiService.getEntity(GongzhaiEntity.class, gongzhai.getId());
			req.setAttribute("gongzhaiPage", gongzhai);
		}
		return new ModelAndView("com/sys/gongzhai/gongzhai");
	}
	
	@RequestMapping(params = "saveRecordAndSaveReceipt")
	public ModelAndView saveRecordAndSaveReceipt(HttpServletRequest req,Model model){
		
		TSUser user = ResourceUtil.getSessionUserName();
		
		String prayingobj = req.getParameter("prayingobj");
		
		String money = req.getParameter("money");
		
		String payway = req.getParameter("payway");
		
		String []livingmember = req.getParameterValues("livingmember");
		
		String address = req.getParameter("address");
		
		String summary = req.getParameter("summary");
		
		String month = req.getParameter("month");
		
		String day = req.getParameter("day");
		
		String livingMemberString = "";
		for(int i = 0; i < livingmember.length;i ++){
			livingMemberString  = livingMemberString + livingmember[i] + "#";
		}
		
		GongzhaiEntity gongzhaiEntity = new GongzhaiEntity();
		
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
		re.setRitualtype("供斋");
		re.setPurpose("供斋");
		re.setRemark("");
		re.setSize("其他");
		receiptService.save(re);
		
		//保存金刚经记录
		gongzhaiEntity.setAddress(address);
		gongzhaiEntity.setLivingmember(livingMemberString);
		gongzhaiEntity.setMoney(Integer.valueOf(money));
		gongzhaiEntity.setPayway(payway);
		gongzhaiEntity.setSummary(summary);
		gongzhaiEntity.setPrayingobj(prayingobj);
		//换时间
		gongzhaiEntity.setFlag(0);
		gongzhaiEntity.setRegistertime(dateString);
		gongzhaiEntity.setRegistrant(user.getRealName());
		gongzhaiEntity.setReceiptid(re.getId());
		gongzhaiEntity.setReceiptno(re.getNo());
		gongzhaiEntity.setHolddate(month + day); 
		gongzhaiService.save(gongzhaiEntity);
		
		
		model.addAttribute("message", "供斋登记成功");
		model.addAttribute("ritualtype", "供斋");
		ReceiptEntity returnRe = receiptService.get(ReceiptEntity.class, re.getId());
		model.addAttribute("returnRe", returnRe);
		String bigMoney=ChineseCurrency.toChineseCurrency(new Double(returnRe.getMoney()));
		model.addAttribute("bigMoney",bigMoney);
		String smallMoney=ChineseCurrency.toSmall(new Double(returnRe.getMoney()));
		model.addAttribute("smallMoney",smallMoney);
		return new ModelAndView("com/sys/success");
		
		
	}
}
