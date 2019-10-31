package com.sys.controller.room;
import java.io.IOException;
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
import org.jeecgframework.web.system.pojo.base.TSType;
import org.jeecgframework.web.system.pojo.base.TSTypegroup;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.entity.registrationroom.RegistrationroomEntity;
import com.sys.entity.reserveroom.ReserveroomEntity;
import com.sys.entity.room.RoomEntity;
import com.sys.service.registrationroom.RegistrationroomServiceI;
import com.sys.service.reserveroom.ReserveroomServiceI;
import com.sys.service.room.RoomServiceI;

/**   
 * @Title: Controller
 * @Description: 客房基本信息
 * @author zhangdaihao
 * @date 2015-12-29 15:09:47
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/roomController")
public class RoomController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RoomController.class);

	@Autowired
	private RoomServiceI roomService;
	@Autowired
	private RegistrationroomServiceI registrationroomService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private ReserveroomServiceI reserveroomService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 客房基本信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "room")
	public ModelAndView room(HttpServletRequest request) {
		return new ModelAndView("com/sys/room/roomList");
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
	public void datagrid(RoomEntity room,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RoomEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, room, request.getParameterMap());
		this.roomService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除客房基本信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(RoomEntity room, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		room = systemService.getEntity(RoomEntity.class, room.getId());
		message = "客房基本信息删除成功";
		roomService.delete(room);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加客房基本信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(RoomEntity room, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(room.getId())) {
			message = "客房基本信息更新成功";
			RoomEntity t = roomService.get(RoomEntity.class, room.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(room, t);
				roomService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "客房基本信息更新失败";
			}
		} else {
			message = "客房基本信息添加成功";
			room.setRestbeds(room.getBeds());
			roomService.save(room);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 客房基本信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(RoomEntity room, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(room.getId())) {
			room = roomService.getEntity(RoomEntity.class, room.getId());
			req.setAttribute("roomPage", room);
		}
		return new ModelAndView("com/sys/room/room");
	}
	
	/**
	 * 跳转到查询页面
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "toSearch")
	public ModelAndView toSearch(HttpServletRequest req) {
		List<TSType> types = systemService.findUniqueByProperty(TSTypegroup.class, "typegroupname", "房间类型").getTSTypes();
		req.setAttribute("types", types);
		return new ModelAndView("com/sys/room/searchPage");
	}
	
	/**
	 * 按照入住时间和离开时间，类型进行查询
	 * @param intakentime
	 * @param leavetime
	 * @param type
	 * @param response
	 * @return
	 */
	@RequestMapping(params = "search")
	public void search(Date intakentime,Date leavetime,String type,HttpServletResponse response) {
		//从住房登记表里查找
		String hql = "from RegistrationroomEntity where 1 = 1  AND intakentype = ? AND (intakentime between ? and ? OR leavetime between ? and ? OR intakentime < ? and leavetime > ?) AND leavestate = ?";
		List<RegistrationroomEntity> registrationrooms = registrationroomService.findHql(hql,type,intakentime,leavetime,intakentime,leavetime,intakentime,leavetime,"未离开");
		
		//从住房预定表里查找
		String hql1 = "from ReserveroomEntity where 1 = 1  AND roomkind = ? AND (intakentime between ? and ? OR leavetime between ? and ? OR intakentime < ? and leavetime > ?) AND predeterminedstate = ?";
		List<ReserveroomEntity> reserveroomEntitys = reserveroomService.findHql(hql1,type,intakentime,leavetime,intakentime,leavetime,intakentime,leavetime,"未入住");
		System.out.println(reserveroomEntitys.size() + "=================");
		
		List<RoomEntity> rooms = null;
		List<String> roomid = new ArrayList<String>();
		//如果两个表都是空的就没有预定和入住
		
		List<RoomEntity> tmp_RoomEntitys = roomService.getList(RoomEntity.class);
		
		for(RoomEntity re : tmp_RoomEntitys){
			re.setRestbeds(re.getBeds());
			roomService.updateEntitie(re);
		}
		if(registrationrooms.size() == 0 && reserveroomEntitys.size() == 0){
			rooms = roomService.findByProperty(RoomEntity.class, "roomKind", type);
			for(RoomEntity room :rooms){
				room.setRestbeds(room.getBeds());
			}
		}
		else{
			//获取已经有人入住的房间的ID
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
			for(ReserveroomEntity reserver:reserveroomEntitys){
				RoomEntity roomEntity = roomService.get(RoomEntity.class, reserver.getRoomid());
				roomEntity.setRestbeds(roomEntity.getRestbeds() - reserver.getNeedbeds()); 
			}
			for(ReserveroomEntity reserver : reserveroomEntitys){
				RoomEntity roomEntity = roomService.get(RoomEntity.class, reserver.getRoomid());
				if(roomEntity.getRestbeds() <= 0){
					roomid.add(reserver.getRoomid());
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
