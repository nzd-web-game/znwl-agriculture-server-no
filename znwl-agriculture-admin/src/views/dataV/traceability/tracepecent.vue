<template>
  <div class="box">
    <div>
      <div class="echart" id="mychart1" ref="chart"></div>
    </div>
    <div>
      <div class="echart" id="mychart2"></div>
    </div>
  </div>
</template>
<script>
import {selectRecordGroupByCity} from '@/api/agriculture/dataStatistics'
export default {
  data () {
    return {
      datas:[],
      list1:[],
      list2:[]
    }
  },
  async mounted () {
    let {rows} = await selectRecordGroupByCity()
    rows.forEach((item)=>{
      this.list1.push(item.num)
      this.list2.push(item.queryCity)
    })
    this.datas = rows.map((item)=>{
      return{
        name:item.queryCity,
        value:item.num
      }
    })
    this.initEcharts();
    this.initChart2()
  },
  methods: {
    initEcharts () {
      const color = ['#ffdc2b', '#4d8cfd', '#45e6de', '#7891D9', '#FF6B6B'];
      let sum = 0;
      for (var i of this.datas) {
        sum += i.value;
      }
      const title = {
        name: '合计次数',
        value: sum,
        unit: '次',
      };
      const rich = {
        name: {
          color: '#DEDEDE',
          fontSize: 16,
          align: 'left',
          padding: [0, 10, 20, 0],
        },
        name1: {
          color: '#DEDEDE',
          fontSize: 16,
          align: 'left',
          padding: [0, 0, 0, 10],
        },
        value: {
          color: '#ccc',
          fontSize: 14,
          align: 'left',
        },
        title: {
          color: '#fff',
          fontSize: 14,
        },
        titleUnit: {
          color: '#fff',
          fontSize: 16,
          padding: [15, 0, 0, 0],
        },
        titleValue: {
          color: '#fff',
          fontSize: 18,
          fontWeight: 700,
          padding: [15, 5, 5, 5],
        },
      };
      let option = {
        color,
        series: [
          {
            type: 'pie',
            center: ['40%', '50%'],
            radius: ['50%', '60%'],
            data: this.datas,
            startAngle: 180,
            label: {
              show: true,
              position: 'center',
              formatter: () => `{title|${title.name}}\n{titleValue|${title.value}}{titleUnit|${title.unit}}`,
              rich,
            },
          },
        ],
      };
      const myChart = this.$echarts.init(document.getElementById("mychart1"));
      myChart.setOption(option);
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    },
    initChart2 () {
      let dater = this.list1
      let option = {
        grid: {
          top: '6%',
          left: '1%',
          right: '20%',
          bottom: '30%',
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: "{b0}: {c0}"
        },

        xAxis: {
          show: false,                //是否显示x轴
          type: 'value'
        },
        yAxis: {
          type: 'category',
          inverse: true,               //让y轴数据逆向
          axisLabel: {
            show: true,
            textStyle: {
              color: '#666'       //y轴字体颜色
            },
            formatter: function (value, index) {
              return [
                '{title|' + value + '} '
              ].join('\n');
            },
            //定义富文本标签
            rich: {
              lg: {
                fontWeight: 'bold',
                fontSize: 12,       //字体默认12
                color: '#08C',
                padding: [0, 5, 0, 0]
              },
              title: {
                color: '#fff',
                fontWeight: 'lighter',

              }
            }
          },
          splitLine: { show: false },   //横向的线
          axisTick: { show: false },    //y轴的端点
          axisLine: { show: false },    //y轴的线
          data: this.list2
        },
        series: [
          {
            name: '数据内框',
            type: 'bar',
            label: {
              normal: {
                show: true,
                position: 'right', // top, right, inside, insideTop,...
                textStyle: {
                  color: 'white',
                  fontSize: 12
                },
                formatter: '{c}' + "次"
              }
            },
            itemStyle: {
              normal: {
                barBorderRadius: 30,
                color: function (params) { // 颜色定制显示（按顺序）
                  var colorList = ['#ffdc2b', '#4d8cfd', '#45e6de', '#FF6B6B', '#7891D9',];
                  return colorList[params.dataIndex]
                },
              }
            },
            barWidth: 10,

            data: dater,
          },
          {
            name: '外框',
            type: 'bar',

            itemStyle: {
              normal: {
                barBorderRadius: 30,
                color: '#285079',
              }
            },
            barGap: '-100%',
            z: 0,
            barWidth: 10,
            data: [50, 50, 50, 50, 50],
          }
        ]
      }
      const myChart = this.$echarts.init(document.getElementById("mychart2"));
      myChart.setOption(option);
     clearInterval(this.toolTipTimer)
       let index = 0
      this.toolTipTimer = setInterval(() => {
      myChart.dispatchAction({
        type: 'showTip', // 提示框
        seriesIndex: 0,
        dataIndex: index // 第几行柱子高亮
      })
      if (index == dater.length - 1) index = 0 //展示到最后一个的时候 从0开始
        else index++
      }, 2000)
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    }

  }
}
</script>
<style lang="scss" scoped>
.box {
  display: flex;
}
#mychart1 {
  width: 300px;
  height: 296px;
}
#mychart2 {
  width: 360px;
  height: 380px;
}
</style>
