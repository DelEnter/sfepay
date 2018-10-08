<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <LINK href="../css/head.css" type=text/css rel=stylesheet>
    <script language="JavaScript" src="../js/util.js"></script>
  	<title>修改结算汇率</title>
  </head>
   <script  type="text/JavaScript">
	function updaterate(f) {		
		goFormWindow(f,"updateBalanceRateAction");
	}
</script>  
  <body>
        <div id="title" value="修改结算汇率" />
        <div id="resizetable" width="400" height="200" style=" width:300px; height:300px; text-align:center">
		    <div style="margin:0 0 0 25px">
            <s:form action="updateBalanceRateAction" theme="simple">
			    <table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="340" align=center 
							bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
					<tr>
						<td>币种</td>
						<td>
							<s:property value="money.moneykindname"/>
							<s:hidden name="rate.id"></s:hidden>
			   			</td>
					</tr>
					<tr>
						<td>结算汇率</td>
						<td>  <input type="text" value="<s:property value='rate.showRate'/>" name="rate.rate"/></td>
					</tr>
					<tr>
						<td>结算汇率执行时间</td>
						<td><s:textfield name="rate.executetime" id="rate.executetime" onfocus="calendar()"/></td>
					</tr>
				</table>
			<input type="button" onClick="updaterate(this.form);" value="修改" />
		</s:form>
            </div>
        <br />
		<a href="findRateAction.action">返回</a>
	</div>
  </body>
</html>
