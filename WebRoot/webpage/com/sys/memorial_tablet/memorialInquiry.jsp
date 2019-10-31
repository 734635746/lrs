<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<html>
 <head>
<%--   <title>牌位查询</title>
  <t:base type="jquery,easyui,tools,DatePicker"></t:base> --%>
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/table2.css">
  <link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/resources/css/button.css">

<!-- <script language="JavaScript" type="text/javascript">
        $(function() {
            $('#condition').change(function() {
                if (this.value == 'dan') {
                    $('#danInfo').show();
                    $('#content').hide();
                    $('#button1').hide();
                } else {
                    $('#danInfo').hide();
                    $('#content').show();
                    $('#button1').show();
                }
            });
            
        });
    </script> -->
 </head>
 <body>
 <body>

    <h4>牌位管理</h4><br>
     <form class="form-inline" >
			<select name="select" id="searchselect" class="form-control">
				 <option value ="registertime">登记时间</option>
		  		 <option value ="dan">段位</option>
		  		 <option value="name">姓名</option>
		  	     <option value="linkman">联系人名字</option>
		  		 <option value="mobilephone">手机号</option>
			</select> <input type="text" id="searchtext" name="searchvalue"
				placeholder="请输入搜索内容..." value="${searchvalue}">
				<input type="hidden" id="path" value="memorial_tabletController.do?conditionQuery" />
			<button type="button" class="btn" id="searchsubmit">搜索</button>
	</form>
     <br>
		<div id="show">
			
		</div>
  </body>
  <script src="${pageContext.request.contextPath}/resources/js/jquery.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js"></script>
  <script src="${pageContext.request.contextPath}/resources/js/page.js"></script>
  <script type="text/javascript">
		$(document).ready(function(){
			$("#searchsubmit").click();
		});
  </script>
<!-- <div>
  <div region="center" style="padding:1px;">
	  <select name="condition" id="condition">
		  <option value ="registertime">登记时间</option>
		  <option value ="dan">段位</option>
		  <option value="name">姓名</option>
		  <option value="linkman">联系人名字</option>
		  <option value="mobilephone">手机号</option>
	 </select> &nbsp;&nbsp;
	 <input type="text" id="content" style="display:visible;">&nbsp;&nbsp;&nbsp;&nbsp;
	 <input type="button" id="button1" class="button" value="查询" style="display:visible;">
  </div>
  <br>
  <div id = "danInfo" style="display:none;">
  		段位 :　<input type="text" id="dan" />
  		行号 : <input type="text" id="row" />
  		位号 : <input type="text" id="dan" />&nbsp;&nbsp;&nbsp;&nbsp;
  		<input type="button" id="button2" class="button" value="查询" style="display:visible;">
  </div>
 </div> -->
 <br><br><br>
</body>
