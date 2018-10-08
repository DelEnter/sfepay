<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>修改角色</title>
</head> 
    <script language="JavaScript" type="text/JavaScript">
	function updatesRole(f) {		
	        alert('123456');
			goFormWindow(f,"../PaySystem/updateRole");
	}
</script> 

<div id="title" value="修改角色"/>
<div id="resizetable" width="400" height="100">
<s:form action="updateRole" id="form3" namespace="/PaySystem">
<div class="windows">

<input type="hidden" name="showrole.id" value="<s:property value='showrole.id'/>" />
  <div class="left">角色名：</div>
  <div class="right"><input type="text" name="showrole.roleName" value="<s:property value='showrole.roleName' />" /></div>
  <div class="clear">&nbsp;</div>
  <div class="left">&nbsp;</div>
  <div class="right"><input type="button" onClick="updatesRole(this.form);" value="修改角色" class="windows_icon1"/></div>
</div>
</s:form>
