package com.sys.controller.goddessbirth;
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
import com.sys.entity.buddhagaya.BuddhagayaEntity;
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.goddessbirth.GoddessbirthEntity;
import com.sys.entity.guanyinopen.GuanyinopenEntity;
import com.sys.entity.livingmenber.LivingmenberEntity;
import com.sys.entity.pray.PrayEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.goddessbirth.GoddessbirthServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;
import com.sys.util.ControllerUtils;

/**   
 * @Title: Controller
 * @Description: 观音诞信息
 * @author zhangdaihao
 * @date 2016-03-02 22:35:25
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/goddessbirthController")
public class GoddessbirthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GoddessbirthController.class);

	@Autowired
	private GoddessbirthServiceI goddessbirthService;
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
	 * 观音诞信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "goddessbirth")
	public ModelAndView goddessbirth(HttpServletRequest request) {
		return new ModelAndView("com/sys/goddessbirth/goddessbirthList");
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
	public void datagrid(GoddessbirthEntity goddessbirth,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(GoddessbirthEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, goddessbirth, request.getParameterMap());
		this.goddessbirthService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除观音诞信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(GoddessbirthEntity goddessbirth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		goddessbirth = systemService.getEntity(GoddessbirthEntity.class, goddessbirth.getId());
		message = "观音诞信息删除成功";
		goddessbirthService.delete(goddessbirth);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加观音诞信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(GoddessbirthEntity goddessbirth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(goddessbirth.getId())) {
			message = "观音诞信息更新成功";
			GoddessbirthEntity t = goddessbirthService.get(GoddessbirthEntity.class, goddessbirth.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(goddessbirth, t);
				goddessbirthService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "观音诞信息更新失败";
			}
		} else {
			message = "观音诞信息添加成功";
			goddessbirthService.save(goddessbirth);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 观音诞信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(GoddessbirthEntity goddessbirth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(goddessbirth.getId())) {
			goddessbirth = goddessbirthService.getEntity(GoddessbirthEntity.class, goddessbirth.getId());
			req.setAttribute("goddessbirthPage", goddessbirth);
		}
		return new ModelAndView("com/sys/goddessbirth/goddessbirth");
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
			//查询今年的观音诞法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "观音诞");
			if(funeralhelds.size()!=1){
				model.addAttribute("message", "抱歉，今年的观音诞法事举行日期安排 登记不正常，请与系统管理员联系！");
				return new ModelAndView("com/sys/nofuneralheldplan");
			}
			
//			---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记			
			
		model.addAttribute("id0", id0);
		
		return new ModelAndView("com/sys/goddessbirth/selectSize");
	}
	
	/**
	 * 显示观音诞记录
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToShowGoddessbirthRecord")
	public ModelAndView redirectToShowGoddessbirthRecord(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");
//		String hql0 = "from GoddessbirthEntity where 1 = 1 AND doritualid = ? AND size = ?";
		String sql = "select * from pray where 1 = 1 AND doritualid = ? AND size = ?";//20180504
		if(id0 != null){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
//			List<GoddessbirthEntity> goddessbirthEntitys = goddessbirthService.findHql(hql0,id0,size);
			List<GoddessbirthEntity> goddessbirthEntitys = goddessbirthService
					.listBySQL(GoddessbirthEntity.class, sql, id0,size);
			model.addAttribute("doritualinfoEntity", doritualinfoEntity);
			model.addAttribute("goddessbirthEntitys", goddessbirthEntitys);
			model.addAttribute("id", id0);
			model.addAttribute("size", size);
		}
		
		return new ModelAndView("com/sys/goddessbirth/showGoddessbirthRecord");
	}
	
	/**
	 * 跳转到编辑登记观音诞
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToEditGoddessbirth")
	public ModelAndView redirectToEditGoddessbirth(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");
		if(size != null){
			try {
				size = URLDecoder.decode(size , "utf-8");
			} catch (UnsupportedEncodingException e) {
				
				e.printStackTrace();
			}
		}
		DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
		String goddessbirthIds = req.getParameter("goddessbirthIds");
		
		String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ? ";
		List<LivingmenberEntity> livingmenberEntityList = systemService.findHql(hql0,id0);
		
//		List<GoddessbirthEntity> goddessbirthEntitys = new ArrayList<GoddessbirthEntity>();
		List<PrayEntity> goddessbirthEntitys = new ArrayList<PrayEntity>();
		
		//查询今年的观音诞法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "观音诞");
		model.addAttribute("funeralheld", funeralhelds);		
		
		//添加做法事人的地址
		model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
		model.addAttribute("updateFlag", "0");
		
		if(size.equals("小")){
			if(goddessbirthIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("goddessbirthEntitys", goddessbirthEntitys);
				return new ModelAndView("com/sys/goddessbirth/editGoddessbirth");
			}
			
			
			
			//用逗号切开
			String goddessbirthIdsSplitByComma[] = goddessbirthIds.substring(0, goddessbirthIds.length()-1).split(",");
			
			PrayEntity guanyinopenEntity;
			
			//分别获取相关数据
			for(int i = 0;i < goddessbirthIdsSplitByComma.length;i ++){
//				GoddessbirthEntity guanyinopenEntity = goddessbirthService.get(GoddessbirthEntity.class, goddessbirthIdsSplitByComma[i]);
				guanyinopenEntity = goddessbirthService.get(PrayEntity.class, goddessbirthIdsSplitByComma[i]);
				goddessbirthEntitys.add(guanyinopenEntity);
			}
			
			
	
	
			model.addAttribute("goddessbirthEntitys", goddessbirthEntitys);
			model.addAttribute("livingmenberEntityList", livingmenberEntityList);
			model.addAttribute("id", id0);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			model.addAttribute("book", goddessbirthEntitys.get(0).getBook());
			
			return new ModelAndView("com/sys/goddessbirth/editGoddessbirth");
		}
		else{
			if(goddessbirthIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("goddessbirthEntitys", goddessbirthEntitys);
				model.addAttribute("size", size);
				return new ModelAndView("com/sys/goddessbirth/editMiddleAndBigGoddessbirth");
			}
			else{
//				GoddessbirthEntity goddessbirthEntity = goddessbirthService.get(GoddessbirthEntity.class, goddessbirthIds.substring(0, goddessbirthIds.length() - 1));
				PrayEntity goddessbirthEntity = goddessbirthService.get(PrayEntity.class, goddessbirthIds.substring(0, goddessbirthIds.length() - 1));
				
				String livingString = goddessbirthEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				goddessbirthEntitys.add(goddessbirthEntity);
				model.addAttribute("goddessbirthEntity", goddessbirthEntity);
				model.addAttribute("goddessbirthEntitys", goddessbirthEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", id0);
				model.addAttribute("size", size);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				return new ModelAndView("com/sys/goddessbirth/editMiddleAndBigGoddessbirth");
			}
		}
	}

	/**
	 * 保存编辑后的观音诞记录和收据
	 * @param req
	 * @param model
	 * @return
	 */
	/*@RequestMapping(params = "updateGoddessbirthAndReceipt")
	public ModelAndView updateGoddessbirthAndReceipt(HttpServletRequest req,Model model) {*/
	private ModelAndView updateGoddessbirthAndReceipt(String ids,String[] antoserial,Model model) {
		
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
		GoddessbirthEntity entity = goddessbirthService.get(
				GoddessbirthEntity.class, id[0]);
//		StringBuilder stb= new StringBuilder("").append(entity.getPaymen()).append("交来农历 ");
		StringBuilder stb= new StringBuilder("");
		String sumSummary ="";
		//查询今年的观音诞法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "观音诞");
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
		
		/*if(funeralheldEntity.getHoldDate().equals(funeralheldEntity.getEndDate())){
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
			GoddessbirthEntity te = goddessbirthService.get(GoddessbirthEntity.class, id[i]);
			te.setAutoserial(antoserial[i]);
			te.setRegistertime(dateString);
			te.setRegistrant(user.getRealName());
			te.setReceiptno(re.getNo());
			goddessbirthService.updateEntitie(te);
			
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
		re.setRegistrant(user.getRealName());
		re.setAddress(entity.getAddress());
		re.setDoritualid(entity.getDoritualid());
		re.setMoney(sum);
		re.setSummary(sumSummary);
		re.setRemark(res);
		re.setSize(entity.getSize());
		re.setObj(obj);
		re.setDate("二#十九#二#廿七");
		//保存收据

		
		
		re.setRitualtype("观音诞");
		re.setRegistertime(dateString);
		re.setPurpose("观音诞");
		re.setFlag(0);
		receiptService.save(re);
		String receipId = re.getId();

		for(int i = 0;i < id.length;i ++){
			GoddessbirthEntity te = goddessbirthService.get(GoddessbirthEntity.class, id[i]);
			te.setReceiptid(receipId);
			goddessbirthService.updateEntitie(te);
		}
		
		
		model.addAttribute("message", "观音诞登记成功");

		model.addAttribute("ritualtype", "观音诞");
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
		String goddessbirthid = req.getParameter("goddessbirthid");
		if(goddessbirthid != null && !goddessbirthid.equals("")){
			GoddessbirthEntity goddessbirthEntity = goddessbirthService.get(GoddessbirthEntity.class, goddessbirthid);
			if(goddessbirthEntity.getSize().equals("小")){
				goddessbirthEntity.setAddress(req.getParameter("address"));
				goddessbirthEntity.setLivingmenber(req.getParameter("livingmenber"));
				goddessbirthEntity.setMoney(Integer.valueOf(req.getParameter("money")));
				goddessbirthEntity.setPayway(req.getParameter("payway"));
				goddessbirthEntity.setSummary(req.getParameter("summary"));
				goddessbirthEntity.setPrayingobj(req.getParameter("prayingobj"));
				goddessbirthEntity.setPaymen(req.getParameter("paymen"));
				goddessbirthService.updateEntitie(goddessbirthEntity);
				model.addAttribute("message", "观音诞牌位修改成功！！");
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
				
				goddessbirthEntity.setAddress(req.getParameter("address"));
				goddessbirthEntity.setLivingmenber(livingString);
				goddessbirthEntity.setMoney(Integer.valueOf(Integer.valueOf(req.getParameter("money"))));
				goddessbirthEntity.setPayway(req.getParameter("payway"));
				goddessbirthEntity.setSummary(req.getParameter("summary"));
				goddessbirthEntity.setPrayingobj(req.getParameter("prayingobj"));
				goddessbirthEntity.setPaymen(req.getParameter("paymen"));
				goddessbirthEntity.setBook(req.getParameter("book"));
				
				goddessbirthService.updateEntitie(goddessbirthEntity);
				model.addAttribute("message", "观音诞牌位修改成功！！");
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
				
//				List<GoddessbirthEntity> goddessbirthEntityList = new ArrayList<GoddessbirthEntity>();
				String ids = "";
				String[] antoserial=new String[size.length];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				for(int i = 0;i < size.length;i ++){
					GoddessbirthEntity goddessbirthEntity = new GoddessbirthEntity();
					
					
					CriteriaQuery cq = new CriteriaQuery(GoddessbirthEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("size",size[i]),Restrictions.like("registertime",currentyear,MatchMode.START)));
					List<GoddessbirthEntity> goddessbirths = goddessbirthService.getListByCriteriaQuery(cq,false);
					
					goddessbirthEntity.setAddress(address[i]);
					goddessbirthEntity.setLivingmenber(livingmember[i]);
					goddessbirthEntity.setMoney(Integer.valueOf(money[i]));
					goddessbirthEntity.setPayway(payway[i]);
					goddessbirthEntity.setSummary(summary[i]);
					goddessbirthEntity.setPrayingobj(prayingobj[i]);
					goddessbirthEntity.setDoritualid(id0);
					goddessbirthEntity.setSize(size[i]);
					goddessbirthEntity.setPaymen(paymen);
					if(!size[i].equals("小")){
						goddessbirthEntity.setBook(book);
					}
					//换时间
					goddessbirthEntity.setFlag(0);
					NumberFormat f=new DecimalFormat("000000");
					goddessbirthEntity.setSerial(f.format(goddessbirths.size() + 1));
					goddessbirthEntity.setRegistertime(dateString);
//					goddessbirthEntityList.add(goddessbirthEntity);
					
					goddessbirthService.save(goddessbirthEntity);
					
					ControllerUtils.save2Pray(goddessbirthEntity, goddessbirthService);
					
					ids += goddessbirthEntity.getId() + ",";
					antoserial[i]=goddessbirthEntity.getSerial();
				}
//				req.setAttribute("goddessbirthEntityList", goddessbirthEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView("com/sys/goddessbirth/AutoSerialGoddessbirth");
				
//------------20180502 Kooking		
				return updateGoddessbirthAndReceipt(ids, antoserial, model);
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
				
//				List<GoddessbirthEntity> goddessbirthEntityList = new ArrayList<GoddessbirthEntity>();
				String ids = "";
				String[] antoserial=new String[1];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				GoddessbirthEntity goddessbirthEntity = new GoddessbirthEntity();
				PrayEntity prayEntity =new PrayEntity();
				
				CriteriaQuery cq = new CriteriaQuery(GoddessbirthEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",sizeflag),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<GoddessbirthEntity> goddessbirths = goddessbirthService.getListByCriteriaQuery(cq,false);
				
				String livingString = "";
				for(int i = 0;i < living.length;i ++){
					if(living[i].equals("") || living[i] == null){
						living[i] = " ";
					}
					livingString += living[i] + ";";
				}
				
				goddessbirthEntity.setAddress(address);
				goddessbirthEntity.setLivingmenber(livingString);
				goddessbirthEntity.setMoney(Integer.valueOf(money));
				goddessbirthEntity.setPayway(payway);
				goddessbirthEntity.setSummary(summary);
				goddessbirthEntity.setPrayingobj(prayingobj);
				goddessbirthEntity.setDoritualid(id0);
				goddessbirthEntity.setPaymen(paymen);
				goddessbirthEntity.setBook(book);
				goddessbirthEntity.setSize(sizeflag);
				goddessbirthEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				goddessbirthEntity.setSerial(f.format(goddessbirths.size() + 1));
				goddessbirthEntity.setRegistertime(dateString);
//				goddessbirthEntityList.add(goddessbirthEntity);
					
				goddessbirthService.save(goddessbirthEntity);
				
				ControllerUtils.save2Pray(goddessbirthEntity, goddessbirthService);
				
				ids += goddessbirthEntity.getId() + ",";
				antoserial[0]=goddessbirthEntity.getSerial();
				
//				req.setAttribute("goddessbirthEntityList", goddessbirthEntityList);
//				req.setAttribute("ids", ids);
//			    return new ModelAndView("com/sys/goddessbirth/AutoSerialGoddessbirth");
				
//------------20180502 Kooking		
				return updateGoddessbirthAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
			}
		}catch(Exception ex){
				ex.printStackTrace();
				return new ModelAndView("com/sys/error");
			}
		
	}
	

	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(GoddessbirthEntity goddessbirthEntity,HttpServletRequest request,Model model) {
		List<GoddessbirthEntity> goddessbirthEntitys = new ArrayList<GoddessbirthEntity>();
		if (StringUtil.isNotEmpty(goddessbirthEntity.getId())) {
			goddessbirthEntity = goddessbirthService.getEntity(GoddessbirthEntity.class, goddessbirthEntity.getId());
			
			TSUser user = ResourceUtil.getSessionUserName();
			if(!user.getRealName().equals(goddessbirthEntity.getRegistrant())){
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			if(goddessbirthEntity.getSize().equals("小")){
				goddessbirthEntitys.add(goddessbirthEntity);
				model.addAttribute("goddessbirthEntitys", goddessbirthEntitys);
				model.addAttribute("id", goddessbirthEntity.getDoritualid());
				model.addAttribute("goddessbirthid", goddessbirthEntity.getId());
				model.addAttribute("clientele", goddessbirthEntity.getPaymen());
				model.addAttribute("size", goddessbirthEntity.getSize());
				return new ModelAndView("com/sys/goddessbirth/editGoddessbirth");
			}
			else{
				
				String livingString = goddessbirthEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				goddessbirthEntitys.add(goddessbirthEntity);
				model.addAttribute("goddessbirthEntity", goddessbirthEntity);
				model.addAttribute("goddessbirthEntitys", goddessbirthEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", goddessbirthEntity.getDoritualid());
				model.addAttribute("size", goddessbirthEntity.getSize());
				model.addAttribute("clientele", goddessbirthEntity.getPaymen());
				model.addAttribute("goddessbirthid", goddessbirthEntity.getId());
				return new ModelAndView("com/sys/goddessbirth/editMiddleAndBigGoddessbirth");
				
			}
		}
		else{
			return new ModelAndView("com/sys/error");
		}
	}
}
