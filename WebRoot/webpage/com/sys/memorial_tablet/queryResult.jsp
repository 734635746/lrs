<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'member.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/div.css">
  </head>
  <script>
  function subF(member_Id){
	  	href = "${pageContext.request.contextPath}/member_find_update?member_Id="+member_Id;
		window.location = href ;
	
  }
	 
  </script>
  
  <body>
  <center>
		<div class="active">
				<c:choose>
				<c:when test="${page.totalCount==0}">
					没有搜索到相关数据
				</c:when>
				<c:otherwise>
				<table class="imagetable">
				<tr>
					<th>会员编号</th>
					<th>会员姓名</th>
					<th>会员手机</th>
					<th colspan="2">操作</th>
				</tr>
				<c:forEach items="${page.result}" var="member">
				<tr>
				 	<td>${member.member_Id}</td>
				 	<td>${member.member_Name}</td>
				 	<td>${member.member_Phone}</td>
				 	<%--<td><input type = "button" value = "修改" class = "button" onclick = "subF('${member.member_Id}')"/></td>
				 	<td><input type = "button" value = "退会" class = "button" onclick = "return verifydelete()"/></td>
				 --%>
				 	<td><a class = "button" href="javascript:subF('${member.member_Id}')" >修改</a></td>
				 	<td>
				 	<c:choose>
				 		<c:when test="${member.state==1}">
				 			<a  class = "button" href="javascript:deleteMember('${member.member_Id}','${pageContext.request.contextPath}/association/deleteMember','${pageContext.request.contextPath}/association/findMembers',${page.pageNo})" >退会</a>
				 		</c:when>
				 		<c:otherwise>
				 			已退会
				 		</c:otherwise>
				 	</c:choose>
				 	</td>
				 	<%--<td><a  class = "button" onclick = "">查看</a></td>
				 --%></tr>
				 
				 </c:forEach>
				 </table>
				  <!--隐藏最大页数,搜索内容  -->
        	  <input type = "hidden" value = "${page.totalPage}" id = "totalPage" >     
              <input type ="hidden" name = "selected" id = "selected" value = ${selected} >
              <input type ="hidden" name = "searchkey" id = "searchkey" value = ${searchvalue} >
			<br>
			 第<span id="lbRow">${page.pageNo}</span> 页, 共<span id="lbpage">${page.totalPage}</span> 页, 
			 共<span id="lbRecord">${page.totalCount}</span> 条记录,
			 <c:if test="${page.pageNo!=1}">
			 	<a href="javascript:goPage('${pageContext.request.contextPath}/association/findMembers',${page.pageNo-1})">上一页</a> 
			 </c:if>
			 <c:if test="${page.pageNo!=page.totalPage}">
			 	<a href="javascript:goPage('${pageContext.request.contextPath}/association/findMembers',${page.pageNo+1})">下一页</a> 
			 </c:if>
			 
			 转到<input name="txtlink" type="text" style="width:29px;" id = "pageNo" value="${page.pageNo}"/> 页
			 <a id="link" tabindex="1" href="javascript:goPage('${pageContext.request.contextPath}/association/findMembers',0)">跳转</a><br />
			<br/>
				</c:otherwise>
				</c:choose>
			
			
		</div>
	</center>
  </body>

</html>
