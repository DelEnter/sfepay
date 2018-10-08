<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>注册</title>
    <link rel="stylesheet" type="text/css" href="css/bail.css">
    <style type="text/css">
    .ADKPasswordStrength_low{ background-color:#CC0000;}
    .ADKPasswordStrength_middle{ background-color:#33CCFF;}
    .ADKPasswordStrength_height{ background-color:#66FF00;}
    </style> 
    <script src="js/prototype-1.6.0.2.js" type="text/javascript"></script>
	<script src="js/json2.js" type="text/javascript" ></script>

	<script language="JavaScript" type="text/JavaScript">
		
		
	 	function Evaluate(word)
	    {
	 		if($("pwd").value==$("username").value){
	 			document.getElementById("passError").innerHTML="用户名和密码不能一致";
	 			return;
	 		}
	 		var d = validatepwd(word);
	 		var erp = document.getElementById("passError").innerHTML;
	 		if(d<=1){
	 			document.getElementById("passError").innerHTML="密码长度小于7位或者全是数字或字母.";
	 		}else if(d>1){
	 			document.getElementById("passError").innerHTML="ok";
	 		}
	    }
	 	 function validatepwd(word){
	    	return word.replace(/^(?:([a-z])|([A-Z])|([0-9])|(.)){7,}|(.)+$/g, "$1$2$3$4$5").length;
	     }
	 	 
	 	 
	 	 function validatecompwd(compwd){
	 		 var pwd = document.getElementById("pwd").value;
	 		 confirmpwd(pwd,compwd);
	 	 }
	 	 
	 	 
	 	 function confirmpwd(pwd,cpwd){
	 		 if(cpwd==pwd){
	 			document.getElementById("rPassError").innerHTML="ok";
	 		 }else{
	 			 document.getElementById("rPassError").innerHTML="两次输入的密码不一致.";
	 		 }
	 	 }
	 	 
	 	 
	 	function checkUser(username){
	 		che('getUsernameByby.action?username='+username);
	 		
		}
	 	
	 	
	 	function che(jsonObjGetUrl)
		{
			//将favorite表单域的值转换为请求参数
			//var params = Form.serialize('form1');
			//创建Ajax.Request对象，对应于发送请求
			var myAjax = new Ajax.Request(
			jsonObjGetUrl,
			{
				//请求方式：POST
				method:'post',
				//请求参数
				//parameters:params,
				//指定回调函数
				onComplete: processResponse,
				//是否异步发送请求
				asynchronous:true
			});
		}
	    function processResponse(request)
		{	
			//使用JSON对象将服务器响应解析成JSON对象
			var res = JSON.parse(request.responseText);
			//遍历JSON对象的每个属性
			if(res.jsonData=="1"){
				$("userError").innerHTML="用户名已经存在.";
			}else{
				$("userError").innerHTML="ok";
			}
		}
	    
	    function validatename(name){
	    	if(name!=""){
	    		$("nameerror").innerHTML="ok";
	    		
	    	}else{
	    		$("nameerror").innerHTML="请输入姓名.";
	    	}
	    	
	    }
	    
	    function checkAll(){
	    	var ue = $("userError").innerHTML;
	    	var pe = $("passError").innerHTML;
	    	var cpe = $("rPassError").innerHTML;
	    	var na = $("nameerror").innerHTML;
	    	var msg="ok";
	    	if(ue==msg && pe==msg && cpe==msg && na==msg){
	    		//alert(ue)
	    		document.getElementById("formu").submit();
	    	}else{
	    		
	    	}
	    }
	</script>
	<style>
		span{
			color: red;
			font-size: 12;			
		}
	</style>
  </head>
  
  <body>
  <h3 style="color: #dcdcdc;background-color: #006666" align="center">用户注册</h3>
  	<center>
	  	<form action="regeditUser.action" method="post" name="formu" id="formu">
			<table width="80%" cellSpacing="1" cellPadding="0"  align="center" class="tableEdit" border="0">  	   		
			  		<tr align="center">
			  			<td align="right">登陆用户名：</td>
			  			<td ><input type="text" name="username" id="username" onblur="checkUser(this.value)"></td>
			  			<td align="left"><span style="color:red" id="userError"></span></td>
			  			<td align="left"><span style="color:blue">*登陆后台用户名,限字母数字</span></td>
			  		</tr>
			  		<tr align="center">
			  			<td align="right">密码：</td>
			  			<td ><input type="password" id="pwd" name="pwd" onblur="Evaluate(this.value);" /></td>
			  			<td  align="left"><span style="color:red" width="20" id="passError" ></span></td>
			  			<td  align="left"><span style="color:blue">*输入一个新密码,不少于7位。必须同时包含字母、数字</span></td>
			  		</tr>
			  		<tr align="center">
			  			<td align="right">确认密码：</td>
			  			<td  ><input type="password" name="compwd" id="compwd" onblur="validatecompwd(this.value);" /></td>
			  			<td align="left"><span style="color:red" id="rPassError"></span></td>
			  			<td  align="left"><span style="color:blue">*再次输入密码</span></td>
			  		</tr>
			  		<tr align="center">
			  			<td align="right">姓名：</td>
			  			<td ><input type="text" name="name" id="name" onblur="validatename(this.value);" /></td>
			  			<td  align="left"><span style="color:red" id="nameerror"></td>
			  			<td  align="left"></span><span style="color:blue">*真实姓名</span></td>
			  		</tr>
			  		<tr>
			  		<td colspan="3" align="center"><input type="button" onclick="checkAll()" value="提交"></td>
			  		</tr>
		
			</table>
		</form>
    </center>
 </body>
</html>
