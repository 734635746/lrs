package com.sys.controller.amitabhabirth;
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

import com.sys.entity.amitabhabirth.AmitabhabirthEntity;
import com.sys.entity.ancestor.AncestorEntity;
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.ghostfes.GhostfesEntity;
import com.sys.entity.jizobirth.JIzobirthEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.entity.releasesouls.ReleaseSoulsEntity;
import com.sys.entity.tombsweepfes.TombsweepfesEntity;
import com.sys.service.amitabhabirth.AmitabhabirthServiceI;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;
import com.sys.util.ControllerUtils;

/**   
 * @Title: Controller
 * @Description: 弥陀诞信息
 * @author zhangdaihao
 * @date 2016-03-03 11:54:51
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/amitabhabirthController")
public class AmitabhabirthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(AmitabhabirthController.class);

	@Autowired
	private AmitabhabirthServiceI amitabhabirthService;
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
	 * 弥陀诞信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "amitabhabirth")
	public ModelAndView amitabhabirth(HttpServletRequest request) {
		return new ModelAndView("com/sys/amitabhabirth/amitabhabirthList");
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
	public void datagrid(AmitabhabirthEntity amitabhabirth,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(AmitabhabirthEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, amitabhabirth, request.getParameterMap());
		
		
		/*TSUser user = ResourceUtil.getSessionUserName();
		
		cq.add(Restrictions.eq("registrant", user.getUserName()));*/
		this.amitabhabirthService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除弥陀诞信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(AmitabhabirthEntity amitabhabirth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		amitabhabirth = systemService.getEntity(AmitabhabirthEntity.class, amitabhabirth.getId());
		message = "弥陀诞信息删除成功";
		amitabhabirthService.delete(amitabhabirth);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加弥陀诞信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(AmitabhabirthEntity amitabhabirth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(amitabhabirth.getId())) {
			message = "弥陀诞信息更新成功";
			AmitabhabirthEntity t = amitabhabirthService.get(AmitabhabirthEntity.class, amitabhabirth.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(amitabhabirth, t);
				amitabhabirthService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "弥陀诞信息更新失败";
			}
		} else {
			message = "弥陀诞信息添加成功";
			amitabhabirthService.save(amitabhabirth);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 弥陀诞信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(AmitabhabirthEntity amitabhabirth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(amitabhabirth.getId())) {
			amitabhabirth = amitabhabirthService.getEntity(AmitabhabirthEntity.class, amitabhabirth.getId());
			req.setAttribute("amitabhabirthPage", amitabhabirth);
		}
		return new ModelAndView("com/sys/amitabhabirth/amitabhabirth");
	}
	
	
	@RequestMapping(params = "redirectToShowAmitabhabirth")
	public ModelAndView redirectToShowAmitabhabirth(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");  /*8月7号*/
//		String hql0 = "from AmitabhabirthEntity where 1 = 1 AND doritualid = ? AND size = ?";
		String sql = "select * from release_souls where 1 = 1 AND doritualid = ? AND size = ?";//20180504
		if(id0 != null){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
//			List<AmitabhabirthEntity> amitabhabirthEntitys = amitabhabirthService.findHql(hql0,id0,size);
			List<AmitabhabirthEntity> amitabhabirthEntitys = amitabhabirthService
					.listBySQL(AmitabhabirthEntity.class,sql, id0,size);
			model.addAttribute("doritualinfoEntity", doritualinfoEntity);
			model.addAttribute("amitabhabirthEntitys", amitabhabirthEntitys);
			model.addAttribute("id", id0);
			model.addAttribute("size", size);
		}
		
		return new ModelAndView("com/sys/amitabhabirth/showAmitabhabirthRecord");
	}
	
	@RequestMapping(params = "redirectToSelectSize")  /*8月7号*/
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
			//获取今年的弥陀诞法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "弥陀诞");
			if(funeralhelds.size()!=1){
				model.addAttribute("message", "抱歉，今年的弥陀诞法事举行日期安排 登记不正常，请与系统管理员联系！");
				return new ModelAndView("com/sys/nofuneralheldplan");
			}
			
//			---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记
			
		model.addAttribute("id0", id0);
		
		return new ModelAndView("com/sys/amitabhabirth/selectSize");
	}
	
	/**
	 * 跳转到编辑登记弥陀诞
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToEditAmitabhabirth")
	public ModelAndView redirectToEditAmitabhabirth(HttpServletRequest req,Model model) {
		try{
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
			String amitabhabirthIds = req.getParameter("amitabhabirthIds");
			
			String hql0 = "from AncestorEntity where 1 = 1 AND ritualid = ? ";
			List<AncestorEntity> ancestorEntityList = systemService.findHql(hql0,id0);
			
//			List<AmitabhabirthEntity> amitabhabirthEntitys = new ArrayList<AmitabhabirthEntity>();
			List<ReleaseSoulsEntity> amitabhabirthEntitys = new ArrayList<ReleaseSoulsEntity>();
			
			//获取今年的弥陀诞法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "弥陀诞");
			model.addAttribute("funeralheld", funeralhelds);
			model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
			model.addAttribute("updateFlag", "0");
			
			if(size.equals("小")){
				if(amitabhabirthIds == null){
					model.addAttribute("ancestorEntityList", ancestorEntityList);
					model.addAttribute("id", id0);
					model.addAttribute("clientele", doritualinfoEntity.getRname());
					model.addAttribute("amitabhabirthEntitys", amitabhabirthEntitys);
					model.addAttribute("size", size);
					return new ModelAndView("com/sys/amitabhabirth/editAmitabhabirth");
				}
				

				
				//用逗号切开
				String amitabhabirthIdsSplitByComma[] = amitabhabirthIds.substring(0, amitabhabirthIds.length()-1).split(",");
				//分别获取相关数据
				for(int i = 0;i < amitabhabirthIdsSplitByComma.length;i ++){
//					AmitabhabirthEntity amitabhabirthEntity = amitabhabirthService.get(AmitabhabirthEntity.class, amitabhabirthIdsSplitByComma[i]);
					ReleaseSoulsEntity amitabhabirthEntity = amitabhabirthService.get(ReleaseSoulsEntity.class, amitabhabirthIdsSplitByComma[i]);
					amitabhabirthEntitys.add(amitabhabirthEntity);
				}
				model.addAttribute("amitabhabirthEntitys", amitabhabirthEntitys);
				model.addAttribute("ancestorEntityList", ancestorEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				
				return new ModelAndView("com/sys/amitabhabirth/editAmitabhabirth");
			}
			else{ //中大牌跳转
				if(amitabhabirthIds == null){
					model.addAttribute("ancestorEntityList", ancestorEntityList);
					model.addAttribute("id", id0);
					model.addAttribute("clientele", doritualinfoEntity.getRname());
					model.addAttribute("amitabhabirthEntitys", amitabhabirthEntitys);
					model.addAttribute("size", size);
					return new ModelAndView("com/sys/amitabhabirth/editMiddleAndBigAmitabhabirth");
				}
				else{
					//已经有数据
//					AmitabhabirthEntity amitabhabirthEntity = amitabhabirthService.get(AmitabhabirthEntity.class, amitabhabirthIds.substring(0, amitabhabirthIds.length() - 1));
					ReleaseSoulsEntity amitabhabirthEntity = amitabhabirthService.get(ReleaseSoulsEntity.class, amitabhabirthIds.substring(0, amitabhabirthIds.length() - 1));
					
//					String ancestorString = amitabhabirthEntity.getAncestor();
					String []ancestorStringByPound  = amitabhabirthEntity.getAncestor().substring(0, amitabhabirthEntity.getAncestor().length()).split("#");
					List<String> ancestorList = new ArrayList<String>();
					String surname = "",tmptype = "1";
					int index = 0;
					if(amitabhabirthEntity.getType() == 5){
						surname = ancestorStringByPound[0];
						index = 1;
					}
					if(ancestorStringByPound[index + 1].equals("1")){
						tmptype = "2";
					}
					String[] ancestorStringBySemicolon = ancestorStringByPound[index].split(";");
					for(int i  = 0; i < ancestorStringBySemicolon.length ; i ++){
						
						ancestorList.add(ancestorStringBySemicolon[i]);
					}
					amitabhabirthEntitys.add(amitabhabirthEntity);
					model.addAttribute("amitabhabirthEntity", amitabhabirthEntity);
					model.addAttribute("amitabhabirthEntitys", amitabhabirthEntitys);
					model.addAttribute("surname", surname);
					model.addAttribute("type", tmptype);
					model.addAttribute("ancestorList", ancestorList);
					model.addAttribute("id", id0);
					model.addAttribute("size", size);
					model.addAttribute("clientele", doritualinfoEntity.getRname());
					return new ModelAndView("com/sys/amitabhabirth/editMiddleAndBigAmitabhabirth");
				}
				
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
		
	}
	
	/**
	 * 保存编辑后的药师诞记录和收据
	 * @param req
	 * @param model
	 * @return
	 */
	/*@RequestMapping(params = "updateAmitabhabirthAndReceipt")
	public ModelAndView updateAmitabhabirthAndReceipt(HttpServletRequest req,Model model) {*/
	private ModelAndView updateAmitabhabirthAndReceipt(String ids,String[] antoserial,Model model) {
		
		try{
		
//			String ids = req.getParameter("ids");
			String subids = ids.substring(0, ids.length()-1);
			String[] id = subids.split(",");
			
			//获取自动编号
//			String[] antoserial = req.getParameterValues("autoserial");
			
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
			
			
			
			/*打印摘要修改*/
			//获取今年的弥陀诞法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
				.getThisYearFuneralheldsByRitualtype(systemService, "弥陀诞");
			FuneralheldEntity funeralheldEntity = funeralhelds.get(0);
			String sumSummary ="", res = "";
			
			AmitabhabirthEntity entity = amitabhabirthService.get(AmitabhabirthEntity.class, id[0]);
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
			for(int i = 0;i < id.length;i ++){
				AmitabhabirthEntity te = amitabhabirthService.get(AmitabhabirthEntity.class, id[i]);
				te.setAutoserial(antoserial[i]);
				te.setRegistertime(dateString);
				te.setRegistrant(user.getRealName());
				te.setReceiptno(re.getNo());
				amitabhabirthService.updateEntitie(te);
				
				sum += te.getMoney();
				obj += te.getAncestor();
//				sumSummary += te.getSummary() + "#";
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
			re.setSize(entity.getSize());
			re.setPayway(entity.getPayway());
			re.setRegistrant(user.getRealName());
			re.setAddress(entity.getAddress());
			re.setDoritualid(entity.getDoritualid());
			re.setMoney(sum);
			re.setSummary(sumSummary);
			re.setRemark(res);
			re.setObj(obj);
			re.setDate("二#十九#二#廿七");
			
			//保存收据
	
			
			
			re.setRitualtype("弥陀诞");
			re.setRegistertime(dateString);
			re.setPurpose("弥陀诞");
			re.setFlag(0);
			receiptService.save(re);
			String receipId = re.getId();
			
			for(int i = 0;i < id.length;i ++){
				AmitabhabirthEntity te = amitabhabirthService.get(AmitabhabirthEntity.class, id[i]);
				te.setReceiptid(receipId);
				amitabhabirthService.updateEntitie(te);
			}
			
			
			model.addAttribute("message", "弥陀诞登记成功");
			model.addAttribute("ritualtype", "弥陀诞");
			ReceiptEntity returnRe = receiptService.get(ReceiptEntity.class, re.getId());
			model.addAttribute("returnRe", returnRe);
			String bigMoney=ChineseCurrency.toChineseCurrency(new Double(returnRe.getMoney()));
			model.addAttribute("bigMoney",bigMoney);
			String smallMoney=ChineseCurrency.toSmall(new Double(returnRe.getMoney()));
			model.addAttribute("smallMoney",smallMoney);
			
			
			return new ModelAndView("com/sys/success");
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
		
	}
	
	
	@RequestMapping(params = "getSerialAndSaveTablet") /*8月17号改动*/
	public ModelAndView getSerial(HttpServletRequest req,Model model){
		
		//修改牌位
		
		String amitabhabirthid = req.getParameter("amitabhabirthid");
		if(amitabhabirthid != null && !amitabhabirthid.equals("")){
			AmitabhabirthEntity amitabhabirthEntity = amitabhabirthService.get(AmitabhabirthEntity.class, amitabhabirthid);
			
			if(amitabhabirthEntity.getSize().equals("小")){
				amitabhabirthEntity.setAddress(req.getParameter("address"));
				amitabhabirthEntity.setAncestor(req.getParameter("ancestor"));
				amitabhabirthEntity.setMoney(Integer.valueOf(req.getParameter("money")));
				amitabhabirthEntity.setPayway(req.getParameter("payway"));
				amitabhabirthEntity.setSummary(req.getParameter("summary"));
				amitabhabirthEntity.setPrayingobj(req.getParameter("prayingobj"));
				amitabhabirthEntity.setPaymen(req.getParameter("paymen"));
				amitabhabirthEntity.setType(Integer.valueOf(req.getParameter("type")));
				amitabhabirthService.updateEntitie(amitabhabirthEntity);
				model.addAttribute("message", "弥陀诞牌位修改成功！！");
				return new ModelAndView("com/sys/updateSuccess");
			}
			else{
				String[] ancestor = req.getParameterValues("ancestor");
				String surname = req.getParameter("surname");
				String type = req.getParameter("type");
				String ancestorString = "";
				if(surname != ""){
					ancestorString += surname + "#";
				}
				for(int i = 0;i < ancestor.length;i ++){
					if(ancestor[i].equals("") || ancestor[i] == null){
						ancestor[i] = " ";
					}
					ancestorString += ancestor[i] + ";";
				}
				if(type.equals("2")){
					ancestorString += "#1";
				}
				else{
					ancestorString += "#0";
				}
				amitabhabirthEntity.setBook(req.getParameter("book"));
				amitabhabirthEntity.setAddress(req.getParameter("address"));
				amitabhabirthEntity.setAncestor(ancestorString);
				amitabhabirthEntity.setMoney(Integer.valueOf(req.getParameter("money")));
				amitabhabirthEntity.setPayway(req.getParameter("payway"));
				amitabhabirthEntity.setSummary(req.getParameter("summary"));
				amitabhabirthEntity.setPrayingobj(req.getParameter("prayingobj"));
				amitabhabirthEntity.setPaymen(req.getParameter("paymen"));
				if(surname == ""){
					amitabhabirthEntity.setType(6);
				}
				else{
					amitabhabirthEntity.setType(5);
				}
				amitabhabirthService.updateEntitie(amitabhabirthEntity);
				model.addAttribute("message", "弥陀诞牌位修改成功！！");
				return new ModelAndView("com/sys/updateSuccess");
			}
		}
		try{
			
			//获取做法事人的ID
			String sizeflag = req.getParameter("sizeflag");
			if(sizeflag == null || sizeflag.equals("小")){
				String id0 = req.getParameter("id");
				
				String paymen = req.getParameter("paymen");
				
				//获取祈福者
				String[] prayingobj = req.getParameterValues("prayingobj");
				
				//获取祈福对象
				String[] ancestor = req.getParameterValues("ancestor");
				
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
				
				//获取大小
				String[] type = req.getParameterValues("type");
				
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				String currentyear = String.valueOf(year);
				
				for(int i = 0;i < type.length;i++){
					if(type[i].equals("2")){
						ancestor[i] = "";
					}
				}
//				List<AmitabhabirthEntity> amitabhabirthEntityList = new ArrayList<AmitabhabirthEntity>();
				String ids = "";
				String[] antoserial=new String[size.length];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				for(int i = 0;i < size.length;i ++){
					AmitabhabirthEntity amitabhabirthEntity = new AmitabhabirthEntity();
					CriteriaQuery cq = new CriteriaQuery(AmitabhabirthEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("size",size[i]),Restrictions.like("registertime",currentyear,MatchMode.START)));
					List<AmitabhabirthEntity> amitabhabirths = amitabhabirthService.getListByCriteriaQuery(cq,false);
					
					amitabhabirthEntity.setAddress(address[i]);
					amitabhabirthEntity.setAncestor(ancestor[i]);
					amitabhabirthEntity.setMoney(Integer.valueOf(money[i]));
					amitabhabirthEntity.setPayway(payway[i]);
					amitabhabirthEntity.setSummary(summary[i]);
					amitabhabirthEntity.setPrayingobj(prayingobj[i]);
					amitabhabirthEntity.setPaymen(paymen);
					amitabhabirthEntity.setDoritualid(id0);
					
					amitabhabirthEntity.setSize(size[i]);
					//换时间
					amitabhabirthEntity.setType(Integer.valueOf(type[i]));
					amitabhabirthEntity.setFlag(0);
					NumberFormat f=new DecimalFormat("000000");
					amitabhabirthEntity.setSerial(f.format(amitabhabirths.size() + 1));
					amitabhabirthEntity.setRegistertime(dateString);
//					amitabhabirthEntityList.add(amitabhabirthEntity);
					
					amitabhabirthService.save(amitabhabirthEntity);
					
					ControllerUtils.save2ReleaseSouls(amitabhabirthEntity, amitabhabirthService);
					
					ids += amitabhabirthEntity.getId() + ",";
					antoserial[i]=amitabhabirthEntity.getSerial();
				}
//				req.setAttribute("amitabhabirthEntityList", amitabhabirthEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView("com/sys/amitabhabirth/AutoSerialAmitabhabirthEntity");
				
//------------20180502 Kooking		
				return updateAmitabhabirthAndReceipt(ids, antoserial, model);
//------------20180502 Kooking					
			}
			else{ //大中牌
				String id0 = req.getParameter("id");
				
				//获取祈福者
				String prayingobj = req.getParameter("prayingobj");
				
				//获取祈福对象
				String[] ancestor = req.getParameterValues("ancestor");
				
				//获取祈福者家庭住址
				String address = req.getParameter("address");
				
				//获取摘要
				String summary = req.getParameter("summary");
				
				//获取摘要
				String payway = req.getParameter("payway");

				String paymen = req.getParameter("paymen");
				
				//获取摘要
				String money = req.getParameter("money");
				
				String surname = req.getParameter("surname");
				
				String book = req.getParameter("book");
	
				String type = req.getParameter("type");
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				String currentyear = String.valueOf(year);
				
				AmitabhabirthEntity amitabhabirthEntity = new AmitabhabirthEntity();
				
				//获取序号
				CriteriaQuery cq = new CriteriaQuery(AmitabhabirthEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",sizeflag),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<AmitabhabirthEntity> amitabhabirths = amitabhabirthService.getListByCriteriaQuery(cq,false);
				
//				List<AmitabhabirthEntity> amitabhabirthEntityList = new ArrayList<AmitabhabirthEntity>();
				
				String ids = "";
				String[] antoserial=new String[1];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				String ancestorString = "";
				if(surname != ""){
					ancestorString += surname + "#";
				}
				
				for(int i = 0;i < ancestor.length;i ++){
					if(ancestor[i].equals("") || ancestor[i] == null){
						ancestor[i] = " ";
					}
					ancestorString += ancestor[i] + ";";
				}
				if(type.equals("2")){
					ancestorString += "#1";
				}
				else{
					ancestorString += "#0";
				}
				
				amitabhabirthEntity.setBook(book);
				amitabhabirthEntity.setAddress(address);
				amitabhabirthEntity.setAncestor(ancestorString);
				amitabhabirthEntity.setMoney(Integer.valueOf(money));
				amitabhabirthEntity.setPayway(payway);
				amitabhabirthEntity.setSummary(summary);
				amitabhabirthEntity.setPrayingobj(prayingobj);
				amitabhabirthEntity.setPaymen(paymen);
				amitabhabirthEntity.setDoritualid(id0);
				amitabhabirthEntity.setSize(sizeflag);
				if(surname == ""){
					amitabhabirthEntity.setType(6);
				}
				else{
					amitabhabirthEntity.setType(5);
				}
				//换时间
				amitabhabirthEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				amitabhabirthEntity.setSerial(f.format(amitabhabirths.size() + 1));
				amitabhabirthEntity.setRegistertime(dateString);
				
				amitabhabirthService.save(amitabhabirthEntity);
				
				ControllerUtils.save2ReleaseSouls(amitabhabirthEntity, amitabhabirthService);
				
				ids += amitabhabirthEntity.getId() + ",";
				antoserial[0]=amitabhabirthEntity.getSerial();
//				amitabhabirthEntityList.add(amitabhabirthEntity);
//				req.setAttribute("amitabhabirthEntityList", amitabhabirthEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView("com/sys/amitabhabirth/AutoSerialAmitabhabirthEntity");
				
//------------20180502 Kooking		
				return updateAmitabhabirthAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
			}
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
		
			
	}
	
	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(AmitabhabirthEntity amitabhabirthEntity,HttpServletRequest request,Model model) {
		
		
		List<AmitabhabirthEntity> amitabhabirthEntitys = new ArrayList<AmitabhabirthEntity>();
		if (StringUtil.isNotEmpty(amitabhabirthEntity.getId())) {
			amitabhabirthEntity = amitabhabirthService.getEntity(AmitabhabirthEntity.class, amitabhabirthEntity.getId());
			TSUser user = ResourceUtil.getSessionUserName();
			if(!user.getRealName().equals(amitabhabirthEntity.getRegistrant())){
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			if(amitabhabirthEntity.getSize().equals("小")){
				amitabhabirthEntitys.add(amitabhabirthEntity);
				model.addAttribute("amitabhabirthEntitys", amitabhabirthEntitys);
				model.addAttribute("ancestorEntityList", amitabhabirthEntity.getAncestor());
				model.addAttribute("id", amitabhabirthEntity.getDoritualid());
				model.addAttribute("amitabhabirthid", amitabhabirthEntity.getId());
				model.addAttribute("clientele", amitabhabirthEntity.getPaymen());
				model.addAttribute("size", amitabhabirthEntity.getSize());
				return new ModelAndView("com/sys/amitabhabirth/editAmitabhabirth");
			}
			else{
//				String ancestorString = amitabhabirthEntity.getAncestor();
				String []ancestorStringByPound  = amitabhabirthEntity.getAncestor().substring(0, amitabhabirthEntity.getAncestor().length()).split("#");
				List<String> ancestorList = new ArrayList<String>();
				String surname = "",tmptype = "1";
				int index = 0;
				if(amitabhabirthEntity.getType() == 5){
					surname = ancestorStringByPound[0];
					index = 1;
				}
				if(ancestorStringByPound[index + 1].equals("1")){
					tmptype = "2";
				}
				String[] ancestorStringBySemicolon = ancestorStringByPound[index].split(";");
				for(int i  = 0; i < ancestorStringBySemicolon.length ; i ++){
					
					ancestorList.add(ancestorStringBySemicolon[i]);
				}
				amitabhabirthEntitys.add(amitabhabirthEntity);
				model.addAttribute("amitabhabirthEntity", amitabhabirthEntity);
				model.addAttribute("amitabhabirthEntitys", amitabhabirthEntitys);
				model.addAttribute("surname", surname);
				model.addAttribute("type", tmptype);
				model.addAttribute("ancestorList", ancestorList);
				model.addAttribute("id", amitabhabirthEntity.getId());
				model.addAttribute("size", amitabhabirthEntity.getSize());
				model.addAttribute("amitabhabirthid", amitabhabirthEntity.getId());
				return new ModelAndView("com/sys/amitabhabirth/editMiddleAndBigAmitabhabirth");
			}
		}
		else{
			return new ModelAndView("com/sys/error");
		}
	}
	
}
