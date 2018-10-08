<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<LINK href="css/bail.css" type=text/css rel=stylesheet>
<%@taglib prefix="s" uri="/struts-tags"%>
<head>
	<title>查看客人投诉进展详情</title>
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
          <div class="right_top" align=center >  &nbsp;&nbsp;&nbsp;<font  size=3 color="">查看客人投诉进展详情</font> </div>
          <div class="right_text">
<s:form action="merAddViewContent" namespace="/merchant" id="form1" method ="post" theme="simple" > 
<s:hidden name="complainId"></s:hidden>

<table width="100%" border="0" cellpadding="4" cellspacing="1" bgcolor="#666666">
  <tr>
    <td width="20%" bgcolor="#FFFFFF"><font color="red">
      <s:property value="message"/></font>&nbsp;</td>
    <td align="left" bgcolor="#FFFFFF">&nbsp;</td>
  </tr>
  <s:iterator value="comList" id="cm">
  <tr>
    <td align="right" bgcolor="#FFFFFF">客人E-mail ：</td>
	<td align="left" bgcolor="#FFFFFF"><s:property value="#cm[0].cmEmail"/> &nbsp; <s:property value="#cm[1].newdate"/>&nbsp;</td>
  </tr>
  <tr>
    <td align="right" bgcolor="#FFFFFF"><s:if test="#cm[1].contenttype==1">
							<font color="blue">客人邮件内容 ：</font>						</s:if>
						<s:else>
							<font color="red">商户回复客人邮件 ：</font>						</s:else>	
                            
          </td>
<td align="left" bgcolor="#FFFFFF"><PRE><s:property value="#cm[1].content"/></PRE></td>
  </tr>
  </s:iterator>
  <tr>
    <td align="right" bgcolor="#FFFFFF">
						新增内容 ：					</td>
					<td align="left" bgcolor="#FFFFFF">
	    <s:textarea name="content" rows="10" cols="50" value="" ></s:textarea>					</td>
  </tr>
  <tr>
    <td bgcolor="#FFFFFF">&nbsp;</td>
    <td align="left" bgcolor="#FFFFFF"><s:submit value="提交" />提示:回复内容不超过2000字符	</td>
  </tr>
</table>

</s:form>
	<table align="center">
   		<tr align="center">
	  		<td>
	  		<A href="merchantComplaintsQuery.action">返回列表</A>
	  		</td>
  		</tr>
   	</table>
</body>
</html>