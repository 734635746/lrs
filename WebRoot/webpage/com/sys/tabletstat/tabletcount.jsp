<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

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
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/button.css"/>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/table.css"/>

<script>
	function forward() {
		/* var name = document.getElementById("name").value;
		var flag = document.getElementById("flag").value;
		var type = document.getElementById("type").value; */
		if ("${name}" == "早普佛") {
			window.location.href = "${pageContext.request.contextPath}/morningpforrController.do?forwardPrint";
		} else if ("${name}" == "晚普佛") {
			window.location.href = "${pageContext.request.contextPath}/eveningpforrController.do?forwardPrint";
		} else if ("${name}" == "上供") {
			window.location.href = "${pageContext.request.contextPath}/prayguanyinController.do?forwardPrint";
		}
		else if ("${name}" == "放生") {
			window.location.href = "${pageContext.request.contextPath}/freeLivesController.do?forwardPrint";
		}else {
			var name = document.getElementById("name").value;
			var flag = document.getElementById("flag").value;
			var type = document.getElementById("type").value;
			window.location.href = "${pageContext.request.contextPath}/doritualinfoController.do?print&name=" + name + "&flag=" + flag + "&type=" + type;
		}
	}
</script>
</head>


<body>
	<center>
		统计${name}的数目 <input type="hidden" value="${name}" id="name"> <input
			type="hidden" value="${flag}" id="flag"> <input type="hidden"
			value="${type}" id="type"> <br>
		<br>
		<br>
		<br>
		<br>

		<table id="receipttable" class="imagetable" width="70%" height="50%">
			<tr>
				<th>序号</th>
				<th>法事类型</th>
				<c:if test="${name=='早普佛'||name=='晚普佛'||name=='上供'||name=='放生'}">
					<th>堂类型</th>
				</c:if>
				<c:if test="${name!='早普佛'&&name!='晚普佛'&&name!='上供'&&name!='放生'}">
					<th>牌位大小</th>
				</c:if>
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
		<br> <input class="button" type="button" value="打印牌位"  onclick="forward();"/>
		<!-- <div>
  		<select id="PrinterList" size="1" style='width:300px' onchange="selectPrinter()"></select>
	</div> -->
	</center>
</body>
</html>
