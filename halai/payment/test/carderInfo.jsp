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
<style type="text/css">
<!--
.text11 {width:200px;
height:19px;
border:1px #cbcfd0 solid ;
}
.text11 {width:200px;
height:19px;
border:1px #cbcfd0 solid ;
}
-->
</style>
<head>
    <title>Payment</title>
    
	<meta https-equiv="pragma" content="no-cache">
	<meta https-equiv="cache-control" content="no-cache">
	<meta https-equiv="expires" content="0">    
	<meta https-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta https-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<link href="https://www.ecpss.com/jsp/ibank/css/style.css" rel="stylesheet" type="text/css" />
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
			if(cardnumFlag){
				if(cvvnumsize==16){
					document.getElementById('cardnumError').innerHTML='';
					var merNo = document.getElementById("merchantnoValue").value;
					//var carkType = document.getElementById("carkType").value;
					getCardInfo('getCardTypeByJson.action?cardnum='+cardnum+'&merNo='+merNo);
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
<style type="text/css">
<!--
.text1 {width:200px;
height:19px;
border:1px #cbcfd0 solid ;
}
body {
	background-color: #FFFFFF;
}
-->
</style>
</head>

<body>
<s:form name="form1" id="form1" action="testaddCarderInfoAction" method="post" theme="simple">
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
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
  <td height="106" valign="top" background="https://www.ecpss.com/jsp/ibank/images/123_r1_c1.jpg">&nbsp;</td>  
  </tr>
</table>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30" bgcolor="#1E638C"><table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td align="left" valign="middle" bgcolor="#1E638C" class="zi2"></td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="9" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
</table>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="217" align="left" valign="top" bgcolor="#FFFFFF"><table width="217" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="https://www.ecpss.com/jsp/ibank/images/top1.jpg" width="217" height="17" /></td>
      </tr>
      <tr>
        <td><table width="217" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="15" background="https://www.ecpss.com/jsp/ibank/images/right1.jpg">&nbsp;</td>
            <td width="191" bgcolor="#1E638C"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td align="center" valign="middle"><img src="https://www.ecpss.com/jsp/ibank/images/tp.jpg" border="0" usemap="#Map2" />
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="5"></td>
                    </tr>
                  </table>
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="1" bgcolor="#E0E0E0"></td>
                    </tr>
                  </table>
                  <table width="100%" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td height="5"></td>
                    </tr>
                  </table></td>
                </tr>
              <tr>
                <td bgcolor="#1E638C"><span class="zi2"><s:text name="center_message"/></span></td>
                </tr>
            </table></td>
            <td width="11" background="https://www.ecpss.com/jsp/ibank/images/left1.jpg">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><img src="https://www.ecpss.com/jsp/ibank/images/bottom1.jpg" width="217" height="22" /></td>
      </tr>
    </table></td>
    <td width="786" align="left" valign="top"><table width="583" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td><img src="https://www.ecpss.com/jsp/ibank/images/top2.jpg" width="583" height="13" /></td>
      </tr>
      <tr>
        <td><table width="583" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="12" background="https://www.ecpss.com/jsp/ibank/images/right2.jpg">&nbsp;</td>
            <td width="558" align="center" valign="top" bgcolor="#FFFFFF"><table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
              <tr>
                <td height="25" align="left" valign="middle"><b class="zi23"><s:text name="require"/></b></td>
              </tr>
            </table>
              <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="1" bgcolor="#CCCCCC"></td>
                </tr>
              </table>
              <table width="96%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="5"></td>
                </tr>
              </table>
              <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">

                <tr>
                  <td height="5" align="left" valign="middle"></td>
                </tr>
                <tr> 
                  <td height="30" align="left" valign="middle"><span class="zi8"><s:text name="order_information"/>: </span></td>
                </tr>
              </table>
              <table width="96%" border="0" cellpadding="0" cellspacing="0" bgcolor="#F5F5F5" class="kuang1">
                <tr>
                  <td width="205" height="30" align="left" valign="middle"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="zi4"><s:text name="payment_amount"/>:</span></td>
                      </tr>
                    </table></td>
                  <td width="235" align="left" valign="middle" class="zi4"><s:property value="Amount" />&nbsp;&nbsp;<s:property value="currencyName"/></td>
                  <td width="161" align="left" valign="middle">&nbsp;</td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="zi4"><s:text name="merchant_order_no"/>: </span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4"><s:property value="BillNo"/></td>
                  <td align="left" valign="middle">&nbsp;</td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr> 
                        <td align="left" valign="middle"><span class="zi4"><s:text name="ecpss_order_no"/>: </span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle"><span class="zi4"><s:property value="rorderno"/></span></td>
                  <td align="left" valign="middle"><input type="hidden" name="rorderno" value="<s:property value="rorderno"/>"></td>
                </tr>
              </table>
              <table width="96%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="5"></td>
                </tr>
              </table>
              <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr> 
                  <td height="30" align="left" valign="middle"><span class="zi8"><s:text name="credit_card_information"/>: </span></td>
                </tr>
              </table>
              <table width="96%" border="0" cellpadding="0" cellspacing="0" bgcolor="#F5F5F5" class="kuang1">
                <tr>
                  <td width="171" height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="credit_card_number"/>:</span></td>
                      </tr>
                    </table></td>
                  <td width="245" align="left" valign="middle" class="zi4">
                  <input class=text1 name="cardnum" id="cardnum" type="text" size="23" onBlur="checkcCardnum()"/>
                    <font color="red"> * </font></td>
                  <td width="109" align="left" valign="middle" class="zi4"><span style="color:red" id="cardnumError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>  
                        <td align="left" valign="middle"><span class="STYLE19"> <s:text name="card_verification_number"/>: </span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4"><label>
                    <input class=text2 name="cvv2" id="cvv2" type="password" size="5" onBlur="checkCvv2()"/>
cvv2<font color="red"><img src="https://www.ecpss.com/jsp/ibank/images/index_888.gif"/> * </font> </label></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="cvv2Error"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"> <s:text name="card_cxpiry_date"/>: </span></td>
                      </tr>
                  </table></td>
                  <td align="left" valign="middle" class="zi4">MM<s:select name="month" id="month" headerKey="" headerValue="--" list="#@java.util.TreeMap@{'01':'01','02':'02','03':'03','04':'04','05':'05','06':'06','07':'07','08':'08','09':'09','10':'10','11':'11','12':'12'}" ></s:select>
                    /
                    YY<s:select name="year" id="year" headerKey="" headerValue="--" list="#@java.util.TreeMap@{'09':'09','10':'10','11':'11','12':'12','13':'13','14':'14','15':'15','16':'16','17':'17','18':'18','19':'19'}" ></s:select>
      <font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="dateInfo"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="issuing_bank"/>: </span></td>
                      </tr>
                  </table></td>
                  <td align="left" valign="middle" class="zi4"><label>
                    <input class=text1 name="cardbank" id="cardbank" type="text" size="23" value="<s:property value='cardbank' />" onBlur="checkcCardname()"/>
                    <font color="red"> * </font> </label></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="cardnameError"></span><s:text name="bank_name_message"/></td>
                </tr>
              </table>
              <table width="96%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="5"></td>
                </tr>
              </table>
              <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="30" align="left" valign="middle"><span class="zi8"><s:text name="shipping_address"/>:</span></td>
                </tr>
              </table>
              <table width="96%" border="0" cellpadding="0" cellspacing="0" bgcolor="#F5F5F5" class="kuang1">
                <tr>
                 <td width="130" height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"> <s:text name="first_name"/>: </span></td>
                      </tr>
                    </table></td>
               
               
                  <td align="left" valign="middle" class="zi4"><input class=text11 name="shippingFirstName" id="shippingFirstName" type="text" size="50" value="<s:property value='shippingFirstName' />"  onBlur="checkSFirstname()"/> <font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="shippingFirstError"></span></td>
                </tr>
                <tr>
                <td width="130" height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"> <s:text name="last_name"/>: </span></td>
                      </tr>
                    </table></td>
                 
                  <td align="left" valign="middle" class="zi4"><input class=text11 name="shippingLastName" id="shippingLastName" type="text" size="50" value="<s:property value='shippingLastName' />" onBlur="checkSLastname()" /><font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="shippingLastError"></span></td>
                </tr>
                <tr>
                  <td width="130" height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"> <s:text name="email"/>: </span></td>
                      </tr>
                    </table></td>
                  <td width="235" align="left" valign="middle" class="zi4">
 <input class=text1 name="shippingEmail" id="shippingEmail" type="text" size="50" value="<s:property value='shippingEmail' />" onBlur="checkMail('shippingEmail','shippingmailInfo')" />
                      <font color="red"> * </font> </td>
                  <td width="161" align="left" valign="middle" class="zi4">	<span style="color:red" id="shippingmailInfo"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="confirm_email"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4">
                   <input class=text1 name="shippingComfirmEmail"  value="<s:property value='shippingEmail' />" id="shippingComfirmEmail" type="text" size="50" onBlur="checkConfirmEmail()"/>
                      <font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="shipcomfirmEmailError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="phone"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4"><label>
                   <input class=text1 name="shippingPhone" id="shippingPhone" type="text" value="<s:property value='shippingPhone' />" size="23" onBlur="checkShipPhone()"/>
                    <font color="red"> * </font></label></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="shippingphoneError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="zip_code"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4">
                  <input class=text1 name="shippingZipcode" id="shippingZipcode" type="text" value="<s:property value='shippingZipcode' />" size="23" onBlur="checkShipZipcode()"/>
                    <font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="shippingzipcodeError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="address"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4">
                  <input class=text1 name="shippingAddress" id="shippingAddress" value="<s:property value='shippingAddress' />" type="text" size="50" onBlur="checkShipAddress()"/>
                    <font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="shippingaddreeError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="city"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4">
                  <input class=text1 name="shippingCity" id="shippingCity" value="<s:property value='shippingCity' />" type="text" size="50" onBlur="checkShipCity()">
                    <font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="shippingCityError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="state"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4">
                   <input class="text1" name="shippingSstate" id="shippingState" value="<s:property value='shippingSstate' />" type="text" size="50"/></td>
                  <td align="left" valign="middle" class="zi4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="country"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4"><label>
                    <select name="shippingCountry" id="shippingCountry" value="<s:property value='shippingCountry' />" size="1" onBlur="checkCountry()">
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
                    </select>
                  </label>
                    <font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="countryError"></span></td>
                </tr>
              </table>        
              
             <table width="96%" border="0" align="center" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="30" align="left" valign="middle"><table width="360" border="0" cellspacing="0" cellpadding="0">
                    <tr>
                      <td width="110"><span class="zi8"><s:text name="billing_address"/>:
                          
                      </span></td>
                      <td width="20"><span class="zi8">
                        <input type="radio" name="thesame" value="1" id="thesame" onClick="noshowBillingAddress('showBillingAddress')" />
                      </span></td>
                      <td width="230"><span class="zi8"><s:text name="same_as_shipping_address"/></span></td>
                      <td width="20"><span class="zi8">
                        <input type="radio" name="thesame" value="2" id="nothesame"  onClick="toshowBillingAddress('showBillingAddress')" />

                      </span></td>
                      <td width="230"><span class="zi8"><s:text name="not_same_as_shipping_address"/></span></td>                    
                    </tr>
                  </table></td>
                </tr>
              </table>
              <div id="showBillingAddress" style="display:none;">
<table width="96%" border="0" cellpadding="0" cellspacing="0" bgcolor="#F5F5F5" class="kuang1">
                <tr>
                  <td width="130" height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="billind_first_name"/>: </span></td>
                      </tr>
                  </table></td>
                  <td width="235" align="left" valign="middle" class="zi4"><label>
                    <input class=text1 name="firstname" id="firstname" type="text" size="40" maxlength="50" value="<s:property value='firstname' />" onBlur="checkBillFirstname()"/>
                    <font color="red"> * </font>
                    <input type="hidden" name="cookieId" value=<%=cookValue %>/>
                  </label></td>
                  <td width="161" align="left" valign="middle" class="zi4"><span style="color:red" id="firstNameError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="billind_last_name"/>: </span></td>
                      </tr>
                  </table></td>
                  <td align="left" valign="middle" class="zi4"><input class=text1 name="lastname" id="lastname" type="text" value="<s:property value='lastname' />" size="40" maxlength="50" onBlur="checkBillLastname()"/>
                      <font color="red"> * </font> </td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="lastNameError"></span></td>
                </tr>
                
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"> <s:text name="billind_email"/>: </span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4">
                  <input class=text1 name="email" id="email" type="text" size="50" value="<s:property value='email' />" onBlur="checkMail('email','mailInfo')" />
                      <font color="red"> * </font> </td>
                  <td align="left" valign="middle" class="zi4">	<span style="color:red" id="mailInfo"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="billind_confirm_email"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4"><font color="red"><font color="red"><span class="zi8"><font color="red"><font color="red">
                    <input class=text11 name="comfirmEmail" value="<s:property value='email' />" id="comfirmEmail" type="text" size="50" onBlur="checkConfirmEmailBill()"/>
                  </font></font></span></font>* </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="comfirmEmailError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="billind_phone"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4"><label>
                   <input class=text1 name="phone" id="phone" type="text" size="23" value="<s:property value='phone' />" onBlur="checkBillphone()"/>
                    <font color="red"> * </font></label></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="billphoneError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="billind_zip_code"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4">
                  <input class=text1 name="zipcode" id="zipcode" type="text" size="23" value="<s:property value='zipcode' />" onBlur="checkBillZipcode()"/>
                    <font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="billzipcodeError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="billind_address"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4">
                  <input class=text1 name="address" id="address" type="text" size="50" value="<s:property value='address' />" onBlur="checkBillAddress()"/>
                    <font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="billaddreeError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="billind_city"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4">
                  <input class=text1 name="city" id="city" type="text" value="<s:property value='city' />" size="50" onBlur="checkbillCity()">
                    <font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="billcityError"></span></td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="billind_state"/>:</span></td>
                      </tr>
                    </table></td> 
                  <td align="left" valign="middle" class="zi4"><input class="text11" value="<s:property value='state' />" name="state" id="state" type="text" size="50"/></td>
                  <td align="left" valign="middle" class="zi4">&nbsp;</td>
                </tr>
                <tr>
                  <td height="30" align="left" valign="middle" class="zi4"><table width="92%" border="0" align="center" cellpadding="0" cellspacing="0">
                      <tr>
                        <td align="left" valign="middle"><span class="STYLE19"><s:text name="billind_country"/>:</span></td>
                      </tr>
                    </table></td>
                  <td align="left" valign="middle" class="zi4"><label>
                    <select name="country" value="<s:property value='country' />" id="country" size="1" onBlur="checkCountry()">
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
                    </select>
                  </label>
                    <font color="red"> * </font></td>
                  <td align="left" valign="middle" class="zi4"><span style="color:red" id="countryError"></span></td>
                </tr>
              </table> 
               </div>
              <table width="96%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="5"></td>
                </tr>
              </table>
              <table width="96%" border="0" cellpadding="0" cellspacing="0">
                <tr>
                  <td height="32" align="center" valign="middle" class="zi4">
                 <!--  <input type="button"  onclick="isSubmit()" style=" background:url(https://www.ecpss.com/jsp/ibank/images/anniu3.jpg); border:0; width:103px; height:28px;" />                  </td>
                 -->
                 <input type="button"  onclick="isSubmit()" value="<s:text name='submit'/>" style=" background:url(https://security.sslepay.com/images/anniu3.gif); border:0; width:103px; height:28px;color:#ffffff" />                  </td>
                
                </tr>
              </table>
              <table width="96%" border="0" cellspacing="0" cellpadding="0">
                <tr>
                  <td height="25" align="left" valign="middle" class="zi23"><strong><s:text name="note"/></strong></td>
                </tr>
              </table></td>
            <td width="23" background="https://www.ecpss.com/jsp/ibank/images/left2.jpg">&nbsp;</td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><img src="https://www.ecpss.com/jsp/ibank/images/bottom2.jpg" width="583" height="15" /></td>
      </tr>
    </table></td>
  </tr>
</table>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="8" bgcolor="#FFFFFF"></td>
  </tr>
</table>
<table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="1" bgcolor="#A8B7BE"></td>
  </tr>
</table><table width="800" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="80" align="center" valign="middle" bgcolor="#FFFFFF" class="zi12"><s:text name="last_message"/> </td>
  </tr>
</table>
</s:form>

<map name="Map"><area shape="rect" coords="118,24,179,79" href="#">
<area shape="rect" coords="35,25,114,84" href="#" target="_blank">
</map>
<map name="Map2"><area shape="rect" coords="3,7,148,60" href="#"><area shape="rect" coords="3,69,152,124" href="#"><area shape="rect" coords="8,129,153,191" href="#"><area shape="rect" coords="4,198,153,265" href="#"></map></body>
</html>
