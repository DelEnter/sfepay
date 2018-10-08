<link rel="stylesheet" href="css/main.css" type="text/css" />
<link rel="shortcut icon" href="favicon.ico" />
<link rel="icon" type="image/gif" href="animated_favicon1.gif" />
<script type="text/javascript" src="css/jquery-1.4.2.min.js"></script>
<!--[if lte IE 6]>
<script type="text/javascript">
		var query = location.search,
			rJQ = /jQuery=(\d\.\d(?:\.\d)?)/,
			jQMatch = rJQ.exec(query),
			jQVersion = jQMatch ? jQMatch[1] : '1.4.2',
			useLive = !!(query.indexOf('live') + 1);
	</script>  
<script type="text/javascript" src="css/jquery.ie6hover.js"></script>
<![endif]-->
<script type="text/javascript">
	function checkHolderInfo(){
		var vercode = document.getElementById("vercode").value;
		var name = document.getElementById("username").value;
		var password=document.getElementById("password").value;
		userCheck.checkAll(name,password,vercode,"");
	}
	
	function checkMerno(){
		if(document.getElementById("merno").value==""){
			alert("商户编号不能为空！");
			document.getElementById("merno").focus();
			return false;
		}
		return true;
	}
	
	function checkUsername(){
		if(document.getElementById("username").value==""){
			alert("用户名不能为空！");
			document.getElementById("username").focus();
			return false;
		}
		return true;
	}
	
	function checkPassword(){
		if(document.getElementById("password").value==""){
			alert("密码不能为空！");
			document.getElementById("password").focus();
			return false;
		}
		return true;
	}
	
	function checkVercode(){
		if(document.getElementById("vercode").value==""){
			alert("验证码不能为空！");
			document.getElementById("vercode").focus();
			return false;
		}
		return true;
	}
	
	function checkAll(){
		if(checkMerno() && checkUsername() && checkPassword() && checkVercode() ){
			return true;
		}
		return false;
	}
	
	function refreshimg(){
	    		var random = Math.random();
	    		//IE7有缓存，为了保证验证不被缓存,加上随机内容的参数
	    		document.getElementById("authImg").src="http://www.sfepay.com/authImg.do";
	    	}
	function submitInfo(terget,op){
		var form2 = document.getElementById('form2');
		if(op=='login'){
			if(checkAll()){
				document.form2.action=terget;
				document.form2.submit();
			
			}else{
				return false;
			}
		}
		document.form2.action=terget;
		document.form2.submit();
	}

	
	function chageLanguage(language){
		if(language=='English'){
		 	document.URL='index_en.html'
		}if(language=='Chinese'){
			document.URL='index_cn.html'
		}
	}
	
	function resetPwd(){
		window.open("resetPwd.jsp");
	}
</script>
<script type="text/javascript">
 $(document).ready(function(){
document.getElementById("merno").focus();
});
</script>