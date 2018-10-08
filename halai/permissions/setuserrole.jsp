<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>用户角色配置主页面</title>
    <link href="../css/other.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript" type="text/JavaScript">
function reloadPage(){

        window.location="../PaySystem/tosetUserRole.action";
}
function add(){ 
   openWindow('../PaySystem/toaddrole');
}

function addResource(){ 
   openWindow('../PaySystem/toaddResouce');
}

</script>


<div class="fate" >
  <div class="title">
    <ul>
      <li class="liicon"><img src="../images/other/icon3.gif" alt="" /></li>
      <li class="lititle">已分配角色</li>
    </ul>
  </div>
  <div class="title2">
    <ul class="ul_title1 font14px">
      <li class="lili"><b>用户名</b></li>
      <li class="lili"><b>角色名</b></li>
      <li class="lili"><b>操作</b></li>
    </ul>
 <s:iterator id="listOne" value="listOne" >
    <ul class="ul_title1">
      <li class="lili"><s:property value="#listOne[0]" /></li>
      <li class="lili"><s:property value='#listOne[2]'/></li>
      <li class="lili">
      	<input type="button" value="" title="修改" class="button_edit_01" onclick="openWindow('../PaySystem/tosetUserRoleFinish.action','users.id=<s:property value='#listOne[1]' />');" />
		<input type="button" value="" title="删除" class="button_deltet_01" onclick="openWindow('../PaySystem/deleteUser.action','users.id=<s:property value='#listOne[1]' />');"  />
      </li>
    </ul>
 </s:iterator>
  </div>
  
</div>


<div class="fate">
  <div class="title">
    <ul>
      <li class="liicon"><img src="../images/other/icon4.gif" alt="" /></li>
      <li class="lititle">未分配角色</li>
    </ul>
  </div>
  <div class="title2">
    <ul class="ul_title1 font14px">
      <li class="lili"><b>用户名</b></li>
      <li class="lili"><b>角色名</b></li>
      <li class="lili"><b>操作</b></li>
    </ul>
<s:iterator id="lostTwo" value="lostTwo" >
    <ul class="ul_title1">
      <li class="lili"><s:property value="#lostTwo[0]" /></li>
      <li class="lili"><s:property value="#lostTwo[1]" /></li>
      <li class="lili">
         <input type="button" value="" title="设置" class="button_edit_01" onclick="openWindow('../PaySystem/tosetUserRoleFinish.action','users.id=<s:property value='#lostTwo[1]' />');" />
<input type="button" value="" title="删除" class="button_deltet_01" onclick="openWindow('../PaySystem/deleteUser.action','users.id=<s:property value='#lostTwo[1]' />');"  />
      </li>
    </ul>
</s:iterator>
  </div>
  
</div>



<!--已分配角色的用户</P>
<s:iterator id="listOne" value="listOne" >
 用户名：  <s:property value="#listOne[0]" />
 角色名：  <s:property value='#listOne[2]'/>
  <input type="button" value="修改" onclick="openWindow('../PaySystem/tosetUserRoleFinish.action','users.id=<s:property value='#listOne[1]' />');" />
<input type="button" value="删除" onclick="openWindow('../PaySystem/deleteUser.action','users.id=<s:property value='#listOne[1]' />');"  />  </p>
</s:iterator>-->

 <!--未分配角色的用户</P> 

<s:iterator id="lostTwo" value="lostTwo" >
用户名： <s:property value="#lostTwo[0]" />
用户ID ：<s:property value="#lostTwo[1]" />
 <input type="button" value="设置" onclick="openWindow('../PaySystem/tosetUserRoleFinish.action','users.id=<s:property value='#lostTwo[1]' />');" />
<input type="button" value="删除" onclick="openWindow('../PaySystem/deleteUser.action','users.id=<s:property value='#lostTwo[1]' />');"  />
</p>
</s:iterator> -->