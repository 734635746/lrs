<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>登记房间</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="registrationroomController.do?save">
			<input id="id" name="id" type="hidden" value="${registrationroomPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							登记人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="registrant" name="registrant" ignore="ignore"
							   value="${registrationroomPage.registrant}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							入住人名字:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="intakename" name="intakename" ignore="ignore"
							   value="${registrationroomPage.intakename}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							身份证号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="identification" name="identification" ignore="ignore"
							   value="${registrationroomPage.identification}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="mobilephone" name="mobilephone" ignore="ignore"
							   value="${registrationroomPage.mobilephone}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							入住房间号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="intakennumber" name="intakennumber" ignore="ignore"
							   value="${registrationroomPage.intakennumber}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							需要床位:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="needbeds" name="needbeds" ignore="ignore"
							   value="${registrationroomPage.needbeds}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							入住房间类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="intakentype" name="intakentype" ignore="ignore"
							   value="${registrationroomPage.intakentype}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<input type="hidden" id="roomid" name="roomid" value="${registrationroomPage.roomid}">
				<tr>
					<td align="right">
						<label class="Validform_label">
							入住时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="intakentime" name="intakentime" ignore="ignore"
							     value="<fmt:formatDate value='${registrationroomPage.intakentime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							登记时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="registertime" name="registertime" ignore="ignore"
							     value="<fmt:formatDate value='${registrationroomPage.registertime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							离开时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="leavetime" name="leavetime" ignore="ignore"
							     value="<fmt:formatDate value='${registrationroomPage.leavetime}' type="date" pattern="yyyy-MM-dd HH:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							确认离开时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="confirmdeparturetime" name="confirmdeparturetime" ignore="ignore"
							     value="<fmt:formatDate value='${registrationroomPage.confirmdeparturetime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							离开状态:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="leavestate" typeGroupCode="leavestate" hasLabel="false" defaultVal="${registrationroomPage.leavestate}" type="radio">
						</t:dictSelect> <span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>