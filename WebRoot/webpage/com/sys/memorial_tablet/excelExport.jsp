<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>弥陀诞信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table3.css">
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  </head>
  <script type="text/javascript" charset="utf-8">
  	function getDan(building_name) {
  		if(building_name==''){
  			$('#demo').html("");
  			return;
  		}

  		var url = "memorial_tabletController.do?findDanName&building_name=" + encodeURIComponent(encodeURIComponent(building_name));
  		$.ajax({
  			type : 'POST',
  			url : url,
  			success : function(data) {
  				var inner = "<select style=\"width: 100px\" name=\"dan_name\" id=\"dan_name\">";
  				var res = data.split(";");
  				for(var i = 0;i < res.length-1;i++){
  					inner += "<option value=\"" + res[i] + "\">" + res[i] + "</option>";
  				}
  				inner += "</select>"
  				document.getElementById("demo").innerHTML = inner;
  				
  			}
  		});
  	}
  	
  	
  	function exportExcel(){
  		var building_name = document.getElementById("building_name").options[document.getElementById("building_name").selectedIndex].value;
  	  	var dan_name = document.getElementById("dan_name").options[document.getElementById("dan_name").selectedIndex].value;   
  		
          $.ajax({
          url:"memorial_tabletController.do?exportStatusXls",
          type:"post",
          dataType:"text",
          data:{
        	building_name:building_name/* building_name */,
        	dan_name:dan_name/* dan_name */
          },
          success:function(responseText){
        	  window.location.href="${pageContext.request.contextPath}/export/excel/"+responseText;
		  },
          error:function(){
            alert("system error");
          }
        }); 
 	}
  	
  </script>
  <body align="center">
  <input type = "hidden" id = "selectedTablet" name = "selectedTablet" value = "">
  <div class="easyui-layout" fit="true">
  <div  style="padding:1px;">
    				楼名 ：
			<select style="width: 100px" name="building_name" id="building_name" onchange="getDan(this.value);">
				<option value="">--- 请选择楼名 ---</option>
				<c:forEach var="buildingName" items="${minfoEntityList}" varStatus="status">
					<option value="${buildingName}">${buildingName}</option>
				</c:forEach>
			</select>&nbsp;&nbsp;
    	段名 ：<span id="demo"></span>&nbsp;&nbsp;
    	<input type = "button" value = "导出"  onclick = "exportExcel()" id = "myButton" class="button">
  <br><br>

    
   </div>
 </div>
   <br><br>
  </body>
</html>
