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
	var attendanceType = document.getElementById("attendanceType").options[document.getElementById("attendanceType").selectedIndex].value;
	var name = document.getElementById("name").options[document.getElementById("name").selectedIndex].value;
	var myselect=document.getElementById("year");
	var index=myselect.selectedIndex ;           
	var year = myselect.options[index].value; 
	
	if(month == "" || attendanceType == ""){
		alert("请选择月份或者考勤类型");
		return ;
	}
	
	var url = "attendanceController.do?statisticsResult&month=" 
				+ encodeURIComponent(encodeURIComponent(month)) 
				+ "&attendanceType=" 
				+ encodeURIComponent(encodeURIComponent(attendanceType)) 
				+ "&name=" + encodeURIComponent(encodeURIComponent(name))
				+ "&year=" + year;
	$.ajax({
		type : 'POST',
		url : url,
		success : function(data) {
			json = JSON.parse(data);
			var inner="<table class = \"imagetable\" border=\"1\"><tr><th>法名</th><th>事假</th><th>迟到</th><th>早退</th><th>缺课</th><th>私假</th><th>病假</th><th>外出</th></tr>";
			for(var i = 0;i < json.length;i++){
				inner += "<tr><th>" + json[i].name + "</th>";
				inner += "<td>" + json[i].casual + "</td>";
				inner += "<td>" + json[i].late + "</td>";
				inner += "<td>" + json[i].early + "</td>";
				inner += "<td>" + json[i].absent + "</td>";
				inner += "<td>" + json[i]._private + "</td>";
				inner += "<td>" + json[i].sick + "</td>";
				inner += "<td>" + json[i].out + "</td>";
				inner += "</tr>";
			}
			
			inner += "</table><br>";
			/* inner += "<input type=\"button\" class=\"button\" value=\"确定交接\" onclick=\"comfirmStat()\">"; */
			document.getElementById("show").innerHTML = inner;
			
			
		}
	});
	
}
function comfirmStat(){
	var start = document.getElementById("start").value;
	var end = document.getElementById("end").value;
	var memberName = document.getElementById("member").options[document.getElementById("member").selectedIndex].value;
	window.location.href = "${pageContext.request.contextPath}/tmp_statController.do?comfirmStat&memberName=" + encodeURIComponent(encodeURIComponent(memberName)) +  "&start=" + start + "&end=" + end;
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
			
			考勤类型 ： 
			<select name="attendanceType" id="attendanceType">
   						<option value="">--- 请选择考勤类型---</option>
					    <option value="早晚课诵">早晚课诵</option>
						<option value="大悲共修">大悲共修</option>
						<option value="金刚经">金刚经</option>
						<option value="二时临斋">二时临斋</option>
			</select>&nbsp;
			
			法名：
			<select style="width: 100px" name="name" id="name">
				<option value="">--- 请选择法名---</option>
				<c:forEach var="pravrajanamember" items="${pravrajanamemberEntityList}" varStatus="status">
					<option value="${pravrajanamember.dharmaname }">${pravrajanamember.dharmaname}</option>
				</c:forEach>
			</select>
			<input type="button" value="查询" class="button" onclick="getData()">
			<br><br>
			<center>
			<div id="show" style="overflow-y:hidden"></div>
			</center>
			
			
		
 </body>