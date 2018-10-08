<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>VIP交易处理</TITLE>
<LINK href="../css/head.css" type=text/css rel=stylesheet>
	<SCRIPT language=JavaScript>    
	    function check(){
	    	var authno =  document.getElementById("authorizeno").value; 
	    	var bacthno = document.getElementById("VIPbacthno").value;
	    	var result =  document.getElementById("isresult").value; 
	    	var remark =  document.getElementById("remark").value; 
	    	var jgpattern =/^[A-Za-z0-9]+$/; 
	    	if((authno=="") & (bacthno=="") & result=="0" & (remark!="")){
	    		if(remark.indexOf(" ")!=0){
	    			form1.submit();
	    		}else {
	    				alert("输入信息有误!!");
	    		}
	    	}else if ((authno!="") & (bacthno!="") & (result=="1")){
    		    form1.submit();
	    	}else{
	    		alert("输入信息有误!");
	    	}
	    }
	    
		function formatamount(val){
			if(val.length>3){
				if (val.indexOf(".")<0){
					var a = val.replace(val.substring(val.length-2,val.length),"."+val.substring(val.length-2,val.length));
					document.getElementById("dccamount").value=a;
				}
			}
			
		}
		
	
	</SCRIPT>

</HEAD>
	<body>
		<H3 align=center>VIP交易处理</H3>
      	<br>
     	<center>
			<s:form id="form1" action="tradeDeal" theme="simple" method="post">
		   		<TABLE cellSpacing=0 cellPadding=0 width=60% align=center border=5>	
					<tr align=center>	
						
		   				<td height=30>卡号</td>
		   				<td width="60%">
		   					<s:property value="getCarNo(o[0]).substring(0,4)"/>&nbsp;<s:property value="getCarNo(o[0]).substring(4,8)"/>&nbsp;<s:property value="getCarNo(o[0]).substring(8,12)"/>&nbsp;<s:property value="getCarNo(o[0]).substring(12,16)"/>
		   					<input type="hidden" name="trade.VIPTerminalNo" value="<s:property value="o[7]"/>"/>
		   					<input type="hidden" name="trade.id" value="<s:property value="o[6]"/>"/>
		   					<s:if test="o[0]=='4000000000000002'">
		   						<font color="red">测试卡号</font>
		   					</s:if>
		   				</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>有效期(MM/YY)</td>
			   	   		<td>
			   	   			<s:property value="o[1]"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>CVV2</td>
			   	   		<td>
			   	   			<s:property value="o[2]"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>流水号</td>
			   	   		<td>
			   	   			<s:property value="o[3]"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>交易金额RMB</td>
			   	   		<td>
			   	   			<s:property value="o[4]"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>通道</td>
			   	   		<td>
			   	   			<s:property value="o[5]"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>终端号</td>
			   	   		<td>
			   	   			<s:property value="o[7]"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>批次号</td>
			   	   		<td>
			   	   			<s:textfield style="text-align:center;" name="trade.VIPBatchNo" id="VIPbacthno"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>授权号</td>
			   	   		<td>
			   	   			<s:textfield style="text-align:center;" name="trade.VIPAuthorizationNo" id="authorizeno"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   <!--	<tr align=center>
				   	   	<td height=30>DCC交易币种<font color="red">(EDC不用填写)</font></td>
				   	   	<td>
				   		<s:select name="trade.DCCTradeCurrency" list="#{'':'','USD':'－－－USD－－－','EUR':'－－－EUR－－－','GBP':'－－－GBP－－－','AUD':'－－－AUD－－－','CAD':'－－－CAD－－－','JPY':'－－－JPY－－－','CHF':'－－－CHF－－－','DKK':'－－－DKK－－－','ZAR':'－－－ZAR－－－','NOK':'－－－NOK－－－','SEK':'－－－SEK－－－'}"  listKey="key" listValue="value"/>
				   	   	</td>
			   	   	</tr>
			   	   	<tr align=center>
				   	   	<td height=30>DCC原始金额<font color="red">(EDC不用填写)</font></td>
				   	   	<td>
				   	   	<s:textfield style="text-align:center;" onblur="formatamount(this.value)" name="trade.DCCTradeAmount" id="dccamount"/>&nbsp;
				   	   	</td>
			   	   	</tr> -->
			   	   	<tr align=center>
			   	   		<td height=30>支付结果</td>
			   	   		
			   	   		<td>
			   	   	<s:select name="isresult" id="isresult" list="#{'1':'－－－成功－－－','0':'－－－失败－－－'}"  listKey="key" listValue="value"/>
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>备注</td>
			   	   		<td>
<s:select name="trade.remark" id="remark" list="#{'':'','Payment Success':'Payment Success','不接受':'不接受','查询卡中心':'查询卡中心','没收（黑卡）':'没收（黑卡）','不接纳此卡':'不接纳此卡','此卡已失效':'此卡已失效','卡过期':'卡过期','无此卡号':'无此卡号','卡号有误':'卡号有误','非法交易':'非法交易','商户要求取消':'商户要求取消','重复交易':'重复交易','被窃卡':'被窃卡','零金额':'零金额','Do not honor':'Do not honor','Not sufficient funds':'Not sufficient funds','Lost card':'Lost card'}"  listKey="key" listValue="value"/>
			   	   		</td>
			   	   	</tr>
			   	</TABLE>
			   	
			   	<input type="button" value="确定" onClick="check()">
			   	<s:reset value="取消"/>
			</s:form>
	</center>
  </body>
</html>
