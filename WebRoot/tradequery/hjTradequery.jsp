<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
	<title>HJ查询</title>
</head>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<script language="JavaScript" type="text/JavaScript">
	function submitAuditing(orderId){
	   var tradeId=document.getElementById("tradeId").value;
	   if(tradeId==""){
		   alert("没有可选项！");
		   return false;
	   }
	   document.getElementById("formu").action="updateTrade";
	   document.getElementById("formu").submit();
    
	}

</script>
<div align="center">
	<h3>HJ查询</h3>
</div>
<s:form name="formu" id="formu" action="queryHjPay" method="post" theme="simple">
<input type="hidden" name="tradeId" id="tradeId" value="<s:property value="tradeinfo.id"/>"/>
<input type="hidden" name="hjQueOrderNo" id="hjQueOrderNo" value="<s:property value="hjQueOrderNo"/>"/>
<input type="hidden" name="hjBilladdress" id="hjBilladdress" value="<s:property value="hjBilladdress"/>"/>
<table align="center">
<tr>
	 		 <tr>
	 		 	<td>流水订单号</td>
		 		<td>
		 			<input type="text" name="orderNo" value="<s:property value='orderNo'/>"/>
		 		</td>
	 		</tr>
	 		<tr class=font-align>
				<td align="center"><br>
					<input type="submit" value="查询" >
				</td>
			</tr>
	</table>
	</s:form>
	<span><s:if test="%{errMsg!=null}"><s:property value="errMsg" /></s:if></span>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr>
		<td align="center">流水号</td>
		<td align="center">交易日期</td>
		<td align="center">金额</td>
		<td align="center">支付状态</td>
		<td align="center">通道返回备注</td>
		<td align="center">操作</td>
	</tr>
	<tr>
		<td align="center"><s:property value="tradeinfo.orderNo" /></td>
		<td align="center"><s:property value="tradeinfo.tradeTime" /></td>
		<td align="center"><s:property value="tradeinfo.tradeAmount" /></td>
		<td align="center"><s:property value="states.getStateName(tradeinfo.tradeState,1)"  escape="false"/></td>
		<td align="center"><span style="color:red"><s:property value="hjRemark" /></span></td>
		<td align="center"><a href="#" onclick="submitAuditing('<s:property value="tradeinfo.id"/>')">更新订单成功</a></td>
	</tr>
</table>
