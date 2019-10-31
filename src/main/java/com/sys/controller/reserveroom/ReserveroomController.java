package com.sys.controller.reserveroom;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ContextHolderUtils;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sys.entity.registrationroom.RegistrationroomEntity;
import com.sys.entity.reserveroom.ReserveroomEntity;
import com.sys.entity.room.RoomEntity;
import com.sys.service.registrationroom.RegistrationroomServiceI;
import com.sys.service.reserveroom.ReserveroomServiceI;
import com.sys.service.room.RoomServiceI;

/**   
 * @Title: Controller
 * @Description: 预定房间
 * @author zhangdaihao
 * @date 2016-01-14 15:09:17
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/reserveroomController")
public class ReserveroomController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(ReserveroomController.class);

	@Autowired
	private ReserveroomServiceI reserveroomService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private RoomServiceI roomService;
	@Autowired
	private RegistrationroomServiceI registrationroomService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 预定房间列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "reserveroom")
	public ModelAndView reserveroom(HttpServletRequest request) {
		return new ModelAndView("com/sys/reserveroom/reserveroomList");
	}
	
	@RequestMapping(params = "toSearch")
	public ModelAndView toSearch(HttpServletRequest request) {
		List<TSType> types = systemService.findUniqueByProperty(TSTypegroup.class, "typegroupname", "房间类型").getTSTypes();
		request.setAttribute("types", types);
		return new ModelAndView("com/sys/reserveroom/searchPage");
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
	public void datagrid(ReserveroomEntity reserveroom,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(ReserveroomEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, reserveroom, request.getParameterMap());
		this.reserveroomService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除预定房间
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(ReserveroomEntity reserveroom, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		reserveroom = systemService.getEntity(ReserveroomEntity.class, reserveroom.getId());
		message = "预定房间删除成功";
		reserveroomService.delete(reserveroom);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加预定房间
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(ReserveroomEntity reserveroom, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(reserveroom.getId())) {
			message = "预定房间更新成功";
			ReserveroomEntity t = reserveroomService.get(ReserveroomEntity.class, reserveroom.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(reserveroom, t);
				reserveroomService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "预定房间更新失败";
			}
		} else {
			message = "预定房间添加成功";
			reserveroomService.save(reserveroom);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 预定房间列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(ReserveroomEntity reserveroom, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(reserveroom.getId())) {
			reserveroom = reserveroomService.getEntity(ReserveroomEntity.class, reserveroom.getId());
			req.setAttribute("reserveroomPage", reserveroom);
		}
		if(req.getParameter("reserverflag").equals("yes")){
			java.text.SimpleDateFormat formatter = new SimpleDateFormat( "yyyy-MM-dd HH:mm:ss");
			try {
				reserveroom.setIntakentime(formatter.parse(req.getParameter("intakentime")));
				reserveroom.setLeavetime(formatter.parse(req.getParameter("leavetime")));
				HttpSession session = ContextHolderUtils.getSession();
				TSUser user = ResourceUtil.getSessionUserName();
				reserveroom.setRegistrant(user.getRealName());
				RoomEntity room = roomService.get(RoomEntity.class, req.getParameter("roomid"));
				reserveroom.setRoomkind(room.getRoomKind());
				reserveroom.setRoomnumber(room.getRoomNumber());
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			req.setAttribute("reserveroomPage", reserveroom);
			req.setAttribute("roomid", req.getParameter("roomid"));
			req.setAttribute("reserverflag", req.getParameter("reserverflag"));
		}
		else{
			if(reserveroom.getPredeterminedstate().equals("已确认") || reserveroom.getPredeterminedstate().equals("已取消")){
				req.setAttribute("duplicateFlag", "T");
			}
		}
		return new ModelAndView("com/sys/reserveroom/reserveroom");
	}
	
	/**
	 * 从预定房间调到登记房间
	 * @param reserveroom
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "forwardToRegisterRoom")
	public ModelAndView forwardToRegisterRoom(String id, HttpServletRequest req) {
		ReserveroomEntity reserveroom = new ReserveroomEntity();
		RegistrationroomEntity registrationroom = new RegistrationroomEntity();
		if(id != null || !id.equals("")){
			reserveroom = reserveroomService.getEntity(ReserveroomEntity.class, id);
			

			//将预定房间的基本信息转到登记入住房间里面去
			registrationroom = new RegistrationroomEntity();
			registrationroom.setIntakentime(reserveroom.getIntakentime());
			registrationroom.setLeavetime(reserveroom.getLeavetime());
			HttpSession session = ContextHolderUtils.getSession();
			TSUser user = ResourceUtil.getSessionUserName();
			registrationroom.setRegistrant(user.getRealName());
			RoomEntity room = roomService.get(RoomEntity.class,reserveroom.getRoomid());
			registrationroom.setIntakentype(room.getRoomKind());
			registrationroom.setIntakennumber(room.getRoomNumber());
			registrationroom.setMobilephone(reserveroom.getMobilephone());
			registrationroom.setNeedbeds(reserveroom.getNeedbeds());
			registrationroom.setRoomid(reserveroom.getRoomid());
			reserveroom.setPredeterminedstate("已确认");
			reserveroomService.updateEntitie(reserveroom);
		
			req.setAttribute("registrationroomPage", registrationroom);
		}
		return new ModelAndView("com/sys/registrationroom/registrationroom");
	}
	
	/**
	 * 查询预定房间
	 * @param intakentime
	 * @param leavetime
	 * @param type
	 * @param response
	 */
	@RequestMapping(params = "query")
	public void query(Date intakentime,Date leavetime,String type,HttpServletResponse response) {
		String hql = "from ReserveroomEntity where 1 = 1  AND roomkind = ? AND (intakentime between ? and ? OR leavetime between ? and ? OR intakentime < ? and leavetime > ?) AND predeterminedstate = ?";
		List<ReserveroomEntity> reserveRooms = reserveroomService.findHql(hql,type,intakentime,leavetime,intakentime,leavetime,intakentime,leavetime,"未入住");
		
		String hql1 = "from RegistrationroomEntity where 1 = 1  AND intakentype = ? AND (intakentime between ? and ? OR leavetime between ? and ? OR intakentime < ? and leavetime > ?) AND leavestate = ?";
		List<RegistrationroomEntity> registrationrooms = registrationroomService.findHql(hql1,type,intakentime,leavetime,intakentime,leavetime,intakentime,leavetime,"未离开");
		
		List<RoomEntity> rooms = null;
		List<String> roomid = new ArrayList<String>();
		
		List<RoomEntity> tmp_RoomEntitys = roomService.getList(RoomEntity.class);
		
		for(RoomEntity re : tmp_RoomEntitys){
			re.setRestbeds(re.getBeds());
			roomService.updateEntitie(re);
		}
		
		if(reserveRooms.size() == 0 && registrationrooms.size() == 0){
			rooms = roomService.findByProperty(RoomEntity.class, "roomKind", type);
			for(RoomEntity room :rooms){
				room.setRestbeds(room.getBeds());
			}
		}
		else{
			//获取已经有人入住的房间的ID
			for(ReserveroomEntity re : reserveRooms){
				RoomEntity roomEntity = roomService.get(RoomEntity.class, re.getRoomid());
				roomEntity.setRestbeds(roomEntity.getRestbeds() - re.getNeedbeds()); 
			}
			for(ReserveroomEntity re : reserveRooms){
				RoomEntity roomEntity = roomService.get(RoomEntity.class, re.getRoomid());
				if(roomEntity.getRestbeds() == 0){
					roomid.add(re.getRoomid());
				}
			}
			for(RegistrationroomEntity re : registrationrooms){
				RoomEntity roomEntity = roomService.get(RoomEntity.class, re.getRoomid());
				roomEntity.setRestbeds(roomEntity.getRestbeds() - re.getNeedbeds()); 
			}
			for(RegistrationroomEntity re : registrationrooms){
				RoomEntity roomEntity = roomService.get(RoomEntity.class, re.getRoomid());
				if(roomEntity.getRestbeds() <= 0){
					roomid.add(re.getRoomid());
				}
			}
			CriteriaQuery cq = new CriteriaQuery(RoomEntity.class);
			if(roomid.size() == 0){
				cq.add(Restrictions.eq("roomKind", type));
			}
			else{
				cq.add(Restrictions.and(Restrictions.eq("roomKind", type),Restrictions.not(Restrictions.in("id", roomid))));
			}
			rooms = roomService.getListByCriteriaQuery(cq, true);
		}
		String result = "";
		if(rooms == null){
			result = result + "找不到适合条件的房间";
		}
		else{
			result = getJSONArrayOfRoomEntity(rooms).toString();
			 
		}
		try {
		      response.getWriter().write(result);
		    } catch (IOException e) {
		      e.printStackTrace();
		 }
	}
	
	/**
	 * 转换为JSON数据
	 * @param ancestors
	 * @return
	 */
	private static JSONArray getJSONArrayOfRoomEntity(List<RoomEntity> rooms){
		JSONArray json = new JSONArray();
		for(RoomEntity room : rooms){
            JSONObject jo = new JSONObject();
            jo.put("id", room.getId());
            jo.put("roomNumber", room.getRoomNumber());
            jo.put("roomKind", room.getRoomKind());
            jo.put("restBeds", room.getRestbeds());
            json.add(jo);
        }
		return json;
		
	}
}
