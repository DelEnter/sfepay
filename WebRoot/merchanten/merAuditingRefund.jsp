<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>Confirm Refund</title>
</head>
<script language="JavaScript" type="text/JavaScript">
	
	function checkSelect(){
		var select = document.formu.checkAll;
		if(select.checked==true){
			selectAll();
		}else{
			noselectAll();
		}
	}
	function selectAll() { 
		var o=document.formu.refundIds;
		//单条记录
		if(o.value!=null){
			o.checked=true;
		}
		//多条记录
	    for(var i=0;i<o.length;i++){
			if(o[i].name == "refundIds") {
				o[i].checked=true;
		    }
		}
	}
	function noselectAll() {
	    var o=document.formu.refundIds;
	    if(o.value!=null){
			o.checked=false;
		}
		 for(var i=0;i<o.length;i++){
				if(o[i].name == "refundIds")
			    {
   					o[i].checked=false;
			    }
			 }
	}
	
	function submitRefund(){
	   var flag=0;
	   var chck=document.getElementsByName("refundIds");
	   for(i=0;i<chck.length;i++){
	       if(chck[i].checked==true){
	           flag++;
	       }
	   }
	   if(flag>0){
	   	  if(confirm("Sure to submit refund information?")){
	   		document.getElementById("formu").action="submitRefund";
			document.getElementById("formu").submit();
	   	  }
       }else{
           alert("Please select one!");
       }
	}
	function applyRefund(tradeamount){
		var refundAmount = document.getElementById("refundAmount").value;
		var v = confirm("Confirm:\nTrade Amount:"+tradeamount+"\nRefund Amount:"+refundAmount);
		if(v==true){
			document.getElementById("form1").action="applyRefund";
			document.getElementById("form1").submit();
		}
	}
</script>
<!-- <p align="center">
	审核已申请退款交易  
</p>  -->
<s:form name="formu" id="formu" action="submitRefundEn" method="post" theme="simple">
<!-- <TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr>
		<td><input type="checkbox" name="checkAll" onclick="checkSelect();" /></td>
		<td>序列</td>
		<td>流水号</td>
		<td>商户订单号</td>
		<td>交易日期</td>
		<td>金额</td>
		<td>交易状态</td>
		<td>退款金额</td>
	</tr>
	<s:iterator id="p" value="previewRefundList" status="s">
	<tr onMouseOver="this.bgColor='#BBBBFF'; "  onMouseOut="this.bgColor='#FFFFFF';">
		<td><input type="checkbox" name="refundIds" value="<s:property value="#p[0].id" />" /></td>
		<td><s:property value="#s.index+1" /></td>
		<td><s:property value="#p[1].orderNo" /></td>
		<td><s:property value="#p[1].merchantOrderNo" /></td>
		<td><s:property value="#p[1].tradeTime" /></td>
		<td><s:property value="#p[1].tradeAmount" /></td>
		<td><s:property value="states.getStateNameEn(#p[1].tradeState,1)" /></td>
		<td><s:property value="#p[0].refundAmount" /></td>
	</tr>
	</s:iterator>
</table> -->
<!-- <p align="center"> 
	<input type="button" name="s" value="Submit Refund" onclick="submitRefund(<s:property value="applyRefund.tradeAmount" />)" />
</p> -->

 <!--头部begin-->
<s:action name="indexMenuEn" executeResult="true" />
<!--头部end-->
<div class="mainbody">
	   <div class="list">
	   
	      <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">Confirm Refunds</li>
             <li class="lilistother"></li>
            
           </ul>
          <ul class="bottom">
               <li class="li_06"><input type="checkbox" name="checkAll" onclick="checkSelect();" /></li>
		       <li class="li_01">Index</li>  
		       <li class="li_08">Order No.</li>    
		       <li class="li_04">Merchant OrderNo.</li>    
		       <li class="li_05">Trade Date</li>   
		       <li class="li_04">Amount</li>   
		       <li class="li_04">Status</li>   
		       <li class="li_03">Refund Amount</li>    
             </ul>
         </div>
		 
		  <div class="listlist">
		     <s:iterator id="p" value="previewRefundList" status="s">
               <ul class="listlistbottom"  onMouseOver="this.bgColor='#BBBBFF'; "  onMouseOut="this.bgColor='#FFFFFF';">
                 <li class="lil_06"><input type="checkbox" name="refundIds" value="<s:property value="#p[0].id" />" /></li>
			     <li class="lil_01"><s:property value="#s.index+1" /></li>
			     <li class="lil_08"><s:property value="#p[1].orderNo" /></li>
			     <li class="lil_04"><s:property value="#p[1].merchantOrderNo" /></li>
		         <li class="lil_05"><s:property value="#p[1].tradeTime" /></li>
			     <li class="lil_04"><s:property value="#p[1].tradeAmount" /></li>
			     <li class="lil_04"><s:property value="states.getStateNameEn(#p[1].tradeState,1)" escape="false" /></li>
			     <li class="lil_03"><s:property value="#p[0].refundAmount" /></li>
               </ul>
		     </s:iterator>
			  <br class="clear" />
		     <ul class="listlistpage">
		        <li><input type="button" class="input_button_01" name="s" value="Confirm" onclick="submitRefund(<s:property value="applyRefund.tradeAmount" />)" /></li>
		      </ul>
		   </div>
     </div>
   </div>
 </div>
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->

</s:form>











