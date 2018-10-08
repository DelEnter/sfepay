<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
    
    <title>Modify Merchant Information</title>
  <head>
	<LINK href="../css/head.css" type=text/css rel=stylesheet/>
	<script language="javascript">
		function detialinfo(){
			var tradeid = document.getElementById("tradeid").value; 
			window.showModalDialog('toUpdateMerPasswordEn.action?trade.id='+tradeid, window,'dialogHeight:325px;dialogWidth:650px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
		}
		function xiugaimima(){
			window.location="toMerModifyPwdEn.action";
		}
	</script>
  </head>
  <body>
  	<s:action name="indexMenuEn" executeResult="true"/>
  	
  	<div class="mainbody">
	   <div class="list">
	   
	      <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">Modify Merchant Information</li>
             <li class="lilistother"><a href="#" onClick="xiugaimima()">Modify Password</a></li>
            
           </ul>
       
         </div>
		 
		  <div class="listlist">
		    
            <s:form action="updateMerMessageEn" name="form1" method="post" theme="simple">
	    	<table width=80% border="0" cellspacing="5"  cellpadding="0">
	    		<tr>
	    			<td width=35% class="font_14px">Merchant Phone NO</td>
	    			<td>
	    				<s:hidden name="mer.id" id="tradeid"/>
	    				<s:textfield name="mer.merphone"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Merchant Mobilephone NO</td>
	    			<td>
	    				<s:textfield name="mer.mermobile"/>
	    			</td>
	    		</tr> 
	    		<tr>
	    			<td class="font_24px">Merchant E-mail</td>
	    			<td>
	    				<s:textfield name="mer.meremail"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Merchant business linkman</td>
	    			<td>
	    				<s:textfield name="mer.linkman"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td>Merchant QQ</td>
	    			<td><s:textfield name="mer.merqq"/></td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Linkman's Passport NO</td>
	    			<td>
	    				<s:property value="mer.certificateno"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Linkman's Telephone NO</td>
	    			<td>
	    				<s:textfield name="mer.linkmanphone"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Linkman's Mobilephone NO</td>
	    			<td>
	    				<s:textfield name="mer.linkmanmobile"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Linkman's Address</td>
	    			<td>
	    				<s:textfield name="mer.linkmanadress"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Linkman's E-mail</td>
	    			<td>
	    				<s:textfield name="mer.linkmanemail"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Website</td>
	    			<td>
	    				<s:textfield name="mer.website"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">MDKey</td>
	    			<td>
	    				<s:property value="mer.md5key"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Account Name</td>
	    			<td>
	    				<s:property value="mer.accountname"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Account-Opening Bank</td>
	    			<td>
	    				<s:property value="mer.bank"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Account</td>
	    			<td>
	    				<s:property value="mer.cardno"/>
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Send Email for Merchant(SUCCESS)</td>
	    			<td>
	    			<input type="radio" name="sendEmail" <s:if test="sendEmail==0">checked</s:if>  value="0" />NO
	    			<input type="radio" name="sendEmail" <s:if test="sendEmail==1">checked</s:if>  value="1" />YES
	    			</td>
	    		</tr>
	    		<tr>
	    			<td class="font_24px">Send Email for Merchant(Chargeback)</td>
	    			<td>
	    			<input type="radio" <s:if test="chargeback==0">checked</s:if>  name="chargeback"  value="0" />NO
	    			<input type="radio" <s:if test="chargeback==1">checked</s:if> name="chargeback" value="1" />YES
	    			</td>
	    		</tr>	    			    		
	    		<tr>
	    			<td class="font_24px"></td>
	    			<td>
	    				<input type="submit"  value="Modify" class="input_button_01"/>
	    			</td>
	    		</tr>
	    	</table>
	    	
    	</s:form>
		    
		   </div>
     </div>
   </div>
    <!--尾部begin-->
	<%@ include file="../merchant/foot.jsp"%>
	<script language="javascript">	
		var temflag='<s:property value='flag'/>';
		if(temflag==1){
			alert("SUCCESS");
		}else if(temflag==2){
			alert("Failure");
		}		
	</script>	
  </body>
</html>
