<%@taglib prefix="s" uri="/struts-tags"%>
<%--
 Created by IntelliJ IDEA.
 User: Bluewater
 Date: 2005-7-21
 Time: 20:55:45
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">


<head>
	<style type='text/css'>
    <!--
    .shade   { height:25px; background-color:#E1E1E1 }
    </style>
	<script language="javascript" >
		function submit(){
			form1.submit();
		}
	</script>
</head>

<body onload="setTimeout('submit()',20000)">
    <form name="form1" action="/card/migs/postauthenticationonlyrequest.jsp" method="POST">	
		<table align="center">
			
			<tr align="center">
				<td width="60%">
					<h4><b>Processing page</b></h4>
				</td>
			</tr>
			<tr class="shade" align="center">
				<td width="60%"><br>
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
				<td class="shade" width="90%">
					<b>Thank you for your support</b>
				</td>
			</tr>
		    <tr>	   
		        <td align="left">

  <input type="hidden" id="locale" name="locale" value="en_AU">
  <input type="hidden" id="saleAmount" name="saleAmount" value="1000" />
 <input type="hidden" id="saleCurrency" name="saleCurrency" value="CNY" />
 <input type="hidden" id="purchaseDescription" name="purchaseDescription" value="descripstion"/>
 <input type="hidden" id="companyId" name="companyId" value="555502" />
 <input type="hidden" id="companyPassword" name="companyPassword" value="FnYTcuf/wDkyfEoXrdpQVw==" />
 <input type="hidden" id="messageReference" name="messageReference" value="16885" />
 <input type="hidden" id="redirectUrl" name="redirectUrl" value="http://localhost:8888/card/migs/authenticationonlycallback.jsp"/>
 <input type="hidden" id="stigUrl" name="stigUrl" value="https://mtf.dcc.migs.mastercard.com.au/stig/stig.api" /></td>
 <input type="hidden" id="creditCardNumber" name="creditCardNumber"/ value="4606990006000865">
 <input type="hidden" id="creditCardExpiryMonth" name="creditCardExpiryMonth" value="12" />
 <input type="hidden" id="creditCardExpiryYear" name="creditCardExpiryYear" value="12" />
 <input type="text" id="dccOfferId" name="dccOfferId" value="" />
  <input type="hidden" value='true' name="dccOfferAccepted"/>
 		        </td>
		     </tr>
		     
		</table>
	</form>
</body>

</html>


