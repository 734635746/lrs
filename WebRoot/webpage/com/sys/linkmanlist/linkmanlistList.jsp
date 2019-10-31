<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<script type="text/javascript">
	$('#addLinkmanlistBtn').linkbutton({   
	    iconCls: 'icon-add'  
	});  
	$('#delLinkmanlistBtn').linkbutton({   
	    iconCls: 'icon-remove'  
	}); 
	$('#addLinkmanlistBtn').bind('click', function(){   
 		 var tr =  $("#add_linkmanlist_table_template tr").clone();
	 	 $("#add_linkmanlist_table").append(tr);
	 	 resetTrNum('add_linkmanlist_table');
    });  
	$('#delLinkmanlistBtn').bind('click', function(){   
      	$("#add_linkmanlist_table").find("input:checked").parent().parent().remove();   
        resetTrNum('add_linkmanlist_table'); 
    }); 
    $(document).ready(function(){
    	$(".datagrid-toolbar").parent().css("width","auto");
    });
</script>
<div style="padding: 3px; height: 25px;width:auto;" class="datagrid-toolbar">
	<a id="addLinkmanlistBtn" href="#">添加</a> <a id="delLinkmanlistBtn" href="#">删除</a> 
</div>
<div style="width: auto;height: 300px;overflow-y:auto;overflow-x:scroll;">
<table border="0" cellpadding="2" cellspacing="0" id="linkmanlist_table">
	<tr bgcolor="#E6E6E6">
		<td align="center" bgcolor="#EEEEEE">序号</td>
		<td align="center" bgcolor="#EEEEEE">操作</td>
				 <td align="left" bgcolor="#EEEEEE">联系人</td>
				  <td align="left" bgcolor="#EEEEEE">QQ</td>
				  <td align="left" bgcolor="#EEEEEE">电子邮件</td>
				  <td align="left" bgcolor="#EEEEEE">手机号</td>
				  <td align="left" bgcolor="#EEEEEE">固话</td>
				  <td align="left" bgcolor="#EEEEEE">微信号</td>
				  <td align="left" bgcolor="#EEEEEE">地址</td>
	</tr>
	<tbody id="add_linkmanlist_table">	
	<c:if test="${fn:length(linkmanlistList)  <= 0 }">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">1</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck"/></td>
				  <td align="left"><input name="linkmanlistList[0].linkman" maxlength="16" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="linkmanlistList[0].qq" maxlength="16" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="linkmanlistList[0].email" maxlength="36" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="linkmanlistList[0].mobilephone" maxlength="11" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="linkmanlistList[0].homephone" maxlength="14" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="linkmanlistList[0].wechat" maxlength="36" type="text" style="width:120px;" ></td>
				  <td align="left"><input name="linkmanlistList[0].address" maxlength="36" type="text" style="width:120px;" ></td>
   			</tr>
	</c:if>
	<c:if test="${fn:length(linkmanlistList)  > 0 }">
		<c:forEach items="${linkmanlistList}" var="poVal" varStatus="stuts">
			<tr>
				<td align="center"><div style="width: 25px;" name="xh">${stuts.index+1 }</div></td>
				<td align="center"><input style="width:20px;"  type="checkbox" name="ck" /></td>
				<input name="linkmanlistList[${stuts.index }].id"  value="${poVal.id }" type="hidden" >
				   <td align="left"><input name="linkmanlistList[${stuts.index }].linkman" maxlength="16" value="${poVal.linkman }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="linkmanlistList[${stuts.index }].qq" maxlength="16" value="${poVal.qq }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="linkmanlistList[${stuts.index }].email" maxlength="36" value="${poVal.email }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="linkmanlistList[${stuts.index }].mobilephone" maxlength="11" value="${poVal.mobilephone }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="linkmanlistList[${stuts.index }].homephone" maxlength="14" value="${poVal.homephone }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="linkmanlistList[${stuts.index }].wechat" maxlength="36" value="${poVal.wechat }" type="text" style="width:120px;"></td>
				   <td align="left"><input name="linkmanlistList[${stuts.index }].address" maxlength="36" value="${poVal.address }" type="text" style="width:120px;"></td>
   			</tr>
		</c:forEach>
	</c:if>	
	</tbody>
</table>
</div>