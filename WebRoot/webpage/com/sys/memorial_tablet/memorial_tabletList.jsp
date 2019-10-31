<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript" charset="utf-8">
	/*
	 *	excel导出
	 */
	function memorial_tabletListExportXls() {
		JeecgExcelExport("memorial_tabletController.do?exportXls","memorial_tabletList");
	}
	function memorial_tabletListExportStatusXls() {
		JeecgExcelExport("memorial_tabletController.do?exportStatusXls","memorial_tabletList");
	}
	function memorial_tabletListImportXls() {
		openuploadwin('Excel导入', 'memorial_tabletController.do?upload', "memorial_tabletList");
	}
	
</script>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="memorial_tabletList" fitColumns="true" title="牌位表" actionUrl="memorial_tabletController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="编号" field="serial" ></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" formatter="yyyy-MM-dd" queryMode="group"  query="true" ></t:dgCol>
   <t:dgCol title="堂口" field="buildingName" query="true"></t:dgCol>
   <t:dgCol title="段位" field="dan" query="true"></t:dgCol>
   <t:dgCol title="行号" field="row" query="true"></t:dgCol>
   <t:dgCol title="位号" field="ordernumber" query="true"></t:dgCol>
   <t:dgCol title="大小" field="size" ></t:dgCol>
   <t:dgCol title="安位时间 " field="comforttime" ></t:dgCol>
   <t:dgCol title="开始时间" field="begintime" ></t:dgCol>
   <t:dgCol title="结束时间 " field="endtime" ></t:dgCol>
   <t:dgCol title="备注" field="remark" ></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="memorial_tabletController.do?del&id={id}" operationCode="delete"/>
  <%--  <t:dgToolBar title="录入" icon="icon-add" url="memorial_tabletController.do?addorupdate" funname="add"></t:dgToolBar> --%>
    <t:dgToolBar title="登记" icon="icon-add" url="memorial_tabletController.do?towardRegister" funname="towardRegisterTablet" operationCode="add"></t:dgToolBar>
   <t:dgToolBar title="编辑" icon="icon-edit" url="memorial_tabletController.do?addorupdate" funname="update" operationCode="update"></t:dgToolBar>
   <t:dgToolBar title="移位" icon="icon-edit" url="memorial_tabletController.do?towardMove" funname="memorialMove" operationCode="move"></t:dgToolBar>
  <%--  <t:dgToolBar title="查询" icon="icon-edit" url="memorial_tabletController.do?inquiry" funname="add"></t:dgToolBar> --%>
   <t:dgToolBar title="导出Excel" icon="icon-search" onclick="memorial_tabletListExportXls();"></t:dgToolBar>
   <%-- <t:dgToolBar title="导出状态Excel" icon="icon-search" onclick="memorial_tabletListExportStatusXls();"></t:dgToolBar> --%>
   <t:dgToolBar title="导出功德堂销售情况表" icon="icon-search" url="memorial_tabletController.do?towardExportExcel" funname="towardRegisterTablet"></t:dgToolBar>
   <t:dgToolBar title="导入Excel" icon="icon-search" onclick="memorial_tabletListImportXls()"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
 
 <script type="text/javascript">
	$(document).ready(function(){
		$("input[name='registertime_begin']").attr("class","easyui-datebox");
		$("input[name='registertime_end']").attr("class","easyui-datebox");
	});
</script>