package com.sys.controller.doritualinfo;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.tag.vo.datatable.SortDirection;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import test.TabletStat;

import com.sys.entity.ancestor.AncestorEntity;
import com.sys.entity.doritualinfo.DoritualinfoEntity;
import com.sys.entity.livingmenber.LivingmenberEntity;
import com.sys.entity.pravrajanamember.PravrajanamemberEntity;
import com.sys.entity.purpose.PurposeEntity;
import com.sys.page.doritualinfo.DoritualinfoPage;
import com.sys.service.doritualinfo.DoritualinfoServiceI;
import com.sys.service.pravrajanamember.PravrajanamemberServiceI;
import com.sys.service.purpose.PurposeServiceI;
/**   
 * @Title: Controller
 * @Description: 做法事人的基本信息
 * @author zhangdaihao
 * @date 2015-11-25 15:49:02
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/doritualinfoController")
public class DoritualinfoController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DoritualinfoController.class);

	@Autowired
	private DoritualinfoServiceI doritualinfoService;
	@Autowired
	private PravrajanamemberServiceI pravrajanamemberService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private PurposeServiceI purposeService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 做法事人的基本信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "doritualinfo")
	public ModelAndView doritualinfo(HttpServletRequest request) {
		return new ModelAndView("com/sys/doritualinfo/doritualinfoList");
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
	public void datagrid(DoritualinfoEntity doritualinfo,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DoritualinfoEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, doritualinfo);
		cq.addOrder("registertime", SortDirection.desc);
		this.doritualinfoService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除做法事人的基本信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DoritualinfoEntity doritualinfo, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		doritualinfo = systemService.getEntity(DoritualinfoEntity.class, doritualinfo.getId());
		message = "删除成功";
		doritualinfoService.delete(doritualinfo);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加做法事人的基本信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DoritualinfoEntity doritualinfo,DoritualinfoPage doritualinfoPage, HttpServletRequest request) {
		List<LivingmenberEntity> livingmenberList =  doritualinfoPage.getLivingmenberList();
		List<AncestorEntity> ancestorList =  doritualinfoPage.getAncestorList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(doritualinfo.getId())) {
			message = "更新成功";
			
			Date nowTime=new Date(); 
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			
			String registertime = time.format(nowTime);
			
			doritualinfo.setRegistertime(registertime);
			doritualinfoService.updateMain(doritualinfo, livingmenberList,ancestorList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			
			Date nowTime=new Date(); 
			SimpleDateFormat time=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
			String registertime = time.format(nowTime);
			
			doritualinfo.setRegistertime(registertime);
			doritualinfoService.addMain(doritualinfo, livingmenberList,ancestorList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	
	
	/**
	* @Title: savePurpose
	* @Description: 添加捐款用途
	* @param purpose
	* @return   
	* @return AjaxJson    返回类型
	* @throws
	*/ 
	@RequestMapping(params = "savePurpose")
	@ResponseBody
	public AjaxJson savePurpose(PurposeEntity purpose) {
		AjaxJson j = new AjaxJson();
		message = "添加成功";
		purposeService.save(purpose);
		systemService.addLog(message, Globals.Log_Type_INSERT,
				Globals.Log_Leavel_INFO);
		j.setMsg(message);
		return j;
	}
	
	
	/**
	 * 做法事人的基本信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DoritualinfoEntity doritualinfo, HttpServletRequest req) {
		String search = req.getParameter("search");
		if (StringUtil.isNotEmpty(doritualinfo.getId())) {
			doritualinfo = doritualinfoService.getEntity(DoritualinfoEntity.class, doritualinfo.getId());
			req.setAttribute("doritualinfoPage", doritualinfo);
		}
		req.setAttribute("searchflag", search);
		return new ModelAndView("com/sys/doritualinfo/doritualinfo");
	}
	
	@RequestMapping(params = "addorupdateById")
	public ModelAndView addorupdateById(String id, HttpServletRequest req) {
		if(id != null && !id.equals("")){
			
			DoritualinfoEntity doritualinfo = doritualinfoService.getEntity(DoritualinfoEntity.class,id);
			req.setAttribute("doritualinfoPage", doritualinfo);
		}
		String search = req.getParameter("search");
		req.setAttribute("searchflag", search);
		
		return new ModelAndView("com/sys/doritualinfo/doritualinfo");
	}
	
	
	/**
	 * 加载明细列表[在世的人]
	 * 
	 * @return
	 */
	@RequestMapping(params = "livingmenberList")
	public ModelAndView livingmenberList(DoritualinfoEntity doritualinfo, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = doritualinfo.getId();
		//===================================================================================
		//查询-在世的人
	    String hql0 = "from LivingmenberEntity where 1 = 1 AND ritualid = ? ";
		try{
		    List<LivingmenberEntity> livingmenberEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("livingmenberList", livingmenberEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/sys/doritualinfo/livingmenberList");
	}
	/**
	 * 加载明细列表[先人表]
	 * 
	 * @return
	 */
	@RequestMapping(params = "ancestorList")
	public ModelAndView ancestorList(DoritualinfoEntity doritualinfo, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id1 = doritualinfo.getId();
		//===================================================================================
		//查询-先人表
	    String hql1 = "from AncestorEntity where 1 = 1 AND ritualid = ? ";
		try{
		    List<AncestorEntity> ancestorEntityList = systemService.findHql(hql1,id1);
			req.setAttribute("ancestorList", ancestorEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/sys/doritualinfo/ancestorList");
	}
	
	/**
	 * 跳转到打印
	 * @param req
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping(params = "print")
	public ModelAndView print(HttpServletRequest req) throws UnsupportedEncodingException {
		String inputer = req.getParameter("name");
		String flag = req.getParameter("flag");
		String type = req.getParameter("type");
		String name   = new String(inputer.getBytes("ISO-8859-1") , "UTF-8");
		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
		req.setAttribute("pravrajanamemberEntityList", pravrajanamemberEntityList);
		req.setAttribute("name", name);
		req.setAttribute("type", type);
		if(flag.equals("1")){
			return new ModelAndView("com/sys/print");
		}
		else{
			return new ModelAndView("com/sys/printRelease");
		}
		
	}
	
	/**
	 * 
	 */
	@RequestMapping(params = "fowardRitualtype")
	public ModelAndView fowardRitualtype(HttpServletRequest req) {
		return new ModelAndView("com/sys/tabletstat/ritualindex");
	}
	
	@RequestMapping(params = "fowardSelectRitualtype")
	public ModelAndView fowardSelectRitualtype(HttpServletRequest req) {
		return new ModelAndView("com/sys/tabletstat/selectRitualtype");
	}
	
	@RequestMapping(params = "tabletcount")
	public ModelAndView tabletcount(HttpServletRequest req) throws UnsupportedEncodingException {
		String type = req.getParameter("type");
		String name = req.getParameter("name");
		String flag = req.getParameter("flag");
		String inputer   = new String(name.getBytes("ISO-8859-1") , "UTF-8"); 
		List<TabletStat> tablet = new ArrayList<TabletStat>();
		Calendar ca = Calendar.getInstance();
		String year = String.valueOf(ca.get(Calendar.YEAR));
		if(type != null){
			String sqlSmallNotPrint = "select count(*) from "+type + " where flag=0 and size='小' and registertime like '" + year + "%'";
			String sqlSmallAlreadyPrint = "select count(*) from "+type + " where flag=1 and size='小' and registertime like '" + year + "%'";
			
			String sqlMiddleNotPrint = "select count(*) from "+type + " where flag=0 and size='大' and registertime like '" + year + "%'";
			String sqlMiddleAlreadyPrint = "select count(*) from "+type + " where flag=1 and size='大' and registertime like '" + year + "%'";
			
			String sqlBigNotPrint = "select count(*) from "+type + " where flag=0 and size='拈香' and registertime like '" + year + "%'";
			String sqlBigAlreadyPrint = "select count(*) from "+type + " where flag=1 and size='拈香' and registertime like '" + year + "%'";
			
			Long countSmallNotPrint = systemService.getCountForJdbc(sqlSmallNotPrint);
			Long countSmallAlreadyPrint = systemService.getCountForJdbc(sqlSmallAlreadyPrint);
			
			Long countMiddleNotPrint = systemService.getCountForJdbc(sqlMiddleNotPrint);
			Long countMiddleAlreadyPrint = systemService.getCountForJdbc(sqlMiddleAlreadyPrint);
			
			Long countBigNotPrint = systemService.getCountForJdbc(sqlBigNotPrint);
			Long countBigAlreadyPrint = systemService.getCountForJdbc(sqlBigAlreadyPrint);
			
			TabletStat ts = new TabletStat();
			ts.setRitualtype(inputer);
			ts.setSize("小");
			ts.setNotprint(countSmallNotPrint);
			ts.setAlreadyprint(countSmallAlreadyPrint);
			tablet.add(ts);
			
			TabletStat tsMiddle = new TabletStat();
			tsMiddle.setRitualtype(inputer);
			tsMiddle.setSize("大");
			tsMiddle.setNotprint(countMiddleNotPrint);
			tsMiddle.setAlreadyprint(countMiddleAlreadyPrint);
			tablet.add(tsMiddle);
			
			TabletStat tsBig = new TabletStat();
			tsBig.setRitualtype(inputer);
			tsBig.setSize("拈香");
			tsBig.setNotprint(countBigNotPrint);
			tsBig.setAlreadyprint(countBigAlreadyPrint);
			tablet.add(tsBig);
			
		}
		req.setAttribute("name", inputer);
		req.setAttribute("tablet", tablet);
		req.setAttribute("flag", flag);
		req.setAttribute("type", type);
		return new ModelAndView("com/sys/tabletstat/tabletcount");
	}
}
