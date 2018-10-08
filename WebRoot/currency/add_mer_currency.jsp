<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    
    <title>添加商户币种</title>
    
	<script language="JavaScript" src="../js/util.js"></script>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
	<script language="JavaScript" type="text/JavaScript">
		function addCourse(f) {		
			goFormWindow(f,"saveMerCurrency");
		}
	</script>  
  </head>
  
  <body>
    <center>
    	<h3>添加商户币种</h3>
    	<s:form action="saveMerCurrency" method="post" theme="simple">
	    	  <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="340" align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
	    		<tr>
	    			<td>商户号</td>
	    			<td>
	    				<s:textfield name="mer.merno"/>
	    				<!--<s:property value="mer.merno"/>
	    				<input type="hidden" name="merCurrency.merchanId" value="<s:property value="mer.id"/>">-->
	    			</td>
	    			
	    		</tr>
	    		<tr>
	    			<td>币种</td>
	    			<td>&nbsp;
	    				<s:select list="list" name="merCurrency.moneyKindNo" listKey="id" listValue="moneykindname"></s:select>
	    			</td>
	    		</tr>
	    	</table>
	    	 <input type="button" onClick="addCourse(this.form);" value="添加商户币种" />
    	</s:form>
    </center>
  </body>
</html>
