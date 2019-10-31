package com.sys.controller.event;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
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

import com.sys.entity.event.EventEntity;
import com.sys.entity.volunteer.VolunteerEntity;
import com.sys.entity.volunteerevent.VolunteereventEntity;
import com.sys.page.event.EventPage;
import com.sys.service.event.EventServiceI;
import com.sys.service.volunteer.VolunteerServiceI;
/**   
 * @Title: Controller
 * @Description: 事件信息登记
 * @author zhangdaihao
 * @date 2015-10-30 15:09:58
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/eventController")
public class EventController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(EventController.class);

	@Autowired
	private EventServiceI eventService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private VolunteerServiceI volunteerService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 事件信息登记列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "event")
	public ModelAndView event(HttpServletRequest request) {
		return new ModelAndView("com/sys/event/eventList");
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
	public void datagrid(EventEntity event,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(EventEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, event);
		this.eventService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除事件信息登记
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(EventEntity event, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		event = systemService.getEntity(EventEntity.class, event.getId());
		message = "删除成功";
		eventService.delete(event);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加事件信息登记
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(EventEntity event,EventPage eventPage, HttpServletRequest request) {
		List<VolunteereventEntity> volunteereventList =  eventPage.getVolunteereventList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(event.getId())) {
			message = "更新成功";
			TSUser user = ResourceUtil.getSessionUserName();
			event.setCreator(user.getRealName());
			eventService.updateMain(event, volunteereventList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			TSUser user = ResourceUtil.getSessionUserName();
			event.setCreator(user.getRealName());
			eventService.addMain(event, volunteereventList);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 事件信息登记列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(EventEntity event, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(event.getId())) {
			event = eventService.getEntity(EventEntity.class, event.getId());
			req.setAttribute("eventPage", event);
		}
		return new ModelAndView("com/sys/event/event");
	}
	
	
	/**
	 * 加载明细列表[义工事务登记]
	 * 
	 * @return
	 */
	@RequestMapping(params = "volunteereventList")
	public ModelAndView volunteereventList(EventEntity event, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = event.getId();
		//===================================================================================
		//查询-义工事务登记
	    String hql0 = "from VolunteereventEntity where 1 = 1 AND eventid = ? ";
		try{
		    List<VolunteereventEntity> volunteereventEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("volunteereventList", volunteereventEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		/*EventEntity eventEntity = eventService.getEntity(EventEntity.class, event.getId());
		String isconfirm = eventEntity.getIsconfirm();
		req.setAttribute("isconfirm", isconfirm);*/
		return new ModelAndView("com/sys/event/volunteereventList");
	}
	
	@RequestMapping(params = "getEventList")
	public ModelAndView getEventList(HttpServletRequest req) {
	
		List<EventEntity> eventList = eventService.getList(EventEntity.class);
		
		req.setAttribute("eventList", eventList);
		return new ModelAndView("com/sys/event/eventResult");
	}
	/**
	 * 对事件进行确认
	 * @param event
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "confirm")
	public ModelAndView confirm(EventEntity event,Model model,HttpServletRequest req) {
		
		EventEntity temp = eventService.getEntity(EventEntity.class, event.getId());
		temp.setIsconfirm("是");
		eventService.saveOrUpdate(temp);
		model.addAttribute("message", "确认成功,确认后不能添加义工");
		return new ModelAndView("com/sys/confirmPage");
	}
	
	/**
	 * 对事件进行评价，跳转页面
	 * @param event
	 * @param model
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "estimate")
	public ModelAndView estimate(EventEntity event,Model model,HttpServletRequest req) {
		
		EventEntity temp = eventService.getEntity(EventEntity.class, event.getId());
		if(temp.getIsestimate().equals("是")){
			model.addAttribute("message", temp.getEventname() + "事件已经评价过,请勿重复评价");
			return new ModelAndView("com/sys/confirmPage");
		}
		//如果还没有评价，把对应的义工取出来
		//===================================================================================
		//获取参数
		Object id0 = event.getId();
		//===================================================================================
		String hql0 = "from VolunteereventEntity where 1 = 1 AND eventid = ? ";
		try{
		    List<VolunteereventEntity> volunteereventEntityList = systemService.findHql(hql0,id0);
		    String eventname = volunteereventEntityList.get(0).getEventname();
		    String eventid = event.getId();
			model.addAttribute("volunteereventList", volunteereventEntityList);
			model.addAttribute("eventname", eventname);
			model.addAttribute("eventid", eventid);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/sys/event/estimateVolunteer");
	}
	
	@RequestMapping(params = "estimatesubmit")
	public ModelAndView estimatesubmit(String eventid,String str,String volunteerids,Model model,HttpServletRequest req) {
		String strtemp = str.substring(0, str.length() - 1);
		String [] volunteerevents = strtemp.split(",");
		for(int i = 0;i < volunteerevents.length;i ++){
			//解析字符串
			//===============================================================
			String[] volunteerevent = volunteerevents[i].split("\\|");
			//===============================================================
			//===============================================================
			//把评价加入到每个员工里面
			VolunteereventEntity volunteereventEntity = systemService.get(VolunteereventEntity.class, volunteerevent[0]);
			volunteereventEntity.setAtitude(Float.parseFloat(volunteerevent[1]));
			volunteereventEntity.setWorkcondition(Float.parseFloat(volunteerevent[2]));
			//===============================================================
			//设置完成后，对实体进行保存
			systemService.saveOrUpdate(volunteereventEntity);
		}
		//因为评价后，义工的个人信息的平均分和次数应该发生改变,更新义工的数据库
		//对volunteerids进行解析
		String []ids = volunteerids.substring(0, volunteerids.length() - 1).split(",");
		for(int i = 0;i < ids.length;i ++){
			//把对应id的员工取出来，进行平均分和次数的 
			List<VolunteereventEntity> volunteereventEntityList = systemService.findByProperty(VolunteereventEntity.class, "volunteerid", ids[i]);
			VolunteerEntity volunteerEntity = volunteerService.getEntity(VolunteerEntity.class, ids[i]);
			//
			//计算平均分和次数
			//总分sum变量
			float sum = 0;
			int count = volunteereventEntityList.size();
			for(int j = 0;j < count;j ++){
				float atitude = volunteereventEntityList.get(j).getAtitude();
				float workcondition = volunteereventEntityList.get(j).getWorkcondition();
				sum += (atitude + workcondition);
			}
				volunteerEntity.setAverage(sum/count);
			volunteerEntity.setJoincount(count);
			volunteerService.saveOrUpdate(volunteerEntity);
			//设置完成后，对实体进行保存
		}
		EventEntity eventEntity = eventService.get(EventEntity.class, eventid);
		eventEntity.setIsestimate("是");
		eventService.saveOrUpdate(eventEntity);
		model.addAttribute("message", "评价成功");
		return new ModelAndView("com/sys/confirmPage");
	}
	
	
}
