<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="eventList" fitColumns="true" title="事件信息登记" actionUrl="eventController.do?datagrid" idField="id" fit="true">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="事件名字" field="eventname" query = "true" ></t:dgCol>
   <t:dgCol title="创建人" field="creator" query = "true"></t:dgCol>
   <t:dgCol title="创建时间" field="createtime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="是否已评价" field="isestimate" ></t:dgCol>
   <t:dgCol title="是否已确认" field="isconfirm" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="eventController.do?del&id={id}" />
   <t:dgToolBar title="录入事件信息" icon="icon-add" url="eventController.do?addorupdate" funname="add"></t:dgToolBar>
   <t:dgToolBar title="编辑事件信息" icon="icon-edit" url="eventController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看事件信息" icon="icon-edit" url="eventController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="分配义工" icon="icon-edit" url="volunteerController.do?getVolunteerList" funname="update"></t:dgToolBar>
   <t:dgToolBar title="确认义工分配" icon="icon-edit" url="eventController.do?confirm" funname="update"></t:dgToolBar>
   <t:dgToolBar title="评价义工" icon="icon-edit" url="eventController.do?estimate" funname="update"></t:dgToolBar>
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
		window.location.href= "/getVolunteerList";
	}
</script>