package com.sys.controller.buddhagaya;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.xmlbeans.impl.xb.xsdschema.RestrictionDocument.Restriction;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sys.entity.buddhabirth.BuddhabirthEntity;
import com.sys.entity.buddhagaya.BuddhagayaEntity;
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.guanyinmonk.GuanyinmonkEntity;
import com.sys.entity.guanyinopen.GuanyinopenEntity;
import com.sys.entity.livingmenber.LivingmenberEntity;
import com.sys.entity.pray.PrayEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.service.buddhagaya.BuddhagayaServiceI;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;
import com.sys.util.ControllerUtils;

/**   
 * @Title: Controller
 * @Description: 释尊成道信息
 * @author zhangdaihao
 * @date 2016-03-03 09:34:37
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/buddhagayaController")
public class BuddhagayaController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(BuddhagayaController.class);

	@Autowired
	private BuddhagayaServiceI buddhagayaService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private DoritualinfoServiceI doritualinfoService;
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
	 * 释尊成道信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "buddhagaya")
	public ModelAndView buddhagaya(HttpServletRequest request) {
		return new ModelAndView("com/sys/buddhagaya/buddhagayaList");
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
	public void datagrid(BuddhagayaEntity buddhagaya,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BuddhagayaEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, buddhagaya, request.getParameterMap());
		this.buddhagayaService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除释尊成道信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(BuddhagayaEntity buddhagaya, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		buddhagaya = systemService.getEntity(BuddhagayaEntity.class, buddhagaya.getId());
		message = "释尊成道信息删除成功";
		buddhagayaService.delete(buddhagaya);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加释尊成道信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(BuddhagayaEntity buddhagaya, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(buddhagaya.getId())) {
			message = "释尊成道信息更新成功";
			BuddhagayaEntity t = buddhagayaService.get(BuddhagayaEntity.class, buddhagaya.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(buddhagaya, t);
				buddhagayaService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "释尊成道信息更新失败";
			}
		} else {
			message = "释尊成道信息添加成功";
			buddhagayaService.save(buddhagaya);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 释尊成道信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(BuddhagayaEntity buddhagaya, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(buddhagaya.getId())) {
			buddhagaya = buddhagayaService.getEntity(BuddhagayaEntity.class, buddhagaya.getId());
			req.setAttribute("buddhagayaPage", buddhagaya);
		}
		return new ModelAndView("com/sys/buddhagaya/buddhagaya");
	}
	
	/**选择牌位大小
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToSelectSize")  //10月30号修改
	public ModelAndView redirectToSelectSize(DoritualinfoEntity doritualinfo, HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");

//		--kooking 20180330	
			try {
				doritualinfo.setRname(new String(doritualinfo.getRname()
						.getBytes("iso8859-1"), "utf-8"));
				doritualinfo.setAddress(new String(doritualinfo.getAddress()
						.getBytes("iso8859-1"), "utf-8"));
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
				logger.info(e.getMessage());
				return new ModelAndView("com/sys/error");
			}
			// 保存做法事人的基本信息
			if (id0 == null || id0.equals("")) {
				// id为空说明信息是新增的，保存
				Date nowTime = new Date();
				SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String registertime = time.format(nowTime);
				doritualinfo.setRegistertime(registertime);
				doritualinfoService.save(doritualinfo);

			} else if (StringUtil.isNotEmpty(doritualinfo.getId())) {// 更新

				Date nowTime = new Date();
				SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String registertime = time.format(nowTime);
				doritualinfo.setRegistertime(registertime);
				doritualinfoService.updateEntitie(doritualinfo);
			}

			id0 = doritualinfo.getId();
//--kooking 20180330

//			---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记
			//获取今年的释尊成道法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "释尊成道");
			if(funeralhelds.size()!=1){
				model.addAttribute("message", "抱歉，今年的释尊成道法事举行日期安排 登记不正常，请与系统管理员联系！");
				return new ModelAndView("com/sys/nofuneralheldplan");
			}
			
//			---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记			
			
		model.addAttribute("id0", id0);
		return new ModelAndView("com/sys/buddhagaya/selectSize");
	}
	
	@RequestMapping(params = "redirectToShowBuddhagayaRecord")
	public ModelAndView redirectToShowBuddhagayaRecord(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");
//		String hql0 = "from BuddhagayaEntity where 1 = 1 AND doritualid = ? AND size = ?";
		String sql = "select * from pray where 1 = 1 AND doritualid = ? AND size = ?";//20180504
		if(id0 != null){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
//			List<BuddhagayaEntity> buddhagayaEntitys = buddhagayaService.findHql(hql0,id0,size);
			List<BuddhagayaEntity> buddhagayaEntitys = buddhagayaService
					.listBySQL(BuddhagayaEntity.class, sql, id0,size);
			model.addAttribute("doritualinfoEntity", doritualinfoEntity);
			model.addAttribute("buddhagayaEntitys", buddhagayaEntitys);
			model.addAttribute("id", id0);
			model.addAttribute("size", size);
		}
		
		return new ModelAndView("com/sys/buddhagaya/showBuddhagayaRecord");
	}
	
	/**
	 * 跳转到编辑登记药师诞
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToEditBuddhagaya")
	public ModelAndView redirectToEditBuddhagaya(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");
		if(size != null){
			try {
				size = URLDecoder.decode(size , "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
		String buddhagayaIds = req.getParameter("buddhagayaIds");
		
		String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ? ";
		List<LivingmenberEntity> livingmenberEntityList = systemService.findHql(hql0,id0);
		
//		List<BuddhagayaEntity> buddhagayaEntitys = new ArrayList<BuddhagayaEntity>();
		List<PrayEntity> buddhagayaEntitys = new ArrayList<PrayEntity>();

		//获取今年的释尊成道法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "释尊成道");
		model.addAttribute("funeralheld", funeralhelds);
		
		//添加做法事人的地址
		model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
		model.addAttribute("updateFlag", "0");
		
		if(size.equals("小")){
			if(buddhagayaIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("buddhagayaEntitys", buddhagayaEntitys);
				return new ModelAndView("com/sys/buddhagaya/editBuddhagaya");
			}
			
			
			
			//用逗号切开
			String buddhagayaIdsSplitByComma[] = buddhagayaIds.substring(0, buddhagayaIds.length()-1).split(",");
			
			//分别获取相关数据
			for(int i = 0;i < buddhagayaIdsSplitByComma.length;i ++){
//				BuddhagayaEntity buddhagayaEntity = buddhagayaService.get(BuddhagayaEntity.class, buddhagayaIdsSplitByComma[i]);
				PrayEntity buddhagayaEntity = buddhagayaService.get(PrayEntity.class, buddhagayaIdsSplitByComma[i]);
				buddhagayaEntitys.add(buddhagayaEntity);
			}
			
			
	
	
			model.addAttribute("buddhagayaEntitys", buddhagayaEntitys);
			model.addAttribute("livingmenberEntityList", livingmenberEntityList);
			model.addAttribute("id", id0);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			model.addAttribute("book", buddhagayaEntitys.get(0).getBook());
			
			return new ModelAndView("com/sys/buddhagaya/editBuddhagaya");
		}
		else{
			if(buddhagayaIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("buddhagayaEntitys", buddhagayaEntitys);
				model.addAttribute("size", size);
				return new ModelAndView("com/sys/buddhagaya/editMiddleAndBigBuddhagaya");
			}
			else{
				//已经有数据
//				BuddhagayaEntity buddhagayaEntity = buddhagayaService.get(BuddhagayaEntity.class, buddhagayaIds.substring(0, buddhagayaIds.length() - 1));
				PrayEntity buddhagayaEntity = buddhagayaService.get(PrayEntity.class, buddhagayaIds.substring(0, buddhagayaIds.length() - 1));
				
				String livingString = buddhagayaEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				buddhagayaEntitys.add(buddhagayaEntity);
				model.addAttribute("buddhagayaEntity", buddhagayaEntity);
				model.addAttribute("buddhagayaEntitys", buddhagayaEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", id0);
				model.addAttribute("size", size);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				return new ModelAndView("com/sys/buddhagaya/editMiddleAndBigBuddhagaya");
			}
		}
	}
	
	/**
	 * 保存编辑后的药师诞记录和收据
	 * @param req
	 * @param model
	 * @return
	 */
	/*@RequestMapping(params = "updateBuddhagayaAndReceipt")
	public ModelAndView updateBuddhagayaAndReceipt(HttpServletRequest req,Model model) {*/
	private ModelAndView updateBuddhagayaAndReceipt(String ids,String[] antoserial,Model model) {
		
		//获取清明节记录的ID
//		String ids = req.getParameter("ids");
		String subids = ids.substring(0, ids.length()-1);
		String[] id = subids.split(",");
		
		//获取自动编号
//		String[] antoserial = req.getParameterValues("autoserial");
		
		ReceiptEntity re = new ReceiptEntity();
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String dateString = formatter.format(currentTime);
		
		Calendar a=Calendar.getInstance();
		ReceiptnoEntity reNo = receiptnoService.findByProperty(ReceiptnoEntity.class, "year",Integer.valueOf(a.get(Calendar.YEAR))).get(0);
		int currentMinCount = reNo.getMincount();
		reNo.setMincount(currentMinCount + 1);
		receiptnoService.updateEntitie(reNo);
		
		NumberFormat f=new DecimalFormat("0000000");
		
		String no = f.format(currentMinCount + 1);
		re.setNo("No." + no);
		
		int sum = 0;
		String obj="";

		/*修改打印摘要内容*/
		BuddhagayaEntity entity = buddhagayaService.get(
				BuddhagayaEntity.class, id[0]);
//		StringBuilder stb= new StringBuilder("").append(entity.getPaymen()).append("交来农历 ");
		StringBuilder stb= new StringBuilder("");
		String sumSummary ="";
		
		//获取今年的释尊成道法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "释尊成道");
		FuneralheldEntity funeralheldEntity = funeralhelds.get(0);
		
//----kooking 20180402  原封不动获取大牌和拈香的摘要
		boolean flag = true;// 标志是否是小牌
		if (entity.getSize().equals("大") || entity.getSize().equals("拈香")) {
			sumSummary = entity.getSummary();
			flag = false;
		}

		if (flag) {// 若为小牌
			stb.append(entity.getPaymen()).append("交来农历 ");
			if (funeralheldEntity.getHoldDate().equals(
					funeralheldEntity.getEndDate())) {
				stb.append(funeralheldEntity.getEndDate());
			} else {
				stb.append(funeralheldEntity.getHoldDate() + "至"
						+ funeralheldEntity.getEndDate());
			}
			stb.append(funeralheldEntity.getRitualtype());
			stb.append("功德款</br>");
			sumSummary = stb.toString();
		}
				
//----kooking 20180402 				
		
/*		if(funeralheldEntity.getHoldDate().equals(funeralheldEntity.getEndDate())){
			stb.append(funeralheldEntity.getEndDate());
		}else{
			stb.append(funeralheldEntity.getHoldDate()+"至"+funeralheldEntity.getEndDate());
		}
		stb.append(funeralheldEntity.getRitualtype());
		
		boolean flag = true;//标志是否是小牌
		
		if(entity.getSize().equals("大")){
			stb.append("大牌");
			flag = false;
		}else if(entity.getSize().equals("拈香")){
			stb.append("拈香");
			flag = false;
		}
		stb.append("功德款</br>");
		
		String sumSummary =stb.toString();*/
		String res="";
		/*修改打印摘要内容*/
		
		TSUser user = ResourceUtil.getSessionUserName();
		
		for(int i = 0;i < id.length;i ++){
			BuddhagayaEntity te = buddhagayaService.get(BuddhagayaEntity.class, id[i]);
			te.setAutoserial(antoserial[i]);
			te.setRegistertime(dateString);
			te.setRegistrant(user.getRealName());
			te.setReceiptno(re.getNo());
			buddhagayaService.updateEntitie(te);
			
			sum += te.getMoney();
			obj += te.getLivingmenber();
			
			/*修改打印摘要内容*/
			if(flag){
				sumSummary +=((i+1)+"、");
				sumSummary += te.getSummary() +" ";
			}
			if(i!=id.length-1){
				res += te.getAutoserial() + ",";
			}else{
				res += te.getAutoserial();
			}
		}
		
		
		re.setPaymen(entity.getPaymen());
		re.setPayway(entity.getPayway());
		re.setSize(entity.getSize());
		re.setRegistrant(user.getRealName());
		re.setAddress(entity.getAddress());
		re.setDoritualid(entity.getDoritualid());
		re.setMoney(sum);
		re.setSummary(sumSummary);
		re.setRemark(res);
		re.setObj(obj);
		re.setDate("二#十九#二#廿七");
		//保存收据

		
		
		re.setRitualtype("释尊成道");
		re.setRegistertime(dateString);
		re.setPurpose("释尊成道");
		re.setFlag(0);
		receiptService.save(re);
		String receipId = re.getId();

		for(int i = 0;i < id.length;i ++){
			BuddhagayaEntity te = buddhagayaService.get(BuddhagayaEntity.class, id[i]);
			te.setReceiptid(receipId);
			buddhagayaService.updateEntitie(te);
		}
		
		
		model.addAttribute("message", "释尊成道登记成功");
		model.addAttribute("ritualtype", "释尊成道");
		ReceiptEntity returnRe = receiptService.get(ReceiptEntity.class, re.getId());
		model.addAttribute("returnRe", returnRe);
		String bigMoney=ChineseCurrency.toChineseCurrency(new Double(returnRe.getMoney()));
		model.addAttribute("bigMoney",bigMoney);
		String smallMoney=ChineseCurrency.toSmall(new Double(returnRe.getMoney()));
		model.addAttribute("smallMoney",smallMoney);
		return new ModelAndView("com/sys/success");
	}
	
	
	@RequestMapping(params = "getSerialAndSaveTablet")
	public ModelAndView getSerial(HttpServletRequest req,Model model){
		
		String buddhagayaid = req.getParameter("buddhagayaid");
		if(buddhagayaid != null && !buddhagayaid.equals("")){
			BuddhagayaEntity buddhagayaEntity = buddhagayaService.get(BuddhagayaEntity.class, buddhagayaid);
			if(buddhagayaEntity.getSize().equals("小")){
				buddhagayaEntity.setAddress(req.getParameter("address"));
				buddhagayaEntity.setLivingmenber(req.getParameter("livingmenber"));
				buddhagayaEntity.setMoney(Integer.valueOf(req.getParameter("money")));
				buddhagayaEntity.setPayway(req.getParameter("payway"));
				buddhagayaEntity.setSummary(req.getParameter("summary"));
				buddhagayaEntity.setPrayingobj(req.getParameter("prayingobj"));
				buddhagayaEntity.setPaymen(req.getParameter("paymen"));
				buddhagayaService.updateEntitie(buddhagayaEntity);
				model.addAttribute("message", "释尊成道牌位修改成功！！");
				return new ModelAndView("com/sys/updateSuccess");
			}
			else{
				String[] living = req.getParameterValues("living");
				
				String livingString = "";
				for(int i = 0;i < living.length;i ++){
					if(living[i].equals("") || living[i] == null){
						living[i] = " ";
					}
					livingString += living[i] + ";";
				}
				
				buddhagayaEntity.setAddress(req.getParameter("address"));
				buddhagayaEntity.setLivingmenber(livingString);
				buddhagayaEntity.setMoney(Integer.valueOf(Integer.valueOf(req.getParameter("money"))));
				buddhagayaEntity.setPayway(req.getParameter("payway"));
				buddhagayaEntity.setSummary(req.getParameter("summary"));
				buddhagayaEntity.setPrayingobj(req.getParameter("prayingobj"));
				buddhagayaEntity.setPaymen(req.getParameter("paymen"));
				buddhagayaEntity.setBook(req.getParameter("book"));
				
				buddhagayaService.updateEntitie(buddhagayaEntity);
				model.addAttribute("message", "释尊成道牌位修改成功！！");
				return new ModelAndView("com/sys/updateSuccess");
			}
		}
		
		try{
			String sizeflag = req.getParameter("sizeflag");
			if(sizeflag == null || sizeflag.equals("小")){
				//获取做法事人的ID
				
				String id0 = req.getParameter("id");
				String paymen = req.getParameter("paymen");
				//获取祈福者
				String[] prayingobj = req.getParameterValues("prayingobj");
				
				//获取祈福对象
				String[] livingmember = req.getParameterValues("livingmenber");
				
				//获取祈福者家庭住址
				String[] address = req.getParameterValues("address");
				
				//获取摘要
				String[] summary = req.getParameterValues("summary");
				
				//获取摘要
				String[] payway = req.getParameterValues("payway");
				
				//获取摘要
				String[] money = req.getParameterValues("money");
				
				//获取大小
				String[] size = req.getParameterValues("size");
				String book = req.getParameter("book");
				
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH)+1;
				//当前年份
				String currentyear = String.valueOf(year);
				//上一年的年份
				String lastyear = String.valueOf(year-1);
				
//				List<BuddhagayaEntity> buddhagayaEntityList = new ArrayList<BuddhagayaEntity>();
				String ids = "";
				String[] antoserial=new String[size.length];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
				//当前日期的字符串
				String dateString = formatter.format(currentTime);
				
				for(int i = 0;i < size.length;i ++){
					BuddhagayaEntity buddhagayaEntity = new BuddhagayaEntity();
					CriteriaQuery cq = new CriteriaQuery(BuddhagayaEntity.class);
					/**
					 * x=当前日期
					 * if(x.month>1){
					 * 		统计登陆日期在 x.year.2.1 ~ 当前日期
					 * }else{ 
					 * 	         统计登陆日期在(x.year-1).2.1	~ 当前日期
					 * }
					 */
					//起始日期的字符串
					String startDateString = null;
					
					if(month>1) {
						startDateString = currentyear+"年02月01日";								
					}else {						
						startDateString = lastyear+"年02月01日";
					}
					cq.add(Restrictions.and(Restrictions.eq("size",size[i]),Restrictions.between("registertime", startDateString, dateString)));
					List<BuddhagayaEntity> buddhagayas = buddhagayaService.getListByCriteriaQuery(cq,false);
//					cq.add(Restrictions.and(Restrictions.eq("size",size[i]),Restrictions.like("registertime",currentyear,MatchMode.START)));
//					List<BuddhagayaEntity> buddhagayas = buddhagayaService.getListByCriteriaQuery(cq,false);
					
					buddhagayaEntity.setAddress(address[i]);
					buddhagayaEntity.setLivingmenber(livingmember[i]);
					buddhagayaEntity.setMoney(Integer.valueOf(money[i]));
					buddhagayaEntity.setPayway(payway[i]);
					buddhagayaEntity.setSummary(summary[i]);
					buddhagayaEntity.setPrayingobj(prayingobj[i]);
					buddhagayaEntity.setDoritualid(id0);
					buddhagayaEntity.setPaymen(paymen);
					buddhagayaEntity.setSize(size[i]);
					//换时间
					if(!size[i].equals("小")){
						buddhagayaEntity.setBook(book);
					}
					
					buddhagayaEntity.setFlag(0);
					NumberFormat f=new DecimalFormat("000000");
					buddhagayaEntity.setSerial(f.format(buddhagayas.size() + 1));
					buddhagayaEntity.setRegistertime(dateString);
//					buddhagayaEntityList.add(buddhagayaEntity);
					
					buddhagayaService.save(buddhagayaEntity);
					
					ControllerUtils.save2Pray(buddhagayaEntity, buddhagayaService);
					
					ids += buddhagayaEntity.getId() + ",";
					antoserial[i]=buddhagayaEntity.getSerial();
				}
//				req.setAttribute("buddhagayaEntityList", buddhagayaEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView("com/sys/buddhagaya/AutoSerialBuddhagaya");
				
//------------20180502 Kooking		
				return updateBuddhagayaAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
			}
			else{
				String id0 = req.getParameter("id");
				
				String paymen = req.getParameter("paymen");
				
				//获取祈福者
				String prayingobj = req.getParameter("prayingobj");
				
				//获取祈福对象
				String[] living = req.getParameterValues("living");
				
				//获取祈福者家庭住址
				String address = req.getParameter("address");
				
				//获取摘要
				String summary = req.getParameter("summary");
				
				//获取摘要
				String payway = req.getParameter("payway");
				
				//获取摘要
				String money = req.getParameter("money");
				
				//获取大小
				String book = req.getParameter("book");
				
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				int month = cal.get(Calendar.MONTH)+1;
				//当前年份
				String currentyear = String.valueOf(year);
				//上一年的年份
				String lastyear = String.valueOf(year-1);
				
//				List<BuddhagayaEntity> buddhagayaEntityList = new ArrayList<BuddhagayaEntity>();
				String ids = "";
				String[] antoserial=new String[1];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
				String dateString = formatter.format(currentTime);
				
				BuddhagayaEntity buddhagayaEntity = new BuddhagayaEntity();
				CriteriaQuery cq = new CriteriaQuery(BuddhagayaEntity.class);
				
				//起始日期的字符串
				String startDateString = null;
				
				if(month>1) {
					startDateString = currentyear+"年02月01日";								
				}else {						
					startDateString = lastyear+"年02月01日";
				}
				cq.add(Restrictions.and(Restrictions.eq("size",sizeflag),Restrictions.between("registertime", startDateString, dateString)));
				List<BuddhagayaEntity> buddhagayas = buddhagayaService.getListByCriteriaQuery(cq,false);
				
				
				//cq.add(Restrictions.and(Restrictions.eq("size",sizeflag),Restrictions.like("registertime",currentyear,MatchMode.START)));
				//List<BuddhagayaEntity> buddhagayas = buddhagayaService.getListByCriteriaQuery(cq,false);
				
				String livingString = "";
				for(int i = 0;i < living.length;i ++){
					if(living[i].equals("") || living[i] == null){
						living[i] = " ";
					}
					livingString += living[i] + ";";
				}
				
				buddhagayaEntity.setAddress(address);
				buddhagayaEntity.setLivingmenber(livingString);
				buddhagayaEntity.setMoney(Integer.valueOf(money));
				buddhagayaEntity.setPayway(payway);
				buddhagayaEntity.setSummary(summary);
				buddhagayaEntity.setPrayingobj(prayingobj);
				buddhagayaEntity.setDoritualid(id0);
				buddhagayaEntity.setPaymen(paymen);
				buddhagayaEntity.setBook(book);
				buddhagayaEntity.setSize(sizeflag);
				buddhagayaEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				buddhagayaEntity.setSerial(f.format(buddhagayas.size() + 1));
				buddhagayaEntity.setRegistertime(dateString);
//				buddhagayaEntityList.add(buddhagayaEntity);
					
				buddhagayaService.save(buddhagayaEntity);
				
				ControllerUtils.save2Pray(buddhagayaEntity, buddhagayaService);
				
				ids += buddhagayaEntity.getId() + ",";
				antoserial[0]=buddhagayaEntity.getSerial();
				
//				req.setAttribute("buddhagayaEntityList", buddhagayaEntityList);
//				req.setAttribute("ids", ids);
//			    return new ModelAndView("com/sys/buddhagaya/AutoSerialBuddhagaya");
				
//------------20180502 Kooking		
				return updateBuddhagayaAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
		
	}
	
	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(BuddhagayaEntity buddhagayaEntity,HttpServletRequest request,Model model) {
		List<BuddhagayaEntity> buddhagayaEntitys = new ArrayList<BuddhagayaEntity>();
		if (StringUtil.isNotEmpty(buddhagayaEntity.getId())) {
			buddhagayaEntity = buddhagayaService.getEntity(BuddhagayaEntity.class, buddhagayaEntity.getId());
			
			TSUser user = ResourceUtil.getSessionUserName();
			if(!user.getRealName().equals(buddhagayaEntity.getRegistrant())){
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			if(buddhagayaEntity.getSize().equals("小")){
				buddhagayaEntitys.add(buddhagayaEntity);
				model.addAttribute("buddhagayaEntitys", buddhagayaEntitys);
				model.addAttribute("id", buddhagayaEntity.getDoritualid());
				model.addAttribute("buddhagayaid", buddhagayaEntity.getId());
				model.addAttribute("clientele", buddhagayaEntity.getPaymen());
				model.addAttribute("size", buddhagayaEntity.getSize());
				return new ModelAndView("com/sys/buddhagaya/editBuddhagaya");
			}
			else{
				
				String livingString = buddhagayaEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				buddhagayaEntitys.add(buddhagayaEntity);
				model.addAttribute("buddhagayaEntity", buddhagayaEntity);
				model.addAttribute("buddhagayaEntitys", buddhagayaEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", buddhagayaEntity.getDoritualid());
				model.addAttribute("size", buddhagayaEntity.getSize());
				model.addAttribute("clientele", buddhagayaEntity.getPaymen());
				model.addAttribute("buddhagayaid", buddhagayaEntity.getId());
				return new ModelAndView("com/sys/buddhagaya/editMiddleAndBigBuddhagaya");
			}
		}
		else{
			return new ModelAndView("com/sys/error");
		}
	}
}
