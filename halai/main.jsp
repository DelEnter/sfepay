<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>首页</title>
<link href="css/style.css" rel="stylesheet" type="text/css">
<link href="../css/other.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
  
    function getLogDetails(){
    var iTop = (window.screen.availHeight- 30 - 750) / 2;
    var iLeft = (window.screen.availWidth- 10 - 700) / 2;
	window.showModalDialog("sys_log_details.html",window,"dialogHeight:300px;dialogWidth:1000px;status:no;scroll:yes;center:yes;help:no");	
 //  window.showModelDialog("client_info_detials.html", "newwindow", "height=570, width=810,  top=" + iTop + ", left=" + iLeft + ", toolbar=no, menubar=no, //scrollbars=yes, resizable=no,location=no, status=no")
  }

</script>

<script language="javascript">
function showlistiframe(obj){

	if(document.getElementById(obj).style.display  == "block"){
		document.getElementById(obj).style.display = "none";
		document.getElementById("sign").innerHTML = "<img src='../images/other/icon2.gif' alt=''>";
	}
	else{
		document.getElementById(obj).style.display = "block";
		document.getElementById("sign").innerHTML="<img src='../images/other/icon.gif' alt=''>";
	}	
}
    function toShowUrl(url){
    document.all.rightFrame.src=url;
    }
function showmenu(targetid){

       var k=1;
       for(k;k<13;k++){
     var f='menu'+k;
     if(targetid!=f){
  document.getElementById(f).style.display="none";   
     }
     }
    if (document.getElementById){
        target=document.getElementById(targetid);
            if (target.style.display=="block"){
                target.style.display="none";
            } else {
                target.style.display="block";
            }
    }
}

</script>

