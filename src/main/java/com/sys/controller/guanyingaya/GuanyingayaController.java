package com.sys.controller.guanyingaya;
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
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.goddessbirth.GoddessbirthEntity;
import com.sys.entity.guanyingaya.GuanyingayaEntity;
import com.sys.entity.guanyinopen.GuanyinopenEntity;
import com.sys.entity.livingmenber.LivingmenberEntity;
import com.sys.entity.pharmacistbirth.PharmacistbirthEntity;
import com.sys.entity.pray.PrayEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.guanyingaya.GuanyingayaServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;
import com.sys.util.ControllerUtils;

/**   
 * @Title: Controller
 * @Description: 观音成道信息表
 * @author zhangdaihao
 * @date 2016-03-03 08:51:10
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/guanyingayaController")
public class GuanyingayaController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GuanyingayaController.class);

	@Autowired
	private GuanyingayaServiceI guanyingayaService;
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
	 * 观音成道信息表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "guanyingaya")
	public ModelAndView guanyingaya(HttpServletRequest request) {
		return new ModelAndView("com/sys/guanyingaya/guanyingayaList");
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
	public void datagrid(GuanyingayaEntity guanyingaya,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(GuanyingayaEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, guanyingaya, request.getParameterMap());
		this.guanyingayaService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除观音成道信息表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(GuanyingayaEntity guanyingaya, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		guanyingaya = systemService.getEntity(GuanyingayaEntity.class, guanyingaya.getId());
		message = "观音成道信息表删除成功";
		guanyingayaService.delete(guanyingaya);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加观音成道信息表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(GuanyingayaEntity guanyingaya, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(guanyingaya.getId())) {
			message = "观音成道信息表更新成功";
			GuanyingayaEntity t = guanyingayaService.get(GuanyingayaEntity.class, guanyingaya.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(guanyingaya, t);
				guanyingayaService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "观音成道信息表更新失败";
			}
		} else {
			message = "观音成道信息表添加成功";
			guanyingayaService.save(guanyingaya);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 观音成道信息表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(GuanyingayaEntity guanyingaya, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(guanyingaya.getId())) {
			guanyingaya = guanyingayaService.getEntity(GuanyingayaEntity.class, guanyingaya.getId());
			req.setAttribute("guanyingayaPage", guanyingaya);
		}
		return new ModelAndView("com/sys/guanyingaya/guanyingaya");
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

			//获取今年的观音成道法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "观音成道");
			if(funeralhelds.size()!=1){
				model.addAttribute("message", "抱歉，今年的观音成道法事举行日期安排 登记不正常，请与系统管理员联系！");
				return new ModelAndView("com/sys/nofuneralheldplan");
			}
			
//			---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记			
			
		model.addAttribute("id0", id0);
		return new ModelAndView("com/sys/guanyingaya/selectSize");
	}
	
	@RequestMapping(params = "redirectToShowGuanyingayaRecord")
	public ModelAndView redirectToShowGuanyingayaRecord(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");
//		String hql0 = "from GuanyingayaEntity where 1 = 1 AND doritualid = ? AND size = ?";
		String sql = "select * from pray where 1 = 1 AND doritualid = ? AND size = ?";//20180504
		if(id0 != null){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
//			List<GuanyingayaEntity> guanyingayaEntitys = guanyingayaService.findHql(hql0,id0,size);
			List<GuanyingayaEntity> guanyingayaEntitys = guanyingayaService
					.listBySQL(GuanyingayaEntity.class, sql, id0,size);
			model.addAttribute("doritualinfoEntity", doritualinfoEntity);
			model.addAttribute("guanyingayaEntitys", guanyingayaEntitys);
			model.addAttribute("id", id0);
			model.addAttribute("size", size);
		}
		
		return new ModelAndView("com/sys/guanyingaya/showGuanyingayaRecord");
	}
	
	/**
	 * 跳转到编辑登记观音成道
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToEditGuanyingaya")
	public ModelAndView redirectToEditGuanyingaya(HttpServletRequest req,Model model) {
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
		String guanyingayaIds = req.getParameter("guanyingayaIds");
		
		String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ? ";
		List<LivingmenberEntity> livingmenberEntityList = systemService.findHql(hql0,id0);
		
//		List<GuanyingayaEntity> guanyingayaEntitys = new ArrayList<GuanyingayaEntity>();
		List<PrayEntity> guanyingayaEntitys = new ArrayList<PrayEntity>();
		
		//获取今年的观音成道法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "观音成道");
		model.addAttribute("funeralheld", funeralhelds);		
		
		//添加做法事人的地址
		model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
		model.addAttribute("updateFlag", "0");		
		
		if(size.equals("小")){
			if(guanyingayaIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("guanyingayaEntitys", guanyingayaEntitys);
				return new ModelAndView("com/sys/guanyingaya/editGuanyingaya");
			}
			
			
			
			//用逗号切开
			String guanyingayaIdsSplitByComma[] = guanyingayaIds.substring(0, guanyingayaIds.length()-1).split(",");
			
			//分别获取相关数据
			for(int i = 0;i < guanyingayaIdsSplitByComma.length;i ++){
//				GuanyingayaEntity guanyingayaEntity = guanyingayaService.get(GuanyingayaEntity.class, guanyingayaIdsSplitByComma[i]);
				PrayEntity guanyingayaEntity = guanyingayaService.get(PrayEntity.class, guanyingayaIdsSplitByComma[i]);
				guanyingayaEntitys.add(guanyingayaEntity);
			}
			
			
	
	
			model.addAttribute("guanyingayaEntitys", guanyingayaEntitys);
			model.addAttribute("livingmenberEntityList", livingmenberEntityList);
			model.addAttribute("id", id0);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			model.addAttribute("book", guanyingayaEntitys.get(0).getBook());
			return new ModelAndView("com/sys/guanyingaya/editGuanyingaya");
		}
		else{
			if(guanyingayaIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("guanyingayaEntitys", guanyingayaEntitys);
				model.addAttribute("size", size);
				return new ModelAndView("com/sys/guanyingaya/editMiddleAndBigGuanyingaya");
			}
			else{
				//已经有数据
//				GuanyingayaEntity guanyingayaEntity = guanyingayaService.get(GuanyingayaEntity.class, guanyingayaIds.substring(0, guanyingayaIds.length() - 1));
				PrayEntity guanyingayaEntity = guanyingayaService.get(PrayEntity.class, guanyingayaIds.substring(0, guanyingayaIds.length() - 1));
				
				String livingString = guanyingayaEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				guanyingayaEntitys.add(guanyingayaEntity);
				model.addAttribute("guanyingayaEntity", guanyingayaEntity);
				model.addAttribute("guanyingayaEntitys", guanyingayaEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", id0);
				model.addAttribute("size", size);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				return new ModelAndView("com/sys/guanyingaya/editMiddleAndBigGuanyingaya");
			}
		}
	}

	
	/**
	 * 保存编辑后的药师诞记录和收据
	 * @param req
	 * @param model
	 * @return
	 */
	/*@RequestMapping(params = "updateGuanyingayaAndReceipt")
	public ModelAndView updateGuanyingayaAndReceipt(HttpServletRequest req,Model model) {*/
	private ModelAndView updateGuanyingayaAndReceipt(String ids,String[] antoserial,Model model) {
		
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
		GuanyingayaEntity entity = guanyingayaService.get(
				GuanyingayaEntity.class, id[0]);
//		StringBuilder stb= new StringBuilder("").append(entity.getPaymen()).append("交来农历 ");
		
		StringBuilder stb= new StringBuilder("");
		String sumSummary ="";
		
		//获取今年的观音成道法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "观音成道");
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
		
	/*	if(funeralheldEntity.getHoldDate().equals(funeralheldEntity.getEndDate())){
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
			GuanyingayaEntity te = guanyingayaService.get(GuanyingayaEntity.class, id[i]);
			te.setAutoserial(antoserial[i]);
			te.setRegistertime(dateString);
			te.setRegistrant(user.getRealName());
			te.setReceiptno(re.getNo());
			guanyingayaService.updateEntitie(te);
			
			sum += te.getMoney();
			obj += te.getLivingmenber();
			/*修改打印摘要内容*/
			if(flag){
				sumSummary +=((i+1)+"、");
				sumSummary += te.getSummary()+" " ;
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

		
		
		re.setRitualtype("观音成道");
		re.setRegistertime(dateString);
		re.setPurpose("观音成道");
		re.setFlag(0);
		receiptService.save(re);
		String receipId = re.getId();

		for(int i = 0;i < id.length;i ++){
			GuanyingayaEntity te = guanyingayaService.get(GuanyingayaEntity.class, id[i]);
			te.setReceiptid(receipId);
			guanyingayaService.updateEntitie(te);
		}
		
		
		model.addAttribute("message", "观音成道登记成功");
		model.addAttribute("ritualtype", "观音成道");
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
		
		String guanyingayaid = req.getParameter("guanyingayaid");
		if(guanyingayaid != null && !guanyingayaid.equals("")){
			GuanyingayaEntity guanyingayaEntity = guanyingayaService.get(GuanyingayaEntity.class, guanyingayaid);
			if(guanyingayaEntity.getSize().equals("小")){
				guanyingayaEntity.setAddress(req.getParameter("address"));
				guanyingayaEntity.setLivingmenber(req.getParameter("livingmenber"));
				guanyingayaEntity.setMoney(Integer.valueOf(req.getParameter("money")));
				guanyingayaEntity.setPayway(req.getParameter("payway"));
				guanyingayaEntity.setSummary(req.getParameter("summary"));
				guanyingayaEntity.setPrayingobj(req.getParameter("prayingobj"));
				guanyingayaEntity.setPaymen(req.getParameter("paymen"));
				guanyingayaService.updateEntitie(guanyingayaEntity);
				model.addAttribute("message", "观音成道牌位修改成功！！");
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
				
				guanyingayaEntity.setAddress(req.getParameter("address"));
				guanyingayaEntity.setLivingmenber(livingString);
				guanyingayaEntity.setMoney(Integer.valueOf(Integer.valueOf(req.getParameter("money"))));
				guanyingayaEntity.setPayway(req.getParameter("payway"));
				guanyingayaEntity.setSummary(req.getParameter("summary"));
				guanyingayaEntity.setPrayingobj(req.getParameter("prayingobj"));
				guanyingayaEntity.setPaymen(req.getParameter("paymen"));
				guanyingayaEntity.setBook(req.getParameter("book"));
				
				guanyingayaService.updateEntitie(guanyingayaEntity);
				model.addAttribute("message", "观音成道牌位修改成功！！");
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
			String currentyear = String.valueOf(year);
			
//			List<GuanyingayaEntity> guanyingayaEntityList = new ArrayList<GuanyingayaEntity>();
			String ids = "";
			String[] antoserial=new String[size.length];
			
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(currentTime);
			
			for(int i = 0;i < size.length;i ++){
				GuanyingayaEntity guanyingayaEntity = new GuanyingayaEntity();
				CriteriaQuery cq = new CriteriaQuery(GuanyingayaEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",size[i]),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<GuanyingayaEntity> guanyingayas = guanyingayaService.getListByCriteriaQuery(cq,false);
				
				guanyingayaEntity.setAddress(address[i]);
				guanyingayaEntity.setLivingmenber(livingmember[i]);
				guanyingayaEntity.setMoney(Integer.valueOf(money[i]));
				guanyingayaEntity.setPayway(payway[i]);
				guanyingayaEntity.setSummary(summary[i]);
				guanyingayaEntity.setPaymen(paymen);
				guanyingayaEntity.setPrayingobj(prayingobj[i]);
				guanyingayaEntity.setDoritualid(id0);
				guanyingayaEntity.setSize(size[i]);
				//换时间
				if(!size[i].equals("小")){
					guanyingayaEntity.setBook(book);
				}
				guanyingayaEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				guanyingayaEntity.setSerial(f.format(guanyingayas.size() + 1));
				guanyingayaEntity.setRegistertime(dateString);
//				guanyingayaEntityList.add(guanyingayaEntity);
				
				guanyingayaService.save(guanyingayaEntity);
				
				ControllerUtils.save2Pray(guanyingayaEntity, guanyingayaService);
				
				ids += guanyingayaEntity.getId() + ",";
				antoserial[i]=guanyingayaEntity.getSerial();
			}
//			req.setAttribute("guanyingayaEntityList", guanyingayaEntityList);
//			req.setAttribute("ids", ids);
//			return new ModelAndView("com/sys/guanyingaya/AutoSerialGuanyingaya");
			
//------------20180502 Kooking		
			return updateGuanyingayaAndReceipt(ids, antoserial, model);
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
				
//				List<GuanyingayaEntity> guanyingayaEntityList = new ArrayList<GuanyingayaEntity>();
				String ids = "";
				String[] antoserial=new String[1];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				GuanyingayaEntity guanyingayaEntity = new GuanyingayaEntity();
				CriteriaQuery cq = new CriteriaQuery(GuanyingayaEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",sizeflag),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<GuanyingayaEntity> guanyingayas = guanyingayaService.getListByCriteriaQuery(cq,false);
				
				String livingString = "";
				for(int i = 0;i < living.length;i ++){
					if(living[i].equals("") || living[i] == null){
						living[i] = " ";
					}
					livingString += living[i] + ";";
				}
				
				guanyingayaEntity.setAddress(address);
				guanyingayaEntity.setLivingmenber(livingString);
				guanyingayaEntity.setMoney(Integer.valueOf(money));
				guanyingayaEntity.setPayway(payway);
				guanyingayaEntity.setSummary(summary);
				guanyingayaEntity.setPrayingobj(prayingobj);
				guanyingayaEntity.setDoritualid(id0);
				guanyingayaEntity.setPaymen(paymen);
				guanyingayaEntity.setBook(book);
				guanyingayaEntity.setSize(sizeflag);
				guanyingayaEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				guanyingayaEntity.setSerial(f.format(guanyingayas.size() + 1));
				guanyingayaEntity.setRegistertime(dateString);
//				guanyingayaEntityList.add(guanyingayaEntity);
					
				guanyingayaService.save(guanyingayaEntity);
				
				ControllerUtils.save2Pray(guanyingayaEntity, guanyingayaService);
				
				ids += guanyingayaEntity.getId() + ",";
				antoserial[0]=guanyingayaEntity.getSerial();
				
//				req.setAttribute("guanyingayaEntityList", guanyingayaEntityList);
//				req.setAttribute("ids", ids);
//			    return new ModelAndView("com/sys/guanyingaya/AutoSerialGuanyingaya");
				
//------------20180502 Kooking		
				return updateGuanyingayaAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
		
	}
	

	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(GuanyingayaEntity guanyingayaEntity,HttpServletRequest request,Model model) {
		List<GuanyingayaEntity> guanyingayaEntitys = new ArrayList<GuanyingayaEntity>();
		if (StringUtil.isNotEmpty(guanyingayaEntity.getId())) {
			guanyingayaEntity = guanyingayaService.getEntity(GuanyingayaEntity.class, guanyingayaEntity.getId());
			
			TSUser user = ResourceUtil.getSessionUserName();
			if(!user.getRealName().equals(guanyingayaEntity.getRegistrant())){
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			if(guanyingayaEntity.getSize().equals("小")){
				guanyingayaEntitys.add(guanyingayaEntity);
				model.addAttribute("guanyingayaEntitys", guanyingayaEntitys);
				model.addAttribute("id", guanyingayaEntity.getDoritualid());
				model.addAttribute("guanyingayaid", guanyingayaEntity.getId());
				model.addAttribute("clientele", guanyingayaEntity.getPaymen());
				model.addAttribute("size", guanyingayaEntity.getSize());
				return new ModelAndView("com/sys/guanyingaya/editGuanyingaya");
			}
			else{
				
				String livingString = guanyingayaEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				guanyingayaEntitys.add(guanyingayaEntity);
				model.addAttribute("guanyingayaEntity", guanyingayaEntity);
				model.addAttribute("guanyingayaEntitys", guanyingayaEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", guanyingayaEntity.getDoritualid());
				model.addAttribute("size", guanyingayaEntity.getSize());
				model.addAttribute("clientele", guanyingayaEntity.getPaymen());
				model.addAttribute("guanyingayaid", guanyingayaEntity.getId());
				return new ModelAndView("com/sys/guanyingaya/editMiddleAndBigGuanyingaya");
			}
		}
		else{
			return new ModelAndView("com/sys/error");
		}
	}
}
