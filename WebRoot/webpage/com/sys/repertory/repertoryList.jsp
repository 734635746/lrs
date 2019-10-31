<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="repertoryList" title="库存表" actionUrl="repertoryController.do?datagrid" idField="id" fit="true" queryMode ="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="登记人" field="register" query = "true"></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" formatter="yyyy-MM-dd" query = "true"></t:dgCol>
   <t:dgCol title="物品编码" field="itemStdmode" query = "true"></t:dgCol>
   <t:dgCol title="物品名称" field="itemName" query = "true"></t:dgCol>
   <t:dgCol title="生产厂家" field="itemManufacturer" query = "true"></t:dgCol>
   <t:dgCol title="生产地址" field="manufacturerAddress" query = "true"></t:dgCol>
   <t:dgCol title="当前库存量" field="currentInventory" query = "true"></t:dgCol>
    <t:dgCol title="单价" field="price" query = "true"></t:dgCol>
     <t:dgCol title="单位" field="unit" query = "true"></t:dgCol>
   <t:dgCol title="最低库存量" field="quantityStorage" query = "true"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="repertoryController.do?del&id={id}" />
   <%-- <t:dgToolBar title="录入" icon="icon-add" url="repertoryController.do?addorupdate" funname="add"></t:dgToolBar> --%>
   <t:dgToolBar title="编辑" icon="icon-edit" url="repertoryController.do?addorupdate" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看" icon="icon-search" url="repertoryController.do?addorupdate" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 <script type="text/javascript">
	$(document).ready(function(){
		$("input[name='registertime']").attr("class","easyui-datebox");
	});
</script>