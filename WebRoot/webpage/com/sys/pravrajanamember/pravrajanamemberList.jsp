<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="pravrajanamemberList" title="出家众人档案表" actionUrl="pravrajanamemberController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="俗名" field="pravrajananame" query="true"></t:dgCol>
   <t:dgCol title="性别" field="pravrajanasex" query="true"></t:dgCol>
   <t:dgCol title="出生日期" field="birthday" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="法名" field="dharmaname" query="true"></t:dgCol>
   <t:dgCol title="籍贯" field="origin" query="true"></t:dgCol>
   <t:dgCol title="民族" field="nation" ></t:dgCol>
   <t:dgCol title="法号" field="religiousname" ></t:dgCol>
   <t:dgCol title="健康情况" field="healthstatus" ></t:dgCol>
   <t:dgCol title="来寺时间" field="arrivetime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="文化程度" field="education" ></t:dgCol>
   <t:dgCol title="剃度寺庙" field="tonsuretemple" ></t:dgCol>
   <t:dgCol title="剃度师父" field="tonsuremaster" ></t:dgCol>
   <t:dgCol title="联系电话" field="phonenumber" query="true"></t:dgCol>
   <t:dgCol title="介绍人" field="introducer" ></t:dgCol>
   <t:dgCol title="身份证" field="identification" ></t:dgCol>
   <t:dgCol title="户口所在地" field="registeredresidence" ></t:dgCol>
   <t:dgCol title="部门" field="departid" query="true" replace="${departsReplace}"></t:dgCol>
   <t:dgCol title="" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="pravrajanamemberController.do?del&id={id}" operationCode="del" />
   <t:dgToolBar title="录入出家众人信息" icon="icon-add" url="pravrajanamemberController.do?addorupdate" funname="add" operationCode="add"></t:dgToolBar>
   <t:dgToolBar title="编辑出家众人信息" icon="icon-edit" url="pravrajanamemberController.do?addorupdate" funname="update" operationCode="update"></t:dgToolBar>
   <t:dgToolBar title="查看出家众人信息" icon="icon-search" url="pravrajanamemberController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>