<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>交易查询</title>
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
		document.getElementById('submitse').style.visibility="hidden";
		document.getElementById('process').style.visibility="visible";
	}
	function cancelRefund(refundid){
		window.location="../PaySystem/cancelRefund.action?refundId="+refundid;
	}
		
	function detialinfo(tradeid){
		window.open ('../merchant/viewMerTradeDetail.action?tradeId='+tradeid) 
	}
</script>
</head>
<body>
<!--头部begin-->
<s:action name="indexMenu" executeResult="true" />
<!--头部end-->
<s:form name="formu" id="formu" action="merTradeQueryList" method="post" theme="simple">
<input type="hidden" name="isdownload" id="isdownload"/>
    <div class="mainbody">
       <div class="search">
         <ul class="searchtext">
           <li class="name">商户订单号</li>
           <li class="nameinput"><input type="text" name="merchantOrderNo" value="<s:property value='merchantOrderNo'/>" /></li>
         </ul>
         <ul class="searchtext">
           <li class="name">交易流水订单号</li>
           <li class="nameinput"><input type="text" name="orderNo" value="<s:property value='orderNo'/>" /></li>
         </ul>
         <br class="clear" />
         
         <ul class="searchselect">
           <li class="selectname">支付情况</li>
           <li class="selectinput">
           <s:select name="isresult" list="#@java.util.TreeMap@{'0':'失败','1':'成功','2':'待处理','4':'待确认','5':'未返回'}"  headerKey="" headerValue="----" />
           </li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">退款情况</li>
           <li class="selectinput"><s:select name="istuikuan" list="#@java.util.TreeMap@{'0':'未退款','1':'全额退款','2':'部分退款'}"  headerKey="" headerValue="----" /></li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">冻结情况 </li>
           <li class="selectinput"><s:select name="isdongjie" list="#@java.util.TreeMap@{'0':'未冻结','1':'冻结','2':'解冻'}"  headerKey="" headerValue="----" /></li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">拒付情况</li>
           <li class="selectinput"><s:select name="isjufu" list="#@java.util.TreeMap@{'0':'未拒付','1':'已拒付'}"  headerKey="" headerValue="----" /></li>
         </ul>
          
         <ul class="searchselect">
           <li class="selectname">划款情况</li>
           <li class="selectinput"><s:select name="ishuakuan" list="#@java.util.TreeMap@{'0':'未划款','1':'已划款'}"  headerKey="" headerValue="----" /></li>
         </ul>  
          <ul class="searchselect">
           <li class="selectname">跟踪单</li>
           <li class="selectinput"><s:select name="ispiccher" list="#@java.util.TreeMap@{'0':'未上传','1':'已上传'}"  headerKey="" headerValue="----" /></li>
         </ul>  
         <ul class="searchselect">
           <li class="selectname">跟踪号</li>
           <li class="selectinput"><s:select name="istrackno" list="#@java.util.TreeMap@{'0':'未填写','1':'已填写'}"  headerKey="" headerValue="----" /></li>
         </ul>
         <ul class="searchselect">
           <li class="selectname">勾兑情况</li>
           <li class="selectinput"><s:select name="isgoudui" list="#@java.util.TreeMap@{'0':'未勾兑','1':'已勾兑'}"  headerKey="" headerValue="----" /></li>
         </ul>   
         <ul class="searchselect">
           <li class="selectname">&nbsp;</li>
           <li class="selectinput">&nbsp;</li>
         </ul>
         <ul class="searchtext">
           <li class="name">开始日期</li>
           <li class="nameinput"><input id="start_time" type="text" name="startDate" size="15" value="<s:property value='startDate'/>"/>
        </li>
         </ul>
         <ul class="searchtext">
           <li class="name">结束日期</li>
           <li class="nameinput"><input type="text" id="end_time" name="endDate" size="15" value="<s:property value='endDate'/>" /></li>
         </ul>
         <ul class="searchtext">
           <li class="name">交易网址 </li>
           <li class="nameinput"><input type="text" name="tradeUrl" value="<s:property value="tradeUrl"/>" /></li>
         </ul>
         <ul class="searchbutton">
           <li>
           	 <div id="submitse" >
            	<input type="image" src="images/search.gif" onclick="chaxun()" /></a>
 		   	</div>
 		   	<div id="process" style="VISIBILITY: hidden;" >
 		   		<img src="https://security.sslepay.com/jsp/ibank/images/loading1.gif" alt="" />
 			</div>
           </li>
         </ul>
       </div>
       <div class="clear">&nbsp;</div>
        <div><img src="images/division.gif" alt="" /></div>
        
         
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">商户交易查询</li>
             <li class="lilistimg">		<input type="image" src="images/download.gif" onclick="exportInfo()" /> </li>
           </ul>
           <ul class="bottom">
             <li class="li_08">流水订单号</li>
             <li class="li_08">商户订单号</li>
             <li class="li_06">金额</li>
             <li class="li_10">日期</li>  
             <li class="li_07">状态</li> 
             <li class="li_05">备注</li>
           </ul>
         </div>
         <div class="listlist">
        <s:iterator id="m" value="info.result" status="s">
			<s:if test="#m[5].indexOf('1093high risk')">
		   <ul class="listlistbottom">
             <li class="lil_08"><a href="#" style="color:#000; text-decoration:underline" onclick="detialinfo(<s:property value="#m[6]"/>)"><s:property value="#m[0]" /></a></li>
             <li class="lil_08"><s:property value="#m[1]"/></li>
             <li class="lil_06"><s:property value="#m[3]"/></li>
             
             <li class="lil_10"><s:property value="#m[2]"/></li>  
             <li class="lil_07">
             <s:property value="states.getStateName(#m[4],1)" escape="false" />
             </li>    
             <li class="lil_05"><s:property value="#m[5]"/></li>
           </ul>
		     </s:if>
            </s:iterator>
            <ul class="listlistpage">
             <li><pages:pages value="info" beanName="info" formName="getElementById('formu')" /></li>
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

            loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "cn");
            loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "cn");
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
   </s:form>
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->
   
   
   