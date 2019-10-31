<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>功德堂信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table3.css">
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  </head>
  <script>
  	
  	 function queryTablet(){
  	    	  var building_name = encodeURI(document.getElementById("building_name").options[document.getElementById("building_name").selectedIndex].value);
  	    	  var dan_name = encodeURI(document.getElementById("dan_name").options[document.getElementById("dan_name").selectedIndex].value); 
  	    	  
  	          $.ajax({
  	          url:"memorial_tabletController.do?queryTablet",
  	          type:"post",
  	          dataType:"text",
  	          data:{
  	        	building_name:building_name/* building_name */,
  	        	dan_name:dan_name/* dan_name */
  	          },
  	          success:function(responseText){
  	        	var dataArray = eval(responseText);
  	        	var row_len = dataArray.length;
  	        	var col_len = dataArray[0].length;
  	        	$("#showTablet").html("");
  			    var str = "";
  			    str = "<table id = \"roomtable\" class=\"imagetable\" border = 1>";
  			    str += "<tr><th></th>"
  			    for(var i = 0;i < col_len-1;i ++){
  			    	str += "<th>" + (i+1) + "</th>"
  			    }
  			    str += "</tr>"
  			    
  			    for(var i = 0;i < row_len-1;i ++){
  			    	str += "<tr><th>" + (i + 1) + "</th>";
  			    	for(var j = 0;j < col_len-1;j ++){
  			    		if(dataArray[i+1][j+1] == 1){
  			    			str += "<td style=\"background-color:green\"><input type = \"checkbox\" value= \"" + i + "&" +  j + "\" name = \"tablet\" disabled=\"disabled\" id = \"tablet\" onclick=\'show()\'></td>"
  			    		}
  			    		else{
  			    			str += "<td style=\"background-color:yellow\"><input type = \"checkbox\" value=  \"" + i +  "&" + j + "\" name = \"tablet\" id = \"tablet\" onclick=\'show()\'></td>"
  			    		}
  			    	}
  			    	str += "</tr>"
  			    }
  			  str += "</table>";
  			  $("#showTablet").append(str);
  	          },
  	          error:function(){
  	            alert("system error");
  	          }
  	        }); 
  	 }
  	 var count = 0;
  	 function show(){
  		count = 0;
  		$("#tips").html("");
  		var str = "已经选择了 ";
  		var hiddenStr = "";
  		$("[name='tablet']:checked").each(function(){
  		     var row = $(this).val().split("&")[0]; 
		     var column = $(this).val().split("&")[1]; 
		     hiddenStr = (parseInt(row) + 1) + "@" + (parseInt(column) + 1)
		     str += (parseInt(row) + 1) + "排" + (parseInt(column) + 1) + "列" + ",";
		     count = count + 1;
		  });
  		$("#tips").html(str);
  		document.getElementById("selectedTablet").value = hiddenStr;
  	 }
  	//var dharmaname = 
  	 function mySubmit(){
  		 var building_name =   encodeURI(document.getElementById("building_name").value) ;
    	 var dan_name =   encodeURI(document.getElementById("dan_name").value) ;
  		 var selectedTablet = document.getElementById("selectedTablet").value;
  		 var id = document.getElementById("id").value;
  		 if(count > 1){
  			 alert("只能选择一个牌位！");
  			 return ;
  		 }else if(count<1){
  		 	 alert("必须选择一个牌位！");
  			 return ;
  		 }
  		 alert("已经选择了" + selectedTablet.split("@")[0] + "排" + selectedTablet.split("@")[1] + "列");
  		 window.location.href="${pageContext.request.contextPath}/memorial_tabletController.do?moveTablet&selectedTablet=" 
  		 + selectedTablet + "&buildingName=" + encodeURI(building_name) + "&dan=" + encodeURI(dan_name)
  		 + "&id=" + encodeURI(id);
  	 }
  	
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
  </script>
  <body>
  <input type = "hidden" id = "selectedTablet" name = "selectedTablet" value = "">
  <center>
  <div>
			堂口 ：
			<select style="width: 100px" name="building_name" id="building_name" onchange="getDan(this.value);">
				<option value="">请选择堂口</option>
				<c:forEach var="buildingName" items="${minfoEntityList}" varStatus="status">
					<option value="${buildingName}">${buildingName}</option>
				</c:forEach>
			</select>&nbsp;&nbsp;
    	段名 ：<span id="demo"></span>
    	&nbsp;&nbsp;
    	<input type = "button" value = "查询"  onclick = "queryTablet()" id = "myButton" class="button">
    	&nbsp;&nbsp;
    	<input type = "hidden" value = "${id}"  id = "id"  name="id">
    	<span id = "tips"></span>
  </div>
  </center>
  <br><br>
  <center>
    <div id = "showTablet">
    </div>
   </center>
   <br><br>
   <center>
   	<input type = "button" value = "确定选择" class = "button" onclick = "mySubmit()">
   </center>
  </body>
</html>
