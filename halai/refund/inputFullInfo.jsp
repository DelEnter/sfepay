<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>补充信息</TITLE>
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
	<base target="_self"> 
	<body>
		<H3 align=center>补充交易信息</H3>
      	<br>
     	<center>
			<s:form id="form1" action="tijiaoinput" theme="simple" method="post">
			<input type="hidden" name="tradeId" value="<s:property value="tradeId"/>" />
		   		<TABLE cellSpacing=0 cellPadding=0 width=60% align=center border=5>	
					<tr align=center>	
		   				<td height=30>卡号</td>
		   				<td width="60%">
		   					<s:property value="o[0]"/>
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
			   	   			<input type="text" value='<s:property value="o[7]"/>' name='posNUM'>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>批次号</td>
			   	   		<td>
			   	   		<input type="text" value='<s:property value="o[8]"/>' name='beatchNUM'>&nbsp;
			   	   		</td>
			   	   	</tr>
			   	   	<tr align=center>
			   	   		<td height=30>授权号</td>
			   	   		<td>
			   	   		<input type="text" value='<s:property value="o[9]"/>' name='voidNUM'>&nbsp;
			   	   		</td>
			   	   	</tr>
 			   	</TABLE>
			   	
			   	<input type="submit" value="确定">
			   	<s:reset value="取消"/>
			</s:form>
	</center>
  </body>
</html>
