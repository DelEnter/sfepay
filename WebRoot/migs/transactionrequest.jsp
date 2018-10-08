<%-- Copyright MasterCard Asia/Pacific (Australia) Pty Ltd. --%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>    
    <title>Example Stig Merchant Hosted Merchant Integration - Transaction Request</title>
    
    <style type="text/css">
        h1 {
            text-align: center;
        }        
        
        div {
            width:50%;
            height:90%;
            border:1px solid blue;   
            padding:5px;
            margin-bottom:10px;         
        }
        
    </style>
</head>
    
<body>

<h2>Example Stig Merchant Hosted Merchant Integration - Transaction Request</h2>

<form action="posttransactionrequest.jsp" method="POST">
        <div>   
        <h5>Transaction Details</h5>
        <table>
            <tr>
                <td><label for="transactionType">Transaction Type</label></td>
                <td><select id="transactionType" name="transactionType">
                    <option>authorise</option>
                    <option>purchase</option>
                </select></td>
            </tr>
            <tr>
                <td><label for="locale">Locale</label></td>
                <td><input type="text" id="locale" name="locale" value="en_AU"></td>
            </tr>
            <tr>
                <td><label for="saleAmount">Sale Base Units</label></td>
                <td><input type="text" id="saleAmount" name="saleAmount" value="1000"/></td>
            </tr>
            <tr>
                <td><label for="saleCurrency">Sale Currency</label></td>
                <td><input type="text" id="saleCurrency" name="saleCurrency" value="CNY"/></td>
            </tr>
            <tr>
                <td><label for="purchaseDescription">Description</label></td>
                <td><input type="text" id="purchaseDescription" name="purchaseDescription"
                           value="descripstion"/></td>
            </tr>
            <tr>
                <td><label for="companyId">Company Id</label></td>
                <td><input type="text" id="companyId" name="companyId" value="555502"/></td>
            </tr>
            <tr>
                <td><label for="companyPassword">Company Password</label></td>
                <td><input type="text" id="companyPassword" name="companyPassword" value="FnYTcuf/wDkyfEoXrdpQVw=="/></td>
            </tr>
            
            <tr>
                <td><label for="referenceNumber">Reference Number</label></td>
                <td><input type="text" id="referenceNumber" name="referenceNumber" value="654321"/></td>
            </tr>
            <tr>
                <td><label for="messageReference">Message Reference</label></td>
                <td><input type="text" id="messageReference" name="messageReference" value="123456"/></td>
            </tr>
            <tr>
                <td><label for="stigUrl">API Url</label></td>
                <td><input type="text" id="stigUrl" name="stigUrl" value="https://rtf.dcc.migs.mastercard.com.au/stig/stig.api"></td>
            </tr>
        </table>   
        </div>         
             
                        
        <div>
        <h5>Credit Card Details</h5>
        <table>
            <tr>
                <td><label for="creditCardNumber">Card Number</label></td>
                <td><input type="text" id="creditCardNumber" name="creditCardNumber" value="5111865002004688"/></td>
            </tr>
            <tr>
                <td><label for="creditCardExpiryMonth">Expiry Month</label></td>
                <td><input type="text" id="creditCardExpiryMonth" name="creditCardExpiryMonth" value="08"/></td>
            </tr>
            <tr>
                <td><label for="creditCardExpiryYear">Expiry Year</label></td>
                <td><input type="text" id="creditCardExpiryYear" name="creditCardExpiryYear" value="12"/></td>
            </tr>
            <tr>
                <td><label for="creditCardCsc">CSC</label></td>
                <td><input type="text" id="creditCardCsc" name="creditCardCsc" value="882"/></td>
            </tr>            
            <tr>
                <td><label for="creditCardName">Card Holder name</label></td>
                <td><input type="text" id="creditCardName" name="creditCardName" value="Cardholder Name"/></td>
            </tr>
        </table>
        </div>            

        
        <div>
        <h5>DCC Details (Optional)</h5>
        <table>        
            <tr>
                <td><label for="dccOfferId">DCC Offer Id</label></td>
                <td><input type="text" id="dccOfferId" name="dccOfferId" /></td>
            </tr>
            <tr>
                <td><label for="dccOfferAccepted">DCC Offer Accepted</label></td>                
                <td>
                    <select id="dccOfferAccepted" name="dccOfferAccepted">
                        <option>true</option>
                        <option>false</option>
                    </select>
                </td>
            </tr>
        </table>
        </div>
        
        <div>
        <h5>3DS Details For Authentication Performed with 3rd Party(Optional)</h5>
        <table>            
            <tr>
                <td><label for="authenticationId">VAS Authentication Id</label></td>
                <td><input type="text" id="authenticationId" name="authenticationId" /></td>                            
            </tr> 
        </table>
        </div>               
        
        <div>
        <h5>3DS Details For Authentication Performed with 3rd Party(Optional)</h5>
        <table>            
            <tr>
                <td><label for="electronicCommerceIndicator">3DS Electronic Commerce Indicator</label></td>
                <td><input type="text" id="electronicCommerceIndicator" name="electronicCommerceIndicator" /></td>                            
            </tr>
            <tr>
                <td><label for="authenticationProviderTransactionId">3DS Authentication Provider Transaction Id</label></td>
                <td><input type="text" id="authenticationProviderTransactionId" name="authenticationProviderTransactionId" /></td>                            
            </tr>
            <tr>
                <td><label for="enrolled">3DS Enrolled</label></td>                
                <td>
                    <select id="enrolled" name="enrolled">
                        <option></option>
                        <option>Y</option>
                        <option>N</option>
                        <option>U</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="status">3DS Status</label></td>                
                <td>
                    <select id="status" name="status">
                        <option></option>
                        <option>Y</option>
                        <option>N</option>
                        <option>U</option>
                        <option>A</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="verificationToken">3DS Verification Token</label></td>
                <td><input type="text" id="verificationToken" name="verificationToken" /></td>                            
            </tr>
            <tr>
                <td><label for="verificationType">3DS Verification Type</label></td>                
                <td>
                    <select id="verificationType" name="verificationType">
                        <option></option>
                        <option>3DS</option>
                        <option>SPA</option>
                    </select>
                </td>
            </tr>
            <tr>
                <td><label for="verificationSecurityLevel">3DS Verification Security Level</label></td>
                <td><input type="text" id="verificationSecurityLevel" name="verificationSecurityLevel" /></td>                            
            </tr>
            <tr>
                <td><label for="verificationStatus">3DS Verification Status</label></td>
                <td><input type="text" id="verificationStatus" name="verificationStatus" /></td>                            
            </tr>
        </table>
        </div>
        
        <table>                                
            <tr>
                <td><input type="submit" id="transactionRequest" name="transactionRequest" value="Transaction Request"/></td>
            </tr>
        </table>
</form>
</body>

</html>