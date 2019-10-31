<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addEducationBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delEducationBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addEducationBtn').bind('click', function(){   
 		 var tr =  $("#add_education_table_template tr").clone();
	 	 $("#add_education_table").append(tr);
	 	 resetTrNum('add_education_table');
    });  
	$('#delEducationBtn').bind('click', function(){   
      	$("#add_education_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_education_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addEducationBtn" href="#">添加</a> <a id="delEducationBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="education_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				  <td align="left" bgcolor="#EEEEEE">开始时间</td>
				  <td align="left" bgcolor="#EEEEEE">结束时间</td>
				  <td align="left" bgcolor="#EEEEEE">专业</td>
				  <td align="left" bgcolor="#EEEEEE">学位</td>
				  <td align="left" bgcolor="#EEEEEE">是否毕业</td>
				  <td align="left" bgcolor="#EEEEEE">证件名称</td>
	</tr>
	<tbody id="add_education_table">	
	<c:if test="${fn:length(educationList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input type="date" pattern="yyyy-MM-dd" name="educationList[0].starttime" maxlength="" style="width:120px;" ></td>
				  <td align="left"><input type="date" pattern="yyyy-MM-dd" name="educationList[0].endtime" maxlength="" style="width:120px;" ></td>
				  <td align="left"><input name="educationList[0].major" maxlength="20" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="educationList[0].degree" maxlength="10" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="educationList[0].graduation" maxlength="3" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="educationList[0].certificate" maxlength="20" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(educationList)  > 0 }">
		<c:forEach items="${educationList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="educationList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="educationList[${stuts.index }].starttime" maxlength="" value="${poVal.starttime }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="educationList[${stuts.index }].endtime" maxlength="" value="${poVal.endtime }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="educationList[${stuts.index }].major" maxlength="20" value="${poVal.major }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="educationList[${stuts.index }].degree" maxlength="10" value="${poVal.degree }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="educationList[${stuts.index }].graduation" maxlength="3" value="${poVal.graduation }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="educationList[${stuts.index }].certificate" maxlength="20" value="${poVal.certificate }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>