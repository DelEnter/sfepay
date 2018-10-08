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
    <title>预授权待处理</title>
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
	    function chuli(str){
	    alert(str);
	    document.getElementById('typesname').value='1';
	     document.getElementById('tradeId').value=str;
	    
	    form2.submit();
	    
	    }
	    
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
  	<H3 align=center>预授权处理</H3>

    <s:form name="form1" id="form1" action="toListPre" theme="simple" method="post" >
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
 <s:form name="form2" id="form2" action="toListPre" theme="simple" method="post" >
        <input type="hidden" id="typesname"  name="typesname" />
             <input type="hidden" id="tradeId"  name="tradeId" />
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
    			<th>完成金额</th>
    			<th>交易时间</th>
    			<th>是否勾兑</th>
    			<th>是否上传跟踪号</th>  		
    			<th>是否上传图片</th>
    			<th>备注</th>
    			<th>处理</th>
    		</tr>
    		<s:set name="trackingno" value="" />
	    		<s:iterator id="gouDuiList" value="info.result" status="s">
	    		     
		    		<tr align=center>
		    			<td>
	  						<input type="checkbox" name="id" value="<s:property value="#gouDuiList[0].id"/>">
	  					</td>
		    			<td>
		    				<s:property value="#gouDuiList[1].merno"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].orderNo"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="states.getStateName(#gouDuiList[0].tradeState,1)"  escape='false' />&nbsp;
		    			</td>
						<td>
						<s:property value="#gouDuiList[0].tradeAmount"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].pre_money"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].tradeTime"/>&nbsp;
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
		    			<td width=12%>
		    			  <button value="处理交易" onclick='chuli(<s:property value='#gouDuiList[0].id'/>)' />
		    				<a href="#" onclick='chuli(<s:property value='#gouDuiList[0].id'/>)' >处理交易</a> &nbsp;
		    			</td>		    					
		    		</tr>
	    		</s:iterator>
    	</table>
        <pages:pages value="info" beanName="info" formName="getElementById('form2')" />
    	<table width="100%" align="center">

 	</table>

  </s:form>
 	<DIV style="FONT-SIZE: 12px" align="center">
		<form  method="post"  action="toauditTrade.action" name="pageform" id="pageform">

			
			<input type="hidden" name="merchantno" value="<s:property value="merchantno"/>"/>
		</form>

    </DIV>
 	
  </body>
</html>
