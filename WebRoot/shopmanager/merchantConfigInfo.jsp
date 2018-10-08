<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
	<title>商户具体设置信息</title>
</head>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<div align="center">
	<h3>商户具体设置信息</h3>
</div>
<s:form name="getMerchantInfo" action="getMerchantInfo">
<div align="center" style="border-bottom:1px solid #aca899; font-size:14px" >
			商户号：<input type="text" name="merno" size="15" value="<s:property value="merno"/>"/>
			<input type="submit"  value="查询" />
	</div>
	<div align="center">
		<h6>商户通道信息</h6>
	</div>
   <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="800" align=center 
	bgColor=#ffffff borderColorLight=#000000 border=1 height="10" >
    		<tr>
    			<td bgColor=#cccccc>商户号</td>
    			<td bgColor=#cccccc>通道</td>
    			<td bgColor=#cccccc>结算周期</td>
    			<td bgColor=#cccccc>结算费率</td>
    			<td bgColor=#cccccc>保证金周期</td>
    			<td bgColor=#cccccc>保证金费率</td>
    			<td bgColor=#cccccc>执行时间</td>
    			<td bgColor=#cccccc>状态</td>
    		</tr>
    		<s:iterator id="m" value="merChannelList" >
	    		<tr>
	    			<td><s:property value="#m[0]" />&nbsp;</td>
	    			<td><s:property value="#m[1]"/>&nbsp;</td>
	    			<td><s:property value="#m[2].balanceCycle"/>天&nbsp;</td>
	    			<td><s:property value="#m[2].balanceCharge"/>&nbsp;</td>
	    			<td><s:property value="#m[2].bailCycle"/>天&nbsp;</td>
	    			<td><s:property value="#m[2].bailCharge"/>&nbsp;</td>
	    			<td><s:property value="#m[2].executeTime"/>&nbsp;</td>
	    			<td>
	    			<s:if test="#m[2].onoff==1">
	    				已开通
	    			</s:if>
	    			<s:else>
						已关闭
	    			</s:else>
	    			</td>
	    			
	    		</tr>
    		</s:iterator>
    	</table>
    	<div align="center">
		<h6>商户卡种通道信息</h6>
	</div>
   <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="800" align=center 
	bgColor=#ffffff borderColorLight=#000000 border=1 height="10" >
    	<tr>
    	<td bgColor=#cccccc>商户号</td>
    	<td bgColor=#cccccc>通道</td>
    	<td bgColor=#cccccc>卡种</td>
    	<td bgColor=#cccccc>状态</td>
    	</tr>
    	<s:iterator id="m" value="merCreditCardList" >
    	<tr>
    	<td><s:property value="#m[0]" />&nbsp;</td>
    	<td><s:property value="#m[1]" />&nbsp;</td>
    	<td><s:property value="#m[2]" />&nbsp;</td>
    	<td><s:if test="#m[3].onoff==1">
    	已开通
    	</s:if>
    	<s:else>
    	已关闭
    	</s:else>&nbsp;</td>
    	</tr>
    	</s:iterator>	
   </table>
   <div align="center">
	<h6>商户币种信息</h6>
</div>
   <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="340" align=center 
	bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
<tr bgColor=#cccccc>
	<td>商户</td>
	<td>币种</td>
</tr>
<input type="hidden" id="merid" value="<s:property value="merCurrency.merchanId"/>">
<s:iterator id="it" value="listcurrency">
	<tr>		
		<td>
			<s:property value="#it[0].merno"/>
		</td>
		<td>
			<s:property value="#it[2].moneykindname"/>
		</td>
		
	</tr>
</s:iterator>
</table>

<div align="center">
<h6>商户风控制值</h6>
</div>

<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="400" align=center 
bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
<tr align="center">
<s:if test="flag != 1">
<td bgColor=#cccccc>
	商户号
</td>
</s:if>
<td bgColor=#cccccc>
	分值
