<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
 <%@ include file="../include/dialog.jsp"%>
 
<script language="JavaScript" src="../js/util.js"></script>
<style>
body,table,tr,td{ font-size:12px;}
</style>
<head>
	<title>商户拒付处理</title>
</head>
<script language="JavaScript" type="text/JavaScript">
	function reloadPage(){
        window.location="../merchant/merChargeBackQuery";
	}	
	function totijiaoshensu(chargebackid){

		window.open('../merchant/toMerSubmitCom.action?chargeBackId='+chargebackid,'newwindow','menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=1,resizable=1,height=300,width=600')
	}
	
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
 <!--头部begin-->
<s:action name="indexMenu" executeResult="true" />
<!--头部end-->
<s:form name="formu" id="formu" action="merChargeBackQuery" method="post" theme="simple">
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
    
   
    <ul class="searchtext">
      <li class="name">开始日期</li>
      <li class="nameinput"><input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>
   </li>
    </ul>
    <ul class="searchtext">
      <li class="name">结束日期</li>
      <li class="nameinput"><input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/></li>
    </ul>
    <ul class="searchbutton">
      <li>
      		<input type="image" src="images/search.gif" onclick="chaxun()" />
      </li>
    </ul>
  </div>
       <div><img src="images/division.gif" alt="" /></div>
       
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">拒付交易查询</li>
             <li style="color:red">注意:如果您需要申诉或其他操作请联系客服</li>
             <li class="lilistother"></li>
          <li class="lilistimg"><a href="#" onclick="exportInfo()"><img src="images/download.gif" alt="下载交易" /></a></a></li>
           </ul>
           <ul class="bottom">
             <li class="li_01">序列</li>  
             <li class="li_08">流水号</li>  
             <li class="li_05">商户订单号</li> 
             <li class="li_06">金额</li> 
             <li class="li_04">拒付日期</li>
             <li class="li_04">交易日期</li>
             <li class="li_03">是否拒付</li>        
             <li class="li_05">拒付理由</li> 
             <!--  <li class="li_07">操作</li>--> 
           </ul>
         </div>
         <div class="listlist">
         <s:iterator id="p" value="info.result" status="s">
           <ul class="listlistbottom">
             <li class="lil_01"><s:property value="#s.index+1" /></li>  
             <li class="lil_08"><s:property value="#p[1].orderNo" /></li>    
			 <li class="lil_05"><s:property value="#p[1].merchantOrderNo" /></li>    
			 <li class="lil_06"><s:property value="#p[1].tradeAmount"/></li>    
			 <li class="lil_04"><s:property value="#p[0].importDate" /></li>
			 <li class="lil_04"><s:property value="#p[1].tradeTime" /></li>
			 <li class="lil_03"><s:property value="states.getStateName(#p[1].tradeState,3)" escape="false" /></li>  
			 <li class="lil_05"><s:property value="#p[0].remark" /></li>    
			 <!--  <li class="lil_07">提交客服-->
			 
			<!-- <s:if test="#p[0].isChargeBack==0">
				<a href="#" onclick="totijiaoshensu(<s:property value="#p[0].id" />)">申诉</a>
			</s:if>
			<s:elseif test="#p[0].isChargeBack==1">
				已提交申诉
			</s:elseif>
			<s:else>
				已放弃申诉
			</s:else>-->
			 
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

            loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "cn");
            loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "cn");
            
            function cleanDate(vid){
            	document.getElementById(vid).value="";
            }
        </script>
        <!-- 上面这段script代码必须放在form体的最后 -->
</s:form>
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->










