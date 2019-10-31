<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>药师诞信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  	<script>
  	
  	function toPharmacistbirth(){
  		var id0 = document.getElementById("id").value;
  		 var size = encodeURI(document.getElementById("size").value);//10月30号做了修改
  		window.location.href = "${pageContext.request.contextPath}/pharmacistbirthController.do?redirectToEditPharmacistbirth&id=" + id0 + "&size=" + encodeURI(size);
  	}
  	
  	function selectBox(){
		var count = 0;
		 var size = encodeURI(document.getElementById("size").value);//10月30号做了修改
		 var pharmacistbirthIds = "";
		 $("[name='checkbox']:checked").each(function(){
			 pharmacistbirthIds += $(this).val()+",";
			 count = count + 1;
		  });
		 
		 if(document.getElementById("size").value == "小"){
			 if(pharmacistbirthIds == "" || count > 5){
				 alert("请选择至少一个牌位，至多5个牌位");
				 return false;
			 }
			 else{
				 var id0 = document.getElementById("id").value;
				 window.location.href="${pageContext.request.contextPath}/pharmacistbirthController.do?redirectToEditPharmacistbirth&id=" + id0 + "&pharmacistbirthIds=" + pharmacistbirthIds + "&size=" + encodeURI(size);
			 }	 
		 }
		 else{
			 if(pharmacistbirthIds == "" || count > 1){
				 alert("请选择一个牌位");
				 return false;
			 }
			 else{
				 var id0 = document.getElementById("id").value;
				 window.location.href="${pageContext.request.contextPath}/pharmacistbirthController.do?redirectToEditPharmacistbirth&id=" + id0 + "&pharmacistbirthIds=" + pharmacistbirthIds + "&size=" + encodeURI(size);
			 }	 
		 }
	}
  	

	//祈福类法事，通过ajax查询共享数据 
	function queryPray(){

  	    	  var size=$("#size").val(); 
  	    	  var livingmenber=$("#livingmenber").val(); 
  	    	  
  	    	  
  	          $.ajax({
  	          url:"prayNReleaseSoulsController.do?pray",
  	          type:"post",
  	          dataType:"json",
  	          data:{
  	        	size:size,
  	        	livingmenber:livingmenber,
  	        	page: "1",
				rows: "20",
				sort: "registertime",
				order: "desc"
  	          },
  	          success:function(responseText){
  	        	var data = eval(responseText);
  	        	var prays=data.rows;
  	        	var total = prays.length;
  			    var str = "";
  			    
  			    if(total==0){
  			    //查询不到记录，提示
  		     		str="没有相应的记录";
  		     		alert(str);
  			    }else if(total>0){
  			   	 	str += '<span id="recordArea">';
  			    	str+='<table id = "receipttable" class="imagetable"><tr>';
  					str+='	<th></th>';
  					str+='	<th>序号</th>';
  					str+='	<th>付款人</th>';
  					str+='	<th>牌位号</th>';
  					str+='	<th>牌位大小</th>';
  					str+='	<th>时间</th>';
  					str+='	<th>祈福者</th>';
  					str+='	<th>祈福对象</th>';
  					str+='	<th>地址</th>';
  					str+='	<th>摘要</th></tr>';
  					
  			    	var j = parseInt(0);	
  					for(;j < total;j ++){
  						str+='<tr><td><input type="checkbox" value="'+prays[j].id+'" name="checkbox"></td>';
  						str+='<td>'+(j+1)+'</td>';
  						str+='<td>'+prays[j].paymen+'</td>';
  						str+='<td>'+prays[j].serial+'</td>';
  						str+='<td>'+prays[j].size+'</td>';
  					 	str+='<td>'+prays[j].registertime+'</td>';
  					 	str+='<td>'+prays[j].prayingobj+'</td>';
  					 	str+='<td>'+prays[j].livingmenber+'</td>';
  					 	str+='<td>'+prays[j].address+'</td>';
  					 	str+='<td>'+prays[j].summary+'</td></tr>';
  					}
  					str+='</table><br><input type="button" class="button" value="确定选择" onclick="selectBox()">';
  					str += '</span>';
  					
  						
  					$("#recordArea").replaceWith(str);
  			    }
  			    
  	          },
  	          error:function(){
  	            alert("系统发生错误，请与系统管理员联系");
  	          }
  	        }); 
  	 }
 
  	</script>

 </head>
 <body style="overflow-y: scroll" scroll="yes">
  		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="">
			<input id="id" name="id" type="hidden" value="${doritualinfoEntity.id }">
			<input id="size" name="size" type="hidden" value="${size}">
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">姓名:</label></td>
			<td class="value">
				<input nullmsg="请填写姓名" errormsg="姓名格式不对" class="inputxt" id="rname" name="rname"
									   value="${doritualinfoEntity.rname}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">手机号码1:</label></td>
			<td class="value">
				<input nullmsg="请填写手机号码1" errormsg="手机号码1格式不对" class="inputxt" id="phonenumber1" name="phonenumber1"
									   value="${doritualinfoEntity.phonenumber1}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">手机号码2:</label></td>
			<td class="value">
				<input nullmsg="请填写手机号码2" errormsg="手机号码2格式不对" class="inputxt" id="phonenumber2" name="phonenumber2"
									   value="${doritualinfoEntity.phonenumber2}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">居住地址:</label></td>
			<td class="value">
				<input style="width:250px;" nullmsg="请填写居住地址" errormsg="居住地址格式不对" class="inputxt" id="address" name="address"
									   value="${doritualinfoEntity.address}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			</table>
				
		</t:formvalid>
		<br>
		<center>
		<h4>药师诞记录</h4>
		
		<br>
		<%@include file="/context/Pray.jsp" %>
		<br>
		<c:if test="${!empty pharmacistbirthEntitys}">
		<span id="recordArea">
		<table id = "receipttable" class="imagetable">
			<tr>
				<th></th>
				<th>序号</th>
				<th>付款人</th>
				<th>牌位号</th>
				<th>牌位大小</th>
				<!-- <th>收据编号</th> -->
				<th>时间</th>
				<th>祈福者</th>
				<th>祈福对象</th>
				<th>地址</th>
				<th>摘要</th>
			</tr>
			<c:forEach items="${pharmacistbirthEntitys}" var="pharmacistbirthEntity" varStatus="stauts">
				<tr>
					<td><input type="checkbox" value="${pharmacistbirthEntity.id}" name="checkbox"></td>
					<td>${stauts.index+1}</td>
					<td>${pharmacistbirthEntity.paymen}</td>
					<td>${pharmacistbirthEntity.serial}</td>
					<td>${pharmacistbirthEntity.size}</td>
					<%-- <td>${pharmacistbirthEntity.receiptNo}</td> --%>
				 	<td>${pharmacistbirthEntity.registertime}</td>
				 	<td>${pharmacistbirthEntity.prayingobj}</td>
				 	<td>${pharmacistbirthEntity.livingmenber}</td>
				 	<td>${pharmacistbirthEntity.address}</td>
				 	<td>${pharmacistbirthEntity.summary}</td>
				 	
				 	<%-- <td><textarea id="summary" name="summary">
							${receipt.summary}
						</textarea>
					</td> --%>
					<%-- 	<td><input type="button" value="点击" onclick="LinktoPharmacistbirth('${receipt.ritualid}')"></td> --%>
				   	
				 </tr>
			</c:forEach>
			
		</table>
		<br>
		<input type="button" class="button" value="确定选择" onclick="selectBox()">
		</span>
		</c:if>
		<br><br>
		<c:if test="${empty pharmacistbirthEntitys}">
			<span id="recordArea">没有药师诞记录，请点击<input type="button" value="药师诞登记" class="button" onclick="toPharmacistbirth()">&nbsp;&nbsp;</span>
			</c:if>
		</center>
 </body>