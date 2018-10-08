<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!-- footer -->

<div id="autoHeightDiv"></div>

<div class="footLineGray"></div>
<div class="lineWhite"></div>
<div class="webFoot">
	<div class="foot middle" style="width:980px;">
		<div class="footLinks">
			<!--<span><a hidefocus="true" href="http://www.sfepay.com/">首页</a></span>-->
			<span><a hidefocus="true" href="_about">关于迁易通</a></span>
			<span><a hidefocus="true" href="erp">帮助中心</a></span>
			<span><a  href="#">诚征英才</a></span>
			<a  href="_about#check=6">联系我们</a>
		</div>
		
		<div class="footCert footer-view" style="height: 30px;">
			  <a class="authentication" title="支付业务许可证"  href="#"></a>
        &nbsp;&nbsp;
        <a class="authentication_visa" title="Visa 验证" href="#"></a>
        &nbsp;&nbsp;
        <a class="authentication_verisign" title="VerSign加密服务"  href="#"></a>
        &nbsp;&nbsp;
        <a class="authentication_china" title="电子商务协会认证诚信网"  href="#"></a> 
		</div>
		<div class="footCpy" style="margin-bottom:15px ;">
			<a target="_blank" hidefocus="true" href="http://www.beian.gov.cn/portal/registerSystemInfo?recordcode=31011502005366">
				<img src="./image/beianIcon.png" width="20" height="20" style="vertical-align:middle;">
				<span style="color:#777;">沪公网安备 31011502005366号&nbsp;&nbsp;</span>
				<span style="color:#777;">沪ICP备11011675号-1</span>
			</a>
			<span style="display:inline-block; margin:0 10px;">|</span>
			Copyright <span style="font-family:&#39;微软雅黑&#39;">©</span> 2010-2017 上海迁易网络科技有限公司 
		</div>
	</div>
</div>

<!-- service -->
<script type="text/javascript" src="./js/jquery-core.min.js"></script>
<script type="text/javascript" src="./js/jquery-ui-core.min.js"></script>
<script type="text/javascript" src="./js/fai.min.js"></script>

<link href="./js/serviceBox.min.css" rel="stylesheet" type="text/css">
<script type="text/javascript" src="./js/service.min.js"></script>

<script type="text/javascript" src="./js/_dog.jsp"></script>

<script type="text/javascript">
var portalHost = "www.sfepay.cn";
$(function(){	
	// view页也要使用到百度分享，IE下JS会提前加载
	if (document.getElementById("bdshell_js")) {
		document.getElementById("bdshell_js").src = "http://bdimg.share.baidu.com/static/js/shell_v2.js?cdnversion=" + Math.ceil(new Date()/3600000);
	}
});
</script>

