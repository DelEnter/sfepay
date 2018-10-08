<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>ECPSS PAYMENT</title>
<style>
*{ margin:0; padding:0}
body,td{ font-weight:bold; font-family:Arial, Helvetica, sans-serif; font-size:12px;}
</style>
</head>
	<script language="javascript">
		function submitok(){
			document.getElementById('form1').submit();

		}
		function submitno(){
		    document.getElementById('tradeType').value='YX';
			document.getElementById('form1').submit();

		}		
	</script>
<body>
<form action="paybyvpn" id="form1" method="post">
<table width="781" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="77" style="background:url(payment/test/bg.gif)"><table width="795" border="0" cellpadding="0" cellspacing="0">
      <tr>
        <td width="550" height="16"></td>
        <td rowspan="2" valign="top"><img src="payment/test/over.gif" alt="" /></td>
      </tr>
      <tr>
        <td><img src="payment/test/logo.gif" alt="" /></td>
        </tr>
    </table></td>
  </tr>
  <tr>
    <td height="10"></td>
  </tr>
  <tr>
    <td height="10"><table width="781" border="0" align="center" cellpadding="0" cellspacing="0">
      <tr>
        <td><img src="payment/test/top.gif" alt="" /></td>
      </tr>
      <tr>
        <td align="center" style="background:url(payment/test/bg1.gif) left repeat-y"><table width="95%" border="0" align="center" cellpadding="0" cellspacing="0">
          <tr>
            <td height="34" style="background:url(payment/test/bg3.gif) repeat-x">&nbsp;</td>
          </tr>
          <tr>
            <td>&nbsp;</td>
          </tr>
          <tr>
            <td align="center"><table width="60%" border="0" cellspacing="0" cellpadding="0">
              <tr>
                <td align="center"><input style="background:url(payment/test/button.gif); border:0; width:183px; height:37px; color:#FFF; font-size:14px; font-weight:bold" type="button" name="button" id="button"  onclick="submitno();" value="<s:property value='local_money'/>"  /></td>
                <td align="center" valign="bottom" style=" font-size:14px">or</td>
                <td align="center"><input style="background:url(payment/test/button.gif); border:0; width:183px; height:37px; color:#FFF; font-size:14px; font-weight:bold" type="button" name="button" id="button" onclick="submitok();" value="<s:property value='foreign_money'/>" /></td>
              </tr>
            </table></td>
          </tr>
          <tr>
            <td height="30" align="center" style=" font-size:14px">Exchange rate:<span style="color:#03F"><s:property value="conversion_Rate_show"/></span>&nbsp;&nbsp;&nbsp;&nbsp;No Commission Fee</td>
          </tr>
          <tr>
            <td height="30">&nbsp;</td>
          </tr>
          <tr>
            <td align="center" style="color:#03F; font-size:14px"><s:property value="cardMessage"/>
              </td>
          </tr>
        </table></td>
      </tr>
      <tr>
        <td><img src="payment/test/bottom.gif" alt="" /></td>
      </tr>
    </table></td>
  </tr>
</table>
<input type="hidden" name="amount_Transaction_Foreign" size="63" value="<s:property value="amount_Transaction_Foreign"/>" maxlength="250"/></td>
<input type="hidden" name="conversion_Rate" value="<s:property value="conversion_Rate"/>" size="20" maxlength="8"/></td>
<input type="hidden" name="currency_Code_Foreign" value="<s:property value="currency_Code_Foreign"/>" size="20" maxlength="16"/></td>
<input type="hidden" name="tradeType" id="tradeType" value="<s:property value="tradeType"/>" size="20" maxlength="8"/></td>
<input type="hidden" name="rorderno" value="<s:property value="rorderno"/>" size="20" maxlength="40"/></td>
<input type="hidden" name="car_termanal" value="<s:property value="car_termanal"/>" size="20" maxlength="40"/></td>
<input type="hidden" name="checktrade" id="checktrade" value="0" size="20" maxlength="40"/></td>  

 
</form>    

</body>
</html>
