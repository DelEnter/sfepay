<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>修改商户币种</title>
  
	<link rel="stylesheet" type="text/css" href="../css/head.css">
<script language="JavaScript" src="../js/util.js"></script>

  </head>
     <script language="JavaScript" type="text/JavaScript">
		function addCourse(f) {		
			goFormWindow(f,"updateMerCurrency");
		}
	</script>  
  <body>
   	<center>
   		<h3>修改商户币种</h3>
   		<s:form action="updateMerCurrency" theme="simple" method="post">
   		   <div id="resizetable" width="400" height="200" style=" width:300px; height:300px; text-align:center">
   			<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="200" align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
				<input type="hidden" name="merCurrency.id" value="<s:property value="merCurrency.id"/>">
				<tr>	
					<td>商户号</td>
					<td>
						<input type="text" name="mer.merno" value="<s:property value="obj[0]"/>"/>
					</td>
				</tr>
				<tr>
					<td>币种</td>
					<td>
						<s:select list="list" name="merCurrency.moneyKindNo" listKey="id" listValue="moneykindname"/>
					</td>
				</tr>
				
			</table>
			    <input type="button" onClick="addCourse(this.form);" value="修改" />
			</div>
			
		</s:form>
   	</center>
  </body>
</html>
