<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>金刚经管理</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
    <script type = "text/javascript">
  function myvalidate(){
	  var val=$('input:radio[name="payway"]:checked').val();
      if(val==null){
          alert("请选择付款方式！");
          return false;
      }
      
      var money = document.getElementById("money").value;
		if (!(/(^[1-9]\d*$)/.test(money))){
			alert("请输入合法的金钱数目!!");
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
  function addLivingmember(){
	 var span1 = document.createElement('span');
	 var input1 = document.createElement('input');
	 input1.setAttribute('style', 'width:50px;');
	 input1.setAttribute('type', 'text');
	 input1.setAttribute('name', 'livingmember');
	 input1.setAttribute('id', 'livingmember');
	 var span1 = document.createElement('span');
	 span1.appendChild(input1);
	    
	 var btn1 = document.getElementById("obj");
	 btn1.insertBefore(span1,null);
  }
  </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
 <center>
 <form action="${pageContext.request.contextPath}/diamondsutraController.do?saveRecordAndSaveReceipt" onsubmit="return myvalidate()" method="post"">
 <table id = "receipttable" class="imagetable">
			<tr>
				<th>名称</th>
				<td>金刚经登记</td>
			</tr>
			<tr>
				<th>委托人</th>
				<td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" required></td>
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
				<th>祈福对象</th>
				<td>
				<div id="obj">
				<input type = "button" value = "添加对象" onclick = "addLivingmember()">
				<span><input style="width:50px;" type="text" id = "livingmember" name="livingmember"></span>
				
				</div>
				</td>
			</tr>
			<tr>
				<th>开始时间</th>
				<td>
				<input type="text" id = "starttime" name = "starttime" onclick="WdatePicker({dateFmt:'yyyy-MM-dd'})">
				</td>
			</tr>
			<tr>
				<th>持续时间</th>
				<td>
				<select name = "duration" id = "duration">
					  <option value ="一个星期">一个星期</option>
					  <option value ="一个月">一个月</option>
					  <option value="三个月">三个月</option>
					  <option value="六个月">六个月</option>
					  <option value="一年">一年</option>
				</select>
				</td>
			</tr>
			<tr>
				<th>地址</th>
				<td><input style="width:250px;" type="text" id = "address" name="address"></td>
			</tr>
			<tr>
				<th>摘要</th>
				<td><textarea rows="3" cols="50" id="summary" name="summary" required></textarea></td>
			</tr>
		</table>
		<br>
		<input type="submit" class = "button" value = "提交">&nbsp;&nbsp;<input type="reset" class = "button"  value = "重置">
</form>
</center>

 </body>