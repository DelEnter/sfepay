<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
  <head>
 <!--  <%@ include file="../include/checkAll.jsp" %> -->
    <!-- 全选组件 -->

    <title>View Abnormal orders</title>
	<link rel="stylesheet" type="text/css" href="css/bail.css">
	<style type="text/css">
	td,tr,table{ font-size:12px;}
	#massage_box{ position:absolute; left:expression((body.clientWidth-350)/2); top:expression((body.clientHeight-200)/2); width:400px;
	height:200px;filter:dropshadow(color=#666666,offx=3,offy=3,positive=2); z-index:2; visibility:hidden}
	#mask{ position:absolute; top:0; left:0; width:expression(body.scrollWidth); height:expression(body.scrollHeight); filter:ALPHA(opacity=60); z-index:1; visibility:hidden}
	.massage{border:#036 solid; border-width:1 1 3 1; width:95%; height:95%; background:#fff; color:#036; font-size:12px; line-height:150%}
	.header{background:#036; height:1%; width:780px; font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12px; padding:3 5 0 5; color:#fff}
	#src{display:inline; width:350px; position:absolute}
	</style>
	<!--实现层移动-->
	<script language="javascript">
	var ispicture;
	function refreshimg(ispicture){
   		document.all.picture.src='picture?ispicture='+ispicture;
   	}
	
	var Obj=''
	document.onmouseup=MUp
	document.onmousemove=MMove
	
	function MDown(Object){
	Obj=Object.id
	document.all(Obj).setCapture()
	pX=event.x-document.all(Obj).style.pixelLeft;
	pY=event.y-document.all(Obj).style.pixelTop;
	}
	
	function MMove(){
	if(Obj!=''){
	document.all(Obj).style.left=event.x-pX;
	document.all(Obj).style.top=event.y-pY;
	}
	}
	
	function MUp(){
	if(Obj!=''){
	document.all(Obj).releaseCapture();
	Obj='';
	}
	}

	</script>
		
	
  </head>
  
  <body>
<s:action name="indexMenuEn" executeResult="true" />    

<div class="mainbody">
<s:form id="form1" action="toShowtroubleEn" theme="simple" method="post">
       <div class="search">
         <ul class="searchtext">
           <li class="name">Ecpss Order No.</li>
           <li class="nameinput"><s:textfield name="tradeinfo.merno"/></li>
         </ul>
         
         <ul class="searchbutton2">
           <li><!--<s:submit value="查询" class="input_button_01" /><s:reset value="取消" class="input_button_01" /> -->
           <input type="submit" value="Search" class="input_button_01" />
           &nbsp;
           <input type="submit" value="Cancel" class="input_button_01" />
           </li>
         </ul>
          
       </div>
       <div><img src="images/division.gif" alt="" /></div>
</s:form>
<s:form action="toShowtrouble" name="form2" theme="simple" method="post">
        <input type="hidden" id="typesname"  name="typesname" />
        <input type="hidden" value="<s:property value='tradeinfo.merno'/>" name="tradeinfo.merno" />
       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">Abnormal orders</li>
           </ul>
           <ul class="bottom">
             <li class="li_04">Amount</li>
             <li class="li_06">Status</li>
             <li class="li_06">Refund</li>
             <li class="li_04">Re-Amount</li>
             <li class="li_06">Freeze</li>
             <li class="li_04">Chargeback</li>
             <li class="li_06">Check</li>
             <li class="li_04">Tracking No.</li>  	
             <li class="li_04">Picture</li>	
             <li class="li_04">Remark</li>
             
           </ul>
         </div>
         <div class="listlist">
        
<s:iterator id="gouDuiList" value="info.result">
           <ul class="listlisttop">
             <li>Order No. ：<s:property value="#gouDuiList[0].orderNo"/></li>
             <li>Merchant Order No. ：<s:property value="#gouDuiList[0].merchantOrderNo"/></li>
             <li>Date ：<s:property value="#gouDuiList[0].tradeTime"/></li>
           </ul>
           <ul class="listlistbottom">  
             <li class="lil_04"><s:property value="#gouDuiList[0].tradeAmount"/></li>
             <li class="lil_06"><s:property value="states.getStateNameEn(#gouDuiList[0].tradeState,1)" escape="false" /></li>
             <li class="lil_06"><s:property value="states.getStateNameEn(#gouDuiList[0].tradeState,2)" escape="false"/></li>
             <li class="lil_04"><s:property value="#gouDuiList[0].backCount"/></li>
             <li class="lil_06"><s:property value="states.getStateNameEn(#gouDuiList[0].tradeState,4)" escape="false"/></li>
             <li class="lil_04"><s:property value="states.getStateNameEn(#gouDuiList[0].tradeState,3)" escape="false"/></li>
             <li class="lil_06"><s:property value="states.getStateNameEn(#gouDuiList[0].tradeState,5)" escape="false"/></li>
             <li class="lil_04"><s:property value="#gouDuiList[0].isTrackNo"/></li>
             <li class="lil_04">
		    &nbsp;            
             </li> 
             <li class="lil_04"><a href="findRemarkAction.action?id=<s:property value="#gouDuiList.id"/>"><s:property value="#gouDuiList[0].matterDepict"/></a>
             </li>   
           </ul>
</s:iterator>	
   
           <ul class="listlistpage">
             <li><pages:pages value="info" beanName="info" formName="getElementById('form2')" /></li>
           </ul>
           
         </div>
       </div>
     </div>
	</s:form>

    <!--<div id="massage_box">
		<div class="massage">
			<div class="header" onmousedown=MDown(massage_box)>
				<div style="display:inline; position:absolute">图片信息</div>
					<span onClick="massage_box.style.visibility='hidden'; mask.style.visibility='hidden'" style="float:right; display:inline; cursor:hand">×</span>
				</div>
				<IMG height=490 src="picture" id="picture" width=780 border=0>
			</div>
		</div>
	<div id="mask"></div> -->
     
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->		
  </body>
</html>
