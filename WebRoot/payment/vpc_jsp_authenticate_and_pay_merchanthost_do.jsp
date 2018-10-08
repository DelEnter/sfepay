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

<%! // Define Constants
    // ****************
    // This is secret for encoding the MD5 hash
    // This secret will vary from merchant to merchant
    // static final String SECURE_SECRET = "your-secure-hash-secret";
    String SECURE_SECRET = "";

    // This is an array for creating hex chars
    static final char[] HEX_TABLE = new char[] {
        '0', '1', '2', '3', '4', '5', '6', '7',
        '8', '9', 'A', 'B', 'C', 'D', 'E', 'F'};

//  ----------------------------------------------------------------------------

    String hashKeys = new String();
    String hashValues = new String();

   /**
    * This method is for sorting the fields and creating an MD5 secure hash.
    *
    * @param fields is a map of all the incoming hey-value pairs from the VPC
    * @param buf is the hash being returned for comparison to the incoming hash
    */
    String hashAllFields(Map fields) {

	    hashKeys = "";
	    hashValues = "";
	
	    // create a list and sort it
	    List fieldNames = new ArrayList(fields.keySet());
	    Collections.sort(fieldNames);
	    String SECURE_SECRET1 = (String) fields.get("SECURE_SECRET");
	    SECURE_SECRET = SECURE_SECRET1;
	    // create a buffer for the md5 input and add the secure secret first
	    StringBuffer buf = new StringBuffer();
	    buf.append(SECURE_SECRET);
	
	    // iterate through the list and add the remaining field values
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
	
	    // create the md5 hash and ISO-8859-1 encode it
	    try {
	        md5 = MessageDigest.getInstance("MD5");
	        ba = md5.digest(buf.toString().getBytes("ISO-8859-1"));
	    } catch (Exception e) {} // wont happen
	
	    hashValues = buf.toString();
	    return hex(ba);
	
	} // end hashAllFields()

//  ----------------------------------------------------------------------------

    /**
     * Returns Hex output of byte array
     */
    static String hex(byte[] input) {
        // create a StringBuffer 2x the size of the hash array
        StringBuffer sb = new StringBuffer(input.length * 2);

        // retrieve the byte array data, convert it to hex
        // and add it to the StringBuffer
        for (int i = 0; i < input.length; i++) {
            sb.append(HEX_TABLE[(input[i] >> 4) & 0xf]);
            sb.append(HEX_TABLE[input[i] & 0xf]);
        }
        return sb.toString();
    }

//  ----------------------------------------------------------------------------
%><%
    // *******************************************
    // START OF MAIN PROGRAM
    // *******************************************

	// The Page does a transaction using the Virtual Payment Client

    // retrieve all the parameters into a hash map
    Map fields = new HashMap();
    Enumeration e = request.getParameterNames();

    while (e.hasMoreElements()) {
        String fieldName = (String) e.nextElement();
        String fieldValue = request.getParameter(fieldName);
        if ((fieldValue != null) && (fieldValue.length() > 0)) {
            fields.put(fieldName, fieldValue);
        }
    }

    // no need to send the vpc url and submit button to the vpc
    String vpcURL = (String) fields.get("virtualPaymentClientURL");
    fields.remove("virtualPaymentClientURL");
    fields.remove("SubButL");


    // Add the submit button that will be used for the HTTPS POST on this page as
    // it will also get added to the POST data when the user submits the form and
    // will therefore need to be incorporated into the secure hash.
    //fields.put("submit", "Continue");

    // Create MD5 secure hash and insert it into the hash map if it was created
    // created. Remember if SECURE_SECRET = "" it will not be created
   // if (SECURE_SECRET != null && SECURE_SECRET.length() > 0) {
        String secureHash = hashAllFields(fields);
        fields.put("vpc_SecureHash", secureHash);
   // }

   // response.setHeader("Content-Type","text/html, charset=ISO-8859-1");
   // response.setHeader("Expires","Mon, 26 Jul 1997 05:00:00 GMT");
   // response.setDateHeader("Last-Modified", new Date().getTime());
   // response.setHeader("Cache-Control","no-store, no-cache, must-revalidate");
   // response.setHeader("Pragma","no-cache");
    
    
%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
    <head><title>Virtual Payment Client Example</title>
       
        <meta http-equiv="cache-control" content="no-cache" />
        <meta http-equiv="pragma" content="no-cache" />
        <meta http-equiv="expires" content="0" />
        <style type='text/css'>
            .shadow   { height:25px; background-color:#E1E1E1 }
        </style>
        
    </head>
    <body onload="javascript:document.getElementById('RedirectForm').submit()" >   <!--  onload="javascript:document.forms[0].submit()"  -->
        <!-- start branding table -->
        	<table width="60%" align="center">
				
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
					<td>
						<b><font size=5 color=blue >Connecting......</font><br><br></b>
					</td>
				</tr>
				
				<tr align="center">
					<td class="shadow" width="90%">
						<b>Thank you for your support. Please wait. </b>
					</td>
				</tr>
			</table>
        <!-- end branding table -->

        <form name="RedirectForm" id="RedirectForm" action="<%=vpcURL%>" method="post">
        <s:token />
            <table width="80%" align="center" border="0" cellpadding='0' cellspacing='0'>
<%
    for (Iterator itr = fields.keySet().iterator(); itr.hasNext();) {
        String fieldName = (String) itr.next();
%>
                <tr>
                    <td><input type="hidden" name="<%=fieldName%>" value="<%=fields.get(fieldName)%>">&nbsp;</td>
                    <td>&nbsp;</td>
                </tr>
<%
    }
%>
                <tr><td colspan="2">&nbsp;</td></tr>
                <tr><td colspan="2" align="center"><%--<input type="submit" name="submit" value="Continue" /></td>--%></tr>
            </table>
        </form>
    </body>
    </html>