<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>
 <head>
  <title>预定房间</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table2.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  <style>
    .footer{position: absolute;bottom: 30px;left: 0;height: 20px;width: 100%;}
  </style>
  	<script>
  	 $(function(){
  	      $("#myButton").click(function(){
  	    	  var intakentime = document.getElementById("intakentime").value;
  	    	  var leavetime = document.getElementById("leavetime").value;
  	    	  if(leavetime == "" || intakentime == ""){
  	    		  alert("日期不能为空");
  	    		  return ;
  	    	  }
  	    	  
  	          $.ajax({
  	          url:"reserveroomController.do?query",
  	          type:"post",
  	          dataType:"text",
  	          data:{
  	        	intakentime:document.getElementById("intakentime").value,
	        	leavetime:document.getElementById("leavetime").value,
	        	type:document.getElementById("type").value
  	          },
  	          success:function(responseText){
  	        	$("#roomtable  tr:not(:first)").html("");
  				var dataArray = eval(responseText);  
  			    var len = dataArray.length;  
  			    if(len>=10){  
  			        len = 10;  
  			    }  
  			    for(var i=0 ;i<len ; i++){
  			    	$("#roomtable").append(
  		                    "<tr><input type = hidden value=" + dataArray[i].id + ">" +
  		                  	"<td><input type=\"checkbox\" name = \"checkbox\"/></td>" +
  		                  	"<td>" + (i+1)   + "</td>" +
  		                    "<td>" + dataArray[i].roomNumber    + "</td>" +
  		                    "<td>" + dataArray[i].roomKind + "</td>" +
  		                  	"<td>" + dataArray[i].restBeds    + "</td></tr>");
  			    }  
  	          },
  	          error:function(){
  	            alert("system error");
  	          }
  	        });
  	      });
  	});
  	 function getCheckBoxSelection(){
  		 	var resultids = "";
  		 	var count = 0;
  			$("#roomtable :checkbox").each(function(){
				if(this.checked){
					count = count + 1;
					var input0 = $.trim($(this).parent().parent().find("input").val());
					resultids = resultids + input0;
					
				}
			});
  			if(count > 1){
  				return "";
  			}
  			return resultids;
  		}	
  	$(function(){
 	     //全选
 	     $("#SelectLivingAll").click(function(){
 	         $('[name=checkbox]:checkbox').attr("checked", this.checked );
 	     });
 	});
  	
  	function toReverseRoom(){
  		var intakentime = document.getElementById("intakentime").value;
    	var leavetime = document.getElementById("leavetime").value;
    	var type = document.getElementById("type").value;
    	var roomids = getCheckBoxSelection();
    	if(roomids == ""){
    		alert("请预定房间,一次只能预定一个房间");
    		return ;
    	}
    	href = "${pageContext.request.contextPath}/reserveroomController.do?addorupdate&intakentime=" + intakentime
    			+ "&leavetime=" + leavetime + "&roomid=" + roomids + "&reserverflag=yes";
		window.location = href ;
  	}
  	</script>

 </head>
 <body>
 <h4>预定房间</h4>
<div>
  <div region="center" style="padding:1px;">
	  入住时间 ：<input type="text" id = "intakentime" name = "intakentime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
	  离开时间 ：<input type="text" id = "leavetime" name = "leavetime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})">
	  房间类型 ： <select id="type" name="type">
		<c:forEach items="${types}" var="TSType">
			<option value="${TSType.typename}">${TSType.typename}</option>
		</c:forEach>
	 </select>
	 <input type="button" value = "查询" id = "myButton" class="button">
	
  </div>
 </div>
 <br><br><br>
<center>
		<table id = "roomtable" class="imagetable" border = 1>
			<tr>
				<th></th>
				<th>序号</th>
				<th>房间号</th>
				<th>房间类型</th>
				<th>剩余床位</th>
			</tr> 
		</table>
</center>
<br>
<center>
<input type = "button" value = "预定登记" onclick="toReverseRoom()" class="button"/>
</center>
</body>
 <script src="http://www.my97.net/dp/My97DatePicker/WdatePicker.js"></script>