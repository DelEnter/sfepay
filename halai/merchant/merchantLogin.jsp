<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>SFEPAY 商户登陆系统</title>
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


<s:form action="merLogin" namespace="/merchant" theme="simple">

<div class="login_new">
  <div class="login_new_left">
    <div class="logo"><img src="images/sfe/logo.gif" alt="" /></div>
    <div class="meun">
      <ul>
        <li><a href="#">关于我们</a></li>
		<li>联系我们</li>
		<li>加入我们</li>
		<li>选择我们</li>
		<li>常见问题</li>
      </ul>
    </div>
    <div class="phone">
      <ul><li>迁易通国际支付</li>
<li>SFEpay Payment CO.LTD</li>
<li>Pudong Shanghai China</li>
<li>Nan ma tou road 101,602room</li>
<li>Tel.:021-61629498</li>
<li>400-690-8380</li></ul>
    </div>
  </div>
  <div class="login_new_right">
    <div class="banner"><img src="images/sfe/banner.gif" alt="" /></div>
    <div class="login">
      <div class="login_matter">
        <h2>用户登录</h2>
        <ul>
          <li>商户号：</li>
          <li><s:textfield name="merno" /></li>
          <li>用户名：</li>
          <li><s:textfield name="username" /></li>
          <li>密码：</li>
          <li><s:password name="password" /></li>
          <li>验证码：</li>
          <li>
            <input type="text" name="vercode" class="text_input_01" />
            <img src="../authImg.do" id="authImg"/>
		    <a href="#" onClick="refreshimg()">看不清</a>
          </li>
          <li>
            <s:submit cssClass="buttons" value="直接登录" id="login_b" />
            <a href="../newReg.jsp" >在线注册</a> | 
            <a href="findPwd.jsp">忘记密码</a>
			<s:property value="messageEnAction"/>
          </li>
          
        </ul>
        
        
      </div>
      <div class="wmtg">
         <h2>我们提供</h2>
         <ul>
           <li><b>VISA/MASTER 3D 收款通道</b></li>
           <li class="icon"><img src="images/sfe/logo_01.gif" alt="" /></li>
           <li class="icon_li">中国商户首选的跨境在线收付服务，帮助您赢得更高外贸收益</li>
         </ul>
         
         <ul>
           <li><b>VISA/MASTER 非3d收款通道</b></li>
           <li class="icon"><img src="images/sfe/logo_02.gif" alt="" /></li>
           <li class="icon_li">中国商户首选的跨境在线收款服务，帮助您赢得更高外贸收益，中国商户首选的跨境在线收付服务 </li>
         </ul>
         
      </div>
    
    </div>
  
  </div>
</div>


<!--
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
      <div class="login">
        <div class="login_left">
        <div class="login_button" align="right"><font color="red">
        	
        	<s:property value="messageEnAction"/></font></div>
      
        <div class="login_fontss">
            <ul>
              <li class="login_icon"><img src="images/icon_001.gif" alt="" /></li>
              <li class="login_font">
成为SFEPAY商户后，您将更自由,方便,安全的收款。 </li>
            </ul>
            <br class="clear" />
            <ul>
              <li class="login_icon"><img src="images/icon_002.gif" alt="" /></li>
              <li class="login_font">第一时间获得国际支付行业动态。SFEPAY更是即时推出新通道来适应各种商户收款需求. </li>
            </ul>
            <br class="clear" />
            <ul>
              <li class="login_icon"><img src="images/icon_003.gif" alt="" /></li>
              <li class="login_font">以低廉的付出获取丰厚的回报,让你的收款变的无限畅通. 
</li>
            </ul>
            <div class="clear">&nbsp;</div>
          </div>
        
        <div class="login_button"><a href="../newReg.jsp" ><img src="images/sigin_up.gif" alt="" /></a></div>
        
      </div>

        <div class="login_right">
          <div class="login_title"><img src="images/title_login.gif" alt="" /></div>
          <div class="login_page">
          <ul>
            <li class="page_name">商户号：</li>
            <li class="page_text"><s:textfield name="merno" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red"></li>
          </ul>
          <ul>
            <li class="page_name">用户名：</li>
            <li class="page_text"><s:textfield name="username" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
          <ul>
            <li class="page_name">密码：</li>
            <li class="page_text"><s:password name="password" cssClass="text_input_02" size="15" /></li>
            <li class="page_font_red">&nbsp;</li>
          </ul>
          <ul>
            <li class="page_name">验证码：</li>
            <li class="page_code"><input type="text" name="vercode" class="text_input_01" /></li>
            <li>
				<img src="../authImg.do" id="authImg"/>
				
				<a href="#" onClick="refreshimg()">看不清</a>
			</li>
          </ul>
        </div>
          <div class="login_button">
            <s:submit cssClass="input_button_02" value="" id="login_b" />
          </div>
          <div class="login_button"><span><a href="findPwd.jsp">忘记密码？</a></span></div>
        </div>
      </div>
   </div>
<br class="clear" />
   <div class="footer">
     <div><span class="font_color_01"><img src="images/icon_04.gif" alt="" />&nbsp;&nbsp;我有意见或建议，跟SFEPAY说两句</span></div>
     <div class="links">
       <ul>
         <li><a href="#">公司简介</a> | <a href="#">网上支付</a> | <a href="#">免责声明</a> | <a href="#">隐私政策</a> | <a href="#">联系我们</a> </li>

         <li>Copyright(c)2010. All rights reserved. "Sfepay"。International Payments - Secure Online Payments - Send Payments Online - Internet Payment <script language="javascript" src="http://count35.51yes.com/click.aspx?id=357291377&logo=7" charset="gb2312"></script></li>
       </ul>
     </div>
   </div>
</div>
-->
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