<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %><head>
	<title>新增角色</title>
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
			goFormWindow(f,"../PaySystem/addrole");
	}
</script>  

<div id="title" value="新增角色"/>
<div id="resizetable" width="400" height="100">
<div class="windows">
  <s:form action="addrole" id="form1" namespace="/PaySystem">
   <div class="left">角色名：</div>
   <div class="right"><input type="text" name="role.roleName"/></div>
   <div class="clear">&nbsp;</div>
   <div class="left">&nbsp;</div>
   <div class="right">
      <input type="button" onClick="addCourse(this.form);" value="新增角色" class="windows_icon1"/>
   </div>
  </s:form>
</div>
</div>