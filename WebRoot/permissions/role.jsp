<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>角色资源管理</title>
    
    <link href="../css/other.css" rel="stylesheet" type="text/css" />
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


<div class="fate" >
  <div class="title">
    <ul>
      <li class="liicon"><img src="../images/other/icon3.gif" alt="" /></li>
      <li class="lititle">分配角色</li>
      <li class="add"><input type="button" value="" title="新增角色" class="button_add_01" onclick="add();"> </li>
    </ul>
  </div>
  <div class="title2">
    <ul class="ul_title1 font14px">
      <li class="lili"><b>角色名</b></li>
      <li class="lili"><b>角色id</b></li>
      <li class="lili"><b>操作</b></li>
    </ul>
<s:iterator id="rolelist" value="rolelist" >
    <ul class="ul_title1">
      <li class="lili"><s:property value="#rolelist.roleName" /></li>
      <li class="lili"><s:property value="#rolelist.id" /></li>
      <li class="lili">
		<input type="button" value="" title="修改" class="button_edit_01" onclick="openWindow('../PaySystem/toUpdateRole.action','showrole.id=<s:property value='id'/>');"  />
<input type="button" value="" title="删除" class="button_deltet_01"  onclick="openWindow('../PaySystem/deleteRole.action','showrole.id=<s:property value='id'/>');" />
<input type="button" value="" title="配置权限" class="button_peizhi_01" onclick="openWindow('../PaySystem/toSetRoleSource.action','role.id=<s:property value='id'/>');" />
      </li>
    </ul>
</s:iterator>
  </div>
</div>

<div class="fate">
  <div class="title">
    <ul>
      <li class="liicon"><img src="../images/other/icon4.gif" alt="" /></li>
      <li class="lititle">分配资源</li>
      <li class="add"><input type="button" value="" title="新增资源" class="button_add_01" onclick="addResource();" /> </li>
    </ul>
  </div>
  <div class="title2">
    <ul class="ul_title1 font14px">
      <li class="lili"><b>资源URL</b></li>
      <li class="lili"><b>资源名称</b></li>
      <li class="lili"><b>操作</b></li>
    </ul>

<s:iterator id="resourcelist2" value="resourcelist2" >
    <ul class="ul_title2">
      <li class="lili"><s:property value="#resourcelist2.resourceUrl" /></li>
      <li class="lili"><s:property value="#resourcelist2.menuName" /></li>
      <li class="lili">
         <input type="button" value="" title="修改" class="button_edit_01" onclick="openWindow('../PaySystem/toUpdateResource.action','resouce.id=<s:property value='id'/>');" />
         <input type="button" value="" title="删除" class="button_deltet_01" onclick="openWindow('../PaySystem/deleteResouce.action','resouce.id=<s:property value='id'/>');"  />
      
      </li>
    </ul>
</s:iterator> 
  </div>
  
</div>


<!--<input type="button" value="新增角色" onclick="add();"> 
  
<p>
<s:iterator id="rolelist" value="rolelist" >
角色名称： <s:property value="#rolelist.roleName" />
角色id：<s:property value="#rolelist.id" />
<input type="button" value="修改" onclick="openWindow('../PaySystem/toUpdateRole.action','showrole.id=<s:property value='id'/>');"  />
<input type="button" value="删除" onclick="openWindow('../PaySystem/deleteRole.action','showrole.id=<s:property value='id'/>');" />
<input type="button" value="配置权限" onclick="openWindow('../PaySystem/toSetRoleSource.action','role.id=<s:property value='id'/>');" />
</p>
</s:iterator> -->


<!--<input type="button" value="新增资源"  onclick="addResource();" />
<p>
<s:iterator id="resourcelist2" value="resourcelist2" >
资源URL： <s:property value="#resourcelist2.resourceUrl" />
资源名称 ：<s:property value="#resourcelist2.menuName" />
<input type="button" value="修改" onclick="openWindow('../PaySystem/toUpdateResource.action','resouce.id=<s:property value='id'/>');" />
<input type="button" value="删除" onclick="openWindow('../PaySystem/deleteResouce.action','resouce.id=<s:property value='id'/>');"  />
</p>
</s:iterator> -->