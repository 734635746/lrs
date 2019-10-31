package com.sys.controller.guanyinopen;
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
import com.sys.entity.ghostfes.GhostfesEntity;
import com.sys.entity.guanyinopen.GuanyinopenEntity;
import com.sys.entity.livingmenber.LivingmenberEntity;
import com.sys.entity.pray.PrayEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.guanyinopen.GuanyinopenServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;
import com.sys.util.ControllerUtils;

/**   
 * @Title: Controller
 * @Description: 观音开库
 * @author zhangdaihao
 * @date 2016-03-02 21:30:18
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/guanyinopenController")
public class GuanyinopenController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(GuanyinopenController.class);

	@Autowired
	private GuanyinopenServiceI guanyinopenService;
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
	 * 观音开库列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "guanyinopen")
	public ModelAndView guanyinopen(HttpServletRequest request) {
		return new ModelAndView("com/sys/guanyinopen/guanyinopenList");
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
	public void datagrid(GuanyinopenEntity guanyinopen,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(GuanyinopenEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, guanyinopen, request.getParameterMap());
		this.guanyinopenService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除观音开库
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(GuanyinopenEntity guanyinopen, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		guanyinopen = systemService.getEntity(GuanyinopenEntity.class, guanyinopen.getId());
		message = "观音开库删除成功";
		guanyinopenService.delete(guanyinopen);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加观音开库
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(GuanyinopenEntity guanyinopen, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(guanyinopen.getId())) {
			message = "观音开库更新成功";
			GuanyinopenEntity t = guanyinopenService.get(GuanyinopenEntity.class, guanyinopen.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(guanyinopen, t);
				guanyinopenService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "观音开库更新失败";
			}
		} else {
			message = "观音开库添加成功";
			guanyinopenService.save(guanyinopen);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 观音开库列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(GuanyinopenEntity guanyinopen, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(guanyinopen.getId())) {
			guanyinopen = guanyinopenService.getEntity(GuanyinopenEntity.class, guanyinopen.getId());
			req.setAttribute("guanyinopenPage", guanyinopen);
		}
		return new ModelAndView("com/sys/guanyinopen/guanyinopen");
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
						.getBytes("utf-8"), "utf-8"));
				doritualinfo.setAddress(new String(doritualinfo.getAddress()
						.getBytes("utf-8"), "utf-8"));
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
			//获取今年的观音开库法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "观音开库");
			if(funeralhelds.size()!=1){
				model.addAttribute("message", "抱歉，今年的观音开库法事举行日期安排 登记不正常，请与系统管理员联系！");
				return new ModelAndView("com/sys/nofuneralheldplan");
			}
			
//			---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记			
			
		model.addAttribute("id0", id0);
		
		return new ModelAndView("com/sys/guanyinopen/selectSize");
	}
	
	/**
	 * 显示观音开库记录
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToShowGuanyinopenRecord")
	public ModelAndView redirectToShowGuanyinopenRecord(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");
//		String hql0 = "from GuanyinopenEntity where 1 = 1 AND doritualid = ? AND size = ?";
		String sql = "select * from pray where 1 = 1 AND doritualid = ? AND size = ?";//20180504
		if(id0 != null){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
//			List<GuanyinopenEntity> guanyinopenEntitys = guanyinopenService.findHql(hql0,id0,size);
			List<GuanyinopenEntity> guanyinopenEntitys = guanyinopenService
					.listBySQL(GuanyinopenEntity.class, sql, id0,size);
			model.addAttribute("doritualinfoEntity", doritualinfoEntity);
			model.addAttribute("guanyinopenEntitys", guanyinopenEntitys);
			model.addAttribute("size", size);
			model.addAttribute("id", id0);
		}
		
		return new ModelAndView("com/sys/guanyinopen/showGuanyinopenRecord");
	}
	
	/**
	 * 跳转到编辑登记观音开库
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToEditGuanyinopen")
	public ModelAndView redirectToEditGuanyinopen(HttpServletRequest req,Model model) {
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
		String guanyinopenIds = req.getParameter("guanyinopenIds");
		
		String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ? ";
		List<LivingmenberEntity> livingmenberEntityList = systemService.findHql(hql0,id0);
		
//		List<GuanyinopenEntity> guanyinopenEntitys = new ArrayList<GuanyinopenEntity>();
		List<PrayEntity> guanyinopenEntitys = new ArrayList<PrayEntity>();
		
		//获取今年的观音开库法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "观音开库");
		model.addAttribute("funeralheld", funeralhelds);			
		
		//添加做法事人的地址
		model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
		model.addAttribute("updateFlag", "0");		
		
		if(size.equals("小")){
			if(guanyinopenIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("guanyinopenEntitys", guanyinopenEntitys);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				return new ModelAndView("com/sys/guanyinopen/editGuanyinopen");
			}
			
			
			
			//用逗号切开
			String guanyinopenIdsSplitByComma[] = guanyinopenIds.substring(0, guanyinopenIds.length()-1).split(",");
			
			//分别获取相关数据
			for(int i = 0;i < guanyinopenIdsSplitByComma.length;i ++){
//				GuanyinopenEntity guanyinopenEntity = guanyinopenService.get(GuanyinopenEntity.class, guanyinopenIdsSplitByComma[i]);
				PrayEntity guanyinopenEntity = guanyinopenService.get(PrayEntity.class, guanyinopenIdsSplitByComma[i]);
				guanyinopenEntitys.add(guanyinopenEntity);
			}
			
			
	
	
			model.addAttribute("guanyinopenEntitys", guanyinopenEntitys);
			model.addAttribute("book", guanyinopenEntitys.get(0).getBook());
			model.addAttribute("livingmenberEntityList", livingmenberEntityList);
			model.addAttribute("id", id0);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			
			return new ModelAndView("com/sys/guanyinopen/editGuanyinopen");
		}
		else{
			if(guanyinopenIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("guanyinopenEntitys", guanyinopenEntitys);
				model.addAttribute("size", size);
				return new ModelAndView("com/sys/guanyinopen/editMiddleAndBigGuanyinopen");
			}
			else{
				//已经有数据
//				GuanyinopenEntity guanyinopenEntity = guanyinopenService.get(GuanyinopenEntity.class, guanyinopenIds.substring(0, guanyinopenIds.length() - 1));
				PrayEntity guanyinopenEntity = guanyinopenService.get(PrayEntity.class, guanyinopenIds.substring(0, guanyinopenIds.length() - 1));
				
				String livingString = guanyinopenEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				guanyinopenEntitys.add(guanyinopenEntity);
				model.addAttribute("guanyinopenEntity", guanyinopenEntity);
				model.addAttribute("guanyinopenEntitys", guanyinopenEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", id0);
				model.addAttribute("size", size);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				return new ModelAndView("com/sys/guanyinopen/editMiddleAndBigGuanyinopen");
			}
		}
	}
	
	/**
	 * 保存编辑后的药师诞记录和收据
	 * @param req
	 * @param model
	 * @return
	 */
	/*@RequestMapping(params = "updateGuanyinopenAndReceipt")
	public ModelAndView updateGuanyinopenAndReceipt(HttpServletRequest req,Model model) {*/
	private ModelAndView updateGuanyinopenAndReceipt(String ids,String[] antoserial,Model model) {
		
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
		GuanyinopenEntity entity = guanyinopenService.get(
				GuanyinopenEntity.class, id[0]);
//		StringBuilder stb= new StringBuilder("").append(entity.getPaymen()).append("交来农历 ");
		StringBuilder stb= new StringBuilder("");
		String sumSummary ="";
		
		//获取今年的观音开库法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "观音开库");
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
		
/*		if (funeralheldEntity.getHoldDate().equals(
				funeralheldEntity.getEndDate())) {
			stb.append(funeralheldEntity.getEndDate());
		} else {
			stb.append(funeralheldEntity.getHoldDate() + "至"
					+ funeralheldEntity.getEndDate());
		}
		stb.append(funeralheldEntity.getRitualtype());
		boolean flag = true;// 标志是否是小牌

		if (entity.getSize().equals("大")) {
			stb.append("大牌");
			flag = false;
		} else if (entity.getSize().equals("拈香")) {
			stb.append("拈香");
			flag = false;
		}
		stb.append("功德款</br>");

		String sumSummary = stb.toString();*/
		String res="";
		/*修改打印摘要内容*/
		
		TSUser user = ResourceUtil.getSessionUserName();
		
		for(int i = 0;i < id.length;i ++){
			GuanyinopenEntity te = guanyinopenService.get(GuanyinopenEntity.class, id[i]);
			te.setAutoserial(antoserial[i]);
			te.setRegistertime(dateString);
			te.setRegistrant(user.getRealName());
			te.setReceiptno(re.getNo());
			guanyinopenService.updateEntitie(te);
			
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
		re.setRegistrant(user.getRealName());
		re.setAddress(entity.getAddress());
		re.setSize(entity.getSize());
		re.setDoritualid(entity.getDoritualid());
		re.setMoney(sum);
		re.setSummary(sumSummary);
		re.setRemark(res);
		re.setObj(obj);
		re.setDate("二#十九#二#廿七");
		//保存收据

		
		
		re.setRitualtype("观音开库");
		re.setRegistertime(dateString);
		re.setPurpose("观音开库");
		re.setFlag(0);
		receiptService.save(re);
		String receipId = re.getId();

		for(int i = 0;i < id.length;i ++){
			GuanyinopenEntity te = guanyinopenService.get(GuanyinopenEntity.class, id[i]);
			te.setReceiptid(receipId);
			guanyinopenService.updateEntitie(te);
		}
		
		
		model.addAttribute("message", "观音开库登记成功");
		model.addAttribute("ritualtype", "观音开库");
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
		
		String guanyinopenid = req.getParameter("guanyinopenid");
		if(guanyinopenid != null && !guanyinopenid.equals("")){
			GuanyinopenEntity guanyinopenEntity = guanyinopenService.get(GuanyinopenEntity.class, guanyinopenid);
			if(guanyinopenEntity.getSize().equals("小")){
				guanyinopenEntity.setAddress(req.getParameter("address"));
				guanyinopenEntity.setLivingmenber(req.getParameter("livingmenber"));
				guanyinopenEntity.setMoney(Integer.valueOf(req.getParameter("money")));
				guanyinopenEntity.setPayway(req.getParameter("payway"));
				guanyinopenEntity.setSummary(req.getParameter("summary"));
				guanyinopenEntity.setPrayingobj(req.getParameter("prayingobj"));
				guanyinopenEntity.setPaymen(req.getParameter("paymen"));
				guanyinopenService.updateEntitie(guanyinopenEntity);
				model.addAttribute("message", "观音开库牌位修改成功！！");
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
				
				guanyinopenEntity.setAddress(req.getParameter("address"));
				guanyinopenEntity.setLivingmenber(livingString);
				guanyinopenEntity.setMoney(Integer.valueOf(Integer.valueOf(req.getParameter("money"))));
				guanyinopenEntity.setPayway(req.getParameter("payway"));
				guanyinopenEntity.setSummary(req.getParameter("summary"));
				guanyinopenEntity.setPrayingobj(req.getParameter("prayingobj"));
				guanyinopenEntity.setPaymen(req.getParameter("paymen"));
				guanyinopenEntity.setBook(req.getParameter("book"));
				
				guanyinopenService.updateEntitie(guanyinopenEntity);
				model.addAttribute("message", "观音开库牌位修改成功！！");
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
			
//			List<GuanyinopenEntity> guanyinopenEntityList = new ArrayList<GuanyinopenEntity>();
			String ids = "";
			String[] antoserial=new String[size.length];
			
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(currentTime);
			
			for(int i = 0;i < size.length;i ++){
				GuanyinopenEntity guanyinopenEntity = new GuanyinopenEntity();
				CriteriaQuery cq = new CriteriaQuery(GuanyinopenEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",size[i]),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<GuanyinopenEntity> guanyinopens = guanyinopenService.getListByCriteriaQuery(cq,false);
				
				guanyinopenEntity.setAddress(address[i]);
				guanyinopenEntity.setLivingmenber(livingmember[i]);
				guanyinopenEntity.setMoney(Integer.valueOf(money[i]));
				guanyinopenEntity.setPayway(payway[i]);
				guanyinopenEntity.setSummary(summary[i]);
				guanyinopenEntity.setPrayingobj(prayingobj[i]);
				guanyinopenEntity.setDoritualid(id0);
				guanyinopenEntity.setSize(size[i]);
				guanyinopenEntity.setPaymen(paymen);
				if(!size[i].equals("小")){
					guanyinopenEntity.setBook(book);
				}
				//换时间
				guanyinopenEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				guanyinopenEntity.setSerial(f.format(guanyinopens.size() + 1));
				guanyinopenEntity.setRegistertime(dateString);
//				guanyinopenEntityList.add(guanyinopenEntity);
				
				guanyinopenService.save(guanyinopenEntity);
				
				ControllerUtils.save2Pray(guanyinopenEntity, guanyinopenService);
				
				ids += guanyinopenEntity.getId() + ",";
				antoserial[i]=guanyinopenEntity.getSerial();
			}
//			req.setAttribute("guanyinopenEntityList", guanyinopenEntityList);
//			req.setAttribute("ids", ids);
//			return new ModelAndView("com/sys/guanyinopen/AutoSerialGuanyinopen");
			
//------------20180502 Kooking		
			return updateGuanyinopenAndReceipt(ids, antoserial, model);
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
				
//				List<GuanyinopenEntity> guanyinopenEntityList = new ArrayList<GuanyinopenEntity>();
				String ids = "";
				String[] antoserial=new String[1];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				GuanyinopenEntity guanyinopenEntity = new GuanyinopenEntity();
				
				CriteriaQuery cq = new CriteriaQuery(GuanyinopenEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",sizeflag),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<GuanyinopenEntity> guanyinopens = guanyinopenService.getListByCriteriaQuery(cq,false);
				
				String livingString = "";
				for(int i = 0;i < living.length;i ++){
					if(living[i].equals("") || living[i] == null){
						living[i] = " ";
					}
					livingString += living[i] + ";";
				}
				
				guanyinopenEntity.setAddress(address);
				guanyinopenEntity.setLivingmenber(livingString);
				guanyinopenEntity.setMoney(Integer.valueOf(money));
				guanyinopenEntity.setPayway(payway);
				guanyinopenEntity.setSummary(summary);
				guanyinopenEntity.setPrayingobj(prayingobj);
				guanyinopenEntity.setDoritualid(id0);
				guanyinopenEntity.setPaymen(paymen);
				guanyinopenEntity.setBook(book);
				guanyinopenEntity.setSize(sizeflag);
				guanyinopenEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				guanyinopenEntity.setSerial(f.format(guanyinopens.size() + 1));
				guanyinopenEntity.setRegistertime(dateString);
//				guanyinopenEntityList.add(guanyinopenEntity);
					
				guanyinopenService.save(guanyinopenEntity);
				
				ControllerUtils.save2Pray(guanyinopenEntity, guanyinopenService);
				
				ids += guanyinopenEntity.getId() + ",";
				antoserial[0]=guanyinopenEntity.getSerial();
				
//				req.setAttribute("guanyinopenEntityList", guanyinopenEntityList);
//				req.setAttribute("ids", ids);
//				 return new ModelAndView("com/sys/guanyinopen/AutoSerialGuanyinopen");
				
//------------20180502 Kooking		
				return updateGuanyinopenAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
			
		
	}
	
	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(GuanyinopenEntity guanyinopenEntity,HttpServletRequest request,Model model) {
		List<GuanyinopenEntity> guanyinopenEntitys = new ArrayList<GuanyinopenEntity>();
		if (StringUtil.isNotEmpty(guanyinopenEntity.getId())) {
			guanyinopenEntity = guanyinopenService.getEntity(GuanyinopenEntity.class, guanyinopenEntity.getId());
			
			TSUser user = ResourceUtil.getSessionUserName();
			if(!user.getRealName().equals(guanyinopenEntity.getRegistrant())){
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			if(guanyinopenEntity.getSize().equals("小")){
				guanyinopenEntitys.add(guanyinopenEntity);
				model.addAttribute("guanyinopenEntitys", guanyinopenEntitys);
				model.addAttribute("id", guanyinopenEntity.getDoritualid());
				model.addAttribute("guanyinopenid", guanyinopenEntity.getId());
				model.addAttribute("clientele", guanyinopenEntity.getPaymen());
				model.addAttribute("size", guanyinopenEntity.getSize());
				return new ModelAndView("com/sys/guanyinopen/editGuanyinopen");
			}
			else{
				
				String livingString = guanyinopenEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				guanyinopenEntitys.add(guanyinopenEntity);
				model.addAttribute("guanyinopenEntity", guanyinopenEntity);
				model.addAttribute("guanyinopenEntitys", guanyinopenEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", guanyinopenEntity.getDoritualid());
				model.addAttribute("size", guanyinopenEntity.getSize());
				model.addAttribute("clientele", guanyinopenEntity.getPaymen());
				model.addAttribute("guanyinopenid", guanyinopenEntity.getId());
				return new ModelAndView("com/sys/guanyinopen/editMiddleAndBigGuanyinopen");
			}
		}
		else{
			return new ModelAndView("com/sys/error");
		}
	}
	
	
}
