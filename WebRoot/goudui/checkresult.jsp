<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>VIP勾兑结果</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
  </head>
  <body>
    <center>
    <s:property value="messageAction"/>
    	<h3>VIP勾兑结果</h3>
    	<div>掉单信息</div>
    	<table cellSpacing=0 cellPadding=0 width=700 align=center border=1>
    		<tr align="center" bgColor="#000000">
    			<TD bgColor=#cccccc>
			    	流水订单号 
			    </TD>
			    	<TD bgColor=#cccccc>
			    	卡号
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
    		<s:iterator id="it" value="lostTradeList">
	    		<tr align="center">
	    			<td>
	    				<s:property value="#it.orderNo"/>&nbsp;
	    			</td>
	    			<td>
	    			<s:property value="getCarNo(#it.cardNo)"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.authorizationNo"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.amount"/>&nbsp;
	    			</td>
	    			<td>
	    				<s:property value="#it.tradeDate"/>&nbsp;
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
	    	卡号
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
	<s:iterator id="it" value="someTradeList">
		<tr align="center">
			<td>
				<s:property value="#it.orderNo"/>&nbsp;
			</td>
			<td>
			<s:property value="getCarNo(#it.cardNo)"/>&nbsp;
			</td>
			<td>
				<s:property value="#it.authorizationNo"/>&nbsp;
			</td>
			<td>
				<s:property value="#it.amount"/>&nbsp;
			</td>
			<td>
				<s:property value="#it.tradeDate"/>&nbsp;
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
	    	卡号
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
	<s:iterator id="it" value="exceptionTradeList">
		<tr align="center">
			<td>
				<s:property value="#it.orderNo"/>&nbsp;
			</td>
			<td>
			<s:property value="#it.cardNo"/>&nbsp;
			</td>
			<td>
				<s:property value="#it.authorizationNo"/>&nbsp;
			</td>
			<td>
				<s:property value="#it.amount"/>&nbsp;
			</td>
			<td>
				<s:property value="#it.tradeDate"/>&nbsp;
			</td>
		</tr>
	</s:iterator>
    	</table>

    	<div>已勾兑交易(本次不勾兑)</div>
    	<table cellSpacing=0 cellPadding=0 width=700 align=center border=1>
    	<tr align="center" bgColor="#000000">
		<TD bgColor=#cccccc>
	    	流水订单号 
	    </TD>
	    	<TD bgColor=#cccccc>
	    	卡号
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
	<s:iterator id="it" value="noCheckTradeList">
		<tr align="center">
			<td>
				<s:property value="#it.orderNo"/>&nbsp;
			</td>
			<td>
			<s:property value="#it.cardNo"/>&nbsp;
			</td>
			<td>
				<s:property value="#it.authorizationNo"/>&nbsp;
			</td>
			<td>
				<s:property value="#it.amount"/>&nbsp;
			</td>
			<td>
				<s:property value="#it.tradeDate"/>&nbsp;
			</td>
		</tr>
	</s:iterator>
    	</table>
    </center>
  </body>
</html>
