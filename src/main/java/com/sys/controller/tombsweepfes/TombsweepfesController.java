package com.sys.controller.tombsweepfes;
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
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.ghostfes.GhostfesEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.entity.releasesouls.ReleaseSoulsEntity;
import com.sys.entity.tombsweepfes.TombsweepfesEntity;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.funeralheld.FuneralheldServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.service.tombsweepfes.TombsweepfesServiceI;
import com.sys.util.ChineseCurrency;
import com.sys.util.ControllerUtils;

/**   
 * @Title: Controller
 * @Description: 清明节
 * @author zhangdaihao
 * @date 2016-03-03 10:05:50
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tombsweepfesController")
public class TombsweepfesController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(TombsweepfesController.class);

	@Autowired
	private TombsweepfesServiceI tombsweepfesService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private DoritualinfoServiceI doritualinfoService;
	@Autowired
	private ReceiptnoServiceI receiptnoService;
	@Autowired
	private ReceiptServiceI receiptService;
	private String message;
	
	//@Autowired
	//private FuneralheldServiceI funeralheldService;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 清明节列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tombsweepfes")
	public ModelAndView tombsweepfes(HttpServletRequest request) {
		return new ModelAndView("com/sys/tombsweepfes/tombsweepfesList");
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
	public void datagrid(TombsweepfesEntity tombsweepfes,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(TombsweepfesEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tombsweepfes, request.getParameterMap());
		this.tombsweepfesService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除清明节
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(TombsweepfesEntity tombsweepfes, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tombsweepfes = systemService.getEntity(TombsweepfesEntity.class, tombsweepfes.getId());
		message = "清明节删除成功";
		tombsweepfesService.delete(tombsweepfes);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加清明节
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(TombsweepfesEntity tombsweepfes, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tombsweepfes.getId())) {
			message = "清明节更新成功";
			TombsweepfesEntity t = tombsweepfesService.get(TombsweepfesEntity.class, tombsweepfes.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tombsweepfes, t);
				tombsweepfesService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "清明节更新失败";
			}
		} else {
			message = "清明节添加成功";
			tombsweepfesService.save(tombsweepfes);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 清明节列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(TombsweepfesEntity tombsweepfes, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tombsweepfes.getId())) {
			tombsweepfes = tombsweepfesService.getEntity(TombsweepfesEntity.class, tombsweepfes.getId());
			req.setAttribute("tombsweepfesPage", tombsweepfes);
		}
		return new ModelAndView("com/sys/tombsweepfes/tombsweepfes");
	}
	
	@RequestMapping(params = "redirectToShowTombsweepfesRecord")
	public ModelAndView redirectToShowTombsweepfesRecord(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");  /*8月7号*/
//		String hql0 = "from TombsweepfesEntity where 1 = 1 AND doritualid = ? AND size = ?";
		String sql = "select * from release_souls where 1 = 1 AND doritualid = ? AND size = ?";//20180504
		
		
		if(id0 != null){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
//			List<TombsweepfesEntity> tombsweepfesEntitys = tombsweepfesService.findHql(hql0,id0,size);
			List<TombsweepfesEntity> tombsweepfesEntitys = tombsweepfesService
					.listBySQL(TombsweepfesEntity.class,sql, id0,size);
			model.addAttribute("doritualinfoEntity", doritualinfoEntity);
			model.addAttribute("tombsweepfesEntitys", tombsweepfesEntitys);
			model.addAttribute("id", id0);
			model.addAttribute("size", size);
		}
		
		return new ModelAndView("com/sys/tombsweepfes/showTombsweepfesRecord");
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
			//查询今年的清明节法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "清明节");
			if(funeralhelds.size()!=1){
				model.addAttribute("message", "抱歉，今年的清明节法事举行日期安排 登记不正常，请与系统管理员联系！");
				return new ModelAndView("com/sys/nofuneralheldplan");
			}
			
