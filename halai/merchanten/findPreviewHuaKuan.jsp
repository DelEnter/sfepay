<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <title>View Settlement Form</title>
	<link rel="stylesheet" type="text/css" href="css/bail.css">
	<style>
	td,tr,talbe{font-size:12px;}
	</style>
	<h3 align="center">View Settlement Form （Print Invalidly）</h3>
  </head>
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
   
  <BODY>
  	<s:form action="createSetemlement" theme="simple" method="post">
	<s:token></s:token>	
	  	<table width="80%"  cellpadding="5" cellSpacing=0 cellPadding=0  align=center border=0>
	  		<tr>		
	  			<td><b>Batch No.:<s:property value="batchNo"/></b></td>
	  		</tr>  
	  	</table >
     	<table width="80%" border=1  align=center cellPadding=5 cellSpacing=0>
<tr>
	    		<th height=25 colSpan=2 width="40%">Merchant No.</th>
	    		<td colSpan="<s:property value="totPage"/>">
	    			<input type="hidden" name="merchantNo" value="<s:property value="tradeinfo.merchantNo"/>">
	    			<s:property value="tradeinfo.merno"/>&nbsp;
          </tr>
	    	<tr>
	    		<th rowSpan=4 >Beneficiary</th>
	    	</tr>
	    	<tr> 
	    		<th  height=20>Account Name</th>
	    		<td colSpan="<s:property value="totPage"/>">
	    			<s:property value="tradeinfo.accountname"/>&nbsp;
            </tr>
	    	<tr>
	    		<th  height=20>Account-Opening Bank</th>
	    		<td colSpan="<s:property value="totPage"/>">
	    			<s:property value="tradeinfo.bank"/>&nbsp;
            </tr>
	    	<tr>
	    		<th  height=20>Account</th>
	    		<td colSpan="<s:property value="totPage"/>">
	    			<s:property value="tradeinfo.cardno"/>&nbsp;
            </tr>
	    	<tr>
	    		<th colSpan=2 height=20>Channel</th>
	    		<s:iterator id="ii" value="listName">
    			  <td>
    			    <s:if test="#ii.channels=='VC-18'">
		    		Master Card 
		    		</s:if>
    			    <s:if test="#ii.channels=='EVIP-1'">
		    		Visa Card 
		    		</s:if>	    			  
    			  </td> 	
	    		
    			      		</s:iterator>
	    	</tr>
	    	
	    	<tr>
	    		<th colSpan=2 height=20>Transaction Currency</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    		<s:if test="#ii.tradeMoneykindName=='美元'">
		    		USD
		    		</s:if>
		         	<s:if test="#ii.tradeMoneykindName=='英镑'">
		    		GBP
		    		</s:if>
		    		<s:if test="#ii.tradeMoneykindName=='欧元'">
		    		EUR
		    		</s:if>
		    			&nbsp;		 
		    	    </td>
	    		</s:iterator>
	    	</tr>
	    	
	    	<tr>
	    		<th colSpan=2 height=20>Transaction Fee Rate</th>
    			<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.procedureRate*100"/>%&nbsp;		    		</td>
    			</s:iterator>
	    	</tr>
	    	
	    	<tr>
	    		<th colSpan=2 height=20>Ration Transaction Fee Rate</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.proceFeeRation*100"/>%&nbsp;		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=20>Rolling Reserve Rate</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.bailrate*100"/>%&nbsp;		    		</td>
	    		</s:iterator>
	    	</tr>

	    	<tr>
	    		<th rowSpan=3>UnSettlement Transaction (current Period)</th>
	    	</tr>
	    	<tr>
	    		<th height=20>Quantity</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tradeNumberAll"/>&nbsp;		    		</td>   	
	    		</s:iterator>	 
	    	</tr>
	    	<tr>
	    		<th height=20>Amount</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tradeMoneyAll"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>UnSettlement and Partial Refunded (current period) to Customers</th>
	    	</tr>
	    	<tr>
	    		<th height=25>Quantity</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noTuikuanNumber"/>&nbsp;		    		</td>  		
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th height=20>Amount</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noTuiKuanMoney"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>UnSettlement and Full refunded (current period) to Customers</th>
	    	</tr>
	    	<tr>
	    		<th height=20>Quantity</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noTuikuanNumberall"/>&nbsp;		    		</td>  		
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th height=20>Amount</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noTuiKuanMoneyall"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>	    	
	    	
	    	<tr>
	    		<th rowSpan=3>Settlement and Partial Refunded (current period) to Customers</th>
	    	</tr>
	    	<tr>
	    		<th height=20>Quantity</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tuiKuanNumber"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th height=20>Amount</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tuiKuanMoney"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>Settlement and Full Refunded (current period) to Customers</th>
	    	</tr>
	    	<tr>
	    		<th height=20>Quantity</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tuiKuanNumberall"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th height=20>Amount</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.tuiKuanMoneyall"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>	    	
	    	
	    	<tr>
	    		<th rowSpan=3>UnSettlement but Chargebacked (current period)</th>
	    	</tr>
	    	<tr>
	    		<th>Quantity</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noProtestNumber"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th>Amount</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.noProtestMoney"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th rowSpan=3>Settlement but Chargebacked (current period)</th>
	    	</tr>
	    	<tr>
	    		<th>Quantity</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.protestNumber"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th>Amount</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.protestMoney"/>&nbsp;		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2>ECPSS Rolling Reserve Amount</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.bailmoney"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=30>ECPSS Transaction Fee</th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.procedureFee"/>&nbsp;		    		</td>
	    		</s:iterator> 
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>The Transaction amount to be Settlement to Merchant </th>
	    		<s:iterator id="ii" value="listName">
		    		<td>
		    			<s:property value="#ii.balancemoney"/>&nbsp;		    	   </td>
	    		</s:iterator> 
	    	</tr>	    	
	    	<tr>
	    		<th colSpan=2 height=30>Chargeback Handling Fee (USD) to Bank</th>
	    			<s:iterator id="ii" value="listName">
			    		<td>
			    			<s:property value="#ii.protestFee"/>&nbsp;			    		</td>
		    		</s:iterator> 
	    	</tr>
	    	

	    	<tr>
	    		<th colSpan=2 height=25>The Transaction amount to be Settlement to Merchant</th>
	    		<s:iterator id="ii" value="foreignMoney">
		    		<td>
		    			<s:property value="#ii"/>&nbsp;		    		</td>
	    		</s:iterator>
	    	</tr>
	    	<tr>
	    		<th colSpan=2 height=25>Total Payable To Merchant</th>
	    		<td colSpan="<s:property value="totPage"/>">
	    			<s:property value="rmbmoney"/>&nbsp;
	    		</td>
	    	</tr>
   	  </table>
<table width="43%" align=center height="10">
    		<tr>
    			<td>Lister：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    			<td>Check Person：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    			<td>Accountant：&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</td>
    		</tr>
    	</table>
    	<table align="center">
    		<tr>
    			<td>
    				Remark： <textarea readonly=true><s:property value='remark'/> </textarea>
    			</td>
    		</tr>    	
    		<tr>
    			<td>
    				<input type="button" value="Directly Print" onClick="huakuan_print();">   
    			</td>
    		</tr>
    		<tr>
    			<td>
    				<a onclick="closewin()" href="#">Close</a>
    			</td>
    		</tr>
    	</table>
    </s:form>
  </body>
</html>
    