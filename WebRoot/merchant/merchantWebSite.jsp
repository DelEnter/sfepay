<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pages" uri="/xs-pages"%>
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>商户网址</title>
</head>
<script language="JavaScript" type="text/JavaScript">
function addWebSite(){
	openWindow('../merchant/toMerchantAddWebSite.action','12');
}
function toBatchWebUpload(){
	openWindow('../merchant/toBatchWebUpload.action','12');
}
function modifyWebSite(id){
	openWindow('../merchant/toUpdateWebSite.action?webSiteId='+id,'12');
}
function delWebSite(id){
	if(confirm("确认删除吗？")){
	window.location="../merchant/delWebSite.action?webSiteId="+id;
	}
}
function toMerchantAuditWebList(){
	window.location="../merchant/toMerchantAuditWebList.action";
}
function merchantWebSites(){
	window.location="../merchant/merchantWebSites.action";
}
</script>
<!--头部begin-->
<s:action name="indexMenu" executeResult="true"/>

    
    
<div class="mainbody">
	   <div class="list">
	   
	      <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle"><a href="#" onclick="merchantWebSites()">商户网址</a></li>
             <li class="lifonttitle"><a href="#" onclick="toMerchantAuditWebList()">网址审核列表</a></li>
             <li class="lilistimg2"><input type="button" value=""  onclick="addWebSite();" class="addWebSite" /></li>
             <li class="lilistimg2"><input type="button" value=""  onclick="toBatchWebUpload();" class="batchUploadWeb" /></li>
            
           </ul>
          <ul class="bottom">
               <li class="li_02">序号</li>  
		       <li class="li_02">网址</li>  
		       <li class="li_02">交易网址</li>
		       <li class="li_02">操作</li>    
             </ul>
         </div>
		 
		  <div class="listlist">
		    <s:iterator id="website" value="internationalWebchannelsList"  status="s">
               <ul class="listlistbottom">
               <li class="lil_02"><s:property value="#s.index+1" /></li>
				 <li class="lil_02"><s:property value="#website.website" /></li>
				 <li class="lil_02"><s:property value="#website.tradeWebsite" /></li>
				 <li class="lil_02"><a href="#" onclick="modifyWebSite(<s:property value="#website.id" />)">修改</a>&nbsp;&nbsp;&nbsp;<a href="#" onclick="delWebSite(<s:property value="#website.id" />)">删除</a></li>
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




