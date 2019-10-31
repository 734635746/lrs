<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>药师诞信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  <script type="text/javascript"> 
  	var index = 0;
function preview(){ 
 		
 		var tableObj = document.getElementById("preview_table");
 		var pre_cowcount = tableObj.rows[0].cells.length;
 		var arr = document.getElementById("preview_table").getElementsByTagName("tr");
 		for(var i=arr.length-1;i > 0;i--){
 			tableObj.deleteRow(i);
 		}
 		var livingObj = ""; 

 		
 		var payway = document.getElementById("payway").value; 
 		var address = document.getElementById("address").value; 
 		var money = document.getElementById("money").value; 
 		var prayingobj = document.getElementById("prayingobj").value;
 		var paymen = document.getElementById("paymen").value;
 		
 		var rowcount = document.getElementById("receipttable").rows.length;
 		var tr = document.getElementById("receipttable").getElementsByTagName("tr");
 		
 		for(var i = 0;i < rowcount;i++){
 			livingObj += tr[i].getElementsByTagName("td")[1].children[0].value + "#";
 		}
 		var pre_row = tableObj.insertRow();
 		var pre_cow0 = pre_row.insertCell(0);
 		pre_cow0.innerHTML = prayingobj;
 		var pre_cow1 = pre_row.insertCell(1);
 		pre_cow1.innerHTML = livingObj;
 		var pre_cow2 = pre_row.insertCell(2);
 		pre_cow2.innerHTML = address;
 		var pre_cow3 = pre_row.insertCell(3);
 		pre_cow3.innerHTML = money;
 		var pre_cow4 = pre_row.insertCell(4);
 		pre_cow4.innerHTML = payway;
 		var pre_cow5 = pre_row.insertCell(5);
 		pre_cow5.innerHTML = paymen;
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
 			if(parseInt(obj.length) > 18){
 				alert("提示 ： 祈福对象只能输入18个字符");
 				return false;
 			}
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
  	

		function changSumary(){
 				/* 付款人交来某月某日至某月某日某法会大牌（拈香）功德款 */
 				var paymen=document.getElementById("paymen").value;
				var sumaryNode=document.getElementById("summary");
				var sumary=paymen+" 交来";
				if("${funeralheld[0].holdDate}"=="${funeralheld[0].endDate}"){
					sumary+="${funeralheld[0].holdDate}";
				}else {
					sumary+="${funeralheld[0].holdDate}"+"至"+"${funeralheld[0].endDate}";
				}
				sumary+="${funeralheld[0].ritualtype}";
				if("${size}" == "大"){
					sumary+="大牌功德款";
				}else{
					sumary+="拈香功德款";
				}
				sumaryNode.innerHTML=sumary;
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
		<h4>编辑药师诞${size}牌记录</h4><input type="button" value="预览" class="button" onclick = "preview()">
		
 		<br><br>
		<center>
		<div id="preview_form" style="display:none;">
	    	<table id = "preview_table" class = "imagetable">
	    	<tr>
				<th>祈福者</th>
				<th>祈福对象</th>
	     		<th>地址</th>
				<th>金额</th>
				<th>付款方式</th>
				<th>付款人</th>
			</tr> 
			
		</table>
			<br>
	        <input type = "button" onclick="hide()" class = "button" value= "取消"></button><br><br>
    	</div>
		<c:if test="${!empty livingList}">
		
		<form action="${pageContext.request.contextPath}/pharmacistbirthController.do?getSerialAndSaveTablet" onsubmit="return myvalidate()" method="post">
		<input id="id" name="id" type="hidden" value="${id}">
		<input type = "hidden" value = "${pharmacistbirthid}" name = "pharmacistbirthid" id = "pharmacistbirthid">
		<input id="size" name="sizeflag" type="hidden" value="${size}">
		<table class="imagetable" width="1000px">
		<tr>
			<th>填写个人信息</th>
			<th>填写祈福对象</th>
		</tr>
		<tr>
		<td>
			<table class="imagetable" width="500px">
			<tr>
				<th nowrap>付款人</th>
				<td><input style="width:100px;" type="text" id = "paymen" name="paymen" value="${pharmacistbirthEntity.paymen}" required> 
				</td>
			</tr>
			<tr>
				<th>敬设</th>
				<td><input style="width:150px;" type="text" id = "book" name="book" value="《药师经》一昶日" required> 
				</td>
			</tr>
			<tr>
				<th>祈福者</th>
				<td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" value="${pharmacistbirthEntity.prayingobj}" required> 
				</td>
			</tr>
			<tr>
				<th>付款方式</th>
				<td><select style="width:60px;" id="payway" name="payway" >
								<option value="现金" ${pharmacistbirthEntity.payway == '现金' ? 'selected = "selected"' : '' }>现金</option>
								<option value="刷卡" ${pharmacistbirthEntity.payway == '刷卡' ? 'selected = "selected"' : '' }>刷卡</option>
								<option value="支付宝" ${pharmacistbirthEntity.payway == '支付宝' ? 'selected = "selected"' : '' }>支付宝</option>
								<option value="微信" ${pharmacistbirthEntity.payway == '微信' ? 'selected = "selected"' : '' }>微信</option>
								<option value="其他" ${pharmacistbirthEntity.payway == '其他' ? 'selected = "selected"' : '' }>其他</option>
						</select>
				</td>
			</tr>
			<tr>
				<th>地址</th>
				<td> <input type="text" style="width:400px;" id = "address" name="address" value="${pharmacistbirthEntity.address}"></td>
<%-- 				<td> <input type="text" style="width:250px;" id = "address" name="address" value="${updateFlag!=1? doritualinfoAddress:pharmacistbirthEntity.address}"></td> --%>
			</tr>
			<tr>
				<th>金额</th>
				<td> <input type="text" style="width:60px;" id = "money" name="money" value="${pharmacistbirthEntity.money}" required onclick="changMoney();"></td>
			</tr>
			<tr>
				<th>摘要</th>
				<td> <textarea rows="3" cols="60" id="summary" name="summary"  onclick="changSumary()" autofocus required>${pharmacistbirthEntity.summary}</textarea></td>
			</tr>
			
			</table>
		</td>
		<td>
			<table id = "receipttable" class="imagetable" width="500px">
				<c:forEach items="${livingList}" var="living" varStatus="stauts">
					<!-- 只显示6条祈福对象 -->
					<c:if test="${stauts.index<6 }">
					<tr>
							<!-- 添加标号 -->
							<td style="width:30px; text-align:center;">${stauts.index+1 }</td>
						 	<td>
						 	<input style="width:100%;" type="text" name="living" id = "living" value="${living}" 
						 	<c:if test="${stauts.index==4 or stauts.index==5 }">placeholder="公司名称请在此处输入，最多18个字符"</c:if>>
						 	</td>
					</tr>
					</c:if>
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
    	<c:if test="${empty livingList}">
    	<form action=${pageContext.request.contextPath}/pharmacistbirthController.do?getSerialAndSaveTablet onsubmit="return myvalidate()" method="post">
		<input id="id" name="id" type="hidden" value="${id}">
		<input id="size" name="sizeflag" type="hidden" value="${size}">
		<table class="imagetable" width="1000px">
		<tr>
			<th>填写个人信息</th>
			<th>填写祈福对象</th>
		</tr>
		<tr>
		<td>
			<table class="imagetable" width="500px">
			<tr>
				<th nowrap>付款人</th>
				<td><input style="width:100px;" type="text" id = "paymen" name="paymen" value="${clientele}" required> 
				</td>
			</tr>
			<tr>
				<th>敬设</th>
				<td><input style="width:150px;" type="text" id = "book" name="book" value="《药师经》一昶日" required> 
				</td>
			</tr>
			<tr>
				<th>祈福者</th>
				<td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" value="${clientele}" required>
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
				<td> <input type="text" style="width:400px;" id = "address" name="address" value="${doritualinfoAddress}"></td>
			</tr>
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
					 	<td><input style="width:100%;" type="text" name="living" id = "living" value="" placeholder="请输入最多10个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">2</td>
					 	<td><input style="width:100%;" type="text" name="living" id = "living" value="" placeholder="请输入最多10个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">3</td>
					 	<td><input style="width:100%;" type="text" name="living" id = "living" value="" placeholder="请输入最多10个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">4</td>
					 	<td><input style="width:100%;" type="text" name="living" id = "living" value="" placeholder="请输入最多10个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">5</td>
					 	<td><input style="width:100%;" type="text" name="living" id = "living" value="" placeholder="公司名称请在此处输入，最多18个字符"></td>
				</tr>
				<tr>
						<!-- 添加标号 -->
						<td style="width:30px; text-align:center;">6</td>
					 	<td><input style="width:100%;" type="text" name="living" id = "living" value="" placeholder="公司名称请在此处输入，最多18个字符"></td>
				</tr>
				
				<!-- 祈福对象改为6个上限 -->
				<!-- <tr>
					 	<td><input style="width:100%;" type="text" name="living" id = "living" value="" placeholder="请输入最多18个字符"></td>
				</tr>
				<tr>
					 	<td><input style="width:100%;" type="text" name="living" id = "living" value="" placeholder="请输入最多18个字符"></td>
				</tr> -->
				
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