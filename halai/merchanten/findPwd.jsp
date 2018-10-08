<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ECPSS Forgot Password</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript">
</script>
	
<s:fielderror />


<s:form action="resetPassword" namespace="/merchant" theme="simple">
<div class="container">
   <div class="header2">
     <div class="logo"><img src="images/logo.gif" alt=""/></div>
     <div class="slogan"><img src="images/slogan02.gif" alt="" /></div>
     <div class="welcome">
       <div class="help">
         <ul>
           <li><img src="images/icon_01.gif" alt="Help" />&nbsp;<a href="#">Help</a></li>
           <li><img src="images/icon_02.gif" alt="Customer Service" />&nbsp;<a href="#">Customer Service</a></li>

         </ul>
       </div>
       <div class="tips"><span><a href="#" class="font_color_01">Home</a></span></div>
       
     </div>
     <div class="bordder"></div>
    
     <div class="url"></div>
   </div>
   <div class="pagebody">
      <div class="login">
        <div class="login_left">

        <div class="login_right">
          <div class="login_title"><b>输入账户信息</b></div>
          <div class="login_title"><font color="red"><s:property value="messageAction"/></font></div>
          <div class="login_page">
          <ul>
            <li class="page_name">Merchant ID：</li>
            <li class="page_text"><s:textfield name="merno" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red"></li>
          </ul>
          <ul>
            <li class="page_name">User Name：</li>
            <li class="page_text"><s:textfield name="userName" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
          <ul>
            <li class="page_name">E-mail：</li>
            <li class="page_text"><s:textfield name="email" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
        </div>
          <div class="login_button">
            <s:submit cssClass="input_button_03" value="" id="login_b" />
          </div>
          <div class="login_button"><span></div>
        </div>
      </div>
   </div>
<br class="clear" />
   <div class="footer">
     <div><span class="font_color_01"><img src="images/icon_04.gif" alt="" />&nbsp;&nbsp;我有意见或建议，跟ECPSS说两句</span></div>
     <div class="links">
       <ul>
         <li><a href="#">公司简介</a> | <a href="#">网上支付</a> | <a href="#">免责声明</a> | <a href="#">隐私政策</a> | <a href="#">联系我们</a> </li>

         <li>Copyright(c)2010. All rights reserved. "Epcss"。International Payments - Secure Online Payments - Send Payments Online - Internet Payment </li>
       </ul>
     </div>
   </div>
</div>
</s:form>
</body>
<script type="text/javascript">
	document.forms(0).merno.focus();
	
function document.onkeyup(){
	if(event.keyCode==13){
		if( document.forms(0).merno.value!=""  &&  document.forms(0).password.value!="" ){
			document.getElementById("login_b").click();
		}
	}
}
</script>
</html>