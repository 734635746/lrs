package com.sys.controller.prayguanyin;

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

import test.TabletStat;

import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.morningpforr.MorningpforrEntity;
import com.sys.entity.prayguanyin.PrayguanyinEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.prayguanyin.PrayguanyinServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**
 * @Title: Controller
 * @Description: 上供管理
 * @author zhangdaihao
 * @date 2016-10-10 16:48:02
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/prayguanyinController")
public class PrayguanyinController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(PrayguanyinController.class);

	@Autowired
	private PrayguanyinServiceI prayguanyinService;
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
	 * 上供管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "prayguanyin")
	public ModelAndView prayguanyin(HttpServletRequest request) {
		return new ModelAndView("com/sys/prayguanyin/prayguanyinList");
	}

	/**
	 * 上供管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "info")
	public ModelAndView Info(HttpServletRequest request) {
		return new ModelAndView("com/sys/prayguanyin/doritualinfoList");
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
	public void datagrid(PrayguanyinEntity prayguanyin,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PrayguanyinEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				prayguanyin, request.getParameterMap());
		this.prayguanyinService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除上供管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(PrayguanyinEntity prayguanyin,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		prayguanyin = systemService.getEntity(PrayguanyinEntity.class,
				prayguanyin.getId());
		message = "上供管理删除成功";
		prayguanyinService.delete(prayguanyin);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加上供管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(PrayguanyinEntity prayguanyin,
			HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(prayguanyin.getId())) {
			message = "上供管理更新成功";
			PrayguanyinEntity t = prayguanyinService.get(
					PrayguanyinEntity.class, prayguanyin.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(prayguanyin, t);
				prayguanyinService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "上供管理更新失败";
			}
		} else {
			message = "上供管理添加成功";
			prayguanyinService.save(prayguanyin);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 上供管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(PrayguanyinEntity prayguanyin,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(prayguanyin.getId())) {
			prayguanyin = prayguanyinService.getEntity(PrayguanyinEntity.class,
					prayguanyin.getId());
			req.setAttribute("prayguanyinPage", prayguanyin);
		}
		return new ModelAndView("com/sys/prayguanyin/prayguanyin");
	}

	@RequestMapping(params = "forwardSelectRecord")
	public ModelAndView forwardSelectRecord(String id, HttpServletRequest req) {
		if(id != null && !id.equals("")){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id);
			req.setAttribute("doritualinfoEntity", doritualinfoEntity);
			CriteriaQuery cq = new CriteriaQuery(PrayguanyinEntity.class);
			cq.add(Restrictions.eq("doritualid", id));

			List<PrayguanyinEntity> morningpforrEntityList = new ArrayList<PrayguanyinEntity>();
			morningpforrEntityList = prayguanyinService.getListByCriteriaQuery(cq, false);

			req.setAttribute("morningpforrEntityList", morningpforrEntityList);
		}
		return new ModelAndView("com/sys/prayguanyin/showPrayguanyinRecord");
	}

	
	/**
	* @Title: ToEditPrayGuanyin
	* @Description: 跳转到登记页面
	* @param doritualinfo
	* @param req
	* @param model
	* @return   
	* @return ModelAndView    返回类型
	* @throws
	*/ 
	@RequestMapping(params = "ToEditPrayGuanyin")
	public ModelAndView ToEditPrayGuanyin(DoritualinfoEntity doritualinfo, HttpServletRequest req,
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

		//添加做法事人的地址
		model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
		model.addAttribute("updateFlag", "0");
		List<PrayguanyinEntity> morningpforrEntitys = new ArrayList<PrayguanyinEntity>();
		
		if (morningPforrIds == null) {
			
			
			morningpforrEntitys = null;
			model.addAttribute("id", id);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			model.addAttribute("morningpforrEntitys", morningpforrEntitys);
			return new ModelAndView("com/sys/prayguanyin/editPrayGuanyin");
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
				PrayguanyinEntity entity = prayguanyinService.get(PrayguanyinEntity.class, morningPforrId[i]);
				morningpforrEntitys.add(entity);
				
				stCountSql = "select count(*) from prayguanyin" + " where flag=0 and size='随堂' and solardate = '"
								+ entity.getSolardate() + "'";
				btCountSql = "select count(*) from prayguanyin" + " where flag=0 and size='包堂' and solardate = '"
								+ entity.getSolardate() + "'";
				
				st = systemService.getCountForJdbc(stCountSql);
				bt = systemService.getCountForJdbc(btCountSql);
				Map<String, String> map = new HashMap<>();
				map.put("st", st + "");
				map.put("bt", bt + "");
				counts.add(map);
			}

			model.addAttribute("morningpforrEntitys", morningpforrEntitys);
			model.addAttribute("counts", counts);
			model.addAttribute("id", id);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			return new ModelAndView("com/sys/prayguanyin/editPrayGuanyin");

		}
	}

	@RequestMapping(params = "getSerialAndSaveTablet")
	/* 8月17号改动 */
	public ModelAndView getSerial(HttpServletRequest req, Model model) {
		String morningpforrid = req.getParameter("morningpforrid");
		// 修改记录
		if (morningpforrid != null && !morningpforrid.equals("")) {
			PrayguanyinEntity morningpforrEntity = prayguanyinService.get(PrayguanyinEntity.class, morningpforrid);
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

			morningpforrEntity.setAddress(address);
			morningpforrEntity.setLivingmenber(livingmember);
			morningpforrEntity.setMoney(Integer.valueOf(money));
			morningpforrEntity.setPayway(payway);
			morningpforrEntity.setSummary(summary);
			morningpforrEntity.setPrayingobj(prayingobj);
			morningpforrEntity.setPaymen(paymen);
			morningpforrEntity.setBook(book);
			morningpforrEntity.setLunardate(lunardate);
			morningpforrEntity.setSolardate(solardate);
			morningpforrEntity.setDoritualid(id0);
			morningpforrEntity.setSize(type);
			// 换时间
			morningpforrEntity.setFlag(0);
			prayguanyinService.updateEntitie(morningpforrEntity);

			model.addAttribute("message", "上供修改成功！！");
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

//			List<PrayguanyinEntity> morningpforrEntities = new ArrayList<PrayguanyinEntity>();
			String ids = "";
			String[] antoserial=new String[money.length];
			
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
			String dateString = formatter.format(currentTime);

			for (int i = 0; i < money.length; i++) {
				PrayguanyinEntity morningpforrEntity = new PrayguanyinEntity();
				
				// 获取该举办日期的上供数目，用于序列号
				CriteriaQuery cq = new CriteriaQuery(PrayguanyinEntity.class);
				cq.add(Restrictions.and(/* Restrictions.eq("size",size[i]), */Restrictions.like("solardate",
						solardates[i], MatchMode.ANYWHERE )));
				List<PrayguanyinEntity> morningpforrs = prayguanyinService.getListByCriteriaQuery(cq, false);

				morningpforrEntity.setAddress(address[i]);
				morningpforrEntity.setLivingmenber(livingmember[i]);
				morningpforrEntity.setMoney(Integer.valueOf(money[i]));
				morningpforrEntity.setPayway(payway[i]);
				morningpforrEntity.setSummary(summary[i]);
				morningpforrEntity.setPrayingobj(prayingobj[i]);
				morningpforrEntity.setDoritualid(id0);
				// guanyinopenEntity.setSize(size[i]);
				morningpforrEntity.setPaymen(paymen);
				morningpforrEntity.setBook(book[i]);
				morningpforrEntity.setSize(type);
				morningpforrEntity.setSolardate(solardates[i]);
				morningpforrEntity.setLunardate(lunardates[i]);

				// 换时间
				morningpforrEntity.setFlag(0);
				NumberFormat nf = new DecimalFormat("000000");
				morningpforrEntity.setSerial(nf.format(morningpforrs.size() + 1));
				morningpforrEntity.setRegistertime(dateString);
//				morningpforrEntities.add(morningpforrEntity);

				prayguanyinService.save(morningpforrEntity);
				ids += morningpforrEntity.getId() + ",";
				antoserial[i]=morningpforrEntity.getSerial();
				
			}
//			req.setAttribute("morningpforrEntities", morningpforrEntities);
//			req.setAttribute("ids", ids);
//			return new ModelAndView("com/sys/prayguanyin/autoSerialPrayGuanyin");
			
//------------20180502 Kooking		
			return updatePrayGuanyinAndReceipt(ids, antoserial, model);
//------------20180502 Kooking
			
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
	}

	
	/*@RequestMapping(params = "updatePrayGuanyinAndReceipt")
	public ModelAndView updatePrayGuanyinAndReceipt(HttpServletRequest req,
			Model model) {*/
	private ModelAndView updatePrayGuanyinAndReceipt(String ids,String[] antoserial,
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
				PrayguanyinEntity te = prayguanyinService.get(PrayguanyinEntity.class, id[i]);
				te.setAutoserial(antoserial[i]);
				te.setRegistertime(dateString);
				te.setRegistrant(user.getRealName());
				te.setReceiptno(re.getNo());
				prayguanyinService.updateEntitie(te);

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

			PrayguanyinEntity entity = prayguanyinService.get(PrayguanyinEntity.class, id[0]);

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

			re.setRitualtype("上供");
			re.setRegistertime(dateString);
			re.setPurpose("上供");
			re.setFlag(0);
			receiptService.save(re);
			String receipId = re.getId();

			for (int i = 0; i < id.length; i++) {
				PrayguanyinEntity te = prayguanyinService.get(PrayguanyinEntity.class, id[i]);
				te.setReceiptid(receipId);
				prayguanyinService.updateEntitie(te);
			}

			model.addAttribute("message", "上供登记成功");
			model.addAttribute("ritualtype", "上供");
			ReceiptEntity returnRe = receiptService.get(ReceiptEntity.class, re.getId());
			model.addAttribute("returnRe", returnRe);
			String bigMoney = ChineseCurrency.toChineseCurrency(new Double(returnRe.getMoney()));
			model.addAttribute("bigMoney", bigMoney);
			String smallMoney = ChineseCurrency.toSmall(new Double(returnRe.getMoney()));
			model.addAttribute("smallMoney", smallMoney);
			return new ModelAndView("com/sys/success");
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}

	}
	
	
	/**
	* @Title: myUpdate
	* @Description: 修改上供记录
	* @param morningpforrEntity
	* @param request
	* @param model
	* @return   
	* @return ModelAndView    返回类型
	* @throws
	*/ 
	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(PrayguanyinEntity prayguanyinEntity, HttpServletRequest request, Model model) {
		List<PrayguanyinEntity> prayguanyinEntitys = new ArrayList<PrayguanyinEntity>();
		if (StringUtil.isNotEmpty(prayguanyinEntity.getId())) {
			prayguanyinEntity = prayguanyinService.getEntity(PrayguanyinEntity.class, prayguanyinEntity.getId());

			TSUser user = ResourceUtil.getSessionUserName();
			if (!user.getRealName().equals(prayguanyinEntity.getRegistrant())) {
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			prayguanyinEntitys.add(prayguanyinEntity);

			model.addAttribute("morningpforrEntity", prayguanyinEntity);

			model.addAttribute("morningpforrEntitys", prayguanyinEntitys);
			model.addAttribute("id", prayguanyinEntity.getDoritualid());
			model.addAttribute("buddhabirthid", prayguanyinEntity.getId());
			model.addAttribute("clientele", prayguanyinEntity.getPaymen());
			model.addAttribute("size", prayguanyinEntity.getSize());
			return new ModelAndView("com/sys/prayguanyin/editPrayGuanyin");
		} else {
			return new ModelAndView("com/sys/error");
		}
	}
	
	
	/**
	 * 获取包堂、随堂数目
	 * @param solardate
	 * @return
	 */
	@ResponseBody
	@RequestMapping(params = "getCount")
	private Map<String, String> getBtCount(String solardate){
		// 随堂
		String stCountSql = "select count(*) from prayguanyin" + " where flag=0 and size='随堂' and solardate = '"
						+ solardate + "'";
		// 包堂
		String btCountSql = "select count(*) from prayguanyin" + " where flag=0 and size='包堂' and solardate = '"
						+ solardate + "'";
		
		Map<String, String> count = new HashMap<>();
		count.put("st", systemService.getCountForJdbc(stCountSql) + "");
		count.put("bt", systemService.getCountForJdbc(btCountSql) + "");
		
		return count;
	}
	
	/**
	 * 加载上供统计表
	 * @author xiezh
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "fowardPrayguanyin")
	public ModelAndView fowardMorningforr(HttpServletRequest req) {
		return new ModelAndView("com/sys/prayguanyin/prayguanyinindex");
	}
	
	/**
	 * 跳转到统计信息
	 * 
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException
	 */
	@RequestMapping(params = "tabletcount")
	public ModelAndView tabletcount(HttpServletRequest req) throws UnsupportedEncodingException {
		Calendar ca = Calendar.getInstance();
		String year = String.valueOf(ca.get(Calendar.YEAR));
		List<TabletStat> tablet = new ArrayList<TabletStat>();
		// 随堂
		String stNotPrint = "select count(*) from prayguanyin" + " where flag=0 and size='随堂' and registertime like '"
				+ year + "%'";
		String stAlreadyPrint = "select count(*) from prayguanyin"
				+ " where flag=1 and size='随堂' and registertime like '" + year + "%'";
		// 包堂
		String btNotPrint = "select count(*) from prayguanyin" + " where flag=0 and size='包堂' and registertime like '"
				+ year + "%'";
		String btAlreadyPrint = "select count(*) from prayguanyin"
				+ " where flag=1 and size='包堂' and registertime like '" + year + "%'";
		// 数目
		Long stNotPrintCount = systemService.getCountForJdbc(stNotPrint);
		Long stAlreadyPrintCount = systemService.getCountForJdbc(stAlreadyPrint);
		Long btNotPrintCount = systemService.getCountForJdbc(btNotPrint);
		Long btAlreadyPrintCount = systemService.getCountForJdbc(btAlreadyPrint);

		TabletStat st = new TabletStat();
		st.setRitualtype("上供");
		st.setSize("随堂");
		st.setNotprint(stNotPrintCount);
		st.setAlreadyprint(stAlreadyPrintCount);
		tablet.add(st);

		TabletStat bt = new TabletStat();
		bt.setRitualtype("上供");
		bt.setSize("包堂");
		bt.setNotprint(btNotPrintCount);
		bt.setAlreadyprint(btAlreadyPrintCount);
		tablet.add(bt);

		req.setAttribute("name", "上供");
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
	public ModelAndView forwardPrint(HttpServletRequest req) throws UnsupportedEncodingException {
		return new ModelAndView("com/sys/prayguanyin/print");
	}
	
	/**
	 * 打印文疏
	 * @param req
	 * @param resp
	 * @param model
	 * @throws IOException
	 */
	@RequestMapping(params = "printPrayguanyinWenshu")
	public void printPrayguanyinWenshu(HttpServletRequest req,HttpServletResponse resp, Model model) throws IOException {
		deleteFile(this.getClass().getClassLoader().getResource("/").getPath().substring(1, this.getClass().getClassLoader().getResource("/").getPath().length() - 16) + "/webpage/c_gen_wenshu");
		Calendar ca1 = Calendar.getInstance();
		String year = String.valueOf(ca1.get(Calendar.YEAR));

		String starttime = req.getParameter("solarstart");
		String endtime = req.getParameter("solarend");
		TSUser user = ResourceUtil.getSessionUserName();
		
		
		List<Object[]> objs = new ArrayList<Object[]>();
		String sql = "select livingmenber,address,lunardate,book,serial from prayguanyin  where solardate >= '" + starttime + "' and solardate <= '" + endtime
				+ "';";
		
		String begindate = "";
		String enddate = "";
		System.out.println(sql);
		Calendar ca = Calendar.getInstance();
		objs = systemService.findListbySql(sql);
		
		String postfixList = "";
		if (objs.size() != 0) {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
			String nodepath = "";
			configuration
					.setServletContextForTemplateLoading(req.getSession().getServletContext(),"/webpage/cli_wenshu_model");
			for (int i = 0; i < objs.size(); i++) {
				String nameStr = String.valueOf(objs.get(i)[0]);
				String[] name = nameStr.substring(0, nameStr.length() - 1)
						.split(";");
				Map dataMap = new HashMap();
				
				dataMap.put("address", String.valueOf(objs.get(i)[1]));
				
				dataMap.put("name1", " ");
				dataMap.put("name2", " ");
				dataMap.put("name3", " ");
				dataMap.put("name4", " ");
				String tmp = "";
				int k = 1;
				for(int j = 0; j < name.length; j++){
					
					if(name[j].length() + tmp.length() <= 29){
						
						if(j == (name.length - 1) && tmp != null){
							tmp = tmp + name[j]  + "奉";
							dataMap.put("name" + k++ , tmp);
						}else{
							tmp = tmp + name[j] + " ";
						}
					}else{
						dataMap.put("name" + k++ , tmp);
						j--;
						tmp = "";
					}
				}
				
				begindate =  String.valueOf(objs.get(i)[2]);
				dataMap.put("lunardate2", begindate/* + "至" + enddate.replace(" ", "")*/);
				dataMap.put("book", String.valueOf(objs.get(i)[3]));
				dataMap.put("lunardate1", begindate);
				Template t = null;
				try {
					t = configuration.getTemplate("wenshuModel_1.ftl");
					t.setEncoding("utf-8");
				} catch (IOException e) {
					e.printStackTrace();
				}
				String postfix = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(objs.get(i)[4] + ".doc");
				dataMap.put("serial",String.valueOf(objs.get(i)[4]));
				
				if(i == 0) first = Integer.parseInt(String.valueOf(objs.get(0)[4]));
				int length = objs.size() - 1;
				if(i == length) last = Integer.parseInt(String.valueOf(objs.get(length)[4]));
				
				postfixList += postfix + ";";
				nodepath = this.getClass().getClassLoader().getResource("/").getPath(); 
				File outFile = new File(nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_wenshu/" + postfix);
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
			downloadPath = "/sgws"+ first + "-" + last + ".zip";
			generateZipFile(nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_wenshu");
			resp.getWriter().write("/webpage/c_gen_wenshu" + downloadPath);
			
		} 
		else{
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
//		System.out.println(files.length);
		
		for (int i = 0; i < files.length; i++) {
			File tmp = files[i];
			tmp.delete();
		}
	}
}
