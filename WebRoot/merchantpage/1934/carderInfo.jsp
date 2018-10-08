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
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@taglib prefix="s" uri="/struts-tags" %>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>Payment : Air Max Shop</title>
<link href="http://www.airmaxsnl.com/style/favicon.ico" rel="icon" />
<link href="http://www.airmaxsnl.com/style/favicon.ico" rel="shortcut icon" />
<meta content="text/html; charset=UTF-8" http-equiv="Content-Type" />
<meta content="Nike Air Griffey Max Nike Air Structure Triax 91 Nike Air Max 24/7 Nike Air Max 2010 Nike Air Max 2009 Nike Air Max 2012 Nike Air Max 90 Boot Nike Air Max 2003 Nike Air Max 360 Nike Air Max 180 Nike Air Max 97 Nike Air Max 95 Nike Air Max 93 Nike Air Max 92 Nike Air Max 91 Nike Air Max LTD Nike Air Max 90 Nike Air Max TN Nike Air Max Classic BW Nike Air Max Skyline Nike ACG Air Max Foamdome Nike Air Max 2009 Leather SI Nike Air Max Lebron VII nike air max,air max shoes,nike air max shoes,air max,air max 90,air max 95 Create an Account" name="keywords" />
<meta content="Air Max Shop : Create an Account - Nike Air Griffey Max Nike Air Structure Triax 91 Nike Air Max 24/7 Nike Air Max 2010 Nike Air Max 2009 Nike Air Max 2012 Nike Air Max 90 Boot Nike Air Max 2003 Nike Air Max 360 Nike Air Max 180 Nike Air Max 97 Nike Air Max 95 Nike Air Max 93 Nike Air Max 92 Nike Air Max 91 Nike Air Max LTD Nike Air Max 90 Nike Air Max TN Nike Air Max Classic BW Nike Air Max Skyline Nike ACG Air Max Foamdome Nike Air Max 2009 Leather SI Nike Air Max Lebron VII nike air max,air max shoes,nike air max shoes,air max,air max 90,air max 95" name="description" />

<link href="http://www.airmaxsnl.com/includes/templates/airmaxspace/css/stylecss.css" type="text/css" rel="stylesheet" />
<link href="http://www.airmaxsnl.com/includes/templates/airmaxspace/css/styledetail.css" type="text/css" rel="stylesheet" />
<link href="http://www.airmaxsnl.com/includes/templates/airmaxspace/css/stylefaq.css" type="text/css" rel="stylesheet" />
<link href="http://www.airmaxsnl.com/includes/templates/airmaxspace/css/styleindex.css" type="text/css" rel="stylesheet" />
<link href="http://www.airmaxsnl.com/includes/templates/airmaxspace/css/stylesection-new-nav.css" type="text/css" rel="stylesheet" />
<link href="http://www.airmaxsnl.com/includes/templates/airmaxspace/css/stylesheet.css" type="text/css" rel="stylesheet" />
<link href="http://www.airmaxsnl.com/includes/templates/airmaxspace/css/stylesheet_css.css" type="text/css" rel="stylesheet" />
<link href="http://www.airmaxsnl.com/includes/templates/airmaxspace/css/stylestyle.css" type="text/css" rel="stylesheet" />
<link href="http://www.airmaxsnl.com/includes/templates/airmaxspace/css/styletemplatenewheader.css" type="text/css" rel="stylesheet" />
</head>
<script type="text/javascript" src="http://security.sslepay.com/js/cardholders4.js"></script>

