<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>

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
		
	function detialinfo(tradeid){
		window.showModalDialog ('../merchanten/viewMerTradeDetailEn.action?tradeId='+tradeid, window,'dialogHeight:380px;dialogWidth:680px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
	}
</script>
</head>
<body>
<!--头部begin-->
<s:action name="indexMenuEn" executeResult="true" />
<!--头部end-->
<s:form name="formu" id="formu" action="merTradeQueryListEn" method="post" theme="simple">
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
           <li class="selectinput"><s:select name="ispiccher" list="#@java.util.TreeMap@{'0':'未上传','1':'已上传'}"  headerKey="" headerValue="----" /></li>
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
           <li>
           		<input type="image" src="images/search.gif" onclick="chaxun()" />
           </li>
         </ul>
       </div>
       <div class="clear">&nbsp;</div>
        <div><img src="images/division.gif" alt="" /></div>
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">Merchant Transactions</li>
             <li class="lilistimg">		<input type="image" src="images/download.gif" onclick="exportInfo()" /> </li>
           </ul>
           <ul class="bottom">
             <li class="li_07">Index</li>  
             <li class="li_08">Order No.</li>
             <li class="li_08">Merchant Order No.</li>
             <li class="li_09">Amount</li>
             <li class="li_10">Date</li>  
             <li class="li_07">Status</li> 
           </ul>
         </div>
         <div class="listlist">
        <s:iterator id="m" value="info.result" status="s">
           <ul class="listlistbottom">
             <li class="lil_07"><s:property value="#s.index+1"/></li>
             <li class="lil_08"><a href="#" style="color:#000; text-decoration:underline" onclick="detialinfo(<s:property value="#m[6]"/>)"><s:property value="#m[0]" /></a></li>
             <li class="lil_08"><s:property value="#m[1]"/></li>
             <li class="lil_09"><s:property value="#m[3]"/></li>
             <li class="lil_10"><s:property value="#m[2]"/></li>  
             <li class="lil_13">
             <s:property value="states.getStateNameEn(#m[4],1)" escape="false" />
            
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
   
   
   