<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="memorialinfo_viewList" title="牌位视图" actionUrl="memorialinfo_viewController.do?datagrid" idField="unionID" fit="true" queryMode="group">
   <t:dgCol title="编号" field="unionID" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="serial" ></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="堂口" field="buildingName" query="true"></t:dgCol>
   <t:dgCol title="段位" field="dan" query="true"></t:dgCol>
   <t:dgCol title="行号" field="row" query="true"></t:dgCol>
   <t:dgCol title="位号" field="ordernumber" query="true"></t:dgCol>
   <t:dgCol title="大小" field="size" ></t:dgCol>
    <t:dgCol title="安位时间 " field="comforttime" ></t:dgCol>
   <t:dgCol title="开始时间" field="begintime" ></t:dgCol>
   <t:dgCol title="结束时间" field="endtime" ></t:dgCol>
   <t:dgCol title="备注" field="remark" ></t:dgCol>
   <t:dgCol title="姓名" field="name" query="true"></t:dgCol>
   <t:dgCol title="状态" field="status"></t:dgCol>
   <t:dgCol title="关系" field="relation"></t:dgCol>
   <t:dgCol title="QQ" field="qq" query="true"></t:dgCol>
   <t:dgCol title="地址" field="address" query="true"></t:dgCol>
   <t:dgCol title="电子邮件" field="email" query="true"></t:dgCol>
   <t:dgCol title="固话" field="homephone" query="true"></t:dgCol>
   <t:dgCol title="联系人" field="linkman" query="true"></t:dgCol>
   <t:dgCol title="手机号" field="mobilephone" query="true"></t:dgCol>
   <t:dgCol title="微信号" field="wechat" query="true"></t:dgCol>
   <t:dgCol title="关系" field="relationship" query="true"></t:dgCol>
   
  </t:datagrid>
  </div>
 </div>