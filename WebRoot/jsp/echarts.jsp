<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" pageEncoding="UTF-8"%>
<%@ taglib uri="/struts-tags" prefix="s" %>
<%@ taglib prefix="pages" uri="/xs-pages" %>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>  
    <meta charset="utf-8">  
    <title>Charts demo</title>  
   
</head>  
<body>  
   <!-- 为ECharts准备一个具备大小（宽高）的Dom -->
    <div id="main" style="height:400px"></div>
    <script src="../js/echarts.common.min.js"></script>
     <script type="text/javascript">
        // 基于准备好的dom，初始化echarts图表
        var myChart = echarts.init(document.getElementById('main')); 
        
        var option = {
        	    title : {
        	        text: '世界人口总量',
        	        subtext: '数据来自网络'
        	    },
        	    tooltip : {
        	        trigger: 'axis'
        	    },
        	    legend: {
        	        data:['2011年', '2012年']
        	    },
        	    toolbox: {
        	        show : true,
        	        feature : {
        	            mark : {show: true},
        	            dataView : {show: true, readOnly: false},
        	            magicType: {show: true, type: ['line', 'bar']},
        	            restore : {show: true},
        	            saveAsImage : {show: true}
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
        	            data : ['巴西','印尼','美国','印度','中国','世界人口(万)']
        	        }
        	    ],
        	    series : [
        	        {
        	            name:'2011年',
        	            type:'bar',
        	            data:[18203, 23489, 29034, 104970, 131744, 630230]
        	        },
        	        {
        	            name:'2012年',
        	            type:'bar',
        	            data:[19325, 23438, 31000, 121594, 134141, 681807]
        	        }
        	    ]
        	};
        	                    
        // 为echarts对象加载数据 
        myChart.setOption(option); 
    </script>  
</body>  
