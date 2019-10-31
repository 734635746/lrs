<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>My JSP 'printSuccess.jsp' starting page</title>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
   <%--  //<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/jquery.js"> --%>
	<script src='http://127.0.0.1:8000/CLodopfuncs.js'></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/printer.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/cliffordtablet.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
	
	function generateTmp(){
		
		var start = document.getElementById("start").value;
		var end = document.getElementById("end").value;
		var type = document.getElementById("type").value;
		var ritualtype = document.getElementById("name").value;
		var size = document.getElementById("size").options[document.getElementById("size").selectedIndex].value;
		var dharmaname = document.getElementById("dharmaname").options[document.getElementById("dharmaname").selectedIndex].value;
		var flag = document.getElementById("flag").value;
		var all = 0;
		if(end == "" && start == ""){
			alert("打印全部");
			all = 1;	
		}
		else{
			if(parseInt(start) > parseInt(end) || end == 0 || start == 0){
				alert("请输入合法，或者不能输入序号为0");
				return;
			}
		}
		
		/* alert(dharmaname == "");  */
		
		$.ajax({
	          url:"${pageContext.request.contextPath}/tmp_wenshuController.do?printWenshu",
	          type:"post",
	          dataType:"text",
	          data:{
	        	ritualtype:ritualtype,
	        	size:size,
	        	start:start,
	        	end:end,
	        	flag:flag,
	        	type:type,
	        	dharmaname:dharmaname,
	        	all:all
	          },
	          success:function(responseText){
	        	
	        	if(responseText == "找不到适合条件的牌位"){
	        		alert("找不到适合条件的牌位");
	        		return ;
	        	}else{
	        		alert("已生成祈福文疏");
		        	window.location.href = "${pageContext.request.contextPath}" + responseText;
	        	}
	        	//getData();
	          },
	          error:function(){
	            alert("system error");
	          }
	        }); 
		
	}
	
		function downloadFile(url) {
			try {
				var elemIF = document_createElement_x("iframe");
				elemIF.src = url;
				elemIF.style.display = "none";
				document.body.a(elemIF);
			} catch (e) {

			}
		}

		function getData() {
			var count1 = document.getElementById("count1").value;
			var ritualtype = document.getElementById("type").value;
			var size = document.getElementById("size").options[document
					.getElementById("size").selectedIndex].value;
			print(ritualtype, size, count1, 'tmp_table');
			//print('tombsweepfes','小',10,'tmp_table');
		}
	</script>
  </head>
  

  <body>
  <input type="hidden" value="" id="count1">
  <input type="hidden" value="${flag}" id="flag">
  <input type="hidden" value="${type}" id="type">
   <input type="hidden" value="${name}" id="name">
    打印${name}文疏
	<br>
  <br><br><br><br><br>
	法事类型： ${name} &nbsp;&nbsp;  
      牌位大小：<select style="width:50px" name="size" id="size"> 
      	<option value="大">大</option>
        <option value="拈香">拈香</option>       
           
         
      </select> &nbsp;&nbsp;  
    法师：<select style="width: 120px" name="dharmaname" id="dharmaname">
				<option value="">--- 请选择法师 ---</option>
				<c:forEach var="pravrajanamemberEntity" items="${pravrajanamemberEntityList}" varStatus="status">
					<option value="${pravrajanamemberEntity.dharmaname}">${pravrajanamemberEntity.dharmaname}</option>
				</c:forEach>
	</select>&nbsp;&nbsp;  
      文疏序号：<input style="width:50px" type="text" id="start">----<input style="width:50px" type="text" id="end">
      <center>
      <br><br>
      <input type="button" class="button" value="生成文疏"  onclick="generateTmp()"> 
     </center>
      
  </body>
  </html>


