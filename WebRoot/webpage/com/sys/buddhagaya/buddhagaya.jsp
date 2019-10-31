<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>释尊成道信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="buddhagayaController.do?save">
			<input id="id" name="id" type="hidden" value="${buddhagayaPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							登记时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="registertime" name="registertime" ignore="ignore"
							   value="${buddhagayaPage.registertime}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							登记人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="registrant" name="registrant" ignore="ignore"
							   value="${buddhagayaPage.registrant}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="serial" name="serial" ignore="ignore"
							   value="${buddhagayaPage.serial}">
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
							   value="${buddhagayaPage.size}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							祈福者:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="prayingobj" name="prayingobj" ignore="ignore"
							   value="${buddhagayaPage.prayingobj}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							在世:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="livingmenber" name="livingmenber" ignore="ignore"
							   value="${buddhagayaPage.livingmenber}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							金额:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="money" name="money" ignore="ignore"
							   value="${buddhagayaPage.money}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收款方式:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="payway" name="payway" ignore="ignore"
							   value="${buddhagayaPage.payway}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							摘要:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="summary" name="summary" ignore="ignore"
							   value="${buddhagayaPage.summary}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收据编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="receiptno" name="receiptno" ignore="ignore"
							   value="${buddhagayaPage.receiptno}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="address" name="address" ignore="ignore"
							   value="${buddhagayaPage.address}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>