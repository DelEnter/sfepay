<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Refunds Manager</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
</style>
</head>
<script language="JavaScript" type="text/JavaScript">

	function exportInfo(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("exportX");
		exportX.value="1";
		formX.submit();
	}
	
	function chaxun(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("exportX");
		exportX.value="";
		formX.submit();
	}
	
	function cancelRefund(refundid){
		if(confirm("Refund you sure you want to cancel?")){
			window.location="../merchanten/deleteRefundEn.action?refundId="+refundid;
		}
	}
	
</script>
<body>
<!--头部begin-->
<s:action name="indexMenuEn" executeResult="true" />
<!--头部end-->
<s:form name="formu" id="formu" action="refundManagerEn" method="post" theme="simple">
<s:hidden name="exportX"/>
    <div class="mainbody">
       <div class="search">
         <ul class="searchtext">
           <li class="name">Merchant OrderNo.</li>
           <li class="nameinput"><input type="input" name="merchantOrderNo" value="<s:property value='merchantOrderNo'/>"/></li>
         </ul>
         <ul class="searchtext">
           <li class="name">Order No.</li>
           <li class="nameinput"><input type="input" name="orderNo" value="<s:property value='orderNo'/>"/></li>
         </ul>
         <br class="clear" />
         <ul class="searchtext">
           <li class="name">Start Date</li>
           <li class="nameinput"><input type="text"  /></li>
         </ul>
         <ul class="searchtext">
           <li class="name">End Date</li>
           <li class="nameinput"><input type="text"  /></li>
         </ul>
        
         <ul class="searchselect">
           <li class="selectname">&nbsp;</li>
           <li class="selectinput">&nbsp;</li>
         </ul>
         <ul class="searchbutton">
           <li><a href="#" onclick="chaxun()"><img src="images/search.gif" alt="Search" /></a></li>
         </ul>
       </div>
       <div><img src="images/division.gif" alt="" /></div>
       
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">Refund Manager</li>
          <li class="lilistimg"><a href="#" onclick="exportInfo()"><img src="images/download.gif" alt="DownLoad" /></a></a></li>
           </ul>
           <ul class="bottom">
             <li class="li_01">Index</li>  
             <li class="li_08">Order</li>  
             <li class="li_04">Merchant Order No.</li> 
             <li class="li_04">Date</li> 
             <li class="li_04">Apply Date</li> 
             <li class="li_03">Amount</li>  
             <li class="li_03">Refund Amount</li>  
             <li class="li_03">Refund Status</li>  
             <li class="li_01">Operate</li>  
           </ul>
         </div>
         <div class="listlist">
         <s:iterator id="p" value="info.result" status="s">
           <ul class="listlistbottom">
             	<li class="lil_01"><s:property value="#s.index+1" /></li>  
                <li class="lil_08"><s:property value="#p[1].orderNo" /></li> 
				<li class="lil_04"><s:property value="#p[1].merchantOrderNo" /></li> 
				<li class="lil_04"><s:property value="#p[1].tradeTime" /></li> 
				<li class="lil_04"><s:property value="#p[0].applyDate" /></li> 
				<li class="lil_03"><s:property value="#p[1].tradeAmount" /></li> 
				<li class="lil_03"><s:property value="#p[0].refundAmount" /></li> 
				<li class="lil_03"><s:property value="states.getRefundDetailStateEn(#p[0].refundState)" /></li> 
				<li class="lil_01">
					<s:if test="#p[0].refundState<3">
						<a href="#" onclick="cancelRefund(<s:property value="#p[0].id" />)">Cancel</a>
					</s:if>
					<s:else>
						&nbsp;
					</s:else>
				</li> 
           </ul>
          </s:iterator> 
           <ul class="listlistpage">
             <li><pages:pages value="info" beanName="info" formName="forms(0)" /></li>
           </ul>
           
         </div>
       </div>
     </div>
   </div>
</s:form>
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->
   
   
   