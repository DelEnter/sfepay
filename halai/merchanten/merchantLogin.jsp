<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ECPSS - Merchant Login</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript">
	function refreshimg(){
	    		//document.getElementById("authImg").src="authImg";
	    		var random = Math.random();
	    		//IE7有缓存，为了保证验证不被缓存,加上随机内容的参数

	    		document.getElementById("authImg").src="../authImg.do?r="+random;
	    	}
	    	
</script>
	
<s:fielderror />


<s:form action="merLoginEn" namespace="/merchanten" theme="simple">
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
        <div class="login_button" align="right"><font color="red"><s:property value="messageAction"/></font></div>
      
        <div class="login_fontss">
            <ul>
              <li class="login_icon"><img src="images/icon_001.gif" alt="" /></li>
              <li class="login_font">Facilitate search information<br /> 
After being our member,you will get more privileges to view the information in our website. </li>
            </ul>
            <br class="clear" />
            <ul>
              <li class="login_icon"><img src="images/icon_002.gif" alt="" /></li>
              <li class="login_font">Get the lastest news and product information at the first time.After being our member, you will view the news and product information exclusively available to members, and be the beneficial of the lastest news at the first time.</li>
            </ul>
            <br class="clear" />
            <ul>
              <li class="login_icon"><img src="images/icon_003.gif" alt="" /></li>
              <li class="login_font">Purchase the products on our website at the most cost-effective prices and view the information.<br /> 
After being our member, you will purchase the products on our website at the most cost-effective prices.
</li>
            </ul>
            <div class="clear">&nbsp;</div>
          </div>
        
        <div class="login_button"><a href="../newRegEn.jsp" ><img src="images/sigin_up.gif" alt="" /></a></div>
        
      </div>

        <div class="login_right">
          <div class="login_title"><img src="images/title_login.gif" alt="" /></div>
          <div class="login_page">
          <ul>
            <li class="page_name">Ecpss ID:</li>
            <li class="page_text"><s:textfield name="merno" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red"></li>
          </ul>
          <ul>
            <li class="page_name">User name:</li>
            <li class="page_text"><s:textfield name="username" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
          <ul>
            <li class="page_name">Password:</li>
            <li class="page_text"><s:password name="password" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
          <ul>
            <li class="page_name">Auth-Code:</li>
            <li class="page_code"><input type="text" name="vercode" class="text_input_01" /></li>
            <li>
				<img src="../authImg.do" id="authImg"/>
				
				<a href="#" onClick="refreshimg()">vague?</a>
			</li>
          </ul>
        </div>
          <div class="login_button">
            <s:submit cssClass="input_button_02" value="" id="login_b" />
          </div>
          <div class="login_button"><span><a href="findPwd.jsp">Forgot Password?</a></span></div>
        </div>
      </div>
   </div>
   <!--尾部begin-->
   <%@ include file="foot.jsp"%>
   <!--尾部end-->	
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