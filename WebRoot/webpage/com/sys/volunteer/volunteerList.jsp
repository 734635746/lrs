<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript" charset="utf-8">
	/*
	 *	excel导出
	 */
	function volunteerListExportXls() {
		JeecgExcelExport("volunteerController.do?exportXls","volunteerList");
	}
	function volunteerListImportXls() {
		openuploadwin('Excel导入', 'volunteerController.do?upload', "volunteerList");
	}
	
</script>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="volunteerList" fitColumns="true" title="义工登记信息" sortName="average"
  actionUrl="volunteerController.do?datagrid" idField="id" fit="true" queryMode="group" checkbox="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="义工姓名" field="name" query="true" ></t:dgCol>
   <t:dgCol title="性别" field="sex" query="true"></t:dgCol>
   <t:dgCol title="出生日期" field="birthday" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="身份证" field="certification" ></t:dgCol>
   <t:dgCol title="籍贯" field="nativeplace" query="true"></t:dgCol>
   <t:dgCol title="手机电话" field="phonenumber" ></t:dgCol>
   <t:dgCol title="固话" field="homenumber" ></t:dgCol>
   <t:dgCol title="专业技能" field="professionalskills" ></t:dgCol>
   <t:dgCol title="最高学历" field="higheducation" ></t:dgCol>
   <t:dgCol title="政治面貌" field="politicalstatus" ></t:dgCol>
   <t:dgCol title="平均分" field="average" ></t:dgCol>
   <t:dgCol title="参与次数" field="joincount" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="volunteerController.do?del&id={id}" />
   <t:dgToolBar title="录入义工信息" icon="icon-add" url="volunteerController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑义工信息" icon="icon-edit" url="volunteerController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看义工信息" icon="icon-edit" url="volunteerController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="导出义工信息" icon="icon-reload" onclick="volunteerListExportXls()"></t:dgToolBar>
   <t:dgToolBar title="导入义工信息" icon="icon-back" onclick="volunteerListImportXls()"></t:dgToolBar>
   <t:dgToolBar title="批量删除" icon="icon-remove" url="volunteerController.do?doDeleteALLSelect" funname="deleteALLSelect"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
	function getListSelections(){
		var ids = '';
		var rows = $("#volunteerList").datagrid("getSelections");
		for(var i=0;i<rows.length;i++){
			ids+=rows[i].id;
			ids+=',';
		}
		ids = ids.substring(0,ids.length-1);
		alert(ids);
		return ids;
	}	
	//表单 sql导出
	function doMigrateOut(title,url,id){
		url += '&ids='+ getListSelections();
		alert(url);
		//window.location.href= url;
	}
	function forward(){
		window.location.href= "eventController.do?getEventList";
	}
</script>