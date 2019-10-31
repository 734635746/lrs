package com.sys.controller.receipt;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
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
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.poi.excel.ExcelExportUtil;
import org.jeecgframework.poi.excel.entity.ExcelTitle;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.entity.amitabhabirth.AmitabhabirthEntity;
import com.sys.entity.buddhabirth.BuddhabirthEntity;
import com.sys.entity.buddhagaya.BuddhagayaEntity;
import com.sys.entity.ghostfes.GhostfesEntity;
import com.sys.entity.goddessbirth.GoddessbirthEntity;
import com.sys.entity.guanyingaya.GuanyingayaEntity;
import com.sys.entity.guanyinmonk.GuanyinmonkEntity;
import com.sys.entity.guanyinopen.GuanyinopenEntity;
import com.sys.entity.honoredbirth.HonoredbirthEntity;
import com.sys.entity.jizobirth.JIzobirthEntity;
import com.sys.entity.memorial_tablet.Memorial_tabletEntity;
import com.sys.entity.pharmacistbirth.PharmacistbirthEntity;
import com.sys.entity.purpose.PurposeEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.entity.tombsweepfes.TombsweepfesEntity;
import com.sys.service.amitabhabirth.AmitabhabirthServiceI;
import com.sys.service.buddhabirth.BuddhabirthServiceI;
import com.sys.service.buddhagaya.BuddhagayaServiceI;
import com.sys.service.ghostfes.GhostfesServiceI;
import com.sys.service.goddessbirth.GoddessbirthServiceI;
import com.sys.service.guanyingaya.GuanyingayaServiceI;
import com.sys.service.guanyinmonk.GuanyinmonkServiceI;
import com.sys.service.guanyinopen.GuanyinopenServiceI;
import com.sys.service.honoredbirth.HonoredbirthServiceI;
import com.sys.service.jizobirth.JIzobirthServiceI;
import com.sys.service.pharmacistbirth.PharmacistbirthServiceI;
import com.sys.service.purpose.PurposeServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.service.tombsweepfes.TombsweepfesServiceI;
import com.sys.util.ChineseCurrency;

