package com.sys.controller.delivery_detail;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.AjaxJson;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.constant.Globals;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.core.util.StringUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSDepart;
import org.jeecgframework.web.system.service.SystemService;
import org.jeecgframework.core.util.MyBeanUtils;

import com.sys.entity.delivery_detail.Delivery_detailEntity;
import com.sys.entity.repertory.RepertoryEntity;
import com.sys.entity.warehousing_detail.Warehousing_detailEntity;
import com.sys.service.delivery_detail.Delivery_detailServiceI;
import com.sys.service.repertory.RepertoryServiceI;

/**   
 * @Title: Controller
 * @Description: 出库明细表
 * @author zhangdaihao
 * @date 2016-10-18 18:50:40
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/delivery_detailController")
public class Delivery_detailController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Delivery_detailController.class);

	@Autowired
	private Delivery_detailServiceI delivery_detailService;
	@Autowired
	private RepertoryServiceI repertoryService;
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
	 * 出库明细表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "delivery_detail")
	public ModelAndView delivery_detail(HttpServletRequest request) {
		return new ModelAndView("com/sys/delivery_detail/delivery_detailList");
	}
	
	@RequestMapping(params = "towardDelivery")
	public ModelAndView towardDelivery(HttpServletRequest request) {
		return new ModelAndView("com/sys/delivery_detail/queryItem");
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
	public void datagrid(Delivery_detailEntity delivery_detail,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Delivery_detailEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, delivery_detail, request.getParameterMap());
		this.delivery_detailService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除出库明细表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Delivery_detailEntity delivery_detail, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		delivery_detail = systemService.getEntity(Delivery_detailEntity.class, delivery_detail.getId());
		message = "出库明细表删除成功";
		delivery_detailService.delete(delivery_detail);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加出库明细表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(Delivery_detailEntity delivery_detail, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(delivery_detail.getId())) {
			message = "出库明细表更新成功";
			Delivery_detailEntity t = delivery_detailService.get(Delivery_detailEntity.class, delivery_detail.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(delivery_detail, t);
				delivery_detailService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "出库明细表更新失败";
			}
		} else {
			message = "出库明细表添加成功";
			delivery_detailService.save(delivery_detail);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 出库明细表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(Delivery_detailEntity delivery_detail, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(delivery_detail.getId())) {
			delivery_detail = delivery_detailService.getEntity(Delivery_detailEntity.class, delivery_detail.getId());
			req.setAttribute("delivery_detailPage", delivery_detail);
		}
		return new ModelAndView("com/sys/delivery_detail/delivery_detail");
	}
	
	@RequestMapping(params = "queryItem")
	@ResponseBody
	public void queryItem(HttpServletRequest req,HttpServletResponse resp,String itemName) throws IOException, ParseException {
		
		
		itemName = URLDecoder.decode(itemName, "utf-8");
		AjaxJson j = new AjaxJson();
		
		CriteriaQuery cq = new CriteriaQuery(RepertoryEntity.class);
		cq.add(Restrictions.like("itemName", itemName, MatchMode.ANYWHERE));
		
		CriteriaQuery cq1 = new CriteriaQuery(RepertoryEntity.class);
		cq1.add(Restrictions.like("itemStdmode", itemName, MatchMode.ANYWHERE));
		
		
		List<RepertoryEntity> repertoryEntityList = repertoryService.getListByCriteriaQuery(cq, false);

		List<RepertoryEntity> repertoryEntityList1 = repertoryService.getListByCriteriaQuery(cq1, false);
		
		for(RepertoryEntity re1 : repertoryEntityList1){
			int flag = 1;
			for(RepertoryEntity re : repertoryEntityList){
				if(re1.getId() == re.getId()){
					flag = 0;
				}
			}
			if(flag == 1){
				repertoryEntityList.add(re1);
			}
			
		}
		
		String res = getJSONArrayOfRepertoryEntityList(repertoryEntityList).toString();
		resp.getWriter().write(res);
		/*resp.getWriter().write(result.substring(0, result.length() - 1));*/
	}
	
	private static JSONArray getJSONArrayOfRepertoryEntityList(List<RepertoryEntity> repertoryEntityList){
		JSONArray json = new JSONArray();
		for(RepertoryEntity re : repertoryEntityList){
            JSONObject jo = new JSONObject();
            jo.put("id", re.getId());
            jo.put("item_stdmode", re.getItemStdmode());
            jo.put("item_name", re.getItemName());
            jo.put("item_manufacturer",re.getItemManufacturer());
            jo.put("current_inventory",re.getCurrentInventory());
            jo.put("unit",re.getUnit());
            
            
            json.add(jo);
        }
		return json;
		
	}
	
	@RequestMapping(params = "deliveryItem")
	@ResponseBody
	public void addOldItem(HttpServletRequest req,HttpServletResponse resp,String itemId,String number) throws IOException, ParseException {
		
		String membername = StringUtil.getEncodePra(req.getParameter("membername"));
		
		RepertoryEntity repertoryEntity = repertoryService.get(RepertoryEntity.class, itemId);
		repertoryEntity.setCurrentInventory(repertoryEntity.getCurrentInventory() - Integer.valueOf(number));
		repertoryService.updateEntitie(repertoryEntity);
		
		//保存入库记录
		Delivery_detailEntity dd = new Delivery_detailEntity();
		dd.setDeliveryQuantity(Integer.valueOf(number));
		dd.setItemManufacturer(repertoryEntity.getItemManufacturer());
		dd.setItemName(repertoryEntity.getItemName());
		dd.setItemStdmode(repertoryEntity.getItemStdmode());
		dd.setManufacturerAddress(repertoryEntity.getManufacturerAddress());
		dd.setUnit(repertoryEntity.getUnit());
		dd.setMemberName(membername);
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		
		String username = ResourceUtil.getSessionUserName().getRealName();
		
		dd.setRegister(username);
		dd.setRegistertime(dateString);
		delivery_detailService.save(dd);
		
		resp.getWriter().write("出货成功！");
		/*resp.getWriter().write(result.substring(0, result.length() - 1));*/
	}
}
