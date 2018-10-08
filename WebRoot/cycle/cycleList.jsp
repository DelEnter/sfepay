<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>用户角色配置主页面</title>
    <link href="../css/other.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript" type="text/JavaScript">

function adds(){ 
   		var str='toAddCycle';
			 window.showModalDialog (str, window,'dialogHeight:300px;dialogWidth:400px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:no;status:yes') ;

}

function todeleteOpera(t){
       document.getElementById('deleteid').value=t;
       form2.submit();
}
</script>
<!--头部begin-->

<div class="mainbody">
	<s:form id="form1" action="tocyclelist" theme="simple" method="post">
 				<table align="center">
 			<tr>
 				<td>商户号</td>
  				<td>
  				<input type="text" value="<s:property value='tradeinfo.merno'/>" name="tradeinfo.merno"/>
  				</td>
    	</tr>
  			<tr>
  				<td>
  					<s:submit value="确定"/>
  				</td>
  				<td>
  					<s:reset value="取消"/>
  				</td>
  			</tr>
  		</table>
  		
  	</s:form>				
    
结算日列表<input type="button" value="新增" onclick="adds();" class="addadmin" /><br />
       <table width="100%" border="0" cellpadding="5" cellspacing="1" bgcolor="#666666">
         <tr>
           <td height="25" align="center" bgcolor="#CCCCCC">商户号&nbsp;</td>
           <td height="25" align="center" bgcolor="#CCCCCC">结算日&nbsp;</td>
           <td height="25" align="center" bgcolor="#CCCCCC">操作&nbsp;</td>
         </tr>
       
         <s:iterator id="listOne" value="tradeList" >
         <tr>
           <td align="center" bgcolor="#FFFFFF"><s:property value="#listOne[1].merno" />&nbsp;</td>
           <td align="center" bgcolor="#FFFFFF"><s:property value="#listOne[0].cycleTime" />&nbsp;</td>
           <td align="center" bgcolor="#FFFFFF"><input type="button" value="" title="删除" class="button_deltet_01" onclick="todeleteOpera('<s:property value='#listOne[0].id'/>');"  />&nbsp;</td>
         </tr>
         </s:iterator>    
  </table>
<!--<div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle"></li>
             <li class="lilistimg2">
               
             </li>
           </ul>
           <ul class="bottom">
             <li class="li_11"></li>  
             <li class="li_09"></li>
           </ul>
         </div>
         <div class="listlist">
      
           
         </div>
       </div> -->
</div>
 <s:form action="deletecycle" id="form2" namespace="/PaySystem">
  				<input type="hidden" value="<s:property value='tradeinfo.merno'/>" name="tradeinfo.merno"/>
 <input type="hidden" id="deleteid" name="settlementCycleTime.id"/>
 </s:form>
