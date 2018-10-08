<%@ page language="java" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>划款历史查看</title>
<link rel="stylesheet" type="text/css" href="css/mercss.css"/>


<script type="text/javascript">
	function merMain(){
		var url ="merMainAction.action";
		document.form1.action=url;
		document.form1.submit();
	}
	
	function  check(){
	
		document.batchform.submit();
	}
</script>

</head>
<body>
 <!--头部begin-->
<s:action name="indexMenu" executeResult="true" />  
<DIV class=clear></DIV>
<div class="middle">
<table>
    <tr>
     
   <td valign="top">  
   	
     <div class="right">
    
          <div class="right_top" align=center >  &nbsp;&nbsp;&nbsp;<font  size=3 color="">划款历史查看</font> </div>
          <div class="right_text">
			<s:form action="huakuanHistoryAction.action" name="batchform" theme="simple" method="post">
			<table  width="43%" align=center border=0 >
				<tr>
					<td align=center colspan=2>
						<font color=red><s:fielderror></s:fielderror></font>
					</td>
				</tr>
				<tr>
					<td align=center >
						 &nbsp;
					</td>
					<td align=center>
						
					批次号: &nbsp;&nbsp;
					<select  name="batchNo"  >
    							<option value="0" >--请选择批次号--</option> 
								<s:iterator id="batchId" value="%{#session.batchList}">
									<option value="<s:property value = "#batchId.batchno" />" ><s:property value = "#batchId.batchno" /></option>
								</s:iterator>
							</select> </td>
				</tr>
				<tr>
					<td align=center colspan=2>
						<input type=button value="查询" onclick="check()"/> 
					</td>
				</tr>
			
			</table>
			</s:form>
		</div>
	 </div>
			<br/>
			<br/>
		<s:if test="%{#session.batchList.size!=0}">
	  	<table  width="43%" align=center height="10">
	  		<tr>		
	  			<td><b>制表时间：</b><s:property value="createtabletime"/>&nbsp;</td>
	  			<td><b>批次号：</b><s:property value="batchNo"/>&nbsp; &nbsp; &nbsp; &nbsp; 
	  			
	  			<a href="huakuanTradeDetailAction.action?batchNo=<s:property value="batchNo"/>" ><font color=blue>(明细查看)</font></a> 
	  			
	  			</td>
	  			
	  		</tr>
	  
	  	</table >
     	<table cellSpacing=0 cellPadding=0 width="43%" align=center border=1>
	    	<tr>
	    		<th height=25 colSpan=2>商户号</th>
	    		<td colSpan="<s:property value="colSpan"/>"> 
	    			<input type="hidden" name="merchantNo" value="<s:property value="merchantNo"/>"/>
	    			<s:property value="merchantNo"/>&nbsp;
	    		</td>
	    	</tr>
	    	<tr>
	    		<th rowSpan=4>收款人</th>
	    	</tr>
	    	<tr>
	    		<th height=25>开户名</th>
	    		<td  colSpan="<s:property value="colSpan"/>"> 
	    			<s:property value="accountName"/>&nbsp;
	    		</td>
	    	</tr>
	    	<tr>
	    		<th height=25>开户行</th>
	    		<td  colSpan="<s:property value="colSpan"/>">
	    			<s:property value="bank"/>&nbsp;
				</td>   		
	    	</tr>
	    	<tr>
	    		<th height=25>帐号</th>
	    		<td  colSpan="<s:property value="colSpan"/>">
	    			<s:property value="cardNo"/>&nbsp;
	    		</td>
	    	</tr>
	    	<tr>
	    		<th height=25 colSpan=2>通道</th>
	    		<s:iterator id="ii" value="listName">
	    			<th><s:property value="#ii.channels"/>通道</th>
	    		</s:iterator>
	    	</tr>
	    	
	    	<tr>
	    		<th colSpan=2 height=25>交易币种</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.trademoneykindname"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>手续费率</th>
    			<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.protestfee"/>&nbsp;
		    		</td>
    			</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>定额手续费</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.procefeeration"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>保证金费率</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.bailrate"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>本期结算交易</th>
	    	</tr>
	    	<tr>
	    		<th height=25>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tradenumber"/> &nbsp;
		    		</td>   	
	    		</s:iterator>	
	    	</tr>
	    	<tr>
	    		<th height=25>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.trademoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>未结算本期退款</th>
	    	</tr>
	    	<tr>
	    		<th height=25>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.norefundnumber"/>&nbsp;
		    		</td>  		
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=25>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.norefundmoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>已经结算本期退款</th>
	    	</tr>
	    	<tr>
	    		<th height=25>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.refundnumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=25>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.refundmoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>已经结算本期冻结</th>	
	    	</tr>
	    	<tr>
	    		<th height=25>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.freezenumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=25>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.freezemoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>解冻交易</th>
	    	</tr>
	    	<tr>
	    		<th height=25>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.thawnumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=25>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.thawmoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>未结算本期拒付</th>
	    	</tr>
	    	<tr>
	    		<th height=25>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td height=25>
		    			<s:property value="#ii.noprotestnumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=25>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td height=25>
		    			<s:property value="#ii.noprotestmoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>已结算本期拒付</th>
	    	</tr>
	    	<tr>
	    		<th height=25>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.protestnumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=25>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.protestmoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>保证金金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.bailmoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=30>手续费</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.poundage"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=30>拒付处理费</th>
	    			<s:iterator id="ii" value="listName">
			    		<td>
			    			<s:property value="#ii.protestfee"/>&nbsp;
			    		</td>
		    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=30>结算汇率</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.balancerate"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>应结算金额(美元)</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.balancemoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>应结算金额(人民币)</th>
	    		<td  colSpan="<s:property value="colSpan"/>">
	    			<s:property value="chinamoney"/>&nbsp;
	    		</td>
	    	</tr>
    	</table>
    	</s:if>
    	<s:else>
    		<div align=center><font  color= red size=3>你好，你暂时还没有划款记录!</font></div>
    	</s:else>
    	
    
			<table align="center">
    		<tr>
    			
    			<td>
    				&nbsp;
    			</td>
    			
    		</tr>
    		<tr>
    			<td align=center>
    				<A href="toMerHuakuanAction.action" target=_top><font color=blue>返回</font></a>
    			</td>
    		</tr>
    	</table>
   			

           
         
   
 </td>
 </tr>
 </table> 
</div>
<br/>
<br/>
		<DIV align=center>
			<form name = form1  action=""></form>
			<A href="javascript:merMain()" target=_top><font color=blue>返回主菜单</font></A>
		</DIV>
<DIV class=clear></DIV>
<br/>
<div class="bottom">
      <%@ include file="bottom.jsp"%>
     <div class="banquan" >
     Copyright 2000-2009   Ecpss All Rights Reserved
     </div>
     <div class="banquan" >
      版权所有：上海迁易网络科技有限公司
     </div>
</div>
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->	
</body>
</html>
