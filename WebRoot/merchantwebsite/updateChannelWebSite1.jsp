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
		var t = document.getElementById("webSiteType1"); 
		var webSiteType1=t.options[t.selectedIndex].value;
		var webSiteType2=document.getElementById("webSiteType2").value;
		var webSiteType="";
		if(webSiteType1!=""){
			webSiteType=webSiteType1;
		}
		if(webSiteType2!=""){
			webSiteType=webSiteType2;
		}
			goFormWindow(f,"../PaySystem/updateMerchantWebSite1.action?webSiteType="+webSiteType);
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
	<table style="margin-left: 20px">
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
	<tr>
		<td>交易网址状态：</td>
		<td>
			<s:radio name="website.isblack"  id="isblack" list="#{'0':'正常交易','1':'禁止交易'}" onblur="checkUpdate('网址',this.value,'isblack');"/>
		</td>
	</tr>
	<tr>
		<td>绑定日期:</td>
		<td>
 			<s:property value="website.executetime"/>
		</td>
	</tr>
	<tr>
		<td>已有网站种类:</td>
		<td>
 			<s:select id="webSiteType1" list="webSiteTypeList" headerKey="" headerValue="--请选择--" />
		</td>
	</tr>
	<tr>
		<td>网站类型:</td>
		<td>
 			<input type="text" id="webSiteType2" name="webSiteType2" value="<s:property value="website.webSiteType"/>" />
		</td>
	</tr>
	</table>
	<div style=" width:300px;font-size: 12px;color: blue;margin-left: 50px">提示:&nbsp;&nbsp;如果网址分类已存在，请下拉选择，如果不存在请在网站类型输入，二填一即可，如果都填默认输入框有效!</div>
	<br />
	<input type="button" onClick="updatesWebSite(this.form);" value="修改网址"
		class="windows_icon1" />
</s:form>
</body>
</html>