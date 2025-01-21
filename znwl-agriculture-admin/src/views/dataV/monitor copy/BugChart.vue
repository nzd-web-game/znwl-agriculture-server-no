<template>
  <div class="w100 h100">
       <div class="pest">
            <div class="pie " ref="pie" ></div>
            <div class="bar" ref="bar"></div>
        </div>
  </div>
</template>

<script>
import { pie,barColumn ,autoHover} from "../echartsOption";
export default {
  name: 'BugChart',
  mixins: [],
  components: {},
  props: {},
  data() {
    return{}
  },
  computed: {},
  watch: {},
  created() {},
  mounted() {
      this.loadChart();
  },
  destroyed() {},
  methods: {
      loadChart(){
          let data = [
            {
                name:"蟋蟀",
                value:"37"
            },
            {
                name:"突背斑红蝽",
                value:"44"
            },
            {
                name:"蝼蛄",
                value:"46"
            },
            {
                name:"鳃金龟",
                value:"46"
            }
        ];
        console.log(this.$echarts)
        let pieChart = this.$echarts.init(this.$refs.pie)
        pieChart.setOption(pie(data,"只"))
        autoHover(pieChart,pie(data),1500)

        let barChart = this.$echarts.init(this.$refs.bar)
        barChart.setOption(barColumn(data,"只"))
        autoHover(barChart,barColumn(data),1500)
      }
  },
};
</script>
<style lang="scss" scoped>
    .pest{
        width: 100%;
        height: 100%;
        display: flex;
        .pie{
            width: 30%;
            height: 100%;
        }
        .bar{
            width: 70%;
            height: 100%;
        }
    }
</style>
