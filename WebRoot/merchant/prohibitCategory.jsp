<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pages" uri="/xs-pages"%>
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>黑卡控制</title>
</head>
 <script language="JavaScript" type="text/JavaScript">
 function addSubmit(){
	 var blackCardNo=document.getElementById("blackCardNo").value;
	 var blackEmail=document.getElementById("blackEmail").value;
	 var blackIp=document.getElementById("blackIp").value;
	if(blackCardNo!=""||blackEmail!=""||blackIp!=""){
		if(confirm("是否确认添加?")){
			document.getElementById("addForm").submit();
		}
	}else{
		alert("最少要填一项！");
		return false;
	}
 }
 function deleteArea(id){
	if(confirm("确定要删除吗?")){
		window.location="../merchant/delBlackCardInfo.action?id="+id;
	}
}
	
</script>
<!--头部begin-->
<s:action name="indexMenu" executeResult="true"/>  
<s:form id="addForm" action="addBlackCardInfo" method="post" theme="simple">

<div class="mainbody">
	<div class="search">
		<table style="margin-left: 50px">
			<tr>
				<td>黑卡号：</td>
				<td><input type="text" id="blackCardNo" name="blackCardNo"  /></td>
				<td>&nbsp;&nbsp;&nbsp;</td>
				<td>黑Email：</td>
				<td><input type="text" id="blackEmail" name="blackEmail"  /></td>
			</tr>
			<tr style="height: 10px"></tr>
			<tr>
				<td>黑Ip：</td>
				<td><input type="text" id="blackIp" name="blackIp"  /></td>
			</tr>
			<tr style="height: 20px"></tr>
			<tr>
				<td colspan="5" align="center"><input type="button" onclick="addSubmit()" value="添  加"></td>
			</tr>
		</table>
	</div>
     <div class="clear"></div>
	      <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">已添加黑卡</li>
             <li class="lilistother"></li>
            
           </ul>
          <ul class="bottom">
               <li class="li_03">序号</li>  
		       <li class="li_02">卡号</li> 
               <li class="li_02">EMAIL</li>
               <li class="li_02">IP</li> 
               <li class="li_01">操作</li>   
             </ul>
         </div>
		 
		  <div class="listlist">
		    <s:iterator id="m" value="backList"  status="s">
               <ul class="listlistbottom">
               	 <li class="lil_03"><s:property value="#s.index+1" /></li>
				 <li class="lil_02"><s:property value="#m.cardno" /></li>
				 <li class="lil_02"><s:property value="#m.email" /></li>
				 <li class="lil_02"><s:property value="#m.ip" /></li>
				 <li class="lil_01"><a href="#" onclick="deleteArea(<s:property value="#m.id"/>)">删除</a></li>
               </ul>
		     </s:iterator>
		     
			  <br class="clear" />
	       <ul class="listlistpage">
		 <li></li>
	       </ul>
		   </div>
     </div>
    </s:form>
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->	




