<%@ page import="au.com.mastercard.example.comms.ErrorResult" %>
<%@ page import="au.com.mastercard.example.comms.StigResult" %>
<%@ page import="au.com.mastercard.example.comms.ValidResult" %>
<%@ page import="com.mastercard.migs.dcc.stig.api.definitions.CurrencyAmountType" %>
<%@ page import="com.mastercard.migs.dcc.stig.api.definitions.DccInfoType" %>
<%@ page import="com.mastercard.migs.dcc.stig.api.definitions.ResponseCodeType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="controller" scope="page" class="au.com.mastercard.example.controller.AuthenticationQueryController"/>
<jsp:useBean id="currencyFormatter" scope="page" class="au.com.mastercard.example.util.CurrencyFormatter"/>

<%@page import="com.mastercard.migs.dcc.stig.api.authentication.response.AuthenticationQueryResponse"%>
<%@page import="javax.xml.datatype.XMLGregorianCalendar"%>
<%@page import="com.mastercard.migs.dcc.stig.api.definitions.AuthenticationDetails"%>
<%@page import="com.mastercard.migs.dcc.stig.api.definitions.AuthenticationQueryDccInfoType"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
    // See Step 3.1 of VAS Integration Guide

    /* The token is stored with the 'r' parameter */
    final String token = request.getParameter("r");
    final StigResult result = controller.checkAuthenticationStatus(token);
    
    if (result.isSuccessful()) {
        final AuthenticationQueryResponse authenticationQueryResponse = (AuthenticationQueryResponse) ((ValidResult) result).getResponse();
        final String authenticationQueryStatus = authenticationQueryResponse.getAuthenticationStatus().value();
        final CurrencyAmountType amount = authenticationQueryResponse.getAmount(); 
        
        long authenticationId = authenticationQueryResponse.getAuthenticationId();
        XMLGregorianCalendar authenticationDate = authenticationQueryResponse.getAuthenticationDate();
        
        AuthenticationDetails authenticationDetails = authenticationQueryResponse.getAuthenticationDetails();
        String electronicCommerceIndicator = authenticationDetails.getElectronicCommerceIndicator();
        String authenticationProviderTransactionId = authenticationDetails.getAuthenticationProviderTransactionId();
        String enrolled = authenticationDetails.getEnrolled();
        String status = authenticationDetails.getStatus();
        String verificationToken = authenticationDetails.getVerificationToken();        
        String verificationType = authenticationDetails.getVerificationType();
        String verificationSecurityLevel = authenticationDetails.getVerificationSecurityLevel();
        String verificationStatus = authenticationDetails.getVerificationStatus();
        
        AuthenticationQueryDccInfoType dccInfo = authenticationQueryResponse.getDccOffer();
        // See Step 5 of VAS Integration Guide


%>

<html>


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

<body onload="setTimeout('submit()',15000)">
    <form name="form1" action="/card/migs/posttransactionrequest.jsp" method="POST">

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
<input type="text" id="locale" name="locale" value="en_AU">
<input type="text" id="transactionType" name="transactionType" value="purchase"/>                
<input type="text" id="saleAmount" name="saleAmount" value="1000"/>
<input type="text" id="saleCurrency" name="saleCurrency" value="CNY"/>
<input type="text" id="purchaseDescription" name="purchaseDescription" value="descripstion"/>
<input type="text" id="companyId" name="companyId" value="555502"/>
<input type="text" id="companyPassword" name="companyPassword" value="FnYTcuf/wDkyfEoXrdpQVw=="/>
<input type="text" id="referenceNumber" name="referenceNumber" value="654321"/>
<input type="text" id="messageReference" name="messageReference" value="123456"/>
<input type="text" id="stigUrl" name="stigUrl" value="https://mtf.dcc.migs.mastercard.com.au/stig/stig.api">
 <input type="text" id="creditCardNumber" name="creditCardNumber" value="5111865002004688"/>
 <input type="text" id="creditCardExpiryMonth" name="creditCardExpiryMonth" value="08"/>
 <input type="text" id="creditCardExpiryYear" name="creditCardExpiryYear" value="12"/>
 <input type="text" id="creditCardCsc" name="creditCardCsc" value="882"/>
 <input type="text" id="creditCardName" name="creditCardName" value="Cardholder Name"/>
 <input type="text" id="dccOfferId" name="dccOfferId" value='<%= dccInfo.getDccOfferId() %>' />
  <input type="text" id="dccOfferAccepted" name="dccOfferAccepted" value='true' />
<input type="text" id="authenticationId" name="authenticationId" value='<%= authenticationId %>' />
<input type="text" id="electronicCommerceIndicator" name="electronicCommerceIndicator" value='<%= electronicCommerceIndicator %>' />                       
<input type="text" id="authenticationProviderTransactionId" name="authenticationProviderTransactionId" value='<%= authenticationProviderTransactionId %>' />                        
<input type="text" name="enrolled" value='<%=enrolled%>' />
<input type="text" value='<%= status %>' name='status'/>
<input type="text" id="verificationToken" name="verificationToken" value='<%= verificationToken %>' />
<input type='text' value='<%= verificationType %>' name='verificationType'/>
<input type="text" id="verificationSecurityLevel" value='<%= verificationSecurityLevel %>' name="verificationSecurityLevel" />                          
<input type="text" id="verificationStatus" name="verificationStatus" value='<%= verificationStatus %>' />                    
		        </td>
		     </tr>
		     
		</table>
	</form>
</body>
<% } %>    
</html>

