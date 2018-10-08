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
			goFormWindow(f,"../PaySystem/updateMerchantWebSite1");
	}
	
	function checkUpdate(name,value,sign) {
		/**if(value == "" || value.length == 0 ) {
			alert(name + "为空");
			return false;
		}
		if(value.indexOf(" ") != -1) {
			alert(name + "中含有空格");
			return false;
			
		}
		return true;
		*/
	}
	
	
</script> 
  <body>
<s:form action="updateMerchantWebSite" id="myForm" namespace="/PaySystem" theme="simple">
	<input type="hidden" name="iid" value="<s:property value="website.id"/>" />
	<input type="hidden" name="merno" value="<s:property value="merchant.merno"/>" />
	<table>
	<tr>
		<td>商户号：</td>
		<td>
			<s:property value="merchant.merno"/>
		</td>
	</tr>
	<tr>
		<td>交易网址：</td>
		<td>
			<s:textfield name="website.tradeWebsite" id="tradeWebsite" size="70" onblur="checkUpdate('交易网址',this.value,'tradeWebsite');"></s:textfield>
		</td>
	</tr>
	<tr>
		<td>返回网址：</td>
		<td>
			<s:textfield name="website.website" size="60" id="website" onblur="checkUpdate('网址',this.value,'website');"></s:textfield>
		</td>
	</tr>
	<tr>
		<td>绑定日期:</td>
		<td>
 			<s:property value="website.executetime"/>
		</td>
	</tr>
	</table>
	<br />
	<input type="button" onClick="updatesWebSite(this.form);" value="修改网址"
		class="windows_icon1" />
</s:form>
</body>
</html>