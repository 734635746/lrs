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
			
			var afternoonName = sp[0] + "Afternoon" + sp[1];
			var $browsers = $("input[name=" + afternoonName + "]"); 
			$browsers.attr("checked",false); 
		}
		function mySub(){
			
			var length = document.getElementById('length').value;
			
			var myselect=document.getElementById("month");
			var index=myselect.selectedIndex ;           
			var month = myselect.options[index].value; //获取月份
			
			var allRecords = "";
			for(var i = 0 ; i < length ; i ++){
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
			var year = "2016";
			var r=window.confirm("确定填写" + month + "的金刚经考勤信息吗？");
	 		if (r==true)
	 		{
	 			window.location.href = "${pageContext.request.contextPath}/attendanceController.do?addDiamodSutraRecord&allRecords=" + encodeURI(encodeURI(allRecords)) + "&dharmaname=" + encodeURI(encodeURI(dharmaname)) + "&month=" + encodeURI(encodeURI(month)) + "&year=" + year;
	 			return true;
	 		}
	 		else
	 		{
	 		  return false;
	 		}
			
		}
		
		function getEverySaturday(month){
			var myselect=document.getElementById("year");
			var index=myselect.selectedIndex ;           
			var year = myselect.options[index].value; 
			var url = "attendanceController.do?calRegularTime&month=" + encodeURIComponent(encodeURIComponent(month)) + "&year=" + year;
			$.ajax({
				type : 'POST',
				url : url,
				success : function(data) {

					
					var res = data.split(";");
					document.getElementById("length").value = res.length;
					var inner = "";
					for(var i = 0;i < 2;i++){
						var oneinner = "";
						oneinner="<div style=\" float:left; width:33%;  height:100px;\"><table class = \"imagetable\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\"><tr><th>日期</th><th>请假事由</th></tr>";
						for(var j = (i*6); j < (i*6 + 6);j ++){
							oneinner += "<tr><td colspan=\"1\" rowspan=\"2\" width=\"50\"><input style=\"width:50px;\" type = \"text\" id = \"" + j  + "\" value=\"" + res[j] + "\"readonly>"
							+ "<br><br><input type = \"button\" class = \"button\" value = \"重置\" onclick = \"myReset('reason|" + j + "')\"></td>";
							oneinner += "<td width=\"200\">";
							oneinner += "事假<input type=\"radio\" name=\"reasonMorning" +j  + "\" value=\"1\" />";
							oneinner +=	"迟到<input type=\"radio\" name=\"reasonMorning" + j + "\" value=\"2\" />";
							oneinner += "早退<input type=\"radio\" name=\"reasonMorning" + j + "\" value=\"3\" />";
							oneinner += "缺课<input type=\"radio\" name=\"reasonMorning" + j + "\" value=\"4\" />";
							oneinner +=	"私假<input type=\"radio\" name=\"reasonMorning" + j + "\" value=\"5\" />";
							oneinner +=	"病假<input type=\"radio\" name=\"reasonMorning" + j + "\" value=\"6\" />";
							oneinner +=	"外出<input type=\"radio\" name=\"reasonMorning" + j + "\" value=\"7\" />";
							oneinner += "</td>";
							oneinner += "</tr>";
							oneinner += "<tr></td>";
							oneinner += "<td width=\"200\">";
							oneinner += "事假<input type=\"radio\" name=\"reasonNoon" + j  + "\" value=\"1\" />";
							oneinner +=	"迟到<input type=\"radio\" name=\"reasonNoon" + j + "\" value=\"2\" />";
							oneinner += "早退<input type=\"radio\" name=\"reasonNoon" + j + "\" value=\"3\" />";
							oneinner += "缺课<input type=\"radio\" name=\"reasonNoon" + j + "\" value=\"4\" />";
							oneinner +=	"私假<input type=\"radio\" name=\"reasonNoon" + j + "\" value=\"5\" />";
							oneinner +=	"病假<input type=\"radio\" name=\"reasonNoon" + j + "\" value=\"6\" />";
							oneinner +=	"外出<input type=\"radio\" name=\"reasonNoon" + j + "\" value=\"7\" />";
							oneinner += "</td>";
							oneinner += "</tr>";
						}
						oneinner += "</table>" ;
						oneinner += "</div>" ;
						inner += oneinner;
						
						
						
						
					}

					var lastinner="<div style=\" float:left; width:33%;  height:100px;\"><table class = \"imagetable\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\"><tr><th>日期</th><th>请假事由</th></tr>";
					for(var i = 12;i < res.length;i++){
						lastinner += "<tr><td colspan=\"1\" rowspan=\"2\" width=\"50\"><input style=\"width:50px;\" type = \"text\" id = \"" + i  + "\" value=\"" + res[i] + "\" readonly>"
						+ "<br><br><input type = \"button\" class = \"button\" value = \"重置\" onclick = \"myReset('reason|" + i + "')\"></td>";
						lastinner += "<td width=\"200\">";
						lastinner +=    "事假<input type=\"radio\" name=\"reasonMorning" + i  + "\" value=\"1\" />";
						lastinner +=	"迟到<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"2\" />";
						lastinner +=    "早退<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"3\" />";
						lastinner += 	"缺课<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"4\" />";
						lastinner +=	"私假<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"5\" />";
						lastinner +=	"病假<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"6\" />";
						lastinner +=	"外出<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"7\" />";
						lastinner += "</td>";
						lastinner += "</tr>";
						lastinner += "<tr></td>";
						lastinner += "<td width=\"200\">";
						lastinner +=    "事假<input type=\"radio\" name=\"reasonMorning" + i  + "\" value=\"1\" />";
						lastinner +=	"迟到<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"2\" />";
						lastinner +=    "早退<input type=\"radio\" name=\"reasonMorning" + i + "\" value=\"3\" />";
						lastinner += 	"缺课<input type=\"radio\" name=\"reasonNoon" + i + "\" value=\"4\" />";
						lastinner +=	"私假<input type=\"radio\" name=\"reasonNoon" + i + "\" value=\"5\" />";
						lastinner +=	"病假<input type=\"radio\" name=\"reasonNoon" + i + "\" value=\"6\" />";
						lastinner +=	"外出<input type=\"radio\" name=\"reasonNoon" + i + "\" value=\"7\" />";
						lastinner += "</td>";
						lastinner += "</tr>" ;  	
					}
					
					lastinner += "</table>" ;
					lastinner += "</div>" ;
					inner += lastinner;
					document.getElementById("show").innerHTML = inner;
					
					
					
				}
			});
		}
		
	
		
	</script>
  </head>
  
  <body>
  	<input type = "hidden" id = "dharmaname" value = "${dharmaname}">
  	<input type = "hidden" id = "length" value = "">
   	录入${dharmaname}的考勤记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	选择年份： <select name="year" id="year">
					    <option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	选择月份：  
   	<select name="month" id="month" onchange="getEverySaturday(this.value);">
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
	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type = "button" name = "mysubmit" class = "button" value = "确认提交" onclick = "mySub()"><br><br>
	
	<div id="show" style="float:left; width:100%;"></div>
  </body>
</html>