//			---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记			
			
		model.addAttribute("id0", id0);
		return new ModelAndView("com/sys/tombsweepfes/selectSize");
	}
	
	
	/**
	 * 跳转到编辑登记药师诞
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToEditTombsweepfes")
	public ModelAndView redirectToEditTombsweepfes(HttpServletRequest req,Model model) {
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
			String tombsweetfesIds = req.getParameter("tombsweetfesIds");
			
			String hql0 = "from AncestorEntity where 1 = 1 AND ritualid = ? ";
			List<AncestorEntity> ancestorEntityList = systemService.findHql(hql0,id0);
			
			//查询今年的清明节法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "清明节");
			//System.out.println(funeralhelds.get(0).getHoldDate() + "----------------------------------------------------");
			model.addAttribute("funeralheld", funeralhelds);
			model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
			model.addAttribute("updateFlag", "0");
			
//			List<TombsweepfesEntity> tombsweepfesEntitys = new ArrayList<TombsweepfesEntity>();
			List<ReleaseSoulsEntity> tombsweepfesEntitys = new ArrayList<ReleaseSoulsEntity>();
			if(size.equals("小")){
				if(tombsweetfesIds == null){
					model.addAttribute("ancestorEntityList", ancestorEntityList);
					model.addAttribute("id", id0);
					model.addAttribute("clientele", doritualinfoEntity.getRname());
					model.addAttribute("tombsweepfesEntitys", tombsweepfesEntitys);
					return new ModelAndView("com/sys/tombsweepfes/editTombsweepfes");
				}
				
				
				
				//用逗号切开
				String tombsweetfesIdsSplitByComma[] = tombsweetfesIds.substring(0, tombsweetfesIds.length()-1).split(",");
				
				//分别获取相关数据
				for(int i = 0;i < tombsweetfesIdsSplitByComma.length;i ++){
//					TombsweepfesEntity tombsweepfesEntity = tombsweepfesService.get(TombsweepfesEntity.class, tombsweetfesIdsSplitByComma[i]);
					ReleaseSoulsEntity tombsweepfesEntity = tombsweepfesService.get(ReleaseSoulsEntity.class, tombsweetfesIdsSplitByComma[i]);
					tombsweepfesEntitys.add(tombsweepfesEntity);
				}
				
				
					
		
				model.addAttribute("tombsweepfesEntitys", tombsweepfesEntitys);
				model.addAttribute("ancestorEntityList", ancestorEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				
				return new ModelAndView("com/sys/tombsweepfes/editTombsweepfes");
			}
			else{ //中大牌跳转
				if(tombsweetfesIds == null){
					model.addAttribute("ancestorEntityList", ancestorEntityList);
					model.addAttribute("id", id0);
					model.addAttribute("clientele", doritualinfoEntity.getRname());
					model.addAttribute("tombsweepfesEntitys", tombsweepfesEntitys);
					model.addAttribute("size", size);
					return new ModelAndView("com/sys/tombsweepfes/editMiddleAndBigTombsweepfes");
				}
				else{
					//已经有数据
//					TombsweepfesEntity tombsweepfesEntity = tombsweepfesService.get(TombsweepfesEntity.class, tombsweetfesIds.substring(0, tombsweetfesIds.length() - 1));
					ReleaseSoulsEntity tombsweepfesEntity = tombsweepfesService.get(ReleaseSoulsEntity.class, tombsweetfesIds.substring(0, tombsweetfesIds.length() - 1));
					
//					String ancestorString = tombsweepfesEntity.getAncestor();
					String []ancestorStringByPound  = tombsweepfesEntity.getAncestor().substring(0, tombsweepfesEntity.getAncestor().length()).split("#");
					List<String> ancestorList = new ArrayList<String>();
					
					String surname = "",tmptype = "1";
					int index = 0;
					if(tombsweepfesEntity.getType() == 5){
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
					tombsweepfesEntitys.add(tombsweepfesEntity);
					model.addAttribute("tombsweepfesEntity", tombsweepfesEntity);
					model.addAttribute("tombsweepfesEntitys", tombsweepfesEntitys);
					model.addAttribute("surname", surname);
					model.addAttribute("type", tmptype);
					model.addAttribute("ancestorList", ancestorList);
					model.addAttribute("id", id0);
					model.addAttribute("size", size);
					model.addAttribute("clientele", doritualinfoEntity.getRname());
					return new ModelAndView("com/sys/tombsweepfes/editMiddleAndBigTombsweepfes");
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
	/*@RequestMapping(params = "updateTombsweepfesAndReceipt")
	public ModelAndView updateTombsweepfesAndReceipt(HttpServletRequest req,Model model) {*/
	private ModelAndView updateTombsweepfesAndReceipt(String ids,String[] antoserial,Model model) {
		try{
			//获取清明节记录的ID
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
			//查询今年的清明节法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "清明节");
			FuneralheldEntity funeralheldEntity = funeralhelds.get(0);
			String sumSummary ="", res = "";
			
			TombsweepfesEntity entity = tombsweepfesService.get(TombsweepfesEntity.class, id[0]);
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
				TombsweepfesEntity te = tombsweepfesService.get(TombsweepfesEntity.class, id[i]);
				te.setAutoserial(antoserial[i]);
				te.setRegistertime(dateString);
				te.setRegistrant(user.getRealName());
				te.setReceiptno(re.getNo());
				tombsweepfesService.updateEntitie(te);
				
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
			re.setSummary(sumSummary);
			re.setRemark(res);
			re.setSize(entity.getSize());
			re.setObj(obj);
			re.setDate("二#十九#二#廿七");
			//保存收据
	
			
			
			re.setRitualtype("清明节");
			re.setRegistertime(dateString);
			re.setPurpose("清明节");
			re.setFlag(0);
			receiptService.save(re);
			String receipId = re.getId();
	
			for(int i = 0;i < id.length;i ++){
				TombsweepfesEntity te = tombsweepfesService.get(TombsweepfesEntity.class, id[i]);
				te.setReceiptid(receipId);
				tombsweepfesService.updateEntitie(te);
			}
			
			
			model.addAttribute("message", "清明节登记成功");
			model.addAttribute("ritualtype", "清明节");
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
	
	
	@RequestMapping(params = "getSerialAndSaveTablet")
	public ModelAndView getSerial(HttpServletRequest req,Model model){
		//修改牌位
		String tombsweepfesid = req.getParameter("tombsweepfesid");
		if(tombsweepfesid != null && !tombsweepfesid.equals("")){
			TombsweepfesEntity tombsweepfesEntity = tombsweepfesService.get(TombsweepfesEntity.class, tombsweepfesid);
			if(tombsweepfesEntity.getSize().equals("小")){
				tombsweepfesEntity.setAddress(req.getParameter("address"));
				tombsweepfesEntity.setAncestor(req.getParameter("ancestor"));
				tombsweepfesEntity.setMoney(Integer.valueOf(req.getParameter("money")));
				tombsweepfesEntity.setPayway(req.getParameter("payway"));
				tombsweepfesEntity.setSummary(req.getParameter("summary"));
				tombsweepfesEntity.setPrayingobj(req.getParameter("prayingobj"));
				tombsweepfesEntity.setPaymen(req.getParameter("paymen"));
				tombsweepfesEntity.setType(Integer.valueOf(req.getParameter("type")));
				tombsweepfesService.updateEntitie(tombsweepfesEntity);
				model.addAttribute("message", "清明节牌位修改成功！！");
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
				tombsweepfesEntity.setBook(req.getParameter("book"));
				tombsweepfesEntity.setAddress(req.getParameter("address"));
				tombsweepfesEntity.setAncestor(ancestorString);
				tombsweepfesEntity.setMoney(Integer.valueOf(req.getParameter("money")));
				tombsweepfesEntity.setPayway(req.getParameter("payway"));
				tombsweepfesEntity.setSummary(req.getParameter("summary"));
				tombsweepfesEntity.setPrayingobj(req.getParameter("prayingobj"));
				tombsweepfesEntity.setPaymen(req.getParameter("paymen"));
				if(surname == ""){
					tombsweepfesEntity.setType(6);
				}
				else{
					tombsweepfesEntity.setType(5);
				}
				tombsweepfesService.updateEntitie(tombsweepfesEntity);
				model.addAttribute("message", "清明节牌位修改成功！！");
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
//				List<TombsweepfesEntity> tombsweepfesEntityList = new ArrayList<TombsweepfesEntity>();
				String ids = "";
				String[] antoserial=new String[size.length];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				for(int i = 0;i < size.length;i ++){
					TombsweepfesEntity tombsweepfesEntity = new TombsweepfesEntity();
					CriteriaQuery cq = new CriteriaQuery(TombsweepfesEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("size",size[i]),Restrictions.like("registertime",currentyear,MatchMode.START)));
					List<TombsweepfesEntity> tombsweepfess = tombsweepfesService.getListByCriteriaQuery(cq,false);
					
					tombsweepfesEntity.setAddress(address[i]);
					tombsweepfesEntity.setAncestor(ancestor[i]);
					tombsweepfesEntity.setMoney(Integer.valueOf(money[i]));
					tombsweepfesEntity.setPayway(payway[i]);
					tombsweepfesEntity.setSummary(summary[i]);
					tombsweepfesEntity.setPrayingobj(prayingobj[i]);
					tombsweepfesEntity.setDoritualid(id0);
					tombsweepfesEntity.setSize(size[i]);
					
					tombsweepfesEntity.setPaymen(paymen);
					//换时间
					tombsweepfesEntity.setType(Integer.valueOf(type[i]));
					tombsweepfesEntity.setFlag(0);
					NumberFormat f=new DecimalFormat("000000");
					tombsweepfesEntity.setSerial(f.format(tombsweepfess.size() + 1));
					tombsweepfesEntity.setRegistertime(dateString);
//					tombsweepfesEntityList.add(tombsweepfesEntity);
					
					tombsweepfesService.save(tombsweepfesEntity);
					
					ControllerUtils.save2ReleaseSouls(tombsweepfesEntity, tombsweepfesService);
					
					ids += tombsweepfesEntity.getId() + ",";
					antoserial[i]=tombsweepfesEntity.getSerial();
				}
//				req.setAttribute("tombsweepfesEntityList", tombsweepfesEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView("com/sys/tombsweepfes/AutoSerialTombsweepfes");
				
//------------20180502 Kooking		
				return updateTombsweepfesAndReceipt(ids, antoserial, model);
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
				
				TombsweepfesEntity tombsweepfesEntity = new TombsweepfesEntity();
				
				//获取序号
				CriteriaQuery cq = new CriteriaQuery(TombsweepfesEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",sizeflag),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<TombsweepfesEntity> tombsweepfess = tombsweepfesService.getListByCriteriaQuery(cq,false);
				
//				List<TombsweepfesEntity> tombsweepfesEntityList = new ArrayList<TombsweepfesEntity>();
				
				String ids = "";
				String[] antoserial=new String[1];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				String ancestorString = "";
				if(surname != ""){
					ancestorString += surname  + "#";
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
				
				
				tombsweepfesEntity.setBook(book);
				tombsweepfesEntity.setAddress(address);
				tombsweepfesEntity.setAncestor(ancestorString);
				tombsweepfesEntity.setMoney(Integer.valueOf(money));
				tombsweepfesEntity.setPayway(payway);
				tombsweepfesEntity.setSummary(summary);
				tombsweepfesEntity.setPrayingobj(prayingobj);
				tombsweepfesEntity.setPaymen(paymen);
				tombsweepfesEntity.setDoritualid(id0);
				tombsweepfesEntity.setSize(sizeflag);
				if(surname == ""){
					tombsweepfesEntity.setType(6);
				}
				else{
					tombsweepfesEntity.setType(5);
				}
				//换时间
				tombsweepfesEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				tombsweepfesEntity.setSerial(f.format(tombsweepfess.size() + 1));
				tombsweepfesEntity.setRegistertime(dateString);
				
				tombsweepfesService.save(tombsweepfesEntity);
				
				ControllerUtils.save2ReleaseSouls(tombsweepfesEntity, tombsweepfesService);
				
				ids += tombsweepfesEntity.getId() + ",";
				antoserial[0]=tombsweepfesEntity.getSerial();
				
//				tombsweepfesEntityList.add(tombsweepfesEntity);
//				req.setAttribute("tombsweepfesEntityList", tombsweepfesEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView("com/sys/tombsweepfes/AutoSerialTombsweepfes");
				
//------------20180502 Kooking		
				return updateTombsweepfesAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
				
			}
				
			
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
	}
	
	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(TombsweepfesEntity tombsweepfesEntity,HttpServletRequest request,Model model) {
		List<TombsweepfesEntity> tombsweepfesEntitys = new ArrayList<TombsweepfesEntity>();
		if (StringUtil.isNotEmpty(tombsweepfesEntity.getId())) {
			tombsweepfesEntity = tombsweepfesService.getEntity(TombsweepfesEntity.class, tombsweepfesEntity.getId());
			
			TSUser user = ResourceUtil.getSessionUserName();
			if(!user.getRealName().equals(tombsweepfesEntity.getRegistrant())){
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			if(tombsweepfesEntity.getSize().equals("小")){
				tombsweepfesEntitys.add(tombsweepfesEntity);
				model.addAttribute("tombsweepfesEntitys", tombsweepfesEntitys);
				model.addAttribute("ancestorEntityList", tombsweepfesEntity.getAncestor());
				model.addAttribute("id", tombsweepfesEntity.getDoritualid());
				model.addAttribute("tombsweepfesid", tombsweepfesEntity.getId());
				model.addAttribute("clientele", tombsweepfesEntity.getPaymen());
				model.addAttribute("size", tombsweepfesEntity.getSize());
				return new ModelAndView("com/sys/tombsweepfes/editTombsweepfes");
			}
			else{
//				String ancestorString = tombsweepfesEntity.getAncestor();
				String []ancestorStringByPound  = tombsweepfesEntity.getAncestor().substring(0, tombsweepfesEntity.getAncestor().length()).split("#");
				List<String> ancestorList = new ArrayList<String>();
				String surname = "",tmptype = "1";
				int index = 0;
				if(tombsweepfesEntity.getType() == 5){
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
				tombsweepfesEntitys.add(tombsweepfesEntity);
				model.addAttribute("tombsweepfesEntity", tombsweepfesEntity);
				model.addAttribute("tombsweepfesEntitys", tombsweepfesEntitys);
				model.addAttribute("surname", surname);
				model.addAttribute("type", tmptype);
				model.addAttribute("ancestorList", ancestorList);
				model.addAttribute("id", tombsweepfesEntity.getId());
				model.addAttribute("size", tombsweepfesEntity.getSize());
				model.addAttribute("tombsweepfesid", tombsweepfesEntity.getId());
				return new ModelAndView("com/sys/tombsweepfes/editMiddleAndBigTombsweepfes");
			}
		}
		else{
			return new ModelAndView("com/sys/error");
		}
	}
}
