<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
	<div region="center" style="padding:1px;">
	
		<t:datagrid name="receiptList" title="收据表"
			actionUrl="receiptController.do?datagrid" idField="id" fit="true"
			sortName="registertime" queryMode="single">
			
			<t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
			<t:dgCol title="收据编号" field="no" query="true"></t:dgCol>
			<t:dgCol title="法事类型" field="ritualtype" query="true"></t:dgCol>
			<t:dgCol title="登记时间" field="registertime" query="true"></t:dgCol>
			<t:dgCol title="登记人" field="registrant" query="true"></t:dgCol>
			<t:dgCol title="付款人" field="paymen" query="true"></t:dgCol>
			<t:dgCol title="对象" field="obj" query="true"></t:dgCol>
			<t:dgCol title="金额" field="money"></t:dgCol>
			<t:dgCol title="付款方式" field="payway" query="true"></t:dgCol>
			<t:dgCol title="摘要" field="summary"></t:dgCol>
			<t:dgCol title="地址" field="address" query="true"></t:dgCol>
			<t:dgCol title="备注" field="remark"></t:dgCol>
			<t:dgCol title="用途" field="purpose" query="true"></t:dgCol>
			<%-- <t:dgCol title="是否已打印" field="flag" replace="已打印_1,未打印_0" query="true"></t:dgCol> --%>
			<t:dgCol title="是否已取消" field="cancel" replace="已取消_1,正常_0"
				query="true"></t:dgCol>
			<t:dgCol title="取消原因" field="cancelreason" query="true"></t:dgCol>
			<t:dgCol title="取消经手人" field="cancelhandler" query="true"></t:dgCol>
			<t:dgCol title="取消日期" field="canceldate" query="true"></t:dgCol>
			<t:dgCol title="是否确认交接" field="transit" replace="已交接_1,未交接_0"
				query="true"></t:dgCol>
			<%--   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="receiptController.do?del&id={id}" /> --%>
			<t:dgToolBar title="查看" icon="icon-search"
				url="receiptController.do?addorupdate" funname="detail"></t:dgToolBar>
		</t:datagrid>

		<!-- 查询条件 -->
		<div style="padding: 3px; height: auto">
			<div name="searchColums" >
				<span> 
				<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;"
					title="收据编号 ">收据编号: </span> <input type="text" name="no" style="width: 100px;"> 
				<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;"
					title="法事类型">法事类型: </span> <input type="text" name="ritualtype" style="width: 100px;">
					
			   <span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 90px;text-align:right;" title="登记时间 ">登记时间: </span>
              <input type="text" name="beginRegistertime" style="width: 100px; height: 24px;">~
              <input type="text" name="endRegistertime" style="width: 100px; height: 24px; margin-right: 20px;">
				
				<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;"
					title="登记人">登记人:</span> <input type="text" name="registrant" style="width: 100px; ">
				<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;"
					title="付款人">付款人: </span> <input type="text" name="paymen" style="width: 100px;">
				<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;"
					title="对象">对象: </span> <input type="text" name="obj" style="width: 100px;">
				
				<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;"
					title="付款方式">付款方式: </span> <select name="payway" style="width: 80px">
						<option value="">请选择</option>
						<option value="现金">现金</option>
						<option value="刷卡">刷卡</option>
						<option value="支付宝">支付宝</option>
						<option value="微信">微信</option>
						<option value="其他">其他</option>
				</select> <br /> <br />
					
				
				<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;"
					title="用途">用途: </span> <input type="text" name="purpose" style="width: 100px; ">
				
				 <span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;"
					title="取消日期">取消日期: </span> <input type="text" name="canceldate" style="width: 76px; height: 20px; line-height: 20px;">
				
				<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 80px;text-align:right;"
					title="是否退订">是否退订: </span> <select name="canel" style="width: 80px">
						<option value="">请选择</option>
						<option value="1">已退订</option>
						<option value="0">未退订</option>
				</select> 
				
				<span style="vertical-align:middle;display:-moz-inline-box;display:inline-block;width: 100px;text-align:right;"
					title="是否确认交接">是否确认交接: </span> <select name="transit" style="width: 80px">
						<option value="">请选择</option>
						<option value="1">已交接</option>
						<option value="0">未交接</option>
				</select>
				
				 <a
					href="#" class="easyui-linkbutton" iconCls="icon-search"
					onclick="receiptListsearch();"
					>查询</a> <a
					href="javascript:void(0)" class="easyui-linkbutton"
					iconCls="icon-putout" onclick="exportExcel();">导出excel</a>
					</span>
			</div>
		</div>
		<!-- 查询条件 -->

	</div>
	
</div>
<<script type="text/javascript">
 $(document).ready(function(){
        $("input[name='beginRegistertime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy年MM月dd日'});});
        
        $("input[name='endRegistertime']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy年MM月dd日'});});
        $("input[name='canceldate']").attr("class","Wdate").attr("style","height:20px;width:90px;").click(function(){WdatePicker({dateFmt:'yyyy年MM月dd日'});});
    });
    
	function exportExcel(){
		JeecgExcelExport("receiptController.do?exportXls","receiptList");
	}
</script>
