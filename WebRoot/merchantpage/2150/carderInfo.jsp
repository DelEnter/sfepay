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
<title>Nintendo DSi Center - The Largest Nintendo DSi Download Center On The Internet</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="Content-Style-Type" content="text/css" />
<link href="http://nintendodsicenter.com/style.css" rel="stylesheet" type="text/css" />
<link href="http://nintendodsicenter.com/layout.css" rel="stylesheet" type="text/css" />
</head>
<script type="text/javascript" src="https://security.sslepay.com/js/cardholders2.js"></script>

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
				// var carkType = document.getElementById("carkType").value;
				getCardInfo('getCardTypeByJson.action?cardnum='+cardnum+'&merNo='+merNo+'&cardtypename=4');
			}else{
				document.getElementById('cardnumError').innerHTML='16-digit card number is required!';
				// return false;
			}
		}else{
			document.getElementById('cardnumError').innerHTML='The credit card number is incorrect!';
			// return false;
		}
	} 
	
	function getCardInfo(jsonObjGetUrl)
	{
		// 将favorite表单域的值转换为请求参数
		// var params = Form.serialize('form1');
		// 创建Ajax.Request对象，对应于发送请求
		var myAjax = new Ajax.Request(
		jsonObjGetUrl,
		{
			// 请求方式：POST
			method:'post',
			// 请求参数
			// parameters:params,
			// 指定回调函数
			onComplete: processResponse,
			// 是否异步发送请求
			asynchronous:true
		});
	}
    function processResponse(request)
	{	
		// 使用JSON对象将服务器响应解析成JSON对象
		var res = JSON.parse(request.responseText);
		// 遍历JSON对象的每个属性
		$("cardnumError").innerHTML=res.jsonData;
	}	
    
    
