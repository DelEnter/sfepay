<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Merchant Transactions</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
</style>
<script language="JavaScript" type="text/JavaScript">

	function exportInfo(){
document.getElementById("isdownload").value="1";
$("#formu").submit();
	}
	
	function chaxun(){
   document.getElementById("isdownload").value="";
$("#formu").submit();
	}
</script>
</head>
<body>
<!--头部begin-->
<%@ include file="agentshead.jsp"%>
<!--头部end-->
<s:form name="formu" id="formu" action="agentsTradeQuery" method="post" theme="simple">
<input type="hidden" name="isdownload" id="isdownload" value=""/>
    <div class="mainbody">
       <div class="search">
         <ul class="searchtext">
           <li class="name">Merchant Order No.</li>
           <li class="nameinput"><input type="text" name="merchantOrderNo" value="<s:property value='merchantOrderNo'/>" /></li>
         </ul>
         <ul class="searchtext">
           <li class="name">Order No.</li>
           <li class="nameinput"><input type="text" name="orderNo" value="<s:property value='orderNo'/>" /></li>
         </ul>
         <ul class="searchtext">
           <li class="name">Merchant No.</li>
           <li class="nameinput"><s:select name="merchantId" list="meridList" listKey="id" listValue="merno" headerKey="" headerValue="----" /></li>
         </ul>
         <br class="clear" />
         <ul class="searchselect">
           <li class="selectname">Status</li>
           <li class="selectinput">
           <s:select name="isresult" list="#@java.util.TreeMap@{'0':'Falied','1':'Approved','2':'Proceeding'}"  headerKey="" headerValue="----" />
           </li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">Refund</li>
           <li class="selectinput"><s:select name="istuikuan" list="#@java.util.TreeMap@{'0':'Unrefund','1':'Refund','2':'Partrefund'}"  headerKey="" headerValue="----" /></li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">Freeze </li>
           <li class="selectinput"><s:select name="isdongjie" list="#@java.util.TreeMap@{'0':'Unfrozen','1':'Frozen','2':'Unfreeze'}"  headerKey="" headerValue="----" /></li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">ChecgeBack</li>
           <li class="selectinput"><s:select name="isjufu" list="#@java.util.TreeMap@{'0':'NO','1':'YES'}"  headerKey="" headerValue="----" /></li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">Settlement</li>
           <li class="selectinput"><s:select name="ishuakuan" list="#@java.util.TreeMap@{'0':'NO','1':'YES'}"  headerKey="" headerValue="----" /></li>
         </ul>  
         <ul class="searchtext">
           <li class="name">Start Date</li>
           <li class="nameinput"><input id="start_time" type="text" name="startDate" size="15" value="<s:property value='startDate'/>"/>
        </li>
         </ul>
         <ul class="searchtext">
           <li class="name">End Date</li>
           <li class="nameinput"><input type="text" id="end_time" name="endDate" size="15" /></li>
         </ul>
          <br class="clear" />
         <ul class="searchbutton">
           <li><a href="#" onclick="chaxun()"><img src="images/search.gif" alt="search" /></a></li>
         </ul>
       </div>
       <!--<div><img src="images/division.gif" alt="" /></div> -->
       
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">Merchant Transactions </li>
             <s:set name="totala" value="0"/>
             <s:set name="currencyy"/>
            <s:iterator id="a" value="amountStatistic" status="s">
             <li class="lilistother">
             	<s:if test="#a[0]!=null && #a[0]==0">
	             	Failed：<font class="font_color_01">
             			<s:if test="#a[1]!=0">
	             			<s:property value="#a[1]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#a[2])"/>
	             			<s:set name="totala" value="#totala+#a[1]"/>
	             			<s:set name="currencyy" value="states.getCurrencyTypeByNo(#a[2])"/>
             			</s:if>
					</font>
             	</s:if>
             	<s:if test="#a[0]==1">
	             	Successful：<font class="font_color_01">
             			<s:if test="#a[1]!=0">
	             			<s:property value="#a[1]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#a[2])"/>
	             			<s:set name="totala" value="#totala+#a[1]"/>
	             			<s:set name="currencyy" value="states.getCurrencyTypeByNo(#a[2])"/>
             			</s:if>
             			
					</font>
             	</s:if>
					
				</li>
            </s:iterator>
           	<li class="lilistother">Total：：<font class="font_color_01"><s:property value="#totala"/>&nbsp;
           		<s:if test="#totala!=0">
           			<s:property value="#currencyy"/>
           		</s:if>	
           			</font></li> 
             <li class="lilistimg"><a href="#" onclick="exportInfo()"><img src="images/download.gif" alt="DownLoad" /></a></a></li>
           </ul>
           <ul class="bottom">
             <li class="li_03">Amount</li>  
             <li class="li_03">Currency</li>  
             <li class="li_03">Channel</li> 
             <li class="li_03">Status</li> 
             <li class="li_03">Freeze</li> 
             <li class="lil_04">Refund</li>  
             <li class="lil_04">ChargeBack</li>  
             <li class="lil_04">Settlement</li>  
             <li class="li_03">Check</li>  
             <li class="li_03">Tracking</li>         
             <li class="li_03">Operate</li>
           </ul>
         </div>
         
         <div class="listlist">
        <s:iterator id="m" value="info.result" status="s">
           <ul class="listlisttop">
             <li>Order No.：<s:property value="#m[0].orderNo" /></li>
             <li>Merchant Order No.：<s:property value="#m[0].merchantOrderNo"/> </li>
             <li>Trade Date ：<s:property value="#m[0].tradeTime"/></li>
           </ul>
           <ul class="listlistbottom">
             <li class="lil_03"><s:property value="#m[0].tradeAmount"/></li>  
             <li class="lil_03"><s:property value="states.getCurrencyTypeByNo(#m[0].moneyType)"/></li>    
             <li class="lil_03"><s:property value="#m[3]"/></li>   
             <li class="lil_03"><s:property value="states.getStateNameEn(#m[0].tradeState,1)" escape="false" /></li>   
             <li class="lil_03"><s:property value="states.getStateNameEn(#m[0].tradeState,4)" escape="false"/></li>    
             <li class="lil_04"><s:property value="states.getStateNameEn(#m[0].tradeState,2)" escape="false"/></li>    
             <li class="lil_04"><s:property value="states.getStateNameEn(#m[0].tradeState,3)" escape="false"/></li>    
             <li class="lil_04"><s:property value="states.getStateNameEn(#m[0].tradeState,8)" escape="false"/></li>    
             <li class="lil_03"><s:property value="states.getStateNameEn(#m[0].tradeState,5)" escape="false"/></li>    
             <li class="lil_03">
             	<s:if test="#m[0].isTrackNo!=null">
             		YES
             	</s:if>
             	<s:else>
             		NO
             	</s:else>
             </li>     
             <li class="lil_03"><img src="images/more_icon.gif" alt="" /></li>
             <li class="lil_03">
             	<s:if test="#m[4]!=#m[5]">
             		<img src="images/icon_05.gif" alt="Shipping address and billing address is inconsistent" />
             	</s:if>
             </li>
           </ul>
            </s:iterator>
           <ul class="listlistpage">
             <li><pages:pages value="info" beanName="info" formName="forms(0)" /></li>
           </ul>
           
         </div>
       </div>
     </div>
   </div>
   
   <!-- 下面这段script代码必须放在form体的最后  
             loadcalendar方法的五个参数分别解释如下：
             1、日期显示文本框的ID号
             2、触发日历控件显示的控件ID号
             3、要显示的日期格式，%Y表示年，%m表示月，%d表示日
             4、是否带周显示，默认是不带
             5、是否带时间显示，默认是不带
             6、日历显示文字的语言，默认是中文 -->
        <script language="javascript" type="text/javascript">

            loadcalendar('start_time', 'start_time', '%Y-%m-%d %H:%M:%S', false, true, "cn");
            loadcalendar('end_time', 'end_time', '%Y-%m-%d %H:%M:%S', false, true, "cn");
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
   </s:form>
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->
   
   
   