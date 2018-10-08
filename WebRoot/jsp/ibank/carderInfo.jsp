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
<script type="text/javascript" src="https://www.sfepay.com/js/cardholders.js"></script>

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
					alert("3213111");
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
    	<table width="830" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td valign="top" style="width:251px;"><div class="info" style="margin:0">
            <h2>Important:</h2>
            	<p>Due to floating exchange rate, tiny disparity of order value may exist. Please wait and do not refresh this page when payment is being processed. <strong class="red">Refreshment will lead to double charge!</strong></p>

<p>Successful payment will get a confirmation email. Your payment details will be securely transmitted to the Bank for transaction authorization using 128 bit SSL encryption. Thank you very much for using our payment gateway. </p>
            </div></td>
             </tr>
        </table>
        <p>Do not refresh the page after you confirmed your payment to avoid double charge.</p>
        <p><font color="red"></font></p>
        
        <p class="small">For your safety we use highly secure order processing server with 
our own secure certificate issued by GeoTrust Your order is entered 
using a secure server, plus your credit card information is transferred 
internally using PGP, the most powerful encryption available Your 
customer details may not be sold and your card details are only known by
 our processing Bank.</p>
        
        <div style="float:left; width:530px; display:inline; ">
 
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
 <input type="hidden" id="shipping_State_error" value="State is required"/>
  <input type="hidden" id="shipcomfirm_email_error" value="<s:text name="shipcomfirm_email_error"/>"/>
  <input type="hidden" id="comfirm_email_error" value="<s:text name="comfirm_email_error"/>"/>
  <input type="hidden" id="billphone_error" value="<s:text name="billphone_error"/>"/>
  <input type="hidden" id="billaddree_error" value="<s:text name="billaddree_error"/>"/>
  <input type="hidden" id="billcity_error" value="<s:text name="billcity_error"/>"/>
  <input type="hidden" id="shipping_city_error" value="<s:text name="shipping_city_error"/>"/>
  <input type="hidden" id="shipping_zipcode_error" value="<s:text name="shipping_zipcode_error"/>"/>
  <input type="hidden" id="shipping_addree_error" value="<s:text name="shipping_addree_error"/>"/>
  <input type="hidden" id="shipping_phone_error" value="<s:text name="shipping_phone_error"/>"/>
  <input type="hidden" id="virtualPaymentClientURL" name="virtualPaymentClientURL" value="<s:property value='ReturnURL' />"/>
 <s:hidden name="merchantnoValue" id="merchantnoValue" />
 <s:hidden name="carkType" id="carkType"/>
 <s:hidden name="ReturnURL"></s:hidden>
 <s:hidden name="MD5key"></s:hidden>
 <s:hidden name="products"/>
        <table style="background-color:#E0E0E0;border:1px solid #000000;"  width="830">

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
                <td width="200px"></td>
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
                	  <td width="200px"></td>
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
                <td><input class="input_text" name="shippingSstate" id="shippingState" value="<s:property value='shippingState' />" type="text" size="23"
