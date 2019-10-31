package com.sys.controller.eveningpforr;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.eveningpforr.EveningpforrEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.entity.tmp_table.Tmp_tableEntity;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.eveningpforr.EveningpforrServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import test.TabletStat;

/**
 * @Title: Controller
 * @Description: 晚普佛管理
 * @author zhangdaihao
 * @date 2016-10-10 15:49:52
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/eveningpforrController")
public class EveningpforrController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(EveningpforrController.class);

	@Autowired
	private EveningpforrServiceI eveningpforrService;
	@Autowired
	private DoritualinfoServiceI doritualinfoService;
	@Autowired
	private ReceiptServiceI receiptService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ReceiptnoServiceI receiptnoService;
	private String message;
	private int first = 0;
	private int last = 0;
	private String downloadPath = "";

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 晚普佛管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "eveningpforr")
	public ModelAndView eveningpforr(HttpServletRequest request) {
		return new ModelAndView("com/sys/eveningpforr/eveningpforrList");
	}

	/**
	 * 晚普佛管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "info")
	public ModelAndView Info(HttpServletRequest request) {
		return new ModelAndView("com/sys/eveningpforr/doritualinfoList");
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
	public void datagrid(EveningpforrEntity eveningpforr,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EveningpforrEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				eveningpforr, request.getParameterMap());
		this.eveningpforrService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除晚普佛管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(EveningpforrEntity eveningpforr,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		eveningpforr = systemService.getEntity(EveningpforrEntity.class,
				eveningpforr.getId());
		message = "晚普佛管理删除成功";
		eveningpforrService.delete(eveningpforr);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加晚普佛管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(EveningpforrEntity eveningpforr,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(eveningpforr.getId())) {
			message = "晚普佛管理更新成功";
			EveningpforrEntity t = eveningpforrService.get(
					EveningpforrEntity.class, eveningpforr.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(eveningpforr, t);
				eveningpforrService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "晚普佛管理更新失败";
			}
		} else {
			message = "晚普佛管理添加成功";
			eveningpforrService.save(eveningpforr);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 晚普佛管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(EveningpforrEntity eveningpforr,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(eveningpforr.getId())) {
			eveningpforr = eveningpforrService.getEntity(
					EveningpforrEntity.class, eveningpforr.getId());
			req.setAttribute("eveningpforrPage", eveningpforr);
		}
		return new ModelAndView("com/sys/eveningpforr/eveningpforr");
	}

	@RequestMapping(params = "forwardSelectRecord")
	public ModelAndView forwardSelectRecord(String id, HttpServletRequest req) {
		if(id != null && !id.equals("")){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(
					DoritualinfoEntity.class, id);
			req.setAttribute("doritualinfoEntity", doritualinfoEntity);

			CriteriaQuery cq = new CriteriaQuery(EveningpforrEntity.class);
			cq.add(Restrictions.eq("doritualid", id));

			List<EveningpforrEntity> eveningpforrEntityList = new ArrayList<EveningpforrEntity>();
			eveningpforrEntityList = eveningpforrService.getListByCriteriaQuery(cq,
					false);

			req.setAttribute("eveningpforrEntityList", eveningpforrEntityList);
		}
		return new ModelAndView("com/sys/eveningpforr/showEveningPforrRecord");
	}

	@RequestMapping(params = "ToEditEveningPforr")
	public ModelAndView ToEditEveningPforr(DoritualinfoEntity doritualinfo, HttpServletRequest req,
			Model model) {
		String id = req.getParameter("id");
//		---kooking 20180402
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
		//保存做法事人的基本信息
		if (id == null || id.equals("")) {// 添加
			
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
			
		id = doritualinfo.getId();
//		---kooking 20180402
		String morningPforrIds = req.getParameter("morningPforrId");
		DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(
				DoritualinfoEntity.class, id);
		//添加做法事人的地址
		model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
		model.addAttribute("updateFlag", "0");
		
		List<EveningpforrEntity> morningpforrEntitys = new ArrayList<EveningpforrEntity>();
		if (morningPforrIds == null) {
			morningpforrEntitys = null;
			model.addAttribute("id", id);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			model.addAttribute("morningpforrEntitys", morningpforrEntitys);
			return new ModelAndView("com/sys/eveningpforr/editEveningPforr");
		} else {
			// 已经有数据
			String morningPforrId[] = morningPforrIds.substring(0,
					morningPforrIds.length() - 1).split(",");

			List<Map<String, String>> counts = new ArrayList<>();
			List<String> surnames = new ArrayList<>();
			List<String> tmptypes = new ArrayList<>();
			List<String> ancestorList = new ArrayList<>();

			// 分别获取相关数据
			// 随堂
			String stCountSql = "";
			// 包堂
			String btCountSql = "";
			Long st, bt;
			for (int i = 0; i < morningPforrId.length; i++) {
				System.out.println(morningPforrId[i]);
				EveningpforrEntity entity = eveningpforrService.get(
						EveningpforrEntity.class, morningPforrId[i]);
				morningpforrEntitys.add(entity);

				String ancestorString = entity.getAncestor();
				String[] ancestorStringByPound = ancestorString.split("#");

				surnames.add("");
				tmptypes.add("0");
				int index = 0;
				if (ancestorStringByPound.length == 3) {
					surnames.set(i, ancestorStringByPound[0]);
					ancestorList.add(ancestorStringByPound[1]);
					index = 1;
				}
				ancestorList.add(ancestorStringByPound[0]);
				if (ancestorStringByPound[index + 1].equals("1")) {
					tmptypes.set(i, "1");
				}

				stCountSql = "select count(*) from eveningpforr"
						+ " where flag=0 and size='随堂' and solardate = '"
						+ entity.getSolardate() + "'";
				btCountSql = "select count(*) from eveningpforr"
						+ " where flag=0 and size='包堂' and solardate = '"
						+ entity.getSolardate() + "'";

				st = systemService.getCountForJdbc(stCountSql);
				bt = systemService.getCountForJdbc(btCountSql);
				Map<String, String> map = new HashMap<>();
				map.put("st", st + "");
				map.put("bt", bt + "");
				counts.add(map);
			}

			model.addAttribute("ancestorList", ancestorList);
			model.addAttribute("morningpforrEntitys", morningpforrEntitys);
			model.addAttribute("counts", counts);
			model.addAttribute("surnames", surnames);
			model.addAttribute("tmptypes", tmptypes);
			model.addAttribute("id", id);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			return new ModelAndView("com/sys/eveningpforr/editEveningPforr");
		}

	}

	@RequestMapping(params = "getSerialAndSaveTablet")
	/* 8月17号改动 */
	public ModelAndView getSerial(HttpServletRequest req, Model model) {
		String morningpforrid = req.getParameter("morningpforrid");
		// 修改记录
		if (morningpforrid != null && !morningpforrid.equals("")) {
			EveningpforrEntity morningpforrEntity = eveningpforrService.get(
					EveningpforrEntity.class, morningpforrid);
			// 获取做法事人的ID
			String id0 = req.getParameter("id");
			// 获取祈福者//阳上
			String prayingobj = req.getParameter("prayingobj");
			// 超度对象
			String ancestor = req.getParameter("ancestor");
			// 获取祈福者家庭住址
			String address = req.getParameter("address");
			// 获取摘要
			String summary = req.getParameter("summary");
			// 获取支付方式
			String payway = req.getParameter("payway");
			// 获取付款人
			String paymen = req.getParameter("paymen");
			// 获取金额
			String money = req.getParameter("money");
			String book = req.getParameter("book");
			// 获取堂类型
			String size = req.getParameter("size");
			// 十方法界
			String type = req.getParameter("type");
			// 历代宗亲
			String surname = req.getParameter("surname");
			// 公历
			String solardate = req.getParameter("solardate");
			// 农历
			String lunardate = req.getParameter("lunardate");

			/*
			 * // 获取当前时间 Calendar cal = Calendar.getInstance(); int year =
			 * cal.get(Calendar.YEAR); String currentyear =
			 * String.valueOf(year);
			 * 
			 * // 获取序号 CriteriaQuery cq = new
			 * CriteriaQuery(EveningpforrEntity.class);
			 * cq.add(Restrictions.like("registertime", currentyear,
			 * MatchMode.START)); List<EveningpforrEntity> morningpforrEntitys =
			 * eveningpforrService.getListByCriteriaQuery(cq, false);
			 * 
			 * String ids = "";
			 * 
			 * Date currentTime = new Date(); SimpleDateFormat formatter = new
			 * SimpleDateFormat("yyyy-MM-dd"); String dateString =
			 * formatter.format(currentTime);
			 */

			morningpforrEntity.setAddress(address);

			String ancestorStr = "";
			if (surname != null && !surname.equals("")) {
				ancestorStr = surname + "#";
			}
			ancestorStr += (ancestor + "#" + type);
			// 祖先
			morningpforrEntity.setAncestor(ancestorStr);

			morningpforrEntity.setMoney(Integer.valueOf(money));
			morningpforrEntity.setPayway(payway);
			morningpforrEntity.setSummary(summary);
			morningpforrEntity.setPrayingobj(prayingobj);
			morningpforrEntity.setPaymen(paymen);
			morningpforrEntity.setBook(book);
			morningpforrEntity.setLunardate(lunardate);
			morningpforrEntity.setSolardate(solardate);
			morningpforrEntity.setDoritualid(id0);
			morningpforrEntity.setSize(size);
			// 换时间
			morningpforrEntity.setFlag(0);
			/*
			 * NumberFormat f = new DecimalFormat("000000");
			 * morningpforrEntity.setSerial(f.format(morningpforrEntitys.size()
			 * + 1)); morningpforrEntity.setRegistertime(dateString);
			 */

			eveningpforrService.updateEntitie(morningpforrEntity);

			model.addAttribute("message", "晚普佛牌位修改成功！！");
			return new ModelAndView("com/sys/updateSuccess");
		}
		// 添加
		try {
			// 获取做法事人的ID
			String id0 = req.getParameter("id");
			String paymen = req.getParameter("paymen");
			// 获取祈福者
			String[] prayingobj = req.getParameterValues("prayingobj");
			// 获取祈福对象
			String[] ancestor = req.getParameterValues("ancestor");
			// 获取祈福者家庭住址
			String[] address = req.getParameterValues("address");
			// 获取摘要
			String[] summary = req.getParameterValues("summary");
			// 获取支付方式
			String[] payway = req.getParameterValues("payway");
			// 获取摘要
			String[] money = req.getParameterValues("money");
			// 获取公历
			String solardates[] = req.getParameterValues("solardate");
			// 获取农历
			String lunardates[] = req.getParameterValues("lunardate");
			// 获取大小
			// String[] size = req.getParameterValues("size");
			String book[] = req.getParameterValues("book");
			// 获取堂类型
			String size = req.getParameter("size");
			// 是否十方法界
			String type[] = req.getParameterValues("type");
			// 历代宗亲
			String surname[] = req.getParameterValues("surname");

//			Calendar cal = Calendar.getInstance();
			/*
			 * int year = cal.get(Calendar.YEAR); String currentyear =
			 * String.valueOf(year);
			 */
//			int day = cal.get(Calendar.DAY_OF_MONTH);
//			NumberFormat f = new DecimalFormat("00");
//			String currentDay = f.format(day);

//			List<EveningpforrEntity> morningpforrEntities = new ArrayList<EveningpforrEntity>();
			String ids = "";
			String[] antoserial=new String[money.length];
			
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
			String dateString = formatter.format(currentTime);

			for (int i = 0; i < money.length; i++) {
				EveningpforrEntity morningpforrEntity = new EveningpforrEntity();

				// 获取数目，用于序列号
				CriteriaQuery cq = new CriteriaQuery(EveningpforrEntity.class);
				cq.add(Restrictions
						.and(/* Restrictions.eq("size",size[i]), */Restrictions
								.like("solardate", solardates[i],
										MatchMode.ANYWHERE)));
				List<EveningpforrEntity> morningpforrs = eveningpforrService
						.getListByCriteriaQuery(cq, false);

				morningpforrEntity.setAddress(address[i]);

				String ancestorStr = "";
				if (surname[i] != null && !surname[i].equals("")) {
					ancestorStr = surname[i] + "#";
				}
				ancestorStr += (ancestor[i] + "#" + type[i]);

				morningpforrEntity.setAncestor(ancestorStr);

				morningpforrEntity.setMoney(Integer.valueOf(money[i]));
				morningpforrEntity.setPayway(payway[i]);
				morningpforrEntity.setSummary(summary[i]);
				morningpforrEntity.setPrayingobj(prayingobj[i]);
				morningpforrEntity.setDoritualid(id0);
				morningpforrEntity.setPaymen(paymen);
				morningpforrEntity.setBook(book[i]);
				morningpforrEntity.setSize(size);
				morningpforrEntity.setSolardate(solardates[i]);
				morningpforrEntity.setLunardate(lunardates[i]);

				// 换时间
				morningpforrEntity.setFlag(0);
				NumberFormat nf = new DecimalFormat("000000");
				morningpforrEntity
						.setSerial(nf.format(morningpforrs.size() + 1));
				morningpforrEntity.setRegistertime(dateString);
//				morningpforrEntities.add(morningpforrEntity);

				eveningpforrService.save(morningpforrEntity);
				ids += morningpforrEntity.getId() + ",";
				antoserial[i]=morningpforrEntity.getSerial();
				
			}
//			req.setAttribute("morningpforrEntities", morningpforrEntities);
//			req.setAttribute("ids", ids);
//			return new ModelAndView(
//					"com/sys/eveningpforr/autoSerialEveningPforr");

//----------20180502 Kooking		
			return updateEveningpforrAndReceipt(ids, antoserial, model);
//------------20180502 Kooking
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
	}

	/*@RequestMapping(params = "updateEveningpforrAndReceipt")
	public ModelAndView updateMorningpforrAndReceipt(HttpServletRequest req,
			Model model) {*/
	private ModelAndView updateEveningpforrAndReceipt(String ids,String[] antoserial,
				Model model) {
		try {

//			String ids = req.getParameter("ids");
			String subids = ids.substring(0, ids.length() - 1);
			String[] id = subids.split(",");

			// 获取自动编号
//			String[] antoserial = req.getParameterValues("autoserial");

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
			String sumSummary = "", res = "";

			TSUser user = ResourceUtil.getSessionUserName();

			for (int i = 0; i < id.length; i++) {
				EveningpforrEntity te = eveningpforrService.get(
						EveningpforrEntity.class, id[i]);
				te.setAutoserial(antoserial[i]);
				te.setRegistertime(dateString);
				te.setRegistrant(user.getRealName());
				te.setReceiptno(re.getNo());
				eveningpforrService.updateEntitie(te);

				sum += te.getMoney();
				obj += te.getAncestor();
				if(te.getSummary() != null && !te.getSummary().trim().equals("")){
					sumSummary += te.getSummary() + "#";
				}
				
				if(i!=id.length-1){
					res += te.getAutoserial() + ",";
				}else{
					res += te.getAutoserial();
				}
				
			}

			EveningpforrEntity entity = eveningpforrService.get(
					EveningpforrEntity.class, id[0]);

			re.setPaymen(entity.getPaymen());
			re.setPayway(entity.getPayway());
			re.setRegistrant(user.getRealName());
			re.setAddress(entity.getAddress());
			re.setDoritualid(entity.getDoritualid());
			re.setMoney(sum);
			re.setSummary(sumSummary);
			re.setRemark(res);
			re.setSize("其他");
			re.setObj(obj);
			re.setDate("二#十九#二#廿七");

			// 保存收据

			re.setRitualtype("晚普佛");
			re.setRegistertime(dateString);
			re.setPurpose("晚普佛");
			re.setFlag(0);
			receiptService.save(re);
			String receipId = re.getId();

			for (int i = 0; i < id.length; i++) {
				EveningpforrEntity te = eveningpforrService.get(
						EveningpforrEntity.class, id[i]);
				te.setReceiptid(receipId);
				eveningpforrService.updateEntitie(te);
			}

			model.addAttribute("message", "晚普佛普佛登记成功");
			model.addAttribute("ritualtype", "晚普佛");
			ReceiptEntity returnRe = receiptService.get(ReceiptEntity.class,
					re.getId());
			model.addAttribute("returnRe", returnRe);
			String bigMoney = ChineseCurrency.toChineseCurrency(new Double(
					returnRe.getMoney()));
			model.addAttribute("bigMoney", bigMoney);
			String smallMoney = ChineseCurrency.toSmall(new Double(returnRe
					.getMoney()));
			model.addAttribute("smallMoney", smallMoney);
			return new ModelAndView("com/sys/success");
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
	}

	/**
	 * 修改晚普佛记录
	 * 
	 * @param guanyinopenEntity
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(EveningpforrEntity morningpforrEntity,
			HttpServletRequest request, Model model) {
		List<EveningpforrEntity> morningpforrEntitys = new ArrayList<EveningpforrEntity>();
		if (StringUtil.isNotEmpty(morningpforrEntity.getId())) {
			morningpforrEntity = eveningpforrService.getEntity(
					EveningpforrEntity.class, morningpforrEntity.getId());

			TSUser user = ResourceUtil.getSessionUserName();
			if (!user.getRealName().equals(morningpforrEntity.getRegistrant())) {
				return new ModelAndView("com/sys/unauthorized");
			}

			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			String ancestorString = morningpforrEntity.getAncestor();

			List<String> surnames = new ArrayList<>();
			List<String> tmptypes = new ArrayList<>();
			String[] ancestorStringByPound = ancestorString.split("#");
			// List<String> ancestorList = new ArrayList<String>();
			List<String> ancestorList = new ArrayList<>();

			surnames.add("");
			tmptypes.add("0");
			int index = 0;
			if (ancestorStringByPound.length == 3) {
				surnames.set(0, ancestorStringByPound[0]);
				ancestorList.add(ancestorStringByPound[1]);
				index = 1;
			}
			ancestorList.add(ancestorStringByPound[0]);
			if (ancestorStringByPound[index + 1].equals("1")) {
				tmptypes.set(0, "1");
			}

			morningpforrEntitys.add(morningpforrEntity);

			model.addAttribute("morningpforrEntity", morningpforrEntity);

			model.addAttribute("morningpforrEntitys", morningpforrEntitys);
			model.addAttribute("id", morningpforrEntity.getDoritualid());
			model.addAttribute("buddhabirthid", morningpforrEntity.getId());
			model.addAttribute("clientele", morningpforrEntity.getPaymen());
			model.addAttribute("size", morningpforrEntity.getSize());
			model.addAttribute("tmptypes", tmptypes);
			model.addAttribute("surnames", surnames);
			model.addAttribute("ancestorList", ancestorList);
			return new ModelAndView("com/sys/eveningpforr/editEveningPforr");
		} else {
			return new ModelAndView("com/sys/error");
		}
	}

	/**
	 * 加载早普佛统计表
	 * 
	 * @author xiezh
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "fowardEveningforr")
	public ModelAndView fowardEveningforr(HttpServletRequest req) {
		return new ModelAndView("com/sys/eveningpforr/eveningpforrindex");
	}

	/**
	 * 跳转到统计信息
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "tabletcount")
	public ModelAndView tabletcount(HttpServletRequest req)
			throws UnsupportedEncodingException {
		Calendar ca = Calendar.getInstance();
		String year = String.valueOf(ca.get(Calendar.YEAR));
		List<TabletStat> tablet = new ArrayList<TabletStat>();
		// 随堂
		String stNotPrint = "select count(*) from eveningpforr"
				+ " where flag=0 and size='随堂' and registertime like '" + year
				+ "%'";
		String stAlreadyPrint = "select count(*) from eveningpforr"
				+ " where flag=1 and size='随堂' and registertime like '" + year
				+ "%'";
		// 包堂
		String btNotPrint = "select count(*) from eveningpforr"
				+ " where flag=0 and size='包堂' and registertime like '" + year
				+ "%'";
		String btAlreadyPrint = "select count(*) from eveningpforr"
				+ " where flag=1 and size='包堂' and registertime like '" + year
				+ "%'";
		// 数目
		Long stNotPrintCount = systemService.getCountForJdbc(stNotPrint);
		Long stAlreadyPrintCount = systemService
				.getCountForJdbc(stAlreadyPrint);
		Long btNotPrintCount = systemService.getCountForJdbc(btNotPrint);
		Long btAlreadyPrintCount = systemService
				.getCountForJdbc(btAlreadyPrint);

		TabletStat st = new TabletStat();
		st.setRitualtype("晚普佛");
		st.setSize("随堂");
		st.setNotprint(stNotPrintCount);
		st.setAlreadyprint(stAlreadyPrintCount);
		tablet.add(st);

		TabletStat bt = new TabletStat();
		bt.setRitualtype("晚普佛");
		bt.setSize("包堂");
		bt.setNotprint(btNotPrintCount);
		bt.setAlreadyprint(btAlreadyPrintCount);
		tablet.add(bt);

		req.setAttribute("name", "晚普佛");
		req.setAttribute("tablet", tablet);

		return new ModelAndView("com/sys/tabletstat/tabletcount");
	}

	/**
	 * 跳转到打印
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "forwardPrint")
	public ModelAndView forwardPrint(HttpServletRequest req)
			throws UnsupportedEncodingException {
		return new ModelAndView("com/sys/eveningpforr/print");
	}

	@RequestMapping(params = "printMorningpforrPaiwei")
	public void printMorningpforrPaiwei(HttpServletRequest req,
			HttpServletResponse resp) throws IOException {
		deleteFile(this
				.getClass()
				.getClassLoader()
				.getResource("/")
				.getPath()
				.substring(
						1,
						this.getClass().getClassLoader().getResource("/")
								.getPath().length() - 16)
				+ "/webpage/c_gen_paiwei/");
		String starttime = req.getParameter("solarstart");
		String endtime = req.getParameter("solarend");

//		TSUser user = ResourceUtil.getSessionUserName();

		Calendar ca = Calendar.getInstance();
//		String year = String.valueOf(ca.get(Calendar.YEAR));
		String sql = "select serial,ancestor,prayingobj from eveningpforr  where solardate >= '"
				+ starttime + "' and solardate <= '" + endtime + "';";

		System.out.println(sql);
		List<Object[]> objs = new ArrayList<Object[]>();
		objs = systemService.findListbySql(sql);

		if (objs.size() != 0) {
			Map<String, String> dataMap = null;
			String nodepath = "";
			nodepath = this.getClass().getClassLoader().getResource("/")
					.getPath();
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
			configuration.setServletContextForTemplateLoading(req.getSession()
					.getServletContext(), "/webpage/c_paiweiModel");

			try {
				int tmps_size = objs.size();
				first = Integer.parseInt(String.valueOf(objs.get(0)[0]));
				last = Integer
						.parseInt(String.valueOf(objs.get(tmps_size - 1)[0]));
				downloadPath = "/wpfpw" + first + "-" + last + ".zip";
				for (int i = 0; i < objs.size(); i += 2) {
					String postfixList = "";
					String postfixqifu = String.valueOf(ca.get(Calendar.YEAR))
							+ String.valueOf(objs.get(i)[0] + ".doc");
					postfixList += postfixqifu + ";";
					nodepath = this.getClass().getClassLoader()
							.getResource("/").getPath();
					File outFileqifu = new File(nodepath.substring(1,
							nodepath.length() - 16)
							+ "/webpage/c_gen_paiwei/" + postfixqifu);
					Writer outqifu = null;
					try {
						outqifu = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(outFileqifu), "utf-8"));
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					List<Object[]> subList = null;

					if (tmps_size - 2 >= 0) {
						subList = objs.subList(i, i + 2);
						dataMap = getEveningpforrMap(subList, 2);
						tmps_size -= 2;
					} else {// 剩余不足5条
						subList = objs.subList(i, objs.size());
						dataMap = getEveningpforrMap(subList, objs.size() % 2);
					}

					Template t = null;
					try {
						t = configuration.getTemplate("cddpw_2.ftl");
						t.setEncoding("utf-8");
					} catch (IOException e) {
						e.printStackTrace();
					}

					t.process(dataMap, outqifu);
					outqifu.close();
				}

			} catch (TemplateException e) {
				e.printStackTrace();
			}

			generateZipFile(nodepath.substring(1, nodepath.length() - 16)
					+ "/webpage/c_gen_paiwei/");
			resp.getWriter().write("/webpage/c_gen_paiwei/" + downloadPath);

		} else {

			resp.getWriter().write("找不到适合条件的牌位");

		}

	}

	// 晚普佛插入数据
	private Map<String, String> getEveningpforrMap(List<Object[]> objs,
			int count) {
		Map<String, String> dataMap = new HashMap<>();

		for (int i = 1; i <= count; i++) {
			String ancestor = String.valueOf(objs.get(i - 1)[1]);
			// Tmp_tableEntity tmp = tmps.get(i - 1);
			String[] all = ancestor.split("#");
			String home = "";
			String[] family = null;
			String type = "";
			if (all.length == 3) {
				home = all[0];
				family = all[1].split(";");
				type = all[2];
			} else if (all.length == 2) {
				home = "  ";
				family = all[0].split(";");
				type = all[1];
			}

			dataMap.put("s" + i + "10", home);

			for (int j = 1; j <= 6; j++) {

				if (j <= family.length) {
					dataMap.put("s" + i + "1" + j, family[j - 1]);

				} else {
					dataMap.put("s" + i + "1" + j, "");
				}
			}

			// dataMap.put("s" + i + "0" , " ");

			if (type.equals("1")) {
				dataMap.put("s" + i + "17", "十方法界冤親債主");
				dataMap.put("s" + i + "18", "歷劫父母師長眷屬");
				dataMap.put("s" + i + "19", "有祀無祀孤魂等眾");
			} else {
				dataMap.put("s" + i + "17", family.length > 6 ? family[6] : "");
				dataMap.put("s" + i + "18", "   ");
				dataMap.put("s" + i + "19", family.length > 7 ? family[7] : "");
			}

			dataMap.put("s" + i + "30", String.valueOf(objs.get(i - 1)[2]));
			dataMap.put("s" + i + "20", String.valueOf(objs.get(i - 1)[0]));
		}

		if (count < 2) {
			dataMap.put("s210", "  ");
			dataMap.put("s211", "  ");
			dataMap.put("s212", "  ");
			dataMap.put("s213", "  ");
			dataMap.put("s214", "  ");
			dataMap.put("s215", "  ");
			dataMap.put("s216", "  ");
			dataMap.put("s217", "  ");
			dataMap.put("s218", "  ");
			dataMap.put("s219", "  ");
			dataMap.put("s220", "  ");
			dataMap.put("s230", "  ");
		}

		return dataMap;
	}

	/**
	 * 打印文疏
	 * 
	 * @param req
	 * @param resp
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(params = "printMorningforrWenshu")
	public void printMorningforrWenshu(HttpServletRequest req,
			HttpServletResponse resp, Model model) throws IOException {
		deleteFile(this
				.getClass()
				.getClassLoader()
				.getResource("/")
				.getPath()
				.substring(
						1,
						this.getClass().getClassLoader().getResource("/")
								.getPath().length() - 16)
				+ "/webpage/c_gen_wenshu");
		Calendar ca1 = Calendar.getInstance();
		String year = String.valueOf(ca1.get(Calendar.YEAR));

		String starttime = req.getParameter("solarstart");
		String endtime = req.getParameter("solarend");
//		TSUser user = ResourceUtil.getSessionUserName();

		List<Object[]> objs = new ArrayList<Object[]>();
		String sql = "select ancestor,prayingobj,serial,address,lunardate,book,registertime from eveningpforr  where solardate >= '"
				+ starttime + "' and solardate <= '" + endtime + "';";

		String begindate = "";
		String enddate = "";
		System.out.println(sql);
		Calendar ca = Calendar.getInstance();
		objs = systemService.findListbySql(sql);

		String postfixList = "";
		if (objs.size() != 0) {
			try {
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("utf-8");
				String nodepath = "";
				configuration.setServletContextForTemplateLoading(req.getSession()
						.getServletContext(), "/webpage/c_rea_wenshu_model");
				for (int i = 0; i < objs.size(); i++) {
					Map dataMap = new HashMap();
					dataMap.put("address", String.valueOf(objs.get(i)[3]));
					begindate = String.valueOf(objs.get(i)[4]);
					enddate = String.valueOf(objs.get(i)[4]);
					/*
					 * dataMap.put("lunardate1", begindate.replace(" ", "") + "至" +
					 * enddate.replace(" ", ""));
					 */
					dataMap.put("lunardate1", begindate);
					dataMap.put("book", String.valueOf(objs.get(i)[5]));
					/*
					 * dataMap.put("lunardate2", "丁酉年　" + begindate.replace(" ", "")
					 * + "至" + enddate.replace(" ", ""));
					 */
					dataMap.put("lunardate2", begindate);
					dataMap.put("register", String.valueOf(objs.get(i)[6]));

					String[] name = String.valueOf(objs.get(i)[0]).split("#");
					if (name.length == 2) {
						// String[] name =
						// String.valueOf(objs.get(i)[5]).split("#");
						//防止下标越界，为-1
						if(name[0].equals("")||name[0].length()==0){
							throw new StringIndexOutOfBoundsException("");
//						resp.getWriter().write("要打印的牌位存在“历代宗亲”或“超度对象”未填写的情况");
							
						}
						String[] nameArr = name[0].substring(0,
								name[0].length() - 1).split(";");
						dataMap.put("na1", "");
						/*
						 * dataMap.put("prayingobj1", "");
						 * dataMap.put("prayingobj2", "");
						 */
						String prayingobj = String.valueOf(objs.get(i)[1]);

						dataMap.put("prayingobj3", prayingobj.replace(';', ' '));
						if (name[1].equals("1")) {// 6
							for (int k = 0; k < 8; k++) {
								if (k < nameArr.length) {
									dataMap.put("name" + (k + 1), nameArr[k]);
								} else {
									dataMap.put("name" + (k + 1), "");
								}

							}
							/*
							 * dataMap.put("name1", nameArr[0]);
							 * dataMap.put("name2", nameArr[1]);
							 * dataMap.put("name3", nameArr[2]);
							 * dataMap.put("name4", nameArr[3]);
							 * dataMap.put("name5", nameArr[4]);
							 * dataMap.put("name6", nameArr[5]);
							 */
							dataMap.put("name7", "");
							dataMap.put("name8", "");
							dataMap.put("wenzi1", "歷劫父母師長眷屬");
							dataMap.put("wenzi2", "有祀無祀孤魂等眾");
							dataMap.put("wenzi3", "十方法界冤親債主");
						} else {
							for (int k = 0; k < 8; k++) {
								if (k < nameArr.length) {
									dataMap.put("name" + (k + 1), nameArr[k]);
								} else {
									dataMap.put("name" + (k + 1), "");
								}

							}
							/*
							 * dataMap.put("name1", nameArr[0]);
							 * dataMap.put("name2", nameArr[1]);
							 * dataMap.put("name3", nameArr[2]);
							 * dataMap.put("name4", nameArr[3]);
							 * dataMap.put("name5", nameArr[4]);
							 * dataMap.put("name6", nameArr[5]);
							 * dataMap.put("name7", nameArr[6]);
							 * dataMap.put("name8", nameArr[7]);
							 */
							dataMap.put("wenzi1", "");
							dataMap.put("wenzi2", "");
							dataMap.put("wenzi3", "");
						}

					}
					if (name.length == 3) {// 5
					// String[] name = String.valueOf(objs.get(i)[5]).split("#");
						String[] nameArr = name[1].split(";");
						dataMap.put("na1", name[0]);
						/*
						 * dataMap.put("prayingobj1", "");
						 * dataMap.put("prayingobj2", "");
						 */
						String prayingobj = String.valueOf(objs.get(i)[1]);
						dataMap.put("prayingobj3", prayingobj.replace(';', ' '));
						if (name[2].equals("1")) {
							for (int k = 0; k < 8; k++) {
								if (k < nameArr.length) {
									dataMap.put("name" + (k + 1), nameArr[k]);
								} else {
									dataMap.put("name" + (k + 1), "");
								}

							}
							/*
							 * dataMap.put("name1", nameArr[0]);
							 * dataMap.put("name2", nameArr[1]);
							 * dataMap.put("name3", nameArr[2]);
							 * dataMap.put("name4", nameArr[3]);
							 * dataMap.put("name5", nameArr[4]);
							 * dataMap.put("name6", nameArr[5]);
							 * dataMap.put("name7", ""); dataMap.put("name8", "");
							 */
							dataMap.put("wenzi1", "歷劫父母師長眷屬");
							dataMap.put("wenzi2", "有祀無祀孤魂等眾");
							dataMap.put("wenzi3", "十方法界冤親債主");
						} else {
							for (int k = 0; k < 8; k++) {
								if (k < nameArr.length) {
									dataMap.put("name" + (k + 1), nameArr[k]);
								} else {
									dataMap.put("name" + (k + 1), "");
								}

							}
							/*
							 * dataMap.put("name1", nameArr[0]);
							 * dataMap.put("name2", nameArr[1]);
							 * dataMap.put("name3", nameArr[2]);
							 * dataMap.put("name4", nameArr[3]);
							 * dataMap.put("name5", nameArr[4]);
							 * dataMap.put("name6", nameArr[5]);
							 * dataMap.put("name7", nameArr[6]);
							 * dataMap.put("name8", nameArr[7]);
							 */
							dataMap.put("wenzi1", "");
							dataMap.put("wenzi2", "");
							dataMap.put("wenzi3", "");
						}
					}

					Template t = null;
					try {
						t = configuration.getTemplate("wenshuRealeaseModel_1.ftl");
						t.setEncoding("utf-8");
					} catch (IOException e) {
						e.printStackTrace();
					}
					String postfix = String.valueOf(ca.get(Calendar.YEAR))
							+ String.valueOf(objs.get(i)[2] + ".doc");
					postfixList += postfix + ";";
					nodepath = this.getClass().getClassLoader().getResource("/")
							.getPath();
					File outFile = new File(nodepath.substring(1,
							nodepath.length() - 16)
							+ "/webpage/c_gen_wenshu/"
							+ postfix);

					dataMap.put("serial", String.valueOf(objs.get(i)[2]));
					if (i == 0)
						first = Integer.parseInt(String.valueOf(objs.get(0)[2]));
					int length = objs.size() - 1;
					if (i == length)
						last = Integer
								.parseInt(String.valueOf(objs.get(length)[2]));

					Writer out = null;
					try {
						out = new BufferedWriter(new OutputStreamWriter(
								new FileOutputStream(outFile), "utf-8"));
					} catch (Exception e1) {
						e1.printStackTrace();
					}
					try {
						t.process(dataMap, out);
						out.close();
					} catch (TemplateException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					}

				}
				downloadPath = "/wpfws" + first + "-" + last + ".zip";
				// System.out.println(nodepath.substring(1, nodepath.length() - 16)
				// + "/webpage/c_gen_wenshu");
				generateZipFile(nodepath.substring(1, nodepath.length() - 16)
						+ "webpage/c_gen_wenshu");
				resp.getWriter().write("/webpage/c_gen_wenshu" + downloadPath);
			} catch (NumberFormatException e) {
				resp.getWriter().write("system error");
				e.printStackTrace();
			}catch (StringIndexOutOfBoundsException se){
				resp.getWriter().write("data error");
				se.printStackTrace();
			}

		} else {
			resp.getWriter().write("找不到适合条件的牌位");

		}

	}

	private void generateZipFile(String notepath) throws IOException {
		String sourceDirStr = notepath;
		String zipPath = notepath + downloadPath;
		// String zipPath = notepath + "/webpage/c_gen_paiwei/paiwei.zip";

		/**
		 * 
		 * @Modified By xiezhihui
		 * @Midified 2017-7-1 去掉多余的压缩文件
		 */
		File sourceDirectory = new File(sourceDirStr);
		File[] files = sourceDirectory.listFiles();
		ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(
				new FileOutputStream(zipPath), 1024));

		byte[] bs = new byte[1024]; // 缓冲数组
		int value = -1; // 文件是否结束标记

		for (File file : files) { // 遍历所有的文件
			zipOut.putNextEntry(new ZipEntry(file.getName())); // 存入文件名称，便于解压缩
			BufferedInputStream bfInput = new BufferedInputStream(
					new FileInputStream(file), 1024);
			while ((value = bfInput.read(bs, 0, bs.length)) != -1) {
				zipOut.write(bs, 0, value);
			}
			bfInput.close(); // 关闭缓冲输入流
		}
		zipOut.flush();
		zipOut.close();
	}

	private void deleteFile(String notepath) {
		File file = new File(notepath);
		File[] files = file.listFiles();
		System.out.println(files.length);
		for (int i = 0; i < files.length; i++) {
			File tmp = files[i];
			tmp.delete();
		}
	}

	@ResponseBody
	@RequestMapping(params = "getCount")
	private Map<String, String> getBtCount(String solardate) {
		// 随堂
		String stCountSql = "select count(*) from eveningpforr"
				+ " where flag=0 and size='随堂' and solardate = '" + solardate
				+ "'";
		// 包堂
		String btCountSql = "select count(*) from eveningpforr"
				+ " where flag=0 and size='包堂' and solardate = '" + solardate
				+ "'";

		Map<String, String> count = new HashMap<>();
		count.put("st", systemService.getCountForJdbc(stCountSql) + "");
		count.put("bt", systemService.getCountForJdbc(btCountSql) + "");

		return count;
	}
}