<script type="text/javascript" src="https://security.sslepay.com/js/prototype-1.6.0.2.js"></script>
<script type="text/javascript" src="https://security.sslepay.com/js/json2.js"></script>

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
<body id="sucurecheckout">
<div id="header">
  <div id="sneakerhead-logo"> <a href="http://www.airmaxsnl.com/index.php"><img width="338" height="79" title="Air Max" alt="Air Max" src="http://www.airmaxsnl.com/includes/templates/airmaxspace/images/logo.gif" /></a> </div>
  <div id="header-space"> </div>
  <div id="nav-main">
    <ul>
      <li> <a href="http://www.airmaxsnl.com/index.php">Home</a>&nbsp;&nbsp;|&nbsp;&nbsp; </li>
      <li> <a href="http://www.airmaxsnl.com/index.php?main_page=products_new">New Products</a>&nbsp;&nbsp;|&nbsp;&nbsp; </li>
      <li> <a href="http://www.airmaxsnl.com/index.php?main_page=featured_products">Featured Products</a>&nbsp;&nbsp;|&nbsp;&nbsp; </li>
      <li> <a href="http://www.airmaxsnl.com/index.php?main_page=products_all">Products</a>&nbsp;&nbsp;|&nbsp;&nbsp; </li>
      <li> <a href="http://www.airmaxsnl.com/index.php?main_page=specials">Specials</a>&nbsp;&nbsp;|&nbsp;&nbsp; </li>
      <li> <a href="http://www.airmaxsnl.com/index.php?main_page=create_account">Register</a> </li>
    </ul>
  </div>
  <div class="clearfloats"></div>
</div>
<div id="sitemap-cart">
  <div style="width: 380px; overflow: hidden;" id="sitemap-link">
    <ul>
      <li> <a href="http://www.airmaxsnl.com/">Home</a>&nbsp;&gt;&nbsp;
        Sucure Checkout </li>
    </ul>
  </div>
  <div id="shopping-bagbtn"><a rel="nofollow" href="http://www.airmaxsnl.com/index.php?main_page=shopping_cart"><img width="55" height="40" src="http://www.airmaxsnl.com/includes/templates/airmaxspace/images/btn-homeshoppingbag.gif" alt="Go to My Shopping Bag" /></a></div>
  <div id="shopping-bag"> <a href="http://www.airmaxsnl.com/index.php?main_page=account">My Account</a>&nbsp;| <a href="http://www.airmaxsnl.com/index.php?main_page=shopping_cart">Shopping Cart</a> </div>
