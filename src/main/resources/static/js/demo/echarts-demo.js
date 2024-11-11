$(function () {
    //发起请求

    var lineChart = echarts.init(document.getElementById("echarts-line-chart"));
    var lineoption = {
        title : {
            text: '周销售'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['销售金额','销售单量']
        },
        grid:{
            x:40,
            x2:40,
            y2:24
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                boundaryGap : false,
                data : []
            }
        ],
        yAxis : [
            {
                type : 'value',
                axisLabel : {
                    formatter: '{value}'
                }
            }
        ],
        series : [
            {
                name:'销售金额',
                type:'line',
                data:[],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            },
            {
                name:'销售单量',
                type:'line',
                data:[],
                markPoint : {
                    data : [
                        {name : '周最低', value : -2, xAxis: 1, yAxis: -1.5}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            }
        ]
    };

    var barChart = echarts.init(document.getElementById("echarts-bar-chart"));
    var baroption = {
        title : {
            text: '日分类销售数据'
        },
        tooltip : {
            trigger: 'axis'
        },
        legend: {
            data:['销售额','销售量']
        },
        grid:{
            x:30,
            x2:40,
            y2:24
        },
        calculable : true,
        xAxis : [
            {
                type : 'category',
                data : []
            }
        ],
        yAxis : [
            {
                type : 'value'
            }
        ],
        series : [
            {
                name:'销售额',
                type:'bar',
                data:[],
                markPoint : {
                    data : [
                        {type : 'max', name: '最大值'},
                        {type : 'min', name: '最小值'}
                    ]
                },
                markLine : {
                    data : [
                        {type : 'average', name: '平均值'}
                    ]
                }
            },
            {
                name:'销售量',
                type:'bar',
                data:[],
                markLine : {
                    data : [
                        {type : 'average', name : '平均值'}
                    ]
                }
            }
        ]
    };
    $.ajax("/preIndex",{
        success(res){
            let weeks = res.weekVos;
            for (let week of weeks) {
                lineoption.xAxis[0].data.push(week.key);
                lineoption.series[0].data.push(week.price);
                lineoption.series[1].data.push(week.number);
            }
            let categoryVos = res.categoryVos;
            for(let category of categoryVos){
                baroption.xAxis[0].data.push(category.key);
                baroption.series[0].data.push(category.price);
                baroption.series[1].data.push(category.number);
            }
            lineChart.setOption(lineoption);
            barChart.setOption(baroption);
        }
    })

    $(window).resize(lineChart.resize);
    window.onresize = barChart.resize;

});
