package com.sys.controller.tmp_wenshu;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import test.TabletStat;

import com.sys.entity.funeralheld.FuneralheldEntity;
import com.sys.entity.pravrajanamember.PravrajanamemberEntity;
import com.sys.entity.tmp_wenshu.Tmp_wenshuEntity;
import com.sys.service.funeralheld.FuneralheldServiceI;
import com.sys.service.pravrajanamember.PravrajanamemberServiceI;
import com.sys.service.tmp_wenshu.Tmp_wenshuServiceI;

import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

/**   
 * @Title: Controller
 * @Description: 文疏打印
 * @author zhangdaihao
 * @date 2017-04-24 10:12:04
 * @version V1.0   
 *
 */
@Controller
@RequestMapping("/tmp_wenshuController")
public class Tmp_wenshuController extends BaseController {
	/**
	 * Logger for this class
	 */
	private static final Logger logger = Logger.getLogger(Tmp_wenshuController.class);

	@Autowired
	private Tmp_wenshuServiceI tmp_wenshuService;
	@Autowired
	private PravrajanamemberServiceI pravrajanamemberService;
	@Autowired
	private SystemService systemService;
	@Autowired
	private FuneralheldServiceI funeralheldService;
	private String message;
	
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
	 * 文疏打印列表 页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "tmp_wenshu")
	public ModelAndView tmp_wenshu(HttpServletRequest request) {
		return new ModelAndView("com/sys/tmp_wenshu/tmp_wenshuList");
	}
	
	@RequestMapping(params = "forwardIndex")
	public ModelAndView forwardIndex(HttpServletRequest request) {
		return new ModelAndView("com/sys/wenshu/ritualindex");
	}
	
	@RequestMapping(params = "fowardSelectRitualtype")
	public ModelAndView fowardSelectRitualtype(HttpServletRequest req) {
		return new ModelAndView("com/sys/wenshu/selectRitualtype");
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
	public void datagrid(Tmp_wenshuEntity tmp_wenshu,HttpServletRequest request, HttpServletResponse response, DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(Tmp_wenshuEntity.class, dataGrid);
		//查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq, tmp_wenshu, request.getParameterMap());
		this.tmp_wenshuService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	/**
	 * 删除文疏打印
	 * 
	 * @return
	 */
	@RequestMapping(params = "del")
	@ResponseBody
	public AjaxJson del(Tmp_wenshuEntity tmp_wenshu, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		tmp_wenshu = systemService.getEntity(Tmp_wenshuEntity.class, tmp_wenshu.getId());
		message = "文疏打印删除成功";
		tmp_wenshuService.delete(tmp_wenshu);
		systemService.addLog(message, Globals.Log_Type_DEL, Globals.Log_Leavel_INFO);
		
		j.setMsg(message);
		return j;
	}


	/**
	 * 添加文疏打印
	 * 
	 * @param ids
	 * @return
	 */
	@RequestMapping(params = "save")
	@ResponseBody
	public AjaxJson save(Tmp_wenshuEntity tmp_wenshu, HttpServletRequest request) {
		AjaxJson j = new AjaxJson();
		if (StringUtil.isNotEmpty(tmp_wenshu.getId())) {
			message = "文疏打印更新成功";
			Tmp_wenshuEntity t = tmp_wenshuService.get(Tmp_wenshuEntity.class, tmp_wenshu.getId());
			try {
				MyBeanUtils.copyBeanNotNull2Bean(tmp_wenshu, t);
				tmp_wenshuService.saveOrUpdate(t);
				systemService.addLog(message, Globals.Log_Type_UPDATE, Globals.Log_Leavel_INFO);
			} catch (Exception e) {
				e.printStackTrace();
				message = "文疏打印更新失败";
			}
		} else {
			message = "文疏打印添加成功";
			tmp_wenshuService.save(tmp_wenshu);
			systemService.addLog(message, Globals.Log_Type_INSERT, Globals.Log_Leavel_INFO);
		}
		j.setMsg(message);
		return j;
	}

	/**
	 * 文疏打印列表页面跳转
	 * 
	 * @return
	 */
	@RequestMapping(params = "addorupdate")
	public ModelAndView addorupdate(Tmp_wenshuEntity tmp_wenshu, HttpServletRequest req) {
		if (StringUtil.isNotEmpty(tmp_wenshu.getId())) {
			tmp_wenshu = tmp_wenshuService.getEntity(Tmp_wenshuEntity.class, tmp_wenshu.getId());
			req.setAttribute("tmp_wenshuPage", tmp_wenshu);
		}
		return new ModelAndView("com/sys/tmp_wenshu/tmp_wenshu");
	}
	
	@RequestMapping(params = "tabletcount")
	public ModelAndView tabletcount(HttpServletRequest req) throws UnsupportedEncodingException {
		String type = req.getParameter("type");
		String name = req.getParameter("name");
		String flag = req.getParameter("flag");	
		String inputer   = new String(name.getBytes("ISO-8859-1") , "UTF-8"); 
		List<TabletStat> tablet = new ArrayList<TabletStat>();
		
		Calendar ca = Calendar.getInstance();
		String year = String.valueOf(ca.get(Calendar.YEAR));
		if(type != null){
			
			String sqlMiddleNotPrint = "select count(*) from "+type + " where flag=0 and size='大' and registertime like '" + year + "%'";
			String sqlMiddleAlreadyPrint = "select count(*) from "+type + " where flag=1 and size='大' and registertime like '" + year + "%'" ;
			
			String sqlBigNotPrint = "select count(*) from "+type + " where flag=0 and size='拈香' and registertime like '" + year + "%'";
			String sqlBigAlreadyPrint = "select count(*) from "+type + " where flag=1 and size='拈香' and registertime like '" + year + "%'";
			
			Long countMiddleNotPrint = systemService.getCountForJdbc(sqlMiddleNotPrint);
			Long countMiddleAlreadyPrint = systemService.getCountForJdbc(sqlMiddleAlreadyPrint);
			
			Long countBigNotPrint = systemService.getCountForJdbc(sqlBigNotPrint);
			Long countBigAlreadyPrint = systemService.getCountForJdbc(sqlBigAlreadyPrint);
			
			TabletStat tsMiddle = new TabletStat();
			tsMiddle.setRitualtype(inputer);
			tsMiddle.setSize("大");
			tsMiddle.setNotprint(countMiddleNotPrint);
			tsMiddle.setAlreadyprint(countMiddleAlreadyPrint);
			tablet.add(tsMiddle);
			
			TabletStat tsBig = new TabletStat();
			tsBig.setRitualtype(inputer);
			tsBig.setSize("拈香");
			tsBig.setNotprint(countBigNotPrint);
			tsBig.setAlreadyprint(countBigAlreadyPrint);
			tablet.add(tsBig);
			
		}
		req.setAttribute("name", inputer);
		req.setAttribute("tablet", tablet);
		req.setAttribute("flag", flag);
		req.setAttribute("type", type);
		return new ModelAndView("com/sys/wenshu/tabletcount");
	}
	
	@RequestMapping(params = "print")
	public ModelAndView print(HttpServletRequest req) throws UnsupportedEncodingException {
		String inputer = req.getParameter("name");
		String flag = req.getParameter("flag");
		String type = req.getParameter("type");
		String name   = new String(inputer.getBytes("ISO-8859-1") , "UTF-8");
		List<PravrajanamemberEntity> pravrajanamemberEntityList = pravrajanamemberService.getList(PravrajanamemberEntity.class);
		req.setAttribute("pravrajanamemberEntityList", pravrajanamemberEntityList);
		req.setAttribute("name", name);
		req.setAttribute("type", type);
		req.setAttribute("flag", flag);
		if(flag.equals("1")){
			return new ModelAndView("com/sys/wenshu/print");
		}
		else{
			return new ModelAndView("com/sys/wenshu/printRelease");
		}
		
	}
	
	
	
	@RequestMapping(params = "printWenshu")
	public void genereateTmp(HttpServletRequest req,HttpServletResponse resp, Model model) throws IOException {
		deleteFile(this.getClass().getClassLoader().getResource("/").getPath().substring(1, this.getClass().getClassLoader().getResource("/").getPath().length() - 16));
		Calendar ca1 = Calendar.getInstance();
		String year = String.valueOf(ca1.get(Calendar.YEAR));

		String ritualtype = req.getParameter("type");
		String size = req.getParameter("size");
		String start = req.getParameter("start");
		String end = req.getParameter("end");
		String flag = req.getParameter("flag");
		String dharmaname= req.getParameter("dharmaname");
		String all = req.getParameter("all");//打印全部标志位
		Map<String,String> ritualMap = init_map();
		TSUser user = ResourceUtil.getSessionUserName();
		
		
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
      		if(mon<3){
      		      yearNum=cal.get(Calendar.YEAR)-1;
      		 }
      		begin_date= String.valueOf(yearNum) + "年03月01日";
      	}else if ("buddhagaya".equals(ritualtype)) {//释尊成道
      		if(mon<2){
      	        yearNum=cal.get(Calendar.YEAR)-1;
      	       }
      		begin_date= String.valueOf(yearNum) + "年02月01日";
      	}
		
  // System.out.println(begin_date);   	
		if(all.equals("1")){
	//		System.out.println("====================1=========================");
			if(dharmaname == ""){
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and cancel=0 and registertime like '" + year + "%' order by serial asc";
				
				if (begin_date != null) {//begin_date不为null，则说明是弥勒佛或释尊成道
		//			System.out.println("====================2=========================");
					sql="select * from " + ritualtype + " where size=\"" + size + "\" and registertime between '" + begin_date +"' AND '"+dateString+ "' and cancel=0 order by serial asc";
				}
				
			}
			else{
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and registrant=\"" + dharmaname + "\" and cancel=0 and registertime like '" + year + "%' order by serial asc";
				if (begin_date != null) {//begin_date不为null，则说明是弥勒佛或释尊成道
					sql="select * from " + ritualtype + " where size=\"" + size + "\" and registrant=\"" + dharmaname + "\" and registertime between '" + begin_date +"' AND '"+dateString+ "' and cancel=0 order by serial asc";
				}
			}
			
		}
		else{
			//System.out.println("====================3=========================");
			if(dharmaname == ""){
			//	System.out.println("====================4=========================");
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and serial between " + start + " and " + end + " and registertime like '" + year + "%' and cancel=0 order by serial asc";
			
				if (begin_date != null) {//begin_date不为null，则说明是弥勒佛或释尊成道
			   
					sql = "select * from " + ritualtype + " where size=\"" + size + "\" and serial between " + start + " and " + end + " and registertime between '" + begin_date +"' AND '"+dateString+ "' and cancel=0 order by serial asc";
						
				}
			
			}
			else{
				sql = "select * from " + ritualtype + " where size=\"" + size + "\" and registrant=\"" + dharmaname + "\" and serial between " + start + " and " + end + " and cancel=0 and registertime like '" + year + "%' order by serial asc";
			
				if (begin_date != null) {//begin_date不为null，则说明是弥勒佛或释尊成道
					   
					sql = "select * from " + ritualtype + " where size=\"" + size + "\" and registrant=\"" + dharmaname + "\" and serial between " + start + " and " + end + " and registertime between '" + begin_date +"' AND '"+dateString+ "' and cancel=0 order by serial asc";
						
				}
			
			}
			
		}
		
		String begindate = "";
		String enddate = "";
		List<FuneralheldEntity> feList = funeralheldService.findByProperty(FuneralheldEntity.class, "ritualtype", ritualMap.get(ritualtype));
		for(FuneralheldEntity fe : feList){
			if(fe.getHoldYear().equals(year)){
				begindate = fe.getHoldDate();
				enddate = fe.getEndDate();
				break;
			}
		}
		System.out.println(sql);
		Calendar ca = Calendar.getInstance();
		objs = systemService.findListbySql(sql);
		
		String postfixList = "";
		if (flag.equals("1") && objs.size() != 0) {
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
			String nodepath = "";
			configuration
					.setServletContextForTemplateLoading(req.getSession().getServletContext(),"/webpage/cli_wenshu_model");
			for (int i = 0; i < objs.size(); i++) {
				String[] name = String
						.valueOf(objs.get(i)[5])
						.substring(0,
								String.valueOf(objs.get(i)[5]).length() - 1)
						.split(";");
				Map dataMap = new HashMap();
				
				dataMap.put("address", String.valueOf(objs.get(i)[11]));
				
				dataMap.put("name1", " ");
				dataMap.put("name2", " ");
				dataMap.put("name3", " ");
				dataMap.put("name4", " ");
				String tmp = "";
				int k = 1;
				for(int j = 0; j < name.length; j++){//8->name.length
					
					if(name[j].length() + tmp.length() <= 29){
						
						if(j == name.length-1 && tmp != null){//7->name.length-1
							tmp = tmp + name[j]  + "  奉";
							dataMap.put("name" + k++ , tmp);
						}else if(!name[j].equals(" ")){
							tmp = tmp + name[j] + " ";
						}
//						System.out.println(tmp);
					}else{
						dataMap.put("name" + k++ , tmp);
						j--;
						tmp = "";
					}
				}
				/*dataMap.put("name1", name[0]);
				dataMap.put("name2", name[1]);
				dataMap.put("name3", name[2]);
				dataMap.put("name4", name[3]);
				dataMap.put("name5", name[4]);
				dataMap.put("name6", name[5]);
				dataMap.put("name7", name[6]);
				dataMap.put("name8", name[7]);*/
				if(begindate.equals(enddate)){//当日期只有一天时，取消“至”字
					dataMap.put("lunardate2", begindate.replace(" ", ""));
					dataMap.put("book", String.valueOf(objs.get(i)[18]));
					dataMap.put("lunardate1","己亥年　" + begindate.replace(" ", "") );
				}else{
					dataMap.put("lunardate2", begindate.replace(" ", "") + "至" + enddate.replace(" ", ""));
					dataMap.put("book", String.valueOf(objs.get(i)[18]));
					dataMap.put("lunardate1", "己亥年　" + begindate.replace(" ", "") + "至" + enddate.replace(" ", ""));
				}
				Template t = null;
				try {
					t = configuration.getTemplate("wenshuModel_1.ftl");
					t.setEncoding("utf-8");
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				Random random=new Random();
				
				int ran=random.nextInt(800)+100;
				
				String ranString=String.valueOf(ran);
				
				String postfix = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(objs.get(i)[3] +ranString+ ".doc");
				dataMap.put("serial",String.valueOf(objs.get(i)[3]));
				if(i == 0) first = Integer.parseInt(String.valueOf(objs.get(0)[3]));
				int length = objs.size() - 1;
				if(i == length) last = Integer.parseInt(String.valueOf(objs.get(length)[3]));
				
				postfixList += postfix + ";";
				nodepath = this.getClass().getClassLoader().getResource("/").getPath(); 
				File outFile = new File(nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_wenshu/" + postfix);
				Writer out = null;
				try {
					out = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(outFile), "utf-8"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					t.process(dataMap, out);
					
					out.close();
				} catch (TemplateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			downloadPath = "/webpage/c_gen_wenshu/qfws"+ first + "-" + last + ".zip";
			generateZipFile(nodepath.substring(1, nodepath.length() - 16));
			resp.getWriter().write(downloadPath);
			
		} 
		else if(flag.equals("2") && objs.size() != 0){
			Configuration configuration = new Configuration();
			configuration.setDefaultEncoding("utf-8");
			String nodepath = "";
			configuration
					.setServletContextForTemplateLoading(req.getSession().getServletContext(),"/webpage/c_rea_wenshu_model");
			for (int i = 0; i < objs.size(); i++) {
				Map dataMap = new HashMap();
				dataMap.put("address", String.valueOf(objs.get(i)[11]));
				dataMap.put("lunardate1", begindate.replace(" ", "") + "至" + enddate.replace(" ", ""));
				dataMap.put("book", String.valueOf(objs.get(i)[19]));
				dataMap.put("lunardate2", "己亥年　" + begindate.replace(" ", "") + "至" + enddate.replace(" ", ""));
				dataMap.put("register",String.valueOf(objs.get(i)[2]));
				if(String.valueOf(objs.get(i)[13]).equals("6")){
					String[] name = String.valueOf(objs.get(i)[5]).split("#");
					String[] nameArr = name[0].substring(0,name[0].length() - 1).split(";");
					dataMap.put("na1", "");
					/*dataMap.put("prayingobj1", "");
				    dataMap.put("prayingobj2", "");*/
					String prayingobj = String.valueOf(objs.get(i)[4]);
					
				    dataMap.put("prayingobj3", prayingobj.replace(';', ' '));
					if(name[1].equals("1")){
						dataMap.put("name1", nameArr[0]);
						dataMap.put("name2", nameArr[1]);
						dataMap.put("name3", nameArr[2]);
						dataMap.put("name4", nameArr[3]);
						dataMap.put("name5", nameArr[4]);
						dataMap.put("name6", nameArr[5]);
						dataMap.put("name7", "");
						dataMap.put("name8", "");
						dataMap.put("wenzi1", "歷劫父母師長眷屬");
					    dataMap.put("wenzi2", "有祀無祀孤魂等眾");
					    dataMap.put("wenzi3", "十方法界冤親債主");
					}
					else{
						dataMap.put("name1", nameArr[0]);
						dataMap.put("name2", nameArr[1]);
						dataMap.put("name3", nameArr[2]);
						dataMap.put("name4", nameArr[3]);
						dataMap.put("name5", nameArr[4]);
						dataMap.put("name6", nameArr[5]);
						dataMap.put("name7", nameArr[6]);
						dataMap.put("name8", nameArr[7]);
						dataMap.put("wenzi1", "");
					    dataMap.put("wenzi2", "");
					    dataMap.put("wenzi3", "");
					}
					
				}
				if(String.valueOf(objs.get(i)[13]).equals("5")){
					String[] name = String.valueOf(objs.get(i)[5]).split("#");
					String[] nameArr = name[1].substring(0,name[1].length() - 1).split(";");
					dataMap.put("na1", name[0]);
					/*dataMap.put("prayingobj1", "");
				    dataMap.put("prayingobj2", "");*/
					String prayingobj = String.valueOf(objs.get(i)[4]);
				   dataMap.put("prayingobj3", prayingobj.replace(';', ' '));
					if(name[2].equals("1")){
						dataMap.put("name1", nameArr[0]);
						dataMap.put("name2", nameArr[1]);
						dataMap.put("name3", nameArr[2]);
						dataMap.put("name4", nameArr[3]);
						dataMap.put("name5", nameArr[4]);
						dataMap.put("name6", nameArr[5]);
						dataMap.put("name7", "");
						dataMap.put("name8", "");
						dataMap.put("wenzi1", "歷劫父母師長眷屬");
					    dataMap.put("wenzi2", "有祀無祀孤魂等眾");
					    dataMap.put("wenzi3", "十方法界冤親債主");
					}
					else{
						dataMap.put("name1", nameArr[0]);
						dataMap.put("name2", nameArr[1]);
						dataMap.put("name3", nameArr[2]);
						dataMap.put("name4", nameArr[3]);
						dataMap.put("name5", nameArr[4]);
						dataMap.put("name6", nameArr[5]);
						dataMap.put("name7", nameArr[6]);
						dataMap.put("name8", nameArr[7]);
						dataMap.put("wenzi1", "");
					    dataMap.put("wenzi2", "");
					    dataMap.put("wenzi3", "");
					}
				}
				
				Template t = null;
				try {
					t = configuration.getTemplate("wenshuRealeaseModel_1.ftl");
					t.setEncoding("utf-8");
				} catch (IOException e) {
					e.printStackTrace();
				}
				Random random=new Random();
				
				int ran=random.nextInt(800)+100;
				
				String ranString=String.valueOf(ran);
				
				
				String postfix = String.valueOf(ca.get(Calendar.YEAR)) + String.valueOf(objs.get(i)[3] +ranString+ ".doc");
				postfixList += postfix + ";";
				nodepath = this.getClass().getClassLoader().getResource("/").getPath(); 
				
				File outFile = new File(nodepath.substring(1, nodepath.length() - 16) + "/webpage/c_gen_wenshu/" + postfix);
				
				dataMap.put("serial",String.valueOf(objs.get(i)[3]));
				if(i == 0) first = Integer.parseInt(String.valueOf(objs.get(0)[3]));
				int length = objs.size() - 1;
				if(i == length) last = Integer.parseInt(String.valueOf(objs.get(length)[3]));
				
				Writer out = null;
				try {
					out = new BufferedWriter(new OutputStreamWriter(
							new FileOutputStream(outFile), "utf-8"));
				} catch (Exception e1) {
					e1.printStackTrace();
				}
				try {
					t.process(dataMap, out);
					out.close();
				} catch (TemplateException e) {
					e.printStackTrace();
				} catch (IOException e) {
					e.printStackTrace();
				}
				
			}
			downloadPath = "/webpage/c_gen_wenshu/cdws"+ first + "-" + last + ".zip";
			generateZipFile(nodepath.substring(1, nodepath.length() - 16));
			resp.getWriter().write(downloadPath);
			
		}
		else{
			resp.getWriter().write("找不到适合条件的牌位");

		}
		
	}
	
	private Map<String,String> init_map(){
		Map<String,String> ritualMap = new HashMap<String,String>();
		ritualMap.put("pharmacistbirth", "药师诞");
		ritualMap.put("buddhabirth", "弥勒佛诞");
		ritualMap.put("guanyinopen", "观音开库");
		ritualMap.put("goddessbirth", "观音诞");
		ritualMap.put("honoredbirth", "释尊诞");
		ritualMap.put("guanyingaya", "观音成道");
		ritualMap.put("guanyinmonk", "观音出家");
		ritualMap.put("buddhagaya", "释尊成道");
		ritualMap.put("tombsweepfes", "清明节");
		ritualMap.put("jizobirth", "地藏诞");
		ritualMap.put("ghostfes", "盂兰节");
		ritualMap.put("amitabhabirth", "弥陀诞");
		return ritualMap;
		
	}
	
	private void generateZipFile(String notepath) throws IOException{
		 String sourceDirStr = notepath + "/webpage/c_gen_wenshu/";  
		 
		   String zipPath = notepath + downloadPath;
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
		   File file = new File(notepath + "/webpage/c_gen_wenshu");
	       File[] files = file.listFiles();
	       System.out.println(files.length);
	       for(int i=0; i<files.length; i++){
	           File tmp = files[i];
	           tmp.delete();
	       }
	   }
	
}
