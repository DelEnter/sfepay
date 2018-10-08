<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%
	if (request.getParameter("random") != null)
		if (request.getParameter("random").equals(
				session.getAttribute("randomCode"))) {
			out.println("ok!");
		}
%>
<html>
	<head>
		<title>修改密码</title>
		<script type="text/javascript" src="dwr/interface/userCheck.js"></script>
		<script type="text/javascript" src="dwr/engine.js"></script>
		<script type="text/javascript" src="dwr/util.js"></script>
		<link rel="stylesheet" type="text/css" href="css/css.css">
        <link rel="stylesheet" type="text/css" href="css/other.css">
		<style>

.rjp {
	color: #083772;
	font-weight: bold;
	font-size: 16px;
	text-decoration: none;
}
</style>		
		<script type="text/javascript">
		
	    	function refreshimg(){
	    		//document.getElementById("authImg").src="authImg";
	    		var random = Math.random();
	    		//IE7有缓存，为了保证验证不被缓存,加上随机内容的参数

	    		document.getElementById("authImg").src="authImg.do?r="+random;
	    	}
	    	
	    	function confirmPwd(){
	    		var newpwd1 = document.getElementById('newpwd1').value;
	    		var newpwd2 = document.getElementById('newpwd2').value;
	    		if(newpwd1!=newpwd2){
	    			document.getElementById('newpwd2Error').innerHTML="两次输入新密码不一致";
	    		}else{
	    			document.getElementById('newpwd2Error').innerHTML="";
	    			
	    		}
	    	}
	    	
	    	function checkLength(val,vid){
	    		
	    		var d = validatepwd(val);
		 		if(d<=1){
		 			document.getElementById(vid+'Error').innerHTML="密码长度小于7位或者全是数字或字母.";
		 			return false;
		 		}else{
		 			document.getElementById(vid+'Error').innerHTML="";
		 			return false;
		 		}
		 		
	    		if(val.length<7){
	    			document.getElementById(vid+'Error').innerHTML="密码长度不能小于6位."
	    			return false;
	    		}else{
	    			document.getElementById(vid+'Error').innerHTML=""
	    			return false;
	    		}
	    		if(val.length>17){
	    			document.getElementById(vid+'Error').innerHTML="密码长度不能大于16位."
	    			return false;
	    		}else{
	    			document.getElementById(vid+'Error').innerHTML=""
	    			return false;
	    		}
	    	}
	    	function validatepwd(word){
		    	return word.replace(/^(?:([a-z])|([A-Z])|([0-9])|(.)){7,}|(.)+$/g, "$1$2$3$4$5").length;
		     }
	    	function tijiao(){
	    		var passwd = document.getElementById('password1').value;
	    		var newpwd1 = document.getElementById('newpwd1').value;
	    		var newpwd2 = document.getElementById('newpwd2').value;
	    		var passwdError = document.getElementById('password1Error').innerHTML;
	    		var newpwd1Error = document.getElementById('newpwd1Error').innerHTML;
	    		var newpwd2Error = document.getElementById('newpwd2Error').innerHTML;
	    		if(passwd==""){
					alert("请输入旧密码信息.");
					return false;
	    		}
	    		if(newpwd1==""){
					alert("请输入新密码信息.");
					return false;
	    		}
	    		if(newpwd2==""){
					alert("请输入新密码信息.");
					return false;
	    		}
	    		if(passwdError!=""){
	    			alert("旧密码信息有误.")
	    			return false;
	    		}
	    		if(newpwd1Error!=""){
	    			alert("新密码信息有误.")
	    			return false;
	    		}
	    		if(newpwd2Error!=""){
	    			alert("新密码信息有误.")
	    			return false;
	    		}
	    		document.getElementById("myform").submit();
	    	}
			
    	</script>
	<script type="text/javascript">
var on = 0;

//定义当前是否大写的状态
var CapsLockValue=0;
var passwordObj;
//给输入的密码框添加新值
	function addValue(newValue)
	{
		
		if (CapsLockValue==0)
		{
			passwordObj.value += newValue;
		}
		else
		{
			passwordObj.value += newValue.toUpperCase();
		}
		
		document.getElementById('myform:password').value=passwordObj.value;
	}
//实现BackSpace键的功能
	function setpassvalue()
	{
		var longnum=document.getElementById('myform:password').value.length;
		var num=passwordObj.value.substr(0,longnum-1);
		passwordObj.value=num;
		document.getElementById('myform:password').value=num;
	}
//输入完毕eveal方法是使var的变量转换成传入类型
	function OverInput()
	{
		softkeyboard.style.display="none";
	}
//显示软键盘
	function showkeyboard(obj)
	{
		softkeyboard.style.display="block";
		passwordObj = obj;
		on = 1;
		
	}
	function close() {

		softkeyboard.style.display="none";
	}

