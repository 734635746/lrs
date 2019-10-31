<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>供斋管理</title>
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
 <center>
 <form action="${pageContext.request.contextPath}/gongzhaiController.do?saveRecordAndSaveReceipt" onsubmit="return myvalidate()" method="post"">
<table id = "receipttable" class="imagetable">
			<tr>
				<th>名称</th>
				<td>供斋登记</td>
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
				</td>
			</tr>
			<tr>
				<th>对象</th>
				<td>
				<div id="obj">
				<input type = "button" value = "添加对象" onclick = "addLivingmember()">
				<span><input style="width:50px;" type="text" id = "livingmember" name="livingmember"></span>
				
				</div>
				</td>
			</tr>
			
			<tr>
				<th>举办日期</th>
				<td>
				<select name="month" id="month">
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
				</select>
				<select name="day" id="day">
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
					    <option value="廿">廿</option>
					    <option value="廿一">廿一</option>
					    <option value="廿二">廿二</option>
					    <option value="廿三">廿三</option>
					    <option value="廿四">廿四"</option>
					    <option value="廿五">廿五</option>
					    <option value="廿六">廿六</option>
					    <option value="廿七">廿七</option>
					    <option value="廿八">廿八</option>
					    <option value="廿九">廿九</option>
					    <option value="卅">卅</option>
						
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