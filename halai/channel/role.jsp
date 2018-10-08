<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
	<title>登陆页面</title>
</head>
<script language="JavaScript" type="text/JavaScript">
function reloadPage(){

        window.location="../PaySystem/roleadmin.action";
}
function add(){ 
   openWindow('../PaySystem/toaddrole.action','12');
}
</script>
<input type="button" value="新增" onclick="add();">

<s:iterator id="rolelist" value="rolelist" >
角色名称： <s:property value="#rolelist.roleName" />
角色id：<s:property value="#rolelist.id" />
</p>
</s:iterator>
