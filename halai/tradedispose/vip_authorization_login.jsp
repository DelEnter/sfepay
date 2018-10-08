<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>VIP交易处理授权人登陆</title>
	<LINK href="../css/head.css" type=text/css rel=stylesheet>
  </head>
  
  <body>
    <center>
    	<h3>VIP交易处理授权人登陆</h3>
    	<s:form action="VIPAuthorizationLong" method="post" theme="simple">
    		<table>
    			<tr>
    				<td>授权人</td>
    				<td>
    					<s:textfield name="user.userName"/>
    				</td>
    			</tr>
    			<tr>
    				<td>密码</td>
    				<td>
    					<s:password name="user.password"/>
    				</td>
    			</tr>
    			<tr>
    				<td>
    					<s:submit value="登陆"/>
    				</td>
    			</tr>
    		</table>
    	</s:form>
    </center>&nbsp;
  </body>
</html>
