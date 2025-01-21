<template>
    <div style="height:100%">
        <div class="farmTask">
            <div class="echarts">
                <div class="pie" id="taskInfo"></div>
                <div class="detail">
                    <div class="info">农事任务详情：<span @click="showDetail">点击查看明细></span></div>
                    <div class="progress"><div>农事任务进度：</div><el-progress style="width:70%" :percentage="percentage" :stroke-width="15"></el-progress></div>
                </div>
            </div>
            <div class="info">
                <div class="item" v-for="item in taskTotal" :key="item.id">
                    <div class="num">{{item.value}}</div>
                    <div class="label">{{item.name}}任务(项)</div>
                </div>
            </div>
        </div>
    </div>
</template>

<script>

import { selectTaskInfo } from "@/api/agriculture/dataStatistics"
import { pie,autoHover } from "../echartsOption"
import { getDicts } from "@/api/system/dict/data";
export default {
    data(){
        return {
            taskTotal: [],
            percentage:0
        }
    },
    mounted(){
        let taskInfo = this.$echarts.init(document.getElementById("taskInfo"))
        getDicts("agriculture_batch_task_status").then(res=>{
            this.taskTotal=res.data.map(item=>{
                return {
                    name:item.dictLabel,
                    id:item.dictValue,
                    value:0
                }
            })
            selectTaskInfo().then(task=>{
                this.taskTotal.forEach(item=>{
                    task.rows.forEach(i=>{
                        if (item.id == i.status) {
                             item.value = i.num
                        }
                    })
                })
                let total = 0,completed = 0
                this.taskTotal.forEach(item=>{
                    total+= Number(item.value)
                    if (item.name == '已完成') {
                        completed = Number(item.value)
                    }
                })
                this.percentage = Number(((completed / total)*100).toFixed(0))
                taskInfo.setOption(pie(this.taskTotal))
                autoHover(taskInfo,pie(this.taskTotal),1500)
            })
        })
    },
    methods:{
        showDetail(){
            this.$emit('show')
        }
    }
}
</script>

<style lang="scss" scoped>
.farmTask{
    height: 100%;
    padding-left:20px;
    box-sizing: border-box;
    .echarts{
        height: 50%;
        display: flex;
        .pie{
            height: 100%;
            width: 120px;
            margin-right: 20px;
        }
        .detail{
            flex: 1;
            color:#fff;
            box-sizing: border-box;
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            padding: 20px 0;
            box-sizing: border-box;
            .progress::v-deep{
                display: flex;
                align-items: center;
                .el-progress__text{
                    color:#fff
                }
            }
            span{
                color: #2EFFB7;
                cursor: pointer;
            }
        }
    }
    .info{
        height: 50%;
        display: flex;
        .item{
            flex: 1;
            height: 100%;
            font-size: 20px;
            display: flex;
            justify-content: center;
            flex-direction: column;
            .num{
                font-size: 26px;
            }
            .label{
                color: #fff;
                font-size: 16px;
            }
            &:nth-of-type(1){
                color: #2EFFB7;
            }
            &:nth-of-type(2){
                color: #099AFF;
            }
            &:nth-of-type(3){
                color: #32EBF2;
            }
            &:nth-of-type(4){
                color: #FCDE1F;
            }
        }
    }
}
</style>