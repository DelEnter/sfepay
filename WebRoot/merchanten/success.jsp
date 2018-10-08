<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Registration Successful</title>
<link rel="stylesheet" type="text/css" href="css/mercss.css"/>



<script type="text/javascript">
	function merMain(){
		var url ="merMainAction.action";
		document.form1.action=url;
		document.form1.submit();
	}
</script>

</head>
<body>
<DIV class=clear></DIV>
<div class="middle">
<table>
    <tr>
     
   <td valign="top">  
   	
     <div class="right">
    
          <div class="right_top" align=center ><font  size=3 color="">Registration Successful</font> </div>
          <div class="right_text">
	
			<table align="center" width="500" height="200">
				<tr align="center">
				<td>
				    New Merchant Create Successfully！
				</td>
				</tr>
				<tr align="center">
				<td>
				   Merchant NO：<s:property value = "merchant.merno"/>
				</td>
				</tr>
				<tr align="center">
				<td>
				  User name: <s:property value = "merchant.username"/> 
				</td>
				</tr>
				<tr align="center">
				<td>
				   Login Password: <s:property value = "passwd"/> 
				</td>
				</tr>
				<tr align="center">
				<td>
				   MD5Key: <s:property value = "merchant.md5key"/> 
				</td>
				</tr>
				
				<tr>
				<td>&nbsp;
				   
				</td>
				</tr>
				
				<tr align="center">
				<td>
				  <font color= "red">Please remember your register information!</font>
				</td>
				</tr>
				
				<tr>
				<td>&nbsp;
				   
				</td>
				</tr>
				
				
				<tr align="center"><td>
				<a href="newReg.jsp">Re-register</a>&nbsp;&nbsp;<a href="http://www.ecpss.com.cn">Back to home</a>
				<td></tr>
				</table>


           
          </div>
    </div>
 </td>
 </tr>
 </table> 
</div>
<br>
<br>
		<DIV align=center>
			<A href="newReg.jsp" target=_top><font color=blue>Back to home</font></A>
		</DIV>
<DIV class=clear></DIV>
<br>
<div class="bottom">
     <div class="banquan" >
     Copyright 2010-2020   sfepay All Rights Reserved
     </div>
     <div class="banquan" >
      版权所有：上海迁易网络科技有限公司
     </div>
</div>
</body>
</html>
