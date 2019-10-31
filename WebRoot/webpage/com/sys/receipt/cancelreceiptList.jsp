<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="cancelreceiptList" title="收据表" actionUrl="receiptController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="no" query="true"></t:dgCol>
   <t:dgCol title="法事类型" field="ritualtype" query="true"></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" ></t:dgCol>
   <t:dgCol title="登记人" field="registrant" query="true"></t:dgCol>
   <t:dgCol title="付款人" field="paymen" query="true"></t:dgCol>
   <t:dgCol title="对象" field="obj" query="true"></t:dgCol>
   <t:dgCol title="金额" field="money" ></t:dgCol>
   <t:dgCol title="付款方式" field="payway" query="true"></t:dgCol>
   <t:dgCol title="摘要" field="summary" ></t:dgCol>
   <t:dgCol title="地址" field="address" query="true"></t:dgCol>
   <t:dgCol title="备注" field="remark" ></t:dgCol>
   <t:dgCol title="用途" field="purpose" query="true"></t:dgCol>
   <t:dgCol title="是否已打印" field="flag" replace="已打印_1,未打印_0" query="true"></t:dgCol>
   <t:dgCol title="是否已取消" field="cancel" replace="已取消_1,正常_0" query="true"></t:dgCol>
    <t:dgCol title="取消原因" field="cancelreason" query="true"></t:dgCol>
    <t:dgCol title="取消经手人" field="cancelhandler" query="true"></t:dgCol>
    <t:dgCol title="取消日期" field="canceldate" query="true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="receiptController.do?del&id={id}" />
   <t:dgToolBar title="退订" icon="icon-undo" url="receiptController.do?cancel" funname="update"></t:dgToolBar>

   <t:dgToolBar title="查看" icon="icon-search" url="receiptController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>