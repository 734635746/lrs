<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>释尊诞信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  <script type="text/javascript"> 
  	var index = 0;
  	var tr1;
  	var tr_clone;
  	function toPharmacistbirth(){
  		var id = document.getElementById("id").value;
  		window.location.href = "${pageContext.request.contextPath}/pharmacistbirthController.do?redirectToPharmacistbirth&id=" + id;
  	}
  	
  	function LinktoPharmacistbirth(ritualid){
  		var id = document.getElementById("id").value;
  		window.location.href = "${pageContext.request.contextPath}/pharmacistbirthController.do?redirectToPharmacistbirthByRitualid&id=" + id + "&ritualid=" + ritualid;
  	}
  	
  	function comfirm(){
		 var receiptids = "";
		 $("[name='checkbox']:checked").each(function(){
			 receiptids += $(this).val()+",";
		  });
		alert(receiptids);
	}
	function onShow(index){
		document.getElementById("form").style.display="block";
		/* document.getElementById("rowindex").value = index;
		alert("=========");
		alert(document.getElementById("rowindex").value); */
	}
	
	function onHide(){
		document.getElementById("form").style.display="none";
		index = 0;
		/* document.getElementById("rowindex").value = ""; */
	}
	//获取复选框选中的值
  	function getLivingMemberListSelections(){
  		var livingMemberList = "";
  		var livingString = "";
  		$("#livingtable :checkbox").each(function(){
				if(this.checked){
					var input0 = $.trim($(this).parent().next().find("input").val());
					var input1 = $.trim($(this).parent().next().next().find("input").val());
					if(input0 == "" && input1 == ""){
						livingMemberList += "";
					}
					else{
						if(input0 == ""){
							livingMemberList += input1 + ";";
	  					}
	  					else{
	  					livingMemberList += input0 + ":" + input1 + ";";
	  					}
					}
					if(input0 == "") input0 = "无";
					if(input1 == "") input1 = "无";
					livingString = livingString + input0 + "|" +  input1 + "|";
				}
			});
  		$(document.getElementById("receipttable").rows[index].cells[2]).find('input').val(livingMemberList);
  		generateSummary(livingMemberList,index);
	}
  	function generateSummary(livingMemberList,index){
  		$(document.getElementById("receipttable").rows[index].cells[6]).find('textarea').val(livingMemberList);
  	}
  	$(function(){
 	     //全选
 	     tr1= $("#receipttable tr").clone();
  		tr_clone = tr1[1];
 	     $("#SelectAncestorAll").click(function(){
 	         $('[name=ancestor]:checkbox').attr("checked", this.checked );
 	     });
 	   	 $("#SelectLivingAll").click(function(){
 	         $('[name=living]:checkbox').attr("checked", this.checked );
 	     });
 	});
  	function getSelected(current_Tr){ 
  		onShow();
  		index = $.trim($(current_Tr).parent()[0].rowIndex);
  		
  		var liviingmemberString = $(document.getElementById("receipttable").rows[index].cells[2]).find('input').val();
  		var oneLivingMember = liviingmemberString.substring(0,liviingmemberString.length - 1).split(";");
  		for(var i = 0;i < oneLivingMember.length; i ++){
  			if(oneLivingMember[i].indexOf(":") > 0 ){
  				var oneLivingMemberSplitBySymbol = oneLivingMember[i].split(":");
  				/* document.getElementById("call" + (i+1)).value = oneLivingMemberSplitBySymbol[0]; */
  				document.getElementById("name" + (i+1)).value = oneLivingMemberSplitBySymbol[1];
  			}
  			else{
  				/* document.getElementById("call" + (i+1)).value = ""; */
  				document.getElementById("name" + (i+1)).value = oneLivingMember[i];
  			}
  		}
  		
  	}
  	function getLivingMemberList(){
  		var rowcount = document.getElementById("livingtable").rows.length;
 		var tr = document.getElementById("livingtable").getElementsByTagName("tr");
  		for(var i = 1;i < rowcount;i ++){
 			var obj = tr[i].getElementsByTagName("td")[1].children[0].value;
 			if(parseInt(obj.length) > 8){
 				alert("提示 ： 祈福对象名字只能输入小于8个字符");
 				return false;
 			}
 		}
  		
  		var updateLivingString = "";
  		for(var i = 1;i <= 5;i ++){
  			/* var call = document.getElementById("call" + i).value; */
			var name = document.getElementById("name" + i).value;
			updateLivingString += name + ";";
			/* if(call != "" || name != ""){
				if(call != ""){
					updateLivingString += call + ":" + name + ";";
				}
				else{
					updateLivingString += name + ";";
				}
			} */
  		}
  		$(document.getElementById("receipttable").rows[index].cells[2]).find('input').val(updateLivingString);
  		generateSummary(updateLivingString,index);
  		onHide();
  	}
  	function changeSize(size){
  		alert("注意： 切换成" + size + "牌登记");
  			if(size == "小"){
  	  			document.getElementById("add").style.display = "block";
  	  			document.getElementById("delete").style.display = "block"
  	  			document.getElementById("_book").style.display = "none";
  	  		}
  	  		else{
  	  			document.getElementById("add").style.display = "none";
  	  			document.getElementById("delete").style.display = "none"
  	  			document.getElementById("_book").style.display = "block";
  	  		}
  		
  	}
  	
  	
 	function addTr(){
 		var tr =  $("#receipttable tr").clone();
 		if(tr[1] == null){
 			$("#receipttable").append(tr_clone);
 		} 
 		else{
 			$("#receipttable").append(tr[1]);
 		}
 	}
 	function deteleTr(){
 		$("#receipttable").find("input:checked").parent().parent().remove();   
 	}
 	
 	function preview(){ 
 		var tableObj = document.getElementById("preview_table");
 		
 		var arr = document.getElementById("preview_table").getElementsByTagName("tr");
 		for(var i=arr.length-1;i > 0;i--){
 			tableObj.deleteRow(i);
 		}
 		
 		var rowcount = document.getElementById("receipttable").rows.length;
 		var cowcount = document.getElementById("receipttable").rows[0].cells.length;
 		var tr = document.getElementById("receipttable").getElementsByTagName("tr");
 		for(var i = 1;i < rowcount;i++){
 			var pre_row = tableObj.insertRow();
 			for(var j = 1;j < cowcount  - 1;j++){
 				var pre_cow = pre_row.insertCell(j-1);
 					pre_cow.innerHTML = tr[i].getElementsByTagName("td")[j].children[0].value;
 				
 			}
 			
 		}
 		document.getElementById("preview_form").style.display="block";
 		
 	}
 	function hide(){
 		var tableObj = document.getElementById("preview_table");
 		var arr = document.getElementById("preview_table").getElementsByTagName("tr");
 		for(var i=arr.length-1;i > 0;i--){
 			tableObj.deleteRow(i);
 		}
 		document.getElementById("preview_form").style.display="none";
 	}
 	
 	function myvalidate(){
 		var rowcount = document.getElementById("receipttable").rows.length;
 		var tr = document.getElementById("receipttable").getElementsByTagName("tr");
 		
 		for(var i = 1 ;i < rowcount;i ++){
 			if (!(/(^[1-9]\d*$)/.test(tr[i].getElementsByTagName("td")[3].children[0].value))){
 				alert("请输入合法的金钱数目，第" + (i+1) + "行的钱的数目不是正整数");
 				return false;
 			}
 		}
 		
 		var r=window.confirm("确定填写信息没错吗？确定后不可以修改，可以预览信息");
 		if (r==true)
 		{
 			return true;
 		}
 		else
 		{
 		  return false;
 		}
 		
 	}
  	
  	

  	</script>

