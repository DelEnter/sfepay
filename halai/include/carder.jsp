<%@ page contentType="text/html; charset=UTF-8"%>
<meta http-equiv="Cache-Control" content="no-store" />
<meta http-equiv="Pragma" content="no-cache" />
<meta http-equiv="Expires" content="0" />
  <style>
  .text1
{
width:200px;
height:19px;
border:1px #cbcfd0 solid ;

}
</style>
<script type="text/javascript" src="dwr/interface/carderCheck.js"></script>
	<script type="text/javascript" src="dwr/engine.js"></script>
	<script type="text/javascript" src="dwr/util.js"></script>
	<script type="text/javascript" src="script/prototype-1.6.0.2.js"></script>
	<script type="text/javascript" src="script/json2.js"></script>
	<script type="text/javascript">
    		var i=0;
		var message;
		<!--验证发卡行名称-->
		function checkcCardname(){
			var cardname = document.getElementById("cardbank").value;
			if(cardname==""){
				document.getElementById("cardnameError").innerHTML='Issuing Bank is required';
			}else{
				document.getElementById("cardnameError").innerHTML='';
			}
		}
		<!--验证重复邮箱-->
		function checkComfirmEmail(){
			var comfirmEmail = document.getElementById("comfirmEmail").value;
			var email = document.getElementById("email").value;
			carderCheck.getComfirmEmail(email, comfirmEmail, callComfirmEmail);
		}
		function callComfirmEmail(comfirmEmailError){
			dwr.util.setValue("comfirmEmailError", comfirmEmailError);
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
		<!--检验firstname-->
		function checkFirstName(){
			var firstname = document.getElementById("firstname").value;
			carderCheck.getFirstname(firstname, callFirstName); 
		}
		function callFirstName(firstNameError){	
			dwr.util.setValue("firstNameError", firstNameError);
		
			if(firstNameError==""){
				return true;
			}else{
				return false;
			}
			
		}
		
		<!--检验lastname-->
		
		function checkLastname(){
			var lastname = document.getElementById("listname").value;
			carderCheck.getLastname(lastname, callLastname);
		}
		function callLastname(lastNameError){
			dwr.util.setValue("lastNameError", lastNameError);
			if(lastNameError==""){
				return true;
			}else{
				return false;
			}
		}	
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
					getCardInfo('jsonData.action?cardnum='+cardnum+'&merNo='+merNo);
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
			$("cardnumError").innerHTML=res.errorInfo;
		}	
		
		//验证卡号类型
		function checkCardType(cardnum,merNo){
			carderCheck.getCardType(cardnum,merNo,callCardType);
		}
		function callCardType(cardnumError){
			dwr.util.setValue("cardnumError", cardnumError);
			if(cardnumError==""){
				return true;
			}else{
				return false;
			}
		}
		
		<!--验证邮箱地址-->
		function checkEmail(){
			var email = document.getElementById("email").value;
			carderCheck.getEmail(email, callEmail);
		}
		function callEmail(emailError){
			dwr.util.setValue("emailError", emailError);
			if(emailError==""){
				return true;
			}else{
				return false;
			}
		}
		
		<!--验证电话号码-->
		function checkPhone(){
			var phone = document.getElementById("phone").value;
			carderCheck.getPhone(phone, callPhone);
		}
		function callPhone(phoneError){
			dwr.util.setValue("phoneError", phoneError);
			if(phoneError==""){
				return true;
			}else{
				return false;
			}
		}
		<!--验证邮编号码-->
		function checkZipcode(){
			var zipcode = document.getElementById("zipcode").value;
			carderCheck.getZipcode(zipcode, callZipcode);
		}
		function callZipcode(zipcodeError){
			dwr.util.setValue("zipcodeError",zipcodeError);
			if(zipcodeError==""){
				return true;
			}else{
				return false;
			}
		}
		
		<!--验证地址-->
		function checkAddress(){
			var address = document.getElementById("address").value;
			carderCheck.getAddress(address, callAddress);
			
		}
		function callAddress(addreeError){
			dwr.util.setValue("addreeError", addreeError);
			if(addreeError==""){
				return true;
			}else{
				return false;
			}
		}
		
		<!--验证城市-->
		function checkCity(){
			var city = document.getElementById("city").value;
			carderCheck.getCity(city, callCity);
		}
		function callCity(cityError){
			dwr.util.setValue("cityError", cityError);
			if(cityError==""){
				return true;
			}else{
				return false;
			}
		}
		
		<!--验证州-->
		function checkState(){
			var state = document.getElementById("state").value;
			carderCheck.getState(state, callState);
		}
		function callState(stateError){
			dwr.util.setValue("stateError", stateError);
			if(stateError==""){
				return true;
			}else{
				return false;
			}
		}
		<!--验证国家--> 
		function checkCountry(){

			var country = document.getElementById("country").value;
			if(country==0){
				alert("The billing country is required!");
			}

			carderCheck.getCountry(country, callCountry);
		}
		function callCountry(countryError){
			dwr.util.setValue("countryError", countryError);
			if(countryError==""){
				return true;
			}else{
				return false;
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
			function isSubmit2(){
			document.form1.submit();
			}
		
		<!--验证数据是否为空	-->
		function isSubmit(){
			var firstname = document.getElementById("firstname").value;
			var listname = document.getElementById("listname").value;
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
			var phoneError = document.getElementById("phoneError").innerHTML;
			var zipcodeError = document.getElementById("zipcodeError").innerHTML;
			var addreeError = document.getElementById("addreeError").innerHTML;
			var cityError = document.getElementById("cityError").innerHTML;
			var countryError = document.getElementById("countryError").innerHTML;
			var cvv2Error = document.getElementById("cvv2Error").innerHTML;
			var dateInfo = document.getElementById("dateInfo").innerHTML;
			if(i==0){
				if(firstname==""){
					alert("Please input first name!");
					message = false;
				}else if(listname==""){
					alert("Please input last name!");
					message = false;
				}else if(cardnum==""){
					alert("16-digit card number is required!");
					message = false;
				}else if(cvv2==""){
					alert("Card Verification Number for VISA is a 3-digit number!");
					message = false;
				}else if(email==""){
					alert("Incorrect email!");
					message = false;
				}else if(comfirmEmail==""){
					alert("Incorrect comfirmEmail!");
					message = false;
				}else if (phone==""){
					alert("Please input the phone!");
					message = false;
				}else if(zipcode==""){
					alert("The billing zipcode is required!");
					message = false;
				}else if(address==""){
					alert("The billing address is required!");
					message = false;
				}else if(city==""){
					alert("The billing city is required!");
					message = false;
				}else if(month==""){
					alert("The month is required!");
					message = false;
				}else if(year==""){
					alert("The year is required!");
					message = false;
				}else if(cardbank==""){
					alert("The Issuing Bank is required!");
				}else if(country==0){
					message = false;
				}else{
					message = true;
				}
			}else{
				message = false;
			}
			if(message==true && firstNameError=="" && lastNameError=="" && cardnumError=="" && mailInfo=="" && comfirmEmailError=="" && phoneError=="" && zipcodeError=="" && addreeError=="" && cityError=="" && countryError=="" && cvv2Error=="" && dateInfo=="" && cardnameError==""){
				i++;
				document.form1.submit();
			}
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
		</script>
