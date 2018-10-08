<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<head>
<title>角色资源配置</title>
<link href="css/body2.css" rel="stylesheet" type="text/css">
</head>
<script language="JavaScript" type="text/JavaScript">

function updateRoleResource2(i){
goFormWindow(i,"../PaySystem/updateRoleResource");

}
</script>
<body>
<div id="title" value="角色资源配置"/>
<div id="resizetable" width="600" height="680" />
<div class="windows_01">
  <s:form action="updateRoleResource" id="form10" namespace="/PaySystem" theme="simple">
  
  
    <div class="title"><span>权限管理</span></div>
    <input type="hidden" name="role.id" value="<s:property value='role.id' />" />
    <div class="xuanxian">
      <ul>
        <s:iterator id="resourcelist" value="resourcelist">
        
          <s:if test="#resourcelist.fatherNumber.substring(4)==01">
            <li style="list-style:none; width:115px; height:20px; float:left; margin:0 5px 0 0; "><input type="checkbox" name="roleresourcecheck"  
              <s:if test="#resourcelist.checkFlag=='true'"> checked="true" </s:if>
              value="<s:property value="#resourcelist.id"/>" />
              <s:property value="#resourcelist.menuName" />
            </li>
          </s:if>
        </s:iterator>
      </ul>
    </div>
    
    
    <div class="title"><span>商户管理</span></div>
    <div class="xuanxian">
      <ul>
        <s:iterator id="resourcelist2" value="resourcelist">
          <s:if test="#resourcelist2.fatherNumber.substring(4)==02">
            <!--<s:property value="#resourcelist2.menuName" /> -->
            
            <li style="list-style:none; width:120px; height:20px; float:left; margin:0 5px 0 0; ">
              <input type="checkbox" name="roleresourcecheck"  
              <s:if test="#resourcelist2.checkFlag=='true'"> checked="true" </s:if>
              value="<s:property value="#resourcelist2.id"/>"/>   <s:property value="#resourcelist2.menuName" />
            </li>
            
          </s:if>
        </s:iterator>
      </ul>
    </div>
    
     <div class="title"><span>交易管理</span></div>
 
    <div class="xuanxian">
      <ul>
          <s:iterator id="resourcelist3" value="resourcelist">
             <s:if test="#resourcelist3.fatherNumber.substring(5)==3"> 
            
            <li style="list-style:none; width:130px; height:20px; float:left; margin:0 5px 0 0; ">
               <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist3.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist3.id" />"/>
        <s:property value="#resourcelist3.menuName" />
              </li>
          </s:if>
    </s:iterator>
      </ul>
    </div>
    
 <div class="title"><span>结算管理</span></div>
 <div class="xuanxian">
      <ul>
<s:iterator id="resourcelist4" value="resourcelist">
      <s:if test="#resourcelist4.fatherNumber.substring(5)==4"> 
         <li style="list-style:none; width:110px; height:20px; float:left; margin:0 5px 0 0; ">
            <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist4.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist4.id"/>" />
        <s:property value="#resourcelist4.menuName" />
         </li>
      </s:if>
</s:iterator>
      </ul>
 </div>
    
    
 <div class="title"><span>对账管理</span></div>
 <div class="xuanxian">
      <ul>
 <s:iterator id="resourcelist5" value="resourcelist">
      <s:if test="#resourcelist5.fatherNumber.substring(5)==5"> 
         <li style="list-style:none; width:110px; height:20px; float:left; margin:0 5px 0 0; ">
            <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist5.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist5.id" />" />
        <s:property value="#resourcelist5.menuName" />
         </li>
 </s:if>
    </s:iterator>
      </ul>
 </div>
     
    
    
    
     <div class="title"><span>系统设置</span></div>
 <div class="xuanxian">
      <ul>
 <s:iterator id="resourcelist6" value="resourcelist">
      <s:if test="#resourcelist6.fatherNumber.substring(5)==6"> 
         <li style="list-style:none; width:110px; height:20px; float:left; margin:0 5px 0 0; ">
             <input type="checkbox" name="roleresourcecheck" <s:if test="#resourcelist6.checkFlag=='true'"> checked="true" </s:if> value="<s:property value="#resourcelist6.id"/>" />
        <s:property value="#resourcelist6.menuName" />
         </li>
