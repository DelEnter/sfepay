<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改商户支付模块</title>
   
	<link rel="stylesheet" type="text/css" href="../css/head.css">
	<%@ include file="../include/dialog.jsp"%>
	<script language="JavaScript" src="../js/util.js"></script>
	<script language="JavaScript" type="text/JavaScript">
		function addCourse(f) {		
			goFormWindow(f,"updateMerPayVersion");
		}
	</script>  
  </head>
  
  <body>
    <center>
    	<h3>修改商户支付模块</h3>
    	
    	<s:form action="" method="post" theme="simple">
    		<input type="hidden" name="payVer.id" value="<s:property value="obj[0].id"/>">
	    	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="200" align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
	    	 	<tr>
	    			<td>商户号</td>
	    			<td><s:property value="obj[1].merno"/></td>
	    		</tr>
	    		<tr>
	    			<td>支付模块</td>
	    			<td>
	    				<input type="text" name="payVer.version" value="<s:property value="obj[0].version"/>">
	    			</td>
	    		</tr>
	    		<tr>
	    		<td>路径域名</td>
	    		<td>
	    		<input type="text" name="payVer.verurl" value="<s:property value="obj[0].verurl"/>">
	    		</td>
	    		</tr>
	    	</table>
	    	 <input type="button" onClick="addCourse(this.form);" value="修改" />
    	</s:form>
    </center>
  </body>
</html>
