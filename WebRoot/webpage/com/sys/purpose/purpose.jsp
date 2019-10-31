<%-- <%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>捐款用途</title>
<t:base type="jquery,easyui,tools"></t:base>
</head>
<body style="overflow-y: hidden" scroll="no">
<t:formvalid formid="formobj" dialog="true"  layout="div"  action="purposeController.do?save">
	<fieldset class="step" >
	<div class="form" align="center"><label class="Validform_label"> 捐款用途: </label> <input name="purpose" class="inputxt" value="" validType="purpose,purpose,id" datatype="s2-10">
	<span class="Validform_checktip">捐款用途名称在2~10位字符</span></div>
	</fieldset>
</t:formvalid>
</body>
</html> --%>

<%@ page language="java" import="java.util.*" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<!DOCTYPE html>
<html>
<head>
<title>捐款用途</title>
<t:base type="jquery,easyui,tools,DatePicker"></t:base>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/button1.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/button2.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/button.css">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/resources/css/table.css">

</head>
<body style="overflow-y: hidden" scroll="no">
	<t:formvalid formid="formobj" dialog="true" usePlugin="password"
		layout="table" action="doritualinfoController.do?savePurpose">
		<table cellpadding="0" cellspacing="1" class="formtable">
			<tr>
				<td align="right"><label class="Validform_label"> 捐款用途:
				</label>
				</td>
				<td class="value"><input id="purpose" name="purpose"
					class="inputxt" validType="purpose,purpose" datatype="s2-10">
					<span class="Validform_checktip">捐款用途名称在2~10位字符</span></td>
			</tr>
		</table>
	</t:formvalid>
</body>
</html>