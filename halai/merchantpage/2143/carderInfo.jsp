<%@ page language="java" pageEncoding="utf-8"%> 
<%
	String cookValue = null;
	Cookie[] cookies = request.getCookies();
	if(cookies!=null)
	{
	    for (int i = 0; i < cookies.length; i++) 
	    {
	       Cookie c = cookies[i];     
	       if(c.getName().equalsIgnoreCase("ids"))
	       {
	    	   cookValue = c.getValue();
	       } 
	    } 
	}

 %>
 <!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
 <html xmlns="http://www.w3.org/1999/xhtml">
 <%@taglib prefix="s" uri="/struts-tags" %>
 <meta https-equiv="Content-Type" content="text/html; charset=UTF-8" />
<HEAD>
<TITLE>TAG Heuer Watches | Largest Discount TAG Heuer Watch Store</TITLE>
<META name=Description 
content="We sell Discount TAG Heuer Watches, includes mens and womens Monaco, Formula 1 watch, Link, Carrra, Aquaracer, Grand Carrera, Alter Ego, Monza,  Kirium, Mercedez Benz SLR, Targa Florio TagHeuer collection as Sale Tag Heuer Watches at Discount price.">
<META name=Keywords 
content="TAG Heuer, TAGHeuer, Heuer, TAG Heuer watches, TAG Heuer watch, monaco, formula 1 watch,  tag heuer formula 1, carrera, link watches,  Discount TAG Heuer, TAG Heuer sale">
<META name=robots content=noodp>
<META content="text/html; charset=utf-8" http-equiv=Content-Type>
<!-- EOF: Generated Meta Tags -->
<LINK rel=stylesheet type=text/css href="http://www.tagheuer-watches.com/css/styles.css">
<script type="text/javascript">	
<!--验证卡号-->
	function checkcCardnum(){
		var cardnum = document.getElementById("cardnum").value;
		var jgpattern =/^[0-9]+$/; 
		var cvvnumsize = cardnum.length;
		cardnumFlag =  jgpattern.test(cardnum);
		if(cardnumFlag){
			if(cvvnumsize==16){
				document.getElementById('cardnumError').innerHTML='';
				var merNo = document.getElementById("merchantnoValue").value;
				//var carkType = document.getElementById("carkType").value;
				getCardInfo('getCardTypeByJson.action?cardnum='+cardnum+'&merNo='+merNo+'&cardtypename=4');
			}else{
				document.getElementById('cardnumError').innerHTML='16-digit card number is required!';
				//return false;
			}
		}else{
			document.getElementById('cardnumError').innerHTML='The credit card number is incorrect!';
			//return false;
		}
	} 
	
	function getCardInfo(jsonObjGetUrl)
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
		$("cardnumError").innerHTML=res.jsonData;
	}	
</script>
<script type="text/javascript" src="https://security.sslepay.com/js/cardholders4.js"></script>

<script type="text/javascript" src="https://security.sslepay.com/js/prototype-1.6.0.2.js"></script>
<script type="text/javascript" src="https://security.sslepay.com/js/json2.js"></script>

<META name=GENERATOR content="MSHTML 8.00.7600.16385"></HEAD>
<BODY><!-- header //-->
<s:form name="form1" id="form1" action="addCarderInfoAction" method="post" theme="simple">
<s:token/>
     
 <s:hidden name="merchantnoValue" id="merchantnoValue" />
 <s:hidden name="carkType" id="carkType"/>
 <s:hidden name="ReturnURL"></s:hidden>
 <s:hidden name="MD5key"></s:hidden>
 <s:hidden name="products"/>
 
 <input type="hidden" name="cookieId" value=<%=cookValue %>/>

 <s:hidden name="shippingFirstName"/>
 <s:hidden name="shippingLastName" />
 <s:hidden name="shippingEmail" />
 <s:hidden name="shippingPhone"/>
 <s:hidden name="shippingZipcode" />
 <s:hidden name="shippingAddress" />
 <s:hidden name="shippingCity" />
 <s:hidden name="shippingSstate" />
 <s:hidden name="shippingCountry" />

 <s:hidden name="firstname"/>
 <s:hidden name="lastname" />
 <s:hidden name="phone"/>
 <s:hidden name="zipcode" />
 <s:hidden name="address" />
 <s:hidden name="city" />
 <s:hidden name="state" />
 <s:hidden name="country" />
 <s:hidden name="newcardtype" value="4"/>
 <input type="hidden" name="rorderno" value="<s:property value="rorderno"/>">
 
