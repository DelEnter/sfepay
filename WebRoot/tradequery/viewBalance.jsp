<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>余额查询</title>
<script language="JavaScript" src="../js/util.js"></script>
</head>
<div align="center" >
	<h3>余额查询</h3>
</div>
<s:form name="formu" id="formu" method="post" theme="simple" action="viewBalance">
<table align="center">
			<tr class=TR_Title>	 		
		 		<td>商户号</td>
		 		<td><s:textfield name="merNo" /></td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="submit" value="查询" >				</td>
			</tr>
  </table>
	
	</br></br>
	<table cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
		<tr bgColor=#cccccc>
			<td>商户号</td>
			<td>保证金余额</td>
			<td>未结算余额</td>
		</tr>
		<s:iterator id="tradeSecond" value="viewList">
		<tr>
			<td><s:property value="#tradeSecond[0]" />&nbsp;</td>
			
			<td>
				<s:if test="#tradeSecond[1]!=null">
					<s:if test="#tradeSecond[2]!=null">
						<s:property value="states.toDouble((#tradeSecond[1]+#tradeSecond[2])*0.1)"/>
					</s:if>
					<s:else>
						<s:property value="states.toDouble(#tradeSecond[1]*0.1)"/>
					</s:else>
				</s:if>
			</td>
			<td>&nbsp;<s:property value="states.toDouble(#tradeSecond[3])" />&nbsp;</td>
		</tr>
		</s:iterator>
</table>

</s:form>