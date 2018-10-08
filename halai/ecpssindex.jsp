<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>首页</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<style type="text/css">
<!--
.STYLE2 {color: #999999; }
-->
</style>
</head>
<body>

<table width="90%" border="0" cellspacing="0" cellpadding="0">
  
  <tr>
    <td width="34%"><span class="logo"><img src="images/logo.gif"></span></td>
    <td width="33%">&nbsp;</td>
    
    <td width="33%" valign="bottom"><table width="100%" border="0" align="right" cellpadding="0" cellspacing="8">
      <tr>
        <td align="right"><a href="logout" class="STYLE2">退出系统</a></td>
        <td align="right"><a href="toModifyPwd" class="STYLE2">修改密码</a></td>
      </tr>
      <tr>
        <td align="right">用户名：
          <font color="red"><s:property value='users.userName'/></font></td>
        <td align="right">角色：<font color="red"><s:property value="#session.user.roleName"/></font></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td colspan="3">&nbsp;</td>
  </tr>
  <tr>
    <td colspan="3"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="3%" background="images/other/top_bg.gif" align="left"><img src="images/other/left.gif" alt=""></td>
        <td width="94%" background="images/other/top_bg.gif">&nbsp;</td>
        <td width="3%" background="images/other/top_bg.gif" align="right"><img src="images/other/right.gif" alt=""></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="5" colspan="3"></td>
  </tr>
</table>

<!--<div style="clear">&nbsp;</div>
<div class="header">
	<div class="logo"></div>
<ul class="hbtu">
    	<li><a href="../login.html">退出系统</a></li>
    	<li><a href="#">修改密码</a></li>
    </ul>
    <p class="hinfo"></p>
</div>
<div class="menu">
	<ul class="menu-t">
    </ul>
	<ul class="menu-b" style="padding-left:20px;">
    </ul>
</div> -->
<iframe id="main_text" name="main_view" src="PaySystem/admin.action" width="100%" height="900" marginwidth="0" marginheight="0" frameborder="0" />
</body>
</html>

