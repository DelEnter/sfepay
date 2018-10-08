<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pages" uri="/xs-pages"%>
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>商户网址</title>
</head>
<!--头部begin-->
<s:action name="indexMenu" executeResult="true"/>  

    
    
<div class="mainbody">
	   <div class="list">
	   
	      <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">商户网址</li>
             <li class="lilistother"></li>
            
           </ul>
          <ul class="bottom">
               <li class="li_02">序号</li>  
		       <li class="li_02">网址</li>  
		       <li class="li_02">交易网址</li>    
             </ul>
         </div>
		 
		  <div class="listlist">
		    <s:iterator id="website" value="internationalWebchannelsList"  status="s">
               <ul class="listlistbottom">
               <li class="lil_02"><s:property value="#s.index+1" /></li>
				 <li class="lil_02"><s:property value="#website.website" /></li>
				 <li class="lil_02"><s:property value="#website.tradeWebsite" /></li>
               </ul>
		     </s:iterator>
		     
			  <br class="clear" />
	       <ul class="listlistpage">
		 <li></li>
	       </ul>
		   </div>
     </div>
   </div>
 </div>
    
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->	




