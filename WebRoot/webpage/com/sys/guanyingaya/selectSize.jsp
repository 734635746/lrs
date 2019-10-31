<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
    <title>My JSP 'selectSize.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
  <center>
  <br><br><br><br><br><br>
    	<form action = "${pageContext.request.contextPath}/guanyingayaController.do?redirectToShowGuanyingayaRecord" method = "post">
    		<input type="hidden" name = "id" value ="${id0}">
    		牌位大小 ：<select style="width:100px;font-size:20px;" name = "size" id = "size">
    			<option value = "小">小</option>
    			<option value = "大">大</option>
    			<option value = "拈香">拈香</option>
    		</select>
    		<input type = "submit" class = "button" value = "确认">
    	</form>
   </center>
  </body>
</html>
