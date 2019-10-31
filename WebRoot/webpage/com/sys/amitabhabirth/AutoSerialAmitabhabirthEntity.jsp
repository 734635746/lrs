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
 <h4>弥陀诞 自动编号</h4>
 <input type = "hidden" name = "paraList" value = "${amitabhabirthEntityList}">
 <br>
		<br>
		<center>
		<c:if test="${!empty amitabhabirthEntityList}">
		<form action="${pageContext.request.contextPath}/amitabhabirthController.do?updateAmitabhabirthAndReceipt" method="post">
		 <input type="hidden" name = "ids" value="${ids}">
		<table id = "receipttable" class="imagetable">
			<tr>
				<th>系统编号</th>
				<th>超渡对象</th>
				<th>牌位大小</th>
				<th>牌位类型</th>
				<th>摘要</th>
				<!-- <th>手写编号</th> -->
			</tr>
			<c:forEach items="${amitabhabirthEntityList}" var="amitabhabirthEntity" varStatus="stauts">
				<tr>
				 	<td>${amitabhabirthEntity.serial}</td>
				 	<%-- <input style="width:50px;" type="hidden" name="serial" value="${amitabhabirthEntity.serial}"> --%>
				 	<td>${amitabhabirthEntity.ancestor}</td>
				 	<td>${amitabhabirthEntity.size}</td>
				 	<td>
						<c:if test="${amitabhabirthEntity.type == '2'}">十方法界</c:if>
				 		<c:if test="${amitabhabirthEntity.type == '3'}">门堂历代宗亲</c:if>
				 		<c:if test="${amitabhabirthEntity.type == '4'}">堕胎婴灵</c:if>
				 		<c:if test="${amitabhabirthEntity.type == '1'}">无</c:if>
				 		<c:if test="${amitabhabirthEntity.type == '0'}"></c:if>
					</td>
				 	<td><textarea>${amitabhabirthEntity.summary}</textarea></td>
				 	<td><input style="width:50px;" type="hidden" name="autoserial" value="${amitabhabirthEntity.serial}"></td>
				   	
				 </tr>
			</c:forEach>
			
		</table>
		<br><br>
		<input type="submit" value="确定" class="button">
		</form>
		</c:if>
		
    	</center>
    	
    	<center>
    		<c:if test="${empty amitabhabirthEntityList}">出现异常
			</c:if>
		
		</center>
 </body>