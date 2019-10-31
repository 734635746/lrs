<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>法事举行日期安排</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="funeralheldController.do?save">
			<input id="id" name="id" type="hidden" value="${funeralheldPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							法事类型:
						</label>
					</td>
					<td class="value">
					<t:dictSelect field="ritualtype" typeGroupCode="ritualtype" hasLabel="false" defaultVal="${funeralheldPage.ritualtype}"></t:dictSelect>
					 <span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							举行年份:
						</label>
					</td>
					<td class="value">
					<t:dictSelect field="holdYear" typeGroupCode="holdYear" hasLabel="false" defaultVal="${funeralheldPage.holdYear}"></t:dictSelect>
					 <span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							农历日期:
						</label>
					</td>
					<td class="value">
						<select style="width:80px" name="month" id="month">
							    <option value="正月">正月</option>
								<option value="二月">二月</option>
								<option value="三月">三月</option>
								<option value="四月">四月</option>
								<option value="五月">五月</option>
								<option value="六月">六月</option>
								<option value="七月">七月</option>
								<option value="八月">八月</option>
								<option value="九月">九月</option>
								<option value="十月">十月</option>
								<option value="十一月">十一月</option>
								<option value="腊月">腊月</option>
						</select>
						<select style="width:80px" name="day" id="day">
							    <option value="初一">初一</option>
							    <option value="初二">初二</option>
							    <option value="初三">初三</option>
							    <option value="初四">初四</option>
							    <option value="初五">初五</option>
							    <option value="初六">初六</option>
							    <option value="初七">初七</option>
							    <option value="初八">初八</option>
							    <option value="初九">初九</option>
							    <option value="初十">初十</option>
							    <option value="十一">十一</option>
							    <option value="十二">十二</option>
							    <option value="十三">十三</option>
							    <option value="十四">十四</option>
							    <option value="十五">十五</option>
							    <option value="十六">十六</option>
							    <option value="十七">十七</option>
							    <option value="十八">十八</option>
							    <option value="十九">十九</option>
							    <option value="廿">廿十</option>
							    <option value="廿一">廿一</option>
							    <option value="廿二">廿二</option>
							    <option value="廿三">廿三</option>
							    <option value="廿四">廿四"</option>
							    <option value="廿五">廿五</option>
							    <option value="廿六">廿六</option>
							    <option value="廿七">廿七</option>
							    <option value="廿八">廿八</option>
							    <option value="廿九">廿九</option>
							    <option value="卅">卅十</option>
								
						</select>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							持续天数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="continuouDays" name="continuouDays" ignore="ignore"
							   value="${funeralheldPage.continuouDays}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							农历日期:
						</label>
					</td>
					<td class="value">
						<select style="width:80px" name="endmonth" id="endmonth">
							    <option value="正月">正月</option>
								<option value="二月">二月</option>
								<option value="三月">三月</option>
								<option value="四月">四月</option>
								<option value="五月">五月</option>
								<option value="六月">六月</option>
								<option value="七月">七月</option>
								<option value="八月">八月</option>
								<option value="九月">九月</option>
								<option value="十月">十月</option>
								<option value="十一月">十一月</option>
								<option value="腊月">腊月</option>
						</select>
						<select style="width:80px" name="endday" id="endday">
							    <option value="初一">初一</option>
							    <option value="初二">初二</option>
							    <option value="初三">初三</option>
							    <option value="初四">初四</option>
							    <option value="初五">初五</option>
							    <option value="初六">初六</option>
							    <option value="初七">初七</option>
							    <option value="初八">初八</option>
							    <option value="初九">初九</option>
							    <option value="初十">初十</option>
							    <option value="十一">十一</option>
							    <option value="十二">十二</option>
							    <option value="十三">十三</option>
							    <option value="十四">十四</option>
							    <option value="十五">十五</option>
							    <option value="十六">十六</option>
							    <option value="十七">十七</option>
							    <option value="十八">十八</option>
							    <option value="十九">十九</option>
							    <option value="廿">廿十</option>
							    <option value="廿一">廿一</option>
							    <option value="廿二">廿二</option>
							    <option value="廿三">廿三</option>
							    <option value="廿四">廿四"</option>
							    <option value="廿五">廿五</option>
							    <option value="廿六">廿六</option>
							    <option value="廿七">廿七</option>
							    <option value="廿八">廿八</option>
							    <option value="廿九">廿九</option>
							    <option value="卅">卅十</option>
								
						</select>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>