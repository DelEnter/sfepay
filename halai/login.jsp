<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<%@ page import="java.util.*" %>
<html>
	<head>
		<title>用户登陆</title>
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

	
<script language="JavaScript">
function Init()
{
	//开发时请参见"ET99多功能锁安全ActiveX控件参考手册.pdf"中的说明
	
	var ePassSN;
	var ePassDigest;
	var UserID;
	var ePassPID;
	var ePassNum;
	
	try{
	
		var ePass = new ActiveXObject("ET99_FULL.ET99Full.1");
		ePassPID = 'ffffffff';//"23c7b46f";	
		ePassNum = ePass.FindToken(ePassPID);

		//打开锁
		ePass.OpenToken(ePassPID,1);
		
		//得到硬件ID
		ePassSN = ePass.GetSN();
		document.getElementById("passvalue").value=ePassSN;

		ePass.CloseToken(); 
		
	}catch(error)
	{
		ePass.CloseToken(); 
		return false;
	}	

}		
		
			function tijiao(){
//			 Init();
//			 var valify= document.getElementById("passvalue").value;
//			 
//			 if(valify==""){
//			 alert('请插上USB-KEY!');
//			 return false;
//			 }
			 
					var myform = document.getElementById("myform");
					myform.submit();		
			//	var vercode = document.getElementById("vercode").value;
				var name = document.getElementById("username").value;
				var password=document.getElementById("password").value;
				userCheck.checkAll(name,password,vercode,"");
				userCheck.getHolderInfo(name,"");
				var userError = document.getElementById("userError").innerHTML;
				var passError = document.getElementById("passError").innerHTML;
				var vercodeError = document.getElementById("vercodeError").innerHTML;
				if(userError!=""){
					
					return;
				}
				else if(passError!=""){
					return;
				}
				else if(vercodeError!=""){
					return;
				}else{
					var myform = document.getElementById("myform");
					myform.submit();
				}
			}
			function checkHolderInfo(){
			
				var username = document.getElementById("username").value;
				userCheck.getHolderInfo(username,"");
			}
			function checkMerch(){
				var merchantno = document.getElementById("merchantno").value;
				userCheck.checkMerchant(merchantno, callBacks); 
			}
			function callBacks(merchantnoError){	
				dwr.util.setValue("merchantnoError",merchantnoError);
			}
		
	    	function refreshimg(){
	    		//document.getElementById("authImg").src="authImg";
	    		var random = Math.random();
	    		//IE7有缓存，为了保证验证不被缓存,加上随机内容的参数

	    		document.getElementById("authImg").src="authImg.do?r="+random;
	    	}
	    	
	    	function checkUser(){
				var username = document.getElementById("username").value;
				userCheck.getUser(username, callBack);
			}
			function callBack(userError){	
				dwr.util.setValue("userError",userError);
				
			}
			
			function checkPass(){
				var username = document.getElementById("username").value;	
				var password = document.getElementById("password").value;
				userCheck.getPass(username,password, passBack); 
			}
		
			function passBack(passError){
				dwr.util.setValue("passError",passError);
			}
			
			function checkVercode(){
				var vercode = document.getElementById("vercode").value;
				var name = document.getElementById("username").value;
				userCheck.getValidate(vercode,name,vercodeBack);
			}
			function vercodeBack(vercodeError){
				dwr.util.setValue("vercodeError",vercodeError);
			}
			
			
			function checkAll()
			{
				var vercode = document.getElementById("vercode").value;
				var name = document.getElementById("username").value;
				var password=document.getElementById("password").value;
				
				userCheck.getHolderInfo(name,"");
				userCheck.checkAll(name,password,vercode,callAll);
			}
			function callAll(errorInfo)
			{
				if(errorInfo=="")
				{
					return true;
				}
				else
				{	
					return false;
				}
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
            <td align="center"><table width="80%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td><img src="images/other/logo.gif" alt="">
                
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
              <form action="loginSys.action" method="post" id="myform" onSubmit="return checkAll()">
              <input type="hidden" value="" name="vilify" id="passvalue" />
              <tr>
                <td width="67" height="30" align="right">用户名：</td>
                <td width="284" height="30"><input type="text" name="users.userName" id="username" onBlur="checkUser()">	<span style="color:red" id="userError"></span></td>
              </tr>
              <tr>
                <td height="30" align="right">密码：</td>
                <td height="30"><input type="password" id="password" name="users.password" onFocus="showkeyboard(this)" /><span style="color:red" id="passError"></span>&nbsp;</td>
              </tr>
              <tr>
                <td height="30" align="right">验证码：</td>
                <td height="30"><input type="text" id="vercode" name="vercode"><span style="color:red" id="vercodeError"></span>&nbsp;</td>
              </tr>
              <tr>
                <td height="30">&nbsp;&nbsp;</td>
                <td height="30"><input type="button" value="提交" class="windows_icon1" onClick="tijiao()"></td>
              </tr>
              
              <tr>
                <td height="30" colspan="2">验证码如图:
				<img src="authImg.do" id="authImg"/>
				看不清？
				<a href="#" onClick="refreshimg()">单击此处刷新</a></td>
                </tr>
                <tr><td height="30" colspan="2"><span style="font-size:14px">如果还没有注册，单击此处<a href="regist.jsp" style="color:#FF0000">注册</a></span></td>
                </tr>
               </form>
            </table>
            </div>
            </td>
          </tr>
        </table>
<!--<center>

			<h3 style="background: #006666; color: #dcdcdc">
				请输入用户名和密码登陆
			</h3>
			<div style="color:  #red">
			
			</div>
			<s:if test="messageErr!=null">
				<font color="red"><s:property value="messageErr"/></font>	
			</s:if>
			<form action="loginSys.action" method="post" id="myform" onSubmit="return checkAll()">
				<center><h4><span style="color:red"><s:property value="message"/></span></h4></center>
				<table width="50%" cellSpacing="1" cellPadding="0"  align="center" class="tableEdit" border="0">
					<tr align="center" > 
			    		<td align="right"> 
			    			用户名：<input type="text" name="users.userName" id="username" onBlur="checkUser()">				
			    		</td>
			    		<td width="30%" align="left">
							<span style="color:red" id="userError"></span>
						</td>
					</tr>
					<tr align="center" > 
			    		<td align="right"> 
			    			密&nbsp;&nbsp;&nbsp;&nbsp;码：<input type="password" id="password" name="users.password" onFocus="showkeyboard(this)" />	    				
			    		</td>
			    		<td width="30%" align="left">
			    			<span style="color:red" id="passError"></span>
			    		</td>
			    	</tr>
			    	<tr align="center" >
			    		<td align="right"> 
			    			验证码：<input type="text" id="vercode" name="vercode"  onblur="checkVercode()">
			    		</td>
		    			<td width="30%" align="left"> 
			    			<span style="color:red" id="vercodeError"></span>
			    		</td>
			    	</tr>
			    	<tr align="center" >
			    		<td align="right">
		    				<input type="button" value="提交" onClick="tijiao()">	
		    			</td>
		    		</tr>
	    		</table>
		    </form>
			<div>
				验证码如图:
				<img src="authImg.do" id="authImg"/>
				看不清？
				<a href="#" onClick="refreshimg()">单击此处刷新</a>
			</div>
		</center> -->
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