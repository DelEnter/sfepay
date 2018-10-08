<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>Transactions</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
</style>
<script language="JavaScript" type="text/JavaScript">

	function exportInfo(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("isdownload");
		exportX.value="1";
		formX.submit();
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
	
	function detialinfo(tradeid){
		window.showModalDialog ('../merchanten/viewMerTradeDetailEn.action?tradeId='+tradeid, window,'dialogHeight:380px;dialogWidth:680px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
	}
	
</script>
</head>
<body>
<!--头部begin-->
<s:action name="indexMenuEn" executeResult="true" />
<!--头部end-->
<s:form name="formu" id="formu" action="merTradeQueryEn" method="post" theme="simple">
<input type="hidden" name="isdownload" id="isdownload"/>
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
           <li class="name">Amount</li>
           <li class="nameinput">
			<input type="text" name="minamount" size="6" value="<s:property value='minamount'/>" />-
			<input type="text" name="maxamount" size="6" value="<s:property value='maxamount'/>" />
			</li>
         </ul>
         <br class="clear" />
         
         <ul class="searchselect">
           <li class="selectname">Status</li>
           <li class="selectinput">
           <s:select name="isresult" list="#@java.util.TreeMap@{'0':'Falied','1':'Approved','2':'Proceeding','4':'Pending','5':'NoReturn'}"  headerKey="" headerValue="----" />
           </li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">Refund</li>
           <li class="selectinput"><s:select name="istuikuan" list="#@java.util.TreeMap@{'0':'Unrefund','1':'Refund','2':'Partrefund'}"  headerKey="" headerValue="----" /></li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">Freeze</li>
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
          <ul class="searchselect">
           <li class="selectname">Invoice</li>
           <li class="selectinput"><s:select name="ispiccher" list="#@java.util.TreeMap@{'0':'Yes','1':'No'}"  headerKey="" headerValue="----" /></li>
         </ul>  
         <ul class="searchselect">
           <li class="selectname">Tracking</li>
           <li class="selectinput"><s:select name="istrackno" list="#@java.util.TreeMap@{'0':'Not filled','1':'completed'}"  headerKey="" headerValue="----" /></li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">Check</li>
           <li class="selectinput"><s:select name="isgoudui" list="#@java.util.TreeMap@{'0':'UnCheck','1':'Checked'}"  headerKey="" headerValue="----" /></li>
         </ul>   
         <ul class="searchselect">
           <li class="selectname">&nbsp;</li>
           <li class="selectinput">&nbsp;</li>
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
         <ul class="searchtext">
           <li class="name">Trade website</li>
           <li class="nameinput"><input type="text"  /></li>
         </ul>
         <ul class="searchbutton">
           <li><input type="image" src="images/search.gif" onclick="chaxun()" /></a></li>
         </ul>
       </div>
       <div class="clear">&nbsp;</div>
         <div><img src="images/division.gif" alt="" /></div>
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">Merchant Transactions</li>
             <s:set name="totala" value="0"/>
             <s:set name="currencyy"/>
             <li class="lilistother">
             		
					Total：&nbsp;
				<s:iterator value="totalAmount" id="total">
				<s:if test="#total[0]!=0">
					<font color="red"><s:property value="#total[0]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#total[1])"/></font>
				</s:if>
				<s:else>
					0.00
				</s:else>
			</s:iterator>&nbsp;&nbsp;&nbsp;&nbsp;
			Successful：&nbsp;<s:iterator value="successfulAmount" id="successful">
				<s:if test="#total[0]!=0">
					<font color="red"><s:property value="#successful[0]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#successful[1])"/></font>
				</s:if>
				<s:else>
					0.00
				</s:else>
			</s:iterator>&nbsp;&nbsp;&nbsp;&nbsp;
			Failed：&nbsp;<s:iterator value="failedAmount" id="failed">
				<s:if test="#total[0]!=0">
					<font color="red"><s:property value="#failed[0]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#failed[1])"/></font>
				</s:if>
				<s:else>
					0.00
				</s:else>
			</s:iterator>
				</li>
             <li class="lilistimg"><input type="image" src="images/download.gif" onclick="exportInfo()" /></li>
           </ul>
           <ul class="bottom">
             <li class="li_03">Amount</li>  
             <li class="li_03">Currency</li>  
             <li class="li_03">Channel</li> 
             <li class="li_03">Status</li> 
             <li class="li_03">Freeze</li> 
             <li class="li_03">Refund</li>  
             <li class="li_04">ChargeBack</li>  
             <li class="li_04">Settlement</li>  
             <li class="li_03">Check</li>  
             <li class="li_03">Tracking</li>         
             <li class="li_03">Operate</li>
             <li class="li_03"></li>
           </ul>
         </div>
         <div class="listlist">
        <s:iterator id="m" value="info.result" status="s">
           <ul class="listlisttop">
             <li>Order No.：<s:property value="#m[0]" /></li>
             <li>Merchant Order No.：<s:property value="#m[1]"/> </li>
             <li>Trade Date ：<s:property value="#m[3]"/></li>
           </ul>
           <ul class="listlistbottom">
             <li class="lil_03"><s:property value="#m[4]"/></li>  
             <li class="lil_03"><s:property value="states.getCurrencyTypeByNo(#m[5])"/></li>    
             <li class="lil_03"><s:property value="#m[7]"/></li>   
             <li class="lil_03"><s:property value="states.getStateNameEn(#m[8],1)" escape="false" /></li>   
             <li class="lil_03"><s:property value="states.getStateNameEn(#m[8],4)" escape="false"/></li>    
             <li class="lil_04"><s:property value="states.getStateNameEn(#m[8],2)" escape="false"/></li>    
             <li class="lil_04"><s:property value="states.getStateNameEn(#m[8],3)" escape="false"/></li>    
             <li class="lil_03"><s:property value="states.getStateNameEn(#m[8],8)" escape="false"/></li>    
             <li class="lil_03"><s:property value="states.getStateNameEn(#m[8],5)" escape="false"/></li>    
             <li class="lil_03">
             	<s:if test="#m[11]!=null">
             		Yes
             	</s:if>
             	<s:else>
             		No
             	</s:else>
             </li>     
             <li class="lil_03"><img src="images/more_icon.gif" alt="See details" onclick="detialinfo(<s:property value="#m[13]"/>)"/></li>
             <li class="lil_03">
             	<s:if test="#m[14]!=#m[15]">
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
   
   
   