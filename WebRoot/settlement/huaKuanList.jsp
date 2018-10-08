<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  	
    <title>划款明细</title>
	<link rel="stylesheet" type="text/css" href="css/bail.css">
	<style>
	td,tr,table{ font-size:12px;}
	</style>
	<h3 align="center">划款明细</h3><br><br>
	<!-- 全选组件 -->
    <%@ include file="../include/checkAll.jsp" %>
  </head>
  
  <body>
   	
   		 <TABLE borderColor=#ffffff cellSpacing=0 cellPadding=0 width="1240" align=center 
	bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
  		
  		<TR align=middle>
	   <th bgColor=#cccccc>
	   		序列
	   </th>
	   		<TH bgColor=#cccccc>
	   		商户订单号
	   		</TH>
	    <TH bgColor=#cccccc>
	    	流水订单号 
	    </TH>
	    <TH bgColor=#cccccc>商户号</TD>
	     <TH bgColor=#cccccc>
	   		交易日期 
	    </TH>
	    <TH bgColor=#cccccc>
	   		金额 
	    </TH>

	    <TH bgColor=#cccccc>
	    	交易币种
	    </TH>
	    <TH bgColor=#cccccc>
	    	通道
	    </TH>
	    <TH bgColor=#cccccc>
	    	支付情况
	    </TH>

	    <TH bgColor=#cccccc>
	    	勾兑 
	    </TH>
	
	    <TH bgColor=#cccccc>
	    	退款
	    </TH>
	    <TH bgColor=#cccccc>
	    	冻结 
	    </TH>
	   
	    <TH bgColor=#cccccc>
	    	划款 
	    </TH>   
	    <TH bgColor=#cccccc>
	    	拒付
	    </TH>   
	    <TH bgColor=#cccccc>
	    	传图
	    </TH>   
	    <TH bgColor=#cccccc>
	    	传号
	    </TH>   
	    <TH bgColor=#cccccc>
	    	备注
	    </TH>   
	  </TR>
	  <s:iterator id="tradeIterator" value="tradeList2" status="i">
		<tr class="font-align">
			<td>
				<s:property value="#i.index+1"/>
			</td>
			<td>
				<s:property value="#tradeIterator[0].merchantorderno"/>&nbsp;
			</td>
			<td>
				<s:property value="#tradeIterator[0].rorderno"/>&nbsp;
			</td>
			<td>
			<s:property value="#tradeIterator[1].merno"/>&nbsp;
			
			</td>
			<td>
				<s:property value="#tradeIterator[0].tradetime"/>&nbsp;
			</td>
			<td>
				<s:property value="#tradeIterator[0].ordercount"/>&nbsp;
			</td>

			<td>
			 <s:property value="states.getCurrencyTypeByNo(#tradeIterator[0].moneytype)" />&nbsp;
			</td>
			<td>
				<s:property value="#tradeIterator[2].channelName"/>&nbsp;
			</td>
			<td>
		  	<s:property value="states.getStateName(#tradeIterator[0].tradeState,1)"  escape='false' />&nbsp;
			</td>
			<td>
		  	<s:property value="states.getStateName(#tradeIterator[0].tradeState,5)"  escape='false' />&nbsp;
			</td>
			<td>
			<s:if test="#tradeIterator[0].tradeState.substring(1,2)==2">
			<font color="red"><s:property value="#tradeIterator[0].backAcount"/></font>
		</s:if>
		<s:else>
			<s:property value="states.getStateName(#tradeIterator[0].tradeState,2)"  escape='false'  />
		</s:else>&nbsp;
			</td>
			<td>
		  	<s:property value="states.getStateName(#tradeIterator[0].tradeState,4)"  escape='false' />&nbsp;
			</td>
			<td>
		  	<s:property value="states.getStateName(#tradeIterator[0].tradeState,8)"  escape='false' />&nbsp;
			</td>
			<td>
		  	<s:property value="states.getStateName(#tradeIterator[0].tradeState,3)"  escape='false' />&nbsp;
			</td>
			<td>
			<s:if test="#tradeIterator[0].ispicture==null">
			未传图
			</s:if>
			<s:else>
			已传图
			</s:else>		
			</td>
			<td>
			<s:if test="#tradeIterator[0].istrackno==null">
			未传号
			</s:if>
			<s:else>
	        <s:property value="#tradeIterator[0].istrackno"/>&nbsp;
			</s:else>				
			</td>
			<td>
				<s:property value="#tradeIterator[0].remark"/>&nbsp;
			</td>
		</tr>
		</s:iterator>
		
		</table>
   		<table align="center">
   			<tr>
	  			<td>
	  			</td>
	  		</tr>
   		</table>
  </body>
</html>
