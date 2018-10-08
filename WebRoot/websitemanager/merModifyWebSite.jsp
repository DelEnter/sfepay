<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<html>
<head>
	<title>修改网址</title>
	<script language="JavaScript" src="../js/util.js"></script>
</head> 
  <body>
<s:form action="modifyWebSite" id="myForm" namespace="/PaySystem" theme="simple"  >
<input type="hidden" name="isAuditWeb.id" value="<s:property value="isAuditWeb.id"/>" />
<table style="margin-left: 50px">
	<tr>
		<td>交易网址：</td>
		<td>
		<input type="text" name="isAuditWeb.tradeWebsite" value="<s:property value="isAuditWeb.tradeWebsite"/>" size="50" />
		</td>
	</tr>
	<tr height="10px"></tr>
	<tr>
		<td>返回网址：</td>
		<td>
		<input type="text" name="isAuditWeb.website" value="<s:property value="isAuditWeb.website"/>" size="50" />
		</td>
	</tr>
	</table>
	<br />
	<input style="margin-left: 50px;" type="submit" value="修改网址"
		class="windows_icon1" />
</s:form>
</body>
</html>