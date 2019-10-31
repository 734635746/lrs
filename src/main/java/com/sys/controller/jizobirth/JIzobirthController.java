package com.sys.controller.jizobirth;
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
import com.sys.entity.jizobirth.JIzobirthEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.entity.releasesouls.ReleaseSoulsEntity;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.jizobirth.JIzobirthServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.util.ChineseCurrency;
import com.sys.util.ControllerUtils;

/**   
 * @Title: Controller
 * @Description: 地藏诞
 * @author zhangdaihao
 * @date 2016-03-03 11:09:06
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/jIzobirthController")
public class JIzobirthController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(JIzobirthController.class);

	@Autowired
	private JIzobirthServiceI jIzobirthService;
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
	 * 地藏诞列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "jIzobirth")
	public ModelAndView jIzobirth(HttpServletRequest request) {
		return new ModelAndView("com/sys/jizobirth/jIzobirthList");
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
	public void datagrid(JIzobirthEntity jIzobirth,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(JIzobirthEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, jIzobirth, request.getParameterMap());
		this.jIzobirthService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除地藏诞
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(JIzobirthEntity jIzobirth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		jIzobirth = systemService.getEntity(JIzobirthEntity.class, jIzobirth.getId());
		message = "地藏诞删除成功";
		jIzobirthService.delete(jIzobirth);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加地藏诞
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(JIzobirthEntity jIzobirth, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(jIzobirth.getId())) {
			message = "地藏诞更新成功";
			JIzobirthEntity t = jIzobirthService.get(JIzobirthEntity.class, jIzobirth.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(jIzobirth, t);
				jIzobirthService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "地藏诞更新失败";
			}
		} else {
			message = "地藏诞添加成功";
			jIzobirthService.save(jIzobirth);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 地藏诞列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(JIzobirthEntity jIzobirth, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(jIzobirth.getId())) {
			jIzobirth = jIzobirthService.getEntity(JIzobirthEntity.class, jIzobirth.getId());
			req.setAttribute("jIzobirthPage", jIzobirth);
		}
		return new ModelAndView("com/sys/jizobirth/jIzobirth");
	}
	
	
	@RequestMapping(params = "redirectToShowJizobirth")
	public ModelAndView redirectToShowJizobirth(HttpServletRequest req,Model model) {
		String id0 = req.getParameter("id");
		String size = req.getParameter("size");  /*8月7号*/
//		String hql0 = "from JIzobirthEntity where 1 = 1 AND doritualid = ? AND size = ?";
		String sql = "select * from release_souls where 1 = 1 AND doritualid = ? AND size = ?";//20180504
		if(id0 != null){
			DoritualinfoEntity doritualinfoEntity = doritualinfoService.getEntity(DoritualinfoEntity.class, id0);
//			List<JIzobirthEntity> jIzobirthEntitys = jIzobirthService.findHql(hql0,id0,size);
			List<JIzobirthEntity> jIzobirthEntitys = jIzobirthService
					.listBySQL(JIzobirthEntity.class,sql, id0,size);
			
			model.addAttribute("doritualinfoEntity", doritualinfoEntity);
			model.addAttribute("jIzobirthEntitys", jIzobirthEntitys);
			model.addAttribute("id", id0);
			model.addAttribute("size", size);
		}
		
		return new ModelAndView("com/sys/jizobirth/showJizobirthRecord");
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
			//获取今年的地藏诞法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "地藏诞");
			if(funeralhelds.size()!=1){
				model.addAttribute("message", "抱歉，今年的地藏诞法事举行日期安排 登记不正常，请与系统管理员联系！");
				return new ModelAndView("com/sys/nofuneralheldplan");
			}
			
