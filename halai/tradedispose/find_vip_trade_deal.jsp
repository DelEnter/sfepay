<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>查询VIP待处理交易</TITLE>

    <LINK href="../css/head.css" type=text/css rel=stylesheet>
     <%@ taglib prefix="pages" uri="/xs-pages"%>
</HEAD>
  
<body>
   <H3 align=center>查询VIP待处理交易</H3>
   <s:form id="form1" action="findTradeDeal" theme="simple" method="post">
	   <TABLE cellSpacing=0 cellPadding=0 width=55% align=center border=0>	
	   		<tr>
	   			<td colspan="4">
	   			<s:property value="messageAction" />
	   			</td>
	   		</tr>
	   		<tr>
		 		<td>流水号</td>
	   			<td>
	   				<s:textfield name="trade.orderNo"></s:textfield>
	   			</td>
		 		<td>商户号</td>
	   			<td>
	   				<s:textfield name="merchant.merno"/>
	   			</td>
	 		</tr>
	   		<tr>
	   			<td>终端号</td>
	 			<td>
	 			<s:select name="trade.VIPTerminalNo" list="terminalList" headerKey="" headerValue="----"  />

		 		</td>
	   			<td></td>
	   			<td>
	   				<s:submit value="查询"></s:submit>
	   			</td>
	   		</tr>
	   </TABLE>
   
	<TABLE borderColor=#ffffff cellSpacing=0 cellPadding=0 width="1000"
		align=center bgColor=#ffffff >
		<tr>	
			<td><FONT color=blue>总额:</FONT><s:property value="allTradeMoney"/> RMB</td>
	  	</tr>
	</table>
   	
   	<TABLE cellSpacing=0 cellPadding=0 width=900 align=center border=1>	
   		
	   	<tr class=TR_Title bgColor=#cccccc align=center>	
			<td >序列</td>
			<td >卡类型</td>
			<td >商户号</td>
			<td >流水号</td>
 			<td >交易时间</td>
  			<td >交易金额</td>
 			<td >交易币种</td>
 			<td >人民币</td>
 			<td >终端号</td>
  			<td >支付状态</td>
 			<td >处理</td>
	 	  </tr>
	 	   <s:iterator id="list" value="info.result" status="t"> 
		  	  <tr class=TR_Title align=center>	
  	  			<td ><s:property value="#t.index+1"/>&nbsp;</td>
		 		<td align=center width=5%>
		 			<s:if test="#list[1].cardNo.substring(0,1)==4"> 
						<font color= blue >VISA</font> 
					</s:if>
					<s:elseif test="#list[1].cardNo.substring(0,1)==5" >
						<font color= blue >MASTERCARD</font>  
					</s:elseif>&nbsp;
		 		</td>
		 		<td><s:property value="#list[0].orderNo.substring(0,4) "/>&nbsp;</td>
		 		<td><s:property value="#list[0].orderNo "/>&nbsp;</td>
		 		<td><s:property value="#list[0].tradeTime "/>&nbsp;</td>
		 		<td><s:property value="#list[0].tradeAmount "/>&nbsp;</td>
		 		<td><s:property value="states.getCurrencyTypeByNo(#list[0].moneyType)"/>&nbsp;</td>
		 		<td><s:property value="#list[0].rmbAmount "/>&nbsp;</td>
		 		<td><s:property value="#list[0].VIPTerminalNo "/>&nbsp;</td>
		 		<td>
		 			<s:property value="states.getStateName(#list[0].tradeState,1)" escape="false"/>&nbsp;
		 		</td>
		 		<td ><a href="toTradeDeal.action?trade.id=<s:property value="#list[0].id "/>">处理</a></td>
   	  		</tr>
	   	   </s:iterator>
	   	   <tr bgColor=#ffffff>
				<td colspan="30">
				<pages:pages value="info" beanName="info"
					formName="getElementById('form1')" />
				</td>
		   </tr>
   </TABLE>
    </s:form>
  </body>
</html>
