<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="funeralheldList" title="法事举行日期安排" actionUrl="funeralheldController.do?datagrid" idField="id" fit="true" queryMode = "group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="法事类型" field="ritualtype" query = 'true'></t:dgCol>
   <t:dgCol title="举行年份" field="holdYear" query = 'true'></t:dgCol>
   <t:dgCol title="农历开始日期" field="holdDate" query = 'true'></t:dgCol>
   <t:dgCol title="持续天数" field="continuouDays" query = 'true'></t:dgCol>
   <t:dgCol title="农历结束日期" field="endDate" query = 'true'></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="funeralheldController.do?del&id={id}" operationCode="del"/>
   <t:dgToolBar title="录入" icon="icon-add" url="funeralheldController.do?addorupdate" funname="add"  operationCode="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="funeralheldController.do?addorupdate" funname="update"  operationCode="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="funeralheldController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>