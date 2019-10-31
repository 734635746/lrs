<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="warehousing_detailList" title="入库明细管理" actionUrl="warehousing_detailController.do?datagrid" idField="id" fit="true" queryMode = "group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="登记人" field="register" ></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" ></t:dgCol>
   <t:dgCol title="物品编码" field="itemStdmode" query = "true"></t:dgCol>
   <t:dgCol title="物品名称" field="itemName"  query = "true"></t:dgCol>
   <t:dgCol title="生产厂家" field="itemManufacturer"  query = "true"></t:dgCol>
   <t:dgCol title="生产地址" field="manufacturerAddress"  query = "true"></t:dgCol>
   <t:dgCol title="入库量" field="incomingQuantity"  query = "true"></t:dgCol>
   <t:dgCol title="单价" field="price"  query = "true"></t:dgCol>
   <t:dgCol title="单位" field="unit"  query = "true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="warehousing_detailController.do?del&id={id}" />
   <t:dgToolBar title="入库" icon="icon-reload" url="warehousing_detailController.do?towardWareHousing" funname="towardWareHousing"></t:dgToolBar>
  <%--  <t:dgToolBar title="录入" icon="icon-add" url="warehousing_detailController.do?addorupdate" funname="add"></t:dgToolBar> --%>
  <%--  <t:dgToolBar title="编辑" icon="icon-edit" url="warehousing_detailController.do?addorupdate" funname="update"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="warehousing_detailController.do?addorupdate" funname="detail"></t:dgToolBar>
   
  </t:datagrid>
  </div>
 </div>