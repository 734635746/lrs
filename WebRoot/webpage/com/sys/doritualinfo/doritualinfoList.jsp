<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/jquery.js">
<script>
</script>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="doritualinfoList" fitColumns="true" title="委托人基本信息" actionUrl="doritualinfoController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="姓名" field="rname" query="true"></t:dgCol>
   <t:dgCol title="手机号码1" field="phonenumber1" query="true"></t:dgCol>
   <t:dgCol title="手机号码2" field="phonenumber2" query="true"></t:dgCol>
   <t:dgCol title="居住地址" field="address" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="doritualinfoController.do?del&id={id}" />
   <t:dgToolBar title="录入基本信息" icon="icon-add" url="doritualinfoController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑基本信息" icon="icon-edit" url="doritualinfoController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="法事登记" icon="icon-edit" url="doritualinfoController.do?addorupdateById&search=1" funname="rigister"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>