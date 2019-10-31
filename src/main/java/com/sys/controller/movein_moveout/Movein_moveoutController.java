package com.sys.controller.movein_moveout;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
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

import com.sys.entity.disciplemember.DisciplememberEntity;
import com.sys.entity.movein_moveout.Movein_moveoutEntity;
import com.sys.service.disciplemember.DisciplememberServiceI;
import com.sys.service.movein_moveout.Movein_moveoutServiceI;

/**   
 * @Title: Controller
 * @Description: 迁入迁出记录
 * @author zhangdaihao
 * @date 2016-03-21 10:01:59
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/movein_moveoutController")
public class Movein_moveoutController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Movein_moveoutController.class);

	@Autowired
	private Movein_moveoutServiceI movein_moveoutService;
	@Autowired
	private DisciplememberServiceI disciplememberService;
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
	 * 迁入迁出记录列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "movein_moveout")
	public ModelAndView movein_moveout(HttpServletRequest request) {
		return new ModelAndView("com/sys/movein_moveout/movein_moveoutList");
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
	public void datagrid(Movein_moveoutEntity movein_moveout,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Movein_moveoutEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, movein_moveout, request.getParameterMap());
		this.movein_moveoutService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除迁入迁出记录
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Movein_moveoutEntity movein_moveout, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		movein_moveout = systemService.getEntity(Movein_moveoutEntity.class, movein_moveout.getId());
		message = "迁入迁出记录删除成功";
		movein_moveoutService.delete(movein_moveout);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加迁入迁出记录
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(Movein_moveoutEntity movein_moveout, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(movein_moveout.getId())) {
			message = "迁入迁出记录更新成功";
			Movein_moveoutEntity t = movein_moveoutService.get(Movein_moveoutEntity.class, movein_moveout.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(movein_moveout, t);
				movein_moveoutService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "迁入迁出记录更新失败";
			}
		} else {
			DisciplememberEntity disciplemember = disciplememberService.getEntity(DisciplememberEntity.class, movein_moveout.getDiscipleid());
			if(movein_moveout.getMoveintime() == null){
				disciplemember.setStatus(1);
				disciplememberService.updateEntitie(disciplemember);
			}
			if(movein_moveout.getMoveouttime() == null){
				disciplemember.setStatus(0);
				disciplememberService.updateEntitie(disciplemember);
			}
			TSUser user = ResourceUtil.getSessionUserName();
			movein_moveout.setA_handler(user.getRealName());
			
			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
			String dateString = formatter.format(currentTime);
			
			movein_moveout.setRegistertime(dateString);
			message = "迁入迁出记录添加成功";
			movein_moveoutService.save(movein_moveout);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 迁入迁出记录列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(Movein_moveoutEntity movein_moveout, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(movein_moveout.getId())) {
			movein_moveout = movein_moveoutService.getEntity(Movein_moveoutEntity.class, movein_moveout.getId());
			req.setAttribute("movein_moveoutPage", movein_moveout);
		}
		return new ModelAndView("com/sys/movein_moveout/movein_moveout");
	}
}
