<template>
  <div>
    <div class="echart" id="mychart5" style="height: 250px"></div>
  </div>
</template>
<script>
import {selectRecordGroupBySellpro} from '@/api/agriculture/dataStatistics'
export default {
  data () {
    return {
      list:[],
      className:[]
    }
  },
  async mounted () {
    let {rows} = await selectRecordGroupBySellpro()
    rows.forEach((item)=>{
      this.list.push(item.num)
      this.className.push(item.sellproName)
    })
    this.initEcharts();
  },
  methods: {
    initEcharts () {
      var colorList = ['#39B3FF', '#47E0E0', '#7891D9', '#FF6B6B', '#C7A530',];
      let option = {
        grid: {
          left: '2%',
          right: '5%',
          bottom: '4%',
          top: '3%',
          containLabel: true
        },
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow'
          },
          formatter: function (params) {
            return params[0].name + '<br/>' +
              "<span style='display:inline-block;margin-right:5px;border-radius:10px;width:9px;height:9px;background-color:rgba(36,207,233,0.9)'></span>" + params[0].value+'次'
          }
        },
        xAxis: {
          type: 'value',
          axisLine: {
            show: true
          },
          axisLabel: {
            show: true,
            textStyle: {
              color: '#ccefff',fontSize:'14'
            },
          },
          splitLine: {
            show: true,
            lineStyle:{
              color:'rgb(33, 62, 73)'
            }
          },
        },
        yAxis: [{
          type: 'category',
          inverse: true,
          axisLabel: {
            show: true,
            textStyle: {
              color: '#ccefff',
              fontSize:'14'
            },
          },
          splitLine: {
            show: false
          },
          axisTick: {
            show: true
          },
          axisLine: {
            show: true,
            lineStyle:{
              color:'rgb(33, 62, 73)'
            }
          },
          data: this.className
        }],
        series: [{
          name: '完成率',
          type: 'bar',
          zlevel: 1,
          itemStyle: {
            normal: {
              borderRadius: [0, 10,10,0] ,// 设置两个数值
              color: (params) => {
                return colorList[params.dataIndex]
              }
            },
          },
          barWidth: 12,
          data: this.list
        }
        ]
      };
      const myChart = this.$echarts.init(document.getElementById("mychart5"));
      myChart.setOption(option); 
      clearInterval(this.toolTipTimer)
       let index = 0
      this.toolTipTimer = setInterval(() => {
      myChart.dispatchAction({
        type: 'showTip', // 提示框
        seriesIndex: 0,
        dataIndex: index // 第几行柱子高亮
      })
      if (index == this.list.length - 1) index = 0 //展示到最后一个的时候 从0开始 
        else index++
      }, 3000)
      window.addEventListener("resize", () => {
        myChart.resize();
      });
    }
  }
}
</script>
<style lang="scss" scoped>
</style>