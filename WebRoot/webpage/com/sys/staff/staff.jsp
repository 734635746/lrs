<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
 <head>
  <title>员工信息</title>
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
  <t:formvalid formid="formobj" dialog="true" usePlugin="password" layout="table" tiptype="1" action="staffController.do?save">
			<input id="id" name="id" type="hidden" value="${staffPage.id }">
			<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
			<td align="right"><label class="Validform_label">员工姓名:</label></td>
			<td class="value">
				<input nullmsg="请填写员工姓名" errormsg="员工姓名格式不对" class="inputxt" id="staffname" name="staffname" 
									   value="${staffPage.staffname}" datatype="*">
								<span class="Validform_checktip">用户名范围在2~10位字符</span>
			</td>
			<td align="right"><label class="Validform_label">员工性别: </label></td>
			<td class="value"><t:dictSelect field="staffsex" typeGroupCode="sex" hasLabel="false" defaultVal="${staffPage.staffsex}"></t:dictSelect> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">出生年月:</label></td>
			<td class="value">
				<input nullmsg="请填写员工出生年月" errormsg="员工出生年月格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="birthday" name="birthday" ignore="ignore"
									     value="<fmt:formatDate value='${staffPage.birthday}' type="date" pattern="yyyy-MM-dd"/>" datatype="*">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">员工籍贯:</label></td>
			<td class="value">
				<input nullmsg="请填写籍贯" errormsg="籍贯格式不对" class="inputxt" id="nativeplace" name="nativeplace"
									   value="${staffPage.nativeplace}" datatype="*">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">身份证号:</label></td>
			<td class="value">
				<input nullmsg="请填写身份证号" errormsg="身份证号格式不对" class="inputxt" id="identification" name="identification" ignore="ignore"
									   value="${staffPage.identification}" datatype="*">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">政治面貌:</label></td>
			<td class="value"><t:dictSelect field="status" typeGroupCode="status" hasLabel="false" defaultVal="${staffPage.status}"></t:dictSelect> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">是否结婚:</label></td>
			<td class="value"><t:dictSelect field="marrystatus" typeGroupCode="married" hasLabel="false" defaultVal="${staffPage.marrystatus}"></t:dictSelect> <span class="Validform_checktip"></span></td>
			<td align="right"><label class="Validform_label">电话号码:</label></td>
			<td class="value">
				<input nullmsg="请填写电话号码" errormsg="电话号码格式不对" class="inputxt" id="phonenumber" name="phonenumber" ignore="ignore"
									   value="${staffPage.phonenumber}"  datatype="m">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">户口地址:</label></td>
			<td class="value">
				<input nullmsg="请填写户口地址" errormsg="户口地址格式不对" class="inputxt" id="address" name="address" ignore="ignore"
									   value="${staffPage.address}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">受过训练:</label></td>
			<td class="value">
				<input nullmsg="请填写受过何训练" errormsg="受过何训练填写格式不对" class="inputxt" id="training" name="training" ignore="ignore"
									   value="${staffPage.training}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">语言能力:</label></td>
			<td class="value">
				<input nullmsg="请填写语言能力" errormsg="语言能力格式不对" class="inputxt" id="languageability" name="languageability" ignore="ignore"
									   value="${staffPage.languageability}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">电脑操作:</label></td>
			<td class="value"><t:dictSelect field="computeropt" typeGroupCode="computer" hasLabel="false" defaultVal="${staffPage.computeropt}"></t:dictSelect> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">入职日期:</label></td>
			<td class="value">
				<input nullmsg="请填写员工入职日期" errormsg="员工入职日期格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd'})"  style="width: 150px" id="entrydate" name="entrydate" ignore="ignore"
									     value="<fmt:formatDate value='${staffPage.entrydate}' type="date" pattern="yyyy-MM-dd"/>">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">试用薪金:</label></td>
			<td class="value">
				<input nullmsg="请填写试用期薪金" errormsg="试用期薪金格式不对" class="inputxt" id="probationsalary" name="probationsalary" ignore="ignore"
									   value="${staffPage.probationsalary}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">入职部门:</label></td>
			<td class="value">
				<input nullmsg="请填写入职部门" errormsg="入职部门格式不对" class="inputxt" id="entrydepartment" name="entrydepartment" ignore="ignore"
									   value="${staffPage.entrydepartment}">
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">试用时间:</label></td>
			<td class="value"><t:dictSelect field="probationtime" typeGroupCode="probation" hasLabel="false" defaultVal="${staffPage.probationtime}"></t:dictSelect> <span class="Validform_checktip"></span></td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">员工角色: </label></td>
			<td class="value" nowrap><input name="roleid" name="roleid" type="hidden" value="${id}" id="roleid"> <input name="roleName" class="inputxt" value="${roleName}" id="roleName"
				readonly="readonly" datatype="*" /> <t:choose hiddenName="roleid" hiddenid="id" url="userController.do?roles" name="roleList" icon="icon-search" title="角色列表" textname="roleName" isclear="true"></t:choose>
			<span class="Validform_checktip">角色可多选</span></td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label"> 所选部门: </label></td>
			<td class="value"><select id="departid" name="departid" datatype="*">
				<c:forEach items="${departList}" var="depart">
					<option value="${depart.id }" <c:if test="${depart.id==staffPage.departid}">selected="selected"</c:if>>${depart.departname}</option>
				</c:forEach>
			</select> <span class="Validform_checktip">请选择部门</span></td>
			<td align="right"><label class="Validform_label"> 所选职位: </label></td>
			<td>	
				<input nullmsg="请填写职位" class="inputxt" id="position" name="position" ignore="ignore"
									   value="${staffPage.position}">
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<%-- 增加一个div，用于调节页面大小，否则默认太小 --%>
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="staffController.do?workexperienceList&id=${staffPage.id}" icon="icon-search" title="工作经历" id="workexperience"></t:tab>
				 <t:tab href="staffController.do?educationList&id=${staffPage.id}" icon="icon-search" title="学历" id="education"></t:tab>
				 <t:tab href="staffController.do?familymemberList&id=${staffPage.id}" icon="icon-search" title="家庭成员" id="familymember"></t:tab>
				</t:tabs>
			</div>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tbody id="add_workexperience_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="workexperienceList[#index#].workunit" maxlength="30" type="text" style="width:120px;"></td>
				  <td align="left"><input name="workexperienceList[#index#].position" maxlength="30" type="text" style="width:120px;"></td>
				  <td align="left"><input type="date" pattern="yyyy-MM-dd" name="workexperienceList[#index#].arrivaldate" maxlength="" style="width:120px;"></td>
				  <td align="left"><input type="date" pattern="yyyy-MM-dd" name="workexperienceList[#index#].departuredate" maxlength="" style="width:120px;"></td>
				  <td align="left"><input name="workexperienceList[#index#].departurereason" maxlength="255" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		<tbody id="add_education_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input type="date" pattern="yyyy-MM-dd" name="educationList[#index#].starttime" maxlength="" style="width:120px;"></td>
				  <td align="left"><input type="date" pattern="yyyy-MM-dd" name="educationList[#index#].endtime" maxlength="" style="width:120px;"></td>
				  <td align="left"><input name="educationList[#index#].major" maxlength="20" type="text" style="width:120px;"></td>
				  <td align="left"><input name="educationList[#index#].degree" maxlength="10" type="text" style="width:120px;"></td>
				  <td align="left"><input name="educationList[#index#].graduation" maxlength="3" type="text" style="width:120px;"></td>
				  <td align="left"><input name="educationList[#index#].certificate" maxlength="20" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
		<tbody id="add_familymember_table_template">
			<tr>
			 <td align="center"><div style="width: 25px;" name="xh"></div></td>
			 <td align="center"><input style="width:20px;" type="checkbox" name="ck"/></td>
				  <td align="left"><input name="familymemberList[#index#].relationship" maxlength="10" type="text" style="width:120px;"></td>
				  <td align="left"><input name="familymemberList[#index#].membername" maxlength="10" type="text" style="width:120px;"></td>
				  <td align="left"><input name="familymemberList[#index#].age" maxlength="" type="text" style="width:120px;"></td>
				  <td align="left"><input name="familymemberList[#index#].occupation" maxlength="11" type="text" style="width:120px;"></td>
				  <td align="left"><input name="familymemberList[#index#].addressnow" maxlength="20" type="text" style="width:120px;"></td>
			</tr>
		 </tbody>
 </body>