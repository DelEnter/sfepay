<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
 <%@ include file="../include/dialog.jsp"%>

<script language="JavaScript" src="../js/util.js"></script>

<html>
  <head>
   
    <!-- 全选组件 -->
    <%@ include file="../include/checkAll.jsp" %>
    <title>Settlement Application</title>
    
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
			alert("Please select the transaction to submit!");
		}else{
	form2.submit();

		}
	}

	</SCRIPT>
  </head>
  <body>
<!--头部begin-->
<s:action name="indexMenuEn" executeResult="true"/>  
  	<!--<H3 align=center>提交划款申请</H3> -->

    <s:form name="form2" action="applyMerSettlementEn" method="post">
              
  <!--  	<center>   &nbsp; &nbsp; &nbsp; &nbsp; 总金额: <font color=red><s:property value="tradeMoney"/> </font></center>
    	<table borderColor=#ffffff cellSpacing=0 cellPadding=0 width="1200" align=center 
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
		    				<s:property value="#gouDuiList[1].merno"/>&nbsp;33
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].orderNo"/>&nbsp;33
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].merchantOrderNo"/>&nbsp;33
		    			</td>		    	
		    			<td>
		    				<s:property value="#gouDuiList[0].tradeAmount"/>&nbsp;33
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList[0].tradeTime"/>&nbsp;333s
		    			</td>
		    	
		    			<td>
                             <s:property value="states.getStateNameEn(#gouDuiList[0].tradeState,1)"  escape="false"/>		    			
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
		    				</s:elseif>333&nbsp;
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
		    				</s:elseif>&nbsp;333
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
		    					已上传
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
    	</table> -->
    
 	<!--<DIV style="FONT-SIZE: 12px" align=center>
  		<a href="jsp/manager/huakuan/huaKuanMain.jsp">返回</a>
   </DIV> -->
    
    
    <div class="mainbody" style="position:absolute; left:310px">
	   <div class="list">
	   
	      <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">Settlement Application</li>
             <li class="lilistother"></font></center></li>
            
           </ul>
          <ul class="bottom">       
               <li class="li_03"><input type="checkbox" onclick='chkall("form2",this)' name=chk></li> 
		       <li class="li_04">Amount</li> 
		       <li class="li_04">Status</li>   
		       <li class="li_04">Refund</li>   
		       <li class="li_03">Freeze</li>    
               <li class="li_04">Chargeback</li>    
               <li class="li_04">Checkornot</li>    
               <li class="li_04">Tracking No</li>    
             </ul>
         </div>
		 
		  <div class="listlist">
		     <s:iterator id="gouDuiList" value="tradeList">
                <ul class="listlisttop">
            		 <li>Order No.:<s:property value="#gouDuiList[0].orderNo"/></li>
            		 <li>Merchant Order No.:<s:property value="#gouDuiList[0].merchantOrderNo"/></li>
             		 <li>Date：<s:property value="#gouDuiList[0].tradeTime"/></li>
           		</ul>
           		<ul class="listlistbottom">
             		 <li class="lil_03"><input type="checkbox" name="freezeId" value="<s:property value="#gouDuiList[0].id"/>"></li>  
             		 <li class="lil_04"><s:property value="#gouDuiList[0].tradeAmount"/></li>    
            		 <li class="lil_04">
                             <s:property value="states.getStateNameEn(#gouDuiList[0].tradeState,1)"  escape="false"/>		
                     </li>   
            		 <li class="lil_04">
            		 	<s:if test="#gouDuiList[0].tradeState.substring(1,2)==2">
            		 	 <font color="red"><s:property value="#gouDuiList[0].backCount"/></font>
            		 	 </s:if>
            		 	 <s:else>
                             <s:property value="states.getStateNameEn(#gouDuiList[0].tradeState,2)"  escape="false"/>		
                     	</s:else>
                     </li>   
             		 <li class="lil_03">
                             <s:property value="states.getStateNameEn(#gouDuiList[0].tradeState,4)"  escape="false"/>		
                     </li>    
            		 <li class="lil_04">
                              <s:property value="states.getStateNameEn(#gouDuiList[0].tradeState,3)"  escape="false"/>		
                     </li>    
            		 <li class="lil_04">
                             <s:property value="states.getStateNameEn(#gouDuiList[0].tradeState,5)"  escape="false"/>		
                     </li>    
           		     <li class="lil_05">
                        <s:if test="#gouDuiList[0].isTrackNo==null">
		    					YES
		    				</s:if>
		    				<s:elseif test="#gouDuiList[0].isTrackNo!=null">
		    					<s:property value="#gouDuiList[0].isTrackNo"/>
		    				</s:elseif>	</li>    
          		 </ul>
		     </s:iterator>
			  <br class="clear" />
		     <ul class="listlistpage">
		        <li><input type="button" value="Confirm&nbsp;" class="input_button_01"" onClick="check()"/>&nbsp;&nbsp;<input type="reset" class="input_button_01" value="Cancel&nbsp;" /></li>
		      </ul>
		   </div>
     </div>
   </div>
    </s:form>
    
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->		
  </body>
</html>
