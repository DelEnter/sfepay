<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@page import ="java.sql.*"%>
<!--<%@ page import="com.tu.model.*,com.tu.buycart.*,java.util.ArrayList"%>-->
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    <title>payment...</title>
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="payment">
	<meta http-equiv="description" content="payment">
  </head>
  <body>    
<%!
/*String DBDRIVER = "com.mysql.jdbc.Driver";
 String DBURL ="jdbc:mysql://198.11.175.67:3306/myorder?autoReconnect=true";
 String DBUSER ="myusertuweitian";
 String DBPASSWORD ="mypasswordhuxiaomi";
 Connection conn = null;
 Statement stmt = null;
 Long id=0L;*/
%>
<%
 /*try
 {
  Class.forName(DBDRIVER);
  conn=DriverManager.getConnection(DBURL,DBUSER,DBPASSWORD);
   try { 
              // 事务开始  
            System.out.println("事务处理开始！");  
            conn.setAutoCommit(false);   // 设置连接不自动提交，即用该连接进行的操作都不更新到数据库  
            stmt = conn.createStatement(); // 创建Statement对象  
            
            
             // 1，处理
             
             //INSERT INTO myorder(amount,billFirstName,billLastName,billAddress,billCity,billState,billCountry,billZip,email,phone)VALUES(@amount,@billFirstName,@billLastName,@billAddress,@billCity,@billState,@billCountry,@billZip,@email,@phone)
  			String sql = "INSERT INTO myorder(amount,billFirstName,billLastName,billAddress,billCity,billState,billCountry,billZip,email,phone)VALUES(?,?,?,?,?,?,?,?,?,?)";  
            // 指定返回生成的主键  
            PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);   //自动返回ID
            // 如果使用静态的SQL，则不需要动态插入参数  
            pstmt.setString(1, request.getParameter("amount")); 
            pstmt.setString(2, request.getParameter("billFirstName")); 
            pstmt.setString(3, request.getParameter("billLastName")); 
            pstmt.setString(4, request.getParameter("billAddress")); 
            pstmt.setString(5, request.getParameter("billCity")); 
            pstmt.setString(6, request.getParameter("billState")); 
            pstmt.setString(7, request.getParameter("billCountry")); 
            pstmt.setString(8, request.getParameter("billZip")); 
            pstmt.setString(9, request.getParameter("email")); 
            pstmt.setString(10,request.getParameter("phone")); 

            
            
             
            pstmt.executeUpdate();   
            // 检索由于执行此 Statement 对象而创建的所有自动生成的键   
            ResultSet rs = pstmt.getGeneratedKeys();   
            
            if (rs.next()) {  
                id = rs.getLong(1);   
                System.out.println("ID:" + id);   
            }  
            
             // 2，处理
             
             
       	mycart c = (mycart)session.getAttribute("cart");

		List<cart> items = c.getItems();
		

		
		for (cart ct : items) {
		/*
			System.out.println("getId:" + ct.getId());
			System.out.println("getProductname:" + ct.getProductname());
			System.out.println("getCount:" + ct.getCount());
			System.out.println("getPrice:" + ct.getPrice());
			System.out.println("getComment:" + ct.getComment());
		*/	
			//琯入数据库
						
			//INSERT INTO myorder(amount,billFirstName,billLastName,billAddress,billCity,billState,billCountry,billZip,email,phone)VALUES(@amount,@billFirstName,@billLastName,@billAddress,@billCity,@billState,@billCountry,@billZip,@email,@phone)
  	/*		String sql2 = "INSERT INTO myorderdetial(productname,count,comment,price,orderid,productid)VALUES(?,?,?,?,?,?)";  
  			
  			PreparedStatement pstmt2 = conn.prepareStatement(sql2);
            pstmt2.setString(1,ct.getProductname()); 
            pstmt2.setFloat(2, ct.getCount()); 
            pstmt2.setString(3,ct.getComment()); 
            pstmt2.setFloat(4,ct.getPrice()); 
            pstmt2.setInt(5, id.intValue()); 
            pstmt2.setInt(6, ct.getId()); 
            
            pstmt2.execute();
			
			
		}
             
            conn.commit();   // 提交给数据库处理  
              
            System.out.println("事务处理结束！");
                  
        //捕获执行SQL语句组中的异常      
        } catch (SQLException e) {  
            try {  
             	System.out.println(e);  
                System.out.println("事务执行失败，进行回滚！\n");  
                conn.rollback(); // 若前面某条语句出现异常时，进行回滚，取消前面执行的所有操作  
            } catch (SQLException e1) {  
                e1.printStackTrace();  
            }  
        } finally {  
            stmt.close();
            conn.close();
        }  
        
 }
 catch(Exception e)
 {
  out.println(e);
 }*/
%>
<h1>payment...</h1>
<form target="_self" action="" target="_top" method="post" id="E_FORM" name="E_FORM">
<input type="hidden" name="add" value="1">
<input type="hidden" name="cmd" value="_xclick">  <%--_xclick代表已做好购物车--%>
<input type="hidden" name="business" value="tuweitian@aol.com">  <%--付入帐户--%>
<input type="hidden" name="item_name" value="">  <%--名称/可以是订单号--%>
<input type="hidden" name="item_number" value="">  <%--可以记录其他信息，如用户ID等--%>
<input type="hidden" name="amount" value="">  <%--金额--%>
<input type="hidden" name="locale.x" value="en_US" />  <%--语种--%>
<input type="hidden" name="no_note" value="1">
<input type="hidden" name="quantity" value="1">  <%--数量--%>
<input type="hidden" name="currency_code" value="USD">  <%--币种--%>
<input type="hidden" name="bn" value="PP-BuyNowBF">
<input type="hidden" name="rm" value="2">  <%--值为2时，支付完成返回值--%>
<input type="hidden" name="return" value="">  <%--返回页面--%>
<%-- <input type="hidden" name="notify_url" value="http://">  Post页面或者pp后台设置--%>
<input style="margin-top:10px" type="image" src="images/payment.png" id="yes" name="b1" value="Payment"  >
</form>
<script type="text/javascript">

</script>
  </body>
</html>
