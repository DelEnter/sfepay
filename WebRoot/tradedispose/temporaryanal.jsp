<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>交易信息分析</title>
</head>
<div align="center">
	<h3>交易信息分析</h3>
	<br/>
	<h5>分析主题：
	<s:if test="%{cardNo6!=null&&cardNo6!=''}"><s:property value="cardNo6"/>**********</s:if>
	<s:if test="%{cardNo!=null&&cardNo!=''}"><s:property value="cardNo.substring(0,6)"/>******<s:property value="cardNo.substring(12)"/></s:if>
	<s:if test="%{ip!=null}"><s:property value="ip" /></s:if>
	<s:if test="%{email!=null}"><s:property value="email" /></s:if>
	</h5>
	<br/>
</div>
<div id="resizetable" width="50%" height="660">
<div style="overflow-y :auto;height: 580px;width: 98%" >
<TABLE cellSpacing="0" cellPadding="0" border=1 width="95%" style="margin-left: 20px;">
	<tr bgColor=#cccccc>
		<td>商户号</td>
		<td>支付结果</td>
		<td>流水号</td>
		<td>金额(外币)</td>
		<td>金额(人民币)</td>
		<td>授权号</td>
		<td>交易日期</td>
		<td>终端号</td>
		<td>卡号</td>
		<td>Email</td>
		<td>IP</td>
		<td>分值</td>
		<td>备注</td>
	</tr>
	<%int i=1;%>
	<s:iterator id="p" value="tradeDetial">
		<tr onclick="showmenu('menu<%=i %>');" onMouseOver="this.bgColor='#BBBBFF'; "  onMouseOut="this.bgColor='#FFFFFF';">
		<td><s:property value="#p[1]" />
		</td>
		<td><s:property value="states.getStateName(#p[0].tradeState,1)" escape="false" />&nbsp;</td>
		<td><s:if test="%{#p[0].orderNo.indexOf('*',1)>0}"><span style="color:red"><s:property value="#p[0].orderNo" /></span></s:if>
		<s:else><s:property value="#p[0].orderNo" /></s:else>
		</td>
		<td><s:property value="#p[0].tradeAmount" /></td>
		<td><s:property value="#p[0].rmbAmount" /></td>
		<td><s:property value="#p[0].VIPAuthorizationNo" />&nbsp;</td>
		<td><s:property value="#p[0].tradeTime" /></td>
		<td><s:property value="#p[0].VIPTerminalNo" /></td>
		<td>

			<s:property value="getCarNo(#p[2].cardNo).substring(0,6)"/>******<s:property value="getCarNo(#p[2].cardNo).substring(12)"/>

		<td><s:property value="#p[2].email" /></td>
		<td><s:property value="#p[2].ip" /></td>
		<td><s:property value="#p[2].maxmindValue" /></td>
		<td><s:property value="#p[0].remark" /></td>
	</tr>
	<tr>
		<td colspan="2"></td>
		<td colspan="9">
			<div id='menu<%=i %>' style="display:none;">
			<table width="100%" border="0" cellspacing="0" cellpadding="1" height="100">
			  <tr>
			    <td bgcolor="#215086" width="32%">
			     <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
			     	<tr class="tableHeading">
			     		<td height="30" colspan="3">
			         <h3>&nbsp;Billing Information</h3>
			        </td>
			      </tr>
			      <tr>
			      	<td width="5"></td>
			     		<td width="200" align="left" valign="top">
			           <address><s:property value="#p[2].firstName" />&nbsp;<s:property value="#p[2].lastName" /><br /><s:property value="#p[2].phone" /><br /><s:property value="#p[2].zipcode" /><br /> <s:property value="#p[2].address" /><br /> <s:property value="#p[2].city" /><br />  <s:property value="#p[2].state" /><br /> <s:property value="#p[2].country" /><br /></address>
			     		</td>
			     		<td>
			           <div></div> 			
			     		</td>
			     	</tr>
			     	      <tr>
			      	<td width="5"></td>
			     		<td align="left" colspan="2"></td>
			     	</tr>
			     	<tr>
			      	<td width="5"></td>
			     		<td align="left" colspan="2" height="50"><s:property value="#p[2].productInfo" /></td>
			     	</tr>
			     	<tr><td colspan="3" height="5"> </td></tr>
			     </table>
			    </td>
			        <td bgcolor="#FFFFFF" width="2%" rowspan="6"></td>
			    <td bgcolor="#215086" width="32%">
			     <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
			     	<tr>
			     		<td height="30" colspan="3">
			         <h3>&nbsp;Shipping Information</h3>
			        </td>
			      </tr>
			      <tr>
			      	<td width="5"></td>
			     		<td width="200" align="left" valign="top">
			           <address><s:property value="#p[2].shippingFullName" /><br /><s:property value="#p[2].shippingPhone" /><br /><s:property value="#p[2].shippingZip" /><br /> <s:property value="#p[2].shippingAddress" /><br /> <s:property value="#p[2].shippingCity" /><br />  <s:property value="#p[2].shippingState" /><br /> <s:property value="#p[2].shippingCountry" /><br /></address>
			     		</td>
			     		<td>
			           <div></div>	
			     		</td>
			     	</tr>
			     
			     	<tr><td colspan="3" height="5"></td></tr>
			    </table></td>
			    <td bgcolor="#FFFFFF" width="2%" rowspan="6"></td>
			    <td bgcolor="#215086" width="32%">
			     <table width="100%" height="100%" border="0" cellpadding="0" cellspacing="0" bgcolor="#FFFFFF">
			     	<tr>
			     		<td height="30" colspan="3">
			         <h3>&nbsp;Shipping tradeUrl</h3>
			        </td>
			      </tr>
			      <tr>
			      	<td width="5"></td>
			     		<td width="200" align="left" valign="top">
			     		<s:property value="#p[0].tradeUrl" />
			     		</td>
			     		<td>
			           <div></div>	
			     		</td>
			     	</tr>
			     
			     	<tr><td colspan="3" height="5"></td></tr>
			    </table></td>
			     	 
			     </tr>
			</table>
			</div>
		</td>
	</tr>
	<%i=i+1; %>
	</s:iterator>
	<input type="hidden" id="infoindex2" value="<%=i %>" />
</table>
</div>
</div>
<script language="javascript">
function showmenu(targetid){
	var j = document.getElementById("infoindex2").value;
    var k=1;
    for(k;k<j;k++){
    var f='menu'+k;
    if(targetid!=f){
  		document.getElementById(f).style.display="none";   
     }
     }
    if (document.getElementById){
        target=document.getElementById(targetid);
            if (target.style.display=="block"){
                target.style.display="none";
            } else {
                target.style.display="block";
            }
    }
}
</script>