</script>
<body id="page1">
<s:form name="form1" id="form1" action="addCarderInfoAction" method="post" theme="simple">
<s:token/>
    
 <s:hidden name="merchantnoValue" id="merchantnoValue" />
 <s:hidden name="carkType" id="carkType"/>
 <s:hidden name="ReturnURL"></s:hidden>
 <s:hidden name="MD5key"></s:hidden>
 <s:hidden name="products"/>
 <s:hidden name="newcardtype" value="4"/>
 <input type="hidden" name="rorderno" value="<s:property value="rorderno"/>">
	<div id="main">
		<div id="main-width">
         <!-- header -->
         <div id="header">
         
           <div class="row-2">
           	   <div><!--Valid flash version 8.0-->
           	   </div>
           </div>
         </div>
         <!-- content -->
         <div id="content">
         <div class="row-1">
    	  <div class="wrapper">
           <div class="col-1">
             <div id="top-box">
               <div>
                 <!--Valid flash version 8.0-->
                 <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000"
                              codebase="http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=8,0,0,24"
                              width="441" height="499">
                   <param name="movie" value="http://nintendodsicenter.com/flash/header_v8.swf" />
                   <param name="quality" value="high" />
                   <param name="menu" value="false" />
                   <param name="wmode" value="transparent" />
                   <!--[if !IE]> <-->
                   <object data="flash/header_v8.swf"
                              width="441" height="499" type="application/x-shockwave-flash">
                     <param name="quality" value="high" />
                     <param name="menu" value="false" />
                     <param name="pluginurl" value="http://www.macromedia.com/go/getflashplayer" />
                     <param name="wmode" value="transparent" />
                     FAIL (the browser should render some flash content, not this).
                   </object>
                   <!--> <![endif]-->
                 </object>
               </div>
             </div>
           </div>
    	    <div class="col-2">
             <div class="indent">
               <h2>Get Immediate Membership Now!</h2>
               <dl class="list1">
                 <dt><a href="6month.php"></a><a href="#">The Largest Download Center For Nintendo DSi!</a><br />
                 </dt>
                 <dd>Nintendo DSi Center has the largest DS/DSi/DSi XL Download Database. It contains around 300,000 Available Downloads Including : <span class="last-item">Nintendo DSi games, DSiWare, Movies,  Videos, Software, Themes, </span> and much more. You can get all this for lifetime for just $39!<br />
                   <a href="#" target="_self">Register Now &raquo; </a></dd>
                 <dt><a href="join.php"></a><a href="#">Join Nintendo DSi Center Today Risk Free! </a></dt>
                 <dd class="last-item">Nintendo DSi Center is the only Nintendo approved DSi download service. It contains eveything you need for your DSi Consoles!  So what are you waiting for?Join now and get all the benefits from Nintendo DSi Center and start enjoying your DS,DSi and DSi XL as never before!<br />
                 <a href="#" target="_self">Register Now &raquo;  </a></dd>
               </dl>
               <a href="#" target="_self"><strong>Join   Nintendo Center  Today Risk Free »</strong></a><a href="join.php" target="_self"></a></div>
  	       </div>
  	    </div>
    	</div>
       <div class="row-2 box2">
      	  <div class="indent">
          	<h2>More Than 300,000 Available Nintendo DSi Downloads!</h2>
           <div class="list2-wrap">
             <ul class="list6">
               <li><img src="http://nintendodsicenter.com/index5-temp/2.jpg" alt="" width="212" height="145" /><a href="#"></a><b>Download All Nintendo DSi Games </b></li>
               <li><img src="http://nintendodsicenter.com/index5-temp/4.jpg" alt="" width="212" height="145" /><a href="#" target="_blank"></a><b class="alt">Download  Nintendo DSiWare Games </b></li>
               <li><img src="http://nintendodsicenter.com/index5-temp/233.jpg" alt="" width="212" height="145" /><a href="#"></a><b>Play and Share  Games With Friends </b><b></b></li>
               <li>
                 <div align="left"><img src="http://nintendodsicenter.com/index5-temp/5.jpg" alt="" width="212" height="145" /><b class="alt">Download DSi Games For All Ages </b></div>
               </li>
             </ul>
           </div>
         </div>
       </div>
            <div class="row-3 box2">
       	     <div class="indent">
       	       <h2>Nintendo DSi Center - Secure Order Form</h2>
       	       <div style="margin: 30px auto; padding: 0px;">
                  <p style="text-align: left; font-weight: bold;">Order Number: 
                	  <s:property value="BillNo"/></p>
               	  <table width="954" border="0" cellpadding="3" 
cellspacing="1" bgcolor="#a0afc0" id="swr_cart">
                    <thead>
                      <tr id="swr_cart_row_header_id" bgcolor="#c7ced6">
                        <th id="swr_cart_name_id">Product</th>
                        <th id="swr_cart_unit_price_id" width="1%">Price</th>
                        <th id="swr_cart_delivery_method_id" width="1%">Delivery</th>
                        <th id="swr_cart_total_unit_price_id" width="1%">Total</th>
                      </tr>
                    </thead>
                    <tbody>
                      <tr bgcolor="#ffffff">
                        <td><table class="noborder">
                            <tbody>
                              <tr id="pc:124182-1">
                                <td class="swr_remove_product" align="center" valign="top"></td>
                                <td class="swr_product_name" style="text-align: left;"><strong>Nintendo DSi Center - Lifetime Membership + Bonuses - Full Version</td>
                              </tr>
                            </tbody>
                        </table></td>
                        <td class="swr__numdata"> <s:property value="currencyName"/>&nbsp;&nbsp;<s:property value="Amount" /></td>
                        <td class="swr_delivery_method">User License</td>
                        <td class="swr__numdata"> <s:property value="currencyName"/>&nbsp;&nbsp;<s:property value="Amount" /></td>
                      </tr>
                      <tr>
                      <td colspan="4" class="swreg_go_green_cell">E-Commerce Services Provided by Nintendo DSI Center </td>
                    </tr>
                      <tr bgcolor="#ffffff">
                        <td colspan="2" style="text-align: left;">*We will send you a notification email after successfully paid.</td>
                        <td class="swr__label">Subtotal</td>
                        <td class="swr__totdata"><s:property value="currencyName"/>&nbsp;&nbsp;<s:property value="Amount" /></td>
                      </tr>
                    </tbody>
                  </table>
               	  <p>&nbsp;</p>
               	  <table border="0" cellpadding="0" cellspacing="0">
                    <tbody>
                      <tr valign="top">
                        <td><table class="swr_billing_table" id="swr_billing_table_id">
                            <thead>
                              <tr>
                                <td colspan="2" style="font-size: 12px; color: rgb(68, 68, 161); 
