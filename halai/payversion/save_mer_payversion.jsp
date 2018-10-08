<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>添加商户支付版本</title>
	<link rel="stylesheet" type="text/css" href="../css/css.css">
	<script language="JavaScript" src="../js/util.js"></script>
    <%@ include file="../include/dialog.jsp"%>
     <script language="JavaScript" type="text/JavaScript">
		function addCourse(f) {	
			goFormWindow(f,"saveMerPayVersion");
		}
	</script>  
  </head>
  
  <body>
    <center>
    	<h3>添加商户支付版本</h3>
    	<s:form action="saveMerPayVersion" method="post" theme="simple">
	    	<table>
	    		<tr>
	    			<td>商户号</td>
	    			<td>
	    				<s:textfield name="mer.merno"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>支付版本</td>
	    			<td>
	    				<s:textfield name="payVer.version"/>
	    			</td>
	    		</tr>
	    		<tr>
	    		<td>路径域名</td>
	    		<td>
	    		<s:textfield name="payVer.verurl"/>
	    		</td>
	    		</tr>
	    	</table>
	    	  <input type="submit" value="添加商户支付模板" />
    	</s:form>
    </center>
  </body>
</html>
