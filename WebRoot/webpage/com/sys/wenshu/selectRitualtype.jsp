<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title></title>
 <%--  <t:base type="jquery,easyui,tools,DatePicker"></t:base> --%>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button1.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button2.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
<script>
function goTo(ritualtype,name,flag){
	window.location.href = "${pageContext.request.contextPath}/tmp_wenshuController.do?tabletcount&type="+ritualtype +"&name=" + name + "&flag=" + flag;
}
</script>
 </head>
 <body scroll="no">
			<center>
			<br><br><br><br><br>
			<table class = "imagetable" border="1">
				<tr>
				<td><input type="button" class="button2" value="弥勒佛" onclick="goTo('buddhabirth','弥勒佛','1')"></td>
				<td><input type="button" class="button2" value="观音开库" onclick="goTo('guanyinopen','观音开库','1')"></td>
				<td><input type="button" class="button2" value="观音诞" onclick="goTo('goddessbirth','观音诞','1')"></td>
				<td><input type="button" class="button2" value="释尊诞" onclick="goTo('honoredbirth','释尊诞','1')"></td>
				</tr>
				<tr>
				<td><input type="button" class="button2" value="观音成道" onclick="goTo('guanyingaya','观音成道','1')"></td>
				<td><input type="button" class="button2" value="观音出家" onclick="goTo('guanyinmonk','观音出家','1')"></td>
				<td><input type="button" class="button2" value="药师佛诞" onclick="goTo('pharmacistbirth','药师诞','1')"></td>
				<td><input type="button" class="button2" value="释尊成道" onclick="goTo('buddhagaya','释尊成道','1')"></td>
				</tr>
			</table>
			<br><br><br>
			<table class = "imagetable" border="1">
				<tr>
				<td><input type="button" class="button1" value="清明节" onclick="goTo('tombsweepfes','清明节','2')"></td>
				<td><input type="button" class="button1" value="盂兰节" onclick="goTo('ghostfes','盂兰节','2')"></td>
				<td><input type="button" class="button1" value="地藏诞" onclick="goTo('jizobirth','地藏诞','2')"></td>
				<td><input type="button" class="button1" value="弥陀诞" onclick="goTo('amitabhabirth','弥陀诞','2')"></td>
				</tr>
			</table>
			</center>
		
 </body>