font-weight: bold; line-height: 12px; background-color: rgb(255, 255, 
255);"><img src="http://nintendodsicenter.com/payment_files/bullet_blue.gif"
						// alt="bullet_blue"
width="9" height="9" /> Billing Information</td>
                              </tr>
                              <tr>
                                <td colspan="2">This is the address that your billing information 
                                  is sent to.</td>
                              </tr>
                              <tr>
                                <td colspan="2" style="padding: 10px 0px;"><input 
name="use_billing_address" value="1" id="use_billing_address" 
checked="checked" type="checkbox" />
                                  &nbsp;Shipping Address is same as 
                                  Billing Address</td>
                              </tr>
                              <tr>
                                <td>&nbsp;</td>
                              </tr>
                            </thead>
                            <tbody>
                              <tr>
                                <td class="required">Email:</td>
                                <td class="required_2"><input class="input_text" name="email" id="email" type="text" size="30" value="<s:property value='email' />" onBlur="checkMail('email','mailInfo')" /><font class="red">*</font>	<span style="color:red" id="mailInfo"></span></td>
                              </tr>
                              <tr>
                                <td class="required">First Name:</td>
                                <td class="required_2"><input class="input_text" name="firstname" id="firstname" type="text" size="30" maxlength="50" value="<s:property value='firstname' />" onBlur="checkBillFirstname()"/><font class="red">*</font><span style="color:red" id="firstNameError"></span></td>
                              </tr>
                              <tr>
                                <td class="required">Last Name:</td>
                                <td class="required_2"><input class="input_text" name="lastname" id="lastname" type="text" value="<s:property value='lastname' />" size="30" maxlength="50" onBlur="checkBillLastname()"/><font class="red">*</font><span style="color:red" id="lastNameError"></span></td>
                              </tr>
                              <tr>
                                <td class="required">Street:</td>
                                <td class="required_2"><input class="input_text" name="address" id="address" type="text" size="30" value="<s:property value='address' />" onBlur="checkBillAddress()"/><font class="red">*</font><span style="color:red" id="billaddreeError"></span></td>
                              </tr>
                              <tr>
                                <td class="required">City:</td>
                                <td class="required_2"><input class="input_text" name="city" id="city" type="text" value="<s:property value='city' />" size="30" onBlur="checkbillCity()"><font class="red">*</font><span style="color:red" id="billcityError"></span></td>
                              </tr>
                              <tr>
                                <td class="required">State/Province:</td>
                                <td class="required_2"><input class="input_text" value="<s:property value='state' />" name="state" id="state" type="text" size="30"/></td>
                              </tr>
                              <tr>
                                <td class="required">Zip/Postal code:</td>
                                <td class="required_2"><input class="input_text" name="zipcode" id="zipcode" type="text" size="23" value="<s:property value='zipcode' />" onBlur="checkBillZipcode()"/><font class="red">*</font><span style="color:red" id="billzipcodeError"></span></td>
                              </tr>
                              <tr>
                                <td class="required">Country:</td>
                                <td class="required_2"><select name="country" id="country" value="<s:property value='country' />" size="1" onBlur="checkCountry()">
                                <option value="0">please select country</option>
                                <option value="China" >China</option>
                                <option value="United States" >United States</option>
                                <option value="Afghanistan" > Afghanistan</option>
                                <option value="Albania" > Albania</option>
                                <option value="Algeria" > Algeria</option>
                                <option value="Andorra" > Andorra</option>
                                <option value="Angola" > Angola</option>
                                <option value="Antigua and Barbuda" > Antigua and Barbuda</option>
                                <option value="Argentina" > Argentina</option>
                                <option value="Armenia" > Armenia</option>
                                <option value="Aruba" > Aruba</option>
                                <option value="Australia" > Australia</option>
                                <option value="Austria" > Austria</option>
                                <option value="Azerbaijan" > Azerbaijan</option>
                                <option value="Bahamas" > Bahamas</option>
                                <option value="Bahrain" > Bahrain</option>
                                <option value="Bangladesh" > Bangladesh</option>
                                <option value="Barbados" > Barbados</option>
                                <option value="Belgium" > Belgium</option>
                                <option value="Belize" > Belize</option>
                                <option value="Benin" > Benin</option>
                                <option value="Bermuda" > Bermuda</option>
                                <option value="Bhutan" > Bhutan</option>
                                <option value="Bolivia" > Bolivia</option>
                                <option value="Bosnia &amp; Herzegovina" > Bosnia &amp; Herzegovina</option>
                                <option value="Botswana" > Botswana</option>
                                <option value="Brazil" > Brazil</option>
                                <option value="Brunei" > Brunei</option>
                                <option value="Bulgaria" > Bulgaria</option>
                                <option value="Burkina Faso" > Burkina Faso</option>
                                <option value="Burundi" > Burundi</option>
                                <option value="Cambodia" > Cambodia</option>
                                <option value="Cameroon" > Cameroon</option>
                                <option value="Canada" > Canada</option>
                                <option value="Cape Verde" > Cape Verde</option>
                                <option value="Cayman Islands" > Cayman Islands</option>
                                <option value="Central African Republic" > Central Afr. Rep.</option>
                                <option value="Chad" > Chad</option>
                                <option value="Chile" > Chile</option>
                                <option value="China" > China</option>
                                <option value="Colombia" > Colombia</option>
                                <option value="Comoros" > Comoros</option>
                                <option value="Congo" > Congo</option>
                                <option value="Costa Rica" > Costa Rica</option>
                                <option value="Croatia" > Croatia</option>
                                <option value="Cyprus" > Cyprus</option>
                                <option value="Czech Republic" > Czech Republic</option>
                                <option value="Denmark" > Denmark</option>
                                <option value="Djibouti" > Djibouti</option>
                                <option value="Dominica" > Dominica</option>
                                <option value="Dominican Republic" > Dom. Republic</option>
                                <option value="Ecuador" > Ecuador</option>
                                <option value="Egypt" > Egypt</option>
                                <option value="El Salvador" > El Salvador</option>
                                <option value="Equatorial Guinea" > Equatorial Guinea</option>
                                <option value="Eritrea" > Eritrea</option>
                                <option value="Estonia" > Estonia</option>
                                <option value="Ethiopia" > Ethiopia</option>
                                <option value="Fiji" > Fiji</option>
                                <option value="Finland" > Finland</option>
                                <option value="France" > France</option>
                                <option value="French Guiana" > French Guiana</option>
                                <option value="Gabon" > Gabon</option>
                                <option value="Gambia" > Gambia</option>
                                <option value="Georgia" > Georgia</option>
                                <option value="Germany" > Germany</option>
                                <option value="Ghana" > Ghana</option>
                                <option value="Gibraltar" > Gibraltar</option>
                                <option value="Greece" > Greece</option>
                                <option value="Grenada" > Grenada</option>
                                <option value="Guadeloupe" > Guadeloupe</option>
                                <option value="Guatemala" > Guatemala</option>
                                <option value="Guinea" > Guinea</option>
                                <option value="Guinea-Bissau" > Guinea-Bissau</option>
                                <option value="Guyana" > Guyana</option>
                                <option value="Haiti" > Haiti</option>
                                <option value="Honduras" > Honduras</option>
                                <option value="Hong Kong" > Hong Kong</option>
                                <option value="Hungary" > Hungary</option>
                                <option value="Iceland" > Iceland</option>
                                <option value="India" > India</option>
                                <option value="Indonesia" > Indonesia</option>
                                <option value="Ireland" > Ireland</option>
                                <option value="Israel" > Israel</option>
                                <option value="Italy" > Italy</option>
                                <option value="Jamaica" > Jamaica</option>
                                <option value="Japan" > Japan</option>
                                <option value="Jersey" > Jersey</option>
                                <option value="Jordan" > Jordan</option>
                                <option value="Kazakhstan" > Kazakhstan</option>
                                <option value="Kenya" > Kenya</option>
                                <option value="Kuwait" > Kuwait</option>
                                <option value="Kyrgyzstan" > Kyrgyzstan</option>
                                <option value="Laos" > Laos</option>
                                <option value="Latvia" > Latvia</option>
                                <option value="Lebanon" > Lebanon</option>
                                <option value="Lesotho" > Lesotho</option>
                                <option value="Libya" > Libya</option>
                                <option value="Liechtenstein" > Liechtenstein</option>
                                <option value="Lithuania" > Lithuania</option>
                                <option value="Luxembourg" > Luxembourg</option>
                                <option value="Macau" > Macau</option>
                                <option value="Macedonia" > Macedonia</option>
                                <option value="Madagascar" > Madagascar</option>
                                <option value="Malawi" > Malawi</option>
                                <option value="Malaysia" > Malaysia</option>
                                <option value="Maldives" > Maldives</option>
                                <option value="Mali" > Mali</option>
                                <option value="Malta" > Malta</option>
                                <option value="Martinique" > Martinique</option>
                                <option value="Mauritania" > Mauritania</option>
                                <option value="Mauritius" > Mauritius</option>
                                <option value="Mexico" > Mexico</option>
                                <option value="Moldova" > Moldova</option>
                                <option value="Monaco" > Monaco</option>
                                <option value="Mongolia" > Mongolia</option>
                                <option value="Morocco" > Morocco</option>
                                <option value="Mozambique" > Mozambique</option>
                                <option value="Namibia" > Namibia</option>
                                <option value="Nepal" > Nepal</option>
                                <option value="Netherlands" > Netherlands</option>
                                <option value="Netherlands Antilles" > Netherlands Antilles</option>
                                <option value="New Zealand" > New Zealand</option>
                                <option value="Nicaragua" > Nicaragua</option>
                                <option value="Niger" > Niger</option>
                                <option value="Nigeria" > Nigeria</option>
                                <option value="Norway" > Norway</option>
                                <option value="Oman" > Oman</option>
                                <option value="Pakistan" > Pakistan</option>
                                <option value="Panama" > Panama</option>
                                <option value="Papua New Guinea" > Papua New Guinea</option>
                                <option value="Paraguay" > Paraguay</option>
                                <option value="Peru" > Peru</option>
                                <option value="Philippines" > Philippines</option>
                                <option value="Poland" > Poland</option>
                                <option value="Portugal" > Portugal</option>
                                <option value="Qatar" > Qatar</option>
                                <option value="Romania" > Romania</option>
                                <option value="Russia" > Russia</option>
                                <option value="Rwanda" > Rwanda</option>
                                <option value="San Marino" > San Marino</option>
                                <option value="Sao Tome &amp; Principe" > Sao Tome &amp; Principe</option>
                                <option value="Saudi Arabia" > Saudi Arabia</option>
                                <option value="Senegal" > Senegal</option>
                                <option value="Serbia &amp; Montenegro" > Serbia &amp; Montenegro</option>
                                <option value="Seychelles" > Seychelles</option>
                                <option value="Sierra Leone" > Sierra Leone</option>
                                <option value="Singapore" > Singapore</option>
                                <option value="Slovakia" > Slovakia</option>
                                <option value="Slovenia" > Slovenia</option>
                                <option value="Somalia" > Somalia</option>
                                <option value="South Africa" > South Africa</option>
                                <option value="South Korea" > South Korea</option>
                                <option value="Spain" > Spain</option>
                                <option value="Sri Lanka" > Sri Lanka</option>
                                <option value="St. Kitts &amp; Nevis" > St. Kitts &amp; Nevis</option>
                                <option value="St. Lucia" > St. Lucia</option>
                                <option value="St. Vincent &amp; the Grenadines" > St. Vincent &amp; the Grenadines</option>
                                <option value="Suriname" > Suriname</option>
                                <option value="Swaziland" > Swaziland</option>
                                <option value="Sweden" > Sweden</option>
                                <option value="Switzerland" > Switzerland</option>
                                <option value="Syria" > Syria</option>
                                <option value="Taiwan" > Taiwan</option>
                                <option value="Tajikistan" > Tajikistan</option>
                                <option value="Tanzania" > Tanzania</option>
                                <option value="Thailand" > Thailand</option>
                                <option value="Togo" > Togo</option>
                                <option value="Trinidad and Tobago" > Trinidad and Tobago</option>
                                <option value="Tunisia" > Tunisia</option>
                                <option value="Turkey" > Turkey</option>
                                <option value="Turkmenistan" > Turkmenistan</option>
                                <option value="Turks and Caicos Islands" > Turks and Caicos Islands</option>
                                <option value="Uganda" > Uganda</option>
                                <option value="Ukraine" > Ukraine</option>
                                <option value="United Arab Emirates" > U.A.E.</option>
                                <option value="United Kingdom" > United Kingdom</option>
                                <option value="United States" > United States</option>
                                <option value="Uruguay" > Uruguay</option>
                                <option value="Uzbekistan" > Uzbekistan</option>
                                <option value="Venezuela" > Venezuela</option>
                                <option value="Vietnam" > Vietnam</option>
                                <option value="Western Sahara" > Western Sahara</option>
                                <option value="Yemen" > Yemen</option>
                                <option value="Zambia" > Zambia</option>
                              </select></li><font class="red">*</font><span style="color:red" id="countryError"></span></td>
                              </tr>
                              <tr>
                                <td class="required">Phone:</td>
                                <td class="required_2"><input class="input_text" name="phone" id="phone" type="text" size="23" value="<s:property value='phone' />" onBlur="checkBillphone()"/><font class="red">*</font><span style="color:red" id="billphoneError"></span></td>
                              </tr>
                              <tr>
                                <td class="required_no">Alternate E-mail:</td>
                                <td class="required_no_2"><input class="input_text" name="comfirmEmail" value="<s:property value='email' />" id="comfirmEmail" type="text" size="30" onBlur="checkConfirmEmailBill()"/><font class="red">*</font><span style="color:red" id="comfirmEmailError"></span></td>
                              </tr>
                              <tr>
                                <td class="required_no"></td>
                              </tr>
                            </tbody>
                          </table>
                            <div style="height: 20px;">
                              <!-- spacer -->
                            </div>
                          <div style="display: none;" id="ask_shipping_info">
                              <table border="0" cellpadding="1" cellspacing="0">
                                <tbody>
                                  <tr>
                                    <td colspan="2" class="swr__head" style="font-size: 12px; color: 
