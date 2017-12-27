var currentVisitChart = '';//当前正在访问的人数量
var currentVisitOpt = '';//当前正在访问的人数量配置

var cpuChart = '';//cpu图表
var cpuOpt = '';//cpu图表配置

var mysqlChart = ''//mysql图表
var mysqlOpt = ''//mysql图表配置

var memoryChart = ''//内存图表
var memoryOpt = ''//内存图表配置

var vedioDataExportChart = ''//视频数据导出图表
var vedioDataExportOpt = ''//视频数据导出配置

var potentRiskChart = ''//潜在风险图表
var potentRiskOpt = ''//潜在风险图表配置

var dataExportChart = ''//数据导出统计
var dataExportOpt = ''//数据导出配置
var activeImg = new Image();
activeImg.src = '../skin/default/images/pattern3.png';

var negativeImg = new Image();
negativeImg.src = '../skin/default/images/pattern2.png';

var grayImg = new Image();
grayImg.src = '../skin/default/images/pattern1.png';

  $(function () {
    getCurrentVisitNumber();//当前访问人数信息
    getServerInfo();//服务器信息
    vedioDataExport();//视频导出
    potentRiskInfo();//潜在风险
    dataExport();//数据导出统计
    // getUserData();//获取用户数据先不用
    initEvents(); //初始化事件
});
/**
 * 初始化事件
 *
 */
