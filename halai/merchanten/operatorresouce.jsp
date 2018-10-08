<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
<title>Operator Privilege Setup</title>
<link href="css/body2.css" rel="stylesheet" type="text/css" />
<link href="css/css.css" rel="stylesheet" type="text/css" />
</head>
<script language="JavaScript" type="text/JavaScript">

function updateRoleResource2(i){
 i.submit();
}
</script>
  	<base target="_self"> 
<body>

<!--<div id="resizetable" width="600" height="680" /> -->
<div style="font-size:14px; font-weight:bold; margin:10px 0 5px 10px"><span>Operator Privilege Setup</span></div>
<div class="windows2">
  <s:form action="operatorResourUpdate" id="form10" namespace="/merchant" theme="simple">
  
  
    <div class="title"><span>Transaction administration</span></div>
    <input type="hidden" name="shopOpera.id" value="<s:property value='shopOpera.id' />" />
    <div class="xuanxian">
      <ul>
        <s:iterator id="resourcelist" value="resourcelist">
          <s:if test="#resourcelist.numberCode.substring(5)==1">
            <li style="list-style:none; width:125px; height:20px; float:left; margin:0 5px 0 0; "><input type="checkbox" name="roleresourcecheck"  
              <s:if test="#resourcelist.checkFlag=='true'"> checked="true" </s:if>
              value="<s:property value="#resourcelist.id"/>" />
              <s:property value="#resourcelist.menuName" />
            </li>
          </s:if>
        </s:iterator>
      </ul>
    </div>
    
    <div class="clear">&nbsp;</div>
    
    <div class="title"><span>Settlement administration</span></div>
    <div class="xuanxian">
      <ul>
        <s:iterator id="resourcelist2" value="resourcelist">
          <s:if test="#resourcelist2.numberCode.substring(5)==2">
            <!--<s:property value="#resourcelist2.menuName" /> -->
            
            <li style="list-style:none; width:125px; height:20px; float:left; margin:0 5px 0 0; ">
              <input type="checkbox" name="roleresourcecheck"  
              <s:if test="#resourcelist2.checkFlag=='true'"> checked="true" </s:if>
              value="<s:property value="#resourcelist2.id"/>"/>   <s:property value="#resourcelist2.menuName" />
            </li>
            
          </s:if>
        </s:iterator>
      </ul>
    </div>
    <div class="clear">&nbsp;</div>
     <div class="title"><span>Refund administration</span></div>
 
    <div class="xuanxian">
      <ul>
          <s:iterator id="resourcelist3" value="resourcelist">
            <s:if test="#resourcelist3.numberCode.substring(5)==3">
            
            <li style="list-style:none; width:125px; height:20px; float:left; margin:0 5px 0 0; ">
               <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist3.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist3.id" />"/>
        <s:property value="#resourcelist3.menuName" />
              </li>
          </s:if>
    </s:iterator>
      </ul>
    </div>
    <div class="clear">&nbsp;</div>
 <div class="title"><span>Tracking administration</span></div>
 <div class="xuanxian">
      <ul>
<s:iterator id="resourcelist4" value="resourcelist">
      <s:if test="#resourcelist4.numberCode.substring(5)==4"> 
         <li style="list-style:none; width:125px; height:20px; float:left; margin:0 5px 0 0; ">
            <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist4.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist4.id"/>" />
        <s:property value="#resourcelist4.menuName" />
         </li>
      </s:if>
</s:iterator>
      </ul>
 </div>
    
    <div class="clear">&nbsp;</div>
 <div class="title"><span>Information Administration</span></div>
 <div class="xuanxian">
      <ul>
 <s:iterator id="resourcelist5" value="resourcelist">
      <s:if test="#resourcelist5.numberCode.substring(5)==5"> 
         <li style="list-style:none; width:125px; height:20px; float:left; margin:0 5px 0 0; ">
            <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist5.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist5.id" />" />
        <s:property value="#resourcelist5.menuName" />
         </li>
 </s:if>
    </s:iterator>
      </ul>
 </div>
     
    
    <div class="clear">&nbsp;</div>
    
     <div class="title"><span>Customer Service Administration</span></div>
 <div class="xuanxian">
      <ul>
 <s:iterator id="resourcelist6" value="resourcelist">
      <s:if test="#resourcelist6.numberCode.substring(5)==6"> 
         <li style="list-style:none; width:125px; height:20px; float:left; margin:0 5px 0 0; ">
             <input type="checkbox" name="roleresourcecheck" <s:if test="#resourcelist6.checkFlag=='true'"> checked="true" </s:if> value="<s:property value="#resourcelist6.id"/>" />
        <s:property value="#resourcelist6.menuName" />
         </li>
</s:if>
   
    </s:iterator>
      </ul>
 </div>
    
    <div class="clear">&nbsp;</div>  
    
     <div class="title"><span>Privilege Administration</span></div>
 <div class="xuanxian">
      <ul>
 <s:iterator id="resourcelist7" value="resourcelist">
      <s:if test="#resourcelist7.numberCode.substring(5)==7"> 
         <li style="list-style:none; width:125px; height:20px; float:left; margin:0 5px 0 0; ">
           <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist7.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist7.id"/>"/>
        <s:property value="#resourcelist7.menuName" />
         </li>
 </s:if>
    
    </s:iterator>
      </ul>
 </div>
    <div class="clear">&nbsp;</div>
    
     <div class="title"><span>ChargeBack</span></div>
 <div class="xuanxian">
      <ul>
 <s:iterator id="resourcelist8" value="resourcelist">
      <s:if test="#resourcelist8.numberCode.substring(5)==8"> 
         <li style="list-style:none; width:125px; height:20px; float:left; margin:0 5px 0 0; ">
            <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist8.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist8.id"/>"/>
        <s:property value="#resourcelist8.menuName" />
         </li>
</s:if>
  
    </s:iterator>
      </ul>
 </div>

    
 <div class="clear">&nbsp;</div>
 <div class="button_save"><input type="button" value="Save" class="input_button_01"  onclick="updateRoleResource2(this.form);" /></div>
    
  </s:form>
</div>
</body>
