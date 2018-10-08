<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>

<html>
  <head>

    <title>Settlement History</title>
	<link rel="stylesheet" type="text/css" href="css/bail.css">
	 <%@ include file="../include/dialog.jsp"%>
    <style>
      td,tr,table{ font-size:12px;}
    </style>
	<!-- 全选组件 -->
    <%@ include file="../include/checkAll.jsp" %>

	<SCRIPT language=JavaScript>
	
    <!-- 检验是否选上需要处理的选项 -->
	function check(){
		var workorderObjectNos = document.getElementsByName('freezeId');
		var gets = new Array();
		var k = 0;
		var result = 0;
		for(var i=0; i<workorderObjectNos.length; i++){
			if(workorderObjectNos[i].checked){
			  // alert("第" + i + "个值：" + workorderObjectNos[i].value);
			    gets[k] = workorderObjectNos[i].value;
			    result =  gets[k];
   				k++;
			}
 		}
 		if(result==0){
			alert("请选上勾兑的选项！");
		}else{
			form2.submit();
		}
	}
  	function openDetail(merchId){
     
     var str ='showMerMingxiEn?batchNo='+merchId ;

	 window.open(str,'View') 
	}
	
	function openView(beatno,merchid){
	  var str ='merchantShowViewen?batchNo='+beatno+'&merchantId='+merchid;

	 window.open(str,'View') 
	
	}   

	</script>   
   
   
</SCRIPT>
  </head>
  
  <body>
  <!--头部begin-->
  

  
  
<s:action name="indexMenuEn" executeResult="true"/>  
  	<s:form id="form1" action="toSettlementhisEn" theme="simple" method="post">
    
    <div class="mainbody">
    
       <div class="search">
         <ul class="searchtext">
           <li class="name">Batch No.</li>
           <li class="nameinput"><s:textfield name="batchNo" value=""/></li>
         </ul>
         <ul class="searchtext">
           <li class="name">Starting Date</li>
           <li class="nameinput"><input id="start_time" type="text" name="startDate" size="15" value="<s:property value='startDate'/>"/></li>
         </ul>
         <ul class="searchtext">
           <li class="name">Ending Date</li>
           <li class="nameinput"><input id="end_time" type="text" name="endDate" size="15" value="<s:property value='endDate'/>"/>	</li>
         </ul>
         
         <ul class="searchbutton2">
           <li><input type="submit" value="Confirm" class="input_button_01" />&nbsp;<input type="reset" class="input_button_01"  value="Cancel" /></li>
         </ul>

       </div>


        <div><img src="images/division.gif" alt="" /></div>
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">View Settlement Form</li>
           </ul>
           <ul class="bottom">
             <li class="li_05">Batch No.</li>
             <li class="li_03">Amount</li>
             <li class="li_04">Settlement Amount</li>
             <li class="li_04">View</li>
             <li class="li_03">Details</li>
             <li class="li_05">Settlement Date</li>
             <li class="li_04">Remark</li>
           </ul>
         </div>
         <div class="listlist">
         <s:iterator id="huaKuanId" value="info.result">
           <ul class="listlistbottom"> 
             <li class="lil_05"><s:property value="#huaKuanId[0].batchno"/></li>
             <li class="lil_03"><s:property value="#huaKuanId[0].ordercount"/></li>    
             <li class="lil_04"><s:property value="#huaKuanId[0].refundmentmoney"/></li>
             <li class="lil_04"><a onClick="openView('<s:property value='#huaKuanId[0].batchno'/>','<s:property value='#huaKuanId[0].merchantno'/>');" href="#">View Settlement Form</a></li>
             <li class="lil_03"><a onClick="openDetail('<s:property value='#huaKuanId[0].batchno'/>')" href="#">Details</a></li>
             <li class="lil_05" ><s:property value="#huaKuanId[0].createtabletime"/></li>
             
             <s:if test="#huaKuanId[0].remark==null">

	  				</s:if>
	  				<s:else>
                        <li class="lil_04" onmouseover="this.style.backgroundColor=''" 
						style="FONT-FAMILY: Verdana, Arial, Helvetica, sans-serif" 
						onmouseout="this.style.backgroundColor=''" align=right><s:property value="#huaKuanId[0].remark.substring(3)"/>.....
						</li>
	  				</s:else>
           </ul>
		</s:iterator>	
           <ul class="listlistpage">
             <li></li>
           </ul>
           
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
 </body>
</html>
