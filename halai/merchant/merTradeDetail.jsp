<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>交易查询</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />

<script language="JavaScript" src="../js/util.js"></script>
</style>
<script language="JavaScript" type="text/JavaScript">

	function exportInfo(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("isdownload");
		exportX.value="1";
		document.getElementById("formu").submit();
	}
	
	function chaxun(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("isdownload");
		exportX.value="";
		formX.submit();
	}
	function cancelRefund(refundid){
		window.location="../PaySystem/cancelRefund.action?refundId="+refundid;
	}
</script>

</head>
<body>
    <div class="mainbody2">
       
       <div class="list">
           <!--<ul class="bottom">
             <li class="li_03">金额</li>  
             <li class="li_01">币种</li>  
             <li class="li_01">通道</li> 
             <li class="li_01">状态</li> 
             <li class="li_01">冻结</li> 
             <li class="li_01">退款</li>  
             <li class="li_01">拒付</li>  
             <li class="li_01">划款</li>  
             <li class="li_01">勾兑</li>  
             <li class="li_01">传号</li>         
             <li class="li_02">持卡人邮箱</li>             
           </ul> -->
        <div class="listlist">
        
           <div class="danzi">
              <div class="jiben"><span><b>流水订单号：</b><s:property value="tradeDetail[0].orderNo" /></span></div>
              <div class="jiben"><span><b>金额：</b><font class="font_color_01"><s:property value="tradeDetail[0].tradeAmount"/></font></span></div>
              <div class="jiben"><span><b>订单号：</b><s:property value="tradeDetail[0].merchantOrderNo" /></span></div>
              <div class="jiben"><span><b>交易日期：</b><s:property value="tradeDetail[0].tradeTime"/></span></div>
              <div class="jiben"><span><b>交易网址：</b><s:property value="tradeDetail[0].tradeUrl"/></span></div>
              <div class="jiben"><span><b>跟踪单号：</b>
              <s:if test="tradeDetail[0].isTrackNo!=null">
              	<s:property value="tradeDetail[0].isTrackNo"/>
              </s:if>
              <s:else>
              	未上传
              </s:else>
              </span></div>
              <div class="biaoge">
                <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#996600">
                  <tr>
                    <td align="center" bgcolor="#F3D08A">金额</td>
                    <td align="center" bgcolor="#F3D08A">币种</td>
                    <td align="center" bgcolor="#F3D08A">通道</td>
                    <td align="center" bgcolor="#F3D08A">状态</td>
                    <td align="center" bgcolor="#F3D08A">冻结</td>
                    <td align="center" bgcolor="#F3D08A">退款</td>
                    <td align="center" bgcolor="#F3D08A">退款金额</td>
                    <td align="center" bgcolor="#F3D08A">拒付</td>
                    <td align="center" bgcolor="#F3D08A">划款</td>
                    <td align="center" bgcolor="#F3D08A">勾兑</td>
                  </tr>
                  <tr>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="tradeDetail[0].tradeAmount"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getCurrencyTypeByNo(tradeDetail[0].moneyType)"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="tradeDetail[2]"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateName(tradeDetail[0].tradeState,1)" escape="false" /></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateName(tradeDetail[0].tradeState,4)" escape="false"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateName(tradeDetail[0].tradeState,2)" escape="false"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="tradeDetail[0].backCount" /></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateName(tradeDetail[0].tradeState,3)" escape="false"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateName(tradeDetail[0].tradeState,8)" escape="false"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateName(tradeDetail[0].tradeState,5)" escape="false"/></td>
                   
                  </tr>
                </table>
              </div>
              
              <div class="jiben"><span><b>姓名：</b><s:property value="tradeDetail[3].shippingFullName"/></span></div>
              <div class="jiben"><span><b>国家：</b><s:property value="tradeDetail[3].shippingCountry"/></span></div>
              <div class="jiben"><span><b>州/省：</b><s:property value="tradeDetail[3].shippingState"/></span></div>
              <div class="jiben"><span><b>城市：</b><s:property value="tradeDetail[3].shippingCity"/></span></div>
              <div class="jiben"><span><b>地址：</b><s:property value="tradeDetail[3].shippingAddress"/></span></div>
              <div class="jiben"><span><b>电话：</b></span><s:property value="tradeDetail[3].shippingPhone"/></div>
              <div class="jiben"><span><b>邮编：</b></span><s:property value="tradeDetail[3].shippingZip"/></div>
              <div class="jiben"><span><b>备注信息：</b><s:property value="tradeDetail[0].remark"/></span></div>   <br>  
              <div class="jiben"><span><b>EMAIL：</b><s:property value="tradeDetail[3].email"/></span></div>
              <div class="jiben_wupin"><span><b>物品信息：</b></span></div>
              <div class="jiben_wupin">
                <span><s:property value="tradeDetail[3].productInfo"/></span>
              </div>
              <div class="clear"></div>       
           </div>
        
        
         </div>
         
      
       </div>
     </div>
   </div>
   
   
   