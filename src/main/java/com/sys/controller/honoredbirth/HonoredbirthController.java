package com.sys.controller.honoredbirth;
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
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.entity.buddhabirth.BuddhabirthEntity;
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.guanyinopen.GuanyinopenEntity;
import com.sys.entity.honoredbirth.HonoredbirthEntity;
import com.sys.entity.livingmenber.LivingmenberEntity;
import com.sys.entity.pharmacistbirth.PharmacistbirthEntity;
import com.sys.entity.pray.PrayEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.honoredbirth.HonoredbirthServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;
import com.sys.util.ControllerUtils;

/**   
 * @Title: Controller
 * @Description: 释尊诞信息
 * @author zhangdaihao
 * @date 2016-03-03 08:30:28
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/honoredbirthController")
public class HonoredbirthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(HonoredbirthController.class);

	@Autowired
	private HonoredbirthServiceI honoredbirthService;
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
	 * 释尊诞信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "honoredbirth")
	public ModelAndView honoredbirth(HttpServletRequest request) {
		return new ModelAndView("com/sys/honoredbirth/honoredbirthList");
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
	public void datagrid(HonoredbirthEntity honoredbirth,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(HonoredbirthEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, honoredbirth, request.getParameterMap());
		this.honoredbirthService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除释尊诞信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(HonoredbirthEntity honoredbirth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		honoredbirth = systemService.getEntity(HonoredbirthEntity.class, honoredbirth.getId());
		message = "释尊诞信息删除成功";
		honoredbirthService.delete(honoredbirth);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加释尊诞信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(HonoredbirthEntity honoredbirth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(honoredbirth.getId())) {
			message = "释尊诞信息更新成功";
			HonoredbirthEntity t = honoredbirthService.get(HonoredbirthEntity.class, honoredbirth.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(honoredbirth, t);
				honoredbirthService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "释尊诞信息更新失败";
			}
		} else {
			message = "释尊诞信息添加成功";
			honoredbirthService.save(honoredbirth);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 释尊诞信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(HonoredbirthEntity honoredbirth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(honoredbirth.getId())) {
			honoredbirth = honoredbirthService.getEntity(HonoredbirthEntity.class, honoredbirth.getId());
			req.setAttribute("honoredbirthPage", honoredbirth);
		}
		return new ModelAndView("com/sys/honoredbirth/honoredbirth");
	}
	
	
	@RequestMapping(params = "redirectToShowHonoredbirthRecord")
	public ModelAndView redirectToShowHonoredbirthRecord(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");
//		String hql0 = "from HonoredbirthEntity where 1 = 1 AND doritualid = ? AND size = ?";
		String sql = "select * from pray where 1 = 1 AND doritualid = ? AND size = ?";//20180504
		if(id0 != null){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
//			List<HonoredbirthEntity> honoredbirthEntitys = honoredbirthService.findHql(hql0,id0,size);
			List<HonoredbirthEntity> honoredbirthEntitys = honoredbirthService
					.listBySQL(HonoredbirthEntity.class, sql, id0,size);
			model.addAttribute("doritualinfoEntity", doritualinfoEntity);
			model.addAttribute("honoredbirthEntitys", honoredbirthEntitys);
			model.addAttribute("id", id0);
			model.addAttribute("size", size);
		}
		
		return new ModelAndView("com/sys/honoredbirth/showHonoredbirthRecord");
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

			//查询今年的释尊诞法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "释尊诞");
			if(funeralhelds.size()!=1){
				model.addAttribute("message", "抱歉，今年的释尊诞法事举行日期安排 登记不正常，请与系统管理员联系！");
				return new ModelAndView("com/sys/nofuneralheldplan");
			}
			
//			---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记			
			
		model.addAttribute("id0", id0);
		
		
		return new ModelAndView("com/sys/honoredbirth/selectSize");
	}
	
	/**
	 * 跳转到编辑登记释尊诞
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToEditHonoredbirth")
	public ModelAndView redirectToEditHonoredbirth(HttpServletRequest req,Model model) {
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
		String honoredbirthIds = req.getParameter("honoredbirthIds");
		
		String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ?";
		List<LivingmenberEntity> livingmenberEntityList = systemService.findHql(hql0,id0);
		
//		List<HonoredbirthEntity> honoredbirthEntitys = new ArrayList<HonoredbirthEntity>();
		List<PrayEntity> honoredbirthEntitys = new ArrayList<PrayEntity>();
		
		//查询今年的释尊诞法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "释尊诞");
		model.addAttribute("funeralheld", funeralhelds);			
		
		//添加做法事人的地址
		model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
		model.addAttribute("updateFlag", "0");		
		
		if(size.equals("小")){
			if(honoredbirthIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("honoredbirthEntitys", honoredbirthEntitys);
				
				return new ModelAndView("com/sys/honoredbirth/editHonoredbirth");
			}
			
			
			
			//用逗号切开
			String honoredbirthIdsSplitByComma[] = honoredbirthIds.substring(0, honoredbirthIds.length()-1).split(",");
			
			//分别获取相关数据
			for(int i = 0;i < honoredbirthIdsSplitByComma.length;i ++){
//				HonoredbirthEntity honoredbirthEntity = honoredbirthService.get(HonoredbirthEntity.class, honoredbirthIdsSplitByComma[i]);
				PrayEntity honoredbirthEntity = honoredbirthService.get(PrayEntity.class, honoredbirthIdsSplitByComma[i]);
				honoredbirthEntitys.add(honoredbirthEntity);
			}
			
			
	
	
			model.addAttribute("honoredbirthEntitys", honoredbirthEntitys);
			model.addAttribute("livingmenberEntityList", livingmenberEntityList);
			model.addAttribute("id", id0);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			model.addAttribute("book", honoredbirthEntitys.get(0).getBook());
			return new ModelAndView("com/sys/honoredbirth/editHonoredbirth");
		}
		else{
			if(honoredbirthIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("honoredbirthEntitys", honoredbirthEntitys);
				model.addAttribute("size", size);
				return new ModelAndView("com/sys/honoredbirth/editMiddleAndBigHonoredbirth");
			}
			else{
				//已经有数据
//				HonoredbirthEntity honoredbirthEntity = honoredbirthService.get(HonoredbirthEntity.class, honoredbirthIds.substring(0, honoredbirthIds.length() - 1));
				PrayEntity honoredbirthEntity = honoredbirthService.get(PrayEntity.class, honoredbirthIds.substring(0, honoredbirthIds.length() - 1));
				
				String livingString = honoredbirthEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				honoredbirthEntitys.add(honoredbirthEntity);
				model.addAttribute("honoredbirthEntity", honoredbirthEntity);
				model.addAttribute("honoredbirthEntitys", honoredbirthEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", id0);
				model.addAttribute("size", size);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				return new ModelAndView("com/sys/honoredbirth/editMiddleAndBigHonoredbirth");
			}
		}
	}
	

	
	/**
	 * 保存编辑后的药师诞记录和收据
	 * @param req
	 * @param model
	 * @return
	 */
	/*@RequestMapping(params = "updateHonoredbirthAndReceipt")
	public ModelAndView updateHonoredbirthAndReceipt(HttpServletRequest req,Model model) {*/
	private ModelAndView updateHonoredbirthAndReceipt(String ids,String[] antoserial,Model model) {
		
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
		HonoredbirthEntity entity = honoredbirthService.get(
				HonoredbirthEntity.class, id[0]);
//		StringBuilder stb= new StringBuilder("").append(entity.getPaymen()).append("交来农历 ");
		StringBuilder stb= new StringBuilder("");
		String sumSummary ="";
		
		//查询今年的释尊诞法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "释尊诞");
		FuneralheldEntity funeralheldEntity = funeralhelds.get(0);
		
// ----kooking 20180402 原封不动获取大牌和拈香的摘要
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

// ----kooking 20180402				
		
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
			HonoredbirthEntity te = honoredbirthService.get(HonoredbirthEntity.class, id[i]);
			te.setAutoserial(antoserial[i]);
			te.setRegistertime(dateString);
			te.setRegistrant(user.getRealName());
			te.setReceiptno(re.getNo());
			honoredbirthService.updateEntitie(te);
			if(flag){
				sumSummary +=((i+1)+"、");
				sumSummary += te.getSummary() +" ";
			}
			sum += te.getMoney();
			obj += te.getLivingmenber();
			
			if(i!=id.length-1){
				res += te.getAutoserial() + ",";
			}else{
				res += te.getAutoserial();
			}
		}
		
		
		re.setPaymen(entity.getPaymen());
		re.setPayway(entity.getPayway());
		re.setRegistrant(user.getRealName());
		re.setSize(entity.getSize());
		re.setAddress(entity.getAddress());
		re.setDoritualid(entity.getDoritualid());
		re.setMoney(sum);
		
		//sumSummary = entity.getSummary();
		
		re.setSummary(sumSummary);
		re.setRemark(res);
		re.setObj(obj);
		re.setDate("二#十九#二#廿七");
		//保存收据

		
		
		re.setRitualtype("释尊诞");
		re.setRegistertime(dateString);
		re.setPurpose("释尊诞");
		re.setFlag(0);
		receiptService.save(re);
		String receipId = re.getId();

		for(int i = 0;i < id.length;i ++){
			HonoredbirthEntity te = honoredbirthService.get(HonoredbirthEntity.class, id[i]);
			te.setReceiptid(receipId);
			honoredbirthService.updateEntitie(te);
		}
		
		
		model.addAttribute("message", "释尊诞登记成功");
		model.addAttribute("ritualtype", "释尊诞");
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
		
		String honoredbirthid = req.getParameter("honoredbirthid");
		if(honoredbirthid != null && !honoredbirthid.equals("")){
			HonoredbirthEntity honoredbirthEntity = honoredbirthService.get(HonoredbirthEntity.class, honoredbirthid);
			if(honoredbirthEntity.getSize().equals("小")){
				honoredbirthEntity.setAddress(req.getParameter("address"));
				honoredbirthEntity.setLivingmenber(req.getParameter("livingmenber"));
				honoredbirthEntity.setMoney(Integer.valueOf(req.getParameter("money")));
				honoredbirthEntity.setPayway(req.getParameter("payway"));
				honoredbirthEntity.setSummary(req.getParameter("summary"));
				honoredbirthEntity.setPrayingobj(req.getParameter("prayingobj"));
				honoredbirthEntity.setPaymen(req.getParameter("paymen"));
				honoredbirthService.updateEntitie(honoredbirthEntity);
				model.addAttribute("message", "释尊诞牌位修改成功！！");
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
				
				honoredbirthEntity.setAddress(req.getParameter("address"));
				honoredbirthEntity.setLivingmenber(livingString);
				honoredbirthEntity.setMoney(Integer.valueOf(Integer.valueOf(req.getParameter("money"))));
				honoredbirthEntity.setPayway(req.getParameter("payway"));
				honoredbirthEntity.setSummary(req.getParameter("summary"));
				honoredbirthEntity.setPrayingobj(req.getParameter("prayingobj"));
				honoredbirthEntity.setPaymen(req.getParameter("paymen"));
				honoredbirthEntity.setBook(req.getParameter("book"));
				
				honoredbirthService.updateEntitie(honoredbirthEntity);
				model.addAttribute("message", "释尊诞牌位修改成功！！");
				return new ModelAndView("com/sys/updateSuccess");
			}
		}
		
		
		try{
			String sizeflag = req.getParameter("sizeflag");
			if(sizeflag == null || sizeflag.equals("小")){
			//获取做法事人的ID
			String id0 = req.getParameter("id");
			
			//获取祈福者
			String[] prayingobj = req.getParameterValues("prayingobj");
			String paymen = req.getParameter("paymen");
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
			String currentyear = String.valueOf(year);
			
//			List<HonoredbirthEntity> honoredbirthEntityList = new ArrayList<HonoredbirthEntity>();
			String ids = "";
			String[] antoserial=new String[size.length];
			
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(currentTime);
			
			for(int i = 0;i < size.length;i ++){
				HonoredbirthEntity honoredbirthEntity = new HonoredbirthEntity();
				CriteriaQuery cq = new CriteriaQuery(HonoredbirthEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",size[i]),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<HonoredbirthEntity> honoredbirths = honoredbirthService.getListByCriteriaQuery(cq,false);
				
				honoredbirthEntity.setAddress(address[i]);
				honoredbirthEntity.setLivingmenber(livingmember[i]);
				honoredbirthEntity.setMoney(Integer.valueOf(money[i]));
				honoredbirthEntity.setPayway(payway[i]);
				honoredbirthEntity.setSummary(summary[i]);
				honoredbirthEntity.setPrayingobj(prayingobj[i]);
				honoredbirthEntity.setDoritualid(id0);
				honoredbirthEntity.setSize(size[i]);
				honoredbirthEntity.setPaymen(paymen);
				//换时间
				if(!size[i].equals("小")){
					honoredbirthEntity.setBook(book);
				}
				
				honoredbirthEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				honoredbirthEntity.setSerial(f.format(honoredbirths.size() + 1));
				honoredbirthEntity.setRegistertime(dateString);
//				honoredbirthEntityList.add(honoredbirthEntity);
				
				honoredbirthService.save(honoredbirthEntity);
				
				ControllerUtils.save2Pray(honoredbirthEntity, honoredbirthService);
				
				ids += honoredbirthEntity.getId() + ",";
				antoserial[i]=honoredbirthEntity.getSerial();
			}
//			req.setAttribute("honoredbirthEntityList", honoredbirthEntityList);
//			req.setAttribute("ids", ids);
//			return new ModelAndView("com/sys/honoredbirth/AutoSerialHonoredbirth");
			
//------------20180502 Kooking		
			return updateHonoredbirthAndReceipt(ids, antoserial, model);
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
				String currentyear = String.valueOf(year);
				
//				List<HonoredbirthEntity> honoredbirthEntityList = new ArrayList<HonoredbirthEntity>();
				String ids = "";
				String[] antoserial=new String[1];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				HonoredbirthEntity honoredbirthEntity = new HonoredbirthEntity();
				CriteriaQuery cq = new CriteriaQuery(HonoredbirthEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",sizeflag),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<HonoredbirthEntity> honoredbirths = honoredbirthService.getListByCriteriaQuery(cq,false);
				
				String livingString = "";
				for(int i = 0;i < living.length;i ++){
					if(living[i].equals("") || living[i] == null){
						living[i] = " ";
					}
					livingString += living[i] + ";";
				}
				
				honoredbirthEntity.setAddress(address);
				honoredbirthEntity.setLivingmenber(livingString);
				honoredbirthEntity.setMoney(Integer.valueOf(money));
				honoredbirthEntity.setPayway(payway);
				honoredbirthEntity.setSummary(summary);
				honoredbirthEntity.setPrayingobj(prayingobj);
				honoredbirthEntity.setDoritualid(id0);
				honoredbirthEntity.setPaymen(paymen);
				honoredbirthEntity.setBook(book);
				honoredbirthEntity.setSize(sizeflag);
				honoredbirthEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				honoredbirthEntity.setSerial(f.format(honoredbirths.size() + 1));
				honoredbirthEntity.setRegistertime(dateString);
//				honoredbirthEntityList.add(honoredbirthEntity);
					
				honoredbirthService.save(honoredbirthEntity);
				
				ControllerUtils.save2Pray(honoredbirthEntity, honoredbirthService);
				
				ids += honoredbirthEntity.getId() + ",";
				antoserial[0]=honoredbirthEntity.getSerial();
				
//				req.setAttribute("honoredbirthEntityList", honoredbirthEntityList);
//				req.setAttribute("ids", ids);
//				 return new ModelAndView("com/sys/honoredbirth/AutoSerialHonoredbirth");
				
//------------20180502 Kooking		
				return updateHonoredbirthAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
		
	}
	
	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(HonoredbirthEntity honoredbirthEntity,HttpServletRequest request,Model model) {
		List<HonoredbirthEntity> honoredbirthEntitys = new ArrayList<HonoredbirthEntity>();
		if (StringUtil.isNotEmpty(honoredbirthEntity.getId())) {
			honoredbirthEntity = honoredbirthService.getEntity(HonoredbirthEntity.class, honoredbirthEntity.getId());
			
			TSUser user = ResourceUtil.getSessionUserName();
			if(!user.getRealName().equals(honoredbirthEntity.getRegistrant())){
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			if(honoredbirthEntity.getSize().equals("小")){
				honoredbirthEntitys.add(honoredbirthEntity);
				model.addAttribute("honoredbirthEntitys", honoredbirthEntitys);
				model.addAttribute("id", honoredbirthEntity.getDoritualid());
				model.addAttribute("honoredbirthid", honoredbirthEntity.getId());
				model.addAttribute("clientele", honoredbirthEntity.getPaymen());
				model.addAttribute("size", honoredbirthEntity.getSize());
				return new ModelAndView("com/sys/honoredbirth/editHonoredbirth");
			}
			else{
				
				String livingString = honoredbirthEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				honoredbirthEntitys.add(honoredbirthEntity);
				model.addAttribute("honoredbirthEntity", honoredbirthEntity);
				model.addAttribute("honoredbirthEntitys", honoredbirthEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", honoredbirthEntity.getDoritualid());
				model.addAttribute("size", honoredbirthEntity.getSize());
				model.addAttribute("clientele", honoredbirthEntity.getPaymen());
				model.addAttribute("honoredbirthid", honoredbirthEntity.getId());
				return new ModelAndView("com/sys/honoredbirth/editMiddleAndBigHonoredbirth");
			}
		}
		else{
			return new ModelAndView("com/sys/error");
		}
	}
}
