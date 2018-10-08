<!--验证发卡行名称-->
function checkcCardname(){
	var cardname = document.getElementById("cardbank").value;
	if(cardname==""){
		document.getElementById("cardnameError").innerHTML='Issuing Bank is required';
	}else{
		document.getElementById("cardnameError").innerHTML='';
	}
}
<!--验证cvv2-->
function checkCvv2(){
	var jgpattern =/^[0-9]+$/; 
	var cvv2 = document.getElementById("cvv2").value;
	var cvvnum = cvv2.length;
	cvv2Flag =  jgpattern.test(cvv2);
	if(cvv2Flag){
		if(cvvnum==3){
			document.getElementById('cvv2Error').innerHTML='';
			return true;
		}else{
			document.getElementById('cvv2Error').innerHTML='3-digit cvv2 is required';
			return false;
		}
	}else{
		document.getElementById('cvv2Error').innerHTML='3-digit cvv2 is required';
		return false;
	}
}
	<!--检验邮箱-->
		
		function checkMail(inputName,divValue)
		{
			var email = document.getElementById(inputName).value;
			var pattern = /^([a-zA-Z0-9_\.\-])+\@(([a-zA-Z0-9\-])+\.)+([a-zA-Z0-9]{2,4})+$/;
			chkFlag = pattern.test(email);
			if(chkFlag)
			{
				document.getElementById(divValue).innerHTML='';
				return true;
			}
			else
			{
				document.getElementById(divValue).innerHTML='Invalid  email!'; 
				return false;
			}	
		}
<!--检验firstname-->
		function checkFirstName(){
			var firstname = document.getElementById("firstname").value;
			if(firstname==""){
				document.getElementById("firstNameError").innerHTML='First Name is required';
			}else{
				document.getElementById("firstNameError").innerHTML='';
			}
		}
		
		<!--检验lastname-->
		
		function checkLastname(){
			var lastname = document.getElementById("listname").value;
			if(lastname==""){
				document.getElementById("lastNameError").innerHTML='Last Name is required';
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
			
			if (dateJHRQ.getTime()>dateJHWCSJ.getTime()) {
				document.getElementById('dateInfo').innerHTML="The valid date is before to the current date."
			}else{
				document.getElementById('dateInfo').innerHTML="";
			}
		} 
		
		<!--检验shipping firstname-->
		function checkSFirstname(){
			var lastname = document.getElementById("shippingFirstName").value;
			if(lastname==""){
				document.getElementById("shippingFirstError").innerHTML='First Name is required';
			}else{
				document.getElementById("shippingFirstError").innerHTML='';
			}
		}
		
		<!--检验shipping lastname-->
		function checkSLastname(){
			var lastname = document.getElementById("shippingLastName").value;
			if(lastname==""){
				document.getElementById("shippingLastError").innerHTML='Last Name is required';
			}else{
				document.getElementById("shippingLastError").innerHTML='';
			}
		}
		<!--检验shipping firstname-->
		function checkBillFirstname(){
			var lastname = document.getElementById("firstname").value;
			if(lastname==""){
				document.getElementById("firstNameError").innerHTML='First Name is required';
			}else{
				document.getElementById("firstNameError").innerHTML='';
			}
		}
		
		<!--检验shipping lastname-->
		function checkBillLastname(){
			var lastname = document.getElementById("lastname").value;
			if(lastname==""){
				document.getElementById("lastNameError").innerHTML='Last Name is required';
			}else{
				document.getElementById("lastNameError").innerHTML='';
			}
		}
		<!--检验ship邮箱是否重复-->
		function checkConfirmEmail(){
			var email = document.getElementById("shippingEmail").value;
			var confirmemail = document.getElementById("shippingComfirmEmail").value;
			if(email != confirmemail){
				document.getElementById("shipcomfirmEmailError").innerHTML='E-mail address is inconsistent';
			}else{
				document.getElementById("shipcomfirmEmailError").innerHTML='';
			}
		}
		<!--检验邮箱是否重复-->
		function checkConfirmEmailBill(){
			var email = document.getElementById("email").value;
			var confirmemail = document.getElementById("comfirmEmail").value;
			if(email != confirmemail){
				document.getElementById("comfirmEmailError").innerHTML='E-mail address is inconsistent';
			}else{
				document.getElementById("comfirmEmailError").innerHTML='';
			}
		}
		
		
		<!--检验bill phone-->
		function checkBillphone(){
			var lastname = document.getElementById("phone").value;
			if(lastname==""){
				document.getElementById("billphoneError").innerHTML='Phone is required';
			}else{
				document.getElementById("billphoneError").innerHTML='';
			}
		}
		
		<!--检验bill Zipcode-->
		function checkBillZipcode(){
			var lastname = document.getElementById("zipcode").value;
			if(lastname==""){
				document.getElementById("billzipcodeError").innerHTML='ZipCode is required';
			}else{
				document.getElementById("billzipcodeError").innerHTML='';
			}
		}
		<!--检验bill Address-->
		function checkBillAddress(){
			var lastname = document.getElementById("address").value;
			if(lastname==""){
				document.getElementById("billaddreeError").innerHTML='Address is required';
			}else{
				document.getElementById("billaddreeError").innerHTML='';
			}
		}
		
		<!--检验bill City-->
		function checkbillCity(){
			var lastname = document.getElementById("city").value;
			if(lastname==""){
				document.getElementById("billcityError").innerHTML='City is required';
			}else{
				document.getElementById("billcityError").innerHTML='';
			}
		}


		<!--检验bill City-->
		function checkShipCity(){
			var lastname = document.getElementById("shippingCity").value;
			if(lastname==""){
				document.getElementById("shippingCityError").innerHTML='City is required';
			}else{
				document.getElementById("shippingCityError").innerHTML='';
			}
		}
		
		<!--检验bill City-->
		function checkShipZipcode(){
			var lastname = document.getElementById("shippingZipcode").value;
			if(lastname==""){
				document.getElementById("shippingzipcodeError").innerHTML='Zipcode is required';
			}else{
				document.getElementById("shippingzipcodeError").innerHTML='';
			}
		}
		
		<!--检验bill City-->
		function checkShipAddress(){
			var lastname = document.getElementById("shippingCity").value;
			if(lastname==""){
				document.getElementById("shippingaddreeError").innerHTML='Address is required';
			}else{
				document.getElementById("shippingaddreeError").innerHTML='';
			}
		}
		<!--检验bill City-->
		function checkShipPhone(){
			var lastname = document.getElementById("shippingPhone").value;
			if(lastname==""){
				document.getElementById("shippingphoneError").innerHTML='Phone is required';
			}else{
				document.getElementById("shippingphoneError").innerHTML='';
			}
		}
		
		var i=0;
		<!--验证数据是否为空	-->
		function isSubmit(){
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
			}else{
				alert("Has been submitted");
				return false;
			}
			document.getElementById("form1").submit();
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
		
		
		
		
		