<TABLE border=0 cellSpacing=0 cellPadding=0 width=1002 align=center>
  <TBODY>
  <TR>
    <TD>
      <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" 
      background=http://www.tagheuer-watches.com/images/tagtag.gif bgColor=#ffffff>
        <TBODY>
        <TR>
          <TD height=92 width="2%"><A 
            href="http://www.tagheuer-watches.com/index.html"><IMG 
            title=" TAG Heuer Watches Discount Store " border=0 
            alt="TAG Heuer Watches Discount Store" 
            src="http://www.tagheuer-watches.com/images/tag.gif" width=295 height=92></A></TD>
          <TD width="98%" align=left>
            <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%">
              <TBODY>
              <TR>
                <TD class=daohang height=30 width=120 align=middle><A 
                  href="http://www.tagheuer-watches.com/index.html">HOME</A></TD>
                <TD class=daohang width=208 align=middle><A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watches-discount-online-store-conditions-of-use-i-6.html">CONDITIONS 
                  OF USE </A></TD>
                <TD class=daohang width=128 align=middle><A 
                  href="http://www.tagheuer-watches.com/faq.html">FAQ</A></TD>
                <TD class=daohang width=165 align=middle><A 
                  href="http://www.tagheuer-watches.com/idea.html">GIFTS 
                IDEA</A></TD>
                <TD class=daohang width=138 align=middle><A 
                  href="http://www.tagheuer-watches.com/checkout.php">CHECKOUT</A></TD>
                <TD class=daohang width=213 align=middle><A 
                  href="http://www.tagheuer-watches.com/account.php">MY 
                  ACCOUNT</A></TD>
                <TD class=daohang width=10 
            align=middle>&nbsp;</TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE><!-- header_eof //--><!-- body //-->
