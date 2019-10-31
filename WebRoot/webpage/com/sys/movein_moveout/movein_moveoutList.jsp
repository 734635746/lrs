<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="movein_moveoutList" title="迁入迁出记录" actionUrl="movein_moveoutController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="迁入时间" field="moveintime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="迁出时间" field="moveouttime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" ></t:dgCol>
   <t:dgCol title="经手人" field="a_handler" query="true"></t:dgCol>
   <t:dgCol title="皈依弟子姓名" field="disciplename"  query="true"></t:dgCol>
   <t:dgCol title="迁入迁出原因" field="reason" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="movein_moveoutController.do?del&id={id}" operationCode = "del"/>
   <t:dgToolBar title="查看" icon="icon-search" url="movein_moveoutController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>