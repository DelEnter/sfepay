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
		document.getElementById('form1').action="merAddViewContent.action";
		document.getElementById('form1').submit();
	}
	function chakan(complainId){
		window.open('viewMerchantComDetails.action?complainId='+complainId) 
	}
</script>
<title>客人投诉意见管理</title>
	<!--头部begin-->
	<s:action name="indexMenu" executeResult="true" />
	<!--头部end-->
	<s:form name="formu" id="formu" action="merchantComplaintsQuery" method="post" theme="simple">
	<input type="hidden" name="isdownload"/>
	<div class="mainbody">
	       <div class="search">
	         <ul class="searchtext">
	           <li class="name2">流水号</li>
	           <li class="nameinput"><input type="input" name="orderNo" value="<s:property value='orderNo'/>"/></li>
             </ul>
             <ul class="searchtext">
	           <li class="name2">处理</li>
	           <li class="nameinput"><s:select name="protype" list="#@java.util.TreeMap@{' ':'所有','0':'未处理','1':'已处理'}"></s:select></li>
	         </ul>
             <ul class="searchtext">
               <li class="nameinput"><input type="submit" value="查询" class="input_button_01" ></li>
	         </ul>
	       </div>
	       <div><font color="red">Tips: 此平台为sfepay转发持卡人投诉信息告知商户sfepay不回复商户对持卡人邮件内容.</font></div>
	       <div><img src="images/division.gif" alt="" /></div>
	       
	       <div class="list">
	         <div class="listtitle">
	           <ul class="top">
	             <li class="lifonttitle">客服投诉查询</li>
	             <li class="lilistother"></li>
	          <li class="lilistimg"></li>
	           </ul>
	           <ul class="bottom">
	             <li class="li_01">序列</li>  
	             <li class="li_08">流水号</li>  
	             <li class="li_05">商户订单号</li> 
	             <li class="li_05">最新回复</li> 
	             <li class="li_05">处理情况</li> 
	             <li class="li_04">操作</li> 
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
						<font color="red">已处理</font>
					</s:if>
					<s:else>
						<font color="blue">未处理</font>
					</s:else>
				 </li>  
				 <li class="lil_04"><a href="#" onclick="chakan(<s:property value="#p.id"/>)" >详情</a></li>    
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










	
	
	
	
	
	
	
	
	
	
	
	
	