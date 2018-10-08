<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<html>
<head>
<link href="../../css/style.css" rel="stylesheet" type="text/css">
<link href="../../css/other.css" rel="stylesheet" type="text/css">
<script language="JavaScript">
  
    function getLogDetails(){
    var iTop = (window.screen.availHeight- 30 - 750) / 2;
    var iLeft = (window.screen.availWidth- 10 - 700) / 2;
	window.showModalDialog("sys_log_details.html",window,"dialogHeight:300px;dialogWidth:1000px;status:no;scroll:yes;center:yes;help:no");	
  }

</script>

<script language="javascript">
function showlistiframe(obj){
	if(document.getElementById(obj).style.display  == "block"){
		document.getElementById(obj).style.display = "none";
		document.getElementById("sign").innerHTML = "<img src='../../images/other/icon2.gif' alt=''>";
	}
	else{
		document.getElementById(obj).style.display = "block";
		document.getElementById("sign").innerHTML="<img src='../../images/other/icon.gif' alt=''>";
	}	
}
    function toShowUrl(url){
    	alert(url);
    document.frames('mainFrame').location=url;
    }
function showmenu(targetid){
       var k=1;
       for(k;k<10;k++){
	     var f='menu'+k;
	     if(targetid!=f){
	  		document.getElementById(f).style.display="none";   
	     }
     	}
      var target=document.getElementById(targetid);
            if (target.style.display=="block"){
                target.style.display="none";
            } else {
                target.style.display="block";
            }
}

</script>

</head>
<body>
<div style="float: left;margin-left: 25%">
<table width="90%" height="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr><td width="200" align="center" valign="top" id="td1" style="display:block;">
      <table width="188" border="0" cellspacing="0" cellpadding="0" class="other_meun_border">
 		 <tr>
          <td class="other_meun_title"><a href="../getTranInfo.action" target="mainFrame"><span style="color:#000000">首页</span>  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp; &nbsp;&nbsp;&nbsp;&nbsp;  </a></td>
        </tr>
           <tr>
          <td class="other_meun_title" onclick="showmenu('menu1');">交易管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
          <div id="menu1" style="display: none">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist" value="resourcelist">
                		<s:if test="#resourcelist[0].substring(5)==1"> 
                <tr>
                  <td class="other_meun_list"> <a href="../<s:property value='#resourcelist[1]' />" target="mainFrame" >
                      <s:property value='#resourcelist[2]'/>
                  </a> </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
          <tr>
          <td class="other_meun_title" onclick="showmenu('menu2');">划款管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
           <div id="menu2" style="display: none">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist" value="resourcelist">         
			           <s:if test="#resourcelist[0].substring(5)==2"> 
                <tr>
                  <td class="other_meun_list"> 
                  <a href="../<s:property value='#resourcelist[1]' />" target="mainFrame" >
                      <s:property value='#resourcelist[2]'/></a>
                  </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu3');">退款管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
            <div id="menu3" style="display: none">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist" value="resourcelist">         
			           <s:if test="#resourcelist[0].substring(5)==3"> 
                <tr>
                  <td class="other_meun_list"> <a href="../<s:property value='#resourcelist[1]' />" target="mainFrame" >
                      <s:property value='#resourcelist[2]'/></a></td>
                </tr></s:if>
              </s:iterator>
            </table>
            </div>
            </td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu4');">跟踪管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
           <div id="menu4" style="display: none">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist" value="resourcelist">         
			           <s:if test="#resourcelist[0].substring(5)==4"> 
                <tr>
                  <td class="other_meun_list"> <a href="../<s:property value='#resourcelist[1]' />" target="mainFrame" >
                      <s:property value='#resourcelist[2]'/></a>
                  </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu5');">信息管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
          <div id="menu5" style="display: none">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist" value="resourcelist">         
			           <s:if test="#resourcelist[0].substring(5)==5">
                <tr>
                  <td class="other_meun_list"><a href="../<s:property value='#resourcelist[1]' />" target="mainFrame"  >
                      <s:property value='#resourcelist[2]'/></a>
                  </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
         <tr>
          <td class="other_meun_title" onclick="showmenu('menu6');">客服管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
          <div id="menu6" style="display: none">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
               <s:iterator id="resourcelist" value="resourcelist">         
			         <s:if test="#resourcelist[0].substring(5)==6"> 
                <tr>
                  <td class="other_meun_list"> <a href="../<s:property value='#resourcelist[1]' />" target="mainFrame"  >
                      <s:property value='#resourcelist[2]'/></a>
                  </td>
                </tr> </s:if>
              </s:iterator>
            </table></div></td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu7');">拒付处理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
              <div id="menu7" style="display: none">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist" value="resourcelist">         
			         <s:if test="#resourcelist[0].substring(5)==8">
               <tr>
                  <td class="other_meun_list"><a href="../<s:property value='#resourcelist[1]' />" target="mainFrame"  >
                      <s:property value='#resourcelist[2]'/></a>
                  </td>
                </tr></s:if>
              </s:iterator>
            </table>
            </div>
            </td>
        </tr>
         <tr>
          <td class="other_meun_title" onclick="showmenu('menu8');">权限管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
             <div id="menu8" style="display: none">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
               <s:iterator id="resourcelist" value="resourcelist">         
			         <s:if test="#resourcelist[0].substring(5)==7">
                <tr>
                  <td class="other_meun_list"> <a href="../<s:property value='#resourcelist[1]' />" target="mainFrame"  >
                      <s:property value='#resourcelist[2]'/></a>
                  </td>
                </tr></s:if>
              </s:iterator>
            </table></div></td>
        </tr>
        <tr>
          <td class="other_meun_title" onclick="showmenu('menu9');">风险管理</td>
        </tr>
        <tr>
          <td class="other_meun_list_bg">
          <div id="menu9" style="display: none">
          <table width="188" border="0" cellspacing="0" cellpadding="0">
              <s:iterator id="resourcelist" value="resourcelist">         
		         <s:if test="#resourcelist[0].substring(5)==9"> 
                <tr>
                  <td class="other_meun_list"><a href="../<s:property value='#resourcelist[1]' />" target="mainFrame" >
                      <s:property value='#resourcelist[2]'/></a>
                  </td>
                </tr> </s:if>
              </s:iterator>
            </table></div></td>
        </tr>
      </table></td>
    <td width="10"  onClick="showlistiframe('td1');" ><table height="100%" border="0" cellpadding="0" cellspacing="0';"
onMouseOver="this.bgColor='#BBBBFF'; "  onMouseOut="this.bgColor='#ffffff';" bgcolor="#ffffff">
        <tr>
          <td id="sign" width="10"><img src="../../images/other/icon.gif" alt=""></td>
        </tr>
      </table></td>
  </tr>
</table>
</div>
</body>
</html>	