<style type="text/css">
#form{
 	width: 300px;
 	height: 150px;
 	margin: 0px auto;
 	margin-bottom:20px;
 	border:1px solid white;
 	background-color: white;
 	position:absolute;/*绝对对齐*/
	z-index:1000;
	left:240px;
 	}
#form h5{
 	margin: 1px;
 	background-color: white;
 	height: 24px;
 	}

</style>
 </head>
 <body style="overflow-y: scroll" scroll="yes">
 <h4>编辑释尊诞记录</h4>
 <span><input type="button" value="预览" class="button" onclick = "preview()">
 			<input type="button" id = "add" value="添加记录" class="button" onclick="addTr()">
 		<input type="button" id = "delete" value="撤销记录" class="button" onclick="deteleTr()"></span>
		<br><br>
		<center>
		<div id="preview_form" style="display:none;">
	    	<table id = "preview_table" class = "imagetable">
	    	<tr>
				<th>祈福者</th>
				<th>祈福对象</th>
	     		<!-- <th>地址</th> -->
				<th>金额</th>
				<th>付款方式</th>
				<th>牌位大小</th>
			</tr> 
			
		</table>
			<br>
	        <input type = "button" onclick="hide()" class = "button" value= "取消"></button>
    	</div>
		<c:if test="${!empty honoredbirthEntitys}">
		<form action="${pageContext.request.contextPath}/honoredbirthController.do?getSerialAndSaveTablet" onsubmit="return myvalidate()" method="post">
		<input id="id" name="id" type="hidden" value="${id}">
		<input type = "hidden" value = "${honoredbirthid}" name = "honoredbirthid" id = "honoredbirthid">
		付款人:<input style="width:100px;" type="text" id = "paymen" name="paymen" value="${clientele}" required><br><br>
		<span id = "_book" style="display:none">敬设:<input style="width:150px;" type="text" id = "book" name="book" value="${book}"><br><br></span>
		<table id = "receipttable" class="imagetable">
			<tr>
				<th>选择</th>
				<th>祈福者</th>
				<th>祈福对象(单击输入框添加祈福对象)</th>
				<!-- <th>地址</th> -->
				<th>金额</th>
				<th>付款方式</th>
				<th>牌位大小</th>
				<th>摘要</th>
			</tr>
			<c:forEach items="${honoredbirthEntitys}" var="honoredbirthEntity" varStatus="stauts">
				<tr>
					<td><input style="width:20px;"  type="checkbox"/></td>
				 	<td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" value="${honoredbirthEntity.prayingobj}"></td>
				 	<td onclick="getSelected(this)"><input type="text" name="livingmenber" id = "livingmenber" value="${honoredbirthEntity.livingmenber}"></td>
				 	<%-- <td><input type="text" id = "address" name="address" value="${honoredbirthEntity.address}"></td> --%>
				 	<input type="hidden" id = "address" name="address" value="${honoredbirthEntity.address}">
