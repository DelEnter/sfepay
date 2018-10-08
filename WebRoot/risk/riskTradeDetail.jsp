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
	<h3>查询详情</h3>
</div>
<div align="center">
查询项:<s:if test="%{queryType==1||queryType==2||queryType==3}">
		<td><s:property value="getCarNo(cardNo)" /></td>
		</s:if>
		<s:else>
		<td><s:property value="cardNo" /></td>
		</s:else>
</div>
<s:form id="formu" action="viewDetails" method="post" theme="simple">
<input type="hidden" name="cardNo" value="<s:property value="cardNo" />">
<input type="hidden" name="detailType" value="<s:property value="detailType" />">
<input type="hidden" name="startDate" value="<s:property value="startDate" />">
<input type="hidden" name="endDate" value="<s:property value="endDate" />">
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr>
		<td align="center">序列</td>
		<td>流水号</td>
		<td>金额(外币)</td>
		<td>金额(人民币)</td>
		<td>交易日期</td>
		<td>卡号</td>
		<td>Email</td>
		<td>IP</td>
		<td>支付结果</td>
		<td>备注</td>
		<td>商品信息</td>
	</tr>
	<s:iterator id="p" value="info.result" status="s">
	<tr onMouseOver="this.bgColor='#BBBBFF'; "  onMouseOut="this.bgColor='#FFFFFF';">
		<td><s:property value="#s.index+1" /></td>
		<td><s:property value="#p[1].orderNo" /></td>
		<td><s:property value="#p[1].tradeAmount" /></td>
		<td><s:property value="#p[1].rmbAmount" /></td>
		<td><s:property value="#p[1].tradeTime" /></td>
		<td><s:property value="getCarNo(#p[0].cardNo).substring(0,6)"/>******<s:property value="getCarNo(#p[0].cardNo).substring(12)"/>
		<td><s:property value="#p[0].email" /></td>
		<td><s:property value="#p[0].ip" /></td>
		<td><s:property value="states.getStateName(#p[1].tradeState,1)" escape="false" />&nbsp;</td>
		<td><s:property value="#p[1].remark" /></td>
		<td><s:property value="#p[0].productInfo" /></td>
	</tr>
	</s:iterator>
</table>
<div align="center">
<pages:pages value="info" beanName="info" formName="getElementById('formu')" />
</div>
</s:form>


