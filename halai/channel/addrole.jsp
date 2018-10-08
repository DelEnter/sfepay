<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<head>
	<title>新增页面</title>
</head>
    <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
			goFormWindow(f,"../PaySystem/addrole");
	}
</script>  
<s:form action="addrole" id="form1" namespace="/PaySystem">

角色名：<input type="text" name="role.roleName"/><br>

<input type="button" onClick="addCourse(this.form);" value="增加课程" class="windows_icon1"/>
</s:form>