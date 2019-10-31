<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addNamelistBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delNamelistBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addNamelistBtn').bind('click', function(){   
 		 var tr =  $("#add_namelist_table_template tr").clone();
	 	 $("#add_namelist_table").append(tr);
	 	 resetTrNum('add_namelist_table');
    });  
	$('#delNamelistBtn').bind('click', function(){   
      	$("#add_namelist_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_namelist_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addNamelistBtn" href="#">添加</a> <a id="delNamelistBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="namelist_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">姓名</td>
				  <td align="left" bgcolor="#EEEEEE">关系</td>
				  <td align="left" bgcolor="#EEEEEE">状态</td>
				  
	</tr>
	<tbody id="add_namelist_table">	
	<c:if test="${fn:length(namelistList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="namelistList[0].name" maxlength="20" type="text" style="width:220px;" ></td>
				  <td align="left"><input name="namelistList[0].relation" maxlength="5" type="text" style="width:120px;" ></td>
				  <td align="left"><t:dictSelect field="namelistList[0].state" typeGroupCode="mem_state" hasLabel="false" defaultVal="延生" type="radio">
						</t:dictSelect> <span class="Validform_checktip"></span></td>
				  
   			</tr>
	</c:if>
	<c:if test="${fn:length(namelistList)  > 0 }">
		<c:forEach items="${namelistList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="namelistList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="namelistList[${stuts.index }].name" maxlength="20" value="${poVal.name }" type="text" style="width:220px;"></td>
				   <td align="left"><input name="namelistList[${stuts.index }].relation" maxlength="5" value="${poVal.relation }" type="text" style="width:120px;"></td>
				   <td align="left"><t:dictSelect field="namelistList[${stuts.index }].state" typeGroupCode="mem_state" hasLabel="false" defaultVal="${poVal.state}" type="radio">
						</t:dictSelect> <span class="Validform_checktip"></span></td>
					
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>