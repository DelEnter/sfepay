 <%@ page contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>xingbill - 新商户注册</title>
	<%-- <script type="text/javascript" src="dwr/interface/carderCheck.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script src="script/public.js" type="text/javascript"></script> --%>
	<script type="text/javascript" src="js/jquery-1.9.1.min.js"></script>
	<script type="text/javascript" src="js/ajaxfileupload.js"></script>
	<!-- <link rel="stylesheet" type="text/css" href="css/merRegister.css" /> -->
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
<!-- <link href="css/searchbar_w880.css" rel="stylesheet" type="text/css" /> -->
<script type="text/JavaScript">
	 $(function(){  
    //点击打开文件选择器  	
    $("#uploadS").on('click', function() {  
        $('#fileToUploadS').click();  
    });  
    //选择文件之后执行上传  
    $('#fileToUploadS').change((
    	upload2 = function() {
    	var fileName =  $('#fileToUploadS').val();
        $.ajaxFileUpload({  
            url:'uploadcard',  
            secureuri:false,  
            fileElementId:'fileToUploadS',//file标签的id  
            dataType: 'json',//返回数据的类型  
            fileName: fileName,//文件名  
            data:{type:'idCardZ'},//一同上传的数据 
            success: function (data) {
            	$('#fileToUploadS').change(upload2);
            	if (data == 'error')
            		return;
                //把图片替换
                var basePath = '<%=path %>';
                $("#uploadS").attr("src", basePath+"/upload/authentication_temp/"+data);
                 
            },  
            error: function (data, status, e) {  
                alert(e);  
            }
        });  
  	 }
    )); 
	}); 

