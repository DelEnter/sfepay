<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>查询拒付</title>
</head>
<script language="JavaScript">

	function replaceheng(val){
		var v = val.replace(/-/g,"");
		document.getElementById("cardnum").value=v;
	}
	
	function updateChargeback(tradeId){
		openWindow('../PaySystem/toSingleChargeback.action?tradeId='+tradeId,'12')
	}
</script>
<div align="center">
	<h3>拒付交易查询</h3>
</div>
<s:form name="formu" id="formu" action="toChargeBackList" theme="simple" method="post">
<TABLE cellSpacing=0 cellPadding=0 align=center border=0>
				<tr  class=TR_Title>
					<td>
						交易流水号
					</td>
					<td>
						<s:textfield name="orderNo" />
					</td>
					<td>
						卡号
					</td>
					<td>
						<s:textfield name="cardnum" id="cardnum" onblur="replaceheng(this.value)"/>
					</td>
				</tr>

				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="搜索"/>
					</td>
				</tr>	
			</TABLE>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr bgColor=#cccccc>
		<td>商户号</td>
		<td>支付结果</td>
		<td>流水号</td>
		<td>金额</td>
		<td>RMB金额</td>
		<td>退款</td>
		<td>拒付</td>
		<td>授权号</td>
		<td>交易日期</td>
		<td>终端号</td>
		<td>卡号</td>
		<td>操作</td>
	</tr>
	<s:set name="countt" value="1"/>
	<s:iterator id="p" value="chargebackList" status="s">
	<tr onclick="showmenu('menu<s:property value="#s.index+1" />');" onMouseOver="this.bgColor='#BBBBFF'; "  onMouseOut="this.bgColor='#FFFFFF';">
		
		<td><s:property value="#p[1].merno" />
			<s:set name="countt" value="#countt+1"/>
		</td>
		<td><s:property value="states.getStateName(#p[0].tradeState,1)" escape="false" />&nbsp;</td>
		<td><s:property value="#p[0].orderNo" /></td>
		<td><s:property value="#p[0].tradeAmount" /></td>
		<td><s:property value="#p[0].rmbAmount" /></td>
		<td><s:property value="states.getStateName(#p[0].tradeState,2)" escape="false" />&nbsp;</td>
		<td><s:property value="states.getStateName(#p[0].tradeState,3)" escape="false" />&nbsp;</td>
		<td><s:property value="#p[0].VIPAuthorizationNo" />&nbsp;</td>
		<td><s:property value="#p[0].tradeTime" /></td>  
		<td><s:property value="#p[0].VIPTerminalNo" /></td>
		<td>
		
			<s:property value="#p[2].cardNo"/>
		</td>
		<td>
		
		<s:if test="#p[0].tradeState.substring(2,3)!=1">
			<a href="#" onclick="updateChargeback(<s:property value="#p[0].id" />)">拒付</a>&nbsp;
		</s:if>
		</td>
	</tr>
	
	</s:iterator>
	<s:hidden name="#countt" id="infoindex"/>
</table>
<div align="center">
</div>

</s:form>

</script>








