<%@ page import="com.ecpss.util.MD5" %>
<%
    String MD5key; //MD5key值
    MD5key = "qufwYah}";

    String BillNo;  //订单编号
    BillNo = "2E00001383";
    String Currency;    //币种
    Currency = "1";
    String Amount;  //支付金额
    Amount = "77.93";
    
    String Succeed = "88";

    String md5src;  //加密字符串    
    //md5src = MerNo + BillNo + Currency + Amount + Language + ReturnURL + MD5key ;
    md5src = BillNo + Currency + Amount + Succeed + MD5key;
    MD5 md5 = new MD5();
    String MD5info; //MD5加密后的字符串
    MD5info = md5.getMD5ofStr(md5src);
    
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>测试接口</title></head>

<body>


<form action="https://secure.wedopay.net/straightLinePay?cardnum=4000000000000002&cvv2=234&month=09&year=15&MerNo=50042&Currency=1&BillNo=jyw10f&Amount=17.5&ReturnURL=http://localhost:8080/telemoneyv2lobby/viewstream.jsp&Language=en&Remark=Good&MD5info=103273E16301A667FD9B6A7D4368C0A0&products=&firstname=Peeter&lastname=Loom&cardbank=unknown&email=tomcrus e@yahoo.com&phone=168888668&zipcode=100000&address=ABC&city=ABC&state=ZHEJIANG&country=CHINA&shippingFirstName=Jet&shippingLastName=Wooo&shippingEmail=jetli@yahoo.com&shippingPhone=268888998&shippingZipCode=200000&shippingAddress=DEF&shippingCity=SHANGHAI&shippingState=SHANGHAI&shippingCountry=Malasia" method="post">
    
    <p align="center"><input type="submit"  name="b1" value="支付">
   
</form>
<h2 id="myH2"></h2>  
</body>
</html>
