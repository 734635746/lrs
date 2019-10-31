<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>放生信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  	<script>
  	
  	//获取委托人（做法事人）的详细信息
  	function getInfo(){
  		//获取信息
  		var id = document.getElementById("id").value;
  		var rname = document.getElementById("rname").value;
  		var phonenumber1 = document.getElementById("phonenumber1").value;
  		var phonenumber2 = document.getElementById("phonenumber2").value;
  		var address = document.getElementById("address").value;
  		if(rname == null || rname == ''){
  			return '';
  		}
  		return "&id=" + id + "&rname=" + rname + "&phonenumber1=" + phonenumber1 + "&phonenumber2=" + phonenumber2 + "&address=" + address;
	}
  	
  	
  	
  	/* function toMorningPforr(){
  		//获取信息
  		var rname = document.getElementById("rname").value;
  		var phonenumber1 = document.getElementById("phonenumber1").value;
  		var phonenumber2 = document.getElementById("phonenumber2").value;
  		var address = document.getElementById("address").value;
  		
  		if(rname == null || rname == ''){
  			alert("请输入姓名");
  			return;
  		}
  	
  		var id0 = document.getElementById("id").value;
  		window.location.href = "${pageContext.request.contextPath}/morningpforrController.do?ToEditMorningPforr&id=" + id0 + "&rname=" + rname + "&phonenumber1=" + phonenumber1 + "&phonenumber2=" + phonenumber2 + "&address=" + address;
  	} */
  	
  	
  	function toFreeLives(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
	  		window.location.href = "${pageContext.request.contextPath}/freeLivesController.do?ToEditFreeLives" + param;
  		}
  	
  	}
  	
  	
  	
  	function selectBox(){
  		 var count = 0;
		 var morningPforrId = "";
		  var param = getInfo();
		 $("[name='checkbox']:checked").each(function(){
			 morningPforrId += $(this).val()+",";
			 count = count + 1;
		  });
		 if(morningPforrId == "" || count > 5){
			 alert("请选择至少一个牌位，至多5个牌位");
			 return false;
		 }
		 else{
			// var id0 = document.getElementById("id").value;
			 window.location.href="${pageContext.request.contextPath}/freeLivesController.do?ToEditFreeLives"+"&morningPforrId=" + morningPforrId+param;
		 }
		 
		 /* if(document.getElementById("size").value == "小"){
			 if(morningPforrId == "" || count > 5){
				 alert("请选择一个牌位");
				 return false;
			 }
			 else{
				 var id0 = document.getElementById("id").value;
				 window.location.href="${pageContext.request.contextPath}/morningpforrController.do?ToEditMorningPforr&id=" + id0 + "&morningPforrId=" + morningPforrId;
			 }	 
		 }
		 else{
			 if(morningPforrId == "" || count > 1){
				 alert("请选择一个牌位");
				 return false;
			 }
			 else{
				 var id0 = document.getElementById("id").value;
				 window.location.href="${pageContext.request.contextPath}/morningpforrController.do?ToEditMorningPforr&id=" + id0 + "&morningPforrId=" + morningPforrId;
			 }	 
		 } */
	}
  	

  	</script>

 </head>
 
 <body style="overflow-y: scroll" scroll="yes">
  		<t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="">
			<input id="id" name="id" type="hidden" value="${doritualinfoEntity.id }">
			<input id="size" name="size" type="hidden" value="${size}">  <!-- 8月17号 -->
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">姓名:</label></td>
			<td class="value">
				<input nullmsg="请填写姓名" errormsg="姓名格式不对" class="inputxt" id="rname" name="rname" 
									   value="${doritualinfoEntity.rname}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">手机号码1:</label></td>
			<td class="value">
				<input nullmsg="请填写手机号码1" errormsg="手机号码1格式不对" class="inputxt" id="phonenumber1" name="phonenumber1" 
									   value="${doritualinfoEntity.phonenumber1}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">手机号码2:</label></td>
			<td class="value">
				<input nullmsg="请填写手机号码2" errormsg="手机号码2格式不对" class="inputxt" id="phonenumber2" name="phonenumber2" 
									   value="${doritualinfoEntity.phonenumber2}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">居住地址:</label></td>
			<td class="value">
				<input style="width:250px;" nullmsg="请填写居住地址" errormsg="居住地址格式不对" class="inputxt" id="address" name="address" 
									   value="${doritualinfoEntity.address}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			</table>
				
		</t:formvalid>
		<br>
		<center>
		<h4>放生记录</h4>
		<c:if test="${!empty freelivesEntityList}">
		<table id = "receipttable" class="imagetable">
			<tr>
				<th></th>
				<th>序号</th>
				<th>付款人</th>
				<th>牌位号</th>
				<th>类型</th>
				<th>收据编号</th>
				<!-- <th>登记时间</th> -->
				<!-- <th>阳上</th> -->
				<th>信众</th>
				<th>地址</th>
				<th>举行日期</th>
				<th>摘要</th>
			</tr>
			<c:forEach items="${freelivesEntityList}" var="freelivesEntity" varStatus="stauts">
				<tr>
					<td><input type="checkbox" value="${freelivesEntity.id}" name="checkbox"></td>
					<td>${stauts.index+1}</td>
					<td>${freelivesEntity.paymen}</td>
					<td>${freelivesEntity.serial}</td>
					<td>${freelivesEntity.size}</td>
					<td>${freelivesEntity.receiptno}</td>
				 	<%-- <td>${morningpforrEntity.registertime}</td> --%>
				 	<%-- <td>${morningpforrEntity.prayingobj}</td> --%>
				 	<td>${freelivesEntity.livingmenber }</td>
				 	<td>${freelivesEntity.address}</td>
				 	<td>${freelivesEntity.lunardate}</td>
				 	<td>${freelivesEntity.summary}</td>
				 	
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
		</c:if>
		<br><br>
		<c:if test="${empty freelivesEntityList}">
			<tr>没有放生记录，请点击<input type="button" value="放生登记" class="button" onclick="toFreeLives()">&nbsp;&nbsp;</tr>
			</c:if>
		</center>
 </body>