</script>
<script language="JavaScript">
			function checkMerchantName(){
				
				var username = document.getElementById("username").value;
				carderCheck.checkMerchantName(username, callUsername); 
			}
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
					document.getElementById('addressInfo').innerHTML='地址必填!';
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
		 			document.getElementById('pwInfo1').innerHTML='确认密码必填!';
		 			return;
		 		}
		 		else
		 		{
		 			if(pw1!=pw2)
		 			{
		 				document.getElementById('pwInfo1').innerHTML='两次密码不一致!';
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
					document.getElementById(divName).innerHTML='身份证号码不正确!！';
					return false;
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
					document.getElementById(divValue).innerHTML='邮箱地址的格式不正确！';
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
					document.getElementById(divValue).innerHTML='手机号码不正确！';
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
 				var mermobile = document.getElementById('mermobile').value;
 				var meremail = document.getElementById('meremail').value;
 				var accountname = document.getElementById('accountname').value;
 				var bank = document.getElementById('bank').value;
 				var cardno = document.getElementById('cardno').value;
 				var uploadFileName = document.getElementById('uploadS').src;
 				$("#uploadFileNameno").attr("value",uploadFileName);
 				
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
 					alert('请输入地址');
 					return false;
 				}else if(username==""){
 					alert('请输入用户名.')
 					return false;
 				}
 				else if(password==""){
 					alert('请输入密码.')
 					return false;
 				}
 				else if(MD5Key==""){
 					alert('请输入MD5key值.')
 					return false;
 				}
 				else if(mername==""){
 					alert('请输入您的商户名.')
 					return false;
 				}
 				else if(certificateno==""){
 					alert('请输入您的身份证卡号.')
 					return false;
 				}
 				else if(uploadS==""){
 					alert('请上传您的手持身份证照.')
 					return false;
 				}
 				else if(mermobile==""){
 					alert('请输入您的手机号码.')
 					return false;
 				}
 				else if(meremail==""){
 					alert('请输入您的E-mail.')
 					return false;
 				}
 				else if(accountname==""){
 					alert('请输入您的开户名.')
 					return false;
 				}
 				else if(bank==""){
 					alert('请输入您的开户银行.')
 					return false;
 				}
 				else if(cardno==""){
 					alert('请输入您的帐号.')
 					return false;
 				}
// 				else if(linkman==""){
// 					alert('请输入联系人信息.')
// 					return false;
// 				}
// 				else if(linkmancertificateno==""){
// 					alert('请输入联系人有效证件号码.')
// 					return false;
// 				}
// 				else if(linkmanmobile==""){
// 					alert('请输入联系人联系方式.')
// 					return false;
// 				}
// 				else if(linkmanemail==""){
// 					alert('请输入联系人E-mail.')
// 					return false;
// 				}
 				else if(website==""){
 					alert('请输入您的网址.')
 					return false;
 				}
 				else if(salesman==""){
 					alert('请输入联系您的xingbill业务员.')
 					return false;
 				}
 				else if(vercode==""){
 					alert('请输入验证码.')
 					return false;
 				}else{
// 					if(cardInfo!=""){
// 						alert(cardInfo);
// 					}else{
// 					}
 					
 					document.getElementById('form1').submit();
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
     <div class="logo"><img src="images/logo-youhui-gray.png" alt=""/></div>
     <div class="welcome">
       <div class="help">
         <ul>
           <li><img src="merchant/images/icon_01.gif" alt="帮助中心" />&nbsp;<a href="#">帮助中心</a></li>
           <li><img src="merchant/images/icon_02.gif" alt="在线客服" />&nbsp;<a href="#">在线客服</a></li>

         </ul>
       </div>
       <div class="tips"><span><a href="index.jsp" class="font_color_01">返回首页</a></span></div>
       
     </div>
     <div class="bordder"></div>
    <!--
     <div class="url"></div> -->
     <div>
     	<span style='color:red'><s:property value="messageAction"/></span>
     </div>
   </div>
   <s:form action="newMerReg" theme="simple" name="form1" id="form1">
   <div class="pagebody_sign">
     <div class="top_top"><img src="images/top11.gif" alt="" /></div>
     <div class="sign_bg">
       <div class="font_sign">
         <div class="title">
           <div class="sign_font"><span>注册</span></div>
         </div> 
         <div class="sign_info">
         
           <div class="sign_info_title"><img src="images/icon.gif" alt="" /><span><font class="font14px">登陆信息</font>&nbsp;基本信息（<span class="font_color_01">*</span>为必填）</span></div>
           <div class="sign_info_info">
             <ul class="font_ul">
               <li class="lili_01">登陆名<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield id="username" name="merchant.username" onkeydown="onlyNumAndLetters()" cssClass="input_texxt_01"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="usernameError"></span>只能使用字母、数字</li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">密码<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:password id="password" cssClass="input_texxt_01" name="merchant.password" onblur="check('password','pwInfo','密码必填!')"></s:password></li>
               <li class="lili_03"><span style="color:red" id="pwInfo"><s:fielderror> <s:param>password</s:param> </s:fielderror></span></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">确认密码<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:password name="password1" cssClass="input_texxt_01" id="password1" onblur="checkTwo()"></s:password></li>
               <li class="lili_03"><span style="color: red" id="pwInfo1">
	    	<s:fielderror>
				<s:param>password1</s:param>
			</s:fielderror>
		</span></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">MD5密钥<span class="font_color_01">*</span></li>
               <li class="lili_02"><input type="text" id="MD5Key" class="input_texxt_01" name="merchant.md5key" maxlength="8" value="<%=generateRandStr%>" readonly /></li>
               <li class="lili_03"><span style="color: red" id="md5"><s:fielderror><s:param>MD5Key</s:param></s:fielderror></span></li>
             </ul> 
             
             
           </div> 
         </div>
         
         <div class="sign_info">
           <div class="sign_info_info">
             <ul class="font_ul">
               <li class="lili_01">商户名称 <span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.mername" cssClass="input_texxt_01"  id="mername" onblur="check('mername','merNameInfo','商户名必填!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="merNameInfo">
	    	<s:fielderror>
				<s:param>mername</s:param>
			</s:fielderror>
		</span></li>
             </ul> 
             
             <ul class="font_ul">
               <li class="lili_01">身份证号码 <span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.certificateno" cssClass="input_texxt_01"  id="certificateno" onblur="checkCard('certificateno','cardInfo')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="cardInfo">
    		<s:fielderror>
				<s:param>certificateno</s:param>
			</s:fielderror>
		</span>
		请输入15位或18位的身份证号</li>
             </ul> 
             <input type="hidden" name ="uploadFileNameno" id="uploadFileNameno" value=""/>
             <ul class="font_ul"  style="height: 150px">
             	<li class="lili_01" style="height: 150px">手持身份证照 <span class="font_color_01">*</span></li>
             	<li class="lili_02" style="width: 200px; height: 150px"><div><img id="uploadS" name="uploadS"  style="width: 192px; height: 140px" alt="" src="<%=path %>/upload/upload.png"> </div>
				<!-- 隐藏file标签 -->  
				<input id="fileToUploadS" style="display: none" type="file" name="file">
				</li>
             </ul>
             
             <ul class="font_ul">
               <li class="lili_01">地址 <span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.meradress" cssClass="input_texxt_01"  id="meradress" onblur="address('meradress','addressInfo')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="addressInfo"></span></li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">传真</li>
               <li class="lili_02"><s:textfield name="merchant.merfax" cssClass="input_texxt_01"  id="merfax"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="faxInfo"></span></li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">联系手机<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.mermobile" cssClass="input_texxt_01"  id="mermobile" onblur="checkMob('mermobile','mobileInfos')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="mobileInfos">
    		<s:fielderror>
				<s:param>mermobile</s:param>
			</s:fielderror>
		</span>
		请输入13..,15..,18..开头的11位手机号</li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">邮箱<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.meremail" cssClass="input_texxt_01"  id="meremail" onblur="checkMail('meremail','mailInfo')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="mailInfo"><s:fielderror>
									<s:param>meremail</s:param>
								</s:fielderror>
							</span>请输入正确的邮箱地址</li>
             </ul>
              <ul class="font_ul">
               <li class="lili_01">商户QQ</li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.merqq"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="qqinfo"></span></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">商户MSN</li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.mermsn"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="mermsnInfo"></span></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">账户类型</li>
               <li class="lili_02_2"><s:select cssClass="input_texxt_01"  name="merchant.mertype" list="#@java.util.TreeMap@{'1':'公司','2':'个人'}"></s:select></li>
               <li class="lili_03"></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">开户名<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.accountname" id="accountname" onblur="check('accountname','accountnameInfo','开户名必填!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="accountnameInfo"><s:fielderror>
														<s:param>accountname</s:param>
													</s:fielderror>
												</span>必须和实际的开户姓名相同</li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">开户银行<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.bank" id="bank" onblur="check('bank','bankInfo','开户银行必填!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="bankInfo"><s:fielderror>
										<s:param>bank</s:param>
									</s:fielderror>
								</span>例如中国工银行</li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">开户账号<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.cardno" id="cardno" onblur="check('cardno','cardnoInfo','开户账号必填!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="cardnoInfo"> <s:fielderror>
												<s:param>cardno</s:param>
											</s:fielderror>
										</span>商户在银行开户的卡号</li>
             </ul> 
           </div> 
         </div> 
         
         <div class="sign_info">
         
           <div class="sign_info_info">
             <ul class="font_ul">
               <li class="lili_01">账户类型</li>
               <li class="lili_02_2"><s:select cssClass="input_texxt_01"  name="merchant.businesstype" list="#@java.util.TreeMap@{'1':'电子商务','2':'电子科技','3':'服装','4':'贸易','5':'能源','6':'其他'}"></s:select></li>
               <li class="lili_03"></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">网址<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.website" cssClass="input_texxt_01"  id="website" onblur="check('website','websiteInfo','商户网址必填!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="websiteInfo"><s:fielderror>
																				<s:param>website</s:param>
																			</s:fielderror>
																		</span></li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">行业年限</li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.businessyears"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="businessyearsInfo"></span></li>
             </ul> 
              <ul class="font_ul">
               <li class="lili_01">以前的收款方式</li>
               <li class="lili_02"><s:textfield cssClass="input_texxt_01"  name="merchant.gatheringway"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="gatheringwayInfo"></span></li>
             </ul> 
            
           </div> 
         </div>
         
         <div class="sign_info">
         
           <div class="sign_info_title"><img src="images/icon.gif" alt="" /><span><font class="font14px">xingbill销售代表</span></div>
           <div class="sign_info_info">
             <ul class="font_ul">
               <li class="lili_01">业务员<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="merchant.salesman" id="salesman" onblur="check('salesman','salesmanInfo','xingbill业务员必填!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="salesmanInfo"><s:fielderror>
																<s:param>salesman</s:param>
															</s:fielderror>
														</span>负责为你开通网上支付的业务</li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">验证码<span class="font_color_01">*</span></li>
               <li class="lili_02"><s:textfield name="vercode" size="10" id="vercode" onblur="check('vercode','vercodeInfo','请填写验证码!')"></s:textfield></li>
               <li class="lili_03"><span style="color: red" id="vercodeInfo"><s:fielderror>
														<s:param>vercode</s:param>
													</s:fielderror>
												</span>请填写下面图片里的字符</li>
             </ul> 
             <ul class="font_ul">
               <li class="lili_01">&nbsp;</li>
               <li class="lili_02"><a href="javascript:refreshimg();"> <img border=0 src="authImg.do" id="authImg" alt="点击刷新" /> 看不清!点击刷新</a></li>
               <li class="lili_03"><span style="color: red" id="vercodeInfo"><s:fielderror>
														<s:param>vercode</s:param>
													</s:fielderror>
												</span></li>
             </ul> 
           </div> 
         </div>

         <div class="sign_info">
         
           <div class="sign_info_title"><img src="images/icon.gif" alt="" /><span><font class="font14px">注册条款</span></div>
           <div class="sign_info_info">
             <ul class="centertt">
               <li><TEXTAREA cols="60" style="font-size:12px; width:550px; line-height:25px" rows="5" name="registerItem" readonly>xingbill在线支付服务协议
欢迎您阅读“xingbill”的“在线支付服务协议”（以下称为“本协议）。本协议
阐述之条款和条件适用于您（在本协议中可以称为您，或者商户）使用上海星
付金融服务有限公司（以下称为“本公司”）所提供的在线支付服务。
一、关于“在线支付服务”及本公司产品名称的理解与同意意
  1、“在线支付服务”是指本公司为您从事电子商务业务提供的代收代付货款
的中介服务。为了维护您的权益，请您在正式使用该项服务之前详细阅读本协议

。
  2、“国际卡支付”是本公司推出的、为从事国际电子商务、以国际卡（visa

、master、AE、JCB、paypal等）以及其他国家借记卡为支付工具的商户提供的

代收款服务的产品。
二、接受条款
  1、通过网站www.xingbill.com首页注册成为商户的个人或公司即表示您接受本协

议之一切条款，并接受本协议之条款和条件的约束。
  2、本公司有权单方面随时修改或变更本协议之条款和条件，并随时通过网站

对外公布最新变更之事项，不作个别通知。在变更事项公布后，如您继续使用本

公司服务的即表示您接受新公布之规定。
三、谁可以使用
  1、本协议提供之服务仅供根据相关法律可以签署具有法律约束力的、并具有

相应民事行为能力的自然人或公司使用。因此，作为个人，您必须年满十八周岁

或以上；作为公司，您必须具备相应的工商注册证件。如不符合本项条件，请勿

使用本协议之服务。
  2、本公司有权自行决定拒绝向任何人提供本协议之服务。该服务也不会提供

给被暂时冻结或被永久终止资格的注册商户。
四、您的资料
  1、您必须保证您在本网站上注册登记资料是真实有效的，并承诺承担因资料

不真实而发生的全部责任；如因此给本公司造成损失（包括直接损失和间接损失

）的，还承担赔偿责任。
  2、如果您在注册时候登记的资料以及直接向本公司提供的资料有变更的需要

，您必须向本公司提出书面申请，经本公司书面同意，方可进行变更。
五、您将得到的服务
  1、安全加密和身份认证：为您使用本服务提供高质量的网络传输加密通道；
  2、提供网上安全支付服务：您的商务网站进行交易所需的在线支付服务，通

过本公司的在线支付网关与本公司所连接的金融机构交换信息，并将金融机构的

确认信息反馈给您和持卡人；
  3、提供安全技术支持：为商户提供定单信息传输的接口规范，配置安全传输

协议，设定后台管理权限，为消费者的支付等重要信息提供高质量的网络传输加

密通道；
  4、准确、实时地将您发来的网上支付的交易请求传递给相关金融机构，并将

金融对该支付请求的应答及时、安全、保密地传递给乙方。本公司对其支付服务

平台的支付接入系统中信息传递过程的安全、保密、实时性负责，承担且仅承担

因此项原因造成的直接经济损失。
  5、为商户提供其在线支付相关信息的查询服务；
  6、在正常工作时间指定工程师为商户提供服务，保证您反映问题后2个小时内

及时响应；
  7、按照《商户申请表》的上的约定为商户划转交易款项；
  8、及时协助乙方处理消费者的投诉和拒付事项；
  9、本公司负责处理涉及在线支付平台运转问题出现的投诉和纠纷；
  10、因本公司系统升级而暂时终止服务的，本公司将提前7天通知您。通知的

方式为网上公告。
六、您应承担的义务
  1、您在网上发布的信息和从事的商务活动，必需严格遵守国家的法律法规及

政府有关部门的管理规定并独自承担与此相应的责任；如有散布淫秽、色情等信

息内容及其他违反《互联网信息服务管理办法》第十五条规定的，本公司有权冻

结您的经营款项；
  2、如实提供基本业务情况说明及相关法律证明；承担由于您所提供的资质材

料不准确、不真实等情况而导致的经济和法律方面的责任。本公司对此保留终止

为您提供支付服务的权力。本公司有权随时查验您提供的相关资料的真实性，商

户有义务配合本公司工作人员的查验资料工作；
  3、您必须在您网站页面上如实描述本公司的支付业务，商户不得采用技术手

段或其它非法手段截获持卡人的卡信息、代持卡人提交订单，您必须在您的网站

上引导持卡人到本公司的支付平台亲自提交订单，否则本公司保留终止为您提供

支付服务的权力；
  4、您必须及时处理持卡人的消费投诉，并负责处理因货物、价格以及售后服

务等方面原因引起的与持卡人的纠纷。本公司可提供相关协助。由于您的原因引

起的一切客户投诉或法律上的责任，均由您自己负责或追究有关方面的责任，与

本公司无关。同时，本公司有权先冻结您与持卡人投诉金额相等的交易资金，以

便有权机关顺利处理相关纠纷；
  5、您终止服务或业务发生变更，应提前一个月书面通知甲方，否则造成损失

由您负责；
  6、您应妥善保管自己的商户号及商户密码，因您自己的商户号和密码保管及

使用不当造成的经济损失，责任由您自己承担；
  7、在交易中发生持卡人拒付的交易，您必须在本公司的通知邮件发出之日起

三个自然日内将拒付交易款项和处理费用及罚款全额支付到本公司的帐户；同时

，本公司也有权直接从您的后续交易中直接扣取；如在3个自然日内您没有或者

后续交易不足满足拒付交易涉及到的金额，自第4日起，您必须承担每日千分之

五的利息费用。
  8、您从事的商业活动必须严格遵守国家的相关法律法规；如果您的客户是外

国持卡人，您还必须遵守客户所在国有关法律法规。
七、费用及结算
  1、本公司收取的各项费用均在您填写的《商户申请表》上确定。
  2、本公司以网上电子划款转帐的方式，将相应的交易款扣除相应手续费后，

划转到您提供的或者指定的帐号中。若商户因各种原因变更帐号时，需及时向本

公司提供有权人签署的真实有效的变更通知。因商户帐号变更但未及时通知本公

司导致增加的额外成本或损失，由商户承担。因商户变更帐号或指定收款单位而

造成的任何损失均由商户自己承担。
八、本公司的权力
   如果您有下列情形之一的，本公司有权立即终止为您提供服务，并有权冻结

您的交易款项；同时，有权追偿您因这些情形而给本公司造成的损失：
     1）、与客户串通诈骗资金；
     2）、无理拒绝为使用本公司在线支付平台支付成功的客户提供对应服务的

；
     3）、您的交易中存在过多的交易纠纷或过大的交易风险、且经双方友好协

商无法解决，该项评估依据甲方或收单银行的调查评估结论为准；
     4）、您的网站上没有提供真实有效的客服联系方式（即您的客户无法通过

您网站提供的联系方式与您取得联系、无法及时得到您的反馈）；您提供的相关

资料不符合实际情况的；
     5）、金融机构或者支付通道提供商出具的要求中止商户交易的书面通知；

或者金融机构或者支付通道提供商停止提供服务的；
     6）、违反《互联网信息服务管理办法》第十五条规定的。
     7）、盗用他人名义注册使用本公司服务的。
九、责任范围及限制
   1、本公司仅对本协议中所列明的义务承担责任，、；
   2、您经由本公司网站所下载或取得的任何资料，应该由您自己自行考量并且

自负风险，因资料之下载而导致您电脑系统之任何损坏或资料流失的，应由你自

己承担责任；
   3、您自本公司以及工作人员取得的建议和咨询，无论是书面或口头的，均不

构成本公司对您服务的承诺；
   4、在任何情况下，本公司对本协议所承担的违约赔偿责任总额不超过本公司

从您处收取的6个月的服务费；
   5、因不可抗力原因造成服务不能提供的，本公司不承担任何责任。
十、退款处理
根据金融机构或支付通道提供商的有关规定，对于网上交易流程中出现的、由于

商户的原因（例：缺货、无法运货等）造成不能发货、做退款处理的情况，按如

下处理：
  1、商户按本公司的要求，在规定时限内，提交退款处理所需要的文字材料；
  2、当您向本公司提出退款请求时，您在我公司的帐户中应有足够退款的账存

资金，或者您须按要求退款的金额将款项转帐至本公司的帐户，本公司收到该款

项后向持卡人做出退款处理。否则造成的一切后果均由商户承担；
  3、退款时本公司不再另收取手续费，但若金融机构或者支付通道合作方需要

另行收取相关费用的，则此费用由商户承担；
  4、扣款过程中收取的手续费不再退还，此费用由商户承担；
  5、您负责解决消费者无故拒付而导致的退款问题。
十一、关于拒付及调单
  1、拒付是国际发卡组织规定信用卡持卡人用信用卡支付款项后，自持卡人收

到帐单后180日内，持卡人有权拒绝支付该笔款项；持卡人提出拒付后，收单行

必须将从该持卡人信用卡中扣去的款项返还给持卡人；
  2、调单是国际发卡组织规定的，信用卡持卡人对某笔消费有异议的时候而向

金融机构申请调查相关情况的交易；
  3、在您的交易中，如果发生拒付交易，则该损失全部由您承担，您必须按照

本协议规定将有关拒付的款项（包括处理费20美元和罚款50美元）全部支付到

本公司；
  4、在您的交易中，如果发生调单交易，则本公司将冻结这笔交易资金的结算

；
  5、如果您的交易在一个月内发生拒付或调单的交易笔数达到本月总交易笔数

的3，或者发生拒付或调单的交易金额达到本月交易金额的3的，本公司将冻结

您本月的全部交易款项。如果交易款项已经结算给商户的，商户有义务在一个星

期内将已结算资金返还到我公司帐户上；
  6、如果您使用我公司的外卡支付平台，当月外卡交易发生拒付的笔数或者金

额达到当月成功交易笔数或者成功交易金额0.5的，则商户必须按照拒付笔数承

担每笔50美金的罚款；另外，商户发生的拒付每笔必须承担20美金的拒付处理

费用。
十二、保密条款
     您与本公司在合作期内获得的信息、资料等内容均为保密信息，保密信息

包括但不限于交易手续费、服务年费以及保证金的数额及支付方式、结算方式、

接口技术、安全协议及证书等，任何一方均应当对保密信息严格保密，且仅为本

合作之目的使用，未经对方事先书面同意，不得向任何第三方透露保密信息，否

则视为违约；守约方除有权主张违约金及损失外还有权终止合同。本条不因本合

同的终止而终止。
十三、其他
  1、您的交易数据在本公司支付平台上保留半年，供您免费查询及下载。您应

及时下载交易数据并存档。超过半年以上的数据查询，经本公司同意后可以查询

；
  2、您终止使用本公司服务后，本公司保留因银行或电信等部门对合同期内交

易进行调单或持卡人对服务期内交易拒付而向商户追偿该金额的权利。
十四、法律适用
  1、	本协议及为实现本协议服务所签订、公布的事项的、解释、履行、效力

等均适用中华人民共和国法律。
  2、	如果本协议任何条款根据现行法律被确定为无效或无法实施，本协议的

其他所有条款将继续有效。此种情况下，本公司将以有效的条款替换该条款，且

该有效条款应尽可能接近原条款和本协议相应的精神和宗旨。
  3、	后继立法除其本身有明确规定外，对本协议不具有溯及力。
十五、诉讼管辖
因本协议发生任何纠纷而提起的诉讼，其第一审管辖法院为xingbill所在法院

院。
注：终止使用本公司服务或合同到期后，继续使用本公司服务的，本公司有权冻

结最后一个月交易或冻结相当于一个月的平均交易金，该冻结的交易须满6个月

后方可无息结算。
十六、转让
   本公司转让本协议无需您的同意。


 							</TEXTAREA></li>
             </ul>
           </div> 
         </div>
         <div class="sign_info">
         
           <div class="sign_info_title">&nbsp;</div>
           <div class="sign_info_info2">
             <ul class="centertt2">
               <li><input name=aa type="button" value="同意注册条款提交" class="button_011" onclick="done()" /></li>
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
     <div class="top_top"><img src="images/top22.gif" alt="" /></div>
   </div>
   <div class="footer">
     <div><span class="font_color_01"><img src="merchant/images/icon_04.gif" alt="" />&nbsp;&nbsp;我有意见或建议，跟xingbill说两句</span></div>
     <div class="links">
       <ul>
         <li><a href="#">公司简介</a> | <a href="#">网上支付</a> | <a href="#">免责声明</a> | <a href="#">隐私政策</a> | <a href="#">联系我们</a> </li>

         <li>Copyright(c)2010. All rights reserved. "xingbill"。International Payments - Secure Online Payments - Send Payments Online - Internet Payment </li>
       </ul>
     </div>
   </div>
</div>
<script language="javaScript" type="text/javascript" src="script/zc_foot.js"></script>
</div></div>
</body>
</html>
