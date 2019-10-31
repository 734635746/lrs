<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'eventResult.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link type="text/css" rel="stylesheet" href="http://cdn.staticfile.org/twitter-bootstrap/3.1.1/css/bootstrap.min.css"/>
	<style type= "text/css">
	.pagination{
		position:fixed;
		bottom:0;
	}
	</style>
</head>
  
  <body>
  <input type="hidden" id= "total" name="field＿name" value="2"/>
  <input type="hidden" id= "eventlist" name="field＿name" value="${eventList}"/>
  <form action = "">
    	<table>
    	<tr>
    		<td>事件名称</td>
    		<td>事件创始人</td>
    		<td>事件创立时间</td>
    		<td>操作</td>
    	</tr>
    	<%-- <c:if test="${!empty eventList}">
    	<c:forEach items="${eventList}" var="poVal" varStatus="stuts"> --%>
		<tr>
			<td><input id="eventname" name="eventname" <%-- value="${poVal.eventname }" --%> type="text" ></td>
	    	<td><input id ="creator" name="creator" <%-- value="${poVal.creator }" --%> type="text" ></td>
	    	<td><input id ="createtime" name="createtime" <%-- value="${poVal.createtime }" --%> type="text" ></td>
	    	<td><input type = "button" value="分配" ></td>
   		</tr>
		<%-- </c:forEach>
    	</c:if> --%>
    	<c:if test="${empty eventList}">
    		没有相应的事件
    	</c:if>
	
  </form>
  <div id="pagination"></div>

	<script type="text/javascript" src="http://cdn.staticfile.org/jquery/1.7.1/jquery.min.js"></script>
	<script type="text/javascript" src="<%=basePath%>/resources/js/jqPaginator.js"></script>
	<script type="text/javascript">
	var total = document.getElementById("total").value;
	/* <c:forEach var="obj" items="${eventList}">
		alert("${obj.createtime}"); 
	</c:forEach> */ 
    $.jqPaginator('#pagination', {
        totalPages: total,
        visiblePages: 5,
        currentPage: 1,
        wrapper:'<ul class="pagination"></ul>',
        first: '<li class="first"><a href="javascript:void(0);">First</a></li>',
        prev: '<li class="prev"><a href="javascript:void(0);">Previous</a></li>',
        next: '<li class="next"><a href="javascript:void(0);">Next</a></li>',
        last: '<li class="last"><a href="javascript:void(0);">Last</a></li>',
        page: '<li class="page"><a href="javascript:void(0);">{{page}}</a></li>',
        onPageChange: function () {
        	<%int k=0;%> 
        	alert(k);
            $('#eventname').val("${eventList[k].eventname}");
			$('#creator').val("${eventList[k].creator}");
			$('#createtime').val("${eventList[k].createtime}");
			<%k++;%> 
        }
    });
	</script>
  </body>
</html>
