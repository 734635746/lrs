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

  	</script>


 </head>
 <body style="overflow-y: scroll" scroll="yes">
 <h4>观音开库 自动编号</h4>
 <br>
		<br>
		<center>
		<c:if test="${!empty guanyinopenEntityList}">
		<form action="${pageContext.request.contextPath}/guanyinopenController.do?updateGuanyinopenAndReceipt" method="post">
		 <input type="hidden" name = "ids" value="${ids}">
		<table id = "receipttable" class="imagetable">
			<tr>
				<th>系统编号</th>
				<th>祈福对象</th>
				<th>牌位大小</th>
				<th>摘要</th>
			</tr>
			<c:forEach items="${guanyinopenEntityList}" var="guanyinopenEntity" varStatus="stauts">
				<tr>
				 	<td>${guanyinopenEntity.serial}</td>
				 	<input style="width:50px;" type="hidden" name="autoserial" value="${guanyinopenEntity.serial}">
				 	<td>${guanyinopenEntity.livingmenber}</td>
				 	<td>${guanyinopenEntity.size}</td>
				 	<td><textarea>${guanyinopenEntity.summary}</textarea></td>
				   	
				 </tr>
			</c:forEach>
			
		</table>
		<br><br>
		<input type="submit" value="确定" class="button">
		</form>
		</c:if>
		
    	</center>
    	
    	<center>
    		<c:if test="${empty guanyinopenEntityList}">出现异常
			</c:if>
		
		</center>
 </body>