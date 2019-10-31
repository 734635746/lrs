<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>客房基本信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="roomController.do?save">
			<input id="id" name="id" type="hidden" value="${roomPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							房间号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="roomNumber" name="roomNumber" ignore="ignore"
							   value="${roomPage.roomNumber}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							房间类型:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="roomKind" typeGroupCode="roomtype" hasLabel="false" defaultVal="${roomPage.roomKind}"></t:dictSelect>
					 	<span class="Validform_checktip"></span>
					 </td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							床位:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="beds" name="beds" ignore="ignore"
							   value="${roomPage.beds}">
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
							   value="${roomPage.remark}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>