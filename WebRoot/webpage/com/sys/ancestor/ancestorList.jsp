<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addAncestorBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delAncestorBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addAncestorBtn').bind('click', function(){   
 		 var tr =  $("#add_ancestor_table_template tr").clone();
	 	 $("#add_ancestor_table").append(tr);
	 	 resetTrNum('add_ancestor_table');
    });  
	$('#delAncestorBtn').bind('click', function(){   
      	$("#add_ancestor_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_ancestor_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addAncestorBtn" href="#">添加</a> <a id="delAncestorBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="ancestor_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">称呼</td>
				  <td align="left" bgcolor="#EEEEEE">姓名</td>
	</tr>
	<tbody id="add_ancestor_table">	
	<c:if test="${fn:length(ancestorList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="ancestorList[0].called" maxlength="10" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="ancestorList[0].name" maxlength="20" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(ancestorList)  > 0 }">
		<c:forEach items="${ancestorList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="ancestorList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="ancestorList[${stuts.index }].called" maxlength="10" value="${poVal.call }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="ancestorList[${stuts.index }].name" maxlength="20" value="${poVal.name }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>