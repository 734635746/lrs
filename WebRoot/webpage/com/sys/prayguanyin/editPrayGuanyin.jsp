<%@ page language="java" import="java.util.*"
	contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>编辑上供记录</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/table.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/button.css">
<script
	src="${pageContext.request.contextPath}/resources/js/calendar.js"></script>
<script type="text/javascript">
	var index = 0;
	var tr1;
	var tr_clone;
	function toPharmacistbirth() {
		var id = document.getElementById("id").value;
		window.location.href = "${pageContext.request.contextPath}/pharmacistbirthController.do?redirectToPharmacistbirth&id=" + id;
	}

	function LinktoPharmacistbirth(ritualid) {
		var id = document.getElementById("id").value;
		window.location.href = "${pageContext.request.contextPath}/pharmacistbirthController.do?redirectToPharmacistbirthByRitualid&id=" + id + "&ritualid=" + ritualid;
	}

	function comfirm() {
		var receiptids = "";
		$("[name='checkbox']:checked").each(function() {
			receiptids += $(this).val() + ",";
		});
		alert(receiptids);
	}
	function onShow(index) {
		document.getElementById("form").style.display = "block";
	/* document.getElementById("rowindex").value = index;
	alert("=========");
	alert(document.getElementById("rowindex").value); */
	}

	function onHide() {
		document.getElementById("form").style.display = "none";
		index = 0;
	/* document.getElementById("rowindex").value = ""; */
	}
	//获取复选框选中的值
	function getLivingMemberListSelections() {
		var livingMemberList = "";
		var livingString = "";
		$("#livingtable :checkbox").each(function() {
			if (this.checked) {
				var input0 = $.trim($(this).parent().next().find("input").val());
				var input1 = $.trim($(this).parent().next().next().find("input").val());
				if (input0 == "" && input1 == "") {
					livingMemberList += "";
				} else {
					if (input0 == "") {
						livingMemberList += input1 + ";";
					} else {
						livingMemberList += input0 + ":" + input1 + ";";
					}
				}
				if (input0 == "")
					input0 = "无";
				if (input1 == "")
					input1 = "无";
				livingString = livingString + input0 + "|" + input1 + "|";
			}
		});
		$(document.getElementById("receipttable").rows[index].cells[2]).find('input').val(livingMemberList);
		generateSummary(livingMemberList, index);
	}
	function generateSummary(livingMemberList, index) {
		$(document.getElementById("receipttable").rows[index].cells[7]).find('textarea').val(livingMemberList);
	}
	$(function() {
		tr1 = $("#receipttable tr").clone();
		tr_clone = tr1[1];
		//全选
		$("#SelectAncestorAll").click(function() {
			$('[name=ancestor]:checkbox').attr("checked", this.checked);
		});
		$("#SelectLivingAll").click(function() {
			$('[name=living]:checkbox').attr("checked", this.checked);
		});
	});
	function getSelected(current_Tr) {
		onShow();
		index = $.trim($(current_Tr).parent()[0].rowIndex);

		var liviingmemberString = $(document.getElementById("receipttable").rows[index].cells[2]).find('input').val();
		var oneLivingMember = liviingmemberString.substring(0, liviingmemberString.length - 1).split(";");
		for (var i = 0; i < oneLivingMember.length; i++) {
			if (oneLivingMember[i].indexOf(":") > 0) {
				var oneLivingMemberSplitBySymbol = oneLivingMember[i].split(":");
				document.getElementById("call" + (i + 1)).value = oneLivingMemberSplitBySymbol[0];
				document.getElementById("name" + (i + 1)).value = oneLivingMemberSplitBySymbol[1];
			} else {
				document.getElementById("call" + (i + 1)).value = "";
				document.getElementById("name" + (i + 1)).value = oneLivingMember[i];
			}
		}

	}
	function getLivingMemberList() {
		var rowcount = document.getElementById("livingtable").rows.length;
		var tr = document.getElementById("livingtable").getElementsByTagName("tr");
		for (var i = 1; i < rowcount; i++) {
			var obj = tr[i].getElementsByTagName("td")[1].children[0].value;
			if (parseInt(obj.length) > 8) {
				alert("提示 ： 祈福对象名字只能输入小于8个字符");
				return false;
			}
		}

		var updateLivingString = "";
		for (var i = 1; i <= 5; i++) {
			var call = document.getElementById("call" + i).value;
			var name = document.getElementById("name" + i).value;
			if (call != "" || name != "") {
				if (call != "") {
					updateLivingString += call + ":" + name + ";";
				} else {
					updateLivingString += name + ";";
				}
			}
		}
		$(document.getElementById("receipttable").rows[index].cells[2]).find('input').val(updateLivingString);
		generateSummary(updateLivingString, index);
		onHide();
	}
	function changeSize(size) {
		alert("注意： 切换成" + size + "牌登记");
		if (size == "小") {
			document.getElementById("add").style.display = "block";
			document.getElementById("delete").style.display = "block"
			document.getElementById("_book").style.display = "none";
		} else {
			document.getElementById("add").style.display = "none";
			document.getElementById("delete").style.display = "none"
			document.getElementById("_book").style.display = "block";
		}

	}

	function addTr() {
		var tr = $("#receipttable tr").clone();
		if (tr[1] == null) {
			$("#receipttable").append(tr_clone);
		} else {
			$("#receipttable").append(tr[1]);
		}
	}
	function deteleTr() {
		$("#receipttable").find("input:checked").parent().parent().remove();
	}

	function preview() {
		var tableObj = document.getElementById("preview_table");

		var arr = document.getElementById("preview_table").getElementsByTagName("tr");
		for (var i = arr.length - 1; i > 0; i--) {
			tableObj.deleteRow(i);
		}

		var rowcount = document.getElementById("receipttable").rows.length;
		var cowcount = document.getElementById("receipttable").rows[0].cells.length;
		var tr = document.getElementById("receipttable").getElementsByTagName("tr");
		for (var i = 1; i < rowcount; i++) {
			var pre_row = tableObj.insertRow();
			for (var j = 1; j < cowcount - 1; j++) {
				var pre_cow = pre_row.insertCell(j - 1);
				pre_cow.innerHTML = tr[i].getElementsByTagName("td")[j].children[0].value;

			}

		}
		document.getElementById("preview_form").style.display = "block";

	}
	function hide() {
		var tableObj = document.getElementById("preview_table");
		var arr = document.getElementById("preview_table").getElementsByTagName("tr");
		for (var i = arr.length - 1; i > 0; i--) {
			tableObj.deleteRow(i);
		}
		document.getElementById("preview_form").style.display = "none";
	}

	function myvalidate() {
		var rowcount = document.getElementById("receipttable").rows.length;
		var tr = document.getElementById("receipttable").getElementsByTagName("tr");

		for (var i = 1; i < rowcount; i++) {
			if (!(/(^[1-9]\d*$)/.test(tr[i].getElementsByTagName("td")[7].children[0].value))) {
				alert("请输入合法的金钱数目，第" + (i + 1) + "行的钱的数目不是正整数");
				return false;
			}
		}

		var r = window.confirm("确定填写信息没错吗？确定后不可以修改，可以预览信息");
		if (r == true) {
			return true;
		} else {
			return false;
		}

	}

	function changeSolarToLunar(currentNode) {
		//alert("yes");
		var solardate = currentNode.value;
		//console.log(solardate);
		var arr = new Array();
		arr = solardate.split("-");
		//alert(arr[0]);
		var lunar = calendar.solar2lunar(arr[0], arr[1], arr[2]);
		//alert('农历：'+lunar.lYear + '年' +lunar.IMonthCn+lunar.IDayCn);
		//console.log(currentNode);
		var lunardate = currentNode.parentNode.parentNode.children[4].children[0];
		//console.log(lunardate);
		lunardate.value = lunar.gzYear + "年" + lunar.IMonthCn + lunar.IDayCn;
	//$("#lunardate").val(lunar.lYear + '年' +lunar.IMonthCn+lunar.IDayCn);
	
		//选择日期后显示当天的随堂和包堂数
        $.ajax({
            url:"${pageContext.request.contextPath}/prayguanyinController.do?getCount",
            data:"solardate="+solardate,
            type:"POST",
            success:function (result) {
            	
            	var dataObj=eval("("+result+")"); 
            	//console.log(dataObj);
            	/* document.getElementById("stCount").innerHTML = dataObj.st;
            	document.getElementById("btCount").innerHTML = dataObj.bt; */
            	//console.log(currentNode.parentNode.parentNode.children[4].children[2]);
            	currentNode.parentNode.parentNode.children[4].children[2].innerHTML = dataObj.st;
            	currentNode.parentNode.parentNode.children[4].children[3].innerHTML = dataObj.bt;
            }
            
        });
	}

	var dlg;
	function getLivingMenbers(current) {
		//console.log(inputVar);
		//console.log(dlg);
		//删除上次弹窗生成的元素
		dlg && document.body.removeChild(dlg[0].parentNode) && document.body.removeChild(document.querySelector('.window-mask'));

		dlg = $.messager.show({
			id : "livings", //窗口ID
			title : '输入祈福对象',
			msg : '<div style="" margin-left: 0px margin-top: 0px align="center"><table id = "livingmenbertable" class="imagetable" width="500px"><tr><td><input style="width:100%;" type="text" name="livingmember1" id = "livingmember1" value="" placeholder="请输入最多18个字符"></td></tr><tr><td><input style="width:100%;" type="text" name="livingmember" id = "livingmember2" value="" placeholder="请输入最多18个字符"></td></tr><tr><td><input style="width:100%;" type="text" name="livingmember" id = "livingmember3" value="" placeholder="请输入最多18个字符"></td></tr><tr><td><input style="width:100%;" type="text" name="livingmember" id = "livingmember4" value="" placeholder="请输入最多18个字符"></td></tr><tr><td><input style="width:100%;" type="text" name="livingmember" id = "livingmember5" value="" placeholder="请输入最多18个字符"></td></tr><tr><td><input style="width:100%;" type="text" name="livingmember" id = "livingmember6" value="" placeholder="请输入最多18个字符"></td></tr><tr><td><input style="width:100%;" type="text" name="livingmember" id = "livingmember7" value="" placeholder="请输入最多18个字符"></td></tr><tr><td><input style="width:100%;" type="text" name="livingmember" id = "livingmember8" value="" placeholder="请输入最多18个字符"></td></tr></table><div><input id="confirm" type="button" class="button" value="确定""></div></div>',
			showType : 'fade',
			timeout : 0,
			closable : true,
			modal : true,
			draggable : true,
			inline : false,
			width : 600,
			height : 400,
			left : 200,
			top : 20,
			style : {
				left : 300,
				top : 100,
				bottom : 100
			},
			onBeforeClose : function() {
				//alert($("#mes1").window);
				/* var living = $("#livingmember1").val();
				console.log(living); */
				current.value = window.temp;
			}
		});
		
		var inputStr = current.value;
		if(inputStr != null && inputStr != ""){
			var inputArr = inputStr.substring(0,inputStr.length - 1).split(";");
			//console.log(inputArr);
			for(var i = 0; i < inputArr.length; i++){
				document.getElementById("livingmember" + (i + 1)).value = inputArr[i];
			}
		}
		
		
		//console.log(dlg);

		$(document).on('click', '#confirm', function() {
			//var livingMen = new Array();
			var livingMen = "";
			for (var i = 0; i < 8; i++) {
				//var nodeId = "#livingmember" + (i+1);

				//console.log($("#livingmember+"));
				//livingMen[i] = $(nodeId).val();
				//console.log(document.getElementById("livingmember" + (i + 1)));
				//console.log(document.getElementById("livingmember" + (i + 1)).value);
				var living = document.getElementById("livingmember" + (i + 1)).value;
				if (living.length > 18) {
					alert("提示 ： 祈福对象名字最多输入于18个字符");
					return false;
				}

				if (living != null && living != "")
					livingMen += living + ";";
			}
			window.temp=livingMen;
			//document.getElementById("livingmenber").value = livingMen;
			dlg.window('close');
			//console.log(input);
			//input.value = livingMen;


		});
	}
	
	function changSumary(currentNode){
		var sumary = "${clientele}交来农历";
		var rowcount = document.getElementById("receipttable").rows.length;
		var tr = document.getElementById("receipttable").getElementsByTagName("tr");
		var date = '';
		for (var i = 1; i < rowcount; i++) {
			date += tr[i].getElementsByTagName("td")[4].children[0].value + "、";
		}
		date = date.substring(0, date.length - 1);
		sumary += (date + "随如意斋共" + (rowcount - 1) + "堂功德款");
		tr[1].getElementsByTagName("textarea")[0].value = sumary;
 	}
 	
 	function changMoney(){
 		var type = document.getElementById("type").value;
 		var rowcount = document.getElementById("receipttable").rows.length;
		var tr = document.getElementById("receipttable").getElementsByTagName("tr");
		
		for (var i = 1; i < rowcount; i++) {
			if(type == "随堂"){
				tr[i].getElementsByTagName("td")[7].children[0].value = 600;
			}else if(type == "包堂"){
				tr[i].getElementsByTagName("td")[7].children[0].value = 6000;
			}
		}
 	}
	
