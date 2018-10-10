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
			<s:textfield name="internationalIsAuditWeb.tradeWebsite" id="tradeWebsite" size="50" ></s:textfield>
		</td>
	</tr>
	</table>
	<br />
		<div>
		<span style="margin-left: 30px;color:blue;">*温馨提示：</span>
		<span style="margin-left: 0px;">其中交易网址不需要添加"http://"</span><br/><br/>
		<span style="margin-left: 30px;">返回网址需要根据您网站的系统类型来进行添加：</span><br/><br/>
		<span style="margin-left: 30px;">请选择：
		<span style="margin-left: 10px;">
		<s:select name='ival' list="#{
		'1':'zencart系统',
		'2':'ecshop系统',
		'3':'magento系统',
		'4':'Prestashop系统',
		'5':'Opencart系统',
		'6':'oscommerce系统',
		'7':'asp语言网站',
		'8':'.net语言网站',
		'9':'php语言网站'}" value='1'>
		</s:select><br/><br/>
		<input style="margin-left: 30px;" type="submit" value="添加网址" class="windows_icon1" />
		<span style="color:red">*添加的网址将在网址审核列表里面等待审核才能生效。</span>
		<br/>
		</span>
		</div>
</s:form>
</body>
</html>