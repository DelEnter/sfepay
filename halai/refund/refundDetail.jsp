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
<div align="center">
	<h3>退款详情</h3>
</div>
<s:form name="formu" id="formu" action="toCreateRefund" method="post">
<div align="center">
批次好:<s:property value="batchNo"/>
</div>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr>
		<td align="center">序列</td>
		<td align="center">流水号</td>
		<td align="center">商户订单号</td>
		<td align="center">交易日期</td>
		<td align="center">申请日期</td>
		<td align="center">审核日期</td>
		<td align="center">退款日期</td>
		<td align="center">交易金额</td>
		<td align="center">退款金额(外币)</td>
		<td align="center">退款金额(RMB)</td>
		<td align="center">交易状态</td>
		<td align="center">退款状态</td>
		<td align="center">批次号</td>
		<td align="center">授权号</td>
	</tr>
	<s:iterator id="p" value="refundDetailList" status="s">
	<tr onMouseOver="this.bgColor='#BBBBFF'; "  onMouseOut="this.bgColor='#FFFFFF';">
		<td><s:property value="#s.index+1" /></td>
		<td><s:property value="#p[1].orderNo" /></td>
		<td>
			<s:property value="#p[1].merchantOrderNo" />
		</td>
		<td align="center"><s:property value="#p[1].tradeTime" /></td>
		<td align="center"><s:property value="#p[0].applyDate" /></td>
		<td align="center"><s:property value="#p[0].auditingDate" /></td>
		<td align="center"><s:property value="#p[0].refundDate" /></td>
		<td align="right"><s:property value="#p[1].tradeAmount" /></td>
		<td align="right"><s:property value="#p[0].refundAmount" /></td>
		<td align="right"><s:property value="#p[0].refundRMBAmount" /></td>
		<td align="center"><s:property value="states.getStateName(#p[1].tradeState,1)" escape="false"/></td>
		<td align="center"><s:property value="states.getRefundDetailState(#p[0].refundState)"escape="false" /></td>
		<td align="center"><s:property value="#p[1].VIPBatchNo" /></td>
		<td align="center"><s:property value="#p[1].VIPAuthorizationNo" /></td>
	</tr>
	</s:iterator>
</table>
<p align="center">
	<input type="button" name="s" value="打印记录" onclick="submitAuditing()" />
</p>
</s:form>


