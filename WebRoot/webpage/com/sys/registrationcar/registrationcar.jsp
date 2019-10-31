<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>车辆使用登记表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script>
  	</script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="registrationcarController.do?save">
			<input id="id" name="id" type="hidden" value="${registrationcarPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							车型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="cartype" typeGroupCode="cartype" hasLabel="false" defaultVal="${registrationcarPage.cartype}">
						</t:dictSelect> 
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							车牌号码:
						</label>
					</td>
				<td class="value">
						<t:dictSelect field="platenumber" typeGroupCode="plate" hasLabel="false" defaultVal="${registrationcarPage.platenumber}">
						</t:dictSelect> 
						<span class="Validform_checktip"></span>
					</td>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="carstatus" name="carstatus" ignore="ignore"
							   value="${registrationcarPage.carstatus}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用车人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="usingcarpeople" name="usingcarpeople" ignore="ignore"
							   value="${registrationcarPage.usingcarpeople}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							用车时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="usingtime" name="usingtime" ignore="ignore"
							     value="<fmt:formatDate value='${registrationcarPage.usingtime}' type="date" pattern="yyyy-MM-dd"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							前往地点:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="toplace" name="toplace" ignore="ignore"
							   value="${registrationcarPage.toplace}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							返回时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="returntime" name="returntime" ignore="ignore"
							     value="<fmt:formatDate value='${registrationcarPage.returntime}' type="date" pattern="yyyy-MM-dd"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							备注:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="remark" name="remark" ignore="ignore"
							   value="${registrationcarPage.remark}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>