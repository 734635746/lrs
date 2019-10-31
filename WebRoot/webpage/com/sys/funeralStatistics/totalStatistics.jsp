<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title></title>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table1.css">
  <script src="${pageContext.request.contextPath}/plug-in/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script>
function getData(){
	
	var month = document.getElementById("month").options[document.getElementById("month").selectedIndex].value;
	var name = document.getElementById("name").options[document.getElementById("name").selectedIndex].value;
	var myselect=document.getElementById("year");
	var index=myselect.selectedIndex ;           
	var year = myselect.options[index].value; 
	if(month == "" || year == ""){
		alert("请选择月份或者年份");
		return ;
	}
	
	var url = "attendanceController.do?totalStatisticsResult&month=" + encodeURIComponent(encodeURIComponent(month)) + "&name=" + encodeURIComponent(encodeURIComponent(name))+ "&year=" + year;
	$.ajax({
		type : 'POST',
		url : url,
		success : function(data) {
			json = JSON.parse(data);
			var inner="<table class = \"imagetable\" border=\"1\"><tr><th>法名</th><th>总堂数</th><th>缺堂数</th><th>参堂数</th></tr>";
				for(var i = 0;i < json.length;i++){
					inner += "<tr><th>" + json[i].name + "</th>";
					inner += "<td>" + json[i].all + "</td>";
					inner += "<td>" + json[i].absent + "</td>";
					inner += "<td>" + json[i].join + "</td>";
					inner += "</tr>";
				}
			
			inner += "</table><br>";
			document.getElementById("show").innerHTML = inner;
		}
			
			
	});
}
	function exportXls(){
		var month = document.getElementById("month").options[document.getElementById("month").selectedIndex].value;
		var name = document.getElementById("name").options[document.getElementById("name").selectedIndex].value;
		var myselect=document.getElementById("year");
		var index=myselect.selectedIndex ;           
		var year = myselect.options[index].value; 
		if(month == "" || year == ""){
			alert("请选择月份或者年份");
			return ;
		}
		
		window.location.href = window.location.href = "${pageContext.request.contextPath}/attendanceController.do?exporTotalXls&month=" + encodeURIComponent(encodeURIComponent(month)) + "&name=" + encodeURIComponent(encodeURIComponent(name))
				+ "&year=" + year;
	}
	
</script>
 </head>
 <body style="overflow-y:hidden" scroll="no">
			
			
			<br><br>
			选择年份： <select name="year" id="year">
					    <option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
			</select>&nbsp;
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
			</select>&nbsp;
			
			法名：
			<select style="width: 120px" name="name" id="name">
				<option value="">--- 请选择法名---</option>
				<c:forEach var="pravrajanamember" items="${pravrajanamemberEntityList}" varStatus="status">
					<option value="${pravrajanamember.dharmaname }">${pravrajanamember.dharmaname}</option>
				</c:forEach>
			</select>
			<input type="button" value="查询" class="button" onclick="getData()">&nbsp;
			<input type="button" id = "excel" value="导出excel" class="button" onclick="exportXls()">
			<br><br>
			<center>
			<div id="show" style="overflow-y:hidden"></div>
			</center>
			
			
		
 </body>