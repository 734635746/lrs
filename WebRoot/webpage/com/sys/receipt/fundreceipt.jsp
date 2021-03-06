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
	
	function changSumary(currentNode){
		//获取付款人
		var paymenNode=document.getElementById("paymen");
		var paymen=paymenNode.value;
		var sumary = paymen+"交来";
		//获取善款用途
		var purposeNode=document.getElementById("purpose");
		var purpose=purposeNode.value;
		//修改摘要
		sumary += purpose+"功德款";
		document.getElementById("summary").value = sumary;
 	}
  </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
 <center>
 <form action="${pageContext.request.contextPath}/receiptController.do?saveFundReceipt" onsubmit="return myvalidate()" method="post"">
 <table id = "receipttable" class="imagetable">
			<tr>
				<th>名称</th>
				<td>善款</td>
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
					<th>用途</th>
					<td>
					<select style="width:100px;" id="purpose" name="purpose" >
							<c:choose>
								<c:when test="${requestScope.purposeList==null }">
									<%--若数据库中没有数据 --%>
									<option value="">暂无数据，请添加</option>
								</c:when>
								<c:otherwise>
									<%-- 显示数据到表格中--%>
									<c:forEach items="${requestScope. purposeList }"
										var="purpose">
										<option value="${purpose.purpose }">${purpose.purpose }</option>
									</c:forEach>
								</c:otherwise>
							</c:choose>
							<!-- <input style="width:100px;" type="text" id = "purpose" name="purpose"> -->
					</select>
					</td>
				</tr>
			<tr>
				<th>摘要</th>
				<td><textarea rows="3" cols="50" id="summary" name="summary"  onclick="changSumary();"></textarea></td>
			</tr>
		</table>
		<br>
		<input type="submit" class = "button" value = "提交">&nbsp;&nbsp;<input type="reset" class = "button"  value = "重置">
</form>
</center>
 </body>