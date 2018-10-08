<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script type="text/javascript" src="../js/json2.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>持卡人信息查询</title>
</head>
<script language="JavaScript">

	function replaceheng(val){
		var v = val.replace(/-/g,"");
		document.getElementById("cardnum").value=v;
	}
	function exportInfo(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("isdownload");
		exportX.value="1";
		formX.submit();
	}
	function query(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("isdownload");
		exportX.value="";
		formX.submit();
	}
	function addBackList(cardNo,email,ip){
		if(confirm("确认添加吗？")){
		window.location="../PaySystem/addBackList2.action?backCardNo="+cardNo+"&backEmail="+email+"&backIp="+ip;
		}
	}
	function addorDelSign(tradeId){
		if(confirm("确定做此操作吗？")){
			window.location="../PaySystem/addorDelSign.action?traderId="+tradeId;
			}
	}
	function showmenu(targetid,url,tradTime){
		
		var j = document.getElementById("infoindex").value;
	       var k=1;
	       for(k;k<j;k++){
		     var f='menu'+k;
		     var risk;
		     var openTime;
		     var tradNum;
		     var type;
		     if(targetid!=f){
		  		document.getElementById(f).style.display="none";   
		     }else{
		    	risk="risk"+k;
		    	openTime="openTime"+k;
		    	tradNum="tradNum"+k;
		    	type="type"+k;
		     }
	     }
	       
	    if (document.getElementById){
	        target=document.getElementById(targetid);
	            if (target.style.display=="block"){
	                target.style.display="none";
	            } else {
	                target.style.display="block";
	                document.getElementById("ajaxTradeUrl").value=ajaxTradeUrl;
	                document.getElementById("ajaxTradetime").value=ajaxTradetime;
	                getTradeWebInfo("getWebTradeDetail.action?ajaxTradeUrl="+url+"&ajaxTradetime="+tradTime,risk,openTime,tradNum,type);
	            }
	    }
	}
	function getTradeWebInfo(jsonObjGetUrl,risk,openTime,tradNum,type)
	{
		var myAjax = new Ajax.Request(
				jsonObjGetUrl,
				{
					//请求方式：POST
					method:'post',
					//请求参数
					//parameters:params,
					//指定回调函数
					onComplete: function(msg){
						var res = JSON.parse(msg.responseText);
						alert(res.jsonData);
						var item=res.jsonData.split(",");
						if(item[0]=="1"){
							document.getElementById(risk).style.color="red";
							document.getElementById(risk).innerHTML="是";
						}else{
							document.getElementById(risk).innerHTML="无";
						}
						document.getElementById(openTime).innerHTML=item[1];
						document.getElementById(type).innerHTML=item[2];
						document.getElementById(tradNum).innerHTML=item[3];
					},
					//是否异步发送请求
					asynchronous:false
				});
	}
	
</script>
<div align="center">
	<h3>持卡人信息查询</h3>
