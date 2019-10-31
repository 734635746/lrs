<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="religiousAttendanceList" title="法事考勤管理" actionUrl="attendanceController.do?religiousAttendanceDatagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="农历日期" field="lunardate" query = "true"></t:dgCol>
   <t:dgCol title="公历日期" field="solardate" formatter="yyyy-MM-dd"  query = "true"></t:dgCol>
   <t:dgCol title="姓名" field="memberName" query = "true"></t:dgCol>
   <t:dgCol title="工号" field="memberId" query = "true"></t:dgCol>
   <t:dgCol title="请假原因" field="reason" query = "true"></t:dgCol>
   <t:dgCol title="考勤类型" field="attendanceType"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="attendanceController.do?del&id={id}"  operationCode="del"/>
  <t:dgToolBar title="录入法事考勤" icon="icon-add" url="attendanceController.do?towardShowCrowdsRecord&type=5" funname="ShowCrowdsRecord"  operationCode="add"></t:dgToolBar>
  <%--  <t:dgToolBar title="录入" icon="icon-add" url="attendanceController.do?addorupdate" funname="add"></t:dgToolBar> --%>
   <%-- <t:dgToolBar title="编辑" icon="icon-edit" url="attendanceController.do?addorupdate" funname="update"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="attendanceController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
  <script type="text/javascript">
	$(document).ready(function(){
		$("input[name='solardate']").attr("class","easyui-datebox");
	});
</script>