<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>

<html>
  <head>
   
    <!-- 全选组件 -->
    <%@ include file="../include/checkAll.jsp" %>
    <title>Settlement Application History</title>
    
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

	</SCRIPT>
  </head>
  <body>
 <!--头部begin-->
<s:action name="indexMenuEn" executeResult="true" />   
  	<!--<H3 align=center>划款申请历史</H3> -->

    <s:form name="form2" action="applyMerSettlementEn" method="post">
               <input type="hidden" value="16885" name="tradeinfo.merno" />
    	<!--<center>   &nbsp; &nbsp; &nbsp; &nbsp; 总金额: <font color=red><s:property value="tradeMoney"/> </font>RMB</center>
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
	  						<input type="checkbox" name="freezeId" value="<s:property value="#gouDuiList.id"/>">
	  					</td>
		    			<td>
		    				<s:property value="tradeinfo.merno"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList.orderNo"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList.merchantOrderNo"/>&nbsp;
		    			</td>		    	
		    			<td>
		    				<s:property value="#gouDuiList.tradeAmount"/>&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList.tradeTime"/>&nbsp;
		    			</td>
		    	
		    			<td>
		    				<s:property value="#gouDuiList.isresultName"/>&nbsp;
		    				<s:if test="#gouDuiList.tradeState.substring(0,1)==0">
		    					失败
		    				</s:if>
		    				<s:elseif test="#gouDuiList.tradeState.substring(0,1)==1">
		    					成功
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList.tradeState.substring(0,1)==2">
		    					待处理
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList.tradeState.substring(0,1)==3">
		    					取消
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList.tradeState.substring(0,1)==4">
		    					待确认
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList.tradeState.substring(0,1)==5">
		    					未返回
		    				</s:elseif>
		    			</td>
		    			<td>
		    				<s:if test="#gouDuiList.tradeState.substring(1,2)==0">
		    					未退款
		    				</s:if>
		    				<s:elseif test="#gouDuiList.tradeState.substring(1,2)==1">
		    					已退款
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList.tradeState.substring(1,2)>1">
		    					<s:property value="#gouDuiList.tradeState.substring(1,2)"/>
		    				</s:elseif>&nbsp;
		    			</td>
		    			<td>
		    				<s:if test="#gouDuiList.tradeState.substring(3,4)==0">
		    					未冻结
		    				</s:if>
		    				<s:elseif test="#gouDuiList.tradeState.substring(3,4)==1">
		    					已冻结
		    				</s:elseif>
		    				<s:elseif test="#gouDuiList.tradeState.substring(3,4)==2">
		    					解冻
		    				</s:elseif>&nbsp;
		    			</td>
		    			<td>
		    				<s:if test="#gouDuiList.tradeState.substring(2,3)==0">
		    					未拒付
		    				</s:if>
		    				<s:elseif test="#gouDuiList.tradeState.substring(2,3)==1">
		    					拒付
		    				</s:elseif>
		    				&nbsp;		    			
		    			</td>		   
		    			<td>
		    				<s:if test="#gouDuiList.tradeState.substring(4,5)==0">
		    					未勾兑
		    				</s:if>
		    				<s:elseif test="#gouDuiList.tradeState.substring(4,5)==1">
		    					已勾兑
		    				</s:elseif>
		    				&nbsp;
		    			</td>
		    			<td>
		    				<s:if test="#gouDuiList.isTrackNo==null">
		    					已上传
		    				</s:if>
		    				<s:elseif test="#gouDuiList.isTrackNo!=null">
		    					<s:property value="#gouDuiList.isTrackNo"/>
		    				</s:elseif>
		    				&nbsp;
		    			</td>
		    			<td>
		    				<s:property value="#gouDuiList.remark"/>&nbsp;
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
     
     
 <div class="mainbody" style="position:absolute; left:310px">
	   <div class="list">
	   
	      <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">Application History</li>
            
           </ul>
          <ul class="bottom">       

		       <li class="li_04">Amount</li> 
		       <li class="li_04">Status</li>   
		       <li class="li_04">Refund</li>   
		       <li class="li_03">Freeze</li>    
               <li class="li_05">Chargeback</li>    
               <li class="li_03">Check</li>    
               <li class="li_05">tracking No.</li>    
             </ul>
         </div>
		 
		  <div class="listlist">
		     <s:iterator id="gouDuiList" value="tradeList">
                <ul class="listlisttop">
            		 <li>Order No.:<s:property value="#gouDuiList.merchantOrderNo"/></li>
             		 <li>Date:<s:property value="#gouDuiList.tradeTime"/></li>
           		</ul>
           		<ul class="listlistbottom">

             		 <li class="lil_04"><s:property value="#gouDuiList.tradeAmount"/></li>    
            		 <li class="lil_04">
            		 <s:property value="states.getStateNameEn(#gouDuiList.tradeState,1)"  escape="false"/>	
                     </li>   
            		 <li class="lil_04">
            		 <s:if test="#gouDuiList.tradeState.substring(1,2)==2">
        		 	 <font color="red"><s:property value="#gouDuiList.backCount"/></font>
        		 	 </s:if>
        		 	 <s:else>
                         <s:property value="states.getStateNameEn(#gouDuiList.tradeState,2)"  escape="false"/>		
                 	</s:else>
                     </li>   
             		 <li class="lil_03">
             		 <s:property value="states.getStateNameEn(#gouDuiList.tradeState,4)"  escape="false"/>		
                     </li>    
            		 <li class="lil_05">
            		 <s:property value="states.getStateNameEn(#gouDuiList.tradeState,3)"  escape="false"/>		
                     </li>    
            		 <li class="lil_03">
            		 <s:property value="states.getStateNameEn(#gouDuiList.tradeState,5)"  escape="false"/>		
                     </li>    
           		     <li class="lil_05">
                     <s:if test="#gouDuiList.isTrackNo==null">
		    					YES
		    				</s:if>
		    				<s:elseif test="#gouDuiList.isTrackNo!=null">
		    					<s:property value="#gouDuiList.isTrackNo"/>
		    				</s:elseif>
                       </li>    

          		 </ul>
		     </s:iterator>
			  <br class="clear" />
		    <!-- <ul class="listlistpage">
		        <li><input type="button" value="确&nbsp;定" class="input_button_01" onClick="check()"/>&nbsp;&nbsp;<input type="reset" class="input_button_01" value="取&nbsp;消" /></li> -->
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
