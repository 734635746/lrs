<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>迁入迁出记录</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="movein_moveoutController.do?save">
			<input id="id" name="id" type="hidden" value="${movein_moveoutPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<c:if test="${flag == 'in'}">
				<tr>
					<td align="right">
						<label class="Validform_label">
							迁入时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="moveintime" name="moveintime" ignore="ignore"
							     value="<fmt:formatDate value='${movein_moveoutPage.moveintime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				</c:if>
				<c:if test="${flag == 'out'}">
				<tr>
					<td align="right">
						<label class="Validform_label">
							迁出时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="moveouttime" name="moveouttime" ignore="ignore"
							     value="<fmt:formatDate value='${movein_moveoutPage.moveouttime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				</c:if>
				
				<tr>
					<td align="right">
						<label class="Validform_label">
							皈依弟子姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="disciplename" name="disciplename" ignore="ignore"
							   value="${movein_moveoutPage.disciplename}">
							   
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							迁入迁出原因:
						</label>
					</td>
					<td class="value">
						<textarea rows="5" cols="70" id="reason" name="reason" autofoucus>${movein_moveoutPage.reason}</textarea>
					</td>
				</tr>
			</table>
			<input type="hidden" value="${movein_moveoutPage.discipleid}" name = "discipleid">
		</t:formvalid>
 </body>