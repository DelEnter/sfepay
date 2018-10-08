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
function delWebSite(id){
	if(confirm("确认删除吗？")){
	window.location="../merchant/merchantDelWebSite?isWebSiteId="+id;
	}
}
function toMerchantAuditWebList(){
	window.location="../merchant/toMerchantAuditWebList.action";
}
</script>
<!--头部begin-->
<s:action name="indexMenu" executeResult="true"/>  

<form action="merchantAddWebSite" method="post">    
<div class="mainbody">

	   <div class="list">
	   
	      <div class="listtitle">
	      <ul class="top">
             <li class="lifonttitle"><a href="#" onclick="toMerchantAuditWebList()">网址审核列表</a></li>
             <li class="lilistimg2"><input type="button" value=""  onclick="addWebSite();" class="addWebSite" /></li>
             <li class="lilistimg2"><input type="button" value=""  onclick="toBatchWebUpload();" class="batchUploadWeb" /></li>
           </ul>
           
          <ul class="bottom">
               <li class="li_06">序号</li>  
		       <li class="li_02">返回网址</li>  
		       <li class="li_02">交易网址</li>
		       <li class="li_03">添加时间</li>
		       <li class="li_06">审核人</li>
		       <li class="li_03">审核状态</li>
		       <li class="li_02">审核备注</li>
		       <li class="li_06">操作</li>    
             </ul>
         </div>
		 
		  <div class="listlist">
		    <s:iterator id="isWebSite" value="isWebSite"  status="s">
               <ul class="listlistbottom">
               <li class="lil_06"><s:property value="#s.index+1" /></li>
				 <li class="lil_02"><s:property value="#isWebSite.website" /></li>
				 <li class="lil_02"><s:property value="#isWebSite.tradeWebsite" /></li>
				 <li class="lil_03"><s:property value="#isWebSite.createtime" /></li>
				 <li class="lil_06"><s:property value="#isWebSite.operator" /></li>
				 <s:if test="#isWebSite.isAudit==\"0\"">
				 <li class="lil_03">未审核</li>
				 </s:if>
				 <s:elseif test="#isWebSite.isAudit==\"2\"">
				 <li class="lil_03">拒绝</li>
				 </s:elseif>
				 <li class="lil_02"><s:property value="#isWebSite.remark" /></li>
				 <li class="lil_06"><a href="#" onclick="delWebSite(<s:property value="#isWebSite.id"/>)">删除</a></li>
               </ul>
		     </s:iterator>
		     
			  <br class="clear" />
	       <ul class="listlistpage">
		 <li></li>
	       </ul>
		   </div>
     </div>
   </div>
 </form>
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
	<!--尾部end-->	