<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <!-- 全选组件 -->
    <%@ include file="../include/checkAll.jsp" %>

    <link rel="stylesheet" type="text/css" href="css/bail.css">
       <LINK href="../css/head.css" type=text/css rel=stylesheet>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <title>外卡审单</title>
	<link rel="stylesheet" type="text/css" href="css/mercss.css"/>

	<style type="text/css">
	
		#massage_box{ position:absolute; left:expression((body.clientWidth-350)/2); top:expression((body.clientHeight-200)/2); width:400px;
		height:200px;filter:dropshadow(color=#666666,offx=3,offy=3,positive=2); z-index:2; visibility:hidden}
		#mask{ position:absolute; top:0; left:0; width:expression(body.scrollWidth); height:expression(body.scrollHeight); filter:ALPHA(opacity=60); z-index:1; visibility:hidden}
		.massage{border:#036 solid; border-width:1 1 3 1; width:95%; height:95%; background:#fff; color:#036; font-size:12px; line-height:150%}
		.header{background:#036; height:1%; width:780px; font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12px; padding:3 5 0 5; color:#fff}
		#src{display:inline; width:350px; position:absolute}
	</style>
	<!--实现层移动-->
	<script language="javascript">
		function detialinfo(tradeid){
			window.showModalDialog('toUploadPicture.action?trade.id='+tradeid, window,'dialogHeight:325px;dialogWidth:650px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
		}
		
		var ispicture;
		function refreshimg(ispicture){
	   		document.all.picture.src='../picture.do?ispicture='+ispicture;
	   	}
	
		var Obj=''
		document.onmouseup=MUp
		document.onmousemove=MMove
		
		function MDown(Object){
		Obj=Object.id
		document.all(Obj).setCapture()
		pX=event.x-document.all(Obj).style.pixelLeft;
		pY=event.y-document.all(Obj).style.pixelTop;
		}
		
		function MMove(){
		if(Obj!=''){
		document.all(Obj).style.left=event.x-pX;
		document.all(Obj).style.top=event.y-pY;
		}
		}
		
		function MUp(){
		if(Obj!=''){
		document.all(Obj).releaseCapture();
		Obj='';
		}
		}
		function copyToClipboard(theField,isalert)
		{
		  var obj=document.getElementById(theField);
		  if(obj!=null)
		  {
		    var clipBoardContent=obj.value;
		    obj.select();
		    window.clipboardData.setData("Text",clipBoardContent); 
		    if(isalert!=false)
		      alert("复制成功");
		  }
		  else
		  {
		     alert("Error!");
		  }
		}	
	
	
	</script>


<SCRIPT language=JavaScript>

   	<!-- 检验是否选上需要处理的选项 -->
	function check(ty){
	    if(ty==2){
	    var tem=document.getElementById('remark').value;
	    if(tem==1){
	    alert('请选择问题单类型');
	    return;}
	    }
		var workorderObjectNos = document.getElementsByName('id');
		var gets = new Array();
		var k = 0;
		var result = 0;
		for(var i=0; i<workorderObjectNos.length; i++){
			if(workorderObjectNos[i].checked){
			  // alert("第" + i + "个值：" + workorderObjectNos[i].value);
			    gets[k] = workorderObjectNos[i].value;
			    result =  gets[k];
   				k++;
			}
 		}
 		if(result==0){
			alert("请选上审核的选项！");
		}else{
            document.getElementById("typesname").value = ty;
			form2.submit();
		}
	}	
	</script>
  </head>
  
  <body>
  	<H3 align=center>外卡审单</H3>

    <s:form name="form1" id="form1" action="toauditTrade" theme="simple" method="post" >
  		<table align="center">
  			<tr>
  				<td>商户号</td>
  				<td>
  					<input type="text" value="<s:property value='tradeinfo.merno'/>"  name="tradeinfo.merno" />
  				</td>
  				<td>交易流水号</td>
  				<td>
  					<input type="text"  name="orderdno" />
  				</td>  	
  				<td>快递</td>			
  				<td>
  					<s:select name="kuaidi" list="#{'':'','EMS':'EMS','DHL':'DHL','UPS':'UPS','TNT':'TNT','Fed':'FedEx','DMS':'DMS','NNN':'其他'}" listKey="key" listValue="value"></s:select>
  				</td>  				
  			</tr>
  			<tr>
  				<td colspan="4" align="center">	
  					<s:submit value="查询"/>
  					<s:reset value="取消"/>
  				</td>
  			</tr>
  		</table>
     </s:form> 
 <s:form name="form2" id="form2" action="toauditTrade" theme="simple" method="post" >
        <input type="hidden" id="typesname"  name="typesname" />
        <input type="hidden" value="<s:property value='tradeinfo.merno'/>" name="tradeinfo.merno" />
    	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="100%" align=center 
			bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
	   		<tr bgColor=#cccccc align=center>
	   			<th>
  					<input type="checkbox" onclick='chkall("form2",this)' name=chk>
  				</th>
    			<th>商户号</th>
    			<th>流水订单号</th>
    			<th>支付情况</th>
    			<th>交易金额</th>
    			<th>交易时间</th>
    			<th>是否退款</th>
    			<th>国家</th>
    			<th>姓名</th>
    			<th>是否勾兑</th>
    			<th>是否上传跟踪号</th>  		
    			<th>是否上传图片</th>
    			<th>备注</th>
    		</tr>
    		<s:set name="trackingno" value="" />
	    		<s:iterator id="gouDuiList" value="info.result" status="s">
	    		     
		    		<tr align=center>
		    			<td>
	  						<input type="checkbox" name="id" value="<s:property value="#gouDuiList[0].id"/>">
	  					</td>
		    			<td>
		    				<s:property value="#gouDuiList[2].merno"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].orderNo"/>&nbsp;
		    			</td>
		    			<td>
		    				成功&nbsp;
		    			</td>
						<td onMouseOver="this.style.backgroundColor=''" 
							style="FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif" 
							onmouseout="this.style.backgroundColor=''" align=right><A 
							title="&#13;&#10;电子邮件： <s:property value="#gouDuiList[1].email"/>   &#13;&#10;联系电话： <s:property value="#gouDuiList[1].phone"/>&#13;&#10;姓名：  <s:property value="#gouDuiList[1].firstName"/> &#13;&#10;国家：    <s:property value="#gouDuiList[1].country"/>&#13;&#10;地址：  <s:property value="#gouDuiList[1].address"/>&#13;&#10;城市： <s:property value="#gouDuiList[1].city"/>&#13;&#10;所在州： <s:property value="#gouDuiList[1].state"/>&#13;&#10;发卡行：  <s:property value="#gouDuiList[1].emitcardbank"/> &#13;&#10;邮政编码：  <s:property value="#gouDuiList[1].zipcode"/>   &#13;&#10;收货人名字：  <s:property value="#gouDuiList[1].shippingFullName"/>   &#13;&#10;收货人地址：  <s:property value="#gouDuiList[1].shippingAddress"/>   &#13;&#10;收货人城市：  <s:property value="#gouDuiList[1].shippingCity"/>   &#13;&#10;收货人州：  <s:property value="#gouDuiList[1].shippingState"/>   &#13;&#10;收货人编码：  <s:property value="#gouDuiList[1].shippingZip"/>   &#13;&#10;收货人国家：  <s:property value="#gouDuiList[1].shippingCountry"/>  &#13;&#10;风险评分：   <s:property value="#gouDuiList[1].riskvalue"/>&#13;&#10;IP地址：  <s:property value="#gouDuiList[1].ip"/>" 
							href="#"><s:property value="#gouDuiList[0].tradeAmount"/>&nbsp; </A>
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].tradeTime"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="states.getStateName(#gouDuiList[0].tradeState,2)"  escape='false'/>&nbsp;
		    			</td>
		    			<td>
		    				 <s:property value="#gouDuiList[1].country"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[1].firstName"/>&nbsp;
		    			</td>		   
		    			<td>
		    				<s:property value="states.getStateName(#gouDuiList[0].tradeState,5)"  escape='false' />&nbsp;
		    			</td>
		    			<td>
		    			    	<s:property value="#gouDuiList[0].isTrackNo"/>&nbsp;
		    			    	<s:if test="#s.index!=0">
		    			    		<s:set name="trackingno" value="#trackingno+#gouDuiList[0].isTrackNo.substring(3)+'\n'"/>
		    			    	</s:if>
		    			    	<s:else>
		    			    		<s:set name="trackingno" value="#gouDuiList[0].isTrackNo.substring(3)+'\n'"/>
		    			    	</s:else>
		    			</td>
		    			<td>
		    			  <span onClick="mask.style.visibility='visible';massage_box.style.visibility='visible'" style="cursor:hand">
			    				<a href="#" onClick="refreshimg('<s:property value='#gouDuiList[0].isPicture'/>')">
			    				<img Border="0" src="../merchant/images/more_icon.gif" title="查看" alt="查看" /></a>
			    		  </span>	  
		    			</td>
		    			<td width=12%>
		    				<s:property value="#gouDuiList[0].remark"/>&nbsp;
		    			</td>
		    					
		    		</tr>
	    		</s:iterator>
    	</table>
        <pages:pages value="info" beanName="info" formName="getElementById('form2')" />
    	<table width="100%" align="center">
	  <tr>
	    <td width="24%"><input type="button" value="审单通过" onClick="check('1')"/> </td>
	    <td width="69%">&nbsp;</td>
	    <td width="3%">&nbsp;</td>
	    <td width="4%">&nbsp;</td>
	    </tr>
	  <tr>
	    <td>&nbsp;跟踪单号</td>
	    <td>
    				列为问题单  </td>
	    <td>&nbsp;</td>
	    <td>&nbsp;</td>
	    </tr>
	  <tr>
		<td valign="top">
		  <textarea id="links" rows="11" cols="20" /><s:property value='#trackingno'/></textarea>
		    <input type="button" value="复制跟踪单号" onClick="javascript:copyToClipboard('links',true);"/> </td>
		<td valign="top">
			<select id="remark" name="remark" value="请选择"  >
				 	<option value="">请选择
                 	<option value="搜索的信息有误">搜索的信息有误
                 	<option value="本邮件号输入有误或有其他情况">本邮件号输入有误或有其他情况
                 	<option value="海关放行">海关放行
                 	<option value="被退回">被退回
                 	<option value="到达处理中心">到达处理中心
                 	<option value="查不到">查不到
                 	<option value="未妥投">未妥投
                 	<option value="到达投递局">到达投递局
                 	<option value="离开处理中心">离开处理中心
                 	<option value="送交海关">送交海关
                 	<option value="签收人和地址都不一样">签收人和地址都不一样
                    <option value="大于600美金3个月后结算">大于600美金3个月后结算
                    <option value="快件等待清关">快件等待清关     	
                    <option value="查不到物流">查不到物流     	
            		</select>
		  		<input type="button" value="列为问题单" onClick="check('2')"/></td>
		<td>&nbsp;</td>
	</tr>
 	</table>
    	<div id="massage_box">
		<div class="massage">
			<div class="header" onmousedown=MDown(massage_box)>
				<div style="display:inline; position:absolute">图片信息</div>
					<span onClick="massage_box.style.visibility='hidden'; mask.style.visibility='hidden'" style="float:right; display:inline; cursor:hand">×</span>
				</div>
				<IMG height=490 id="picture" width=780 border=0 />
			</div>
		</div>
		<div id="mask"></div>
  </s:form>
 	<DIV style="FONT-SIZE: 12px" align="center">
		<form  method="post"  action="toauditTrade.action" name="pageform" id="pageform">

			
			<input type="hidden" name="merchantno" value="<s:property value="merchantno"/>"/>
		</form>

    </DIV>
 	
  </body>
</html>
