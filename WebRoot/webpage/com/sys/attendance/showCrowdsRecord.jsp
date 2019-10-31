<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button3.css">
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
		function addAttendance(name){
			var type = document.getElementById("type").value;
			window.location.href = "${pageContext.request.contextPath}/attendanceController.do?addMorningAndEveningPuja&dharmaname=" + encodeURI(encodeURI(name)) + "&type=" + type;
		}
	</script>

  </head>
  
  <body>
		  <input type = "hidden" id = "type" value = "${type}">
		  <center>
		  <c:if test="${length > 0}">
		  <div style=" float:center; width:100%; height:100px;">
			  <table id = "" class="imagetable">
				<tr>
					<th>序号</th>
					<th>法名</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${pravrajanamemberEntityList}" var="pravrajanamemberEntity" varStatus="stauts">
					<tr>
						<td>${stauts.index+1}</td>
						<td width="100">${pravrajanamemberEntity.dharmaname}</td>
						<td width="120"><input type="button" class = "button" value = "录入${message}考勤" onclick = "addAttendance('${pravrajanamemberEntity.dharmaname}')"></td>
					 </tr>
				</c:forEach>
				
			</table>
		  </div>
		  
		  </c:if>
		  <center>
		  
  </body>
</html>
