<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
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
	<script src='http://127.0.0.1:8000/CLodopfuncs.js'></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/printer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/receipt.js"></script>
	<script>
	  function forward(){
			window.location.href="${pageContext.request.contextPath}/receiptController.do?forward";
		}
	</script>
  </head>
  
  <body>
  <center>
    <br>${message}<br>
    <br>打印${ritualtype}收据<br>
    <table id = "receipttable" class="imagetable">
			<tr>
				<th>序号</th>
				<th>收据编号</th>
				<th>时间</th>
				<th>付款人</th>
				<th>付款方式</th>
				<th>牌位类型</th>
				<th>金额</th>
				<th>对象</th>
				<th>地址</th>
				<th>摘要</th>
				<th>备注</th>
				<th>操作</th>
			</tr>
			<tr>
					<td>${stauts.index+1}</td>
					<td>${returnRe.no}</td>
				 	<td>${returnRe.registertime}</td>
				 	<td>${returnRe.paymen}</td>
				 	<td>${returnRe.payway}</td>
				 	<td>${returnRe.size}</td>
				 	<td>${returnRe.money}</td>
				 	<td>${returnRe.obj}</td>
				 	<td>${returnRe.address}</td>
				 	<td>${returnRe.summary}</td>
				 	<td>${returnRe.remark}</td>
				 	<td><input class="button" type="button" onclick="print(this.id)" id="${returnRe.id}" value="打印"/>  </td>
			</tr>
			
		</table>
	<div>
  		打印机选择 ： <select id="PrinterList" size="1" style='width:300px' onchange="selectPrinter()"></select>
	</div>
   </center>
  </body>
</html>
