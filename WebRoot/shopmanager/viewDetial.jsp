<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="http://localhost:8888/ecpss/js/util.js"></script><head>
	<title>查看客人投诉进展详情</title>
</head>

<style type="text/css">
body,table,tr,td{ font-size:12px;}
*{ padding:0; margin:0; list-style:none}
body{ padding:10px 0; font-family:Arial;font-size:12px }
#page{ width:600px; margin:0 auto; border:solid 1px #F4F1EC;}
#header{ border-bottom:20px solid #135CA9; width:900px; background-color:#F4F1EC;}
#left{ width:280px; overflow:hidden; float:left; margin-top:10px; border:solid 1px #F4F1EC; border-left:none; border-top:none; height:620px; background:url(image/left.jpg) no-repeat 0 0; }
#right{ float:right; width:410px; overflow:hidden; margin-top:10px;}

tr{ height:25px; line-height:25px;}
td{ border-bottom:1px solid #F4F1EC; text-align:left;}
input{ border:1px solid #135CA9; height:20px;}
span{ color:#FF0000}
</style>
<script type="text/javascript">
	//function onsubmita1(){
	//	document.getElementById('form1').action="addViewContent.action";
	//	document.getElementById('form1').submit();
	//}
	function reloadPage(){
		var a = document.getElementById('complainId').value;
	        window.location="../PaySystem/viewDetails.action?complainId="+a;
	}
	function onsubmita1(){
		var a = document.getElementById('complainId').value;
		var content = document.getElementById('content').value;
		window.location='../PaySystem/addViewContent.action?complainId='+a+'&content='+content;
	}
</script>
<body>
<H3 align=center>查看客人投诉进展详情</H3>
	<center>
<s:form action="viewDetails" id="form1" method ="post" theme="simple" > 
<s:hidden name="complainId" id="complainId"></s:hidden>

<table width="100%" border=0 align="center" cellpadding=5 cellspacing=1 bgcolor="#999999">
				<tr>
					<td colspan="5" bgcolor="#FFFFFF">
						<font color="red">
					  <s:property value="message"/></font>					</td>
                </tr>
			<s:iterator value="comList" id="cm">
				<tr>
					<td align="right" bgcolor="#FFFFFF">
						客人E-mail ：					</td>
                    <td colspan="4" align="right" bgcolor="#FFFFFF"><s:property value="#cm[0].cmEmail"/> &nbsp; <s:property value="#cm[1].newdate"/></td>
                </tr>
				<tr>
					<td width="20%" align="right" bgcolor="#FFFFFF">
						<s:if test="#cm[1].contenttype==1">
							<font color="blue">客人邮件内容 ：</font>						</s:if>
						<s:else>
							<font color="red">商户回复客人邮件 ：</font>						</s:else>					</td>
					
						<td colspan="4" align="left" bgcolor="#FFFFFF">
						<PRE><s:property value="#cm[1].content"/></PRE>
						</td>
                </tr>
			</s:iterator>
				<tr>
					<td align="right" bgcolor="#FFFFFF">
						新增内容 ：					</td>
					<td colspan="4" align="right" bgcolor="#FFFFFF">
						<s:textarea name="content" rows="10" cols="50" value=""></s:textarea>					</td>
                </tr>
				<tr>
					<td align="right" bgcolor="#FFFFFF">					</td>
					<td colspan="4" align="right" bgcolor="#FFFFFF">
						<input type="button" value="提交" onClick="onsubmita1()" />					</td>
                </tr>
			</table>

</s:form>
<p>&nbsp;</p>
<p>&nbsp;</p>
</body>
</html>