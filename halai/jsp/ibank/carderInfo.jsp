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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css" href="https://security.sslepay.com/jsp/ibank/imagess/style.css" />
<title>Payment</title>
<style>
.namess{ width:120px; height:35px; float:left; display:inline; font-size:11px}
.inputss{ width:330px; height:auto; _height:35px; min-height:35px; float:left; display:inline; font-size:11px}
.inputss input{ width:200px;}
h2{ color:#683C13}
</style>
<script type="text/javascript" src="https://security.sslepay.com/js/cardholders.js"></script>

	<script type="text/javascript" src="https://security.sslepay.com/js/prototype-1.6.0.2.js"></script>
	<script type="text/javascript" src="https://security.sslepay.com/js/json2.js"></script>
    
    <script type="text/javascript">	
	<!--验证卡号-->
		function checkcCardnum(){
			var cardnum = document.getElementById("cardnum").value;
			var jgpattern =/^[0-9]+$/; 
			var cvvnumsize = cardnum.length;
			cardnumFlag =  jgpattern.test(cardnum);
			//
			var o=document.getElementsByName("newcardtype");
		    var len=o.length;  
		    var cardtypename;
		    for (var i=0;i<len ;i++ ) { 
		    	if(o[i].checked==true){
		    		cardtypename = o[i].value;
		    	}
		    }
			if(cardnumFlag){
				//if(cvvnumsize==16){
					document.getElementById('cardnumError').innerHTML='';
					var merNo = document.getElementById("merchantnoValue").value;
					//var carkType = document.getElementById("carkType").value;
					
					getCardInfo('getCardTypeByJson.action?cardnum='+cardnum+'&merNo='+merNo+'&cardtypename='+cardtypename);
				//}else{
				//	document.getElementById('cardnumError').innerHTML='16-digit card number is required!';
					//return false;
				//}
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


</head>

<body>

	<div class="wrap" style=" width:830px;">
    	
        <div class="header"><img src="jsp/ibank/images/visamaster.gif" /></div>
        
 
        
        
        <div style="float:left; width:530px; display:inline; ">
        
        <h1 style="color:#5E2F03"><s:text name="require"/></h1>
        
        <s:form name="form1" id="form1" action="addCarderInfoAction" method="post" theme="simple">
<s:token/>
 <input type="hidden" id="cardname" value="<s:text name="cardname_error"/>"/>
 <input type="hidden" id="cvv2_error" value="<s:text name="cvv2_error"/>"/>
 <input type="hidden" id="mail_info" value="<s:text name="mail_info"/>"/>
 <input type="hidden" id="first_name_error" value="<s:text name="first_name_error"/>"/>
 <input type="hidden" id="last_name_error" value="<s:text name="last_name_error"/>"/>
 <input type="hidden" id="date_info" value="<s:text name="date_info"/>"/>
 <input type="hidden" id="shipping_first_error" value="<s:text name="shipping_first_error"/>"/>
 <input type="hidden" id="shipping_last_error" value="<s:text name="shipping_last_error"/>"/>
 
  <input type="hidden" id="shipcomfirm_email_error" value="<s:text name="shipcomfirm_email_error"/>"/>
  <input type="hidden" id="comfirm_email_error" value="<s:text name="comfirm_email_error"/>"/>
  <input type="hidden" id="billphone_error" value="<s:text name="billphone_error"/>"/>
  <input type="hidden" id="billaddree_error" value="<s:text name="billaddree_error"/>"/>
  <input type="hidden" id="billcity_error" value="<s:text name="billcity_error"/>"/>
  <input type="hidden" id="shipping_city_error" value="<s:text name="shipping_city_error"/>"/>
  <input type="hidden" id="shipping_zipcode_error" value="<s:text name="shipping_zipcode_error"/>"/>
  <input type="hidden" id="shipping_addree_error" value="<s:text name="shipping_addree_error"/>"/>
  <input type="hidden" id="shipping_phone_error" value="<s:text name="shipping_phone_error"/>"/>
     
 <s:hidden name="merchantnoValue" id="merchantnoValue" />
 <s:hidden name="carkType" id="carkType"/>
 <s:hidden name="ReturnURL"></s:hidden>
 <s:hidden name="MD5key"></s:hidden>
 <s:hidden name="products"/>
 
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="jsp/ibank/images/top1.gif" /></td>
          </tr>
        </table>
        <table style="border-left:1px solid #C4864B; background:url(jsp/ibank/images/bg_02.gif) repeat-x; border-right:1px solid #C4864B;"  width="830">

<tr>
            	<td colspan="2"><h2><s:text name="order_information"/>:</h2></td>
            </tr>
            <tr>
            	<td class="left"><s:text name="payment_amount"/>:</td>
                <td><s:property value="Amount" />&nbsp;&nbsp;<s:property value="currencyName"/></td>            
            </tr>
            <tr>
            	<td><s:text name="merchant_order_no"/>: </td>
                <td><s:property value="BillNo"/></td>
            </tr>
            <tr>
            	<td><s:text name="ecpss_order_no"/>: </td>
                <td><s:property value="rorderno"/> <input type="hidden" name="rorderno" value="<s:property value="rorderno"/>"></td>
            </tr>
            <tr>
  <td colspan="2"><hr /></td>
</tr>
            <tr>
            <td colspan="2"><h2><s:text name="credit_card_information"/>:</h2></td>
            </tr>
            <tr>
              <td colspan="2"><table width="100%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td align="center"><s:if test="cardtype_jcb==true"><img src="https://security.sslepay.com/jsp/ibank/imagess/ic1.gif" alt="Jcb Card" /></s:if></td>
                  <td align="center"><s:if test="cardtype_ae==true"><img src="https://security.sslepay.com/jsp/ibank/imagess/ic2.gif" alt="American Express Card" /></s:if></td>
                  <td align="center"><s:if test="cardtype_visa==true"><img src="https://security.sslepay.com/jsp/ibank/imagess/ic3.gif" alt="Visa Card" /></s:if></td>
                  <td align="center"><s:if test="cardtype_master==true"><img src="https://security.sslepay.com/jsp/ibank/imagess/ic4.gif" alt="Master Card" /></s:if></td>
                  <td align="center"><s:if test="cardtype_dc==true"><img src="https://security.sslepay.com/jsp/ibank/imagess/ic5.gif" alt="Diners Club Card" /></s:if></td>
                </tr>
                <tr>
                  <td align="center">
                  <s:if test="cardtype_jcb==true"><input type="radio" name="newcardtype" id="jcb" value="3" onblur="checkcCardnum()"
                  		<s:if test="cardtype_jcb==false">disabled</s:if>  /></s:if>
                  </td>
                  <td align="center"><s:if test="cardtype_ae==true"><input type="radio" name="newcardtype" id="ae" value="6" onblur="checkcCardnum()"
                	    <s:if test="cardtype_ae==false">disabled</s:if>/></s:if></td>
                  <td align="center"><s:if test="cardtype_visa==true"><input type="radio" name="newcardtype" id="visa" value="4" checked="checked"  onblur="checkcCardnum()"
                	  <s:if test="cardtype_visa==false">disabled</s:if> /></s:if></td>
                  <td align="center"><s:if test="cardtype_master==true"><input type="radio" name="newcardtype" id="master" value="5" onblur="checkcCardnum()"
                	  <s:if test="cardtype_master==false">disabled</s:if>/></s:if></td>
                  <td align="center"><s:if test="cardtype_dc==true"><input type="radio" name="newcardtype" id="dc" value="7" onblur="checkcCardnum()"
                	  <s:if test="cardtype_dc==false">disabled</s:if>/></s:if></td>
                </tr>
              </table></td>
            </tr>
            <tr>
            	<td><s:text name="credit_card_number"/>:</td>
                <td><input type="text" class="input_text" name="cardnum" id="cardnum" size="17" onBlur="checkcCardnum()"/><font class="red">*</font><span style="color:red" id="cardnumError"></span></td>
            </tr>
            
            <tr>
            	<td><s:text name="card_verification_number"/>:</td>
                <td><input class="input_text2" name="cvv2" id="cvv2" type="password" size="5" onBlur="checkCvv2()"/>ccv2 <img src="https://security.sslepay.com/jsp/ibank/images/card.gif" alt="" /> <font class="red">*</font><span style="color:red" id="cvv2Error"></span></td>
            </tr>
            
            <tr>
            	<td><s:text name="card_cxpiry_date"/>:</td>
                <td>month: <s:select name="month" id="month" headerKey="" headerValue="--" list="#@java.util.TreeMap@{'01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12'}" ></s:select> / year: <s:select name="year" id="year" headerKey="" headerValue="--" list="#@java.util.TreeMap@{'11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19','20':'20','21':'21','22':'22','23':'23','24':'24','25':'25','26':'26','27':'27'}" ></s:select><font class="red">*</font><span style="color:red" id="dateInfo"></span></td>
            </tr>
            
            <tr>
            	<td><s:text name="issuing_bank"/>:</td>
                <td><input class="input_text" name="cardbank" id="cardbank" type="text" size="23" value="<s:property value='cardbank' />" onBlur="checkcCardname()" /> <font class="red">*</font> <br /><span style="color:red" id="cardnameError"></span><s:text name="bank_name_message"/></td>
            </tr>
            <tr>
  <td colspan="2"><hr /></td>
</tr>
            <tr><td colspan="2"><h2><s:text name="shipping_address"/>:</h2></td></tr>
            
            <tr>
            	<td><s:text name="first_name"/>:</td>
                <td><input class="input_text" name="shippingFirstName" id="shippingFirstName" type="text" size="23" value="<s:property value='shippingFirstName' />"  onBlur="checkSFirstname()"/><font class="red">*</font><span style="color:red" id="shippingFirstError"></span></td>
            </tr>
            
            <tr>
            	<td><s:text name="last_name"/>:</td>
                <td><input class="input_text" name="shippingLastName" id="shippingLastName" type="text" size="23" value="<s:property value='shippingLastName' />" onBlur="checkSLastname()" /><font class="red">*</font> <span style="color:red" id="shippingLastError"></span></td>
            </tr>
            
            <tr>
            	<td><s:text name="email"/>:</td>
                <td><input class="input_text" name="shippingEmail" id="shippingEmail" type="text" size="23" value="<s:property value='shippingEmail' />" onBlur="checkMail('shippingEmail','shippingmailInfo')" /><font class="red">*</font>	<span style="color:red" id="shippingmailInfo"></span></td>
            </tr>
            
            <tr>
            	<td><s:text name="confirm_email"/>:</td>
                <td><input class="input_text" name="shippingComfirmEmail"  value="<s:property value='shippingEmail' />" id="shippingComfirmEmail" type="text" size="23" onBlur="checkConfirmEmail()"/><font class="red">*</font> <span style="color:red" id="shipcomfirmEmailError"></span></td>
            </tr>
            
            <tr>
            	<td><s:text name="phone"/>:</td>
                <td><input class="input_text" name="shippingPhone" id="shippingPhone" type="text" value="<s:property value='shippingPhone' />" size="23" onBlur="checkShipPhone()"/><font class="red">*</font><span style="color:red" id="shippingphoneError"></span></td>
            </tr>
            
            <tr>
            	<td><s:text name="zip_code"/>:</td>
                <td><input class="input_text" name="shippingZipcode" id="shippingZipcode" type="text" value="<s:property value='shippingZipcode' />" size="23" onBlur="checkShipZipcode()"/><font class="red">*</font><span style="color:red" id="shippingzipcodeError"></span></td>
            </tr>
            
            <tr>
            	<td><s:text name="address"/>:</td>
                <td><input  class="input_text"  name="shippingAddress" id="shippingAddress" value="<s:property value='shippingAddress' />" type="text" size="23" onBlur="checkShipAddress()"/><font class="red">*</font><span style="color:red" id="shippingaddreeError"></span></td>
            </tr>
            
            <tr>
            	<td><s:text name="city"/>:</td>
                <td><input class="input_text" name="shippingCity" id="shippingCity" value="<s:property value='shippingCity' />" type="text" size="23" onBlur="checkShipCity()"><font class="red">*</font><span style="color:red" id="shippingCityError"></span></td>
            </tr>
            
            <tr>
            	<td><s:text name="state"/>:</td>
                <td><input class="input_text" name="shippingSstate" id="shippingState" value="<s:property value='shippingSstate' />" type="text" size="23"/></td>
            </tr>
            
            <tr>
            	<td><s:text name="country"/>:</td>
                <td>
                <select name="shippingCountry" id="shippingCountry" value="<s:property value='shippingCountry' />" size="1" onBlur="checkCountry()">
                      <option value="0">please select country</option>
                      <option value="CN" >China</option>
                      <option value="US" >United States</option>
                      <option value="AF" > Afghanistan</option>
                      <option value="AL" > Albania</option>
                      <option value="DZ" > Algeria</option>
                      <option value="AD" > Andorra</option>
                      <option value="AI" > Angola</option>
                      <option value="AG" > Antigua and Barbuda</option>
                      <option value="AR" > Argentina</option>
                      <option value="AM" > Armenia</option>
                      <option value="Aruba" > Aruba</option>
                      <option value="AU" > Australia</option>
                      <option value="AT" > Austria</option>
                      <option value="AZ" > Azerbaijan</option>
                      <option value="BS" > Bahamas</option>
                      <option value="BH" > Bahrain</option>
                      <option value="BD" > Bangladesh</option>
                      <option value="BB" > Barbados</option>
                      <option value="BE" > Belgium</option>
                      <option value="BZ" > Belize</option>
                      <option value="BJ" > Benin</option>
                      <option value="BM" > Bermuda</option>
                      <option value="BHU" > Bhutan</option>
                      <option value="BO" > Bolivia</option>
                      <option value="BIH" > Bosnia &amp; Herzegovina</option>
                      <option value="BW" > Botswana</option>
                      <option value="BR" > Brazil</option>
                      <option value="BN" > Brunei</option>
                      <option value="BG" > Bulgaria</option>
                      <option value="BF" > Burkina Faso</option>
                      <option value="BI" > Burundi</option>
                      <option value="KHM" > Cambodia</option>
                      <option value="CM" > Cameroon</option>
                      <option value="CA" > Canada</option>
                      <option value="Cape Verde" > Cape Verde</option>
                      <option value="KY" > Cayman Islands</option>
                      <option value="CF" > Central Afr. Rep.</option>
                      <option value="TD" > Chad</option>
                      <option value="CL" > Chile</option>
                      <option value="CN" > China</option>
                      <option value="CO" > Colombia</option>
                      <option value="COM" > Comoros</option>
                      <option value="CG" > Congo</option>
                      <option value="CR" > Costa Rica</option>
                      <option value="HRV" > Croatia</option>
                      <option value="CY" > Cyprus</option>
                      <option value="CZ" > Czech Republic</option>
                      <option value="DK" > Denmark</option>
                      <option value="DJ" > Djibouti</option>
                      <option value="DO" > Dominica</option>
                      <option value="DO" > Dom. Republic</option>
                      <option value="EC" > Ecuador</option>
                      <option value="EG" > Egypt</option>
                      <option value="SV" > El Salvador</option>
                      <option value="Equatorial Guinea" > Equatorial Guinea</option>
                      <option value="ERI" > Eritrea</option>
                      <option value="EE" > Estonia</option>
                      <option value="ET" > Ethiopia</option>
                      <option value="FJ" > Fiji</option>
                      <option value="FI" > Finland</option>
                      <option value="FR" > France</option>
                      <option value="GF" > French Guiana</option>
                      <option value="GA" > Gabon</option>
                      <option value="GM" > Gambia</option>
                      <option value="GE" > Georgia</option>
                      <option value="DE" > Germany</option>
                      <option value="GH" > Ghana</option>
                      <option value="GI" > Gibraltar</option>
                      <option value="GR" > Greece</option>
                      <option value="GD" > Grenada</option>
                      <option value="Guadeloupe" > Guadeloupe</option>
                      <option value="GT" > Guatemala</option>
                      <option value="GN" > Guinea</option>
                      <option value="GBS" > Guinea-Bissau</option>
                      <option value="GY" > Guyana</option>
                      <option value="HT" > Haiti</option>
                      <option value="HN" > Honduras</option>
                      <option value="HK" > Hong Kong</option>
                      <option value="HU" > Hungary</option>
                      <option value="IS" > Iceland</option>
                      <option value="IN" > India</option>
                      <option value="ID" > Indonesia</option>
                      <option value="IE" > Ireland</option>
                      <option value="IL" > Israel</option>
                      <option value="IT" > Italy</option>
                      <option value="JM" > Jamaica</option>
                      <option value="JP" > Japan</option>
                      <option value="Jersey" > Jersey</option>
                      <option value="JO" > Jordan</option>
                      <option value="KZ" > Kazakhstan</option>
                      <option value="KE" > Kenya</option>
                      <option value="KW" > Kuwait</option>
                      <option value="KG" > Kyrgyzstan</option>
                      <option value="LA" > Laos</option>
                      <option value="LV" > Latvia</option>
                      <option value="LB" > Lebanon</option>
                      <option value="LS" > Lesotho</option>
                      <option value="LY" > Libya</option>
                      <option value="LI" > Liechtenstein</option>
                      <option value="LT" > Lithuania</option>
                      <option value="LU" > Luxembourg</option>
                      <option value="MO" > Macau</option>
                      <option value="MKD" > Macedonia</option>
                      <option value="MG" > Madagascar</option>
                      <option value="MW" > Malawi</option>
                      <option value="MY" > Malaysia</option>
                      <option value="MV" > Maldives</option>
                      <option value="ML" > Mali</option>
                      <option value="MT" > Malta</option>
                      <option value="MTQ" > Martinique</option>
                      <option value="MRT" > Mauritania</option>
                      <option value="MU" > Mauritius</option>
                      <option value="MX" > Mexico</option>
                      <option value="MD" > Moldova</option>
                      <option value="MC" > Monaco</option>
                      <option value="MN" > Mongolia</option>
                      <option value="MA" > Morocco</option>
                      <option value="MZ" > Mozambique</option>
                      <option value="NA" > Namibia</option>
                      <option value="NP" > Nepal</option>
                      <option value="NL" > Netherlands</option>
                      <option value="Netherlands Antilles" > Netherlands Antilles</option>
                      <option value="NZ" > New Zealand</option>
                      <option value="NI" > Nicaragua</option>
                      <option value="NE" > Niger</option>
                      <option value="NG" > Nigeria</option>
                      <option value="NO" > Norway</option>
                      <option value="OM" > Oman</option>
                      <option value="PK" > Pakistan</option>
                      <option value="PA" > Panama</option>
                      <option value="PG" > Papua New Guinea</option>
                      <option value="PY" > Paraguay</option>
                      <option value="PE" > Peru</option>
                      <option value="PH" > Philippines</option>
                      <option value="PL" > Poland</option>
                      <option value="PT" > Portugal</option>
                      <option value="QA" > Qatar</option>
                      <option value="RO" > Romania</option>
                      <option value="RU" > Russia</option>
                      <option value="RW" > Rwanda</option>
                      <option value="SM" > San Marino</option>
                      <option value="ST" > Sao Tome &amp; Principe</option>
                      <option value="SA" > Saudi Arabia</option>
                      <option value="SN" > Senegal</option>
                      <option value="Serbia &amp; Montenegro" > Serbia &amp; Montenegro</option>
                      <option value="SN" > Seychelles</option>
                      <option value="SL" > Sierra Leone</option>
                      <option value="SG" > Singapore</option>
                      <option value="SK" > Slovakia</option>
                      <option value="SI" > Slovenia</option>
                      <option value="Somalia" > Somalia</option>
                      <option value="ZA" > South Africa</option>
                      <option value="kr" > South Korea</option>
                      <option value="ES" > Spain</option>
                      <option value="LK" > Sri Lanka</option>
                      <option value="St. Kitts &amp; Nevis" > St. Kitts &amp; Nevis</option>
                      <option value="LC" > St. Lucia</option>
                      <option value="VC" > St. Vincent &amp; the Grenadines</option>
                      <option value="SR" > Suriname</option>
                      <option value="SZ" > Swaziland</option>
                      <option value="SE" > Sweden</option>
                      <option value="CH" > Switzerland</option>
                      <option value="SY" > Syria</option>
                      <option value="TW" > Taiwan</option>
                      <option value="TJ" > Tajikistan</option>
                      <option value="TZ" > Tanzania</option>
                      <option value="TH" > Thailand</option>
                      <option value="TG" > Togo</option>
                      <option value="TT" > Trinidad and Tobago</option>
                      <option value="TN" > Tunisia</option>
                      <option value="TR" > Turkey</option>
                      <option value="TM" > Turkmenistan</option>
                      <option value="Turks and Caicos Islands" > Turks and Caicos Islands</option>
                      <option value="UG" > Uganda</option>
                      <option value="UA" > Ukraine</option>
                      <option value="AE" > U.A.E.</option>
                      <option value="GB" > United Kingdom</option>
                      <option value="US" > United States</option>
                      <option value="UY" > Uruguay</option>
                      <option value="UZ" > Uzbekistan</option>
                      <option value="VE" > Venezuela</option>
                      <option value="VN" > Vietnam</option>
                      <option value="EH" > Western Sahara</option>
                      <option value="YE" > Yemen</option>
                      <option value="ZM" > Zambia</option>
                    </select></li><font class="red">*</font><span style="color:red" id="countryError"></span>                </td>
            </tr>
            <tr>
              <td colspan="4"><div class="title"><span><font class="font_color"><s:text name="billing_address"/>:</font>  &nbsp;&nbsp;&nbsp;<input type="radio" name="thesame" value="1" id="thesame" onClick="noshowBillingAddress('showBillingAddress')" />&nbsp;<label for="cd"><s:text name="same_as_shipping_address"/></label>&nbsp;&nbsp;<input type="radio" name="thesame" value="2" id="nothesame"  onClick="toshowBillingAddress('showBillingAddress')" />&nbsp;<label for="cds"><s:text name="not_same_as_shipping_address"/></label></span></div></td>
            </tr>
            <tr>
              <td colspan="4" style="text-align:left">            
                <div id="showBillingAddress" style=" margin:0 0 0 -20px; display:none">
                  <div class="inputs">
                    <ul>
                      <input type="hidden" name="cookieId" value=<%=cookValue %>/>
                      <li class="namess"><s:text name="billind_first_name"/>:</li>
                      <li class="inputss"><input class="input_text" name="firstname" id="firstname" type="text" size="40" maxlength="50" value="<s:property value='firstname' />" onBlur="checkBillFirstname()"/><font class="red">*</font><span style="color:red" id="firstNameError"></span></li>
                 <li class="namess"><s:text name="billind_last_name"/> :</li>
                      <li class="inputss"><input class="input_text" name="lastname" id="lastname" type="text" value="<s:property value='lastname' />" size="40" maxlength="50" onBlur="checkBillLastname()"/><font class="red">*</font><span style="color:red" id="lastNameError"></span></li>
  
               <li class="namess"><s:text name="billind_email"/> :</li>
                      <li class="inputss"><input class="input_text" name="email" id="email" type="text" size="50" value="<s:property value='email' />" onBlur="checkMail('email','mailInfo')" /><font class="red">*</font>	<span style="color:red" id="mailInfo"></span></li>
                 <li class="namess"><s:text name="billind_confirm_email"/> :</li>
                      <li class="inputss"><input class="input_text" name="comfirmEmail" value="<s:property value='email' />" id="comfirmEmail" type="text" size="50" onBlur="checkConfirmEmailBill()"/><font class="red">*</font><span style="color:red" id="comfirmEmailError"></span></li>
                 <li class="namess"><s:text name="billind_phone"/>:</li>
                      <li class="inputss"><input class="input_text" name="phone" id="phone" type="text" size="23" value="<s:property value='phone' />" onBlur="checkBillphone()"/><font class="red">*</font><span style="color:red" id="billphoneError"></span></li>
                 <li class="namess"><s:text name="billind_zip_code"/>:</li>
                      <li class="inputss"><input class="input_text" name="zipcode" id="zipcode" type="text" size="23" value="<s:property value='zipcode' />" onBlur="checkBillZipcode()"/><font class="red">*</font><span style="color:red" id="billzipcodeError"></span></li>
                 <li class="namess"><s:text name="billind_address"/>:</li>
                      <li class="inputss"><input class="input_text" name="address" id="address" type="text" size="50" value="<s:property value='address' />" onBlur="checkBillAddress()"/><font class="red">*</font><span style="color:red" id="billaddreeError"></span></li>
                 <li class="namess"><s:text name="billind_city"/>:</li>
                      <li class="inputss"><input class="input_text" name="city" id="city" type="text" value="<s:property value='city' />" size="50" onBlur="checkbillCity()"><font class="red">*</font><span style="color:red" id="billcityError"></span></li>
                 <li class="namess"><s:text name="billind_state"/> :</li>
                      <li class="inputss"><input class="input_text" value="<s:property value='state' />" name="state" id="state" type="text" size="50"/></li>
                 <li class="namess"><s:text name="billind_country"/>:</li>
                      <li class="inputss"> <select name="country" value="<s:property value='country' />" id="country" size="1" onBlur="checkCountry()">
                      <option value="0">please select country</option>
                      <option value="CN" >China</option>
                      <option value="US" >United States</option>
                      <option value="AF" > Afghanistan</option>
                      <option value="AL" > Albania</option>
                      <option value="DZ" > Algeria</option>
                      <option value="AD" > Andorra</option>
                      <option value="AI" > Angola</option>
                      <option value="AG" > Antigua and Barbuda</option>
                      <option value="AR" > Argentina</option>
                      <option value="AM" > Armenia</option>
                      <option value="Aruba" > Aruba</option>
                      <option value="AU" > Australia</option>
                      <option value="AT" > Austria</option>
                      <option value="AZ" > Azerbaijan</option>
                      <option value="BS" > Bahamas</option>
                      <option value="BH" > Bahrain</option>
                      <option value="BD" > Bangladesh</option>
                      <option value="BB" > Barbados</option>
                      <option value="BE" > Belgium</option>
                      <option value="BZ" > Belize</option>
                      <option value="BJ" > Benin</option>
                      <option value="BM" > Bermuda</option>
                      <option value="BHU" > Bhutan</option>
                      <option value="BO" > Bolivia</option>
                      <option value="BIH" > Bosnia &amp; Herzegovina</option>
                      <option value="BW" > Botswana</option>
                      <option value="BR" > Brazil</option>
                      <option value="BN" > Brunei</option>
                      <option value="BG" > Bulgaria</option>
                      <option value="BF" > Burkina Faso</option>
                      <option value="BI" > Burundi</option>
                      <option value="KHM" > Cambodia</option>
                      <option value="CM" > Cameroon</option>
                      <option value="CA" > Canada</option>
                      <option value="Cape Verde" > Cape Verde</option>
                      <option value="KY" > Cayman Islands</option>
                      <option value="CF" > Central Afr. Rep.</option>
                      <option value="TD" > Chad</option>
                      <option value="CL" > Chile</option>
                      <option value="CN" > China</option>
                      <option value="CO" > Colombia</option>
                      <option value="COM" > Comoros</option>
                      <option value="CG" > Congo</option>
                      <option value="CR" > Costa Rica</option>
                      <option value="HRV" > Croatia</option>
                      <option value="CY" > Cyprus</option>
                      <option value="CZ" > Czech Republic</option>
                      <option value="DK" > Denmark</option>
                      <option value="DJ" > Djibouti</option>
                      <option value="DO" > Dominica</option>
                      <option value="DO" > Dom. Republic</option>
                      <option value="EC" > Ecuador</option>
                      <option value="EG" > Egypt</option>
                      <option value="SV" > El Salvador</option>
                      <option value="Equatorial Guinea" > Equatorial Guinea</option>
                      <option value="ERI" > Eritrea</option>
                      <option value="EE" > Estonia</option>
                      <option value="ET" > Ethiopia</option>
                      <option value="FJ" > Fiji</option>
                      <option value="FI" > Finland</option>
                      <option value="FR" > France</option>
                      <option value="GF" > French Guiana</option>
                      <option value="GA" > Gabon</option>
                      <option value="GM" > Gambia</option>
                      <option value="GE" > Georgia</option>
                      <option value="DE" > Germany</option>
                      <option value="GH" > Ghana</option>
                      <option value="GI" > Gibraltar</option>
                      <option value="GR" > Greece</option>
                      <option value="GD" > Grenada</option>
                      <option value="Guadeloupe" > Guadeloupe</option>
                      <option value="GT" > Guatemala</option>
                      <option value="GN" > Guinea</option>
                      <option value="GBS" > Guinea-Bissau</option>
                      <option value="GY" > Guyana</option>
                      <option value="HT" > Haiti</option>
                      <option value="HN" > Honduras</option>
                      <option value="HK" > Hong Kong</option>
                      <option value="HU" > Hungary</option>
                      <option value="IS" > Iceland</option>
                      <option value="IN" > India</option>
                      <option value="ID" > Indonesia</option>
                      <option value="IE" > Ireland</option>
                      <option value="IL" > Israel</option>
                      <option value="IT" > Italy</option>
                      <option value="JM" > Jamaica</option>
                      <option value="JP" > Japan</option>
                      <option value="Jersey" > Jersey</option>
                      <option value="JO" > Jordan</option>
                      <option value="KZ" > Kazakhstan</option>
                      <option value="KE" > Kenya</option>
                      <option value="KW" > Kuwait</option>
                      <option value="KG" > Kyrgyzstan</option>
                      <option value="LA" > Laos</option>
                      <option value="LV" > Latvia</option>
                      <option value="LB" > Lebanon</option>
                      <option value="LS" > Lesotho</option>
                      <option value="LY" > Libya</option>
                      <option value="LI" > Liechtenstein</option>
                      <option value="LT" > Lithuania</option>
                      <option value="LU" > Luxembourg</option>
                      <option value="MO" > Macau</option>
                      <option value="MKD" > Macedonia</option>
                      <option value="MG" > Madagascar</option>
                      <option value="MW" > Malawi</option>
                      <option value="MY" > Malaysia</option>
                      <option value="MV" > Maldives</option>
                      <option value="ML" > Mali</option>
                      <option value="MT" > Malta</option>
                      <option value="MTQ" > Martinique</option>
                      <option value="MRT" > Mauritania</option>
                      <option value="MU" > Mauritius</option>
                      <option value="MX" > Mexico</option>
                      <option value="MD" > Moldova</option>
                      <option value="MC" > Monaco</option>
                      <option value="MN" > Mongolia</option>
                      <option value="MA" > Morocco</option>
                      <option value="MZ" > Mozambique</option>
                      <option value="NA" > Namibia</option>
                      <option value="NP" > Nepal</option>
                      <option value="NL" > Netherlands</option>
                      <option value="Netherlands Antilles" > Netherlands Antilles</option>
                      <option value="NZ" > New Zealand</option>
                      <option value="NI" > Nicaragua</option>
                      <option value="NE" > Niger</option>
                      <option value="NG" > Nigeria</option>
                      <option value="NO" > Norway</option>
                      <option value="OM" > Oman</option>
                      <option value="PK" > Pakistan</option>
                      <option value="PA" > Panama</option>
                      <option value="PG" > Papua New Guinea</option>
                      <option value="PY" > Paraguay</option>
                      <option value="PE" > Peru</option>
                      <option value="PH" > Philippines</option>
                      <option value="PL" > Poland</option>
                      <option value="PT" > Portugal</option>
                      <option value="QA" > Qatar</option>
                      <option value="RO" > Romania</option>
                      <option value="RU" > Russia</option>
                      <option value="RW" > Rwanda</option>
                      <option value="SM" > San Marino</option>
                      <option value="ST" > Sao Tome &amp; Principe</option>
                      <option value="SA" > Saudi Arabia</option>
                      <option value="SN" > Senegal</option>
                      <option value="Serbia &amp; Montenegro" > Serbia &amp; Montenegro</option>
                      <option value="SN" > Seychelles</option>
                      <option value="SL" > Sierra Leone</option>
                      <option value="SG" > Singapore</option>
                      <option value="SK" > Slovakia</option>
                      <option value="SI" > Slovenia</option>
                      <option value="Somalia" > Somalia</option>
                      <option value="ZA" > South Africa</option>
                      <option value="kr" > South Korea</option>
                      <option value="ES" > Spain</option>
                      <option value="LK" > Sri Lanka</option>
                      <option value="St. Kitts &amp; Nevis" > St. Kitts &amp; Nevis</option>
                      <option value="LC" > St. Lucia</option>
                      <option value="VC" > St. Vincent &amp; the Grenadines</option>
                      <option value="SR" > Suriname</option>
                      <option value="SZ" > Swaziland</option>
                      <option value="SE" > Sweden</option>
                      <option value="CH" > Switzerland</option>
                      <option value="SY" > Syria</option>
                      <option value="TW" > Taiwan</option>
                      <option value="TJ" > Tajikistan</option>
                      <option value="TZ" > Tanzania</option>
                      <option value="TH" > Thailand</option>
                      <option value="TG" > Togo</option>
                      <option value="TT" > Trinidad and Tobago</option>
                      <option value="TN" > Tunisia</option>
                      <option value="TR" > Turkey</option>
                      <option value="TM" > Turkmenistan</option>
                      <option value="Turks and Caicos Islands" > Turks and Caicos Islands</option>
                      <option value="UG" > Uganda</option>
                      <option value="UA" > Ukraine</option>
                      <option value="AE" > U.A.E.</option>
                      <option value="GB" > United Kingdom</option>
                      <option value="US" > United States</option>
                      <option value="UY" > Uruguay</option>
                      <option value="UZ" > Uzbekistan</option>
                      <option value="VE" > Venezuela</option>
                      <option value="VN" > Vietnam</option>
                      <option value="EH" > Western Sahara</option>
                      <option value="YE" > Yemen</option>
                      <option value="ZM" > Zambia</option>
                        </select><font class="red">*</font><span style="color:red" id="countryError"></span></li>
              </ul>
            </div>
                </div></td>
            </tr>
            
            <tr><td colspan="2">&nbsp;</td></tr>
            
            <tr>
            <td >&nbsp;</td><td style=" padding:20px 0;"><input onclick="isSubmit()" style=" width:170px; height:33px; border:0; background:url(jsp/ibank/images/co.gif)" value="" type="button" /></td>
            </tr>
            <tr>
            <td>&nbsp;</td>
            <td><div id="process" style="visibility:hidden;">
        		<img src="https://security.sslepay.com/jsp/ibank/imagess/loading1.gif" alt="" /></div></td>
            </tr>
        </table>
        <table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td><img src="jsp/ibank/images/top2.gif" /></td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
        </table>
        </s:form>
        <table width="830" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" style="width:251px;"><div class="info" style="margin:0">
            <h2>Important:</h2>
            	<p>Due to floating exchange rate, tiny disparity of order value may exist. Please wait and do not refresh this page when payment is being processed. <strong class="red">Refreshment will lead to double charge!</strong></p>

<p>Successful payment will get a confirmation email. Your payment details will be securely transmitted to the Bank for transaction authorization using 128 bit SSL encryption. Thank you very much for using our payment gateway. </p>
            </div></td>
            <td width="11">&nbsp;</td>
            <td width="268" valign="top">
              <table width="100%" border="0" cellspacing="3" cellpadding="0">
                <tr>
                  <td><img id="trustwaveSealImage" src="https://security.sslepay.com/jsp/ibank/imagess/seal_image.png" style="cursor: pointer;" onclick="javascript:window.open('https://sealserver.trustwave.com/cert.php?customerId=w6onNpZ9ZhnnfsR5amFEuytnZidqDb&amp;size=105x54&amp;style=normal', 'c_TW', 'location=no, toolbar=no, resizable=yes, scrollbars=yes, directories=no, status=no, width=615, height=720'); return false;" oncontextmenu="javascript:alert('Copying Prohibited by Law - Trusted Commerce is a Service Mark of TrustWave Holdings, Inc.'); return false;" alt="This site protected by Trustwave's Trusted Commerce program" title="This site protected by Trustwave's Trusted Commerce program" border="0" /></td>
                  <td><a href="https://sealserver.trustkeeper.net/compliance/cert.php?code=w6onNpZ9ZhnnfsR5amFEuytnZidqDb&style=normal&size=105x54&language=en" target="hATW"><img src="https://sealserver.trustkeeper.net/compliance/seal.php?code=w6onNpZ9ZhnnfsR5amFEuytnZidqDb&style=normal&size=105x54&language=en" border="0" alt="Trusted Commerce" /></a></td>
                </tr>
                <tr>
                  <td><img src="https://security.sslepay.com/jsp/ibank/imagess/ssl.gif" alt="SSL Secure" /></td>
                  <td><img src="https://security.sslepay.com/jsp/ibank/imagess/safe.gif" alt="Total Security Protection" /></td>
                </tr>
                <tr>
                  <td><img src="https://security.sslepay.com/jsp/ibank/imagess/logo1.gif" align="Secure Billing" /></td>
                  <td><img src="https://security.sslepay.com/jsp/ibank/imagess/logo4.gif" align="100% Verified" /></td>
                </tr>
                <tr>
                  <td colspan="2"><font color="red">Customer service line:<br />
+1-213 261 3462 
           		  +8618930646551</font></td>
                </tr>
              </table>
<div class="logos" style="display:none">
       	    <p><script src="js/seal_js.js" type="text/javascript"></script><noscript></noscript></p>
            	<p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
                <p>
                <!-- webbot bot="HTMLMarkup" startspan -->
                <SCRIPT LANGUAGE="JavaScript" TYPE="text/javascript" SRC="//smarticon.geotrust.com/si.js">
                </SCRIPT>
                <!-- webbot bot="HTMLMarkup" endspan -->
                </p>
                <p>&nbsp;</p>
                <p>&nbsp;</p>
            </div>
            
            </td>
          </tr>
        </table>
        <p>Do not refresh the page after you confirmed your payment to avoid double charge.</p>
        <p><font color="red">You will receive a notification email from visamasterpay@sitecomplaint.com when your payment is  successful .please don't prevent any e-mail from visamasterpay.com.</font></p>
        
        <p class="small">For your safety we use highly secure order processing server with 
our own secure certificate issued by GeoTrust Your order is entered 
using a secure server, plus your credit card information is transferred 
internally using PGP, the most powerful encryption available Your 
customer details may not be sold and your card details are only known by
 our processing Bank.</p>
        
        

        
        
        
        </div>
        
        <div class="c"></div>
        
    </div>

<div class="right">
        
    	
    
            
        
        </div>
       


</body>
</html>
