package com.sys.controller.ghostfes;

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

import com.sys.entity.ancestor.AncestorEntity;
import com.sys.entity.buddhagaya.BuddhagayaEntity;
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.ghostfes.GhostfesEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.entity.releasesouls.ReleaseSoulsEntity;
import com.sys.entity.tombsweepfes.TombsweepfesEntity;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.ghostfes.GhostfesServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;
import com.sys.util.ControllerUtils;

/**
 * @Title: Controller
 * @Description: 盂兰节
 * @author zhangdaihao
 * @date 2016-03-03 10:29:48
 * @version V1.0
 * 
 */
@Controller
@RequestMapping("/ghostfesController")
public class GhostfesController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger
			.getLogger(GhostfesController.class);

	@Autowired
	private GhostfesServiceI ghostfesService;
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
	 * 盂兰节列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "ghostfes")
	public ModelAndView ghostfes(HttpServletRequest request) {
		return new ModelAndView("com/sys/ghostfes/ghostfesList");
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
	public void datagrid(GhostfesEntity ghostfes, HttpServletRequest request,
			HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(GhostfesEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				ghostfes,request.getParameterMap());
		this.ghostfesService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除盂兰节
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(GhostfesEntity ghostfes, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		ghostfes = systemService.getEntity(GhostfesEntity.class,
				ghostfes.getId());
		message = "盂兰节删除成功";
		ghostfesService.delete(ghostfes);
		systemService.addLog(message, Globals.Log_Type_DEL,
				Globals.Log_Leavel_INFO);

		j.setMsg(message);
		return j;
	}

	/**
	 * 添加盂兰节
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(GhostfesEntity ghostfes, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(ghostfes.getId())) {
			message = "盂兰节更新成功";
			GhostfesEntity t = ghostfesService.get(GhostfesEntity.class,
					ghostfes.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(ghostfes, t);
				ghostfesService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE,
						Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "盂兰节更新失败";
				logger.info(e.getMessage());
			}
		} else {
			message = "盂兰节添加成功";
			ghostfesService.save(ghostfes);
			systemService.addLog(message, Globals.Log_Type_INSERT,
					Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 盂兰节列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(GhostfesEntity ghostfes,
			HttpServletRequest req) {
		if (StringUtil.isNotEmpty(ghostfes.getId())) {
			ghostfes = ghostfesService.getEntity(GhostfesEntity.class,
					ghostfes.getId());
			req.setAttribute("ghostfesPage", ghostfes);
		}
		return new ModelAndView("com/sys/ghostfes/ghostfes");
	}

	@RequestMapping(params = "redirectToShowGhostfesRecord")
	public ModelAndView redirectToShowGhostfesRecord(HttpServletRequest req,
			Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size"); /* 8月7号 */
//		String hql0 = "from GhostfesEntity where 1 = 1 AND doritualid = ? AND size = ?";
		String sql = "select * from release_souls where 1 = 1 AND doritualid = ? AND size = ?";//20180504
		if (id0 != null) {
			DoritualinfoEntity doritualinfoEntity = doritualinfoService
					.getEntity(DoritualinfoEntity.class, id0);
			/*List<GhostfesEntity> ghostfesEntitys = ghostfesService.findHql(
					hql0, id0, size);*/
			List<GhostfesEntity> ghostfesEntitys = ghostfesService
					.listBySQL(GhostfesEntity.class,sql, id0,size);
			
			model.addAttribute("doritualinfoEntity", doritualinfoEntity);
			model.addAttribute("ghostfesEntitys", ghostfesEntitys);
			model.addAttribute("id", id0);
			model.addAttribute("size", size);
		}

		return new ModelAndView("com/sys/ghostfes/showGhostfesRecord");
	}

	@RequestMapping(params = "redirectToSelectSize")
	/* 8月7号 */
	public ModelAndView redirectToSelectSize(DoritualinfoEntity doritualinfo,
			HttpServletRequest req, Model model) {
		String id0 = req.getParameter("id");

//	--kooking 20180330	
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
		model.addAttribute("id0", id0);
		
		
//		---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记
		
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "盂兰节");
		if(funeralhelds.size()!=1){
			model.addAttribute("message", "抱歉，今年的盂兰节法事举行日期安排 登记不正常，请与系统管理员联系！");
			return new ModelAndView("com/sys/nofuneralheldplan");
		}
		
//		---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记
		return new ModelAndView("com/sys/ghostfes/selectSize");
	}

	/**
	 * 跳转到编辑登记盂兰节
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToEditGhostfes")
	public ModelAndView redirectToEditGhostfes(HttpServletRequest req,
			Model model) {
		try {
			String id0 = req.getParameter("id");
			String size = req.getParameter("size");
			if (size != null) {
				try {
					size = URLDecoder.decode(size, "utf-8");
				} catch (UnsupportedEncodingException e) {
					
					e.printStackTrace();
				}
			}
			DoritualinfoEntity doritualinfoEntity = doritualinfoService
					.getEntity(DoritualinfoEntity.class, id0);
			String ghostfesIds = req.getParameter("ghostfesIds");

			String hql0 = "from AncestorEntity where 1 = 1 AND ritualid = ? ";
			List<AncestorEntity> ancestorEntityList = systemService.findHql(
					hql0, id0);

//			List<GhostfesEntity> ghostfesEntitys = new ArrayList<GhostfesEntity>();
			List<ReleaseSoulsEntity> ghostfesEntitys = new ArrayList<ReleaseSoulsEntity>();
			//获取今年的盂兰节法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "盂兰节");
			model.addAttribute("funeralheld", funeralhelds);
			//添加做法事人的地址
			model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
			model.addAttribute("updateFlag", "0");
			if (size.equals("小")) {
				if (ghostfesIds == null) {
					model.addAttribute("ancestorEntityList", ancestorEntityList);
					model.addAttribute("id", id0);
					model.addAttribute("clientele",
							doritualinfoEntity.getRname());
					model.addAttribute("ghostfesEntitys", ghostfesEntitys);
					return new ModelAndView("com/sys/ghostfes/editGhostfes");
				}

				// 用逗号切开
				String ghostfesIdsIdsSplitByComma[] = ghostfesIds.substring(0,
						ghostfesIds.length() - 1).split(",");
				
				// 分别获取相关数据
				for (int i = 0; i < ghostfesIdsIdsSplitByComma.length; i++) {
					/*GhostfesEntity ghostfesEntity = ghostfesService
							.get(GhostfesEntity.class,
									ghostfesIdsIdsSplitByComma[i]);*/
					ReleaseSoulsEntity ghostfesEntity = ghostfesService
							.get(ReleaseSoulsEntity.class,
									ghostfesIdsIdsSplitByComma[i]);
					ghostfesEntitys.add(ghostfesEntity);
				}
				/*02月15日修改*/
			

				model.addAttribute("ghostfesEntitys", ghostfesEntitys);
				model.addAttribute("ancestorEntityList", ancestorEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());

				return new ModelAndView("com/sys/ghostfes/editGhostfes");
			} else {
				if (ghostfesIds == null) {
					model.addAttribute("ancestorEntityList", ancestorEntityList);
					model.addAttribute("id", id0);
					model.addAttribute("clientele",
							doritualinfoEntity.getRname());
					model.addAttribute("ghostfesEntitys", ghostfesEntitys);
					model.addAttribute("size", size);
					return new ModelAndView(
							"com/sys/ghostfes/editMiddleAndBigGhostfes");
				} else {
					// 已经有数据
					/*GhostfesEntity ghostfesEntity = ghostfesService.get(
							GhostfesEntity.class,
							ghostfesIds.substring(0, ghostfesIds.length() - 1));*/
					ReleaseSoulsEntity ghostfesEntity = ghostfesService.get(
							ReleaseSoulsEntity.class,
							ghostfesIds.substring(0, ghostfesIds.length() - 1));

					String ancestorString = ghostfesEntity.getAncestor();
					String[] ancestorStringByPound = ghostfesEntity
							.getAncestor()
							.substring(0, ghostfesEntity.getAncestor().length())
							.split("#");
					List<String> ancestorList = new ArrayList<String>();

					String surname = "", tmptype = "1";
					int index = 0;
					if (ghostfesEntity.getType() == 5) {
						surname = ancestorStringByPound[0];
						index = 1;
					}
					if (ancestorStringByPound[index + 1].equals("1")) {
						tmptype = "2";
					}
					String[] ancestorStringBySemicolon = ancestorStringByPound[index]
							.split(";");
					for (int i = 0; i < ancestorStringBySemicolon.length; i++) {

						ancestorList.add(ancestorStringBySemicolon[i]);
					}
					ghostfesEntitys.add(ghostfesEntity);
					model.addAttribute("ghostfesEntity", ghostfesEntity);
					model.addAttribute("ghostfesEntitys", ghostfesEntitys);
					model.addAttribute("surname", surname);
					model.addAttribute("type", tmptype);
					model.addAttribute("ancestorList", ancestorList);
					model.addAttribute("id", id0);
					model.addAttribute("size", size);
					model.addAttribute("clientele",
							doritualinfoEntity.getRname());
					return new ModelAndView(
							"com/sys/ghostfes/editMiddleAndBigGhostfes");
				}

			}
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
	}

	/**
	 * 保存编辑后的孟兰节记录和收据
	 * 
	 * @param req
	 * @param model
	 * @return
	 */
	/*@RequestMapping(params = "updateGhostfesAndReceipt")kooking
	public ModelAndView updateGhostfesAndReceipt(HttpServletRequest req,
			Model model) {*/
	private ModelAndView updateGhostfesAndReceipt(String ids,String[] antoserial,
				Model model) {
		try {
			// 获取盂兰节记录的ID
//			String ids = req.getParameter("ids");
			String subids = ids.substring(0, ids.length() - 1);
			String[] id = subids.split(",");

			// 获取自动编号
//			String[] antoserial = req.getParameterValues("autoserial");kooking

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
			/*打印摘要修改*/
			//获取今年的盂兰节法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "盂兰节");
			FuneralheldEntity funeralheldEntity = funeralhelds.get(0);
			String sumSummary ="", res = "";
			GhostfesEntity entity = ghostfesService.get(GhostfesEntity.class,
					id[0]);
			boolean flag=true;//标志是否是小牌
			if(entity.getSize().equals("大")|| entity.getSize().equals("拈香"))
			{
//				sumSummary = entity.getPaymen()+"交来";
				sumSummary=entity.getSummary();
				flag = false;
			}
			
			
			if(flag){//若为小牌
				
				sumSummary+=entity.getPaymen()+"交来";
				
				if(funeralheldEntity.getHoldDate().equals(funeralheldEntity.getEndDate())){
					sumSummary += funeralhelds.get(0).getHoldDate()+funeralheldEntity.getRitualtype();
				}else{
					sumSummary += funeralheldEntity.getHoldDate()+" 至 "+funeralheldEntity.getEndDate()+funeralheldEntity.getRitualtype();
				}
				
				sumSummary+="功德款:";
			}
			
			/*if(!flag){
				if(entity.getSize().equals("大")){
					sumSummary += "大牌";
				}else{
					sumSummary += "拈香";
				}
				sumSummary +="功德款</br>";
			}*/
			/*打印摘要修改*/
			
			
			TSUser user = ResourceUtil.getSessionUserName();
			List<String> summary = new ArrayList<String>();
			for (int i = 0; i < id.length; i++) {
				GhostfesEntity te = ghostfesService.get(GhostfesEntity.class,
						id[i]);
				te.setAutoserial(antoserial[i]);
				te.setRegistertime(dateString);
				te.setRegistrant(user.getRealName());
				te.setReceiptno(re.getNo());
				ghostfesService.updateEntitie(te);

				sum += te.getMoney();
				obj += te.getAncestor();
				
				/*打印摘要修改*/
				/*if (!summary.contains(te.getSummary())) {
					if(flag){
						sumSummary += (i+1)+"、"+te.getSummary() +"。";
					}else {
						sumSummary+=(i+1)+"、"+te.getSummary();
					}
				}*/
				if(flag){
					sumSummary += (i+1)+"、"+te.getSummary() +"。";
				}
				/*打印摘要修改*/
				
				summary.add(te.getSummary());
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
			re.setSize(entity.getSize());
			re.setSummary(sumSummary);
			re.setRemark(res);
			re.setObj(obj);
			re.setDate("二#十九#二#廿七");
			// 保存收据

			re.setRitualtype("盂兰节");
			re.setRegistertime(dateString);
			re.setPurpose("盂兰节");
			re.setFlag(0);
			receiptService.save(re);
			String receipId = re.getId();

			for (int i = 0; i < id.length; i++) {
				GhostfesEntity te = ghostfesService.get(GhostfesEntity.class,
						id[i]);
				te.setReceiptid(receipId);
				ghostfesService.updateEntitie(te);
			}

			model.addAttribute("message", "盂兰节登记成功");
			model.addAttribute("ritualtype", "盂兰节");
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

	@RequestMapping(params = "getSerialAndSaveTablet")
	public ModelAndView getSerial(HttpServletRequest req, Model model) {
		// 修改牌位
		String ghostfesid = req.getParameter("ghostfesid");
		if (ghostfesid != null && !ghostfesid.equals("")) {
			GhostfesEntity ghostfesEntity = ghostfesService.get(
					GhostfesEntity.class, ghostfesid);
			if (ghostfesEntity.getSize().equals("小")) {
				ghostfesEntity.setAddress(req.getParameter("address"));
				ghostfesEntity.setAncestor(req.getParameter("ancestor"));
				ghostfesEntity.setMoney(Integer.valueOf(req
						.getParameter("money")));
				ghostfesEntity.setPayway(req.getParameter("payway"));
				ghostfesEntity.setSummary(req.getParameter("summary"));
				ghostfesEntity.setPrayingobj(req.getParameter("prayingobj"));
				ghostfesEntity.setPaymen(req.getParameter("paymen"));
				ghostfesEntity
						.setType(Integer.valueOf(req.getParameter("type")));
				ghostfesService.updateEntitie(ghostfesEntity);
				model.addAttribute("message", "盂兰节牌位修改成功！！");
				return new ModelAndView("com/sys/updateSuccess");
			} else {
				String[] ancestor = req.getParameterValues("ancestor");
				String surname = req.getParameter("surname");
				String type = req.getParameter("type");
				String ancestorString = "";
				if (surname != "") {
					ancestorString += surname + "#";
				}
				for (int i = 0; i < ancestor.length; i++) {
					if (ancestor[i].equals("") || ancestor[i] == null) {
						ancestor[i] = " ";
					}
					ancestorString += ancestor[i] + ";";
				}
				if (type.equals("2")) {
					ancestorString += "#1";
				} else {
					ancestorString += "#0";
				}
				ghostfesEntity.setBook(req.getParameter("book"));
				ghostfesEntity.setAddress(req.getParameter("address"));
				ghostfesEntity.setAncestor(ancestorString);
				ghostfesEntity.setMoney(Integer.valueOf(req
						.getParameter("money")));
				ghostfesEntity.setPayway(req.getParameter("payway"));
				ghostfesEntity.setSummary(req.getParameter("summary"));
				ghostfesEntity.setPrayingobj(req.getParameter("prayingobj"));
				ghostfesEntity.setPaymen(req.getParameter("paymen"));
				if (surname == "") {
					ghostfesEntity.setType(6);
				} else {
					ghostfesEntity.setType(5);
				}
				ghostfesService.updateEntitie(ghostfesEntity);
				model.addAttribute("message", "盂兰节牌位修改成功！！");
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
				String[] ancestor = req.getParameterValues("ancestor");

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

				// 获取大小
				String[] type = req.getParameterValues("type");

				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				String currentyear = String.valueOf(year);

				for (int i = 0; i < type.length; i++) {
					if (type[i].equals("2")) {
						ancestor[i] = "";
					}
				}
				List<GhostfesEntity> ghostfesEntityList = new ArrayList<GhostfesEntity>();
				String ids = "";
				String[] antoserial=new String[size.length];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);

				for (int i = 0; i < size.length; i++) {
					GhostfesEntity ghostfesEntity = new GhostfesEntity();
					CriteriaQuery cq = new CriteriaQuery(GhostfesEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("size", size[i]),
							Restrictions.like("registertime", currentyear,
									MatchMode.START)));
					List<GhostfesEntity> ghostfesEntitys = ghostfesService
							.getListByCriteriaQuery(cq, false);

					ghostfesEntity.setAddress(address[i]);
					ghostfesEntity.setAncestor(ancestor[i]);
					ghostfesEntity.setMoney(Integer.valueOf(money[i]));
					ghostfesEntity.setPayway(payway[i]);
					ghostfesEntity.setSummary(summary[i]);
					ghostfesEntity.setPrayingobj(prayingobj[i]);
					ghostfesEntity.setPaymen(paymen);
					ghostfesEntity.setDoritualid(id0);
					ghostfesEntity.setSize(size[i]);
					// 换时间
					ghostfesEntity.setType(Integer.valueOf(type[i]));
					ghostfesEntity.setFlag(0);
					NumberFormat f = new DecimalFormat("000000");
					ghostfesEntity
							.setSerial(f.format(ghostfesEntitys.size() + 1));
					ghostfesEntity.setRegistertime(dateString);
//					ghostfesEntityList.add(ghostfesEntity);

					ghostfesService.save(ghostfesEntity);
					
					ControllerUtils.save2ReleaseSouls(ghostfesEntity, ghostfesService);
					
					ids += ghostfesEntity.getId() + ",";
					antoserial[i]=ghostfesEntity.getSerial();

				}
//				req.setAttribute("ghostfesEntityList", ghostfesEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView("com/sys/ghostfes/AutoSerialGhostfes");
				
//------------20180502 Kooking		
				return updateGhostfesAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
				
			} else { // 大中牌
				String id0 = req.getParameter("id");

				// 获取祈福者
				String prayingobj = req.getParameter("prayingobj");

				// 获取祈福对象
				String[] ancestor = req.getParameterValues("ancestor");

				// 获取祈福者家庭住址
				String address = req.getParameter("address");

				// 获取摘要
				String summary = req.getParameter("summary");

				// 获取摘要
				String payway = req.getParameter("payway");
				String paymen = req.getParameter("paymen");

				// 获取摘要
				String money = req.getParameter("money");

				String surname = req.getParameter("surname");
				String book = req.getParameter("book");

				String type = req.getParameter("type");
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				String currentyear = String.valueOf(year);

				GhostfesEntity ghostfesEntity = new GhostfesEntity();

				// 获取序号
				CriteriaQuery cq = new CriteriaQuery(GhostfesEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size", sizeflag),
						Restrictions.like("registertime", currentyear,
								MatchMode.START)));
				List<GhostfesEntity> ghostfesEntitys = ghostfesService
						.getListByCriteriaQuery(cq, false);

//				List<GhostfesEntity> ghostfesEntityList = new ArrayList<GhostfesEntity>();

				String ids = "";
				String[] antoserial=new String[1];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);

				String ancestorString = "";
				if (surname != "") {
					ancestorString += surname + "#";
				}
				for (int i = 0; i < ancestor.length; i++) {
					if (ancestor[i].equals("") || ancestor[i] == null) {
						ancestor[i] = " ";
					}
					ancestorString += ancestor[i] + ";";
				}
				if (type.equals("2")) {
					ancestorString += "#1";
				} else {
					ancestorString += "#0";
				}

				ghostfesEntity.setBook(book);
				ghostfesEntity.setAddress(address);
				ghostfesEntity.setAncestor(ancestorString);
				ghostfesEntity.setMoney(Integer.valueOf(money));
				ghostfesEntity.setPayway(payway);
				ghostfesEntity.setSummary(summary);
				ghostfesEntity.setPrayingobj(prayingobj);
				ghostfesEntity.setPaymen(paymen);
				ghostfesEntity.setDoritualid(id0);
				ghostfesEntity.setSize(sizeflag);
				if (surname == "") {
					ghostfesEntity.setType(6);
				} else {
					ghostfesEntity.setType(5);
				}
				// 换时间
				ghostfesEntity.setFlag(0);
				NumberFormat f = new DecimalFormat("000000");
				ghostfesEntity.setSerial(f.format(ghostfesEntitys.size() + 1));
				ghostfesEntity.setRegistertime(dateString);

				ghostfesService.save(ghostfesEntity);
				
				ControllerUtils.save2ReleaseSouls(ghostfesEntity, ghostfesService);
				
				ids += ghostfesEntity.getId() + ",";
				antoserial[0]=ghostfesEntity.getSerial();
				
//				ghostfesEntityList.add(ghostfesEntity);
//				req.setAttribute("ghostfesEntityList", ghostfesEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView("com/sys/ghostfes/AutoSerialGhostfes");

//------------20180502 Kooking		
				return updateGhostfesAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
			}

		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
	}

	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(GhostfesEntity ghostfesEntity,
			HttpServletRequest request, Model model) {
		List<GhostfesEntity> ghostfesEntitys = new ArrayList<GhostfesEntity>();
		if (StringUtil.isNotEmpty(ghostfesEntity.getId())) {
			ghostfesEntity = ghostfesService.getEntity(GhostfesEntity.class,
					ghostfesEntity.getId());

			TSUser user = ResourceUtil.getSessionUserName();
			if (!user.getRealName().equals(ghostfesEntity.getRegistrant())) {
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			if (ghostfesEntity.getSize().equals("小")) {//小牌
				ghostfesEntitys.add(ghostfesEntity);
				model.addAttribute("ghostfesEntitys", ghostfesEntitys);
				model.addAttribute("ancestorEntityList",
						ghostfesEntity.getAncestor());
				model.addAttribute("id", ghostfesEntity.getDoritualid());
				model.addAttribute("ghostfesid", ghostfesEntity.getId());
				model.addAttribute("clientele", ghostfesEntity.getPaymen());
				model.addAttribute("size", ghostfesEntity.getSize());
				return new ModelAndView("com/sys/ghostfes/editGhostfes");
			} else {//大牌或拈香
				String ancestorString = ghostfesEntity.getAncestor();
				String[] ancestorStringByPound = ghostfesEntity.getAncestor()
						.substring(0, ghostfesEntity.getAncestor().length())
						.split("#");
				List<String> ancestorList = new ArrayList<String>();
				String surname = "", tmptype = "1";
				int index = 0;
				if (ghostfesEntity.getType() == 5) {
					surname = ancestorStringByPound[0];
					index = 1;
				}
				if (ancestorStringByPound[index + 1].equals("1")) {
					tmptype = "2";
				}
				String[] ancestorStringBySemicolon = ancestorStringByPound[index]
						.split(";");
				for (int i = 0; i < ancestorStringBySemicolon.length; i++) {

					ancestorList.add(ancestorStringBySemicolon[i]);
				}
				ghostfesEntitys.add(ghostfesEntity);
				model.addAttribute("ghostfesEntity", ghostfesEntity);
				model.addAttribute("ghostfesEntitys", ghostfesEntitys);
				model.addAttribute("surname", surname);
				model.addAttribute("type", tmptype);
				model.addAttribute("ancestorList", ancestorList);
				model.addAttribute("id", ghostfesEntity.getId());
				model.addAttribute("size", ghostfesEntity.getSize());
				model.addAttribute("ghostfesid", ghostfesEntity.getId());
				return new ModelAndView(
						"com/sys/ghostfes/editMiddleAndBigGhostfes");
			}
		} else {
			return new ModelAndView("com/sys/error");
		}
	}

}