/**   
 * @Title: Controller
 * @Description: 收据表
 * @author zhangdaihao
 * @date 2015-12-04 15:18:31
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/receiptController")
public class ReceiptController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReceiptController.class);

	@Autowired
	private ReceiptServiceI receiptService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ReceiptnoServiceI receiptnoService;
	private String message;
	
	@Autowired
	private AmitabhabirthServiceI amitabhabirthService;
	@Autowired
	private BuddhabirthServiceI buddhabirthService;
	@Autowired
	private BuddhagayaServiceI buddhagayaService;
	@Autowired
	private GhostfesServiceI ghostfesService;
	@Autowired
	private GoddessbirthServiceI goddessbirthService;
	@Autowired
	private GuanyingayaServiceI guanyingayaService;
	@Autowired
	private GuanyinmonkServiceI guanyinmonkService;
	@Autowired
	private GuanyinopenServiceI guanyinopenService;
	@Autowired
	private HonoredbirthServiceI honoredbirthService;
	@Autowired
	private JIzobirthServiceI jIzobirthService;
	@Autowired
	private TombsweepfesServiceI tombsweepfesService;
	@Autowired
	private PharmacistbirthServiceI pharmacistbirthService;
	@Autowired
	private PurposeServiceI purposeService;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 收据表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "receipt")
	public ModelAndView receipt(HttpServletRequest request) {
		return new ModelAndView("com/sys/receipt/receiptList");
	}
	
	/**
	 * 收据表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "cancelreceipt")
	public ModelAndView cancelreceipt(HttpServletRequest request) {
		return new ModelAndView("com/sys/receipt/cancelreceiptList");
	}
	
	@RequestMapping(params = "fundreceipt")
	public ModelAndView fundreceipt(HttpServletRequest request) {
		return new ModelAndView("com/sys/receipt/fundreceiptList");
	}
	
	@RequestMapping(params = "conreceipt")
	public ModelAndView conreceipt(HttpServletRequest request) {
		return new ModelAndView("com/sys/receipt/consecration/conreceiptList");
	}
	
	@RequestMapping(params = "free")
	public ModelAndView free(HttpServletRequest request) {
		return new ModelAndView("com/sys/receipt/free/freeList");
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
	public void datagrid(ReceiptEntity receipt,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ReceiptEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, receipt, request.getParameterMap());
		
		String beginRegistertime = request.getParameter("beginRegistertime");
		String endRegistertime = request.getParameter("endRegistertime");
		if(beginRegistertime!=null && endRegistertime!=null && !beginRegistertime.trim().isEmpty()&& !endRegistertime.trim().isEmpty()){
			cq.add(Restrictions.between("registertime",beginRegistertime , endRegistertime));
		}
		// 处理cancel的bug
		String cancel_str = request.getParameter("canel");
		if(cancel_str!=null && !cancel_str.trim().isEmpty()){
			int cancel = Integer.parseInt(cancel_str);
			cq.add(Restrictions.eq("cancel", cancel));
		}
		
		this.receiptService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 善款的easyui AJAX请求数据
	 * @param receipt
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "funddatagrid")
	public void funddatagrid(ReceiptEntity receipt,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ReceiptEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, receipt, request.getParameterMap());
		cq.add(Restrictions.eq("ritualtype", "善款"));
		this.receiptService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	
	/**
	 * 开光的easyui AJAX请求数据
	 * @param receipt
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "condatagrid")
	public void condatagrid(ReceiptEntity receipt,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ReceiptEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, receipt, request.getParameterMap());
		cq.add(Restrictions.eq("ritualtype", "开光"));
		this.receiptService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	
	/**
	 * 删除收据表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ReceiptEntity receipt, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		receipt = systemService.getEntity(ReceiptEntity.class, receipt.getId());
		message = "收据表删除成功";
		receiptService.delete(receipt);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}

	

	/**
	 * 添加收据表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ReceiptEntity receipt, HttpServletRequest request,Model model) {

		AjaxJson j = new AjaxJson();
		String cancel = request.getParameter("cancel");
		if(cancel != null && cancel.equals("1")){
			ReceiptEntity re = receiptService.get(ReceiptEntity.class, receipt.getId());
			TSUser user = ResourceUtil.getSessionUserName();
			re.setCancelhandler(user.getRealName());
			re.setCancel(1);
			
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(currentTime);
			re.setCanceldate(dateString);
			re.setCancelreason(receipt.getCancelreason());
			receiptService.updateEntitie(re);
			//判断是那种类型
			try {
				if(receipt.getRitualtype().equals("清明节")){
					CriteriaQuery cq = new CriteriaQuery(TombsweepfesEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<TombsweepfesEntity> tombsweepfesEntityList = tombsweepfesService.getListByCriteriaQuery(cq, false);
					for(TombsweepfesEntity te : tombsweepfesEntityList){
						te.setCancel(1);
						tombsweepfesService.updateEntitie(te);
					}
				}
				
				if(receipt.getRitualtype().equals("药师诞")){
					CriteriaQuery cq = new CriteriaQuery(PharmacistbirthEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<PharmacistbirthEntity> pharmacistbirthEntityList = pharmacistbirthService.getListByCriteriaQuery(cq, false);
					for(PharmacistbirthEntity te : pharmacistbirthEntityList){
						te.setCancel(1);
						pharmacistbirthService.updateEntitie(te);
					}
				}
				
				if(receipt.getRitualtype().equals("弥勒佛诞")){
					CriteriaQuery cq = new CriteriaQuery(BuddhabirthEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<BuddhabirthEntity> buddhabirthEntityList = buddhabirthService.getListByCriteriaQuery(cq, false);
					for(BuddhabirthEntity te : buddhabirthEntityList){
						te.setCancel(1);
						buddhabirthService.updateEntitie(te);
					}
				}
				
				if(receipt.getRitualtype().equals("观音开库")){
					CriteriaQuery cq = new CriteriaQuery(GuanyinopenEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<GuanyinopenEntity> guanyinopenEntityList = guanyinopenService.getListByCriteriaQuery(cq, false);
					for(GuanyinopenEntity te : guanyinopenEntityList){
						te.setCancel(1);
						guanyinopenService.updateEntitie(te);
					}
				}
				if(receipt.getRitualtype().equals("观音诞")){
					CriteriaQuery cq = new CriteriaQuery(GoddessbirthEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<GoddessbirthEntity> goddessbirthEntityList = goddessbirthService.getListByCriteriaQuery(cq, false);
					for(GoddessbirthEntity te : goddessbirthEntityList){
						te.setCancel(1);
						guanyinopenService.updateEntitie(te);
					}
				}
				
				if(receipt.getRitualtype().equals("地藏诞")){
					CriteriaQuery cq = new CriteriaQuery(JIzobirthEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<JIzobirthEntity> jIzobirthEntityList = jIzobirthService.getListByCriteriaQuery(cq, false);
					for(JIzobirthEntity te : jIzobirthEntityList){
						te.setCancel(1);
						jIzobirthService.updateEntitie(te);
					}
				}
				
				if(receipt.getRitualtype().equals("弥陀诞")){
					CriteriaQuery cq = new CriteriaQuery(AmitabhabirthEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<AmitabhabirthEntity> amitabhabirthEntityList = amitabhabirthService.getListByCriteriaQuery(cq, false);
					for(AmitabhabirthEntity te : amitabhabirthEntityList){
						te.setCancel(1);
						amitabhabirthService.updateEntitie(te);
					}
				}
				
				if(receipt.getRitualtype().equals("释尊诞")){
					CriteriaQuery cq = new CriteriaQuery(HonoredbirthEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<HonoredbirthEntity> honoredbirthEntityList = honoredbirthService.getListByCriteriaQuery(cq, false);
					for(HonoredbirthEntity te : honoredbirthEntityList){
						te.setCancel(1);
						honoredbirthService.updateEntitie(te);
					}
				}
				
				if(receipt.getRitualtype().equals("盂兰节")){
					CriteriaQuery cq = new CriteriaQuery(GhostfesEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<GhostfesEntity> ghostfesEntityList = ghostfesService.getListByCriteriaQuery(cq, false);
					for(GhostfesEntity te : ghostfesEntityList){
						te.setCancel(1);
						ghostfesService.updateEntitie(te);
					}
				}
				
				if(receipt.getRitualtype().equals("观音出家")){
					CriteriaQuery cq = new CriteriaQuery(GuanyinmonkEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<GuanyinmonkEntity> guanyinmonkEntityList = guanyinmonkService.getListByCriteriaQuery(cq, false);
					for(GuanyinmonkEntity te : guanyinmonkEntityList){
						te.setCancel(1);
						guanyinmonkService.updateEntitie(te);
					}
				}
				
				if(receipt.getRitualtype().equals("观音成道")){
					CriteriaQuery cq = new CriteriaQuery(GuanyingayaEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<GuanyingayaEntity> guanyingayaEntityList = guanyingayaService.getListByCriteriaQuery(cq, false);
					for(GuanyingayaEntity te : guanyingayaEntityList){
						te.setCancel(1);
						guanyingayaService.updateEntitie(te);
					}
				}
				
				if(receipt.getRitualtype().equals("释尊成道")){
					CriteriaQuery cq = new CriteriaQuery(BuddhagayaEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("receiptid",receipt.getId())));
					List<BuddhagayaEntity> buddhagayaEntityList = buddhagayaService.getListByCriteriaQuery(cq, false);
					for(BuddhagayaEntity te : buddhagayaEntityList){
						te.setCancel(1);
						buddhagayaService.updateEntitie(te);
					}
				}
				message = "收据表撤销成功";
			}catch (Exception e) {
				e.printStackTrace();
				message = "收据表撤销失败";
			}
			j.setMsg(message);
			return j;
			
		}
		else{
			if (StringUtil.isNotEmpty(receipt.getId())) {
				message = "收据表更新成功";
				ReceiptEntity t = receiptService.get(ReceiptEntity.class, receipt.getId());
				try {
					MyBeanUtils.copyBeanNotNull2Bean(receipt, t);
					receiptService.saveOrUpdate(t);
					systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
				} catch (Exception e) {
					e.printStackTrace();
					message = "收据表更新失败";
				}
			} else {
				message = "收据表添加成功";
				TSUser user = ResourceUtil.getSessionUserName();
				receipt.setRegistrant(user.getUserName());
				String no = String.valueOf((int)(Math.random()*100000));
				receipt.setNo("No." + no);
				//换时间
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				receipt.setRegistertime(dateString);
				receiptService.save(receipt);
				systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			}
			j.setMsg(message);
			return j;
		}
		
	}

	/**
	 * 收据表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ReceiptEntity receipt, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(receipt.getId())) {
			receipt = receiptService.getEntity(ReceiptEntity.class, receipt.getId());
			req.setAttribute("receiptPage", receipt);
		}
		return new ModelAndView("com/sys/receipt/receipt");
	}
	
	/**
	 * 收据表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "cancel")
	public ModelAndView cancel(ReceiptEntity receipt, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(receipt.getId())) {
			receipt = receiptService.getEntity(ReceiptEntity.class, receipt.getId());
			req.setAttribute("receiptPage", receipt);
		}
		return new ModelAndView("com/sys/receipt/cancelreceipt");
	}
	/**
	 * 善款列表页面跳转
	 * @param receipt
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "fundaddorupdate")
	public ModelAndView fundaddorupdate(ReceiptEntity receipt, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(receipt.getId())) {
			receipt = receiptService.getEntity(ReceiptEntity.class, receipt.getId());
			req.setAttribute("receiptPage", receipt);
		}
		
		return new ModelAndView("com/sys/receipt/fundreceipt");
	}
	
	
	
	/**
	 * 开光列表页面跳转
	 * @param receipt
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "conToward")
	public ModelAndView conToward(HttpServletRequest req) {
		return new ModelAndView("com/sys/receipt/consecration/conreceipt");
	}
	
	@RequestMapping(params = "freeToward")
	public ModelAndView freeToward(HttpServletRequest req) {
		return new ModelAndView("com/sys/receipt/free/free");
	}
	
	/**
	 * 善款列表页面跳转
	 * @param receipt
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "fundToward")
	public ModelAndView fundToward(HttpServletRequest req) {
		// 获取善款用途
		List<PurposeEntity> purposeList = purposeService
				.getList(PurposeEntity.class);
		req.setAttribute("purposeList", purposeList);
		return new ModelAndView("com/sys/receipt/fundreceipt");
	}
	
	/**
	 * 开光列表页面跳转
	 * @param receipt
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "saveConReceipt")
	public ModelAndView saveConReceipt(HttpServletRequest req,Model model) {
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
		receipt.setSize("其他");
		
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
		
		receipt.setRegistertime(dateString);
		receipt.setRitualtype("开光");
		receipt.setPurpose("开光");
		receipt.setRemark("");
		receiptService.save(receipt);
		
		model.addAttribute("message", "开光记录登记成功");
		model.addAttribute("ritualtype", "开光");
		model.addAttribute("returnRe", receipt);
		String bigMoney=ChineseCurrency.toChineseCurrency(new Double(receipt.getMoney()));
		model.addAttribute("bigMoney",bigMoney);
		String smallMoney=ChineseCurrency.toSmall(new Double(receipt.getMoney()));
		model.addAttribute("smallMoney",smallMoney);
		return new ModelAndView("com/sys/success");
	}
	
	/**
	 * 善款收据保存
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "saveFundReceipt")
	public ModelAndView saveFundReceipt(HttpServletRequest req,Model model) {
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
		receipt.setRitualtype("善款");
		receipt.setPurpose(req.getParameter("purpose"));
		receipt.setRemark("");
		receiptService.save(receipt);
		
		model.addAttribute("message", "善款记录登记成功");
		model.addAttribute("ritualtype", "善款");
		model.addAttribute("returnRe", receipt);
		String bigMoney=ChineseCurrency.toChineseCurrency(new Double(receipt.getMoney()));
		model.addAttribute("bigMoney",bigMoney);
		String smallMoney=ChineseCurrency.toSmall(new Double(receipt.getMoney()));
		model.addAttribute("smallMoney",smallMoney);
		return new ModelAndView("com/sys/success");
	}
	
	@RequestMapping(params = "forward")
	public ModelAndView forward(HttpServletRequest req) {

		return new ModelAndView("com/sys/printSuccess");
	}
	
	
	/**
	 * 导出excel
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(params = "exportXls")
	public void exportXls(ReceiptEntity receipt,HttpServletRequest request,HttpServletResponse response
			, DataGrid dataGrid) {
	
		// 解决乱码问题
		//canceldate obj paymen payway purpose registrant ritual 登记时间区间
		//
		String canceldate = receipt.getCanceldate();
		if(canceldate!=null && !canceldate.trim().isEmpty()){
			try {
				canceldate = new String(canceldate.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receipt.setCanceldate(canceldate);
		}
		
		String obj = receipt.getObj();
		if(obj!=null && !obj.trim().isEmpty()){
			try {
				obj = new String(obj.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receipt.setObj(obj);
		}
		
		String paymen = receipt.getPaymen();
		if(paymen!=null && !paymen.trim().isEmpty()){
			try {
				paymen = new String(paymen.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receipt.setPaymen(paymen);
		}
		
		String payway = receipt.getPayway();
		if(payway!=null && !payway.trim().isEmpty()){
			try {
				payway = new String(payway.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receipt.setPayway(payway);
		}
		
		String purpose = receipt.getPurpose();
		if(purpose!=null && !purpose.trim().isEmpty()){
			try {
				purpose = new String(purpose.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receipt.setPurpose(purpose);
		}
		
		String registrant = receipt.getRegistrant();
		if(registrant!=null && !registrant.trim().isEmpty()){
			try {
				registrant = new String(registrant.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receipt.setRegistrant(registrant);
		}
		
		String ritualtype = receipt.getRitualtype();
		if(ritualtype!=null && !ritualtype.trim().isEmpty()){
			try {
				ritualtype = new String(ritualtype.getBytes("ISO-8859-1"),"UTF-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			receipt.setRitualtype(ritualtype);
		}
		
		// 
		
		
		response.setContentType("application/vnd.ms-excel");
		String codedFileName = null;
		OutputStream fOut = null;
		try {
			codedFileName = "收据信息";
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
			CriteriaQuery cq = new CriteriaQuery(ReceiptEntity.class, dataGrid);
			
			// 扩展区间查询
			String beginRegistertime = request.getParameter("beginRegistertime");
			String endRegistertime = request.getParameter("endRegistertime");
			if(beginRegistertime!=null && endRegistertime!=null && !beginRegistertime.trim().isEmpty()&& !endRegistertime.trim().isEmpty()){
				beginRegistertime = new String(beginRegistertime.getBytes("ISO-8859-1"),"UTF-8");
				endRegistertime = new String(endRegistertime.getBytes("ISO-8859-1"),"UTF-8");
				cq.add(Restrictions.between("registertime",beginRegistertime , endRegistertime));
			}
			
			Map<String, String[]> map = request.getParameterMap();
			
			org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, receipt, request.getParameterMap());
			
			List<ReceiptEntity> receiptEntitys = this.receiptService.getListByCriteriaQuery(cq,false);
			workbook = ExcelExportUtil.exportExcel(new ExcelTitle("收据列表", "导出人:"+request.getSession().getAttribute("userName"),
					"导出信息"), ReceiptEntity.class, receiptEntitys);
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
