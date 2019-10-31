<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
     <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		window.onload=function(){
			setLunarDate();
		}
		var current = 0;
		var lunarDayTmp = new Array("初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三",
			    "十四","十五","十六", "十七", "十八","十九","廿十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","卅十");
		function setLunarDate(){
			
			for(var i  = 1;i <= 30 ;i ++){
				var lunardate = changePattern(i);
				document.getElementById(i.toString()).value = lunardate;
			}
		}
		function changePattern(number){
			var lunarDay=new Array("初一","初二","初三","初四","初五","初六","初七","初八","初九","初十","十一","十二","十三",
				    "十四","十五","十六", "十七", "十八","十九","廿十","廿一","廿二","廿三","廿四","廿五","廿六","廿七","廿八","廿九","卅十");
			return lunarDay[number - 1];
		}
		function myReset(name){
			var sp = name.split("|");
			var morningName = sp[0] + "Morning" + sp[1];
			var $browsers = $("input[name=" + morningName + "]"); 
			$browsers.attr("checked",false); 
			
			var noonName = sp[0] + "Noon" + sp[1];
			var $browsers = $("input[name=" + noonName + "]"); 
			$browsers.attr("checked",false); 
			
		}
		function mySub(){
			
			var length = document.getElementById('length').value;
			
			var month = document.getElementById("month1").value; //获取月份
			
			var myselect1=document.getElementById("ritualtype");
			var index1=myselect1.selectedIndex ;           
			var ritualtype = myselect1.options[index1].value; 
			
			var allRecords = "";
			for(var i = current ; i < (parseInt(current) + parseInt(length)); i ++){
				var lunardate = document.getElementById(i.toString()).value;
				var oneRecord = "";
				var reasonMorning = "无";
				var reasonNoon = "无";
				
				var reasonMorningRadio = document.getElementsByName("reasonMorning" + i.toString());
		        for(var j = 0 ; j < reasonMorningRadio.length ; j++)
		        {
		            if(reasonMorningRadio[j].checked==true)
		            {
		            	reasonMorning = reasonMorningRadio[j].value;
		            }
		        }
		        var reasonNoonRadio = document.getElementsByName("reasonNoon" + i.toString());
		        for(var j = 0 ; j < reasonNoonRadio.length ; j++)
		        {
		            if(reasonNoonRadio[j].checked==true)
		            {
		            	reasonNoon = reasonNoonRadio[j].value;
		            }
		        }
				
				oneRecord += lunardate + ";" + reasonMorning + ";" + reasonNoon;
				allRecords += oneRecord + "0";
				
			}
			var dharmaname = document.getElementById("dharmaname").value;
			
			var myselect=document.getElementById("year");
			var index=myselect.selectedIndex ;           
			var year = myselect.options[index].value; 
			
			var r=window.confirm("确定填写" + month + "的法事考勤信息吗？");
	 		if (r==true)
	 		{
	 			window.location.href = "${pageContext.request.contextPath}/attendanceController.do?addReligiousRecord&allRecords=" + encodeURI(encodeURI(allRecords)) + "&dharmaname=" + encodeURI(encodeURI(dharmaname)) 
	 					+ "&month=" + encodeURI(encodeURI(month)) + "&year=" + year + "&ritualtype=" + encodeURI(encodeURI(ritualtype));
	 			return true;
	 		}
	 		else
	 		{
	 		  return false;
	 		}
			
		}
		
		function getHolddate(ritualtype){
			
			var myselect=document.getElementById("year");
			var index=myselect.selectedIndex ;           
			var year = myselect.options[index].value; 
			
			var url = "attendanceController.do?getHoldDate&ritualtype=" + encodeURIComponent(encodeURIComponent(ritualtype)) + "&year=" + year;
			$.ajax({
				type : 'POST',
				url : url,
				success : function(data) {
					var inner="<table class = \"imagetable\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\"><tr><th>日期</th><th>请假事由</th></tr>";
					var res = data.split(";");
					document.getElementById("month1").value = res[0].split(" ")[0];
					
					for(var m = 0;m < lunarDayTmp.length;m ++){
						if(lunarDayTmp[m] == res[0].split(" ")[1]){
							current = m;
							break;
						}
					}
					document.getElementById("length").value = res[1];
					for(var i = current;i < (parseInt(current) + parseInt(res[1]));i++){
						inner += "<tr><td colspan=\"1\" rowspan=\"2\" width=\"50\"><input style=\"width:50px;\" type = \"text\" id = \"" + i  + "\" value=\"" + lunarDayTmp[i] + "\" readonly>"
						+ "<br><br><input type = \"button\" class = \"button\" value = \"重置\" onclick = \"myReset('reason|" + i + "')\"></td>";
						inner += "<td width=\"340\">";
						inner += 	"事假<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"1\" />";
						inner += 	"迟到<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"2\" />";
						inner += 	"早退<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"3\" />";
						inner += 	"缺课<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"4\" />";
						inner +=	"私假<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"5\" />";
						inner +=	"病假<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"6\" />";
						inner +=	"外出<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"7\" />";
						inner += "</td>";
						inner += "</tr>";
						inner += "<tr></td>";
						inner += "<td width=\"340\">";
						inner += 	"事假<input type=\"radio\" name=\"reasonNoon" + i + "\" value=\"1\" />";
						inner += 	"迟到<input type=\"radio\" name=\"reasonNoon" + i + "\" value=\"2\" />";
						inner += 	"早退<input type=\"radio\" name=\"reasonNoon" + i + "\" value=\"3\" />";
						inner += 	"缺课<input type=\"radio\" name=\"reasonNoon" + i + "\" value=\"4\" />";
						inner +=	"私假<input type=\"radio\" name=\"reasonNoon" + i + "\" value=\"5\" />";
						inner +=	"病假<input type=\"radio\" name=\"reasonNoon" + i + "\" value=\"6\" />";
						inner +=	"外出<input type=\"radio\" name=\"reasonNoon" + i + "\" value=\"7\" />";
						inner += "</td>";
						inner += "</tr>";    	
					}
					inner += "</select>";
					document.getElementById("show").innerHTML = inner;
					
				},
			error: function(data) { 
				alert(data); 
			} 
				
			});
		}
		
	
		
	</script>
  </head>
  
  <body>
  	<input type = "hidden" id = "dharmaname" value = "${dharmaname}">
  	<input type = "hidden" id = "length" value = "">
   	录入${dharmaname}的法事考勤记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	选择年份： <select name="year" id="year">
					    <option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	选择法事类型： <select name="ritualtype" id="ritualtype" onchange="getHolddate(this.value);">
   						<option value="">--- 请选择法事类型---</option>
					    <option value="弥勒佛诞">弥勒佛诞</option>
						<option value="观音开库">观音开库</option>
						<option value="观音诞">观音诞</option>
						<option value="释尊诞">释尊诞</option>
						<option value="观音成道">观音成道</option>
						<option value="观音出家">观音出家</option>
						<option value="药师诞">药师诞</option>
						<option value="释尊成道">释尊成道</option>
						<option value="清明节">清明节</option>
						<option value="盂兰节">盂兰节</option>
						<option value="地藏诞">地藏诞</option>
						<option value="弥陀诞">弥陀诞</option>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	<input type = "hidden" name = "month1" id = "month1" value = "">
   	<input type = "button" name = "mysubmit" class = "button" value = "确认提交" onclick = "mySub()"><br><br>
	
	<div style=" float:left; width:33%;  height:100px;">
	
	
	</div>
	<div id="show" style="overflow-y:hidden"></div>
  </body>
</html>
