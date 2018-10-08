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
<s:form action="merchantAddWebSite" id="myForm" namespace="/merchant" theme="simple"  >
<table  style="margin-left: 30px">
	<tr>
		<td>交易网址：</td>
		<td>
			<s:textfield name="internationalIsAuditWeb.tradeWebsite" id="tradeWebsite" size="50"></s:textfield>
		</td>
	</tr>
	<tr height="20px"></tr>
	<tr>
		<td>返回网址：</td>
		<td>
			<s:textfield name="internationalIsAuditWeb.website" id="website" size="50"></s:textfield>
		</td>
	</tr>
	</table>
	<br />
	<input style="margin-left: 30px;" type="submit" value="添加网址"
		class="windows_icon1" />
		<br />
		<span style="margin-left: 80px;color:red">*添加的网址将在网址审核列表里面等待审核才能生效。</span>
		<br/>
		<div>
		<span style="margin-left: 30px;color:blue;">*温馨提示：</span><br/>
		<span style="margin-left: 40px;">其中交易网址不需要添加"http://",返回网址需要添加,具体根据您网站情况来判断"http://"或"https://".</span><br/>
		<span style="margin-left: 30px;">返回网址需要根据您网站的系统类型来进行添加具体如下：</span><br/>
		<span style="margin-left: 30px;"><textarea rows="7" cols="73" readonly="readonly"> zencart系统(http://您的交易网址/index.php?main_page=checkout_payresult).
 ecshop系统(http://您的交易网址/respond.php?code=sfepay).
 magento系统(http://您的交易网址/sfepay/payment/return/).
 Prestashop系统(http://您的交易网址/modules/sfepay/validation.php).
 Opencart系统(http://您的交易网址/index.php?route=payment/sfepay/callback).
 oscommerce系统(http://您的交易网址/checkout_sfepayresult.php).
		</textarea></span>
		<span style="margin-left: 30px;color:blue;">*其他系统：</span><br/>
		<span style="margin-left: 40px;">返回地址为您这边用于接收我司网关数据的页面如：</span><br/>
		<span style="margin-left: 30px;"><textarea rows="4" cols="73" readonly="readonly"> asp语言网站(http://您的交易网址/receive.asp).
 .net语言网站(http://您的交易网址/PayResult.aspx).
 php语言网站(http://您的交易网址/PayResult.php).
		</textarea></span>
		</div>
</s:form>
</body>
</html>