</div>
<s:form name="formu" id="formu" action="cardholdersQuery2" theme="simple" method="post">
<input type="hidden" name="isdownload" id="isdownload"/>
<input type="hidden" name="ajaxTradeUrl" id="ajaxTradeUrl"/>
<input type="hidden" name="ajaxTradetime" id="ajaxTradetime"/>
<TABLE cellSpacing=0 cellPadding=0 align=center border=0>
				<tr  class=TR_Title>
					<td>
						交易流水号
					</td>
					<td>
						<s:textfield name="orderNo" />
					</td>
					<td>
						商户号
					</td>
					<td>
						<s:textfield name="merchantNo" />
					</td>

				</tr>
				<tr>
					<td>
						支付状态
					</td>
					<td>
						<s:select name="isresult"
							list="#{'':'所有','1':'成功','0':'失败','2':'待处理','4':'待确认','5':'无返回'}"
							listKey="key" listValue="value" />
					</td>
		
					<td>
						开始日期
					</td>
					<td>
						 <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>
					</td>
					<td>
						结束日期
					</td>
					<td>
						<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>
					</td>
				</tr>
				<tr>
					<td>
						持卡人邮箱
					</td>
					<td>
						<s:textfield name="email"/>
					</td>
					<td>
						卡号
					</td>
					<td>
						<s:textfield name="cardnum"/>
					</td>

					
				</tr>
				<tr>
					<td>
						分值范围
					</td>
					<td>
						<s:textfield name="minmaxmind" size="5" />---<s:textfield name="maxmaxmind" size="5"/>
					</td>
					<td>
					IP
					</td>
					<td>
						<s:textfield name="ip"/>
					</td>
				</tr>
				<tr>
					<td>
						卡段前6
					</td>
					<td>
						<s:textfield name="six" size="5" />
					</td>
					<td>
						卡段后4
					</td>
					<td>
						<s:textfield name="four" size="5" />
					</td>
				</tr>
				<tr>
					<td>
						卡段前9
					</td>
					<td>
						<s:textfield name="nine" size="6" />
					</td>
					<td>
						卡段前12
					</td>
					<td>
						<s:textfield name="twelve" size="7" />
					</td>
				</tr>
				<tr>
					<td>
						交易网址
					</td>
					<td>
						<s:textfield name="tradeUrl" size="30" />
					</td>
					<td>
						备注
					</td>
					<td colspan="3">
						<s:textfield name="remark" size="30" />
					</td>
				</tr>
				<tr>
				<td>拒付状态</td>
				<td>
				<s:select name="isProtest" list="#{'':'所有','1':'已拒付','0':'未拒付'}" listKey="key" listValue="value"/>
				</td>
				<td>支付通道</td>
	 			<td colspan="3">
	 				<s:select name="channelId" list="channelList" listKey="id" listValue="channelName" headerKey="" headerValue="所有"/>
				</td>
				</tr>
				<tr>
				<td>退款情况</td>
		 		<td>
		 			<s:select name="istuikuan" list="#{'':'所有','1':'已退款','0':'未退款','2':'部分退款'}" listKey="key" listValue="value"/>
	 			</td>
	 			<td>
				国家简码
				</td>
	 			<td colspan="3">
						<s:textfield name="country" size="30" />
					</td>
				</tr>
				<tr>
				<td>持卡人姓名</td>
		 		<td>
		 			<s:textfield name="customerName" size="30" />
	 			</td>
	 			<td>
				电话
				</td>
	 			<td colspan="3">
						<s:textfield name="phone" size="30" />
					</td>
				</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="button" value="搜索" onclick="query()" />&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
						<input type="button" value="下载" onclick="exportInfo()" />
					</td>
				</tr>	
			</TABLE>
			<div align="center"><s:if test="%{msg!=null}"><s:property value="msg" /></s:if></div>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
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
		<td>操作</td>
	</tr>
	<s:set name="countt" value="1"/>
	<s:iterator id="p" value="info.result" status="s">
	<tr onclick="showmenu('menu<s:property value="#s.index+1" />','<s:property value="#p[0].tradeUrl" />','<s:property value="#p[0].tradeTime" />');" onMouseOver="this.bgColor='#BBBBFF'; "  onMouseOut="this.bgColor='#FFFFFF';">
		
		<td><s:property value="#p[1].merno" />
			<s:set name="countt" value="#countt+1"/>
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

			<s:property value="getCarNo(#p[2].cardNo)"/>

		<td><s:property value="#p[2].email" /></td>
		<td><s:property value="#p[2].ip" /></td>
		<td><s:property value="#p[2].maxmindValue" /></td>
		<td><s:property value="#p[0].remark" /></td>
		<td><input type="button" value="添黑名单"  onclick="addBackList('<s:property value="getCarNo(#p[2].cardNo)" />','<s:property value="#p[2].email" />','<s:property value="#p[2].ip" />')" />
		&nbsp;<s:if test="%{#p[0].orderNo.indexOf('*',1)>0}"><input type="button" value="删除标记" onclick="addorDelSign('<s:property value="#p[0].id" />')" /></s:if>
		<s:else><input type="button" value="添加标记" onclick="addorDelSign('<s:property value="#p[0].id" />')" /></s:else>
		</td>
	</tr>
	<tr>
		<td colspan="2"></td>
		<td colspan="9">
			<div id='menu<s:property value="#s.index+1" />' style="display:none;">
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
			     	<tr>
						 <td height="30" colspan="2">
						      是否有风险：<span id='risk<s:property value="#s.index+1" />'></span>
						 </td>
					</tr>
					<tr>
					<td height="30" colspan="3">
						        		所属类型：<span id='type<s:property value="#s.index+1" />'></span>
						        	</td>
					</tr>
					<tr>
						<td height="30" colspan="3">
						        		开通时间：<span id='openTime<s:property value="#s.index+1" />'></span>
						        	</td>
					</tr>
					<tr>
						<td height="30" colspan="2">
						        		交易笔数：第<span id='tradNum<s:property value="#s.index+1" />'></span>笔
						        	</td>
					</tr>
						        	
						        	
						        	
			    </table></td>
			     	 
			     </tr>
			</table>
			</div>
		</td>
	</tr>
	</s:iterator>
	<s:hidden name="#countt" id="infoindex"/>
</table>
<div align="center">
<pages:pages value="info" beanName="info" formName="getElementById('formu')" />
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

            loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "cn");
            loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "cn");
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
</s:form>








