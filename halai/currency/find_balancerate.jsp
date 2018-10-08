<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<script language="JavaScript" src="../js/util.js"></script>
<%@ include file="../include/dialog.jsp"%>

<script language="JavaScript" type="text/JavaScript">
function reloadPage(){
        window.location="findBalanceRateAction.action";
}
</script>
<html>
  <head>
	  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
  		<%@ include file="../util/calendar.jsp" %>
  		<title>结算汇率查询</title>
  </head>
  
  <body>
    <center>
    	<h3>结算汇率查询</h3>
    	<input type="button" value="添加" onclick="openWindow('toSaveBalanceAction','rate.id=<s:property value="#it[0].id"/>')" />

  		<!--<a href="toSaveBalanceAction.action">添加结算汇率</a>  -->
	    <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="840" align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
			<tr>
				<td bgColor=#cccccc>币种</td>
				<td bgColor=#cccccc>结算汇率</td>
				<td bgColor=#cccccc>结算汇率执行时间</td>
				<td bgColor=#cccccc>结算汇率设置时间</td>
				<td bgColor=#cccccc>修改</td>
				<td bgColor=#cccccc>删除</td>
			</tr>
			<s:iterator id="it" value="list">
				<tr>
					<td><s:property value="#it[1].moneykindname"/>&nbsp;</td>
					<td><s:property value="#it[0].showRate"/>&nbsp;</td>
					<td><s:property value="#it[0].executetime"/>&nbsp;</td>
					<td><s:property value="#it[0].settime"/>&nbsp;</td>
					<td><input type="button" value="修改" onclick="openWindow('findUpdateBalanceRateAction','rate.id=<s:property value="#it[0].id"/>')" /> 
						 <!-- <a href="findUpdateBalanceRateAction.action?rate.id=<s:property value="#it[0].id"/>">修改</a>-->
					</td>
					<td>
						  
					<!--<input type="button" value="删除" onclick="openWindow('deleteBalanceRateAction','rate.id=<s:property value="#it[0].id"/>')" />
						
						 	<a href="deleteBalanceRateAction.action?rate.id=<s:property value="#it[0].id"/>">删除</a>
					-->
					</td>
	    		</tr>
			</s:iterator>
		</table>
	</center>
  </body>
</html>
