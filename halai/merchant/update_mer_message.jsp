<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
    
    <title>修改商户信息</title>
  <head>
	<LINK href="../css/head.css" type=text/css rel=stylesheet/>
	<script language="javascript">
		function detialinfo(){
			var tradeid = document.getElementById("tradeid").value; 
			window.showModalDialog('toUpdateMerPassword.action?trade.id='+tradeid, window,'dialogHeight:325px;dialogWidth:650px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
		}
		function xiugaimima(){
			window.location="toMerModifyPwd.action";
		}
	</script>
  </head>
  <body>
  	<s:action name="indexMenu" executeResult="true"/>
  	
  	<div class="mainbody">
	   <div class="list">
	   
	      <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">修改商户信息</li>
             <li class="lilistother"><a href="#" onClick="xiugaimima()">修改密码</a></li>
            
           </ul>
       
         </div>
		 
		  <div class="listlist">
		    
            <s:form action="updateMerMessage" name="form1" method="post" theme="simple">
	    	<table width=80% border="0" cellspacing="5"  cellpadding="0">
	    		<tr>
	    			<td width=25% class="font_14px">商户固定电话</td>
	    			<td>
	    				<s:hidden name="mer.id" id="tradeid"/>
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
	    				<s:property value="mer.certificateno"/>
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
	    			<td class="font_14px">mer</td>
	    			<td>
	    				<input type="submit"  value="修改" class="input_button_01"/>
	    			</td>
	    		</tr>
	    	</table>
	    	
    	</s:form>
		    
		   </div>
     </div>
   </div>
    <!--尾部begin-->
	<%@ include file="foot.jsp"%>
	<script language="javascript">	
		var temflag='<s:property value='flag'/>';
		if(temflag==1){
			alert("修改商户信息成功");
		}else if(temflag==2){
			alert("修改商户信息失败");
		}		
	</script>	
  </body>
</html>
