<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
<script>
function forward(){
	var name = document.getElementById("name").value;
	window.location.href="${pageContext.request.contextPath}/doritualinfoController.do?print&name=" + name;
}
</script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'confirmPage.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<%-- <script src='http://127.0.0.1:8000/CLodopfuncs.js'></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/printer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/receipt.js"></script> --%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  </head>
  
  <body>
  <center>
  统计${name}的数目
  <input type="hidden" value="${name}" id="name">
  <br><br><br><br><br>
  
    <table id = "receipttable" class="imagetable">
			<tr>
				<th>序号</th>
				<th>法事类型</th>
				<th>牌位大小</th>
				<th>已打印</th>
				<th>未打印</th>

			</tr>
			<c:forEach items="${tablet}" var="tablet" varStatus="stauts">
			<tr>
					<td>${stauts.index+1}</td>
					<td>${tablet.ritualtype}</td>
				 	<td>${tablet.size}</td>
				 	<td>${tablet.alreadyprint}</td>
				 	<td>${tablet.notprint}</td>
				 	
			</tr>
			</c:forEach>
			
		</table>
		<br>
		<input class = "button" type="button" value="打印牌位" onClick="forward()">
	<!-- <div>
  		<select id="PrinterList" size="1" style='width:300px' onchange="selectPrinter()"></select>
	</div> -->
   </center>
  </body>
</html>
