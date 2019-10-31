<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="fundreceiptList" title="收据表" actionUrl="receiptController.do?funddatagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="no" query="true"></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" query="true"></t:dgCol>
   <t:dgCol title="登记人" field="registrant" query="true"></t:dgCol>
   <t:dgCol title="捐款人" field="paymen" query="true"></t:dgCol>
   <t:dgCol title="金额" field="money" query="true"></t:dgCol>
   <t:dgCol title="地址" field="address" query="true"></t:dgCol>
   <t:dgCol title="备注" field="remark" ></t:dgCol>
   <t:dgCol title="用途" field="purpose"  query="true"></t:dgCol>
   <t:dgCol title="摘要" field="summary" ></t:dgCol>
  <%--  <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="receiptController.do?del&id={id}" /> --%>
   <t:dgToolBar title="录入善款" icon="icon-add" url="receiptController.do?fundToward" funname="fundregister"></t:dgToolBar>
  <%--  <t:dgToolBar title="编辑善款" icon="icon-edit" url="receiptController.do?fundaddorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看善款" icon="icon-search" url="receiptController.do?fundaddorupdate" funname="detail"></t:dgToolBar> --%>
  </t:datagrid>
  </div>
 </div>