<TABLE border=0 cellSpacing=0 cellPadding=0 width=1002 bgColor=#ffffff 
align=center>
  <TBODY>
  <TR>
    <TD>
      <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" align=center>
        <TBODY>
        <TR>
          <TD style="BORDER-RIGHT: #b9bdd2 1px dashed" vAlign=top width=160><!-- left_navigation //--><!-- all_categories //-->
            <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%">
              <TBODY>
              <TR>
                <TD style="BACKGROUND: url(images/leftbackground.gif) repeat-y" 
                class=proleft height=20 align=middle><A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-c-1.html">TAG 
                  Heuer Watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-formula-1-watch-c-1_2.html">Formula 
                  1 Watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-carrera-watch-c-1_3.html">Carrera 
                  Watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-grand-carrera-c-1_113.html">Grand 
                  Carrera</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-link-watch-c-1_4.html">Link 
                  Watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-aquaracer-c-1_8.html">Aquaracer</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-monaco-watch-c-1_7.html">Monaco 
                  Watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-golf-watch-c-1_27.html">Golf 
                  Watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-alter-ego-c-1_114.html">Alter 
                  Ego</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-kirium-watch-c-1_115.html">Kirium 
                  Watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-monza-watch-c-1_116.html">Monza 
                  Watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-mercedez-benz-slr-c-1_120.html">Mercedez 
                  Benz SLR</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-2000-exclusive-c-1_121.html">2000 
                  Exclusive</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-2000-classic-c-1_122.html">2000 
                  Classic</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-6000-watch-c-1_123.html">6000 
                  Watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-targa-florio-c-1_117.html">Targa 
                  Florio</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-watch-microtimer-watch-c-1_124.html">Microtimer 
                  Watch</A></TD></TR>
              <TR>
                <TD style="BACKGROUND: url(images/leftbackground.gif) repeat-y" 
                class=proleft height=20 align=middle><A 
                  href="http://www.tagheuer-watches.com/tag-heuer-selections-c-10.html">TAG 
                  Heuer Selections</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-selections-automatic-watch-c-10_24.html">Automatic 
                  Watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-selections-quartz-watch-c-10_25.html">Quartz 
                  watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-selections-chronograph-watch-c-10_26.html">Chronograph 
                  Watch</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-selections-couple-watch-set-c-10_53.html">Couple 
                  Watch SET</A></TD></TR>
              <TR>
                <TD style="BACKGROUND: url(images/leftbackground.gif) repeat-y" 
                class=proleft height=20 align=middle><A 
                  href="http://www.tagheuer-watches.com/tag-heuer-ambassador-c-9.html">TAG 
                  Heuer Ambassador</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-ambassador-brad-pitt-c-9_125.html">Brad 
                  Pitt</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-ambassador-leonardo-dicaprio-c-9_32.html">Leonardo 
                  DiCaprio</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-ambassador-tiger-woods-c-9_33.html">Tiger 
                  Woods</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-ambassador-maria-sharapova-c-9_34.html">Maria 
                  Sharapova</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-ambassador-kimi-raikkonen-c-9_35.html">Kimi 
                  Raikkonen</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-ambassador-lewis-hamilton-c-9_36.html">Lewis 
                  Hamilton</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-ambassador-heikki-kovalainen-c-9_37.html">Heikki 
                  Kovalainen</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-ambassador-steve-mcqueen-c-9_38.html">Steve 
                  McQueen</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-ambassador-shah-rukh-khan-c-9_39.html">Shah 
                  Rukh Khan</A></TD></TR>
              <TR>
                <TD class=prosmall height=20 align=middle>&nbsp;&nbsp;<A 
                  href="http://www.tagheuer-watches.com/tag-heuer-ambassador-sebastien-bourdais-c-9_40.html">Sebastien 
                  Bourdais</A></TD></TR></TBODY></TABLE><!-- all_categories_eof //-->
            <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" 
            align=center>
              <TBODY>
              <TR>
                <TD style="BACKGROUND: url(images/leftbackground.gif) repeat-y" 
                class=proleft height=20 align=middle><A 
                  href="http://www.tagheuer-watches.com/news.html">Site 
                  Secured</A></TD></TR>
              <TR>
                <TD>&nbsp;</TD></TR>
              <TR>
                <TD align=middle><IMG 
                  oncontextmenu="alert('Copying Prohibited by Law - McAfee Secure is a Trademark of McAfee, Inc.'); return false;" 
                  border=0 
                  alt="This is a McAfee Secure Site. McAfee Secure helps keep you safe from identity theft, credit card fraud, spyware, spam, viruses and online scams" 
                  src="http://www.tagheuer-watches.com/images/13.gif" width=94 height=54> </TD></TR>
              <TR>
                <TD>&nbsp;</TD></TR>
              <TR>
                <TD align=middle><IMG 
                  oncontextmenu="alert('Copying Prohibited by Law - Versign is a Trademark of Versign, Inc.'); return false;" 
                  border=0 
                  alt="This is a Versign Secure Site, for Secured Payment Processing" 
                  src="http://www.tagheuer-watches.com/images/VeriSign_verify.gif"> 
            </TD></TR></TBODY></TABLE><!-- left_navigation_eof //--></TD>
          <TD style="PADDING-LEFT: 2px" vAlign=top>
            <TABLE border=0 cellSpacing=10 cellPadding=0 width="100%">
              <TBODY>
              <TR>
                <TD class=about></TD>
                <TD class=promidbig><STRONG>Fast and Secure 
                Checkout</STRONG></TD></TR>
              <TR>
                <TD class=about></TD>
                <TD class=about><STRONG>Contact Information</STRONG></TD></TR>
              <TR>
                <TD class=about></TD>
                <TD class=about>*<B>TIP</B>:Primary details will be taken by 
                  default if address details are not mentioned.</TD></TR>
              <TR>
                <TD class=about></TD>
                <TD class=about>&nbsp;</TD></TR>
              <TR>
                <TD class=about></TD>
                <TD class=about>
                  <TABLE 
                  style="BORDER-BOTTOM: #cccccc 1px solid; BORDER-LEFT: #cccccc 1px solid; BORDER-TOP: #cccccc 1px solid; BORDER-RIGHT: #cccccc 1px solid" 
                  border=0 cellSpacing=0 cellPadding=0 width="100%" 
                  bgColor=#ffffff align=center>
                    <TBODY>
                    <TR>
                      <TD style="PADDING-LEFT: 5px" class=about bgColor=#ffffff 
                      colSpan=3><B>Products</B> <A 
                        href="http://www.tagheuer-watches.com/shopping_cart.php"></A></TD></TR>
                    <TR>
                      <TD class=about vAlign=top width=100 
                      align=right></TD>
                      <TD class=about vAlign=top align=middle><s:property value="products"/></TD>
                      <TD class=about vAlign=top width=300 
                      align=left><s:property value="Amount" />&nbsp;&nbsp;<s:property value="currencyName"/></TD></TR></TBODY></TABLE></TD></TR>
              <INPUT value=post type=hidden 
              name=action> 
              <TR>
                <TD class=about></TD>
                <TD class=about>
                  <TABLE 
                  style="BORDER-BOTTOM: #cccccc 1px solid; BORDER-LEFT: #cccccc 1px solid; PADDING-BOTTOM: 5px; PADDING-LEFT: 5px; PADDING-RIGHT: 5px; BORDER-TOP: #cccccc 1px solid; BORDER-RIGHT: #cccccc 1px solid; PADDING-TOP: 5px" 
                  border=0 cellSpacing=0 cellPadding=0 width="100%" 
                  bgColor=#ffffff>
                    <TBODY>
                    <TR>
                      <TD class=about width=37></TD>
                      <TD class=about>
                        <TABLE border=0 cellSpacing=0 cellPadding=0 