</script>

<style type="text/css">
#form {
	width: 300px;
	height: 150px;
	margin: 0px auto;
	margin-bottom: 20px;
	border: 1px solid white;
	background-color: white;
	position: absolute; /*绝对对齐*/
	z-index: 1000;
	left: 240px;
}

#form h5 {
	margin: 1px;
	background-color: white;
	height: 24px;
}

#address{
	width: 300px;
}
</style>
</head>
<body style="overflow-y: scroll" scroll="yes">
	<h4>编辑上供记录</h4>
	<span> <!-- <input type="button" value="预览" class="button" onclick = "preview()"> -->
		<c:if test="${empty morningpforrEntity}">
			<!-- 编辑的时候不需要添加功能 -->
			<input type="button" id="add" value="添加记录" class="button"
				onclick="addTr()">
			<input type="button" id="delete" value="撤销记录" class="button"
				onclick="deteleTr()">
		</c:if> </span>
	<br>
	<br>
	<center>
		<div id="preview_form" style="display:none;">
			<table id="preview_table" class="imagetable">
				<tr>
					<th>祈福者</th>
					<th>祈福对象</th>
					<th>地址</th>
					<th>金额</th>
					<th>付款方式</th>
					<th>牌位大小</th>
				</tr>

			</table>
			<br> <input type="button" onclick="hide()" class="button"
				value="取消">
			</button>
		</div>
		<c:if test="${!empty morningpforrEntitys}">
			<form
				action="${pageContext.request.contextPath}/prayguanyinController.do?getSerialAndSaveTablet"
				onsubmit="return myvalidate()" method="post">
				<input id="id" name="id" type="hidden" value="${id}"> <input
					type="hidden" value="${morningpforrEntity.id}"
					name="morningpforrid" id="morningpforrid"> 付款人:<input
					style="width:100px;" type="text" id="paymen" name="paymen"
					value="${clientele}" required> 类型 ：<select id="type"
					name="type" onchange="changMoney();">
					<option value="随堂">随堂</option>
					<option value="包堂">包堂</option>
				</select> <br> <br>
				<%-- <span id = "_book" style="display:none">敬设:<input style="width:150px;" type="text" id = "book" name="book" value="${book}" ><br><br></span> --%>
				<table id="receipttable" class="imagetable">
					<tr>
						<!-- <th>选择</th>
				<th>祈福者</th>
				<th>祈福对象(单击输入框添加祈福对象)</th>
				<th>地址</th>
				<th>金额</th>
				<th>付款方式</th>
				<th>牌位大小</th>
				<th>摘要</th> -->
						<th>选择</th>
						<th>付款人</th>
						<!-- <th>阳上</th> -->
						<th>敬设</th>
						<th>上供日期</th>
						<th>农历</th>
						<th>祈福对象</th>
						<th>地址</th>
						<th>金额</th>
						<th>付款方式</th>
						<th>摘要</th>
					</tr>
					<c:forEach items="${morningpforrEntitys}" var="morningpforrEntity"
						varStatus="stauts">
						<tr>
							<td><input style="width:20px;" type="checkbox" />
							</td>
							<!-- 付款人 -->
							<td><input style="width:60px;" type="text" id="prayingobj"
								name="prayingobj" value="${morningpforrEntity.prayingobj}">
							</td>
							<!-- 敬设 -->
							<td><input style="width:80px;" type="text" id="book"
								name="book" value="《供如意斋一堂》">
							</td>
							<!-- 公历 -->
							<td><input type="date" id="solardate" name="solardate"
								value="${morningpforrEntity.solardate}"
								onchange="changeSolarToLunar(this)">
							</td>
							<!-- 农历 -->
							<td><input type="text" id="lunardate" name="lunardate"
								value="${morningpforrEntity.lunardate}" placeholder="选择公历自动生成农历"><br>
								随堂：<label id="stCount">${counts[stauts.index].st}</label>&nbsp;&nbsp;&nbsp;&nbsp;包堂：<label
								id="btCount">${counts[stauts.index].bt}</label></td>
							<!-- 祈福对象 -->
							<td><input onclick="getLivingMenbers(this);" type="text"
								name="livingmenber" id="livingmenber"
								value="${morningpforrEntity.livingmenber}">
							</td>
							<!-- 地址 -->
							<td><input type="text" id="address" name="address"
								value="${morningpforrEntity.address}">
							</td>
							<%-- <td><input type="text" id="address" name="address"
								value="${updateFlag!=1?doritualinfoAddress:morningpforrEntity.address}">
							</td> --%>
							<!-- 金额 -->
							<td><input style="width:40px;" type="text" id="money"
								name="money" value="${morningpforrEntity.money}"  required="required"><!-- readonly="readonly" -->
							</td>
							<!-- 付款方式 -->
							<td><select style="width:60px;" id="payway" name="payway">
									<option value="现金">现金</option>
									<option value="刷卡">刷卡</option>
									<option value="支付宝">支付宝</option>
									<option value="微信">微信</option>
									<option value="其他">其他</option>
							</select>
							</td>
							<%-- <td>
					<select style="width:50px;" id="size" name="size" onchange="changeSize(this.value);">
						<option value="小" <c:if test="${guanyinopenEntitys.size eq '小'}">selected="selected"</c:if>>小</option>
					</select>
					</td> --%>
							<!-- 摘要 -->
							<td><textarea rows="3" cols="40" id="summary" name="summary"
									onclick="changSumary();">${morningpforrEntity.summary}</textarea>
							</td>
							<%-- 	<td><input type="button" value="点击" onclick="LinktoPharmacistbirth('${receipt.ritualid}')"></td> --%>

						</tr>
					</c:forEach>

				</table>
				<br> <br> <input type="submit" value="确定" class="button">
			</form>
		</c:if>

	</center>

	<center>
		<c:if test="${empty morningpforrEntitys}">
			<form
				action="${pageContext.request.contextPath}/prayguanyinController.do?getSerialAndSaveTablet"
				onsubmit="return myvalidate()" method="post">
				<input id="id" name="id" type="hidden" value="${id}"> 付款人:<input
					style="width:100px;" type="text" id="paymen" name="paymen"
					value="${clientele}" required> 类型 ：<select id="type"
					name="type" onchange="changMoney();">
					<option value="随堂">随堂</option>
					<option value="包堂">包堂</option>
				</select> <br> <br>
				<!-- <span id = "_book" style="display:none">敬设:<input style="width:150px;" type="text" id = "book" name="book" value=""><br><br></span> -->
				<table id="receipttable" class="imagetable">
					<tr>
						<!-- <th>选择</th>
					<th>祈福者</th>
					<th>祈福对象(单击输入框添加祈福对象)</th>
					<th>地址</th>
					<th>金额</th>
					<th>付款方式</th>
					<th>牌位大小</th>
					<th>摘要</th> -->
						<th>选择</th>
						<th>付款人</th>
						<!-- <th>阳上</th> -->
						<th>敬设</th>
						<th>上供日期</th>
						<th>农历</th>
						<th>祈福对象</th>
						<th>地址</th>
						<th>金额</th>
						<th>付款方式</th>
						<th>摘要</th>
					</tr>
					<tr>
						<td><input style="width:20px;" type="checkbox" />
						</td>
						<td><input style="width:60px;" type="text" id="prayingobj"
							name="prayingobj" value="${clientele}">
						</td>
						<%-- <td><input style="width:100px;" type="text" id = "prayingobj" name="prayingobj" value="${clientele}"></td> --%>
						<td><input style="width:100px;" type="text" id="book"
							name="book" value="《供如意斋一堂》" required> <!-- 公历 -->
						<td><input type="date" id="solardate" name="solardate"
							value="" onchange="changeSolarToLunar(this)">
						</td>
						<!-- 农历 -->
						<td><input type="text" id="lunardate" name="lunardate"
							value="" placeholder="选择公历自动生成农历"><br> 随堂：<label
							id="stCount"></label>&nbsp;&nbsp;&nbsp;&nbsp;包堂：<label
							id="btCount"></label></td>
						<td><input onclick="getLivingMenbers(this);" type="text"
							name="livingmenber" class="livingmenber">
						</td>
						<td><input  type="text" id="address" name="address" value="${doritualinfoAddress}">
						</td>
						<td><input style="width:40px;" type="text" id="money"
							name="money" value="600" required ><!-- readonly="readonly" -->
						</td>
						<td><select style="width:60px;" id="payway" name="payway">
								<option value="现金">现金</option>
								<option value="刷卡">刷卡</option>
								<option value="支付宝">支付宝</option>
								<option value="微信">微信</option>
								<option value="其他">其他</option>
						</select>
						</td>
						<!-- <td>
						<select style="width:50px;" id="size" name="size" onchange="changeSize(this.value);">
							<option value="小">小</option>
						</select>
						</td> -->

						<td><textarea rows="3" cols="40" id="summary" name="summary"
								onclick="changSumary();"></textarea>
						</td>
						<%-- 	<td><input type="button" value="点击" onclick="LinktoPharmacistbirth('${receipt.ritualid}')"></td> --%>

					</tr>

				</table>
				<br> <br> <input type="submit" value="确定" class="button">
			</form>
		</c:if>

		<div id="form" style="display:none;">
			<h5>祈福对象</h5>
			<table id="livingtable" class="imagetable">
				<tr>
					<th>称呼</th>
					<th>名字</th>
				</tr>
				<tr>
					<td><input type="text" id="call1" value="">
					</td>
					<td><input style="width:250px;" type="text" id="name1"
						value="" placeholder="请输入最多8个字符">
					</td>
				</tr>
				<tr>
					<td><input type="text" id="call2" value="">
					</td>
					<td><input style="width:250px;" type="text" id="name2"
						value="" placeholder="请输入最多8个字符">
					</td>
				</tr>
				<tr>
					<td><input type="text" id="call3" value="">
					</td>
					<td><input style="width:250px;" type="text" id="name3"
						value="" placeholder="请输入最多8个字符">
					</td>
				</tr>

				<tr>
					<td><input type="text" id="call4" value="">
					</td>
					<td><input style="width:250px;" type="text" id="name4"
						value="" placeholder="请输入最多8个字符">
					</td>
				</tr>
				<tr>
					<td><input type="text" id="call5" value="">
					</td>
					<td><input style="width:250px;" type="text" id="name5"
						value="" placeholder="请输入最多8个字符">
					</td>
				</tr>
			</table>
			<br> <input type="button" onclick="getLivingMemberList()"
				class="button" value="确定">
			</button>
			<input type="button" onclick="onHide()" class="button" value="取消">
			</button>
		</div>
	</center>
	<!-- <div id="dd" class="easyui-dialog" title="My Dialog" style="width:400px;height:200px;"
    		data-options="iconCls:'icon-save',resizable:true,modal:true">
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
		</div> -->


</body>