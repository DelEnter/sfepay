<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>划款表审核</title>
	<link rel="stylesheet" type="text/css" href="css/bail.css">
	<style>
	td,tr,talbe{font-size:12px;}
	</style>
	<h3 align="center">划款表审核</h3>
  </head>
  <SCRIPT language=JavaScript>
  function passSettlement(f){
      f.submit();
      alert('审核通过');
	  window.close();
  }
  function nopassSettlement(f){
      f.submit();
      alert('审核不通过');
  window.dialogArguments.location.reload();    
      parent.window.close();
  }  
  	</script>  
  	<base target="_self">   
  <BODY>
  	<s:form action="createSetemlement" theme="simple" method="post">
	<s:token></s:token>	
	  	<table width="80%" cellSpacing=0 cellPadding=0  align=center border=0>
	  		<tr>		
	  			<td><b>批次号：<s:property value="batchNo"/></b></td>
	  		</tr>  
	  	</table >
     	<table width="80%" cellSpacing=0 cellPadding=0  align=center border=1>
	    	<tr>
	    		<th height=25 colSpan=2 width="40%">商户号</th>
	    		<td colSpan="<s:property value="totPage"/>">
	    			<input type="hidden" name="merchantNo" value="<s:property value="tradeinfo.merchantNo"/>">
	    			<s:property value="tradeinfo.merno"/>&nbsp;
	    		</td>
	    	</tr>
	    	<tr>
	    		<th rowSpan=4 >收款人</th>
	    	</tr>
	    	<tr> 
	    		<th  height=20>开户名</th>
	    		<td colSpan="<s:property value="totPage"/>">
	    			<s:property value="tradeinfo.accountname"/>&nbsp;
	    		</td>
	    	</tr>
	    	<tr>
	    		<th  height=20>开户行</th>
	    		<td colSpan="<s:property value="totPage"/>">
	    			<s:property value="tradeinfo.bank"/>&nbsp;
				</td>   		
	    	</tr>
	    	<tr>
	    		<th  height=20>帐号</th>
	    		<td colSpan="<s:property value="totPage"/>">
	    			<s:property value="tradeinfo.cardno"/>&nbsp;
	    		</td>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=20>通道</th>
	    		<s:iterator id="ii" value="listName">
	    			<td><s:property value="#ii.channels"/></td>&nbsp;
	    		</s:iterator>
	    	</tr>
	    	
	    	<tr>
	    		<th colSpan=2 height=20>交易币种</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tradeMoneykindName"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	
	    	<tr>
	    		<th colSpan=2 height=20>手续费率</th>
    			<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.produceRate"/>&nbsp;
		    		</td>
    			</s:iterator>
	    	</tr>
	    	
	    	<tr>
	    		<th colSpan=2 height=20>定额手续费</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.proceFeeRation"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=20>保证金比例</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.bailrate"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>本期未划款交易</th>
	    	</tr>
	    	<tr>
	    		<th height=20>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tradeNumberAll"/> &nbsp;
		    		</td>   	
	    		</s:iterator>	
	    	</tr>
	    	<tr>
	    		<th height=20>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tradeMoneyAll"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>未划款本期部分退款</th>
	    	</tr>
	    	<tr>
	    		<th height=25>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noTuikuanNumber"/>&nbsp;
		    		</td>  		
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=20>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noTuiKuanMoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>未划款本期全部退款</th>
	    	</tr>
	    	<tr>
	    		<th height=20>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noTuikuanNumberall"/>&nbsp;
		    		</td>  		
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=20>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noTuiKuanMoneyall"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>	    	
	    	
	    	<tr>
	    		<th rowSpan=3>已经划款本期部分退款</th>
	    	</tr>
	    	<tr>
	    		<th height=20>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tuiKuanNumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=20>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tuiKuanMoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>已经划款本期全部退款</th>
	    	</tr>
	    	<tr>
	    		<th height=20>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tuiKuanNumberall"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=20>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tuiKuanMoneyall"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>	    	
	    	<tr>
	    		<th rowSpan=3>已划款本期冻结</th>	
	    	</tr>
	    	<tr>
	    		<th height=20>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.freezeNumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=20>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.freezeMoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>解冻交易</th>
	    	</tr>
	    	<tr>
	    		<th height=20>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.thawNumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th height=20>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.thawMoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>未划款本期拒付</th>
	    	</tr>
	    	<tr>
	    		<th>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noProtestNumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noProtestMoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>已划款本期拒付</th>
	    	</tr>
	    	<tr>
	    		<th>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.protestNumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.protestMoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2>保证金金额</th>
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
		    			<s:property value="#ii.procedureFee"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>应划款交易金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.balancemoney"/>&nbsp;
		    	   </td>
	    		</s:iterator>
	    	</tr>	    	
	    	<tr>
	    		<th colSpan=2 height=30>拒付处理费</th>
	    			<s:iterator id="ii" value="listName">
			    		<td>
			    			<s:property value="#ii.protestFee"/>&nbsp;&nbsp;&nbsp;(拒付笔数*20 USD*<s:property value="#ii.rateTostring"/>)
			    		</td>
		    		</s:iterator>
	    	</tr>
	    	

	    	<tr>
	    		<th colSpan=2 height=25>应划款交易金额(RMB)</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.rmbmoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>风控处理费</th>
	    		<td colSpan="<s:property value="totPage+1"/>">
	    			<s:property value="#ii.riskFee"/>&nbsp;
	    		</td>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>通道使用费</th>
	    		<td colSpan="<s:property value="totPage+1"/>">
	    			<s:property value="#ii.channelFee"/>&nbsp;
	    		</td>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>合计</th>
	    		<td colSpan="<s:property value="totPage+1"/>">
	    			<s:property value="rmbmoney"/>&nbsp;
	    		</td>
	    	</tr>
    	</table>
    	 </s:form>
    	<table align="center">
    	<form name="passfome" action="auditSettlementPass" method="post" >
    	     <tr>
    			<td>
				<s:hidden name="remark"></s:hidden>
    			</td>
    		</tr> 
    		<tr>
    			<td>
    			    <input type="hidden" value="<s:property value='batchNo'/>" name="batchNo"/>
    			    <input type="hidden" value="<s:property value='merchantId'/>" name="merchantId"/>
    				<input type="button" onclick="passSettlement(this.form);"  value="审核通过"/>
    			
    			</td>
    		</tr> 	
    	</form>		
    	        <tr>
    			<td>
    			<form name="passfome" action="auditSettlementNotOk" method="post" >
    			    <input type="hidden" value="<s:property value='batchNo'/>" name="batchNo"/>
    			    <input type="hidden" value="<s:property value='merchantId'/>" name="merchantId"/>
    				<input type="button" onclick="nopassSettlement(this.form);" value="审核不通过"/>
    			</form>	
    			</td>
    		</tr>
    		<tr>
			<td>
				<input type="button" value="打印" onClick="huakuan_print();">   
			</td>
		</tr>
    	</table>
    	<SCRIPT language=JavaScript>
  		var value = 0;
	    function huakuan_print(){
	    	if(value==0){
	    		window.print();
	    	}
	    	value++;
	    	window.history.forward();
	    }

    </script> 
  </body>
</html>
    