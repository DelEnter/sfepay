<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="../include/dialog.jsp"%>

<script language="JavaScript" type="text/JavaScript">
	
	
	
</script>
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
    <title>物流开通状态</title> 
  </head>
  <body>
   	<center>
   		<h3>物流开通状态</h3>
   		
   		<div align="center" style="border-bottom:1px solid #aca899; font-size:14px" >
			商户号：<s:textfield name="merid"/>		
		</div>
		
		<s:form action="expressReg" name="form1" method="post" theme="simple">
	   		 <table borderColor=#ffffff cellSpacing=0 cellPadding=0  align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10" >
				
	    		<tr >
	    			<td align=center>
		    			<s:if test="status==1">
				 			<s:radio name="expopenstatus" list="%{#{'1':'已开通','0':'未开通'}}" value="1"></s:radio>
						 </s:if>
					</td>
					<td align=center>
			 		<s:else>
			 			<s:radio name="expopenstatus" list="%{#{'1':'已开通','0':'未开通'}}" value="0"></s:radio>		 
			 		</s:else>
			 		</td>
	    		</tr>   
	    		<tr>
	    			<td align=right>
		    			<s:submit value="修改"/>
		    		</td>
	    		</tr>
	    		<input id="merid" name="merid" type="hidden" value="<s:property value="merid"/>">
	    	</table>
    	</s:form>
   	</center>
  </body>
</html>
				