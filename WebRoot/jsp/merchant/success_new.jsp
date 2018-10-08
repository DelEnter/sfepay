<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>注册成功</title>
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
    
          <div class="right_top" align=center ><font  size=3 color="">注册成功</font> </div>
          <div class="right_text">
	
			<table align="center" width="500" height="200">
				<tr align="center">
				<td>
				    新增商户成功！
				</td>
				</tr>
				<tr align="center">
				<td>
				   商户号：<s:property value = "merchant.merno"/>
				</td>
				</tr>
				<tr align="center">
				<td>
				   用户名: <s:property value = "merchant.username"/> 
				</td>
				</tr>
				<tr align="center">
				<td>
				   登录密码: <s:property value = "passwd"/> 
				</td>
				</tr>
				
				<tr>
				<td>&nbsp;
				   
				</td>
				</tr>
				
				<tr align="center">
				<td>
				  <font color= "red">请记住您的注册信息!</font>
				</td>
				</tr>
				
				<tr>
				<td>&nbsp;
				   
				</td>
				</tr>
				
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
			<A href="/_index" target=_top><font color=blue>返回主页</font></A>
		</DIV>
<DIV class=clear></DIV>
<br>
<div class="bottom">
     <div class="banquan" >
     Copyright 2010-2020   xingbill All Rights Reserved
     </div>
     <div class="banquan" >
      版权所有：上海迁易网络科技有限公司
     </div>
</div>
</body>
</html>
