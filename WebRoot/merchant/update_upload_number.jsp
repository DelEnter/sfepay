<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>   
    <title>修改上传跟踪单号</title>

	<link rel="stylesheet" type="text/css" href="../css/head.css">

  </head>
  
  <body>
    <center>
    	<h3>修改上传跟踪单号</h3>
    	<s:form action="uploadNumber" method="post" theme="simple">
    		<table cellSpacing=0 cellPadding=0 width=700 align=center border=1>
    			<tr align="center" bgcolor="#cccccc">
    				<td>流水号</td>
    				<td>商户订单号</td>
    				<td>交易日期</td>
    				<td>交易金额</td>
    				<td>支付状态</td>
    				<td>填写单号</td>
    			</tr>
    			<tr align="center">
    				<td>
    					<s:property value="trade.orderNo"/>
    					<s:hidden name="trade.id"></s:hidden>
    				</td>
    				<td>
    					<s:property value="trade.merchantOrderNo"/>
    				</td>
    				<td>
    					<s:property value="trade.tradeTime"/>
    				</td>
    				<td>
    					<s:property value="trade.tradeAmount"/>
    				</td>
    				<td>
    					<s:property value="states.getStateName(#it[0].tradeState,1)" escape="false"/>&nbsp;
      				</td>
    				<td>
    					<s:textfield name="trade.isTrackNo"></s:textfield>
    				</td>
    			</tr>
    		</table>
           <ul class="listlistpage">
             <li><pages:pages value="info" beanName="info" formName="forms(0)" /></li>
           </ul>     		
    		<s:submit value="上传"/>
    	</s:form>
    </center>
    <!--尾部begin-->
	<%@ include file="foot.jsp"%>
  </body>
</html>
