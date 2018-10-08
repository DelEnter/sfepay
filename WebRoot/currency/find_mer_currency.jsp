<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<script language="JavaScript" type="text/JavaScript">
	function reloadPage(){
		var merno = document.getElementById("merid").value;
		window.location="findMerCurrency.action?mer.merno="+merno;
	}
</script>
<html>
  <head>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
    <title>查询商户币种</title>
  
  </head>
  
  <body>
   	<center>
   		<h3>查询商户币种</h3>
   		<s:form action="findMerCurrency" method="post" theme="simple">
	   		<table>
	   			<tr>
	   				<td>商户号</td>
	   				<td>
	   					<s:textfield name="mer.merno"/>
	   				</td>
	   				<td> 
	   					<s:submit value="查询"/>
	   				</td>
	   				<td>
	   					<input type="button" value="添加商户币种" onclick="openWindow('toSaveMerCurrency','mer.merno=<s:property value="mer.merno"/>')" />
	   				</td>
	   			</tr>
	   		</table>
   		</s:form>
		  <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="340" align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
			<tr bgColor=#cccccc>
				<td>商户</td>
				<td>币种</td>
				<td>修改</td>
				<td>删除</td>
			</tr>
			<input type="hidden" id="merid" value="<s:property value="merCurrency.merchanId"/>">
			<s:iterator id="it" value="list">
				<tr>		
					<td>
						<s:property value="#it[0].merno"/>
					</td>
					<td>
						<s:property value="#it[2].moneykindname"/>
					</td>
					
					<td> <input type="button" value="修改" onclick="openWindow('toUpdateMerCurrency','merCurrency.id=<s:property value="#it[1].id"/>')" /></td>
					<td> <input type="button" value="删除" onclick="openWindow('deleteMerCurrency','merCurrency.id=<s:property value="#it[1].id"/>')" /></td>
				</tr>
			</s:iterator>
		</table>
   	</center>
  </body>
</html>
				