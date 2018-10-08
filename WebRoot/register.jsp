<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<!-- saved from url=(0029)http://jz.faisco.com/reg.html -->
<html><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8"><script type="text/javascript" async="" src="./js/adv-1.js"></script><script charset="utf-8" src="./js/lxb.js"></script><script charset="utf-8" src="./js/v.js"></script>

<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>迁易通注册</title>
<meta name="keywords" content="迁易通">

<link href="./css/mainCss.min.css" rel="stylesheet" type="text/css">
<link rel="stylesheet" type="text/css" href="css/common-html.css"/>
<script type="text/javascript" src="./js/jquery-core.min.js"></script>
<script type="text/javascript" src="./js/register.js"></script>
<script type="text/javascript">
	function reflesh(){
		var img = document.getElementById("register_code_img");
		img.src = "register_code?r=" + Math.random();
	}
</script>
<!-- yanzhenma -->
<script type="text/javascript">
	var countdown = 60;
	function settime(obj) {
		if (countdown == 0) {
			obj.removeAttribute("disabled");
			obj.value = "免费获取验证码";
			countdown = 60;
			return;
		} else {
			obj.setAttribute("disabled", true);
			obj.value = "重新发送(" + countdown + ")";
			countdown--;
		}
		setTimeout(function() {
			settime(obj)
		}, 1000)
	}
	function postphonever(em){
		var phone = document.getElementById("phone").value;
		var reg = /^1[34578]\d{9}$/;
		if (!reg.test(phone))
		{
			alert('手机号格式不正确');
			return;
		}
		$.ajax({
			type : "POST",
			url : "phonever.action",
			data : "phone=" + phone,
			dataType : "json",
			async : false,
			success : function(result) {
				if (result == "1") {
					alert("发送成功");
					settime(em);
				} else if (result == "3") {
					alert("取消发送，请耐心等待60秒时间");
				} else {
					alert("发送失败，请检查手机号是否正确");
				}
			}
		});
	}
</script>

<!-- baidu stat -->

<script type="text/javascript" async="" src="./js/presadv.js"></script><script type="text/javascript" async="" src="./js/mv.js"></script><script type="text/javascript" async="" src="./js/mba.js"></script><script async="" src="./js/a.js"></script><script type="text/javascript" async="" src="./js/mvl.js"></script><script src="./js/hm.js"></script><script src="./js/hm.js(1)"></script><script type="text/javascript">
var _hmt = _hmt || [];
(function() {
  var hm = document.createElement("script");
  hm.src = "//hm.baidu.com/hm.js?0e779bbf8fcaf2ef810d349978d4c5f0";
  var s = document.getElementsByTagName("script")[0]; 
  s.parentNode.insertBefore(hm, s);
})();
</script>

<!-- baidu stat end -->

<!-- sogou stat -->
<script type="text/javascript">
  var _sogou_sa_q = _sogou_sa_q || [];
  _sogou_sa_q.push(['_sid', '9936-10279']);
 (function() {
	var _sogou_sa_protocol = (("https:" == document.location.protocol) ? "https://" : "http://");
	var _sogou_sa_src=_sogou_sa_protocol+"hermes.sogou.com/sa.js%3Fsid%3D9936-10279";
	document.write(unescape("%3Cscript src='" + _sogou_sa_src + "' type='text/javascript'%3E%3C/script%3E"));
	})();
</script><script src="./js/sa.js" type="text/javascript"></script><script src="./js/lyb.js" type="text/javascript"></script>
<!-- sogou stat end -->

