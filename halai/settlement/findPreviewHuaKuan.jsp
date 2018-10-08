<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
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
    <title>查看划款表</title>
	<link rel="stylesheet" type="text/css" href="css/bail.css">
	<h3 align="center">查看划款表 (打印无效)</h3>
  </head>
  <BODY>
  	<s:form action="createHuakuanAction" theme="simple" method="post">
	  	<table  width="48%" align=center height="10">
	  		<tr>		
	  			<td><b>制表时间：</b><s:property value="createtabletime"/>&nbsp;</td>
	  			<td><b>批次号：</b><s:property value="batchNo"/>&nbsp;</td>
	  		</tr>
	  
	  	</table >
     	<table cellSpacing=0 cellPadding=0 width="48%" align=center border=1>
	    	<tr>
	    		<th height=25 colSpan=2 width="30%" >商户号</th>
	    		<td colSpan="<s:property value="colSpan"/>"> 
	    			<input type="hidden" name="merchantNo" value="<s:property value="merchantNo"/>">
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
	    			<td><s:property value="#ii.channels"/>通道</td>
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
		    			<s:property value="#ii.poundagerate"/>&nbsp;
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
	    		<th colSpan=2 height=25>保证金比例</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.bailrate"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>结算汇率设置日期</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.exchangedate"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>结算汇率</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.balancerate"/>&nbsp;
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
	    		<th>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noprotestnumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noprotestmoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>已结算本期拒付</th>
	    	</tr>
	    	<tr>
	    		<th>笔数</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.protestnumber"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th>金额</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.protestmoney"/>&nbsp;
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
		    			<s:property value="#ii.poundage"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=30>拒付处理费(只按美元汇率结算)</th>
	    			<s:iterator id="ii" value="listName">
			    		<td>
			    			<s:property value="#ii.protestfee"/>&nbsp;
			    		</td>
		    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=30>结算当天美元汇率</th>
	    			<s:iterator id="ii" value="listName">
			    		<td>
			    			<s:property value="#ii.nowbalancerate"/>&nbsp;
			    		</td>
		    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>应结算金额(美元)(不包含拒付处理费)</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.balancemoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>应结算金额(RMB)(包含拒付处理费)</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.rmbmoney"/>&nbsp;
		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>应结算金额(RMB)(包含拒付处理费)</th>
	    		<td colSpan="<s:property value="colSpan"/>">
	    			<s:property value="chinamoney"/>&nbsp;
	    		</td>
	    	</tr>
    	</table>
    	<table width="43%" align=center height="10">
    		<tr>
    			<td>制表人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    			<td>复核人：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    			<td>财务：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    		</tr>
    	</table>
    	<table align="center">
    		<tr>
    			<td>
    				<input type="button" value="直接打印" onclick="huakuan_print();">   
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<a href="findHuaKuanAction.action">返回</a>
    			</td>
    		</tr>
    	</table>
    </s:form>
  </body>
</html>
    