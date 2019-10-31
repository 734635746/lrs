<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="FreeLivesList" title="放生管理" actionUrl="freeLivesController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" query="true"></t:dgCol>
   <t:dgCol title="登记人" field="registrant" query="true"></t:dgCol>
   <t:dgCol title="编号" field="serial" query="true"></t:dgCol>
   <t:dgCol title="付款者" field="prayingobj" query="true"></t:dgCol>
   <t:dgCol title="信众" field="livingmenber" query="true"></t:dgCol>
   <t:dgCol title="堂类型" field="size" query="true"></t:dgCol>
   <t:dgCol title="金额" field="money" query="true"></t:dgCol>
   <t:dgCol title="收款方式" field="payway" query="true"></t:dgCol>
   <t:dgCol title="摘要" field="summary" query="true"></t:dgCol>
   <t:dgCol title="收据编号" field="receiptno" query="true"></t:dgCol>
   <t:dgCol title="地址" field="address" query="true"></t:dgCol>
  <%--  <t:dgCol title="是否已打印" field="flag" replace="已打印_1,未打印_0" query="true"></t:dgCol> --%>
   <t:dgCol title="是否已取消" field="cancel" replace="已取消_1,正常_0" query="true"></t:dgCol>
   <%-- <t:dgCol title="autoserial" field="autoserial" ></t:dgCol> --%>
   <t:dgCol title="付款人" field="paymen" ></t:dgCol>
   <t:dgCol title="农历日期" field="lunardate" query="true"></t:dgCol>
   <t:dgCol title="公历日期" field="solardate" ></t:dgCol>
   <t:dgToolBar title="查看" icon="icon-search" url="freeLivesController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="修改放生记录" icon="icon-edit" url="freeLivesController.do?myUpdate" funname="morningpforUpdate"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>