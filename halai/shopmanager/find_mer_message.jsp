<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>查询商户信息</title>
   
	<link rel="stylesheet" type="text/css" href="../css/head.css">

  </head>
  
  <body>
    <center>
    	<h3>查询商户信息</h3>
    	<s:form action="ShowMerMessage" method="post" theme="simple">
    		<table>
    			<tr>
    				<td>商户号</td>
    				<td>
    					<s:textfield name="mer.merno"/>
    				</td>
    			</tr> 
    			<tr>
    				<td>
    					<s:submit value="查询"/>
    				</td>
    			</tr>
    		</table>
    	</s:form>
	    	<table  border="0" cellspacing="5"  cellpadding="0">
	    		<tr>
	    			<td>商户号</td>
	    			<td>
	    				<s:property value="mer.merno"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td width=25% class="font_14px">商户固定电话</td>
	    			<td>
	    				<s:property value="mer.merphone"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">商户手机</td>
	    			<td>
	    				<s:property value="mer.mermobile"/>	    			
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">商户邮箱</td>
	    			<td>
	    				<s:property value="mer.meremail"/>	    			

	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">商户业务联系人姓名</td>
	    			<td>
	    				<s:property value="mer.linkman"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>商户QQ</td>
	    			<td><s:property value="mer.merqq"/></td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">联系人身份证号码</td>
	    			<td>
	    				<s:property value="mer.linkmancertificateno"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">联系人固定电话</td>
	    			<td>
	    				<s:property value="mer.linkmanphone"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">联系人手机</td>
	    			<td>
	    				<s:property value="mer.linkmanmobile"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">联系人地址</td>
	    			<td>
	    				<s:property value="mer.linkmanadress"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">联系人邮箱</td>
	    			<td>
	    				<s:property value="mer.linkmanemail"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">网址</td>
	    			<td>
	    				<s:property value="mer.website"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">MDKey</td>
	    			<td>
	    				<s:property value="mer.md5key"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">开户名</td>
	    			<td>
	    				<s:property value="mer.accountname"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">开户行</td>
	    			<td>
	    				<s:property value="mer.bank"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">账号</td>
	    			<td>
	    				<s:property value="mer.cardno"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">所有权人</td>
	    			<td>
	    				<s:property value="mer.mername"/>
	    			</td>
	    		</tr>	
	    		<tr>
	    			<td class="font_14px">所有权人地址</td>
	    			<td>
	    				<s:property value="mer.meradress"/>
	    			</td>
	    		</tr>	    
	    		<tr>
	    			<td class="font_14px">有权人身份证</td>
	    			<td>
	    				<s:property value="mer.certificateno"/>
	    			</td>
	    		</tr>	
	    	</table>
    	
    </center>
  </body>
</html>
