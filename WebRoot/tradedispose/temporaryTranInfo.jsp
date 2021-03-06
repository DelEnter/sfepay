<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<title>重跑审核</title>
<script language="JavaScript" src="../js/util.js"></script>
<link type="text/css" rel="stylesheet" href="../css/other.css" />
<%@ include file="../include/checkAll.jsp" %>
</head>
<script language="JavaScript" type="text/JavaScript">
function audit(){
	var str=document.getElementsByName("orderIds");
	var channelName=document.getElementById("channelName").value;
	var statusType=document.getElementById("statusType").value;
	var ids="";
	for(var i=0;i<str.length;i++){
		if(str[i].checked==true){
			var value=str[i].value;
				ids+=value+",";
		}
	}
	if(ids==""){
		alert("请选择要操作的项！");
		return false;
	}
	if(confirm("确定重跑吗？")){
	window.location="../PaySystem/temporaryInfo?ids="+ids+"&channelName="+channelName+"&statusType="+statusType;
	}
}
function delWebSite(){
	var str=document.getElementsByName("orderIds");
	var statusType=document.getElementById("statusType").value;
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
	window.location="../PaySystem/delTemporaryInfo?ids="+ids+"&statusType="+statusType;
	}
}
function toAudit(){
	var str=document.getElementsByName("orderIds");
	var statusType=document.getElementById("statusType").value;
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
	if(confirm("确认提交重审吗？")){
	window.location="../PaySystem/auditTemporaryInfo?ids="+ids+"&statusType="+statusType;
	}
}

function toRisk(){
	var str=document.getElementsByName("orderIds");
	var statusType=document.getElementById("statusType").value;
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
	if(confirm("确认提交疑似风险订单重审吗？")){
	window.location="../PaySystem/riskTemporaryInfo?ids="+ids+"&statusType="+statusType;
	}
}

function queryDetail(orderId,url,tradTime){
	window.open("../PaySystem/temporaryDetail?orderId="+orderId+"&tranUrl="+url+"&tradtime="+tradTime);
}
function getCardNoInfo(cardNo6,cardNo,ip,email){
	openWindow('../PaySystem/getCardNoAnal.action?cardNo6='+cardNo6+'&cardNo='+cardNo+'&ip='+ip+'&email='+email,'12');
}

