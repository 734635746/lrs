<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="setFreeList" title="收据表" actionUrl="setFreeController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="付款人" field="paymen" query="true"></t:dgCol>
   <t:dgCol title="金额" field="money" query="true"></t:dgCol>
  <%--   <t:dgCol title="是否已打印" field="flag" replace="已打印_1,未打印_0" query="true"></t:dgCol> --%>
   <t:dgCol title="是否已取消" field="cancel" replace="已取消_1,正常_0" query="true"></t:dgCol>
   <t:dgCol title="备注" field="remark" ></t:dgCol>
   <t:dgCol title="摘要" field="summary" ></t:dgCol>
  <%--  <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="receiptController.do?del&id={id}" /> --%>
   <t:dgToolBar title="录入开光收入" icon="icon-add" url="setFreeController.do?towardSetFree" funname="free"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>