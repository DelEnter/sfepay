<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head> 
    <title>列为黑卡</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
	<%@ include file="../include/dialog.jsp"%>
	<script language="JavaScript" src="../js/util.js"></script>
	 <script language="JavaScript" type="text/JavaScript">
		function addCourse(f) {		
			goFormWindow(f,"backCardMake");
		}
	</script> 
  </head>
  
  <body>
  	<center>
  		<h3>列为黑卡</h3>
  		<s:form action="backCardMake" method="post" theme="simple" name="form1">
	  		<table>
	  			<tr>
	  				<td>ip</td>
	  				<td>
	  					<s:textfield name="back.ip"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>邮箱</td>
	  				<td>
	  					<s:textfield name="back.email"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>卡号</td>
	  				<td>
	  					<s:textfield name="back.cardno"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>cookie</td>
	  				<td>
	  					<s:textfield name="back.cookie"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>备注</td>
	  				<td>
	  					<s:textfield name="back.remark"/>
	  				</td>
	  			</tr>
	  			<tr>
	  				<td>	
	  					<s:submit value="列为黑卡"></s:submit>
	  				</td>		
	  			</tr>
	  		</table>
  		</s:form>
  	</center>&nbsp;
  	<script language="javascript">	
		var temflag='<s:property value='flag'/>';
		if(temflag==1){
			alert("成功列为黑卡!");
		}	
	</script>	
  </body>
</html>
