<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>弥陀诞信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  <script type="text/javascript"> 
  	var index = 0;
 	function change(number,current){	//8月11号修改
  		var inner = "";
  		var tableObj = document.getElementById("receipttable");
  		var arr = document.getElementById("receipttable").getElementsByTagName("tr");
  		if(number == "2"){
  			var r=window.confirm("确定选择十方法界，会撤销最后两行数据！");
  			if(r==false){
  				var s = document.getElementById("type");
  				var ops = s.options;
  				ops[0].selected = true;
  				return;
  			}
  			for(var i=arr.length-1;i > 5;i--){
  	 			tableObj.deleteRow(i);
  	 		}
  		}
  		if(number == "1"){
  			if(parseInt(arr.length) == 6){
  				for(var i=0;i < 2;i++){
  					var tr =  $("#receipttable tr").clone();
  					$("#receipttable").append(tr[1]);
  	  	 		}
  			}
  			
  			/* 20190614 kooking 修改标号*/
  			var newTr = $("#receipttable tr");
  			newTr.children().eq(12).text("7");
  			newTr.children().eq(14).text("8");
  			/* 20190614 kooking */
  		}
  	}
 	function preview(){ 
 		
 		var tableObj = document.getElementById("preview_table");
 		var pre_cowcount = tableObj.rows[0].cells.length;
 		var arr = document.getElementById("preview_table").getElementsByTagName("tr");
 		for(var i=arr.length-1;i > 0;i--){
 			tableObj.deleteRow(i);
 		}
 		

 		//获取值
 		var type = document.getElementById("type").value;
 		if(parseInt(type) == 2){
 			type = "有";
 		}
 		else{
 			type = "无";
 		}
 		
 		var payway = document.getElementById("payway").value; 
 		var address = document.getElementById("address").value; 
 		var money = document.getElementById("money").value; 
 		var surname = document.getElementById("surname").value; 
 		var prayingobj = document.getElementById("prayingobj").value;
 		var paymen = document.getElementById("paymen").value;
 		if(surname == ""){
 			surname = "无";
 		}
 		var releaseObj = ""; 
 		
 		var rowcount = document.getElementById("receipttable").rows.length;
 		var tr = document.getElementById("receipttable").getElementsByTagName("tr");
 		
 		for(var i = 0;i < rowcount;i++){
 			releaseObj += tr[i].getElementsByTagName("td")[1].children[0].value + "#";
 		}
 		var pre_row = tableObj.insertRow();
 		var pre_cow0 = pre_row.insertCell(0);
 		pre_cow0.innerHTML = type;
 		var pre_cow1 = pre_row.insertCell(1);
 		pre_cow1.innerHTML = surname;
 		var pre_cow2 = pre_row.insertCell(2);
 		pre_cow2.innerHTML = prayingobj;
 		var pre_cow3 = pre_row.insertCell(3);
 		pre_cow3.innerHTML = releaseObj;
 		var pre_cow4 = pre_row.insertCell(4);
 		pre_cow4.innerHTML = address;
 		var pre_cow5 = pre_row.insertCell(5);
 		pre_cow5.innerHTML = money;
 		var pre_cow6 = pre_row.insertCell(6);
 		pre_cow6.innerHTML = payway;
 		var pre_cow7 = pre_row.insertCell(7);
 		pre_cow7.innerHTML = paymen;
 		/* for(var j = 0;j < pre_cowcount;j++){
 			var pre_cow = pre_row.insertCell(j);
 			pre_cow.innerHTML = tr[i].getElementsByTagName("td")[j].children[0].value;
 				
 		} */
 			
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
 		for(var i = 0;i < rowcount;i ++){
 			var obj = tr[i].getElementsByTagName("td")[1].children[0].value;
 			if(parseInt(obj.length) > 8){
 				alert("提示 ： 超渡对象只能输入8个字符");
 				return false;
 			}
 		}
 		
 		//判断是否有弥陀诞节日安排，若无，不允许提交
 		if("${fn:length(funeralheld)}" == "0" && "${updateFlag}"!=1){
 			alert("无弥陀诞日期安排记录！");
 			return false;
 		}
 		
 		var money = document.getElementById("money").value;
 		if (!(/(^[1-9]\d*$)/).test(money)){
 			alert("请输入合法的金钱数目!!");
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
  	
  	function changMoney(){
 		var livingCount = 0;
 		$("input[id^='living']").each(function(){
 			if(livingCount != null && $(this).val() != ''){
 				livingCount ++;
 			}
        });
        
        var baseMoney = 0;
        if("${size}" == "大"){
        	 baseMoney = 600;
        }else if("${size}" == "拈香"){
        	baseMoney = 1800;
        }
        
        if(livingCount <= 6){
        	document.getElementById("money").value = baseMoney;
        }else if(livingCount > 6){
        	document.getElementById("money").value = baseMoney + 30 * (livingCount - 6);
        }
        
 	}
  	
  	//修改摘要
  	function changSumary(){
  	
 				var paymen=document.getElementById("paymen").value;
				var sumaryNode=document.getElementById("summary");
				var sumary=paymen+" 交来";
				if("${funeralheld[0].holdDate}"=="${funeralheld[0].endDate}"){
					sumary+="${funeralheld[0].holdDate}";
				}else {
					sumary+="${funeralheld[0].holdDate}"+"至"+"${funeralheld[0].endDate}";
				}
				sumary+="弥陀诞";
				if("${size}" == "大"){
					sumary+="大牌功德款";
				}else{
					sumary+="拈香功德款";
				}
				sumaryNode.innerHTML=sumary; 	
		/* if("${fn:length(funeralheld)}" == "0"){
				alert("无弥陀诞日期安排记录！");
			}else if("${fn:length(funeralheld)}" == "1"||"${fn:length(funeralheld)}" == "2"){
				
				
			}else{
				alert("今年的弥陀诞日期安排记录数只能有两条！");
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
		<h4>编辑弥陀诞${size}牌记录</h4><input type="button" value="预览" class="button" onclick = "preview()">
		
 		
 		<br><br>
		<center>
		<div id="preview_form" style="display:none;">
	    	<table id = "preview_table" class = "imagetable">
	    	<tr>
				<th>是否有十方法界</th>
				<th>是否有门堂历代</th>
				<th>阳上</th>
				<th>超渡对象</th>
	     		<th>地址</th>
				<th>金额</th>
				<th>付款方式</th>
				<th>付款人</th>
			</tr> 
			
		</table>
			<br>
	        <input type = "button" onclick="hide()" class = "button" value= "取消"></button><br><br>
    	</div>
		<c:if test="${!empty ancestorList}">
		
		<form action="${pageContext.request.contextPath}/amitabhabirthController.do?getSerialAndSaveTablet" onsubmit="return myvalidate()" method="post">
		<input id="id" name="id" type="hidden" value="${id}">
		<input id="size" name="sizeflag" type="hidden" value="${size}">
		<input type = "hidden" value = "${amitabhabirthid}" name = "amitabhabirthid" id = "amitabhabirthid">
		<table class="imagetable" width="1000px">
		<tr>
			<th>填写个人信息</th>
			<th>填写超渡对象</th>
		</tr>
		<tr>
		<td>
			<table class="imagetable" width="500px">
			<tr>
				<th nowrap>历代宗亲</th>
				<td><input style="width:80px;" type ="text" name = "surname" id = "surname" value = "${surname}">历代宗亲</td>
			</tr>
			<tr>
				<th>是否有十方法界</th>
				<td><select style="width:100px;" id="type" name="type" font-size:20px;" onchange="change(this.value,this)">
								<option value="1" ${type == 1 ? 'selected = "selected"' : '' }>无</option>
								<option value="2" ${type == 2 ? 'selected = "selected"' : '' }>十方法界</option>
						</select>
				</td>
			</tr>
			<tr>
				<th>付款人</th>
				<td><input style="width:100px;" type="text" id = "paymen" name="paymen" value="${amitabhabirthEntity.paymen}" required> 
				</td>
			</tr>
			<tr>
				<th>敬设</th>
				<td><input style="width:150px;" type="text" id = "book" name="book" value="《三时系念》一昶日" required> 
				</td>
			</tr>
			<tr>
				<th>阳上</th>
				<td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" value="${amitabhabirthEntity.prayingobj}" required> 
				</td>
			</tr>
			<tr>
				<th>付款方式</th>
				<td> <select style="width:60px;" id="payway" name="payway" >
								<option value="现金" ${amitabhabirthEntity.payway == '现金' ? 'selected = "selected"' : '' }>现金</option>
								<option value="刷卡" ${amitabhabirthEntity.payway == '刷卡' ? 'selected = "selected"' : '' }>刷卡</option>
								<option value="支付宝" ${amitabhabirthEntity.payway == '支付宝' ? 'selected = "selected"' : '' }>支付宝</option>
								<option value="微信" ${amitabhabirthEntity.payway == '微信' ? 'selected = "selected"' : '' }>微信</option>
								<option value="其他" ${amitabhabirthEntity.payway == '其他' ? 'selected = "selected"' : '' }>其他</option>
						</select>
				</td>
			</tr>
			<tr>
				<th>地址</th>
				<td> <input type="text" style="width:400px;" id = "address" name="address" value="${amitabhabirthEntity.address}"></td>
<%-- 				<td> <input type="text" style="width:250px;" id = "address" name="address" value="${updateFlag!=1? doritualinfoAddress:amitabhabirthEntity.address}"></td> --%>
			</tr>
			<%-- <input type="hidden" style="width:250px;" id = "address" name="address" value="${amitabhabirthEntity.address}"> --%>
			<tr>
				<th>金额</th>
				<td> <input type="text" style="width:60px;" id = "money" name="money" value="${amitabhabirthEntity.money}" required onclick="changMoney();"></td>
			</tr>
			<tr>
				<th>摘要</th>
				<td> <textarea rows="3" cols="60" id="summary" name="summary"  onclick="changSumary()" autofocus required>${amitabhabirthEntity.summary}</textarea></td>
			</tr>
			
			</table>
		</td>
		<td>
			<table id = "receipttable" class="imagetable" width="500px">
				<c:forEach items="${ancestorList}" var="ancestor" varStatus="stauts">
					<tr>
							<!-- 添加标号 -->
							<td style="width:30px; text-align:center;">${stauts.index+1}</td>
						 	<td>
						 	<input style="width:100%;" type="text" name="ancestor" id = "ancestor" value="${ancestor}"
						 	<c:if test="${stauts.index+1==5}">placeholder="请在此处输入堕胎婴灵，最多8个字符"</c:if>>
						 	</td>
					</tr>
				</c:forEach>
				
			</table>
		</td>
		</tr>
		</table>
		<br><br>
		<input type="submit" value="确定" class="button">&nbsp;&nbsp;&nbsp;
		
		</form>
		</c:if>
		
    	</center>
    	
    	<center>
    	<c:if test="${empty amitabhabirthEntitys}">
    	<form action="${pageContext.request.contextPath}/amitabhabirthController.do?getSerialAndSaveTablet" onsubmit="return myvalidate()" method="post">
		<input id="id" name="id" type="hidden" value="${id}">
		<input id="size" name="sizeflag" type="hidden" value="${size}">
		<table class="imagetable" width="1000px">
		<tr>
			<th>填写个人信息</th>
			<th>填写超渡对象</th>
		</tr>
		<tr>
		<td>
			<table class="imagetable" width="500px">
			<tr>
				<th nowrap>历代宗亲</th>
				<td><input style="width:80px;" type ="text" name = "surname" id = "surname" value = "">历代宗亲</td>
			</tr>
			<tr>
				<th>是否有十方法界</th>
				<td><select style="width:100px;" id="type" name="type" font-size:20px;" onchange="change(this.value,this)">
								<option value="1">无</option>
								<option value="2">十方法界</option>
						</select>
				</td>
			</tr>
			<tr>
				<th>付款人</th>
				<td><input style="width:100px;" type="text" id = "paymen" name="paymen" value="${clientele}" required> 
				</td>
			</tr>
			<tr>
				<th>敬设</th>
				<td><input style="width:150px;" type="text" id = "book" name="book" value="《三时系念》一昶日" required> 
				</td>
			</tr>
			<tr>
				<th>阳上</th>
				<td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" value="${clientele}" required>
				</td>
			</tr>
			<tr>
				<th>付款方式</th>
				<td> <select style="width:60px;" id="payway" name="payway" >
								<option value="现金">现金</option>
								<option value="刷卡">刷卡</option>
								<option value="支付宝">支付宝</option>
								<option value="微信">微信</option>
								<option value="其他">其他</option>
						</select>
				</td>
			</tr>
			<tr>
				<th>地址</th>
				<td> <input type="text" style="width:400px;" id = "address" name="address" value="${doritualinfoAddress}"></td>
			</tr>
			<%-- <input type="hidden" style="width:250px;" id = "address" name="address" value="${doritualinfoAddress}"> --%>
			<tr>
				<th>金额</th>
				<td> <input type="text" style="width:60px;" id = "money" name="money" value="" required onclick="changMoney();"></td>
			</tr>
			<tr>
				<th>摘要</th>
				<td> <textarea rows="3" cols="60" id="summary" name="summary"  onclick="changSumary()" autofocus required></textarea></td>
			</tr>
			
			</table>
		</td>
		<td>
			<table id = "receipttable" class="imagetable" width="500px">
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">1</td>
					 	<td><input style="width:100%;" type="text" name="ancestor" id = "ancestor" value="" placeholder="请输入最多8个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">2</td>
					 	<td><input style="width:100%;" type="text" name="ancestor" id = "ancestor" value="" placeholder="请输入最多8个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">3</td>
					 	<td><input style="width:100%;" type="text" name="ancestor" id = "ancestor" value="" placeholder="请输入最多8个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">4</td>
					 	<td><input style="width:100%;" type="text" name="ancestor" id = "ancestor" value="" placeholder="请输入最多8个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">5</td>
					 	<td><input style="width:100%;" type="text" name="ancestor" id = "ancestor" value="" placeholder="堕胎婴灵请在此处输入，最多8个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">6</td>
					 	<td><input style="width:100%;" type="text" name="ancestor" id = "ancestor" value="" placeholder="请输入最多8个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">7</td>
					 	<td><input style="width:100%;" type="text" name="ancestor" id = "ancestor" value="" placeholder="请输入最多8个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">8</td>
					 	<td><input style="width:100%;" type="text" name="ancestor" id = "ancestor" value="" placeholder="请输入最多8个字符"></td>
				</tr>
				
			</table>
		</td>
		</tr>
		</table>
		<br><br>
			<input type="submit" value="确定" class="button">&nbsp;&nbsp;&nbsp;
		</form>
		</c:if>
		</center>
	
 </body>