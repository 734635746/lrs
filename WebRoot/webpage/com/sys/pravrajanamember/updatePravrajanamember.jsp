<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>出家众人档案表</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pravrajanamemberController.do?save">
			<input id="id" name="id" type="hidden" value="${pravrajanamemberPage.id }">
			<table style="width: 800px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							俗名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="pravrajananame" name="pravrajananame" 
							   value="${pravrajanamemberPage.pravrajananame}" datatype="*" readonly>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							性别:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="pravrajanasex" typeGroupCode="sex" hasLabel="false" defaultVal="${pravrajanamemberPage.pravrajanasex}"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							出生日期:
						</label>
					</td>
					<td class="value">
						<input nullmsg="请填写员工出生年月" errormsg="员工出生年月格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="birthday" name="birthday" ignore="ignore"
									     value="<fmt:formatDate value='${pravrajanamemberPage.birthday}' type="date" pattern="yyyy-MM-dd"/>">
								<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							法名:
						</label>
					</td>
					<td class="value">
						<input nullmsg="请填写"  class="inputxt" id="dharmaname" name="dharmaname" 
							   value="${pravrajanamemberPage.dharmaname}" readonly>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							籍贯:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="origin" name="origin" ignore="ignore"
							   value="${pravrajanamemberPage.origin}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							民族:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="nation" name="nation" ignore="ignore"
							   value="${pravrajanamemberPage.nation}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							法号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="religiousname" name="religiousname"
							   value="${pravrajanamemberPage.religiousname}" readonly>
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							健康情况:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="healthstatus" typeGroupCode="health" hasLabel="false" defaultVal="${pravrajanamemberPage.healthstatus}"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							来寺时间:
						</label>
					</td>
					<td class="value">
						<input nullmsg="请填写员工来寺时间" errormsg="来寺时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="arrivetime" name="arrivetime" ignore="ignore"
									     value="<fmt:formatDate value='${pravrajanamemberPage.arrivetime}' type="date" pattern="yyyy-MM-dd"/>">
								<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							文化程度:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="education" typeGroupCode="education" hasLabel="false" defaultVal="${pravrajanamemberPage.education}"></t:dictSelect>
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							剃度寺庙:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="tonsuretemple" name="tonsuretemple" ignore="ignore"
							   value="${pravrajanamemberPage.tonsuretemple}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							剃度师父:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="tonsuremaster" name="tonsuremaster" ignore="ignore"
							   value="${pravrajanamemberPage.tonsuremaster}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							联系电话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="phonenumber" name="phonenumber" ignore="ignore"
							   value="${pravrajanamemberPage.phonenumber}" datatype="m">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							介绍人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="introducer" name="introducer" ignore="ignore"
							   value="${pravrajanamemberPage.introducer}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							身份证:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="identification" name="identification" ignore="ignore"
							   value="${pravrajanamemberPage.identification}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							户口所在地:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="registeredresidence" name="registeredresidence" ignore="ignore"
							   value="${pravrajanamemberPage.registeredresidence}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right"><label class="Validform_label"> 角色: </label></td>
					<td class="value" nowrap><input name="roleid" name="roleid" type="hidden" value="${id}" id="roleid"> <input name="roleName" class="inputxt" value="${roleName }" id="roleName"
						readonly="readonly" datatype="*" /></td>
						
					<td align="right"><label class="Validform_label"> 所选部门: </label></td>
					<td class="value"><select id="departid" name="departid" datatype="*">
						<c:forEach items="${departList}" var="depart">
							<option value="${depart.id }" <c:if test="${depart.id==pravrajanamemberPage.departid}">selected="selected"</c:if>>${depart.departname}</option>
						</c:forEach>
					</select> <span class="Validform_checktip">请选择部门</span></td>
				</tr>
				
			</table>
			<br>
			<center>
			<input type = "submit" value = "提交">
			</center>
		</t:formvalid>
</body>