function yesOrnoAnal(){
	var j = document.getElementById("infoindex").value;
	var isAnal=document.getElementById("isAnal");
    for(var k=1;k<j;k++){
  		document.getElementById('analcardNoDiv'+k).style.display="block";
  		document.getElementById('analcardNoDiv'+k+k).style.display="none";
  		document.getElementById('analIpDiv'+k).style.display="block";
  		document.getElementById('analIpDiv'+k+k).style.display="none";
  		document.getElementById('analEmailDiv'+k).style.display="block";
  		document.getElementById('analEmailDiv'+k+k).style.display="none";
  		if(isAnal.checked==true){
  			document.getElementById('analcardNoDiv'+k).style.display="none";
  	  		document.getElementById('analcardNoDiv'+k+k).style.display="block";
  	  		document.getElementById('analIpDiv'+k).style.display="none";
  			document.getElementById('analIpDiv'+k+k).style.display="block";
  			document.getElementById('analEmailDiv'+k).style.display="none";
  			document.getElementById('analEmailDiv'+k+k).style.display="block";
  		}
     }
}
</script>
<body>
<h3 align="center"><s:if test="%{statusType==0}">重跑交易</s:if>
<s:elseif test="%{statusType==1}">重跑交易复审</s:elseif>
<s:elseif test="%{statusType==5}">疑似风险订单重跑复审</s:elseif>
<s:else>重跑交易失败处理</s:else></h3>
<s:form action="toTemporaryInfo" id="form1" namespace="/PaySystem">
<input type="hidden" id="statusType" name="statusType" value='<s:property value="statusType"/>'>
<div style="margin: 20px 10px 10px 100px">
                  <table align="center" >
                    <!-- <tr class=TR_Title>
                      <td>&nbsp;订单号： </td>
                      <td><input type="text" name="orderNo" value='<s:property value="orderNo"/>'></td>&nbsp;&nbsp;
                      <td>&nbsp;通道： </td>
                      <td>
                      	<s:select id="channelName" name="channelName" list="#{'CA':'CA','HJ':'HJ'}" listKey="key" listValue="value" theme="simple"/>
                      </td>
                    </tr>-->
					<tr class=TR_Title>
                      <td>&nbsp;订单号： </td>
                      <td><input type="text" name="orderNo" value='<s:property value="orderNo"/>'></td>&nbsp;&nbsp;
                      <td>&nbsp;通道： </td>
                      <td><select id="channelName" name="channelName">
                      	<option value="CA">CA</option>
                      	<option value="HJ">HJ</option>
						<option value="VPN">VPN</option>
						<option value="HW">HW</option>
                      </select>
                      </td>
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
                      <td>&nbsp;商户号： </td>
                      <td><input type="text" name="merchantNo" value='<s:property value="merchantNo"/>' /></td>
                      <td>&nbsp;备注： </td>
                      <td><input type="text" name="remark" value='<s:property value="remark"/>' /></td>
                    </tr>
                    <tr class=TR_Title>
                      <td>&nbsp;ip： </td>
                      <td><input type="text" name="qip" value='<s:property value="qip"/>' /></td>
                      <td>&nbsp;email： </td>
                      <td><input type="text" name="qemail" value='<s:property value="qemail"/>' /></td>
                    </tr>
                    <tr class=TR_Title>                      
                      <td>&nbsp;交易网站： </td>
                      <td><input type="text" name="tturl" value='<s:property value="tturl"/>' /></td>
                    </tr>
			 		<tr class=font-align>
					<td colSpan="8" align="center"><br>
						<input type="submit" value="查询" >&nbsp;&nbsp;&nbsp;<input type="checkbox" id="isAnal" onclick="yesOrnoAnal()">
					</td>
					</tr>
                  </table>
                  </div>
                  <div align="center"><s:if test="%{msg!=null}"><s:property value="msg" /></s:if></div>
                  <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#333333" style="margin-top: 20px">
                    <tr>
                      <td width="4%" bgcolor="#FFFFFF" align="center">
  					  <input type="checkbox" onclick='chkall("form1",this)' name=chk>
  					  </td>
                      <td height="30" width="7%" align="center" bgcolor="#cccccc" class="font14px">商户号 </td>
                      <td height="30" width="20%" align="center" bgcolor="#cccccc" class="font14px">订单号</td>
					  <td height="30" width="15%" align="center" bgcolor="#cccccc" class="font14px">交易卡号</td>
                      <td height="30" width="10%" align="center" bgcolor="#cccccc" class="font14px">交易金额</td>
                      <td height="30" width="7%" align="center" bgcolor="#cccccc" class="font14px">币种</td>
					  <td height="30" width="8%" align="center" bgcolor="#cccccc" class="font14px">ip</td>
                      <td height="30" width="10%" align="center" bgcolor="#cccccc" class="font14px">邮箱</td>
					  <td height="30" width="5%" align="center" bgcolor="#cccccc" class="font14px">风险分数</td>
					  <td height="30" width="5%" align="center" bgcolor="#cccccc" class="font14px">国家</td>
                      <td height="30" width="9%" align="center" bgcolor="#cccccc" class="font14px">备注</td>
                    </tr>
                    <%int i=1;%>
                    <s:iterator value="info.result" id="s">
                    <tr>
                      <td bgcolor="#FFFFFF" align="center"><input type="checkbox" name="orderIds" value="<s:property value="#s[0]"/>"/></td>
                      <td height="30" align="center" bgcolor="#FFFFFF"><font color=#930909> <s:property value="#s[1].substring(0,4)"/></font></td>
                      <td onMouseOver="this.style.backgroundColor='#fff'" 
							style="FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif;background:#fff" 
							onmouseout="this.style.backgroundColor='#fff'" align="center"><A 
							title="交易卡号:  <s:property value="getCarNo(#s[6].cardNo).substring(0,6)"/>******<s:property value="getCarNo(#s[6].cardNo).substring(12)"/>&#13;&#10;&#13;&#10;交易时间：  <s:property value="#s[5]"/>&#13;&#10;&#13;&#10;联系电话： <s:property value="#s[6].phone"/>&#13;&#10;&#13;&#10;姓名：  <s:property value="#s[6].firstName"/>  <s:property value="#s[6].lastName"/>&#13;&#10;&#13;&#10;输入的发卡行：    <s:property value="#s[6].userBank"/>&#13;&#10;&#13;&#10;国家：    <s:property value="#s[6].country"/>&#13;&#10;&#13;&#10;地址：  <s:property value="#s[6].address"/>&#13;&#10;&#13;&#10;物品信息：  <s:property value="#s[6].productInfo"/>&#13;&#10;&#13;&#10;邮政编码：  <s:property value="#s[6].zipcode"/>&#13;&#10;&#13;&#10;风险评分：   <s:property value="#s[6].maxmindValue"/>&#13;&#10;&#13;&#10;交易网站：  <s:property value="#s[7]"/>&#13;&#10;&#13;&#10;bankCountry : <s:property value="#s[6].bankcountry"/>&#13;&#10;&#13;&#10;bankName : <s:property value="#s[6].bankname"/>" 
							href="#" onclick="queryDetail('<s:property value="#s[0]"/>','<s:property value="#s[7]"/>','<s:property value="#s[5]"/>')"><s:property value="#s[1]"/>&nbsp; </A>
		    		</td>
					 <td style="FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif;background:#fff" align="center"><div id='analcardNoDiv<%=i%>'><a onclick="getCardNoInfo('<s:property value="getCarNo(#s[6].cardNo).substring(0,6)"/>','','','')"   href="#"><s:property value="getCarNo(#s[6].cardNo).substring(0,6)"/></a>******<a onclick="getCardNoInfo('','<s:property value="getCarNo(#s[6].cardNo)"/>','','')"   href="#"><s:property value="getCarNo(#s[6].cardNo).substring(12)"/></a>
							</div><div id='analcardNoDiv<%=i%><%=i%>' style="display:none;"><s:property value="getCarNo(#s[6].cardNo).substring(0,6)"/>******<s:property value="getCarNo(#s[6].cardNo).substring(12)"/></div></td>
                      <td height="30" align="center" bgcolor="#FFFFFF"><font color=#930909> <s:property value="#s[2]"/></font>	</td>
                      <td height="30" align="center" bgcolor="#FFFFFF"><s:property value="states.getCurrencyTypeByNo(#s[3])"/></td>
					  <td height="30" align="center" bgcolor="#FFFFFF"><div id='analIpDiv<%=i%>'><a onclick="getCardNoInfo('','','<s:property value="#s[6].ip"/>','')"   href="#"><s:property value="#s[6].ip"/></a></div>
					  <div id='analIpDiv<%=i%><%=i%>' style="display:none;"><s:property value="#s[6].ip"/></div>
					  </td>
					  <td height="30" align="center" bgcolor="#FFFFFF"><div id='analEmailDiv<%=i%>'><a onclick="getCardNoInfo('','','','<s:property value="#s[6].email"/>')"   href="#"><s:property value="#s[6].email"/></a></div>
					  <div id='analEmailDiv<%=i%><%=i%>' style="display:none;"><s:property value="#s[6].email"/></div></td>
					  <td height="30" align="center" bgcolor="#FFFFFF"><s:property value="#s[6].maxmindValue"/></td>
					  <td height="30" align="center" bgcolor="#FFFFFF"><s:property value="#s[6].country"/></td>
					  <td height="30" align="center" bgcolor="#FFFFFF"><s:property value="#s[8]"/></td>
                    </tr>
					<%i++; %>
                    </s:iterator>
                    
  </table>
	<input type="hidden" id="infoindex" value="<%=i%>">
<pages:pages value="info" beanName="info" formName="getElementById('form1')" />
<table width="80%" border="0" cellspacing="0" cellpadding="0" style="margin-top: 20px">
                    <tr>
                      <td align="left">
                      <s:if test="%{statusType!=3}"><input type="button" value="重 跑" onclick="audit()" />&nbsp;&nbsp;&nbsp;</s:if>
                      <s:if test="%{statusType==0}"><input type="button" value="重跑交易删除" onclick="delWebSite()" /></s:if>
					  <s:if test="%{statusType==1}"><input type="button" value="复审删除" onclick="delWebSite()" /></s:if>
					  <s:if test="%{statusType==2}"><input type="button" value="失败处理删除" onclick="delWebSite()" /></s:if> 
					  <s:if test="%{statusType==5}"><input type="button" value="疑似风险订单处理删除" onclick="delWebSite()" /></s:if> 
                      <s:if test="%{statusType==0||statusType==3}">
                      &nbsp;&nbsp;&nbsp;<input type="button" value="复 审" onclick="toAudit()" /> 
                      </s:if>
                      <s:if test="%{statusType==0}">
                      &nbsp;&nbsp;&nbsp;<input type="button" value="疑似风险订单复审" onclick="toRisk()" /> 
                      </s:if>
                      <s:if test="%{statusType==3}"><input type="button" value="直接删除" onclick="delWebSite()" /></s:if> 
                      </td>
                    </tr>
                  </table>
                  
                  <script language="javascript" type="text/javascript">

            loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "en");
            loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "en");
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
            
        </script>
</s:form>
</body>
</html>