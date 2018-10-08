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
		<option value="AF">AF Afghanistan</option>
		<option value="AL">AL Albania</option>
		<option value="AD">AD Andorra</option>
		<option value="AI">AI Angola</option>
		<option value="AM">AM Armenia</option>
		<option value="AW">AW Aruba</option>
		<option value="AU">AU Australia</option>
		<option value="AE">AE United Arab Emirates</option>
		<option value="AR">AR Argentina</option>
		<option value="AG">AG Antigua and Barbuda</option>
		<option value="AT">AT Austria</option>
		<option value="AZ">AZ Azerbaijan</option>
		<option value="AN">AN Netherlands Antilles</option>
		<option value="BB">BB Barbados</option>
		<option value="BD">BD Bangladesh</option>
		<option value="BE">BE Belgium</option>
		<option value="BZ">BZ Belize</option>
		<option value="BJ">BJ Benin</option>
		<option value="BT">BT Bhutan</option>
		<option value="BO">BO Bolivia</option>
		<option value="BA">BA Bosnia and Herzegovina</option>
		<option value="BW">BW Botswana</option>
		<option value="BN">BN Brunei</option>
		<option value="BG">BG Bulgaria</option>
		<option value="BH">BH Bahrain</option>
		<option value="BM">BM Bermuda</option>
		<option value="BR">BR Brazil</option>
		<option value="BS">BS Bahamas</option>
		<option value="BF">BF Burkina Faso</option>
		<option value="BI">BI Burundi</option>
		<option value="CM">CM Cameroon</option>
		<option value="CA">CA Canada</option>
		<option value="CV">CV Cape Verde</option>
		<option value="CF">CF Central African Republic</option>
		<option value="KM">KM Comoros</option>
		<option value="CG">CG Congo</option>
		<option value="CH">CH Switzerland</option>
		<option value="CL">CL Chile</option>
		<option value="CN">CN China</option>
		<option value="CO">CO Colombia</option>
		<option value="CR">CR Costa Rica</option>
		<option value="CY">CY Cyprus</option>
		<option value="CZ">CZ Czech Republic</option>
		<option value="DE">DE Germany</option>
		<option value="DK">DK Denmark</option>
		<option value="DJ">DJ Djibouti</option>
		<option value="DZ">DZ Algeria</option>
		<option value="DO">DO Dominican Republic</option>
		<option value="EC">EC Ecuador</option>
		<option value="EG">EG Egypt</option>
		<option value="ER">ER Eritrea</option>
		<option value="EE">EE Estonia</option>
		<option value="ET">ET Ethiopia</option>
		<option value="ES">ES Spain</option>
		<option value="EH">EH Western Sahara</option>
		<option value="FJ">FJ Fiji</option>
		<option value="FR">FR France</option>
		<option value="FI">FI Finland</option>
		<option value="GF">GF French Guiana</option>
		<option value="GA">GA Gabon</option>
		<option value="GM">GM Gambia</option>
		<option value="GE">GE Georgia</option>
		<option value="GH">GH Ghana</option>
		<option value="GI">GI Gibraltar</option>
		<option value="GD">GD Grenada</option>
		<option value="GR">GR Greece</option>
		<option value="GP">GP Guadeloupe</option>
		<option value="GT">GT Guatemala</option>
		<option value="GY">GY Guyana</option>
		<option value="GW">GW Guinea-Bissau</option>
		<option value="GB">GB United Kingdom</option>
		<option value="HT">HT Haiti</option>
		<option value="HN">HN Honduras</option>
		<option value="HK">HK Hong Kong</option>
		<option value="HU">HU Hungary</option>
		<option value="HR">Croatia</option>
		<option value="ID">ID The Republic of Indonesia</option>
		<option value="IE">IE Ireland</option>
		<option value="IL">IL Israel</option>
		<option value="IN">IN India</option>
		<option value="IS">IS Iceland</option>
		<option value="IT">IT Italy</option>
		<option value="JM">JM Jamaica</option>
		<option value="JP">JP Japan</option>
		<option value="JO">JO Jordan</option>
		<option value="KZ">KZ Kazakhstan</option>
		<option value="KE">KE Kenya</option>
		<option value="KG">KG Kyrgyzstan</option>
		<option value="KR">KR Korea</option>
		<option value="KW">KW Kuwait</option>
		<option value="KN">KN Saint Kitts and Nevis</option>
		<option value="LB">LB Lebanon</option>
		<option value="LY">LY Libyan Arab Jamahiriya</option>
		<option value="LI">LI Liechtenstein</option>
		<option value="LK">LK Sri Lanka</option>
		<option value="LT">LT Lithuania</option>
		<option value="LU">LU Luxembourg</option>
		<option value="LV">LV Latvia</option>
		<option value="LC">LC Saint Lucia</option>
		<option value="MC">MC Monaco</option>
		<option value="MO">MO Macau</option>
		<option value="Mk">Mk Macedonia</option>
		<option value="MG">MG Madagascar</option>
		<option value="MW">MW Malawi</option>
		<option value="MV">MV Maldives</option>
		<option value="ML">ML Mali</option>
		<option value="MT">MT Malta</option>
		<option value="MQ">MQ Martinique</option>
		<option value="MR">MR Mauritania</option>
		<option value="MU">MU Mauritius</option>
		<option value="ME">ME Mexico</option>
		<option value="MY">MY Malaysia</option>
		<option value="MD">MD Moldova</option>
		<option value="MN">MN Mongolia</option>
		<option value="MA">MA Morocco</option>
		<option value="MZ">MZ Mozambique</option>
		<option value="NA">NA Namibia</option>
		<option value="NP">NP Nepal</option>
		<option value="NL">NL Netherlands</option>
		<option value="NI">NI Nicaragua</option>
		<option value="NE">NE Niger</option>
		<option value="NG">NG Nigeria</option>
		<option value="NO">NO Norway</option>
		<option value="NZ">NZ New Zealand</option>
		<option value="OM">OM Oman</option>
		<option value="PK">PK Pakistan</option>
		<option value="PA">PA Panama</option>
		<option value="PG">PG Papua New Guinea</option>
		<option value="PY">PY Paraguay</option>
		<option value="PE">PE Peru</option>
		<option value="PH">PH Philippines</option>
		<option value="PL">PL Poland</option>
		<option value="PT">PT Portugal</option>
		<option value="QA">QA Qatar</option>
		<option value="RO">RO Romania</option>
		<option value="RU">RU Russian Federation</option>
		<option value="RW">RW Rwanda</option>
		<option value="SM">SM San Marino</option>
		<option value="ST">ST Sao Tome and Principe</option>
		<option value="SA">SA Saudi Arabia</option>
		<option value="SN">SN Senegal</option>
		<option value="RS">RS Serbia</option>
		<option value="SZ">SZ Swaziland</option>
		<option value="SC">SC Seychelles</option>
		<option value="SL">SL Sierra Leone</option>
		<option value="SO">SO Somalia</option>
		<option value="SR">SR Suriname</option>
		<option value="SW">SW Sweden</option>
		<option value="SG">SG Singapore</option>
		<option value="SK">SK Slovakia</option>
		<option value="SI">SI Slovenia</option>
		<option value="SV">SV El Salvador</option>
		<option value="SY">SY Syrian Arab Republic</option>
		<option value="TJ">TJ Tajikistan</option>
		<option value="TZ">TZ Tanzania</option>
		<option value="TH">TH Thailand</option>
		<option value="TG">TG Togo</option>
		<option value="TN">TN Tunisia</option>
		<option value="TR">TR Turkey</option>
		<option value="TT">TT Trinidad and Tobago</option>
		<option value="TW">TW Taiwan, Province of China</option>
		<option value="TM">TM Turkmenistan</option>
		<option value="TC">TC Turks and Caicos Islands</option>
		<option value="UG">UG Uganda</option>
		<option value="UA">UA Ukraine</option>
		<option value="US">US United States</option>
		<option value="UY">UY Uruguay</option>
		<option value="UZ">UZ Uzbekistan</option>
		<option value="VE">VE Venezuela</option>
		<option value="VC">VC Saint Vincent and the Grenadines</option>
		<option value="VN">VN Vietnam</option>
		<option value="YE">YE Yemen</option>
		<option value="ZM">ZM Zambia</option>
		<option value="ZA">ZA South Africa</option>
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




