
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
	
	<style type='text/css'>
    	.shadow   { height:25px; background-color:#E1E1E1 }
    </style>
    
	<script language="javascript">
		function submit(){
			document.forms["form1"].submit();
		}
	</script>
</head>
<body  onload="setTimeout('submit()',3000)"> <!-- onload="setTimeout('submit()',3000)"-->

<!-- Start Branding Table -->

<form action="paybydcc" id="form1" method="post">

<table align="center">
			
			<tr align="center">
				<td width="60%">
					<h4><b>Processing page</b></h4>
				</td>
			</tr>
			<tr class="shadow" align="center">
				<td width="60%"><br/>
				</td>
			</tr>
			
			<tr align="center">
				<td><br>
					<b>Hint: Please wait and do not refresh page in the process of submitting your payment
					</b><br>
				</td>
			</tr>
			<tr align="center">
				<td>
					<b>which may lead to double charge.<br><br></b>
				</td>
			</tr>
			<tr align="center">
				<td>
					You will be directed to the Payment Server.
				</td>
			</tr><br>
			<tr align="center">
				<td class="shadow" width="90%">
					<b>Thank you for your support</b>
				</td>
			</tr>
</table>


<!-- get user input -->
<table width="80%" align="center" border="0" cellpadding='0' cellspacing='0'>
    <tr class="shade"> 
        <td width="59%"><input type="hidden" name="amount_Transaction_Foreign" size="63" value="<s:property value="amount_Transaction_Foreign"/>" maxlength="250"/></td>
    </tr>
    <tr>
        <td><input type="hidden" name="conversion_Rate" value="<s:property value="conversion_Rate"/>" size="20" maxlength="8"/></td>
    </tr>
    <tr class="shade">     
        <td><input type="hidden" name="currency_Code_Foreign" value="<s:property value="currency_Code_Foreign"/>" size="20" maxlength="16"/></td>
    </tr>
    <tr> 
        <td><input type="hidden" name="tradeType" value="<s:property value="tradeType"/>" size="20" maxlength="8"/></td>
    </tr>
    <tr class="shade">
        <td><input type="hidden" name="rorderno" value="<s:property value="rorderno"/>" size="20" maxlength="40"/></td>
    </tr>
    <tr> 
        <td><input type=hidden name="vpc_Merchant" value="<s:property value="merchantId"/>" size="20" maxlength="16"/></td>
    </tr>
    <tr class="shade">
        <td><input type="hidden" name="vpc_OrderInfo" value="<s:property value="rorderno"/>" size="20" maxlength="34"/></td>
    </tr>
    <tr>
        <td><input type="hidden" name="vpc_Amount" value="<s:property value="tradeOrder" />" size="20" maxlength="10"/></td>
    </tr>
    <tr class="shade">
       <td><input type="hidden" name="vpc_ReturnURL" size="63" value="<s:property value="connectURL"/>" maxlength="250"/></td> 
    </tr>
    <tr class="shade">
        <td><input type="hidden" name="vpc_Locale" value="en" size="20" maxlength="5"/></td>
    </tr>
    
   
   <tr class="shade">
        <td><input type="hidden" name="vpc_card" value="<s:property value="vpc_Card"/>" size="20" maxlength="5"/></td>
    </tr>
    <tr class="shade">
        <td><input type="hidden" name="vpc_gateway" value="ssl" size="20" maxlength="5"/></td>
    </tr>
    <tr class="shade">
        <td><input type="hidden" name="vpc_CardNum" value="<s:property value="vpc_CardNum"/>" size="20" maxlength="5"/></td>
    </tr>
    <tr class="shade">
        <td><input type="hidden" name="vpc_CardExp" value="<s:property value="vpc_CardExp"/>" size="20" maxlength="5"/></td>
    </tr>
    <tr class="shade">
        <td><input type="hidden" name="vpc_CardSecurityCode" value="<s:property value="cvv2"/>" size="20" maxlength="5"/></td>
    </tr>
   </table>

</form>
</body>
	
</html>
