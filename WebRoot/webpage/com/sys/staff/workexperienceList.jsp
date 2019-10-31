<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addWorkexperienceBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delWorkexperienceBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addWorkexperienceBtn').bind('click', function(){   
 		 var tr =  $("#add_workexperience_table_template tr").clone();
	 	 $("#add_workexperience_table").append(tr);
	 	 resetTrNum('add_workexperience_table');
    });  
	$('#delWorkexperienceBtn').bind('click', function(){   
      	$("#add_workexperience_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_workexperience_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addWorkexperienceBtn" href="#">添加</a> <a id="delWorkexperienceBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="workexperience_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">曾工作单位</td>
				  <td align="left" bgcolor="#EEEEEE">职位</td>
				  <td align="left" bgcolor="#EEEEEE">到职年月</td>
				  <td align="left" bgcolor="#EEEEEE">离职年月</td>
				  <td align="left" bgcolor="#EEEEEE">离职原因</td>
	</tr>
	<tbody id="add_workexperience_table">	
	<c:if test="${fn:length(workexperienceList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="workexperienceList[0].workunit" maxlength="30" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="workexperienceList[0].position" maxlength="30" type="text" style="width:120px;" ></td>
				  <td align="left"><input type="date" pattern="yyyy-MM-dd" name="workexperienceList[0].arrivaldate" maxlength=" " style="width:120px;" ></td>
				  <td align="left"><input type="date" pattern="yyyy-MM-dd" name="workexperienceList[0].departuredate" maxlength=" " style="width:120px;" ></td>
				  <td align="left"><input name="workexperienceList[0].departurereason" maxlength="255" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(workexperienceList)  > 0 }">
		<c:forEach items="${workexperienceList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="workexperienceList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="workexperienceList[${stuts.index }].workunit" maxlength="30" value="${poVal.workunit }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="workexperienceList[${stuts.index }].position" maxlength="30" value="${poVal.position }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="workexperienceList[${stuts.index }].arrivaldate" maxlength="" value="${poVal.arrivaldate }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="workexperienceList[${stuts.index }].departuredate" maxlength="" value="${poVal.departuredate }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="workexperienceList[${stuts.index }].departurereason" maxlength="255" value="${poVal.departurereason }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>