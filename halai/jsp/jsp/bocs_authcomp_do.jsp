<%@ page contentType="text/html;charset=ISO-8859-1" language="java" %>

<%@ page import="java.util.List,
                 java.util.ArrayList,
                 java.util.Collections,
                 java.util.Comparator,
                 java.util.Iterator,
                 java.util.Enumeration,
                 java.util.Date,
                 java.security.MessageDigest,
                 java.util.Map,
                 java.net.URLEncoder,
                 java.util.HashMap"%>

<%! static String SECURE_SECRET = "ncvb40n880aech0uxcj91sj0cj8a2xu1";

    static final char[] HEX_TABLE = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};


    String hashKeys = new String();
    String hashValues = new String();

    String hashAllFields(Map fields) {

	    hashKeys = "";
	    hashValues = "";
	
	    List fieldNames = new ArrayList(fields.keySet());
	    Collections.sort(fieldNames);
	
	    StringBuffer buf = new StringBuffer();
	    buf.append(SECURE_SECRET);
	
	    Iterator itr = fieldNames.iterator();
	
	    while (itr.hasNext()) {
	        String fieldName = (String) itr.next();
	        String fieldValue = (String) fields.get(fieldName);
	            hashKeys += fieldName + ", ";
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
	
	    hashValues = buf.toString();
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


%><%
    Map fields = new HashMap();
    Enumeration e = request.getParameterNames();

    while (e.hasMoreElements()) {
        String fieldName = (String) e.nextElement();
        String fieldValue = request.getParameter(fieldName);
        if ((fieldValue != null) && (fieldValue.length() > 0)) {
            fields.put(fieldName, fieldValue);
        }
    }

    
    String vpcURL = (String) fields.get("GatewayClientURL");
    fields.remove("GatewayClientURL");
    fields.remove("SubButL");


    
    fields.put("submit", "Continue");

    
    if (SECURE_SECRET != null && SECURE_SECRET.length() > 0) {
        String secureHash = hashAllFields(fields);
        fields.put("HASH", secureHash);
    }

    response.setHeader("Content-Type","text/html;charset=ISO-8859-1");
    response.setHeader("Expires","Mon, 26 Jul 1997 05:00:00 GMT");
    response.setDateHeader("Last-Modified", new Date().getTime());
    response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
    response.setHeader("Pragma","no-cache");
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>

    

    <head><title>Virtual Payment Client Example</title>
        <meta http-equiv='Content-Type' content='text/html; charset=ISO-8859-1'>
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        <style type='text/css'>
            <!--
            H1       { font-family:Arial,sans-serif; font-size:20pt; color:#08185A; font-weight:600; margin-bottom:0.1em}
            H2       { font-family:Arial,sans-serif; font-size:14pt; color:#08185A; font-weight:100; margin-top:0.1em}
            H2.co    { font-family:Arial,sans-serif; font-size:24pt; color:#08185A; margin-top:0.1em; margin-bottom:0.1em; font-weight:100}
            H3.co    { font-family:Arial,sans-serif; font-size:16pt; color:#FFFFFF; margin-top:0.1em; margin-bottom:0.1em; font-weight:100}
            BODY     { font-family:Verdana,Arial,sans-serif; font-size:10pt; color:#08185A background-color:#FFFFFF }
            TR       { height:25px; }
            TR.shade { height:25px; background-color:#E1E1E1 }
            TR.title { height:25px; background-color:#C1C1C1 }
            TD       { font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#08185A }
            P        { font-family:Verdana,Arial,sans-serif; font-size:10pt; color:#FFFFFF }
            P.blue   { font-family:Verdana,Arial,sans-serif; font-size:7pt;  color:#08185A }
            P.red    { font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#FF0066 }
            P.green  { font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#00AA00 }
            DIV.bl   { font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#C1C1C1 }
            LI       { font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#FF0066 }
            INPUT    { font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#08185A; background-color:#E1E1E1; font-weight:bold }
            SELECT   { font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#08185A; background-color:#E1E1E1; font-weight:bold; width:300 }
            TEXTAREA { font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#08185A; background-color:#E1E1E1; font-weight:normal; scrollbar-arrow-color:#08185A; scrollbar-base-color:#E1E1E1 }
            A:link   { font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#08185A }
            A:visited{ font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#08185A }
            A:hover  { font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#FF0000 }
            A:active { font-family:Verdana,Arial,sans-serif; font-size:8pt;  color:#FF0000 }
            -->
        </style>
    </head>
    <body>
        
        <table width='100%' border='2' cellpadding='2' bgcolor='#C1C1C1'>
            <tr>
                <td bgcolor='#E1E1E1' width='90%'><h2 class='co'>&nbsp;BOCS Gateway Client Example</h2></td>
                <td bgcolor='#C1C1C1' align='center'><h3 class='co'>BOCGS</h3></td>
            </tr>
        </table>
        

        <center>
          <h1>JSP MOTO AuthComplete Transaction</h1>
    </center>

        <form name="RedirectForm" action="<%=vpcURL%>" method="post">
            <table width="80%" align="center" border="0" cellpadding='0' cellspacing='0'>
<%
    for (Iterator itr = fields.keySet().iterator(); itr.hasNext();) {
        String fieldName = (String) itr.next();
%>
                <tr>
                    <td><input type="hidden" name="<%=fieldName%>" value="<%=fields.get(fieldName)%>"><%=fieldName%>:&nbsp;</td>
                    <td><%=fields.get(fieldName)%></td>
                </tr>
<%
    }
%>
                <tr><td colspan="2">&nbsp;</td></tr>
                <tr><td colspan="2" align="center"><input type="submit" name="submit" value="Continue" /></td></tr>
            </table>
        </form>
    </body>
    <head>
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="expires" content="0" />
    </head>
</html>