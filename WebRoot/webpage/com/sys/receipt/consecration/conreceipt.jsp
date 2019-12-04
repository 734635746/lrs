<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>收据表</title>
  <t:base type="jquery,easyui,tools,DatePicker">
   <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  </t:base>
  <script type = "text/javascript">
  function myvalidate(){
	  	
	  	 var val=$('input:radio[name="payway"]:checked').val();
         if(val==null){
             alert("请选择付款方式！");
             return false;
         }
         
		var r=window.confirm("确定填写信息没错吗？确定后不可以修改！");
		if (r==true)
		{
			return true;
		}
		else
		{
		  return false;
		}
		
	}
	
	function changeSummary(){
		var paymen = document.getElementById("paymen").value;
		document.getElementById("summary").value = paymen + "交来大殿祈福功德款";
	}
  </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
 <center>
 <form action="${pageContext.request.contextPath}/receiptController.do?saveConReceipt" onsubmit="return myvalidate()"  method="post">
 <table id = "receipttable" class="imagetable">
			<tr>
				<th>名称</th>
				<td>开光</td>
			</tr>
			<tr>
				<th>委托人</th>
				<td><input style="width:100px;" type="text" id = "paymen" name="paymen" required></td>
			</tr>
			<tr>
				<th>金额</th>
				<td><input style="width:50px;" type="text" id = "money" name="money" required></td>
			</tr>
			<tr>
				<th>付款方式</th>
				<td><input type="radio" value="现金" name="payway">现金
				<input type="radio" value="刷卡" name="payway">刷卡
				<input type="radio" value="支付宝" name="payway">支付宝
				<input type="radio" value="微信" name="payway">微信
				<input type="radio" value="其他" name="payway">其他
				</td>
			</tr>
			<tr>
				<th>地址</th>
				<td><input style="width:250px;" type="text" id = "address" name="address"></td>
			</tr>
			<tr>
				<th>摘要</th>
				<td><textarea rows="3" cols="50" id="summary" name="summary" onclick="changeSummary();"></textarea></td>
			</tr>
		</table>
		<br>
		<input type="submit" class = "button" value = "提交">&nbsp;&nbsp;<input type="reset" class = "button"  value = "重置">
</form>
</center>
 </body>