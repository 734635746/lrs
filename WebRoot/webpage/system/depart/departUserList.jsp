<%-- <%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<t:datagrid name="departUserList" title="用户管理" actionUrl="departController.do?userDatagrid&departid=${departid}" fit="true" fitColumns="true" idField="id" queryMode="group">
	<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
	<t:dgCol title="用户名" sortable="false" field="userName" query="true"></t:dgCol>
	<t:dgCol title="真实姓名" field="realName" query="true"></t:dgCol>
	<t:dgCol title="状态" sortable="true" field="status" replace="正常_1,禁用_0,超级管理员_-1"></t:dgCol>
	<t:dgCol title="操作" field="opt"></t:dgCol>
	<t:dgDelOpt title="删除" url="userController.do?del&id={id}&userName={userName}" />
	<t:dgDelOpt title="删除" url="staffController.do?del&id={id}" />
	<t:dgToolBar title="用户录入" icon="icon-add" url="staffController.do?addorupdate&departid=${departid}" funname="add"></t:dgToolBar>
	<t:dgToolBar title="用户编辑" icon="icon-edit" url="staffController.do?addorupdate&departid=${departid}" funname="update"></t:dgToolBar>
</t:datagrid> --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="staffDepartList" fitColumns="true" title="员工信息" actionUrl="staffController.do?userDatagrid&departid=${departid}" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="员工姓名" field="staffname" query="true"></t:dgCol>
   <t:dgCol title="员工性别" field="staffsex" ></t:dgCol>
   <%-- <t:dgCol title="部门" field="departid" query="true" replace="${departsReplace}"></t:dgCol> --%>
    <t:dgCol title="电话号码" field="phonenumber" ></t:dgCol>
   <t:dgCol title="职位" field="position" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt"></t:dgCol>
   <t:dgDelOpt title="删除" url="staffController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="staffController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="staffController.do?addorupdate" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>