</s:if>
   
    </s:iterator>
      </ul>
 </div>
    
      
    
     <div class="title"><span>客服系统</span></div>
 <div class="xuanxian">
      <ul>
 <s:iterator id="resourcelist7" value="resourcelist">
      <s:if test="#resourcelist7.fatherNumber.substring(5)==7"> 
         <li style="list-style:none; width:110px; height:20px; float:left; margin:0 5px 0 0; ">
           <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist7.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist7.id"/>"/>
        <s:property value="#resourcelist7.menuName" />
         </li>
 </s:if>
    
    </s:iterator>
      </ul>
 </div>
    
    
     <div class="title"><span>风控系统</span></div>
 <div class="xuanxian">
      <ul>
 <s:iterator id="resourcelist8" value="resourcelist">
      <s:if test="#resourcelist8.fatherNumber.substring(5)==8"> 
         <li style="list-style:none; width:110px; height:20px; float:left; margin:0 5px 0 0; ">
            <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist8.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist8.id"/>"/>
        <s:property value="#resourcelist8.menuName" />
         </li>
</s:if>
  
    </s:iterator>
      </ul>
 </div>
    
 <div class="title"><span>报表系统(统计)</span></div>
 <div class="xuanxian">
      <ul>
<s:iterator id="resourcelist9" value="resourcelist">
      <s:if test="#resourcelist9.fatherNumber.substring(5)==9"> 
         <li style="list-style:none; width:110px; height:20px; float:left; margin:0 5px 0 0; ">
           <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist9.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist9.id"/>" />
        <s:property value="#resourcelist9.menuName" />
         </li>
  </s:if>
    </s:iterator>
      </ul>
 </div>
    
 <div class="title"><span>风控系统</span></div>
 <div class="xuanxian">
      <ul>
 <s:iterator id="resourcelist10" value="resourcelist">
      <s:if test="#resourcelist10.fatherNumber.substring(4)==10"> 
         <li style="list-style:none; width:110px; height:20px; float:left; margin:0 5px 0 0; ">
             <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist10.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist10.id"/>" />
        <s:property value="#resourcelist10.menuName" />
         </li>
</s:if>
    </s:iterator>
      </ul>
 </div>
 
 <div class="title"><span>系统日志管理</span></div>
 <div class="xuanxian">
      <ul>
 <s:iterator id="resourcelist11" value="resourcelist">
      <s:if test="#resourcelist11.fatherNumber.substring(4)==11"> 
         <li style="list-style:none; width:110px; height:20px; float:left; margin:0 5px 0 0; ">
           <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist11.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist11.id"/>" />
        <s:property value="#resourcelist11.menuName" />
         </li>
 </s:if>
    </s:iterator>
      </ul>
 </div>

    
 <div class="title"><span>持卡人信息</span></div>
 <div class="xuanxian">
      <ul>
 <s:iterator id="resourcelist12" value="resourcelist">
      <s:if test="#resourcelist12.fatherNumber=='000012'"> 
         <li style="list-style:none; width:110px; height:20px; float:left; margin:0 5px 0 0; ">
             <input type="checkbox" name="roleresourcecheck"  
        
        <s:if test="#resourcelist12.checkFlag=='true'"> checked="true" </s:if>
        value="<s:property value="#resourcelist12.id"/>" />
        <s:property value="#resourcelist12.menuName" />
         </li>
</s:if>
    </s:iterator>
      </ul>
 </div>
 
 <div><input type="button" value="保存"  onclick="updateRoleResource2(this.form);" /></div>
    
  </s:form>
</div>
</body>
