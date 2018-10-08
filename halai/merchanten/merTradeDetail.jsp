<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title></title>
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
<body contentEditable=true>

    <div class="mainbody2">
       
       <div class="list">
           <!--<ul class="bottom">
             <li class="li_03">Amount</li>  
             <li class="li_01">Currency</li>  
             <li class="li_01">Channel</li> 
             <li class="li_01">Status</li> 
             <li class="li_01">Freeze</li> 
             <li class="li_01">Refund</li>  
             <li class="li_01">ChargeBack</li>  
             <li class="li_01">Settlement</li>  
             <li class="li_01">Check</li>  
             <li class="li_01">Tracking</li>         
             <li class="li_02">持卡人邮箱</li>             
           </ul> -->
        <div class="listlist">
        
           <div class="danzi">
              <div class="jiben"><span><b>Order No.：</b><s:property value="tradeDetail[0].orderNo" /></span></div>
              <div class="jiben"><span><b>Amount：</b><font class="font_color_01"><s:property value="tradeDetail[0].tradeAmount"/></font></span></div>
              <div class="jiben"><span><b>Merchant Order No.：</b><s:property value="tradeDetail[0].merchantOrderNo" /></span></div>
              <div class="jiben"><span><b>Trade Date：</b><s:property value="tradeDetail[0].tradeTime"/></span></div>
              <div class="jiben"><span><b>Trade website：</b><s:property value="tradeDetail[0].tradeUrl"/></span></div>
              <div class="jiben"><span><b>Tracking No.：</b>
              <s:if test="tradeDetail[0].isTrackNo!=null">
              	<s:property value="tradeDetail[0].isTrackNo"/>
              </s:if>
              <s:else>
              	No
              </s:else>
              </span></div>
              <div class="biaoge">
                <table width="100%" border="0" cellpadding="0" cellspacing="1" bgcolor="#996600">
                  <tr>
                    <td align="center" bgcolor="#F3D08A">Amount</td>
                    <td align="center" bgcolor="#F3D08A">Currency</td>
                    <td align="center" bgcolor="#F3D08A">Channel</td>
                    <td align="center" bgcolor="#F3D08A">Status</td>
                    <td align="center" bgcolor="#F3D08A">Freeze</td>
                    <td align="center" bgcolor="#F3D08A">Refund</td>
                    <td align="center" bgcolor="#F3D08A">ChargeBack</td>
                    <td align="center" bgcolor="#F3D08A">Settlement</td>
                    <td align="center" bgcolor="#F3D08A">Check</td>
                    <td align="center" bgcolor="#F3D08A">Tracking</td>
                  </tr>
                  <tr>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="tradeDetail[0].tradeAmount"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getCurrencyTypeByNo(tradeDetail[0].moneyType)"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="tradeDetail[2]"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateNameEn(tradeDetail[0].tradeState,1)" escape="false" /></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateNameEn(tradeDetail[0].tradeState,4)" escape="false"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateNameEn(tradeDetail[0].tradeState,2)" escape="false"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateNameEn(tradeDetail[0].tradeState,3)" escape="false"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateNameEn(tradeDetail[0].tradeState,8)" escape="false"/></td>
                    <td align="center" bgcolor="#FFF6E4"><s:property value="states.getStateNameEn(tradeDetail[0].tradeState,5)" escape="false"/></td>
                    <td align="center" bgcolor="#FFF6E4">
                      <s:if test="tradeDetail[0].isTrackNo!=null">
             		    Yes
             	      </s:if>
             	      <s:else>
             		    No
             	      </s:else>
                    </td>
                  </tr>
                </table>
              </div>
              <div class="jiben"><span><b>Name：</b><s:property value="tradeDetail[3].shippingFullName"/></span></div>
              <div class="jiben"><span><b>Country：</b><s:property value="tradeDetail[3].shippingCountry"/></span></div>
              <div class="jiben"><span><b>State：</b><s:property value="tradeDetail[3].shippingState"/></span></div>

              <div class="jiben"><span><b>City：</b><s:property value="tradeDetail[3].shippingCity"/></span></div>
              <div class="jiben"><span><b>Street：</b><s:property value="tradeDetail[3].shippingState"/></span></div>
              <div class="jiben"><span><b>Telephone NO.：</b></span><s:property value="tradeDetail[3].shippingPhone"/></div>
              <div class="jiben"><span><b>Post Code：</b></span><s:property value="tradeDetail[3].shippingZip"/></div>
              <div class="jiben"><span><b>Remark Information：</b>
               <s:if test="tradeDetail[0].remark=='黑卡'">
              	Confiscate the card
              </s:if>
               <s:if test="tradeDetail[0].remark=='沒收卡'">
              	Confiscate the card
              </s:if>
               <s:if test="tradeDetail[0].remark=='被窃卡'">
              	Stolen card
              </s:if>
               <s:if test="tradeDetail[0].remark=='不接纳此卡'">
              	not acceptable card
              </s:if>
               <s:if test="tradeDetail[0].remark=='不接受'">
              	decline
              </s:if>
               <s:if test="tradeDetail[0].remark=='查询卡中心'">
              	inquire Card center
              </s:if>
               <s:if test="tradeDetail[0].remark=='此卡已失效'">
              	This card has run out
              </s:if>
               <s:if test="tradeDetail[0].remark=='单笔超限'">
              	Single pen off-gauge
              </s:if>
               <s:if test="tradeDetail[0].remark=='单笔交易超过限额'">
              	Single transactions exceed limit
              </s:if>
               <s:if test="tradeDetail[0].remark=='非法交易'">
              illegal dealings
              </s:if>
               <s:if test="tradeDetail[0].remark=='风险值过大！'">
              Value at risk too
              </s:if>                                                                      
               <s:if test="tradeDetail[0].remark=='禁止交易地区'">
              Prohibited transactions areas
              </s:if>
               <s:if test="tradeDetail[0].remark=='卡过期'">
              Card expired
              </s:if>
               <s:if test="tradeDetail[0].remark=='卡号有误'">
              Wrong number
              </s:if>
               <s:if test="tradeDetail[0].remark=='零金额'">
             Zero sum
              </s:if>
               <s:if test="tradeDetail[0].remark=='没收（黑卡）'">
              Confiscate the card
              </s:if>
               <s:if test="tradeDetail[0].remark=='商户要求取消'">
              Merchants to cancel
              </s:if>
               <s:if test="tradeDetail[0].remark=='无此卡号'">
             Without this card number
              </s:if>
               <s:if test="tradeDetail[0].remark=='月交易量超限'">
             Month volume off-gauge
              </s:if>
               <s:if test="tradeDetail[0].remark=='中风险！'">
             medium risk
              </s:if>
               <s:if test="tradeDetail[0].remark=='重复交易'">
              Repeat dealings
              </s:if>
              <s:else>
               <s:property value="tradeDetail[0].remark"/>
               </s:else>
              </span></div>     
              <div class="jiben"><span><b>Merchandise Information：</b></span></div>
              <div class="jiben_wupin">
                <span><s:property value="tradeDetail[3].productInfo"/></span>
              </div>
              <div class="jiben"><span><b>BillingAddress：</b></span></div>
              <div class="jiben_wupin">
                <span><s:property value="tradeUrl"/></span>
              </div>              
              <div class="clear"></div>       
           </div>
        
          <!-- <ul class="listlisttop">
             <li class="lil_03"><s:property value="tradeDetail[0].tradeAmount"/></li>  
             <li class="lil_01"><s:property value="states.getCurrencyTypeByNo(tradeDetail[0].moneyType)"/></li>    
             <li class="lil_01"><s:property value="tradeDetail[2]"/></li>   
             <li class="lil_01"><s:property value="states.getStateName(tradeDetail[0].tradeState,1)" escape="false" /></li>   
             <li class="lil_01"><s:property value="states.getStateName(tradeDetail[0].tradeState,4)" escape="false"/></li>    
             <li class="lil_01"><s:property value="states.getStateName(tradeDetail[0].tradeState,2)" escape="false"/></li>    
             <li class="lil_01"><s:property value="states.getStateName(tradeDetail[0].tradeState,3)" escape="false"/></li>    
             <li class="lil_01"><s:property value="states.getStateName(tradeDetail[0].tradeState,8)" escape="false"/></li>    
             <li class="lil_01"><s:property value="states.getStateName(tradeDetail[0].tradeState,5)" escape="false"/></li>    
             <li class="lil_01">
             	<s:if test="tradeDetail[0].isTrackNo!=null">
             		Yes
             	</s:if>
             	<s:else>
             		No
             	</s:else>
             </li>     
             <li class="lil_02"></li>
           </ul> -->
         </div>
         
       <!--<div class="search3">
         		<ul class="searchtext">
		           <li class="name"><b>Billing Address</b></li>
		           <li class="nameinput"></li>
		        </ul>
         		<ul class="searchtext">
		           <li class="name"><b>Shipping Address</b></li>
		           <li class="nameinput"></li>
		        </ul>
		        <br class="clear" />
         		<ul class="searchtext">
		           <li class="name"><s:property value="tradeDetail[3].zipcode"/></li>
		           <li class="nameinput"></li>
		        </ul>
         		<ul class="searchtext">
		              111<li class="name"><s:property value="tradeDetail[3].shippingZip"/></li>
		           <li class="nameinput"></li>
		        </ul>
		        <br class="clear" />
         		<ul class="searchtext">
		           <li class="name"><s:property value="tradeDetail[3].city"/></li>
		           <li class="nameinput"></li>
		        </ul>
         		<ul class="searchtext">
		           11111<li class="name"><s:property value="tradeDetail[3].shippingCity"/></li>
		           <li class="nameinput"></li>
		        </ul>
		        <br class="clear" />
         		<ul class="searchtext">
		           <li class="name"><s:property value="tradeDetail[3].state"/></li>
		           <li class="nameinput"></li>
		        </ul>
         		<ul class="searchtext">
		          111111 <li class="name"><s:property value="tradeDetail[3].shippingState"/></li>
		           <li class="nameinput"></li>
		        </ul>
		        <br class="clear" />
         		<ul class="searchtext">
		           <li class="name"><s:property value="tradeDetail[3].address"/></li>
		           <li class="nameinput"></li>
		        </ul>
         		<ul class="searchtext">
		           1111111<li class="name"><s:property value="tradeDetail[3].shippingAddress"/></li>
		           <li class="nameinput"></li>
		        </ul>
		        <br class="clear" />
         		<ul class="searchtext">
		           <li class="name"><s:property value="tradeDetail[3].country"/></li>
		           <li class="nameinput"></li>
		        </ul>
         		<ul class="searchtext">
		           111111<li class="name"><s:property value="tradeDetail[3].shippingCountry"/></li>
		           <li class="nameinput"></li>
		        </ul>
		        <br class="clear" />
         		<ul class="searchtext">
		           <li class="name"><s:property value="tradeDetail[3].firstName"/>&nbsp;<s:property value="tradeDetail[3].lastName"/> </li>
		           <li class="nameinput"></li>
		        </ul>
         		<ul class="searchtext">
		           111111<li class="name"><s:property value="tradeDetail[3].shippingFullName"/></li>
		           <li class="nameinput"></li>
		        </ul>
		        <br class="clear" />
         		<ul class="searchtext">
		           <li class="name"><s:property value="tradeDetail[3].email"/></li>
		           <li class="nameinput"></li>
		        </ul>
         		<ul class="searchtext">
		           1111111<li class="name"><s:property value="tradeDetail[3].shippingEmail"/></li>
		           <li class="nameinput"></li>
		        </ul>
		        <br class="clear" />
         		<ul class="searchtext">
		           <li class="name"><s:property value="tradeDetail[3].phone"/></li>
		           <li class="nameinput"></li>
		        </ul>
         		<ul class="searchtext">
		           1111111<li class="name"><s:property value="tradeDetail[3].shippingPhone"/></li>
		           <li class="nameinput"></li>
		        </ul>
         </div> --> 
         <!--<div>
         		<ul>
		           <li><b>物品信息</b></li>
		           <li></li>
		        </ul>
         		<ul>
		           <li><s:property value="tradeDetail[3].productInfo"/></li>
		           <li></li>
		        </ul>
         </div> -->
         <!--<div>
         		<ul>
		           <li><b>备注信息</b></li>
		           <li></li>
		        </ul>
         		<ul>
		           <li>Payment failed</li>
		           <li></li>
		        </ul>
         </div> -->
       </div>
     </div>
   </div>
   
   
   