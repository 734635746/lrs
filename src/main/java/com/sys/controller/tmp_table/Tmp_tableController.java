package com.sys.controller.tmp_table;
import java.awt.print.Printable;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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

import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.tmp_table.Tmp_tableEntity;
import com.sys.service.funeralheld.FuneralheldServiceI;
import com.sys.service.tmp_table.Tmp_tableServiceI;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**   
 * @Title: Controller
 * @Description: 临时表
 * @author zhangdaihao
 * @date 2016-03-12 20:53:45
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tmp_tableController")
public class Tmp_tableController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Tmp_tableController.class);

	@Autowired
	private Tmp_tableServiceI tmp_tableService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FuneralheldServiceI funeralheldService;
	private String message;
	private int qifuCount = 0;
	private int smallCount = 0;	
	private int bigCount = 2;
	private int nianxiangCount = 1;
	
	private int first;//下载文件首序号
	private int last;//下载文件未序号
	private String downloadPath;//下载路径
	
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}


	/**
	 * 临时表列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tmp_table")
	public ModelAndView tmp_table(HttpServletRequest request) {
		System.out.println("测试tmp_table");
		return new ModelAndView("com/sys/tmp_table/mp_tableList");
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
	public void datagrid(Tmp_tableEntity tmp_table,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Tmp_tableEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tmp_table, request.getParameterMap());
		this.tmp_tableService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除临时表
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Tmp_tableEntity tmp_table, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tmp_table = systemService.getEntity(Tmp_tableEntity.class, tmp_table.getId());
		message = "临时表删除成功";
		tmp_tableService.delete(tmp_table);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加临时表
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(Tmp_tableEntity tmp_table, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tmp_table.getId())) {
			message = "临时表更新成功";
			Tmp_tableEntity t = tmp_tableService.get(Tmp_tableEntity.class, tmp_table.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tmp_table, t);
				tmp_tableService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "临时表更新失败";
			}
		} else {
			message = "临时表添加成功";
			tmp_tableService.save(tmp_table);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 临时表列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(Tmp_tableEntity tmp_table, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tmp_table.getId())) {
			tmp_table = tmp_tableService.getEntity(Tmp_tableEntity.class, tmp_table.getId());
			req.setAttribute("tmp_tablePage", tmp_table);
		}
		return new ModelAndView("com/sys/tmp_table/tmp_table");
	}
/*
	@RequestMapping(params = "genereateTmp")
	public void genereateTmp(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		System.out.println("genereateTmp");
		List<Tmp_tableEntity> cleartmps = tmp_tableService.getList(Tmp_tableEntity.class);
		systemService.deleteAllEntitie(cleartmps);

		String ritualtype = req.getParameter("type");
		String size = req.getParameter("size");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		String flag = req.getParameter("flag");
		String dharmaname= req.getParameter("dharmaname");
		String all = req.getParameter("all");//打印全部标志位
		
		TSUser user = ResourceUtil.getSessionUserName();
		
		Calendar ca = Calendar.getInstance();
		String year = String.valueOf(ca.get(Calendar.YEAR));
		List<Tmp_tableEntity> tmps = new ArrayList<Tmp_tableEntity>();
		List<Object[]> objs = new ArrayList<Object[]>();
		String sql = "";
		if(all.equals("1")){
			if(dharmaname == ""){
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and registertime like '" + year + "%' and cancel=0 order by serial asc";
			}
			else{
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and registrant=\"" + dharmaname + "\" and registertime like '" + year + "%' and cancel=0 order by serial asc";
			}
			
		}
		else{
			if(dharmaname == ""){
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and serial between " + start + " and " + end + " and registertime like '" + year + "%' and cancel=0 order by serial asc";
			}
			else{
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and registrant=\"" + dharmaname + "\" and serial between " + start + " and " + end + " and registertime like '" + year + "%' and cancel=0 order by serial asc";
			}
			
		}
		
		System.out.println(sql);
		objs = systemService.findListbySql(sql);
		
		if(objs.size() != 0){
			for(int i = 0;i < objs.size();i++){
				Tmp_tableEntity te = new Tmp_tableEntity();
				te.setRegistertime(String.valueOf(objs.get(i)[1]));
				te.setRegistrant(String.valueOf(objs.get(i)[2]));
				
				//te.setSerial(String.valueOf(objs.get(i)[16])); //打印手工编号
			    te.setSerial(String.valueOf(objs.get(i)[3]));
				te.setPrayingobj(String.valueOf(objs.get(i)[4]));
				te.setAncestor(String.valueOf(objs.get(i)[5]));
				te.setMoney(Integer.valueOf((String)String.valueOf(objs.get(i)[6])));
				te.setPayway(String.valueOf(objs.get(i)[7]));
				te.setSummary(String.valueOf(objs.get(i)[8]));
				te.setReceiptno(String.valueOf(objs.get(i)[9]));
				te.setAddress(String.valueOf(objs.get(i)[11]));
				if(flag.equals("1")){
					te.setType(0);
					te.setFlag(Integer.valueOf(String.valueOf(objs.get(i)[13])));
					te.setSize(String.valueOf(objs.get(i)[14]));
				}
				else{
					te.setType(Integer.valueOf(String.valueOf(objs.get(i)[13])));
					te.setFlag(Integer.valueOf(String.valueOf(objs.get(i)[14])));
					te.setSize(String.valueOf(objs.get(i)[15]));
				}
				
				tmps.add(te);
				tmp_tableService.save(te);
				
			}
			resp.getWriter().write(((Integer)objs.size()).toString());
		}
		else{

			resp.getWriter().write("找不到适合条件的牌位");
			
		}
	}
*/
	
	
	@RequestMapping(params = "genereateTmp")
	public void genereateTmp(HttpServletRequest req,HttpServletResponse resp) throws IOException {
		System.out.println(this.getClass().getClassLoader().getResource("/").getPath().substring(1, this.getClass().getClassLoader().getResource("/").getPath().length() - 16));
		deleteFile(this.getClass().getClassLoader().getResource("/").getPath().substring(1, this.getClass().getClassLoader().getResource("/").getPath().length() - 16));
		System.out.println("genereateTmp！！！");
		List<Tmp_tableEntity> cleartmps = tmp_tableService.getList(Tmp_tableEntity.class);
		systemService.deleteAllEntitie(cleartmps);

		String ritualtype = req.getParameter("type");
		String size = req.getParameter("size");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		String flag = req.getParameter("flag");
		String dharmaname= req.getParameter("dharmaname");
		String all = req.getParameter("all");//打印全部标志位
		//小牌打印条数
		smallCount = Integer.parseInt(req.getParameter("printcount"));
		qifuCount = Integer.parseInt(req.getParameter("printcount"));
		
		TSUser user = ResourceUtil.getSessionUserName();
		
		Calendar ca = Calendar.getInstance();
		String year = String.valueOf(ca.get(Calendar.YEAR));
		List<Tmp_tableEntity> tmps = new ArrayList<Tmp_tableEntity>();
		List<Object[]> objs = new ArrayList<Object[]>();
		String sql = "";
		
		Date currentTime = new Date();
		SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
		String dateString = formatter.format(currentTime);
		
		Calendar cal = Calendar.getInstance();
        int mon=cal.get(Calendar.MONTH);
        int yearNum=0;
        String begin_date=null;
        
      //释尊成道和弥勒佛法事可能存在跨年，实现逻辑需要另外处理
		if ("buddhabirth".equals(ritualtype)) {//弥勒佛
			yearNum=cal.get(Calendar.YEAR);
			 if(mon<3){//此处为跨年处理，取去年03月01日至今日为区间打印  kooking 20190618
				 yearNum=yearNum-1;
		        }
				begin_date= String.valueOf(yearNum) + "年03月01日";
		}else if ("buddhagaya".equals(ritualtype)) {//释尊成道
			yearNum=cal.get(Calendar.YEAR);
			if(mon<2){//此处为跨年处理，取去年02月01日至今日为区间打印  kooking 20190618
	        	yearNum=yearNum-1;
	        }
			begin_date= String.valueOf(yearNum) + "年02月01日";
		}
        
		if(all.equals("1")){
			if(dharmaname == ""){
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and registertime like '" + year + "%' and cancel=0 order by serial asc";
				
				if (begin_date != null) {//begin_date不为null，则说明是弥勒佛或释尊成道
					sql="select * from " + ritualtype + " where size=\"" + size + "\" and registertime between '" + begin_date +"' AND '"+dateString+ "' and cancel=0 order by serial asc";
				}
				
			}
			else{
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and registrant=\"" + dharmaname + "\" and registertime like '" + year + "%' and cancel=0 order by serial asc";
				
				if (begin_date != null) {//begin_date不为null，则说明是弥勒佛或释尊成道
					sql="select * from " + ritualtype + " where size=\"" + size + "\" and registrant=\"" + dharmaname + "\" and registertime between '" + begin_date +"' AND '"+dateString+ "' and cancel=0 order by serial asc";
				}
			}
			
		}
		else{
			if(dharmaname == ""){
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and serial between " + start + " and " + end + " and registertime like '" + year + "%' and cancel=0 order by serial asc";
				
				if (begin_date != null) {//begin_date不为null，则说明是弥勒佛或释尊成道
					sql="select * from " + ritualtype + " where size=\"" + size + "\" and serial between " + start + " and " + end + " and registertime between '" + begin_date +"' AND '"+dateString+ "' and cancel=0 order by serial asc";
				}
			}
			else{
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and registrant=\"" + dharmaname + "\" and serial between " + start + " and " + end + " and registertime like '" + year + "%' and cancel=0 order by serial asc";
				
				if (begin_date != null) {//begin_date不为null，则说明是弥勒佛或释尊成道
					sql="select * from " + ritualtype + " where size=\"" + size + "\" and registrant=\"" + dharmaname + "\" and serial between " + start + " and " + end + " and registertime between '" + begin_date +"' AND '"+dateString+ "' and cancel=0 order by serial asc";
				}
			}
			
		}
		
		System.out.println(sql);
		objs = systemService.findListbySql(sql);
		
		if(objs.size() != 0){
			for(int i = 0;i < objs.size();i++){
				
				Tmp_tableEntity te = new Tmp_tableEntity();
				te.setRegistertime(String.valueOf(objs.get(i)[1]));
				te.setRegistrant(String.valueOf(objs.get(i)[2]));
				
				//te.setSerial(String.valueOf(objs.get(i)[16])); //打印手工编号
			    te.setSerial(String.valueOf(objs.get(i)[3]));
				te.setPrayingobj(String.valueOf(objs.get(i)[4]));
				te.setAncestor(String.valueOf(objs.get(i)[5]));
				te.setMoney(Integer.valueOf((String)String.valueOf(objs.get(i)[6])));
				te.setPayway(String.valueOf(objs.get(i)[7]));
				te.setSummary(String.valueOf(objs.get(i)[8]));
				te.setReceiptno(String.valueOf(objs.get(i)[9]));
				te.setAddress(String.valueOf(objs.get(i)[11]));
				if(flag.equals("1")){
					te.setType(0);
					te.setFlag(Integer.valueOf(String.valueOf(objs.get(i)[13])));
					te.setSize(String.valueOf(objs.get(i)[14]));
				}
				else{
					te.setType(Integer.valueOf(String.valueOf(objs.get(i)[13])));
					te.setFlag(Integer.valueOf(String.valueOf(objs.get(i)[14])));
					te.setSize(String.valueOf(objs.get(i)[15]));
				}
				tmps.add(te);
//				System.out.println("打印文书");
//				tmp_tableService.save(te);
				
			}
//			String postfix = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(objs.get(1)[3] + ".doc");
			if(flag.equals("1") && tmps.size() != 0){
				Map<String, String> dataMap = null;
				String nodepath = "";
				nodepath = this.getClass().getClassLoader().getResource("/").getPath(); 
				if(size.equals("小")){
					Configuration configuration = new Configuration();
					configuration.setDefaultEncoding("utf-8");
					configuration
							.setServletContextForTemplateLoading(req.getSession().getServletContext(),"/webpage/c_paiweiModel");		
					int tmps_size = tmps.size();
					first = Integer.parseInt(tmps.get(0).getSerial());
					last = Integer.parseInt(tmps.get(tmps_size - 1).getSerial());
					for(int i = 0; i < tmps.size() ; i += qifuCount){
						
						List<Tmp_tableEntity> subList = null;
						
						if(tmps_size - qifuCount >= 0){
							subList = tmps.subList(i, i + qifuCount) ;//每次插入5条记录
							dataMap = getQifuSmallMap(subList, qifuCount);
							tmps_size -= qifuCount;
						}else{//剩余不足5条
							subList = tmps.subList(i, tmps.size()) ;
							dataMap = getQifuSmallMap(subList, tmps.size() % qifuCount);
						}
						

						Template tp = null;
						try {
							tp = configuration.getTemplate("qfxpw_4.ftl");
							tp.setEncoding("utf-8");
						} catch (IOException e) {
							e.printStackTrace();
						}
						
						Random random=new Random();
						
						int ran=random.nextInt(800)+100;
						
						String ranString=String.valueOf(ran);
						String postfix1 = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(objs.get(i)[3]+ranString + ".doc");
						downloadPath = "/webpage/c_gen_wenshu/qfxpw"+ first + "-" + last + ".zip";
//						int a = nodepath.length();
						File outFile1 = new File(nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_paiwei/" + postfix1);						
						Writer out1 = null;
						try {
							out1 = new BufferedWriter(new OutputStreamWriter(
									new FileOutputStream(outFile1), "utf-8"));
						} catch (Exception e1) {
							e1.printStackTrace();
						}
						try {
							tp.process(dataMap, out1);
							out1.close();
						} catch (TemplateException e) {
							e.printStackTrace();
						} catch (IOException e) {
							e.printStackTrace();
						}
					}
				}else if(size.equals("大") || size.equals("拈香")){//祈福大牌
					Configuration configuration = new Configuration();
					configuration.setDefaultEncoding("utf-8");
					configuration
					.setServletContextForTemplateLoading(req.getSession().getServletContext(),"/webpage/c_paiweiModel");	
		
					try {
						int tmps_size = tmps.size();
						first = Integer.parseInt(tmps.get(0).getSerial());
						last = Integer.parseInt(tmps.get(tmps_size - 1).getSerial());
						downloadPath = "/webpage/c_gen_wenshu/qfdpw"+ first + "-" + last + ".zip";
						for(int i = 0; i < tmps.size() ; i+=bigCount){
							String postfixList = "";
							Random random=new Random();
							int ran=random.nextInt(800)+100;
							
							String ranString=String.valueOf(ran);
							String postfixqifu = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(objs.get(i)[3] +ranString+ ".doc");
							postfixList += postfixqifu + ";";
							nodepath = this.getClass().getClassLoader().getResource("/").getPath(); 
							File outFileqifu = new File(nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_paiwei/" + postfixqifu);
							Writer outqifu = null;
							try {
								outqifu = new BufferedWriter(new OutputStreamWriter(
										new FileOutputStream(outFileqifu), "utf-8"));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							List<Tmp_tableEntity> subList = null;
							
							if(tmps_size - bigCount >= 0){
								subList = tmps.subList(i, i + bigCount) ;//每次插入5条记录
								dataMap = getQifuBigMap(subList, bigCount);
								tmps_size -= bigCount;
							}else{//剩余不足5条
								subList = tmps.subList(i, tmps.size()) ;
								dataMap = getQifuBigMap(subList, tmps.size() % bigCount);
							}
							
							Template t = null;
							try {
								t = configuration.getTemplate("qfdpw_21.ftl");
								t.setEncoding("utf-8");
							} catch (IOException e) {
								e.printStackTrace();
							}
							
//							List<Tmp_tableEntity> subList = null;
//							int tmps_size = tmps.size();
//							if(tmps_size - qifuCount >= 0){
//								subList = tmps.subList(i, i + qifuCount) ;//每次插入4条记录
//								tmps_size -= qifuCount;
//							}else{//剩余不足4条
//								subList = tmps.subList(i, tmps_size) ;
//							}
							t.process(dataMap, outqifu);
							outqifu.close();
						}
						
//						do{
//							
//						}while(tmps_size > 0);
						
					} catch (TemplateException e) {
						e.printStackTrace();
					}
				
					
				}/*else{//祈福拈香牌
					Configuration configuration = new Configuration();
					configuration.setDefaultEncoding("utf-8");
					configuration
					.setServletContextForTemplateLoading(req.getSession().getServletContext(),"/webpage/c_paiweiModel");	
		
					
					try {
						for(int i = 0; i < tmps.size() ; i ++){
							
							String postfixList = "";
							String postfixqifu = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(objs.get(i)[3] + ".doc");
							postfixList += postfixqifu + ";";
							nodepath = this.getClass().getClassLoader().getResource("/").getPath(); 
							File outFileqifu = new File(nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_paiwei/" + postfixqifu);
							Writer outqifu = null;
							try {
								outqifu = new BufferedWriter(new OutputStreamWriter(
										new FileOutputStream(outFileqifu), "utf-8"));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							Template t = null;

							try {
								t = configuration.getTemplate("qfnxpw.ftl");
									
							} catch (IOException e) {
								e.printStackTrace();
							}
							t.setEncoding("utf-8");
//							List<Tmp_tableEntity> subList = null;
//							int tmps_size = tmps.size();
//							if(tmps_size - qifuCount >= 0){
//								subList = tmps.subList(i, i + qifuCount) ;//每次插入4条记录
//								tmps_size -= qifuCount;
//							}else{//剩余不足4条
//								subList = tmps.subList(i, tmps_size) ;
//							}
							dataMap = getQifuNianXiangMap(tmps.get(i));
							t.process(dataMap, outqifu);
							outqifu.close();
						}
						
//						do{
//							
//						}while(tmps_size > 0);
						
					} catch (TemplateException e) {
						e.printStackTrace();
					}
				
					
				}*/
				
				generateZipFile(nodepath.substring(1, nodepath.length() - 16));
				resp.getWriter().write(downloadPath);
			
			}
			else if(flag.equals("2")){
				Configuration configuration = new Configuration();
				configuration.setDefaultEncoding("utf-8");
				String nodepath = "";
				configuration
						.setServletContextForTemplateLoading(req.getSession().getServletContext(),"/webpage/c_paiweiModel");
				
				Map<String, String> dataMap = null;
	
				
				String postfixList = "";
				
//				postfixList += postfix + ";";
				nodepath = this.getClass().getClassLoader().getResource("/").getPath(); 
				
				try {
					//超度
					//小牌
					Template t = null;
					if(size.equals("小")){
						int tmps_size = tmps.size();
						first = Integer.parseInt(tmps.get(0).getSerial());
						last = Integer.parseInt(tmps.get(tmps_size - 1).getSerial());
						downloadPath = "/webpage/c_gen_wenshu/cdxpw"+ first + "-" + last + ".zip";
						for(int i = 0; i < tmps.size() ; i += smallCount){
							
							List<Tmp_tableEntity> subList = null;
							
							if(tmps_size - smallCount >= 0){
								subList = tmps.subList(i, i + smallCount) ;//每次插入5条记录
								dataMap = getChaoDuSmallMap(subList, smallCount);
								tmps_size -= smallCount;
							}else{//剩余不足5条
								subList = tmps.subList(i, tmps.size()) ;
								dataMap = getChaoDuSmallMap(subList, tmps.size() % smallCount);
							}
							

							Template tp = null;
							try {
								tp = configuration.getTemplate("cdxpw_4.ftl");
								tp.setEncoding("utf-8");
							} catch (IOException e) {
								e.printStackTrace();
							}
							Random random=new Random();
							
							int ran=random.nextInt(800)+100;
							
							String ranString=String.valueOf(ran);
							
							String postfix1 = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(objs.get(i)[3] +ranString+ ".doc");
							File outFile1 = new File(nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_paiwei/" + postfix1);						
							Writer out1 = null;
							try {
								out1 = new BufferedWriter(new OutputStreamWriter(
										new FileOutputStream(outFile1), "utf-8"));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							try {
								tp.process(dataMap, out1);
								out1.close();
							} catch (TemplateException e) {
								e.printStackTrace();
							} catch (IOException e) {
								e.printStackTrace();
							}
						}
					}else if(size.equals("大") || size.equals("拈香")){//大牌和拈香
						
						Writer out = null;
						
						int tmps_size = tmps.size();
						first = Integer.parseInt(tmps.get(0).getSerial());
						last = Integer.parseInt(tmps.get(tmps_size - 1).getSerial());
						downloadPath = "/webpage/c_gen_wenshu/cddpw"+ first + "-" + last + ".zip";
						for(int i = 0; i < tmps.size() ; i += bigCount){
							Random random=new Random();
							int ran=random.nextInt(800)+100;
							
							String ranString=String.valueOf(ran);
							
						
							String postfixda = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(objs.get(i)[3] + ranString+".doc");
							File outFileda = new File(nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_paiwei/" + postfixda);
							try {
								out = new BufferedWriter(new OutputStreamWriter(
										new FileOutputStream(outFileda), "utf-8"));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							List<Tmp_tableEntity> subList = null;
							
							if(tmps_size - bigCount >= 0){
								subList = tmps.subList(i, i + bigCount) ;//每次插入bigCount条记录
								dataMap = getChaoduBigMap(subList, bigCount);
								tmps_size -= bigCount;
							}else{//剩余不足bigCount条
								subList = tmps.subList(i, tmps.size()) ;
								dataMap = getChaoduBigMap(subList, tmps.size() % bigCount);
							}
				
								
							Template tda = null;
							try {
								tda = configuration.getTemplate("cddpw_2.ftl");
								tda.setEncoding("utf-8");
							} catch (IOException e) {
								e.printStackTrace();
							}
							tda.process(dataMap, out);
							out.close();
						}
					}/*else if(size.equals("拈香")){//拈香
						Writer out = null;
						
						int tmps_size = tmps.size();
						for(int i = 0; i < tmps.size() ; i++){
							String postfixnianxiang = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(objs.get(i)[3] + ".doc");
							File outFilenianxiang = new File(nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_paiwei/" + postfixnianxiang);
							try {
								out = new BufferedWriter(new OutputStreamWriter(
										new FileOutputStream(outFilenianxiang), "utf-8"));
							} catch (Exception e1) {
								e1.printStackTrace();
							}
							
							
							
							dataMap = getChaoDuNianXiangMap(tmps.get(i));
								
							Template tda = null;
							try {
								tda = configuration.getTemplate("cdnxdpw.ftl");
								tda.setEncoding("utf-8");
							} catch (IOException e) {
								e.printStackTrace();
							}
							tda.process(dataMap, out);
							out.close();
							}
					
					}*/
				} catch (TemplateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			
				generateZipFile(nodepath.substring(1, nodepath.length() - 16));
				//resp.getWriter().write("已生成牌位");
				resp.getWriter().write(downloadPath);
			}
//			resp.getWriter().write(((Integer)objs.size()).toString());
		}
		else{

			resp.getWriter().write("找不到适合条件的牌位");
			
		}
		
		
		
	}
	
	
	//超度小牌
	private Map<String, String> getChaoDuSmallMap(List<Tmp_tableEntity> tmps,int count){
		Map<String, String> dataMap = new HashMap<>();
		for(int i = 1; i <= count; i++){
			Tmp_tableEntity tmp = tmps.get(i - 1);
			String[] ancestor = tmp.getAncestor().split(";");
			dataMap.put("s" + i + "0", tmp.getSerial());
//			dataMap.put("s" + i + "4", tmp.getPrayingobj());
			dataMap.put("sys" + i, tmp.getPrayingobj());
			
			
			if(tmp.getType()==1){
				/*for(int j = 1; j <= 3; j++){
					if(j <= ancestor.length){
						dataMap.put("s" + i + j, ancestor[j - 1]);
					}else{
						dataMap.put("s" + i + j, " ");
					}
				}*/
				
				if(ancestor.length == 1){
					
					dataMap.put("s" + i + "2", " ");
					dataMap.put("s" + i + "1", ancestor[0]);
					dataMap.put("s" + i + "3", " ");
					
					dataMap.put("b" + i + "1", " ");
					dataMap.put("b" + i + "2", " ");
				}else if(ancestor.length == 2){
					dataMap.put("s" + i + "1", " ");
					dataMap.put("s" + i + "2", " ");
					dataMap.put("s" + i + "3", " ");
					
					dataMap.put("b" + i + "1", ancestor[0]);
					dataMap.put("b" + i + "2", ancestor[1]);
				}else if(ancestor.length == 3){
					dataMap.put("s" + i + "1", ancestor[0]);
					dataMap.put("s" + i + "2", ancestor[1]);
					dataMap.put("s" + i + "3", ancestor[2]);
					
					dataMap.put("b" + i + "1", " ");
					dataMap.put("b" + i + "2", " ");
				}
				
			}else if(tmp.getType()==2){
				
				dataMap.put("s" + i + "1","歷劫父母師長眷屬");
				dataMap.put("s" + i + "2","十方法界冤親債主");
				dataMap.put("s" + i + "3","有祀無祀孤魂等眾");
				
				dataMap.put("b" + i + "1", " ");
				dataMap.put("b" + i + "2", " ");
				
			}else if(tmp.getType()==3){
				dataMap.put("s" + i + "1", tmp.getAncestor() + "門堂上歷代宗親");
				dataMap.put("s" + i + "2", " ");
				dataMap.put("s" + i + "3", " ");
				
				dataMap.put("b" + i + "1", " ");
				dataMap.put("b" + i + "2", " ");
			}else if(tmp.getType()==4){
				dataMap.put("s" + i + "1", tmp.getAncestor() + "墮胎嬰靈");
				dataMap.put("s" + i + "2", " ");
				dataMap.put("s" + i + "3", " ");
				
				dataMap.put("b" + i + "1", " ");
				dataMap.put("b" + i + "2", " ");
			}
//			dataMap.put("s10", "111");
//			dataMap.put("s11", "111");
//			dataMap.put("s12", "111");
//			dataMap.put("s13", "111");
//			dataMap.put("s14", "111");
//			dataMap.put("s20", "111");
//			dataMap.put("s21", "111");
//			dataMap.put("s22", "111");
//			dataMap.put("s23", "111");
//			dataMap.put("s24", "111");
//			dataMap.put("s30", "111");
//			dataMap.put("s31", "111");
//			dataMap.put("s32", "111");
//			dataMap.put("s33", "111");
//			dataMap.put("s34", "111");
//			dataMap.put("s40", "111");
//			dataMap.put("s41", "111");
//			dataMap.put("s42", "111");
//			dataMap.put("s43", "111");
//			dataMap.put("s44", "111");
//			dataMap.put("s50", "111");
//			dataMap.put("s51", "111");
//			dataMap.put("s52", "111");
//			dataMap.put("s53", "111");
//			dataMap.put("s54", "111");
		}
		if(count < smallCount){
			for(int i = count + 1; i <= smallCount; i++){
				dataMap.put("s" + i + "0", "  ");
				dataMap.put("s" + i + "1", "  ");
				dataMap.put("s" + i + "2", "  ");
				dataMap.put("s" + i + "3", "  ");
				
				dataMap.put("b" + i + "1", " ");
				dataMap.put("b" + i + "2", " ");
				
				dataMap.put("sys" + i, "  ");
			}
		}
		
		return dataMap;
	}
	
	//超度拈香
	private Map<String, String> getChaoDuNianXiangMap(Tmp_tableEntity tmp){
		Map<String, String> dataMap = new HashMap<>();
		
			
			String[] all = tmp.getAncestor().split("#");
			String home = null;
			String[] family = null;
			String type = null;
			if(all.length == 3){
				home = all[0];
				family = all[1].split(";");
				type = all[2];
			}else if(all.length == 2){
				home = "  ";
				family = all[0].split(";");
				type = all[1];
			}
			
			dataMap.put("s0", home);
			
			for(int i = 1;i <= 8;i++){
				
				if(i < family.length){
					dataMap.put("s" + i, family[i-1]);
					
				}else{
					dataMap.put("s" + i, "  ");
				}
			}

			dataMap.put("s9" , " ");
			if(type.equals("1")){
				dataMap.put("s10", "歷劫父母師長眷屬");
				dataMap.put("s11", "十方法界冤親債主");
				dataMap.put("s12", "有祀無祀孤魂等眾");
			}else{
				dataMap.put("s10" , "   ");
				dataMap.put("s11" , "   ");
				dataMap.put("s12" , "   ");
			}
			
			dataMap.put("s19" , tmp.getPrayingobj());
			dataMap.put("s20" , tmp.getSerial());
		
		return dataMap;
	}
	
	//超度大牌插入数据
	private Map<String, String> getChaoduBigMap(List<Tmp_tableEntity> tmps,int count){
		Map<String, String> dataMap = new HashMap<>();
		
		for(int i = 1; i <= count; i++){
			
			Tmp_tableEntity tmp = tmps.get(i - 1);
			String[] all = tmp.getAncestor().split("#");
			String home = null;
			String[] family = null;
			String type = null;
			if(all.length == 3){
				home = all[0];
				family = all[1].split(";");
				type = all[2];
			}else if(all.length == 2){
				home = "  ";
				family = all[0].split(";");
				type = all[1];
			}
			
			dataMap.put("s" + i + "10", home);
			
			for(int j = 1; j <= 6; j++){
				
				if(j <= family.length){
					dataMap.put("s" + i +"1" + j, family[j-1]);
					
				}else{
					dataMap.put("s" + i +"1" + j, "  ");
				}
			}
			
//			dataMap.put("s" + i + "0" , " ");
			
			if(type.equals("1")){
				dataMap.put("s" + i + "17", "十方法界冤親債主");
				dataMap.put("s" + i + "18", "歷劫父母師長眷屬");
				dataMap.put("s" + i + "19", "有祀無祀孤魂等眾");
			}else{
				dataMap.put("s" + i + "17" , family[6]);
				dataMap.put("s" + i + "18" , "   ");
				dataMap.put("s" + i + "19" , family[7]);
			}
			
			dataMap.put("s" + i + "30" , tmp.getPrayingobj());
			dataMap.put("s" + i + "20" , tmp.getSerial());
		}
			
		if(count < bigCount){
			dataMap.put("s210", "  ");
			dataMap.put("s211", "  ");
			dataMap.put("s212", "  ");
			dataMap.put("s213", "  ");
			dataMap.put("s214", "  ");
			dataMap.put("s215", "  ");
			dataMap.put("s216", "  ");
			dataMap.put("s217", "  ");
			dataMap.put("s218", "  ");
			dataMap.put("s219", "  ");
			dataMap.put("s220", "  ");
			dataMap.put("s230", "  ");
		}
			
//			dataMap.put("s1" , family[0]);
//			dataMap.put("s2" , family[1]);
//			dataMap.put("s3" , family[2]);
//			dataMap.put("s4" , family[3]);
//			dataMap.put("s5" , family[4]);
//			dataMap.put("s6" , family[5]);
//			dataMap.put("s7" , family[6]);
//			dataMap.put("s8" , family[7]);
			
		
		return dataMap;
	}
	
	//祈福拈香
	private Map<String, String> getQifuNianXiangMap(Tmp_tableEntity tmp){
		Map<String, String> dataMap = new HashMap<>();
		String[] family = {" "," "," "," "," "," "," "," "};
		family = tmp.getAncestor().split(";");
		
			dataMap.put("s1" , family[0]);
			dataMap.put("s2" , family[1]);
			dataMap.put("s3" , family[2]);
			dataMap.put("s4" , family[3]);
			dataMap.put("s5" , family[4]);
			dataMap.put("s6", family[5]);
			dataMap.put("s7", family[6]);
			dataMap.put("s8", family[7]);
			dataMap.put("s10" , tmp.getSerial());
		
		return dataMap;
	}
	
	//祈福大
	private Map<String, String> getQifuBigMap(List<Tmp_tableEntity> tmps, int count){
		Map<String, String> dataMap = new HashMap<>();
		for(int i = 1; i <= count; i++){
			Tmp_tableEntity tmp = tmps.get(i - 1);
			String[] family ;
			family = tmp.getAncestor().split(";");
			
			for(int k = family.length - 1; k >= 0; k--){
				if(family[k].equals(" ")) 
					family = Arrays.copyOf(family, family.length-1);
				else break;
			}
			
			dataMap.put("s" + i + "0" , tmp.getSerial());
			if(family.length % 2 == 0){
				for(int j = 1; j <=6; j++){
					
					if(j <= family.length){
						dataMap.put("s" + i + j, family[j-1]);
						
					}else{
						dataMap.put("s" + i + j, "");
					}
					if(j < 6) dataMap.put("b" + i + j, "");
				}
			}else{
				for(int j = 1; j <= 5; j++){
					
					if(j <= family.length){
						dataMap.put("b" + i + j, family[j-1]);
						
					}else{
						dataMap.put("b" + i + j, "");
					}
					dataMap.put("s" + i + j, "");
				}
				dataMap.put("s" + i + "6", "");
			}
			
//				dataMap.put("s" + i + "1" , family[0]);
//				dataMap.put("s" + i + "2" , family[1]);
//				dataMap.put("s" + i + "3" , family[2]);
//				dataMap.put("s" + i + "4" , family[3]);
//				dataMap.put("s" + i + "5" , family[4]);
//				dataMap.put("s" + i + "6", family[5]);
//				dataMap.put("s" + i + "7", family[6]);
//				dataMap.put("s" + i + "8", family[7]);
		
		}
		if(count < bigCount){
			dataMap.put("s20", "  ");
			dataMap.put("s21", "  ");
			dataMap.put("s22", "  ");
			dataMap.put("s23", "  ");
			dataMap.put("s24", "  ");
			dataMap.put("s25", "  ");
			dataMap.put("s26", "  ");
//			dataMap.put("s27", "  ");
//			dataMap.put("s28", "  ");
			
			dataMap.put("b20", "  ");
			dataMap.put("b21", "  ");
			dataMap.put("b22", "  ");
			dataMap.put("b23", "  ");
			dataMap.put("b24", "  ");
			dataMap.put("b25", "  ");
//			dataMap.put("b26", "  ");
//			dataMap.put("b27", "  ");
		}
		return dataMap;
	}
	
	private Map<String, String> getQifuSmallMap(List<Tmp_tableEntity> tmps,int count){
		Map<String, String> dataMap = new HashMap<>();
		for(int i = 1; i <= count; i++){
			Tmp_tableEntity tmp = tmps.get(i - 1);
			String[] ancestor = tmp.getAncestor().split(";");		
				dataMap.put("s" + i + "0", tmp.getSerial());
				if(ancestor.length % 2 != 0){//其父对象个数奇数
					for(int j = 1; j <= 5; j++){
						if(j <= ancestor.length){
							dataMap.put("s" + i + j, ancestor[j - 1]);
						}else{
							dataMap.put("s" + i + j, " ");
						}
						if(j < 5) dataMap.put("b" + i + j, " ");
					}
				}else{//祈福对象个数为偶数
					for(int j = 1; j <= 4; j++){
						if(j <= ancestor.length){
							dataMap.put("b" + i + j, ancestor[j - 1]);
						}else{
							dataMap.put("b" + i + j, " ");
						}
						dataMap.put("s" + i + j, " ");
					}
					dataMap.put("s" + i + "5", " ");
				}
				
				/*
				if(ancestor.length == 1){
					dataMap.put("s" + i + "4", " ");
					dataMap.put("s" + i + "2", " ");
					dataMap.put("s" + i + "1", ancestor[0]);
					dataMap.put("s" + i + "3", " ");
					dataMap.put("s" + i + "5", " ");
				}else if(ancestor.length == 2){
					dataMap.put("s" + i + "4", " ");
					dataMap.put("s" + i + "2", ancestor[0]);
					dataMap.put("s" + i + "1", " ");
					dataMap.put("s" + i + "3", ancestor[1]);
					dataMap.put("s" + i + "5", " ");
				}else if(ancestor.length == 3){
					dataMap.put("s" + i + "4", ancestor[0]);
					dataMap.put("s" + i + "2", " ");
					dataMap.put("s" + i + "1", ancestor[1]);
					dataMap.put("s" + i + "3", " ");
					dataMap.put("s" + i + "5", ancestor[2]);
				}else if(ancestor.length == 4){
					dataMap.put("s" + i + "4", ancestor[0]);
					dataMap.put("s" + i + "2", ancestor[1]);
					dataMap.put("s" + i + "1", ancestor[2]);
					dataMap.put("s" + i + "3", ancestor[3]);
					dataMap.put("s" + i + "5", " ");
					
				}else if(ancestor.length == 5){
					dataMap.put("s" + i + "4", ancestor[0]);
					dataMap.put("s" + i + "2", ancestor[1]);
					dataMap.put("s" + i + "1", ancestor[2]);
					dataMap.put("s" + i + "3", ancestor[3]);
					dataMap.put("s" + i + "5", ancestor[4]);
					
				}	*/			

		}
		if(count < qifuCount){
			for(int i = count + 1; i <= qifuCount; i++){
				dataMap.put("s" + i + "0", "  ");
				dataMap.put("s" + i + "1", "  ");
				dataMap.put("s" + i + "2", "  ");
				dataMap.put("s" + i + "3", "  ");
				dataMap.put("s" + i + "4", "  ");
				dataMap.put("s" + i + "5", "  ");
				
				dataMap.put("b" + i + "0", "  ");
				dataMap.put("b" + i + "1", "  ");
				dataMap.put("b" + i + "2", "  ");
				dataMap.put("b" + i + "3", "  ");
				dataMap.put("b" + i + "4", "  ");
			}
		}
		
		return dataMap;
	}
	
	private void generateZipFile(String notepath) throws IOException{
		 String sourceDirStr = notepath + "/webpage/c_gen_paiwei/";  
		 String zipPath = notepath + downloadPath;
		 //  String zipPath = notepath + "/webpage/c_gen_paiwei/paiwei.zip";
		   
		   /**
		    * 
		    * @Modified By xiezhihui
		    * @Midified 2017-7-1 去掉多余的压缩文件
		    */
		   File sourceDirectory = new File(sourceDirStr);  
		   File[] files = sourceDirectory.listFiles();  
	      ZipOutputStream zipOut = new ZipOutputStream(  
	          new BufferedOutputStream(  
	              new FileOutputStream(zipPath), 1024));  
	
	      
	
	      byte[] bs = new byte[1024];    // 缓冲数组  
	      int value = -1;   //文件是否结束标记  
	        
	      for (File file : files) { // 遍历所有的文件  
	          zipOut.putNextEntry(new ZipEntry(file.getName()));   // 存入文件名称，便于解压缩  
	          BufferedInputStream bfInput = new BufferedInputStream(  
	                  new FileInputStream(file), 1024);  
	          while ((value = bfInput.read(bs, 0, bs.length)) != -1) {  
	              zipOut.write(bs, 0, value);  
	          }  
	          bfInput.close(); //关闭缓冲输入流  
	      }  
	      zipOut.flush();  
	      zipOut.close(); 
	}

	private void deleteFile(String notepath){
		   File file = new File(notepath + "/webpage/c_gen_paiwei");
	      File[] files = file.listFiles();
	      System.out.println(files.length);
	      for(int i=0; i<files.length; i++){
	          File tmp = files[i];
	          tmp.delete();
	      }
	}
	

}
