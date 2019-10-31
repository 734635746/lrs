<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>入库明细管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="warehousing_detailController.do?save">
			<input id="id" name="id" type="hidden" value="${warehousing_detailPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							物品规格:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="itemStdmode" name="itemStdmode" ignore="ignore"
							   value="${warehousing_detailPage.itemStdmode}">
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
							   value="${warehousing_detailPage.itemName}">
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
							   value="${warehousing_detailPage.itemManufacturer}">
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
							   value="${warehousing_detailPage.manufacturerAddress}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							入库量:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="incomingQuantity" name="incomingQuantity" ignore="ignore"
							   value="${warehousing_detailPage.incomingQuantity}" datatype="n">
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
						<input class="inputxt" id="price" name="price" ignore="ignore"
							   value="${warehousing_detailPage.price}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							单位:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="unit" name="unit" ignore="ignore"
							   value="${warehousing_detailPage.unit}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>