
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd"><%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>

<html>
  <head>
    
    <title>View uploaded tracking NO</title>
	<link rel="stylesheet" type="text/css" href="../css/head.css">
	<%@ taglib prefix="pages" uri="/xs-pages"%>
	  <%@ include file="../include/dialog.jsp"%>
	  <script language="JavaScript" src="../js/util.js"></script>
	<script language="javascript">
		function detialinfo(tradeid){
			//window.open ('toUploadNumber.action?trade.id='+tradeid,'newwindow','menubar=no,toolbar=no,location=no,directories=no,status=no,scrollbars=1,resizable=1,height=300,width=600') 
			window.showModalDialog('toUploadNumberEn.action?trade.id='+tradeid, window,'dialogHeight:325px;dialogWidth:650px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
		}
		
		function chaxun(){
			var formX = document.getElementById("form1");
			var exportX = document.getElementById("isdownload");
			exportX.value="";
			formX.submit();
		}
		function exportInfo(){
			var formX = document.getElementById("form1");
			var exportX = document.getElementById("isdownload");
			exportX.value="1";
			formX.submit();
		}
		function detialinfo2(tradeid){
			window.showModalDialog ('../merchanten/viewMerTradeDetailEn.action?tradeId='+tradeid, window,'dialogHeight:380px;dialogWidth:680px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
		}
		function toimpot(){
			window.showModalDialog ('../merchanten/toImportTrackingEn.action', window,'dialogHeight:380px;dialogWidth:680px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
		}
	</script>
  </head>
  
  <body>
  <!--头部begin-->
<s:action name="indexMenuEn" executeResult="true"/>  
<s:form action="findUploadNumberEn" method="post" theme="simple" id="form1" name="form1" >
<input type="hidden" name="isdownload" id="isdownload"/>
    <div class="mainbody">
    <div class="search">
    <ul class="searchtext">
      <li class="name">Merchant Order NO</li>
      <li class="nameinput"><input type="text" name="merchantOrderNo" value="<s:property value='merchantOrderNo'/>" /></li>
    </ul>
    <ul class="searchtext">
      <li class="name">Ecpss Order NO</li>
      <li class="nameinput"><input type="text" name="orderNo" value="<s:property value='orderNo'/>" /></li>
    </ul>
    <br class="clear" />
    
   
    <ul class="searchtext">
      <li class="name">Starting Date</li>
      <li class="nameinput"><input id="start_time" type="text" name="startDate" size="15" value="<s:property value='startDate'/>"/>
   </li>
    </ul>
    <ul class="searchtext">
      <li class="name">Ending Date</li>
      <li class="nameinput"><input type="text" id="end_time" name="endDate" size="15" /></li>
    </ul>
    <ul class="searchbutton">
      <li>
      		<input type="image" src="images/search.gif" onclick="chaxun()" />
      </li>
    </ul>
  </div>
  
    <div><img src="images/division.gif" alt="" /></div>
    <div align="center">Please upload batch in the format of the data which is downloaded.</div>
	   <div class="list">
	      <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">View uploaded tracking NO</li>
             <li class="lilistother"><a href="#" onclick="toimpot()">Upload</a></li>
             <li class="lilistimg">
             <input type="image" src="images/download.gif" onclick="exportInfo()" /> </li>
           </ul>
          <ul class="bottom">
		       <li class="li_08">Ecpss Order NO</li>  
		       <li class="li_05">Merchant Order NO</li>    
		       <li class="li_05">Starting Date</li>    
		       <li class="li_03">Amount</li>   
		       <li class="li_05">Payment Status</li>   
		       <li class="li_05">Fill in</li>    
             </ul>
         </div>
		 
		  <div class="listlist">
		    	<s:iterator id="it" value="info.result">
               <ul class="listlistbottom">
                <li class="lil_08"><a href="#" style="color:#000; text-decoration:underline" onclick="detialinfo2(<s:property value="#it.id"/>)"><s:property value="#it.orderNo"/></a></li>
		 		<li class="lil_05"><s:property value="#it.merchantOrderNo"/></li>
		 		<li class="lil_05"><s:property value="#it.tradeTime"/></li>
		 		<li class="lil_03"><s:property value="#it.tradeAmount"/></li>
		 		<li class="lil_05"><s:property value="states.getStateNameEn(#it.tradeState,1)" escape="false" /></li>
		 		<li class="lil_05">
		 		<s:if test="#it.isTrackNo!=null">
   					<s:property value="#it.isTrackNo"/>
   				</s:if>
   				<s:else>
   					<a href="#" onClick="detialinfo(<s:property value="#it.id"/>)"><img src="images/tianxie.gif" alt="填写" title="填写" /></a>
   				</s:else></li>
		
               </ul>
		     </s:iterator>
			  <br class="clear" />
	       <ul class="listlistpage">
		 <li><pages:pages value="info" beanName="info"
						formName="getElementById('form1')" /></li>
	       </ul>
		   </div>
     </div>
   </div>
   <div align="center">
   	 <h4><span style="color:red">Note (uploaded tracking a single number can not be modified</span></h4>
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
   loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "cn");
   loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "cn");
</script>
<!-- 上面这段script代码必须放在form体的最后 -->
     <!--尾部begin-->
	<%@ include file="../merchanten/foot.jsp"%>
  </body>
</html>
