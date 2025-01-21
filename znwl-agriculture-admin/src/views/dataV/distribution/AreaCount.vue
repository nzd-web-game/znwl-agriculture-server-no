<template>
  <div class="w100 h100">
    <div class="echart" id="mychart" style="height: 100%"></div>
  </div>
</template>

<script>
export default {
  name: '',
  mixins: [],
  components: {},
  props: {},
  data() {
    return {};
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
    this.initEcharts();
  },
  destroyed() {},
  methods: {
    initEcharts() {
      let data = [
        '产业园规划',
        '标准农田',
        '种植业',
        '标注养殖',
        '养殖业',
        '高标准农田',
      ];
      const option = {
        tooltip: {
          trigger: 'axis',
          axisPointer: {
            type: 'shadow',
          },
        },
        grid: {
          top: '10%',
          right: '3%',
          left: '5%',
          bottom: '10%',
        },
        xAxis: [
          {
            type: 'category',
            data: data,
            axisLine: {
              lineStyle: {
                color: '#FFFFFF',
              },
            },
            axisLabel: {
              margin: 10,
              color: '#e2e9ff',
              textStyle: {
                fontSize: 12,
              },
            },
            axisTick: {
              show: false,
            },
          },
        ],
        yAxis: [
          {
            axisLabel: {
              formatter: '{value}',
              color: '#e2e9ff',
            },
            axisTick: {
              show: false,
            },
            axisLine: {
              show: false,
              lineStyle: {
                color: 'rgba(0,186,255,.6)',
              },
            },
            splitLine: {
              lineStyle: {
                color: 'rgba(255,255,255,0.12)',
              },
            },
          },
        ],
        series: [
          {
            type: 'bar',
            data: [80, 80, 97, 53, 95, 26],
            barWidth: '14px',
            itemStyle: {
              normal: {
                color: this.$echarts.graphic.LinearGradient(
                  0,
                  0,
                  0,
                  1,
                  [
                    {
                      offset: 0,
                      color: 'rgba(0,244,255,1)', // 0% 处的颜色
                    },
                    {
                      offset: 1,
                      color: 'rgba(0,77,167,1)', // 100% 处的颜色
                    },
                  ],
                  false
                ),
                shadowColor: 'rgba(0,160,221,1)',
                shadowBlur: 4,
              },
            },
            label: {
              normal: {
                show: true,
                lineHeight: 30,
                formatter: '{c}',
                position: 'top',
                textStyle: {
                  color: '#00D6F9',
                  fontSize: 16,
                },
              },
            },
          },
        ],
      };
      const myChart = this.$echarts.init(document.getElementById('mychart'));
      myChart.setOption(option);
      clearInterval(this.toolTipTimer);
      let index = 0;
      this.toolTipTimer = setInterval(() => {
        myChart.dispatchAction({
          type: 'showTip', // 提示框
          seriesIndex: 0,
          dataIndex: index, // 第几行柱子高亮
        });
        if (index == data.length - 1) index = 0; //展示到最后一个的时候 从0开始
        else index++;
      }, 3000);
      window.addEventListener('resize', () => {
        myChart.resize();
      });
    },
  },
};
</script>
<style lang="scss" scoped>
</style>
