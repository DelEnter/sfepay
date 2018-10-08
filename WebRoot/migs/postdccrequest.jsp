<%-- Copyright MasterCard Asia/Pacific (Australia) Pty Ltd. --%>

<%@ page import="au.com.mastercard.example.comms.ErrorResult" %>
<%@ page import="au.com.mastercard.example.comms.StigResult" %>
<%@ page import="au.com.mastercard.example.comms.ValidResult" %>
<%@ page import="com.mastercard.migs.dcc.stig.api.dcc.request.DccRequest" %>
<%@ page import="com.mastercard.migs.dcc.stig.api.dcc.response.DccResponse" %>
<%@ page import="com.mastercard.migs.dcc.stig.api.definitions.ResponseCodeType" %>
              
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
                                                 
<jsp:useBean id="controller" scope="page" class="au.com.mastercard.example.controller.DccRequestController"/>
<html>

<%
    final String companyId = request.getParameter("companyId");
    final String companyPassword = request.getParameter("companyPassword");
    final String saleAmount = request.getParameter("saleAmount");
    final String currency = request.getParameter("currency");
    final String cardNumber = request.getParameter("cardNumber");
    final String stigUrl = request.getParameter("stigUrl");
    final String locale = request.getParameter("locale");    

    final StigResult result = controller.handleRequest(companyId, companyPassword, cardNumber, saleAmount, currency, locale, stigUrl);
    
    if (result.isSuccessful()) {
        // See Merchant Hosted DCC Integration Step 2.2 of VAS Integration Guide
        final DccResponse dccResponse = (DccResponse) ((ValidResult) result).getResponse();
        final ResponseCodeType responseCode = dccResponse.getResponseCode();
        final String responseStatus = responseCode.getStatus().value();       
  
%>      
<head>
    <title>DCC Response Page</title>
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

<h1>Result of your DCC Request: </h1>

<table>
    <tbody>
    <tr>
        <td>Your DCC Request has:</td>
        <td id="status"><%= responseStatus %></td>
    </tr>
    <tr>
        <td>DCC Response Code:</td>
        <td><%= responseCode.getResponseCode() %></td>
    </tr>
    <tr>
        <td>DCC Response Message:</td>
        <td id="message"><%= responseCode.getResponseMessage() %></td>
    </tr>    
    <% if (responseStatus.equals("SUCCESS")) { %>
    <tr>
        <td>DCC Provider:</td>
        <td id="dccProvider"><%= dccResponse.getDccProvider() %></td>
    </tr>
    
    <tr>
        <td>DCC Offer Id:</td>
        <td id="dccOfferId"><%= dccResponse.getDccOffer().getDccOfferId() %></td>
    </tr>
    
    <tr>
        <td>DCC Offer Date:</td>
        <td><%= dccResponse.getDccOffer().getDate() %></td>
    </tr>
    
    <tr>
        <td>DCC Offer Expiry Date:</td>
        <td><%= dccResponse.getDccOffer().getDccOfferExpiryDate() %></td>
    </tr>

    <tr>
        <td>DCC Offer Amount:</td>
        <td><%= dccResponse.getDccOffer().getConvertedAmount().getAmount() %></td>
    </tr>
    
    <tr>
        <td>DCC Offer Currency:</td>
        <td id="offerCurrency"><%= dccResponse.getDccOffer().getConvertedAmount().getCurrency() %></td>
    </tr>
    
    <tr>
        <td>DCC Offer Exchange Rate:</td>
        <td><%= dccResponse.getDccOffer().getConversionExchangeRate() %></td>
    </tr>
    
    <tr>
        <td>DCC Offer Margin Rate:</td>
        <td><%= dccResponse.getDccOffer().getMarginRate() %></td>
    </tr>
    
    </tbody>
</table>

<tbody>
    <tr>
        <td>DCC Disclaimer:</td>
        <td class="dccInfo" colspan="2"><%= dccResponse.getDccOffer().getDccOfferDisclaimer()%></td>
    </tr>
</tbody>
    
</table>
</body>      
    <% } %>  
<%             
    } else {
%>
<head><title>Error Occured</title></head>
<body><h1>Error Occured</h1>
<span><%= ((ErrorResult) result).getErrorMessage() %></span>
</body>

<%
    }
%>
</html>