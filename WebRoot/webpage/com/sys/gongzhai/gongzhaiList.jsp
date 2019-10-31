<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="gongzhaiList" title="供斋管理" actionUrl="gongzhaiController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" ></t:dgCol>
   <t:dgCol title="登记人" field="registrant" ></t:dgCol>
   <t:dgCol title="编号" field="serial" ></t:dgCol>
   <t:dgCol title="付款者" field="prayingobj" ></t:dgCol>
   <t:dgCol title="对象" field="livingmember" ></t:dgCol>
  	<t:dgCol title="金额" field="money" ></t:dgCol>
   <t:dgCol title="收款方式" field="payway" query="true"></t:dgCol>
   <t:dgCol title="摘要" field="summary" ></t:dgCol>
   <t:dgCol title="收据编号" field="receiptno" ></t:dgCol>
   <t:dgCol title="地址" field="address" ></t:dgCol>
 <%--   <t:dgCol title="是否已打印" field="flag" replace="已打印_1,未打印_0"  query="true"></t:dgCol> --%>
   <t:dgCol title="是否已取消" field="cancel" replace="已取消_1,正常_0" query="true"></t:dgCol>
   <t:dgCol title="举办日期" field="holddate" ></t:dgCol>
 <%--   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="gongzhaiController.do?del&id={id}" /> --%>
   <t:dgToolBar title="录入" icon="icon-add" url="gongzhaiController.do?toAddGongzhai" funname="gongzhairegister"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>