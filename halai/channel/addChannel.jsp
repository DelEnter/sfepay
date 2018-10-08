<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>新增通道</title>
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
			goFormWindow(f,"../PaySystem/addChannel");
	}
</script>  
<s:form action="addrole" id="form1" namespace="/PaySystem" theme="simple">

<div id="title" value="新增通道"/>
<div id="resizetable" width="400" height="360">
  <div class="windows">
  
     <div class="left">通道名称：</div>
     <div class="right"><input type="text" name="channel.channelName"/></div>
     <div class="left">银行名称：</div>
     <div class="right"><input type="text" name="channel.bankName"/></div>
     <div class="left">商户号：</div>
     <div class="right"><input type="text" name="channel.bankMerchantId"/></div>
     <div class="left">银行接入码：</div>
     <div class="right"><input type="text" name="channel.accessCode"/></div>
     <div class="left">url地址：</div>
     <div class="right"><input type="text" name="channel.bankUrl"/></div>
     <div class="left">对账url地址：</div>
     <div class="right"><input type="text" name="channel.checkUrl"/></div>
     <div class="left">对账用户名：</div>
     <div class="right"><input type="text" name="channel.checkUserName"/></div>
     <div class="left">对账密码：</div>
     <div class="right"><input type="text" name="channel.checkUserPwd"/></div>
     <div class="left">加密md5：</div>
     <div class="right"><input type="text" name="channel.md5"/></div>
     <div class="clear">&nbsp;</div>
     <div class="left">&nbsp;</div>
     <div class="right">
        <input type="button" onClick="addCourse(this.form);" value="添加通道" class="windows_icon1"/>
     </div>
 
  </div>
</div>

</s:form>