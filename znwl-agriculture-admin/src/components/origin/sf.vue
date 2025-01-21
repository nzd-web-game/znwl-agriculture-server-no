<template>
  <div>
    <div class="form-top">
        <el-form :inline="true" class="demo-form-inline">
            <el-form-item label="选中日期">
                <el-date-picker v-model="sfTime" type="datetimerange" range-separator="至" start-placeholder="开始日期" end-placeholder="结束日期" size="small"> </el-date-picker>
            </el-form-item>
            <el-form-item>
                <el-button type="success" size="mini" @click="search">&nbsp;查询&nbsp;</el-button>
                <el-button @click="resetName" size="mini">&nbsp;重置&nbsp;</el-button>
            </el-form-item>
        </el-form>
    </div>
    <div class="sf-content">
        <el-table :data="sftableData" :stripe="true" tooltip-effect="dark" size="mini" style="width: 100%" 
         :header-cell-style="{ background: 'rgba(239, 249, 243, 1)', color: '#000' }" class="table-content"
        >
            <el-table-column  type="index" label="序号"> </el-table-column>
            <el-table-column  prop="id" label="id"> </el-table-column>
            <el-table-column label="肥料名称" >
                <template slot-scope="scope"> <div class="dp-name">   {{ scope.row.name }}</div></template>
            </el-table-column>
            <el-table-column  prop="dataT" label="施肥日期"> </el-table-column>
        </el-table>
        <div class="page-block">
        <el-pagination
          @size-change="handleSizeChange"
          @current-change="handleCurrentChange"
          :current-page="currentPage"
          :page-size="pSize"
          layout="total, prev, pager, next, jumper"
          :total="totalPage"
        >
        </el-pagination>
      </div>
    </div>
  </div>
</template>

<script>
export default {
    data(){
        return{
            sfTime:'',
            sftableData:[],
            currentPage:1,
            pSize:8,
            totalPage:0
        }
    },
    mounted(){
        this.getsfData()
    },
    methods:{
        getsfData(sTime,eTime){
            this.$http.post("/dev-api/iaPartitionFoodSensorValue/getFeedingPage",{
                iaPartitionFoodId:localStorage.getItem("syInfo"),
                currentPage:this.currentPage,
                pageSize:this.pSize,
                startTime:sTime,
                endTime:eTime
            }).then(res=>{
                console.log(res)
                if(res.data.code==0&&res.data.data.records.length>0){
                    let newsData = res.data.data.records;
                    this.totalPage = res.data.data.total
                    let sfData=[]
                    let obj={};
                    newsData.forEach(el=>{
                        el.listValues.forEach(t=>{
                            if(t.name=='id'){
                                obj.id = t.numericValue.value
                            }
                            if(t.name=='fertilizerNme'){
                                obj.name = t.stringValue.value
                            }
                            if(t.name=='date'){
                                obj.dataT = t.stringValue.value
                            }
                        })
                        sfData.push(obj)
                    })
                    this.sftableData = sfData
                }else{
                    this.$message.error(res.data.msg)
                }
            })
        },
        handleSizeChange(val) {
            this.pSize = val
        },
        handleCurrentChange(val) {
            this.currentPage = val
            this.getsfData()
        },
        search(){
            this.getsfData(this.TimeDate(this.sfTime[0]),this.TimeDate(this.sfTime[1]))
        },
        TimeDate(date){
            const year = date.getFullYear();
            const month = (date.getMonth() + 1).toString().padStart(2, '0');
            const day = date.getDate().toString().padStart(2, '0');
            return `${year}-${month}-${day}`;
        },
        resetName(){
            this.sfTime = ""
        }
    }
}
</script>

<style lang="scss" scoped>
.form-top{
    // margin: 10px 20px 0;
    .demo-form-inline{
        height: 50px;
    }
    .inpname{
        width: 240px;
    }
}
.dp-name{
    color: #0CBF5B;
}
.page-block {
  display: flex;
  justify-content: flex-end;
  margin-top: 10px;
}
</style>