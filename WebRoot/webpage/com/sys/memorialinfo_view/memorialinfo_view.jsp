<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>牌位视图</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="memorialinfo_viewController.do?save">
			<input id="id" name="id" type="hidden" value="${memorialinfo_viewPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="serial" name="serial" ignore="ignore"
							   value="${memorialinfo_viewPage.serial}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							注册时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="registertime" name="registertime" ignore="ignore"
							     value="<fmt:formatDate value='${memorialinfo_viewPage.registertime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							段位:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="dan" name="dan" ignore="ignore"
							   value="${memorialinfo_viewPage.dan}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							行号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="row" name="row" ignore="ignore"
							   value="${memorialinfo_viewPage.row}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							位号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="ordernumber" name="ordernumber" ignore="ignore"
							   value="${memorialinfo_viewPage.ordernumber}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							大小:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="size" name="size" ignore="ignore"
							   value="${memorialinfo_viewPage.size}">
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
							   value="${memorialinfo_viewPage.remark}">
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
						<input class="inputxt" id="name" name="name" ignore="ignore"
							   value="${memorialinfo_viewPage.name}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							状态:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="state" name="state" ignore="ignore"
							   value="${memorialinfo_viewPage.state}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							QQ:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="qq" name="qq" ignore="ignore"
							   value="${memorialinfo_viewPage.qq}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="address" name="address" ignore="ignore"
							   value="${memorialinfo_viewPage.address}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							电子邮件:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="email" name="email" ignore="ignore"
							   value="${memorialinfo_viewPage.email}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							固话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="homephone" name="homephone" ignore="ignore"
							   value="${memorialinfo_viewPage.homephone}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							linkman:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="linkman" name="linkman" ignore="ignore"
							   value="${memorialinfo_viewPage.linkman}">
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
							   value="${memorialinfo_viewPage.mobilephone}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							微信号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="wechat" name="wechat" ignore="ignore"
							   value="${memorialinfo_viewPage.wechat}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							主键:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="linkmanid" name="linkmanid" 
							   value="${memorialinfo_viewPage.linkmanid}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							nameid:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="nameid" name="nameid" 
							   value="${memorialinfo_viewPage.nameid}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>