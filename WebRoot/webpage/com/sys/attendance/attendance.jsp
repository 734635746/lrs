<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>考勤管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="attendanceController.do?save">
			<input id="id" name="id" type="hidden" value="${attendancePage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							农历日期:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="lunardate" name="lunardate" ignore="ignore"
							   value="${attendancePage.lunardate}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							公历日期:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="solardate" name="solardate" ignore="ignore"
							   value="${attendancePage.solardate}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="memberName" name="memberName" ignore="ignore"
							   value="${attendancePage.memberName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							工号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="memberId" name="memberId" ignore="ignore"
							   value="${attendancePage.memberId}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							请假原因:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="reason" name="reason" ignore="ignore"
							   value="${attendancePage.reason}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							考勤类型:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="attendanceType" name="attendanceType" ignore="ignore"
							   value="${attendancePage.attendanceType}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>