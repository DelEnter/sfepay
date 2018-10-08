<%@ page import="com.ecpss.util.MD5" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<%--
 Created by IntelliJ IDEA.
 User: Bluewater
 Date: 2005-7-21
 Time: 20:55:45
 To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    //字符编码
    String CharacterEncoding = "UTF-8";
    request.setCharacterEncoding(CharacterEncoding);
    String BillNo = request.getParameter("BillNo");
    String Currency = request.getParameter("Currency");
    String Amount = request.getParameter("Amount");
    String tradeOrder = request.getParameter("tradeOrder");
    String Succeed = request.getParameter("Succeed");
    String Result = request.getParameter("Result");
    String MD5info = request.getParameter("MD5info");
    String MD5key = "ecpss123";
    MD5 md5 = new MD5();
    String md5src = BillNo + Currency + Amount + Succeed + MD5key;

  
%>

<html>
<head><title>Payment Result</title></head>

<body>

<p align="center">Payment Result</p>
<table align="center" border="1" cellpadding="4">

    <tr>
        <td>Order No.:</td>
        <td><%=BillNo%></td>
    </tr>
    <tr>
        <td>Amount:</td>
        <td><%=Amount%></td>
    </tr>
    <tr>
        <td>Payment Result:</td>
        <td><%=Result%></td>
    </tr>
</table>
<p align="center"><a href="#" onclick="javascript:window.close()"><font size=2 color=blove>【Close】</font></a></p>
</body>

</html>

