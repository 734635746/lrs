<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>放生管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="freeLivesController.do?save">
			<input id="id" name="id" type="hidden" value="${morningpforrPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							登记时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="registertime" name="registertime" ignore="ignore"
							   value="${morningpforrPage.registertime}">
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
							   value="${morningpforrPage.registrant}">
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
							   value="${morningpforrPage.serial}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							付款者:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="prayingobj" name="prayingobj" ignore="ignore"
							   value="${morningpforrPage.prayingobj}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							信众:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="livingmenber" name="livingmenber" ignore="ignore"
							   value="${morningpforrPage.livingmenber}">
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
							   value="${morningpforrPage.money}" datatype="n">
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
							   value="${morningpforrPage.payway}">
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
							   value="${morningpforrPage.summary}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							receiptno:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="receiptno" name="receiptno" ignore="ignore"
							   value="${morningpforrPage.receiptno}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							doritualid:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="doritualid" name="doritualid" ignore="ignore"
							   value="${morningpforrPage.doritualid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							address:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="address" name="address" ignore="ignore"
							   value="${morningpforrPage.address}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							receiptid:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="receiptid" name="receiptid" ignore="ignore"
							   value="${morningpforrPage.receiptid}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							flag:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="flag" name="flag" ignore="ignore"
							   value="${morningpforrPage.flag}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							size:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="size" name="size" ignore="ignore"
							   value="${morningpforrPage.size}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							autoserial:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="autoserial" name="autoserial" ignore="ignore"
							   value="${morningpforrPage.autoserial}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							cancel:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="cancel" name="cancel" ignore="ignore"
							   value="${morningpforrPage.cancel}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							paymen:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="paymen" name="paymen" ignore="ignore"
							   value="${morningpforrPage.paymen}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							lunardate:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="lunardate" name="lunardate" ignore="ignore"
							   value="${morningpforrPage.lunardate}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							solardate:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="solardate" name="solardate" ignore="ignore"
							   value="${morningpforrPage.solardate}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							book:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="book" name="book" ignore="ignore"
							   value="${morningpforrPage.book}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </body>