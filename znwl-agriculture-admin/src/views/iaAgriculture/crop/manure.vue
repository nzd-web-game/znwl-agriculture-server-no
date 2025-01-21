<template>
    <div class="list-content">
        <div class="form-top">
            <el-form :inline="true" class="demo-form-inline">
                <!-- <el-form-item label="分区名称">
          <el-input
            v-model="fatName"
            placeholder="请输入名称"
            size="small"
          ></el-input>
        </el-form-item> -->
                <el-form-item label="日期时间">
                    <!-- <el-date-picker
            v-model="fatTime"
            type="datetime"
            placeholder="选择日期时间"
          >
          </el-date-picker> -->
                    <el-date-picker size="small" v-model="fatTime" type="daterange" range-separator="至"
                        start-placeholder="开始日期" end-placeholder="结束日期">
                    </el-date-picker>
                </el-form-item>
                <!-- <el-form-item label="ID">
          <el-input
            v-model="fatid"
            type="number"
            placeholder="请输入ID"
            size="small"
          ></el-input>
        </el-form-item> -->
                <el-form-item>
                    <el-button type="success" size="mini" @click="manureSearch">&nbsp;查询&nbsp;</el-button>
                    <el-button @click="manureReset" size="mini">&nbsp;重置&nbsp;</el-button>
                </el-form-item>
            </el-form>
            <div class="do-right">
                <el-button type="success" size="mini" plain @click="manureEditAdd()">&nbsp; 新增 &nbsp;</el-button>
            </div>
        </div>
        <div class="plant-table">
            <el-table :data="manureData" :stripe="true" tooltip-effect="dark" border size="mini" style="width: 100%"
                :header-cell-style="{
          background: 'rgba(239, 249, 243, 1)',
          color: '#000',
        }" class="table-content">
                <el-table-column type="index" label="序号"> </el-table-column>
                <el-table-column prop="id" label="ID"> </el-table-column>
                <el-table-column prop="partitionName" label="分区名称">
                    <template slot-scope="scope">
                        <div class="dp-name">{{ scope.row.partitionName }}</div>
                    </template>
                </el-table-column>
                <el-table-column prop="foodName" label="肥料名称"> </el-table-column>
                <el-table-column prop="weight" label="重量"> </el-table-column>
                <el-table-column prop="date" label="施肥日期"> </el-table-column>
                <el-table-column prop="description" label="备注"> </el-table-column>
                <el-table-column prop="address" label="操作">
                    <template slot-scope="scope">
                        <div class="do-text">
                            <el-link class="txt-btn" type="success" @click="manureEditAdd(scope.row.id,1)">修改</el-link>
                            <el-link class="txt-btn" type="success" @click="manureEditAdd(scope.row.id,2)">详情</el-link>
                            <el-link class="txt-btn" type="danger" @click="deleteData(scope.row.id)">删除</el-link>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
            <div class="page-block">
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                    :current-page="currentPage" :page-size="pSize" layout="total, prev, pager, next, jumper"
                    :total="totalPage">
                </el-pagination>
            </div>
        </div>
        <!-- 新增/修改弹框 -->
        <el-dialog :title='btnTxt==0?"新增":btnTxt==1?"修改":"详情"' :visible.sync="manureEditDialog" width="40%">
            <el-form :model="manureForm" label-width="120px">
                <el-form-item label="分区名称">
                    <el-select :disabled="btnTxt==2?true:false" v-model="manureForm.iaPartitionId" placeholder="请选择"
                        size="small" class="manureInput">
                        <el-option v-for="item in manureOptions" :key="item.id" :label="item.partitionName"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="肥料名称">
                    <el-input :disabled="btnTxt==2?true:false" v-model="manureForm.foodName" required
                        placeholder="请输入肥料名称" class="manureInput"></el-input>
                </el-form-item>
                <el-form-item label="重量">
                    <el-input :disabled="btnTxt==2?true:false" v-model="manureForm.weight" required placeholder="请输入重量"
                        class="manureInput"></el-input>
                </el-form-item>
                <el-form-item label="施肥日期">
                    <el-date-picker :disabled="btnTxt==2?true:false" class="manureInput" v-model="manureForm.date"
                        type="datetime" placeholder="选择日期时间">
                    </el-date-picker>
                </el-form-item>

                <el-form-item label="备注信息">
                    <el-input :disabled="btnTxt==2?true:false" type="textarea" v-model="manureForm.description"
                        :rows="3" class="manureInput" placeholder="请输入备注信息"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="manureEditDialog = false">关闭</el-button>
                <el-button type="success" size="small" @click="manureDoBtn">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                btnTxt: 0,
                manureEditDialog: false,
                fatName: "",
                fatid: "",
                fatTime: '',
                currentPage: 1,
                totalPage: 20,
                pSize: 10,
                manureOptions: [],
                manureData: [],
                manureForm: {
                    date: "",
                    foodName: "",
                    weight: "",
                    iaPartitionId: "",
                    description: "",
                    partitionName: ''
                },
                edId: 0,
                fqId: ''
            };
        },
        mounted() {
            this.getAllData()
            this.fqData()
        },
        methods: {
            sfDataDetail(n) {
                this.$http.post("/dev-api/iaFeeding/detail?id=" + n).then(res => {
                    console.log(res)
                    this.fqId = res.data.data.iaPartitionId
                    this.manureForm = res.data.data
                    this.manureOptions.forEach(el => {
                        if (el.id == res.data.data.iaPartitionId) {
                            this.manureForm.iaPartitionId = el.partitionName
                        }
                    })

                })
            },
            //   弹框确定按钮
            manureDoBtn() {
                //新增
                if (this.btnTxt == 0) {
                    const year = this.manureForm.date.getFullYear();
                    const month = (this.manureForm.date.getMonth() + 1).toString().padStart(2, '0');
                    const day = this.manureForm.date.getDate().toString().padStart(2, '0');
                    const h = this.manureForm.date.getHours().toString().padStart(2, '0')
                    const f = this.manureForm.date.getMinutes().toString().padStart(2, '0')
                    const s = this.manureForm.date.getSeconds().toString().padStart(2, '0')
                    this.manureForm.date = `${year}-${month}-${day} ${h}:${f}:${s}`;
                    this.manureForm.weight = parseInt(this.manureForm.weight)
                    this.$http.post("/dev-api/iaFeeding/create", this.manureForm).then(res => {
                        console.log(res)
                        if (res.data.code == 0) {
                            this.$message.success("数据新增成功~")
                            this.getAllData()
                        }
                    })
                } else if (this.btnTxt == 1) {
                    // 确定修改按钮
                    this.manureForm.id = this.edId
                    this.manureOptions.forEach(item => {
                        if (item.id == this.fqId) {
                            this.manureForm.iaPartitionId = item.id
                        }
                    })
                    this.$http.post("/dev-api/iaFeeding/update", this.manureForm).then(res => {
                        console.log(res)
                        if (res.data.code == 0) {
                            this.$message.success("修改成功！")
                            this.getAllData()
                        }
                    })
                }
                this.manureEditDialog = false;
                //
            },
            // 获取全部数据
            getAllData(n, sTime, eTime) {
                this.$http.post("/dev-api/iaFeeding/page", {
                    currentPage: this.currentPage,
                    pageSize: this.pSize,
                    keyword: n,
                    startTime: sTime,
                    endTime: eTime
                }).then(res => {
                    // console.log(res)
                    this.manureData = res.data.data.records
                    this.totalPage = res.data.data.total
                })
            },
            // 分区列表
            fqData() {
                this.$http.get("/dev-api/partition/getList").then(res => {
                    console.log("123");
                    console.log(res);
                    this.manureOptions = res.data.data
                })
            },
            TimeDate(date) {
                const year = date.getFullYear();
                const month = (date.getMonth() + 1).toString().padStart(2, '0');
                const day = date.getDate().toString().padStart(2, '0');
                return `${year}-${month}-${day}`;
            },
            manureSearch() {
                console.log(this.TimeDate(this.fatTime[0]), this.TimeDate(this.fatTime[0]))
                this.getAllData("", this.TimeDate(this.fatTime[0]), this.TimeDate(this.fatTime[1]))
            },
            manureReset() {
                this.fatName = "";
                this.fatTime = '';
                this.fatid = "";
            },
            handleSizeChange(val) {
                this.pSize = val
            },
            handleCurrentChange(val) {
                this.currentPage = val
                //   console.log(`当前页: ${val}`);
                this.getAllData()
            },
            // 新增按钮显示弹框
            manureEditAdd(n1, n2) {
                if (n2 == 1) {
                    this.edId = n1
                    this.btnTxt = 1
                    console.log("编辑!!!");
                    // this.$http.post("/dev-api/iaFeeding/detail?id="+n1).then(res=>{
                    //     console.log(res)
                    //     this.manureForm  =res.data.data
                    // })
                    this.sfDataDetail(n1)
                } else if (n2 == 2) {
                    this.btnTxt = 2
                    // console.log("详情");
                    // this.$http.post("/dev-api/iaFeeding/detail?id="+n1).then(res=>{
                    //     this.manureForm  =res.data.data
                    // })
                    this.sfDataDetail(n1)
                } else {
                    this.manureForm = {
                            date: "",
                            foodName: "",
                            weight: "",
                            iaPartitionId: "",
                            description: "",
                            partitionName: ''
                        },
                        this.btnTxt = 0
                    console.log("新增");
                }
                this.manureEditDialog = true;


            },
            deleteData(id) {
                this.$confirm("确定要删除该条数据吗?", "删除", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning",
                    })
                    .then(() => {
                        this.$http.post("/dev-api/iaFeeding/delete?id=" + id).then(res => {
                            if (res.data.code == 0) {
                                this.$message({
                                    type: "success",
                                    message: "删除成功!",
                                });
                                this.getAllData()
                            }
                        })
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消删除",
                        });
                    });
            },

        },
    };
