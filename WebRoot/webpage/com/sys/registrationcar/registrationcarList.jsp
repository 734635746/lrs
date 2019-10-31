<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="registrationcarList" title="车辆使用登记表" actionUrl="registrationcarController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="车型" field="cartype" query="true"></t:dgCol>
   <t:dgCol title="车牌号码" field="platenumber" query="true"></t:dgCol>
   <t:dgCol title="状态" field="carstatus" ></t:dgCol>
   <t:dgCol title="用车人" field="usingcarpeople" ></t:dgCol>
   <t:dgCol title="用车时间" field="usingtime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="前往地点" field="toplace" ></t:dgCol>
   <t:dgCol title="返回时间" field="returntime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="备注" field="remark" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="registrationcarController.do?del&id={id}" />
   <t:dgToolBar title="登记" icon="icon-add" url="registrationcarController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="registrationcarController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="registrationcarController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>