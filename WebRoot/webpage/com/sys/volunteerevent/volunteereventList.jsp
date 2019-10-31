<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addVolunteereventBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delVolunteereventBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addVolunteereventBtn').bind('click', function(){   
 		 var tr =  $("#add_volunteerevent_table_template tr").clone();
	 	 $("#add_volunteerevent_table").append(tr);
	 	 resetTrNum('add_volunteerevent_table');
    });  
	$('#delVolunteereventBtn').bind('click', function(){   
      	$("#add_volunteerevent_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_volunteerevent_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addVolunteereventBtn" href="#">添加</a> <a id="delVolunteereventBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="volunteerevent_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">事件名字</td>
				  <td align="left" bgcolor="#EEEEEE">义工名字</td>
				  <td align="left" bgcolor="#EEEEEE">工作情况</td>
				  <td align="left" bgcolor="#EEEEEE">工作态度</td>
	</tr>
	<tbody id="add_volunteerevent_table">	
	<c:if test="${fn:length(volunteereventList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="volunteereventList[0].eventname" maxlength="36" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="volunteereventList[0].volunteername" maxlength="36" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="volunteereventList[0].workcondition" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="volunteereventList[0].atitude" maxlength="" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(volunteereventList)  > 0 }">
		<c:forEach items="${volunteereventList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="volunteereventList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="volunteereventList[${stuts.index }].eventname" maxlength="36" value="${poVal.volunteerid }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="volunteereventList[${stuts.index }].volunteername" maxlength="36" value="${poVal.volunteerid }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="volunteereventList[${stuts.index }].workcondition" maxlength="" value="${poVal.workcondition }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="volunteereventList[${stuts.index }].atitude" maxlength="" value="${poVal.atitude }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>