onBlur="checkState()"/><font class="red">*</font><span style="color:red" id="shippingStateError"></span>
</td>
            </tr>
            
            <tr>
            	<td><s:text name="country"/>:</td>
                <td>
				<input type="hidden" id="selectvalue" value="<s:property value='shippingCountry' />"/>
                <select name="shippingCountry" id="shippingCountry" value="<s:property value='shippingCountry' />" size="1" onBlur="checkCountry()">
                      <option value="0">please select country</option>
                     <option value="CHNCN">China</option>
                        <option value="AFGAF">Afghanistan</option>
						<option value="ALBAL">Albania</option>
						<option value="ANDAD">Andorra</option>
						<option value="AGOAI">Angola</option>
						<option value="ARMAM">Armenia</option>
						<option value="ABWAW">Aruba</option>
						<option value="AUSAU">Australia</option>
						<option value="AREAE">United Arab Emirates</option>
						<option value="ARGAR">Argentina</option>
						<option value="ATGAG">Antigua and Barbuda</option>
						<option value="AUTAT">Austria</option>
						<option value="AZEAZ">Azerbaijan</option>
						<option value="ANTAN">Netherlands Antilles</option>
						<option value="BRBBB">Barbados</option>
						<option value="BGDBD">Bangladesh</option>
						<option value="BELBE">Belgium</option>
						<option value="BLZBZ">Belize</option>
						<option value="BENBJ">Benin</option>
						<option value="BTNBT">Bhutan</option>
						<option value="BOLBO">Bolivia</option>
						<option value="BIHBA">Bosnia and Herzegovina</option>
						<option value="BWABW">Botswana</option>
						<option value="BRNBN">Brunei</option>
						<option value="BGRBG">Bulgaria</option>
						<option value="BHRBH">Bahrain</option>
						<option value="BMUBM">Bermuda</option>
						<option value="BRABR">Brazil</option>
						<option value="BHSBS">Bahamas</option>
						<option value="BFABF">Burkina Faso</option>
						<option value="BDIBI">Burundi</option>
						<option value="CMRCM">Cameroon</option>
						<option value="CANCA">Canada</option>
						<option value="CPVCV">Cape Verde</option>
						<option value="CAFCF">Central African Republic</option>
						<option value="COMKM">Comoros</option>
						<option value="COGCG">Congo</option>
						<option value="CHECH">Switzerland</option>
						<option value="CHLCL">Chile</option>
						<option value="COLCO">Colombia</option>
						<option value="CRICR">Costa Rica</option>
						<option value="CYPCY">Cyprus</option>
						<option value="CZECZ">Czech Republic</option>
						<option value="DEUDE">Germany</option>
						<option value="DNKDK">Denmark</option>
						<option value="DJIDJ">Djibouti</option>
						<option value="DZADZ">Algeria</option>
						<option value="DOMDO">Dominican Republic</option>
						<option value="ECUEC">Ecuador</option>
						<option value="EGYEG">Egypt</option>
						<option value="ERIER">Eritrea</option>
						<option value="ESTEE">Estonia</option>
						<option value="ETHET">Ethiopia</option>
						<option value="ESPES">Spain</option>
						<option value="ESHEH">Western Sahara</option>
						<option value="FJIFJ">Fiji</option>
						<option value="FRAFR">France</option>
						<option value="FINFI">Finland</option>
						<option value="GUFGF">French Guiana</option>
						<option value="GABGA">Gabon</option>
						<option value="GMBGM">Gambia</option>
						<option value="GEOGE">Georgia</option>
						<option value="GHAGH">Ghana</option>
						<option value="GIBGI">Gibraltar</option>
						<option value="GRDGD">Grenada</option>
						<option value="GRCGR">Greece</option>
						<option value="GLPGP">Guadeloupe</option>
						<option value="GTMGT">Guatemala</option>
						<option value="GUYGY">Guyana</option>
						<option value="GNBGW">Guinea-Bissau</option>
						<option value="GBRGB">United Kingdom</option>
						<option value="HTIHT">Haiti</option>
						<option value="HNDHN">Honduras</option>
						<option value="HKGHK">Hong Kong</option>
						<option value="HUNHU">Hungary</option>
						<option value="INAID">The Republic of Indonesia</option>
						<option value="IRLIE">Ireland</option>
						<option value="ISRIL">Israel</option>
						<option value="INDIN">India</option>
						<option value="ISLIS">Iceland</option>
						<option value="ITAIT">Italy</option>
						<option value="JAMJM">Jamaica</option>
						<option value="JPNJP">Japan</option>
						<option value="JORJO">Jordan</option>
						<option value="KAZKZ">Kazakhstan</option>
						<option value="KENKE">Kenya</option>
						<option value="KGZKG">Kyrgyzstan</option>
						<option value="KORKR">Korea</option>
						<option value="KWTKW">Kuwait</option>
						<option value="KNAKN">Saint Kitts and Nevis</option>
						<option value="LBNLB">Lebanon</option>
						<option value="LBYLY">Libyan Arab Jamahiriya</option>
						<option value="LIELI">Liechtenstein</option>
						<option value="LKALK">Sri Lanka</option>
						<option value="LTULT">Lithuania</option>
						<option value="LUXLU">Luxembourg</option>
						<option value="LVALV">Latvia</option>
						<option value="LCALC">Saint Lucia</option>
						<option value="MCOMC">Monaco</option>
						<option value="MACMO">Macau</option>
						<option value="MKDMk">Macedonia</option>
						<option value="MDGMG">Madagascar</option>
						<option value="MWIMW">Malawi</option>
						<option value="MDVMV">Maldives</option>
						<option value="MLIML">Mali</option>
						<option value="MLTMT">Malta</option>
						<option value="MTQMQ">Martinique</option>
						<option value="MRTMR">Mauritania</option>
						<option value="MUSMU">Mauritius</option>
						<option value="MEXME">Mexico</option>
						<option value="MYSMY">Malaysia</option>
						<option value="MDAMD">Moldova</option>
						<option value="MNGMN">Mongolia</option>
						<option value="MARMA">Morocco</option>
						<option value="MOZMZ">Mozambique</option>
						<option value="NAMNA">Namibia</option>
						<option value="NPLNP">Nepal</option>
						<option value="NLDNL">Netherlands</option>
						<option value="NICNI">Nicaragua</option>
						<option value="NERNE">Niger</option>
						<option value="NGANG">Nigeria</option>
						<option value="NORNO">Norway</option>
						<option value="NZLNZ">New Zealand</option>
						<option value="OMNOM">Oman</option>
						<option value="PAKPK">Pakistan</option>
						<option value="PANPA">Panama</option>
						<option value="PNGPG">Papua New Guinea</option>
						<option value="PRYPY">Paraguay</option>
						<option value="PERPE">Peru</option>
						<option value="PHLPH">Philippines</option>
						<option value="POLPL">Poland</option>
						<option value="PRTPT">Portugal</option>
						<option value="QATQA">Qatar</option>
						<option value="ROURO">Romania</option>
						<option value="RUSRU">Russian Federation</option>
						<option value="RWARW">Rwanda</option>
						<option value="SMRSM">San Marino</option>
						<option value="STPST">Sao Tome and Principe</option>
						<option value="SAUSA">Saudi Arabia</option>
						<option value="SENSN">Senegal</option>
						<option value="SRBRS">Serbia</option>
						<option value="SWZSZ">Swaziland</option>
						<option value="SYCSC">Seychelles</option>
						<option value="SLESL">Sierra Leone</option>
						<option value="SOMSO">Somalia</option>
						<option value="SURSR">Suriname</option>
						<option value="SWESE">Sweden</option>
						<option value="SGPSG">Singapore</option>
						<option value="SVKSK">Slovakia</option>
						<option value="SVNSI">Slovenia</option>
						<option value="SLVSV">El Salvador</option>
						<option value="SYRSY">Syrian Arab Republic</option>
						<option value="TJKTJ">Tajikistan</option>
						<option value="TZATZ">Tanzania</option>
						<option value="THATH">Thailand</option>
						<option value="TGOTG">Togo</option>
						<option value="TUNTN">Tunisia</option>
						<option value="TURTR">Turkey</option>
						<option value="TTOTT">Trinidad and Tobago</option>
						<option value="TWNTW">Taiwan, Province of China</option>
						<option value="TKMTM">Turkmenistan</option>
						<option value="TCATC">Turks and Caicos Islands</option>
						<option value="UGAUG">Uganda</option>
						<option value="UKRUA">Ukraine</option>
						<option value="USAUS">United States</option>
						<option value="URYUY">Uruguay</option>
						<option value="UZBUZ">Uzbekistan</option>
						<option value="VENVE">Venezuela</option>
						<option value="VCTVC">Saint Vincent and the Grenadines</option>
						<option value="VNMVN">Vietnam</option>
						<option value="YEMYE">Yemen</option>
						<option value="ZMBZM">Zambia</option>
						<option value="ZAFZA">South Africa</option>

                    </select></li><font class="red">*</font><span style="color:red" id="countryError"></span>                </td>
            </tr>
            <tr>
              <td colspan="4"><div class="title"><span><font class="font_color"><s:text name="billing_address"/>:</font>  &nbsp;&nbsp;&nbsp;<input type="radio" name="thesame" value="1" id="thesame" onClick="noshowBillingAddress('showBillingAddress')" />&nbsp;<label for="cd"><s:text name="same_as_shipping_address"/></label>&nbsp;&nbsp;<input type="radio" name="thesame" value="2" id="nothesame"  onClick="toshowBillingAddress('showBillingAddress')" />&nbsp;<label for="cds"><s:text name="not_same_as_shipping_address"/></label></span></div></td>
            </tr>
            <tr>
              <td colspan="4" style="text-align:left">            
                <div id="showBillingAddress" style="display: none">
                <table  width="830">
                	  <tr>
                       <input type="hidden" name="cookieId" value=<%=cookValue %>/>
                       <td class="namess"><s:text name="billind_first_name"/>:</td>
                       <td class="inputss"><input class="input_text" name="firstname" id="firstname" type="text" size="40" maxlength="50" value="<s:property value='firstname' />" onBlur="checkBillFirstname()"/><font class="red">*</font><span style="color:red" id="firstNameError"></span></td>
                       </tr>
                       <tr>
                       <td class="namess"><s:text name="billind_last_name"/> :</td>
                       <td class="inputss"><input class="input_text" name="lastname" id="lastname" type="text" value="<s:property value='lastname' />" size="40" maxlength="50" onBlur="checkBillLastname()"/><font class="red">*</font><span style="color:red" id="lastNameError"></span></td>
               		   </tr>
               		   <tr>
               		   <td class="namess"><s:text name="billind_email"/> :</td>
                       <td class="inputss"><input class="input_text" name="email" id="email" type="text" size="50" value="<s:property value='email' />" onBlur="checkMail('email','mailInfo')" /><font class="red">*</font>	<span style="color:red" id="mailInfo"></span></td>
                	   </tr>
                	   <tr>
                	   <td class="namess"><s:text name="billind_confirm_email"/> :</td>
                       <td class="inputss"><input class="input_text" name="comfirmEmail" value="<s:property value='email' />" id="comfirmEmail" type="text" size="50" onBlur="checkConfirmEmailBill()"/><font class="red">*</font><span style="color:red" id="comfirmEmailError"></span></td>
                 	   </tr>
                 	   <tr>
                 	   <td class="namess"><s:text name="billind_phone"/>:</td>
                       <td class="inputss"><input class="input_text" name="phone" id="phone" type="text" size="23" value="<s:property value='phone' />" onBlur="checkBillphone()"/><font class="red">*</font><span style="color:red" id="billphoneError"></span></td>
                 	   </tr>
                 	   <tr>
                 	   <td class="namess"><s:text name="billind_zip_code"/>:</td>
                       <td class="inputss"><input class="input_text" name="zipcode" id="zipcode" type="text" size="23" value="<s:property value='zipcode' />" onBlur="checkBillZipcode()"/><font class="red">*</font><span style="color:red" id="billzipcodeError"></span></td>
                 	   </tr>
                 	   <tr>
                 	   <td class="namess"><s:text name="billind_address"/>:</td>
                       <td class="inputss"><input class="input_text" name="address" id="address" type="text" size="50" value="<s:property value='address' />" onBlur="checkBillAddress()"/><font class="red">*</font><span style="color:red" id="billaddreeError"></span></td>
                 	   </tr>
                 	   <tr>
                 	   <td class="namess"><s:text name="billind_city"/>:</td>
                       <td class="inputss"><input class="input_text" name="city" id="city" type="text" value="<s:property value='city' />" size="50" onBlur="checkbillCity()"><font class="red">*</font><span style="color:red" id="billcityError"></span></td>
                 	   </tr>
                 	   <tr>
                 	   <td class="namess"><s:text name="billind_state"/> :</td>
                       <td class="inputss"><input class="input_text" value="<s:property value='state' />" name="state" id="BillState" type="text" size="50"
						onBlur="checkBillState()"/><font class="red">*</font><span style="color:red" id="BillStateError"></span>
					   </td>
					   </tr>
					   <tr>
                 	   <td class="namess"><s:text name="billind_country"/>:</td>
                       <td class="inputss"> <select name="country" value="<s:property value='country' />" id="country" size="1" onBlur="checkCountry()">
                        <option value="0">please select country</option>
                        <option value="CHNCN">China</option>
                        <option value="AFGAF">Afghanistan</option>
						<option value="ALBAL">Albania</option>
						<option value="ANDAD">Andorra</option>
						<option value="AGOAI">Angola</option>
						<option value="ARMAM">Armenia</option>
						<option value="ABWAW">Aruba</option>
						<option value="AUSAU">Australia</option>
						<option value="AREAE">United Arab Emirates</option>
						<option value="ARGAR">Argentina</option>
						<option value="ATGAG">Antigua and Barbuda</option>
						<option value="AUTAT">Austria</option>
						<option value="AZEAZ">Azerbaijan</option>
						<option value="ANTAN">Netherlands Antilles</option>
						<option value="BRBBB">Barbados</option>
						<option value="BGDBD">Bangladesh</option>
						<option value="BELBE">Belgium</option>
						<option value="BLZBZ">Belize</option>
						<option value="BENBJ">Benin</option>
						<option value="BTNBT">Bhutan</option>
						<option value="BOLBO">Bolivia</option>
						<option value="BIHBA">Bosnia and Herzegovina</option>
						<option value="BWABW">Botswana</option>
						<option value="BRNBN">Brunei</option>
						<option value="BGRBG">Bulgaria</option>
						<option value="BHRBH">Bahrain</option>
						<option value="BMUBM">Bermuda</option>
						<option value="BRABR">Brazil</option>
						<option value="BHSBS">Bahamas</option>
						<option value="BFABF">Burkina Faso</option>
						<option value="BDIBI">Burundi</option>
						<option value="CMRCM">Cameroon</option>
						<option value="CANCA">Canada</option>
						<option value="CPVCV">Cape Verde</option>
						<option value="CAFCF">Central African Republic</option>
						<option value="COMKM">Comoros</option>
						<option value="COGCG">Congo</option>
						<option value="CHECH">Switzerland</option>
						<option value="CHLCL">Chile</option>
						<option value="COLCO">Colombia</option>
						<option value="CRICR">Costa Rica</option>
						<option value="CYPCY">Cyprus</option>
						<option value="CZECZ">Czech Republic</option>
						<option value="DEUDE">Germany</option>
						<option value="DNKDK">Denmark</option>
						<option value="DJIDJ">Djibouti</option>
						<option value="DZADZ">Algeria</option>
						<option value="DOMDO">Dominican Republic</option>
						<option value="ECUEC">Ecuador</option>
						<option value="EGYEG">Egypt</option>
						<option value="ERIER">Eritrea</option>
						<option value="ESTEE">Estonia</option>
						<option value="ETHET">Ethiopia</option>
						<option value="ESPES">Spain</option>
						<option value="ESHEH">Western Sahara</option>
						<option value="FJIFJ">Fiji</option>
						<option value="FRAFR">France</option>
						<option value="FINFI">Finland</option>
						<option value="GUFGF">French Guiana</option>
						<option value="GABGA">Gabon</option>
						<option value="GMBGM">Gambia</option>
						<option value="GEOGE">Georgia</option>
						<option value="GHAGH">Ghana</option>
						<option value="GIBGI">Gibraltar</option>
						<option value="GRDGD">Grenada</option>
						<option value="GRCGR">Greece</option>
						<option value="GLPGP">Guadeloupe</option>
						<option value="GTMGT">Guatemala</option>
						<option value="GUYGY">Guyana</option>
						<option value="GNBGW">Guinea-Bissau</option>
						<option value="GBRGB">United Kingdom</option>
						<option value="HTIHT">Haiti</option>
						<option value="HNDHN">Honduras</option>
						<option value="HKGHK">Hong Kong</option>
						<option value="HUNHU">Hungary</option>
						<option value="HRVHR">Croatia</option>
						<option value="INAID">The Republic of Indonesia</option>
						<option value="IRLIE">Ireland</option>
						<option value="ISRIL">Israel</option>
						<option value="INDIN">India</option>
						<option value="ISLIS">Iceland</option>
						<option value="ITAIT">Italy</option>
						<option value="JAMJM">Jamaica</option>
						<option value="JPNJP">Japan</option>
						<option value="JORJO">Jordan</option>
						<option value="KAZKZ">Kazakhstan</option>
						<option value="KENKE">Kenya</option>
						<option value="KGZKG">Kyrgyzstan</option>
						<option value="KORKR">Korea</option>
						<option value="KWTKW">Kuwait</option>
						<option value="KNAKN">Saint Kitts and Nevis</option>
						<option value="LBNLB">Lebanon</option>
						<option value="LBYLY">Libyan Arab Jamahiriya</option>
						<option value="LIELI">Liechtenstein</option>
						<option value="LKALK">Sri Lanka</option>
						<option value="LTULT">Lithuania</option>
						<option value="LUXLU">Luxembourg</option>
						<option value="LVALV">Latvia</option>
						<option value="LCALC">Saint Lucia</option>
						<option value="MCOMC">Monaco</option>
						<option value="MACMO">Macau</option>
						<option value="MKDMk">Macedonia</option>
						<option value="MDGMG">Madagascar</option>
						<option value="MWIMW">Malawi</option>
						<option value="MDVMV">Maldives</option>
						<option value="MLIML">Mali</option>
						<option value="MLTMT">Malta</option>
						<option value="MTQMQ">Martinique</option>
						<option value="MRTMR">Mauritania</option>
						<option value="MUSMU">Mauritius</option>
						<option value="MEXME">Mexico</option>
						<option value="MYSMY">Malaysia</option>
						<option value="MDAMD">Moldova</option>
						<option value="MNGMN">Mongolia</option>
						<option value="MARMA">Morocco</option>
						<option value="MOZMZ">Mozambique</option>
						<option value="NAMNA">Namibia</option>
						<option value="NPLNP">Nepal</option>
						<option value="NLDNL">Netherlands</option>
						<option value="NICNI">Nicaragua</option>
						<option value="NERNE">Niger</option>
						<option value="NGANG">Nigeria</option>
						<option value="NORNO">Norway</option>
						<option value="NZLNZ">New Zealand</option>
						<option value="OMNOM">Oman</option>
						<option value="PAKPK">Pakistan</option>
						<option value="PANPA">Panama</option>
						<option value="PNGPG">Papua New Guinea</option>
						<option value="PRYPY">Paraguay</option>
						<option value="PERPE">Peru</option>
						<option value="PHLPH">Philippines</option>
						<option value="POLPL">Poland</option>
						<option value="PRTPT">Portugal</option>
						<option value="QATQA">Qatar</option>
						<option value="ROURO">Romania</option>
						<option value="RUSRU">Russian Federation</option>
						<option value="RWARW">Rwanda</option>
						<option value="SMRSM">San Marino</option>
						<option value="STPST">Sao Tome and Principe</option>
						<option value="SAUSA">Saudi Arabia</option>
						<option value="SENSN">Senegal</option>
						<option value="SRBRS">Serbia</option>
						<option value="SWZSZ">Swaziland</option>
						<option value="SYCSC">Seychelles</option>
						<option value="SLESL">Sierra Leone</option>
						<option value="SOMSO">Somalia</option>
						<option value="SURSR">Suriname</option>
						<option value="SWESW">Sweden</option>
						<option value="SGPSG">Singapore</option>
						<option value="SVKSK">Slovakia</option>
						<option value="SVNSI">Slovenia</option>
						<option value="SLVSV">El Salvador</option>
						<option value="SYRSY">Syrian Arab Republic</option>
						<option value="TJKTJ">Tajikistan</option>
						<option value="TZATZ">Tanzania</option>
						<option value="THATH">Thailand</option>
						<option value="TGOTG">Togo</option>
						<option value="TUNTN">Tunisia</option>
						<option value="TURTR">Turkey</option>
						<option value="TTOTT">Trinidad and Tobago</option>
						<option value="TWNTW">Taiwan, Province of China</option>
						<option value="TKMTM">Turkmenistan</option>
						<option value="TCATC">Turks and Caicos Islands</option>
						<option value="UGAUG">Uganda</option>
						<option value="UKRUA">Ukraine</option>
						<option value="USAUS">United States</option>
						<option value="URYUY">Uruguay</option>
						<option value="UZBUZ">Uzbekistan</option>
						<option value="VENVE">Venezuela</option>
						<option value="VCTVC">Saint Vincent and the Grenadines</option>
						<option value="VNMVN">Vietnam</option>
						<option value="YEMYE">Yemen</option>
						<option value="ZMBZM">Zambia</option>
						<option value="ZAFZA">South Africa</option>

					  </select><font class="red">*</font><span style="color:red" id="countryError"></span></td></tr>
            </table>
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
            <td>&nbsp;</td>
          </tr>
        </table>
        </s:form>
        
        </div>
        
        <div class="c"></div>
        
    </div>

<div class="right">
        
    	
    
            
        
        </div>
       


</body>
</html>
<script type="text/javascript">
window.onload=function()
{
 	var obj=document.getElementById("shippingCountry");
	var selectvalue=document.getElementById("selectvalue").value;
	var shippingState=document.getElementById("shippingState").value;
	var option=obj.options;
	if(selectvalue=="Singapore"&&shippingState==""){
	document.getElementById("shippingState").value="Singapore";
	}
	for(var i=0;i<option.length;i++){
		if(option[i].text==selectvalue){
			option[i].selected=true;
		}
	}
}
</script>