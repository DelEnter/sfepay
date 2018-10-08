<!--验证发卡行名称-->
function checkcCardname(){
	var cardname = document.getElementById("cardbank").value;
	var cardnameError = document.getElementById("cardname").value;
	
	if(cardname==""){
		document.getElementById("cardnameError").innerHTML=cardnameError;
	}else{
		document.getElementById("cardnameError").innerHTML='';
	}
}
<!--验证cvv2-->
function checkCvv2(){
	var jgpattern =/^[0-9]+$/; 
	var cvv2 = document.getElementById("cvv2").value;
	var cvv2_error = document.getElementById("cvv2_error").value;
	var cvvnum = cvv2.length;
	cvv2Flag =  jgpattern.test(cvv2);
	if(cvv2Flag){
		if(cvvnum==3){
			document.getElementById('cvv2Error').innerHTML='';
			return true;
		}else{
			document.getElementById('cvv2Error').innerHTML=cvv2_error;
			return false;
		}
	}else{
		document.getElementById('cvv2Error').innerHTML=cvv2_error;
		return false;
	}
}
	<!--检验邮箱-->
		
		function checkMail(inputName,divValue)
		{
			var email = document.getElementById(inputName).value;
			var mail_info = document.getElementById("mail_info").value;
			
			var pattern = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			chkFlag = pattern.test(email);
			if(chkFlag)
			{
				document.getElementById(divValue).innerHTML='';
				return true;
			}
			else
			{
				document.getElementById(divValue).innerHTML=mail_info; 
				return false;
			}	
		}
