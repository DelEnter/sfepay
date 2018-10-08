<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>SFEPAY 商户修改密码系统</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<script type="text/javascript">
</script>
	
<s:fielderror />


<s:form action="modifyMerchantPwd" namespace="/merchant" theme="simple">
<div class="container">
   <div class="header2">
     <div class="logo"><img src="images/logo.gif" alt=""/></div>
     <div class="slogan"><img src="images/slogan02.gif" alt="" /></div>
     <div class="welcome">
       <div class="help">
         <ul>
           <li><img src="images/icon_01.gif" alt="帮助中心" />&nbsp;<a href="#">帮助中心</a></li>
           <li><img src="images/icon_02.gif" alt="在线客服" />&nbsp;<a href="#">在线客服</a></li>

         </ul>
       </div>
       <div class="tips"><span><a href="#" class="font_color_01">返回首页</a></span></div>
       
     </div>
     <div class="bordder"></div>
    
     <div class="url"></div>
   </div>
   <div class="pagebody">
      <div class="login" style="width:250px; margin:0 auto;">
        <div class="login_left" style="display:none">
        <div class="login_button">
        	<font color="red">
        		
        		密码强度建议： 
        	</font></div>
      
        <div class="login_fontss">
            <ul>
              <li class="login_icon"><img src="images/icon_001.gif" alt="" /></li>
              <li class="login_font">密码最小长度为6位； </li>
            </ul>
            <br class="clear" />
            <ul>
              <li class="login_icon"><img src="images/icon_002.gif" alt="" /></li>
              <li class="login_font">高强度密码须包含以下4种中的3种： </li>
            </ul>
            <br class="clear" />
            <ul>
              <li class="login_icon"><img src="images/icon_003.gif" alt="" /></li>
              <li class="login_font">大写字母 &nbsp;小写字母 &nbsp;数字&nbsp;&nbsp;特殊符号，如$、*、%... &nbsp;</li>
            </ul>
            <div class="clear">&nbsp;</div>
          </div>
        
        <div class="login_button"></div>
        
      </div>

        <div class="login_right2" >
          <div class="login_title"><img src="images/title_login2.gif" alt="" /></div>
          <div class="login_page">
          <ul>
            <li class="page_font_red"><s:property value="messageAction"/></li>
          </ul>
          <ul>
            <li class="page_name2">商户名：</li>
            <li class="page_text"><s:property value="merchant.merno"/>
            <s:hidden name="merchant.merno"/></li>
            <li class="page_font_red"></li>
          </ul>
          <ul>
            <li class="page_name2">用户名：</li>
            <li class="page_text"><s:property value="merchant.username"/>
            
            <s:hidden name="merchant.username"/></li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
          <ul>
            <li class="page_name2">旧密码：</li>
            <li class="page_text"><s:password name="password" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
          <ul>
            <li class="page_name2">身份证：</li>
            <li class="page_text"><s:textfield name="shenfenzheng" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
          <ul>
            <li class="page_name2">新密码：</li>
            <li class="page_text"><s:password name="newpassword" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
          <ul>
            <li class="page_name2">确认密码：</li>
            <li class="page_text"><s:password name="confirmPwd" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
          <ul>
            <li class="page_name2">验证码：</li>
            <li class="page_code"><input type="text" class="text_input_01" /></li>
            <li class="page_code">1123</li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
        </div>
          <div class="login_button">
            <s:submit cssClass="input_button_03" value="" id="login_b" />
          </div>
        </div>
      </div>
   </div>
<br class="clear" />
   <div class="footer">
     <div><span class="font_color_01"><img src="images/icon_04.gif" alt="" />&nbsp;&nbsp;我有意见或建议，想说两句</span></div>
     <div class="links">
       <ul>
         <li><a href="#">公司简介</a> | <a href="#">网上支付</a> | <a href="#">免责声明</a> | <a href="#">隐私政策</a> | <a href="#">联系我们</a> </li>

         <li>Copyright(c)2010. All rights reserved. "Epcss"。International Payments - Secure Online Payments - Send Payments Online - Internet Payment </li>
       </ul>
     </div>
   </div>
</div>
</s:form>

      



<!--<table align="center" width="918px" height="639px">
	<tr>
		<td>
			<table border="0">
			<tr>
					<td >&nbsp;</td>
					<td width="50">商户号：</td>
					<td><s:textfield name="merno" cssClass="input01" size="15" /></td>
					<td width="50">用户名：</td>
					<td><s:textfield name="username" cssClass="input01" size="15" /></td>
					<td width="45">密 码：</td>
					<td><s:password name="password" cssClass="input01" size="15" /></td>
					<td align="right"></td>
					<td><s:submit cssClass="button01" value="登　录" id="login_b" /></td>
					<td> <a href="../newReg.jsp" >注册  </a></td>
			</tr>
			</table>
		</td>
	</tr>
</table> -->
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