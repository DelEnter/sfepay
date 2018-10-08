<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>

<script language="JavaScript" type="text/JavaScript">
function reloadPage(){
        window.location="toMerSucRateInfo.action";
}

</script>
<html>
	
  <head>
  	<LINK href="../css/head.css" type=text/css rel=stylesheet>
    <title>备注控制信息</title>
      		
  </head>
  <body>
    <center>
    	<h3><br></h3><h3>备注控制信息</h3>
    	<div>
    		<input type="button" value="添加" onclick="openWindow('toAddMerSucRate','')" />
    	</div>
    	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="640" align=center 
	bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
    		<tr>
    			<td bgColor=#cccccc>序号</td>
    			<td bgColor=#cccccc>备注信息</td>
    			<td bgColor=#cccccc>执行状态</td>
    			<td bgColor=#cccccc>执行时间</td>
    			<td bgColor=#cccccc>操作人</td>
    			<td bgColor=#cccccc>修改</td>
    			<td bgColor=#cccccc>删除</td>

    		</tr>
    		<s:iterator id="il" value="li" status="s">
	    		<tr><td><s:property value="#s.index+1" /></td>
	    			<td><s:property value="#il.tradeRemark"/>&nbsp;</td>
	    			<td><s:if test="#il.status==1">正在使用</s:if><s:else>停用</s:else></td>
	    			<td><s:property value="#il.lastDate"/>&nbsp;</td>
	    			<td><s:property value="#il.lastMan"/>&nbsp;</td>
	    			<td> <input type="button" value="修改" onclick="openWindow('toAddMerSucRate','merSucRate.id=<s:property value="#il.id"/>')" /></td>   
	    			<td> <input type="button" value="删除" onclick="openWindow('deleteMerSucRate','merSucRate.id=<s:property value="#il.id"/>')" /></td>
	    		</tr>
    		</s:iterator>
    	</table>
    </center>
  </body>
</html>
