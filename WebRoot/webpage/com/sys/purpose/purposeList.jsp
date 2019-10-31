<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/js/jquery.js">


<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="purposeList"  title="善款用途管理" actionUrl="purposeController.do?datagrid" idField="id"   
  		fit="true"  >
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="用途" field="purpose" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" ></t:dgCol>
   <t:dgDelOpt title="删除" url="purposeController.do?del&id={id}" />
   <t:dgToolBar title="录入用途" icon="icon-add" url="purposeController.do?add" funname="add"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>