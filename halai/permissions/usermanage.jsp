<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
	<title>用户管理页面</title>
</head>
<script language="JavaScript" type="text/JavaScript">
function reloadPage(){

        window.location="../PaySystem/roleadmin.action";
}
function add(){ 
   openWindow('../PaySystem/toaddrole');
}

function addResource(){ 
   openWindow('../PaySystem/toaddResouce');
}

function tosetUserRole(){ 
   openWindow('../PaySystem/tosetUserRole');
}
</script>
<input type="button" value="角色分配" onclick="tosetUserRole();">

<input type="button" value="新增角色" onclick="add();">   
<p>
<s:iterator id="rolelist" value="rolelist" >
角色名称： <s:property value="#rolelist.roleName" />
角色id：<s:property value="#rolelist.id" />
<input type="button" value="修改" onclick="openWindow('../PaySystem/toUpdateRole.action','showrole.id=<s:property value='id'/>');"  />
<input type="button" value="删除" onclick="openWindow('../PaySystem/deleteRole.action','showrole.id=<s:property value='id'/>');" />
</p>
</s:iterator>
<input type="button" value="新增资源"  onclick="addResource();" />
<p>
<s:iterator id="resourcelist2" value="resourcelist2" >
资源URL： <s:property value="#resourcelist2.resourceUrl" />
资源名称 ：<s:property value="#resourcelist2.menuName" />
<input type="button" value="修改" onclick="openWindow('../PaySystem/toUpdateResource.action','resouce.id=<s:property value='id'/>');" />
<input type="button" value="删除" onclick="openWindow('../PaySystem/deleteResouce.action','resouce.id=<s:property value='id'/>');"  />
</p>
</s:iterator>