<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%> 
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'estimateVolunteer.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link href="http://netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css" rel="stylesheet">
	<script src="http://libs.baidu.com/jquery/1.10.2/jquery.min.js"></script>
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/table.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/button.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/div.css">
	<link href="${pageContext.request.contextPath}/resources/css/star-rating.css" media="all" rel="stylesheet" type="text/css"/>
	<script src="${pageContext.request.contextPath}/resources/js/star-rating.js" type="text/javascript"></script>

  </head>
  
  <body>
  <center><p><font size="5" face="arial" color="black">${eventname}</font></p></center>
  <input type = "hidden" id = "eventid" value = "${eventid}">
    <center>
		<div id="show">
		<form action="">
			<table class="imagetable">
				<tr>
					<th>序号</th>
					<th>义工名字</th>
					<th>工作态度</th>
					<th>工作情况</th>
				</tr>
				<c:forEach items="${volunteereventList}" var="volunteerevent" varStatus="stauts">
				<input type="hidden" id = "id${stauts.index+1}" value = ${volunteerevent.id}>
				<input type="hidden" id = "volunteerid${stauts.index+1}" value = ${volunteerevent.volunteerid}>
				<tr>
					<td>${stauts.index+1}</td>
				 	<td>${volunteerevent.volunteername}</td>
				 	<td><input id="input-21d${stauts.index+1}" value="2" type="number" class="rating" min=0 max=5 step=0.5 data-size="xs"></td>
				   	<td><input id="input-21e${stauts.index+1}" value="2" type="number" class="rating" min=0 max=5 step=0.5 data-size="xs"></td>
				 </tr>
				 </c:forEach>
				
			</table>
			<br>
			<input type = "button" class = "button" value="提交" onclick = "toSubmit()">&nbsp;&nbsp;&nbsp;
			<input type = "reset" class = "button" value="重置">
		</form>
		</div>
	</center>
<script>
	    jQuery(document).ready(function () {
	        $(".rating-kv").rating();
	    });
		function on(){
			var temp = document.getElementById("input-21d1");
			var temp1 = document.getElementById("input-21e1");
		}
		function getStr(){
			var len = "${fn:length(volunteereventList)}";
			var res = "";
			for(var i = 1;i <= len;i ++){
				var id0 = document.getElementById("id" + i);
				var atitude0 = document.getElementById("input-21d" + i);
				var work0 = document.getElementById("input-21e" + i);
				var str = id0.value + "|" + atitude0.value + "|" + work0.value + ",";		
				res = res + str;
			}
			return res;
		}
		function getVolunteerids(){
			var len = "${fn:length(volunteereventList)}";
			var res = "";
			for(var i = 1;i <= len;i ++){
				var id0 = document.getElementById("volunteerid" + i);
				var str = id0.value + ",";		
				res = res + str;
			}
			return res;
		}
		function toSubmit(){
			href = "${pageContext.request.contextPath}/eventController.do?estimatesubmit&eventid=${eventid}&str=" + getStr() + "&volunteerids=" + getVolunteerids();
			window.location = href;
		}
	</script>
  </body>
</html>
