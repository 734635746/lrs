package com.sys.controller.registrationroom;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.entity.registrationroom.RegistrationroomEntity;
import com.sys.entity.room.RoomEntity;
import com.sys.service.registrationroom.RegistrationroomServiceI;
import com.sys.service.room.RoomServiceI;

/**   
 * @Title: Controller
 * @Description: 登记房间
 * @author zhangdaihao
 * @date 2015-12-29 19:20:53
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/registrationroomController")
public class RegistrationroomController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RegistrationroomController.class);

	@Autowired
	private RegistrationroomServiceI registrationroomService;
	@Autowired
	private RoomServiceI roomService;
	@Autowired
	private SystemService systemService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 登记房间列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "registrationroom")
	public ModelAndView registrationroom(HttpServletRequest request) {
		return new ModelAndView("com/sys/registrationroom/registrationroomList");
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
	public void datagrid(RegistrationroomEntity registrationroom,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RegistrationroomEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, registrationroom, request.getParameterMap());
		this.registrationroomService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除登记房间
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(RegistrationroomEntity registrationroom, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		registrationroom = systemService.getEntity(RegistrationroomEntity.class, registrationroom.getId());
		message = "登记房间删除成功";
		registrationroomService.delete(registrationroom);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加登记房间
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(RegistrationroomEntity registrationroom, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(registrationroom.getId())) {
			message = "登记房间更新成功";
			if(registrationroom.getLeavestate().equals("已离开")){
				RoomEntity room = roomService.get(RoomEntity.class, registrationroom.getRoomid());
				room.setRestbeds(room.getRestbeds() + registrationroom.getNeedbeds());
				roomService.updateEntitie(room);
			}
			RegistrationroomEntity t = registrationroomService.get(RegistrationroomEntity.class, registrationroom.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(registrationroom, t);
				registrationroomService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "登记房间更新失败";
			}
		} else {
			message = "登记房间添加成功";
			registrationroomService.save(registrationroom);
			/*RoomEntity room = roomService.get(RoomEntity.class, registrationroom.getRoomid());
			sint rest = Integer.parseInt(request.getParameter("rest"));
			room.setRestbeds(room.getRestbeds() - rest);
			registrationroom.setIntakentype(room.getRoomKind());*/
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 登记房间列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(RegistrationroomEntity registrationroom, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(registrationroom.getId())) {
			registrationroom = registrationroomService.getEntity(RegistrationroomEntity.class, registrationroom.getId());
			req.setAttribute("registrationroomPage", registrationroom);
		}
		if(req.getParameter("bookflag").equals("yes")){
			java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			try {
				registrationroom.setIntakentime(formatter.parse(req.getParameter("intakentime")));
				registrationroom.setLeavetime(formatter.parse(req.getParameter("leavetime")));
				HttpSession session = ContextHolderUtils.getSession();
				TSUser user = ResourceUtil.getSessionUserName();
				registrationroom.setRegistrant(user.getRealName());
				RoomEntity room = roomService.get(RoomEntity.class, req.getParameter("roomid"));
				registrationroom.setIntakentype(room.getRoomKind());
				registrationroom.setIntakennumber(room.getRoomNumber());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("registrationroomPage", registrationroom);
			req.setAttribute("roomid", req.getParameter("roomid"));
		}
		return new ModelAndView("com/sys/registrationroom/registrationroom");
	}
}
