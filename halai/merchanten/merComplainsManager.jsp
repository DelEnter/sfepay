<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ include file="../include/dialog.jsp"%>
<base target="_self">
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<script type="text/javascript">
	function onsubmita(){
		document.getElementById('form1').action="merAddViewContentEn.action";
		document.getElementById('form1').submit();
	}
	function chakan(complainId){
		window.showModalDialog('viewMerchantComDetailsEn.action?complainId='+complainId, window,'dialogHeight:325px;dialogWidth:650px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
	}
</script>
<title>Management of cardholder complaints</title>
	<!--头部begin-->
	<s:action name="indexMenuEn" executeResult="true" />
	<!--头部end-->
	<s:form name="formu" id="formu" action="merchantComplaintsQueryEn" method="post" theme="simple">
	<input type="hidden" name="isdownload"/>
	<div class="mainbody">
	       <div class="search">
	         <ul class="searchtext">
	           <li class="name2">Order No.</li>
	           <li class="nameinput"><input type="input" name="orderNo" value="<s:property value='orderNo'/>"/></li>
             </ul>
             <ul class="searchtext">
	           <li class="name2">Dispose</li>
	           <li class="nameinput"><s:select name="protype" list="#@java.util.TreeMap@{' ':'All','0':'NO','1':'Yes'}"></s:select></li>
	         </ul>
             <ul class="searchtext">
               <li class="nameinput"><input type="submit" value="Search" class="input_button_01" ></li>
	         </ul>
	       </div>
	       <div><img src="images/division.gif" alt="" /></div>
	       
	       <div class="list">
	         <div class="listtitle">
	           <ul class="top">
	             <li class="lifonttitle">Cardholder complaints</li>
	             <li class="lilistother"></li>
	          <li class="lilistimg"></li>
	           </ul>
	           <ul class="bottom">
	             <li class="li_01">Index</li>  
	             <li class="li_08">Order No.</li>  
	             <li class="li_05">Merchant Order No.</li> 
	             <li class="li_05">New</li> 
	             <li class="li_05">Dispose</li> 
	             <li class="li_05">Operate</li> 
	           </ul>
	         </div>
	         <div class="listlist">
	         <s:iterator id="p" value="info.result" status="s">
	           <ul class="listlistbottom">
	             <li class="lil_01"><s:property value="#s.index+1" /></li>  
	             <li class="lil_08"><s:property value="#p.orderNo" /></li>    
				 <li class="lil_05"><s:property value="#p.merchantOrderNo" /></li>    
				 <li class="lil_05"><s:property value="#p.lastDate"/></li>    
				 <li class="lil_05">
					 <s:if test="#p.processingResults==1">
						<font color="red">Yes</font>
					</s:if>
					<s:else>
						<font color="blue">No</font>
					</s:else>
				 </li>  
				 <li class="lil_05"><a href="#" onclick="chakan(<s:property value="#p.id"/>)" >Details</a></li>    
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










	
	
	
	
	
	
	
	
	
	
	
	
	