<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>

<%@ page import="java.util.List,
                 java.util.ArrayList,
                 java.util.Collections,
                 java.util.Iterator,
                 java.util.Enumeration,
                 java.util.Date,
                 java.security.MessageDigest,
                 java.util.Map,
                 java.net.URLEncoder,
                 java.util.HashMap"%>

<%! static final String SECURE_SECRET = "ncvb40n880aech0uxcj91sj0cj8a2xu1";

    
    static final char[] HEX_TABLE = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    String hashAllFields(Map fields) {

        
        List fieldNames = new ArrayList(fields.keySet());
        Collections.sort(fieldNames);

        
        StringBuffer buf = new StringBuffer();
        buf.append(SECURE_SECRET);

        
        Iterator itr = fieldNames.iterator();

        while (itr.hasNext()) {
            String fieldName = (String) itr.next();
            String fieldValue = (String) fields.get(fieldName);
            if ((fieldValue != null) && (fieldValue.length() > 0)) {
                buf.append(fieldValue);
            }
        }

        MessageDigest md5 = null;
        byte[] ba = null;

        
        try {
            md5 = MessageDigest.getInstance("MD5");
            ba = md5.digest(buf.toString().getBytes("ISO-8859-1"));
         } catch (Exception e) {} 

       return hex(ba);

    } 
    static String hex(byte[] input) {
        
        StringBuffer sb = new StringBuffer(input.length * 2);

        
        for (int i = 0; i < input.length; i++) {
            sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
            sb.append(HEX_TABLE[input[i] & 0xf]);
        }
        return sb.toString();
    }


    private static String null2unknown(String in) {
        if (in == null || in.length() == 0) {
            return "No Value Returned";
        } else {
            return in;
        }
    } 
    String getResponseDescription(String vResponseCode) {

        String result = "";

        
        if (vResponseCode.length() != 1) {

            
            char input = vResponseCode.charAt(0);

            switch (input){
                case '0' : result = "Transaction Successful"; break;
                case '1' : result = "Unknown Error"; break;
                case '2' : result = "Bank Declined Transaction"; break;
                case '3' : result = "No Reply from Bank"; break;
                case '4' : result = "Expired Card"; break;
                case '5' : result = "Insufficient Funds"; break;
                case '6' : result = "Error Communicating with Bank"; break;
                case '7' : result = "Payment Server System Error"; break;
                case '8' : result = "Transaction Type Not Supported"; break;
                case '9' : result = "Bank declined transaction (Do not contact Bank)"; break;
                case 'A' : result = "Transaction Aborted"; break;
                case 'C' : result = "Transaction Cancelled"; break;
                case 'D' : result = "Deferred transaction has been received and is awaiting processing"; break;
                case 'F' : result = "3D Secure Authentication failed"; break;
                case 'I' : result = "Card Security Code verification failed"; break;
                case 'L' : result = "Shopping Transaction Locked (Please try the transaction again later)"; break;
                case 'N' : result = "Cardholder is not enrolled in Authentication Scheme"; break;
                case 'P' : result = "Transaction has been received by the Payment Adaptor and is being processed"; break;
                case 'R' : result = "Transaction was not processed - Reached limit of retry attempts allowed"; break;
                case 'S' : result = "Duplicate SessionID (OrderInfo)"; break;
                case 'T' : result = "Address Verification Failed"; break;
                case 'U' : result = "Card Security Code Failed"; break;
                case 'V' : result = "Address Verification and Card Security Code Failed"; break;
                case '?' : result = "Transaction status is unknown"; break;
                default  : result = "Unable to be determined";
            }

            return result;
        } else {
            return "No Value Returned";
        }
    } 
    private String displayAVSResponse(String vAVSResultCode) {

        String result = "";
        if (vAVSResultCode != null || vAVSResultCode.length() == 0) {

            if (vAVSResultCode.equalsIgnoreCase("Unsupported") || vAVSResultCode.equalsIgnoreCase("No Value Returned")) {
                result = "AVS not supported or there was no AVS data provided";
            } else {
                
                char input = vAVSResultCode.charAt(0);

                switch (input){
                    case 'X' : result = "Exact match - address and 9 digit ZIP/postal code"; break;
                    case 'Y' : result = "Exact match - address and 5 digit ZIP/postal code"; break;
                    case 'S' : result = "Service not supported or address not verified (international transaction)"; break;
                    case 'G' : result = "Issuer does not participate in AVS (international transaction)"; break;
                    case 'A' : result = "Address match only"; break;
                    case 'W' : result = "9 digit ZIP/postal code matched, Address not Matched"; break;
                    case 'Z' : result = "5 digit ZIP/postal code matched, Address not Matched"; break;
                    case 'R' : result = "Issuer system is unavailable"; break;
                    case 'U' : result = "Address unavailable or not verified"; break;
                    case 'E' : result = "Address and ZIP/postal code not provided"; break;
                    case 'N' : result = "Address and ZIP/postal code not matched"; break;
                    case '0' : result = "AVS not requested"; break;
                    default  : result = "Unable to be determined";
                }
            }
        } else {
            result = "null response";
        }
        return result;
    }


    private String displayCSCResponse(String vCSCResultCode) {

        String result = "";
        if (vCSCResultCode != null || vCSCResultCode.length() == 0) {

            if (vCSCResultCode.equalsIgnoreCase("Unsupported")  || vCSCResultCode.equalsIgnoreCase("No Value Returned")) {
                result = "CSC not supported or there was no CSC data provided";
            } else {
                
                char input = vCSCResultCode.charAt(0);

                switch (input){
                    case 'M' : result = "Exact code match"; break;
                    case 'S' : result = "Merchant has indicated that CSC is not present on the card (MOTO situation)"; break;
                    case 'P' : result = "Code not processed"; break;
                    case 'U' : result = "Card issuer is not registered and/or certified"; break;
                    case 'N' : result = "Code invalid or not matched"; break;
                    default  : result = "Unable to be determined";
                }
            }

        } else {
            result = "null response";
        }
        return result;
    }


    private String getStatusDescription(String vStatus) {

        String result = "";
        if (vStatus != null && !vStatus.equals("")) {

            if (vStatus.equalsIgnoreCase("Unsupported")  || vStatus.equals("No Value Returned")) {
                result = "3DS not supported or there was no 3DS data provided";
            } else {

                char input = vStatus.charAt(0);

                switch (input){
                    case 'Y'  : result = "The cardholder was successfully authenticated."; break;
                    case 'E'  : result = "The cardholder is not enrolled."; break;
                    case 'N'  : result = "The cardholder was not verified."; break;
                    case 'U'  : result = "The cardholder's Issuer was unable to authenticate due to some system error at the Issuer."; break;
                    case 'F'  : result = "There was an error in the format of the request from the merchant."; break;
                    case 'A'  : result = "Authentication of your Merchant ID and Password to the ACS Directory Failed."; break;
                    case 'D'  : result = "Error communicating with the Directory Server."; break;
                    case 'C'  : result = "The card type is not supported for authentication."; break;
                    case 'S'  : result = "The signature on the response received from the Issuer could not be validated."; break;
                    case 'P'  : result = "Error parsing input from Issuer."; break;
                    case 'I'  : result = "Internal Payment Server system error."; break;
                    default   : result = "Unable to be determined"; break;
                }
            }
        } else {
            result = "null response";
        }
        return result;
    }


