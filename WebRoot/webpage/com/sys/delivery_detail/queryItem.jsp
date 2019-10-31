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
			var url = "delivery_detailController.do?queryItem&itemName=" + encodeURIComponent(encodeURIComponent(itemName));
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
						inner += "<td><input type = \"button\" class = \"button\" value = \"出货\"  onclick = \"addItem('" + json[i].id + "'," + "'" + json[i].current_inventory + "'," +  "'" + json[i].item_name + "')\"></td>";
						inner += "</tr>";
					}
					
					inner += "</table><br>";
					/* inner += "<input type=\"button\" class=\"button\" value=\"确定交接\" onclick=\"comfirmStat()\">"; */
					document.getElementById("show").innerHTML = inner;
				}
			});
		}
		function addItem(id,current_inventory,name){
			myclick(id,name,current_inventory);
		}
		
		function myclick(id,name,current_inventory){

			document.getElementById("form").style.display='block';
			document.getElementById("name").value = name;
			document.getElementById("number").value = current_inventory;
			document.getElementById("itemId").value = id;
			document.getElementById("tmp_number").value = current_inventory;
			
		}
		function on_submit(){
			
			var name = document.getElementById("name").value;
			var number = document.getElementById("number").value;
			var membername = document.getElementById("membername").value;
			var tmp_number = document.getElementById("tmp_number").value;
			if(parseInt(number) > parseInt(tmp_number)){
				alert("库存不足，现在库存量为　" + tmp_number);
				document.getElementById("number").value = tmp_number;
				return;
			}
			if(number == ""){
				alert("请填写出货数量！");
				return ;
			}
			var itemId = document.getElementById("itemId").value;
			var r=window.confirm("确定出货 " + name + " 货物 " + number + " 个吗？");
	 		if (r==true)
	 		{
				var url = "delivery_detailController.do?deliveryItem&itemId=" + itemId + "&number=" + number + "&membername=" + encodeURIComponent(encodeURIComponent(membername));
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
   	<h4>出库登记</h4>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
   	填写物品编码（或者物品名称）：<input type = "text" id = "itemName" name = "itemName" value = "">
   	&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<input type = "button" name = "mysubmit" class = "button" value = "查找" onclick = "getItems()"/>
   	
	<center>
	
	<div id="form" style="display:none;"><h5>出货</h5>
			<input type="hidden" id = "tmp_number" name="" value = ""/>
	    	<form name="form1">
	    	<table class = "imagetable">
	    	<tr>
		    		<th>货物名称</th>
					<td><input type="text" id = "name" readonly/></td>
			</tr>
			<tr>
					<th>取货人</th>
					<td><input type="text" id = "membername" name="" required/></td>
			</tr>
			<tr>
					<th>数量</th>
					<td><input type="text" id = "number" name="" required/></td>
			</tr>
			</table>
			<br>
				 <input type="hidden" id = "itemId" value = ""/>
	            <input type="button" class = "button" value="提交" onclick = "on_submit()"/>
	            <input type = "button" onclick="on_exit()" class = "button" value= "取消"></button>
	        </form>
    </div>
    <br>
    </center>
    <center>
	<div id="show" style="overflow-y:hidden;position: relative;z-index:0"></div>
	</center>
	
	
    
    	
	</center>
  </body>
</html>
