<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>

<TITLE>多次重复交易查询</TITLE>
<link rel="stylesheet" type="text/css" href="../css/head.css">

</HEAD>
  <BODY>
	<h3 align="center">多次重复交易查询 </h3>
	<br>
	<TABLE borderColor=#ffffff cellSpacing=0 cellPadding=0 width="1040" align=center 
		bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
  		<TR align=middle>
		    <TD bgColor=#cccccc>
		    	流水订单号 
		    </TD>
		    <TD bgColor=#cccccc>
		    	商户订单号
		    </TD>
		    <TD bgColor=#cccccc>
		    	商户号
		    </TD>
		    <TD bgColor=#cccccc>
				交易时间
		    </TD>
		    <TD bgColor=#cccccc>
		   		订单金额 
		    </TD>
		    <TD bgColor=#cccccc>
		    	交易币种
		    </TD>
		    <TD bgColor=#cccccc>
		    	支付通道
		    </TD>
		    <TD bgColor=#cccccc>
		    	支付情况
		    </TD>
			<TD bgColor=#cccccc>
		    	是否冻结 
		    </TD>
		    <TD bgColor=#cccccc>
		    	是否退款
		    </TD>
		    <TD bgColor=#cccccc>
		    	是否拒付
		    </TD>
		   
		    <TD bgColor=#cccccc>
		    	是否划款 
		    </TD> 
		    <TD bgColor=#cccccc>
		    	是否勾兑 
		    </TD>
		    <TD bgColor=#cccccc>
		    	备注 
		    </TD>  
	  	</TR>
	  	<s:iterator id="tradeIterator" value="list">
			<tr class="font-align">	
				<td>
					<s:property value="#tradeIterator[1].orderNo"/>&nbsp;
				</td>
				<td>
					<s:property value="#tradeIterator[1].merchantOrderNo"/>&nbsp;
				</td>
				<td>
					<s:property value="#tradeIterator[2].merno"/>&nbsp;
				</td>
				<td>
					<s:property value="#tradeIterator[1].tradeTime"/>&nbsp;
				</td>
				<td>
					<s:property value="#tradeIterator[1].tradeAmount"/>&nbsp;
				</td>
				<td>
					<s:property value="#tradeIterator[3].moneykindname"/>&nbsp;
				</td>
				<td>
					<s:property value="#tradeIterator[4].channelName"/>
				</td>
				<td>
					<s:property value="states.getStateName(#tradeIterator[1].tradeState,1)" escape="false" />&nbsp;
				</td>
				<td>
					<s:property value="states.getStateName(#tradeIterator[1].tradeState,4)" />&nbsp;
				</td>
				
				<td>
					<s:property value="states.getStateName(#tradeIterator[1].tradeState,2)" />&nbsp;
				</td>
				<td>
					<s:property value="states.getStateName(#tradeIterator[1].tradeState,3)" escape="false"/>&nbsp;
				</td>
				<td>
					<s:property value="states.getStateName(#tradeIterator[1].tradeState,8)" />&nbsp;
				</td>
				<td>
					<s:property value="states.getStateName(#tradeIterator[1].tradeState,5)" />&nbsp;
				</td>
				<td>
					<s:property value="#tradeIterator[1].remark"/>&nbsp;
				</td>
			</tr>
		</s:iterator>		
		</table>
	</BODY>
</HTML>
