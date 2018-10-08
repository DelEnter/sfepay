<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<s:if test="creditCardType!=null">
		<title>修改卡种</title>
	</s:if>
	<s:else>
		<title>新增卡种</title>
	</s:else>
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addCreditCard(f) {		
			goFormWindow(f,"../PaySystem/addCreditCard");
	}
</script> 
<form action="addCreditCard" id="form1">

<div id="title" value="新增卡种"/>
<div id="resizetable" width="400" height="170">
  <div class="windows">
  
     <div class="left">卡种名称：</div>
     <div class="right"><s:textfield name="creditCardType.cardName"></s:textfield></div>
     <div class="clear">&nbsp;</div>
     <div class="left">卡种简称：</div>
     <div class="right"><s:textfield name="creditCardType.shortName"></s:textfield></div>
     <div class="clear">&nbsp;</div>
     <div class="left">&nbsp;</div>
     <div class="right">
       <input type="button" onClick="addCreditCard(this.form);" value="submit" class="windows_icon1"/>
     </div>
  </div>
</div>
</form>