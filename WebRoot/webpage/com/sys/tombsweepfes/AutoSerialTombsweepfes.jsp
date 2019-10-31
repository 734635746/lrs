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
 <h4>清明节 自动编号</h4>
 <br>
		<br>
		<center>
		<c:if test="${!empty tombsweepfesEntityList}">
		<form action="${pageContext.request.contextPath}/tombsweepfesController.do?updateTombsweepfesAndReceipt" method="post">
		 <input type="hidden" name = "ids" value="${ids}">
		<table id = "receipttable" class="imagetable">
			<tr>
				<th>系统编号</th>
				<th>超渡对象</th>
				<th>牌位大小</th>
				<th>牌位类型</th>
				<th>摘要</th>
				<th>手写编号</th>
			</tr>
			<c:forEach items="${tombsweepfesEntityList}" var="tombsweepfesEntity" varStatus="stauts">
				<tr>
				 	<td>${tombsweepfesEntity.serial}</td>
				 	<%-- <input style="width:50px;" type="hidden" name="autoserial" value="${tombsweepfesEntity.serial}"> --%>
				 	<td>${tombsweepfesEntity.ancestor}</td>
				 	<td>${tombsweepfesEntity.size}</td>
				 	<td>
						<c:if test="${tombsweepfesEntity.type == '2'}">十方法界</c:if>
				 		<c:if test="${tombsweepfesEntity.type == '3'}">门堂历代宗亲</c:if>
				 		<c:if test="${tombsweepfesEntity.type == '4'}">堕胎婴灵</c:if>
				 		<c:if test="${tombsweepfesEntity.type == '1'}">无</c:if>
					</td>
				 	<td><textarea>${tombsweepfesEntity.summary}</textarea></td>
				   	<td><input style="width:50px;" type="text" name="autoserial" value="${tombsweepfesEntity.serial}"></td>
				 </tr>
			</c:forEach>
			
		</table>
		<br><br>
		<input type="submit" value="确定" class="button">
		</form>
		</c:if>
		
    	</center>
    	
    	<center>
    		<c:if test="${empty tombsweepfesEntityList}">出现异常
			</c:if>
		
		</center>
 </body>