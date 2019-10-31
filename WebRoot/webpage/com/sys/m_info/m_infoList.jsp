<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="m_infoList" title="牌位信息" actionUrl="m_infoController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="堂口" field="buildingName" ></t:dgCol>
   <t:dgCol title="段名" field="danName" ></t:dgCol>
   <t:dgCol title="行数" field="Row" ></t:dgCol>
   <t:dgCol title="列数" field="Column" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="m_infoController.do?del&id={id}"  operationCode="delete"/>
   <t:dgToolBar title="录入" icon="icon-add" url="m_infoController.do?addorupdate" funname="add" operationCode="add"></t:dgToolBar>
   <%-- <t:dgToolBar title="编辑" icon="icon-edit" url="m_infoController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="m_infoController.do?addorupdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>