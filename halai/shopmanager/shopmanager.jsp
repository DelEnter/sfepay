<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pages" uri="/xs-pages"%>
<%@ include file="../include/dialog.jsp"%>
<link type="text/css" rel="stylesheet" href="../css/other.css" />
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>新增页面</title>
</head>
    <script language="JavaScript" type="text/JavaScript">
    function updateMerMessage(merid){
    	window.location="../PaySystem/managerMerMessage.action?mer.id="+merid
    }
     
	function addCourse(f) {		
			goFormWindow(f,"../PaySystem/toManagerShop");
	}
	function toMerChannelmanager(merid){
		window.location="../PaySystem/toMerChannel.action?merid="+merid
	}
	function toMerCreditCardmanager(merid){
		window.location="../PaySystem/toMerCreditCard.action?merid="+merid
	}
	function toCurrency(merid){
		window.location="../PaySystem/findMerCurrency.action?merCurrency.merchanId="+merid;
	}
	
	//管理值设定
	function toManageValueSetting(merid) {
		window.location="../PaySystem/toManageValueSetting.action?merid="+merid
	}
	
	
	//商户网址
	function toMerchantWebSite(merid) {
		//alert(merid);
		window.location="../PaySystem/findMerchantWebSite1.action?merid="+merid
	}
	
	//开通 关闭
	function openOrClose(merid){
		openWindow('../PaySystem/openorclosemerchant.action?merid='+merid,'12');
	}
</script>     <h3 align="center"> 商户管理</h3>
<s:form action="toManagerShop" id="form1" namespace="/PaySystem">
     
                  <table width="80%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td align="left">&nbsp;商户号： 
                      <input type="text" name="merchant.merno" />                      &nbsp;姓名：
                      <input type="text" name="merchant.username" />&nbsp; <input type="submit" value="搜索"/> </td>
                    </tr>
                  </table>
  
                  
                  <table width="80%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td>&nbsp;</td>
                    </tr>
                  </table>
                  <table width="80%" border="0" cellpadding="0" cellspacing="1" bgcolor="#333333">
                    <tr>
                      <td height="40" align="center" bgcolor="#cccccc" class="font14px">商户号 </td>
                      <td height="40" align="center" bgcolor="#cccccc" class="font14px">商户名称 </td>
                      <td height="40" align="center" bgcolor="#cccccc" class="font14px">MD5KEY</td>
                      <td height="40" align="center" bgcolor="#cccccc" class="font14px">开通与否</td>
                      <td height="40" align="center" bgcolor="#cccccc" class="font14px">操作</td>
                    </tr>
                    <s:iterator value="info.result" id="s">
                    <tr>
                      <td height="40" align="center" bgcolor="#FFFFFF"><font color=#930909> 
                      <s:property value="#s.merno"/></font>
			<input type = "hidden" value="<s:property value="#s.merno"/>" name="merno" /></td>
                      <td height="40" align="center" bgcolor="#FFFFFF"><s:property value="#s.username"/></td>
                      <td height="40" align="center" bgcolor="#FFFFFF"><font color=#930909> 
                      <s:property value="#s.md5key"/></font>	</td>
                      <td height="40" align="center" bgcolor="#FFFFFF"><s:property value="#s.isopen"/></td>
<td height="40" align="center" bgcolor="#FFFFFF">
<input type="button" value="修改商户信息"  class="button_04" onclick="updateMerMessage('<s:property value="#s.id"/>');"/>&nbsp;  
<input type="button" onclick="toMerCreditCardmanager('<s:property value="#s.id"/>');"  class="button_02" value="卡种设置"/>&nbsp;
<input type="button" onclick="toCurrency('<s:property value="#s.id"/>');" value="币种设置"  class="button_03"/>&nbsp;
<input type="button" value="网址设置"  class="button_04" onclick="toMerchantWebSite('<s:property value="#s.id"/>');"/>&nbsp;
<input type="button" onclick="toMerChannelmanager('<s:property value="#s.id"/>');"  class="button_05" value="通道设置"/>&nbsp;
<input type="button" value="条件设置" class="button_06"  onclick="openWindow('../PaySystem/toConditionSetting1.action','merid=<s:property value="#s.id"/>');"/>&nbsp;
<input type="button" value="管理设置" class="button_07"  onclick="openWindow('../PaySystem/toManageValueSetting1.action','merid=<s:property value="#s.id"/>');"/>
<input type="button" class="button_07"  name="p" value="<s:if test="#s.isopen==1">关闭</s:if><s:else>开通</s:else>" onclick="openOrClose(<s:property value="#s.id"/>);"/>
</td>
                    </tr>
                    </s:iterator>
  </table>
	
<pages:pages value="info" beanName="info" formName="getElementById('form1')" />
</s:form>