</td>
<td bgColor=#cccccc>
	单笔交易限额(上限)
</td>
<td bgColor=#cccccc>
	单笔交易限额(下限)
</td>
<td bgColor=#cccccc>
	天交易限额
</td>
<td bgColor=#cccccc>
	月笔交易限额
</td>
</tr>
<s:iterator id="imm" value="internationalMerchantManagerList">
	<tr align="center">
	<s:if test="flag != 1">
	<td align="left">
			<s:property value="#imm[1].merno"/>
		</td>
	</s:if>
		<td align="left">
			<s:property value="#imm[0].markValue"/>&nbsp;
		</td>
		<td>
			<s:property value="#imm[0].penQuota"/>&nbsp;
		</td>
		<td>
			<s:property value="#imm[0].penQuotaLower"/>&nbsp;
		</td>
		<td>
			<s:property value="#imm[0].dayQuota"/>&nbsp;
		</td>
		
		<td>
			<s:property value="#imm[0].monthQuota"/>&nbsp;
		</td>
	</tr>
</s:iterator>
</table>
<div align="center">
<h6>商户交易条件</h6>
</div>
<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="400" align=center 
bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
<tr align="center">
<td bgColor=#cccccc>
	项目
</td>
<td bgColor=#cccccc>
	可交易笔数
</td>
<td bgColor=#cccccc>
	周期(小时)
</td>
<td bgColor=#cccccc>
	备注
</td>
</tr>

<s:iterator id="bt" value="internationalTradeconditionList">
	<tr align="center">
	<td align="left">
			<s:property value="#bt[0].itemName"/>
		</td>
		<td>
			<s:property value="#bt[0].tradenumber"/>
		</td>
		<td>
			<s:property value="#bt[0].cycle"/>
		</td>
		<td>
			<s:property value="#bt[0].remark"/>&nbsp;
		</td>
	</tr>
</s:iterator>
</table>



<div align="center">
<h6>商户结算日</h6>
</div>
<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="400" align=center 
bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
<tr>
  <td height="25" align="center" bgcolor="#CCCCCC">商户号&nbsp;</td>
  <td height="25" align="center" bgcolor="#CCCCCC">结算日&nbsp;</td>
</tr>

<s:iterator id="listOne" value="tradeList" >
<tr>
  <td align="center" bgcolor="#FFFFFF"><s:property value="#listOne[1].merno" />&nbsp;</td>
  <td align="center" bgcolor="#FFFFFF"><s:property value="#listOne[0].cycleTime" />&nbsp;</td>
</tr>
</s:iterator>    
</table>

<div align="center">
<h6>商户独立终端</h6>
</div>
<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="800" align=center 
bgColor=#ffffff borderColorLight=#000000 border=1 height="10" >
		<tr>
			<td bgColor=#cccccc>通道</td>
			<td bgColor=#cccccc>终端号</td>
			<td bgColor=#cccccc>状态</td>
		</tr>
		<s:iterator id="m" value="merchantTerminalList" >
    		<tr>
    			<td><s:property value="#m[2].channelName" />&nbsp;</td>
    			<td><s:property value="#m[1].terminalNo"/>&nbsp;</td>
    			<td>
    			<s:if test="#m[0].isopen==1">
    				已开通
    			</s:if>
    			<s:else>
					已关闭
    			</s:else>
    			</td>
    			
    			
    		</tr>
		</s:iterator>
		<s:iterator id="m1" value="merchantNoList" >
		<tr>
		<td><s:property value="#m1[2].channelName" />&nbsp;</td>
		<td><s:property value="#m1[1].bankMerchantId"/>&nbsp;</td>
		<td>
		<s:if test="#m1[0].isopen==1">
		已开通
		</s:if>
		<s:else>
		已关闭
		</s:else>
		</td>
		
		
		</tr>
		</s:iterator>
	</table>
  </s:form>
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  
  