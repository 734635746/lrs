package com.sys.controller.pravrajanamember;
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
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.pojo.base.TSRole;
import org.jeecgframework.web.system.pojo.base.TSRoleUser;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.web.system.service.UserService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sys.entity.education.EducationEntity;
import com.sys.entity.familymember.FamilymemberEntity;
import com.sys.entity.pravrajanamember.PravrajanamemberEntity;
import com.sys.entity.staff.StaffEntity;
import com.sys.entity.workexperience.WorkexperienceEntity;
import com.sys.service.pravrajanamember.PravrajanamemberServiceI;

/**   
 * @Title: Controller
 * @Description: 出家众人档案表
 * @author zhangdaihao
 * @date 2015-10-24 10:33:23
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/pravrajanamemberController")
public class PravrajanamemberController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(PravrajanamemberController.class);

	@Autowired
	private PravrajanamemberServiceI pravrajanamemberService;
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
	 * 出家众人档案表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "pravrajanamember")
	public ModelAndView pravrajanamember(HttpServletRequest request) {
		List<TSDepart> departList = systemService.getList(TSDepart.class);
		request.setAttribute("departsReplace", RoletoJson.listToReplaceStr(departList, "departname", "id"));
		return new ModelAndView("com/sys/pravrajanamember/pravrajanamemberList");
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
	public void datagrid(PravrajanamemberEntity pravrajanamember,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(PravrajanamemberEntity.class, dataGrid);
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
		
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, pravrajanamember, request.getParameterMap());
		this.pravrajanamemberService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除出家众人档案表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(PravrajanamemberEntity pravrajanamember, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		pravrajanamember = systemService.getEntity(PravrajanamemberEntity.class, pravrajanamember.getId());
		message = "出家众人档案表删除成功";
		pravrajanamemberService.delete(pravrajanamember);
		
		TSUser tsUser = userService.findUniqueByProperty(TSUser.class, "system_user_id", pravrajanamember.getId());
		List<TSRoleUser> roleUserList = systemService.findByProperty(TSRoleUser.class, "TSUser.id", tsUser.getId());
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
	 * 添加出家众人档案表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(PravrajanamemberEntity pravrajanamember, HttpServletRequest request) {
		String roleid = oConvertUtils.getString(request.getParameter("roleid"));
		
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(pravrajanamember.getId())) {
			message = "出家众人档案表更新成功";
			PravrajanamemberEntity t = pravrajanamemberService.get(PravrajanamemberEntity.class, pravrajanamember.getId());
			
			TSUser tsUser = userService.findUniqueByProperty(TSUser.class, "system_user_id", t.getId());
			tsUser.setStatus(Globals.User_Normal);
			tsUser.setMobilePhone(t.getPhonenumber());
			tsUser.setUserName(t.getDharmaname());
			tsUser.setRealName(t.getDharmaname());
			tsUser.setStatus((short)1);
			tsUser.setSystem_user_id(t.getId());
			TSDepart tsDepart = systemService.getEntity(TSDepart.class,pravrajanamember.getDepartid());
			tsUser.setTSDepart(tsDepart);
			systemService.updateEntitie(tsUser);
			List<TSRoleUser> ru = systemService.findByProperty(TSRoleUser.class, "TSUser.id", tsUser.getId());
			systemService.deleteAllEntitie(ru);
			
			TSUser user = ResourceUtil.getSessionUserName();
			List<TSRoleUser> roleusers = systemService.findByProperty(TSRoleUser.class, "TSUser.id", user.getId());
			String roleuserString = "";
			for(TSRoleUser tru : roleusers){
				roleuserString += tru.getTSRole().getRoleName() + ",";
			}
			
			if (StringUtil.isNotEmpty(roleid)) {
				saveRoleAndUser(tsUser, roleid);
			}
			
			
			try {
				MyBeanUtils.copyBeanNotNull2Bean(pravrajanamember, t);
				pravrajanamemberService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "出家众人档案表更新失败";
			}
		} else {
			message = "出家众人档案表添加成功";
			pravrajanamemberService.save(pravrajanamember);
			saveToSystemUser(pravrajanamember,roleid);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}
	
	private void saveToSystemUser(PravrajanamemberEntity pravrajanamember,String roleid){
		TSUser user = getTSUser(pravrajanamember);
		userService.save(user);
		
		//保存角色
		if (StringUtil.isNotEmpty(roleid)) {
			saveRoleAndUser(user,roleid);
		}
	}
 
	 private TSUser getTSUser(PravrajanamemberEntity pravrajanamember){
		 	TSUser user = new TSUser();
			user.setStatus(Globals.User_Normal);
			user.setMobilePhone(pravrajanamember.getPhonenumber());
			user.setUserName(pravrajanamember.getDharmaname());
			user.setRealName(pravrajanamember.getDharmaname());
			user.setPassword(PasswordUtil.encrypt(user.getUserName(), "888888", PasswordUtil.getStaticSalt()));
			user.setStatus((short)1);
			user.setSystem_user_id(pravrajanamember.getId());
			TSDepart tsDepart = systemService.getEntity(TSDepart.class,pravrajanamember.getDepartid());
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

	 
	 public void getRoleInPravrajanamember(HttpServletRequest req, PravrajanamemberEntity pravrajanamember) {
			TSUser user = userService.findUniqueByProperty(TSUser.class, "system_user_id", pravrajanamember.getId());
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
	 * 出家众人档案表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(PravrajanamemberEntity pravrajanamember, HttpServletRequest req) {
		List<TSDepart> departList = new ArrayList<TSDepart>();
		String departid = oConvertUtils.getString(req.getParameter("departid"));
		if(!StringUtil.isEmpty(departid)){
			departList.add((TSDepart)systemService.getEntity(TSDepart.class,departid));
		}else {
			departList.addAll((List)systemService.getList(TSDepart.class));
		}
		req.setAttribute("departList", departList);
		
		if (StringUtil.isNotEmpty(pravrajanamember.getId())) {
			getRoleInPravrajanamember(req,pravrajanamember);
			pravrajanamember = pravrajanamemberService.getEntity(PravrajanamemberEntity.class, pravrajanamember.getId());
			req.setAttribute("pravrajanamemberPage", pravrajanamember);
		}
		return new ModelAndView("com/sys/pravrajanamember/pravrajanamember");
	}
	
	
	@RequestMapping(params = "update")
	public ModelAndView update(HttpServletRequest req) {
		TSUser user = ResourceUtil.getSessionUserName();
		PravrajanamemberEntity pravrajanamember = null;
		if(user.getSystem_user_id() != null){
			
			pravrajanamember = pravrajanamemberService.getEntity(PravrajanamemberEntity.class, user.getSystem_user_id());
			getRoleInPravrajanamember(req,pravrajanamember);
			req.setAttribute("pravrajanamemberPage", pravrajanamember);
			
		}
		List<TSDepart> departList = new ArrayList<TSDepart>();
		String departid = oConvertUtils.getString(pravrajanamember.getDepartid());
		if(!StringUtil.isEmpty(departid)){
			departList.add((TSDepart)systemService.getEntity(TSDepart.class,departid));
		}else {
			departList.addAll((List)systemService.getList(TSDepart.class));
		}
		req.setAttribute("departList", departList);
		
			
		return new ModelAndView("com/sys/pravrajanamember/updatePravrajanamember");
	}
}
