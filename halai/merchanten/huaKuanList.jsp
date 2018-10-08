<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>


<html>
  <head>
  	
    <title>Settlement Details</title>
	<link rel="stylesheet" type="text/css" href="css/bail.css">
	<style>
	td,tr,table{ font-size:12px;}
	</style>
	<h3 align="center">Settlement Details</h3><br><br>
	<!-- 全选组件 -->
    <%@ include file="../include/checkAll.jsp" %>
  </head>
  
  <body>


   		<TABLE style="background:#CB7610;" cellSpacing="1" cellPadding="5" width="100%"  border="0">
  		
  		<TR align="middle" height="30">
	   <th bgColor="#F6E0B3">
	   		Serial No.
	   </th>
	   <th bgColor="#F6E0B3">
	    	Ecpss Order No. 
	    </TH>
	   <th bgColor="#F6E0B3">
	    	Merchant Order No.
	    </TH>
	    <!--<TH bgColor=#cccccc>商户号</TD> -->
	    <th bgColor="#F6E0B3">
	   		Transaction Date 
	    </TH>
	   <th bgColor="#F6E0B3">
	   		Amount
	    </TH>

	    <th bgColor="#F6E0B3">
	    	Channel
	    </TH>
	   <th bgColor="#F6E0B3">
	    	Payment Status
	    </TH>

	   <th bgColor="#F6E0B3">
	    	Check
	    </TH>
	
	  <th bgColor="#F6E0B3">
	    	Refund
	    </TH>
	   <th bgColor="#F6E0B3">
	    	Freeze 
	    </TH>

	   <th bgColor="#F6E0B3">
	    	Chargeback
	    </TH>   
	  <th bgColor="#F6E0B3">
	    	Upload Invoice Picture
	    </TH>   
	  <th bgColor="#F6E0B3">
	    	Upload Tracking No.
	    </TH>   
	   <th bgColor="#F6E0B3">
	    	Remark
	    </TH>   
	  </TR>
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
			</td>
			<!--<td>
				<s:property value="#tradeIterator.tradeMoneyName"/>&nbsp; 
			</td>-->
			<td bgColor="#FFF6E4">
				<s:property value="#tradeIterator[2].channelName"/>&nbsp;
			</td>
			<td bgColor="#FFF6E4">
<s:property value="states.getStateNameEn(#tradeIterator[0].tradeState,1)"  escape='false' />&nbsp;
			</td>
			<td bgColor="#FFF6E4">
		  	<s:property value="states.getStateNameEn(#tradeIterator[0].tradeState,5)"  escape='false' />&nbsp;
			</td>
			<td bgColor="#FFF6E4">
		  	<s:property value="states.getStateNameEn(#tradeIterator[0].tradeState,2)"  escape='false' />&nbsp;
			</td>
			<td bgColor="#FFF6E4">
		  	<s:property value="states.getStateNameEn(#tradeIterator[0].tradeState,4)"  escape='false' />&nbsp;
			</td>
			<!--<td>
				<s:property value="#tradeIterator.ishuakuanName"/>&nbsp; 
			</td>-->
			<td bgColor="#FFF6E4">
		  	<s:property value="states.getStateNameEn(#tradeIterator[0].tradeState,3)"  escape='false' />&nbsp;
			</td>
			<td bgColor="#FFF6E4">
			<s:if test="#tradeIterator[0].ispicture==null">
			NO
			</s:if>
			<s:else>
			YEW
			</s:else>	
			</td>
			<td bgColor="#FFF6E4">
			<s:if test="#tradeIterator[0].istrackno==null">
			NO
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
		
		</table> 
   		
  </body>
</html>
