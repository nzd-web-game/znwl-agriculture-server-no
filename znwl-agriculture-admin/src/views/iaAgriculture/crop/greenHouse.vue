<template>
    <div class="list-content">
        <el-card class="card-margin-bottom">
            <div class="form-top">
                <el-form :inline="true" class="form-minus-bottom">
                    <el-form-item label="大棚名称">
                        <el-input v-model="mcName" placeholder="请输入名称" size="small" class="inpname"></el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="primary" icon="el-icon-search" size="mini" @click="houseSearch">搜索</el-button>
                        <el-button icon="el-icon-refresh" size="mini" @click="resetName">重置</el-button>
                    </el-form-item>
                    <el-form-item class="fr">
                        <el-button type="primary" plain icon="el-icon-plus" size="mini"
                            @click="addHouse()">新增</el-button>
                    </el-form-item>
                    <!-- <el-form-item>
                        <el-button type="success" size="mini" @click="houseSearch">&nbsp;查询&nbsp;</el-button>
                        <el-button @click="resetName" size="mini">&nbsp;重置&nbsp;</el-button>
                    </el-form-item> -->
                </el-form>
                <!-- <div class="do-right">
                    <el-button type="success" size="mini" plain @click="addHouse()">&nbsp; 新增 &nbsp;</el-button>
                </div> -->
            </div>
        </el-card>
        <div class="plant-table">
            <el-card class="card-padding-bottom">
                <el-table :data="houseData">
                    <el-table-column type="index" label="序号"> </el-table-column>
                    <el-table-column prop="name" label="大棚名称">
                        <template slot-scope="scope">
                            <div class="dp-name"> {{ scope.row.name }}</div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="contractAddr" label="合约地址"> </el-table-column>
                    <el-table-column prop="area" label="大棚面积"> </el-table-column>
                    <el-table-column prop="temperature" label="温度">
                        <template slot-scope="scope">
                            <div class="dp-name"> {{ scope.row.temperature ? scope.row.temperature : '/' }}</div>
                        </template>
                    </el-table-column>
                    <el-table-column prop="humidity" label="湿度">
                        <template slot-scope="scope">
                            <div class="dp-name"> {{ scope.row.humidity ? scope.row.humidity : '/' }}</div>
                        </template>

                    </el-table-column>
                    <el-table-column prop="bigBreedingQuantity" label="最大区分数量/头"> </el-table-column>
                    <el-table-column prop="address" label="大棚位置"> </el-table-column>
                    <el-table-column prop="remark" label="备注">
                        <template slot-scope="scope">
                            <div class="dp-name"> {{ scope.row.remark ? scope.row.remark : '/' }}</div>
                        </template>

                    </el-table-column>
                    <el-table-column label="操作" width="300">
                        <template slot-scope="scope">
                            <div class="do-text">
                                <el-button size="small" class="padding-5" type="primary" icon="el-icon-edit"
                                    @click="addHouse(scope.row.id)"
                                    v-hasPermi="['agriculture:batch:edit']">修改</el-button>
                                <el-button size="small" class="padding-5" type="danger" icon="el-icon-delete"
                                    @click="deleteData(scope.row.id)"
                                    v-hasPermi="['agriculture:batch:remove']">删除</el-button>
                                <el-button size="small" class="padding-5" plain type="warning" icon="el-icon-s-claim"
                                    @click="houseCheck(scope.row.id)"
                                    v-hasPermi="['agriculture:batch:edit']">环境详情</el-button>
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
            </el-card>
        </div>
        <!-- 新增/修改弹框 -->
        <el-dialog :title="btnTxt == 1 ? '编辑' : btnTxt == 2 ? '详情' : '新增'" :visible.sync="houseEditDialog" width="40%">
            <el-form :model="houseDoForm" label-width="120px">
                <el-form-item label="大棚名称">
                    <el-input v-model="houseDoForm.name" required placeholder="请输入名称"
                        :disabled="btnTxt == 2 ? true : false"></el-input>
                </el-form-item>
                <el-form-item label="大棚面积">
                    <el-input v-model="houseDoForm.area" required placeholder="请输入大棚面积"
                        :disabled="btnTxt == 2 ? true : false"></el-input>
                </el-form-item>
                <el-form-item label="最大分区数量">
                    <el-input v-model="houseDoForm.bigBreedingQuantity" required placeholder="最大分区数量"
                        :disabled="btnTxt == 2 ? true : false"></el-input>
                </el-form-item>
                <el-form-item label="大棚位置">
                    <el-input v-model="houseDoForm.address" required placeholder="请输入大棚位置"
                        :disabled="btnTxt == 2 ? true : false"></el-input>
                </el-form-item>
                <el-form-item label="绑定设备">
                    <el-input v-if="btnTxt == 2" v-model="houseDoForm.deviceId" required :disabled="true"></el-input>
                    <el-select v-model="houseDoForm.deviceId" placeholder="请选择" size="small" v-else>
                        <el-option v-for="item in deviceOptions" :key="item.id" :label="item.deviceName"
                            :value="item.id">
                        </el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="备注信息">
                    <el-input type="textarea" :disabled="btnTxt == 2 ? true : false" v-model="houseDoForm.description"
                        :rows="3" placeholder="请输入备注信息"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="houseEditDialog = false">关闭</el-button>
                <el-button type="success" size="small" @click="houseDoBtn">确认</el-button>
            </div>
        </el-dialog>
        <!-- 大棚状态抽屉页面 -->
        <el-dialog title="大棚状态抽屉" :visible.sync="houseStatusDialog" width="50%">
            <div class="status-content">
                <el-form :inline="true" class="demo-form-inline">
                    <el-form-item label="请搜索">
                        <el-input v-model="stateContent" placeholder="请搜索">
                        </el-input>
                    </el-form-item>
                    <el-form-item>
                        <el-button type="success" size="mini" @click="statusSearch">&nbsp;查询&nbsp;</el-button>
                    </el-form-item>
                </el-form>
                <el-table :data="statusData" :stripe="true" tooltip-effect="dark" border size="mini" style="width: 100%"
                    :header-cell-style="{ background: 'rgba(239, 249, 243, 1)', color: '#000' }">
                    <el-table-column prop="id" label="ID"> </el-table-column>
                    <el-table-column prop="airquality" label="空气质量"> </el-table-column>
                    <el-table-column prop="temperature" label="温度"> </el-table-column>
                    <el-table-column prop="humidity" label="湿度"> </el-table-column>
                    <el-table-column prop="pressure" label="气压"> </el-table-column>
                    <el-table-column prop="dateTime" label="记录日期"> </el-table-column>
                </el-table>
                <div class="page-block">
                    <el-pagination @size-change="sSizeChange" @current-change="sCurrentChange"
                        :current-page="scurrentPage" :page-size="spSize" layout="total, prev, pager, next, jumper"
                        :total="stotal">
                    </el-pagination>
                </div>
            </div>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="houseStatusDialog = false">关闭</el-button>
                <el-button type="success" size="small" @click="houseDoBtn">确认</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                btnTxt: 0,
                houseStatusDialog: false,
                houseEditDialog: false,
                mcName: '',
                currentPage: 1,
                totalPage: 0,
                pSize: 10,
                houseData: [],
                // 设备选择
                deviceOptions: [],
                typeVal: '',
                statusData: [],
                // 状态弹框列表
                stotal: 0,
                scurrentPage: 1,
                spSize: 10,
                checkId: 0,
                stateContent: '',
                houseDoForm: {
                    deviceId: '',
                    name: '',
                    address: '',
                    // breedingQuantity:0,
                    bigBreedingQuantity: '',
                    area: '',
                    description: ''
                },
                sbId: '',
            }
        },
        mounted() {
            this.getHouseData()
            this.devideData()
        },
        methods: {
            devideData() {
                this.$http.get("/dev-api/device/listAll").then(res => {
                    console.log(res)
                    this.deviceOptions = res.data.data
                })
            },
            getHouseData(val) {
                this.$http.post("/dev-api/iaPasture/page", {
                    currentPage: this.currentPage,
                    pageSize: this.pSize,
                    keyword: val
                }).then(res => {
                    console.log(res)
                    this.houseData = res.data.data.records
                    this.totalPage = res.data.data.total
                })
            },
            houseSearch() {
                this.getHouseData(this.mcName)
            },
            resetName() {
                this.mcName = ""
                this.getHouseData()
            },
            handleSizeChange(val) {
                this.pSize = val
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                this.currentPage = val
                console.log(`当前页: ${val}`);
                this.getHouseData()
            },
            // 新增按钮显示弹框
            addHouse(n) {
                if (n) {
                    console.log("编辑")
                    this.btnTxt = 1
                    this.$http.post("/dev-api/iaPasture/detail?id=" + n).then(res => {
                        console.log(res)
                        this.houseDoForm = res.data.data
                    })
                    this.plantDetail(n)
                } else {
                    this.houseDoForm = {
                            deviceId: '',
                            name: '',
                            address: '',
                            bigBreedingQuantity: '',
                            area: '',
                            description: ''
                        },
                        this.btnTxt = 0
                }
                this.houseEditDialog = true
            },
            //   详情
            houseDetail(id) {
                this.houseEditDialog = true;
                this.btnTxt = 2;
                this.plantDetail(id)
            },
            plantDetail(n) {
                this.$http.post("/dev-api/iaPasture/detail?id=" + n).then(res => {
                    console.log(res)
                    this.houseDoForm = res.data.data;
                    const dvId = res.data.data.devices;
                    if (dvId.length) {
                        this.sbId = dvId[0].id
                        this.deviceOptions.forEach(item => {
                            if (item.id == dvId[0].id) {
                                console.log("详情id", res.data.data.id)
                                this.houseDoForm.deviceId = item.deviceName
                            }
                        })
                    }

                })
            },
            // 查看列表分页
            sSizeChange(n) {
                this.spSize = n
            },
            sCurrentChange(n) {
                this.scurrentPage = n
                this.houseCheck(this.checkId)
            },
            houseCheck(n, val) {
                this.checkId = n
                this.houseStatusDialog = true
                this.$http.post("/dev-api/iaPasture/ivPastureSensorValuePage", {
                    currentPage: this.scurrentPage,
                    keyword: this.mcName,
                    pageSize: this.spSize,
                    pastureId: n,
                    keyword: val
                }).then(res => {
                    console.log(res)
                    this.statusData = res.data.data.records;
                    this.stotal = res.data.data.total
                })
            },
            deleteData(id) {
                this.$confirm('确定要删除该条数据吗?', '删除', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$http.post("/dev-api/iaPasture/delete?id=" + id).then(res => {
                        // console.log(res)
                        if (res.data.code == 0) {
                            this.getHouseData()
                            this.$message({
                                type: 'success',
                                message: '删除成功!'
                            });
                        }
                    })

                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
            },
            //   弹框确定按钮
            houseDoBtn() {
                if (this.btnTxt == 0) {
                    this.$http.post("/dev-api/iaPasture/create", this.houseDoForm).then(res => {
                        console.log(res)
                        if (res.data.code == 0) {
                            this.$message.success("数据新增成功");
                            this.houseEditDialog = false;
                            this.getHouseData()
                        } else {
                            this.$message.error(res.data.msg);
                        }
                    })
                } else if (this.btnTxt == 1) {
                    console.log("确认编辑")
                    this.deviceOptions.forEach(item => {
                        if (item.id == this.sbId) {
                            this.houseDoForm.deviceId = item.id
                        }
                    })
                    this.$http.post("/dev-api/iaPasture/update", this.houseDoForm).then(res => {
                        console.log(res)
                        if (res.data.code == 0) {
                            this.$message.success("数据修改成功");
                            this.houseEditDialog = false;
                            this.getHouseData()
                        }
                    })
                } else {
                    this.houseEditDialog = false;
                }


            },
            // 抽屉页面搜索
            statusSearch() {
                this.houseCheck(this.checkId, this.stateContent)
            },
        }
    }
</script>

<style lang="scss" scoped>
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
                            color: #FA7C01;

                        }

                        &:nth-child(2) {
                            color: #0CBF5B;
                        }

                        &:nth-child(3) {
                            color: #019FE8;
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
            color: #0CBF5B;
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