</head>
<style>
html {
	overflow-x:hidden;
	overflow-y:hidden;
}
</style>
<BODY scroll=no style="MARGIN: 0px">
<form name="redirectForm" method="post">
</form>
<table width="90%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td width="200" align="center" valign="top" id="td1" style="display:block;"><!--    <iframe src="PaySystem/admin.action" scrolling="auto" width="100%" id="leftFrame" name="leftFrame" frameborder="0" height="100%" style="display:block">
</iframe> -->
      <table width="188" border="0" cellspacing="0" cellpadding="0" class="other_meun_border">
 
           <tr>
          <td class="other_meun_title" onclick="showmenu('menu8');">查询系统</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
          <div id="menu8" style="display:none;">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist8" value="resourcelist"><s:if test="#resourcelist8[0].substring(5)==8">
                <tr>
                  <td class="other_meun_list"> <a href="#" onClick="toShowUrl('<s:property value='#resourcelist8[1]' />');" >
                      <s:property value='#resourcelist8[2]'/>
                  </a> </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
          <tr>
          <td class="other_meun_title" onclick="showmenu('menu12');">持卡人信息</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
           <div id="menu12" style="display:none;">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist12" value="resourcelist"><s:if test="#resourcelist12[0]=='000012'">
                <tr>
                  <td class="other_meun_list"> <a href="#" onClick="toShowUrl('<s:property value='#resourcelist12[1]' />');" >
                      <s:property value='#resourcelist12[2]'/>
                  </a> </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu3');">交易管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
            <div id="menu3" style="display:none;">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist3" value="resourcelist"><s:if test="#resourcelist3[0].substring(5)==3">
                <tr>
                  <td class="other_meun_list"> <a href="#" onClick="toShowUrl('<s:property value='#resourcelist3[1]' />');" >
                      <s:property value='#resourcelist3[2]'/>
                  </a> </td>
                </tr></s:if>
              </s:iterator>
            </table>
            </div>
            </td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu4');">结算管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
           <div id="menu4" style="display:none;">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist4" value="resourcelist"><s:if test="#resourcelist4[0].substring(5)==4">
                <tr>
                  <td class="other_meun_list"> <a href="#" onClick="toShowUrl('<s:property value='#resourcelist4[1]' />');" >
                      <s:property value='#resourcelist4[2]'/>
                  </a> </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu5');">对账管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
          <div id="menu5" style="display:none;">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist5" value="resourcelist"><s:if test="#resourcelist5[0]=='000005'">
                <tr>
                  <td class="other_meun_list"> <a href="#" onClick="toShowUrl('<s:property value='#resourcelist5[1]' />');" >
                      <s:property value='#resourcelist5[2]'/>
                  </a> </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
         <tr>
          <td class="other_meun_title" onclick="showmenu('menu7');">客服系统</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
          <div id="menu7" style="display:none;">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist7" value="resourcelist"><s:if test="#resourcelist7[0]=='000007'">
                <tr>
                  <td class="other_meun_list"> <a href="#" onClick="toShowUrl('<s:property value='#resourcelist7[1]' />');" >
                      <s:property value='#resourcelist7[2]'/>
                  </a></td>
                </tr> </s:if>
              </s:iterator>
            </table></div></td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu2');">商户管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
              <div id="menu2" style="display:none;">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist2" value="resourcelist">
               <s:if test="#resourcelist2[0]=='000002'">  
               <tr>
                  <td class="other_meun_list"><a href="#" onClick="toShowUrl('<s:property value='#resourcelist2[1]' />');" >
                      <s:property value='#resourcelist2[2]'/>
                  </a> </td>
                </tr></s:if>
              </s:iterator>
            </table>
            </div>
            </td>
        </tr>
         <tr>
          <td class="other_meun_title" onclick="showmenu('menu10');">风控系统</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
             <div id="menu10" style="display:none;">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist10" value="resourcelist"><s:if test="#resourcelist10[0].substring(5)==0">
                <tr>
                  <td class="other_meun_list"> <a href="#" onClick="toShowUrl('<s:property value='#resourcelist10[1]' />');" >
                      <s:property value='#resourcelist10[2]'/>
                  </a> </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu6');">系统设置</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
          <div id="menu6" style="display:none;">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist6" value="resourcelist"><s:if test="#resourcelist6[0]=='000006'">
                <tr>
                  <td class="other_meun_list"> <a href="#" onClick="toShowUrl('<s:property value='#resourcelist6[1]' />');" >
                      <s:property value='#resourcelist6[2]'/>
                  </a></td>
                </tr> </s:if>
              </s:iterator>
            </table></div></td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu9');">报表系统(统计)</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
           <div id="menu9" style="display:none;">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist9" value="resourcelist"><s:if test="#resourcelist9[0]=='000009'">
                <tr>
                  <td class="other_meun_list"> <a href="#" onClick="toShowUrl('<s:property value='#resourcelist9[1]' />');" >
                      <s:property value='#resourcelist9[2]'/>
                  </a> </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu11');">系统日志管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
           <div id="menu11" style="display:none;">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist11" value="resourcelist"><s:if test="#resourcelist11[0]=='000011'">
                <tr>
                  <td class="other_meun_list"> <a href="#" onClick="toShowUrl('<s:property value='#resourcelist11[1]' />');" >
                      <s:property value='#resourcelist11[2]'/>
                  </a> </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
      
       <tr>
          <td class="other_meun_title" onclick="showmenu('menu1');"><a  href="#">权限管理</a></td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
    <div id="menu1" style="display:none;">
          
          <table width="188" border="0" cellspacing="0" cellpadding="0">
     
              <s:iterator id="resourcelist" value="resourcelist">
                <s:if test="#resourcelist[0]=='000001'"> <tr>
                  <td class="other_meun_list"><a href="#" onClick="toShowUrl('<s:property value='#resourcelist[1]' />');" >
                      <s:property value='#resourcelist[2]'/>
                      </a> </td>
                </tr></s:if>
                  
              </s:iterator>
            
            </table>
         </div>
            
            </td>
        </tr>        
      </table></td>
    <td width="10"  onClick="showlistiframe('td1');" ><table height="100%" border="0" cellpadding="0" cellspacing="0';"
onMouseOver="this.bgColor='#BBBBFF'; "  onMouseOut="this.bgColor='#ffffff';" bgcolor="#ffffff">
        <tr>
          <td id="sign" width="10"><img src="../images/other/icon.gif" alt=""></td>
        </tr>
      </table></td>
    <td><iframe  width="100%" id="rightFrame" scrolling="auto" frameborder="0" height="100%" name="rightFrame"></iframe></td>
  </tr>
</table>
</body>
</html>
