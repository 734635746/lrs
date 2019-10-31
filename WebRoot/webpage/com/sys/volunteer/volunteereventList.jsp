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
<div style="padding: 3px; height: 35px;width:auto;" class="datagrid-toolbar">
	<!-- <a id="addVolunteereventBtn" href="#">添加</a>  --> <a id="delVolunteereventBtn" href="#">删除</a>
</div>
<div style="width: 1000px;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="3" id="volunteerevent_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号 </td>
		<td align="center" bgcolor="#EEEEEE">操作 </td>
				  <td align="left" bgcolor="#EEEEEE">事件名字</td>
				  <td align="left" bgcolor="#EEEEEE">义工名字</td>
				  <td align="left" bgcolor="#EEEEEE">工作情况</td>
				  <td align="left" bgcolor="#EEEEEE">工作态度</td>
	</tr>
	<tbody id="add_volunteerevent_table">	
	<%-- <c:if test="${fn:length(volunteereventList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="volunteereventList[0].eventname" maxlength="36" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="volunteereventList[0].volunteername" maxlength="36" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="volunteereventList[0].workcondition" maxlength="" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="volunteereventList[0].atitude" maxlength="" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if> --%>
	<c:if test="${fn:length(volunteereventList)  > 0 }">
		<c:forEach items="${volunteereventList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="volunteereventList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="volunteereventList[${stuts.index }].eventname" maxlength="36" value="${poVal.eventname }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="volunteereventList[${stuts.index }].volunteername" maxlength="36" value="${poVal.volunteername }" type="text" style="width:120px;"></td>
				   <td align="left">
				   		<c:choose>
					       <c:when test="${poVal.workcondition==5}">
					              	<input name="volunteereventList[${stuts.index }].workcondition" maxlength="" value="${poVal.workcondition }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="很好" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.workcondition==4.5}">
					              	<input name="volunteereventList[${stuts.index }].workcondition" maxlength="" value="${poVal.workcondition }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="好" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.workcondition==4}">
					              	<input name="volunteereventList[${stuts.index }].workcondition" maxlength="" value="${poVal.workcondition }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="还好" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.workcondition==3.5}">
					              	<input name="volunteereventList[${stuts.index }].workcondition" maxlength="" value="${poVal.workcondition }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="一般" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.workcondition==3}">
					              	<input name="volunteereventList[${stuts.index }].workcondition" maxlength="" value="${poVal.workcondition }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="普通" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.workcondition==2.5}">
					              	<input name="volunteereventList[${stuts.index }].workcondition" maxlength="" value="${poVal.workcondition }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="还行" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.workcondition==2}">
					              	<input name="volunteereventList[${stuts.index }].workcondition" maxlength="" value="${poVal.workcondition }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="差" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.workcondition==1.5}">
					              	<input name="volunteereventList[${stuts.index }].workcondition" maxlength="" value="${poVal.workcondition }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="有点差" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.workcondition==1}">
					              	<input name="volunteereventList[${stuts.index }].workcondition" maxlength="" value="${poVal.workcondition }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="不行" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:otherwise>
					              <input name="volunteereventList[${stuts.index }].workcondition" maxlength="" value="${poVal.workcondition }" type="text" style="width:120px;">
					       </c:otherwise>
						</c:choose>
				   </td>
				   <td align="left">
				   		<c:choose>
					       <c:when test="${poVal.atitude==5}">
					              	<input name="volunteereventList[${stuts.index }].atitude" maxlength="" value="${poVal.atitude }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="很好" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.atitude==4.5}">
					              	<input name="volunteereventList[${stuts.index }].atitude" maxlength="" value="${poVal.atitude }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="好" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.atitude==4}">
					              	<input name="volunteereventList[${stuts.index }].atitude" maxlength="" value="${poVal.atitude }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="还好" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.atitude==3.5}">
					              	<input name="volunteereventList[${stuts.index }].atitude" maxlength="" value="${poVal.atitude }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="一般" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.atitude==3}">
					              	<input name="volunteereventList[${stuts.index }].atitude" maxlength="" value="${poVal.atitude }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="普通" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.atitude==2.5}">
					              	<input name="volunteereventList[${stuts.index }].atitude" maxlength="" value="${poVal.atitude }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="还行" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.atitude==2}">
					              	<input name="volunteereventList[${stuts.index }].atitude" maxlength="" value="${poVal.atitude }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="差" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.atitude==1.5}">
					              	<input name="volunteereventList[${stuts.index }].atitude" maxlength="" value="${poVal.atitude }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="有点差" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:when test="${poVal.atitude==1}">
					              	<input name="volunteereventList[${stuts.index }].atitude" maxlength="" value="${poVal.atitude }" type="hidden" style="width:120px;">
					              	<input name="" maxlength="" value="不行" type="text" style="width:120px;" readonly>
					       </c:when>
					       <c:otherwise>
					              <input name="volunteereventList[${stuts.index }].atitude" maxlength="" value="${poVal.atitude }" type="text" style="width:120px;">
					       </c:otherwise>
						</c:choose>
				   </td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>