<!--  baidu2  -->
<script src="./js/logger.js"></script><link href="./css/bdsstyle.css" rel="stylesheet" type="text/css"></head><body class="body_reg"><iframe frameborder="0" style="display: none;" src="./js/saved_resource.html"></iframe><div id="bdshare_s" style="display: block;"><iframe id="bdsIfr" style="position:absolute;display:none;z-index:9999;" frameborder="0" src="./js/saved_resource(1).html"></iframe><div id="bdshare_l" style="display: none;"><div id="bdshare_l_c"><h6>分享到</h6><ul><li><a href="http://jz.faisco.com/reg.html#" class="bds_mshare mshare">一键分享</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_qzone qqkj">QQ空间</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_tsina xlwb">新浪微博</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_bdysc bdysc">百度云收藏</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_renren rrw">人人网</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_tqq txwb">腾讯微博</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_bdxc bdxc">百度相册</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_kaixin001 kxw">开心网</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_tqf txpy">腾讯朋友</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_tieba bdtb">百度贴吧</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_douban db">豆瓣网</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_tsohu shwb">搜狐微博</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_bdhome bdhome">百度新首页</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_sqq sqq">QQ好友</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_thx thx">和讯微博</a></li><li><a href="http://jz.faisco.com/reg.html#" class="bds_more">更多...</a></li></ul><p><a href="http://jz.faisco.com/reg.html#" class="goWebsite">百度分享</a></p></div></div></div><div style="display:none;">
<script src="./js/adv.js"></script><script type="text/javascript">
var _bdhmProtocol = (("https:" == document.location.protocol) ? " https://" : " http://");
document.write(unescape("%3Cscript src='" + _bdhmProtocol + "hm.baidu.com/h.js%3Fbc4ef9b2d31c2b842a6ad4a8ae06b906' type='text/javascript'%3E%3C/script%3E"));
</script><script src="./js/h.js" type="text/javascript"></script><a href="http://tongji.baidu.com/hm-web/welcome/ico?s=bc4ef9b2d31c2b842a6ad4a8ae06b906" target="_blank"><img border="0" src="./js/21.gif" width="20" height="20"></a>
</div>
<!--  baidu2 end -->

<!-- pinyou find code start -->
<script type="text/javascript">
var _py = _py || [];
_py.push(['a', 'bts..zT6q8oc8Gm5sEIfcFR_iB0']);
_py.push(['domain','stats.ipinyou.com']);
_py.push(['e','']);
-function(d) {
	var s = d.createElement('script'),
	e = d.body.getElementsByTagName('script')[0]; e.parentNode.insertBefore(s, e),
	f = 'https:' == location.protocol;
	s.src = (f ? 'https' : 'http') + '://'+(f?'fm.ipinyou.com':'fm.p0y.cn')+'/j/adv.js';
}(document);
</script>
<noscript>&lt;img src="//stats.ipinyou.com/adv.gif?a=bts..zT6q8oc8Gm5sEIfcFR_iB0&amp;e=" style="display:none;"/&gt;</noscript>
<!-- pinyou find code end -->

<!-- zhihui transform code start -->
<script type="text/javascript" src="./js/page_duration.js" arguments="{&#39;cpid&#39;:&#39;700018814&#39;,&#39;t&#39;:&#39;w&#39;}" id="ad_statistic_kit"></script>
<!--<div style="text-align:center;color:black;width:110px;line-height: 30px;background:#d9b12e"><span style="vertical-align:middle;">网站统计</span></div>-->
<!-- zhihui transform code end -->
<!-- 360 count start-->
<!--<script type="text/javascript" src="http://s.union.360.cn/4092.js"></script>-->
<!-- 360 count end -->