%><%
    Map fields = new HashMap();
    for (Enumeration e = request.getParameterNames(); e.hasMoreElements();) {
        String fieldName = (String) e.nextElement();
        String fieldValue = request.getParameter(fieldName);
        if ((fieldValue != null) && (fieldValue.length() > 0)) {
            fields.put(fieldName, fieldValue);
        }
    }


    String bocs_hash = null2unknown((String) fields.remove("HASH"));
    String hashValidated = null;

    
    boolean errorExists = false;

    if (SECURE_SECRET != null && SECURE_SECRET.length() > 0 &&
        (fields.get("Resp_Code") != null || fields.get("Resp_Code") != "No Value Returned")) {

        
        String secureHash = hashAllFields(fields);

        
        if (bocs_hash.equalsIgnoreCase(secureHash)) {
            
            hashValidated = "<font color='#00AA00'><strong>CORRECT</strong></font>";
        } else {
            
            errorExists = true;
            hashValidated = "<font color='#FF0066'><strong>INVALID HASH</strong></font>";
        }
    } else {
        
        hashValidated = "<font color='orange'><strong>Not Calculated - No 'SECURE_SECRET' present.</strong></font>";
    }

    
    String type            = null2unknown((String)fields.get("Trans_Type"));
    String merchantID      = null2unknown((String)fields.get("Merchant_Id"));
    String access          = null2unknown((String)fields.get("Author_Str"));
    String terminalID      = null2unknown((String)fields.get("Terminal_Id"));
    String invoice         = null2unknown((String)fields.get("Invoice_No"));
	String type1           = null2unknown((String)fields.get("Trans_Type_Ori"));
    String refNo           = null2unknown((String)fields.get("Ref_No"));
    String resCode         = null2unknown((String)fields.get("Resp_Code"));
	String resCodeQ        = null2unknown((String)fields.get("Resp_Code_Q"));
    String orderNo         = null2unknown((String)fields.get("Order_No"));
    String authID          = null2unknown((String)fields.get("Auth_Code"));
	String cust            = null2unknown((String)fields.get("Custom"));
    String date            = null2unknown((String)fields.get("Trans_Date"));
    String time            = null2unknown((String)fields.get("Trans_Time"));
    String currency        = null2unknown((String)fields.get("Currency_Code_T"));
	String amount          = null2unknown((String)fields.get("Amount_Loc"));
    String ramount         = null2unknown((String)fields.get("Amount_Ref"));
	String bamount         = null2unknown((String)fields.get("Amount_Bal"));
    String dcccurrency     = null2unknown((String)fields.get("Currency_Code"));
    String dccamount       = null2unknown((String)fields.get("Amount_For"));

    String error = "";
    if (resCode.equals("7") || resCode.equals("No Value Returned") || errorExists) {
        error = "Error ";
    }

    response.setHeader("Content-Type","text/html; charset=ISO-8859-1");
    response.setHeader("Expires","Mon, 26 Jul 1997 05:00:00 GMT");
    response.setDateHeader("Last-Modified", new Date().getTime());
    response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
    response.setHeader("Pragma","no-cache");

