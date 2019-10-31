package com.sys.controller.pharmacistbirth;
import java.io.IOException;
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

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

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

import com.sys.entity.ancestor.AncestorEntity;
import com.sys.entity.buddhabirth.BuddhabirthEntity;
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.guanyinopen.GuanyinopenEntity;
import com.sys.entity.honoredbirth.HonoredbirthEntity;
import com.sys.entity.jizobirth.JIzobirthEntity;
import com.sys.entity.livingmenber.LivingmenberEntity;
import com.sys.entity.pharmacistbirth.PharmacistbirthEntity;
import com.sys.entity.pray.PrayEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.entity.tombsweepfes.TombsweepfesEntity;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.pharmacistbirth.PharmacistbirthServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;
import com.sys.util.ControllerUtils;

/**   
 * @Title: Controller
 * @Description: 药师诞信息
 * @author zhangdaihao
 * @date 2015-11-25 16:31:20
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/pharmacistbirthController")
public class PharmacistbirthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PharmacistbirthController.class);

	@Autowired
	private PharmacistbirthServiceI pharmacistbirthService;
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
	 * 药师诞信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "pharmacistbirth")
	public ModelAndView pharmacistbirth(HttpServletRequest request) {
		return new ModelAndView("com/sys/pharmacistbirth/pharmacistbirthList");
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
	public void datagrid(PharmacistbirthEntity pharmacistbirth,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PharmacistbirthEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pharmacistbirth, request.getParameterMap());
		this.pharmacistbirthService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除药师诞信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(PharmacistbirthEntity pharmacistbirth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		pharmacistbirth = systemService.getEntity(PharmacistbirthEntity.class, pharmacistbirth.getId());
		message = "药师诞信息删除成功";
		pharmacistbirthService.delete(pharmacistbirth);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加药师诞信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(PharmacistbirthEntity pharmacistbirth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(pharmacistbirth.getId())) {
			message = "药师诞信息更新成功";
			PharmacistbirthEntity t = pharmacistbirthService.get(PharmacistbirthEntity.class, pharmacistbirth.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(pharmacistbirth, t);
				pharmacistbirthService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "药师诞信息更新失败";
			}
		} else {
			message = "药师诞信息添加成功";
			Date now = new Date(); 
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			pharmacistbirth.setRegistertime(dateFormat.format(now));
			pharmacistbirthService.save(pharmacistbirth);
			String pharmacistbirthid = pharmacistbirth.getId();
			String livingString = request.getParameter("livingString");
			String ancestorString = request.getParameter("ancestorString");
			String doritualid = request.getParameter("ritualid");
			generateAndSaveReceipt(pharmacistbirth,doritualid);
			updateLivingAndAncestorList(doritualid,pharmacistbirthid,livingString,ancestorString);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	private void updateLivingAndAncestorList(String doritualid,String pharmacistbirthid,String livingString,String ancestorString){
		
//		DoritualinfoEntity doritual = doritualinfoService.get(DoritualinfoEntity.class, doritualid);
		
		if(livingString.equals("") && ancestorString.equals("")){
			return ;
		}
		else{
			if(!ancestorString.equals("")){
				String ancestorSubString = ancestorString.substring(0, ancestorString.length() - 1);
				String[] ancestorArr = ancestorSubString.split("\\|");
				for(int i = 0;i < ancestorArr.length;){
					AncestorEntity newAncestorEntity = new AncestorEntity();
					newAncestorEntity.setCalled(ancestorArr[i]);
					newAncestorEntity.setName(ancestorArr[i+1]);
					newAncestorEntity.setRitualid(doritualid);
					newAncestorEntity.setPharmacistbirthid(pharmacistbirthid);
					systemService.save(newAncestorEntity);
					i = i + 2;
				}
			}
			if(!livingString.equals("")){
				String livingSubString = livingString.substring(0, livingString.length() - 1);
				String[] livingArr = livingSubString.split("\\|");
				for(int i = 0;i < livingArr.length;){
					LivingmenberEntity newLivingmenberEntity = new LivingmenberEntity();
					newLivingmenberEntity.setCalled(livingArr[i]);
					newLivingmenberEntity.setName(livingArr[i+1]);
					newLivingmenberEntity.setRitualid(doritualid);
					newLivingmenberEntity.setPharmacistbirthid(pharmacistbirthid);
					systemService.save(newLivingmenberEntity);
					i = i + 2;
				}
			}
		}
	}

	/**
	 * 药师诞信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(PharmacistbirthEntity pharmacistbirth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(pharmacistbirth.getId())) {
			pharmacistbirth = pharmacistbirthService.getEntity(PharmacistbirthEntity.class, pharmacistbirth.getId());
			req.setAttribute("pharmacistbirthPage", pharmacistbirth);
		}
		return new ModelAndView("com/sys/pharmacistbirth/pharmacistbirth");
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
			//查询今年药师诞法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "药师诞");
			if(funeralhelds.size()!=1){
				model.addAttribute("message", "抱歉，今年的药师诞法事举行日期安排 登记不正常，请与系统管理员联系！");
				return new ModelAndView("com/sys/nofuneralheldplan");
			}
			
//			---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记			
			
		model.addAttribute("id0", id0);
		
		return new ModelAndView("com/sys/pharmacistbirth/selectSize");
	}
	
	/**
	 * 药师诞信息登记跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "redirectToPharmacistbirth")
	public ModelAndView redirectToPharmacistbirth(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		if(id0 != null){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
			PharmacistbirthEntity pharmacistbirth = new PharmacistbirthEntity();
			pharmacistbirth.setPrayingobj(doritualinfoEntity.getRname());
			//获取在世的人列表
			String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ? ";
			List<LivingmenberEntity> livingmenberEntityList = systemService.findHql(hql0,id0);
			
			//获取先人列表
			String hql1 = "from AncestorEntity where 1 = 1 AND ritualid = ? ";
			List<AncestorEntity> ancestorEntityList = systemService.findHql(hql1,id0);
			
			model.addAttribute("ancestorEntityList", ancestorEntityList);
			model.addAttribute("livingmenberEntityList", livingmenberEntityList);
			model.addAttribute("pharmacistbirthPage", pharmacistbirth);
			model.addAttribute("id", id0);
			
		}
		
		return new ModelAndView("com/sys/pharmacistbirth/pharmacistbirth");
	}
	
	/**
	 * 药师诞信息登记跳转页面
	 * 
	 * @return
	 */
	@RequestMapping(params = "redirectToPharmacistbirthByRitualid")
	public ModelAndView redirectToPharmacistbirthByRitualid(String ritualid,HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		if(id0 != null){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
			PharmacistbirthEntity pharmacistbirth = new PharmacistbirthEntity();
			pharmacistbirth.setPrayingobj(doritualinfoEntity.getRname());
			//获取在世的人列表
			String hql0 = "from LivingmenberEntity where 1 = 1 AND pharmacistbirthid = ? ";
			List<LivingmenberEntity> livingmenberEntityList = systemService.findHql(hql0,ritualid);
			
			//获取先人列表
			String hql1 = "from AncestorEntity where 1 = 1 AND pharmacistbirthid = ? ";
			List<AncestorEntity> ancestorEntityList = systemService.findHql(hql1,ritualid);
			
			model.addAttribute("ancestorEntityList", ancestorEntityList);
			model.addAttribute("livingmenberEntityList", livingmenberEntityList);
			model.addAttribute("pharmacistbirthPage", pharmacistbirth);
			model.addAttribute("id", id0);
			
		}
		
		return new ModelAndView("com/sys/pharmacistbirth/pharmacistbirth");
	}
	
	@RequestMapping(params = "redirectToShowPharmacistbirthRecord")
	public ModelAndView redirectToShowPharmacistbirthRecord(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");
//		String hql0 = "from PharmacistbirthEntity where 1 = 1 AND doritualid = ? AND size = ?";
		String sql = "select * from pray where 1 = 1 AND doritualid = ? AND size = ?";//20180504
		if(id0 != null){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
//			List<PharmacistbirthEntity> pharmacistbirthEntitys = pharmacistbirthService.findHql(hql0,id0,size);
			List<PharmacistbirthEntity> pharmacistbirthEntitys = pharmacistbirthService
					.listBySQL(PharmacistbirthEntity.class, sql, id0,size);
			model.addAttribute("doritualinfoEntity", doritualinfoEntity);
			model.addAttribute("pharmacistbirthEntitys", pharmacistbirthEntitys);
			model.addAttribute("size", size);
			model.addAttribute("id", id0);
		}
		
		return new ModelAndView("com/sys/pharmacistbirth/showPharmacistbirthRecord");
	}
	
	/**
	 * 获取数据库当前简称的序号
	 * @param shortcall
	 * @param response
	 */
	@RequestMapping(params = "getSerial")
	public void getSerial(String shortcall, HttpServletResponse response) {
		CriteriaQuery cq = new CriteriaQuery(PharmacistbirthEntity.class);
		cq.add(Restrictions.like("serial", shortcall,MatchMode.START));
		List<PharmacistbirthEntity> pharmacistbirths = pharmacistbirthService.getListByCriteriaQuery(cq, true);
		String result = String.valueOf(pharmacistbirths.size() + 1);
		System.out.println(result);
		
	    try {
	      response.getWriter().write(result);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	@RequestMapping(params = "getLivingmenberNameList")
	public void getLivingmenberNameList(String objName, HttpServletResponse response) {
		String result = null;
		DoritualinfoEntity doritual = doritualinfoService.findUniqueByProperty(DoritualinfoEntity.class, "rname", objName);
		//================================================
		//获取ID值
		Object id0 = doritual.getId();
		//获取在世的人的名单
		String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ? ";
		List<LivingmenberEntity> livingmenberEntityList = systemService.findHql(hql0,id0);
		
		
		result = getJSONArrayOfLivingmenberEntity(livingmenberEntityList).toString();
		System.out.println(result);
	    try {
	      response.getWriter().write(result);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	@RequestMapping(params = "getAncestorNameList")
	public void getAncestorNameList(String objName, HttpServletResponse response) {
		String result = null;
		DoritualinfoEntity doritual = doritualinfoService.findUniqueByProperty(DoritualinfoEntity.class, "rname", objName);
		//================================================
		//获取ID值
		Object id0 = doritual.getId();
		
		//获取过世的人的名单
		String hql1 = "from AncestorEntity where 1 = 1 AND ritualid = ? ";
		List<AncestorEntity> ancestorEntityList = systemService.findHql(hql1,id0);
		
		result = getJSONArrayOfAncestorEntity(ancestorEntityList).toString();
		System.out.println(result);
	    try {
	      response.getWriter().write(result);
	    } catch (IOException e) {
	      e.printStackTrace();
	    }
	}
	
	private static JSONArray getJSONArrayOfLivingmenberEntity(List<LivingmenberEntity> livingmenbers){
		JSONArray json = new JSONArray();
		for(LivingmenberEntity lm : livingmenbers){
            JSONObject jo = new JSONObject();
            jo.put("id", lm.getId());
            jo.put("called", lm.getCalled());
            jo.put("name", lm.getName());
            json.add(jo);
        }
		return json;
		
	}
	private static JSONArray getJSONArrayOfAncestorEntity(List<AncestorEntity> ancestors){
		JSONArray json = new JSONArray();
		for(AncestorEntity act : ancestors){
            JSONObject jo = new JSONObject();
            jo.put("id", act.getId());
            jo.put("called", act.getCalled());
            jo.put("name", act.getName());
            json.add(jo);
        }
		return json;
		
	}
	
	
	private void generateAndSaveReceipt(PharmacistbirthEntity pharmacistbirth,String doritualid){
		ReceiptEntity re = new ReceiptEntity();
		String no = doritualid.substring(0, 6);//先随便获取编号No
		re.setNo("No." + no);
		re.setDoritualid(doritualid);
		re.setMoney(pharmacistbirth.getMoney());
		re.setPaymen(pharmacistbirth.getPrayingobj());
		re.setPayway(pharmacistbirth.getPayway());
		re.setRegistrant(pharmacistbirth.getRegistrant());
		re.setSummary(pharmacistbirth.getSummary());
		re.setRitualid(pharmacistbirth.getId());
		re.setRitualtype("药师诞");
		re.setDate("二#十九#二#廿七");
		re.setRegistertime(pharmacistbirth.getRegistertime());
		receiptService.save(re);
	}
	
	/**
	 * 跳转到编辑登记药师诞
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToEditPharmacistbirth")
	public ModelAndView redirectToEditPharmacistbirth(HttpServletRequest req,Model model) {
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
		String pharmacistbirthIds = req.getParameter("pharmacistbirthIds");
		
		String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ? ";
		List<LivingmenberEntity> livingmenberEntityList = systemService.findHql(hql0,id0);
		
//		List<PharmacistbirthEntity> pharmacistbirthEntitys = new ArrayList<PharmacistbirthEntity>();
		List<PrayEntity> pharmacistbirthEntitys = new ArrayList<PrayEntity>();
		
		//查询今年药师诞法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "药师诞");
		model.addAttribute("funeralheld", funeralhelds);			
		
		//添加做法事人的地址
		model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
		model.addAttribute("updateFlag", "0");		
		
		if(size.equals("小")){
			if(pharmacistbirthIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("pharmacistbirthEntitys", pharmacistbirthEntitys);
				return new ModelAndView("com/sys/pharmacistbirth/editPharmacistbirth");
			}
			
			
			
			//用逗号切开
			String pharmacistbirthIdsSplitByComma[] = pharmacistbirthIds.substring(0, pharmacistbirthIds.length()-1).split(",");
			
			//分别获取相关数据
			for(int i = 0;i < pharmacistbirthIdsSplitByComma.length;i ++){
//				PharmacistbirthEntity pharmacistbirthEntity = pharmacistbirthService.get(PharmacistbirthEntity.class, pharmacistbirthIdsSplitByComma[i]);
				PrayEntity pharmacistbirthEntity = pharmacistbirthService.get(PrayEntity.class, pharmacistbirthIdsSplitByComma[i]);
				pharmacistbirthEntitys.add(pharmacistbirthEntity);
			}
			
			
	
	
			model.addAttribute("pharmacistbirthEntitys", pharmacistbirthEntitys);
			model.addAttribute("livingmenberEntityList", livingmenberEntityList);
			model.addAttribute("id", id0);
			model.addAttribute("clientele", doritualinfoEntity.getRname());
			model.addAttribute("book", pharmacistbirthEntitys.get(0).getBook());
			return new ModelAndView("com/sys/pharmacistbirth/editPharmacistbirth");
		}
		else{
			if(pharmacistbirthIds == null){
				model.addAttribute("livingmenberEntityList", livingmenberEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				model.addAttribute("pharmacistbirthEntitys", pharmacistbirthEntitys);
				model.addAttribute("size", size);
				return new ModelAndView("com/sys/pharmacistbirth/editMiddleAndBigPharmacistbirth");
			}
			else{
				//已经有数据
				PrayEntity pharmacistbirthEntity = pharmacistbirthService.get(PrayEntity.class, pharmacistbirthIds.substring(0, pharmacistbirthIds.length() - 1));
				
				String livingString = pharmacistbirthEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				pharmacistbirthEntitys.add(pharmacistbirthEntity);
				model.addAttribute("pharmacistbirthEntity", pharmacistbirthEntity);
				model.addAttribute("pharmacistbirthEntitys", pharmacistbirthEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", id0);
				model.addAttribute("size", size);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				return new ModelAndView("com/sys/pharmacistbirth/editMiddleAndBigPharmacistbirth");
			}
		}
	}
	

	
	/**
	 * 保存编辑后的药师诞记录和收据
	 * @param req
	 * @param model
	 * @return
	 */
	/*@RequestMapping(params = "updatePharmacistbirthAndReceipt")
	public ModelAndView updatePharmacistbirthAndReceipt(HttpServletRequest req,Model model) {*/
	private ModelAndView updatePharmacistbirthAndReceipt(String ids,String[] antoserial,Model model) {
		
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
		PharmacistbirthEntity entity = pharmacistbirthService.get(
				PharmacistbirthEntity.class, id[0]);
//		StringBuilder stb= new StringBuilder("").append(entity.getPaymen()).append("交来农历 ");
		StringBuilder stb= new StringBuilder("");
		String sumSummary ="";
		
		//查询今年药师诞法事举行日期安排
		List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "药师诞");
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
			PharmacistbirthEntity te = pharmacistbirthService.get(PharmacistbirthEntity.class, id[i]);
			te.setAutoserial(antoserial[i]);
			te.setRegistertime(dateString);
			te.setRegistrant(user.getRealName());
			te.setReceiptNo(re.getNo());
			pharmacistbirthService.updateEntitie(te);
			
			sum += te.getMoney();
			obj += te.getLivingmenber();
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
		re.setSize(entity.getSize());
		re.setMoney(sum);
		re.setSummary(sumSummary);
		re.setRemark(res);
		re.setObj(obj);
		re.setDate("二#十九#二#廿七");
		//保存收据

		
		
		re.setRitualtype("药师诞");
		re.setRegistertime(dateString);
		re.setPurpose("药师诞");
		re.setFlag(0);
		receiptService.save(re);
		String receipId = re.getId();

		for(int i = 0;i < id.length;i ++){
			PharmacistbirthEntity te = pharmacistbirthService.get(PharmacistbirthEntity.class, id[i]);
			te.setReceiptId(receipId);
			pharmacistbirthService.updateEntitie(te);
		}
		
		
		model.addAttribute("message", "药师诞登记成功");
		model.addAttribute("ritualtype", "药师诞");
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
		
		String pharmacistbirthid = req.getParameter("pharmacistbirthid");
		if(pharmacistbirthid != null && !pharmacistbirthid.equals("")){
			PharmacistbirthEntity pharmacistbirthEntity = pharmacistbirthService.get(PharmacistbirthEntity.class, pharmacistbirthid);
			if(pharmacistbirthEntity.getSize().equals("小")){
				pharmacistbirthEntity.setAddress(req.getParameter("address"));
				pharmacistbirthEntity.setLivingmenber(req.getParameter("livingmenber"));
				pharmacistbirthEntity.setMoney(Integer.valueOf(req.getParameter("money")));
				pharmacistbirthEntity.setPayway(req.getParameter("payway"));
				pharmacistbirthEntity.setSummary(req.getParameter("summary"));
				pharmacistbirthEntity.setPrayingobj(req.getParameter("prayingobj"));
				pharmacistbirthEntity.setPaymen(req.getParameter("paymen"));
				pharmacistbirthService.updateEntitie(pharmacistbirthEntity);
				model.addAttribute("message", "药师诞牌位修改成功！！");
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
				
				pharmacistbirthEntity.setAddress(req.getParameter("address"));
				pharmacistbirthEntity.setLivingmenber(livingString);
				pharmacistbirthEntity.setMoney(Integer.valueOf(Integer.valueOf(req.getParameter("money"))));
				pharmacistbirthEntity.setPayway(req.getParameter("payway"));
				pharmacistbirthEntity.setSummary(req.getParameter("summary"));
				pharmacistbirthEntity.setPrayingobj(req.getParameter("prayingobj"));
				pharmacistbirthEntity.setPaymen(req.getParameter("paymen"));
				pharmacistbirthEntity.setBook(req.getParameter("book"));
				
				pharmacistbirthService.updateEntitie(pharmacistbirthEntity);
				model.addAttribute("message", "药师诞牌位修改成功！！");
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
			
//			List<PharmacistbirthEntity> pharmacistbirthEntityList = new ArrayList<PharmacistbirthEntity>();
			String ids = "";
			String[] antoserial=new String[size.length];
			
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(currentTime);
			
			for(int i = 0;i < size.length;i ++){
				PharmacistbirthEntity pharmacistbirthEntity = new PharmacistbirthEntity();
				CriteriaQuery cq = new CriteriaQuery(PharmacistbirthEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",size[i]),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<PharmacistbirthEntity> pharmacistbirths = pharmacistbirthService.getListByCriteriaQuery(cq,false);
				
				pharmacistbirthEntity.setAddress(address[i]);
				pharmacistbirthEntity.setLivingmenber(livingmember[i]);
				pharmacistbirthEntity.setMoney(Integer.valueOf(money[i]));
				pharmacistbirthEntity.setPayway(payway[i]);
				pharmacistbirthEntity.setSummary(summary[i]);
				pharmacistbirthEntity.setPrayingobj(prayingobj[i]);
				pharmacistbirthEntity.setDoritualid(id0);
				pharmacistbirthEntity.setPaymen(paymen);
				pharmacistbirthEntity.setSize(size[i]);
				//换时间
				
				if(!size[i].equals("小")){
					pharmacistbirthEntity.setBook(book);
				}
				
				pharmacistbirthEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				pharmacistbirthEntity.setSerial(f.format(pharmacistbirths.size() + 1));
				pharmacistbirthEntity.setRegistertime(dateString);
//				pharmacistbirthEntityList.add(pharmacistbirthEntity);
				
				pharmacistbirthService.save(pharmacistbirthEntity);
				
				ControllerUtils.save2Pray(pharmacistbirthEntity, pharmacistbirthService);
				
				ids += pharmacistbirthEntity.getId() + ",";
				antoserial[i]=pharmacistbirthEntity.getSerial();
			}
//			req.setAttribute("pharmacistbirthEntityList", pharmacistbirthEntityList);
//			req.setAttribute("ids", ids);
//			return new ModelAndView("com/sys/pharmacistbirth/AutoSerialPharmacistbirth");
			
//------------20180502 Kooking		
			return updatePharmacistbirthAndReceipt(ids, antoserial, model);
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
				
//				List<PharmacistbirthEntity> pharmacistbirthEntityList = new ArrayList<PharmacistbirthEntity>();
				String ids = "";
				String[] antoserial=new String[1];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				PharmacistbirthEntity pharmacistbirthEntity = new PharmacistbirthEntity();
				CriteriaQuery cq = new CriteriaQuery(PharmacistbirthEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",sizeflag),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<PharmacistbirthEntity> pharmacistbirths = pharmacistbirthService.getListByCriteriaQuery(cq,false);
				
				String livingString = "";
				for(int i = 0;i < living.length;i ++){
					if(living[i].equals("") || living[i] == null){
						living[i] = " ";
					}
					livingString += living[i] + ";";
				}
				
				pharmacistbirthEntity.setAddress(address);
				pharmacistbirthEntity.setLivingmenber(livingString);
				pharmacistbirthEntity.setMoney(Integer.valueOf(money));
				pharmacistbirthEntity.setPayway(payway);
				pharmacistbirthEntity.setSummary(summary);
				pharmacistbirthEntity.setPrayingobj(prayingobj);
				pharmacistbirthEntity.setDoritualid(id0);
				pharmacistbirthEntity.setPaymen(paymen);
				pharmacistbirthEntity.setBook(book);
				pharmacistbirthEntity.setSize(sizeflag);
				pharmacistbirthEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				pharmacistbirthEntity.setSerial(f.format(pharmacistbirths.size() + 1));
				pharmacistbirthEntity.setRegistertime(dateString);
//				pharmacistbirthEntityList.add(pharmacistbirthEntity);
					
				pharmacistbirthService.save(pharmacistbirthEntity);
				
				ControllerUtils.save2Pray(pharmacistbirthEntity, pharmacistbirthService);
				
				ids += pharmacistbirthEntity.getId() + ",";
				antoserial[0]=pharmacistbirthEntity.getSerial();
				
//				req.setAttribute("pharmacistbirthEntityList", pharmacistbirthEntityList);
//				req.setAttribute("ids", ids);
//				 return new ModelAndView("com/sys/pharmacistbirth/AutoSerialPharmacistbirth");
				
//------------20180502 Kooking		
				return updatePharmacistbirthAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
		
		    
			
	}
	
	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(PharmacistbirthEntity pharmacistbirthEntity,HttpServletRequest request,Model model) {
		List<PharmacistbirthEntity> pharmacistbirthEntitys = new ArrayList<PharmacistbirthEntity>();
		if (StringUtil.isNotEmpty(pharmacistbirthEntity.getId())) {
			pharmacistbirthEntity = pharmacistbirthService.getEntity(PharmacistbirthEntity.class, pharmacistbirthEntity.getId());
			
			TSUser user = ResourceUtil.getSessionUserName();
			if(!user.getRealName().equals(pharmacistbirthEntity.getRegistrant())){
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			if(pharmacistbirthEntity.getSize().equals("小")){
				pharmacistbirthEntitys.add(pharmacistbirthEntity);
				model.addAttribute("pharmacistbirthEntitys", pharmacistbirthEntitys);
				model.addAttribute("id", pharmacistbirthEntity.getDoritualid());
				model.addAttribute("pharmacistbirthid", pharmacistbirthEntity.getId());
				model.addAttribute("clientele", pharmacistbirthEntity.getPaymen());
				model.addAttribute("size", pharmacistbirthEntity.getSize());
				return new ModelAndView("com/sys/pharmacistbirth/editPharmacistbirth");
			}
			else{
				
				String livingString = pharmacistbirthEntity.getLivingmenber();
				List<String> livingList = new ArrayList<String>();
//				int index = 0;
				String[] livingStringBySemicolon = livingString.split(";");
				for(int i  = 0; i < livingStringBySemicolon.length ; i ++){
					
					livingList.add(livingStringBySemicolon[i]);
				}
				pharmacistbirthEntitys.add(pharmacistbirthEntity);
				model.addAttribute("pharmacistbirthEntity", pharmacistbirthEntity);
				model.addAttribute("pharmacistbirthEntitys", pharmacistbirthEntitys);
				model.addAttribute("livingList", livingList);
				model.addAttribute("id", pharmacistbirthEntity.getDoritualid());
				model.addAttribute("size", pharmacistbirthEntity.getSize());
				model.addAttribute("clientele", pharmacistbirthEntity.getPaymen());
				model.addAttribute("pharmacistbirthid", pharmacistbirthEntity.getId());
				return new ModelAndView("com/sys/pharmacistbirth/editMiddleAndBigPharmacistbirth");
			}
		}
		else{
			return new ModelAndView("com/sys/error");
		}
	}
	
}
