<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="disciplememberList" title="皈依弟子档案表" actionUrl="disciplememberController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="姓名" field="disciplename" query="true"></t:dgCol>
   <t:dgCol title="法名" field="dharmaname" ></t:dgCol>
   <t:dgCol title="籍贯" field="origin" query="true"></t:dgCol>
   <t:dgCol title="性别" field="sex" query="true"></t:dgCol>
   <t:dgCol title="学历" field="education" query="true"></t:dgCol>
   <t:dgCol title="学科" field="subject" ></t:dgCol>
   <t:dgCol title="职业" field="profession" query="true"></t:dgCol>
   <t:dgCol title="职称" field="professiontitle" ></t:dgCol>
   <t:dgCol title="爱好" field="hobby" ></t:dgCol>
   <t:dgCol title="特长" field="speciality" ></t:dgCol>
   <t:dgCol title="手机电话" field="phonenumber" query="true"></t:dgCol>
   <t:dgCol title="固话" field="homephone" ></t:dgCol>
   <t:dgCol title="联系地址" field="address" ></t:dgCol>
   <t:dgCol title="皈依原因" field="disciplereason" ></t:dgCol>
   <t:dgCol title="是否迁出" field="status" replace="是_1,否_0"  query='true'></t:dgCol>
   <t:dgCol title="" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="disciplememberController.do?del&id={id}" operationCode = "del"></t:dgDelOpt>
   
   <t:dgToolBar title="录入皈依弟子信息" icon="icon-add" url="disciplememberController.do?addorupdate" funname="add" operationCode="add"></t:dgToolBar>
   <t:dgToolBar title="编辑皈依弟子信息" icon="icon-edit" url="disciplememberController.do?addorupdate" funname="update" operationCode="update"></t:dgToolBar>
   <t:dgToolBar title="查看皈依弟子信息" icon="icon-search" url="disciplememberController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="皈依弟子迁出" icon="icon-cancel" url="disciplememberController.do?addorupdate&flag=out" funname="update" operationCode = "out"></t:dgToolBar>
   <t:dgToolBar title="皈依弟子迁入" icon="icon-reload" url="disciplememberController.do?addorupdate&flag=in" funname="update" operationCode = "in"></t:dgToolBar>
  	
  </t:datagrid>
  </div>
 </div>