%>  <!DOCTYPE HTML PUBLIC '-//W3C//DTD HTML 4.01 Transitional//EN'>
    <HTML>
    <HEAD><TITLE>JSP VPC Example - VPC Response <%=error%>Page</TITLE>
        <meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        <STYLE type='text/css'>
            <!--
            H1       { font-family:Arial,sans-serif; font-size:24pt; color:#08185A; font-weight:100}
            H2.co    { font-family:Arial,sans-serif; font-size:24pt; color:#08185A; margin-top:0.1em; margin-bottom:0.1em; font-weight:100}
            H3.co    { font-family:Arial,sans-serif; font-size:16pt; color:#000000; margin-top:0.1em; margin-bottom:0.1em; font-weight:100}
            body     { font-family:Verdana,Arial,sans-serif; font-size:10pt; color:#08185A background-color:#FFFFFF }
            P        { font-family:Verdana,Arial,sans-serif; font-size:8pt; color:#FFFFFF }
            A:link   { font-family:Verdana,Arial,sans-serif; font-size:8pt; color:#08185A }
            A:visited{ font-family:Verdana,Arial,sans-serif; font-size:8pt; color:#08185A }
            A:hover  { font-family:Verdana,Arial,sans-serif; font-size:8pt; color:#FF0000 }
            A:active { font-family:Verdana,Arial,sans-serif; font-size:8pt; color:#FF0000 }
            TD       { font-family:Verdana,Arial,sans-serif; font-size:8pt; color:#08185A }
            TD.red   { font-family:Verdana,Arial,sans-serif; font-size:8pt; color:#FF0066 }
            TD.green { font-family:Verdana,Arial,sans-serif; font-size:8pt; color:#00AA00 }
            TH       { font-family:Verdana,Arial,sans-serif; font-size:10pt; color:#08185A; font-weight:bold; background-color:#E1E1E1; padding-top:0.5em; padding-bottom:0.5em}
            input    { font-family:Verdana,Arial,sans-serif; font-size:8pt; color:#08185A; background-color:#E1E1E1; font-weight:bold }
            select   { font-family:Verdana,Arial,sans-serif; font-size:8pt; color:#08185A; background-color:#E1E1E1; font-weight:bold; width:463 }
            textarea { font-family:Verdana,Arial,sans-serif; font-size:8pt; color:#08185A; background-color:#E1E1E1; font-weight:normal; scrollbar-arrow-color:#08185A; scrollbar-base-color:#E1E1E1 }
            -->
        </STYLE>
    </HEAD>
    <BODY>

    
    <table width='100%' border='2' cellpadding='2' bgcolor='#C1C1C1'><tr><td bgcolor='#E1E1E1' width='90%'><h2 class='co'>&nbsp;BOCS Gateway Client Example</h2></td><td bgcolor='#C1C1C1' align='center'><h3 class='co'>BOCGS</h3></td></tr></table>
    

    <CENTER>
      <H1>JSP MOTO Query Transaction<%=error%>Response Page</H1></CENTER>

    <TABLE width="85%" align='center' cellpadding='5' border='0'>

        <tr bgcolor="#C1C1C1">
            <td colspan="2" height="25"><p><strong>&nbsp;Basic</strong></p></td>
        </tr>
        <tr>
            <td width="50%" align='right'><strong><i>Transaction Type: </i></strong></td>
            <td width="50%"><%=type%></td>
        </tr>
       <tr bgcolor='#E1E1E1'>
            <td align='right'><strong><i>Query Response Code: </i></strong></td>
            <td><%=resCode%></td>
        </tr>
        <tr>
            <td align='right'><strong><i>Access Code: </i></strong></td>
            <td><%=access%></td>
        </tr>
        <tr bgcolor='#E1E1E1'>
            <td align='right'><strong><i>Merchant Id: </i></strong></td>
            <td><%=merchantID%></td>
        </tr>
        <tr>
            <td align='right'><strong><i>Terminal ID: </i></strong></td>
            <td><%=terminalID%></td>
        </tr>
        <tr>
            <td colspan='2' align='center'><font color='#C1C1C1'>Fields above are the request values returned.<br></font><HR>
            </td>
        </tr>
 		<tr bgcolor='#E1E1E1'>
            <td align='right'><strong><i>Original Transaction Type: </i></strong></td>
            <td><%=type1%></td>
        </tr>
        <tr>
            <td align='right'><strong><i><%=type1%> Response Code: </i></strong></td>
            <td><%=resCodeQ%></td>
        </tr>
        <tr bgcolor='#E1E1E1'>
            <td align='right'><strong><i>Author Code: </i></strong></td>
            <td><%=authID%></td>
        </tr>
        <tr>
            <td align='right'><strong><i>Invoice No: </i></strong></td>
            <td><%=invoice%></td>
        </tr>
        <tr bgcolor='#E1E1E1'>
            <td align='right'><strong><i>Refernce No: </i></strong></td>
            <td><%=refNo%></td>
        </tr><tr>
            <td align='right'><strong><i>Order No: </i></strong></td>
            <td><%=orderNo%></td>
        </tr>
        <tr bgcolor='#E1E1E1'>
            <td align='right'><strong><i>Custom No: </i></strong></td>
            <td><%=cust%></td>
        </tr>
        <tr>
            <td align='right'><strong><i>Trans Date: </i></strong></td>
            <td><%=date%></td>
        </tr>
        <tr bgcolor='#E1E1E1'>
            <td align='right'><strong><i>Trans Time: </i></strong></td>
            <td><%=time%></td>
        </tr>
        <tr>
            <td align='right'><strong><i>Currency Code: </i></strong></td>
            <td><%=currency%></td>
        </tr>
        <tr bgcolor='#E1E1E1'>
            <td align='right'><strong><i><%=type1%> Amount: </i></strong></td>
            <td><%=amount%></td>
        </tr>
        <tr>
            <td align='right'><strong><i>Balance Amount: </i></strong></td>
            <td><%=bamount%></td>
        </tr>

        <tr>
            <td colspan='2' align='center'><font color='#C1C1C1'>Fields above are for a standard transaction.<br><HR>
                Fields below are additional fields for extra functionality.</font><br></td>
        </tr>

        <tr bgcolor="#C1C1C1">
            <td colspan="2" height="25"><p><strong>&nbsp;DCC Field</strong></p></td>
        </tr>
        <tr>
            <td align='right'><strong><i>DCC Currency: </i></strong></td>
            <td><%=dcccurrency%></td>
        </tr>
        <tr bgcolor='#E1E1E1'>
            <td align='right'><strong><i>DCC Amount: </i></strong></td>
            <td><%=dccamount%></td>
        </tr>

        <tr bgcolor="#C1C1C1">
            <td colspan="2" height="25"><p><strong>&nbsp;Hash Validation</strong></p></td>
        </tr>
        <tr>
            <td align="right"><strong><i>Hash Validated Correctly: </i></strong></td>
            <td><%=hashValidated%></td>
        </tr>
</TABLE><br>

    <CENTER><P><A HREF='./bocs_sales.html'>New Transaction</A></P></CENTER>

    </BODY>
    <head>
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="expires" content="0" />
    </head>
    </HTML>
