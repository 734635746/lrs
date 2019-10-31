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
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <script src="${pageContext.request.contextPath}/plug-in/My97DatePicker/WdatePicker.js"></script>
  <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
<script>
/* function goTo(ritualtype,name){
	window.location.href = "${pageContext.request.contextPath}/doritualinfoController.do?tabletcount&type="+ritualtype +"&name=" + name;
} */
function getMember(id) {
	if(id==''){
		$('#demo').html("");
		return;
	}

	var url = "tmp_statController.do?findMember&id=" + encodeURIComponent(encodeURIComponent(id));
	$.ajax({
		type : 'POST',
		url : url,
		success : function(data) {
			var inner = "<select style=\"width: 100px\" name=\"member\" id=\"member\">";
			var res = data.split(";");
			for(var i = 0;i < res.length-1;i++){
				inner += "<option value=\"" + res[i] + "\">" + res[i] + "</option>";
			}
			inner += "</select>"
			document.getElementById("demo").innerHTML = inner;  
			
		}
	});
}
function getData(){
	var start = document.getElementById("start").value;
	var end = document.getElementById("end").value;
	if(start != ""  && end == "" || start == "" && end != null || start == "" && end == ""){
		alert("请规范填写，不填写则默认为当天");
	}
	var departid = 1;
	//var memberName = document.getElementById("member").options[document.getElementById("member").selectedIndex].value;
	//var memberName = $.session.get("userName");
	//console.log('${userName}');
	var url = "tmp_statController.do?statResult&id=" + encodeURIComponent(encodeURIComponent(departid)) + "&memberName=" +encodeURIComponent(encodeURIComponent('${userName}'))+ "&start=" + start + "&end=" + end;
	$.ajax({
		type : 'POST',
		url : url,
		success : function(data) {
			json = JSON.parse(data);
			var inner="<table class = \"imagetable\" border=\"1\"><tr><th></th><th>现金</th><th>刷卡</th><th>支付宝</th><th>微信</th><th>其他</th><th>总计</th></tr>";
			inner += "<tr><th>数量</th>";
				
			inner += "<td>" + json[0].cash + "</td>";
			inner += "<td>" + json[0].card + "</td>";
			inner += "<td>" + json[0].alipay + "</td>";
			inner += "<td>" + json[0].weixin + "</td>";
			inner += "<td>" + json[0].other + "</td>";
			inner += "<td>" + json[0].total + "</td>";
			
			inner += "</tr>";
			inner += "<tr><th>总计</th>";
			
			inner += "<td>" + json[1].cash + "</td>";
			inner += "<td>" + json[1].card + "</td>";
			inner += "<td>" + json[1].alipay + "</td>";
			inner += "<td>" + json[1].weixin + "</td>";
			inner += "<td>" + json[1].other + "</td>";
			inner += "<td>" + json[1].total + "</td>";
			
			inner += "</tr>";
			inner += "</table><br>";
		//	inner += "<input type=\"button\" class=\"button\" value=\"确定交接\" onclick=\"comfirmStat()\">";
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
			时间段：<input style="width:100px;" type="text" id="start" name = "start" onclick="WdatePicker()">---
			<input style="width:100px;" type="text" id="end" name = "end" onclick="WdatePicker()">&nbsp;&nbsp;
<!-- 			<select style="width: 100px" name="depart" id="depart" onchange="getMember(this.value);"> -->
<!-- 				<option value="">--- 请选择部门 ---</option> -->
<!-- 				<c:forEach var="depart" items="${tsDeparts}" varStatus="status"> -->
<!-- 					<option value="${depart.id }">${depart.departname}</option> -->
<!-- 				</c:forEach> -->
<!-- 			</select>&nbsp; -->
<!-- 			员工姓名: <span id="demo"></span> -->
			<input type="button" value="查询" class="button" onclick="getData()">
			<br><br>
			<center>
			<div id="show" style="overflow-y:hidden"></div>
			</center>
			
			
		
 </body>