<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <title>操作员登陆</title>
	<LINK href="../css/head.css" type=text/css rel=stylesheet>
  </head>
  
  <body>
    <center>
    	<h3>VIP操作员登陆</h3>
    	<s:form action="VIPLong" method="post" theme="simple">
	    	<table>
	    		<tr>
	    			<td>用户名</td>
	    			<td><s:textfield name="user.userName"/></td>
	    		</tr>
	    		<tr>
	    			<td>密码</td>
	    			<td><s:password name="user.password"/></td>
	    		</tr>
	    		
	    	</table>
	    	<s:submit value="登陆"/>
    	</s:form>
    </center>
  </body>
</html>
