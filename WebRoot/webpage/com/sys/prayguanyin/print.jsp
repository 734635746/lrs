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
	<script src="${pageContext.request.contextPath}/resources/js/calendar.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
function generateWenshu(){
		var solarstart = document.getElementById("solarstart").value;
		var solarend = document.getElementById("solarend").value;
		if(solarstart == ""){
			alert("请选择起始日期");
			return;
		}else if(solarend == ""){
			alert("请选择终止日期");
			return;
		}
		$.ajax({
	          url:"${pageContext.request.contextPath}/prayguanyinController.do?printPrayguanyinWenshu",
	          type:"post",
	          dataType:"text",
	          data:{
	        	solarstart:solarstart,
	        	solarend:solarend
	          },
	          success:function(responseText){
	          /* alert("responseText"); */
	        	document.getElementById("count1").value=responseText;
	        	/* if(responseText == "已生成牌位"){
	        		
	        	} */
	        	if(responseText == "找不到适合条件的牌位"){
	        		alert("找不到适合条件的牌位");
	        		return ;
	        	}else{
	        		alert("已生成文疏");
	        		window.location.href = "${pageContext.request.contextPath}" + responseText;
	        		return ;
	        	}
	        	getData();
	          },
	          error:function(){
	            alert("system error");
	          }
	        }); 
		
	}

	
	function getData(){
		var count1  = document.getElementById("count1").value;
		var ritualtype =  document.getElementById("type").value;
		var size = document.getElementById("size").options[document.getElementById("size").selectedIndex].value;
		print(ritualtype,size,count1,'tmp_table');
	}
	
	function changeSolarToLunar(currentNode, lunarInput){
 	 	//alert("yes");
 	 	var solardate = currentNode.value;
 	 	//console.log(solardate);
 	 	var arr = new Array();
 	 	arr = solardate.split("-");
 	 	//alert(arr[0]);
 	 	var lunar = calendar.solar2lunar(arr[0], arr[1], arr[2]);
 	 	//alert('农历：'+lunar.lYear + '年' +lunar.IMonthCn+lunar.IDayCn);
 	 	//console.log(currentNode);
 	 	//var lunardate = currentNode.parentNode.parentNode.children[4].children[0];
 	 	//console.log(lunardate);
 	 	
 	 	console.log(lunarInput);
 	 	//lunardate.value = lunar.lYear + "年" +lunar.IMonthCn+lunar.IDayCn;
 	 	$(lunarInput).val(lunar.lYear + '年' +lunar.IMonthCn+lunar.IDayCn);
 	 }
	
	//获取当前日期并设置为打印起始日期
	Date.prototype.format = function(format) { 
	       var date = { 
	              "M+": this.getMonth() + 1, 
	              "d+": this.getDate(), 
	              "h+": this.getHours(), 
	              "m+": this.getMinutes(), 
	              "s+": this.getSeconds(), 
	              "q+": Math.floor((this.getMonth() + 3) / 3), 
	              "S+": this.getMilliseconds() 
	       }; 
	       if (/(y+)/i.test(format)) { 
	              format = format.replace(RegExp.$1, (this.getFullYear() + '').substr(4 - RegExp.$1.length)); 
	       } 
	       for (var k in date) { 
	              if (new RegExp("(" + k + ")").test(format)) { 
	                     format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? date[k] : ("00" + date[k]).substr(("" + date[k]).length)); 
	              } 
	       } 
	       return format; 
	} 
	var d = new Date().format('yyyy-MM-dd'); 
	
	window.onload = function () {
	    var solardate = new Date().format('yyyy-MM-dd');

	    var arr = new Array();
 	 	arr = solardate.split("-");
 	 	var lunar = calendar.solar2lunar(arr[0], arr[1], arr[2]);
 	 	
	    document.getElementById("solarstart").value = solardate;
	    document.getElementById("lunarstart").value = lunar.lYear + '年' +lunar.IMonthCn+lunar.IDayCn;
	}
	</script>
	<style>
		label{display:inline-block;width:250px;text-align:right}
	</style>
  </head>
  

  <body>
  <input type="hidden" value="" id="count1">
  <input type="hidden" value="1" id="flag">
  <input type="hidden" value="${type}" id="type">
   <input type="hidden" value="${name}" id="name">
   <center>
    打印上供文疏
	<br>
	<div>
  	<!-- <select id="PrinterList" size="1" style='width:300px' onchange="selectPrinter()"></select> -->
	</div>
  <br><br><br><br>
	 <p>
      <label>请先选择举行上供的时间段:</label><input type="date" name="solarstart" id="solarstart" onchange="changeSolarToLunar(this,'#lunarstart');">----<input type="date" name="solarend" id="solarend" onchange="changeSolarToLunar(this, '#lunarend');">
     </p>
      <br>
     <p>
      <label>农历时间段:</label><input type="text" id="lunarstart" placeholder="选择公历自动生成农历">----<input type="text" id="lunarend" placeholder="选择公历自动生成农历">
     </p>
      <br><br>
      <input type="button" class="button" value="打印文疏"  onclick="generateWenshu()"> 
     </center>
      
  </body>
  </html>


