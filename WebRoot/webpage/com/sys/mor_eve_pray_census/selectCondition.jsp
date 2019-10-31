<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title></title>
 <%--  <t:base type="jquery,easyui,tools,DatePicker"></t:base> --%>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button1.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button2.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table1.css">
  <script src="${pageContext.request.contextPath}/plug-in/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script>
/* function goTo(ritualtype,name){
	window.location.href = "${pageContext.request.contextPath}/doritualinfoController.do?tabletcount&type="+ritualtype +"&name=" + name;
} */
function getData(){
	var month = document.getElementById("month").options[document.getElementById("month").selectedIndex].value;
	var name = document.getElementById("name").options[document.getElementById("name").selectedIndex].value;
	var year = "2016";
	
	if(month == ""){
		alert("请选择月份或者考勤类型");
		return ;
	}
	var url = "attendanceController.do?mor_eve_pray_census&month=" + encodeURIComponent(encodeURIComponent(month)) + "&name=" + encodeURIComponent(encodeURIComponent(name))+ "&year=" + year
	$.ajax({
		type : 'POST',
		url : url,
		success : function(data) {
			json = JSON.parse(data);
			var inner="<table class = \"imagetable\" border=\"1\"><tr><th>法名</th><th colspan=\"3\">普佛</th><th colspan=\"3\">上供</th></tr> <tr><th></th><th>应参加</th><th>实参加</th><th>缺堂数</th><th>应参加</th><th>实参加</th><th>缺堂数</th></tr>";
			for(var i = 0;i < json.length;i++){
				inner += "<tr><td>" + json[i].name + "</td>";
				inner += "<td>" + json[i].all_pforr + "</td>";
				inner += "<td>" + json[i].join_pforr + "</td>";
				inner += "<td>" + json[i].absent_pforr + "</td>";
				inner += "<td>" + json[i].all_pray + "</td>";
				inner += "<td>" + json[i].join_pray + "</td>";
				inner += "<td>" + json[i].absent_pray + "</td>";
				inner += "</tr>";
			}
			
			inner += "</table><br>";
			/* inner += "<input type=\"button\" class=\"button\" value=\"确定交接\" onclick=\"comfirmStat()\">"; */
			document.getElementById("show").innerHTML = inner;
			
			
		}
	});
	
}
</script>
 </head>
 <body style="overflow-y:hidden" scroll="no">
			
			
			<br><br>
			农历月份：
			<select name="month" id="month">
   						<option value="">--- 请选择月份---</option>
					    <option value="正月">正月</option>
						<option value="二月">二月</option>
						<option value="三月">三月</option>
						<option value="四月">四月</option>
						<option value="五月">五月</option>
						<option value="六月">六月</option>
						<option value="七月">七月</option>
						<option value="八月">八月</option>
						<option value="九月">九月</option>
						<option value="十月">十月</option>
						<option value="十一月">十一月</option>
						<option value="腊月">腊月</option>
			</select>&nbsp;&nbsp;&nbsp;&nbsp;
			
			
			法名：
			<select style="width: 150px" name="name" id="name">
				<option value="">--- 请选择法名---</option>
				<c:forEach var="pravrajanamember" items="${pravrajanamemberEntityList}" varStatus="status">
					<option value="${pravrajanamember.dharmaname }">${pravrajanamember.dharmaname}</option>
				</c:forEach>
			</select>&nbsp;
			<input type="button" value="查询" class="button" onclick="getData()">
			<br><br>
			<center>
			<div id="show" style="overflow-y:hidden"></div>
			<!-- <table class = "imagetable" border="1">
				<tr>
					<th>法名</th>
					<th colspan="3">普佛</th>
					<th colspan="3">上供</th>
				</tr> 
				<tr>
					<th></th>
					<th>缺课</th><th>私假</th><th>病假</th>
					<th>缺课</th><th>私假</th><th>病假</th>
				</tr>
				<tr>
					<td>姓名</td>
					<td>1</td><td>2</td><td>3</td>
					<td>4</td><td>5</td><td>6</td>
				</tr>
				
			</table>
			 -->
			
		
 </body>