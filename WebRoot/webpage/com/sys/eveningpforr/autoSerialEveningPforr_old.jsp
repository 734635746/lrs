<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  
<script type="text/javascript"> 
javascript:window.history.forward(-1);
</script>
 </head>
 <body style="overflow-y: scroll" scroll="yes">
 <h4>早普佛 自动编号</h4>
 <input type = "hidden" name = "paraList" value = "${morningpforrEntity}">
 <br>
		<br>
		<center>
		<c:if test="${!empty morningpforrEntity}">
		<form action="${pageContext.request.contextPath}/eveningpforrController.do?updateEveningpforrAndReceipt" method="post">
		 <input type="hidden" name = "ids" value="${ids}">
		<table id = "receipttable" class="imagetable">
			<tr>
				<th>系统编号</th>
				<th>祈福对象</th>
				<th>摘要</th>
			</tr>
			<tr>
				 	<td>${morningpforrEntity.serial}</td>
				 	<input style="width:50px;" type="hidden" name="autoserial" value="${morningpforrEntity.serial}">
				 	<td>${morningpforrEntity.ancestor}</td>
				 	<td><textarea>${morningpforrEntity.summary}</textarea></td>
				   	
			</tr>
			
		</table>
		<br><br>
		<input type="submit" value="确定" class="button">
		</form>
		</c:if>
		
    	</center>
    	
    	<center>
    		<c:if test="${empty morningpforrEntity}">出现异常
			</c:if>
		
		</center>
 </body>