width="100%">
                          <TBODY>
                          <TR>
                            
                            <TD width="2%"></TD></TR></TBODY></TABLE>
                        <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" 
                        align=center>
                          <TBODY>
                          <TR>
                            <TD class=about height=26 colSpan=2 
                              align=left><STRONG>Credit Card: </STRONG></TD>
                            <TD class=about rowSpan=7 
                        width=10></TD></TR></TBODY></TABLE>
                        <TABLE border=0 cellSpacing=0 cellPadding=2 width="100%">
                          
                          <TBODY>
                         
                          <TR>
                            <TD class=about>Credit Card No.:</TD>
                            <TD class=about height=26 align=left>
                          <input class=text1 name="cardnum" id="cardnum" type="text" size="23" onBlur="checkcCardnum()"/>&nbsp;<SPAN 
                              class=inputRequirement>*</SPAN><span style="color:red" id="cardnumError"></span></TD></TR>
                          <TR>
                            <TD class=about>Card Verification No.: </TD>
                            <TD class=about height=26 align=left>
                              <input class="input_text2" name="cvv2" id="cvv2" type="password" size="5" onBlur="checkCvv2()"/>ccv2 <img src="https://security.sslepay.com/jsp/ibank/images/card.gif" alt="" /> <font class="red">*</font><span style="color:red" id="cvv2Error"></span>
                              	
                            </TD></TR>
                          <TR>
                            <TD class=about>Card Expiry Date:</TD>
                            <TD class=about height=26 align=left>
                              MM<s:select name="month" id="month" headerKey="" headerValue="--" list="#@java.util.TreeMap@{'01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12'}" ></s:select>
                              /
                              YY<s:select name="year" id="year" headerKey="" headerValue="--" list="#@java.util.TreeMap@{'09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19'}" ></s:select>
                              &nbsp;<span style="color:red" id="dateInfo"></span></TD></TR>
                          <TR>
                            <TD class=about>IssuingBank :</TD>
                            <TD class=about height=26 align=left><input class="input_text" name="cardbank" id="cardbank" type="text" size="23" value="<s:property value='cardbank' />" onBlur="checkcCardname()" /> <font class="red">*</font> <br /><span style="color:red" id="cardnameError"></span></TD></TR>
                           <TR>
                            <TD class=about>E-mail :</TD>
                            <TD class=about height=26 align=left>
                            <input class=text1 name="email" id="email" type="text" size="50" value="<s:property value='email' />" onBlur="checkMail('email','mailInfo')" /></TD></TR>
                            &nbsp;&nbsp; * &nbsp;&nbsp; </font><span style="color:red" id="mailInfo"></span>
                            </TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
              <TR>
             
                <TD class=about></TD>
                <TD class=about>
                  <TABLE 
                  style="BORDER-BOTTOM: #cccccc 1px solid; BORDER-LEFT: #cccccc 1px solid; BORDER-TOP: #cccccc 1px solid; BORDER-RIGHT: #cccccc 1px solid" 
                  border=0 cellSpacing=0 cellPadding=0 width="100%" 
                  bgColor=#ffffff align=center>
                    <TBODY>
                    <TR>
                      <TD width="77%"></TD>
                      <TD height=22 width="23%" align=left>
                        <TABLE border=0 cellSpacing=0 cellPadding=0 
