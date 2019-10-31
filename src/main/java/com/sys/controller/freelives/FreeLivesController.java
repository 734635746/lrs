package com.sys.controller.freelives;

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

import org.antlr.grammar.v3.ANTLRv3Parser.element_return;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.entity.amitabhabirth.AmitabhabirthEntity;
import com.sys.entity.attendance.AttendanceEntity;
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.freelives.FreeLivesEntity;
import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.guanyinopen.GuanyinopenEntity;
import com.sys.entity.morningpforr.MorningpforrEntity;
import com.sys.entity.pravrajanamember.PravrajanamemberEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.entity.tmp_table.Tmp_tableEntity;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.freelives.FreeLivesServiceI;
import com.sys.service.morningpforr.MorningpforrServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
import test.TabletStat;

/**
 * @Title: Controller
 * @Description: 放生管理管理
 * @author kooking
 * @date 2018-7-06 20:37:58
 * @version V1.0
 *
 */
@Controller
@RequestMapping("/freeLivesController")
public class FreeLivesController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(FreeLivesController.class);

	@Autowired
	private FreeLivesServiceI freeLivesService;
	@Autowired
	private DoritualinfoServiceI doritualinfoService;
	@Autowired
	private ReceiptServiceI receiptService;
	@Autowired
	private ReceiptnoServiceI receiptnoService;
	@Autowired
	private SystemService systemService;
	private String message;
	private int first;
	private int last;
	private String downloadPath;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	/**
	 * 早普佛管理列表 查询页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "freeLives")
	public ModelAndView freeLives(HttpServletRequest request) {
		return new ModelAndView("com/sys/freelives/FreeLivesList");
	}

	/**
	 * 早普佛管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "info")
	public ModelAndView Info(HttpServletRequest request) {
		return new ModelAndView("com/sys/freelives/doritualinfoList");
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
	public void datagrid(FreeLivesEntity freelives, HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(FreeLivesEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, freelives,
				request.getParameterMap());
		this.freeLivesService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除早普佛管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(FreeLivesEntity freelives, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		freelives = systemService.getEntity(FreeLivesEntity.class, freelives.getId());
		message = "放生管理删除成功";
		freeLivesService.delete(freelives);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加早普佛管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(FreeLivesEntity freelives, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(freelives.getId())) {
			message = "放生管理更新成功";
			FreeLivesEntity t = freeLivesService.get(FreeLivesEntity.class, freelives.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(freelives, t);
				freeLivesService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "放生管理更新失败";
			}
		} else {
			message = "放生管理添加成功";
			freeLivesService.save(freelives);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 早普佛管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(FreeLivesEntity freelives, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(freelives.getId())) {
			freelives = freeLivesService.getEntity(FreeLivesEntity.class, freelives.getId());
			req.setAttribute("morningpforrPage", freelives);
		}
		return new ModelAndView("com/sys/freelives/FreeLives");
	}

	//2017-9-27修改
	@RequestMapping(params = "forwardSelectRecord")
	public ModelAndView forwardSelectRecord(String id, HttpServletRequest req) {
		
		if(id != null && !id.equals("")){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id);
			req.setAttribute("doritualinfoEntity", doritualinfoEntity);
			CriteriaQuery cq = new CriteriaQuery(FreeLivesEntity.class);
			cq.add(Restrictions.eq("doritualid", id));

			List<FreeLivesEntity> freelivesEntityList = new ArrayList<FreeLivesEntity>();
			freelivesEntityList = freeLivesService.getListByCriteriaQuery(cq, false);

			req.setAttribute("freelivesEntityList", freelivesEntityList);
		}
		return new ModelAndView("com/sys/freelives/showFreeLivesRecord");
	}

	

	// 2017年8月2号修改
	@RequestMapping(params = "ToEditFreeLives")
	public ModelAndView ToEditFreeLives(DoritualinfoEntity doritualinfo, HttpServletRequest req, Model model) {
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
		} else if (StringUtil.isNotEmpty(doritualinfo.getId())){// 更新
			Date nowTime = new Date();
			SimpleDateFormat time = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			String registertime = time.format(nowTime);
			doritualinfo.setRegistertime(registertime);
			doritualinfoService.updateEntitie(doritualinfo);

		}
			
		id = doritualinfo.getId();
//		---kooking 20180402		
		String morningPforrIds = req.getParameter("morningPforrId");
		DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id);
		// 添加做法事人的地址
		model.addAttribute("doritualinfoAddress",
				doritualinfoEntity.getAddress());
		model.addAttribute("updateFlag", "0");
		List<FreeLivesEntity> freelivesEntitys = new ArrayList<FreeLivesEntity>();
		
		if (morningPforrIds == null) {
			
			
			freelivesEntitys = null;
			model.addAttribute("id", id);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			model.addAttribute("morningpforrEntitys", freelivesEntitys);
			return new ModelAndView("com/sys/freelives/editFreeLives");
		} else {
			// 已经有数据
			String morningPforrId[] = morningPforrIds.substring(0, morningPforrIds.length() - 1).split(",");
			
			List<Map<String, String>> counts = new ArrayList<>();

			// 分别获取相关数据
			// 随堂
			String stCountSql = "";
			// 包堂
			String btCountSql = "";
			Long st,bt;
			for (int i = 0; i < morningPforrId.length; i++) {
				System.out.println(morningPforrId[i]);
				FreeLivesEntity entity = freeLivesService.get(FreeLivesEntity.class, morningPforrId[i]);
				freelivesEntitys.add(entity);
				
				stCountSql = "select count(*) from freelives" + " where flag=0 and size='随堂' and solardate = '"
								+ entity.getSolardate() + "'";
				btCountSql = "select count(*) from freelives" + " where flag=0 and size='包堂' and solardate = '"
								+ entity.getSolardate() + "'";
				
				st = systemService.getCountForJdbc(stCountSql);
				bt = systemService.getCountForJdbc(btCountSql);
				Map<String, String> map = new HashMap<>();
				map.put("st", st + "");
				map.put("bt", bt + "");
				counts.add(map);
			}

			model.addAttribute("morningpforrEntitys", freelivesEntitys);
			model.addAttribute("counts", counts);
			model.addAttribute("id", id);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			return new ModelAndView("com/sys/freelives/editFreeLives");

		}

	}


	@RequestMapping(params = "getSerialAndSaveTablet") /*2017年 8月3号改动 */
	public ModelAndView getSerial(HttpServletRequest req, Model model) {
		String morningpforrid = req.getParameter("morningpforrid");
		// 修改记录
		if (morningpforrid != null && !morningpforrid.equals("")) {
			FreeLivesEntity freeLivesEntity = freeLivesService.get(FreeLivesEntity.class, morningpforrid);
			// 获取做法事人的ID
			String id0 = req.getParameter("id");
			// 获取祈福者
			String prayingobj = req.getParameter("prayingobj");
			// 获取祈福对象
			String livingmember = req.getParameter("livingmenber");
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
			// 获取类型
			String type = req.getParameter("type");

			// 公历
			String solardate = req.getParameter("solardate");
			// 农历
			String lunardate = req.getParameter("lunardate");

			/*// 获取当前时间
			Calendar cal = Calendar.getInstance();
			int year = cal.get(Calendar.YEAR);
			String currentyear = String.valueOf(year);

			// 获取序号
			CriteriaQuery cq = new CriteriaQuery(MorningpforrEntity.class);
			cq.add(Restrictions.like("registertime", currentyear, MatchMode.START));
			List<MorningpforrEntity> morningpforrEntitys = morningpforrService.getListByCriteriaQuery(cq, false);

			String ids = "";

			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(currentTime);*/

			freeLivesEntity.setAddress(address);
			freeLivesEntity.setLivingmenber(livingmember);
			freeLivesEntity.setMoney(Integer.valueOf(money));
			freeLivesEntity.setPayway(payway);
			freeLivesEntity.setSummary(summary);
			freeLivesEntity.setPrayingobj(prayingobj);
			freeLivesEntity.setPaymen(paymen);
			freeLivesEntity.setBook(book);
			freeLivesEntity.setLunardate(lunardate);
			freeLivesEntity.setSolardate(solardate);
			freeLivesEntity.setDoritualid(id0);
			freeLivesEntity.setSize(type);
			// 换时间
			freeLivesEntity.setFlag(0);
			/*NumberFormat f = new DecimalFormat("000000");
			morningpforrEntity.setSerial(f.format(morningpforrEntitys.size() + 1));
			morningpforrEntity.setRegistertime(dateString);*/

			freeLivesService.updateEntitie(freeLivesEntity);

			model.addAttribute("message", "放生信息修改成功！！");
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
			String[] livingmember = req.getParameterValues("livingmenber");
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
			// 获取类型
			String type = req.getParameter("type");

			/*Calendar cal = Calendar.getInstance();
			int day = cal.get(Calendar.DAY_OF_MONTH);
			NumberFormat f = new DecimalFormat("00");
			String currentDay = f.format(day);*/

//			List<MorningpforrEntity> morningpforrEntities = new ArrayList<MorningpforrEntity>();
			String ids = "";
			String[] antoserial=new String[money.length];

			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
			String dateString = formatter.format(currentTime);

			for (int i = 0; i < money.length; i++) {
				FreeLivesEntity freeLivesEntity = new FreeLivesEntity();
				
				// 获取数目，用于序列号
				CriteriaQuery cq = new CriteriaQuery(FreeLivesEntity.class);
				cq.add(Restrictions.and(/* Restrictions.eq("size",size[i]), */Restrictions.like("solardate",
						solardates[i], MatchMode.ANYWHERE )));
				List<FreeLivesEntity> freelives = freeLivesService.getListByCriteriaQuery(cq, false);

				freeLivesEntity.setAddress(address[i]);
				freeLivesEntity.setLivingmenber(livingmember[i]);
				freeLivesEntity.setMoney(Integer.valueOf(money[i]));
				freeLivesEntity.setPayway(payway[i]);
				freeLivesEntity.setSummary(summary[i]);
				freeLivesEntity.setPrayingobj(prayingobj[i]);
				freeLivesEntity.setDoritualid(id0);
				// guanyinopenEntity.setSize(size[i]);
				freeLivesEntity.setPaymen(paymen);
				freeLivesEntity.setBook(book[i]);
				freeLivesEntity.setSize(type);
				freeLivesEntity.setSolardate(solardates[i]);
				freeLivesEntity.setLunardate(lunardates[i]);

				// 换时间
				freeLivesEntity.setFlag(0);
				NumberFormat nf = new DecimalFormat("000000");
				freeLivesEntity.setSerial(nf.format(freelives.size() + 1));
				freeLivesEntity.setRegistertime(dateString);
//				morningpforrEntities.add(morningpforrEntity);

				freeLivesService.save(freeLivesEntity);
				ids += freeLivesEntity.getId() + ",";
				antoserial[i]=freeLivesEntity.getSerial();

			}
//			req.setAttribute("morningpforrEntities", morningpforrEntities);
//			req.setAttribute("ids", ids);
//			return new ModelAndView("com/sys/morningpforr/autoSerialMorningPforr");

//------------20180502 Kooking		
			return updateMorningpforrAndReceipt(ids, antoserial, model);
//------------20180502 Kooking
			
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			return new ModelAndView("com/sys/error");
		}

	}

	/*@RequestMapping(params = "updateMorningpforrAndReceipt")
	public ModelAndView updateMorningpforrAndReceipt(HttpServletRequest req, Model model) {*/
	private ModelAndView updateMorningpforrAndReceipt(String ids,String[] antoserial, Model model) {

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
			ReceiptnoEntity reNo = receiptnoService
					.findByProperty(ReceiptnoEntity.class, "year", Integer.valueOf(a.get(Calendar.YEAR))).get(0);
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
				FreeLivesEntity te = freeLivesService.get(FreeLivesEntity.class, id[i]);
				te.setAutoserial(antoserial[i]);
				te.setRegistertime(dateString);
				te.setRegistrant(user.getRealName());
				te.setReceiptno(re.getNo());
				freeLivesService.updateEntitie(te);

				sum += te.getMoney();
				obj += te.getLivingmenber();
				if(te.getSummary() != null && !te.getSummary().trim().equals("")){
					sumSummary += te.getSummary() + "#";
				}
				
				if(i!=id.length-1){
					res += te.getAutoserial() + ",";
				}else{
					res += te.getAutoserial();
				}
				
			}

			FreeLivesEntity entity = freeLivesService.get(FreeLivesEntity.class, id[0]);

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

			re.setRitualtype("早普佛");
			re.setRegistertime(dateString);
			re.setPurpose("早普佛");
			re.setFlag(0);
			receiptService.save(re);
			String receipId = re.getId();

			for (int i = 0; i < id.length; i++) {
				FreeLivesEntity te = freeLivesService.get(FreeLivesEntity.class, id[i]);
				te.setReceiptid(receipId);
				freeLivesService.updateEntitie(te);
			}

			model.addAttribute("message", "早普佛登记成功");
			model.addAttribute("ritualtype", "早普佛");
			ReceiptEntity returnRe = receiptService.get(ReceiptEntity.class, re.getId());
			model.addAttribute("returnRe", returnRe);
			String bigMoney = ChineseCurrency.toChineseCurrency(new Double(returnRe.getMoney()));
			model.addAttribute("bigMoney", bigMoney);
			String smallMoney = ChineseCurrency.toSmall(new Double(returnRe.getMoney()));
			model.addAttribute("smallMoney", smallMoney);
			return new ModelAndView("com/sys/success");
		} catch (Exception ex) {
			ex.printStackTrace();
			logger.error(ex);
			return new ModelAndView("com/sys/error");
		}

	}

	/**
	 * 修改早普佛记录
	 * 
	 * @param guanyinopenEntity
	 * @param request
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(FreeLivesEntity freelivesEntity, HttpServletRequest request, Model model) {
		List<FreeLivesEntity> freelivesEntitys = new ArrayList<FreeLivesEntity>();
		if (StringUtil.isNotEmpty(freelivesEntity.getId())) {
			freelivesEntity = freeLivesService.getEntity(FreeLivesEntity.class, freelivesEntity.getId());

			TSUser user = ResourceUtil.getSessionUserName();
			if (!user.getRealName().equals(freelivesEntity.getRegistrant())) {
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			freelivesEntitys.add(freelivesEntity);

			model.addAttribute("morningpforrEntity", freelivesEntity);

			model.addAttribute("morningpforrEntitys", freelivesEntitys);
			model.addAttribute("id", freelivesEntity.getDoritualid());
			model.addAttribute("buddhabirthid", freelivesEntity.getId());
			model.addAttribute("clientele", freelivesEntity.getPaymen());
			model.addAttribute("size", freelivesEntity.getSize());
			return new ModelAndView("com/sys/freelives/editFreeLives");
		} else {
			return new ModelAndView("com/sys/error");
		}
	}
	

	/**
	 * 加载早普佛统计表
	 * @author xiezh
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "fowardFreeLives")
	public ModelAndView fowardFreeLives(HttpServletRequest req) {
		return new ModelAndView("com/sys/freelives/FreeLivesindex");
	}
	/**
	 * 跳转到统计信息
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	/*@RequestMapping(params = "tabletcount")
	
	public ModelAndView tabletcount(HttpServletRequest req) throws UnsupportedEncodingException {
		Calendar ca = Calendar.getInstance();
		String year = String.valueOf(ca.get(Calendar.YEAR));
		List<TabletStat> tablet = new ArrayList<TabletStat>();
		// 随堂
		String stNotPrint = "select count(*) from freelives" + " where flag=0 and size='随堂' and registertime like '"
				+ year + "%'";
		String stAlreadyPrint = "select count(*) from freelives"
				+ " where flag=1 and size='随堂' and registertime like '" + year + "%'";
		// 包堂
		String btNotPrint = "select count(*) from freelives" + " where flag=0 and size='包堂' and registertime like '"
				+ year + "%'";
		String btAlreadyPrint = "select count(*) from freelives"
				+ " where flag=1 and size='包堂' and registertime like '" + year + "%'";
		// 数目
		Long stNotPrintCount = systemService.getCountForJdbc(stNotPrint);
		Long stAlreadyPrintCount = systemService.getCountForJdbc(stAlreadyPrint);
		Long btNotPrintCount = systemService.getCountForJdbc(btNotPrint);
		Long btAlreadyPrintCount = systemService.getCountForJdbc(btAlreadyPrint);

		TabletStat st = new TabletStat();
		st.setRitualtype("放生");
		st.setSize("随堂");
		st.setNotprint(stNotPrintCount);
		st.setAlreadyprint(stAlreadyPrintCount);
		tablet.add(st);

		TabletStat bt = new TabletStat();
		bt.setRitualtype("放生");
		bt.setSize("包堂");
		bt.setNotprint(btNotPrintCount);
		bt.setAlreadyprint(btAlreadyPrintCount);
		tablet.add(bt);

		req.setAttribute("name", "放生");
		req.setAttribute("tablet", tablet);

		return new ModelAndView("com/sys/tabletstat/tabletcount");
		
	}
*/
	/**
	 * (直接)跳转到打印
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	/*@RequestMapping(params = "forwardPrint")
	public ModelAndView forwardPrint(HttpServletRequest req) throws UnsupportedEncodingException {
		return new ModelAndView("com/sys/freelives/print");
	}*/

	
	
	/**
	* @Title: printFreeLives
	* @Description: 打印放生名单
	* @param req
	* @param resp
	* @throws IOException   
	* @return void    返回类型
	* @throws
	*/ 
	@RequestMapping(params = "printFreeLives")
	public void printFreeLives(HttpServletRequest req, HttpServletResponse resp) throws IOException {
		deleteFile(this.getClass().getClassLoader().getResource("/").getPath().substring(1,
				this.getClass().getClassLoader().getResource("/").getPath().length() - 16)+ "/webpage/c_gen_fangsheng/" );
		String starttime = req.getParameter("solarstart");
//		String endtime = req.getParameter("solarend");
		String money=req.getParameter("money");
		String lunarDate=req.getParameter("lunardate");
		
		//只截取月日
		lunarDate=lunarDate.substring(5, lunarDate.length());

		//每个word文档打印的最大记录数
		int maxSize=56;
//		TSUser user = ResourceUtil.getSessionUserName();

		Calendar ca = Calendar.getInstance();
//		String year = String.valueOf(ca.get(Calendar.YEAR));
		String sql = "select serial,livingmenber from freelives  where solardate = '" + starttime + "' and money >= '" +money
				+ "';";

		System.out.println(sql);
		List<Object[]> objs = new ArrayList<Object[]>();
		objs = systemService.findListbySql(sql);

		if (objs.size() != 0) {
			Map<String, String> dataMap = null;
			String nodepath = "";
			nodepath = this.getClass().getClassLoader().getResource("/").getPath();
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
			configuration.setServletContextForTemplateLoading(req.getSession().getServletContext(),
					"/webpage/c_paiweiModel");

			try {
				int tmps_size = objs.size();
				int objsSize=tmps_size;
				first = Integer.parseInt(String.valueOf(objs.get(0)[0]));
				last = Integer.parseInt(String.valueOf(objs.get(tmps_size - 1)[0]));
				downloadPath = "/fsjl" + first + "-" + last + ".zip";
				for (int i = 0; i < objsSize; i += maxSize) {
//					String postfixList = "";
					String postfixfs = String.valueOf(ca.get(Calendar.YEAR))
							+ String.valueOf(objs.get(i)[0] + ".doc");
//					postfixList += postfixqifu + ";";
					nodepath = this.getClass().getClassLoader().getResource("/").getPath();
					File outFileqifu = new File(
							nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_fangsheng/" + postfixfs);
					Writer outqifu = null;
					try {
						outqifu = new BufferedWriter(
								new OutputStreamWriter(new FileOutputStream(outFileqifu), "utf-8"));
					} catch (Exception e1) {
						e1.printStackTrace();
					}

					List<Object[]>  subList = null;

					if (tmps_size - maxSize >= 0) {
						subList = objs.subList(i, i + maxSize);// 每次插入56条记录
						dataMap = getQifuBigMap(subList, lunarDate,maxSize);
						tmps_size -= maxSize;
					} else {// 剩余不足56条
						subList = objs.subList(i, objsSize);
						dataMap = getQifuBigMap(subList, lunarDate,maxSize);
					}

					Template t = null;
					try {
						t = configuration.getTemplate("fstemp.ftl");
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

			generateZipFile(nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_fangsheng/");
			resp.getWriter().write("/webpage/c_gen_fangsheng/" + downloadPath);

		} else {

			resp.getWriter().write("找不到适合条件的放生记录");

		}

	}

	// 将放生记录填入模板对应位置
	private Map<String, String> getQifuBigMap(List<Object[]> objs, String date,int maxsize) {
		Map<String, String> dataMap = new HashMap<>();
		int count=objs.size();
		//时间
		dataMap.put("time", date);
		
		for (int i = 0; i < maxsize; i++) {
			if(i<count){
				dataMap.put("n" + (i+1) , String.valueOf(objs.get(i )[1]));
			}else {
				//使用Template.process(Object rootMap, Writer out)方法要注意：
				//模板中所有的el表达式都必须有内容
				//这里不够用空格" "代替
				dataMap.put("n" + (i+1) , " ");
			}
			
			
		}
		return dataMap;
	}

	//压缩文件夹内所有文件
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
		ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath), 1024));

		byte[] bs = new byte[1024]; // 缓冲数组
		int value = -1; // 文件是否结束标记

		for (File file : files) { // 遍历所有的文件
			zipOut.putNextEntry(new ZipEntry(file.getName())); // 存入文件名称，便于解压缩
			BufferedInputStream bfInput = new BufferedInputStream(new FileInputStream(file), 1024);
			while ((value = bfInput.read(bs, 0, bs.length)) != -1) {
				zipOut.write(bs, 0, value);
			}
			bfInput.close(); // 关闭缓冲输入流
		}
		zipOut.flush();
		zipOut.close();
	}
	
	private void generateWenshuZipFile(String notepath) throws IOException {
		String sourceDirStr = notepath  ;
		String zipPath = notepath + downloadPath;
		// String zipPath = notepath + "/webpage/c_gen_paiwei/paiwei.zip";

		/**
		 * 
		 * @Modified By xiezhihui
		 * @Midified 2017-7-1 去掉多余的压缩文件
		 */
		File sourceDirectory = new File(sourceDirStr);
		File[] files = sourceDirectory.listFiles();
		ZipOutputStream zipOut = new ZipOutputStream(new BufferedOutputStream(new FileOutputStream(zipPath), 1024));

		byte[] bs = new byte[1024]; // 缓冲数组
		int value = -1; // 文件是否结束标记

		for (File file : files) { // 遍历所有的文件
			zipOut.putNextEntry(new ZipEntry(file.getName())); // 存入文件名称，便于解压缩
			BufferedInputStream bfInput = new BufferedInputStream(new FileInputStream(file), 1024);
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
	private Map<String, String> getBtCount(String solardate){
		// 随堂
		String stCountSql = "select count(*) from freelives" + " where flag=0 and size='随堂' and solardate = '"
						+ solardate + "'";
		// 包堂
		String btCountSql = "select count(*) from freelives" + " where flag=0 and size='包堂' and solardate = '"
						+ solardate + "'";
		
		Map<String, String> count = new HashMap<>();
		count.put("st", systemService.getCountForJdbc(stCountSql) + "");
		count.put("bt", systemService.getCountForJdbc(btCountSql) + "");
		
		return count;
	}
}
