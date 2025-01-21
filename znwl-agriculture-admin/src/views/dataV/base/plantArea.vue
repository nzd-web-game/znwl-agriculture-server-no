<template>
  <div class="plantArea" id="plnatArea"></div>
</template>

<script>
import { selectAreaInfo } from "@/api/agriculture/dataStatistics"
import { bar,autoHover } from "../echartsOption"
export default {
    data(){
        return {
            area:[]
        }
    },
    mounted(){
        let plnatArea = this.$echarts.init(document.getElementById("plnatArea"))
        selectAreaInfo().then(res=>{
            this.area = Array.from(new Set(res.rows.map(item=>item.name))).map(item=>{return{name:item,value:0}})
            this.area.forEach(item=>{
                res.rows.forEach(i=>{
                    if (i.name == item.name) {
                        item.value+=i.value
                    }
                })
            })
            plnatArea.setOption(bar(this.area))
            autoHover(plnatArea,bar(this.area),1500)
        })

    }
}
</script>

<style lang="scss" scoped>
.plantArea{
    height: 100%;
    width: 100%;
}
</style>