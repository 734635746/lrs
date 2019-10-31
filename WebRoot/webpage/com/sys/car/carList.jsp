<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="carList" title="车辆信息" actionUrl="carController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="车辆牌子" field="carname" query="true" ></t:dgCol>
   <t:dgCol title="车牌号" field="carnumber" query="true"></t:dgCol>
   <t:dgCol title="车型" field="cartype" query="true"></t:dgCol>
   <t:dgCol title="买车的时间" field="buytime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="carController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="carController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="carController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="carController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>