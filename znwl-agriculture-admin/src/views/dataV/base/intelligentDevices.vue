<template>
    <div class="intelligentDevices">
        <div id="pieChart"></div>
        <div id="barChart"></div>
    </div>
</template>

<script>
import { pie,barColumn,autoHover } from "../echartsOption"
import { selectDeviceInfo } from "@/api/agriculture/dataStatistics"
export default {
    data(){
        return {
            product:[],
        }
    },
    mounted(){
        let pieChart = this.$echarts.init(document.getElementById("pieChart"))
        let barChart = this.$echarts.init(document.getElementById("barChart"))
        selectDeviceInfo().then(res=>{
            this.product=res.rows.filter(item=>item.productName!='合计').map(item=>{
                return {
                    name:item.productName,
                    value:item.deviceCount
                }
            })
            pieChart.setOption(pie(this.product))
            autoHover(pieChart,pie(this.product),1500)

            barChart.setOption(barColumn(this.product))
            autoHover(barChart,barColumn(this.product),1500)
        })
    },
}
</script>

<style lang="scss" scoped>
.intelligentDevices{
    height: 100%;
    width: 100%;
    display: flex;
    #pieChart{
        width: 30%;
        height: 100%;
    }
    #barChart{
        width: 70%;
        height: 100%;
    }
}
</style>