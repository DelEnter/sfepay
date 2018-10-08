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
	    	}else if ((authno!="") & (bacthno!="") & (result=="1") & remark==""){
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
			<s:form id="form1" action="disposeErrorTrade" theme="simple" method="post">
		   		<TABLE cellSpacing=0 cellPadding=0 width=60% align=center border=5>	
					<tr align=center>	
						<input type="hidden" name="tradeId" value="<s:property value='errortrade[7].id'/>"/>
						<input type="hidden" name="shijikoukuan" value="<s:property value='errortrade[4]'/>"/>
						<input type="hidden" name="yingkoukuan" value="<s:property value='errortrade[5]'/>"/>
						<input type="hidden" name="chae" value="<s:property value='errortrade[6]'/>"/>
		   				<td height=30>卡号</td>
		   				<td width="60%">
		   					<s:property value="errortrade[0].substring(0,4)"/>&nbsp;<s:property value="errortrade[0].substring(4,8)"/>&nbsp;<s:property value="errortrade[0].substring(8,12)"/>&nbsp;<s:property value="errortrade[0].substring(12,16)"/>
		   				</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>有效期(MM/YY)</td>
			   	   		<td>
			   	   			<s:property value="errortrade[1]"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>CVV2</td>
			   	   		<td>
			   	   			<s:property value="errortrade[2]"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>现扣金额RMB</td>
			   	   		<td>
			   	   			<s:property value="errortrade[4]"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   	<td height=30>应扣金额RMB</td>
			   	   	<td>
			   	   	<s:property value="errortrade[5]"/>&nbsp;
			   	   	</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   	<td height=30>差额RMB</td>
			   	   	<td>
			   	   	<font color="red"><s:property value="errortrade[6]"/>&nbsp;</font>
			   	   	</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>批次号</td>
			   	   		<td>
			   	   			<s:textfield style="text-align:center;" name="VIPBatchNo" id="VIPbacthno"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>授权号</td>
			   	   		<td>
			   	   			<s:textfield style="text-align:center;" name="VIPAuthorizationNo" id="authorizeno"/>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   
			   	   	<tr align=center>
			   	   		<td height=30>支付结果</td>
			   	   		
			   	   		<td>
			   	   	<s:select name="isresult" list="#{'1':'－－－成功－－－','0':'－－－失败－－－'}"  listKey="key" listValue="value"/>
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>备注</td>
			   	   		<td>
			   	   			<s:select name="remark" id="remark" list="#{'':'','不接受':'不接受','查询卡中心':'查询卡中心','没收（黑卡）':'没收（黑卡）','不接纳此卡':'不接纳此卡','此卡已失效':'此卡已失效','卡过期':'卡过期','无此卡号':'无此卡号','卡号有误':'卡号有误','非法交易':'非法交易','商户要求取消':'商户要求取消','重复交易':'重复交易','被窃卡':'被窃卡','零金额':'零金额','TEST':'TEST'}"  listKey="key" listValue="value"/>
			   	   		</td>
			   	   	</tr>
			   	</TABLE>
			   	
			   	<input type="submit" value="确定" >
			   	<s:reset value="取消"/>
			</s:form>
	</center>
  </body>
</html>
