package com.sys.controller.warehousing_detail;
import java.io.IOException;
import java.net.URLDecoder;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
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

import test.AttendanceStatistics;
import test.CalEverySaturday;
import test.Lunar;
import test.LunarCalendar;
import test.LunarToSolar;

import com.sys.entity.repertory.RepertoryEntity;
import com.sys.entity.warehousing_detail.Warehousing_detailEntity;
import com.sys.service.repertory.RepertoryServiceI;
import com.sys.service.warehousing_detail.Warehousing_detailServiceI;

/**   
 * @Title: Controller
 * @Description: 入库明细管理
 * @author zhangdaihao
 * @date 2016-10-18 18:42:52
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/warehousing_detailController")
public class Warehousing_detailController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Warehousing_detailController.class);

	@Autowired
	private Warehousing_detailServiceI warehousing_detailService;
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
	 * 入库明细管理列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "warehousing_detail")
	public ModelAndView warehousing_detail(HttpServletRequest request) {
		return new ModelAndView("com/sys/warehousing_detail/warehousing_detailList");
	}
	
	@RequestMapping(params = "towardWareHousing")
	public ModelAndView towardWareHousing(HttpServletRequest request) {
		return new ModelAndView("com/sys/warehousing_detail/queryItem");
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
	public void datagrid(Warehousing_detailEntity warehousing_detail,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Warehousing_detailEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, warehousing_detail, request.getParameterMap());
		this.warehousing_detailService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除入库明细管理
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Warehousing_detailEntity warehousing_detail, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		warehousing_detail = systemService.getEntity(Warehousing_detailEntity.class, warehousing_detail.getId());
		message = "入库明细管理删除成功";
		warehousing_detailService.delete(warehousing_detail);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加入库明细管理
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(Warehousing_detailEntity warehousing_detail, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(warehousing_detail.getId())) {
			message = "入库明细管理更新成功";
			Warehousing_detailEntity t = warehousing_detailService.get(Warehousing_detailEntity.class, warehousing_detail.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(warehousing_detail, t);
				warehousing_detailService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "入库明细管理更新失败";
			}
		} else {
			message = "入库明细管理添加成功";
			warehousing_detailService.save(warehousing_detail);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 入库明细管理列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(Warehousing_detailEntity warehousing_detail, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(warehousing_detail.getId())) {
			warehousing_detail = warehousing_detailService.getEntity(Warehousing_detailEntity.class, warehousing_detail.getId());
			req.setAttribute("warehousing_detailPage", warehousing_detail);
		}
		return new ModelAndView("com/sys/warehousing_detail/warehousing_detail");
	}
	
	@RequestMapping(params = "queryItem")
	@ResponseBody
	public void queryItem(HttpServletRequest req,HttpServletResponse resp,String itemName) throws IOException, ParseException {
		
		
		itemName = URLDecoder.decode(itemName, "utf-8");
		AjaxJson j = new AjaxJson();
		
		CriteriaQuery cq1 = new CriteriaQuery(RepertoryEntity.class);
		cq1.add(Restrictions.like("itemStdmode", itemName, MatchMode.ANYWHERE));
		
		
		CriteriaQuery cq = new CriteriaQuery(RepertoryEntity.class);
		cq.add(Restrictions.like("itemName", itemName, MatchMode.ANYWHERE));
		
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
	
	
	@RequestMapping(params = "addOldItem")
	@ResponseBody
	public void addOldItem(HttpServletRequest req,HttpServletResponse resp,String itemId,String number) throws IOException, ParseException {
		
		RepertoryEntity repertoryEntity = repertoryService.get(RepertoryEntity.class, itemId);
		repertoryEntity.setCurrentInventory(repertoryEntity.getCurrentInventory() + Integer.valueOf(number));
		repertoryService.updateEntitie(repertoryEntity);
		
		//保存入库记录
		Warehousing_detailEntity wd = new Warehousing_detailEntity();
		wd.setIncomingQuantity(Integer.valueOf(number));
		wd.setItemManufacturer(repertoryEntity.getItemManufacturer());
		wd.setItemName(repertoryEntity.getItemName());
		wd.setItemStdmode(repertoryEntity.getItemStdmode());
		wd.setManufacturerAddress(repertoryEntity.getManufacturerAddress());
		wd.setPrice(repertoryEntity.getPrice());
		wd.setUnit(repertoryEntity.getUnit());
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		
		String username = ResourceUtil.getSessionUserName().getRealName();
		
		wd.setRegister(username);
		wd.setRegistertime(dateString);
		warehousing_detailService.save(wd);
		
		resp.getWriter().write("添加货物数量成功！");
		/*resp.getWriter().write(result.substring(0, result.length() - 1));*/
	}
	
	@RequestMapping(params = "addNewItem")
	@ResponseBody
	public void addNewItem(HttpServletRequest req,HttpServletResponse resp) throws IOException, ParseException {
		
		String item_name = StringUtil.getEncodePra(req.getParameter("item_name"));
		String inventory = req.getParameter("inventory");
		String item_manufacturer = StringUtil.getEncodePra(req.getParameter("item_manufacturer"));
		String manufacturer_address = StringUtil.getEncodePra(req.getParameter("manufacturer_address"));
		String item_stdmode = StringUtil.getEncodePra(req.getParameter("item_stdmode"));
		String price = req.getParameter("price");
		String unit = StringUtil.getEncodePra(req.getParameter("unit"));
		String quantity_storage = req.getParameter("quantity_storage");
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		String dateString = formatter.format(currentTime);
		
		String username = ResourceUtil.getSessionUserName().getRealName();
		
		
		RepertoryEntity repertoryEntity = new RepertoryEntity();
		repertoryEntity.setItemManufacturer(item_manufacturer);
		repertoryEntity.setItemName(item_name);
		repertoryEntity.setManufacturerAddress(manufacturer_address);
		repertoryEntity.setCurrentInventory(Integer.valueOf(inventory));
		repertoryEntity.setPrice(Float.valueOf(price));
		repertoryEntity.setUnit(unit);
		repertoryEntity.setItemStdmode(item_stdmode);
		repertoryEntity.setRegister(username);
		repertoryEntity.setRegistertime(dateString);
		repertoryEntity.setQuantityStorage(Integer.valueOf(quantity_storage));
		
		repertoryService.save(repertoryEntity);
		
		
		Warehousing_detailEntity wd = new Warehousing_detailEntity();
		wd.setIncomingQuantity(Integer.valueOf(inventory));
		wd.setItemManufacturer(repertoryEntity.getItemManufacturer());
		wd.setItemName(repertoryEntity.getItemName());
		wd.setItemStdmode(repertoryEntity.getItemStdmode());
		wd.setManufacturerAddress(repertoryEntity.getManufacturerAddress());
		wd.setPrice(repertoryEntity.getPrice());
		wd.setUnit(repertoryEntity.getUnit());
		wd.setRegister(username);
		wd.setRegistertime(dateString);
		warehousing_detailService.save(wd);
		
		resp.getWriter().write("添加记录成功！");
		/*resp.getWriter().write(result.substring(0, result.length() - 1));*/
	}
	
	
	
	
	
}
	