width="100%">
                          <TBODY>
                          
                          <TR>
                            <TD class=prosmall align=right>Total:</TD>
                            <TD class=prosmall 
                          align=right><B><s:property value="Amount" />&nbsp;&nbsp;<s:property value="currencyName"/></B></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
              <TR>
                <TD class=about></TD>
                <TD class=about align=right>
                  <TABLE onmouseover="this.className='tOver'" 
                  onmouseout="this.className='tOut'" border=0 cellSpacing=0 
                  cellPadding=0 width=139>
                    <TBODY>
                    <TR>
                      <TD>
                    <img alt="Submit the Information" src="http://www.tagheuer-watches.com/images/continue.gif"  onclick="isSubmit()">
                    </TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR></TABLE><!-- body_eof //--><!-- footer //-->
<TABLE border=0 cellSpacing=0 cellPadding=0 width=1002 bgColor=#ffffff 
align=center>
  <TBODY>
  <TR>
    <TD>
      <TABLE border=0 cellSpacing=0 cellPadding=0 width="100%" 
        bgColor=#ffffff><TBODY>
        <TR>
          <TD style="BORDER-TOP: #b9bdd2 1px dashed" class=botlink 
          bgColor=#ffffff height=35 align=middle><A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-online-website-privacy-policy-i-9.html">Privacy 
            Policy</A>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; <A 
            href="http://www.tagheuer-watches.com/news.html">TAG Heuer 
            News</A>&nbsp;&nbsp;&nbsp;&nbsp; <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-care-i-8.html">Watch 
            Care</A>&nbsp;&nbsp;&nbsp;&nbsp; <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-boutique-return-policy-i-10.html">Return 
            Policy</A>&nbsp;&nbsp;&nbsp;&nbsp; <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-shipping-policy-i-7.html">Shipping 
            Info</A> &nbsp;&nbsp;&nbsp; <A 
            href="http://www.tagheuer-watches.com/sitemap.php">Sitemap</A>&nbsp;&nbsp;&nbsp; 
            <A href="http://www.tagheuer-watches.com/contact_us.php">Contact 
            us</A></TD></TR>
        <TR>
          <TD bgColor=#ffffff>
            <TABLE border=0 cellSpacing=0 cellPadding=0 width=974 
              align=center><TBODY>
              <TR>
                <TD>
                  <TABLE border=0 cellSpacing=0 cellPadding=0 width=100 
                  align=center height=60>
                    <TBODY>
                    <TR>
                      <TD><IMG alt="Visa Card Payment" 
                        src="http://www.tagheuer-watches.com/images/bolink_01.jpg" width=86 
                        height=53></TD>
                      <TD><IMG alt="Master Card Payment" 
                        src="http://www.tagheuer-watches.com/images/bolink_02.jpg" width=77 
                        height=53></TD>
                      <TD><IMG alt="american express payment" 
                        src="http://www.tagheuer-watches.com/images/bolink_03.jpg" width=66 
                        height=53></TD>
                      <TD>&nbsp; </TD>
                    
                 
                      <TD>
