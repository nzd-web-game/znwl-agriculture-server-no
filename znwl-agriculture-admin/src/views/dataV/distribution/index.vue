<template>
  <div>
    <LayOut :bg="bg" :title="title">
      <template v-slot:one>
        <map-chart></map-chart>
      </template>
      <template slot="two">
        <home-count></home-count>
      </template>
      <template slot="three">
        <area-count></area-count>
      </template>
      <template slot="four">
        <green-industry></green-industry>
      </template>
      <template slot="five">
        <money-industry></money-industry>
      </template>
      <template slot="six">
        <industry-percent></industry-percent>
      </template>
    </LayOut>
  </div>
</template>

<script>
import LayOut from '../layOut.vue';
import MapChart from './MapChart.vue';
import HomeCount from './HomeCount.vue';
import AreaCount from './AreaCount.vue';
import greenIndustry from './greenIndustry.vue';
import moneyIndustry from './moneyIndustry.vue';
import industryPercent from './industrypercent.vue';

export default {
  props: {},
  components: {
    LayOut,
    MapChart,
    HomeCount,
    AreaCount,
    greenIndustry,
    moneyIndustry,
    industryPercent,
  },
  data() {
    return {
      bg: require('../img/bg.png'),
      title: [
        '集约经营主体',
        '主导产业面积',
        '产业绿色发展',
        '主导产业产值',
        '主导产业占比',
      ],
    };
  },
  mounted() {},
  methods: {},
};
</script>

<style lang="scss" scoped>
</style>
