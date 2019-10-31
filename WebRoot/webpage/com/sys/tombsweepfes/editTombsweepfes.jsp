<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<!DOCTYPE html>
<html>
 <head>
  <title>清明节信息</title>
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
  		$(document.getElementById("receipttable").rows[index].cells[3]).find('input').val(livingMemberList);
  		generateSummary(livingMemberList,index);
	}
  	function generateSummary(livingMemberList,index){
  		$(document.getElementById("receipttable").rows[index].cells[8]).find('textarea').val(livingMemberList);
  	}
  	$(function(){
  		tr1= $("#receipttable tr").clone();
  		tr_clone = tr1[1];
 	     //全选
 	     $("#SelectAncestorAll").click(function(){
 	         $('[name=ancestor]:checkbox').attr("checked", this.checked );
 	     });
 	   	 $("#SelectLivingAll").click(function(){
 	         $('[name=living]:checkbox').attr("checked", this.checked );
 	     });

 	   	 var ghostfesid = document.getElementById("ghostfesid").value;
 	   	 if(ghostfesid != null){
 	   		document.getElementById("money").readOnly=true;
 	   		document.getElementById('buttonAdd').style.display="none";
 	   		document.getElementById('buttonDel').style.display="none";
 	   		
 	   	 }
 	});

  	function getAncestorList(){
  		var rowcount = document.getElementById("livingtable").rows.length;
 		var tr = document.getElementById("livingtable").getElementsByTagName("tr");
 		for(var i = 1;i < rowcount;i ++){
 			var obj = tr[i].getElementsByTagName("td")[1].children[0].value;
 			if(parseInt(obj.length) > 8){
 				alert("提示 ： 超渡对象名字只能输入小于8个字符");
 				return false;
 			}
 		}
 		
  		var updateAncestorString = "";
  		for(var i = 1;i <= 3;i ++){
  			/* var call = document.getElementById("call" + i).value; */
			var name = document.getElementById("name" + i).value;
			updateAncestorString += name + ";";
			/* if(call != "" || name != ""){
				if(call != ""){
					updateAncestorString += call + ":" + name + ";";
				}
				else{
					updateAncestorString += name + ";";
				}
			} */
  		}
  		$(document.getElementById("receipttable").rows[index].cells[3]).find('input').val(updateAncestorString);
  		//generateSummary(updateAncestorString,index);
  		onHide();
  	}
  	
  	function getSelected(current_Tr){   //8月11号修改
  		
  		index = $.trim($(current_Tr).parent()[0].rowIndex);
  		var tr = document.getElementById("receipttable").getElementsByTagName("tr");
  		var td = tr[index].getElementsByTagName("td")[2];
  		if(td.children[0].value == 1){
  			onShow();
  			var ancestormemberString = $(document.getElementById("receipttable").rows[index].cells[3]).find('input').val();
  	  		var oneAncestorMember = ancestormemberString.substring(0,ancestormemberString.length - 1).split(";");
  	  		for(var i = 0;i < oneAncestorMember.length; i ++){
  	  			if(oneAncestorMember[i].indexOf(":") > 0 ){
  	  				var oneAncestorMemberSplitBySymbol = oneAncestorMember[i].split(":");
  	  				/* document.getElementById("call" + (i+1)).value = oneAncestorMemberSplitBySymbol[0]; */
  	  				document.getElementById("name" + (i+1)).value = oneAncestorMemberSplitBySymbol[1];
  	  			}
  	  			else{
  	  				/* document.getElementById("call" + (i+1)).value = ""; */
  	  				document.getElementById("name" + (i+1)).value = oneAncestorMember[i];
  	  			}
  	  		}
  		}
  		
  		
  	}
 	function change(number,current){	//8月11号修改
  		var inner = "";
  		var selectindex = $.trim($(current).parent().parent()[0].rowIndex);
  		//var rowcount = document.getElementById("receipttable").rows.length;
  		var tr = document.getElementById("receipttable").getElementsByTagName("tr");
  		var td = tr[selectindex].getElementsByTagName("td")[3];
  		if(number == "2"){
  			td.innerHTML = "<input style='width:30px;' type='hidden' name='ancestor' id = 'ancestor'>";
  		}
  		if(number == "3"){
  			td.innerHTML = "<input style='width:70px;' type='text' name='ancestor' id = 'ancestor' required><br>门堂历代祖先";
  		} 
  		if(number == "4"){
  			td.innerHTML = "<input style='width:60px;' type='text' name='ancestor' id = 'ancestor'><br>堕胎婴灵";
  		}
  		if(number == "1"){
  			td.innerHTML = "<input type='text' name='ancestor' id = 'ancestor' requried>";
  		}
  	}
 	function addTr(){
 		var tr =  $("#receipttable tr").clone();
 		if(tr[1] == null){
 			$("#receipttable").append(tr_clone);
 		} 
 		else{
 			tr[1].children[8].children[0].textContent = '';
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
 				if(j == 2){
 					if(tr[i].getElementsByTagName("td")[j].children[0].value == 2){
 						pre_cow.innerHTML = "十方法界";
 					}
 					if(tr[i].getElementsByTagName("td")[j].children[0].value == 3){
 						pre_cow.innerHTML = "门堂历代宗亲";
 					}
 					if(tr[i].getElementsByTagName("td")[j].children[0].value == 4){
 						pre_cow.innerHTML = "堕胎婴灵";
 					}
 					if(tr[i].getElementsByTagName("td")[j].children[0].value == 1){
 						pre_cow.innerHTML = "无";
 					}
 				}
 				else{
 					pre_cow.innerHTML = tr[i].getElementsByTagName("td")[j].children[0].value;
 				}
 				
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
 			if (!(/(^[1-9]\d*$)/.test(tr[i].getElementsByTagName("td")[4].children[0].value))){
 				alert("请输入合法的金钱数目，第" + (i+1) + "行的钱的数目不是正整数");
 				return false;
 			}
 		}
 		
 		//判断是否有盂兰节节日安排，若无，不允许提交
 		if("${fn:length(funeralheld)}" == "0" && "${updateFlag}"!=1 ){
 			alert("无清明节日期安排记录！");
 			return false;
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
 	
 	function changSumary(currentNode){
 				var index = currentNode.parentNode.parentNode.children[2].children[0].selectedIndex;
				var prayingobj=currentNode.parentNode.parentNode.children[1].children[0].value;
				var ancestor = currentNode.parentNode.parentNode.children[3].children[0].value;
				if(index == 0){//十方法界
				//currentNode.textContent = "农历${funeralheld[0].holdDate} 盂兰节法会。十方法界";
				currentNode.textContent = "十方法界"+"  阳上:"+prayingobj;
				}else if(index == 1){//门堂上历代祖先
				
					//console.log(ancestor);
					currentNode.textContent =ancestor+ "门堂历代祖先" +"  阳上:"+prayingobj;
				}else if(index == 2){//堕胎婴灵
					currentNode.textContent =  "堕胎婴灵 "+ancestor +"  阳上:"+prayingobj;
				}else{//无
					currentNode.textContent = ancestor+"  阳上:"+prayingobj;
				}		
		//console.log(index);
		/* if("${fn:length(funeralheld)}" == "0"){
				alert("无清明节日期安排记录！");
			}else if("${fn:length(funeralheld)}" == "1"){
				
			}else{
				alert("今年的清明节日期安排记录数只能有一条！")
			} */
		
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
 <h4>	</h4>
 <input type="button" id = "add" value="添加记录" id = "buttonAdd" class="button" onclick="addTr()">
 		<input type="button" id = "delete" value="撤销记录" id = "buttonDel" class="button" onclick="deteleTr()"></span>
 <input type="button" value="预览" class="button" onclick = "preview()">
 <br>
		<br>
		<center>
		<div id="preview_form" style="display:none;">
	    	<table id = "preview_table" class = "imagetable">
	    	<tr>
				<th>阳上</th>
				<th>超渡类型</th>
				<th>超渡对象</th>
	     		<!-- <th>地址</th> -->
				<th>金额</th>
				<th>付款方式</th>
				<th>牌位大小</th>
			</tr> 
			
		</table>
			<br>
	        <input type = "button" onclick="hide()" class = "button" value= "取消"></button>
    	</div>
		<c:if test="${!empty tombsweepfesEntitys}">
		<form action="${pageContext.request.contextPath}/tombsweepfesController.do?getSerialAndSaveTablet"  onsubmit="return myvalidate()" method="post">
		<input id="id" name="id" type="hidden" value="${id}">
		<input type = "hidden" value = "${tombsweepfesid}" name = "tombsweepfesid" id = "tombsweepfesid">
		付款人:<input style="width:100px;" type="text" id = "paymen" name="paymen" value="${clientele}" required>
		<table id = "receipttable" class="imagetable">
			<tr>
				<th>选择</th>
				<th>阳上</th>
				<th>超渡类型</th>
				<th>超渡对象</th>
				<!-- <th>地址</th> -->
				<th>金额</th>
				<th>付款方式</th>
				<th>牌位大小</th>
				<th>摘要</th>
			</tr>
			<c:forEach items="${tombsweepfesEntitys}" var="tombsweepfesEntity" varStatus="stauts">
				<tr>
					<td><input style="width:20px;"  type="checkbox"/></td>
				 	<td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" value="${tombsweepfesEntity.prayingobj}"></td>
				 	<td>
							<select style="width:100px;" id="type" name="type" onchange="change(this.value,this)">
							<option value="2" ${tombsweepfesEntity.type == 2 ? 'selected = "selected"' : '' }>十方法界</option>
							<option value="3" ${tombsweepfesEntity.type == 3 ? 'selected = "selected"' : '' }>门堂历代宗亲</option>
							<option value="4" ${tombsweepfesEntity.type == 4 ? 'selected = "selected"' : '' }>堕胎婴灵</option>
							<option value="1" ${tombsweepfesEntity.type == 1 ? 'selected = "selected"' : '' }>无</option>
							</select>
					</td>
				 	<td onclick="getSelected(this)"><input type="text" name="ancestor" id = "ancestor" value="${tombsweepfesEntity.ancestor}"></td>
				 	<%-- <td><input type="text" id = "address" name="address" value="${tombsweepfesEntity.address}"></td> --%>
				 	<input type="hidden" id = "address" name="address" value="${tombsweepfesEntity.address}">
<%-- 				 	<input type="hidden" id = "address" name="address" value="${updateFlag!=1? doritualinfoAddress: tombsweepfesEntity.address}"> --%>
				 	<td><input style="width:40px;" type="text" id = "money" name="money" value="100" required></td>
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
					<select style="width:50px;" id="size" name="size" >
						<option value="小">小</option>
					</select>
					</td>
					
					
				 	
				 	<td><textarea rows="3" cols="60" id="summary" name="summary" required onclick="changSumary(this);">${tombsweepfesEntity.summary}</textarea>
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
    		<c:if test="${empty tombsweepfesEntitys}">
    		<form action="${pageContext.request.contextPath}/tombsweepfesController.do?getSerialAndSaveTablet"  onsubmit="return myvalidate()" method="post">
			<input id="id" name="id" type="hidden" value="${id}">
			付款人:<input style="width:100px;" type="text" id = "paymen" name="paymen" value="${clientele}" required>
			<table id = "receipttable" class="imagetable">
				<tr>
					<th>选择</th>
					<th>阳上</th>
					<th>超渡类型</th>
					<th>超渡对象</th>
					<!-- <th>地址</th> -->
					<th>金额</th>
					<th>付款方式</th>
					<th>牌位大小</th>
					<th>摘要</th>
				</tr>
				<tr>
						<td><input style="width:20px;"  type="checkbox"/></td>
					 	<td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" value="${clientele}"></td>
					 	<td>
							<select style="width:100px;" id="type" name="type" onchange="change(this.value,this)">
							<option value="2">十方法界</option>
							<option value="3">门堂历代宗亲</option>
							<option value="4">堕胎婴灵</option>
							<option value="1">无</option>
							</select>
						</td>
					 	<td onclick="getSelected(this)"><input type="hidden" name="ancestor" id = "ancestor" value=""></td>
					 	<!-- <td><input type="text" id = "address" name="address" value=""></td> -->
					 	<input type="hidden" id = "address" name="address" value="${ doritualinfoAddress}">
					 	<td><input style="width:40px;" type="text" id = "money" name="money" value="100" required></td>
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
						<select style="width:50px;" id="size" name="size" >
							<option value="小">小</option>
						</select>
						</td>
						
					 	
					 	<td><textarea rows="3" cols="60" id="summary" name="summary" autofocus required onclick="changSumary(this);"></textarea>
						</td>
						<%-- 	<td><input type="button" value="点击" onclick="LinktoPharmacistbirth('${receipt.ritualid}')"></td> --%>
					   	
					 </tr>
				
			</table>
			<br><br>
			<input type="submit" value="确定" class="button">
		</form>
		</c:if>
		
		<div id="form" style="display:none;"><h5>超渡对象</h5>
	    	<table id = "livingtable" class = "imagetable">
	    	<tr>
				<th>位置</th>
				<!-- <th>称呼</th> -->
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
			
			</table>
			<br>
			<input type = "button" onclick="getAncestorList()" class = "button" value= "确定"></button>
	        <input type = "button" onclick="onHide()" class = "button" value= "取消"></button>
    	</div>
		</center>
 </body>