<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
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
		formX.submit();
	}
	
	function chaxun(){
		var formX = document.getElementById("formu");
		var exportX = document.getElementById("isdownload");
		exportX.value="";
		formX.submit();
	}
</script>
</head>
<body>
<!--头部begin-->
<%@ include file="agentshead.jsp"%>
<!--头部end-->
<s:form name="formu" id="formu" action="agentsTradeQuery" method="post" theme="simple">
<input type="hidden" name="isdownload"/>
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
         <ul class="searchtext">
           <li class="name">商户号</li>
           <li class="nameinput"><s:select name="merchantId" list="meridList" listKey="id" listValue="merno" headerKey="" headerValue="----" /></li>
         </ul>
         <br class="clear" />
         <ul class="searchselect">
           <li class="selectname">支付情况</li>
           <li class="selectinput">
           <s:select name="isresult" list="#@java.util.TreeMap@{'0':'失败','1':'成功','2':'待处理'}"  headerKey="" headerValue="----" />
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
         <ul class="searchtext">
           <li class="name">开始日期</li>
           <li class="nameinput"><input id="start_time" type="text" name="startDate" size="15" value="<s:property value='startDate'/>"/>
        </li>
         </ul>
         <ul class="searchtext">
           <li class="name">结束日期</li>
           <li class="nameinput"><input type="text" id="end_time" name="endDate" size="15" /></li>
         </ul>
          <br class="clear" />
         <ul class="searchbutton">
           <li><a href="#" onclick="chaxun()"><img src="images/search.gif" alt="开始搜索" /></a></li>
         </ul>
       </div>
       <!--<div><img src="images/division.gif" alt="" /></div> -->
       
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">商户交易查询</li>
             <s:set name="totala" value="0"/>
             <s:set name="currencyy"/>
            <s:iterator id="a" value="amountStatistic" status="s">
             <li class="lilistother">
             	<s:if test="#a[0]!=null && #a[0]==0">
	             	失败：<font class="font_color_01">
             			<s:if test="#a[1]!=0">
	             			<s:property value="#a[1]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#a[2])"/>
	             			<s:set name="totala" value="#totala+#a[1]"/>
	             			<s:set name="currencyy" value="states.getCurrencyTypeByNo(#a[2])"/>
             			</s:if>
					</font>
             	</s:if>
             	<s:if test="#a[0]==1">
	             	成功：<font class="font_color_01">
             			<s:if test="#a[1]!=0">
	             			<s:property value="#a[1]"/>&nbsp;<s:property value="states.getCurrencyTypeByNo(#a[2])"/>
	             			<s:set name="totala" value="#totala+#a[1]"/>
	             			<s:set name="currencyy" value="states.getCurrencyTypeByNo(#a[2])"/>
             			</s:if>
             			
					</font>
             	</s:if>
					
				</li>
            </s:iterator>
           	<li class="lilistother">总交易金额：<font class="font_color_01"><s:property value="#totala"/>&nbsp;
           		<s:if test="#totala!=0">
           			<s:property value="#currencyy"/>
           		</s:if>	
           			</font></li> 
             <li class="lilistimg"><a href="#" onclick="exportInfo()"><img src="images/download.gif" alt="下载交易" /></a></a></li>
           </ul>
           <ul class="bottom">
             <li class="li_03">金额</li>  
             <li class="li_03">币种</li>  
             <li class="li_03">通道</li> 
             <li class="li_03">状态</li> 
             <li class="li_03">冻结</li> 
             <li class="li_05">退款</li>  
             <li class="li_03">拒付</li>  
             <li class="li_03">划款</li>  
             <li class="li_03">勾兑</li>  
             <li class="li_03">传号</li>         
             <li class="li_03">操作</li>
           </ul>
         </div>
         
         <div class="listlist">
        <s:iterator id="m" value="info.result" status="s">
           <ul class="listlisttop">
             <li>流水订单号：<s:property value="#m[0].orderNo" /></li>
             <li>订单号：<s:property value="#m[0].merchantOrderNo"/> </li>
             <li>交易日期 ：<s:property value="#m[0].tradeTime"/></li>
           </ul>
           <ul class="listlistbottom">
             <li class="lil_03"><s:property value="#m[0].tradeAmount"/></li>  
             <li class="lil_03"><s:property value="states.getCurrencyTypeByNo(#m[0].moneyType)"/></li>    
             <li class="lil_03"><s:property value="#m[3]"/></li>   
             <li class="lil_03"><s:property value="states.getStateName(#m[0].tradeState,1)" escape="false" /></li>   
             <li class="lil_03"><s:property value="states.getStateName(#m[0].tradeState,4)" escape="false"/></li>    
             <li class="lil_05"><s:property value="states.getStateName(#m[0].tradeState,2)" escape="false"/></li>    
             <li class="lil_03"><s:property value="states.getStateName(#m[0].tradeState,3)" escape="false"/></li>    
             <li class="lil_03"><s:property value="states.getStateName(#m[0].tradeState,8)" escape="false"/></li>    
             <li class="lil_03"><s:property value="states.getStateName(#m[0].tradeState,5)" escape="false"/></li>    
             <li class="lil_03">
             	<s:if test="#m[0].isTrackNo!=null">
             		是
             	</s:if>
             	<s:else>
             		否
             	</s:else>
             </li>     
             <li class="lil_03"><img src="images/more_icon.gif" alt="" /></li>
             <li class="lil_03">
             	<s:if test="#m[4]!=#m[5]">
             		<img src="images/icon_05.gif" alt="发货地址与账单地址不一致" />
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
   
   
   