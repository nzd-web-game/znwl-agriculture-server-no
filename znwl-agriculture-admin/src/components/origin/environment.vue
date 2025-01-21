<template>
    <div>
        <div class="form-top">
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item label="选中日期">
                    <el-date-picker v-model="envTime" type="datetimerange" range-separator="至" start-placeholder="开始日期"
                        end-placeholder="结束日期" size="small">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="success" size="mini" @click="enSearch">&nbsp;查询&nbsp;</el-button>
                    <el-button @click="resetName" size="mini">&nbsp;重置&nbsp;</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="sf-content">
            <el-table :data="entableData" :stripe="true" tooltip-effect="dark" size="mini" style="width: 100%"
                :header-cell-style="{
          background: 'rgba(239, 249, 243, 1)',
          color: '#000',
        }" class="table-content">
                <el-table-column prop="id" label="ID"> </el-table-column>
                <!-- <el-table-column label="传感器类型">
                  <template slot-scope="scope"> <div class="dp-name">   {{ scope.row.type }}</div></template>
              </el-table-column>
              <el-table-column  prop="num" label="数值"> </el-table-column> -->
                <el-table-column prop="temperature" label="温度"> </el-table-column>
                <el-table-column prop="humidity" label="湿度"> </el-table-column>
                <el-table-column prop="pressure" label="气压"> </el-table-column>
                <el-table-column prop="airquality" label="空气质量"> </el-table-column>
                <el-table-column prop="dateT" label="施肥日期"> </el-table-column>
            </el-table>
            <div class="page-block">
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                    :current-page="currentPage" :page-size="pSize" layout="total, prev, pager, next, jumper"
                    :total="totalPage">
                </el-pagination>
            </div>
        </div>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                envTime: "",
                value: "",
                entableData: [],
                currentPage: 1,
                pSize: 8,
                totalPage: 0,
            };
        },
        mounted() {
            this.hjData();
        },
        methods: {
            hjData(sTime, eTime) {
                this.$http
                    .post("/dev-api/iaPartitionFoodSensorValue/getEnvironmentPage", {
                        iaPartitionFoodId: localStorage.getItem("syInfo"),
                        currentPage: this.currentPage,
                        pageSize: this.pSize,
                        startTime: sTime,
                        endTime: eTime,
                    })
                    .then((res) => {
                        console.log(res);
                        if (res.data.code == 0) {
                            if (res.data.data.records.length > 0) {
                                let newsData = res.data.data.records;
                                this.totalPage = res.data.data.total;
                                let hjData = [];
                                let obj = {};
                                newsData.forEach((el) => {
                                    el.listValues.forEach((t) => {
                                        if (t.name == "id") {
                                            obj.id = t.numericValue.value;
                                        }
                                        if (t.name == "temperature") {
                                            obj.temperature = t.stringValue.value;
                                        }
                                        if (t.name == "date") {
                                            obj.dateT = t.stringValue.value;
                                        }
                                        if (t.name == "humidity") {
                                            obj.humidity = t.stringValue.value;
                                        }
                                        if (t.name == "airquality") {
                                            obj.airquality = t.stringValue.value;
                                        }
                                        if (t.name == "pressure") {
                                            obj.pressure = t.stringValue.value;
                                        }
                                    });
                                    hjData.push(obj);
                                });
                                this.entableData = hjData;
                            } else {
                                this.$message.error("暂无数据");
                            }
                        } else {
                            this.$message.error(res.data.msg);
                        }
                    });
            },
            handleSizeChange(val) {
                this.pSize = val;
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.getsfData();
            },
            TimeDate(date) {
                const year = date.getFullYear();
                const month = (date.getMonth() + 1).toString().padStart(2, "0");
                const day = date.getDate().toString().padStart(2, "0");
                return `${year}-${month}-${day}`;
            },
            enSearch() {
                this.getsfData(
                    this.TimeDate(this.envTime[0]),
                    this.TimeDate(this.envTime[1])
                );
            },
            resetName() {},
        },
    };
</script>

<style lang="scss" scoped>
    .form-top {

        // margin: 10px 20px 0;
        .demo-form-inline {
            height: 50px;
        }

        .inpname {
            width: 240px;
        }
    }

    .dp-name {
        color: #0cbf5b;
    }

    .page-block {
        display: flex;
        justify-content: flex-end;
        margin-top: 10px;
    }
</style>
