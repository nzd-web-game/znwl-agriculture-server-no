<template>
    <div class="industrialBase">
        <LayOut :title="title">
            <template v-slot:two>
                <base-info></base-info>
            </template>
            <template v-slot:three>
                <product-prices></product-prices>
            </template>
            <template v-slot:four>
                <farm-task @show="show=true"></farm-task>
            </template>
            <template v-slot:five>
                <plant-area></plant-area>
            </template>
            <template v-slot:six>
                <intelligent-devices></intelligent-devices>
            </template>
        </LayOut>
        <el-dialog :visible.sync="show" width="60%" custom-class="taskDetail" :modal="false">
            <div slot="title">农事任务</div>
            <task></task>
        </el-dialog>
    </div>
</template>

<script>
import LayOut from "../layOut.vue";
import baseInfo from './baseInfo.vue';
import productPrices from './productPrices.vue';
import farmTask from './farmTask.vue';
import plantArea from './plantArea.vue';
import intelligentDevices from './intelligentDevices.vue';
import task from "./task.vue"
export default {
    components: {
        LayOut,baseInfo,productPrices,farmTask,plantArea,intelligentDevices,task
    },
    data() {
        return {
            title: [
                "基地概况",
                "农产品价格",
                "农事任务",
                "种植品种面积",
                "智能设备",
            ],
            show:false,
        };
    },
};
</script>

<style lang="scss" scoped>
.industrialBase::v-deep{
    height: 100%;
    width: 100%;
    .taskDetail{
        .el-dialog__header{
            background: #0B2439;
            color: #fff;
        }
        .el-dialog__body{
            background: #0B2439;
        }
    }
}
</style>
