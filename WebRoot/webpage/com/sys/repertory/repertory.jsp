<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>库存表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="repertoryController.do?save">
			<input id="id" name="id" type="hidden" value="${repertoryPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							物品规格:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="itemStdmode" name="itemStdmode" ignore="ignore"
							   value="${repertoryPage.itemStdmode}" readonly>
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
							   value="${repertoryPage.itemName}" readonly>
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
							   value="${repertoryPage.itemManufacturer}" readonly>
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
							   value="${repertoryPage.manufacturerAddress}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							当前库存量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="currentInventory" name="currentInventory" ignore="ignore"
							   value="${repertoryPage.currentInventory}" datatype="n" readonly>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							价格:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="price" name="price" ignore="ignore"
							   value="${repertoryPage.price}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单价:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="unit" name="unit" ignore="ignore"
							   value="${repertoryPage.unit}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							最低库存量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="quantityStorage" name="quantityStorage" ignore="ignore"
							   value="${repertoryPage.quantityStorage}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>