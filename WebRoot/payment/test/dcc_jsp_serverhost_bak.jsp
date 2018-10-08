<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="s" uri="/struts-tags"%>
<html>
	<head>
	<style>
*{ margin:0; padding:0}
body{ font-family:Arial, Helvetica, sans-serif; font:"宋体"; font-size:12px;}
.content{ width:780px; height:auto; margin:0 auto}
.content .top{ background:url(images/cc_top.gif) repeat-x; height:70px;}
.content .top .logo{ width:147px; height:60px; float:left; margin:0 0 0 151px; display:inline;}
.content .top .right2{ width:238px; height:60px; float:right}
.content .top .right2 .iccon{ width:50px; height:59px; float:left}
.content .top .right2 .fontt{ color:#FFFFFF; font-weight:bold; width:173px; margin:25px 0 0 10px; height:25px; float:left}
.content .bodder{ width:730px; height:auto; margin:10px auto}

.content .bodder .bg{ background:url(images/bbbg.gif) repeat-y}
.content .bodder .bg .ttop{ width:728px; height:35px; background:#5164A6; font-size:18px; color:#FFFFFF; text-align:center; padding:15px 0 0 0}
.content .bodder .bg .anniu{ width:440px; height:80px; margin:10px auto 0 auto;}
.content .bodder .bg .anniu .iconn{ width:188px; height:38px; float:left;}
.content .bodder .bg .anniu .or{ float:left; width:55px; text-align:center; font-size:14px; font-weight:bold; padding:20px 0 0 0}
.content .bodder .bg .anniu .tt{ width:196px; height:40px; text-align:center; font-weight:bold; margin:0 auto;}

.content .bodder .font_ttt{ width:689px; height:40px; font-size:14px; font-weight:bold; text-align:center; color:#0000FF}
.icon_01{ width:188px; height:37px; background:url(images/iconn.gif) no-repeat; color:#FFFFFF; font-weight:bold; font-size:17px; font-family:Arial, Helvetica, sans-serif; border:0}
</style>
	<script language="javascript">
		function submitok(){
			document.getElementById('form1').submit();
		}
		function submitno(){
		    document.getElementById('checktrade').value='1';
			document.getElementById('form1').submit();
		}		
	</script>
</head>
<body>
<form action="testpaybydcc" id="form1" method="post">
<div class="content">
  <div class="top">
    <div class="logo"><img src="images/llogin.gif" alt="" /></div>
    <div class="right2">
      <div class="iccon"><img src="images/iconcc.gif" alt="" /></div>
      <div class="fontt"><span>Total Security Protection</span></div>
    </div>
  </div>
  <div class="bodder">
    <div><img src="images/toop.gif" alt="" /></div>
    <div class="bg">
      <div class="ttop"><span>Choose Transaction Currency Please</span></div>
      <div class="anniu">
        <div class="iconn"><input type="button" class="icon_01" onclick="submitno();" value="<s:property value='local_money'/>" /></div>
        <div class="or"><span>or</span></div>
        <div class="iconn"><input type="button" class="icon_01" onclick="submitok();" value="<s:property value='foreign_money'/>" /></div>
        <div style="clear:both">&nbsp;</div>
   <div class="tt"><span style="color:#0055FF"><s:property value="local_money"/></span> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                   <span style="color:#0055FF"><s:property value="foreign_money"/></span>
   </div>
 
        
        
      <div class="tt"><span>Exchange rate:</span><span style="color:#0055FF"><s:property value="conversion_Rate_show"/></span></div>
      </div>
      <div class="font_ttt"><span>I declare that I have been offered a choice of payment currencies and my choice is final.<br />
      I understand that the currency conversion is not provided by Visa.</span></div>
    </div>
    <div><img src="images/bottomm.gif" alt="" /></div>
  </div>
  </div>
<input type="hidden" name="amount_Transaction_Foreign" size="63" value="<s:property value="amount_Transaction_Foreign"/>" maxlength="250"/></td>
<input type="hidden" name="conversion_Rate" value="<s:property value="conversion_Rate"/>" size="20" maxlength="8"/></td>
<input type="hidden" name="currency_Code_Foreign" value="<s:property value="currency_Code_Foreign"/>" size="20" maxlength="16"/></td>
<input type="hidden" name="tradeType" value="<s:property value="tradeType"/>" size="20" maxlength="8"/></td>
<input type="hidden" name="rorderno" value="<s:property value="rorderno"/>" size="20" maxlength="40"/></td>
<input type="hidden" name="car_termanal" value="<s:property value="car_termanal"/>" size="20" maxlength="40"/></td>
<input type="hidden" name="checktrade" id="checktrade" value="0" size="20" maxlength="40"/></td>  

 
</form>    
</body>
</html>
