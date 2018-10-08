<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<html>
<head>
	<title>修改网址</title>
	<script language="JavaScript" src="../js/util.js"></script>
</head> 
    <script language="JavaScript" type="text/JavaScript">
	function updatesWebSite(f) {		
			goFormWindow(f,"../PaySystem/updateMerchantWebSite");
	}
</script> 
  <body>
<s:form action="updateMerchantWebSite" id="myForm" namespace="/PaySystem" theme="simple">
	<input type="hidden" name="iid" value="<s:property value="website.id"/>" />
	
	<input type="hidden" name="merno" value="<s:property value="merno"/>" />
	<tr>
		<td>交易网址：</td>
		<td>
			<s:textfield name="website.tradeWebsite" id="tradeWebsite"></s:textfield>
		</td>
	</tr>
	<tr>
		<td>返回网址：</td>
		<td>
			<s:textfield name="website.website" id="website"></s:textfield>
		</td>
	</tr>
	<tr>
		<td>绑定日期:</td>
		<td>
 			<s:property value="website.executetime"/>
		</td>
	</tr>
	<br />
	<input type="button" onClick="updatesWebSite(this.form);" value="修改网址"
		class="windows_icon1" />
</s:form>
</body>
</html>