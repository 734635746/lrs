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
	
	var url = "attendanceController.do?prayGuanyinStatisticsResult&month=" + encodeURIComponent(encodeURIComponent(month)) + "&name=" + encodeURIComponent(encodeURIComponent(name))+ "&year=" + year;
	$.ajax({
		type : 'POST',
		url : url,
		success : function(data) {
			json = JSON.parse(data);
			var inner="<table class = \"imagetable\" border=\"1\"><tr><th>法名</th><th>初一</th><th>初二</th><th>初三</th><th>初四</th><th>初五</th><th>初六</th><th>初七</th>"
			+"<th>初八</th><th>初九</th><th>初十</th><th>十一</th><th>十二</th><th>十三</th><th>十四</th><th>十五</th><th>十六</th><th>十七</th><th>十八</th><th>十九</th>"
			+"<th>廿十</th><th>廿一</th><th>廿二</th><th>廿三</th><th>廿四</th><th>廿五</th><th>廿六</th><th>廿七</th><th>廿八</th><th>廿九</th><th>卅十</th></tr>";
				for(var i = 0;i < parseInt(json.length)/30;i++){
					inner += "<tr><th>" + json[(parseInt(i)*30)].name + "</th>";
						for(var j = (parseInt(i)*30); j < ((parseInt(i)*30) + 30);j ++){
						inner += "<td>" + json[j].count + "</td>";
					}
					inner += "</tr>";
				}
			
			inner += "</table><br>";
			/* inner += "<input type=\"button\" class=\"button\" value=\"确定交接\" onclick=\"comfirmStat()\">"; */
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
		
		window.location.href = window.location.href = "${pageContext.request.contextPath}/attendanceController.do?exportXls&month=" + encodeURIComponent(encodeURIComponent(month)) + "&name=" + encodeURIComponent(encodeURIComponent(name))
				+ "&year=" + year + "&type=" + 3;
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
			<div id="show" style="overflow-y:hidden"></div>
			
			
		
 </body>