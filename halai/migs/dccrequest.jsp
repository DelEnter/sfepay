<%-- Copyright MasterCard Asia/Pacific (Australia) Pty Ltd. --%>


<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8"/>
</head>
<body>

<h2>Example Stig Merchant Hosted Merchant Integration - DCC Request</h2>

<form action="postdccrequest.jsp" method="post">
    <div>
        <table>
            <tr>
                <td><label for="locale">Locale</label></td>
                <td><input type="text" id="locale" name="locale" value="en_AU"></td>
            </tr>
            <tr>
                <td><label for="saleAmount">Sale Base Units</label></td>
                <td><input type="text" id="saleAmount" value="1000" name="saleAmount" /></td>
            </tr>
            <tr>
                <td><label for="currency">Currency</label></td>
                <td><input type="text" id="currency" value="CNY" name="currency" /></td>
            </tr>            
            <tr>
                <td><label for="companyId">Company Id</label></td>
                <td><input type="text" id="companyId" value="555502" name="companyId" /></td>
            </tr>
            <tr>
                <td><label for="companyPassword">Company Password</label></td>
                <td><input type="text" id="companyPassword" value="FnYTcuf/wDkyfEoXrdpQVw==" name="companyPassword" /></td>
            </tr>
            <tr>
                <td><label for="cardNumber">Card Number</label></td>
                <td><input type="text" id="cardNumber" value="460699" name="cardNumber" /></td>
            </tr>            
            <tr>
                <td>
                    <label for="stigUrl">API Url</label>
                </td>
                <td>
                    <input type="text" id="stigUrl" value="https://mtf.dcc.migs.mastercard.com.au/stig/stig.api" name="stigUrl"/>
                </td>
            </tr>
            <tr>
                <td><input type="submit" id="dccRequest" name="dccRequest" value="DCC Request"/></td>
            </tr>
        </table>
    </div>

</form>
</body>

</html>