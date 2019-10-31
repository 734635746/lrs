<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>牌位信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="m_infoController.do?save">
			<input id="id" name="id" type="hidden" value="${m_infoPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							堂口:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="buildingName" name="buildingName" ignore="ignore"
							   value="${m_infoPage.buildingName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							段名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="danName" name="danName" ignore="ignore"
							   value="${m_infoPage.danName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							行数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="Row" name="Row" ignore="ignore"
							   value="${m_infoPage.Row}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							列数:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="Column" name="Column" ignore="ignore"
							   value="${m_infoPage.Column}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>