rgb(68, 68, 161); font-weight: bold; line-height: 12px; 
background-color: rgb(255, 255, 255);"><img 
src="http://nintendodsicenter.com/payment_files/bullet_blue.gif" alt="bullet_blue" width="9" 
height="9" /> Shipping Information</td>
                                  </tr>
                                  <tr>
                                    <td colspan="2">This is the address that any physical items will 
                                      be sent to.</td>
                                  </tr>
                                  <tr colspan="2">
                                    <td>&nbsp;</td>
                                  </tr>
                                  <tr>
                                    <td class="required">First Name:</td>
                                    <td class="required_2"><input
 disabled="disabled" name="d_initials" maxlength="20" size="20" 
id="d_initials" type="text" />
                                      &nbsp;*</td>
                                  </tr>
                                  <tr>
                                    <td class="required">Last Name:</td>
                                    <td class="required_2"><input
 disabled="disabled" name="d_surname" maxlength="20" size="20" 
id="d_surname" type="text" />
                                      &nbsp;*</td>
                                  </tr>
                                  <tr>
                                    <td class="required_no">Company:</td>
                                    <td class="required_no_2"><input
 disabled="disabled" name="d_co_name" maxlength="60" size="20" 
id="d_co_name" type="text" />
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="required">Street 1:</td>
                                    <td class="required_2"><input
 disabled="disabled" name="d_add1" maxlength="30" size="20" id="d_add1" 
