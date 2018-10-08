<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>取消待处理交易</title>

	<link rel="stylesheet" type="text/css" href="../css/head.css">
	
  </head>
  <script language="JavaScript" type="text/JavaScript">
	  function chaxun(){
			var formX = document.getElementById("formu");
			formX.submit();
	  }
  </script>
  <body>
  	<s:action name="indexMenu" executeResult="true"/> 
    
   	<!--<center> -->
   		<s:form action="processTradeQuery" name="form1" method="post" theme="simple">
        
            <div class="mainbody">
       <div class="search">
         <ul>
           <li>可以根据Sfepay流水号查询出一笔待处理交易进行取消处理。</li>
		   <li>如果您认为不需要处理此笔交易，可点击"取消",取消此笔交易.</li>
		   <li>Tips: 每天每个商户号只能取消一笔待处理交易.</li>
         </ul>
         <ul>
	         <li><font color="red"><s:property value="messageAction"/></font></li>
	         <li></li>
	         <li></li>
         </ul>
         
         <ul class="searchtext">
         	<li class="name"><input type="text" size="50" name="orderNo" value="<s:property value="orderNo"/>" /></li>
         	<li class="nameinput"></li>
         </ul>
         <ul class="searchbutton">
         <li>
         <div id="submitse" >
         	<input type="image" src="images/search.gif" onclick="chaxun()" /></a>
		   	</div>
		   	<div id="process" style="VISIBILITY: hidden;" >
		   		<img src="https://security.sslepay.com/jsp/ibank/images/loading1.gif" alt="" />
			</div>
         
         </li>
       </div>
       <div class="clear">&nbsp;</div>
       <div><img src="images/division.gif" alt="" /></div>
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">待处理</li>
             <li class="lilistimg"> </li>
           </ul>
           <ul class="bottom"> 
             <li class="li_08">流水号</li>
             <li class="li_04">商户订单号</li>
             <li class="li_04">交易日期</li>
             <li class="li_12">交易金额</li>  
             <li class="li_12">交易币种</li> 
             <li class="li_12">支付状态</li> 
             <li class="li_07">处理</li> 
           </ul>
         </div>
         
         <div class="listlist">
         
          <s:iterator id="it" value="tradeList"> 
          
           <ul class="listlistbottom">
             <li class="lil_08"><s:property value="#it.orderNo"/></li>
             <li class="lil_04"><s:property value="#it.merchantOrderNo"/></li>
             <li class="lil_04"><s:property value="#it.tradeTime"/></li>
             <li class="lil_12"><s:property value="#it.tradeAmount"/></li>  
             <li class="lil_12"><s:property value="states.getCurrencyTypeByNo(#it.moneyType)"/></li>   
             <li class="lil_12"><s:property value="states.getStateName(#it.tradeState,1)" escape="flase"/></li>   
             <li class="lil_07">
               <a href="cancelTrade.action?orderNo=<s:property value="#it.orderNo"/>"><img src="images/icon_005.gif" alt="取消" title="取消" /></a>
             </li>      
           </ul>
           
          </s:iterator>
         
           
         </div>
       </div>
     </div>
        </s:form>
        </div>
   	<!--尾部begin-->
	<%@ include file="foot.jsp"%>
  </body>
</html>
