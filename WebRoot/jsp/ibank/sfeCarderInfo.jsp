<%@page import="com.ecpss.util.MD5"%>
<%@ page language="java" 
    pageEncoding="utf-8"%>
<%
//字符编码
String CharacterEncoding = "UTF-8";
request.setCharacterEncoding(CharacterEncoding);
String merchantOrderNo = request.getParameter("merchantOrderNo");
String tradeNo = request.getParameter("tradeNo");
String amount = request.getParameter("Amount");
String responseCode =request.getParameter("responseCode");
String billaddress = request.getParameter("billaddress");
String remark = request.getParameter("remark");
String MD5key = request.getParameter("MD5key");
MD5 md5=new MD5();
String md5src = merchantOrderNo + amount + responseCode+ MD5key;
md5src = md5.getMD5ofStr(md5src);
System.out.println("账单地址："+billaddress);
%>
merchantOrderNo=<%=merchantOrderNo%>&amount=<%=amount%>&billaddress=<%=billaddress%>&succeed=<%=responseCode %>&remark=<%=remark %>&md5src=<%=md5src %>&tradeNo=<%=tradeNo %>
