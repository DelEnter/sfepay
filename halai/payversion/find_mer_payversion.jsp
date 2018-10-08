<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查看商户支付版本</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
	<%@ include file="../include/dialog.jsp"%>
	<script language="JavaScript" src="../js/util.js"></script>
	<script language="JavaScript" type="text/JavaScript">
	function reloadPage(){
		window.location="findMerPayVersion.action";
	}
	
	</script>
  </head>
  
  <body>
    <center>
    	<h3>查看商户支付版本</h3>
    	<s:form action="findMerPayVersion" method="post" theme="simple">
    		<div>
    			商户号<s:textfield name="mer.merno"></s:textfield>
    			<s:submit value="查询"></s:submit>
    			<input type="button" value="添加" onclick="openWindow('toSaveMerPayVersion','')" />
    		</div>
    		<br>
    		<table borderColor=#ffffff cellSpacing=0 cellPadding=0  align=center 
				bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
    			<tr bgColor=#cccccc align="center">
    				<td>商户号</td>
    				<td>支付版本(路径)</td>
    				<td>路径对应域名</td>
    				<td>操作人</td>
    				<td>操作时间</td>
    				<td>修改</td>
    				<td>删除</td>
    			</tr>
    			<s:iterator id="it" value="list">
	    			<tr align="center">
	    				<td>
	    					<s:property value="#it[1].merno"/>
	    				</td>
	    				<td>
	    					<s:property value="#it[0].version"/>
	    				</td>
	    				<td>
	    				<s:property value="#it[0].verurl"/>
	    				</td>
	    				<td>
	    					<s:property value="#it[0].lastMan"/>
	    				</td>
	    				<td>
	    					<s:property value="#it[0].lastDate"/>
	    				</td>
	    				<td> 
	    					<input type="button" value="修改" onclick="openWindow('toUpdateMerPayVersion','payVer.id=<s:property value="#it[0].id"/>')" />
	    				</td>   
		    			<td>
		    				<input type="button" value="删除" onclick="openWindow('deleteMerPayVersion','payVer.id=<s:property value="#it[0].id"/>')" />
		    			</td>
		    		
	    			</tr>
    			</s:iterator>
    		</table>
    	</s:form>
    </center>
  </body>
</html>
