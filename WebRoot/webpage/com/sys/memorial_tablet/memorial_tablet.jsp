<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>牌位表</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base>
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
 </script>
 </head>
 <body style="overflow-y: hidden" scroll="no">
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="memorial_tabletController.do?save">
			<input id="id" name="id" type="hidden" value="${memorial_tabletPage.id }">
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">编号:</label></td>
			<td class="value">
				<input nullmsg="请填写编号" errormsg="编号格式不对" class="inputxt" id="serial" name="serial" ignore="ignore"
									   value="${memorial_tabletPage.serial}"  readonly="readonly">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">堂口:</label></td>
			<td class="value">
				<input nullmsg="请填写堂口" errormsg="堂口格式不对" class="inputxt" id="buildingName" name="buildingName" ignore="ignore"
									   value="${memorial_tabletPage.buildingName}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">段位:</label></td>
			<td class="value">
				<input nullmsg="请填写段位" errormsg="段位格式不对" class="inputxt" id="dan" name="dan" ignore="ignore"
									   value="${memorial_tabletPage.dan}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">行号:</label></td>
			<td class="value">
				<input nullmsg="请填写行号" errormsg="行号格式不对" class="inputxt" id="row" name="row" ignore="ignore"
									   value="${memorial_tabletPage.row}" datatype="n">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">位号:</label></td>
			<td class="value">
				<input nullmsg="请填写位号" errormsg="位号格式不对" class="inputxt" id="ordernumber" name="ordernumber" ignore="ignore"
									   value="${memorial_tabletPage.ordernumber}" datatype="n">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">大小:</label></td>
			<td class="value">
				<input nullmsg="请填写大小" errormsg="大小格式不对" class="inputxt" id="size" name="size" ignore="ignore"
									   value="${memorial_tabletPage.size}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<th align="right"><label class="Validform_label">备注:</label></th>
			<td class="value">
				<input nullmsg="请填写备注" errormsg="备注格式不对" class="inputxt" id="remark" name="remark" ignore="ignore"
									   value="${memorial_tabletPage.remark}">
								<span class="Validform_checktip"></span>
			</td>
			<th align="right"><label class="Validform_label">安位时间:</label></th>
			<td class="value">
				<input nullmsg="请填写安位时间" errormsg="备注格式不对" class="inputxt" id="comforttime" name="comforttime" ignore="ignore"
									   value="${memorial_tabletPage.comforttime}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<th align="right"><label class="Validform_label">开始时间:</label></th>
			<td class="value">
				<input nullmsg="请填写开始时间" errormsg="备注格式不对" class="inputxt" id="begintime" name="begintime" ignore="ignore"
									   value="${memorial_tabletPage.begintime}">
								<span class="Validform_checktip"></span>
			</td>
			<th align="right"><label class="Validform_label">结束时间:</label></th>
			<td class="value">
				<input nullmsg="请填写结束时间" errormsg="备注格式不对" class="inputxt" id="endtime" name="endtime" ignore="ignore"
									   value="${memorial_tabletPage.endtime}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="memorial_tabletController.do?namelistList&id=${memorial_tabletPage.id}" icon="icon-search" title="姓名列表" id="namelist"></t:tab>
				 <t:tab href="memorial_tabletController.do?linkmanlistList&id=${memorial_tabletPage.id}" icon="icon-search" title="联系人列表" id="linkmanlist"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_namelist_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="namelistList[#index#].name" maxlength="20" type="text" style="width:220px;"></td>
				  <td align="left"><input name="namelistList[#index#].relation" maxlength="5" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="namelistList[#index#].state" typeGroupCode="mem_state" hasLabel="false" defaultVal="延生" type="radio">
						</t:dictSelect> <span class="Validform_checktip"></span></td>
					
			</tr>
		 </tbody>
		<tbody id="add_linkmanlist_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
			 	  <td align="left"><input name="linkmanlistList[#index#].linkman" maxlength="16" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="linkmanlistList[#index#].qq" maxlength="16" type="text" style="width:120px;"></td>
				  <td align="left"><input name="linkmanlistList[#index#].email" maxlength="36" type="text" style="width:120px;"></td>
				  <td align="left"><input name="linkmanlistList[#index#].mobilephone" maxlength="11" type="text" style="width:120px;"></td>
				  <td align="left"><input name="linkmanlistList[#index#].homephone" maxlength="14" type="text" style="width:120px;"></td>
				  <td align="left"><input name="linkmanlistList[#index#].wechat" maxlength="36" type="text" style="width:120px;"></td>
				  <td align="left"><input name="linkmanlistList[#index#].address" maxlength="36" type="text" style="width:120px;"></td>
				  <td align="left"><input name="linkmanlistList[#index#].relationship" maxlength="36" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		</table>
 </body>