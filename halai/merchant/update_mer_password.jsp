<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>修改商户密码</title>
     <link href="css/css.css" rel="stylesheet" type="text/css" />
     <script language="JavaScript">
     	function checkPass(){
     		var password = document.getElementById("password").value;
     		var confirmapss = document.getElementById("confirmapss").value;
     		if(password!=confirmapss){
     			alert("输入的密码不一致");
     		}
     		else {
     			form1.action="updateMerPassword";
     			form1.submit();
     		}
     	}
     </script>
  </head>
  <base target="_self">
  <body>
   <!-- <h3>修改商6户密码</h3> -->
   <div id="resizetable" width="400" height="150" />
   <div class="windows">
  
  <s:form action="updateMerPassword" method="post" name="form1" theme="simple">
   <div class="left">原密码：</div>
   <div class="right"><s:password id="pass" name="pass" class="text_input_02" />
	    				<s:hidden name="mer.id"/></div>
   <div class="clear"></div>
   
   <div class="left">密码：</div>
   <div class="right"><s:password name="mer.password" id="password" class="text_input_02" /></div>
   <div class="clear"></div>
   <div class="left">确认密码：</div>
   <div class="right"><s:password name="confirmpass" id="confirmapss" class="text_input_02"/></div>
   <div class="clear"></div>
   <div class="left">身份证号码</div>
   <div class="right">
     <s:textfield name="mer.certificateno"></s:textfield>
   </div>
   
   <div class="clear"></div>
   <div class="left">&nbsp;</div>
   <div class="right">
     <input type="button" value="&nbsp;修&nbsp;改&nbsp;" class="button_01" onclick="checkPass()"/>
   </div>
   </s:form>  
</div>
  </body>
</html>
