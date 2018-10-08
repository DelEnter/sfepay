<%-- Copyright MasterCard Asia/Pacific (Australia) Pty Ltd. --%>

<%@ page import="au.com.mastercard.example.comms.ErrorResult" %>
<%@ page import="au.com.mastercard.example.comms.StigResult" %>
<%@ page import="au.com.mastercard.example.comms.ValidResult" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<jsp:useBean id="controller" scope="page" class="au.com.mastercard.example.controller.AuthenticationOnlyRequestController"/>

<%@page import="com.mastercard.migs.dcc.stig.api.definitions.AuthenticationResponseCodeType"%>
<%@page import="com.mastercard.migs.dcc.stig.api.authentication.redirect.AuthenticationRedirectResponse"%><html>
<%

    // authentication details    
    final String locale = request.getParameter("locale");
    final String saleAmount = request.getParameter("saleAmount");
    final String saleCurrency = request.getParameter("saleCurrency");
    final String purchaseDescription = request.getParameter("purchaseDescription");    
    final String companyId = request.getParameter("companyId");
    final String companyPassword = request.getParameter("companyPassword");    
    final String messageReference = request.getParameter("messageReference");
    final String merchantRedirectUrl = request.getParameter("redirectUrl");
    final String stigUrl = request.getParameter("stigUrl");
    
    // credit card details    
    final String creditCardNumber = request.getParameter("creditCardNumber");
    final String creditCardExpiryMonth = request.getParameter("creditCardExpiryMonth");
    final String creditCardExpiryYear = request.getParameter("creditCardExpiryYear");    
    
    // DCC details
    final String dccOfferId = request.getParameter("dccOfferId");
    final boolean dccOfferAccepted = new Boolean(request.getParameter("dccOfferAccepted"));
    
    final StigResult result = controller.handleRequest(companyId, companyPassword,
            locale, messageReference, saleAmount, saleCurrency, purchaseDescription,
            creditCardNumber, creditCardExpiryMonth, creditCardExpiryYear,
            dccOfferId, dccOfferAccepted, merchantRedirectUrl, stigUrl);

    if (result.isSuccessful()) {
        // See Step 2.3 of VAS Integration Guide        
        response.sendRedirect(((ValidResult) result).getResponse().toString());
    } else {
%>
<html>
<head><title>Error Occured</title></head>
<body><h1>Error Occured</h1>
<span><%= ((ErrorResult) result).getErrorMessage() %></span>
</body>
</html>

<%
    }
%>