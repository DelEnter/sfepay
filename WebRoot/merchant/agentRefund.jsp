<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<html>
  <head>
    <title>退款</title>
    <script language="JavaScript" src="../js/util.js"></script>
  </head>
      <script language="JavaScript" type="text/JavaScript">
      function addCourse(){
  		var refundAmount = document.getElementById("refundAmount").value;
  		var tradeamount = document.getElementById("applyRefund").value;
  		if(parseInt(refundAmount)==0){
  			alert("退款金额不能为零！");
  			return false;
  		}
  		if(refundAmount<=tradeamount){
  			var v = confirm("确认信息:\n可退款金额:"+tradeamount+"\n退款金额:"+refundAmount);
  			if(v==true){
  				document.getElementById("form1").submit();
  			}
  		}else{
  			alert("退款金额不能大于可退款金额.")
  		}
  		
  	}
</script> 
 <body>
  	<center>
    <H3>代理商户退款</H3>
	    <s:form id ="form1" action="agentsRefund" theme="simple">
        <input type="hidden" name="tradeId" id="tradeId" value="<s:property value="orderId" />"/>
        <div style="margin:0 0 0 50px">
		<table borderColor=#ffffff border="1"  width="240" align=center 
		bgColor=#ffffff borderColorLight=#000000 height="20">
		    	<tr>
		    		<td>流水号:</td>
		    		<td>
		    		&nbsp;<s:property value="trade.orderNo" />
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>交易日期:</td>
		    		<td>&nbsp;<s:property value="trade.tradeTime" />
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>金额:</td>
		    		<td>&nbsp;<s:property value="trade.tradeAmount" />
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>支付状态:</td>
		    		<td>&nbsp;<s:property value="states.getStateName(trade.tradeState,1)"  escape="false"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>退款状态:</td>
		    		<td>&nbsp;<s:property value="states.getStateName(trade.tradeState,2)"  escape="false"/>
		    		</td>
		    	</tr>
		    	<tr>
		    		<td>可退款金额:</td>
		    		<td>&nbsp;<s:property value="applyRefund" />
		    		</td>
		    	</tr>
		    	<tr>
		    	<td>退款金额:</td>
		    	<td><input type="hidden" name="applyRefund" id="applyRefund" title="Enter Refund Amount" value="<s:property value="applyRefund" />"/>
				&nbsp;<input type="text" name="refundAmount" id="refundAmount" title="Enter Refund Amount" value="<s:property value="applyRefund" />"/>
		    	</td>
		    	</tr>
		    </table>
		</div>
            <input type="button" onClick="addCourse();" value="退款" class="windows_icon1"/>
	    </s:form>
    </center>
    
  </body>
</html>
