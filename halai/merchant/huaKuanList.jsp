<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


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


   		<TABLE style="background:#CB7610;" cellSpacing="1" cellPadding="5" width="100%"  border="0">
  		
  		<TR align="middle" height="30">
	   <th bgColor="#F6E0B3">
	   		序列
	   </th>
	   <th bgColor="#F6E0B3">
	    	流水订单号 
	    </TH>
	   <th bgColor="#F6E0B3">
	    	商户订单号
	    </TH>
	    <!--<TH bgColor=#cccccc>商户号</TD> -->
	    <th bgColor="#F6E0B3">
	   		交易日期 
	    </TH>
	   <th bgColor="#F6E0B3">
	   		金额
	    </TH>
	    <!--<TH bgColor=#cccccc>
	    	交易币种
	    </TH> -->
	    <th bgColor="#F6E0B3">
	    	通道
	    </TH>
	   <th bgColor="#F6E0B3">
	    	支付情况
	    </TH>

	   <th bgColor="#F6E0B3">
	    	勾兑 
	    </TH>
	
	  <th bgColor="#F6E0B3">
	    	退款
	    </TH>
	   <th bgColor="#F6E0B3">
	    	冻结 
	    </TH>
	   
	  <TH bgColor="#F6E0B3">
	    	划款 
	    </TH>    
	   <th bgColor="#F6E0B3">
	    	拒付
	    </TH>   
	  <th bgColor="#F6E0B3">
	    	传图
	    </TH>   
	  <th bgColor="#F6E0B3">
	    	传号
	    </TH>   
	   <th bgColor="#F6E0B3">
	    	备注
	    </TH>   
	  </TR>
	    	<s:set name="tongjiamount" value="0" />
	  <s:iterator id="tradeIterator" value="tradeList2" status="i">
		<tr height="30" align="middle">
			<td bgColor="#FFF6E4">
				<s:property value="#i.index+1"/>
			</td>
			<td bgColor="#FFF6E4">
				<s:property value="#tradeIterator[0].rorderno"/>&nbsp;
			</td>
			<td bgColor="#FFF6E4">
				<s:property value="#tradeIterator[0].merchantorderno"/>&nbsp;
			</td>
			<!--<td>
				<s:property value="#tradeIterator.merchantno"/>&nbsp; 
			</td>-->
			<td bgColor="#FFF6E4">
					<s:property value="#tradeIterator[0].tradetime"/>&nbsp;
			</td>
			<td bgColor="#FFF6E4">
					<s:property value="#tradeIterator[0].ordercount"/>&nbsp;
					<s:set name="tongjiamount" value="#tongjiamount+#tradeIterator[0].ordercount" />
			</td>
			<!--<td>
				<s:property value="#tradeIterator.tradeMoneyName"/>&nbsp; 
			</td>-->
			<td bgColor="#FFF6E4">
				<s:property value="#tradeIterator[2].channelName"/>&nbsp;
			</td>
			<td bgColor="#FFF6E4">
<s:property value="states.getStateName(#tradeIterator[0].tradeState,1)"  escape='false' />&nbsp;
			</td>
			<td bgColor="#FFF6E4">
		  	<s:property value="states.getStateName(#tradeIterator[0].tradeState,5)"  escape='false' />&nbsp;
			</td>
			<td bgColor="#FFF6E4">
			<s:if test="#tradeIterator[0].tradeState.substring(1,2)==2">
			<font color="red"><s:property value="#tradeIterator[0].backAcount"/></font>
				
			</s:if>
			<s:else>
				<s:property value="states.getStateName(#tradeIterator[0].tradeState,2)"  escape='false'  />
			</s:else>&nbsp;
			</td>
			<td bgColor="#FFF6E4">
		  	<s:property value="states.getStateName(#tradeIterator[0].tradeState,4)"  escape='false' />&nbsp;
			</td>
			<td bgColor="#FFF6E4">
				<s:property value="states.getStateName(#tradeIterator[0].tradeState,8)"  escape='false' />&nbsp; 
			</td>
			<td bgColor="#FFF6E4">
		  	<s:property value="states.getStateName(#tradeIterator[0].tradeState,3)"  escape='false' />&nbsp;
			</td>
			<td bgColor="#FFF6E4">
			<s:if test="#tradeIterator[0].ispicture==null">
			未传图
			</s:if>
			<s:else>
			已传图
			</s:else>	
			</td>
			<td bgColor="#FFF6E4">
			<s:if test="#tradeIterator[0].istrackno==null">
			未传号
			</s:if>
			<s:else>
	        <s:property value="#tradeIterator[0].istrackno"/>&nbsp;
			</s:else>	
			</td>
			<td bgColor="#FFF6E4">
				<s:property value="#tradeIterator[0].remark"/>&nbsp;
			</td>
		</tr>
		</s:iterator>
		<tr height="30" align="middle">
		<td colspan="4" align="right" bgColor="#FFF6E4">
			Total : &nbsp;
		</td>
		<td bgColor="#FFF6E4">
			<s:property value="#tongjiamount"/>&nbsp;
		</td>
		<td colspan="10"  align="right" bgColor="#FFF6E4">
		&nbsp;
	</td>
		</tr>
		</table> 
   		
  </body>
</html>
