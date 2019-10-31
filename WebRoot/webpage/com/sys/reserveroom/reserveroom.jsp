<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>预定房间</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script language="javascript">
    function forward() {
    	var id = document.getElementById("id").value;
    	window.location="${pageContext.request.contextPath}/reserveroomController.do?forwardToRegisterRoom&id=" + id;
    }
</script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
 <c:if test="${duplicateFlag!='T'}">
　　<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="reserveroomController.do?save">
			<input id="id" name="id" type="hidden" value="${reserveroomPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<tr>
					<td align="right">
						<label class="Validform_label">
							房号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="roomnumber" name="roomnumber" ignore="ignore"
							   value="${reserveroomPage.roomnumber}">
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
						<input class="inputxt" id="roomkind" name="roomkind" ignore="ignore"
							   value="${reserveroomPage.roomkind}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							所需床位:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="needbeds" name="needbeds" ignore="ignore"
							   value="${reserveroomPage.needbeds}" datatype="n">
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
							   value="${reserveroomPage.registrant}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							登记时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="registertime" name="registertime" ignore="ignore"
							     value="<fmt:formatDate value='${reserveroomPage.registertime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							入住时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="intakentime" name="intakentime" ignore="ignore"
							     value="<fmt:formatDate value='${reserveroomPage.intakentime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							离开时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="leavetime" name="leavetime" ignore="ignore"
							     value="<fmt:formatDate value='${reserveroomPage.leavetime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
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
							   value="${reserveroomPage.mobilephone}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<input type="hidden" id="roomid" name="roomid" value="${reserveroomPage.roomid}">
				<tr>
					<td align="right">
						<label class="Validform_label">
							预定状态:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="predeterminedstate" typeGroupCode="predestate" hasLabel="false" defaultVal="${reserveroomPage.predeterminedstate}" type="radio">
						</t:dictSelect> <span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							确认入住:
						</label>
					</td>
					<td class="value">
						<c:if test="${reserverflag != 'yes'}">
						<input type="button" value="确认入住" onclick="forward()">
						</c:if>
					</td>
				</tr>
			</table>
		</t:formvalid>
 </c:if>
  <c:if test="${duplicateFlag=='T'}">
  	该记录已经被取消或者已经确认入住，请勿重复此操作
  </c:if>
 
  
 </body>