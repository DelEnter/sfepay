<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
 <%@ include file="../include/dialog.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.0 Transitional//EN">
<HTML>
<HEAD>
<TITLE>商户网址管理</TITLE>
<META http-equiv=Content-Type content="text/html; charset=utf-8">
<LINK href="css/style.css" type=text/css rel=stylesheet>
<script language="JavaScript" src="../js/util.js"></script>	

<script type="text/javascript">

function done1(){
	form1.submit();
}

function done2(){
	form2.submit();
}

function done3(){

   	form3.submit();
   		
}

function reset1(){
	document.getElementByName("merchantNo").value = "";
	document.getElementByName("channel").value = "";
	document.getElementByName("startDate1").value = "";
}

//修改网址
function update(id){
	openWindow('../PaySystem/toUpdateMerchantWebSite1.action?iid='+id,'12')
}
</script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
</HEAD>
<BODY>
<H3 align=center>商户网址管理</H3>



<TABLE width=615 align=center border=0>
<s:form name="form1" action="toWebSiteManager" validate="true"  theme="simple" method="post">
<font color=red>  <s:fielderror></s:fielderror> </font>
<TBODY>
 <tr>
		<td>交易网址：</td>
		<td><input type="text" size="70" name="tradeurl" value="<s:property value="tradeurl"/>"/></td>
	</tr>
  	<tr>
  		<td>返回网址：</td>
	    <td><input type="text" size="70" name="returnurl" value="<s:property value="returnurl"/>"/></td>
  	</tr>
  		<tr>
  		<td>网址状态：</td>
	    <td><s:select id="isblack" name="isblack" list="#{'':'所有','0':'正常交易','1':'禁止交易'}"/>&nbsp;&nbsp;&nbsp;
	    网址分类：<s:select id="webType" name="webType" list="webTypeList" headerKey="" headerValue="--请选择--" /> --%>
	    </td>
  	</tr>
  	<tr>
  		<TD>商户号：</td>
  		<td><s:textfield name="merno"></s:textfield>
  		<input name =maker type="button" value= "查询" onClick="done1()" /> 网站个数:<font color="red"><s:property value="urlcount"/> </font>个</TD>
  		
  	</tr>
  
 </TBODY>
</s:form> 
</TABLE>
 <br>
 <div align=center> <font size=2 color= blue ></font> </div>	
 <s:form name="form3" action="submitwebsite" theme="simple" method="post">
 <table cellSpacing=0 cellPadding=0 width=80% align=center bgColor=#ffffff border=0>
	<tr>
		<td  width="7%"  align="right">
		
		</td>
		<td width="80%" align="left">
			<input name="merno"  type=hidden  value= "<s:property value="merno" />"/>
		</td>
	</tr>
</table>
 <table cellSpacing=0 cellPadding=0 width=100% align=center bgColor=#cccccc border=1>
							  
  <TBODY>
 <TR align=center bgColor=#ffffff >
  <TD height=30  align=center>&nbsp;&nbsp;</td>
	    <TD height=30  align=center>&nbsp;&nbsp;商户号</td>
	    <TD height=30  align=center >&nbsp;&nbsp;商户网址</TD>
	    <TD height=30  align=center >&nbsp;&nbsp;交易返回网址</TD>
	  	<TD height=30  align=center>网址状态</td>
	  	<TD height=30  align=center>网站类型</td>
	  	<TD height=30  align=center>操作时间</td>
	   	<TD height=30  align=center>&nbsp;&nbsp;操作员</td>
	   	<TD height=30  align=center>&nbsp;&nbsp;操作</td>
    </TR>
  	<s:iterator id ="website"  value = "websiteList" status="s">
	
	<TR align=center bgColor=#ffffff >
	<TD >
	<s:property value="#s.index+1"/>
	</td>
	    <TD height=30>
	    	<font size=2 color= blue ></font>
	    	<s:property value="#website[1]"/>
	    	<input name="ids"  type=hidden  value= "<s:property value = "#website[0].id" />">&nbsp;
	    </td>
	    <TD height=30>
	    	<input type="text" size=25 name="tradeurls" value="<s:property  value = "#website[0].tradeWebsite"/>" >&nbsp;
	    </TD>
	    <TD height=30>
	    	<input type="text" size=65 name="returnurls" value="<s:property  value = "#website[0].website"/>" >&nbsp;
	    </TD>
	    <TD height=30>
	    <s:if test="#website[0].isblack==0">
			<font color="#20DF10">正常交易</font>&nbsp;
	    </s:if>
	    <s:if test="#website[0].isblack==1">
			<font color="#FF0000">禁止交易</font>&nbsp;
	    </s:if>
	    </td>
	    <TD height=30>
	    	<s:property value="#website[0].webSiteType"/>&nbsp;
		</td>
	    <TD height=30>
	    	<s:property value="#website[0].executetime"/>&nbsp;
		</td>
	    <TD height=30>
	    	<s:property value = "#website[0].operator" />&nbsp;
	    </td>
	    <TD height=30>
	    	<input type="button" name="p" value="update" onclick="update(<s:property value="#website[0].id" />);"/>&nbsp;
	    </td>
	</TR>
	</s:iterator>
	
	<TR align=center bgColor=#ffffff >
	<TD height=30   ></td>
	    <TD height=30   >添加新网址</td>
	    <TD height=30 ><input type="text" size=25 name="newtradeWebsite" value="" ></TD>
	    <TD height=30 ><input type="text" size=65 name="newWebsite" value="" ></TD>
	    <TD height=30 >
		</td>
		<TD height=30 >
		</td>
	</TR>
	
	
	
 </TBODY>

 </table>

 <div align=center>
 <p> <font size=2 color= blue>注意:添加网址的个数不能超过6个。
 </font>
 
 </div>	
 <br>
 <table width=315 align=center>
<tr>
<TD height=30 align="right"> <input name =confirm1 type="button" value= "确定" onClick = "done3()"/> </td>
<TD height=30 align="left"> <input name =reset1 type="reset"  value="取消" onClick = "reset1()"/></td>
</tr>
</table>
 </s:form> 



<br>
<center> <font size=2> <a href="jsp/manager/risk/riskMain.jsp">返回</a> </font></center>	 
 
 
</BODY>
</HTML>

