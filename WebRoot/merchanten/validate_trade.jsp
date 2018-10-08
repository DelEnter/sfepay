<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>Transaction to be confirmed</title>

	<link rel="stylesheet" type="text/css" href="../css/head.css">
	
  </head>
  
  <body>
  	<s:action name="indexMenuEn" executeResult="true"/> 
    
   	<!--<center> -->
   		<s:form action="validateTradeDisposeEn" name="form1" method="post" theme="simple">
        
            <div class="mainbody">
       <div class="search">
         <ul>
           <li>All of the pending transactions showed here are risk. They're left by payment gateway for merchant 
           to make decision to continue these transactions or not.If you though this transaction is safe, 
           and agree to continue, you can click"Continue" to go on, if you decide to cancel this transaction, 
           you can click " Cancel".If you do not do any actions on these risk pending transactions,
           we will regard them as "Cancel".</li>
         </ul>
       </div>
       <div class="clear">&nbsp;</div>
       <div><img src="images/division.gif" alt="" /></div>
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">To be confirmed</li>
             <li class="lilistimg"> </li>
           </ul>
           <ul class="bottom"> 
             <li class="li_08">Ecpss Order NO</li>
             <li class="li_04">Merchant Order NO</li>
             <li class="li_04">Transaction Date</li>
             <li class="li_12">Transaction Amount</li>  
             <li class="li_12">Trade Currency</li> 
             <li class="li_06">Channels</li> 
             <li class="li_12">Payment Status</li> 
             <li class="li_07">Risk</li> 
             <li class="li_07">validate</li> 
           </ul>
         </div>
         
         <div class="listlist">
         
          <s:iterator id="it" value="info.result"> 
          
           <ul class="listlistbottom">
             
             <li class="lil_08"><s:property value="#it[0].orderNo"/></li>
             <li class="lil_04"><s:property value="#it[0].merchantOrderNo"/></li>
             <li class="lil_04"><s:property value="#it[0].tradeTime"/></li>
             <li class="lil_12"><s:property value="#it[0].tradeAmount"/></li>  
             <li class="lil_12"><s:property value="states.getCurrencyTypeByNo(#it[2].moneykindno)"/></li>    
             <li class="lil_06"><s:property value="#it[4].channelName"/></li>   
             <li class="lil_12"><s:property value="states.getStateNameEn(#it[0].tradeState,1)" escape="flase"/></li>   
             <li class="lil_07"><s:property value="#it[1].maxmindValue"/></li>   
             <li class="lil_07">
               <a href="validateTradeDisposeEn.action?trade.id=<s:property value="#it[0].id"/>&trade.tradeState=2"><img src="images/icon_004.gif" alt="确认" title="确认" /></a>
	   		   &nbsp;&nbsp;
               <a href="validateTradeDisposeEn.action?trade.id=<s:property value="#it[0].id"/>&trade.tradeState=3"><img src="images/icon_005.gif" alt="取消" title="取消" /></a>
             </li>      
           </ul>
           
          </s:iterator>
         
           
         </div>
       </div>
     </div>
     </s:form>	
   	<!--尾部begin-->
	<%@ include file="foot.jsp"%>
	<script language="javascript">	
		var temflag='<s:property value='flag'/>';
		if(temflag==1){
			alert("确认继续交易!");
		}else if(temflag==2){
			alert("取消交易!");
		}else if(temflag==3){
			alert("系统出现异常!");
		}	
	</script>	
  </body>
</html>