//设置是否大写的值
function setCapsLock()
{
	if (CapsLockValue==0)
	{
		CapsLockValue=1;
	}
	else 
	{
		CapsLockValue=0;
	}
}
//-->
</script>    	
		<link rel="stylesheet" type="text/css" href="css/bail.css">
	</head>
	<body>
    

    
		<table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td align="center"><table width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                 <td><img src="images/logo-sfepay.png" alt="">
                
                </td>
              </tr>
            </table></td>
          </tr>
          <tr>
             <td height="537" background="images/other/login_bg.gif">
             <table width="360" border="0" align="center" cellpadding="0" cellspacing="3">
          <table width="360" border="0" align="center" cellpadding="0" cellspacing="3">
              
              <tr>
              	<td colspan="2"><font color="red"><s:property value="messageAction"/></font></td>
              </tr>
              <form action="modifyPwd.action" method="post" id="myform" onSubmit="return checkAll()">
             
              <tr>
                <td width="67" height="30" align="right">用户名：</td>
                <td width="284" height="30"><s:property value="users.userName"/>
               <input type="hidden" name="userName" value="<s:property value="users.userName"/>" />
                <span style="color:red" id="userError"></span></td>
              </tr>
              <tr>
                <td height="30" align="right">原始密码：</td>
                <td height="30"><input type="password" id="password1" name="password1"  />
                <span style="color:red" id="password1Error"></span>&nbsp;</td>
              </tr>
              <tr>
                <td height="30" align="right">密码：</td>
                <td height="30"><input type="password" id="newpwd1" name="newpwd1" onblur="checkLength(this.value,this.id)" onFocus="showkeyboard(this)" />
                <span style="color:red" id="newpwd1Error"></span>&nbsp;</td>
              </tr>
              <tr>
                <td height="30" align="right">确认密码：</td>
                <td height="30"><input type="password" id="newpwd2" name="newpwd2" onblur="checkLength(this.value,this.id);confirmPwd();" onFocus="showkeyboard(this)" />
                <span style="color:red" id="newpwd2Error"></span>&nbsp;</td>
              </tr>
              <tr>
                <td height="30">&nbsp;&nbsp;</td>
                <td height="30"><input type="button" value="提交" class="windows_icon1" onclick="tijiao()"></td>
              </tr>
               </form>
            </table>
            </div>
            </td>
          </tr>
        </table>
							<div align="center" id="softkeyboard" style="display: none">
							<table width="273" border="0" cellspacing="0" cellpadding="0">
								<tr>
									<td bgcolor="#989898">
									<table width="100%" border="0" cellspacing="1" cellpadding="0">
										<tr>
											<td bgcolor="#FFFFFF">
											<table width="100%" border="0" cellspacing="0" cellpadding="0">
												<tr>
													<td height="120" align="center">
													<table width="262" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('#')" class="rjp">#</a>
															</td>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue(',')" class="rjp">,</a>
															</td>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('.')" class="rjp">.</a>
															</td>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('/')" class="rjp">/</a>
															</td>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('1')" class="rjp">1</a>
															</td>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('2')" class="rjp">2</a>
															</td>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('3')" class="rjp">3</a>
															</td>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('4')" class="rjp">4</a>
															</td>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('5')" class="rjp">5</a>
															</td>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('6')" class="rjp">6</a>
															</td>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('7')" class="rjp">7</a>
															</td>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('8')" class="rjp">8</a>
															</td>
														</tr>
														<tr>
															<td background=" images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('9')" class="rjp">9</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('0')" class="rjp">0</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('a')" class="rjp">a</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('b')" class="rjp">b</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('c')" class="rjp">c</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('d')" class="rjp">d</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('e')" class="rjp">e</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('f')" class="rjp">f</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('g')" class="rjp">g</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('h')" class="rjp">h</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('i')" class="rjp">i</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('j')" class="rjp">j</a>
															</td>
														</tr>
														<tr>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('k')" class="rjp">k</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('l')" class="rjp">l</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('m')" class="rjp">m</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('n')" class="rjp">n</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('o')" class="rjp">o</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('p')" class="rjp">p</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('q')" class="rjp">q</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('r')" class="rjp">r</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('s')" class="rjp">s</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('t')" class="rjp">t</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('u')" class="rjp">u</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('v')" class="rjp">v</a>
															</td>
														</tr>
														<tr>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('w')" class="rjp">w</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('x')" class="rjp">x</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('y')" class="rjp">y</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('z')" class="rjp">z</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('`')" class="rjp">`</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('-')" class="rjp">-</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('=')" class="rjp">=</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('@')" class="rjp">@</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue('[')" class="rjp">[</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue(']')" class="rjp">]</a>
															</td>
															<td background="images/keyboard/login09.jpg" width="22" height="24"
																align="center">
															<a href="javascript:addValue(';')" class="rjp">;</a>
															</td>
															<td background="images/keyboard/login12.jpg" width="22" height="24"
																align="center">
															<img src="images/keyboard/transparent.gif" />
															</td>
														</tr>
													</table>
													<table width="262" border="0" cellspacing="0" cellpadding="0">
														<tr>
															<td width="82" height="24" background="images/keyboard/login10.jpg"
																align="center">
															<a href="javascript:addValue(' ')" class="rjp">Space</a>
															</td>
															<td width="48" background="images/keyboard/login10b.jpg" align="center">
															<a href="javascript:setCapsLock()" class="rjp">Shift</a>
															</td>
															<td width="86" background="images/keyboard/login10c.jpg" align="center">
															<a href="javascript:setpassvalue(this)" class="rjp">BackSpace</a>
															</td>
															<td width="48" background="images/keyboard/login10d.jpg" align="center">
															<a href="javascript:OverInput()" class="rjp">
															<font color="#CC0000">Enter</font>
															</a>
															</td>
														</tr>
													</table>
													</td>
												</tr>
												<tr>
													<td height="23" background="images/keyboard/login08.jpg" valign="top">
													为安全起见,建议使用软键盘输入密码.</td>
												</tr>
											</table>
											</td>
										</tr>
									</table>
									</td>
								</tr>
							</table>
							</div>		
		
	</body>
</html>