function initEvents() {
    //窗口缩放调整图表
    $(window).resize(function () {
        if (currentVisitChart) {
          currentVisitChart.resize();
        }
        if (cpuChart) {
          cpuChart.resize();
        }
        if (mysqlChart) {
          mysqlChart.resize();
        }
        if (memoryChart) {
          memoryChart.resize();
        }
        if (vedioDataExportChart) {
          vedioDataExportChart.resize();
        }
        if (potentRiskChart) {
          potentRiskChart.resize();
        }
        if (dataExportChart) {
          dataExportChart.resize();
        }
    });

}
// 获取正在访问人数的信息
function getCurrentVisitNumber(){
  currentVisitChart = echarts.init(document.getElementById('curr_Visit_Chart'));
  currentVisitOpt.series[0].data[0].value = 40;
  currentVisitOpt.grid.top=100;

  currentVisitChart.setOption(currentVisitOpt);
  $("#curr_Visit_Chart canvas").css("top","50px");
}
// 系统CPU内存等信息
function getServerInfo(){
  cpuChart = echarts.init(document.getElementById('serverinfo_cpu_chart'));
  mysqlChart = echarts.init(document.getElementById('serverinfo_mysql_chart'));
  memoryChart = echarts.init(document.getElementById('serverinfo_mem_chart'));
  cpuChart.setOption(cpuOpt);
  mysqlChart.setOption(mysqlOpt);
  memoryChart.setOption(memoryOpt);
}
// 视频数据导出
function vedioDataExport(){
  vedioDataExportChart = echarts.init(document.getElementById('vedio_export'));
  vedioDataExportChart.setOption(vedioDataExportOpt);
}
//潜在风险
function potentRiskInfo(){
  potentRiskChart = echarts.init(document.getElementById('potent_risk'));
  potentRiskChart.setOption(potentRiskOpt);
}
//数据导出
function dataExport(){
  dataExportChart = echarts.init(document.getElementById('data_export'));
  dataExportChart.setOption(dataExportOpt);
}
//当前正在访问的人数量配置
currentVisitOpt = {
  // tooltip: {
  //   formatter: "{a} <br/>{b} : {c}%"//饼图、仪表盘、漏斗图: {a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）
  // },
  grid: {
    // left: 0,
    y: 150,
    // right: 0,
    // bottom: 0
    y2:0,
  },
  series: [{
    name: "当前访问的人数",
    type: "gauge",
    radius:"95%",
    startAngle:200,
    endAngle:-20,
    splitNumber:50,
    axisLine: {//仪表盘轴线相关配置
      "lineStyle": {
        "color": [
          [1, new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
            offset: 0,
            color: '#50C9BE'
          }, {
            offset: 1,
            color: '#D3F546'
          }])],
          [1, "#cacaca"]
        ],
        "width": 35,
      },

    },
    splitLine: {
      "show": false
    },
    axisTick: {//刻度样式。
      lineStyle: {
        color:'#fff',
        width: 2
      },
      length: 35,
      splitNumber:1
    },
    pointer: {
      show:false
    },
    axisLabel: {//刻度标签
      show:false,
    },
    detail: {
      formatter: "{value}%",
      offsetCenter: [0, -10],
      textStyle: {
        fontSize: 32,
        fontWeight:'bold',
        color: "#50C892"
      }
    },
    title: {
      offsetCenter: [0, "60%"]
    },
    data: [{
      name: "",
      value: 31
    }]
  }]
};
//cpu信息
cpuOpt = {
  tooltip: {
    trigger: 'item',
    formatter: "{a}：{c}%"
  },
  grid: {
    left: 0,
    top: 0,
    right: 0,
    bottom: 0
  },
  xAxis: [{
    type: 'value',
    show: false,
  }],
  yAxis: [{
    type: 'category',
    show: false,
  }],
  series: [{
    type: 'bar',
    barWidth: 10,
    barGap: '-100%',
    silent: true,
    itemStyle: {
      normal: {
        color: {
          image: grayImg,
          repeat: 'repeat'
        }
      }
    },
    data: [100],
  }, {
    name: '摄像头在线率',
    type: 'bar',
    barWidth: 10,
    itemStyle: {
      normal: {
        color: {
          image: activeImg,
          repeat: 'repeat'
        }
      }
    },
    data: [50]
  }
  ]
}
//mysql信息
mysqlOpt = {
  tooltip: {
    trigger: 'item',
    formatter: "{a}：{c}%"
  },
  grid: {
    left: 0,
    top: 0,
    right: 0,
    bottom: 0
  },
  xAxis: [{
    type: 'value',
    show: false,
  }],
  yAxis: [{
    type: 'category',
    show: false,
  }],
  series: [{
    type: 'bar',
    barWidth: 10,
    barGap: '-100%',
    silent: true,
    itemStyle: {
      normal: {
        color: {
          image: grayImg,
          repeat: 'repeat'
        }
      }
    },
    data: [100],
  }, {
    name: 'NVR在线率',
    type: 'bar',
    barWidth: 10,
    itemStyle: {
      normal: {
        color: {
          image: negativeImg,
          repeat: 'repeat'
        }
      }
    },
    data: [50]
  }
  ]
}
//内存信息
memoryOpt = {
  tooltip: {
    trigger: 'item',
    formatter: "{a}：{c}%"
  },
  grid: {
    left: 0,
    top: 0,
    right: 0,
    bottom: 0
  },
  xAxis: [{
    type: 'value',
    show: false,
  }],
  yAxis: [{
    type: 'category',
    show: false,
  }],
  series: [{
    type: 'bar',
    barWidth: 10,
    barGap: '-100%',
    silent: true,
    itemStyle: {
      normal: {
        color: {
          image: grayImg,
          repeat: 'repeat'
        }
      }
    },
    data: [100],
  }, {
    name: '图像采集率',
    type: 'bar',
    barWidth: 10,
    itemStyle: {
      normal: {
        color: {
          image: negativeImg,
          repeat: 'repeat'
        }
      }
    },
    data: [80]
  }
  ]
}
// 视频数据导出信息
vedioDataExportOpt = {
  //color: ['#3398DB'],
  tooltip: {
    trigger: 'axis',
      backgroundColor:'rgba(255,255,255,0.8)',
      extraCssText: 'box-shadow: 0 0 8px rgba(0, 0, 0, 0.3);',
      textStyle:{
      color:'#6a717b',
    },

  },
  grid: {
    top:'0%',
    left: '3%',
    right: '4%',
    bottom: '5%',
    containLabel: true
  },
  yAxis: [{
    type: 'category',
    data: ['OPPO R9S','Vivo X9','OPPO R9m','Vivo X7','Hisense M20-T','OPPO A37m','OPPO A57','OPPO A33','OPPO R11S','OPPO R11S'],

    axisTick: {
      alignWithLabel: true,
    },
    axisLabel: {
      margin: 5,
      textStyle: {
        fontSize: 12,
        color:'#94999f'
      }
    },
    axisLine: {
      lineStyle: {
        color: '#dbe0e6'
      }
    },

  }],
    xAxis: [{
  type: 'value',
  axisLabel: {
    margin: 5,
    textStyle: {
      fontSize: 12,
      color:'#94999f'
    }
  },
  axisLine: {
    lineStyle: {
      color: '#fff'
    }
  },
  splitLine: {
    lineStyle: {
      color: '#dbe0e6'
    }
  }
}],
  series: [{
  name: 'Top 10',
  type: 'bar',
  barWidth:15,
  data: [7700, 8800, 9900, 11100, 14200, 16000, 18400, 20500, 22600, 24700],
  label: {
    normal: {
      show: true,
      position: 'insideRight',
      textStyle: {
        color: 'white' //color of value
      }
    }
  },
  itemStyle: {
    normal: {
      color: new echarts.graphic.LinearGradient(0, 0, 1, 0, [{
        offset: 0,
        color: '#64bdc8' // 0% 处的颜色
      }, {
        offset: 1,
        color: '#00c484' // 100% 处的颜色
      }], false),
      barBorderRadius: [0, 15,15, 0],
      shadowColor: 'rgba(0,0,0,0.1)',
      shadowBlur: 3,
      shadowOffsetY: 3
    }
  }
}]
};
// 潜在风险配置
potentRiskOpt = {
  title: {
    text: "用户总数",
    textStyle: {
      fontSize: 14,
      color: "#9C9C9C"
    },
    subtext: "20",
    subtextStyle: {
      fontWeight: "bold",
      fontSize: 22,
      color: "#6BCFA1"
    },
    left: "center",
    top: "35%"
  },
  color: ['#B9E9D3', '#50C892', '#43A076', '#48ecf6', '#ffcd5a', '#16c092'],
  // 饼图、仪表盘、漏斗图: {a}（系列名称），{b}（数据项名称），{c}（数值）, {d}（百分比）
  tooltip: {
    trigger: 'item',
    formatter: "{b} : {c} ({d}%)"
  },
  legend: {
    orient: 'horizontal',
    bottom: '40px',
    itemGap: 10,
    itemWidth: 10,
    itemHeight: 10,
    data: ['允许打印', '明文导出', '密文导出', '自主外发', '屏幕无水印', '打印无水印']
  },
  series: [
    {
      name: '',
      type: 'pie',
      radius: ['28%', '40%'],
      avoidLabelOverlap: true,
      center: ['50%', '40%'],
      hoverOffset:80,
      data: [
        {value: 20, name: '允许打印'},
        {value: 20, name: '明文导出'},
        {value: 20, name: '密文导出'},
        {value: 20, name: '自主外发'},
        {value: 20, name: '屏幕无水印'},
        {value: 20, name: '打印无水印'}
      ],
      itemStyle: {
        emphasis: {
          shadowBlur: 10,
          shadowOffsetX: 0,
          shadowColor: 'rgba(0, 0, 0, 0.5)'
        }
      },
      labelLine:{
        normal:{
          show:true,
          length2: 40,
          lineStyle:{
            color:"#ededed",
          }
        }
      },
      label: {
        normal: {
          textStyle: {
            color: '#000',
            fontSize:14
          }
        }
      }
    }
  ]
};

