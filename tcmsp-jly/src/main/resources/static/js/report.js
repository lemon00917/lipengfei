// 基于准备好的dom，初始化echarts实例
		
        function initChart(List1,List2,H1,H2,H3,L1,L2,L3,M){
        	var myChart = echarts.init(document.getElementById('report_24jl'));
        	var jlcgImgBase64;
            var seriesData = [];  //总能耗数值数组
           // var H1 = 5, H2 = 10, H3 = 20, M = 0, L1 = -5, L2 = -10, L3 = -20;
            var s1 = '5';
            var s2 = '15';
            var s3 = '25';
            //判断数值动态设置颜色
            option2 = {
                title: {
                    text: '经络传感数据'
                },
                animation: false, //动画失效
                color: ['#F8CB00'],
                tooltip: {
                    trigger: 'axis',
                    axisPointer: { // 坐标轴指示器，坐标轴触发有效
                        type: 'shadow' // 默认为直线，可选为：'line' | 'shadow'
                    }
                },
                grid: {
                    right: '4%',
                    bottom: '3%',
                    containLabel: true
                },
                xAxis: [{
                    type: 'category',
                    data: ['肺经','大肠','胃经','脾经','心经','小肠','膀胱','肾经','心包','三焦','胆经','肝经'],
                    axisTick: {
                        alignWithLabel: true
                    }
                }],
                yAxis: {},
                series: [{
                    name: '左',
                    type: 'bar',
//                    barWidth: '65%',
                    data: List1,
                    markLine: {                      //开始标预警线

                        itemStyle: {
                            normal: {
                                borderWidth: 1,

                                lineStyle: {

                                    type: 'dash',
                                    color: '#333 ',
                                    width: 1,
                                },

                                label: {
                                    formatter: '',
                                    textStyle: {
                                        fontSize: 16,
                                        fontWeight: "bolder",
                                    },
                                }
                            }
                            
                        },
                        data: [
                            [{
                                coord: ['肺经', H3]
                            }, {
                                coord: ['肝经', H3]
                            }],
                            [{
                                coord: ['肺经', H2]
                            }, {
                                coord: ['肝经', H2]
                            }],
                            [{
                                coord: ['肺经', H1]
                            }, {
                                coord: ['肝经', H1]
                            }],
                            [{
                                coord: ['肺经', L1]
                            }, {
                                coord: ['肝经', L1]
                            }],
                            [{
                                coord: ['肺经', L2]
                            }, {
                                coord: ['肝经', L2]
                            }],
                            [{
                                coord: ['肺经', L3]
                            }, {
                                coord: ['肝经', L3]
                            }]
                        ]
                    },
                    itemStyle: {
                        normal: {
                            color: function(params) {      //根据预警线的值 显示对应的颜色      
                                // build a color map as your need.
                                var colorList = ['#B3EE3A', '#436EEE', '#CDCD00', '#EE3B3B'];
                                if(params.data > H3) {
                                    return colorList[3];
                                } else if(params.data >= H2 && params.data <= H3) {
                                    return colorList[2];
                                }else if(params.data >= H1 && params.data <= H2) {
                                    return colorList[1];
                                }else if(params.data >= M && params.data <= H1) {
                                    return colorList[0];
                                }else if(params.data >= L1 && params.data <= M) {
                                    return colorList[0];
                                }else if(params.data >= L2 && params.data <= L1) {
                                    return colorList[1];
                                }else if(params.data >= L3 && params.data <= L2) {
                                    return colorList[2];
                                }else if(params.data < L3) {
                                    return colorList[3];
                                }
                            }

                        },
                    }
                },{
                    name: '右',
                    type: 'bar',
//                    barWidth: '65%',
                    data:List2,
                    markLine: {                      //开始标预警线

                        itemStyle: {
                            normal: {
                                borderWidth: 1,

                                lineStyle: {

                                    type: 'dash',
                                    color: '#333 ',
                                    width: 2,
                                },

                                label: {
                                    formatter: '12.6',
                                    textStyle: {
                                        fontSize: 16,
                                        fontWeight: "bolder",
                                    },
                                }
                            },

                        }
                    },
                    itemStyle: {
                        normal: {
                            color: function(params) {      //根据预警线的值 显示对应的颜色      
                            	// build a color map as your need.
                                var colorList = ['#B3EE3A', '#436EEE', '#CDCD00', '#EE3B3B'];
                                if(params.data > H3) {
                                    return colorList[3];
                                } else if(params.data >= H2 && params.data <= H3) {
                                    return colorList[2];
                                }else if(params.data >= H1 && params.data <= H2) {
                                    return colorList[1];
                                }else if(params.data >= M && params.data <= H1) {
                                    return colorList[0];
                                }else if(params.data >= L1 && params.data <= M) {
                                    return colorList[0];
                                }else if(params.data >= L2 && params.data <= L1) {
                                    return colorList[1];
                                }else if(params.data >= L3 && params.data <= L2) {
                                    return colorList[2];
                                }else if(params.data < L3) {
                                    return colorList[3];
                                }
                            }

                        },
                    }
                }],
            };

            myChart.setOption(option2);
            jlcgImgBase64 = myChart.getDataURL();
    		//console.log(jlcgImgBase64)
            document.getElementById('jlcg').setAttribute('src',jlcgImgBase64)
        }
        
        $(document).ready(function(params){
        	 var id=$("#userid").val();
        	$.ajax({
                type: "POST",
                url: contextPath + "/jlzb/getChartData?id="+id,
//                data: data,
                dataType:"json",
                success: function(res) {
                    console.log(res);
                    initChart(res.list1,res.list2,res.h1,res.h2,res.h3,res.l1,res.l2,res.l3,res.m);
                },
                error: function(XMLHttpRequest, textStatus, errorThrown) {
                    
                }
            });
        })
