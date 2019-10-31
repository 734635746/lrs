<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="eveningpforrList" title="晚普佛管理" actionUrl="eveningpforrController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" ></t:dgCol>
   <t:dgCol title="登记人" field="registrant" ></t:dgCol>
  <%--  <t:dgCol title="登记时间" field="registertime" query="true"></t:dgCol> --%>
   <%-- <t:dgCol title="登记人" field="registrant" query="true"></t:dgCol> --%>
   <t:dgCol title="编号" field="serial" query="true"></t:dgCol>
   <t:dgCol title="祈福者" field="prayingobj" query="true"></t:dgCol>
   <t:dgCol title="超渡对象" field="ancestor" query="true"></t:dgCol>
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
  <%--  <t:dgCol title="操作" field="opt" width="100"></t:dgCol> --%>
  <%--  <t:dgDelOpt title="删除" url="eveningpforrController.do?del&id={id}" />
   <t:dgToolBar title="录入" icon="icon-add" url="eveningpforrController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="eveningpforrController.do?addorupdate" funname="update"></t:dgToolBar> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="eveningpforrController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="修改晚普佛记录" icon="icon-search" url="eveningpforrController.do?myUpdate" funname="eveningpforUpdate"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>