<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>清明节信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  	<script>
  	
  	function toTombsweepfes(){
  		var id0 = document.getElementById("id").value;
  		 var size = encodeURI(document.getElementById("size").value); /* 8月17号 */
  		window.location.href = "${pageContext.request.contextPath}/tombsweepfesController.do?redirectToEditTombsweepfes&id=" + id0 + "&size=" + encodeURI(size);
  	}
  	
  	function selectBox(){
  		var count = 0;
  		 var size = encodeURI(document.getElementById("size").value); /* 8月17号 */
		 var tombsweetfesIds = "";
		 $("[name='checkbox']:checked").each(function(){
			 tombsweetfesIds += $(this).val()+",";
			 count = count + 1;
		  });
		 if(document.getElementById("size").value == "小"){
			 if(tombsweetfesIds == ""){
				 alert("请选择至少一个牌位，至多5个牌位");
				 return false;
			 }
			 else{
				 var id0 = document.getElementById("id").value;
				 window.location.href="${pageContext.request.contextPath}/tombsweepfesController.do?redirectToEditTombsweepfes&id=" + id0 + "&tombsweetfesIds=" + tombsweetfesIds + "&size=" + encodeURI(size);
			}	
		 }
		 else{
			 if(tombsweetfesIds == "" || count > 1){
				 alert("请选择一个牌位");
				 return false;
			 }
			 else{
				 var id0 = document.getElementById("id").value;
				 window.location.href="${pageContext.request.contextPath}/tombsweepfesController.do?redirectToEditTombsweepfes&id=" + id0 + "&tombsweetfesIds=" + tombsweetfesIds + "&size=" + encodeURI(size);
			 }	 
		 }

	}
  	

	//超度类法事，通过Ajax查询共享数据
 function queryReleaseSouls(){
	 //获取查询条件
 	  var prayingobj = $("#prayingobj").val();
// 	  var size=encodeURI($("#size").val()); 
 	  var size=$("#size").val(); 
 	  
       $.ajax({
       url:"prayNReleaseSoulsController.do?releaseSouls",
       type:"post",
       dataType:"json",
       data:{
     	prayingobj:prayingobj/* building_name */,
//     	dan_name:dan_name/* dan_name */,
     	size:size,
     	page: "1",
		rows: "20",
		sort: "registertime",
		order: "desc"
       },
      
       success:function(responseText){
     	var data = eval(responseText);
     	var arrays=data.rows;
     	var len=arrays.length;
     	var str = "";
     	
     	if(len==0){
     		//查询不到记录，提示
     		str="没有相应的记录";
     		alert(str);
     	}else if(len > 0){
		    str += '<span id="recordArea">';
		    str += '<table id = "receipttable" class="imagetable">';
			str+="<tr>";
			str+="	<th></th>";
			str+="	<th>序号</th>";
			str+="	<th>付款人</th>";
			str+="	<th>牌位号</th>";
			str+="	<th>牌位大小</th>";
			str+="	<th>时间</th>";
			str+="	<th>阳上</th>";
			str+="	<th>超渡对象</th>";
			str+="	<th>超渡类型</th>";
			str+="	<th>地址</th>";
			str+="	<th>摘要</th>";
			str+="</tr>";
		    	
		    	
		  	var i = parseInt(0);
		    for(;i < len;i ++){
		    	str += '<tr>';
				str += '<td><input type="checkbox" value="'+arrays[i].id+'" name="checkbox"></td>';
				str += '<td>'+(i+1)+'</td>';
				str += '<td>'+arrays[i].paymen+'</td>';
				str += '<td>'+arrays[i].serial+'</td>';
				str += '<td>'+arrays[i].size+'</td>';
			 	str += '<td>'+arrays[i].registertime+'</td>';
			 	str += '<td>'+arrays[i].prayingobj+'</td>';
			 	str += '<td>'+arrays[i].ancestor+'</td>';
			 	
			 	if(arrays[i].type=='2'){
			 		str+='<td>十方法界</td>';
			 	}else if(arrays[i].type=='3'){
			 		str+='<td>门堂历代宗亲</td>';
			 	}else if(arrays[i].type=='4'){
			 		str+='<td>堕胎婴灵</td>';
			 	}else if(arrays[i].type=='1'){
			 		str+='<td>无</td>';
			 	}else{
			 		str+='<td></td>';
			 	}
			 	
			 	str+='<td>'+arrays[i].address+'</td>';
			 	
			 	str+='<td>'+arrays[i].summary+'</td></tr>';
			 	
		    }
		    str += '</table><br>';
			str += '<input type="button" class="button" value="确定选择" onclick="selectBox()">';
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
		<h4>清明节记录</h4>
		
		<br>
		<%@include file="/context/ReleaseSouls.jsp" %>
		<br>
		<c:if test="${!empty tombsweepfesEntitys}">
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
				<th>阳上</th>
				<th>超渡对象</th>
				<th>超渡类型</th>
				<th>地址</th>
				<th>摘要</th>
			</tr>
			<c:forEach items="${tombsweepfesEntitys}" var="tombsweepfesEntity" varStatus="stauts">
				<tr>
					<td><input type="checkbox" value="${tombsweepfesEntity.id}" name="checkbox"></td>
					<td>${stauts.index+1}</td>
					<td>${tombsweepfesEntity.paymen}</td>
					<td>${tombsweepfesEntity.serial}</td>
					<td>${tombsweepfesEntity.size}</td>
					<%-- <td>${tombsweepfesEntity.receiptno}</td> --%>
				 	<td>${tombsweepfesEntity.registertime}</td>
				 	<td>${tombsweepfesEntity.prayingobj}</td>
				 	<td>${tombsweepfesEntity.ancestor}</td>
				 	<td>
				 		<c:if test="${tombsweepfesEntity.type == '2'}">十方法界</c:if>
				 		<c:if test="${tombsweepfesEntity.type == '3'}">门堂历代宗亲</c:if>
				 		<c:if test="${tombsweepfesEntity.type == '4'}">堕胎婴灵</c:if>
				 		<c:if test="${tombsweepfesEntity.type == '1'}">无</c:if>
				 	</td>
				 	<td>${tombsweepfesEntity.address}</td>
				 	<td>${tombsweepfesEntity.summary}</td>
				 	
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
		<c:if test="${empty tombsweepfesEntitys}">
			<span id="recordArea">没有清明节记录，请点击<input type="button" value="清明节登记" class="button" onclick="toTombsweepfes()">&nbsp;&nbsp;</span>
			</c:if>
		</center>
 </body>