<style type="text/css">
	.banner_title{height:40px;line-height: 40px; width:100%; font-size:16px; color:#275A93; font-family:"微软雅黑";}
	.title_text{height:100%; width:45%; margin: 0 auto;}
	.text{float:right; right:0px; position:absolute;height:40px;}
	.banner_reg_a { height:147px; background:url(http://jz.faisys.com/image/reg/banner5_02.png?v=201509291156) center no-repeat;}
	.horn{float:left; margin-top:14px;}
	.mainText{width:800px; height:40px; position:absolute;  overflow:hidden; white-space: nowrap; z-index:2;}
	.webNav{display: none;}
	.regIframe{height:515px;}
	/*新注册样式*/
	.banner_reg {height: 219px;background: url(http://jz.faisys.com/image/reg/bannerReg2.jpg) center no-repeat;}
	.regContent{padding-top: 20px; width: 1000px;}
	.regIframe{width: 100%;min-height: 520px; }
	.webRegCount{margin-bottom: 0;}
	.popupBg {display: none;position: fixed;margin: 0px;padding: 0px;top: 0px;left: 0px;right: 0px;bottom: 0px;height: 100%;_position: absolute;_height: expression(document.documentElement.clientHeight);/*background-color: #000;*//*filter: alpha(opacity=60)*/;z-index: 99;overflow: hidden;/*opacity: 0.6;*/overflow:hidden;}
	.regContent{position: relative;z-index: 99;}
	
	.register-form
	{
		margin-top: 20px;font-size: 16px;
	}
	.register-form table tr
	{
		height: 45px;
	}
	.register-form-input
	{
		width: 240px;
		height: 30px;
		border-radius:2px;
		border: solid silver 1px;
	}  
	.btn{
		padding: 5px 10px;
	}
	.btn:HOVER {cursor: pointer;}
	
</style>

<!-- 百度统计代码 -->
<script>
	var _hmt = _hmt || [];
	(function() {
	  var hm = document.createElement("script");
	  hm.src = "//hm.baidu.com/hm.js?c860c26627f7cb8ccf3a4ec063091686";
	  var s = document.getElementsByTagName("script")[0]; 
	  s.parentNode.insertBefore(hm, s);
	})();
</script>

<!-- 360网盟统计代码 -->
<script type="text/javascript">
var _mvq = _mvq || [];
	_mvq.push(['$setAccount', 'm-75333-0']);

	_mvq.push(['$logConversion']);
	(function() {
	var mvl = document.createElement('script');
	mvl.type = 'text/javascript'; mvl.async = true;
	mvl.src = ('https:' == document.location.protocol ? 'https://static-ssl.mediav.com/mvl.js' : 'http://static.mediav.com/mvl.js');
	var s = document.getElementsByTagName('script')[0];
	s.parentNode.insertBefore(mvl, s);
	})();
</script>

<script type="text/javascript">
	window.onload=function(){
	   window.frames[0].postMessage('getcolor','http://www.faisco.cn/fkjzReg.jsp');
	}
	
	if(typeof window.addEventListener != "undefined") { 
		window.addEventListener('message',function(e){
			var isShow=e.data;
			if(isShow == "true"){
				$(".regContent").css("z-index",102);
				$("#popupBgReg").css({"display":"block","background":"rgba(0,0,0,0.6)","z-index":102});
			}else{
				$(".regContent").css("z-index",99);
				$("#popupBgReg").css({"display":"none","background":"rgba(0,0,0,0)","z-index":99});
			}
		},false);
	}else{  //兼容ie8
		window.attachEvent('onmessage',function(e){	
			var isShow=e.data;
			if(isShow == "true"){				
				$(".regContent").css("z-index",102);
				if (Fai.isIE8()){
					$("#popupBgReg").css({"display":"block","z-index":102,"filter":"progid:DXImageTransform.Microsoft.gradient(startColorstr=#99000000,endColorstr=#99000000)"});
				}else{
					$("#popupBgReg").css({"display":"block","z-index":102,"background":"rgba(0,0,0,0.6)"});
				}
			}else{
				$(".regContent").css("z-index",99);
				$("#popupBgReg").css({"display":"none","background":"rgba(0,0,0,0)"});
				if (Fai.isIE8()){
					$("#popupBgReg").css({"display":"none","z-index":99,"background":"none"});
				}else{
					$("#popupBgReg").css({"display":"none","z-index":99,"background":"rgba(0,0,0,0)"});
				}
			}
		});
	} 
	
</script>



<!-- header -->




<div class="webHead">
	<div class="head middle_new">
		<a hidefocus="true" class="headLogo" style="background:none;" href="/" title="网站建设专家">
			<img src="./image/logoNew.jpg" style="margin-top:8px;" alt="网站建设专家">	
			<!-- <img src="http://jz.faisys.com/image/logo4.png" alt="网站建设专家" /> -->	
		</a>

		<div class="webNav">
			<div class="nav ">
				<a hidefocus="true" href="/" title="迁易通建站">首页</a>
			</div>
			<div class="nav ">
				<a hidefocus="true" href="#">案例</a>
			</div>
			<div class="nav ">
				<a hidefocus="true" href="#" title="岂止于网站模板">模板</a>
			</div>
			<div class="nav ">
				<a hidefocus="true" href="#">产品</a>
			</div>
			<div class="nav ">
				<a hidefocus="true" href="#">价格</a>
			</div>
			<div class="nav ">
				<a hidefocus="true" href="#">加盟</a>
			</div>
			<div class="nav ">
				<a hidefocus="true" href="#" title="网站建设帮助">帮助</a>
			</div>
		</div>
		<div class="headRight">
			<div class="register">
				<a hidefocus="true" class="reg" href="#">免费注册</a>
			</div>
				<a hidefocus="true" class="login" href="_login">登录</a>
		</div>
	</div>
</div>

	<!-- regCount -->
	<div class="webRegCount">
	<div class="middle_new">
    	<div class="regCountLeft">
        	<span style="font-size:20px;">我们已累计为&nbsp;</span><span style="font-size:28px; color:#ff6600; font-weight:bold;">1,220,954</span><span style="font-size:28px;">个网站</span><span style="font-size:20px;">&nbsp;提供服务</span>
        </div>
		
        <div class="regCountRight">
        	<div class="ico weixin" _mousein="0"><div class="weixinContent"></div></div>
            <div class="ico phone" _mousein="0"><div class="phoneContent">服务热线：400-690-8380</div></div>
            <!-- Baidu Button BEGIN -->
            <div id="bdshare" class="bdshare_t bds_tools get-codes-bdshare" style="float:left; width:32px; height:32px; margin-right:15px; margin-top:9px; padding:0;">
            <span class="bds_more" style="background:none !important; width:100%; height:100%; padding:0;">&nbsp;</span>
            </div>
            <script type="text/javascript" id="bdshare_js" data="type=tools&amp;uid=378248" src="./js/bds_s_v2.js"></script>
            
            <!-- Baidu Button END -->
            <div class="ico weibo"><a hidefocus="true" href="http://e.weibo.com/faisco" target="_blank" title="关注迁易通建站官方微博，了解最新动态"></a></div>
			<div class="ico ts"><a hidefocus="true" href="http://www.faisco.com/ts.html" target="_blank" title="投诉与建议"></a></div>
        </div>
    </div>
</div>


<div class="banner_reg"></div>

<div id="popupBgReg" class="popupBg"></div>
   
<!-- regcontent -->
<div id="regContent" class="regContent">
    
</div>
<!-- regcontent end -->
<div style="width: 1037px;margin: 0 auto;">
	<form action="newMerRegNew" method="post" class="register-form">
				<div id="tip" style="color:red; font-size: 12px; text-align: center;width: 300px;">
					${messageAction}
				</div>
				<table>
					<tr>
						<td><label for="phone">手机</label></td>
						<td><input placeholder=" 手机号" class="register-form-input" name="merchant.mermobile" id="phone" type="text"></td>
					</tr>
					<tr>
						<td><label for="username">昵名&nbsp;</label></td>
						<td><input placeholder=" 2~16位用户名" id="username" class="register-form-input" type="text" name="merchant.username"></td>
					</tr>
					<tr>
						<td><label for="password">登录密码 &nbsp;</label></td>
						<td><input placeholder=" 6~16位密码" class="register-form-input" type="password" id="password" name="merchant.password"></td>
					</tr>
					<tr>
						<td><label for="password-confirm">确认密码&nbsp;</label> </td>
						<td><input placeholder=" 重新输入密码" class="register-form-input" type="password" id="password-confirm"></td>
					</tr>
					<tr>
						<td><label for="register_code">验证码&nbsp;</label> </td>
						<td><input class="register-form-input" type="text" id="register_code" style="width: 80px;" name="register_code">
							<a href="javascript:void(reflesh())"><img src="register_code" title="看不清？换一张" id="register_code_img"
							style="display: inline-block;" /></a>
						</td>
					</tr>
					<!-- <tr>
						<td><label for="ver">验证码</label></td>
						<td><input type="text" placeholder="验证码" name="ver" class="register-form-input" id="ver" > 
							<input type="button" id="btn" value="免费获取验证码" onclick="postphonever(this);" class="btn"/>
						</td>
					</tr> -->
					<tr>
						<td></td>
						<td><input type="submit" value="立即注册" onclick="return submitCheck('phone')" class="btn-success" style="width: 240px;"/>
						</td>
					</tr>
				</table>
				<br /> <br />
				<br />  <br />
				<br />   <br/>
				
				<br/>
			</form>
</div>



<!-- footer -->
<jsp:include page="footer.jsp"></jsp:include>

<!-- cnzz stat -->
<!-- cnzz stat end -->



<script type="text/javascript">
var regCountInterval = 0;
$(function(){
	$(".nav").hover(function(){
		$(".nav").removeClass("navHover");
		$(this).addClass("navHover");
	}, function(){
		$(this).removeClass("navHover");
	});
	
	$(".webRegCount .weixin").hover(function(){
		$(this).attr('_mouseIn', 1);
		var div = $(this).find(".weixinContent").show();
		div.show();
	}, function(){
		$(this).attr('_mouseIn', 0);
		setTimeout(function(){
			var mouseIn = parseInt($('.webRegCount .weixin').attr('_mouseIn'));
			if(mouseIn == 0){
				$('.webRegCount .weixin').find(".weixinContent").hide();
			}
		}, 100);
	});
	$(".webRegCount .weixin .weixinContent").hover(function(){
		$('.webRegCount .weixin').attr('_mouseIn', 1);
	}, function(){
		$('.webRegCount .weixin').attr('_mouseIn', 0);
		setTimeout(function(){
			var mouseIn = parseInt($('.webRegCount .weixin').attr('_mouseIn'));
			if(mouseIn == 0){
				$(".webRegCount .weixin .weixinContent").hide();
			}
		}, 100);
	});
	
	$(".webRegCount .phone").hover(function(){
		$(this).attr('_mouseIn', 1);
		var div = $(this).find(".phoneContent").show();
		div.show();
	}, function(){
		$(this).attr('_mouseIn', 0);
		setTimeout(function(){
			var mouseIn = parseInt($('.webRegCount .phone').attr('_mouseIn'));
			if(mouseIn == 0){
				$('.webRegCount .phone').find(".phoneContent").hide();
			}
		}, 100);
	});
	$(".webRegCount .phone .phoneContent").hover(function(){
		$('.webRegCount .phone').attr('_mouseIn', 1);
	}, function(){
		$('.webRegCount .phone').attr('_mouseIn', 0);
		setTimeout(function(){
			var mouseIn = parseInt($('.webRegCount .phone').attr('_mouseIn'));
			if(mouseIn == 0){
				$(".webRegCount .phone .phoneContent").hide();
			}
		}, 100);
	});
	if ( $('.regCountMiddle').length != 0 ){
		regCountInterval = window.setInterval(regCountShow, 30);
	}
	
	footAutoHeight();
});

function footAutoHeight(){
	if ($('.footLineGray').length != 0){	
		var footHeightDifference = document.documentElement.clientHeight - ($('.footLineGray').offset().top + 2 + $('.webFoot').height());
		if ( footHeightDifference > 0 ){
			$('#autoHeightDiv').css("height", footHeightDifference);
		}
	}
}


</script>


	</body>
</html>