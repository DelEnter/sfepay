<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <LINK href="../css/head.css" type=text/css rel=stylesheet>
  	<%@ include file="../util/calendar.jsp" %>
    <title>修改商户管理值</title>
  </head>
     <script language="JavaScript" type="text/JavaScript">
	function addCourse(f) {		
		goFormWindow(f,"updateMerchantManagerAction");
	}
</script> 
  <body>
  	<h3>修改商户管理值</h3>
  	<center>
	  	<s:form action="updateMerchantManagerAction" theme="simple" method="post">
		    <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="640" align=center 
			bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
				<tr>
					<td>商户号</td>
					<s:hidden name="merman.merchantId"/>
					<td><s:property value="merman.merchantId"/></td>
				</tr>
				<tr>
					<td>风险值</td>
					<td>
						
						<s:hidden name="merman.id"/>
						<s:textfield name="merman.markValue"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td>账单地址</td>
					<td>
						<s:textfield name="merman.merchantAddress"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td>单笔交易限额</td>
					<td>
						<s:textfield name="merman.penQuota"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td>日交易限额</td>
					<td>
						<s:textfield name="merman.dayQuota"/>&nbsp;
					</td>
				</tr>
				<tr>
					<td>月交易限额</td>
					<td>
						<s:textfield name="merman.monthQuota"/>&nbsp;
					</td>
				</tr>
		    </table>
		    <input type="button" onClick="addCourse(this.form);" value="修改" />
	    </s:form>
    </center>
  </body>
</html>
