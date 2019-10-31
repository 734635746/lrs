<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
    
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
		};
		
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
			var  myselect=document.getElementById("month");
			var index=myselect.selectedIndex ;           
			var month = myselect.options[index].value; //获取月份
			
			var allRecords = "";
			for(var i = 1 ; i <= 30 ; i ++){
				var lunardate = document.getElementById(i.toString()).value;
				var oneRecord = "";
				var reasonMorning = "无";
				var reasonNoon = "无";
				var reasonAfternoon = "无";
				
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
		        var reasonAfternoonRadio = document.getElementsByName("reasonAfternoon" + i.toString());
		        for(var j = 0 ; j < reasonAfternoonRadio.length ; j++)
		        {
		            if(reasonAfternoonRadio[j].checked==true)
		            {
		            	reasonAfternoon = reasonAfternoonRadio[j].value;
		            }
		        }

		        
				
				oneRecord += lunardate + ";" + reasonMorning + ";" + reasonNoon + ";" + reasonAfternoon;
				allRecords += oneRecord + "0";
				
			}
			var dharmaname = document.getElementById("dharmaname").value;
			var myselect=document.getElementById("year");
			var index=myselect.selectedIndex ;           
			var year = myselect.options[index].value; 
			
			var r=window.confirm("确定填写" + month + "的早晚课诵考勤信息吗？");
	 		if (r==true)
	 		{
	 			window.location.href = "${pageContext.request.contextPath}/attendanceController.do?addMorningAndEveningPujaRecord&allRecords=" + encodeURI(encodeURI(allRecords)) + "&dharmaname=" + encodeURI(encodeURI(dharmaname)) + "&month=" + encodeURI(encodeURI(month)) + "&year=" + year;
	 			return true;
	 		}
	 		else
	 		{
	 		  return false;
	 		}
			
		}
		
	</script>
  </head>
  
  <body>
  	<input type = "hidden" id = "dharmaname" value = "${dharmaname}">
   	录入${dharmaname}的考勤记录&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	选择年份： <select name="year" id="year">
					    <option value="2016">2016</option>
						<option value="2017">2017</option>
						<option value="2018">2018</option>
						<option value="2019">2019</option>
	</select>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	选择月份：  
   	<select name="month" id="month">
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
	<div>
	<div style=" float:left; width:33%;  height:100px;">
	<table id = "" class="imagetable"  cellspacing="0" cellpadding="0">
		<tr>
			<th>日期</th>
			<th>请假事由</th>
		</tr>
	<c:forEach var="i" begin="1" end="10" varStatus="status">
       
       	<tr>
             <td colspan="1" rowspan="3" width="50"><input style="width:50px;" type = "text" id = "${i}" value = "" readonly>
             	<br><br><input type = "button" class = "button" value = "重置" onclick = "myReset('reason|${i}')"></td>
             <td width="200">
             	事假<input type="radio" name="reasonMorning${i}" value="1" />
       			迟到<input type="radio" name="reasonMorning${i}" value="2" />
       			早退<input type="radio" name="reasonMorning${i}" value="3" />
             	缺课<input type="radio" name="reasonMorning${i}" value="4" />
       			私假<input type="radio" name="reasonMorning${i}" value="5" />
       			病假<input type="radio" name="reasonMorning${i}" value="6" />
       			外出<input type="radio" name="reasonMorning${i}" value="7" />
             </td>
        </tr>
        <tr>
             <td width="200">
             	事假<input type="radio" name="reasonNoon${i}" value="1" />
       			迟到<input type="radio" name="reasonNoon${i}" value="2" />
       			早退<input type="radio" name="reasonNoon${i}" value="3" />
             	缺课<input type="radio" name="reasonNoon${i}" value="4" />
       			私假<input type="radio" name="reasonNoon${i}" value="5" />
       			病假<input type="radio" name="reasonNoon${i}" value="6" />
       			外出<input type="radio" name="reasonNoon${i}" value="7" />
             </td>
        </tr>
        <tr>
                    
             <td width="200">
           		  事假<input type="radio" name="reasonAfternoon${i}" value="1" />
       			迟到<input type="radio" name="reasonAfternoon${i}" value="2" />
       			早退<input type="radio" name="reasonAfternoon${i}" value="3" />
             	缺课<input type="radio" name="reasonAfternoon${i}" value="4" />
       			私假<input type="radio" name="reasonAfternoon${i}" value="5" />
       			病假<input type="radio" name="reasonAfternoon${i}" value="6" />
       			外出<input type="radio" name="reasonAfternoon${i}" value="7" />
             </td>
        </tr>
	</c:forEach>
	</table>
	</div>
	
		<div style=" float:left; width:33%; height:100px;">
		<table id = "" class="imagetable"  cellspacing="0" cellpadding="0">
		<tr>
			<th>日期</th>
			<th>请假事由</th>
		</tr>
	<c:forEach var="i" begin="11" end="20" varStatus="status">
       
       <tr>
             <td colspan="1" rowspan="3" width="50"><input style="width:50px;" type = "text" id = "${i}" value = "" readonly>
             	<br><br><input type = "button" class = "button" value = "重置" onclick = "myReset('reason|${i}')"></td>
             <td width="200">
             	事假<input type="radio" name="reasonMorning${i}" value="1" />
       			迟到<input type="radio" name="reasonMorning${i}" value="2" />
       			早退<input type="radio" name="reasonMorning${i}" value="3" />
             	缺课<input type="radio" name="reasonMorning${i}" value="4" />
       			私假<input type="radio" name="reasonMorning${i}" value="5" />
       			病假<input type="radio" name="reasonMorning${i}" value="6" />
       			外出<input type="radio" name="reasonMorning${i}" value="7" />
             </td>
        </tr>
        <tr>
             <td width="200">
             	事假<input type="radio" name="reasonNoon${i}" value="1" />
       			迟到<input type="radio" name="reasonNoon${i}" value="2" />
       			早退<input type="radio" name="reasonNoon${i}" value="3" />
             	缺课<input type="radio" name="reasonNoon${i}" value="4" />
       			私假<input type="radio" name="reasonNoon${i}" value="5" />
       			病假<input type="radio" name="reasonNoon${i}" value="6" />
       			外出<input type="radio" name="reasonNoon${i}" value="7" />
             </td>
        </tr>
        <tr>
                    
             <td width="200">
           		  事假<input type="radio" name="reasonAfternoon${i}" value="1" />
       			迟到<input type="radio" name="reasonAfternoon${i}" value="2" />
       			早退<input type="radio" name="reasonAfternoon${i}" value="3" />
             	缺课<input type="radio" name="reasonAfternoon${i}" value="4" />
       			私假<input type="radio" name="reasonAfternoon${i}" value="5" />
       			病假<input type="radio" name="reasonAfternoon${i}" value="6" />
       			外出<input type="radio" name="reasonAfternoon${i}" value="7" />
             </td>
        </tr>
	</c:forEach>
	</table>
	</div>
	
	<div style=" float:left; width:33%; height:100px;">
	<table id = "" class="imagetable" cellspacing="0" cellpadding="0">
		<tr>
			<th>日期</th>
			<th>请假事由</th>
		</tr>
	<c:forEach var="i" begin="21" end="30" varStatus="status">
       
       	<tr>
             <td colspan="1" rowspan="3" width="50"><input style="width:50px;" type = "text" id = "${i}" value = "" readonly>
             	<br><br><input type = "button" class = "button" value = "重置" onclick = "myReset('reason|${i}')"></td>
             <td width="200">
             	事假<input type="radio" name="reasonMorning${i}" value="1" />
       			迟到<input type="radio" name="reasonMorning${i}" value="2" />
       			早退<input type="radio" name="reasonMorning${i}" value="3" />
             	缺课<input type="radio" name="reasonMorning${i}" value="4" />
       			私假<input type="radio" name="reasonMorning${i}" value="5" />
       			病假<input type="radio" name="reasonMorning${i}" value="6" />
       			外出<input type="radio" name="reasonMorning${i}" value="7" />
             </td>
        </tr>
        <tr>
             <td width="200">
             	事假<input type="radio" name="reasonNoon${i}" value="1" />
       			迟到<input type="radio" name="reasonNoon${i}" value="2" />
       			早退<input type="radio" name="reasonNoon${i}" value="3" />
             	缺课<input type="radio" name="reasonNoon${i}" value="4" />
       			私假<input type="radio" name="reasonNoon${i}" value="5" />
       			病假<input type="radio" name="reasonNoon${i}" value="6" />
       			外出<input type="radio" name="reasonNoon${i}" value="7" />
             </td>
        </tr>
        <tr>
                    
             <td width="200">
           		  事假<input type="radio" name="reasonAfternoon${i}" value="1" />
       			迟到<input type="radio" name="reasonAfternoon${i}" value="2" />
       			早退<input type="radio" name="reasonAfternoon${i}" value="3" />
             	缺课<input type="radio" name="reasonAfternoon${i}" value="4" />
       			私假<input type="radio" name="reasonAfternoon${i}" value="5" />
       			病假<input type="radio" name="reasonAfternoon${i}" value="6" />
       			外出<input type="radio" name="reasonAfternoon${i}" value="7" />
             </td>
        </tr>
	</c:forEach>
	</table>
	</div>
	
	</div>
	<div>
	</div>
  </body>
</html>
