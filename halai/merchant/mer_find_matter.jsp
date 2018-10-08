<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>商户查看问题单</title>

	<link rel="stylesheet" type="text/css" href="../css/head.css">
  </head>
  
  <body>
    <center>
    	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="1200" align=center 
			bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
    		<tr>
	   			<td>流水订单号</td>
	   			<td>订单号</td>
	   			<td>支付状态</td>
	   			<td>交易金额</td>
	   			<td>交易时间</td>
	   			<td>审单日期</td>
	   			<td>备注</td>
    		</tr>
    		<tr>
    			<s:iterator id="it" value="info.result">
	    			<td>
	    				<s:property value="#it.orderNo"/>	
	    			</td>
	    			<td>
	    				<s:property value="#it.merchantOrderNo"/>	
	    			</td>
	    			<td>
	    				<s:property value="#it.tradeState"/>	
	    			</td>
	    			<td>
	    				<s:property value="#it.tradeAmount"/>	
	    			</td>
	    			<td>
	    				<s:property value="#it.tradeTime"/>	
	    			</td>
	    			<td>
	    				<s:property value="#it."/>	
	    			</td>
	    			<td>
	    				<s:property value="#it."/>	
	    			</td>
    			</s:iterator>
    		</tr>
    	</table>
    </center>
  </body>
</html>
