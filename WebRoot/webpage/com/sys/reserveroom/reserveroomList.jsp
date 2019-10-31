<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="reserveroomList" title="预定房间" actionUrl="reserveroomController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="房号" field="roomnumber" ></t:dgCol>
   <t:dgCol title="房间类型" field="roomkind" ></t:dgCol>
   <t:dgCol title="所需床位" field="needbeds" ></t:dgCol>
   <t:dgCol title="登记人" field="registrant" ></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="入住时间" field="intakentime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="离开时间" field="leavetime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="手机号" field="mobilephone" ></t:dgCol>
   <t:dgCol title="预定状态" field="predeterminedstate" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="reserveroomController.do?del&id={id}" />
  <%--  <t:dgToolBar title="编辑预定登记信息" icon="icon-edit" url="reserveroomController.do?addorupdate&reserverflag=no" funname="update"></t:dgToolBar> --%>
   <t:dgToolBar title="查看预定登记信息" icon="icon-search" url="reserveroomController.do?addorupdate&reserverflag=no" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="预定登记房间" icon="icon-add" url="reserveroomController.do?toSearch" funname="add"></t:dgToolBar>
   <t:dgToolBar title="确认入住登记" icon="icon-edit" url="reserveroomController.do?addorupdate&reserverflag=no" funname="update"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>