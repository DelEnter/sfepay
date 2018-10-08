<%
//字符编码
String CharacterEncoding = "UTF-8";
request.setCharacterEncoding(CharacterEncoding);
String ReturnURL = request.getParameter("ReturnURL");
String merchantOrderNo = request.getParameter("merchantOrderNo");
String tradeMoneyType = request.getParameter("tradeMoneyType");
String ordercount = request.getParameter("ordercount");
String responseCode =request.getParameter("responseCode");
String message = request.getParameter("message");
String md5Value = request.getParameter("md5Value");
%>
BillNo=<%=merchantOrderNo %>&Currency=<%=tradeMoneyType %>&Amount=<%=ordercount %>&Succeed=<%=responseCode %>&Result=<%=message %>&ReturnURL=<%=ReturnURL %>&MD5info=<%=md5Value %>
