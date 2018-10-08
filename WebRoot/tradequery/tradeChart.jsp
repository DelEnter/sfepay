<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>
 <%@ include file="../include/dialog.jsp"%>
<script language="JavaScript" src="../js/util.js"></script>
<script type="text/javascript" src="../js/prototype-1.6.0.2.js"></script>
<script type="text/javascript" src="../js/json2.js"></script>
<head>
    <meta charset="utf-8">
    <title>数据统计图</title>
</head>

<body>
<br/>
<table align="center">
	<tr class=TR_Title>
		 		<td>商户号</td>
		 		<td>
		 			<input type="text" id="merchantNo" name="merchantNo" value="<s:property value='merchantNo'/>"/>
		 		</td>
	 		</tr>
	 		 <tr class=TR_Title>
	 		 	<td>第一周期</td>
		 		<td>
		 			 <input id="start_time" type="text" name="startDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate'/>"/>---
		 		</td>
		 		<td colspan=2>
		 			<input type="text" id="end_time" name="endDate" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate'/>"/>
	 			</td>
	 		</tr>
	 		 <tr class=TR_Title>
	 		 	<td>第二周期</td>
		 		<td>
		 			 <input id="start_time2" type="text" name="startDate2" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='startDate2'/>"/>---
		 		</td>
		 		<td colspan=2>
		 			<input type="text" id="end_time2" name="endDate2" ondblclick="cleanDate(this.id)" size="15" value="<s:property value='endDate2'/>"/>
	 			</td>
	 		</tr>
	 		<tr class=font-align>
				<td colSpan="8" align="center"><br>
					<input type="button" onclick="queTradeChart()" value="查询" >
				</td>
				
			</tr>
	</table>
	<br/>
    <div id="main" style="height:800px;padding:30px;"></div>
    <script src="../js/echarts/echarts.js"></script>
    
    <script type="text/javascript">
    window.onload=queTradeChart();
   	function queTradeChart(){
   		
    require.config({
        paths: {
            echarts: '../js/echarts'
        }
    });
    require(
        [
            'echarts',
            'echarts/chart/bar'
        ],
        function (ec) {
            //--- 折柱 ---
            var myChart = ec.init(document.getElementById('main'));
            var merNos = [];  
            var firstData = []; 
            var lastData = []; 
            var startDate=document.getElementById('start_time').value;
            var endDate=document.getElementById('end_time').value;
            var startDate2=document.getElementById('start_time2').value;
            var endDate2=document.getElementById('end_time2').value;
            var merchantNo=document.getElementById('merchantNo').value;
            var myAjax = new Ajax.Request(
    				"getTradeChart.action?merchantNo="+merchantNo+"&startDate="+startDate+"&endDate="+endDate+"&startDate2="+startDate2+"&endDate2="+endDate2,
    				{
    					//请求方式：POST
    					method:'post',
    					//请求参数
    					//parameters:params,
    					//指定回调函数
    					onComplete: function(msg){
    						var res = JSON.parse(msg.responseText);
    						merNos=res.merNos;
    						firstData=res.firstData;
    						lastData=res.lastData;
    					},
    					//是否异步发送请求
    					asynchronous:false
    				});
            myChart.setOption({
                tooltip : {
                    trigger: 'axis'
                },
                title : {
                    text: '交易笔数统计',
                },
                legend: {
                    data:['第一周期', '第二周期']
                },
                toolbox: {
                    show : true,
                    feature : {
                        mark : {show: false},
                        dataView : {show: true, readOnly: false},
                        magicType: {show: true, type: ['line', 'bar']},
                        restore : {show: true},
                        saveAsImage : {show: false}
                    }
                },
                calculable : true,
                xAxis : [
                    {
                        type : 'value',
                        boundaryGap : [0, 0.01]
                    }
                ],
                yAxis : [
                    {
                        type : 'category',
                        data : merNos
                    }
                ],
                series : [
                    {
                        name:'第一周期',
                        type:'bar',
                        data:firstData
                    },
                    {
                        name:'第二周期',
                        type:'bar',
                        data:lastData
                    }
                ]
            });
          
        }
    );
  }
   	loadcalendar('start_time', 'start_time', '%Y-%m-%d', false, true, "cn");
    loadcalendar('end_time', 'end_time', '%Y-%m-%d', false, true, "cn");
    loadcalendar('start_time2', 'start_time2', '%Y-%m-%d', false, true, "cn");
    loadcalendar('end_time2', 'end_time2', '%Y-%m-%d', false, true, "cn");
    function cleanDate(vid){
    	document.getElementById(vid).value="";
    }
    </script>
</body>