</script>

<style lang="scss" scoped>
    .manureInput {
        width: 300px;
    }

    .form-top {
        margin: 10px 10px 0;
        display: flex;
        justify-content: space-between;
        align-items: center;

        .demo-form-inline {
            height: 50px;
        }

        .inpname {
            width: 240px;
        }
    }

    .plant-do {
        // height: 100px;
        margin-left: 10px;
        display: flex;
        justify-content: space-between;
        align-items: center;

        .do-right {
            width: 40%;
            display: flex;
            justify-content: space-between;
            align-items: center;

            .desc-item {
                display: flex;
                align-items: center;
                font-size: 14px;

                p {
                    margin-right: 10px;
                }

                ul {
                    font-size: 12px;
                    display: flex;

                    .dot {
                        width: 10px;
                        height: 10px;
                    }

                    li {
                        height: 20px;
                        display: flex;
                        align-items: center;
                        margin-right: 8px;
                        width: 50px;

                        &:first-child {
                            color: #fa7c01;
                        }

                        &:nth-child(2) {
                            color: #0cbf5b;
                        }

                        &:nth-child(3) {
                            color: #019fe8;
                        }
                    }
                }
            }
        }
    }

    .plant-table {
        margin: 10px;
    }

    .table-content {

        // height: 100%;
        .dp-name {
            color: #0cbf5b;
        }

        .do-text {
            font-size: 12px;
        }

        .txt-btn {
            font-size: 12px;
            margin: 0 5px;
        }
    }

    .page-block {
        display: flex;
        justify-content: flex-end;
        margin-top: 10px;
    }
</style>
