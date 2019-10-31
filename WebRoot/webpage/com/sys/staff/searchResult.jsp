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
				<input nullmsg="请填写员工姓名" errormsg="员工姓名格式不对" class="inputxt" id="staffname" name="staffname" ignore="ignore"
									   value="${staffPage.staffname}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">员工性别:</label></td>
			<td class="value">
				<input nullmsg="请填写员工性别" errormsg="员工性别格式不对" class="inputxt" id="staffsex" name="staffsex" ignore="ignore"
									   value="${staffPage.staffsex}" >
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">员工出生年月:</label></td>
			<td class="value">
				<input nullmsg="请填写员工出生年月" errormsg="员工出生年月格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="birthday" name="birthday" ignore="ignore"
									     value="<fmt:formatDate value='${staffPage.birthday}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" >
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">籍贯:</label></td>
			<td class="value">
				<input nullmsg="请填写籍贯" errormsg="籍贯格式不对" class="inputxt" id="nativeplace" name="nativeplace" ignore="ignore"
									   value="${staffPage.nativeplace}" >
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">身份证号:</label></td>
			<td class="value">
				<input nullmsg="请填写身份证号" errormsg="身份证号格式不对" class="inputxt" id="identification" name="identification" ignore="ignore"
									   value="${staffPage.identification}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">政治面貌:</label></td>
			<td class="value">
				<input nullmsg="请填写政治面貌" errormsg="政治面貌格式不对" class="inputxt" id="status" name="status" ignore="ignore"
									   value="${staffPage.status}" >
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">婚否:</label></td>
			<td class="value">
				<input nullmsg="请填写婚否" errormsg="婚否格式不对" class="inputxt" id="marrystatus" name="marrystatus" ignore="ignore"
									   value="${staffPage.marrystatus}" >
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">电话号码:</label></td>
			<td class="value">
				<input nullmsg="请填写电话号码" errormsg="电话号码格式不对" class="inputxt" id="phonenumber" name="phonenumber" ignore="ignore"
									   value="${staffPage.phonenumber}" >
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">户口地址:</label></td>
			<td class="value">
				<input nullmsg="请填写户口地址" errormsg="户口地址格式不对" class="inputxt" id="address" name="address" ignore="ignore"
									   value="${staffPage.address}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">受过何训练:</label></td>
			<td class="value">
				<input nullmsg="请填写受过何训练" errormsg="受过何训练填写格式不对" class="inputxt" id="training" name="training" ignore="ignore"
									   value="${staffPage.training}" >
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">语言能力:</label></td>
			<td class="value">
				<input nullmsg="请填写语言能力" errormsg="语言能力格式不对" class="inputxt" id="languageability" name="languageability" ignore="ignore"
									   value="${staffPage.languageability}" >
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">电脑操作:</label></td>
			<td class="value">
				<input nullmsg="请填写电脑操作" errormsg="电脑操作填写格式不对" class="inputxt" id="computeropt" name="computeropt" ignore="ignore"
									   value="${staffPage.computeropt}" >
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">入职日期:</label></td>
			<td class="value">
				<input nullmsg="请填写员工入职日期" errormsg="员工入职日期格式不对" class="Wdate" onClick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss'})"  style="width: 150px" id="entrydate" name="entrydate" ignore="ignore"
									     value="<fmt:formatDate value='${staffPage.entrydate}' type="date" pattern="yyyy-MM-dd hh:mm:ss"/>" readonly>
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">试用期薪金:</label></td>
			<td class="value">
				<input nullmsg="请填写试用期薪金" errormsg="试用期薪金格式不对" class="inputxt" id="probationsalary" name="probationsalary" ignore="ignore"
									   value="${staffPage.probationsalary}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
			<td align="right"><label class="Validform_label">入职部门:</label></td>
			<td class="value">
				<input nullmsg="请填写入职部门" errormsg="入职部门格式不对" class="inputxt" id="entrydepartment" name="entrydepartment" ignore="ignore"
									   value="${staffPage.entrydepartment}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			<td align="right"><label class="Validform_label">试用期满规定时间:</label></td>
			<td class="value">
				<input nullmsg="请填写试用期满规定时间" errormsg="试用期满规定时间格式不对" class="inputxt" id="probationtime" name="probationtime" ignore="ignore"
									   value="${staffPage.probationtime}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<td align="right"><label class="Validform_label"> 所选部门: </label></td>
			<td class="value"><select id="departid" name="departid" datatype="*">
				<c:forEach items="${departList}" var="depart">
					<option value="${depart.id }" <c:if test="${depart.id==staffPage.departid}">selected="selected"</c:if>>${depart.departname}</option>
				</c:forEach>
			</select> <span class="Validform_checktip">请选择部门</span></td>
			<td align="right"><label class="Validform_label"> 所选职位: </label></td>
			<td>	
				<input nullmsg="请填写职位" class="inputxt" id="position" name="position"
									   value="${staffPage.position}" readonly>
								<span class="Validform_checktip"></span>
			</td>
			</tr>
			<tr>
					<td align="right"><label class="Validform_label"> 角色: </label></td>
					<td class="value" nowrap><input name="roleid" name="roleid" type="hidden" value="${id}" id="roleid"> <input name="roleName" class="inputxt" value="${roleName }" id="roleName"
						readonly="readonly" datatype="*" />
			</tr>
			</table>
			<div style="width: auto;height: 200px;">
				<div style="width:690px;height:1px;"></div>
				<t:tabs id="tt" iframe="false" tabPosition="top" fit="false">
				 <t:tab href="staffController.do?workexperienceList&id=${staffPage.id}" icon="icon-search" title="工作经历" id="workexperience"></t:tab>
				 <t:tab href="staffController.do?educationList&id=${staffPage.id}" icon="icon-search" title="学历" id="education"></t:tab>
				 <t:tab href="staffController.do?familymemberList&id=${staffPage.id}" icon="icon-search" title="家庭成员" id="familymember"></t:tab>
				</t:tabs>
			</div>
			<center>
			<input type = "submit" value = "提交">
			</center>
			</t:formvalid>
			<!-- 添加 明细 模版 -->
		<table style="display:none">
		<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">曾工作单位</td>
				  <td align="left" bgcolor="#EEEEEE">职位</td>
				  <td align="left" bgcolor="#EEEEEE">到职年月</td>
				  <td align="left" bgcolor="#EEEEEE">离职年月</td>
				  <td align="left" bgcolor="#EEEEEE">离职原因</td>
		</tr>
		<tbody id="add_workexperience_table">
			<c:if test="${fn:length(workexperienceList)  > 0 }">
			<c:forEach items="${workexperienceList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="workexperienceList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="workexperienceList[${stuts.index }].workunit" maxlength="30" value="${poVal.workunit }" type="text" style="width:120px;" readonly></td>
				   <td align="left"><input name="workexperienceList[${stuts.index }].position" maxlength="30" value="${poVal.position }" type="text" style="width:120px;" readonly></td>
				   <td align="left"><input name="workexperienceList[${stuts.index }].arrivaldate" maxlength="" value="${poVal.arrivaldate }" type="text" style="width:120px;" readonly></td>
				   <td align="left"><input name="workexperienceList[${stuts.index }].departuredate" maxlength="" value="${poVal.departuredate }" type="text" style="width:120px;" readonly></td>
				   <td align="left"><input name="workexperienceList[${stuts.index }].departurereason" maxlength="255" value="${poVal.departurereason }" type="text" style="width:120px;" readonly></td>
   			</tr>
			</c:forEach>
			</c:if>	
		 </tbody>
		<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">开始时间</td>
				  <td align="left" bgcolor="#EEEEEE">结束时间</td>
				  <td align="left" bgcolor="#EEEEEE">专业</td>
				  <td align="left" bgcolor="#EEEEEE">学位</td>
				  <td align="left" bgcolor="#EEEEEE">是否毕业</td>
				  <td align="left" bgcolor="#EEEEEE">证件名称</td>
		</tr>
		<tbody id="add_education_table">
			<c:if test="${fn:length(educationList)  > 0 }">
			<c:forEach items="${educationList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="educationList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="educationList[${stuts.index }].starttime" maxlength="" value="${poVal.starttime }" type="text" style="width:120px;" readonly></td>
				   <td align="left"><input name="educationList[${stuts.index }].endtime" maxlength="" value="${poVal.endtime }" type="text" style="width:120px;" readonly></td>
				   <td align="left"><input name="educationList[${stuts.index }].major" maxlength="20" value="${poVal.major }" type="text" style="width:120px;" readonly></td>
				   <td align="left"><input name="educationList[${stuts.index }].degree" maxlength="10" value="${poVal.degree }" type="text" style="width:120px;"readonly></td>
				   <td align="left"><input name="educationList[${stuts.index }].graduation" maxlength="3" value="${poVal.graduation }" type="text" style="width:120px;"readonly></td>
				   <td align="left"><input name="educationList[${stuts.index }].certificate" maxlength="20" value="${poVal.certificate }" type="text" style="width:120px;"readonly></td>
   			</tr>
			</c:forEach>
			</c:if>	
		 </tbody>
		<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">关系</td>
				  <td align="left" bgcolor="#EEEEEE">成员姓名</td>
				  <td align="left" bgcolor="#EEEEEE">年龄</td>
				  <td align="left" bgcolor="#EEEEEE">职业</td>
				  <td align="left" bgcolor="#EEEEEE">现住地址</td>
	    </tr>
		<tbody id="add_familymember_table">
			<c:if test="${fn:length(familymemberList)  > 0 }">
			<c:forEach items="${familymemberList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="familymemberList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="familymemberList[${stuts.index }].relationship" maxlength="10" value="${poVal.relationship }" type="text" style="width:120px;" readonly></td>
				   <td align="left"><input name="familymemberList[${stuts.index }].membername" maxlength="10" value="${poVal.membername }" type="text" style="width:120px;" readonly></td>
				   <td align="left"><input name="familymemberList[${stuts.index }].age" maxlength="" value="${poVal.age }" type="text" style="width:120px;" readonly></td>
				   <td align="left"><input name="familymemberList[${stuts.index }].occupation" maxlength="11" value="${poVal.occupation }" type="text" style="width:120px;" readonly></td>
				   <td align="left"><input name="familymemberList[${stuts.index }].addressnow" maxlength="20" value="${poVal.addressnow }" type="text" style="width:120px;" readonly></td>
   			</tr>
			</c:forEach>
			</c:if>	
		 </tbody>
		<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">部门</td>
				  <td align="left" bgcolor="#EEEEEE">职位</td>
		</tr>
		<tbody id="add_department_table">	
		<c:if test="${fn:length(departmentList)  > 0 }">
			<c:forEach items="${departmentList}" var="poVal" varStatus="stuts">
				<tr>
					<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
					<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
					<input name="departmentList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
					   <td align="left"><input name="departmentList[${stuts.index }].dept" maxlength="20" value="${poVal.dept }" type="text" style="width:120px;"></td>
					   <td align="left"><input name="departmentList[${stuts.index }].job" maxlength="20" value="${poVal.job }" type="text" style="width:120px;"></td>
	   			</tr>
			</c:forEach>
		</c:if>	
		</tbody>
		</table>
 </body>