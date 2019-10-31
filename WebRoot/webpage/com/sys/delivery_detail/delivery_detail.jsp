<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>出库明细表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="delivery_detailController.do?save">
			<input id="id" name="id" type="hidden" value="${delivery_detailPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							物品代码:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="itemStdmode" name="itemStdmode" ignore="ignore"
							   value="${delivery_detailPage.itemStdmode}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							物品名称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="itemName" name="itemName" ignore="ignore"
							   value="${delivery_detailPage.itemName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							生产厂家:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="itemManufacturer" name="itemManufacturer" ignore="ignore"
							   value="${delivery_detailPage.itemManufacturer}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							生产地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="manufacturerAddress" name="manufacturerAddress" ignore="ignore"
							   value="${delivery_detailPage.manufacturerAddress}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							取货人名字:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="memberName" name="memberName" ignore="ignore"
							   value="${delivery_detailPage.memberName}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							出库量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="deliveryQuantity" name="deliveryQuantity" ignore="ignore"
							   value="${delivery_detailPage.deliveryQuantity}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>