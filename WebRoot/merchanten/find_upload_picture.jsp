<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<%@ taglib prefix="s" uri="/struts-tags"%>
<html>
  <head>
    <title>Upload Invoice Picture</title>

	<%@ taglib prefix="pages" uri="/xs-pages"%>
	<link rel="stylesheet" type="text/css" href="css/mercss.css"/>

	<style type="text/css">
	
		#massage_box{ position:absolute; left:expression((body.clientWidth-350)/2); top:expression((body.clientHeight-200)/2); width:400px;
		height:200px;filter:dropshadow(color=#666666,offx=3,offy=3,positive=2); z-index:2; visibility:hidden}
		#mask{ position:absolute; top:0; left:0; width:expression(body.scrollWidth); height:expression(body.scrollHeight); filter:ALPHA(opacity=60); z-index:1; visibility:hidden}
		.massage{border:#036 solid; border-width:1 1 3 1; width:95%; height:95%; background:#fff; color:#036; font-size:12px; line-height:150%}
		.header{background:#036; height:1%; width:780px; font-family:Verdana, Arial, Helvetica, sans-serif; font-size:12px; padding:3 5 0 5; color:#fff}
		#src{display:inline; width:350px; position:absolute}
	</style>
	<!--实现层移动-->
	<script language="javascript">
	function detialinfo(tradeid){
		window.showModalDialog('toUploadPictureEn.action?trade.id='+tradeid, window,'dialogHeight:325px;dialogWidth:650px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') 
	}
	
	var ispicture;
	function refreshimg(ispicture){
   		document.all.picture.src='../picture.do?ispicture='+ispicture;
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
  	<s:action name="indexMenuEn" executeResult="true"/> 
    
    <s:form action="" method="post" theme="simple" name="form1">
    
	<div class="mainbody">
	   <div class="list">
	   
	      <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">Upload Invoice Picture</li>
             <li class="lilistother"></li>
            
           </ul>
          <ul class="bottom">       
               <li class="li_08">Ecpss Order NO</li>  
		       <li class="li_04">Merchant Order NO</li> 
		       <li class="li_04">Transaction Date</li>   
		       <li class="li_04">Transaction Amount</li>   
		       <li class="li_04">Payment Status</li>    
               <li class="li_03">Upload</li>    
               <li class="li_03">Status</li>    
               <li class="li_01">View</li>   
             </ul>
         </div>
		 
		  <div class="listlist">
		   <s:iterator id="it" value="info.result">
           		<ul class="listlistbottom">
             		 
                     <li class="lil_08"><s:property value="#it.orderNo"/></li>  
             		 <li class="lil_04"><s:property value="#it.merchantOrderNo"/></li>    
            		 <li class="lil_04"><s:property value="#it.tradeTime"/></li>   
            		 <li class="lil_04"><s:property value="#it.tradeAmount"/></li>   
             		 <li class="lil_04"><s:property value="states.getStateNameEn(#it.tradeState,1)" escape="false" /></li>    

            		 <li class="lil_03">
            		 	<a href="#" onclick="detialinfo(<s:property value="#it.id"/>)">Upload</a>
            		 <li class="lil_03">
            
                      <s:if test="#it.isPicture!=null">
		    					Yes
		    				</s:if>
		    				<s:elseif test="#it.isPicture==null">
		    					No
		    				</s:elseif>
                     </li>    
           		     <li class="lil_01">
                  <span onClick="mask.style.visibility='visible';massage_box.style.visibility='visible'" style="cursor:hand">
			    				<a href="#" onClick=refreshimg("<s:property value="#it.isPicture"/>")><img
												src="images/more_icon.gif" title="View" alt="View" />
									</a> </span>	   
                       </li>    
            		
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
 
    <div id="massage_box">
		<div class="massage">
			<div class="header" onmousedown=MDown(massage_box)>
				<div style="display:inline; position:absolute">图片信息</div>
					<span onClick="massage_box.style.visibility='hidden'; mask.style.visibility='hidden'" style="float:right; display:inline; cursor:hand">×</span>
				</div>
				<IMG height=490  id="picture" width=780 border=0 />
			</div>
		</div>
	<div id="mask"></div>
	</s:form>
	
	<!--尾部begin-->
	<%@ include file="../merchant/foot.jsp"%>
	
  </body>
</html>
