<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<%@ taglib prefix="pages" uri="/xs-pages"%>
<%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script><head>
	<title>禁止支付地区</title>
</head>
<!--头部begin-->
<s:action name="indexMenu" executeResult="true"/>  
<s:form action="submitarea" method="post" theme="simple">

<div class="mainbody">
	   <div class="list">
	   <div class="search">
       <ul class="searchtext">
         <li class="name3">选择需要禁止交易的国家或地区</li>
         <li class="nameinput2">
     <select name="area" id="area" style="height:200px;" multiple="multiple">
         <option value="US United States of America">US United States of America</option>
         <option value="AU Australia">AU Australia</option>
         <option value="GB United Kiongdom">GB United Kingdom</option>
         <option value="FR France">FR France</option>
         <option value="AE United Arab Emirates">AE United Arab Emirates</option>
         <option value="AG Antigua and Barbuda">AG Antigua and Barbuda</option>
         <option value="AN">AN</option>
         <option value="AR Argentina">AR Argentina</option>
         <option value="AT Austria">AT Austria</option>
         <option value="AZ Azerbaijan">AZ Azerbaijan</option>
         <option value="BB Barbados">BB Barbados</option>
         <option value="BD Bangladesh">BD Bangladesh</option>
         <option value="BE Belgium">BE Belgium</option>
         <option value="BG Bulgaria">BG Bulgaria</option>
         <option value="BH Bahrain">BH Bahrain</option>
         <option value="BM BermudaIs.">BM BermudaIs.</option>
         <option value="BR Brazil">BR Brazil</option>
         <option value="BS Bahamas">BS Bahamas</option>
         <option value="CA Canada">CA Canada</option>
         <option value="CH Switzerland">CH Switzerland</option>
         <option value="CL Chile">CL Chile</option>
         <option value="CN China">CN China</option>
         <option value="CO Colombia">CO Colombia</option>
         <option value="CR Costa Rica">CR Costa Rica</option>
         <option value="CY Cyprus">CY Cyprus</option>
         <option value="CZ Czech Republic">CZ Czech Republic</option>
         <option value="DE Germany">DE Germany</option>
         <option value="DK Denmark">DK Denmark</option>
         <option value="DZ Algeria">DZ Algeria</option>
         <option value="DO Dominica Rep.">DO Dominica Rep.</option>
         <option value="EC Ecuador">EC Ecuador</option>
         <option value="EE Estonia">EE Estonia</option>
         <option value="ES Spain">ES Spain</option>
         <option value="FI Finland">FI Finland</option>
         <option value="GP">GP</option>
         <option value="GR Greece">GR Greece</option>
         <option value="GT Guatemala">GT Guatemala</option>
         <option value="GY Guyana">GY Guyana</option>
         <option value="HK Hongkong">HK Hongkong</option>
         <option value="HR">HR</option>
         <option value="HU Hungary">HU Hungary</option>
         <option value="IE Ireland">IE Ireland</option>
         <option value="IL Israel">IL Israel</option>
         <option value="IN India">IN India</option>
	 <option value="ID Indonesia">ID Indonesia</option>
         <option value="IS Iceland">IS Iceland</option>
         <option value="IT Italy">IT Italy</option>
         <option value="JP Japan">JP Japan</option>
         <option value="KE Kenya">KE Kenya</option>
         <option value="KR Korea">KR Korea</option>
         <option value="KW Kuwait">KW Kuwait</option>
         <option value="KY">KY</option>
         <option value="LK Sri Lanka">LK Sri Lanka</option>
         <option value="LT Lithuania">LT Lithuania</option>
         <option value="LU Luxembourg">LU Luxembourg</option>
         <option value="LV Latvia">LV Latvia</option>
		 <option value="MC Monaco">MC Monaco</option>
		 <option value="MA Morocco">MA Morocco</option>
         <option value="MO Macao">MO Macao</option>
         <option value="MQ">MQ</option>
         <option value="MT Malta">MT Malta</option>
         <option value="MX Mexico">MX Mexico</option>
         <option value="MY Malaysia">MY Malaysia</option>
         <option value="NL Netherlands">NL Netherlands</option>
         <option value="NO Norway">NO Norway</option>
         <option value="NZ NewZealand">NZ NewZealand</option>
         <option value="OM Oman">OM Oman</option>
         <option value="PE Peru">PE Peru</option>
         <option value="PH Philippines">PH Philippines</option>
         <option value="PL Poland">PL Poland</option>
         <option value="PT Portugal">PT Portugal</option>
         <option value="QA Qatar">QA Qatar</option>
         <option value="RO Romania">RO Romania</option>
         <option value="RU Russia">RU Russia</option>
         <option value="SA Saudi Arabia">SA Saudi Arabia</option>
         <option value="SE Sweden">SE Sweden</option>
         <option value="SG Singapore">SG Singapore</option>
         <option value="SI Slovenia">SI Slovenia</option>
         <option value="SK Slovakia">SK Slovakia</option>
         <option value="SV EISalvador">SV EISalvador</option>
         <option value="SY Syria">SY Syria</option>
         <option value="TH Thailand">TH Thailand</option>
         <option value="TR Turkey">TR Turkey</option>
         <option value="TT Trinidad and Tobago">TT Trinidad and Tobago</option>
         <option value="TW Taiwan">TW Taiwan</option>
         <option value="VE Venezuela">VE Venezuela</option>
	 <option value="VN Vietnam">VN Vietnam</option>
         <option value="ZA South Africa">ZA South Africa</option>
     </select>
         </li>
       </ul>
           
       <ul class="searchselect">
         <li class="selectname"><input type="submit" name="aaa" value="提交" /></li>
         <li class="selectinput">&nbsp;</li>
       </ul>
       
     </div>
     <div class="clear"></div>
	      <div class="listtitle">
           <ul class="top">
             <li class="lifonttitle">已禁止交易地区</li>
             <li class="lilistother"></li>
            
           </ul>
          <ul class="bottom">
               <li class="li_02">序号</li>  
		       <li class="li_02">禁止地区</li> 
               <li class="li_02">操作</li>    
             </ul>
         </div>
		 
		  <div class="listlist">
		    <s:iterator id="m" value="mrcList"  status="s">
               <ul class="listlistbottom">
               <li class="lil_02"><s:property value="#s.index+1" /></li>
				 <li class="lil_02"><s:property value="#m.area" /></li>
				 <li class="lil_02"><a href="#" onclick="deleteArea(<s:property value="#m.id"/>)">删除</a></li>
               </ul>
		     </s:iterator>
		     
			  <br class="clear" />
	       <ul class="listlistpage">
		 <li></li>
	       </ul>
		   </div>
     </div>
   </div>
 </div>
    </s:form>
    <script language="JavaScript" type="text/JavaScript">
    function deleteArea(areaId){
		if(confirm("确定要删除吗?")){
			window.location="../merchant/deleteArea.action?areaId="+areaId;
		}
	}
	
</script>
 <!--尾部begin-->
<%@ include file="foot.jsp"%>
<!--尾部end-->	




