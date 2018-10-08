<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>新增/修改migs通道</title>
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
			goFormWindow(f,"../PaySystem/addMigsMerchantNo");
	}
</script>  
<s:form action="addrole" id="form1" namespace="/PaySystem" theme="simple">
<s:hidden name="migsMerchantNo.id"/>
<div id="title" value="新增/修改migs通道"/>
<div id="resizetable" width="400" height="360">
  <div class="windows">
     <div class="left">银行名称：</div>
     <div class="right"><input type="text" name="migsMerchantNo.bankName" value="<s:property value="migsMerchantNo.bankName"/>" /></div>
     <div class="left">商户号：</div>
     <div class="right"><input type="text" name="migsMerchantNo.bankMerchantId" value="<s:property value="migsMerchantNo.bankMerchantId"/>"/></div>
     <div class="left">卡种：</div>
     <div class="right"><input type="text" name="migsMerchantNo.cardtype" value="<s:property value="migsMerchantNo.cardtype"/>"/></div>
     <div class="left">银行接入码：</div>
     <div class="right"><input type="text" name="migsMerchantNo.accessCode" value="<s:property value="migsMerchantNo.accessCode"/>"/></div>
     <div class="left">url地址：</div>
     <div class="right"><input type="text" name="migsMerchantNo.bankUrl" value="<s:property value="migsMerchantNo.bankUrl"/>"/></div>
     <div class="left">对账url地址：</div>
     <div class="right"><input type="text" name="migsMerchantNo.checkUrl" value="<s:property value="migsMerchantNo.checkUrl"/>"/></div>
     <div class="left">对账用户名：</div>
     <div class="right"><input type="text" name="migsMerchantNo.checkUserName" value="<s:property value="migsMerchantNo.checkUserName"/>"/></div>
     <div class="left">对账密码：</div>
     <div class="right"><input type="text" name="migsMerchantNo.checkUserPwd" value="<s:property value="migsMerchantNo.checkUserPwd"/>"/></div>
     <div class="left">加密md5：</div>
     <div class="right"><input type="text" name="migsMerchantNo.md5" value="<s:property value="migsMerchantNo.md5"/>"/></div>
     <div class="left">账单地址：</div>
     <div class="right"><input type="text" name="migsMerchantNo.billingaddress" value="<s:property value="migsMerchantNo.billingaddress"/>"/></div>
     <div class="clear">&nbsp;</div>
     <div class="left">&nbsp;</div>
     <div class="right">
        <input type="button" onClick="addCourse(this.form);" value="添加通道" class="windows_icon1"/>
     </div>
 
  </div>
</div>

</s:form>