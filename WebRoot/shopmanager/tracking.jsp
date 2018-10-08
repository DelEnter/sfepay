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
    	<s:form action="toupdateTrack" method="post" theme="simple">
    		<table>
    			<tr>
    				<td>商户号</td>
    				<td>
    					<s:textfield name="merchantno"/>
    				</td>
    			</tr> 
    			<tr>
    				<td>
    					<s:submit value="查询"/>
    				</td>
    			</tr>
    		</table>
    	</s:form>
    	</p>
    	<s:form action="updateTrack" method="post" theme="simple">
    		<table>
    			<tr>
    				<td>商户号</td>
    				<td>
    					<s:textfield name="merchantno"/>
    				</td>
    			</tr> 
    			<tr>
    				<td>1--发送跟踪单号邮件：</td>
    				<td>
    					<s:textfield name="flag"/>   ： 0为默认的发送邮件，1 不发送邮件
    				</td>
    			</tr>     			
    			<tr>
    			<td>2--是否发送商户客服联系方式：</td>
    			<td>
    			<s:textfield name="kefuflag"/>   ： 0为默认的不发，1 发送
    			</td>
    			</tr>     			
    			<tr>
    			<td>3--邮件内容状态：</td>
	    			<td>
	    				<s:textfield name="contentflag"/>   ： 0为默认的不发广告，1 发送广告
	    			</td>
    			</tr>     			
    			<tr>
    			<td>4--交易成功后否发生邮件给商户：</td>
	    			<td>
	    				<s:textfield name="successtomerchant"/>   ： 0发送，1 不发送
	    			</td>
    			</tr>     			
    			<tr>
    			<td>5--交易界面是否显示汇率：</td>
	    			<td>
	    				<s:textfield name="showrate"/>   ： 0不显示，1 显示
	    			</td>
    			</tr>     			
    			<tr> 
    			<td>6--发生拒付后是否发送邮件给商户：</td>
	    			<td>
	    				<s:textfield name="chargebacktomerchant"/>   ：0不发送，1 发送
	    			</td>
    			</tr>     			
    			<tr> 
    			<td>7--实时通道不待处理,直接失败：</td>
    			<td>
    			<s:textfield name="failureEVIP"/>   ：0默认，1 直接失败
    			</td>
    			<tr> 
    			<td>8--显示DCC金额以及下载：</td>
    			<td>
    			<s:textfield name="dccmessage"/>   ：0不显示，1 显示
    			</td>    			
    			</tr>     			
    			<tr>   	    			  			
    				<td>
    					<s:submit value="保存"/>
    				</td>
    			</tr>
    		</table>
    	</s:form>
    </center>
  </body>
</html>
