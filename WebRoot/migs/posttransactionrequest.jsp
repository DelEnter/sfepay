<%-- Copyright MasterCard Asia/Pacific (Australia) Pty Ltd. --%>

<%@ page import="au.com.mastercard.example.comms.ErrorResult" %>
<%@ page import="au.com.mastercard.example.comms.StigResult" %>
<%@ page import="au.com.mastercard.example.comms.ValidResult" %>
<%@page import="com.mastercard.migs.dcc.stig.api.transaction.response.TransactionResponse"%>
<%@page import="com.mastercard.migs.dcc.stig.api.definitions.ResponseCodeType"%>
<%@page import="com.mastercard.migs.dcc.stig.api.definitions.CurrencyAmountType"%>
<%@page import="com.mastercard.migs.dcc.stig.api.definitions.DccInfoType"%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="controller" scope="page" class="au.com.mastercard.example.controller.TransactionRequestController"/>
<jsp:useBean id="currencyFormatter" scope="page" class="au.com.mastercard.example.util.CurrencyFormatter"/>


<%@page import="com.mastercard.migs.dcc.stig.api.definitions.AuthenticationResponseCodeType"%><html>
<%

    // transaction details
    final String transactionType = request.getParameter("transactionType");
    final String locale = request.getParameter("locale");
    final String saleAmount = request.getParameter("saleAmount");
    final String saleCurrency = request.getParameter("saleCurrency");
    final String description = request.getParameter("purchaseDescription");    
    final String companyId = request.getParameter("companyId");
    final String companyPassword = request.getParameter("companyPassword");
    final String referenceNumber = request.getParameter("referenceNumber");
    final String messageReference = request.getParameter("messageReference");
    final String stigUrl = request.getParameter("stigUrl");
    
    // credit card details    
    final String creditCardNumber = request.getParameter("creditCardNumber");
    final String creditCardExpiryMonth = request.getParameter("creditCardExpiryMonth");
    final String creditCardExpiryYear = request.getParameter("creditCardExpiryYear");
    final String creditCardCsc = request.getParameter("creditCardCsc");
    final String creditCardName = request.getParameter("creditCardName");
    
    // DCC details
    final String dccOfferId = request.getParameter("dccOfferId");
    final boolean dccOfferAccepted = new Boolean(request.getParameter("dccOfferAccepted"));
    
    // 3DS referenced id
    String authenticationId = request.getParameter("authenticationId");
    
    // 3DS details
    final String electronicCommerceIndicator = request.getParameter("electronicCommerceIndicator");
    final String authenticationProviderTransactionId = request.getParameter("authenticationProviderTransactionId");
    final String enrolled3ds = request.getParameter("enrolled");
    final String status3ds = request.getParameter("status");
    final String verificationToken = request.getParameter("verificationToken");
    final String verificationType = request.getParameter("verificationType");
    final String verificationSecurityLevel = request.getParameter("verificationSecurityLevel");
    final String verificationStatus = request.getParameter("verificationStatus");

    final StigResult result = controller.handleRequest(companyId, companyPassword, saleAmount, saleCurrency,
            description, transactionType, messageReference, referenceNumber, locale, stigUrl, 
            creditCardNumber, creditCardExpiryMonth, creditCardExpiryYear, creditCardCsc, creditCardName,
            dccOfferId, dccOfferAccepted,
            authenticationId, electronicCommerceIndicator, authenticationProviderTransactionId, enrolled3ds, status3ds,
            verificationToken, verificationType, verificationSecurityLevel, verificationStatus);
    if (result.isSuccessful()) {
        // See Merchant Hosted Transaction Integration Step 2.2 of VAS Integration Guide
        final TransactionResponse transactionResponse = (TransactionResponse) ((ValidResult) result).getResponse();
        final ResponseCodeType responseCode = transactionResponse.getTransactionResponse();
        final String responseStatus = responseCode.getStatus().value();
        final String approval = responseCode.getStatus().value().equals("SUCCESS") ? "Approved" : "Declined";
        final CurrencyAmountType amount = transactionResponse.getAmount();
  
%>      
<head>
    <title>Receipt Page</title>
    <style type="text/css">
        h1 {
            text-align: center;
        }

        th {
            text-align: right;
            padding-right: 30px;
        }

        .dccInfo {
            font-style: italic;
            text-align: left;
        }

        table {
            margin-left: auto;
            margin-right: auto;

        }

    </style>
</head>
<body>

<h1>Result of your transaction: </h1>

<table>
    <tbody>
    <tr>
        <td>Your transaction has been:</td>
        <td id="transactionStatus"><%= approval %>
        </td>
    </tr>
    <% if (approval.equals("Approved")) { %>
    <% final AuthenticationResponseCodeType authenticationResponseCode = transactionResponse.getAuthenticationResponse();
        if (authenticationResponseCode != null) {
    %>
    <tr>
        <td><b>3DS Response :</b></td>
        <td id="authenticationResponse"><b><%= authenticationResponseCode.getMessage() %></b></td>
    <tr>
    <% } %>    
    <tr>
        <td>Your receipt number is:</td>
        <td style="font-style:italic;">Merchant generated receipt number</td>
    </tr>
    <tr>
        <td>Transation amount:</td>
        <td id="transactionAmount"><%= currencyFormatter.formatAmount(amount.getAmount(), amount.getExponent()) %>
            <%= amount.getCurrency() %>
        </td>
    </tr>
    <% final DccInfoType dccInfo = transactionResponse.getDccInformation();
        if (dccInfo != null && dccInfo.isDccOfferAccepted()) {
    %>
    <tr>
        <td><b>Transaction currency:</b></td>
        <td id="transactionCurrency"><b><%= dccInfo.getConvertedAmount().getCurrency() %></b>
        </td>
        <tr>        
        <td>Exchange rate:</td>
        <td><%= dccInfo.getConversionExchangeRate() %>
        </td>
        </tr>
    </tr>



    <tr>
        <td>Total amount due:</td>
        <td><%= currencyFormatter.formatAmount(dccInfo.getConvertedAmount().getAmount(), dccInfo.getConvertedAmount().getExponent())  %> <%= dccInfo.getConvertedAmount().getCurrency()%>
        </td>
    </tr>
    <tr>
        <td class="dccInfo" colspan="2"><%= dccInfo.getDccOfferDisclaimer()%>
        </td>
    </tr>

    <% } else {%>
        <tr>
        <td>Total amount due:</td>
        <td><%= currencyFormatter.formatAmount(amount.getAmount(), amount.getExponent()) %>
            <%= amount.getCurrency() %>
        </td>
    </tr>
    <%
    }
    %>        
    <% } %>

    
    </tbody>

</table>

</body>

<% } else { %>

<head><title>Error Occured</title></head>
<body>
<h1>Error!</h1>

<span>
<%= ((ErrorResult) result).getErrorMessage() %>
</span>
</body>

<% } %>
</html>