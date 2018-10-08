<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<script language="JavaScript" type="text/JavaScript">
function reloadPage(){
    window.location="findMerchantManagerAction.action";
}

</script>
<html>
  <head>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
  	<%@ include file="../util/calendar.jsp" %>
    <title>商户管理值设置</title>
  </head>
  
  <body>
    <center>
    	<h3>商户管理值设置</h3>
    	<TABLE cellSpacing=0 cellPadding=0 align=center border=0>	
			<s:form id="form1" action="findMerchantManagerAction" theme="simple" method="post">
				<tr>
					<td>商户号</td>
					<td><s:textfield name="merman.merchantId"/></td>
					<td>
						<s:submit value="查询"></s:submit>
					</td>
				</tr>
			</s:form>
		</TABLE>
    	
    		<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="640" align=center 
			bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
				<tr>
					<td bgColor=#cccccc>风险值</td>
					<td bgColor=#cccccc>账单地址</td>
					<td bgColor=#cccccc>单笔交易限额</td>
					<td bgColor=#cccccc>日交易限额</td>
					<td bgColor=#cccccc>月交易限额</td>
					<td bgColor=#cccccc>修改</td>
				</tr>
				<tr>
					<s:iterator id="it" value="list">
						<td><s:property value="merman.markValue"/></td>
						<td><s:property value="merman.merchantAddress"/></td>
						<td><s:property value="merman.penQuota"/></td>
						<td><s:property value="merman.dayQuota"/></td>
						<td><s:property value="merman.monthQuota"/></td>
						<td><input type="button" value="修改" onclick="openWindow('findupMerchantManagerAction','merman.id=<s:property value="#it.id"/>& merman.merchantId=<s:property value="merman.merchantId"/>')" /></td>
					</s:iterator>
				</tr>
    		</table>
    	
    </center>
  </body>
</html>
