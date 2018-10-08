<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<title>划款提交申请</title>
<link href="css/css.css" rel="stylesheet" type="text/css" />
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
</style>
<!-- 全选组件 -->
<%@ include file="../include/checkAll.jsp" %>
<title>划款提交申请</title>

<link rel="stylesheet" type="text/css" href="css/style.css">
<style>
 td,tr,table{ font-size:12px;}
</style>
<SCRIPT language=JavaScript>

<!-- 判断开始是否大于结束时间 -->
function checkTime(){
	var time1= dojo.widget.byId("startTime");
	var startTime = time1.getValue();
	var time2= dojo.widget.byId("endTime");
	var endTime = time2.getValue();
		if(startTime>endTime){
			alert("开始时间大于结束时间!");
		}else{
			form1.submit();
		}
}

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
		alert("请选上要提交的交易！");
	}else{
form2.submit();

	}
}
</SCRIPT>
</head>
<body>
<!--头部begin-->
<s:action name="indexMenu" executeResult="true" />
<!--头部end-->
<s:form name="form2" action="applyMerSettlement" method="post" theme="simple">
    <div class="mainbody">
       <div class="clear">&nbsp;</div>
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">提交划款申请</li>
             <li class="lilistimg"></li>
           </ul>
           <ul class="bottom">       
           <li class="li_03"><input type="checkbox" onclick='chkall("form2",this)' name=chk></li> 
	       <li class="li_05">交易金额</li> 
	       <li class="li_04">状态</li>   
	       <li class="li_04">是否退款</li>   
	       <li class="li_04">是否冻结</li>    
           <li class="li_04">是否拒付</li>    
           <li class="li_04">是否勾兑</li>    
           <li class="li_05">是否上传跟踪号</li>    
         </ul>
         </div>
         <div class="listlist">
         <s:iterator id="gouDuiList" value="tradeList">
         <ul class="listlisttop">
     		 <li>流水订单号：<s:property value="#gouDuiList[0].orderNo"/></li>
     		 <li>订单号：<s:property value="#gouDuiList[0].merchantOrderNo"/></li>
      		 <li>交易日期：<s:property value="#gouDuiList[0].tradeTime"/></li>
    		</ul>
    		<ul class="listlistbottom">
      		 <li class="lil_03"><input type="checkbox" name="freezeId" value="<s:property value="#gouDuiList[0].id"/>"></li>  
      		 <li class="lil_05"><s:property value="#gouDuiList[0].tradeAmount"/></li>    
     		 <li class="lil_04">
                      <s:property value="states.getStateName(#gouDuiList[0].tradeState,1)"  escape="false"/>		
              </li>   
     		 <li class="lil_04">
     		 	<s:if test="#gouDuiList[0].tradeState.substring(1,2)==2">
     		 	 <font color="red"><s:property value="#gouDuiList[0].backCount"/></font>
     		 	 </s:if>
     		 	 <s:else>
                      <s:property value="states.getStateName(#gouDuiList[0].tradeState,2)"  escape="false"/>		
              	</s:else>
              </li>   
      		 <li class="lil_04">
                      <s:property value="states.getStateName(#gouDuiList[0].tradeState,4)"  escape="false"/>		
              </li>    
     		 <li class="lil_04">
                       <s:property value="states.getStateName(#gouDuiList[0].tradeState,3)"  escape="false"/>		
              </li>    
     		 <li class="lil_04">
                      <s:property value="states.getStateName(#gouDuiList[0].tradeState,5)"  escape="false"/>		
              </li>    
    		     <li class="lil_05">
                    <s:if test="#gouDuiList[0].isTrackNo==null">
	    					已上传
	    				</s:if>
	    				<s:elseif test="#gouDuiList[0].isTrackNo!=null">
	    					<s:property value="#gouDuiList[0].isTrackNo"/>
	    				</s:elseif>
               </li>    
   		 </ul>
	     </s:iterator>
            <ul class="listlistpage">
             <li><input type="button" value="确&nbsp;定" class="input_button_01" onClick="check()"/>&nbsp;&nbsp;<input type="reset" class="input_button_01" value="取&nbsp;消" /></li>
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
   
   
   