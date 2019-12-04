package com.sys.controller.buddhabirth;

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
import com.sys.entity.guanyinopen.GuanyinopenEntity;
import com.sys.entity.livingmenber.LivingmenberEntity;
import com.sys.entity.pharmacistbirth.PharmacistbirthEntity;
import com.sys.entity.pray.PrayEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.entity.tombsweepfes.TombsweepfesEntity;
import com.sys.service.buddhabirth.BuddhabirthServiceI;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;
import com.sys.util.ControllerUtils;

/**
 * @Title: Controller
 * @Description: 弥勒佛诞登记
 * @author zhangdaihao
 * @date 2016-03-02 20:35:00
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/buddhabirthController")
public class BuddhabirthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(BuddhabirthController.class);

	@Autowired
	private BuddhabirthServiceI buddhabirthService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private DoritualinfoServiceI doritualinfoService;
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
	 * 弥勒佛诞登记列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "buddhabirth")
	public ModelAndView buddhabirth(HttpServletRequest request) {
		return new ModelAndView("com/sys/buddhabirth/buddhabirthList");
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
	public void datagrid(BuddhabirthEntity buddhabirth,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(BuddhabirthEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				buddhabirth, request.getParameterMap());
		this.buddhabirthService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除弥勒佛诞登记
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(BuddhabirthEntity buddhabirth,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		buddhabirth = systemService.getEntity(BuddhabirthEntity.class,
				buddhabirth.getId());
		message = "弥勒佛诞登记删除成功";
		buddhabirthService.delete(buddhabirth);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加弥勒佛诞登记
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(BuddhabirthEntity buddhabirth,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(buddhabirth.getId())) {
			message = "弥勒佛诞登记更新成功";
			BuddhabirthEntity t = buddhabirthService.get(
					BuddhabirthEntity.class, buddhabirth.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(buddhabirth, t);
				buddhabirthService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "弥勒佛诞登记更新失败";
			}
		} else {
			message = "弥勒佛诞登记添加成功";
			buddhabirthService.save(buddhabirth);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 弥勒佛诞登记列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(BuddhabirthEntity buddhabirth,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(buddhabirth.getId())) {
			buddhabirth = buddhabirthService.getEntity(BuddhabirthEntity.class,
					buddhabirth.getId());
			req.setAttribute("buddhabirthPage", buddhabirth);
		}
		return new ModelAndView("com/sys/buddhabirth/buddhabirth");
	}

	/**
	 * 选择牌位大小
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToSelectSize")
	// 10月30号修改
	public ModelAndView redirectToSelectSize(DoritualinfoEntity doritualinfo, HttpServletRequest req, Model model) {
		String id0 = req.getParameter("id");

		// --kooking 20180330
		try {
			doritualinfo.setRname(new String(doritualinfo.getRname().getBytes(
					"iso8859-1"), "utf-8"));
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
		// --kooking 20180330

//		---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记
		//查询今年的弥勒佛诞法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "弥勒佛诞");
		if(funeralhelds.size()!=1){
			model.addAttribute("message", "抱歉，今年的弥勒佛诞法事举行日期安排 登记不正常，请与系统管理员联系！");
			return new ModelAndView("com/sys/nofuneralheldplan");
		}
		
//		---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记		
		
		model.addAttribute("id0", id0);

		return new ModelAndView("com/sys/buddhabirth/selectSize");
	}

	/**
	 * 显示弥勒佛诞记录
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToShowBuddhabirthRecord")
	public ModelAndView redirectToShowBuddhabirthRecord(HttpServletRequest req,
			Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");
//		String hql0 = "from BuddhabirthEntity where 1 = 1 AND doritualid = ? AND size = ?"; // 10月30号修改
		String sql = "select * from pray where 1 = 1 AND doritualid = ? AND size = ?";//20180504
		if (id0 != null) {
			DoritualinfoEntity doritualinfoEntity = doritualinfoService
					.getEntity(DoritualinfoEntity.class, id0);
//			List<BuddhabirthEntity> buddhabirthEntitys = buddhabirthService.findHql(hql0,id0,size);
			List<BuddhabirthEntity> buddhabirthEntitys = buddhabirthService
					.listBySQL(BuddhabirthEntity.class,  sql, id0,size);
			model.addAttribute("doritualinfoEntity", doritualinfoEntity);
			model.addAttribute("buddhabirthEntitys", buddhabirthEntitys);
			model.addAttribute("id", id0);
			model.addAttribute("size", size);
		}

		return new ModelAndView("com/sys/buddhabirth/showBuddhabirthRecord");
	}

	/**
	 * 跳转到编辑登记弥勒佛诞
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToEditBuddhabirth")
	public ModelAndView redirectToEditBuddhabirth(HttpServletRequest req,
			Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");
		if (size != null) {
			try {
				size = URLDecoder.decode(size, "utf-8");
			} catch (UnsupportedEncodingException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(
				DoritualinfoEntity.class, id0);
		String buddhabirthIds = req.getParameter("buddhabirthIds");

		String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ? ";
		List<LivingmenberEntity> livingmenberEntityList = systemService
				.findHql(hql0, id0);

//		List<BuddhabirthEntity> buddhabirthEntitys = new ArrayList<BuddhabirthEntity>();
		List<PrayEntity> buddhabirthEntitys = new ArrayList<PrayEntity>();
		
		//查询今年的弥勒佛诞法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
						.getThisYearFuneralheldsByRitualtype(systemService, "弥勒佛诞");
		model.addAttribute("funeralheld", funeralhelds);		
		
		//添加做法事人的地址
		model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
		model.addAttribute("updateFlag", "0");
		
		if (size.equals("小")) {
			if (buddhabirthIds == null) {
				model.addAttribute("livingmenberEntityList",
						livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("buddhabirthEntitys", buddhabirthEntitys);
				return new ModelAndView("com/sys/buddhabirth/editBuddhabirth");
			}

			// 用逗号切开
			String buddhabirthIdsSplitByComma[] = buddhabirthIds.substring(0,
					buddhabirthIds.length() - 1).split(",");

			PrayEntity pharmacistbirthEntity;
			
			// 分别获取相关数据
			for (int i = 0; i < buddhabirthIdsSplitByComma.length; i++) {
				/*BuddhabirthEntity pharmacistbirthEntity = buddhabirthService
						.get(BuddhabirthEntity.class,
								buddhabirthIdsSplitByComma[i]);*/
				pharmacistbirthEntity = buddhabirthService
						.get(PrayEntity.class,
								buddhabirthIdsSplitByComma[i]);
				buddhabirthEntitys.add(pharmacistbirthEntity);
			}
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			model.addAttribute("buddhabirthEntitys", buddhabirthEntitys);
			model.addAttribute("livingmenberEntityList", livingmenberEntityList);
			model.addAttribute("book", buddhabirthEntitys.get(0).getBook());
			model.addAttribute("id", id0);

			return new ModelAndView("com/sys/buddhabirth/editBuddhabirth");
		} else { // 中大牌跳转
			if (buddhabirthIds == null) {
				model.addAttribute("livingmenberEntityList",
						livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("buddhabirthEntitys", buddhabirthEntitys);
				model.addAttribute("size", size);
				return new ModelAndView(
						"com/sys/buddhabirth/editMiddleAndBigBuddhabirth");
			} else {
				// 已经有数据
				/*BuddhabirthEntity buddhabirthEntity = buddhabirthService.get(
						BuddhabirthEntity.class, buddhabirthIds.substring(0,
								buddhabirthIds.length() - 1));*/
				PrayEntity buddhabirthEntity = buddhabirthService.get(
						PrayEntity.class, buddhabirthIds.substring(0,
								buddhabirthIds.length() - 1));

				String livingString = buddhabirthEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for (int i = 0; i < livingStringBySemicolon.length; i++) {

					livingList.add(livingStringBySemicolon[i]);
				}
				buddhabirthEntitys.add(buddhabirthEntity);
				model.addAttribute("buddhabirthEntity", buddhabirthEntity);
				model.addAttribute("buddhabirthEntitys", buddhabirthEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", id0);
				model.addAttribute("size", size);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				return new ModelAndView(
						"com/sys/buddhabirth/editMiddleAndBigBuddhabirth");
			}

		}
	}

	/**
	 * 保存编辑后的药师诞记录和收据
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	/*@RequestMapping(params = "updateBuddhabirthAndReceipt")
	public ModelAndView updateBuddhabirthAndReceipt(HttpServletRequest req,
			Model model) {*/
	private ModelAndView updateBuddhabirthAndReceipt(String ids,String[] antoserial,
				Model model) {

		resolver(ids,antoserial, model);

		return new ModelAndView("com/sys/success");
	}

	@RequestMapping(params = "print-html")
	public ModelAndView updateBuddhabirthAndReceipt2(HttpServletRequest req,
			Model model) {

//		resolver(req, model);

		return new ModelAndView("com/sys/print-html");
	}

	private void resolver(String ids,String[] antoserial, Model model) {
		// 获取弥勒佛诞记录的ID
//		String ids = req.getParameter("ids");
		String subids = ids.substring(0, ids.length() - 1);
		String[] id = subids.split(",");

		// 获取自动编号
//		String[] antoserial = req.getParameterValues("autoserial");

		ReceiptEntity re = new ReceiptEntity();

		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String dateString = formatter.format(currentTime);

		Calendar a = Calendar.getInstance();
		ReceiptnoEntity reNo = receiptnoService.findByProperty(
				ReceiptnoEntity.class, "year",
				Integer.valueOf(a.get(Calendar.YEAR))).get(0);
		int currentMinCount = reNo.getMincount();
		reNo.setMincount(currentMinCount + 1);
		receiptnoService.updateEntitie(reNo);

		NumberFormat f = new DecimalFormat("0000000");

		String no = f.format(currentMinCount + 1);
		re.setNo("No." + no);
		
		
		
		
		
		int sum = 0;
		String obj = "";
		/*修改打印摘要内容*/
		BuddhabirthEntity entity = buddhabirthService.get(
				BuddhabirthEntity.class, id[0]);
//		StringBuilder stb= new StringBuilder("").append(entity.getPaymen()).append("交来农历 ");
		StringBuilder stb= new StringBuilder("");
		String sumSummary ="";
		//查询今年的弥勒佛诞法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "弥勒佛诞");
		FuneralheldEntity funeralheldEntity = funeralhelds.get(0);
//----kooking 20180402 
		boolean flag = true;//标志是否是小牌
		if(entity.getSize().equals("大")|| entity.getSize().equals("拈香"))
		{
			sumSummary=entity.getSummary();
			flag = false;
		}
		
		if(flag){//若为小牌
			stb.append(entity.getPaymen()).append("交来农历 ");
			if(funeralheldEntity.getHoldDate().equals(funeralheldEntity.getEndDate())){
				stb.append(funeralheldEntity.getEndDate());
			}else{
				stb.append(funeralheldEntity.getHoldDate()+"至"+funeralheldEntity.getEndDate());
			}
			stb.append(funeralheldEntity.getRitualtype());
			stb.append("功德款</br>");
			sumSummary =stb.toString();
		}
		
//----kooking 20180402 		
		
		
		
		/*if(entity.getSize().equals("大")){
			stb.append("大牌");
			flag = false;
		}else if(entity.getSize().equals("拈香")){
			stb.append("拈香");
			flag = false;
		}*/
		
		String res="";
		/*修改打印摘要内容*/
		
		TSUser user = ResourceUtil.getSessionUserName();

		for (int i = 0; i < id.length; i++) {
			BuddhabirthEntity te = buddhabirthService.get(
					BuddhabirthEntity.class, id[i]);
			te.setAutoserial(antoserial[i]);
			te.setRegistertime(dateString);
			te.setRegistrant(user.getRealName());
			te.setReceiptno(re.getNo());
			buddhabirthService.updateEntitie(te);

			sum += te.getMoney();
			obj += te.getLivingmenber();
			/*修改打印摘要内容*/
			if(flag){
				sumSummary +=((i+1)+"、");
				sumSummary += te.getSummary()+" ";
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
		// 保存收据

		re.setRitualtype("弥勒佛诞");
		re.setRegistertime(dateString);
		re.setPurpose("弥勒佛诞");
		re.setFlag(0);
		receiptService.save(re);
		String receipId = re.getId();

		for (int i = 0; i < id.length; i++) {
			BuddhabirthEntity te = buddhabirthService.get(
					BuddhabirthEntity.class, id[i]);
			te.setReceiptid(receipId);
			buddhabirthService.updateEntitie(te);
		}

		model.addAttribute("message", "弥勒佛诞登记成功");
		model.addAttribute("ritualtype", "弥勒佛");
		ReceiptEntity returnRe = receiptService.get(ReceiptEntity.class,
				re.getId());
		model.addAttribute("returnRe", returnRe);

		String bigMoney = ChineseCurrency.toChineseCurrency(new Double(returnRe
				.getMoney()));
		model.addAttribute("bigMoney", bigMoney);
		String smallMoney = ChineseCurrency.toSmall(new Double(returnRe
				.getMoney()));
		model.addAttribute("smallMoney", smallMoney);
	}
	
	
	

	@RequestMapping(params = "getSerialAndSaveTablet")
	public ModelAndView getSerial(HttpServletRequest req, Model model) {
		String buddhabirthid = req.getParameter("buddhabirthid");
		if (buddhabirthid != null && !buddhabirthid.equals("")) {
			BuddhabirthEntity buddhabirthEntity = buddhabirthService.get(
					BuddhabirthEntity.class, buddhabirthid);
			if (buddhabirthEntity.getSize().equals("小")) {
				buddhabirthEntity.setAddress(req.getParameter("address"));
				buddhabirthEntity.setLivingmenber(req
						.getParameter("livingmenber"));
				buddhabirthEntity.setMoney(Integer.valueOf(req
						.getParameter("money")));
				buddhabirthEntity.setPayway(req.getParameter("payway"));
				buddhabirthEntity.setSummary(req.getParameter("summary"));
				buddhabirthEntity.setPrayingobj(req.getParameter("prayingobj"));
				buddhabirthEntity.setPaymen(req.getParameter("paymen"));
				buddhabirthService.updateEntitie(buddhabirthEntity);
				model.addAttribute("message", "弥勒佛诞牌位修改成功！！");
				return new ModelAndView("com/sys/updateSuccess");
			} else {
				String[] living = req.getParameterValues("living");

				String livingString = "";
				for (int i = 0; i < living.length; i++) {
					if (living[i].equals("") || living[i] == null) {
						living[i] = " ";
					}
					livingString += living[i] + ";";
				}

				buddhabirthEntity.setAddress(req.getParameter("address"));
				buddhabirthEntity.setLivingmenber(livingString);
				buddhabirthEntity.setMoney(Integer.valueOf(Integer.valueOf(req
						.getParameter("money"))));
				buddhabirthEntity.setPayway(req.getParameter("payway"));
				buddhabirthEntity.setSummary(req.getParameter("summary"));
				buddhabirthEntity.setPrayingobj(req.getParameter("prayingobj"));
				buddhabirthEntity.setPaymen(req.getParameter("paymen"));
				buddhabirthEntity.setBook(req.getParameter("book"));

				buddhabirthService.updateEntitie(buddhabirthEntity);
				model.addAttribute("message", "弥勒佛诞牌位修改成功！！");
				return new ModelAndView("com/sys/updateSuccess");
			}
		}

		try {
			String sizeflag = req.getParameter("sizeflag");
			if (sizeflag == null || sizeflag.equals("小")) {
				// 获取做法事人的ID
				String id0 = req.getParameter("id");

				String paymen = req.getParameter("paymen");

				// 获取祈福者
				String[] prayingobj = req.getParameterValues("prayingobj");

				// 获取祈福对象
				String[] livingmember = req.getParameterValues("livingmenber");

				// 获取祈福者家庭住址
				String[] address = req.getParameterValues("address");

				// 获取摘要
				String[] summary = req.getParameterValues("summary");

				// 获取摘要
				String[] payway = req.getParameterValues("payway");

				// 获取摘要
				String[] money = req.getParameterValues("money");

				// 获取大小
				String[] size = req.getParameterValues("size");

				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				String currentyear = String.valueOf(year);

//				List<BuddhabirthEntity> buddhabirthEntityList = new ArrayList<BuddhabirthEntity>();
				String ids = "";
				String[] antoserial=new String[size.length];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				 //20190615 kooking
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy年MM月dd日");
				//当前日期
				String dateString1 = formatter1.format(currentTime);
				 //20190615 kooking
				
				
				/**
				 * x=当前月份
				 * if(x<3){
				 * 	统计日期 x.year-1.4.1 ~ 当前日期 
				 * }else if(x>=4){
				 * 	统计日期 x.year.4.1 ~ 当前日期
				 * }
				 */
				//当前月份
				
				int mon = cal.get(Calendar.MONTH)+1; //2019.12.4
				if(mon<3) {//1、2月份
					year = cal.get(Calendar.YEAR)-1;
				}else if(mon>=4) {//4月份~12月份
					year = cal.get(Calendar.YEAR);
				}
//                int mon=cal.get(Calendar.MONTH);
//                
//                if(mon<3)
//                	year=cal.get(Calendar.YEAR)-1;
				
                
//				String begin_date= String.valueOf(year) + "-03-01";  //20190615 kooking
//				String begin_date= String.valueOf(year) + "年03月01日";
				String begin_date= String.valueOf(year) + "年04月01日";


				for (int i = 0; i < size.length; i++) {
					BuddhabirthEntity buddhabirthEntity = new BuddhabirthEntity();
					CriteriaQuery cq = new CriteriaQuery(
							BuddhabirthEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("size", size[i]),
							Restrictions.between("registertime", begin_date,dateString1))); //20190615 kooking
					
					List<BuddhabirthEntity> buddhabirths = buddhabirthService
							.getListByCriteriaQuery(cq, false);

					buddhabirthEntity.setAddress(address[i]);
					buddhabirthEntity.setLivingmenber(livingmember[i]);
					buddhabirthEntity.setMoney(Integer.valueOf(money[i]));
					buddhabirthEntity.setPayway(payway[i]);
					buddhabirthEntity.setSummary(summary[i]);
					buddhabirthEntity.setPrayingobj(prayingobj[i]);
					buddhabirthEntity.setDoritualid(id0);
					buddhabirthEntity.setPaymen(paymen);
					buddhabirthEntity.setSize(size[i]);
					// 换时间
					buddhabirthEntity.setFlag(0);
					NumberFormat f = new DecimalFormat("000000");
					buddhabirthEntity
							.setSerial(f.format(buddhabirths.size() + 1));
					buddhabirthEntity.setRegistertime(dateString);
//					buddhabirthEntityList.add(buddhabirthEntity);

					buddhabirthService.save(buddhabirthEntity);
					
					ControllerUtils.save2Pray(buddhabirthEntity, buddhabirthService);
					
					ids += buddhabirthEntity.getId() + ",";
					antoserial[i]=buddhabirthEntity.getSerial();
				}
//				req.setAttribute("buddhabirthEntityList", buddhabirthEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView(
//						"com/sys/buddhabirth/AutoSerialBuddhabirth");

//------------20180502 Kooking		
				return updateBuddhabirthAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	

			} else { // 大中牌
				String id0 = req.getParameter("id");

				String paymen = req.getParameter("paymen");

				// 获取祈福者
				String prayingobj = req.getParameter("prayingobj");

				// 获取祈福对象
				String[] living = req.getParameterValues("living");

				// 获取祈福者家庭住址
				String address = req.getParameter("address");

				// 获取摘要
				String summary = req.getParameter("summary");

				// 获取摘要
				String payway = req.getParameter("payway");

				// 获取摘要
				String money = req.getParameter("money");

				// 获取大小
				String book = req.getParameter("book");

				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				String currentyear = String.valueOf(year);

//				List<BuddhabirthEntity> buddhabirthEntityList = new ArrayList<BuddhabirthEntity>();
				String ids = "";
				String[] antoserial=new String[1];

				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				 //20190615 kooking
				SimpleDateFormat formatter1 = new SimpleDateFormat("yyyy年MM月dd日");
				String dateString1 = formatter1.format(currentTime);
				 //20190615 kooking

				/**
				 * x=当前月份
				 * if(x<3){
				 * 	统计日期 x.year-1.4.1 ~ 当前日期 
				 * }else if(x>=4){
				 * 	统计日期 x.year.4.1 ~ 当前日期
				 * }
				 */
				//当前月份
				
				int mon = cal.get(Calendar.MONTH)+1; //2019.12.4
				if(mon<3) {//1、2月份
					year = cal.get(Calendar.YEAR)-1;
				}else if(mon>=4) {//4月份~12月份
					year = cal.get(Calendar.YEAR);
				}
				
//				  int mon=cal.get(Calendar.MONTH);
//	                
//	                if(mon<3)
//	                	year=cal.get(Calendar.YEAR)-1;
	                
//					String begin_date= String.valueOf(year) + "-03-01"; //20190615 kooking
//	                String begin_date= String.valueOf(year) + "年03月01日";
				String begin_date= String.valueOf(year) + "年04月01日";
				BuddhabirthEntity buddhabirthEntity = new BuddhabirthEntity();
				CriteriaQuery cq = new CriteriaQuery(BuddhabirthEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size", sizeflag),
						Restrictions.between("registertime", begin_date,dateString1))); //20190615 kooking
				List<BuddhabirthEntity> buddhabirths = buddhabirthService
						.getListByCriteriaQuery(cq, false);

				String livingString = "";
				for (int i = 0; i < living.length; i++) {
					if (living[i].equals("") || living[i] == null) {
						living[i] = " ";
					}
					livingString += living[i] + ";";
				}

				buddhabirthEntity.setAddress(address);
				buddhabirthEntity.setLivingmenber(livingString);
				buddhabirthEntity.setMoney(Integer.valueOf(money));
				buddhabirthEntity.setPayway(payway);
				buddhabirthEntity.setSummary(summary);
				buddhabirthEntity.setPrayingobj(prayingobj);
				buddhabirthEntity.setDoritualid(id0);
				buddhabirthEntity.setPaymen(paymen);
				buddhabirthEntity.setBook(book);
				buddhabirthEntity.setSize(sizeflag);
				buddhabirthEntity.setFlag(0);
				NumberFormat f = new DecimalFormat("000000");
				buddhabirthEntity.setSerial(f.format(buddhabirths.size() + 1));
				buddhabirthEntity.setRegistertime(dateString);
//				buddhabirthEntityList.add(buddhabirthEntity);

				buddhabirthService.save(buddhabirthEntity);
				
				ControllerUtils.save2Pray(buddhabirthEntity, buddhabirthService);
				
				ids += buddhabirthEntity.getId() + ",";
				antoserial[0]=buddhabirthEntity.getSerial();
//				req.setAttribute("buddhabirthEntityList", buddhabirthEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView(
//						"com/sys/buddhabirth/AutoSerialBuddhabirth");
//------------20180502 Kooking		
				return updateBuddhabirthAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}

	}

	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(BuddhabirthEntity buddhabirthEntity,
			HttpServletRequest request, Model model) {
		List<BuddhabirthEntity> buddhabirthEntitys = new ArrayList<BuddhabirthEntity>();
		if (StringUtil.isNotEmpty(buddhabirthEntity.getId())) {
			buddhabirthEntity = buddhabirthService.getEntity(
					BuddhabirthEntity.class, buddhabirthEntity.getId());

			TSUser user = ResourceUtil.getSessionUserName();
			if (!user.getRealName().equals(buddhabirthEntity.getRegistrant())) {
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			if (buddhabirthEntity.getSize().equals("小")) {
				buddhabirthEntitys.add(buddhabirthEntity);
				model.addAttribute("buddhabirthEntitys", buddhabirthEntitys);
				model.addAttribute("id", buddhabirthEntity.getDoritualid());
				model.addAttribute("buddhabirthid", buddhabirthEntity.getId());
				model.addAttribute("clientele", buddhabirthEntity.getPaymen());
				model.addAttribute("size", buddhabirthEntity.getSize());
				return new ModelAndView("com/sys/buddhabirth/editBuddhabirth");
			} else {

				String livingString = buddhabirthEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for (int i = 0; i < livingStringBySemicolon.length; i++) {

					livingList.add(livingStringBySemicolon[i]);
				}
				buddhabirthEntitys.add(buddhabirthEntity);
				model.addAttribute("buddhabirthEntity", buddhabirthEntity);
				model.addAttribute("buddhabirthEntitys", buddhabirthEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", buddhabirthEntity.getDoritualid());
				model.addAttribute("size", buddhabirthEntity.getSize());
				model.addAttribute("clientele", buddhabirthEntity.getPaymen());
				model.addAttribute("buddhabirthid", buddhabirthEntity.getId());
				return new ModelAndView(
						"com/sys/buddhabirth/editMiddleAndBigBuddhabirth");
			}
		} else {
			return new ModelAndView("com/sys/error");
		}
	}

}
