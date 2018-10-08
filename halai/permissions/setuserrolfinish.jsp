<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
	<title>设置用户角色</title>
    <link href="css/other.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">

function setUserRoles(){
   var f=document.form7; 
   goFormWindow(f,'../PaySystem/setUserRole');
}

</script>
<body>
<div id="title" value="修改角色"/>
<div id="resizetable" width="400" height="170">
<div class="windows">
  <s:form action="setUserRole" id="form7" namespace="/PaySystem" theme="simple">

   <div class="left">用户名：</div>
   <div class="right"><input type="text" value="<s:property value='users.userName'/>" /> </div>
   <div class="clear">&nbsp;</div>
   <div class="left">角色：</div>
   <div class="right">
   
   <s:select name="roleUser.role.id" list="rolelist" listKey="id"  listValue="roleName"/>
   
    <input type="hidden" name="users.id" value="<s:property value='users.id'/>" />
    <input type="hidden" name="roleUser.id" value="<s:property value='roleUser.id'/>" /> 
   
   </div>
   
    <div class="clear">&nbsp;</div>
   <div class="left">&nbsp;</div>
   <div class="right"><input type="button" onClick="setUserRoles();" value="设置角色" /></div>
     </s:form>
   
</div>

</body>