var date = ['2017-07-15 21:55:00', '2017-07-15 21:50:00', '2017-07-15 21:45:00', '2017-07-15 21:40:00', '2017-07-15 21:35:00', '2017-07-15 21:30:00', '2017-07-15 21:25:00', '2017-07-15 21:20:00'];
var data = [1,100,30,150,250,350,295,349];


dataExportOpt = {
  title: {
    show:true,
    text: '数据导出统计',
    textStyle:{
      fontSize:14,
      fontFamily:"Microsoft YaHei",
      fontWeight:100,
      verticalAlign:"middle",
      lineHeight:40,
      height:40,

    },
    padding: [
      15,  // 上
      0, // 右
      0,  // 下
      10, // 左
    ]
  },
  tooltip: {
    trigger: 'axis'
  },
  xAxis: {
    type : 'category',
    boundaryGap : false,
    splitLine: {
      show: false,
      lineStyle: {
        color: '#eee'
      }
    },
    axisLine: {
      lineStyle: {
        color: '#ccc'
      }
    },
    axisLabel:{
      textStyle:{
        color:"#666"
      }
    },
    data: ['2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15', '2017-07-15']
  },
  yAxis : [
    {
      type : 'value',
      splitLine: {
        show: true,
        lineStyle: {
          color: '#eee'
        }
      },
      axisLine: {
        show: false,
        lineStyle: {
          color: '#ccc'
        }
      },
      axisLabel:{
        textStyle:{
          color:"#666"
        }
      }
    }
  ],
  grid:{
    top:"70px",
    left: "80px",
    right: "80px",
    bottom:"12%"
  },
  visualMap: {
    top: 20,
    right: 5,
    pieces: [{
      gt: 0,
      lte: 100,
      color: '#68D7E5'
    }, {
      gt: 100,
      lte: 200,
      color: '#50C892'
    }, {
      gt: 200,
      lte: 300,
      color: '#FBAA59'
    }, {
      gt: 300,
      color: '#CC0A39'
    }],
    outOfRange: {
      color: '#fff'
    }
  },
  series: {
    name: '',
    type: 'line',
    smooth:true,
    data: [10,100,30,150,250,350,295,349,1,22,33,444,55,233,432,22,11],
  }
};













/**
 * 日期选择控件
 *
 */
// function ininDateTimePicker() {
//     $('#openWind .j-input-date').val(new Date().Format('yyyy-MM-dd HH:mm')).datetimepicker({
//         language: 'zh-CN',
//         format: 'yyyy-mm-dd hh:ii',
//         autoclose: true,
//         minView: 0,
//     }).on('changeDate', function () {
//         $('.btn-active').removeClass('btn-active');
//         $('.btn-date').addClass('btn-active');
//     });
// }
