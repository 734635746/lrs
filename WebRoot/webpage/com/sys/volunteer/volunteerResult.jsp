<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'result.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/table.css">
	<link rel="stylesheet" type="text/css" href="<%=basePath%>/resources/css/div.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/page.js">
  </head>
  
  <body>
  	 ${message}
  	 <h4>义工列表</h4>
     <center>
		<div id="show">
			<table class="imagetable">
				<tr>
					<th>序号</th>
					<th></th>
					<th>义工名字</th>
					<th>手机号码</th>
					<th>专业技能</th>
					<th>最高学历</th>
					<th>政治面貌</th>
					<th>平均分</th>
				</tr>
				<c:forEach items="${page.result}" var="volunteer" varStatus="stauts">
				<tr>
					<td>${stauts.index+1+(page.pageNo-1)*page.pageSize}</td>
					<td><input type="checkbox" value="${volunteer.id}split${volunteer.name}" name = "checkbox"/></td>
				 	<td>${volunteer.name}</td>
				 	<td>${volunteer.phonenumber}</td>
				 	<td>${volunteer.professionalskills}</td>
				 	<td>${volunteer.higheducation}</td>
				 	<td>${volunteer.politicalstatus}</td>
				 	<td>${volunteer.average}</td>
				 </tr>
				 </c:forEach>
				
			</table>
			<br>

			 	<input type = "hidden" value = "${page.totalPage}" id = "totalPage" >     
			<br>
			 第<span id="lbRow">${page.pageNo}</span> 页, 共<span id="lbpage">${page.totalPage}</span> 页, 
			 共<span id="lbRecord">${page.totalCount}</span> 条记录,
			 <c:if test="${page.pageNo!=1}">
			 	<a href="${pageContext.request.contextPath}/volunteerController.do?getVolunteerList&pageNo=${page.pageNo-1}&eventid=${eventid}">上一页</a> 
			 </c:if>
			 <c:if test="${page.pageNo!=page.totalPage}">
			 	<a href="${pageContext.request.contextPath}/volunteerController.do?getVolunteerList&pageNo=${page.pageNo+1}&eventid=${eventid}">下一页</a> 
			 </c:if>
		</div>
		<br><br>
		<input type="button" value="分配" class="button" id = "btn5" onclick = "forward()">
	</center>
	
  </body>
 <script type="text/javascript" src="<%=basePath%>/resources/js/jquery.js"></script>
 <script type="text/javascript">
	function getListSelections(){
		 var ids = "";
		    $("[name='checkbox'][checked]").each(function(){
		     ids +=$(this).val()+",";
		    })
		return ids;
	}	

	function forward(){
		href = "${pageContext.request.contextPath}/volunteerController.do?giveVolunteer&eventname=${eventname}&eventid=${eventid}&ids=" + getListSelections();
		window.location = href ;
	}
	 /* $("#btn5").click(function(){
		 alert("==============");
		    var str="";
		    $("[name='checkbox'][checked]").each(function(){
		     str+=$(this).val()+" ";
		   //alert($(this).val());
		    })
		   alert(str);
		    }) */
</script>
</html>
