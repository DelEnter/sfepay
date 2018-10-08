<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <%@ include file="../include/dialog.jsp"%>
 <link href="../css/css.css" rel="stylesheet" type="text/css" />
<script language="JavaScript" src="../js/util.js"></script>

<head>
	<title>Apply Refund</title>
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
	   	  if(confirm("Confirm the information you submit refund?")){
	   		document.getElementById("formu").action="submitRefund";
			document.getElementById("formu").submit();
	   	  }
       }else{
           alert("Please select one!");
       }
	}
	
	function reloadPage(){
	        window.location="../PaySystem/toCreditCard.action";
	}
	function applyRefund(tradeamount){
		var refundAmount = document.getElementById("refundAmount").value;
		if(refundAmount<=tradeamount){
			var v = confirm("Confirm:\nTrade Amount:"+tradeamount+"\nRefund Amount:"+refundAmount);
			if(v==true){
				document.getElementById("form1").action="applyRefund";
				document.getElementById("form1").submit();
			}
		}else{
			alert("Refunds can not be greater than the amount of the transaction amount.")
		}
		
	}
	function cancelRefund(refundid){
		window.location="../PaySystem/cancelRefund.action?refundId="+refundid;
	}
</script>
<s:form name="toApplyRefund" action="toApplyRefundEn" id="form1" theme="simple">
<input type="hidden" name="tradeId" value="<s:property value="applyRefund.id" />" />
<!-- <div align="center">

</div>

<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">	
	<tr>
		<td></td>
		<td>OrderNo.</td>
		<td>MerOrderNo.</td>
		<td>交易日期</td>
		<td>金额</td>
		<td>支付状态</td>
		<td>退款状态</td>
		<td>退款金额</td>
	</tr>
	<tr>
		<td></td>
		<td><s:property value="applyRefund.orderNo" /></td>
		<td><s:property value="applyRefund.merchantOrderNo" /></td>
		<td><s:property value="applyRefund.tradeTime" /></td>
		<td><s:property value="applyRefund.tradeAmount" /></td>
		<td><s:property value="states.getStateNameEn(applyRefund.tradeState,1)" /></td>
		<td><s:property value="states.getStateNameEn(applyRefund.tradeState,2)" /></td>
		<td><input type="input" name="refundAmount" id="refundAmount" style="color:red" title="Enter Refund Amount" value="<s:property value="applyRefund.tradeAmount" />"/></td>
	</tr>
</table>
<p align="center">
	<font color="red"><s:property value="messageAction"/><br/></font>
	<input type="button" name="s" value="Apply Refund" onclick="applyRefund(<s:property value="applyRefund.tradeAmount" />)" />
</p>
</s:form>

<p align="center">
	<font color="red">
	提醒：根据Ecpss交易流水号查询出该笔交易，填写退款金额进行申请退款
	<br/>
	申请退款交易需要进入商户退款审核管理页面进行确认提交退款操作
	</font>
</p>
 -->
 <!--头部begin-->
<s:action name="indexMenuEn" executeResult="true" />
<!--头部end-->
<div class="mainbody">
       <div class="search2">
         <ul class="searchtext">
           <li class="name">Order No.</li>
           <li class="nameinput"><input type="input" name="orderNo" value="<s:property value='orderNo'/>"/>
<input type="submit" value="Search" class="input_button_01" ></li>
         </ul>
       </div>
       <div><img src="images/division.gif" alt="" /></div>
       
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">Apply Refund</li>
             <li class="lilistother"></li>
         
           </ul>
           <ul class="bottom">
             <li class="li_08">OrderNo.</li>  
             <li class="li_05">MerchantOrderNo.</li>  
             <li class="li_04">Trade Date</li> 
             <li class="li_03">Amount</li> 
             <li class="li_03">Status</li> 
             <li class="li_04">Refund Status</li>        
             <li class="li_05">RefundAmount</li> 
           </ul>
         </div>
         <div class="listlist">
           <ul class="listlistbottom">
             <li class="lil_08"><s:property value="applyRefund.orderNo" /></li>  
             <li class="lil_05"><s:property value="applyRefund.merchantOrderNo" /></li>    
			 <li class="lil_04"><s:property value="applyRefund.tradeTime" /></li>    
			 <li class="lil_03"><s:property value="applyRefund.tradeAmount" /></li>    
			 <li class="lil_03"><s:property value="states.getStateNameEn(applyRefund.tradeState,1)" escape="false"/></li>  
			 <li class="lil_04"><s:property value="states.getStateNameEn(applyRefund.tradeState,2)" escape="false"/></li>  
			 <li class="lil_05"><input type="input" name="refundAmount" id="refundAmount" class="text_input_01" style="color:red" title="Enter Refund Amount" value="<s:property value="applyRefund.tradeAmount" />"/></li>    
           </ul>
           
           <ul class="listlistpage">
             <li><font color="red"><s:property value="messageAction"/><br/></font>
	<input type="button" name="s" value="Submit Apply" class="input_button_01" onclick="applyRefund(<s:property value="applyRefund.tradeAmount" />)" /></li>
           </ul>
		   <br class="clear" />
		   <ul class="lislistpage">
		     <li><font color="red">
	提醒：根据Ecpss交易流水号查询出该笔交易，填写退款金额进行申请退款
	<br/>
	申请退款交易需要进入商户退款审核管理页面进行确认提交退款操作
	</font></li>
		   </ul>
           
         </div>
       </div>
     </div>
   </div>
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->






