<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>3D勾兑结果</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
  </head>
  <body>
    <center>
    	<h3>3D勾兑结果</h3>
    	<div>掉单信息</div>
    	<table cellSpacing=0 cellPadding=0 width=700 align=center border=1>
    		<tr align="center" bgColor="#000000">
    			<TD bgColor=#cccccc>
			    	流水订单号 
			    </TD>
			   	<TD bgColor=#cccccc>
			   		授权号
			   	</TD>
			    <TD bgColor=#cccccc>
			   		交易金额 
			    </TD>
			    <TD bgColor=#cccccc>
					交易时间
			    </TD>
			     <TD bgColor=#cccccc>
					支付状态
			    </TD>
    		</tr>
    		<s:iterator id="it" value="lostList">
	    		<tr align="center">
	    			<td>
	    				<s:property value="#it.orderNo"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.VIPAuthorizationNo"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.tradeAmount"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.tradeTime"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="states.getStateName(#it.tradeState,1)" escape="false"/>&nbsp;
	    			</td>
	    		</tr>
    		</s:iterator>
    	</table>
    	
    	
    	<div>重复单交易</div>
    	<table cellSpacing=0 cellPadding=0 width=700 align=center border=1>
    		<tr align="center" bgColor="#000000">
    			<TD bgColor=#cccccc>
			    	流水订单号 
			    </TD>
			   	<TD bgColor=#cccccc>
			   		授权号
			   	</TD>
			    <TD bgColor=#cccccc>
			   		交易金额 
			    </TD>
			    <TD bgColor=#cccccc>
					交易时间
			    </TD>
    		</tr>
    		<s:iterator id="it" value="echoList">
	    		<tr align="center">
	    			<td>
	    				<s:property value="#it.orderNo"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.VIPAuthorizationNo"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.tradeAmount"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.tradeTime"/>&nbsp;
	    			</td>
	    		</tr>
    		</s:iterator>
    	</table>
    	
    	<div>异常单信息</div>
    	<table cellSpacing=0 cellPadding=0 width=700 align=center border=1>
    		<tr align="center" bgColor="#000000">
    			<TD bgColor=#cccccc>
			    	流水订单号 
			    </TD>
			   	<TD bgColor=#cccccc>
			   		授权号
			   	</TD>
			    <TD bgColor=#cccccc>
			   		交易金额 
			    </TD>
			    <TD bgColor=#cccccc>
					交易时间
			    </TD>
    		</tr>
    		<s:iterator id="it" value="excptionList">
	    		<tr align="center">
	    			<td>
	    				<s:property value="#it.orderNo"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.VIPAuthorizationNo"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.tradeAmount"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.tradeTime"/>&nbsp;
	    			</td>
	    		</tr>
    		</s:iterator>
    	</table>
    </center>
  </body>
</html>
