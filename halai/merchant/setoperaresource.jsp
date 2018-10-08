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
function reloadPage(){

        window.location="../PaySystem/tosetUserRole.action";
}
function adds(){ 
   		var str='toAddOpera';
			 window.showModalDialog (str, window,'dialogHeight:300px;dialogWidth:400px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:no;status:yes') ;

}
function setResource(f){
          var str='operatorResour.action?shopOpera.id='+f;
			 window.showModalDialog (str, window,'dialogHeight:630px;dialogWidth:593px;toolbar:yes;menubar:yes;scroll:yes;resizable:yes;location:yes;status:yes') ;
          
}
function todeleteOpera(t){
       document.getElementById('deleteid').value=t;
       form1.submit();
}
</script>
<!--头部begin-->
<s:action name="indexMenu" executeResult="true"/>  
<div class="mainbody">

       <div class="list">
         <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">操作员列表</li>
             <li class="lilistimg2"><input type="button" value="" onclick="adds();" class="addadmin" /></li>
           </ul>
           <ul class="bottom">
             <li class="li_11">用户名</li>  
             <li class="li_09">操作</li>
           </ul>
         </div>
         <div class="listlist">
       <s:iterator id="listOne" value="opera" >
           <ul class="listlistbottom">
             <li class="lil_11"><s:property value="#listOne.userName" /></li>
             <li class="lil_09"><input type="button" value="" title="分配设置" class="button_edit_01" onclick="setResource('<s:property value='#listOne.id'/>');"  />
		<input type="button" value="" title="删除" class="button_deltet_01" onclick="todeleteOpera('<s:property value='#listOne.id'/>');"  /></li>  
           </ul>
         </s:iterator>    
           
         </div>
       </div>
     </div>

<!--<input type="button" value="新增操作员" onclick="add();" />

<div class="fate" >
  <div class="title">
    <ul>
      <li class="liicon"><img src="../images/other/icon3.gif" alt="" /></li>
      <li class="lititle">操作员</li>
    </ul>
  </div>
  <div class="title2">
    <ul class="ul_title1 font14px">
      <li class="lili"><b>用户名</b></li>
      <li class="lili"><b>操作</b></li>
    </ul>
 <s:iterator id="listOne" value="opera" >
    <ul class="ul_title1">
      <li class="lili"><s:property value="#listOne.userName" /></li>
      <li class="lili">
      	<input type="button" value="" title="分配设置" class="button_edit_01" onclick="openWindow('../PaySystem/tosetUserRoleFinish.action','users.id=<s:property value='#listOne[1]' />');" />
		<input type="button" value="" title="删除" class="button_deltet_01" onclick="todeleteOpera('<s:property value='#listOne.id'/>');"  />
      </li>
    </ul>
 </s:iterator>
  </div>
  
</div> -->
 <s:form action="delOperator" id="form1" namespace="/merchant">
 <input type="hidden" id="deleteid" name="shopOpera.id"/>
 </s:form>
 
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->	