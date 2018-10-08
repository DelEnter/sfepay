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
    String MD5key = "iKfcWg{q";
    MD5 md5 = new MD5();
    String md5src = BillNo + Currency + Amount + Succeed + MD5key;

   // System.out.println("到商户页面上的MD5SRC=");

	
    String md5str;
    System.out.println("billno"+BillNo);
    md5str = md5.getMD5ofStr(md5src);
%>
out.print("===="+BillNo);
<html>
<head><title>商户交易结果页面</title></head>

<body>

<p align="center">=商户交易结果页面</p>
<table align="center" border="1" cellpadding="4">

    <tr>
        <td>订单号:</td>
        <td><%=BillNo%></td>
    </tr>
    <tr>
        <td>币种</td>
        <td><%=Currency%></td>
    </tr>
    <tr>
        <td>金额</td>
        <td><%=Amount%></td>
    </tr>
    <tr>
        <td>支付状态</td>
        <td><%=Succeed%></td>
    </tr>
    <tr>
        <td>支付结果</td>
        <td><%=Result%></td>
    </tr>
    <tr>
        <td>取得的MD5校验信息</td>
        <td> <%=MD5info%></td>
    </tr>
    <tr>
        <td>本地MD5检验信息</td>
        <td><%=md5str%></td>
    </tr>
    <tr>
        <td>签名匹配</td>
        <td><%=(md5str.equalsIgnoreCase(MD5info))?"验证成功!":"验证失败!"%>
        	<s:property value="BillNo"/>
        </td>
    </tr>
</table>
<p align="center"><a href="#" onclick="javascript:window.close()"><font size=2 color=blove>【关闭此窗口】</font></a></p>
</body>

</html>

