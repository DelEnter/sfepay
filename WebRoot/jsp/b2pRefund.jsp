<%@page import="org.apache.commons.lang.StringUtils"%>
<%@page import="com.ecpss.util.MD5"%>
<%@ page language="java" 
    pageEncoding="utf-8"%>
<%
//字符编码
String CharacterEncoding = "UTF-8";
request.setCharacterEncoding(CharacterEncoding);
String tradeNo = request.getParameter("tradeNo");
String refundNo = request.getParameter("refundNo");
String merchantOrderNo = request.getParameter("merOrderNo");
String amount = request.getParameter("refundAmount");
String responseCode =request.getParameter("responseCode");
String MD5key = request.getParameter("MD5key");
MD5 md5=new MD5();
String md5src="";
String mes="";
if(StringUtils.isNotBlank(tradeNo)){
	md5src = tradeNo + amount + responseCode+ MD5key;
	md5src = md5.getMD5ofStr(md5src);
	mes="tradeNo="+tradeNo;
}else if(StringUtils.isNotBlank(merchantOrderNo)){
	md5src = merchantOrderNo + amount + responseCode+ MD5key;
	md5src = md5.getMD5ofStr(md5src);
	mes="merchantOrderNo="+merchantOrderNo;
}
%>
<%=mes%>&refundAmount=<%=amount%>&succeed=<%=responseCode %>&md5src=<%=md5src %>&refundNo=<%=refundNo %>
