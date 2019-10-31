package com.sys.controller.disciplemember;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.MyBeanUtils;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.service.SystemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.sys.entity.disciplemember.DisciplememberEntity;
import com.sys.entity.movein_moveout.Movein_moveoutEntity;
import com.sys.service.disciplemember.DisciplememberServiceI;

/**   
 * @Title: Controller
 * @Description: 皈依弟子档案表
 * @author zhangdaihao
 * @date 2015-10-24 09:37:43
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/disciplememberController")
public class DisciplememberController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(DisciplememberController.class);

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
	 * 皈依弟子档案表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "disciplemember")
	public ModelAndView disciplemember(HttpServletRequest request) {
		return new ModelAndView("com/sys/disciplemember/disciplememberList");
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
	public void datagrid(DisciplememberEntity disciplemember,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(DisciplememberEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, disciplemember, request.getParameterMap());
		this.disciplememberService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除皈依弟子档案表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(DisciplememberEntity disciplemember, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		disciplemember = systemService.getEntity(DisciplememberEntity.class, disciplemember.getId());
		message = "皈依弟子档案表删除成功";
		disciplememberService.delete(disciplemember);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加皈依弟子档案表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(DisciplememberEntity disciplemember, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(disciplemember.getId())) {
			message = "皈依弟子档案表更新成功";
			DisciplememberEntity t = disciplememberService.get(DisciplememberEntity.class, disciplemember.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(disciplemember, t);
				disciplememberService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "皈依弟子档案表更新失败";
			}
		} else {
			message = "皈依弟子档案表添加成功";
			disciplememberService.save(disciplemember);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 皈依弟子档案表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(DisciplememberEntity disciplemember, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(disciplemember.getId())) {
			String flag = req.getParameter("flag");
			if(flag != null){
				req.setAttribute("flag", flag);
				disciplemember = disciplememberService.getEntity(DisciplememberEntity.class, disciplemember.getId());
				if(flag.equals("in")){
					if(disciplemember.getStatus() == 1){
						Movein_moveoutEntity m = new Movein_moveoutEntity();
						m.setDiscipleid(disciplemember.getId());
						m.setDisciplename(disciplemember.getDisciplename());
						req.setAttribute("movein_moveoutPage", m);
						return new ModelAndView("com/sys/movein_moveout/movein_moveout");
					}
				}
				if(flag.equals("out")){
					if(disciplemember.getStatus() == 0){
						Movein_moveoutEntity m = new Movein_moveoutEntity();
						m.setDiscipleid(disciplemember.getId());
						m.setDisciplename(disciplemember.getDisciplename());
						req.setAttribute("movein_moveoutPage", m);
						return new ModelAndView("com/sys/movein_moveout/movein_moveout");
					}
				}
			}
			else{
				disciplemember = disciplememberService.getEntity(DisciplememberEntity.class, disciplemember.getId());
				req.setAttribute("disciplememberPage", disciplemember);
				return new ModelAndView("com/sys/disciplemember/disciplemember");
			}
			
		}
		else{
			req.setAttribute("disciplememberPage", disciplemember);
			return new ModelAndView("com/sys/disciplemember/disciplemember");
		}
		return new ModelAndView("com/sys/disciplemember/error");
	}
}
