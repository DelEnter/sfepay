<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
   
    <!-- 全选组件 -->
    <%@ include file="../include/checkAll.jsp" %>
  
    <title>外卡划款处理</title>
    
	<link rel="stylesheet" type="text/css" href="css/bail.css">
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
			alert("请选上勾兑的选项！");
		}else{
			document.getElementById('gosettlement').value=gets;
			form3.submit();
//		var str='viewTransferAcount.action?freezdIds='+gets;
//			 window.showModalDialog (str, window,'dialogHeight:820px;dialogWidth:1000px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
//	//		form2.submit();
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
	//		  alert("第" + i + "个值：" + workorderObjectNos[i].value);
			    gets[k] = workorderObjectNos[i].value;
			    result =  gets[k];
   				k++;
   				if(result==0){
				alert("请选上勾兑的选项！");
				}else{
				     form2.submit();
					//alert(workorderObjectNos);
				 	//window.location.href="auditingErrorAction.action?id="+result+"";
				}
			}
 		}
 		
	}
	
	

	function checkEvent(name,allCheckId)
	{
	  var allCk=document.getElementById(allCheckId);
	  if(allCk.checked==true)
	  checkAll(name);
	  else
	  checkAllNo(name);
	  
	}

	//全选
	function checkAll(name)
	{
	  var names=document.getElementsByName(name);
	  var len=names.length;
	  
	  var selectedlength=document.getElementById('selectedlength').value;
	   
	    if(selectedlength!=''){
	    	
	    	len = selectedlength;
	    }
	    
	  if(len>0)
	  {
	   var i=0;
	   for(i=0;i<len;i++)
	   names[i].checked=true;
	    
	  }
	}

	//全不选
	function checkAllNo(name)
	{
	  var names=document.getElementsByName(name);
	 var len=names.length;
	 if(len>0)
	  {
	    var i=0;
	    for(i=0;i<len;i++)
	    names[i].checked=false;
	  }
	}

	
	</SCRIPT>
  </head>
  <body>
	<s:form id="form3" action="viewTransferAcount.action" theme="simple" method="post">
	<input type="hidden" id="gosettlement"  name="freezdIds"/>
	</s:form>
  
  
  	<H3 align=center>外卡划款处理</H3>

  	<s:form id="form1" action="viewTransferMoney.action" theme="simple" method="post">
  		<table align="center">
  			<tr>
  				<td>
  					商户号<s:select name="tradeinfo.merno" list="merchantNoList" />
  					
  				<!--	<input type="text" name="tradeinfo.merno" size="6" value="<s:property value='tradeinfo.merno'/>"/>-->
  				</td>
  				<td>
  					流水号<input type="text" name="orderdno" /> 
  				</td>  				
  				<td>
  					<input type="submit" value="交易查询"/>	
  				</td>
  			</tr>
  			<tr>
  			<span style="color:red"><s:property value='showMessage'/></span> 
  			</tr>
  		</table>
  	</s:form>
  	
    <s:form name="form2" action="viewTransferMoney" method="post" target='_self'>  
    	<input type="hidden" name="tradeinfo.merno" value="<s:property value='tradeinfo.merno'/>">
	  	<TABLE width="1240">
		    <TBODY>
		   		<TR>
			      	<TD>
			    	</TD>
			    </TR>
		    </TBODY>
	    </TABLE>
    	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="1240" align=center 
			bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
	   		<tr bgColor=#cccccc align=center>
	   			<td>  <input style= "overflow-x:visible;width:40; " type="text" title="输入需要选择的条数" name="selectedlength" id="selectedlength"/> 
	   				<input type="hidden" name="merchantNo" value="<s:property value="merchNo"/>"/>
	   				<s:if test="tradeinfo.merno!=null">
  						<input type="checkbox" onclick='checkEvent("freezeId","chk")' id="chk" name=chk>
  					</s:if>
  				</td>
    			<th>序列</th>
    			<th>商户号</th>
    			<td>流水订单号</th>
    			<th>交易金额</th>
    			<th>交易时间</th>
    			<th>审核日期</th>
    			<th>状态</th>
    			<th>是否退款</th>
    			<th>是否冻结</th>
    			<th>是否拒付</th>
    			<th>是否勾兑</th>
    			<th>是否上传跟踪号</th>  		
    			<th>备注</th>
    		</tr>
    		<s:set name="totalAmount" value="0"></s:set>
	    		<s:iterator id="gouDuiList" value="tradeList" status="i">
	    			
		    		<tr align=center>
		    			<td>
		    				<s:if test="tradeinfo.merno!=null">
		  						<input type="checkbox" name="freezeId" value="<s:property value="#gouDuiList[8]"/>">
		  						<input type="hidden" name="merchno" value="<s:property value="#gouDuiList.merchantno"/>">
	  						</s:if>
	  					</td>
	  					<td>
	  						<s:property value="#i.index+1"/>
	  					</td>
		    			<td>
		    				<s:property value="tradeinfo.merno"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0]"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[1]"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[2]"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[3]"/>&nbsp;
		    			</td>
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList[4],1)"  escape='false'  />&nbsp;
		    			</td>
		    			<td>
		    				<s:if test="#gouDuiList[4].substring(1,2)==2">
		    					<font color="red"><s:property value="#gouDuiList[5]"/></font>
		    				</s:if>
		    				<s:else>
		    				
		    				<s:property value="states.getStateName(#gouDuiList[4],2)"  escape='false'  />
		    				</s:else>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="states.getStateName(#gouDuiList[4],4)"  escape='false'  />&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="states.getStateName(#gouDuiList[4],3)"  escape='false'  />&nbsp;
		    			</td>		   
		    			<td>
		    				<s:property value="states.getStateName(#gouDuiList[4],5)"  escape='false'  />&nbsp;
		    			</td>

		    			<td>
		    				<s:if test="#gouDuiList[6]==null">
		    					未上传
		    				</s:if>
		    				<s:elseif test="#gouDuiList[6]!=null">
		    					<s:property value="#gouDuiList[6]"/>
		    				</s:elseif>
		    				&nbsp;
		    			</td>	
		    			<td>
		    				<s:property value="#gouDuiList[7]"/>&nbsp;
		    			</td>		    				    			
		    		</tr>
		    		<s:set name="totalAmount" value="#totalAmount+#gouDuiList[1]"></s:set>
	    		</s:iterator>
	    	
	    		 <tr align=center>
	    		 <td colspan="4">&nbsp;</td>
	    			<td><FONT color="red">小计：</FONT><s:property value="#totalAmount"/></td>
	    		</tr> 
    	</table>
    		<table align="center">
    		<tr>
    			<td>
    				<input type="button" value="预览划款表" onclick="check()"/>
    			</td>
    			<td>
    				<input type="button" value="审核有误" onclick="cancel()"/>
    			</td>
    			<td>
    				<input type="reset" value="取消"/>
    			</td>
    		</tr>
    	</table>

    </s:form>
    	<table align="center">
    		<tr>
   				<td>
   				<H3 align=center>异常单列表</h3>	
   				</td>
   				<td>
   					&nbsp;
   				</td>
   				<td>
   					&nbsp;
   				</td>
   			</tr>
    	</table>    
    	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="1200" align=center 
			bgColor=#ffffff borderColorLight=#000000 border=1 height="10">
	   		<tr bgColor=#cccccc align=center>
                <th>序列</th>
    			<th>异常类型</th>                
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
    			<th>是否划款</th>    			
    			<th>是否上传跟踪号</th>  		
    			<th>备注</th>
    		</tr>
	    		<s:iterator id="gouDuiList3" value="tradeList3" status="k2">
		    		<tr align=center>
		    			<td>
	  						<s:property value="#k2.index+1"/>
	  					</td>
		    			<td>
		    				<s:if test="#gouDuiList3.tradeState.substring(2,3)==1">
		    					未划款拒付
		    				</s:if>
		    				<s:elseif test="#gouDuiList3.tradeState.substring(1,2)==1">
		    					未划款全额退款
		    				</s:elseif>  						    						    						    						    						    				
		    			</td>	  					
		    			<td>
		    				<s:property value="tradeinfo.merno"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList3.orderNo"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList3.merchantOrderNo"/>&nbsp;
		    			</td>		    	
		    			<td>
		    				<s:property value="#gouDuiList3.tradeAmount"/>&nbsp;
		    			</td>

		    			<td>
		    				<s:property value="#gouDuiList3.tradeTime"/>&nbsp;
		    			</td>
		    	
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList3.tradeState,1)"  escape='false'  />&nbsp;
		    			</td>
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList3.tradeState,2)"  escape='false'  />&nbsp;
		    			</td>
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList3.tradeState,4)"  escape='false'  />&nbsp;
		    			</td>
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList3.tradeState,3)"  escape='false'  />&nbsp;
		    			</td>		   
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList3.tradeState,5)"  escape='false'  />&nbsp;
		    			</td>		    		
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList3.tradeState,8)"  escape='false'  />&nbsp;
		    			</td>
		    			<td>
		    				<s:if test="#gouDuiList2[5]==null">
		    					未上传
		    				</s:if>
		    				<s:elseif test="#gouDuiList2[5]!=null">
		    					<s:property value="#gouDuiList[5]"/>
		    				</s:elseif>
		    				&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList2[6]"/>&nbsp;
		    			</td>
		    		</tr>
		    
	    		</s:iterator>    		
	    		<s:iterator id="gouDuiList2" value="tradeList2" status="k3">
		    		<tr align=center>
		    			<td>
	  						<s:property value="tradeList3.size()+#k3.index+1"/>
	  					</td>
		    			<td>
		    				<s:if test="#gouDuiList2[7]==3">
		    					已划款拒付
		    				</s:if>
		    				<s:elseif test="#gouDuiList2[7]==4">
		    					已划款部分退款
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList2[7]==5">
		    					已划款全部退款
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList2[7]==6">
		    					已划款冻结
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList2[7]==7">
		    					已划款解冻
		    				</s:elseif>	    						    						    						    						    						    				
		    			</td>	  					
		    			<td>
		    				<s:property value="tradeinfo.merno"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList2[1]"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList2[0]"/>&nbsp;
		    			</td>		    	
		    			<td>
		    				<s:property value="#gouDuiList2[2]"/>&nbsp;
		    			</td>

		    			<td>
		    				<s:property value="#gouDuiList2[3]"/>&nbsp;
		    			</td>
		    	
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList2[4],1)"  escape='false'  />&nbsp;
		    			</td>
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList2[4],2)"  escape='false'  />&nbsp;
		    			</td>
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList2[4],4)"  escape='false'  />&nbsp;
		    			</td>
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList2[4],3)"  escape='false'  />&nbsp;
		    			</td>		   
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList2[4],5)"  escape='false'  />&nbsp;
		    			</td>		    		
		    			<td>
		    					<s:property value="states.getStateName(#gouDuiList2[4],8)"  escape='false'  />&nbsp;
		    			</td>
		    			<td>
		    				<s:if test="#gouDuiList2[5]==null">
		    					未上传
		    				</s:if>
		    				<s:elseif test="#gouDuiList2[5]!=null">
		    					<s:property value="#gouDuiList[5]"/>
		    				</s:elseif>
		    				&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList2[6]"/>&nbsp;
		    			</td>
		    		</tr>
	    		</s:iterator>
    	
    	</table>
    	<table align="center">
    		<tr>
   				<td>
   					&nbsp;
   				</td>
   				<td>
   					&nbsp;
   				</td>
   				<td>
   					&nbsp;
   				</td>
   			</tr>
    	</table>
  </body>
</html>
