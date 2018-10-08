<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<head>
	<title>投诉管理列表</title>
</head>
<style>
body,table,tr,td{ font-size:12px;}
</style>
</HEAD>
	<script src="../js/mootools-1.2-core-jm.js" type="text/javascript"></script>
<script language="JavaScript" type="text/JavaScript">
	function reloadPage(){
	        window.location="../PaySystem/showmanager.action";
	}
	function add(){ 
	   openWindow('../PaySystem/showAddCom.action','12');
	}
	function tianjia() {	
		$('form1').action="addComplains";
		$('form1').submit();
	}

	function chaxun() {	
		$('form1').action="showmanager";
		$('form1').submit();
	}
		
	function getTradeNo(email,tradeno){
		var jsonRequest = new Request.JSON({async:false,url:'../jsonecpss/getTradeNoBy.action?tradeno='+tradeno+'&email='+email, onComplete: function(jsonObj){
			if(jsonObj!=null && jsonObj.jsonData !=null ){
				var d = jsonObj.jsonData.split("|");
				if(tradeno!=""){
					$('email').value = d[3];
				}
				$('cm.merchantNo').value = d[0];
				$('cm.orderNo').value = d[1];
				$('cm.merchantOrderNo').value = d[2];
				$('ordererror').innerHTML = "";
			}else{
				$('ordererror').innerHTML = "无符合条件交易";
			}
		}}).get({randerNum:Math.random()});	
	}
</script>
<BODY>
<div align="center">
	<h3>持卡人交易投诉</h3>
</div>
<s:form name="showmanager" namespace="PaySystem" name="form1" id="form1" theme="simple">
<TABLE cellSpacing=0 cellPadding=0 align=center border=0>	
			<tr class=TR_Title>
				<td>商户号</td>
		 		<td>
		 			<s:textfield name="merchantno"/>
		 		</td>
				<td>流水订单号</td>
		 		<td>
		 			<s:textfield name="orderNo"/>
		 		</td>
				<td>类型</td>
		 		<td>
		 			<s:select name="type" list="#@java.util.TreeMap@{'询问支付情况':'询问支付情况','要求终止订单/退款':'要求终止订单/退款','询问跟踪信息':'询问跟踪信息','差价问题':'差价问题','货不对版':'货不对版','询问商户联系信息':'询问商户联系信息'}"  headerKey="" headerValue="----" />
		 		</td>
		 	</tr>
		 	<tr>
		 		<td colspan="4" align="center">
		 			<input type="button" onclick="chaxun()" name="cha" value="查询">
		 		</td>
		 	</tr>
			</TABLE>
	<TABLE borderColor=#ffffff cellSpacing=0 cellPadding=0 width="80%" align=center 
	bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
  		<TR align=middle>
	    <TD bgColor=#cccccc>
	    	序列
	    </TD>
	    <TD bgColor=#cccccc>
	    	商户号
	    </TD>
	    <TD bgColor=#cccccc>
	    	流水订单号 
	    </TD>
	    <TD bgColor=#cccccc>
	    	商户订单号
	    </TD>
	    <TD bgColor=#cccccc>
	    	类型
	    </TD>
	    <TD bgColor=#cccccc>
	    	最新回复时间
	    </TD>
	    <TD bgColor=#cccccc>
			处理情况
	    </TD>
	    <TD bgColor=#cccccc>
	    	操作
	    </TD>
	  </TR>
	  
	  <s:iterator id="com" value="info.result" status="c"> 
		<tr class="font-align" style="word-wrap:break-word;word-break:break-all;">
			<td>
				<s:property value="#c.index+1"/>
			</td>
			<td>
				<s:property value="#com.merchantNo"/>
			</td>
			<td>
				<s:property value="#com.orderNo"/>
			</td>
			<td>
				<s:property value="#com.merchantOrderNo"/>
			</td>
			<td>
				<s:property value="#com.cmType"/>&nbsp;
			</td>
			<td>
				<s:property value="#com.lastDate"/>
			</td>
			<td>
				<s:if test="#com.processingResults==1">
					<font color="red">已处理</font>
				</s:if>
				<s:else>
					<font color="blue">未处理</font>
				</s:else>
			</td>
			<td>
				<a href="viewDetails.action?complainId=<s:property value="#com.id"/>" >详情</a>
			</td>
		</tr>
		</s:iterator>
		</table>
		<div align="center">
			<pages:pages value="info" beanName="info" formName="getElementById('form1')" />
		</div>
		
		<table width="800" border="0" cellspacing="1" cellpadding="1" align="center">
	<tr>
		<td align="center">
			<table align="center" border=0 cellpadding=1 cellspacing=1
				width="100%">
				<tr>
					<td align="right">
						<font color="red"><s:property value="messageAction"/></font>
					</td>
					<td>
					</td>
				</tr>
				<tr>
					<td align="right">
						E-mail ：
					</td>
					<td>
						<s:textfield name="email" onblur="getTradeNo(this.value,'')" size="35" value=""></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="right">
						商户号 ：
					</td>
					<td>
						<s:textfield name="cm.merchantNo" size="10" value=""></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="right">
						流水订单号 ：
					</td>
					<td>
						<s:textfield name="cm.orderNo" onblur="getTradeNo('',this.value)"  size="35" value=""></s:textfield>
						<div id="ordererror"></div>
					</td>
				</tr>
				<tr>
					<td align="right">
						商户订单号 ：
					</td>
					<td>
						<s:textfield name="cm.merchantOrderNo" size="35" value=""></s:textfield>
					</td>
				</tr>
				<tr>
					<td align="right">
						类型 ：
					</td>
					<td>
						<s:select name="cm.cmType" list="#@java.util.TreeMap@{'询问支付情况':'询问支付情况','要求终止订单/退款':'要求终止订单/退款','询问跟踪信息':'询问跟踪信息','差价问题':'差价问题','货不对版':'货不对版','询问商户联系信息':'询问商户联系信息','其他':'其他'}"  headerKey="" headerValue="----" />
					</td>
				</tr>
				<tr>
					<td align="right">
						内容 ：
					</td>
					<td>
						<s:textarea name="content" rows="10" cols="40" value=""></s:textarea>
					</td>
				</tr>
				<tr>
					<td colspan="2" align="center">
						<input type="button" onClick="tianjia();" class="windows_icon1" value="Submit" />
					</td>
				</tr>
			</table>
		</td>
	</tr>
</table>
		</s:form>
	</BODY>
</HTML>