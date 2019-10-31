<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>事件信息登记</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
  <script type="text/javascript">
  	window.onload = function(){
  		var isconfirm = document.getElementById("isconfirm");
  		if(isconfirm.value == ""){
  			isconfirm.value="否";
  		}
  		var isestimate = document.getElementById("isestimate");
  		if(isestimate.value == ""){
  			isestimate.value="否";
  		}
	}
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
  	
 </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="eventController.do?save">
			<input id="id" name="id" type="hidden" value="${eventPage.id }">
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">事件名字:</label></td>
			<td class="value">
				<input nullmsg="请填写事件名字" errormsg="事件名字格式不对" class="inputxt" id="eventname" name="eventname" ignore="ignore"
									   value="${eventPage.eventname}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">创建时间:</label></td>
			<td class="value">
				<input nullmsg="请填写创建时间" errormsg="创建时间格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="createtime" name="createtime" ignore="ignore"
									     value="<fmt:formatDate value='${eventPage.createtime}' type="date" pattern="yyyy-MM-dd"/>">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">是否已经确认:</label></td>
			<td class="value">
				<input nullmsg="是否确认" errormsg="格式不对" class="inputxt" id="isconfirm" name="isconfirm" ignore="ignore"
									   value="${eventPage.isconfirm}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">是否已经评价:</label></td>
			<td class="value">
				<input nullmsg="是否评价" errormsg="事件名字格式不对" class="inputxt" id="isestimate" name="isestimate" ignore="ignore"
									   value="${eventPage.isestimate}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="eventController.do?volunteereventList&id=${eventPage.id}" icon="icon-search" title="义工事务登记" id="volunteerevent"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_volunteerevent_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="volunteereventList[#index#].eventname" maxlength="36" type="text" style="width:120px;"></td>
				  <td align="left"><input name="volunteereventList[#index#].volunteername" maxlength="36" type="text" style="width:120px;"></td>
				  <td align="left"><input name="volunteereventList[#index#].workcondition" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="volunteereventList[#index#].atitude" maxlength="" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		</table>
 </body>