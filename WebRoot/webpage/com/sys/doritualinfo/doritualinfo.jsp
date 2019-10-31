<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>委托人的基本信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button1.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button2.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <script type="text/javascript">
  //初始化下标
	function resetTrNum(tableId) {
		$tbody = $("#"+tableId+"");
		$tbody.find('>tr').each(function(i){
			$(':input, select', this).each(function(){
				var $this = $(this), name = $this.attr('name'), val = $this.val();
				if(name!=null){
					if (name.indexOf("#index#") >= 0){
						$this.attr("name",name.replace('#index#',i));
					}else{
						var s = name.indexOf("[");
						var e = name.indexOf("]");
						var new_name = name.substring(s+1,e);
						$this.attr("name",name.replace(new_name,i));
					}
				}
			});
			$(this).find('div[name=\'xh\']').html(i+1);
		});
	}
	
	function getInfo(){
  		//获取信息
  		var id = document.getElementById("id").value;
  		var rname = document.getElementById("rname").value;
  		var phonenumber1 = document.getElementById("phonenumber1").value;
  		var phonenumber2 = document.getElementById("phonenumber2").value;
  		var address = document.getElementById("address").value;
  		if(rname == null || rname == ''){
  			return '';
  		}
  		return "&id=" + id + "&rname=" + rname + "&phonenumber1=" + phonenumber1 + "&phonenumber2=" + phonenumber2 + "&address=" + address;
	}
  
  	function toPharmacistbirth(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
	  		window.location.href = "${pageContext.request.contextPath}/pharmacistbirthController.do?redirectToSelectSize" + param;
  		}
  	}
  	
  	function toBuddhabirth(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
	  		window.location.href = "${pageContext.request.contextPath}/buddhabirthController.do?redirectToSelectSize" + param;
  		}
  	}
  	function toGuanyinopen(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
  			window.location.href = "${pageContext.request.contextPath}/guanyinopenController.do?redirectToSelectSize" + param;
  		}
  	}
  	function toGoddessbirth(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
	  		window.location.href = "${pageContext.request.contextPath}/goddessbirthController.do?redirectToSelectSize" +param;
  		}
  	}
  	function toHonoredbirth(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
	  		window.location.href = "${pageContext.request.contextPath}/honoredbirthController.do?redirectToSelectSize" + param;
  		}
  	}
  	function toGuanyingaya(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
  			window.location.href = "${pageContext.request.contextPath}/guanyingayaController.do?redirectToSelectSize" + param;
  		}
  	}
  	function toGuanyinmonk(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
	  		window.location.href = "${pageContext.request.contextPath}/guanyinmonkController.do?redirectToSelectSize" + param;
  		}
  	}
  	
  	function toBuddhagaya(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
	  		window.location.href = "${pageContext.request.contextPath}/buddhagayaController.do?redirectToSelectSize" + param;
  		}
  	}
  	
  	function toTombsweepfes(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
	  		window.location.href = "${pageContext.request.contextPath}/tombsweepfesController.do?redirectToSelectSize" + param;
  		}
  	}
  	function toGhostfes(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
	  		window.location.href = "${pageContext.request.contextPath}/ghostfesController.do?redirectToSelectSize" + param;
  		}
  	}
  	function toJizobirth(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
	  		window.location.href = "${pageContext.request.contextPath}/jIzobirthController.do?redirectToSelectSize" + param;
  		}
  	}
  	function toAmitabhabirth(){
  		var param = getInfo();
  		if(param == undefined || param == ''){
  			alert("请输入姓名");
  		}else{
	  		window.location.href = "${pageContext.request.contextPath}/amitabhabirthController.do?redirectToSelectSize" + param;  /* 8月7号 */
  		}
  	}
 </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="doritualinfoController.do?save">
			<input id="id" name="id" type="hidden" value="${doritualinfoPage.id }">
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">姓名:</label></td>
			<td class="value">
				<input nullmsg="请填写姓名" errormsg="姓名格式不对" class="inputxt" id="rname" name="rname"
									   value="${doritualinfoPage.rname}" datatype="s">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">手机号码1:</label></td>
			<td class="value">
				<input nullmsg="请填写手机号码1" errormsg="手机号码1格式不对" class="inputxt" id="phonenumber1" name="phonenumber1" ignore="ignore"
									   value="${doritualinfoPage.phonenumber1}" datatype="m">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">手机号码2:</label></td>
			<td class="value">
				<input nullmsg="请填写手机号码2" errormsg="手机号码2格式不对" class="inputxt" id="phonenumber2" name="phonenumber2" ignore="ignore"
									   value="${doritualinfoPage.phonenumber2}"  datatype="m">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">居住地址:</label></td>
			<td class="value">
				<input style="width:250px;" nullmsg="请填写居住地址" errormsg="居住地址格式不对" class="inputxt" id="address" name="address" ignore="ignore"
									   value="${doritualinfoPage.address}" datatype="*" errormsg="该字段不为空">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			</table>
			<c:if test="${searchflag=='1'}">
			<center>
			<br><br><br><br><br>
			<table class = "imagetable" border="1">
				<tr>
				<td><input type="button" class="button2" value="弥勒佛登记" onclick="toBuddhabirth()">
				<br><span><center><!-- (正月初一) --></center></span></td>
				<td><input type="button" class="button2" value="观音开库" onclick="toGuanyinopen()">
				<br><span><center><!-- (正月廿六) --></center></span></td>
				<td><input type="button" class="button2" value="观音诞登记" onclick="toGoddessbirth()">
				<br><span><center><!-- (二月十九至廿七) --></center></span></td>
				<td><input type="button" class="button2" value="释尊诞登记" onclick="toHonoredbirth()">
				<br><span><center><!-- (四月初八) --></center></span></td>
				</tr>
				<tr>
				<td><input type="button" class="button2" value="观音成道登记" onclick="toGuanyingaya()">
				<br><span><center><!-- (六月十九) --></center></span></td>
				<td><input type="button" class="button2" value="观音出家登记" onclick="toGuanyinmonk()">
				<br><span><center><!-- (九月十九) --></center></span></td>
				<td><input type="button" class="button2" value="药师佛诞" onclick="toPharmacistbirth()">
				<br><span><center><!-- (九月三十) --></center></span></td>
				<td><input type="button" class="button2" value="释尊成道登记" onclick="toBuddhagaya()">
				<br><span><center><!-- (十二月初八) --></center></span></td>
				</tr>
			</table>
			<br><br><br>
			<table class = "imagetable" border="1">
				<tr>
				<td><input type="button" class="button1" value="清明节登记" onclick="toTombsweepfes()">
				<br><span><center><!-- (二月十九至廿七) --></center></span></td>
				<td><input type="button" class="button1" value="盂兰节登记" onclick="toGhostfes()">
				<br><span><center><!-- (七月十五) --></center></span></td>
				<td><input type="button" class="button1" value="地藏诞登记" onclick="toJizobirth()">
				<br><span><center><!-- (七月廿九) --></center></span></td>
				<td><input type="button" class="button1" value="弥陀诞登记" onclick="toAmitabhabirth()">
				<br><span><center><!-- (十一月十七) --></center></span></td>
				</tr>
			</table>
			</center>
			</c:if>
			<c:if test="${searchflag!='1'}">
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="doritualinfoController.do?livingmenberList&id=${doritualinfoPage.id}" icon="icon-search" title="延生" id="livingmenber"></t:tab>
				 <t:tab href="doritualinfoController.do?ancestorList&id=${doritualinfoPage.id}" icon="icon-search" title="往生" id="ancestor"></t:tab>
				</t:tabs>
			</div>
			</c:if>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_livingmenber_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="livingmenberList[#index#].called" maxlength="10" type="text" style="width:120px;"></td>
				  <td align="left"><input name="livingmenberList[#index#].name" maxlength="20" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		<tbody id="add_ancestor_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="ancestorList[#index#].called" maxlength="10" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="ancestorList[#index#].name" maxlength="20" type="text" style="width:120px;" ></td>
			</tr>
		 </tbody>
		</table>
		
 </body>