//			---kooking 20180402查询是否有相应的法会日期安排，若没有，不允许登记			
			
		model.addAttribute("id0", id0);
		
		return new ModelAndView("com/sys/jizobirth/selectSize");
	}
	/**
	 * 跳转到编辑登记地藏诞
	 * @param req
	 * @param model
	 * @return
	 */
	@RequestMapping(params = "redirectToEditJizobirth")
	
	public ModelAndView redirectToEditJizobirth(HttpServletRequest req,Model model) {
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
			String jizobirthIds = req.getParameter("jizobirthIds");
			
			String hql0 = "from AncestorEntity where 1 = 1 AND ritualid = ? ";
			List<AncestorEntity> ancestorEntityList = systemService.findHql(hql0,id0);
			
			//获取今年的地藏诞法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "地藏诞");
			model.addAttribute("funeralheld", funeralhelds);
			model.addAttribute("doritualinfoAddress", doritualinfoEntity.getAddress());
			model.addAttribute("updateFlag", "0");
			
//			List<JIzobirthEntity> jIzobirthEntitys = new ArrayList<JIzobirthEntity>();
			List<ReleaseSoulsEntity> jIzobirthEntitys = new ArrayList<ReleaseSoulsEntity>();
			if(size.equals("小")){
				if(jizobirthIds == null){
					model.addAttribute("ancestorEntityList", ancestorEntityList);
					model.addAttribute("id", id0);
					model.addAttribute("clientele", doritualinfoEntity.getRname());
					model.addAttribute("jIzobirthEntitys", jIzobirthEntitys);
					return new ModelAndView("com/sys/jizobirth/editJizobirth");
				}
				

				
				//用逗号切开
				String jizobirthIdsSplitByComma[] = jizobirthIds.substring(0, jizobirthIds.length()-1).split(",");
				
				//分别获取相关数据
				for(int i = 0;i < jizobirthIdsSplitByComma.length;i ++){
//					JIzobirthEntity jIzobirthEntity = jIzobirthService.get(JIzobirthEntity.class, jizobirthIdsSplitByComma[i]);
					ReleaseSoulsEntity jIzobirthEntity = jIzobirthService.get(ReleaseSoulsEntity.class, jizobirthIdsSplitByComma[i]);
					jIzobirthEntitys.add(jIzobirthEntity);
				}
				
				model.addAttribute("jIzobirthEntitys", jIzobirthEntitys);
				model.addAttribute("ancestorEntityList", ancestorEntityList);
				model.addAttribute("id", id0);
				model.addAttribute("clientele", doritualinfoEntity.getRname());
				
				return new ModelAndView("com/sys/jizobirth/editJizobirth");
			}
			else{
				if(jizobirthIds == null){
					model.addAttribute("ancestorEntityList", ancestorEntityList);
					model.addAttribute("id", id0);
					model.addAttribute("clientele", doritualinfoEntity.getRname());
					model.addAttribute("jIzobirthEntitys", jIzobirthEntitys);
					model.addAttribute("size", size);
					return new ModelAndView("com/sys/jizobirth/editMiddleAndBigJizobirth");
				}
				else{
					//已经有数据
//					JIzobirthEntity jIzobirthEntity = jIzobirthService.get(JIzobirthEntity.class, jizobirthIds.substring(0, jizobirthIds.length() - 1));
					ReleaseSoulsEntity jIzobirthEntity = jIzobirthService.get(ReleaseSoulsEntity.class, jizobirthIds.substring(0, jizobirthIds.length() - 1));
					
//					String ancestorString = jIzobirthEntity.getAncestor();
					String []ancestorStringByPound  = jIzobirthEntity.getAncestor().substring(0, jIzobirthEntity.getAncestor().length()).split("#");
					List<String> ancestorList = new ArrayList<String>();
					
					String surname = "",tmptype = "1";
					int index = 0;
					if(jIzobirthEntity.getType() == 5){
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
					jIzobirthEntitys.add(jIzobirthEntity);
					model.addAttribute("jIzobirthEntity", jIzobirthEntity);
					model.addAttribute("jIzobirthEntitys", jIzobirthEntitys);
					model.addAttribute("surname", surname);
					model.addAttribute("type", tmptype);
					model.addAttribute("ancestorList", ancestorList);
					model.addAttribute("id", id0);
					model.addAttribute("size", size);
					model.addAttribute("clientele", doritualinfoEntity.getRname());
					return new ModelAndView("com/sys/jizobirth/editMiddleAndBigJizobirth");
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
	/*@RequestMapping(params = "updateJizobirthAndReceipt")
	public ModelAndView updateJizobirthAndReceipt(HttpServletRequest req,Model model) {*/
	private ModelAndView updateJizobirthAndReceipt(String ids,String[] antoserial,Model model) {
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
			//获取今年的地藏诞法事举行日期安排
			List<FuneralheldEntity> funeralhelds = ControllerUtils
					.getThisYearFuneralheldsByRitualtype(systemService, "地藏诞");
			FuneralheldEntity funeralheldEntity = funeralhelds.get(0);
			String sumSummary ="", res = "";
			
			JIzobirthEntity entity = jIzobirthService.get(JIzobirthEntity.class, id[0]);
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
				JIzobirthEntity te = jIzobirthService.get(JIzobirthEntity.class, id[i]);
				te.setAutoserial(antoserial[i]);
				te.setRegistertime(dateString);
				te.setRegistrant(user.getRealName());
				te.setReceiptno(re.getNo());
				jIzobirthService.updateEntitie(te);
				
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
			re.setObj(obj);
			re.setSize(entity.getSize());
			re.setDate("二#十九#二#廿七");
			//保存收据
	
			
			
			re.setRitualtype("地藏诞");
			re.setRegistertime(dateString);
			re.setPurpose("地藏诞");
			re.setFlag(0);
			receiptService.save(re);
			String receipId = re.getId();
	
			for(int i = 0;i < id.length;i ++){
				JIzobirthEntity te = jIzobirthService.get(JIzobirthEntity.class, id[i]);
				te.setReceiptid(receipId);
				jIzobirthService.updateEntitie(te);
			}
			
			
			model.addAttribute("message", "地藏诞登记成功");
			model.addAttribute("ritualtype", "地藏诞");
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
				String jIzobirthid = req.getParameter("jIzobirthid");
				if(jIzobirthid != null && !jIzobirthid.equals("")){
					JIzobirthEntity jIzobirthEntity = jIzobirthService.get(JIzobirthEntity.class, jIzobirthid);
					if(jIzobirthEntity.getSize().equals("小")){
						jIzobirthEntity.setAddress(req.getParameter("address"));
						jIzobirthEntity.setAncestor(req.getParameter("ancestor"));
						jIzobirthEntity.setMoney(Integer.valueOf(req.getParameter("money")));
						jIzobirthEntity.setPayway(req.getParameter("payway"));
						jIzobirthEntity.setSummary(req.getParameter("summary"));
						jIzobirthEntity.setPrayingobj(req.getParameter("prayingobj"));
						jIzobirthEntity.setPaymen(req.getParameter("paymen"));
						jIzobirthEntity.setType(Integer.valueOf(req.getParameter("type")));
						jIzobirthService.updateEntitie(jIzobirthEntity);
						model.addAttribute("message", "地藏诞牌位修改成功！！");
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
						jIzobirthEntity.setBook(req.getParameter("book"));
						jIzobirthEntity.setAddress(req.getParameter("address"));
						jIzobirthEntity.setAncestor(ancestorString);
						jIzobirthEntity.setMoney(Integer.valueOf(req.getParameter("money")));
						jIzobirthEntity.setPayway(req.getParameter("payway"));
						jIzobirthEntity.setSummary(req.getParameter("summary"));
						jIzobirthEntity.setPrayingobj(req.getParameter("prayingobj"));
						jIzobirthEntity.setPaymen(req.getParameter("paymen"));
						if(surname == ""){
							jIzobirthEntity.setType(6);
						}
						else{
							jIzobirthEntity.setType(5);
						}
						jIzobirthService.updateEntitie(jIzobirthEntity);
						model.addAttribute("message", "地藏诞牌位修改成功！！");
						return new ModelAndView("com/sys/updateSuccess");
					}
				}
		try{
			//获取做法事人的ID
			String sizeflag = req.getParameter("sizeflag");
			if(sizeflag == null || sizeflag.equals("小")){
				String id0 = req.getParameter("id");
				
				//获取祈福者
				String[] prayingobj = req.getParameterValues("prayingobj");
				String paymen = req.getParameter("paymen");
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
//				List<JIzobirthEntity> jIzobirthEntityList = new ArrayList<JIzobirthEntity>();
				String ids = "";
				String[] antoserial=new String[size.length];
				
				Date currentTime = new Date();
				SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
				String dateString = formatter.format(currentTime);
				
				for(int i = 0;i < size.length;i ++){
					JIzobirthEntity jIzobirthEntity = new JIzobirthEntity();
					CriteriaQuery cq = new CriteriaQuery(JIzobirthEntity.class);
					cq.add(Restrictions.and(Restrictions.eq("size",size[i]),Restrictions.like("registertime",currentyear,MatchMode.START)));
					List<JIzobirthEntity> jIzobirths = jIzobirthService.getListByCriteriaQuery(cq,false);
					
					jIzobirthEntity.setAddress(address[i]);
					jIzobirthEntity.setAncestor(ancestor[i]);
					jIzobirthEntity.setMoney(Integer.valueOf(money[i]));
					jIzobirthEntity.setPayway(payway[i]);
					jIzobirthEntity.setSummary(summary[i]);
					jIzobirthEntity.setPrayingobj(prayingobj[i]);
					jIzobirthEntity.setDoritualid(id0);
					jIzobirthEntity.setSize(size[i]);
					jIzobirthEntity.setPaymen(paymen);
					//换时间
					jIzobirthEntity.setType(Integer.valueOf(type[i]));
					jIzobirthEntity.setFlag(0);
					NumberFormat f=new DecimalFormat("000000");
					jIzobirthEntity.setSerial(f.format(jIzobirths.size() + 1));
					jIzobirthEntity.setRegistertime(dateString);
//					jIzobirthEntityList.add(jIzobirthEntity);
					
					jIzobirthService.save(jIzobirthEntity);
					
					ControllerUtils.save2ReleaseSouls(jIzobirthEntity, jIzobirthService);
					
					ids += jIzobirthEntity.getId() + ",";
					antoserial[i]=jIzobirthEntity.getSerial();
				}
//				req.setAttribute("jIzobirthEntityList", jIzobirthEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView("com/sys/jizobirth/AutoSerialJizobirth");
				
//------------20180502 Kooking		
				return updateJizobirthAndReceipt(ids, antoserial, model);
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
				String paymen = req.getParameter("paymen");
				//获取摘要
				String summary = req.getParameter("summary");
				
				//获取摘要
				String payway = req.getParameter("payway");
				
				//获取摘要
				String money = req.getParameter("money");
				
				String surname = req.getParameter("surname");
				String book = req.getParameter("book");
	
				String type = req.getParameter("type");
				Calendar cal = Calendar.getInstance();
				int year = cal.get(Calendar.YEAR);
				String currentyear = String.valueOf(year);
				
				JIzobirthEntity jIzobirthEntity = new JIzobirthEntity();
				
				//获取序号
				CriteriaQuery cq = new CriteriaQuery(JIzobirthEntity.class);
				cq.add(Restrictions.and(Restrictions.eq("size",sizeflag),Restrictions.like("registertime",currentyear,MatchMode.START)));
				List<JIzobirthEntity> amitabhabirths = jIzobirthService.getListByCriteriaQuery(cq,false);
				
//				List<JIzobirthEntity> jIzobirthEntityList = new ArrayList<JIzobirthEntity>();
				
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
				
				
				jIzobirthEntity.setBook(book);
				jIzobirthEntity.setAddress(address);
				jIzobirthEntity.setAncestor(ancestorString);
				jIzobirthEntity.setMoney(Integer.valueOf(money));
				jIzobirthEntity.setPayway(payway);
				jIzobirthEntity.setSummary(summary);
				jIzobirthEntity.setPrayingobj(prayingobj);
				jIzobirthEntity.setPaymen(paymen);
				jIzobirthEntity.setDoritualid(id0);
				jIzobirthEntity.setSize(sizeflag);
				if(surname == ""){
					jIzobirthEntity.setType(6);
				}
				else{
					jIzobirthEntity.setType(5);
				}
				//换时间
				jIzobirthEntity.setFlag(0);
				NumberFormat f=new DecimalFormat("000000");
				jIzobirthEntity.setSerial(f.format(amitabhabirths.size() + 1));
				jIzobirthEntity.setRegistertime(dateString);
				
				jIzobirthService.save(jIzobirthEntity);
				
				ControllerUtils.save2ReleaseSouls(jIzobirthEntity, jIzobirthService);
				
				ids += jIzobirthEntity.getId() + ",";
				antoserial[0]=jIzobirthEntity.getSerial();
				
//				jIzobirthEntityList.add(jIzobirthEntity);
//				req.setAttribute("jIzobirthEntityList", jIzobirthEntityList);
//				req.setAttribute("ids", ids);
//				return new ModelAndView("com/sys/jizobirth/AutoSerialJizobirth");
				
//------------20180502 Kooking		
				return updateJizobirthAndReceipt(ids, antoserial, model);
//------------20180502 Kooking	
				
			}
			
		}catch(Exception ex){
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}
	}
	
	@RequestMapping(params = "myUpdate")
	public ModelAndView myUpdate(JIzobirthEntity jIzobirthEntity,HttpServletRequest request,Model model) {
		List<JIzobirthEntity> jIzobirthEntitys = new ArrayList<JIzobirthEntity>();
		if (StringUtil.isNotEmpty(jIzobirthEntity.getId())) {
			jIzobirthEntity = jIzobirthService.getEntity(JIzobirthEntity.class, jIzobirthEntity.getId());
			
			TSUser user = ResourceUtil.getSessionUserName();
			if(!user.getRealName().equals(jIzobirthEntity.getRegistrant())){
				return new ModelAndView("com/sys/unauthorized");
			}
			
			//添加属性，标记是否为修改页面
			model.addAttribute("updateFlag", "1");
			
			if(jIzobirthEntity.getSize().equals("小")){
				jIzobirthEntitys.add(jIzobirthEntity);
				model.addAttribute("jIzobirthEntitys", jIzobirthEntitys);
				model.addAttribute("ancestorEntityList", jIzobirthEntity.getAncestor());
				model.addAttribute("id", jIzobirthEntity.getDoritualid());
				model.addAttribute("jIzobirthid", jIzobirthEntity.getId());
				model.addAttribute("clientele", jIzobirthEntity.getPaymen());
				model.addAttribute("size", jIzobirthEntity.getSize());
				return new ModelAndView("com/sys/jizobirth/editJizobirth");
			}
			else{
//				String ancestorString = jIzobirthEntity.getAncestor();
				String []ancestorStringByPound  = jIzobirthEntity.getAncestor().substring(0, jIzobirthEntity.getAncestor().length()).split("#");
				List<String> ancestorList = new ArrayList<String>();
				String surname = "",tmptype = "1";
				int index = 0;
				if(jIzobirthEntity.getType() == 5){
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
				jIzobirthEntitys.add(jIzobirthEntity);
				model.addAttribute("jIzobirthEntity", jIzobirthEntity);
				model.addAttribute("jIzobirthEntitys", jIzobirthEntitys);
				model.addAttribute("surname", surname);
				model.addAttribute("type", tmptype);
				model.addAttribute("ancestorList", ancestorList);
				model.addAttribute("id", jIzobirthEntity.getId());
				model.addAttribute("size", jIzobirthEntity.getSize());
				model.addAttribute("jIzobirthid", jIzobirthEntity.getId());
				return new ModelAndView("com/sys/jizobirth/editMiddleAndBigJizobirth");
			}
		}
		else{
			return new ModelAndView("com/sys/error");
		}
	}
}
