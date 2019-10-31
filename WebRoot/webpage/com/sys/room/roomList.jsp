<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="roomList" title="客房基本信息" actionUrl="roomController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="房间号" field="roomNumber" ></t:dgCol>
   <t:dgCol title="房间类型" field="roomKind" ></t:dgCol>
   <t:dgCol title="床位" field="beds" ></t:dgCol>
   <t:dgCol title="备注" field="remark" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="roomController.do?del&id={id}" />
   <t:dgToolBar title="录入房间信息" icon="icon-add" url="roomController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑房间信息" icon="icon-edit" url="roomController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看房间信息" icon="icon-search" url="roomController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="入住登记" icon="icon-add" url="roomController.do?toSearch" funname="add"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>