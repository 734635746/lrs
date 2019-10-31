<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>药师诞信息</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table2.css">
  	<script>
  	 $(function(){
  	      $("#myButton").click(function(){
  	        $.ajax({
  	          url:"pharmacistbirthController.do?getSerial",
  	          type:"post",
  	          dataType:"text",
  	          data:{
  	        	shortcall:document.getElementById("short").value
  	          },
  	          success:function(responseText){
  	          	var kind = $('input:radio:checked').val();
	  	  	    var shortcall = document.getElementById("short").value;
  	            document.getElementById("serial").value = shortcall + kind + responseText;
  	          },
  	          error:function(){
  	            alert("system error");
  	          }
  	        });
  	      });
  	});
  		
  		function addtr(){
			var item = "<tr><td><input type=\"checkbox\" value=\"\" name = \"living\"/></td><td><input name =\"pname\"  type=\"text\" value=\"\"></td>" + 
			"<td><input type=\"text\" value=\"\"></td></tr>"; 
			$('.imagetable').append(item);
		}
  		function addtrAncestor(){
			var item = "<tr><td><input type=\"checkbox\" value=\"\" name = \"ancestor\"/></td><td><input name =\"pname\"  type=\"text\" value=\"\"></td>" + 
			"<td><input type=\"text\" value=\"\"></td></tr>";
			$('.imagetable1').append(item);
		}
  	
	  	
	  	//获取复选框选中的值
	  	function getLivingMemberListSelections(){	
	  		var livingMemberList = "";
	  		var livingString = "";
	  		$("#livingtable :checkbox").each(function(){
  				if(this.checked){
  					var input0 = $.trim($(this).parent().next().find("input").val());
  					var input1 = $.trim($(this).parent().next().next().find("input").val());
  					if(input0 == "" && input1 == ""){
  						livingMemberList += "";
  					}
  					else{
  						if(input0 == ""){
  							livingMemberList += input1 + ";";
  	  					}
  	  					else{
  	  					livingMemberList += input0 + ":" + input1 + ";";
  	  					}
  					}
  					if(input0 == "") input0 = "无";
  					if(input1 == "") input1 = "无";
  					livingString = livingString + input0 + "|" +  input1 + "|";
  				}
  			});
	  		document.getElementById("livingmenber").value = livingMemberList;
	  		document.getElementById("livingString").value = livingString;
		}
	  	function getAncestorListSelections(){	
	  		var ancestorList = "";
	  		var ancestorString = "";
	  		$("#ancestortable :checkbox").each(function(){
  				if(this.checked){
  					var input0 = $.trim($(this).parent().next().find("input").val());
  					var input1 = $.trim($(this).parent().next().next().find("input").val());
  					if(input0 == "" && input1 == ""){
  						ancestorList += "";
  					}
  					else{
  						if(input0 == ""){
  	  						ancestorList += input1 + ";";
  	  					}
  	  					else{
  	  						ancestorList += input0 + ":" + input1 + ";";
  	  					}
  					}
  					if(input0 == "") input0 = "无";
  					if(input1 == "") input1 = "无";
  					ancestorString = ancestorString + input0 + "|" +  input1 + "|";
  				}
  			});
	  		document.getElementById("ancestor").value = ancestorList;
	  		document.getElementById("ancestorString").value = ancestorString;
		}	
	  	
	  	function generateSummary(){
	  		var summary = document.getElementById("ancestor").value + document.getElementById("livingmenber").value;
	  		document.getElementById("summary").value = summary;
	  	}
	  	
	  	/* function selectAll(){  
	  	    if ($("#SelectAll").attr("checked")) {  
	  	        $("#ancestortable:checkbox").attr("checked", true);  
	  	    } else {  
	  	        $("#ancestortable:checkbox").attr("checked", false);  
	  	    }  
	  	} */  
	  	
	  	$(function(){
	  	     //全选
	  	     $("#SelectAncestorAll").click(function(){
	  	         $('[name=ancestor]:checkbox').attr("checked", this.checked );
	  	     });
	  	   	 $("#SelectLivingAll").click(function(){
	  	         $('[name=living]:checkbox').attr("checked", this.checked );
	  	     });
	  	});
	  	
	  	
  	</script>

 </head>
 <body style="overflow-y: scroll" scroll="yes">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" action="pharmacistbirthController.do?save">
			<input id="id" name="id" type="hidden" value="${pharmacistbirthPage.id }">
			<table style="width: 600px;" cellpadding="0" cellspacing="1" class="formtable">
				<%-- <tr>
					<td align="right">
						<label class="Validform_label">
							登记时间:
						</label>
					</td>
					<td class="value">
						<input class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="registertime" name="registertime" ignore="ignore"
							     value="<fmt:formatDate value='${pharmacistbirthPage.registertime}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>">
						<span class="Validform_checktip"></span>
					</td>
				</tr> --%>
				<tr>
					<td align="right">
						<label class="Validform_label">
							登记时间:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="registertime" name="registertime" ignore="ignore"
							   value="${pharmacistbirthPage.registertime}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							登记人:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="registrant" name="registrant" ignore="ignore"
							   value="${pharmacistbirthPage.registrant}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="serial" name="serial" ignore="ignore"
							   value="${pharmacistbirthPage.serial}">
						<input id = "myButton" type="button" value="获取">
						<span class="Validform_checktip"></span>
					</td>
					
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							大小:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="size" name="size" ignore="ignore"
							   value="${pharmacistbirthPage.size}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							祈福者:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="prayingobj" name="prayingobj" ignore="ignore"
							   value="${pharmacistbirthPage.prayingobj}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							在世:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="livingmenber" name="livingmenber" ignore="ignore"
							   value="${pharmacistbirthPage.livingmenber}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							金额:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="money" name="money" ignore="ignore"
							   value="${pharmacistbirthPage.money}" datatype="n">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收款方式:
						</label>
					</td>
					<td class="value">
						<t:dictSelect field="payway" typeGroupCode="payway" hasLabel="false" defaultVal="${pharmacistbirthPage.payway}" type="radio">
						</t:dictSelect> <span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							摘要:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="summary" name="summary" ignore="ignore"
							   value="${pharmacistbirthPage.summary}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							收据编号:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="receiptNo" name="receiptNo" ignore="ignore"
							   value="${pharmacistbirthPage.receiptNo}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
				<tr>
					<td align="right">
						<label class="Validform_label">
							地址:
						</label>
					</td>
					<td class="value">
						<input class="inputxt" id="address" name="address" ignore="ignore"
							   value="${pharmacistbirthPage.address}">
						<span class="Validform_checktip"></span>
					</td>
				</tr>
		</t:formvalid>
 </body>