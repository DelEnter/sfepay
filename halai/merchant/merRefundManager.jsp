<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>退款管理</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
</style>
</head>
<script language="JavaScript" type="text/JavaScript">

	function exportInfo(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("exportX");
		exportX.value="1";
		formX.submit();
	}
	
	function chaxun(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("exportX");
		exportX.value="";
		formX.submit();
	}
	
	function cancelRefund(refundid){
		if(confirm("确定要取消退款吗?")){
			window.location="../merchant/deleteRefund.action?refundId="+refundid;
		}
	}
	
</script>
<body>
<!--头部begin-->
<s:action name="indexMenu" executeResult="true" />
<!--头部end-->
<s:form name="formu" id="formu" action="refundManager" method="post" theme="simple">
<s:hidden name="exportX" id="exportX"/>
    <div class="mainbody">
       <div class="search">
         <ul class="searchtext">
           <li class="name">商户订单号</li>
           <li class="nameinput"><input type="input" name="merchantOrderNo" value="<s:property value='merchantOrderNo'/>"/></li>
         </ul>
         <ul class="searchtext">
           <li class="name">交易流水订单号</li>
           <li class="nameinput"><input type="input" name="orderNo" value="<s:property value='orderNo'/>"/></li>
         </ul>
         <br class="clear" />
         <ul class="searchtext">
           <li class="name">开始日期</li>
           <li class="nameinput">
 		   <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>

           </li>
         </ul>
         <ul class="searchtext">
           <li class="name">结束日期</li>
           <li class="nameinput">
			<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>

           </li>
         </ul>
        
         <ul class="searchselect">
           <li class="selectname">&nbsp;</li>
           <li class="selectinput">&nbsp;</li>
         </ul>
         <ul class="searchbutton">
           <li><a href="#" onclick="chaxun()"><img src="images/search.gif" alt="开始搜索" /></a></li>
         </ul>
       </div>
       <div><img src="images/division.gif" alt="" /></div>
       
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">退款管理查询</li>
          <li class="lilistimg"><a href="#" onclick="exportInfo()"><img src="images/download.gif" alt="下载交易" /></a></a></li>
           </ul>
           <ul class="bottom">
             <li class="li_01">序列</li>  
             <li class="li_08">流水号</li>  
             <li class="li_10">商户订单号</li> 
             <li class="li_12">交易日期</li> 
             <li class="li_12">申请日期</li> 
             <li class="li_04">交易金额</li>  
             <li class="li_04">退款金额</li>  
             <li class="li_03">退款状态</li>  
             <li class="li_01">操作</li>  
           </ul>
         </div>
         <div class="listlist">
         <s:iterator id="p" value="info.result" status="s">
           <ul class="listlistbottom">
             	<li class="lil_01"><s:property value="#s.index+1" /></li>  
                <li class="lil_08"><s:property value="#p[1].orderNo" /></li> 
				<li class="lil_10"><s:property value="#p[1].merchantOrderNo" /></li> 
				<li class="lil_12"><s:property value="#p[1].tradeTime" /></li> 
				<li class="lil_12"><s:property value="#p[0].applyDate" /></li> 
				<li class="lil_04"><s:property value="#p[1].tradeAmount" /></li> 
				<li class="lil_04"><s:property value="#p[0].refundAmount" /></li> 
				<li class="lil_03"><s:property value="states.getRefundDetailState(#p[0].refundState)" /></li> 
				<li class="lil_01">
					<s:if test="#p[0].refundState<3">
						<a href="#" onclick="cancelRefund(<s:property value="#p[0].id" />)">取消</a>
					</s:if>
					<s:else>
						&nbsp;
					</s:else>
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
</s:form>
<!-- 下面这段script代码必须放在form体的最后  
loadcalendar方法的五个参数分别解释如下：
1、日期显示文本框的ID号
2、触发日历控件显示的控件ID号
3、要显示的日期格式，%Y表示年，%m表示月，%d表示日
4、是否带周显示，默认是不带
5、是否带时间显示，默认是不带
6、日历显示文字的语言，默认是中文 -->
<script language="javascript" type="text/javascript">

loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "en");
loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "en");

function cleanDate(vid){
	document.getElementById(vid).value="";
}
</script>
<!-- 上面这段script代码必须放在form体的最后 -->
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->
   
   
   