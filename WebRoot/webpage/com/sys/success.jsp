<%@ page language="java" import="java.util.*, org.jeecgframework.web.system.pojo.base.TSUser, org.jeecgframework.core.util.ResourceUtil" pageEncoding="UTF-8"%>
<%@include file="/context/mytags.jsp"%>
<style  media="print"  type="text/css">
  .Noprint
  { display: none; }
</style>
<%
TSUser user = ResourceUtil.getSessionUserName();
String userName=user.getUserName();
%>			
<html>
  <style type="text/css">
<!--
.STYLE4 {font-size: 9pt}
-->
  </style>
  <head>
    <base href="<%=basePath%>">
    <t:base type="jquery,easyui,tools,DatePicker"></t:base>
    <title></title>
    <style>
	.r{position:absolute; top:325px;left:345px;}
	.info{text-align:center;}
	.STYLE1 {font-family: "宋体"}
.STYLE3 {font-size: 10}
    #Layer1 {
	position:absolute;
	width:167px;
	height:72px;
	z-index:1;
	left: 494px;
	top: 180px;
	visibility: visible;
}
    </style>
	<script language="javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.8.3.js"></script>	
	<script language="javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery-1.8.3.min.js"></script>	
	<script language="javascript" src="${pageContext.request.contextPath}/plug-in/jquery/jquery.jqprint.js"></script>	
 	<script type="text/javascript">
	function printInfo(){
		$('#forinfo').jqprint();
	}
	</script>
  </head>
  
  <body>
  <div class="info" id="forinfo">
   <table cellspacing="0" cellpadding="0" style="border-collapse:collapse; float:left; margin:0pt 9pt; width:528.35pt">
    <tbody>
     <tr style="height:225.6pt">
      <td style="padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:6.2pt"><p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:Calibri; font-size:10.5pt">&nbsp;</span></p></td>
      <td style="padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:522.2pt">
       <table width="806" cellpadding="0" cellspacing="0" style="border-collapse:collapse; margin-left:0pt; width:495.8pt">
        <tbody>
         <tr style="height:27.4pt">
          <td colspan="3" style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:514.75pt"><p align="left" style="margin:0pt; orphans:0; widows:0">
          <span style="font-family:Calibri; font-size:12pt">LRNo:</span>  
		  <span style="color:#ff0000; font-family:Calibri; font-size:12pt"> ${returnRe.no}</span>
		  <span style="font-family:黑体; font-size:18pt; font-weight:bold">广 州 市 六 榕 寺 收 据&nbsp; </span>
	      <span style="font-family:宋  体; font-size:10.5pt">&nbsp;开票日期：</span>
		  <span style="color:#ff0000; font-family:Calibri; font-size:10.5pt">${returnRe.registertime}</span>
		  
		  </p>		  </td>
         </tr>
        
         <tr>
          <td colspan="3" style="border-left-color:#000000; border-left-style:solid; 
         	 border-left-width:0.75pt; border-right-color:#000000;
            	border-right-style:solid; border-right-width:0.75pt;height:76.10pt;
            	border-top-color:#000000; border-top-style:solid;
              border-top-width:0.75pt; padding-left:5.03pt;
              padding-right:5.03pt; vertical-align:top; 
              width:514.75pt">
          <p style="margin:0pt; orphans:0; text-align:justify; widows:0">
			  <span style="font-family:宋体; font-size:10.5pt; font-weight:bold;">摘要:</span>
			  <span style="font-family:宋体; font-size:14.5pt;">${returnRe.summary}</span></p>
          <div id="Layer1"><span style="margin:0pt; orphans:0; text-align:justify; widows:0"><img src="${pageContext.request.contextPath}/resources/tmskz.png" width="188" height="89"></span></div></td>
         </tr>
         <tr style="height:18.85pt">
          <td colspan="3" style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:top; width:514.75pt"><p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:宋体; font-size:10.5pt">
          	金额</span><span style="font-family:Calibri; font-size:10.5pt">
          	
          </span><span style="font-family:宋体; font-size:10.5pt">（人民币大写）：</span>
          <span style="font-family:宋体; font-size:10.5pt">${bigMoney}</span>
          <span style="font-family:宋体; font-size:10.5pt">&nbsp;&nbsp;&nbsp;&nbsp;
                                  金额</span>
		  <span style="font-family:宋体; font-size:10.5pt">(人民币小写： </span><span style="font-family:宋体;  font-size:10.5pt">${smallMoney})</span>
		  </p></td>
         </tr>
         <tr style="height:18.4pt">
          <td colspan="3" style="border-bottom-color:#000000; 
          border-bottom-style:solid; border-bottom-width:0.75pt; 
          border-left-color:#000000; border-left-style:solid; 
          border-left-width:0.75pt; border-right-color:#000000; 
          border-right-style:solid; border-right-width:0.75pt; 
          border-top-color:#000000; border-top-style:solid; 
          border-top-width:0.75pt; padding-left:5.03pt; 
          padding-right:5.03pt; vertical-align:top; width:514.75pt">
          
          <p style="font-size:9pt; line-height:150%; margin:0pt; orphans:0; text-align:justify; widows:0">
          
          	<span style="font-family:宋体; font-size:9pt">&nbsp;</span>
          	
          	<span style="font-family:宋体; font-size:9pt">拈香</span>
	          	<c:if test="${returnRe.size=='拈香'}">
	          		<span style="font-family:宋体; font-size:9pt">█&nbsp;</span>				</c:if>
				<c:if test="${returnRe.size!='拈香'}">
	          		<span style="font-family:宋体; font-size:9pt">□&nbsp;</span>				</c:if>
          	<span style="font-family:宋体; font-size:9pt">大&nbsp;牌</span>
	          	<c:if test="${returnRe.size=='大'}">
	          		<span style="font-family:宋体; font-size:9pt">█&nbsp;</span>				</c:if>
	          	<c:if test="${returnRe.size!='大'}">
	          		<span style="font-family:宋体; font-size:9pt">□&nbsp;</span>				</c:if>
          	<span style="font-family:宋体; font-size:9pt">小&nbsp;牌</span>
	          	<c:if test="${returnRe.size=='小'}">
	          		<span style="font-family:宋体; font-size:9pt">█&nbsp;</span>				</c:if>
	          	<c:if test="${returnRe.size!='小'}">
	          		<span style="font-family:宋体; font-size:9pt">□&nbsp;</span>				</c:if>
          	<span style="font-family:宋体; font-size:9pt">其他</span>
          		<c:if test="${returnRe.size=='其他'}">
	          		<span style="font-family:宋体; font-size:9pt">█&nbsp;</span>				</c:if>
	          	<c:if test="${returnRe.size!='其他'}">
	          		<span style="font-family:宋体; font-size:9pt">□&nbsp;</span>				</c:if>
          </p></td>
         </tr>
         <tr style="height:24.6pt">
          <td colspan="3" style="border-bottom-color:#000000; border-bottom-style:solid; 
          border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; 
          border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; 
          border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; 
          border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; 
          vertical-align:top; width:514.75pt">
          
          <p style="font-size:9pt; line-height:150%; margin:0pt; orphans:0; text-align:justify; widows:0">
          <span style="font-family:宋体; font-size:9pt">&nbsp;</span>
          <span style="font-family:宋体; font-size:9pt">现金</span>
          	    <c:if test="${returnRe.payway =='现金'}">
	          		<span style="font-family:宋体; font-size:9pt">█&nbsp;</span>				</c:if>
				<c:if test="${returnRe.payway !='现金'}">
	          		<span style="font-family:宋体; font-size:9pt">□&nbsp;</span>				</c:if>
          <span style="font-family:宋体; font-size:9pt">银行卡</span>
         	 	<c:if test="${returnRe.payway =='刷卡'}">
	          		<span style="font-family:宋体; font-size:9pt">█&nbsp;</span>				</c:if>
				<c:if test="${returnRe.payway !='刷卡'}">
	          		<span style="font-family:宋体; font-size:9pt">□&nbsp;</span>				</c:if>
          <span style="font-family:宋体; font-size:9pt">支付宝</span>
				<c:if test="${returnRe.payway =='支付宝'}">
	          		<span style="font-family:宋体; font-size:9pt">█&nbsp;</span>				</c:if>
				<c:if test="${returnRe.payway !='支付宝'}">
	          		<span style="font-family:宋体; font-size:9pt">□&nbsp;</span>				</c:if>
          <span style="font-family:宋体; font-size:9pt">微信</span>
				<c:if test="${returnRe.payway =='微信'}">
	          		<span style="font-family:宋体; font-size:9pt">█&nbsp;</span>				</c:if>
				<c:if test="${returnRe.payway !='微信'}">
	          		<span style="font-family:宋体; font-size:9pt">□&nbsp;</span>				</c:if>
		 <span style="font-family:宋体; font-size:9pt">其他</span>
				<c:if test="${returnRe.payway =='其他'}">
	          		<span style="font-family:宋体; font-size:9pt">█&nbsp;</span>				</c:if>
				<c:if test="${returnRe.payway !='其他'}">
	          		<span style="font-family:宋体; font-size:9pt">□&nbsp;</span>				</c:if>		
          </p></td>
         </tr>
         
         <tr style="height:18.85pt">
          <td width="55" style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:top; width:41.6pt">
          <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:宋体; font-size:10.5pt">备注：</span></p></td>
          <td colspan="2" style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:top; width:462.35pt"><p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="color:#ff0000; font-family:Calibri; font-size:10.5pt">${returnRe.remark}</span></p></td>
         </tr>
         <tr style="height:11.95pt">
          <td colspan="3" style="border-bottom-color:#000000; border-bottom-style:solid; border-bottom-width:0.75pt; border-left-color:#000000; border-left-style:solid; border-left-width:0.75pt; border-right-color:#000000; border-right-style:solid; border-right-width:0.75pt; border-top-color:#000000; border-top-style:solid; border-top-width:0.75pt; padding-left:5.03pt; padding-right:5.03pt; vertical-align:top; width:514.75pt">
          	<p style="margin:0pt; orphans:0; text-align:justify; widows:0">
	          <span style="font-family:宋体; font-size:10.5pt">收款单位盖章：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;  会计：</span>
	          <span style="font-family:宋体; font-size:10.5pt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
	          <span style="font-family:宋体; font-size:10.5pt">出纳：</span>
	          <span style="font-family:宋体; font-size:10.5pt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </span>
	          <span style="font-family:宋体; font-size:10.5pt">经手人：<%=userName%></span>          	</p>          </td>
         </tr>
         <tr style="height:11.35pt">
      <td style="padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:6.2pt"><p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:Calibri; font-size:10.5pt">&nbsp;</span></p></td>
      <td width="696" style="padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:522.2pt">
      <p style="margin:0pt; orphans:0; text-align:center; widows:0">
      <span style="font-family:华文隶书; font-size:7.5pt">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="STYLE1">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<span class="STYLE3">&nbsp;&nbsp; 
      	地址：广州市越秀区六榕路87号&nbsp;&nbsp;&nbsp; 020-83388095</span></span><span class="STYLE3"></p></td>
      <td width="53" style="padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:17.55pt"><p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:华文隶书; font-size:10.5pt">&nbsp;</span></p></td>
     </tr>
        </tbody>
       </table>
       <p style="margin:0pt; orphans:0; text-align:justify; widows:0"></p></td>
      <td style="padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:17.55pt">
      
      <p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:Calibri; font-size:10.5pt">&nbsp;</span>      </p>
      <p class="STYLE4" style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:宋体">第一联</span></p>
      <p align="justify" class="STYLE4" style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:宋体">存根</span></p>
      <p align="justify" class="STYLE4" style="margin:0pt; orphans:0; text-align:justify; widows:0">&nbsp;</p>
      <p align="justify" class="STYLE4 STYLE1" style="margin:0pt; orphans:0; text-align:justify; widows:0">第二联</p>
      <p align="justify" class="STYLE4 STYLE1" style="margin:0pt; orphans:0; text-align:justify; widows:0">顾客</p>
      <p align="justify" class="STYLE4 STYLE1" style="margin:0pt; orphans:0; text-align:justify; widows:0">&nbsp;</p>
      <p align="justify" class="STYLE4 STYLE1" style="margin:0pt; orphans:0; text-align:justify; widows:0">第三联</p>
      <p align="justify" class="STYLE4 STYLE1" style="margin:0pt; orphans:0; text-align:justify; widows:0">财务</p></td>
      	  <td style="padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:17.55pt">
		      <p style="margin:0pt; orphans:0; text-align:justify; widows:0">
		      <span style="font-family:Calibri; font-size:10.5pt">&nbsp;</span></p>
		      <p style="margin:0pt; orphans:0; text-align:justify; widows:0">
		      <span style="font-family:Calibri; font-size:10.5pt">&nbsp;</span></p>
		      <p style="margin:0pt; orphans:0; text-align:justify; widows:0">
		      <span style="font-family:宋体; font-size:10.5pt">&nbsp;</span></p>
		      <p style="margin:0pt; orphans:0; text-align:justify; widows:0">
		      <span style="font-family:Calibri; font-size:10.5pt">&nbsp;</span></p>
		      <p style="margin:0pt; orphans:0; text-align:justify; widows:0">
		      <span style="font-family:宋体; font-size:10.5pt">&nbsp;</span></p>
	      </td>
     </tr>
     
     <tr style="height:3.7pt">
      <td style="padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:6.2pt"><p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:Calibri; font-size:5pt">&nbsp;</span></p></td>
      <td style="padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:522.2pt"><p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:Calibri; font-size:5pt">&nbsp;</span></p></td>
      <td style="padding-left:5.4pt; padding-right:5.4pt; vertical-align:top; width:17.55pt"><p style="margin:0pt; orphans:0; text-align:justify; widows:0"><span style="font-family:Calibri; font-size:5pt">&nbsp;</span></p></td>
     </tr>
    </tbody>
   </table>
  
  </div>
    <div class="r">
       
		<input name="print" id="print" type="button" class="Noprint" value="打印" onClick="window.print()">
		
    </div>
<!-- <button id="btn_sub"  style=" margin-top:460px; margin-left:320px;"  onclick="printOrder()">打印</button> -->
</body>
  </body>
</html>
