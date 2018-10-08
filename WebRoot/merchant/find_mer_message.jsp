<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查询商户信息</title>
   
	<link rel="stylesheet" type="text/css" href="../css/head.css">

  </head>
  
  <body>
    <center>
    	<h3>查询商户信息</h3>
    	<s:form action="managerMerMessage" method="post" theme="simple">
    		<table>
    			<tr>
    				<td>商户号</td>
    				<td>
    					<s:textfield name="mer.merno"/>
    				</td>
    			</tr>
    			<tr>
    				<td>
    					<s:submit value="查询"/>
    				</td>
    			</tr>
    		</table>
    	</s:form>
    </center>
  </body>
</html>
