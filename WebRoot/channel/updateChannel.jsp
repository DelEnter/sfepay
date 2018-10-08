<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>修改通道</title>
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
		goFormWindow(f,"../PaySystem/updateChannel");
	}
</script>  
<s:form action="addrole" id="form1" namespace="/PaySystem">

<div id="title" value="修改通道"/>
<div id="resizetable" width="400" height="360">
  <div class="windows">
    <input type="hidden" name="channelId" value="<s:property value="channel.id"/>"/>
      <div class="left">通道名称：</div>
      <div class="right"><input type="text" name="channel.channelName" value="<s:property value="channel.channelName"/>"/></div>
      <div class="left">银行名称：</div>
     <div class="right"><input type="text" name="channel.bankName" value="<s:property value="channel.bankName"/>"/></div>
     <div class="left">商户号：</div>
     <div class="right"><input type="text" name="channel.bankMerchantId" value="<s:property value="channel.bankMerchantId"/>"/></div>
     <div class="left">银行接入码：</div>
     <div class="right"><input type="text" name="channel.accessCode" value="<s:property value="channel.accessCode"/>"/></div>
     <div class="left">url地址：</div>
     <div class="right"><input type="text" name="channel.bankUrl" value="<s:property value="channel.bankUrl"/>"/></div>
     <div class="left">对账url地址：</div>
     <div class="right"><input type="text" name="channel.checkUrl" value="<s:property value="channel.checkUrl"/>"/></div>
     <div class="left">对账用户名：</div>
     <div class="right"><input type="text" name="channel.checkUserName" value="<s:property value="channel.checkUserName"/>"/></div>
     <div class="left">对账密码：</div>
     <div class="right"><input type="text" name="channel.checkUserPwd" value="<s:property value="channel.checkUserPwd"/>"/></div>
     <div class="left">加密md5：</div>
     <div class="right"><input type="text" name="channel.md5" value="<s:property value="channel.md5"/>"/></div>
      <div class="clear">&nbsp;</div>
      <div class="left">&nbsp;</div>
      <div class="right">
        <input type="button" onClick="addCourse(this.form);" value="修改通道" class="windows_icon1"/>
      </div>
  
  </div>
</div>
</s:form>