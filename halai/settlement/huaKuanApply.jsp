<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <!-- 全选组件 -->
    <%@ include file="../include/checkAll.jsp" %>
    <title>代商户提交申请</title>
    
	<link rel="stylesheet" type="text/css" href="css/style.css">
    <style>
     td,tr,table{ font-size:12px;}
    </style>
	<SCRIPT language=JavaScript>
	
	<!-- 判断开始是否大于结束时间 -->
    function checkTime(){
    	var time1= dojo.widget.byId("startTime");
    	var startTime = time1.getValue();
    	var time2= dojo.widget.byId("endTime");
    	var endTime = time2.getValue();
   		if(startTime>endTime){
   			alert("开始时间大于结束时间!");
   		}else{
   			form1.submit();
   		}
    }
    
   	<!-- 检验是否选上需要处理的选项 -->
	function check(){
		var workorderObjectNos = document.getElementsByName('freezeId');
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
			alert("请选上要提交的交易！");
		}else{
	form2.submit();

		}
	}
	<!--审核不通过-->
	function cancel(){
		var workorderObjectNos = document.getElementsByName('freezeId');
		var gets = new Array();
		var k = 0;
		var result = 0;
		for(var i=0; i<workorderObjectNos.length; i++){
			if(workorderObjectNos[i].checked){
			  // alert("第" + i + "个值：" + workorderObjectNos[i].value);
			    gets[k] = workorderObjectNos[i].value;
			    result =  gets[k];
   				k++;
   				if(result==0){
				alert("请选上勾兑的选项！");
				}else{
					//alert(workorderObjectNos);
				 	window.location.href="auditingErrorAction.action?id="+result+"";
				}
			}
 		}
 		
	}
	</SCRIPT>
  </head>
  <body>
  	<H3 align=center>代商户提交申请</H3>

  	<s:form id="form1" action="toSettlement.action" theme="simple" method="post">
  		<center><font color=red>  <s:fielderror></s:fielderror> </font></center>
  		<table align="center">
  			<tr>
  				<td>商户号</td>
  				<td>
  					<input type="text" value="<s:property value='tradeinfo.merno'/>"  name="tradeinfo.merno" />
  				</td>
  				<td>流水订单号</td>
  				<td>
  					<input type="text"   name="orderdno" />
  				</td>
  			</tr>
  			</table>
  	   	<table align="center">
  	   		<tr>
  				<td>
  					<s:submit value="确定"/>
  				</td>
  				<td>
	   				<s:reset value="取消"/>
	   			</td>			
  			</tr>
  	   	</table>
  	</s:form>
    <s:form name="form2" action="toSettlement" method="post">
               <input type="hidden" value="<s:property value='tradeinfo.merno'/>" name="tradeinfo.merno" />
    	<center>   &nbsp; &nbsp; &nbsp; &nbsp;</font></center>
    	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="100%" align=center 
			bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
	   		<tr bgColor=#cccccc align=center>
	   			<td>
  					<input type="checkbox" onclick='chkall("form2",this)' name=chk>
  				</td>
    			<th>商户号</th>
    			<th>流水订单号</th>
    			<th>订单号</th> 			
    			<th>交易金额</th>

    			<th>交易时间</th>
    			
    			<th>状态</th>
    			<th>是否退款</th>
    			<th>是否冻结</th>
    			<th>是否拒付</th>
    			<th>是否勾兑</th>
    			<th>是否上传跟踪号</th>  		
    			<th>备注</th>
    		</tr>
	    		<s:iterator id="gouDuiList" value="tradeList">
		    		<tr align=center>
		    			<td>
	  						<input type="checkbox" name="freezeId" value="<s:property value="#gouDuiList[0].id"/>">
	  					</td>
		    			<td>
		    				<s:property value="#gouDuiList[1].merno"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].orderNo"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].merchantOrderNo"/>&nbsp;
		    			</td>		    	
		    			<td>
		    				<s:property value="#gouDuiList[0].tradeAmount"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].tradeTime"/>&nbsp;
		    			</td>
		    	
		    			<td>
		    				<s:property value="#gouDuiList[0].isresultName"/>&nbsp;
		    				<s:if test="#gouDuiList[0].tradeState.substring(0,1)==0">
		    					失败
		    				</s:if>
		    				<s:elseif test="#gouDuiList[0].tradeState.substring(0,1)==1">
		    					成功
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList[0].tradeState.substring(0,1)==2">
		    					待处理
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList[0].tradeState.substring(0,1)==3">
		    					取消
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList[0].tradeState.substring(0,1)==4">
		    					待确认
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList[0].tradeState.substring(0,1)==5">
		    					未返回
		    				</s:elseif>
		    			</td>
		    			<td>
		    				<s:if test="#gouDuiList[0].tradeState.substring(1,2)==0">
		    					未退款
		    				</s:if>
		    				<s:elseif test="#gouDuiList[0].tradeState.substring(1,2)==1">
		    					已退款
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList[0].tradeState.substring(1,2)>1">
		    					<s:property value="#gouDuiList[0].tradeState.substring(1,2)"/>
		    				</s:elseif>&nbsp;
		    			</td>
		    			<td>
		    				<s:if test="#gouDuiList[0].tradeState.substring(3,4)==0">
		    					未冻结
		    				</s:if>
		    				<s:elseif test="#gouDuiList[0].tradeState.substring(3,4)==1">
		    					已冻结
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList[0].tradeState.substring(3,4)==2">
		    					解冻
		    				</s:elseif>&nbsp;
		    			</td>
		    			<td>
		    				<s:if test="#gouDuiList[0].tradeState.substring(2,3)==0">
		    					未拒付
		    				</s:if>
		    				<s:elseif test="#gouDuiList[0].tradeState.substring(2,3)==1">
		    					拒付
		    				</s:elseif>
		    				&nbsp;		    			
		    			</td>		   
		    			<td>
		    				<s:if test="#gouDuiList[0].tradeState.substring(4,5)==0">
		    					未勾兑
		    				</s:if>
		    				<s:elseif test="#gouDuiList[0].tradeState.substring(4,5)==1">
		    					已勾兑
		    				</s:elseif>
		    				&nbsp;
		    			</td>
		    			<td>
		    				<s:if test="#gouDuiList[0].isTrackNo==null">
		    					未上传
		    				</s:if>
		    				<s:elseif test="#gouDuiList[0].isTrackNo!=null">
		    					<s:property value="#gouDuiList[0].isTrackNo"/>
		    				</s:elseif>
		    				&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].remark"/>&nbsp;
		    			</td>
		    		</tr>
	    		</s:iterator>
    	
    	</table>
    	<table align="center">
    		<tr>
   				<td>&nbsp;
   					
   				</td>
   				<td>&nbsp;
   					
   				</td>
   				<td>&nbsp;
   					
   				</td>
   			</tr>
    	</table>
    	<table align="center">
    		<tr>
    			<td>
    				<input type="button" value="确定" onClick="check()"/>
    			</td>
    			<td>&nbsp;
    				
    			</td>
    			<td>
    				<input type="reset" value="取消"/>
    			</td>
    		</tr>
    	</table>
    </s:form>
 	<DIV style="FONT-SIZE: 12px" align=center>
   <!--		<a href="jsp/manager/huakuan/huaKuanMain.jsp">返回</a> -->
    </DIV>
    
		
  </body>
</html>
