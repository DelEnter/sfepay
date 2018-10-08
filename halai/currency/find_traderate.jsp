<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<script language="JavaScript" type="text/JavaScript">
function reloadPage(){
        window.location="findTradeRateAction.action";
}
</script>
<html>
  <head>
	  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
  		<%@ include file="../util/calendar.jsp" %>
  		<title>交易汇率查询</title>
  </head>
  
  <body>
    <center>
    	<h3>交易汇率查询</h3>
    	<input type="button" value="添加" onclick="openWindow('toSaveTradeRateAction','rate.id=<s:property value="#it[0].id"/>')" />

	    <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="840" align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
			<tr>
				<td bgColor=#cccccc>币种</td>
				<td bgColor=#cccccc>交易汇率</td>
				<td bgColor=#cccccc>交易汇率执行时间</td>
				<td bgColor=#cccccc>交易汇率设置时间</td>
				<td bgColor=#cccccc>操作人</td>
				<td bgColor=#cccccc>修改</td>
				<td bgColor=#cccccc>删除</td>
			</tr>
			<s:iterator id="it" value="list">
				<tr>
					<td><s:property value="#it[1].moneykindname"/>&nbsp;</td>
					<td><s:property value="#it[0].showRate"/>&nbsp;</td>
					<td><s:property value="#it[0].executetime"/>&nbsp;</td>
					<td><s:property value="#it[0].settime"/>&nbsp;</td>
					<td><s:property value="#it[0].creater"/>&nbsp;</td>
					<td> <input type="button" value="修改" onclick="openWindow('findUpdateTradeRateAction','rate.id=<s:property value="#it[0].id"/>')" /></td>
					<td> <input type="button" value="删除" onclick="openWindow('deleteTradeRateAction','rate.id=<s:property value="#it[0].id"/>')" /></td>
	    		</tr>
			</s:iterator>
		</table>
	</center>
  </body>
</html>
