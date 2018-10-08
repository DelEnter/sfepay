<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">


<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
  <head>
   
    <title>保证金列表</title>
    	<%@ taglib prefix="pages" uri="/xs-pages"%>
        <%@ include file="../../util/calendar.jsp" %>
		<LINK href="../css/head.css" type=text/css rel=stylesheet>
  </head>
  <SCRIPT language=JavaScript>
    	function openDetail(merchId){
     
	 var str ='findHuakuanBailByMerchant?bailhua.batchno='+merchId ;

	 window.open (str) ;
	}
    	function openDetailMingXi(merchId){
     
	 var str ='findListBailbyMerchant?bailhua.batchno='+merchId ;

	 window.open (str) ;
	}	
  	</script>   
  <body>
<!--头部begin-->
<s:action name="indexMenu" executeResult="true" />
<!--头部end-->  


<s:form action="findNoHuakuanBailByMerchant" theme="simple" method="post" name="form1">
<div class="mainbody">

       <div class="search">
         <ul class="searchtext">
           <li class="name">批次号</li>
           <li class="nameinput3"><s:textfield name="bailhua.batchno" class="classes" /></li>
         </ul>
         <ul class="searchbutton2">
           <li><s:submit value="查询" class="input_button_01" /></li>
         </ul>
       </div>
       
       <div class="clear">&nbsp;</div>
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">保证金明细</li>
             <li class="lilistimg"></li>
           </ul>
           <ul class="bottom">
	       <li class="li_04">批次号</li> 
	       <li class="li_03">保证金额</li>   
	       <li class="li_10">结算人民币</li>   
	       <li class="li_09">开始交易时间</li>    
           <li class="li_09">结束交易时间</li>    
           <li class="li_09">划款日期</li>    
           <li class="li_01">查看</li>   
           <li class="li_01">明细</li>   
         </ul>
         </div>
         
         <div class="listlist">
        <s:iterator id="it" value="info.result">
    		<ul class="listlistbottom">
      		 <li class="lil_04"><s:property value="#it.batchno"/></li> 
     		 <li class="lil_03"><s:property value="#it.bailmoney"/></li>   
     		 <li class="lil_10"><s:property value="#it.balancemoney"/></li>   
      		 <li class="lil_09"><s:property value="#it.tradestarttime"/></li>    
     		 <li class="lil_09"><s:property value="#it.tradeendtime"/></li>    
     		 <li class="lil_09"><s:property value="#it.huakuantime"/></li>    
    		 <li class="lil_01"><a onclick="openDetail(<s:property value="#it.batchno"/>)" href="#">查看</a></li>    
             <li class="lil_01"><a onclick="openDetailMingXi(<s:property value="#it.batchno"/>)" href="#">明细</a></li>
   		 </ul>
	     </s:iterator>
            <ul class="listlistpage">
             <li><pages:pages value="info" beanName="info" formName="getElementById('form1')" /></li>
           </ul>
         </div>
         
       </div>
         
     </div>
   </div>
</s:form>

 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->	
  </body>
</html>
							