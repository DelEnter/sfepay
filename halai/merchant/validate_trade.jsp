<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
  <head>
    <title>待确认交易</title>

	<link rel="stylesheet" type="text/css" href="../css/head.css">
	
  </head>
  
  <body>
  	<s:action name="indexMenu" executeResult="true"/> 
    
   	<!--<center> -->
   		<s:form action="validateTradeDispose" name="form1" method="post" theme="simple">
        
            <div class="mainbody">
       <div class="search">
         <ul>
           <li>这里显示的所有处理中交易是有风险的交易，是支付网关预留给商户作出处理决定的交易。</li>
		   <li>如果您认为此笔交易没有风险，愿意继续交易，可点击"继续",完成此笔交易;如果您决定取消这笔交易,可点击"取消".</li>
		   <li>如果您在24小时内没对有风险的处理中的交易做出处理，本公司将视为"取消".</li>
         </ul>
       </div>
       <div class="clear">&nbsp;</div>
       <div><img src="images/division.gif" alt="" /></div>
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">待确认</li>
             <li class="lilistimg"> </li>
           </ul>
           <ul class="bottom"> 
             <li class="li_08">流水号</li>
             <li class="li_04">商户订单号</li>
             <li class="li_04">交易日期</li>
             <li class="li_12">交易金额</li>  
             <li class="li_12">交易币种</li> 
             <li class="li_06">通道</li> 
             <li class="li_12">支付状态</li> 
             <li class="li_07">风险度</li> 
             <li class="li_07">处理</li> 
           </ul>
         </div>
         
         <div class="listlist">
         
          <s:iterator id="it" value="info.result"> 
          
           <ul class="listlistbottom">
             <li class="lil_08"><s:property value="#it[0].orderNo"/></li>
             <li class="lil_04"><s:property value="#it[0].merchantOrderNo"/></li>
             <li class="lil_04"><s:property value="#it[0].tradeTime"/></li>
             <li class="lil_12"><s:property value="#it[0].tradeAmount"/></li>  
             <li class="lil_12"><s:property value="#it[2].moneykindname"/></li>   
             <li class="lil_06"><s:property value="#it[4].channelName"/></li>   
             <li class="lil_12"><s:property value="states.getStateName(#it[0].tradeState,1)" escape="flase"/></li>   
             <li class="lil_07"><s:property value="#it[1].maxmindValue"/></li>   
             <li class="lil_07">
               <a href="validateTradeDispose.action?trade.id=<s:property value="#it[0].id"/>&trade.tradeState=2"><img src="images/icon_004.gif" alt="确认" title="确认" /></a>
	   		   &nbsp;&nbsp;
               <a href="validateTradeDispose.action?trade.id=<s:property value="#it[0].id"/>&trade.tradeState=3"><img src="images/icon_005.gif" alt="取消" title="取消" /></a>
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
