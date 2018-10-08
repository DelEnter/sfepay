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
	function updateWebSitemanager(f) {		
	        //alert('123456');
			goFormWindow(f,"../PaySystem/updateWebSiteManager");
			//document.getElementById("myForms").submit();
	}
</script> 
  <body>
<s:form action="updateWebSiteManager" id="myForm22" theme="simple">
	<input type="hidden" name="website.id" value="<s:property value="website.id"/>" />
	<table >
	<tr>
		<td>商户号：</td>
		<td>
			<s:property value="merchant.merno" />
		</td>
	</tr>
	<tr>
		<td>交易网址：</td>
		<td>
			<s:textfield name="website.tradeWebsite"  size="60"></s:textfield>
		</td>
	</tr>
	<tr>
		<td>返回网址：</td>
		<td>
			<s:textfield name="website.website" size="70"></s:textfield>
		</td>
	</tr>
	</table>
	<input type="button" onClick="updateWebSitemanager(this.form);" value="修改网址"
		class="windows_icon1" />
</s:form>
</body>
</html>