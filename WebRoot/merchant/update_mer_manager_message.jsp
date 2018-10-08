<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<html>
    
    <title>修改商户管理信息</title>
  <head>
	<LINK href="../css/head.css" type=text/css rel=stylesheet>
  </head>
  <body>
 	<center>
 		<h3>修改商户管理信息</h3>
            <s:form action="updateMerManagerMessage" name="form1" method="post" theme="simple">
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
	    				<s:hidden name="mer.id"/>
	    				<s:textfield name="mer.merphone"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">商户手机</td>
	    			<td>
	    				<s:textfield name="mer.mermobile"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">商户邮箱</td>
	    			<td>
	    				<s:textfield name="mer.meremail"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">商户业务联系人姓名</td>
	    			<td>
	    				<s:textfield name="mer.linkman"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>商户QQ</td>
	    			<td><s:textfield name="mer.merqq"/></td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">联系人身份证号码</td>
	    			<td>
	    				<s:textfield name="mer.linkmancertificateno"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">联系人固定电话</td>
	    			<td>
	    				<s:textfield name="mer.linkmanphone"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">联系人手机</td>
	    			<td>
	    				<s:textfield name="mer.linkmanmobile"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">联系人地址</td>
	    			<td>
	    				<s:textfield name="mer.linkmanadress"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">联系人邮箱</td>
	    			<td>
	    				<s:textfield name="mer.linkmanemail"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">网址</td>
	    			<td>
	    				<s:textfield name="mer.website"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">MDKey</td>
	    			<td>
	    				<s:textfield name="mer.md5key"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">开户名</td>
	    			<td>
	    				<s:textfield name="mer.accountname"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">开户行</td>
	    			<td>
	    				<s:textfield name="mer.bank"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">账号</td>
	    			<td>
	    				<s:textfield name="mer.cardno"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">所有权人</td>
	    			<td>
	    				<s:textfield name="mer.mername"/>
	    			</td>
	    		</tr>	
	    		<tr>
	    			<td class="font_14px">所有权人地址</td>
	    			<td>
	    				<s:textfield name="mer.meradress"/>
	    			</td>
	    		</tr>	    
	    		<tr>
	    			<td class="font_14px">有权人身份证</td>
	    			<td>
	    				<s:textfield name="mer.certificateno"/>
	    			</td>
	    		</tr>
				<tr>
	    			<td class="font_14px">首次开通时间</td>
	    			<td>
	    				<input id="openTime" type="text" name="openTime" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='mer.openTime'/>"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">年费</td>
	    			<td>
	    				<s:textfield name="mer.annualFee"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">备注</td>
	    			<td>
	    				<s:textfield name="mer.remark"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px">拒付手续费</td>
	    			<td>
	    				<s:textfield name="mer.dishonor"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_15px">是否开通合作物流</td>
	    			<td>
							<s:if test="mer==null">
							   <s:radio name="mer.expopenstatus" list="%{#{'1':'已开通','0':'未开通'}}" value="1"></s:radio>
							</s:if>
							<s:else>
								<s:radio name="mer.expopenstatus" list="%{#{'1':'已开通','0':'未开通'}}" ></s:radio>
							</s:else>	    				
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_16px">物流折扣</td>
	    			<td>
	    				<s:textfield name="mer.discountfee"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_14px"></td>
	    			<td>
	    				<s:submit value="修改"/>
	    			</td>
	    		</tr>
	    	</table>
	    	 <script language="javascript" type="text/javascript">

            loadcalendar('openTime', 'openTime', '%Y-%m-%d', false, true, "en");
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
        </script>
    	</s:form>
	</center>
  </body>
</html>
