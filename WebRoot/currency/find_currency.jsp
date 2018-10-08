<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>

<script language="JavaScript" type="text/JavaScript">
function reloadPage(){
        window.location="findCurrencyAction.action";
}

</script>
<html>
	
  <head>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
    <title>币种查询</title>
      		
  </head>
  <body>
    <center>
    	<h3><br></h3><h3>币种查询</h3>
    	<div>
    		<input type="button" value="添加" onclick="openWindow('toAddCurrencyAction','moneykind.id=<s:property value="#il.id"/>')" />
    	</div>
    	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="640" align=center 
	bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
    		<tr>
    			<td bgColor=#cccccc>币种编号</td>
    			<td bgColor=#cccccc>币种名称</td>
    			<td bgColor=#cccccc>执行时间</td>
    			<td bgColor=#cccccc>操作人</td>
    			<td bgColor=#cccccc>修改</td>
    			<td bgColor=#cccccc>删除</td>

    		</tr>
    		<s:iterator id="il" value="li">
	    		<tr>
	    			<td><s:property value="#il.moneykindno"/>&nbsp;</td>
	    			<td><s:property value="#il.moneykindname"/>&nbsp;</td>
	    			<td><s:property value="#il.lastDate"/>&nbsp;</td>
	    			<td><s:property value="#il.lastMan"/>&nbsp;</td>
	    			<td> <input type="button" value="修改" onclick="openWindow('findUpdateCurrencyAction','moneykind.id=<s:property value="#il.id"/>')" /></td>   
	    			<td> <input type="button" value="删除" onclick="openWindow('deleteCurrencyAction','moneykind.id=<s:property value="#il.id"/>')" /></td>
	    		</tr>
    		</s:iterator>
    	</table>
    </center>
  </body>
</html>