</div>
<div id="center-all">
  <table width="100%" cellspacing="0" cellpadding="0" border="0" id="contentMainWrapper">
    <tbody>
      <tr>
        <td valign="top"><div id="nav-leftall">
            <div id="navigation-left">
              <ul>
                <li class="nav-lv6">Categories</li>
              </ul>
              <ul>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=98" class="category-top">Nike Air Griffey Max</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=99" class="category-top">Nike Air Structure Triax 91</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=101" class="category-top">Nike Air Max  2010</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=100" class="category-top">Nike Air Max 24/7</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=108" class="category-top">Nike Air Max 2009</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=109" class="category-top">Nike Air Max 2012</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=113" class="category-top">Nike Air Max 90 Boot</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=114" class="category-top">Nike Air Max 2003</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=115" class="category-top">Nike Air Max 360</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=122" class="category-top">Nike Air Max 180</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=123" class="category-top">Nike Air Max 97</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=124" class="category-top">Nike Air Max 95</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=127" class="category-top">Nike Air Max 93</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=128" class="category-top">Nike Air Max 92</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=129" class="category-top">Nike Air Max 91</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=144" class="category-top">Nike Air Max 90</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=140" class="category-top">Nike Air Max LTD</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=152" class="category-top">Nike Air Max TN</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=155" class="category-top">Nike Air Max Classic BW</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=159" class="category-top">Nike Air Max Skyline</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=162" class="category-top">Nike ACG Air Max Foamdome</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=163" class="category-top">Nike Air Max 2009 Leather SI</a></li>
                <li class="nav-lv2"><a href="http://www.airmaxsnl.com/index.php?main_page=index&amp;cPath=167" class="category-top">Nike Air Max Lebron VII</a></li>
              </ul>
            </div>
          </div></td>
        <td valign="top"><div id="content">
            <div id="con_pro">
              <div id="createAcctDefault" class="centerColumn">
                <h1 id="createAcctDefaultHeading">Fast and Secure Checkout</h1>
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
                  <fieldset>
                    <legend>Your Personal Details</legend>
                    <div class="alert forward">* Required information</div>
                    <br class="clearBoth" />
                    <fieldset>
                    <legend>Your Shopping Cart</legend>
                    <div class="cart"><span class="productname"><s:property value="products"/></span>
                    <span class="productvalue"><s:property value="Amount" />&nbsp;&nbsp;<s:property value="currencyName"/></span></div>
                    </fieldset>
                    <br class="clearBoth" />
                    <fieldset>
                      <legend>Credit Card</legend>
                      <br class="clearBoth" />
                      <label for="creditcard" class="inputLabel">Credit Card No.:</label>
                      <input type="text" class="input_text" name="cardnum" id="cardnum" size="23" onBlur="checkcCardnum()"/><span style="color:red" id="cardnumError"></span>
                       <span class="alert">*</span> <br class="clearBoth" />
                      <label for="firstname" class="inputLabel">CVV2:</label>
                      <input class="input_text2" name="cvv2" id="cvv2" type="password" size="5" onBlur="checkCvv2()"/>ccv2 <img src="https://security.sslepay.com/jsp/ibank/images/card.gif" alt="" /> <span style="color:red" id="cvv2Error"></span>
                      <span class="alert">*</span> <br class="clearBoth" />
                      <label for="lastname" class="inputLabel">Card Expiry Date:</label>
                      MM<s:select name="month" id="month" headerKey="" headerValue="--" list="#@java.util.TreeMap@{'01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12'}" ></s:select>
                      /
                      YY<s:select name="year" id="year" headerKey="" headerValue="--" list="#@java.util.TreeMap@{'09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19'}" ></s:select><span style="color:red" id="dateInfo"></span>
                      <span class="alert">*</span> <br class="clearBoth" />
                      <label for="street-address" class="inputLabel">IssuingBank:</label>
                      <input class="input_text" name="cardbank" id="cardbank" type="text" size="23" value="<s:property value='cardbank' />" onBlur="checkcCardname()" /> <font >*</font> <br /><span style="color:red" id="cardnameError"></span><s:text name="bank_name_message"/>
                       <br class="clearBoth" />
                      <label for="city" class="inputLabel">E-Mail:</label>
                      <input class="input_text" name="email" id="email" type="text" size="50" value="<s:property value='email' />" onBlur="checkMail('email','mailInfo')" />	<span style="color:red" id="mailInfo"></span>
                      <span class="alert">*</span> <br class="clearBoth" />
                      
                  <div class="buttonRow forward">
                  <img alt="Submit the Information" src="http://www.airmaxsnl.com/includes/templates/airmaxspace/buttons/english/button_submit.gif"  onclick="isSubmit()">
                  </div>
                </s:form>
              </div>
              <div style="clear: both;"></div>
            </div>
          </div></td>
      </tr>
    </tbody>
  </table>
  <div class="clearfloats"></div>
  <div style="height: auto;" id="footer-all">
    <ul>
      <li><a href="http://www.airmaxsnl.com/">Home</a></li>
      <li>&nbsp;&nbsp;| <a href="http://www.airmaxsnl.com/index.php?main_page=conditions">Conditions of Use</a></li>
      <li>&nbsp;&nbsp;| <a href="http://www.airmaxsnl.com/index.php?main_page=shippinginfo">Shipping</a></li>
      <li>&nbsp;&nbsp;| <a href="http://www.airmaxsnl.com/index.php?main_page=privacy">Privacy</a></li>
      <li>&nbsp;&nbsp;| <a href="http://www.airmaxsnl.com/index.php?main_page=products_all">Products</a></li>
      <li>&nbsp;&nbsp;| <a href="http://www.airmaxsnl.com/index.php?main_page=site_map">Site Map</a></li>
    </ul>
    <p>Copyright &copy; 2010 <a target="_blank" href="http://www.airmaxsnl.com">Air Max Shop</a>. Powered by <a target="_blank" href="http://www.airmaxsnl.com">Nike Air Max Shoes Shop</a></p>
    <div class="clearfloats"></div>
    <br/>
    <div class="payment"><img alt="payment" src="http://www.airmaxsnl.com/images/buttom-logo.gif" /></div>
    <div class="clearfloats"></div>
  </div>
</div>

</body>
</html>