<!--检验firstname-->
		function checkFirstName(){
			var firstname = document.getElementById("firstname").value;
			var first_name_error = document.getElementById("first_name_error").value;
			if(firstname==""){
				document.getElementById("firstNameError").innerHTML=first_name_error;
			}else{
				document.getElementById("firstNameError").innerHTML='';
			}
		}
		
		<!--检验lastname-->
		
		function checkLastname(){
			var lastname = document.getElementById("listname").value;
			var last_name_error = document.getElementById("last_name_error").value;
			if(lastname==""){
				document.getElementById("lastNameError").innerHTML=last_name_error;
			}else{
				document.getElementById("lastNameError").innerHTML='';
			}
		}
		//验证有效期不能小于当前日期
		function checkdate(){
			var date = new Date(); //获得系统日期的文本值
			var y = date.getFullYear(); 
			var m = date.getMonth(); 
			var d = date.getDate(); 
			var dt = y+"-"+m+"-"+d;
			var year = document.getElementById("year").value;//获得用户选择的日期文本值
			var month = document.getElementById("month").value;
			var date = "20"+year+"-"+month+"-"+d;
			
			var arrJHRQ=dt.split('-'); //转成成数组，分别为年，月，日，下同
			var arrJHWCSJ=date.split('-');
			var dateJHRQ=new Date(parseInt(arrJHRQ[0]),parseInt(arrJHRQ[1])-1,parseInt(arrJHRQ[2]),0,0,0); //新建日期对象
			var dateJHWCSJ=new Date(parseInt(arrJHWCSJ[0]),parseInt(arrJHWCSJ[1])-1,parseInt(arrJHWCSJ[2]),0,0,0);
			var date_info = document.getElementById("date_info").value;
			if (dateJHRQ.getTime()>dateJHWCSJ.getTime()) {
				
				document.getElementById('dateInfo').innerHTML=date_info
				
			}else{
				document.getElementById('dateInfo').innerHTML="";
			}
		} 
		
		<!--检验shipping firstname-->
		function checkSFirstname(){
			var lastname = document.getElementById("shippingFirstName").value;
			var shipping_first_error = document.getElementById("shipping_first_error").value;
			if(lastname==""){
				document.getElementById("shippingFirstError").innerHTML=shipping_first_error;
			}else{
				document.getElementById("shippingFirstError").innerHTML='';
			}
		}
		
		<!--检验shipping lastname-->
		function checkSLastname(){
			var lastname = document.getElementById("shippingLastName").value;
			var shipping_last_error = document.getElementById("shipping_last_error").value;
			if(lastname==""){
				document.getElementById("shippingLastError").innerHTML=shipping_last_error;
			}else{
				document.getElementById("shippingLastError").innerHTML='';
			}
		}
		<!--检验shipping firstname-->
		function checkBillFirstname(){
			var lastname = document.getElementById("firstname").value;
			var first_name_error = document.getElementById("first_name_error").value;
			if(lastname==""){
				document.getElementById("firstNameError").innerHTML=first_name_error;
			}else{
				document.getElementById("firstNameError").innerHTML='';
			}
		}
		
		<!--检验shipping lastname-->
		function checkBillLastname(){
			var lastname = document.getElementById("lastname").value;
			var last_name_error = document.getElementById("last_name_error").value;
			if(lastname==""){
				document.getElementById("lastNameError").innerHTML=last_name_error;
			}else{
				document.getElementById("lastNameError").innerHTML='';
			}
		}
		<!--检验ship邮箱是否重复-->
		function checkConfirmEmail(){
			var email = document.getElementById("shippingEmail").value;
			var confirmemail = document.getElementById("shippingComfirmEmail").value;
			var shipcomfirm_email_error = document.getElementById("shipcomfirm_email_error").value;
			
			if(email != confirmemail){
				document.getElementById("shipcomfirmEmailError").innerHTML=shipcomfirm_email_error;
			}else{
				document.getElementById("shipcomfirmEmailError").innerHTML='';
			}
		}
		<!--检验邮箱是否重复-->
		function checkConfirmEmailBill(){
			var email = document.getElementById("email").value;
			var confirmemail = document.getElementById("comfirmEmail").value;
			var comfirm_email_error = document.getElementById("comfirm_email_error").value;
			if(email != confirmemail){
				document.getElementById("comfirmEmailError").innerHTML=comfirm_email_error;
			}else{
				document.getElementById("comfirmEmailError").innerHTML='';
			}
		}
		
		
		<!--检验bill phone-->
		function checkBillphone(){
			var lastname = document.getElementById("phone").value;
			var billphone_error = document.getElementById("billphone_error").value;
			if(lastname==""){
				document.getElementById("billphoneError").innerHTML=billphone_error;
			}else{
				document.getElementById("billphoneError").innerHTML='';
			}
		}
		
		<!--检验bill Zipcode-->
		function checkBillZipcode(){
			var lastname = document.getElementById("zipcode").value;
			var billzipcode_error = document.getElementById("billzipcode_error").value;
			if(lastname==""){
				document.getElementById("billzipcodeError").innerHTML=billzipcode_error;
			}else{
				document.getElementById("billzipcodeError").innerHTML='';
			}
		}
		<!--检验bill Address-->
		function checkBillAddress(){
			var lastname = document.getElementById("address").value;
			var billaddree_error = document.getElementById("billaddree_error").value;
			if(lastname==""){
				document.getElementById("billaddreeError").innerHTML=billaddree_error;
			}else{
				document.getElementById("billaddreeError").innerHTML='';
			}
		}
		
		<!--检验bill City-->
		function checkbillCity(){
			var lastname = document.getElementById("city").value;
			var billcity_error = document.getElementById("billcity_error").value;
			if(lastname==""){
				document.getElementById("billcityError").innerHTML=billcity_error;
			}else{
				document.getElementById("billcityError").innerHTML='';
			}
		}


		<!--检验bill City-->
		function checkShipCity(){
			var lastname = document.getElementById("shippingCity").value;
			var shipping_city_error = document.getElementById("shipping_city_error").value;
			if(lastname==""){
				document.getElementById("shippingCityError").innerHTML=shipping_city_error;
			}else{
				document.getElementById("shippingCityError").innerHTML='';
			}
		}
		
		<!--检验bill City-->
		function checkShipZipcode(){
			var lastname = document.getElementById("shippingZipcode").value;
			var shipping_zipcode_error = document.getElementById("shipping_zipcode_error").value;
			if(lastname==""){
				document.getElementById("shippingzipcodeError").innerHTML=shipping_zipcode_error;
			}else{
				document.getElementById("shippingzipcodeError").innerHTML='';
			}
		}
		
		<!--检验bill City-->
		function checkShipAddress(){
			var lastname = document.getElementById("shippingAddress").value;
			var shipping_addree_error = document.getElementById("shipping_addree_error").value;
			
			if(lastname==""){
				document.getElementById("shippingaddreeError").innerHTML=shipping_addree_error;
			}else{
				document.getElementById("shippingaddreeError").innerHTML='';
			}
		}
		<!--检验bill City-->
		function checkShipPhone(){
			var lastname = document.getElementById("shippingPhone").value;
			var shipping_phone_error = document.getElementById("shipping_phone_error").value;
			if(lastname==""){
				document.getElementById("shippingphoneError").innerHTML=shipping_phone_error;
			}else{
				document.getElementById("shippingphoneError").innerHTML='';
			}
		}
		
		var i=0;
		<!--验证数据是否为空	-->
		function isSubmit(){
			var thesame = document.getElementById('thesame').checked;
			var nothesame = document.getElementById('nothesame').checked;
			
			var firstname = document.getElementById("firstname").value;
			var listname = document.getElementById("lastname").value;
			var cardnum = document.getElementById("cardnum").value;
			var cvv2 = document.getElementById("cvv2").value;
			var email = document.getElementById("email").value;
			var comfirmEmail = document.getElementById("comfirmEmail").value;
			var phone = document.getElementById("phone").value;
			var zipcode = document.getElementById("zipcode").value;
			var address = document.getElementById("address").value;
			var city = document.getElementById("city").value;
			var state = document.getElementById("state").value;
			var country = document.getElementById("country").value;
			var month = document.getElementById("month").value;
			var year = document.getElementById("year").value;
			var cardbank = document.getElementById("cardbank").value;
			
			var cardnameError = document.getElementById("cardnameError").innerHTML;
			var firstNameError = document.getElementById("firstNameError").innerHTML;
			var lastNameError = document.getElementById("lastNameError").innerHTML;
			var cardnumError = document.getElementById("cardnumError").innerHTML;
			var mailInfo = document.getElementById("mailInfo").innerHTML;
			var comfirmEmailError = document.getElementById("comfirmEmailError").innerHTML;
			var phoneError = document.getElementById("billphoneError").innerHTML;
			var zipcodeError = document.getElementById("billzipcodeError").innerHTML;
			var addreeError = document.getElementById("billaddreeError").innerHTML;
			var cityError = document.getElementById("billcityError").innerHTML;
			var countryError = document.getElementById("countryError").innerHTML;
			var cvv2Error = document.getElementById("cvv2Error").innerHTML;
			var dateInfo = document.getElementById("dateInfo").innerHTML;
			
			
			
			var shippingfirstname = document.getElementById("shippingFirstName").value;
			var shippinglistname = document.getElementById("shippingLastName").value;
			var shippingemail = document.getElementById("shippingEmail").value;
			var shippingcomfirmEmail = document.getElementById("shippingComfirmEmail").value;
			var shippingphone = document.getElementById("shippingPhone").value;
			var shippingzipcode = document.getElementById("shippingZipcode").value;
			var shippingaddress = document.getElementById("shippingAddress").value;
			var shippingcity = document.getElementById("shippingCity").value;
			var shippingstate = document.getElementById("shippingState").value;
			var shippingcountry = document.getElementById("shippingCountry").value;
			
			var shippingfirstNameError = document.getElementById("shippingFirstError").innerHTML;
			var shippinglastNameError = document.getElementById("shippingLastError").innerHTML;
			var shippingmailInfo = document.getElementById("shippingmailInfo").innerHTML;
			var shippingcomfirmEmailError = document.getElementById("shipcomfirmEmailError").innerHTML;
			var shippingphoneError = document.getElementById("shippingphoneError").innerHTML;
			var shippingzipcodeError = document.getElementById("shippingzipcodeError").innerHTML;
			var shippingaddreeError = document.getElementById("shippingaddreeError").innerHTML;
			var shippingcityError = document.getElementById("shippingCityError").innerHTML;
			
			if(cardnum==""){
				alert("16-digit card number is required!");
				return false;
			}
			if(cvv2==""){
				alert("Card Verification Number for VISA is a 3-digit number!");
				return false;
			}
			if(month==""){
				alert("The month is required!");
				return false;
			}
			if(year==""){
				alert("The year is required!");
				return false;
			}
			if(cardnumError!=""){
				alert(cardnumError);
				return false;
			}
			if(cvv2Error!=""){
				alert(cvv2Error);
				return false;
			}
			if(dateInfo!=""){
				alert(dateInfo);
				return false;
			}
			if(i==0){
				if(shippingfirstname==""){
					alert("Please input first name!");
					return false;
				}
				if(shippinglistname==""){
					alert("Please input last name!");
					return false;
				}
				if(shippingemail==""){
					alert("Incorrect email!");
					return false;
				}
				if(shippingmailInfo!=""){
					alert(shippingmailInfo);
					return false;
				}
				if(shippingcomfirmEmail==""){
					alert("Incorrect comfirmEmail!");
					return false;
				}
				if(shippingcomfirmEmailError!=""){
					alert(shippingcomfirmEmailError);
					return false;
				}
				if(shippingphone==""){
					alert("Please input the phone!");
					return false;
				}
				if(shippingzipcode==""){
					alert("The shipping zipcode is required!");
					return false;
				}
				if(shippingaddress==""){
					alert("The shipping address is required!");
					return false;
				}
				if(shippingcity==""){
					alert("The shipping city is required!");
					return false;
				}
				if(shippingcountry==0){
					alert("The shipping country is required!");
					return false;
				}
				if(nothesame=="" && thesame==""){
					alert("Please select the billing with the shipping is the same or not same!");
					return false;
				}
				//如果选择不一样的地址就判断值
				if(nothesame==true){
					if(firstname==""){
						alert("Please input billing first name!");
						return false;
					}
					if(listname==""){
						alert("Please input billing last name!");
						return false;
					}
					if(email==""){
						alert("Incorrect billing email!");
						return false;
					}
					if(comfirmEmail==""){
						alert("Incorrect billing comfirmEmail!");
						return false;
					}
					if(phone==""){
						alert("Please input billing phone!");
						return false;
					}
					if(zipcode==""){
						alert("The billing zipcode is required!");
						return false;
					}
					if(address==""){
						alert("The billing address is required!");
						return false;
					}
					if(city==""){
						alert("The billing city is required!");
						return false;
					}
					if(country==0){
						alert("The billing country is required!");
						return false;
					}
				}
			}else{
				alert("Has been submitted");
				return false;
			}
			document.getElementById("form1").submit();
			document.getElementById("submitse").style.visibility="hidden";
			document.getElementById("process").style.visibility="visible";
		}
		
  function showBillingAddress(targetid){
    if (document.getElementById){
        target=document.getElementById(targetid);
            if (target.style.display=="block"){
                target.style.display="none";
            } else {
                target.style.display="block";
            }
    }  
  }	
  function toshowBillingAddress(targetid){
    if (document.getElementById){
        target=document.getElementById(targetid);
            if (target.style.display=="none"){
                target.style.display="block";
            } else {
                target.style.display="block";
            }
    }  
  }	
  function noshowBillingAddress(targetid){
    if (document.getElementById){
        target=document.getElementById(targetid);
            if (target.style.display=="block"){
                target.style.display="none";
            } else {
                target.style.display="none";
            }
    }  
  }			
		
		
		
		
		