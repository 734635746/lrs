<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>皈依弟子档案表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="disciplememberController.do?save">
			<input id="id" name="id" type="hidden" value="${disciplememberPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							姓名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="disciplename" name="disciplename" ignore="ignore"
							   value="${disciplememberPage.disciplename}" datatype="*">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							法名:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="dharmaname" name="dharmaname" ignore="ignore"
							   value="${disciplememberPage.dharmaname}">
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
							   value="${disciplememberPage.origin}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							性别:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="sex" typeGroupCode="sex" hasLabel="false" defaultVal="${disciplememberPage.sex}"></t:dictSelect> 
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							学历:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="education" name="education" ignore="ignore"
							   value="${disciplememberPage.education}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							学科:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="subject" name="subject" ignore="ignore"
							   value="${disciplememberPage.subject}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							职业:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="profession" name="profession" ignore="ignore"
							   value="${disciplememberPage.profession}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							职称:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="professiontitle" name="professiontitle" ignore="ignore"
							   value="${disciplememberPage.professiontitle}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							爱好:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="hobby" name="hobby" ignore="ignore"
							   value="${disciplememberPage.hobby}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							特长:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="speciality" name="speciality" ignore="ignore"
							   value="${disciplememberPage.speciality}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							手机电话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="phonenumber" name="phonenumber" ignore="ignore"
							   value="${disciplememberPage.phonenumber}" datatype="m">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							固话:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="homephone" name="homephone" ignore="ignore"
							   value="${disciplememberPage.homephone}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							联系地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="address" name="address" ignore="ignore"
							   value="${disciplememberPage.address}">
						<span class="Validform_checktip"></span>
					</td>
					<td align="right">
						<label class="Validform_label">
							皈依原因
						</label>
					</td>
					<td> 
						 ：<input type="text" value="请在下面文本框填写原因" readonly ignore="ignore">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
			</table>
			<textarea rows="5" cols="96" id="disciplereason" name="disciplereason">
		${disciplememberPage.disciplereason}
			</textarea>
		</t:formvalid>
 </body>