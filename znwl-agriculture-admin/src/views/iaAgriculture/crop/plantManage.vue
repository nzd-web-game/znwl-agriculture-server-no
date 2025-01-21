<template>
    <div class="list-content">

        <div class="form-top">
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item label="大棚名称">

                    <el-input v-model="plantName" placeholder="请输入大棚名称" size="small"></el-input>
                </el-form-item>
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
            <el-table :data="plantData" :stripe="true" tooltip-effect="dark" border size="mini" style="width: 100%"
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
                <el-table-column prop="name" label="大棚名称"> </el-table-column>
                <el-table-column prop="variety" label="品种"> </el-table-column>
                <el-table-column label="是否加工">
                    <template slot-scope="scope">
                        <div class="dp-name">{{ scope.row.processState?"加工":"未加工" }}</div>
                    </template>
                </el-table-column>
                <el-table-column prop="plantName" label="种植名称"> </el-table-column>
                <el-table-column prop="plantDate" label="种植日期">
                    <template slot-scope="scope">
                        <div class="dp-name">{{ new Date(scope.row.plantDate).toLocaleString()  }}</div>
                    </template>
                </el-table-column>
                <el-table-column prop="contractAddr" label="合约地址"> </el-table-column>
                <el-table-column prop="remark" label="备注"> </el-table-column>
                <el-table-column label="操作" width="150px">
                    <template slot-scope="scope">
                        <div class="do-text">
                            <el-link class="txt-btn" type="success" @click="manureEditAdd(scope.row.id, 1)">修改</el-link>
                            <el-link class="txt-btn" type="success" @click="manureCSAdd(scope.row.id)">成熟</el-link>
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
        <el-dialog :title="btnTxt == 0 ? '新增' : btnTxt == 1 ? '修改' : '详情'" :visible.sync="plantEditDialog" width="40%">
            <el-form :model="plantForm" label-width="120px">
                <el-form-item label="分区名称">
                    <el-input :disabled="btnTxt == 2 ? true : false" v-model="plantForm.partitionName" required
                        placeholder="请输入分区名称" class="manureInput"></el-input>
                </el-form-item>

                <el-form-item label="所属大棚">
                    <el-select :disabled="btnTxt == 2 ? true : false" v-model="plantForm.pastureId" placeholder="请选择"
                        size="small" class="manureInput">
                        <el-option v-for="item in dpDataList" :key="item.id" :label="item.name" :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="种植名称">
                    <el-input :disabled="btnTxt == 2 ? true : false" v-model="plantForm.plantName" required
                        placeholder="请输入种植名称" class="manureInput"></el-input>
                </el-form-item>
                <el-form-item label="品种">
                    <el-input :disabled="btnTxt == 2 ? true : false" v-model="plantForm.variety" required
                        placeholder="请输入品种" class="manureInput"></el-input>
                </el-form-item>
                <el-form-item label="种植时间">
                    <el-date-picker :disabled="btnTxt == 2 ? true : false" class="manureInput"
                        v-model="plantForm.plantDate" type="datetime" placeholder="选择日期时间">
                    </el-date-picker>
                </el-form-item>

                <el-form-item label="备注信息">
                    <el-input :disabled="btnTxt == 2 ? true : false" type="textarea" v-model="plantForm.remark"
                        :rows="3" class="manureInput" placeholder="请输入备注信息"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="plantEditDialog = false">关闭</el-button>
                <el-button type="success" size="small" @click="plantDoBtn">确定</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                plantName: '',
                plantId: '',
                btnTxt: 0,
                plantEditDialog: false,
                fatName: "",
                fatid: "",
                fatTime: "",
                currentPage: 1,
                totalPage: 0,
                pSize: 10,
                plantOptions: [],
                plantData: [],
                dpDataList: [],
                plantForm: {
                    partitionName: "",
                    pastureId: "",
                    plantName: "",
                    variety: "",
                    contractAddr: "",
                    plantDate: "",
                    number: 0,
                    // processState:0,
                    remark: '',
                    // status:'',
                },
                edId: 0,
            };
        },
        mounted() {
            this.getAllData();
            this.fqData();
            this.dpData()
        },
        methods: {
            // 成熟
            manureCSAdd(id) {
                this.$confirm("确定要成熟操作吗?", "成熟", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning",
                    }).then(() => {
                        this.$http.post("/dev-api/partition/harvestPartition",
                            [id]
                        ).then((res) => {
                            console.log(res);
                            if (res.data.code == 0) {
                                this.$message({
                                    type: "success",
                                    message: "成熟成功!",
                                });
                                this.getAllData();
                            }
                        });
                    })
                    .catch(() => {
                        this.$message({
                            type: "info",
                            message: "已取消",
                        });
                    });
            },
            // 弹框确定按钮
            plantDoBtn() {
                //新增
                if (this.btnTxt == 0) {
                    this.$http.post("/dev-api/partition/add", this.plantForm).then((res) => {
                        console.log(res);
                        if (res.data.code == 0) {
                            this.$message.success("数据新增成功~");
                            this.getAllData();
                        }
                    });
                } else if (this.btnTxt == 1) {
                    // 确定修改按钮
                    this.plantForm.id = this.edId;
                    this.plantForm.plantDate = new Date(this.plantForm.plantDate);
                    this.$http.put("/dev-api/partition/update", this.plantForm).then((res) => {
                        console.log(res)
                        if (res.data.code == 0) {
                            this.$message.success("修改成功！");
                            this.getAllData();
                        }
                    });
                }
                this.plantEditDialog = false;
                //
            },
            // 获取全部数据
            getAllData(n, val) {
                this.$http
                    .get("/dev-api/partition/list", {
                        params: {
                            page: this.currentPage,
                            pageSize: this.pSize,
                            pastureName: val,
                            id: n
                        }

                    })
                    .then((res) => {
                        console.log(res)
                        this.plantData = res.data.data.records
                        this.totalPage = res.data.data.total;
                    });
            },
            // 大棚列表
            dpData() {
                this.$http.get("/dev-api/iaPasture/list").then((res) => {
                    this.dpDataList = res.data.data;
                });
            },
            // 分区列表
            fqData() {
                this.$http.get("/dev-api/partition/getList").then((res) => {
                    this.plantOptions = res.data.data;
                });
            },
            TimeDate(date) {
                const year = date.getFullYear();
                const month = (date.getMonth() + 1).toString().padStart(2, "0");
                const day = date.getDate().toString().padStart(2, "0");
                return `${year}-${month}-${day}`;
            },
            manureSearch() {
                this.getAllData(this.plantId, this.plantName);
            },
            manureReset() {
                this.fatName = "";
                this.fatTime = "";
                this.fatid = "";
            },
            handleSelectionChange(val) {
                this.multipleSelection = val;
                console.log(val);
            },
            handleSizeChange(val) {
                this.pSize = val;
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                //   console.log(`当前页: ${val}`);
                this.getAllData(this.plantId, this.plantName);
            },
            // 新增按钮显示弹框
            manureEditAdd(n1, n2) {
                if (n2 == 1) {
                    this.edId = n1;
                    this.btnTxt = 1;
                    this.$http.get("/dev-api/partition/getById?id=" + n1).then((res) => {
                        console.log(res);
                        this.plantForm = res.data.data;
                    });
                } else {
                    this.plantForm = {
                            partitionName: "",
                            pastureId: "",
                            plantName: "",
                            variety: "",
                            contractAddr: "",
                            plantDate: "",
                            number: 0,
                            // processState:0,
                            remark: '',
                            // status:'',
                        },
                        this.btnTxt = 0;

                }
                this.plantEditDialog = true;
            },
            deleteData(id) {
                console.log(id);
                this.$confirm("确定要删除该条数据吗?", "删除", {
                        confirmButtonText: "确定",
                        cancelButtonText: "取消",
                        type: "warning",
                    }).then(() => {
                        this.$http.delete("/dev-api/partition/delete", {
                            id: id
                        }).then((res) => {
                            console.log(res);
                            if (res.data.code == 0) {
                                this.$message({
                                    type: "success",
                                    message: "删除成功!",
                                });
                                this.getAllData();
                            }
                        });
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
