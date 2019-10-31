package com.sys.controller.setfree;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.jeecgframework.core.common.controller.BaseController;
import org.jeecgframework.core.common.hibernate.qbc.CriteriaQuery;
import org.jeecgframework.core.common.model.json.DataGrid;
import org.jeecgframework.core.util.ResourceUtil;
import org.jeecgframework.tag.core.easyui.TagUtil;
import org.jeecgframework.web.system.pojo.base.TSUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.sys.entity.morningpforr.MorningpforrEntity;
import com.sys.entity.receipt.ReceiptEntity;
import com.sys.entity.receiptno.ReceiptnoEntity;
import com.sys.entity.setfree.SetFreeEntity;
import com.sys.service.receipt.ReceiptServiceI;
import com.sys.service.receiptno.ReceiptnoServiceI;
import com.sys.service.setfree.SetFreeServiceI;
import com.sys.util.ChineseCurrency;

@Controller
@RequestMapping("/setFreeController")
public class SetFreeController extends BaseController {
	@Autowired
	private SetFreeServiceI setFreeService;
	@Autowired
	private ReceiptnoServiceI receiptnoService;
	@Autowired
	private ReceiptServiceI receiptService;

	@RequestMapping(params = "setFreeList")
	public ModelAndView setFreeList(HttpServletRequest request) {
		return new ModelAndView("com/sys/receipt/setfree/setFreeList");
	}

	@RequestMapping(params = "towardSetFree")
	public ModelAndView towardSetFree(HttpServletRequest req) {
		return new ModelAndView("com/sys/receipt/setfree/setfree");
	}

	@RequestMapping(params = "datagrid")
	public void datagrid(SetFreeEntity setFreeEntity,
			HttpServletRequest request, HttpServletResponse response,
			DataGrid dataGrid) {
		CriteriaQuery cq = new CriteriaQuery(SetFreeEntity.class, dataGrid);
		// 查询条件组装器
		org.jeecgframework.core.extend.hqlsearch.HqlGenerateUtil.installHql(cq,
				setFreeEntity, request.getParameterMap());
		this.setFreeService.getDataGridReturn(cq, true);
		TagUtil.datagrid(response, dataGrid);
	}

	@RequestMapping(params = "saveSetFree")
	public ModelAndView saveSetFree(HttpServletRequest req, Model model) {
		// 添加
		try {
			String paymen = req.getParameter("paymen");
			// 信众
			String prayingobj = req.getParameter("prayingobj");
			// 获取祈福对象
			String livingmember = req.getParameter("livingmenber");

			// 获取摘要
			String summary = req.getParameter("summary");
			// 获取支付方式
			String payway = req.getParameter("payway");
			// 获取摘要
			String money = req.getParameter("money");
			// 获取公历
			String solardates = req.getParameter("solardate");
			// 获取农历
			String lunardates = req.getParameter("lunardate");
			// 获取类型

			Date currentTime = new Date();
			SimpleDateFormat formatter = new SimpleDateFormat("yyyy年MM月dd日");
			String dateString = formatter.format(currentTime);

			SetFreeEntity setFree = new SetFreeEntity();

			setFree.setLivingmenber(livingmember);
			setFree.setMoney(Integer.valueOf(money));
			setFree.setPayway(payway);
			setFree.setSummary(summary);
			setFree.setPrayingobj(prayingobj);
			setFree.setPaymen(paymen);
			setFree.setSolardate(solardates);
			setFree.setLunardate(lunardates);
			setFree.setRegistertime(dateString);
			
			setFreeService.save(setFree);
			//以下保存收据
			ReceiptEntity receipt = new ReceiptEntity();
			receipt.setPaymen(paymen);
			receipt.setMoney(Integer.valueOf(money));
			receipt.setPayway(payway);
			receipt.setSummary(summary);
			receipt.setSize("其他");
			
			TSUser user = ResourceUtil.getSessionUserName();
			receipt.setRegistrant(user.getRealName());
			Calendar a=Calendar.getInstance();
			ReceiptnoEntity reNo = receiptnoService.findByProperty(ReceiptnoEntity.class, "year",Integer.valueOf(a.get(Calendar.YEAR))).get(0);
			int currentMinCount = reNo.getMincount();
			reNo.setMincount(currentMinCount + 1);
			receiptnoService.updateEntitie(reNo);
			
			NumberFormat f=new DecimalFormat("0000000");
			
			String no = f.format(currentMinCount + 1);
			receipt.setNo("No." + no);
			receipt.setRegistertime(dateString);
			receipt.setRitualtype("放生");
			receipt.setPurpose("放生");
			receipt.setRemark("");
			receiptService.save(receipt);
			
			model.addAttribute("message", "放生记录登记成功");
			model.addAttribute("ritualtype", "放生");
			model.addAttribute("returnRe", receipt);
			String bigMoney=ChineseCurrency.toChineseCurrency(new Double(receipt.getMoney()));
			model.addAttribute("bigMoney",bigMoney);
			String smallMoney=ChineseCurrency.toSmall(new Double(receipt.getMoney()));
			model.addAttribute("smallMoney",smallMoney);
			return new ModelAndView("com/sys/success");
		} catch (Exception ex) {
			ex.printStackTrace();
			return new ModelAndView("com/sys/error");
		}

	}

}
