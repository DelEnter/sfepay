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
<s:form action="updateWebSite" id="myForm" namespace="/merchant" theme="simple"  >
<input type="hidden" name="webChannels.id" value="<s:property value="webChannels.id"/>" />
<table style="margin-left: 50px">
	<tr>
		<td>交易网址：</td>
		<td>
			<s:textfield name="webChannels.tradeWebsite" id="tradeWebsite" size="50"></s:textfield>
		</td>
	</tr>
	<tr height="20px"></tr>
	<tr>
		<td>返回网址：</td>
		<td>
			<s:textfield name="webChannels.website" id="website" size="50"></s:textfield>
		</td>
	</tr>
	</table>
	<br />
	<input style="margin-left: 50px;" type="submit" value="修改网址"
		class="windows_icon1" />
		<br />
		<br/>
		<span style="margin-left: 80px;color:red">*修改的网址将在网址审核列表里面等待审核才能生效。</span>
</s:form>
</body>
</html>