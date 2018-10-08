<%@ page import="com.ecpss.util.MD5" %>
<%
    String MD5key; //MD5key值
    MD5key = ")fNAmBMY";

    String MerNo;   //商户ID
    MerNo = "1685";

    String BillNo;  //订单编号
    BillNo = String.valueOf(System.currentTimeMillis());
    String Currency;    //币种
    Currency = "1";
    String Amount;  //支付金额
    Amount = "0.01";

    String Language;    //支付语言
    Language = "en";

    String ReturnURL;   //返回地址
    
    ReturnURL = "http://192.168.2.100:8888/card/payresult.jsp";


//    ReturnURL = "http://192.168.1.108/ecpss/payresult.jsp";


    String tradeAdd;   //返回地址
    
    String remark = "好产品";
   

    String md5src;  //加密字符串    
    md5src = MerNo + BillNo + Currency + Amount + Language + ReturnURL + MD5key ;
    MD5 md5 = new MD5();
    String MD5info; //MD5加密后的字符串
    MD5info = md5.getMD5ofStr(md5src);
    
%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head><title>测试接口</title></head>

<body>
<form action="https://172.16.10.98/GatewayT/bgs3p method="post">
    <table align="center">
        <caption align="top"><font color=blove>=支付信息</font></caption>
       
        <tr><td>Trans_Type</td>
            <td>
            	<input type="text" name="Trans_Type" value="presales">
            </td>
        </tr>
        <tr><td>Trans_Model</td>
            <td><input type="text" name="Trans_Model" value=""></td></tr>

        <tr><td>Merchant_Id</td>
            <td><input type="text" name="Merchant_Id " value=""></td></tr>

        <tr><td>Author_Str</td>
            <td><input type="text" name="Author_Str" value=""></td></tr>

        <tr><td>Terminal_Id</td>
            <td><input type="text" name="Terminal_Id" value="" size="60"></td></tr>

        <tr><td>Currency_Code_T</td>
            <td><input type="text" name="Currency_Code_T" value=""></td></tr>

		<tr>
			<td>Amount_Loc</td>
			<td><input type="text" name="Amount_Loc" value=""></td>
		</tr>

        <tr><td>Order_No</td>
            <td><input type="text" name="Order_No" value=""></td></tr>
         <tr><td>Custom</td>
            <td><input type="text" name="Custom" value=""></td></tr>            
         <tr><td>bocs_ReturnURL</td>
            <td><input type="text" name="bocs_ReturnURL" value=""></td></tr>
         <tr><td>end _ReturnURL</td>
            <td><input type="text" name="end _ReturnURL" value=""></td></tr>   
            
         <td>HASH</td>               
         <tr><td>HASH</td>
         <td><input type="text" name="HASH" value=""></td></tr>     
 
    </table>
    <p align="center"><input type="submit" name="b1" value="支付">
     </p>

</form>
</body>
</html>