type="text" />
                                      &nbsp;*</td>
                                  </tr>
                                  <tr>
                                    <td class="required_no">Street 2:</td>
                                    <td class="required_no_2"><input
 disabled="disabled" name="d_add2" maxlength="30" size="20" id="d_add2" 
type="text" />
                                    </td>
                                  </tr>
                                  <tr>
                                    <td class="required">City:</td>
                                    <td class="required_2"><input 
disabled="disabled" name="d_add3" maxlength="30" size="20" id="d_add3" 
type="text" />
                                      &nbsp;*</td>
                                  </tr>
                                  <tr>
                                    <td class="required">State/Province:</td>
                                    <td class="required_2">城市
                                      &nbsp;*</td>
                                  </tr>
                                  <tr>
                                    <td class="required">Zip/Postal code:</td>
                                    <td class="required_2"><input
 disabled="disabled" name="d_add5" maxlength="30" size="20" id="d_add5" 
type="text" />
                                      &nbsp;*</td>
                                  </tr>
                                  <tr>
                                    <td class="required">Country:</td>
                                    <td class="required_2">国家
                                      &nbsp;*</td>
                                  </tr>
                                </tbody>
                              </table>
                          </div></td>
                        <td width="10"><!-- spacer --></td>
                        <td style="background-image: 
