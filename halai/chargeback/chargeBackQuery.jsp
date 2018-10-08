<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>拒付处理</title>
</head>
<script language="JavaScript" type="text/JavaScript">
	
	function checkSelect(){
		var select = document.formu.checkAll;
		if(select.checked==true){
			selectAll();
		}else{
			noselectAll();
		}
	}
	function selectAll() { 
		var o=document.formu.orders;
		//单条记录
		if(o.value!=null){
			o.checked=true;
		}
		//多条记录
	    for(var i=0;i<o.length;i++){
			if(o[i].name == "orders") {
				o[i].checked=true;
		    }
		}
	}
	function noselectAll() {
	    var o=document.formu.orders;
	    if(o.value!=null){
			o.checked=false;
		}
		 for(var i=0;i<o.length;i++){
				if(o[i].name == "orders")
			    {
   					o[i].checked=false;
			    }
			 }
	}
	
	
</script>
<script language="JavaScript" type="text/JavaScript">
	function reloadPage(){
        window.location="../PaySystem/chargebackTradeQuery";
	}	
	function chargeback(tradeid){
		if(confirm("确定要拒付这笔交易吗?")){
			window.location="../PaySystem/chargeBack.action?tradeId="+tradeid;
		}
	}
	function updateChargeback(chargebackid){
		openWindow('../PaySystem/toUpdateChargeback.action?chargebackId='+chargebackid,'12')
	}
	function shanchu(chargebackid){
		if(confirm("确定要删除这笔交易吗?")){
			window.location="../PaySystem/deleteChargeBack.action?chargebackId="+chargebackid;
		}
	}
	function xiazaishensu(chargebackid){
		window.location="../PaySystem/downloadCom.action?chargebackId="+chargebackid;
	}
	function tianjiajufu(){
	   var flag=0;
	   var chck=document.getElementsByName("orders");
	   for(i=0;i<chck.length;i++){
	       if(chck[i].checked==true){
	           flag++;
	       }
	   }
	   if(flag>0){
	   		document.getElementById("formu").action="batchChargeBack";
			document.getElementById("formu").submit();
       }else{
           alert("请选择一个!");
       }
	}
</script>

<div align="center">
	<h3>拒付处理</h3>
</div>
<s:form name="formu" id="formu" action="chargebackTradeQuery" method="post" theme="simple">
<TABLE cellSpacing=0 cellPadding=0 align=center border=0>
				<tr class=TR_Title>
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
					<td>
						卡号
					</td>
					<td>
						<s:textfield name="cardNo" />
					</td>
				</tr>
				<tr>
		 		<td>开始日期</td> 		
		 		<td>
		 		   <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>
		 		</td>
		 		<td>结束日期</td>
		 		<td>
		 			<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>
		 		</td>
	 		</tr>
	 		<tr>
	 		<td>是否拒付</td> 		
	 		<td>
 			<s:select name="isProtest" list="#{'':'所有','1':'已拒付','0':'未拒付'}" listKey="key" listValue="value"/>

	 		</td>
	 		<td>批次号</td>
	 		<td>
 			<s:select name="batchno" list="batchnoList"  headerKey="" headerValue="----" />
	 		</td>
	 		</tr>
				<tr>
					<td colspan="5" align="center">
						<input type="submit" value="搜索"/>
					</td>
				</tr>	
			</TABLE>
<TABLE cellSpacing="0" cellPadding="0" align="center" border=1 width="100%">
	<tr bgcolor="#cccccc">
		<td><input type="checkbox" name="checkAll" onclick="checkSelect();" />序列</td>
		<td>流水号</td>
		<td>商户订单号</td>
		<td>金额</td>
		<td>RMB金额</td>
		<td>日期</td>
		<td>是否退款</td>
		<td>授权号</td>
		<td>金额(上传)</td>
		<td>日期(上传)</td>
		<td>卡号(上传)</td>
		<td>授权号(上传)</td>
		<td>是否拒付</td>
		<td>导入时间</td>
		<td>拒付理由</td>
		<td>档号</td>
		<td>操作</td>
	</tr>
	<s:iterator id="p" value="info.result" status="s">
	<tr 
	<s:if test="systemManagerService.getByCardNoAndAmount(#p[0].cardNoBy,#p[0].tradeAmountBy)">
		bgcolor="#62c65d"
	</s:if> >
		<td><s:if test="#p[1].tradeState.substring(2,3)!=1"><input type="checkbox" name="orders" value="<s:property value="#p[0].id" />" /></s:if><s:property value="#s.index+1" /></td>
		<td><s:property value="#p[1].orderNo" /></td>
		<td><s:property value="#p[1].merchantOrderNo" /></td>
		<td><s:property value="#p[1].tradeAmount"/></td>
		<td><s:property value="#p[1].rmbAmount"/></td>
		<td><s:property value="#p[1].tradeTime" /></td>
		<td><s:property value="states.getStateName(#p[1].tradeState,2)" escape="false"  /></td>
		<td><s:property value="#p[1].VIPAuthorizationNo" /></td>
		<td><s:property value="#p[0].tradeAmountBy" /></td>
		<td><s:property value="#p[0].tradeDateBy" /></td>
		<td><s:property value="#p[0].cardNoBy" /></td>
		<td><s:property value="#p[0].authorizationNoBy" /></td>
		<td><s:property value="states.getStateName(#p[1].tradeState,3)" escape="false" /></td>
		<td><s:property value="#p[0].importDate" /></td>
		<td><s:property value="#p[0].remark" /></td>
		<td><s:property value="#p[0].ref" /></td>
		<td>
			<s:if test="#p[1].tradeState.substring(2,3)!=1">
				<a href="#" onclick="updateChargeback(<s:property value="#p[0].id" />)">拒付</a>&nbsp;|&nbsp;
				<a href="#" onclick="shanchu(<s:property value="#p[0].id" />)">删除</a>&nbsp;|&nbsp;
			</s:if>
			<s:if test="#p[0].complaintsFileName!=null">
				<a href="#" onclick="xiazaishensu(<s:property value="#p[0].id" />)">下载申诉</a>
			</s:if>
			<s:if test="systemManagerService.getByCardNoAndAmount(#p[0].cardNoBy,#p[0].tradeAmountBy)">
			<a href="#" onclick="shanchu(<s:property value="#p[0].id" />)">删除</a>&nbsp;|&nbsp;
			</s:if>
		</td>
	</tr>
	</s:iterator>
</table>
<div align="center">
<pages:pages value="info" beanName="info" formName="forms(0)" />
</div>

<p align="center">
	<input type="button" name="s" value="拒付" onclick="tianjiajufu()" />
</p> <!-- 下面这段script代码必须放在form体的最后  
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











