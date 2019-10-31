package com.sys.controller.staff;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.PasswordUtil;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.RoletoJson;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.core.util.oConvertUtils;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.controller.core.UserController;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sys.entity.staff.StaffEntity;
import com.sys.page.staff.StaffPage;
import com.sys.service.staff.StaffServiceI;
import com.sys.entity.workexperience.WorkexperienceEntity;
import com.sys.entity.education.EducationEntity;
import com.sys.entity.familymember.FamilymemberEntity;
import com.sys.entity.department.DepartmentEntity;
/**   
 * @Title: Controller
 * @Description: 员工信息
 * @author zhangdaihao
 * @date 2015-10-23 10:16:46
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/staffController")
public class StaffController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(StaffController.class);

	@Autowired
	private StaffServiceI staffService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private UserService userService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 员工信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "staff")
	public ModelAndView staff(HttpServletRequest request) {
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));
		return new ModelAndView("com/sys/staff/staffList");
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
	public void datagrid(StaffEntity staff,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(StaffEntity.class, dataGrid);
		//查询条件组装器
		
		TSUser user = ResourceUtil.getSessionUserName();
		List<TSRoleUser> roleusers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		String roleuserString = "";
		for(TSRoleUser tru : roleusers){
			roleuserString += tru.getTSRole().getRoleName() + ",";
		}
		if(!roleuserString.contains("管理员")){
			cq.add(Restrictions.eq("id",user.getSystem_user_id()));
		}
		
		
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, staff);
		this.staffService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}
	/**
	 * 获取部门的员工
	 * @param user
	 * @param request
	 * @param response
	 * @param dataGrid
	 */
	@RequestMapping(params = "userDatagrid")
	public void userDatagrid(StaffEntity staff,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(StaffEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, staff);
		String departid = oConvertUtils.getString(request.getParameter("departid"));
		if (!StringUtil.isEmpty(departid)) {
			cq.add(Restrictions.eq("departid", departid));
		}
		
		this.systemService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除员工信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(StaffEntity staff, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		staff = systemService.getEntity(StaffEntity.class, staff.getId());
		message = "删除成功";
		staffService.delete(staff);
		// 删除用户时先删除用户和角色关系表
		TSUser tsUser = userService.findUniqueByProperty(TSUser.class, "system_user_id", staff.getId());
		List<TSRoleUser> roleUserList = systemService.findByProperty(TSRoleUser.class, "TSUser.id", tsUser.getId());
		if (roleUserList.size() >= 1) {
			for (TSRoleUser tRoleUser : roleUserList) {
				systemService.delete(tRoleUser);
			}
		}
		try{
			userService.delete(tsUser);
		}catch(Exception ex){
			System.out.println("EXCEPTION ==============================");
			ex.printStackTrace();
		}
		
		
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加员工信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(StaffEntity staff,StaffPage staffPage, HttpServletRequest request) {
		//获取用户的角色
		String roleid = oConvertUtils.getString(request.getParameter("roleid"));
		
		List<WorkexperienceEntity> workexperienceList =  staffPage.getWorkexperienceList();
		List<EducationEntity> educationList =  staffPage.getEducationList();
		List<FamilymemberEntity> familymemberList =  staffPage.getFamilymemberList();
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(staff.getId())) {
			TSUser tsUser = userService.findUniqueByProperty(TSUser.class, "system_user_id", staff.getId());
			tsUser.setStatus(Globals.User_Normal);
			tsUser.setMobilePhone(staff.getPhonenumber());
			tsUser.setUserName(staff.getStaffname());
			tsUser.setRealName(staff.getStaffname());
			tsUser.setStatus((short)1);
			tsUser.setSystem_user_id(staff.getId());
			TSDepart tsDepart = systemService.getEntity(TSDepart.class,staff.getDepartid());
			tsUser.setTSDepart(tsDepart);
			systemService.updateEntitie(tsUser);
			List<TSRoleUser> ru = systemService.findByProperty(TSRoleUser.class, "TSUser.id", tsUser.getId());
			systemService.deleteAllEntitie(ru);
			message = "更新成功";
			if (StringUtil.isNotEmpty(roleid)) {
				saveRoleAndUser(tsUser, roleid);
			}
			staffService.updateMain(staff, workexperienceList,educationList,familymemberList);
			systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
		} else {
			message = "添加成功";
			staffService.addMain(staff, workexperienceList,educationList,familymemberList);
			saveToSystemUser(staff,roleid);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
			
		}
		j.setMsg(message);
		return j;
	}
	/**
	 * 保存系统用户
	 * @param staff
	 * @param roleid
	 */
	 private void saveToSystemUser(StaffEntity staff,String roleid){
			TSUser user = getTSUser(staff);
			userService.save(user);
			
			//保存角色
			if (StringUtil.isNotEmpty(roleid)) {
				saveRoleAndUser(user,roleid);
			}
		}
	 
	 private TSUser getTSUser(StaffEntity staff){
		 	TSUser user = new TSUser();
			user.setStatus(Globals.User_Normal);
			user.setMobilePhone(staff.getPhonenumber());
			user.setUserName(staff.getStaffname());
			user.setRealName(staff.getStaffname());
			user.setPassword(PasswordUtil.encrypt(user.getUserName(), "888888", PasswordUtil.getStaticSalt()));
			user.setStatus((short)1);
			user.setSystem_user_id(staff.getId());
			TSDepart tsDepart = systemService.getEntity(TSDepart.class,staff.getDepartid());
			user.setTSDepart(tsDepart);
			return user;
	 }
	 	
	 private void saveRoleAndUser(TSUser user, String roleidstr){
	 		String[] roleids = roleidstr.split(",");
			for (int i = 0; i < roleids.length; i++) {
				TSRoleUser rUser = new TSRoleUser();
				TSRole role = systemService.getEntity(TSRole.class, roleids[i]);
				rUser.setTSRole(role);
				rUser.setTSUser(user);
				systemService.save(rUser);

			}
	 	}

	/**
	 * 员工信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(StaffEntity staff, HttpServletRequest req) {
		List<TSDepart> departList = new ArrayList<TSDepart>();
		String departid = oConvertUtils.getString(req.getParameter("departid"));
		if(!StringUtil.isEmpty(departid)){
			departList.add((TSDepart)systemService.getEntity(TSDepart.class,departid));
		}else {
			departList.addAll((List)systemService.getList(TSDepart.class));
		}
		req.setAttribute("departList", departList);
		
		if (StringUtil.isNotEmpty(staff.getId())) {
			staff = staffService.getEntity(StaffEntity.class, staff.getId());
			getRoleInStaff(req,staff);
			req.setAttribute("staffPage", staff);
		}
		return new ModelAndView("com/sys/staff/staff");
	}
	
	public void getRoleInStaff(HttpServletRequest req, StaffEntity staff) {
		TSUser user = userService.findUniqueByProperty(TSUser.class, "system_user_id", staff.getId());
		List<TSRoleUser> roleUsers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
		String roleId = "";
		String roleName = "";
		if (roleUsers.size() > 0) {
			for (TSRoleUser tRoleUser : roleUsers) {
				roleId += tRoleUser.getTSRole().getId() + ",";
				roleName += tRoleUser.getTSRole().getRoleName() + ",";
			}
		}
		req.setAttribute("id", roleId);
		req.setAttribute("roleName", roleName);

	}
	
	
	/**
	 * 加载明细列表[工作经历]
	 * 
	 * @return
	 */
	@RequestMapping(params = "workexperienceList")
	public ModelAndView workexperienceList(StaffEntity staff, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id0 = staff.getId();
		//===================================================================================
		//查询-工作经历
	    String hql0 = "from WorkexperienceEntity where 1 = 1 AND staffid = ? ";
		try{
		    List<WorkexperienceEntity> workexperienceEntityList = systemService.findHql(hql0,id0);
			req.setAttribute("workexperienceList", workexperienceEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/sys/staff/workexperienceList");
	}
	/**
	 * 加载明细列表[学历]
	 * 
	 * @return
	 */
	@RequestMapping(params = "educationList")
	public ModelAndView educationList(StaffEntity staff, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id1 = staff.getId();
		//===================================================================================
		//查询-学历
	    String hql1 = "from EducationEntity where 1 = 1  AND staffid = ? ";
		try{
		    List<EducationEntity> educationEntityList = systemService.findHql(hql1,id1);
			req.setAttribute("educationList", educationEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/sys/staff/educationList");
	}
	/**
	 * 加载明细列表[家庭成员]
	 * 
	 * @return
	 */
	@RequestMapping(params = "familymemberList")
	public ModelAndView familymemberList(StaffEntity staff, HttpServletRequest req) {
	
		//===================================================================================
		//获取参数
		Object id2 = staff.getId();
		//===================================================================================
		//查询-家庭成员
	    String hql2 = "from FamilymemberEntity where 1 = 1 AND staffid = ? ";
		try{
		    List<FamilymemberEntity> familymemberEntityList = systemService.findHql(hql2,id2);
			req.setAttribute("familymemberList", familymemberEntityList);
		}catch(Exception e){
			logger.info(e.getMessage());
		}
		return new ModelAndView("com/sys/staff/familymemberList");
	}
	
	/**
	 * 查询员工信息
	 * @param staff
	 * @param req
	 * @return
	 */
	@RequestMapping(params = "search")
	public ModelAndView search(HttpServletRequest req) {
		TSUser user = ResourceUtil.getSessionUserName();
		if(user.getSystem_user_id() != null){
			
			StaffEntity staff = staffService.getEntity(StaffEntity.class, user.getSystem_user_id());
			getRoleInStaff(req,staff);
			req.setAttribute("staffPage", staff);
			
			List<TSDepart> departList = new ArrayList<TSDepart>();
			String departid = oConvertUtils.getString(staff.getDepartid());
			if(!StringUtil.isEmpty(departid)){
				departList.add((TSDepart)systemService.getEntity(TSDepart.class,departid));
			}else {
				departList.addAll((List)systemService.getList(TSDepart.class));
			}
			req.setAttribute("departList", departList);
			
			Object id0 = staff.getId();
				//===================================================================================
			//查询-工作经历
			String hql0 = "from WorkexperienceEntity where 1 = 1 AND staffid = ? ";
			try{
				   List<WorkexperienceEntity> workexperienceEntityList = systemService.findHql(hql0,id0);
				   req.setAttribute("workexperienceList", workexperienceEntityList);
			}catch(Exception e){
					logger.info(e.getMessage());
			}
				
			Object id1 = staff.getId();
				//===================================================================================
				//查询-学历
			String hql1 = "from EducationEntity where 1 = 1 AND staffid = ? ";
			try{
				    List<EducationEntity> educationEntityList = systemService.findHql(hql1,id1);
					req.setAttribute("educationList", educationEntityList);
			}catch(Exception e){
					logger.info(e.getMessage());
			}
				
			Object id2 = staff.getId();
				//===================================================================================
				//查询-家庭成员
			String hql2 = "from FamilymemberEntity where 1 = 1 AND staffid = ? ";
			try{
				    List<FamilymemberEntity> familymemberEntityList = systemService.findHql(hql2,id2);
					req.setAttribute("familymemberList", familymemberEntityList);
			}catch(Exception e){
					logger.info(e.getMessage());
			}
		}
			
		return new ModelAndView("com/sys/staff/searchResult");
	}
	
	
}
