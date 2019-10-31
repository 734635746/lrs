<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>车辆信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="carController.do?save">
			<input id="id" name="id" type="hidden" value="${carPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							车辆牌子:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="carname" name="carname" ignore="ignore"
							   value="${carPage.carname}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							车牌号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="carnumber" name="carnumber" ignore="ignore"
							   value="${carPage.carnumber}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							车型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="cartype" typeGroupCode="cartype" hasLabel="false" defaultVal="${carPage.cartype}">
						</t:dictSelect> 
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							买车的时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="buytime" name="buytime" ignore="ignore"
							     value="<fmt:formatDate value='${carPage.buytime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>