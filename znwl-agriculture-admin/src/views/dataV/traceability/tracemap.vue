<template>
  <div style="height:900px">
    <div class="w100" ref="chart" style="height:900px"></div>
  </div>
</template>
<script>
import { selectRecord } from "@/api/agriculture/dataStatistics";
import { mapChart } from "@/views/components/DataChart";
export default {
  data () {
    return {
      // 溯源码查询记录表格数据
      recordCountList: [],
      queryParams: {
        pageNum: 1,
        pageSize: 10,
        queryDate: null,
        queryCity: null,
        sellproId: null
      }
    }
  },
  async mounted () {
    await this.getList();
    this.loadMapChart();
  },
  methods: {
    async getList () {
      this.loading = true;
      const { rows, total } = await selectRecord(this.queryParams);
      this.recordCountList = rows;
      this.total = total;
      this.loading = false;
    },
    loadMapChart () {
      let data = this.recordCountList.map(item => ({
        name: item.queryCity,
        value: [item.queryCoordinate.split(',')[0], item.queryCoordinate.split(',')[1], item.queryCount]
      }));
      let i = 0;
      this.recordCountList.forEach(item => {
        i += item.queryCount;
      })
      mapChart(this.$refs.chart, data, i,'rgb(28, 61, 73)','rgb(64, 219, 165)',{fontSize:30});
    },
  }
}
</script>
<style >
</style>
