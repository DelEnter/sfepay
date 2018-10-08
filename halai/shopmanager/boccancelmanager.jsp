<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
	<title>TC通道撤销交易管理</title>
</head>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<script language="JavaScript" type="text/JavaScript">
	function canceltrade(tradeid){
        document.getElementById("orderId").value=tradeid;
        document.getElementById("formu").action="tcCancelTrade";
        document.getElementById("formu").submit();
	}
	function submits(){
        document.getElementById("formu").action="tcCancelTradeQuery";
        document.getElementById("formu").submit();
	}
</script>
<s:form name="formu" id="formu" action="tcCancelTradeQuery" method="post" theme="simple">
<s:hidden name="orderId" />
<s:property value="messageAction"/>
<div align="center">
流水号
<input type="input" name="orderNo" value="<s:property value='orderNo'/>"/>
<input type="button" onclick="submits()" value="查询" >
</div>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr>
		<td align="center">序列</td>
		<td align="center">流水号</td>
		<td align="center">商户流水号</td>
		<td align="center">交易日期</td>
		<td align="center">外币金额</td>
		<td align="center">RMB金额</td>
		<td align="center">BOC-RRN</td>
		<td align="center">授权号</td>
		<td align="center">批次号</td>
		<td align="center">终端号</td>
		<td align="center">撤销交易</td>
		
	</tr>
	<s:iterator id="p" value="info.result" status="s">
	<tr>
		<td><s:property value="#s.index+1" /></td>
		<td><s:property value="#p[0].orderNo" /></td>
		<td><s:property value="#p[0].merchantOrderNo" /></td>
		<td><s:property value="#p[0].tradeTime" /></td>
		<td><s:property value="#p[0].tradeAmount" />&nbsp;</td>
		<td><s:property value="#p[0].rmbAmount" />&nbsp;</td>
		<td><s:property value="#p[0].boc_rrn" />&nbsp;</td>
		<td><s:property value="#p[0].VIPAuthorizationNo" />&nbsp;</td>
		<td><s:property value="#p[0].VIPBatchNo" />&nbsp;</td>
		<td><s:property value="#p[0].VIPTerminalNo" />&nbsp;</td>
		<td><a href="#" onclick="canceltrade(<s:property value='#p[0].id'/>)"/>撤销&nbsp;</td>
		
	</tr>
	</s:iterator>
</table>
<div align="center">
<pages:pages value="info" beanName="info" formName="forms(0)" />
</div>

<p align="center">
</p>
</s:form>