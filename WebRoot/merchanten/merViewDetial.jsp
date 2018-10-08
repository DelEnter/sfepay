<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="css/bail.css" type=text/css rel=stylesheet>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
	<title> Customer Service </title>
    <style>
    td{ font-size:12px;}
    </style>
</head>
<script type="text/javascript">
	function onsubmita(){
		document.getElementById('form1').action="merAddViewContent.action";
		document.getElementById('form1').submit();
	}
</script>
<base target="_self">
<body>
<DIV class=clear></DIV>
<div class="middle">
   <td valign="top">  
     <div class="right">
          <div class="right_top" align=center >  &nbsp;&nbsp;&nbsp;<font  size=3 color=""> Customer Service </font> </div>
          <div class="right_text">
<s:form action="viewMerchantComDetails" namespace="/merchant" id="form1" method ="post" theme="simple" > 
<s:hidden name="complainId"></s:hidden>

<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#666666">
  <tr>
    <td width="20%" bgcolor="#FFFFFF"><font color="red">
      <s:property value="message"/></font>&nbsp;</td>
    <td align="left" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <s:iterator value="comList" id="cm">
  <tr>
    <td align="right" bgcolor="#FFFFFF">E-mail ：</td>
	<td align="left" bgcolor="#FFFFFF"><s:property value="#cm[0].cmEmail"/> &nbsp; <s:property value="#cm[1].newdate"/>&nbsp;</td>
  </tr>
  <tr>
    <td align="right" bgcolor="#FFFFFF"><s:if test="#cm[1].contenttype==1">
							<font color="blue">Content ：</font>						</s:if>
						<s:else>
							<font color="red">Merchant Reply Content ：</font>						</s:else>	
                            
          </td>
<td align="left" bgcolor="#FFFFFF"><PRE><s:property value="#cm[1].content"/></PRE></td>
  </tr>
  </s:iterator>
  <tr>
    <td align="right" bgcolor="#FFFFFF">
						New Content：					</td>
					<td align="left" bgcolor="#FFFFFF">
	    <s:textarea name="content" rows="10" cols="50" value="" ></s:textarea>					</td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF">&nbsp;</td>
    <td align="left" bgcolor="#FFFFFF"><input type="button" value="提交" onClick="onsubmita()" />		</td>
  </tr>
</table>

</s:form>
	<table align="center">
   		<tr align="center">
	  		<td>
	  		<A href="merchantComplaintsQuery.action">Back</A>
	  		</td>
  		</tr>
   	</table>
</body>
</html>