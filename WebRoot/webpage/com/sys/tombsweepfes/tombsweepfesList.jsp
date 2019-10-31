<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="tombsweepfesList" title="清明节" actionUrl="tombsweepfesController.do?datagrid" idField="id" fit="true" sortName="registertime" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" query='true'></t:dgCol>
   <t:dgCol title="登记人" field="registrant" query='true'></t:dgCol>
   <t:dgCol title="系统编号" field="serial" query='true'></t:dgCol>
   <t:dgCol title="自动编号" field="autoserial" query='true'></t:dgCol>
   <t:dgCol title="牌位大小" field="size" query='true'></t:dgCol>
    <t:dgCol title="付款人" field="paymen" query='true'></t:dgCol>
   <t:dgCol title="阳上" field="prayingobj" query='true'></t:dgCol>
   <t:dgCol title="超渡对象" field="ancestor" ></t:dgCol>
   <t:dgCol title="类型" field="type" query='true' replace="无_1,十方法界_2,门堂历代宗亲_3,堕胎婴灵_4"></t:dgCol>
   <t:dgCol title="金额" field="money" query='true'></t:dgCol>
   <t:dgCol title="收款方式" field="payway" query='true'></t:dgCol>
   <t:dgCol title="摘要" field="summary" ></t:dgCol>
   <t:dgCol title="收据编号" field="receiptno" query='true'></t:dgCol>
   <t:dgCol title="地址" field="address" query='true'></t:dgCol>
  <%--  <t:dgCol title="是否已打印" field="flag" replace="已打印_1,未打印_0"  query="true"></t:dgCol> --%>
   <t:dgCol title="是否已取消" field="cancel" replace="已取消_1,正常_0" query="true"></t:dgCol>
  <%--  <t:dgCol title="操作" field="opt" width="100"></t:dgCol> --%>
  <%--  <t:dgDelOpt title="删除" url="tombsweepfesController.do?del&id={id}" /> --%>
   <t:dgToolBar title="查看" icon="icon-search" url="tombsweepfesController.do?addorupdate" funname="detail"></t:dgToolBar>
   <t:dgToolBar title="修改清明节记录" icon="icon-search" url="tombsweepfesController.do?myUpdate" funname="ritualUpdate"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
  <script>
 $(document).ready(function(){
		
		$("input[name='registertime']").attr("class","easyui-datebox");
	});
</script>