<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addFamilymemberBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delFamilymemberBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addFamilymemberBtn').bind('click', function(){   
 		 var tr =  $("#add_familymember_table_template tr").clone();
	 	 $("#add_familymember_table").append(tr);
	 	 resetTrNum('add_familymember_table');
    });  
	$('#delFamilymemberBtn').bind('click', function(){   
      	$("#add_familymember_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_familymember_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addFamilymemberBtn" href="#">添加</a> <a id="delFamilymemberBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="familymember_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">关系</td>
				  <td align="left" bgcolor="#EEEEEE">成员姓名</td>
				  <td align="left" bgcolor="#EEEEEE">年龄</td>
				  <td align="left" bgcolor="#EEEEEE">职业</td>
				  <td align="left" bgcolor="#EEEEEE">现住地址</td>
	</tr>
	<tbody id="add_familymember_table">	
	<c:if test="${fn:length(familymemberList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="familymemberList[0].relationship" maxlength="10" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="familymemberList[0].membername" maxlength="10" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="familymemberList[0].age" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="familymemberList[0].occupation" maxlength="11" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="familymemberList[0].addressnow" maxlength="20" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(familymemberList)  > 0 }">
		<c:forEach items="${familymemberList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="familymemberList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="familymemberList[${stuts.index }].relationship" maxlength="10" value="${poVal.relationship }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="familymemberList[${stuts.index }].membername" maxlength="10" value="${poVal.membername }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="familymemberList[${stuts.index }].age" maxlength="" value="${poVal.age }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="familymemberList[${stuts.index }].occupation" maxlength="11" value="${poVal.occupation }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="familymemberList[${stuts.index }].addressnow" maxlength="20" value="${poVal.addressnow }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>