<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>商户币种</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">

  </head>
  
  <body> 
  	<center>
  		<s:form action="" method="post" theme="simple">
	  		<table>
	  			<tr>
	  				<td>商户</td>
	  				<td><s:textfield name=""/></td>
	  			</tr>
	  			<tr>
	  				<td>币种</td>
	  				<td>
	  					<s:select list="list" name="" listKey="key", listValue="value" ></s:select>
	  				</td>
	  			</tr>
	  			<s:submit value="修改"/>
	  		</table>
  		</s:form>
  	</center>
  </body>
</html>
