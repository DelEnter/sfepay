$(function() {
	//成功笔数
	successArr = [];
	//失败笔数
	failedArr = [];
	//拒付笔数
	rejectArr = [];
	//退款笔数
	refundArr = [];

	//网站数组
	dateArrurl = [];
	
	
	
	try {
		if (!data || data.length == 0)
		{
			$(".chart-container").hide();
			return;
		}
	} catch (e) {
		$(".chart-container").hide();
	}
	
	$(".chart-container").show();
	var totalSuccessCount = 0, totalFailedCount = 0, totalRefundCount = 0, totalRejectCount = 0;
	for (var i = data.length - 1 ; i >= 0; i--)
	{
		var row = data[i];
		dateArrurl.push(row[0]);
		var successCount = row[6] == null ? 0 : row[6];
		successArr.push(successCount);
		totalSuccessCount += successCount;
		
		var failedCount = row[4] == null ? 0 : row[4];
		failedArr.push(failedCount);
		totalFailedCount += failedCount;
		
		var rejectCount = row[8] == null ? 0 : row[8];
		rejectArr.push(rejectCount);
		totalRejectCount += rejectCount;
		
		var refundCount = row[10] == null ? 0 : row[10];
		refundArr.push(refundCount);
		totalRefundCount += refundCount;
	}
	
	//国家交易数量图表
	var tradeCountChart = echarts.init(document.getElementById('main'));
	
	var option = {
		    title : {
		        text: '网站交易数量统计',
		    },
		    tooltip : {
		        trigger: 'axis'
		    },
		    legend: {
		        data:['成功数量','失败数量', "拒付数量", "退款数量"]
		    },
		    toolbox: {
		        show : true,
		        feature : {
		            mark : {show: true},
		            dataView : {show: true, readOnly: false},
		            magicType : {show: true, type: ['line', 'bar', 'stack', 'tiled']},
		            restore : {show: true},
		            saveAsImage : {show: true}
		        }
		    },
		    calculable : true,
		    xAxis : [
		        {
		            type : 'category',
		            boundaryGap : false,
		            data : dateArrurl
		        }
		    ],
		    yAxis : [
		        {
		            type : 'value'
		        }
		    ],
		    series : [
		        {
		            name:'成功数量',
		            type:'line',
		            color: '#c23531',
		            smooth:true,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data:successArr
		        },
		        {
		            name:'失败数量',
		            type:'line',
		            smooth:true,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data:failedArr
		        },
		        {
		            name:'拒付数量',
		            type:'line',
		            smooth:true,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data:rejectArr
		        },
		        {
		            name:'退款数量',
		            type:'line',
		            smooth:true,
		            itemStyle: {normal: {areaStyle: {type: 'default'}}},
		            data:refundArr
		        }
		    ]
		};

    // 使用刚指定的配置项和数据显示图表。
    tradeCountChart.setOption(option);
    
    //成功失败占比
    var circleChart = echarts.init(document.getElementById('circle'));
    var circle_option = {
    	    title : {
    	        text: '成功失败占比',
    	        x:'center'
    	    },
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	    },
    	    legend: {
    	        orient : 'vertical',
    	        x : 'left',
    	        data:['交易成功','交易失败']
    	    },
    	    toolbox: {
    	        show : true,
    	        feature : {
    	            mark : {show: true},
    	            dataView : {show: true, readOnly: false},
    	            magicType : {
    	                show: true, 
    	                type: ['pie', 'funnel'],
    	                option: {
    	                    funnel: {
    	                        x: '25%',
    	                        width: '50%',
    	                        funnelAlign: 'left',
    	                        max: 1548
    	                    }
    	                }
    	            },
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    calculable : true,
    	    series : [
    	        {
    	            name:'交易量占比',
    	            type:'pie',
    	            radius : '55%',
    	            center: ['50%', '60%'],
    	            data:[
    	                {value:totalSuccessCount, name:'交易成功'},
    	                {value:totalFailedCount, name:'交易失败'},
    	            ]
    	        }
    	    ]
    	};
    circleChart.setOption(circle_option);
    //拒付成功占比图表
    var rejectCircleChart = echarts.init(document.getElementById('reject_circle'));
    var reject_circle_option = {
    	    title : {
    	        text: '拒付和未拒付占比',
    	        x:'center'
    	    },
    	    tooltip : {
    	        trigger: 'item',
    	        formatter: "{a} <br/>{b} : {c} ({d}%)"
    	    },
    	    legend: {
    	        orient : 'vertical',
    	        x : 'left',
    	        data:['未拒付','拒付']
    	    },
    	    toolbox: {
    	        show : true,
    	        feature : {
    	            mark : {show: true},
    	            dataView : {show: true, readOnly: false},
    	            magicType : {
    	                show: true, 
    	                type: ['pie', 'funnel'],
    	                option: {
    	                    funnel: {
    	                        x: '25%',
    	                        width: '50%',
    	                        funnelAlign: 'left',
    	                        max: 1548
    	                    }
    	                }
    	            },
    	            restore : {show: true},
    	            saveAsImage : {show: true}
    	        }
    	    },
    	    calculable : true,
    	    series : [
    	        {
    	            name:'拒付和未拒付占比',
    	            type:'pie',
    	            radius : '55%',
    	            center: ['50%', '60%'],
    	            data:[
    	                {value:totalSuccessCount - totalRejectCount , name:'未拒付'},
    	                {value:totalRejectCount, name:'拒付'},
    	            ]
    	        }
    	    ]
    	};
    rejectCircleChart.setOption(reject_circle_option);
});