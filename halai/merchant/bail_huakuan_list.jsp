<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <title>保证金划款明细表</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
  </head>
  
  <body>
    <center>
    	<h3 align="center" style=" font-size:24px; color:#000000; background:#ffffff">划款明细</h3>
    	<TABLE style="background:#CB7610;" cellSpacing="1" cellPadding="5" width="100%"  border="0">
  		
  		<TR align="middle" height="30">

			    <th bgColor="#F6E0B3">
			    	流水订单号  
			    </th>
			    <th bgColor="#F6E0B3">
			    	商户订单号
			    </th>
			    <th bgColor="#F6E0B3">
			   		交易日期 
			    </th>
			    <th bgColor="#F6E0B3">
			   		交易金额
			    </th>
			    <th bgColor="#F6E0B3">
			    	交易币种
			    </th>
			    <th bgColor="#F6E0B3">
			    	通道
			    </th>
			    <th bgColor="#F6E0B3">
			    	支付情况
			    </th>
			    <th bgColor="#F6E0B3">
			    	是否勾兑 
			    </th>
			
			   <th bgColor="#F6E0B3">
			    	是否退款
			   </th>
			    <th bgColor="#F6E0B3">
			    	是否冻结 
			    </th>
			   
			    <th bgColor="#F6E0B3">
			    	是否划款 
			    </th> 
			   <th bgColor="#F6E0B3">
			    	是否拒付
			    </TD>   
			    <th bgColor="#F6E0B3">
			    	是否传图
			    </th>
			    <th bgColor="#F6E0B3">
			    	是否传号
			    </th>  
			    <th bgColor="#F6E0B3">
			    	备注
			    </th> 
		  	</TR>
		  	<s:iterator id="it" value="list">
			  <tr align="center">
			  		<td bgColor="#FFF6E4">
			  		<s:property value="#it[0].orderNo"/>
			  	</td>
			  		<td bgColor="#FFF6E4">
			  		<s:property value="#it[0].merchantOrderNo"/>
			  	</td>
			  		<td bgColor="#FFF6E4">
			  		<s:property value="#it[0].tradeTime"/>
			  	</td>
			  		<td bgColor="#FFF6E4">
			  		<s:property value="#it[0].tradeAmount"/>
			  	<td bgColor="#FFF6E4">
			  		<s:property value="#it[2].moneykindname"/>
			  	</td>
			  	<td bgColor="#FFF6E4">
			  		<s:property value="#it[3].channelName"/>
			  	</td>
			  	<td bgColor="#FFF6E4">
			  		<s:property value="states.getStateName(#it[0].tradeState,1)" escape="false"/>&nbsp;
			  	</td>
			  	<td bgColor="#FFF6E4">
			  		<s:property value="states.getStateName(#it[0].tradeState,5)" escape="false"/>&nbsp;
			  	</td>
			  	<td bgColor="#FFF6E4">
			  		<s:property value="#it[0].backCount" />&nbsp;
			  	</td>
			  	<td bgColor="#FFF6E4">
			  		<s:property value="states.getStateName(#it[0].tradeState,4)" escape="false"/>&nbsp;
			  	</td>
			  	<td bgColor="#FFF6E4">
			  		<s:property value="states.getStateName(#it[0].tradeState,8)" escape="false"/>&nbsp;
			  	</td>
			  	<td bgColor="#FFF6E4">
			  		<s:property value="states.getStateName(#it[0].tradeState,3)" escape="false" />&nbsp;
			  	</td>
			  	<td bgColor="#FFF6E4">
			  		<s:if test="#it[0].isPicture!=null">
			  			已上传
			  		</s:if>
			  		<s:else>
			  			未上传
			  		</s:else>&nbsp;
			  	</td>
			  	<td bgColor="#FFF6E4">
			  		<s:if test="#it[0].isTrackNo!=null">
			  			<s:property value="#it[0].isTrackNo"/>
			  		</s:if>
			  		<s:else>
			  			未上传
			  		</s:else>&nbsp;
			  	</td>
			  	<td bgColor="#FFF6E4">
			  		<s:property value="#it[0].remark"/>&nbsp;
			  	</td>
			  </tr>
		  	</s:iterator>
    	</table>
    </center>
  </body>
</html>
