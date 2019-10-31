<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="staffList" fitColumns="true" title="员工信息" actionUrl="staffController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="员工姓名" field="staffname" query="true"></t:dgCol>
   <t:dgCol title="员工性别" field="staffsex" query="true"></t:dgCol>
   <t:dgCol title="出生年月" field="birthday" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="员工籍贯" field="nativeplace" query="true"></t:dgCol>
   <t:dgCol title="身份证号" field="identification" query="true"></t:dgCol>
   <t:dgCol title="政治面貌" field="status" ></t:dgCol>
   <t:dgCol title="是否结婚" field="marrystatus" ></t:dgCol>
   <t:dgCol title="电话号码" field="phonenumber" query="true"></t:dgCol>
   <t:dgCol title="户口地址" field="address" query="true"></t:dgCol>
   <t:dgCol title="受过训练" field="training" ></t:dgCol>
   <t:dgCol title="语言能力" field="languageability" ></t:dgCol>
   <t:dgCol title="电脑操作" field="computeropt" ></t:dgCol>
   <t:dgCol title="入职日期" field="entrydate" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="试用薪金" field="probationsalary" ></t:dgCol>
   <t:dgCol title="入职部门" field="entrydepartment" ></t:dgCol>
   <t:dgCol title="试用时间" field="probationtime" ></t:dgCol>
   <t:dgCol title="部门" field="departid" query="true" replace="${departsReplace}"></t:dgCol>
   <t:dgCol title="职位" field="position" query="true"></t:dgCol>
   <t:dgCol title="" field="opt"></t:dgCol>
   <t:dgDelOpt title="删除" url="staffController.do?del&id={id}"  operationCode="del"/>
   <t:dgToolBar title="录入员工信息" icon="icon-add" url="staffController.do?addorupdate" funname="add" operationCode="add"></t:dgToolBar>
   <t:dgToolBar title="编辑员工信息" icon="icon-edit" url="staffController.do?addorupdate" funname="update" operationCode="update"></t:dgToolBar>
   <t:dgToolBar title="查看员工信息" icon="icon-edit" url="staffController.do?addorupdate" funname="detail" ></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>