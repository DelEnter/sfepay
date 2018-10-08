<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
 <%@ include file="../include/checkAll.jsp" %>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>黑名单管理</title>
</head>
<div align="center" >
	<h3>黑名单管理</h3>
</div>
<script language="JavaScript" type="text/JavaScript">
function addSensitive(){
	
	if(document.getElementById("billNo").value==""&&document.getElementById("merId").value==""&&document.getElementById("email").value==""&&document.getElementById("tradeUrl").value==""&&document.getElementById("products").value==""&&document.getElementById("phone").value==""&&document.getElementById("zipCode").value==""){
		alert("至少有一项不能为空！");
		return false;
	}
	if(confirm("确认添加吗？")){
	document.getElementById("addformu").submit();
	}
}
function query(){
	document.getElementById("formu").submit();
}
function delSen(type){
	var str=document.getElementsByName("orderIds");
	var ids="";
	for(var i=0;i<str.length;i++){
		if(str[i].checked==true){
			var value=str[i].value;
			ids+=value+",";
		}
	}
	if(ids==""){
		alert("请选择要操作的项！");
		return  false;
	}
	if(confirm("确认删除吗？")){
	window.location="../PaySystem/delVisaRisk.action?riskIds="+ids+"&delType="+type;
	}
}
function updateSen(orderId){
	if(confirm("确认要把此信息的交易都更新成失败吗？")){
		window.location="../PaySystem/updateVisaRisk.action?riskOrderId="+orderId;
		}
}
function checkWeb(){
	if(document.getElementById("noWeb").checked){
		document.getElementById("isWeb").value="1";
	}else{
		document.getElementById("isWeb").value="0";
	}
	if(document.getElementById("noQWeb").checked){
		document.getElementById("isQweb").value="1";
	}else{
		document.getElementById("isQweb").value="0";
	}
}
</script>
<s:form id="addformu" action="addVisaRisk" method="post" theme="simple">
<table align="center">
		<tr class=TR_Title>
	 		<td align="right">风险卡号：</td>
	 		<td><input type="text" id="billNo" name="highRiskInfo.cardno" maxlength="16"></td>
	 		<td align="right">风险email：</td>
	 		<td><input type="text" id="email" name="highRiskInfo.email"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">风险Ip：</td>
	 		<td><input type="text" id="tradeUrl" name="highRiskInfo.ip"></td>
	 		<td align="right">交易网址：</td>
	 		<td><input type="text" id="products" name="highRiskInfo.tradeUrl"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">风险电话：</td>
	 		<td><input type="text" id="phone" name="highRiskInfo.phone"></td>
	 		<td align="right">风险邮编：</td>
	 		<td><input type="text" id="zipCode" name="highRiskInfo.zipCode"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">风险商户：</td>
	 		<td><input type="text" id="merId" name="highRiskInfo.merId"></td>
	 		<td align="right">交易是否直接取消：</td>
	 		<td><input type="checkbox" id="noWeb" onclick="checkWeb()"><input type="hidden" id="isWeb" name="isWeb" value="0">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	 		是否关联网址：<input type="checkbox" id="noQWeb" onclick="checkWeb()"><input type="hidden" id="isQweb" name="isQweb" value="0"></td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="button" onclick="addSensitive()" value="添加" >
				</td>
	 		</tr>
	</table>
	</s:form>
	<hr/>
	<s:form id="formu" action="visaRisk" method="post" theme="simple">
	<table align="center">
		<tr class=TR_Title>
	 		<td align="right">风险卡号：</td>
	 		<td><input type="text" id="cardNo" name="cardNo" maxlength="16" value="<s:property value='cardNo' />"></td>
	 		<td align="right">风险email：</td>
	 		<td><input type="text" id="email" name="email" value="<s:property value='email' />"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">风险Ip：</td>
	 		<td><input type="text" id="ip" name="ip" value="<s:property value='ip' />"></td>
	 		<td align="right">风险商户：</td>
	 		<td><input type="text" id="merNo" name="merNo" value="<s:property value='merNo' />"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">风险电话：</td>
	 		<td><input type="text" id="phone" name="phone" value="<s:property value='phone' />"></td>
	 		<td align="right">风险邮编：</td>
	 		<td><input type="text" id="zipCode" name="zipCode" value="<s:property value='zipCode' />"></td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td>开始日期</td> 		
		 		<td>
		 		   <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>
		 		</td>
		 		<td>结束日期</td>
		 		<td>
		 			<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>
		 		</td>
	 		</tr>
	 		<tr class=TR_Title>
	 		<td align="right">交易网址：</td>
	 		<td><input type="text" id="tradUrl" name="tradUrl" value="<s:property value='tradUrl' />"></td>
	 		<td align="right">名单状态：</td>
	 		<td>
	 		<s:select name="isFail" list="#{'':'全部','1':'已失败'}" listKey="key" listValue="value"/>
	 		</td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="button" onclick="query()" value="查询" >
				</td>
				
			</tr>
	</table>
	<br/>
	<br/>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr align="center" bgColor=#cccccc>
		<td width="4%" bgcolor="#FFFFFF" align="center">
  			<input type="checkbox" onclick='chkall("formu",this)' name=chk>
  		</td>
		<td width="10%">卡号</td>
		<td width="14%">email</td>
		<td width="10%">Ip</td>
		<td width="8%">电话</td>
		<td width="7%">邮编</td>
		<td width="5%">商户号</td>
		<td width="17%">交易网址</td>
		<td width="10%">添加日期</td>
		<td width="5%">操纵员</td>
		<td width="5%">操作</td>
	</tr>
	<s:iterator id="risk" value="info.result">
	<tr align="center">
	 	<td bgcolor="#FFFFFF" align="center"><input type="checkbox" name="orderIds" value="<s:property value="#risk[0].id"/>"/></td>
		<s:if test="%{#risk[0].errorColumn==1}">
		<td style="font-size: 10px; color: red"><s:property value="#risk[0].cardno" />&nbsp;</td>
		</s:if>
		<s:else>
		<td style="font-size: 10px;"><s:property value="#risk[0].cardno" />&nbsp;</td>
		</s:else>
		<s:if test="%{#risk[0].errorColumn==2}">
		<td style="font-size: 10px; color: red"><s:property value="#risk[0].email" />&nbsp;</td>
		</s:if>
		<s:else>
		<td style="font-size: 10px"><s:property value="#risk[0].email" />&nbsp;</td>
		</s:else>
		<s:if test="%{#risk[0].errorColumn==3}">
		<td style="font-size: 10px; color: red"><s:property value="#risk[0].ip" />&nbsp;</td>
		</s:if>
		<s:else>
		<td style="font-size: 10px"><s:property value="#risk[0].ip" />&nbsp;</td>
		</s:else>
		<s:if test="%{#risk[0].errorColumn==5}">
		<td style="font-size: 10px; color: red"><s:property value="#risk[0].phone" />&nbsp;</td>
		</s:if>
		<s:else>
		<td style="font-size: 10px"><s:property value="#risk[0].phone" />&nbsp;</td>
		</s:else>
		<s:if test="%{#risk[0].errorColumn==6}">
		<td style="font-size: 10px; color: red"><s:property value="#risk[0].zipCode" />&nbsp;</td>
		</s:if>
		<s:else>
		<td style="font-size: 10px"><s:property value="#risk[0].zipCode" />&nbsp;</td>
		</s:else>
		<td style="font-size: 10px"><s:property value="#risk[0].merId" />&nbsp;</td>
		<s:if test="%{#risk[0].errorColumn==4}">
		<td style="font-size: 10px; color: red"><s:property value="#risk[0].tradeUrl" />&nbsp;</td>
		</s:if>
		<s:else>
		<td style="font-size: 10px"><s:property value="#risk[0].tradeUrl" />&nbsp;</td>
		</s:else>
		<td style="font-size: 10px"><s:property value="#risk[0].createDate" />&nbsp;</td>
		<td style="font-size: 10px"><s:property value="#risk[0].operator" />&nbsp;</td>
		<td style="font-size: 10px"><a href="#" onclick="updateSen('<s:property value="#risk[0].id"/>')">更新直接失败</a></td>
	</tr>
	</s:iterator>
</table>
<div align="center">
<pages:pages value="info" beanName="info" formName="getElementById('formu')" />
<table width="100%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 20px">
                    <tr>
                      <td align="left">
                      <input type="button" value="删除整行" onclick="delSen('1')" />
                      <input type="button" value="只删除网址 " onclick="delSen('0')" /> 
                      </td>
                    </tr>
                  </table>
</div>
<!-- 下面这段script代码必须放在form体的最后  
             loadcalendar方法的五个参数分别解释如下：
             1、日期显示文本框的ID号
             2、触发日历控件显示的控件ID号
             3、要显示的日期格式，%Y表示年，%m表示月，%d表示日
             4、是否带周显示，默认是不带
             5、是否带时间显示，默认是不带
             6、日历显示文字的语言，默认是中文 -->
        <script language="javascript" type="text/javascript">

            loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "en");
            loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "en");
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
</s:form>

