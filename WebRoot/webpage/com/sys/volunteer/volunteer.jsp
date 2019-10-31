<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>义工登记信息</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="volunteerController.do?save">
			<input id="id" name="id" type="hidden" value="${volunteerPage.id }">
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">义工姓名:</label></td>
			<td class="value">
				<input nullmsg="请填写义工姓名" errormsg="义工姓名格式不对" class="inputxt" id="name" name="name" ignore="ignore"
									   value="${volunteerPage.name}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">性别:</label></td>
			<td class="value">
				<t:dictSelect field="sex" typeGroupCode="sex" hasLabel="false" defaultVal="${volunteerPage.sex}"></t:dictSelect> 
				<span class="Validform_checktip"></span></td>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">出生日期:</label></td>
			<td class="value">
				<input nullmsg="请填写出生日期" errormsg="出生日期格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="birthday" name="birthday" ignore="ignore"
									     value="<fmt:formatDate value='${volunteerPage.birthday}' type="date" pattern="yyyy-MM-dd"/>">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">身份证:</label></td>
			<td class="value">
				<input nullmsg="请填写身份证" errormsg="身份证格式不对" class="inputxt" id="certification" name="certification" ignore="ignore"
									   value="${volunteerPage.certification}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">籍贯:</label></td>
			<td class="value">
				<input nullmsg="请填写籍贯" errormsg="籍贯格式不对" class="inputxt" id="nativeplace" name="nativeplace" ignore="ignore"
									   value="${volunteerPage.nativeplace}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">手机电话:</label></td>
			<td class="value">
				<input nullmsg="请填写手机电话" errormsg="手机电话格式不对" class="inputxt" id="phonenumber" name="phonenumber" ignore="ignore"
									   value="${volunteerPage.phonenumber}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">固话:</label></td>
			<td class="value">
				<input nullmsg="请填写固话" errormsg="固话格式不对" class="inputxt" id="homenumber" name="homenumber" ignore="ignore"
									   value="${volunteerPage.homenumber}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">专业技能:</label></td>
			<td class="value">
				<input nullmsg="请填写专业技能" errormsg="专业技能格式不对" class="inputxt" id="professionalskills" name="professionalskills" ignore="ignore"
									   value="${volunteerPage.professionalskills}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">最高学历:</label></td>
			<td class="value">
				<t:dictSelect field="higheducation" typeGroupCode="education" hasLabel="false" defaultVal="${volunteerPage.higheducation}"></t:dictSelect>
						<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">政治面貌:</label></td>
			<td class="value">
				<t:dictSelect field="politicalstatus" typeGroupCode="status" hasLabel="false" defaultVal="${volunteerPage.politicalstatus}"></t:dictSelect> 
				<span class="Validform_checktip"></span>
			</td>
			</tr>
			<%-- <tr>
			<td align="right"><label class="Validform_label">平均分:</label></td>
			<td class="value">
				<input nullmsg="请填写平均分" errormsg="平均分格式不对" class="inputxt" id="average" name="average" ignore="ignore"
									   value="${volunteerPage.average}" datatype="n">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">参加次数:</label></td>
			<td class="value">
				<input nullmsg="请填写平均分" errormsg="平均分格式不对" class="inputxt" id="average" name="average" ignore="ignore"
									   value="${volunteerPage.joincount}" datatype="n">
								<span class="Validform_checktip"></span>
			</td>
			</tr> --%>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="volunteerController.do?volunteereventList&id=${volunteerPage.id}" icon="icon-search" title="义工事务登记" id="volunteerevent"></t:tab>
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