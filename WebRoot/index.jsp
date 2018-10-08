<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>星支付</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript">
	function refreshimg(){
	    		//document.getElementById("authImg").src="authImg";
	    		var random = Math.random();
	    		//IE7有缓存，为了保证验证不被缓存,加上随机内容的参数
	    		if(document.getElementById("msg").value!=""){
	    			document.getElementById("authImg").src="../authImg.do?r="+random;
	    		}else{
	    		document.getElementById("authImg").src="authImg.do?r="+random;
	    		}
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
		document.form2.submit();
	}
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
		if(checkMerno() && checkPassword() && checkVercode() ){
			return true;
		}
		return false;
	}
	    	
</script>
<body class="cms-home">
<!--头部-->
<div class="top-bar">
    <div class="w-980 clearfix">
       <ul class="top-menu fr clearfix">   
            <li><a title="登录" href="phone.jsp">联系我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
            <li><a title="登录" href="#">技术支持</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
            <li><a href="#" title="注册" >加入收藏</a></li>
       </ul>
    </div>
</div>
<!--导航-->
<div class="header">
    <div class="w-980 clearfix">
        <div class="nav clearfix">
            <div class="logo">
                <h1><a href="/"><img src="images/logo-youhui-gray.png"></a></h1>
            </div>
            <ul class="nav-enter fr clearfix">
            	<li class="fl"><a class="ent licai" href="index.jsp">首页</a></li>
                <li class="fl"><a class="ent licai" href="about.jsp" >关于星支付</a></li>
                <li class="fl"><a  href="#" class="ent fuwu">金融服务</a></li>
                <li class="fl nav-yifen"><a  href="#" class="ent yifen">新闻中心<span class="new-pro"></span></a></li>
              <li class="fl last"><a  href="#" class="ent shanghu">商家服务</a></li>
            </ul>
        </div>
    </div>
</div>

<!--banner 部分--->

<div class="banner pr">

<!--登录框-->
        <div class="w-980 login-area pr">
		  <div class="logbx">
    <div class="login-main success-login" id="J-login-main">
        <div class="login-wrapper pr" id="J-loginWrapper">
            <h3 class="login-tag">登录星支付</h3>
           <form name="form2" id="form2" action="" method="post" onsubmit="return checkHolderInfo()">
                <input type="text" id="merno" name="merno" size="18" placeholder="商户号">
                <input type="text" id="username" name="username" size="18" placeholder="用户名">
                <input type="password" id="password" name="password" size="18" placeholder="密码">
                <input type="text" id="vercode" name="vercode" size="9" placeholder="验证码">
                <a href="javascript:refreshimg();">
             <img style="height:25px; margin-left:9px; margin-top: 10px" 
             <s:if test="messageEnAction!=null">
             src="../authImg.do"
             </s:if>
			<s:else>
			src="authImg.do"
			</s:else>
              id="authImg" alt="点击刷新" /></a>
              <input type="hidden" id="msg" value='<s:property value="messageEnAction"/>'>
              <br/>
          		<span><font color="red"><s:property value="messageEnAction"/></font></span>
                <input type="button" value="登 录" onclick="submitInfo('merchant/merLogin.action','login')" class="login-btn">
          </form>
            <p class="mt10"><a class="reg-entry" 
             <s:if test="messageEnAction!=null">
             href="../newReg.jsp"
             </s:if>
			<s:else>
			href="newReg.jsp"
			</s:else>
            >注册新商户</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<a class="reg-entry" 
             <s:if test="messageEnAction!=null">
             href="../merchant/findPwd.jsp"
             </s:if>
			<s:else>
			href="merchant/findPwd.jsp"
			</s:else>
            >忘记密码</a></p>
            

            <p class="login-error hidden"></p>
        </div>
    </div>
</div>
        </div>
</div>

<!--首页内容-->
<div class="wrapper">
    <div class="w-980">
        <ul class="pay_method first">
            <h2>星支付</h2>
            <li>*上海星付金融服务有限公司推出最新收款通道</li>
            <li>* 用心做支付，拒绝耍流氓，确保资金安全	</li>
            <li>* 今日支付的新星，明日支付的明星</li>
        </ul>
    	<ul class="pay_method"><h2>新支付</h2>
            <li>* 商户后台操作简单，上手快</li>
            <li>* 订单查询管理方便快捷</li>
            <li>* 结算帐款安全快捷，准确保密</li>
            <li>* 实现子商户多项权限功能设置</li>
            <li>* 各项数据批量化下载</li>
        </ul>
    	<ul class="pay_method last"><h2>心支付</h2>
            <li> * 订单全程跟踪监控，确保交易安全</li>
            <li> * 7*24小时专业化的双语客服服务团队</li>
            <li> * 问题与投诉24小时内反馈解决机制</li>
            <li> * 全国统一免费热线中心</li>
        </ul>
    </div>
</div>
<!--网站底部-->
<div class="footer">
	<div class="footer-message">
        <ul class="clearfix">
            <li><a  href="/">关于星支付</a> |&nbsp;</li>
            <li><a  href="/">帮助中心</a> |&nbsp;</li>
            <li><a  href="/">诚征英才</a> |&nbsp;</li>
            <li><a  href="/">联系我们</a></li>
        </ul>
        <p class="copyright">个人用户客服热线 400-118-5581 企业客户及合作伙伴客服热线 400-118-5581<a  href="/">    星付金融服务有限公司</a> </p>
        <div class="footer-view">
            <a class="authentication" title="支付业务许可证"  href="http://co.baifubao.com/content/life/licence.html"></a>
            &nbsp;&nbsp;
            <span class="authentication_visa" title="Visa 验证"></span>
            &nbsp;&nbsp;
            <a class="authentication_verisign" title="VerSign加密服务"  href="https://sealinfo.websecurity.norton.com/splash?form_file=fdf/splash.fdf&amp;dn=www.baifubao.com&amp;lang=zh_cn"></a>
            &nbsp;&nbsp;
            <a class="authentication_china" title="电子商务协会认证诚信网"  href="https://ss.cnnic.cn/verifyseal.dll?sn=2010052400100001128&amp;pa=326341"></a> 
        </div>
    </div>
</div>


</body>
</html>