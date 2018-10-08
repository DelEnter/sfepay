<%@ page contentType="text/html; charset=UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
<title>星支付</title>
<link href="css/styles.css" rel="stylesheet" type="text/css" />
<link href="../css/styles.css" rel="stylesheet" type="text/css" />
</head>
<%
String urlType=(String)session.getAttribute("url");
if(!"2".equals(urlType)){
session.setAttribute("url", "1");
}
%>
<script type="text/javascript" src="https://security.sslepay.com/js/prototype-1.6.0.2.js"></script>
	<script type="text/javascript" src="https://security.sslepay.com/js/json2.js"></script>
    
    <script type="text/javascript">	
    function downFile(){
    	cheMerUsers('getSessionUser.action');
    }
	function cheMerUsers(jsonObjGetUrl)
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
			asynchronous:false
		});
	}
    function processResponse(request)
	{	
		//使用JSON对象将服务器响应解析成JSON对象
		var res = JSON.parse(request.responseText);
		//遍历JSON对象的每个属性
		if(res.jsonData=="0"){
			alert("请先登录在下载！");
			window.location="./merLogin.action";
		}else{
			var files=res.jsonData.split("|");
			document.getElementById("zc").href=files[0];
			document.getElementById("mg").href=files[1];
			document.getElementById("ec").href=files[2];
			document.getElementById("asp").href=files[3];
			}
	}
    </script>
<body class="cms-home">
<!--头部-->
<div class="top-bar">
    <div class="w-980 clearfix">
       <ul class="top-menu fr clearfix">   
            <li><a title="登录" href="phone.jsp">联系我们</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
            <li><a title="登录" href="#">接口下载</a>&nbsp;&nbsp;|&nbsp;&nbsp;</li>
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
                <li class="fl"><a class="ent licai" href="#" >关于星支付</a></li>
                <li class="fl"><a  href="#" class="ent fuwu">金融服务</a></li>
                <li class="fl nav-yifen"><a  href="#" class="ent yifen">新闻中心<span class="new-pro"></span></a></li>
              <li class="fl last"><a  href="#" class="ent shanghu">商家服务</a></li>
            </ul>
        </div>
    </div>
</div>
<div style="height: 300px;margin-top: 100px;margin-left:600px;width: 400px;font-size: "  >
<input type="hidden" id="fileType" />
<a id="zc" href="#" onclick="downFile('1');"><span style="color:red;font-size:14px">ZenCart系统支付接口</span></a><br/><br/>
<a id="mg" href="#" onclick="downFile('1');"><span style="color:red;font-size:14px">magento系统支付接口</span></a><br/><br/>
<a id="ec" href="#" onclick="downFile('1');"><span style="color:red;font-size:14px">ecshop系统支付接口</span></a><br/><br/>
<a id="asp" href="#" onclick="downFile('1');"><span style="color:red;font-size:14px">asp系统支付接口</span></a><br/><br/>
<span style="color:blue">* 温馨提示：需要其他接口请联系xingbill技术!&nbsp;&nbsp;&nbsp;<a target=blank href=tencent://message/?uin=466865609&Site=466865609&Menu=yes><img border="0" SRC=http://wpa.qq.com/pa?p=10:466865609:10 alt="点击这里给我发消息"></a></span>
<br/><br/><div style="height: 290px;margin-left: 100px">
<%if("2".equals(urlType)){ %>
<a href="goHome.action">返回商户后台</a>
<% }%>
</div>
</div>

<!--banner 部分--->


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