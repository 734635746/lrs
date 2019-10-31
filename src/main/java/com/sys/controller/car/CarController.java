package com.sys.controller.car;
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
import com.sys.service.car.CarServiceI;

/**   
 * @Title: Controller
 * @Description: 车辆信息
 * @author zhangdaihao
 * @date 2015-12-28 19:37:14
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/carController")
public class CarController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(CarController.class);

	@Autowired
	private CarServiceI carService;
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
	 * 车辆信息列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "car")
	public ModelAndView car(HttpServletRequest request) {
		return new ModelAndView("com/sys/car/carList");
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
	public void datagrid(CarEntity car,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(CarEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, car, request.getParameterMap());
		this.carService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除车辆信息
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(CarEntity car, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		car = systemService.getEntity(CarEntity.class, car.getId());
		message = "车辆信息删除成功";
		carService.delete(car);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加车辆信息
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(CarEntity car, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(car.getId())) {
			message = "车辆信息更新成功";
			CarEntity t = carService.get(CarEntity.class, car.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(car, t);
				carService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "车辆信息更新失败";
			}
		} else {
			message = "车辆信息添加成功";
			carService.save(car);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 车辆信息列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(CarEntity car, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(car.getId())) {
			car = carService.getEntity(CarEntity.class, car.getId());
			req.setAttribute("carPage", car);
		}
		return new ModelAndView("com/sys/car/car");
	}
}
