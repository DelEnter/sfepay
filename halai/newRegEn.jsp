 <%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ECPSS - Registration</title>
	
<%
	//MD5 key
	String radStr = "AaBbCcDdEeFfGgHhiJjKkLMmNnOoPpQqRrSsTtUuVvWwXxYyZz~!^()_{}[]";
	StringBuffer generateRandStr = new StringBuffer();
	Random rand = new Random();
	int length = 8;
	for (int i = 0; i < length; i++) {
		int randNum = rand.nextInt(60);
		generateRandStr.append(radStr.substring(randNum, randNum + 1));
	}
%>
	<style type="text/css">
		/* CSS Document */
*{margin:0;padding:0;}
body,.am .main_box h3,.am .main_box p input{font-size:12px;}
body,body a,.one .col_1 .road{color:#767676;}
body a{text-decoration:none;}
body a:hover,.p_t a{text-decoration:underline;}
#page_bar img{border:none;}
.top_tile{background:url(img/top_tile.jpg) repeat-x;}
.top_owner{background:url(images/top_owne.gif) center 0 no-repeat;}
.banner{height:141px;}
.column_wrapper{width:867px;margin:0 auto;padding-left:12px;}
.one{height:137px;background:url(img/one_bg.jpg);}
.one .col_1,.one .col_2,.two .col_1,.two .col_2,.o_p .col_2_{width:305px;float:left;padding-left:14px;}
.one .col_2{width:auto;padding-left:207px;position:relative;}
.one .col_1 input{border:1px solid #aeaeae;}
.one .col_1 .long,.one .col_1 .short{width:98px;height:18px;}
.one .col_1 .short{width:43px;}
.one .col_1 .road{width:83px;height:20px;}
.one .col_1 .login{background:url(img/login.gif);width:72px;height:22px;border:none;cursor:pointer;}
.one .col_2 h2,.pass_t .col_1 h2,.pass_t .col_2_2 h2,.o_p .col_2_ h2{color:#535353;margin:12px 0;}
.one .col_1 h2 span{color:#eb6d02;}
.one .col_1 h3, .one .col_1 h3.dl{color:#eb6d02;font-size:13px;padding:2px 14px 0 0;text-align:center;}
.one .col_1 h3.dl{padding:20px 14px 0 0;}
.one .col_2 h2,.one .col_2 p{color:#d15e01;margin:25px 0 0;}
.one .col_2 p{line-height:18px;width:312px;margin-top:5px;}
.one .col_2 a{width:146px;height:43px;display:block;position:absolute;left:29px;top:44px;}
.two{clear:both;padding:10px 0 10px 12px;background:#fff;overflow:hidden;}
.two .col_1{padding:0;width:319px;}
.two .col_1 img{border:none;}
.two .col_2{width:526px;padding-left:10px;font-family:Arial, Helvetica, sans-serif;}
.two .col_2 ul li{list-style:none;background:url(img/li.gif) repeat-x left bottom;height:26px;line-height:26px;}
.two .col_2 ul li a,.foot a,.global-nav-links td a{text-decoration:none}
.foot{text-align:center;line-height:25px;background:url(img/foot.jpg) no-repeat center top;padding-top:17px;width:879px;margin:1px auto 0;height:88px;clear:both;}
.global-nav-links td{text-align:center;line-height:24px;}
.global-nav-links td.first {background:none;}
.o_p .col_1{width:303px;border:1px solid #f7d382;background:url(img/pass.jpg);}
.o_p{background:#fff url(img/wrap_foot.gif) no-repeat 0 100%;padding-bottom:10px;}
.o_p .col_1 input{border:1px solid #ffb75a;}
.o_p .col_1 .button{width:51px;height:18px;background:url(img/button.jpg);border:none;margin:4px 0;}
.o_p .col_1 .btn_2{background-position:0 100%;}
.o_p .col_1,.o_p .col_1 a,.o_p .col_1 .road{color:#b05100;}
.o_p .col_1 h2{color:#e32b2b;margin:6px 0 12px;}
.o_p .col_2_{padding:0 10px;width:528px;}
.o_p .col_2_ table{ background:#f3f7fb;margin-bottom:1px;}
.o_p .col_2_ table td{padding:6px 3px;*padding:5px 3px;}
.o_p .col_2_ table td img{border:none;margin-right:6px;}
.o_p .col_2_ a,.pass_t .col_1 a{color:#0265b2;}
.pass_t .col_1{background:url(img/pass_t.jpg);height:186px;border:1px solid #dfdfdf;padding-left:12px;width:305px;position:relative;}
.pass_t .col_1 h2{margin-top:8px;}
.pass_t .col_1 h1{font-size:18px;font-family:"黑体";font-weight:normal;padding:10px 0;}
.pass_t .col_1 p{line-height:22px;}
.pass_t .col_2_2 h2,.o_p .col_2_ h2{margin:8px 0 3px;*margin:6px 0 2px;}
.pass_t .col_1,.pass_t .col_2_2 h2,.o_p .col_2_ h2,.pass_t .col_1 h2{color:#0265b2;}
.pass_t .col_2_2 h2{color:#7f7f7f;}
.pass_t .col_2_2 ul li{list-style:none;float:left;margin:0 4px;width:252px;text-indent:10px;background:url(img/pass_li.gif) no-repeat 0 10px;line-height:21px;height:21px;font-size:13px;}
.pass_t .col_1 p span{background:url(img/blue.gif) no-repeat 0 4px;padding-left:8px;}
.pass_t .col_1 .om{position:absolute;width:98px;height:53px;bottom:10px;right:19px;background:url(img/om.jpg);}
.o_p .l,.o_p .r{float:left;width:319px;}
.o_p .r{width:auto;}
.pass_t .col_2_2{width:538px;padding:5px 0 5px 10px;}
/* return */
.one_return{background:#fff none;padding-top:40px;}
.one_return .text{width:500px;height:60px;border:1px solid #fc9003;margin:0 auto;background:#fff4bb url(img/ok.jpg) no-repeat 20px 9px;color:#ff5400;font-family:"黑体";padding-left:80px;}
.one_return .text h1{font-size:25px;margin-top:17px;}
.return_menu_box{text-align:center;}
.return_menu_box p{text-align:left;line-height:24px;color:#000;}

.nav_content {margin:0 auto;}
.nav_content .text_box {background:url(img/text.jpg) left bottom repeat-x #f8f8f8;padding:10px 18px 18px;}
.nav_content .text_box h3 {font-family:"微软雅黑";color:#ed0a02;font-size:20px;border-bottom:1px solid #e8e8e8;height:36px;}
.nav_content .text_box h3 span {float:right;background:url(img/an.jpg) left top no-repeat;width:85px;height:26px;padding-left:25px;line-height:25px;font-family:"宋体";font-size:12px;font-weight:500;color:#fff;}
.nav_content .text_box h3 span a {color:#fff;}
/*.nav_content .text_box h3 span {float:right;background:url(img/jr1.jpg) left top no-repeat;width:85px;height:24px;padding-left:26px;line-height:23px;font-family:"宋体";font-size:12px;font-weight:500;color:#4d4d4d;}*/

.nav_content .text_box p {line-height:1.6em;font-size:13px;padding-top:15px;color:#464646;}



/* false */
.one_return .false{background-image:url(img/false.gif);}
/* am */
.am{padding:20px 0 12px 12px;}
.am h4{font-size:12px;color:#767676;font-weight:normal;margin:0 0 10px 45px;}
.am h4 span{color:#ff4100;}
.am .main_box{width:783px;margin:0 auto;border:1px solid #e6e6e6;}
.am .main_box h3{height:24px;line-height:24px;padding-left:18px;color:#000;border-bottom:1px solid #e6e6e6;background:#f1f1f1;}
.am .main_box .main{height:218px;padding:8px 8px 5px 18px;line-height:22px;}
.am .form_box{text-align:center;margin:10px 0 0;*margin:10px 0;}
.am .form_box input{background:#eee;border:1px solid #767676;height:22px;line-height:22px;cursor:pointer;}
/* register */
.am .main_box .input_box{margin-left:17px;}
.am .main_box .input_box input{border:1px solid #9b9b9b;}
.am .main_box .input_box span,.am .main_box .input_box span a,.p_t,.p_t a{color:#f00;}
.am .form_box .submit_{background:url(img/register_submit.gif);width:72px;height:24px;color:#fff;border:none;font-weight:bold;}
.vspace,.line{font-size:0;height:10px;clear:both;}
.line{height:1px;width:528px;border-top:1px solid #dfdfdf;margin-left:10px;}
.pass_through p{line-height:40px;}
.p_t_menu{text-align:center;margin-top:10px;}
.p_t_menu input{margin-bottom:20px;}
.am .main_box .main,.o_p{overflow:auto;}
.o_p,.one_return,.am{height:auto;}
.one .col_1 h2, .one .col_2 h2,.pass_t .col_1 h2,.pass_t .col_2_2 h2,.o_p .col_2_ h2,.o_p .col_2_,.return_menu_box p{font-size:14px;}
.one .col_1_dl{background:url(img/one_bg.jpg) -12px 0;border:none;height:137px;border-right:1px solid #dcdbd6}
.one .col_1_dl h2{color:#535353;margin-top:17px;}
.am .mb_b{padding:10px 50px;width:683px;color:#000;margin-top:10px;}
.am .mb_b h2{font-size:15px;margin:10px 0;}
.am .mb_b p{line-height:22px;}
.pass_through .return_menu_box table{margin:0 auto;text-align:left;}
.pass_through .return_menu_box table p a,.pass_t .col_1 h1{color:#ee8c00;}

.foot .footer{background:none;clear:both;_height:10px;min-height:10px;margin:0 auto;color:#767676}
.foot .footer a{color:#767676}
.foot .footer .bottom_link{margin:0 auto;line-height:1.1em;text-align:center;font-family:Arial, "宋体"}
.foot .footer .bottom_copyright{width:360px;margin:0 auto;padding-bottom:22px;_height:1px;min-height:1px;font-family:Arial, "宋体"}
.foot .footer .bottom_copyright p{padding:18px 0 0;float:left;width:330px;_display:inline;text-align:center;line-height:1.4em}
.foot .footer .bottom_copyright .copyright_gongshang_logo{float:right;margin-top:20px}

.clear{height:1px; font-size:0; line-height:0; overflow:hidden; margin-top:-1px; clear:both;}
.dot_line{background:url(img/dot.gif) repeat-x 0 0; margin:0 0 0 10px; height:1px; width:528px; font-size:0;}
.box_r{width:538px; padding:0 0 0 10px; margin-top:10px;}
.box_r .c_l{float:left; width:260px;}
.box_r .c_l span.image{text-align:center; display:block; margin:8px 0 20px;}
.box_r .c_l span.btn{text-align:center; display:block;}
.box_r .c_l span img{border:none;}
.box_r .c_r{width:248px; float:left; padding:0 0 5px 10px;}
.box_r .c_r h3{margin-bottom:5px; font-size:14px; color:#0265B2;}
.box_r .c_r h3 a{color:#0265B2;}
.box_r .c_r p{line-height:20px;}
.box_r .c_r img{border:none;}
.box_r .i_con{width:520px;}
.box_r .i_con img.fr{float:left; margin-right:12px; border:1px solid #ccc;}
.box_r .i_con h3{margin-bottom:3px; font-size:14px; color:#0265B2;}
.box_r .i_con h3 a{color:#0265B2;}
.box_r .i_con p{line-height:20px;}
	</style>	
		

<link href="css/searchbar_w880.css" rel="stylesheet" type="text/css" />
<script language="JavaScript">

			function callUsername(usernameError){	
				dwr.util.setValue("usernameError", usernameError);
			}
		
 			function getValue(inputName,divName,innerName)
			{
				var username = document.getElementById(inputName).value;
				if(username=="")
				{
 					document.getElementById(divName).innerHTML=innerName;
 					return;
 				}
				else
 				{
 					document.getElementById(divName).innerHTML="";
 				}
			}
			function check(inputName,divName,innerName){
				getValue(inputName,divName,innerName);
			}
			//地址
			function address(){
				var add = document.getElementById('meradress').value;
				if(add==''){
					document.getElementById('addressInfo').innerHTML='Address Required!';
					return;
				}else{
					document.getElementById('addressInfo').innerHTML='';
					return;
				}
			}
			//验证密码和确认密码
 			function checkTwo()
 			{
 				var pw1 = document.getElementById('password').value;
 				var pw2 = document.getElementById('password1').value;
 				if(pw2=='')
 				{
		 			document.getElementById('pwInfo1').innerHTML='Confirm Password Required!';
		 			return;
		 		}
		 		else
		 		{
		 			if(pw1!=pw2)
		 			{
		 				document.getElementById('pwInfo1').innerHTML='Passwords do not match!';
		 				return;
		 			}
		 		}
		 		document.getElementById('pwInfo1').innerHTML='';
		 			
 			}
 			//验证身份证的长度
 			function checkCard(inputName,divName)
 			{
 				var email = document.getElementById(inputName).value;
  				var pattern = /^(\d{15}$|^\d{18}$|^\d{17}(\d|X|x))$/;
  				chkFlag = pattern.test(email);
  				if(chkFlag)
  				{
  					document.getElementById(divName).innerHTML='';
					return true;
				}
				else
				{
					document.getElementById(divName).innerHTML='ID number is incorrect!';
					return false;
				}
 			}
 			//验证座机号码
 			    function checkPhone(inputName,divValue)
 			    {
				    var str=document.getElementById(inputName).value;
				    var reg=/(^[0-9]{3,4}\-[0-9]{7,8}$)|(^[0-9]{7,8}$)|(^\([0-9]{3,4}\)[0-9]{3,8}$)|(^0{0,1}13[0-9]{9}$)/; 
					if (reg.test(str)==false)
					{
				      document.getElementById(divValue).innerHTML='Your input is not correct, such as：0771-4213550';
					  return false;
				    }
				    else
				    {
				    	document.getElementById(divValue).innerHTML='';
				    }
    			}
  			
  			
  			//检查邮箱的正确性
  			function checkMail(inputName,divValue)
  			{
  				var email = document.getElementById(inputName).value;
  				var pattern = /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(\.[a-zA-Z0-9_-])+/;
  				chkFlag = pattern.test(email);
  				if(chkFlag)
  				{
  					document.getElementById(divValue).innerHTML='';
					return true;
				}
				else
				{
					document.getElementById(divValue).innerHTML='E-mail address format is incorrect!';
					return false;
				}
  			}
  			
  			//验证手机的正确性
  			function checkMob(inputName,divValue)
  			{
  				var email = document.getElementById(inputName).value;
  				var pattern = /^(13[0-9]|15[0-9]|18[0-9])\d{8}$/;
  				chkFlag = pattern.test(email);
  				if(chkFlag)
  				{
  					document.getElementById(divValue).innerHTML='';
					return true;
				}
				else
				{
					document.getElementById(divValue).innerHTML='Phone numbers are not correct!';
					return false;
				}
  			}
 			
 			function done(){
 				
				var address = document.getElementById("meradress").value;
 				var username = document.getElementById("username").value;
 				
 				var password = document.getElementById('password').value;
 				var MD5Key = document.getElementById('MD5Key').value;
 				var mername = document.getElementById('mername').value;
 				var certificateno = document.getElementById('certificateno').value;
 				var merphone = document.getElementById('merphone').value;
 				var mermobile = document.getElementById('mermobile').value;
 				var meremail = document.getElementById('meremail').value;
 				var accountname = document.getElementById('accountname').value;
 				var bank = document.getElementById('bank').value;
 				var cardno = document.getElementById('cardno').value;
 				//var linkman = document.getElementById('linkman').value;
 				//var linkmancertificateno = document.getElementById('linkmancertificateno').value;
 				//var linkmanmobile = document.getElementById('linkmanmobile').value;
 				//var linkmanphone = document.getElementById('linkmanphone').value;
 				//var linkmanemail = document.getElementById('linkmanemail').value;
 				var website = document.getElementById('website').value;
 				var salesman = document.getElementById('salesman').value;
 				var vercode = document.getElementById('vercode').value;
 				
 				var usernameError = document.getElementById("usernameError").innerHTML;
 				var cardInfo = document.getElementById('cardInfo').innerHTML;
 				if(address==""){
 					alert('Please enter the address');
 					return false;
 				}
 				if(username==""){
 					alert('Please enter a user name.')
 					return false;
 				}
 				else if(password==""){
 					alert('Please enter your password.')
 					return false;
 				}
 				else if(MD5Key==""){
 					alert('Please enter MD5key value.')
 					return false;
 				}
 				else if(mername==""){
 					alert('Please enter your business name.')
 					return false;
 				}
 				
 				else if(meremail==""){
 					alert('Please enter your E-mail.')
 					return false;
 				}
 				else if(accountname==""){
 					alert('Please enter your branch name.')
 					return false;
 				}
 				else if(bank==""){
 					alert('Please enter your bank account.')
 					return false;
 				}
 				else if(cardno==""){
 					alert('Please enter your account number.')
 					return false;
 				}
 				
 				
 				
 				else if(website==""){
 					alert('Please enter your URL.')
 					return false;
 				}
 				else if(salesman==""){
 					alert('Please enter contact your Ecpss salesman.')
 					return false;
 				}
 				else if(vercode==""){
 					alert('Please enter the verification code.')
 					return false;
 				}else{
 					if(cardInfo!=""){
 						alert(cardInfo);
 					}else{
						document.getElementById('form1').submit();
 					}
				}

			}
	
			function refreshimg(){
	    		var random = Math.random();
	    		//IE7有缓存，为了保证验证不被缓存,加上随机内容的参数
	    		document.getElementById("authImg").src="authImg.do?r="+random;
	    	}


</script>
<link href="merchant/css/css.css" rel="stylesheet" type="text/css" />
</head>
<body>
<div class="container">
   <div class="header2">
     <div class="logo"><img src="images/logo.gif" alt=""/></div>
     <div class="slogan"><img src="merchant/images/slogan02.gif" alt="" /></div>
     <div class="welcome">
       <div class="help">
         <ul>
           <li><img src="merchant/images/icon_01.gif" alt="Help" />&nbsp;<a href="#">Help</a></li>
           <li><img src="merchant/images/icon_02.gif" alt="Customer Service" />&nbsp;<a href="#">Customer Service</a></li>

         </ul>
       </div>
       <div class="tips"><span><a href="merchanten" class="font_color_01">Home</a></span></div>
       
     </div>
     <div class="bordder"></div>
    <!--
     <div class="url"></div> -->
   </div>
    <s:form action="newMerRegEn" theme="simple" name="form1" id="form1">
   <div class="pagebody_sign">
     <div class="top_top"><img src="images/top_top.gif" alt="" /></div>
     <div class="sign_bg">
       <div class="font_sign">
         <div class="title">
           <div class="imgg"><img src="images/sigin_img.gif" alt="ecpss" /></div>
           <div class="sign_font"><span>Register</span></div>
         </div> 
         <div class="sign_info">
         
           <div class="sign_info_title"><img src="images/icon.gif" alt="" /><span><font class="font14px">Login Information</font>&nbsp;Basic Information（<span class="font_color_01">*</span>Required）</span></div>
           <div class="sign_info_info">
             <ul class="font_ul">
               <li class="lili_01">Nickname<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield id="username" name="merchant.username" onkeydown="onlyNumAndLetters()" cssClass="input_texxt_01" onblur="checkMerchantName()"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="usernameError"></span>only alphabet or number be allowed</li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">Password<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:password id="password" cssClass="input_texxt_01" name="merchant.password" onblur="check('password','pwInfo','Password Required!')"></s:password></li>
               <li class="lili_03"><span style="color:red" id="pwInfo"><s:fielderror> <s:param>password</s:param> </s:fielderror></span>Password consists of 6-20 characters</li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">Confirm Password<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:password name="password1" cssClass="input_texxt_01" id="password1" onblur="checkTwo()"></s:password></li>
               <li class="lili_03"><span style="color: red" id="pwInfo1">
	    	<s:fielderror>
				<s:param>password1</s:param>
			</s:fielderror>
		</span>
    	Please double input password</li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">MD5key<span class="font_color_01">*</span></li>
               <li class="lili_02"><input type="text" id="MD5Key" class="input_texxt_01" name="merchant.md5key" maxlength="8" value="<%=generateRandStr%>" /></li>
               <li class="lili_03"><span style="color: red" id="md5"><s:fielderror><s:param>MD5Key</s:param></s:fielderror></span>
    	MD5key, Safely encrypt</li>
             </ul> 
             
             
           </div> 
         </div>
         
         <div class="sign_info">
           <div class="sign_info_title"><img src="images/icon.gif" alt="" /><span><font class="font14px">Merchant Information</span></div>
           <div class="sign_info_info">
             <ul class="font_ul">
               <li class="lili_01">Merchant Name <span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.mername" cssClass="input_texxt_01"  id="mername" onblur="check('mername','merNameInfo','Business name required!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="merNameInfo">
	    	<s:fielderror>
				<s:param>mername</s:param>
			</s:fielderror>
		</span></li>
             </ul> 
             
             <ul class="font_ul">
               <li class="lili_01">Passport NO <span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.certificateno" cssClass="input_texxt_01"  id="certificateno" ></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="cardInfo">
    		<s:fielderror>
				<s:param>certificateno</s:param>
			</s:fielderror>
		</span>
		please input 15 or 18 identification numbers</li>
             </ul> 
             
             <ul class="font_ul">
               <li class="lili_01">Address <span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.meradress" cssClass="input_texxt_01"  id="meradress" onblur="address('meradress','addressInfo')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="addressInfo"></span></li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">Contact telephone NO <span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.merphone" cssClass="input_texxt_01"  id="merphone" ></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="phoneInfo"></span>fixed phone number should enclose the code</li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">Fax</li>
               <li class="lili_02"><s:textfield name="merchant.merfax" cssClass="input_texxt_01"  id="merfax"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="faxInfo"></span></li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">mobilephone<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.mermobile" cssClass="input_texxt_01"  id="mermobile" ></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="mobileInfos">
    		<s:fielderror>
				<s:param>mermobile</s:param>
			</s:fielderror>
		</span>
		please input mobilephone NO. beginning with13、15、18</li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">E-mail<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.meremail" cssClass="input_texxt_01"  id="meremail" onblur="checkMail('meremail','mailInfo')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="mailInfo"><s:fielderror>
									<s:param>meremail</s:param>
								</s:fielderror>
							</span>please input correct E-mail address</li>
             </ul>
              <ul class="font_ul">
               <li class="lili_01">Merchant QQ</li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.merqq"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="qqinfo"></span></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">Merchant MSN</li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.mermsn"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="mermsnInfo"></span></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">Account Type</li>
               <li class="lili_02_2"><s:select cssClass="input_texxt_01"  name="merchant.mertype" list="#@java.util.TreeMap@{'1':'Company','2':'Personal'}"></s:select></li>
               <li class="lili_03"></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">Account Nam<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.accountname" id="accountname" onblur="check('accountname','accountnameInfo','开户名必填!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="accountnameInfo"><s:fielderror>
														<s:param>accountname</s:param>
													</s:fielderror>
												</span>Must same as the account-opening name</li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">Account-opening bank<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.bank" id="bank" onblur="check('bank','bankInfo','Bank Required!!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="bankInfo"><s:fielderror>
										<s:param>bank</s:param>
									</s:fielderror>
								</span>eg. ICBC</li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">Account<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.cardno" id="cardno" onblur="check('cardno','cardnoInfo','Bank Required!!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="cardnoInfo"> <s:fielderror>
												<s:param>cardno</s:param>
											</s:fielderror>
										</span>the bank card NO</li>
             </ul> 
           </div> 
         </div> 
         
         <div class="sign_info">
         
           <div class="sign_info_title"><img src="images/icon.gif" alt="" /><span><font class="font14px">Merchant business information</font></div>
           <div class="sign_info_info">
             <ul class="font_ul">
               <li class="lili_01">Merchant Type</li>
               <li class="lili_02_2"><s:select cssClass="input_texxt_01"  name="merchant.businesstype" list="#@java.util.TreeMap@{'1':'E-commerce','2':'Electronics Technology','3':'Clothing','4':'Trading','5':'Energy','6':'Other'}"></s:select></li>
               <li class="lili_03"></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">Website<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.website" cssClass="input_texxt_01"  id="website" onblur="check('website','websiteInfo','Required business web site!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="websiteInfo"><s:fielderror>
																				<s:param>website</s:param>
																			</s:fielderror>
																		</span></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">Trade Time</li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.businessyears"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="businessyearsInfo"></span></li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">Former collect payment method</li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.gatheringway"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="gatheringwayInfo"></span></li>
             </ul> 
            
           </div> 
         </div>
         
         <div class="sign_info">
         
           <div class="sign_info_title"><img src="images/icon.gif" alt="" /><span><font class="font14px">ECPSS sales' information</span></div>
           <div class="sign_info_info">
             <ul class="font_ul">
               <li class="lili_01">Sales<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.salesman" id="salesman" onblur="check('salesman','salesmanInfo','ECPSS Salesman required!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="salesmanInfo"><s:fielderror>
																<s:param>salesman</s:param>
															</s:fielderror>
														</span>sales who open the account for you</li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">Auth-Code<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="vercode" size="10" id="vercode" onblur="check('vercode','vercodeInfo','Please fill in the verification code!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="vercodeInfo"><s:fielderror>
														<s:param>vercode</s:param>
													</s:fielderror>
												</span>Type the code shown below</li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">&nbsp;</li>
               <li class="lili_02"><a href="javascript:refreshimg();"> <img border=0 src="authImg.do" id="authImg" alt="Click Refresh" /> Not clear,click to refresh</a></li>
               <li class="lili_03"><span style="color: red" id="vercodeInfo"><s:fielderror>
														<s:param>vercode</s:param>
													</s:fielderror>
												</span></li>
             </ul> 
           </div> 
         </div>

         <div class="sign_info">
         
           <div class="sign_info_title"><img src="images/icon.gif" alt="" /><span><font class="font14px">Register clause</span></div>
           <div class="sign_info_info">
             <ul class="centertt">
               <li><TEXTAREA cols="60" style="font-size:12px; width:550px; line-height:25px" rows="5" name="registerItem" readonly>ECPSS Online Payment Service
               transactions which may be considered unlawful in any jurisdiction in which you conduct business and you comply with all
               laws, regulations and requirements applicable to your business and to the Payments;
               (c) the Merchant will indemnify and keep harmless ECPSS PAYMENT and its parents, subsidiaries, affiliates, officers,
               directors, representatives, agents, and employees from and against any and all claims, losses, liabilities, costs, and
               other expenses incurred as a result of or arising directly or indirectly out of or in connection with:
               (i) any misrepresentation, breach of warranty or non-fulfillment of any undertaking on your part under this Agreement;
               (ii) any claims, demands, awards, judgements, actions and proceedings by whomsoever made, arising out of or in any way
               connected with your performance under this Agreement;
               (iii) any claims, demands, awards, judgements, actions and proceedings made by third party included, but not limited to,
               penalties imposed by banks for any reason, arising out of or in any way connected with the transactions between you and
               the User.

               5.2 ECPSS PAYMENT represents and warrants that during the term of this Agreement:
               (a) it is duly incorporated, organized and validly existing and in good standing under the laws of the Republic of China
               , has all requisite powers, licenses and permits and has undertaken all actions and has fulfilled all conditions to
               enter into, to perform under and to comply with its obligations under this Agreement;
               (b) we have all necessary rights and authority to execute and deliver this Agreement and perform its obligations
               hereunder and nothing contained in this Agreement or in the performance of this Agreement will place ECPSS PAYMENT in
               breach of any other contract or obligation; and
               (c) subject the other provisions of this Agreement, we shall remit Payments to you in a timely manner and in accordance
               with this Agreement.
               5.3 Except as otherwise provided herein, ECPSS PAYMENT expressly disclaims, all other representation, warranties or
               conditions, whether express, implied, statutory or otherwise, including, without limitation, the implied warranties of
               merchantability, title fitness for a particular purpose and non-infringement of third party rights.

               6. Confidentiality
               6.1 ECPSS PAYMENT will own and retain all of their respective rights, titles and interests in and to all intellectual
               property embodied in or associated with the design and delivery of the Services, including, but not limited to, content,
               such as software, graphics, start-up information and materials, designs, methods, architecture, materials, publications,
               business plans and other tangible intellectual property-based assets of any kind whether in machine readable, printed or
               other form and including, without limitation, all revisions, enhancements, technical know-how, patents, copyrights,
               trademarks, and trade secrets.
               6.2 Except as expressly stated in this Agreement, the parties will have no rights of any kind in or to any of each
               other’s intellectual property. There are no implied licenses under this agreement, and any rights not expressly granted
               under this Agreement are reserved by the respective party.
               6.3 The Merchant will not, without the prior written consent of the cardholder, use or disclose information on the
               cardholder or his/her transactions howsoever obtained and in whatsoever form the information shall take, to any third
               party (other than the Merchant’s agents for the sole purpose of assisting the Merchant to complete or enforce the
               transactions and the Merchant’s insurers and professional advisers) unless such disclosure is compelled by law or
               judicial order and the Merchant shall indemnify ECPSS PAYMENT accordingly in the event of any claim (direct or
               indirect) arising therefrom.
               6.4 ECPSS PAYMENT shall be entitled to disclose information about the Merchant and this Agreement to any agents
               (including without limitation any collection agencies), contractors or advisers who provide a service to ECPSS PAYMENT
               in relation to the performance or enforcement of this Agreement.

               7. Liability

               7.1 In no event will ECPSS PAYMENT be liable in contract or in tort for any indirect loss or damage caused by our
               failure to fulfill our responsibilities or for any consequential damages, including, but not limited to, loss of profits
               or anticipated savings or incidental damages, even if we had been advised of the possibility of such damages.
               7.2 ECPSS PAYMENT shall not be liable for any damage and/or loss caused by or related to the performance or
               non-performance of the Services, unless such damage and/or loss is caused by intent or gross negligence on the part of
               ECPSS PAYMENT.
               7.4 Any right of compensation pursuant to this Agreement shall be invalidated if (legal) proceedings/action to claim
               compensation have not been instituted within one (1) year of the damage and/or loss having arisen.
               7.5 The Merchant shall not hold ECPSS PAYMENT liable or responsible for any action, claim, cost, expense, damage and
               loss, including consequential loss or damage or loss of profit, which the Merchant may suffer or incur as a result of a
               breakdown in the Systems or when the Systems are not available for any reason whatsoever.

               8. Restrictions

               8.1 The Merchant hereby agrees with ECPSS PAYMENT that it shall not:
               (a) impose additional charges for transactions by customers where payment is to be effected through the System;
               (b) refuse transactions by User who wishes to effect payment through the System regardless of the value of the
               transaction, except where in the reasonable opinion of the Merchant the User is suspected not to be the authorized user
               or legitimate owner of the card presented (collectively “the Card User”); and/or
               (c) favour any particular card when accepting transaction for which payment is to be effected through the System
               notwithstanding any agreement between the Merchant and any card issuing company.

               9. Undertakings of the Merchant
               9.1 The Merchant agrees and undertakes throughout the term of this Agreement that it shall:-
               (a) at its own expense and before the time agreed for installation or activation of the Services, prepare and provide
               the necessary, compatible operational equipment, software and connection specified by ECPSS PAYMENT;
               (b) not alter, copy, modify or tamper with any software provided by ECPSS PAYMENT;
               (c) install such measures as may be necessary to protect the security and integrity of related hardware or software of
               the Systems;
               (d) where required, comply with all security or encryption standards, rules procedures imposed by ECPSS PAYMENT;
               (e) prior to providing the Services, open and maintain at all times during the term of this Agreement, an account in its
               name with one of the Participating Banks which shall be designated for purposes of clearing and settling Transactions
               handled by the Merchant; and
               (f) inform ECPSS PAYMENT of any change in the particulars of its designated account.

               10. Undertakings of ECPSS PAYMENT

               10.1 ECPSS PAYMENT agrees and undertakes that it shall use its commercially reasonable endeavours to facilitate the
               Transactions and the functions required to enable the Merchant to provide the Services.

               11. Suspension or Termination of the Provision of Services

               11.1 It is your obligation to fulfill your obligations under this Agreement and to inform us of any material change to
               your business or payment profile. ECPSS PAYMENT at its sole discretion will have the right to suspend or terminate the
               provision of Services to the Merchant should one of the following occur.
               a. a material change in the number, value, type or profile of payments of which we were not informed in advance
               b. a sustained drop in the number, value, type or profile of payments of which we were not informed in advance
               c. we suspect or have evidence that fraud is or may be occurring
               d. the integrity or reputation of ECPSS PAYMENT is brought or threatened to be brought into disrepute by the Merchant
               e. you stop trading,
               f. you sell your business or there is a change in control of your business without advising us,
               g. you change the name or nature of your business without advising us,
               h. the legal status of your business changes without advising us,
               i. a trustee or receiver is appointed for any or all of your property, you become insolvent or unable to pay debts as
               they mature, or ceases to so pay, or makes an assignment for the benefit of creditors, bankruptcy or insolvency
               proceedings under bankruptcy or insolvency code or similar law, whether voluntary or involuntary are properly commenced
               by or against you, your company is dissolved or liquidated;

               12 Term, Termination and Ongoing Provisions

               12.1 This Agreement will be effective as of the date of signing by the parties hereto and will remain in effect for one
               (1) year. Unless written notice is provided to the contrary by either party one (1) month prior to expiration of the
               initial term of this Agreement or any extension term, this Agreement shall automatically renew for successive one (1)
               year term.
               12.2 The termination of this Agreement shall not affect any provision of this Agreement which by its wording or nature
               is intended to remain effective and to continue to operate in the event of termination of this Agreement, and shall not
               prejudice or affect the rights of either Party against the other in respect of any breach of the terms and conditions of
               this Agreement.

               13 Assignment, Variation of Agreement

               13.1 The Merchant shall not, without the prior written consent of ECPSS PAYMENT, assign or transfer any of its rights
               or obligations under this Agreement. This Agreement may be varied only by the agreement of both parties in writing
               provided that ECPSS PAYMENT shall be entitled at any time to vary or supplement such terms of this Agreement which
               relate to matters purely of an operational nature by giving not less than 30 days’ written notice to the Merchant.

               14. Governing Law

               14.1 This Agreement and any disputes shall in all respects be exclusively governed by and interpreted in accordance with
               the laws of the Republic of China and the parties hereto agree to submit to the non-exclusive jurisdiction of the courts
               of Republic of China.

               15 Assignment

               15.1 None of the Parties shall assign or transfer this Agreement or any or all of their rights and/or obligations under
               the Agreement nor any part of it, nor any benefit nor interest in or under it, to any third party without the prior
               written consent of the other Party which shall not be unreasonably withheld; provided, however that we may assign this
               Agreement without the consent or approval of you to our parent or subsidiary or associated companies, in connection with
               a merger, reorganization, recapitalisation or sale of all of or substantially all of our stock, business or assets. Any
               attempt to assign this Agreement other than as permitted herein shall be null and void. Subject to the foregoing, this
               Agreement will be fully binding upon, inure to the benefit of and be enforceable by the parties hereto and there
               respective successors and assigns.


               17 Force Majeure
               17.1 No failure or omission by any party to carry out its obligations or observe any of the stipulations or conditions
               of this Agreement, shall give rise to any claims against the party in question or be deemed a breach of this Agreement,
               if such failure or omission arises from a cause of force majeure, such as acts of God, war or warlike hostilities, civil
               commotion, riots, blockades, embargos, sabotage, strikes, lockout, shortage of labour, delay in deliveries of whatsoever
               from sub-contractors or machine failure caused by force majeure, or any other event beyond the control of the party in question.

               18 Notices and Consents

               18.1 Any notice to be given by either of the parties hereto to the other in connection with this Agreement shall be in
               writing and shall be delivered to its address stated in this Agreement or to such other address as either party may
               notify to the other for such purpose.
               18.2 All communications sent by post to the last address of the Merchant registered with ECPSS PAYMENT
               will be deemed to have been duly delivered to the Merchant regardless of whether any such communications have been returned through post.



 							</TEXTAREA></li>
             </ul>
           </div> 
         </div>
         <div class="sign_info">
         
           <div class="sign_info_title">&nbsp;</div>
           <div class="sign_info_info2">
             <ul class="centertt2">
               <li><input name=aa type="button" value="Submit" class="button_011" onclick="done()" /></li>
             </ul>
           </div> 
         </div>
         
<table class="input_box" width="95%" border="0" cellspacing="0" cellpadding="0">
	<tr>
	    <td colspan="3" width="10%" align="center">
	    	
	    </td>

  	</tr>
</table>
       </div>
       </s:form>
     </div>
     <div class="top_top"><img src="images/bottom.gif" alt="" /></div>
   </div>
   <div class="footer">
     <div><span class="font_color_01"><img src="merchant/images/icon_04.gif" alt="" />&nbsp;&nbsp;I have comments or suggestions, say a few words with ECPSS</span></div>
     <div class="links">
       <ul>
         <li><a href="#">About</a> | <a href="#">Online Payment</a> | <a href="#">Disclaimer</a> | <a href="#">Privacy Policy</a> | <a href="#">Contact us</a> </li>

         <li>Copyright(c)2010. All rights reserved. "Epcss"。International Payments - Secure Online Payments - Send Payments Online - Internet Payment </li>
       </ul>
     </div>
   </div>
</div>
<script language="javaScript" type="text/javascript" src="script/zc_foot.js"></script>
</div></div>
</body>
</html>
