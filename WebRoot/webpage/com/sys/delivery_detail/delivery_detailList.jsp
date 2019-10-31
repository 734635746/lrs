<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="delivery_detailList" title="出库明细表" actionUrl="delivery_detailController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="登记人" field="register" ></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" ></t:dgCol>
   <t:dgCol title="物品编码" field="itemStdmode" ></t:dgCol>
   <t:dgCol title="物品名称" field="itemName" ></t:dgCol>
   <t:dgCol title="生产厂家" field="itemManufacturer" ></t:dgCol>
   <t:dgCol title="生产地址" field="manufacturerAddress" ></t:dgCol>
   <t:dgCol title="取货人名字" field="memberName" ></t:dgCol>
   <t:dgCol title="出库量" field="deliveryQuantity" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="delivery_detailController.do?del&id={id}" />
<%--    <t:dgToolBar title="录入" icon="icon-add" url="delivery_detailController.do?addorupdate" funname="add"></t:dgToolBar> --%>
	<t:dgToolBar title="出库" icon="icon-cancel" url="delivery_detailController.do?towardDelivery" funname="towardDelivery"></t:dgToolBar>
  <%--  <t:dgToolBar title="编辑" icon="icon-edit" url="delivery_detailController.do?addorupdate" funname="update"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="delivery_detailController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>