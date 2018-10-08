<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
	<title>Apply Refund</title>
</head>
<style>
body,table,tr,td{ font-size:12px;}
</style>
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
	
	function submitAuditing(){
	   var applyRefund=document.getElementById("applyRefund").value;
	   var refundAmount=document.getElementById("refundAmount").value;
	   if(applyRefund==""){
		   applyRefund="0";
	   }
	   if(parseFloat(applyRefund)<parseFloat(refundAmount)||refundAmount==""||parseFloat(refundAmount)<=0){
		   alert("退款金额为空或超限！");
		   return false;
	   }
	   document.getElementById("formu").action="singleapplyRefund";
	   document.getElementById("formu").submit();
    
	}
	function exportInfo(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("exportX");
		exportX.value="1";
		formX.submit();
	}
	function batchrefund(){
		var formX = document.getElementById("formu");
		formX.action = "batchRefund";
		formX.submit();
	}
	function chaxun(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("exportX");
		exportX.value="";
		formX.submit();
	}
	
	function buchongxinxi(tradeid){
		window.showModalDialog ("../PaySystem/toinputFulInfo.action?tradeId="+tradeid, window,'dialogHeight:630px;dialogWidth:593px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') ;
	}
</script>
<div align="center">
	<h3>强制退款操作</h3>
</div>
<s:form name="formu" id="formu" action="querySingleRefund" method="post" theme="simple">
<input type="hidden" name="tradeId" id="tradeId" value="<s:property value="tradeinfo.id"/>"/>
<table align="center">
<tr>
	 		 <tr>
	 		 	<td>流水订单号</td>
		 		<td>
		 			<input type="input" name="orderNo" value="<s:property value='orderNo'/>"/>
		 		</td>
	 		</tr>
	 		<tr class=font-align>
	 		<td><font color="red"><s:property value="messageAction"/></font></td>
				<td align="center"><br>
					<input type="submit" value="查询" >
				</td>
			</tr>
	</table>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr>
		<td align="center">流水号</td>
		<td align="center">交易日期</td>
		<td align="center">金额</td>
		<td align="center">支付状态</td>
		<td align="center">退款状态</td>
		<td align="center">可退款金额</td>
		<td align="center">退款金额</td>
	</tr>
	<tr>
		<td><s:property value="tradeinfo.orderNo" /></td>
		<td><s:property value="tradeinfo.tradeTime" /></td>
		<td><s:property value="tradeinfo.tradeAmount" /></td>
		<td><s:property value="states.getStateName(tradeinfo.tradeState,1)"  escape="false"/></td>
		<td><s:property value="states.getStateName(tradeinfo.tradeState,2)"  escape="false"/></td>
		<td><s:property value="applyRefund" /></td>
		<td>
		<input type="hidden" name="applyRefund" id="applyRefund" title="Enter Refund Amount" value="<s:property value="applyRefund" />"/>
		<input type="text" name="refundAmount" id="refundAmount" title="Enter Refund Amount" value="<s:property value="applyRefund" />"/>
		</td>
	</tr>
</table>
<div align="center">
</div>
<div align="right">
</div>
<p align="center">
	<input type="button" name="s" value="生成退款" onclick="submitAuditing()" />

</p>
</s:form>