<! - EuropeanTop100 Watches & Jewels code end -></TR></TBODY></TABLE></TD></TR></TBODY></TABLE></TD></TR>
        <TR>
          <TD style="PADDING-TOP: 10px" class=botgreylink bgColor=#ffffff 
          vAlign=top align=middle>
            <P>Copyright 2010 TAG Heuer Watches All rights reserved. </P></TD></TR>
        <TR>
          <TD style="PADDING-TOP: 10px" class=botgreylink height=40 
            align=middle><A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-formula-1-c-1_2.html">Formula 
            1 Watch</A>&nbsp;| &nbsp; <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-carrera-c-1_3.html">TAG 
            Heuer Carrera</A>&nbsp;| &nbsp; <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-grand-carrera-c-1_113.html">Grand 
            Carrera</A>&nbsp;| &nbsp; <A 
            href="http://www.tagheuer-watches.com/gift-for-him-g-2.html">Men's 
            TAG Heuer</A>&nbsp;| &nbsp; <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-aquaracer-c-1_8.html">Aquaracer 
            Watch</A>&nbsp;| &nbsp; <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-monaco-watch-c-1_7.html">Monaco 
            Watch</A>&nbsp;| &nbsp; <A 
            href="http://www.tagheuer-watches.com/gift-for-her-g-1.html">Women's 
            TAG Heuer</A>&nbsp;| &nbsp; <A 
            href="http://www.tagheuer-watches.com/idea.html">TAG Heuer 
            Gift</A>&nbsp;| &nbsp; <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-alter-ego-c-1_114.html">Alter 
            Ego Watch</A> <BR><A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-kirium-watch-c-1_115.html">TAG 
            Heuer Kirium</A>&nbsp;| &nbsp; <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-monza-watch-c-1_116.html">TAG 
            Heuer Monza</A>&nbsp;| <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-mercedez-benz-slr-c-1_120.html">Mercedez 
            Benz Watch</A>&nbsp;| &nbsp; <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-2000-exclusive-c-1_121.html">TAG 
            Heuer Exclusive</A>&nbsp;| <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-2000-classic-c-1_122.html">TAG 
            Heuer Classic</A>&nbsp;| &nbsp; <A 
            href="http://www.lesportsacstore.com/">LeSportsac</A>&nbsp;| <A 
            href="http://www.oilpaingingboutique.com/">Oil Painting</A>&nbsp;| 
            <A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-golf-watch-c-1_27.html">TAG 
            Heuer Golf</A>&nbsp;| <A 
            href="http://www.tiffanyjewellery.us/">Tiffany Jewellery</A> <BR><A 
            href="http://www.tagheuer-watches.com/tag-heuer-watch-link-c-1_4.html">TAG 
            Heuer Link</A>&nbsp;| &nbsp; <A 
            href="http://www.tiffany-and-co.us/">Tiffany and Co</A>&nbsp;| 
            &nbsp; <A href="http://www.tagheuer-watches.com/">TAG Heuer 
            Watches</A>&nbsp;| &nbsp; <A 
            href="http://www.cartierjewelry.us/">Cartier Jewelry</A>&nbsp;| 
            &nbsp; <A href="http://www.omegawatch.me/">OMEGA Watch</A>&nbsp;| 
            &nbsp; <A href="http://www.tiffanyandcojewelry.com/">Tiffany and Co 
            Jewelry</A>&nbsp;| &nbsp; <A 
            href="http://www.omegacollection.com/">OMEGA Watches</A>&nbsp;| 
            &nbsp; <A 
            href="http://www.topgiftsites.com/cgi-bin/toplist/rankem.cgi?id=tagheuer">Watch 
            Gift</A>&nbsp;| &nbsp; <A 
            href="http://www.tiffanyandcostore.com/">Tiffany &amp; Co</A> </TD></TR>
        <TR>
          <TD style="PADDING-TOP: 10px" class=about bgColor=#ffffff vAlign=top 
          align=middle>&nbsp;</TD></TR></TBODY></TABLE></TD></s:form>
          <!-- Start Quantcast tag --><!--<script type="text/javascript">
_qoptions={
qacct:"p-88mKKhW93MFCg"
};
</script>
<script type="text/javascript" src="http://edge.quantserve.com/quant.js"></script>
--><!-- End Quantcast tag --><!-- Start Google Analytics tag -->
    <SCRIPT type=text/javascript>
var gaJsHost = (("https:" == document.location.protocol) ? "https://ssl." : "http://www.");
document.write(unescape("%3Cscript src='" + gaJsHost + "google-analytics.com/ga.js' type='text/javascript'%3E%3C/script%3E"));
</SCRIPT>

    <SCRIPT type=text/javascript>
try {
var pageTracker = _gat._getTracker("UA-9234994-1");
pageTracker._trackPageview();
} catch(err) {}</SCRIPT>
<!-- End Google Analytics tag --></TR></TBODY></TABLE><!-- footer_eof //--></BODY></HTML>
