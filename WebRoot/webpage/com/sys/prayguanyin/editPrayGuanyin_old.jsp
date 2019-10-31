<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>上供信息</title>
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
 			releaseObj += tr[i].getElementsByTagName("td")[0].children[0].value + "#";
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
 			var obj = tr[i].getElementsByTagName("td")[0].children[0].value;
 			if(parseInt(obj.length) > 12){
 				alert("提示 ： 祈福对象只能输入12个字符");
 				return false;
 			}
 		}
 		
 		var money = document.getElementById("money").value;
 		if (!(/(^[1-9]\d*$)/.test(money))){
 			alert("请输入合法的金钱数目!!");
 			return false;
 		}
 		
 		var r=window.confirm("确定填写信息没错吗？确定后不可以修改");
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
		<h4>编辑上供记录</h4><input type="button" value="预览" class="button" onclick = "preview()">
		
 		
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
		<c:if test="${morningpforrEntity != null}">
		
		<form action="${pageContext.request.contextPath}/prayguanyinController.do?getSerialAndSaveTablet" onsubmit="return myvalidate()" method="post">
		<input id="id" name="id" type="hidden" value="${id}">
		<table class="imagetable" width="800px">
		<tr>
			<th>填写个人信息</th>
			<th>填写祈福对象</th>
		</tr>
		<tr>
		<td>
			<table class="imagetable" width="300px">
			<tr>
				<th nowrap>付款人</th>
				<td><input style="width:100px;" type="text" id = "paymen" name="paymen" value="${morningpforrEntity.paymen}" required> 
				</td>
			</tr>
			<tr>
				<th>阳上</th>
				<td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" value="${morningpforrEntity.prayingobj}" required> 
				</td>
			</tr>
			<tr>
				<th>敬设</th>
				<td><input style="width:150px;" type="text" id = "book" name="book" value="${morningpforrEntity.book}" required>
				</td>
			</tr>
			<tr>
				<th>举行日期</th>
				<td>
				<select style="width: 60px" name="month" id="month">
						<option value="">月份</option>
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
				</select>&nbsp;
				<select style="width: 100px" name="day1" id="day1">
						<option value="">--请选择日子--</option>
					    <option value="初一">初一</option>
					    <option value="初二">初二</option>
					    <option value="初三">初三</option>
					    <option value="初四">初四</option>
					    <option value="初五">初五</option>
					    <option value="初六">初六</option>
					    <option value="初七">初七</option>
					    <option value="初八">初八</option>
					    <option value="初九">初九</option>
					    <option value="初十">初十</option>
					    <option value="十一">十一</option>
					    <option value="十二">十二</option>
					    <option value="十三">十三</option>
					    <option value="十四">十四</option>
					    <option value="十五">十五</option>
					    <option value="十六">十六</option>
					    <option value="十七">十七</option>
					    <option value="十八">十八</option>
					    <option value="十九">十九</option>
					    <option value="廿十">廿十</option>
					    <option value="廿一">廿一</option>
					    <option value="廿二">廿二</option>
					    <option value="廿三">廿三</option>
					    <option value="廿四">廿四"</option>
					    <option value="廿五">廿五</option>
					    <option value="廿六">廿六</option>
					    <option value="廿七">廿七</option>
					    <option value="廿八">廿八</option>
					    <option value="廿九">廿九</option>
					    <option value="卅十">卅十</option>
						
				</select>
				</td>
			</tr>
			<tr>
				<th>付款方式</th>
				<td><select style="width:60px;" id="payway" name="payway" >
								<option value="现金" ${morningpforrEntity.payway == '现金' ? 'selected = "selected"' : '' }>现金</option>
								<option value="刷卡" ${morningpforrEntity.payway == '刷卡' ? 'selected = "selected"' : '' }>刷卡</option>
								<option value="支付宝" ${morningpforrEntity.payway == '支付宝' ? 'selected = "selected"' : '' }>支付宝</option>
								<option value="微信" ${morningpforrEntity.payway == '微信' ? 'selected = "selected"' : '' }>微信</option>
								<option value="其他" ${morningpforrEntity.payway == '其他' ? 'selected = "selected"' : '' }>其他</option>
						</select>
				</td>
			</tr>
			<tr>
				<th>地址</th>
				<td> <input type="text" style="width:250px;" id = "address" name="address" value="${morningpforrEntity.address}"></td>
			</tr>
			<tr>
				<th>金额</th>
				<td> <input type="text" style="width:60px;" id = "money" name="money" value="${morningpforrEntity.money}" required></td>
			</tr>
			<tr>
				<th>摘要</th>
				<td> <textarea rows="3" cols="50" id="summary" name="summary"  autofocus required>${morningpforrEntity.summary}</textarea></td>
			</tr>
			
			</table>
		</td>
		<td>
			<table id = "receipttable" class="imagetable" width="500px">
		
				<c:forEach items="${livingmenberList}" var="livingmenber" varStatus="stauts">
					<tr>
						 	<td><input style="width:100%;" type="text" name="livingmember" id = "livingmember" value="${livingmenber}"></td>
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
    	<c:if test="${morningpforrEntity == null}">
    	<form action="${pageContext.request.contextPath}/prayguanyinController.do?getSerialAndSaveTablet" onsubmit="return myvalidate()" method="post">
		<input id="id" name="id" type="hidden" value="${id}">
		<table class="imagetable" width="800px">
		<tr>
			<th>填写个人信息</th>
			<th>填写祈福对象</th>
		</tr>
		<tr>
		<td>
			<table class="imagetable" width="300px">
			
			<tr>
				<th nowrap>付款人</th>
				<td><input style="width:100px;" type="text" id = "paymen" name="paymen" value="${clientele}" required> 
				</td>
			</tr>
			<tr>
				<th>阳上</th>
				<td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" value="${clientele}" required>
				</td>
			</tr>
			<tr>
				<th>敬设</th>
				<td><input style="width:100px;" type="text" id = "book" name="book" value="" required>
				</td>
			</tr>
			<tr>
				<th>举行日期</th>
				<td>
				<select style="width: 60px" name="month" id="month">
						<option value="">月份</option>
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
				</select>&nbsp;
				<select style="width: 100px" name="day1" id="day1">
						<option value="">--请选择日子--</option>
					    <option value="初一">初一</option>
					    <option value="初二">初二</option>
					    <option value="初三">初三</option>
					    <option value="初四">初四</option>
					    <option value="初五">初五</option>
					    <option value="初六">初六</option>
					    <option value="初七">初七</option>
					    <option value="初八">初八</option>
					    <option value="初九">初九</option>
					    <option value="初十">初十</option>
					    <option value="十一">十一</option>
					    <option value="十二">十二</option>
					    <option value="十三">十三</option>
					    <option value="十四">十四</option>
					    <option value="十五">十五</option>
					    <option value="十六">十六</option>
					    <option value="十七">十七</option>
					    <option value="十八">十八</option>
					    <option value="十九">十九</option>
					    <option value="廿十">廿十</option>
					    <option value="廿一">廿一</option>
					    <option value="廿二">廿二</option>
					    <option value="廿三">廿三</option>
					    <option value="廿四">廿四"</option>
					    <option value="廿五">廿五</option>
					    <option value="廿六">廿六</option>
					    <option value="廿七">廿七</option>
					    <option value="廿八">廿八</option>
					    <option value="廿九">廿九</option>
					    <option value="卅十">卅十</option>
						
				</select>
				</td>
			</tr>
			<tr>
				<th>付款方式</th>
				<td>  <select style="width:60px;" id="payway" name="payway" >
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
				<td> <input type="text" style="width:250px;" id = "address" name="address" value=""></td>
			</tr>
			<tr>
				<th>金额</th>
				<td> <input type="text" style="width:60px;" id = "money" name="money" value="" required></td>
			</tr>
			<tr>
				<th>摘要</th>
				<td> <textarea rows="3" cols="50" id="summary" name="summary"  autofocus required></textarea></td>
			</tr>
			
			</table>
		</td>
		<td>
			<table id = "receipttable" class="imagetable" width="500px">
				<tr>
					 	<td><input style="width:100%;" type="text" name="livingmember" id = "livingmember" value="" placeholder="请输入最多12个字符"></td>
				</tr>
				<tr>
					 	<td><input style="width:100%;" type="text" name="livingmember" id = "livingmember" value="" placeholder="请输入最多12个字符"></td>
				</tr>
				<tr>
					 	<td><input style="width:100%;" type="text" name="livingmember" id = "livingmember" value="" placeholder="请输入最多12个字符"></td>
				</tr>
				<tr>
					 	<td><input style="width:100%;" type="text" name="livingmember" id = "livingmember" value="" placeholder="请输入最多12个字符"></td>
				</tr>
				<tr>
					 	<td><input style="width:100%;" type="text" name="livingmember" id = "livingmember" value="" placeholder="请输入最多12个字符"></td>
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