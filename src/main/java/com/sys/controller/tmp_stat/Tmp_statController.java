package com.sys.controller.tmp_stat;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.entity.pravrajanamember.PravrajanamemberEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.staff.StaffEntity;
import com.sys.entity.tmp_stat.Tmp_statEntity;
import com.sys.service.pravrajanamember.PravrajanamemberServiceI;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.staff.StaffServiceI;
import com.sys.service.tmp_stat.Tmp_statServiceI;

/**   
 * @Title: Controller
 * @Description: 统计收据
 * @author zhangdaihao
 * @date 2016-03-14 08:48:30
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tmp_statController")
public class Tmp_statController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Tmp_statController.class);

	@Autowired
	private Tmp_statServiceI tmp_statService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ReceiptServiceI receiptService;
	@Autowired
	private StaffServiceI staffService;
	@Autowired
	private PravrajanamemberServiceI pravrajanamemberService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 统计收据列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tmp_stat")
	public ModelAndView tmp_stat(HttpServletRequest request) {
		return new ModelAndView("com/sys/tmp_stat/tmp_statList");
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
	public void datagrid(Tmp_statEntity tmp_stat,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Tmp_statEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tmp_stat, request.getParameterMap());
		this.tmp_statService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除统计收据
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Tmp_statEntity tmp_stat, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tmp_stat = systemService.getEntity(Tmp_statEntity.class, tmp_stat.getId());
		message = "统计收据删除成功";
		tmp_statService.delete(tmp_stat);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加统计收据
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(Tmp_statEntity tmp_stat, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tmp_stat.getId())) {
			message = "统计收据更新成功";
			Tmp_statEntity t = tmp_statService.get(Tmp_statEntity.class, tmp_stat.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tmp_stat, t);
				tmp_statService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "统计收据更新失败";
			}
		} else {
			message = "统计收据添加成功";
			tmp_statService.save(tmp_stat);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 统计收据列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(Tmp_statEntity tmp_stat, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tmp_stat.getId())) {
			tmp_stat = tmp_statService.getEntity(Tmp_statEntity.class, tmp_stat.getId());
			req.setAttribute("tmp_statPage", tmp_stat);
		}
		return new ModelAndView("com/sys/tmp_stat/tmp_stat");
	}
	

	/**
	 * 
	 */
	@RequestMapping(params = "fowardIndex")
	public ModelAndView fowardRitualtype(HttpServletRequest req) {
		return new ModelAndView("com/sys/tmpstat/statindex");
	}
	
	/**
	 * 收入管理IndexForward
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "fowardIndexIncome")
	public ModelAndView fowardRitualtypeIncome(HttpServletRequest req) {
		return new ModelAndView("com/sys/tmpstat/incomeIndex");
	}
	
	
	/**
	 * 
	 */
	@RequestMapping(params = "fowardSelect")
	public ModelAndView fowardSelect(HttpServletRequest req) {
		CriteriaQuery cq2 = new CriteriaQuery(TSDepart.class);
		List<TSDepart> tsDeparts = systemService.getListByCriteriaQuery(cq2, false);
		req.setAttribute("tsDeparts", tsDeparts);
		
		return new ModelAndView("com/sys/tmpstat/selectCondition");
	}
	
	
	@RequestMapping(params = "fowardSelectIncome")
	public ModelAndView fowardSelectIncome(HttpServletRequest req) {
		CriteriaQuery cq2 = new CriteriaQuery(TSDepart.class);
		List<TSDepart> tsDeparts = systemService.getListByCriteriaQuery(cq2, false);
		req.setAttribute("tsDeparts", tsDeparts);
		
		return new ModelAndView("com/sys/tmpstat/IncomeCondition");
	}
	

	
	
	/**
	 * 找员工
	 * @param req
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params = "findMember")
	@ResponseBody
	public void findMember(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		AjaxJson j = new AjaxJson();
		String id = StringUtil.getEncodePra(req.getParameter("id"));
		String names = "";
		List<StaffEntity> staffs = staffService.findByProperty(StaffEntity.class, "departid", id);
		List<PravrajanamemberEntity> pravrajanamemberEntitys = pravrajanamemberService.findByProperty(PravrajanamemberEntity.class, "departid", id);
		if (staffs.size() > 0) {
			for (StaffEntity staff : staffs) {
				names += staff.getStaffname() +";";
			}
		} 
		else if(pravrajanamemberEntitys.size()>0){ //2017年12月13号修改
			for (PravrajanamemberEntity pravrajanamemberEntity : pravrajanamemberEntitys) {
				names += pravrajanamemberEntity.getPravrajananame() +";";
			}
		}
		else {
		}
			names += "没有员工!";

		resp.getWriter().write(names);
	}
	
	/**
	 * 找员工
	 * @param req
	 * @return
	 * @throws IOException 
	 */
	@RequestMapping(params = "statResult")
	@ResponseBody
	public void statresult(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		AjaxJson j = new AjaxJson();
		
		List<Tmp_statEntity> clearList = tmp_statService.getList(Tmp_statEntity.class);
		tmp_statService.deleteAllEntitie(clearList);
		
		String id = StringUtil.getEncodePra(req.getParameter("id"));
		String memberName = StringUtil.getEncodePra(req.getParameter("memberName"));
		String start = StringUtil.getEncodePra(req.getParameter("start"));
		String end = StringUtil.getEncodePra(req.getParameter("end"));
		
		
		
		if(start == null || start.trim() == "" || end == null || end.trim()=="" ){
			 Date currentTime = new Date();
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
			 String dateString = formatter.format(currentTime);
			 start = dateString;
			 end = dateString;
		}else{
				// 格式化时间，写死代码，懒了
				start = start.replaceFirst("-", "年");
				start = start.replace("-", "月");
				start = start + "日";
				
				end = end.replaceFirst("-", "年");
				end = end.replace("-", "月");
				end = end + "日";
		}
		
		
		/*String names = "";
		List<StaffEntity> staffs = staffService.findByProperty(StaffEntity.class, "departid", id);
		if (staffs.size() > 0) {
			for (StaffEntity staff : staffs) {
				names += staff.getStaffname() +";";
			}
		} else {
			names += "没有员工!";
		}*/
		//String[] purposes = {"金刚经","长明灯","供斋","开光","药师诞","弥勒佛诞","观音开库","观音诞","清明节","地藏诞","弥陀诞","释尊诞","盂兰节","观音出家","观音成道","释尊出家","善款","合计"};
		String[] purposes = {"数量","合计"};
		String[] payways = {"现金","刷卡","支付宝","微信","其他"};
		
		
		List<Tmp_statEntity> stats = new ArrayList<Tmp_statEntity>();
		List<Object> objs = null;
		
			for(int k = 0;k < purposes.length;k++){
				Tmp_statEntity stat = new Tmp_statEntity();
				for(int i = 0; i < payways.length;i++){
				String sql = null;
				// 合计另算
				if(purposes[k].equals("合计")){
					sql = "select SUM(money) from receipt where registrant=\""+memberName+ "\" and registertime between \"" + start + "\" and \"" + end +"\" and payway=\"" + payways[i] + "\" and transit=0 and cancel=0";
					
				}
				else if(purposes[k].equals("数量")){
					sql = "select COUNT(*) from receipt where registrant=\"" + memberName + "\" and registertime between \"" + start + "\" and \"" + end + "\" and payway=\"" + payways[i] + "\" and transit=0 and cancel=0";
				}
				
				stat.setRitualtype(purposes[k]);
				
				objs = systemService.findListbySql(sql);
				if(objs != null)
					System.out.println(objs.get(0));
				if(objs != null && objs.get(0) != null){
					String sumMoney = objs.get(0).toString();
					if(payways[i].equals("现金")){
						stat.setCash(Integer.valueOf(sumMoney));
					}
					if(payways[i].equals("刷卡")){
						stat.setCard(Integer.valueOf(sumMoney));
					}
					if(payways[i].equals("支付宝")){
						stat.setAlipay(Integer.valueOf(sumMoney));
					}
					if(payways[i].equals("微信")){
						stat.setWeixin(Integer.valueOf(sumMoney));
					}
					if(payways[i].equals("其他")){
						stat.setOther(Integer.valueOf(sumMoney));
					}
				}	
				else{
					if(payways[i].equals("现金")){
						stat.setCash(0);
					}
					if(payways[i].equals("刷卡")){
						stat.setCard(0);
					}
					if(payways[i].equals("支付宝")){
						stat.setAlipay(0);
					}
					if(payways[i].equals("微信")){
						stat.setWeixin(0);
					}
					if(payways[i].equals("其他")){
						stat.setOther(0);
					}
				}
				
				
			}
			
			stats.add(stat);
		}
		
		for(Tmp_statEntity tmp : stats){
			if(tmp!=null){
				if(tmp.getRitualtype().equals("善款")){
					System.out.println("善款："+tmp);
				}
				tmp.setTotal(tmp.getCard() + tmp.getCash() + tmp.getAlipay() + tmp.getWeixin() + tmp.getOther());
				tmp_statService.save(tmp);
			}
		}
		
		String res = getJSONArrayOfTmp_statEntity(stats).toString();
		
		resp.getWriter().write(res);
	}
	
	private static JSONArray getJSONArrayOfTmp_statEntity(List<Tmp_statEntity> stats){
		JSONArray json = new JSONArray();
		for(Tmp_statEntity tmpstat : stats){
            JSONObject jo = new JSONObject();
            jo.put("ritualtype", tmpstat.getRitualtype());
            jo.put("cash", tmpstat.getCash());
            jo.put("card", tmpstat.getCard());
            jo.put("alipay", tmpstat.getAlipay());
            jo.put("weixin", tmpstat.getWeixin());
            jo.put("other", tmpstat.getOther());
            jo.put("total", tmpstat.getTotal());
            json.add(jo);
        }
		return json;
		
	}
	
	/**
	 * 确认交接
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "comfirmStat")
	public ModelAndView comfirmStat(HttpServletRequest req) {
		String memberName = StringUtil.getEncodePra(req.getParameter("memberName"));
		String start = StringUtil.getEncodePra(req.getParameter("start"));
		String end = StringUtil.getEncodePra(req.getParameter("end"));
		
		if(start == "" || end == ""){
			 Date currentTime = new Date();
			 SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
			 String dateString = formatter.format(currentTime);
			 
			 start = dateString;
			 end = dateString;
		}else{//如果二者都不为空,转换日期格式化
			start = start.replaceFirst("-", "年");
			start = start.replace("-", "月");
			start = start + "日";
			
			end = end.replaceFirst("-", "年");
			end = end.replace("-", "月");
			end = end + "日";
		}
		
		
		CriteriaQuery cq = new CriteriaQuery(ReceiptEntity.class);
		cq.add(Restrictions.eq("registrant", memberName));
		cq.add(Restrictions.ge("registertime", start));
		cq.add(Restrictions.le("registertime", end));
		List<ReceiptEntity> receipts = receiptService.getListByCriteriaQuery(cq, false);
		
		for(ReceiptEntity re : receipts){
			re.setTransit(1);
			receiptService.updateEntitie(re);
		}
		return new ModelAndView("com/sys/tmpstat/success");
	}
	
}
