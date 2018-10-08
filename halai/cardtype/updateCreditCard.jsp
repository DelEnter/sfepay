<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>修改卡种</title>
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
		goFormWindow(f,"../PaySystem/updateCreditCard");
	}
</script>  
<s:form action="addrole" id="form1" namespace="/PaySystem">
<input type="hidden" name="carditCardId" value="<s:property value="creditCardType.id"/>"/>

<div id="title" value="修改卡种"/>
<div id="resizetable" width="400" height="130">
  <div class="windows">
  
     <div class="left">卡种名称：</div>
     <div class="right"><input type="text" name="creditCardType.cardName" value="<s:property value="creditCardType.cardName"/>" /></div>
     <div class="left">卡种简称：</div>
     <div class="right"><input type="text" name="creditCardType.shortName" value="<s:property value="creditCardType.shortName"/>"/></div>
     <div class="clear">&nbsp;</div>
     <div class="left">&nbsp;</div>
     <div class="right">
      <input type="button" onClick="addCourse(this.form);" value="修改卡种" class="windows_icon1"/>
     </div>
 
  </div>
</div>
</s:form>