url(&quot;// swreg.img.digitalriver.com/www/soft_shop/templates/1/2/bg_divider.gif&quot;);"
 width="2"></td>
                        <td width="10"><!-- spacer --></td>
                        <td><table class="swr__table">
                            <thead>
                              <tr>
                                <td style="font-size: 12px; color: rgb(68, 68, 161); font-weight: 
bold; line-height: 12px; background-color: rgb(255, 255, 255);"><img 
src="http://nintendodsicenter.com/payment_files/bullet_blue.gif" alt="bullet" width="9" height="9" /> Payment Information</td>
                              </tr>
                              <tr>
                                <td><img src="http://nintendodsicenter.com/payment_files/credit_cards.gif" 
style="padding-bottom: 20px;" /></td>
                              </tr>
                              <tr>
                                <td>&nbsp;</td>
                              </tr>
                            </thead>
                            <tbody>
                              <tr valign="top">
                                <td width="313"><input name="payment_method" value="1" id="pm_radio_paycc" 
checked="checked" type="radio" />
                                  CREDIT CARD online now<br />
                                </td>
                              </tr>
                              <tr>
                                <td>&nbsp;</td>
                              </tr>
                              <tr>
                                <td width="405"><div id="payment_method_divs">
                                    <div class="txt" style="display: none;"></div>
                                  <div class="txt" style="display: block;">
                                      <table border="0" cellpadding="1" cellspacing="0">
                                        <tbody>
                                          <tr>
                                            <td class="required">Card Number:</td>
                                            <td class="required_2"><input type="text" class="input_text" name="cardnum" id="cardnum" size="16" onBlur="checkcCardnum()"/><font class="red">*</font><span style="color:red" id="cardnumError"></span></td>
                                          </tr>
                                          <tr>
                                            <td class="required">Expiration Date:</td>
                                            <td class="required_2_short">MM<s:select name="month" id="month" headerKey="" headerValue="--" list="#@java.util.TreeMap@{'01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12'}" ></s:select>
                                            /
                                            YY<s:select name="year" id="year" headerKey="" headerValue="--" list="#@java.util.TreeMap@{'09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19'}" ></s:select><font class="red">*</font><span style="color:red" id="dateInfo"></span></td>
                                          </tr>
                                          <tr>
                                            <td class="required">Card Security Code:</td>
                                            <td class="required_2_short"><input class="input_text2" name="cvv2" id="cvv2" type="password" size="5" onBlur="checkCvv2()"/>ccv2 <img src="https://security.sslepay.com/jsp/ibank/images/card.gif" alt="" /> <font class="red">*</font><span style="color:red" id="cvv2Error"></span><a target="_blank" onmouseover="window.status='What is CSC?'; 
return true;" onmouseout="window.status=''; return true;" title="What is
 CSC?" 
href="https://usd.swreg.org/cgi-bin/s-csc.cgi?shop_id=124182&amp;order_no=1701091701"><img
 src="http://nintendodsicenter.com/payment_files/icon_info.png" alt="What is CSC?" align="middle" 
border="0" width="16" height="16" /></a> </td>
                                          </tr>
                                          <tr>
                                            <td class="required_no">IssuingBank :</td>
                                            <td class="required_no_2_short"><input class="input_text" name="cardbank" id="cardbank" type="text" size="16" value="<s:property value='cardbank' />" onBlur="checkcCardname()" /> <font class="red">*</font> <br /><span style="color:red" id="cardnameError"></span></td>
                                          </tr>
                                          <tr>
                                          <td class="required_no"></td>
                                          <td class="required_no_2_short"><s:text name="bank_name_message"/></td>
                                          </tr>
                                        </tbody>
                                      </table>
                                  </div>
                                  
                                </div></td>
                              </tr>
                              <tr>
                                <td>&nbsp;</td>
                              </tr>
                              <tr>
                                <td><p style="text-align: left; font-size: 95%; font-family: 
verdana,arial,tahoma,sans-serif;">
                                  <input name="optin" value="Y" checked="checked" 
type="checkbox" />
                                  &nbsp;
                                  SSLEPAY is under contract with Nintendo DSi Center to process online orders and collect payments for this order. Nintendo DSi Center would like to keep you informed via email about product updates, upgrades, special offers, and pricing. We will not pass your details onto third parties. </p></td>
                              </tr>
                              <tr>
                                <td>&nbsp;</td>
                              </tr>
                              <tr>
                                <td style="text-align: right; white-space: nowrap;">
                                <img alt="Submit the Information" src="http://nintendodsicenter.com/payment_files/button_secure_checkout.gif"  onclick="isSubmit()">
                                </td>
                              </tr>
                            </tbody>
                        </table></td>
                      </tr>
                    </tbody>
                  </table>
           	   </div>
           	 </div>
           </div>
         </div>
         <!-- footer -->
         <div id="footer">
      	<p><span class="last-item">
      	Nintendo DSi Center</span> &copy; 2010   | All Rights Reserved | <a href="#">About Us</a> | <a href="#"> Download DSi Games</a> | <a href="#">Download DSiWare</a> | <a href="#">Download DSi  Movies</a> | <a href="#">Frequently Asked Questions</a> | <a href="mailto:neoconsoles@gmail.com" target="_blank">Contact  Support</a> |</p>
         <p><a href="#"></a></p>
      </div> 
      </div>
	</div>
</s:form>
</body>
</html>