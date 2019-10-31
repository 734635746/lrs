package com.sys.controller.registrationcar;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sys.entity.car.CarEntity;
import com.sys.entity.education.EducationEntity;
import com.sys.entity.registrationcar.RegistrationcarEntity;
import com.sys.entity.staff.StaffEntity;
import com.sys.service.car.CarServiceI;
import com.sys.service.registrationcar.RegistrationcarServiceI;
import com.sys.service.staff.StaffServiceI;

/**   
 * @Title: Controller
 * @Description: 车辆使用登记表
 * @author zhangdaihao
 * @date 2015-10-27 10:11:45
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/registrationcarController")
public class RegistrationcarController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(RegistrationcarController.class);

	@Autowired
	private RegistrationcarServiceI registrationcarService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private StaffServiceI staffService;
	@Autowired
	private CarServiceI carService;
	private String message;
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 车辆使用登记表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "registrationcar")
	public ModelAndView registrationcar(HttpServletRequest request) {
		return new ModelAndView("com/sys/registrationcar/registrationcarList");
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
	public void datagrid(RegistrationcarEntity registrationcar,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(RegistrationcarEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, registrationcar, request.getParameterMap());
		this.registrationcarService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除车辆使用登记表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(RegistrationcarEntity registrationcar, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		registrationcar = systemService.getEntity(RegistrationcarEntity.class, registrationcar.getId());
		message = "车辆使用登记表删除成功";
		registrationcarService.delete(registrationcar);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加车辆使用登记表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(RegistrationcarEntity registrationcar, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		//===================================================================================
		//获取员工姓名参数
		Object para = registrationcar.getUsingcarpeople();
		//获取用车时间
		Object para1 = registrationcar.getUsingtime();
		//获取返回时间
		Object para2 = registrationcar.getReturntime();
		//获取车牌号码
		Object para3 = registrationcar.getPlatenumber();
		//建立hql语句
		String hql = "from RegistrationcarEntity where 1 = 1  AND platenumber = ? AND (usingtime between ? and ? OR returntime between ? and ?)";
		//===================================================================================
		//查询是否存在这个员工
		StaffEntity staff = (StaffEntity) systemService.findUniqueByProperty(StaffEntity.class, "staffname", para);
		List<RegistrationcarEntity> registrationcars = registrationcarService.findHql(hql,para3,para1,para2,para1,para2);
		
		if(staff != null){
			if(registrationcars.size() == 0){
				if (StringUtil.isNotEmpty(registrationcar.getId())) {
					message = "车辆使用登记表更新成功";
					RegistrationcarEntity t = registrationcarService.get(RegistrationcarEntity.class, registrationcar.getId());
					try {
						MyBeanUtils.copyBeanNotNull2Bean(registrationcar, t);
						registrationcarService.saveOrUpdate(t);
						systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
					} catch (Exception e) {
						e.printStackTrace();
						message = "车辆使用登记表更新失败";
					}
				} else {
					message = "车辆使用登记表添加成功";
					registrationcarService.save(registrationcar);
					systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
				}
			}
			else{
				message = "车牌" + para3 + "在这段时间已经被使用中";
			}
		}
		else{
			message = "不存在这个员工";
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 车辆使用登记表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(RegistrationcarEntity registrationcar, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(registrationcar.getId())) {
			registrationcar = registrationcarService.getEntity(RegistrationcarEntity.class, registrationcar.getId());
			req.setAttribute("registrationcarPage", registrationcar);
		}
		
		return new ModelAndView("com/sys/registrationcar/registrationcar");
	}
}
