<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    
    <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
    <script type="text/javascript" src="${pageContext.request.contextPath}/resources/js/jquery.js"></script>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script type="text/javascript">
		
		
		function getItems(){
			var itemName = document.getElementById("itemName").value;
			if(itemName == ""){
				alert("请填写名称！");
				return;
			}
			var url = "warehousing_detailController.do?queryItem&itemName=" + encodeURIComponent(encodeURIComponent(itemName));
			$.ajax({
				type : 'POST',
				url : url,
				success : function(data) {
					var inner="<table class = \"imagetable\" border=\"1\" cellspacing=\"0\" cellpadding=\"0\"><tr><th></th><th>物品编码</th><th>货物名称</th><th>生产地址</th><th>当前库存量</th><th>单位</th><th>操作</th></tr>";
					json = JSON.parse(data);
					for(var i = 0;i < json.length;i++){
						inner += "<tr><td>" + (i+1) + "</td>";
						inner += "<td>" + json[i].item_stdmode + "</td>";
						inner += "<td>" + json[i].item_name + "</td>";
						inner += "<td>" + json[i].item_manufacturer + "</td>";
						inner += "<td>" + json[i].current_inventory + "</td>";
						inner += "<td>" + json[i].unit + "</td>";
						inner += "<td><input type = \"button\" class = \"button\" value = \"添加\"  onclick = \"addItem('" + json[i].id + "'," + "'" + json[i].item_name + "')\"></td>";
						inner += "</tr>";
					}
					
					inner += "</table><br>";
					/* inner += "<input type=\"button\" class=\"button\" value=\"确定交接\" onclick=\"comfirmStat()\">"; */
					document.getElementById("show").innerHTML = inner;
				}
			});
		}
		function addItem(id,name){
			myclick(id,name);
		}
		
		function myclick(id,name){

			document.getElementById("form").style.display='block';
			document.getElementById("name").value = name;
			document.getElementById("itemId").value = id;
		}
		function on_submit(){
			
			var name = document.getElementById("name").value;
			var number = document.getElementById("number").value;
			if(number == ""){
				alert("请填写添加数量！");
				return ;
			}
			var itemId = document.getElementById("itemId").value;
			var r=window.confirm("确定添加 " + name + " 货物 " + number + " 个吗？");
	 		if (r==true)
	 		{
				var url = "warehousing_detailController.do?addOldItem&itemId=" + itemId + "&number=" + number;
				$.ajax({
					type : 'POST',
					url : url,
					success : function(data) {
						alert(data);
						getItems();
					}
				});
	 			document.getElementById("form").style.display="none";
	 			return true;
	 		}
	 		else
	 		{
	 		  return false;
	 		}
	 		
			
		}
		
		function on_exit(){
			document.getElementById("form").style.display="none";
		}
		function on_exit1(){
			document.getElementById("form2").style.display="none";
		}
		
		function addNewItem(){
			document.getElementById("form2").style.display='block';
		}
		
		function add_Submit(){
			
			var item_stdmode = document.getElementById("item_stdmode").value;
			var item_name = document.getElementById("item_name").value;
			var item_manufacturer = document.getElementById("item_manufacturer").value;
			var manufacturer_address = document.getElementById("manufacturer_address").value;
			var inventory = document.getElementById("inventory").value;
			var price = document.getElementById("price").value;
			var unit = document.getElementById("unit").value;
			var quantity_storage = document.getElementById("quantity_storage").value;
			
			var r=window.confirm("确定填写信息没错吗？");
	 		if (r==true)
	 		{
				var url = "warehousing_detailController.do?addNewItem&item_name=" + encodeURIComponent(encodeURIComponent(item_name)) + "&item_manufacturer=" + encodeURIComponent(encodeURIComponent(item_manufacturer)) 
						+ "&manufacturer_address=" + encodeURIComponent(encodeURIComponent(manufacturer_address)) + "&inventory=" + inventory
						+ "&item_stdmode=" + encodeURIComponent(encodeURIComponent(item_stdmode)) + "&price=" + price + "&unit=" + encodeURIComponent(encodeURIComponent(unit))
						+ "&quantity_storage=" + quantity_storage;
				$.ajax({
					type : 'POST',
					url : url,
					success : function(data) {
						alert(data);
						on_exit1();
					}
				});
	 			document.getElementById("form").style.display="none";
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
 	border:1px;
 	background-color: white;
 	/* position:absolute;/*绝对对齐*/ */
 	z-index:2;
 	}
#form h5{
 	margin: 1px;
 	background-color: white;
 	height: 24px;
 	}

</style>
  </head>
  
  <body>
   	<h4>入库登记</h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	填写物品编码（或者物品名称）：<input type = "text" id = "itemName" name = "itemName" value = "">
   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type = "button" name = "mysubmit" class = "button" value = "查找" onclick = "getItems()">
   	&nbsp;<input type = "button" name = "mysubmit" class = "button" value = "添加记录" onclick = "addNewItem()"><br><br>
	<center>
	
	<div id="form" style="display:none;"><h5>添加货物</h5>
	    	<form name="form1">
	    	<table class = "imagetable">
	    	<tr>
		    		<th>
		        	货物名称
					</th>
					<td>
						<input type="text" id = "name" readonly/>
					</td>
			</tr>
			<tr>
					<th>添加数量</th>
					<td><input type="text" id = "number" name="" /></td>
			</tr>
			</table>
			<br>
				 <input type="hidden" id = "itemId" value = ""/>
	            <input type="button" class = "button" value="提交" onclick = "on_submit()"/>
	            <input type = "button" onclick="on_exit()" class = "button" value= "取消"></button>
	        </form>
    </div>
    
	<div id="show" style="overflow-y:hidden;position: relative;z-index:0"></div>
	
	
    
    <div id="form2" style="display:none;"><h5>添加记录</h5>
	    	<form name="form1">
	    	<table class = "imagetable">
	    	<tr>
		    		<th>物品编码</th>
					<td><input type="text" id = "item_stdmode" name = "item_stdmode" value = "" required/></td>
					<th>货物名称</th>
					<td><input type="text" id = "item_name" name = "item_name" value = "" required /></td>
			</tr>
			<tr>
					<th>生产地址</th>
					<td><input type="text" id = "manufacturer_address" name = "manufacturer_address" value = ""/></td>
		    		
					<th>生产厂家</th>
					<td><input type="text" id = "item_manufacturer" name = "item_manufacturer" value = ""/></td>
			</tr>
			<tr>
		    		<th>数量</th>
					<td><input style="width:50px;" type="text" id = "inventory" name = "inventory" value = "" required/></td>
					<th>最低库存量</th>
					<td><input style="width:50px;" type="text" id = "quantity_storage" name = "quantity_storage" value = "" required/></td>
			</tr>
			<tr>	
					<th>单价</th>
					<td><input style="width:50px;"type="text" id = "price" name = "price" value = "" required/></td>
					<th>单位</th>
					<td><input style="width:50px;" type="text" id = "unit" name = "unit" value = "" required/></td>
			</tr>
			</table>
			<br>
				 <input type="hidden" id = "itemId" value = ""/>
	            <input type="button" class = "button" value="提交" onclick = "add_Submit()"/>
	            <input type = "button" onclick="on_exit1()" class = "button" value= "取消"></button>
	        </form>
    </div>
    	
	</center>
  </body>
</html>
