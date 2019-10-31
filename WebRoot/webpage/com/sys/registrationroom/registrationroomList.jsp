<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<div class="easyui-layout" fit="true">
  <div region="center" style="padding:1px;">
  <t:datagrid name="registrationroomList" title="登记房间" actionUrl="registrationroomController.do?datagrid" idField="id" fit="true" queryMode="group">
   <t:dgCol title="编号" field="id" hidden="false"></t:dgCol>
   <t:dgCol title="登记人" field="registrant" ></t:dgCol>
   <t:dgCol title="入住人名字" field="intakename" ></t:dgCol>
   <t:dgCol title="身份证号" field="identification" ></t:dgCol>
   <t:dgCol title="手机号" field="mobilephone" ></t:dgCol>
   <t:dgCol title="入住时间" field="intakentime" formatter="yyyy/MM/dd" query="true"></t:dgCol>
   <t:dgCol title="离开时间" field="leavetime" formatter="yyyy/MM/dd" query="true"></t:dgCol>
   <t:dgCol title="确认离开时间" field="confirmdeparturetime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="登记时间" field="registertime" formatter="yyyy-MM-dd hh:mm:ss"></t:dgCol>
   <t:dgCol title="房间类型" sortable="true" field="intakentype" dictionary="roomtype" query="true"></t:dgCol>
    <t:dgCol title="房间号" sortable="true" field="intakennumber" query="true"></t:dgCol>
   <t:dgCol title="所需床位" field="needbeds"></t:dgCol>
   <t:dgCol title="离开状态" field="leavestate"></t:dgCol>
   <t:dgCol title="操作" field="opt" width="100"></t:dgCol>
   <t:dgDelOpt title="删除" url="registrationroomController.do?del&id={id}" />
   <t:dgToolBar title="编辑" icon="icon-edit" url="registrationroomController.do?addorupdate&bookflag=no" funname="update"></t:dgToolBar>
   <t:dgToolBar title="退房登记" icon="icon-edit" url="registrationroomController.do?addorupdate&bookflag=no" funname="update"></t:dgToolBar>
   <t:dgToolBar title="查看退房登记信息" icon="icon-search" url="registrationroomController.do?addorupdate&bookflag=no" funname="detail"></t:dgToolBar>
  </t:datagrid>
  </div>
 </div>
<script type="text/javascript">
	$(document).ready(function(){
		$("input[name='createDate_begin']").attr("class","easyui-datebox");
		$("input[name='createDate_end']").attr("class","easyui-datebox");
		$("input[name='intakentime']").attr("class","easyui-datebox");
		$("input[name='leavetime']").attr("class","easyui-datebox");
	});
</script>