<%-- 				 	<input type="hidden" id = "address" name="address" value="${updateFlag!=1? doritualinfoAddress:honoredbirthEntity.address}"> --%>
				 	<td><input style="width:40px;" type="text" id = "money" name="money" value="${honoredbirthEntity.money}" required></td>
				 	<td>
				 	<select style="width:60px;" id="payway" name="payway" >
						<option value="现金">现金</option>
						<option value="刷卡">刷卡</option>
						<option value="支付宝">支付宝</option>
						<option value="微信">微信</option>
						<option value="其他">其他</option>
					</select>
					</td>
					<td>
					<select style="width:50px;" id="size" name="size" onchange="changeSize(this.value);">
						<option value="小" <c:if test="${honoredbirthEntity.size eq '小'}">selected="selected"</c:if>>小</option>
					</select>
					</td>
				 	
				 	<td><textarea rows="3" cols="60" id="summary" name="summary" required>${honoredbirthEntity.summary}</textarea>
					</td>
					<%-- 	<td><input type="button" value="点击" onclick="LinktoPharmacistbirth('${receipt.ritualid}')"></td> --%>
				   	
				 </tr>
			</c:forEach>
			
		</table>
		<br><br>
		<input type="submit" value="确定" class="button">
		</form>
		</c:if>
		
    	</center>
    	
    	<center>
    		<c:if test="${empty honoredbirthEntitys}">
    		<form action="${pageContext.request.contextPath}/honoredbirthController.do?getSerialAndSaveTablet" onsubmit="return myvalidate()" method="post">
			<input id="id" name="id" type="hidden" value="${id}">
			付款人:<input style="width:100px;" type="text" id = "paymen" name="paymen" value="${clientele}" required><br><br>
			<span id = "_book" style="display:none">敬设:<input style="width:150px;" type="text" id = "book" name="book" value="" ><br><br></span>
			<table id = "receipttable" class="imagetable">
				<tr>
					<th>选择</th>
					<th>祈福者</th>
					<th>祈福对象(单击输入框添加祈福对象)</th>
					<!-- <th>地址</th> -->
					<th>金额</th>
					<th>付款方式</th>
					<th>牌位大小</th>
					<th>摘要</th>
				</tr>
				<tr>
						<td><input style="width:20px;"  type="checkbox"/></td>
					 	<td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" value="${clientele}"></td>
					 	<td onclick="getSelected(this)"><input type="text" name="livingmenber" id = "livingmenber" value=""></td>
					 	<!-- <td><input type="text" id = "address" name="address" value=""></td> -->
					 	<input type="hidden" id = "address" name="address" value="${doritualinfoAddress}">
					 	<td><input style="width:40px;" type="text" id = "money" name="money" value="" required></td>
					 	<td>
					 	<select style="width:60px;" id="payway" name="payway" >
						<option value="现金">现金</option>
						<option value="刷卡">刷卡</option>
						<option value="支付宝">支付宝</option>
						<option value="微信">微信</option>
						<option value="其他">其他</option>
					</select>
						</td>
						<td>
						<select style="width:50px;" id="size" name="size" onchange="changeSize(this.value);">
							<option value="小">小</option>
						</select>
						</td>
					 	
					 	<td><textarea rows="3" cols="60" id="summary" name="summary" autofocus required></textarea>
						</td>
						<%-- 	<td><input type="button" value="点击" onclick="LinktoPharmacistbirth('${receipt.ritualid}')"></td> --%>
					   	
					 </tr>
				
			</table>
			<br><br>
			<input type="submit" value="确定" class="button">
		</form>
		</c:if>
		
		<div id="form" style="display:none;"><h5>祈福对象</h5>
	    	<table id = "livingtable" class = "imagetable">
	    	<tr>
				<!-- <th>称呼</th> -->
				<th>位置</th>
				<th>名字</th>
			</tr> 
			<tr>
				<!-- 添加标号 -->
				<td><div style="width:30px; text-align:center;">1</div></td>
				<!-- <td><input type = "text" id="call1" value=""></td> -->
				<td><input style="width:250px;" type = "text" id="name1" value="" placeholder="请输入最多8个字符"></td>
			</tr>
			<tr>
				<!-- 添加标号 -->
				<td><div style="width:30px; text-align:center;">2</div></td>
				<!-- <td><input type = "text" id="call2" value=""></td> -->
				<td><input style="width:250px;" type = "text" id="name2" value="" placeholder="请输入最多8个字符"></td>
			</tr>
			<tr>
				<!-- 添加标号 -->
				<td><div style="width:30px; text-align:center;">3</div></td>
				<!-- <td><input type = "text" id="call3" value=""></td> -->
				<td><input style="width:250px;" type = "text" id="name3" value="" placeholder="请输入最多8个字符"></td>
			</tr>
			<tr>
				<!-- 添加标号 -->
				<td><div style="width:30px; text-align:center;">4</div></td>
				<!-- <td><input type = "text" id="call4" value=""></td> -->
				<td><input style="width:250px;" type = "text" id="name4" value="" placeholder="请输入最多8个字符"></td>
			</tr>
			<tr>
				<!-- 添加标号 -->
				<td><div style="width:30px; text-align:center;">5</div></td>
				<!-- <td><input type = "text" id="call5" value=""></td> -->
				<td><input style="width:250px;" type = "text" id="name5" value="" placeholder="请输入最多8个字符"></td>
			</tr>
			</table>
			<br>
			<input type = "button" onclick="getLivingMemberList()" class = "button" value= "确定"></button>
	        <input type = "button" onclick="onHide()" class = "button" value= "取消"></button>
    	</div>
		</center>
 </body>