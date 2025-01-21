<template>
    <div class="list-content">
        <div class="form-top">
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item label="ID">
                    <el-input v-model="plantSearcher.id" size="small" class="inpname"></el-input>
                </el-form-item>
                <el-form-item label="大棚名称">
                    <el-input v-model="plantSearcher.pastureName" size="small" class="inpname"></el-input>
                </el-form-item>



                <el-form-item>
                    <el-button type="success" size="mini" @click="plantSearch">&nbsp;查询&nbsp;</el-button>
                    <el-button @click="resetPlantSearch" size="mini">&nbsp;重置&nbsp;</el-button>
                </el-form-item>
            </el-form>
            <div class="do-right">
                <el-button type="success" size="mini" @click="fqRipe()">分区成熟</el-button>
                <el-button type="success" size="mini" @click="dpRipe()">大棚成熟</el-button>
            </div>
        </div>
        <div class="plant-table">
            <el-table :data="plantData" :stripe="true" tooltip-effect="dark" border size="mini" style="width: 100%"
                :header-cell-style="{
          background: 'rgba(239, 249, 243, 1)',
          color: '#000',
        }" class="table-content">
                <el-table-column type="index" label="序号"> </el-table-column>
                <el-table-column prop="id" label="ID" align="center"></el-table-column>
                <el-table-column prop="plantName" label="分区名称"></el-table-column>
                <el-table-column prop="name" label="大棚名称" align="center"></el-table-column>
                <el-table-column prop="variety" label="品种" align="center"></el-table-column>
                <el-table-column prop="plantName" label="种植名称" align="center"></el-table-column>
                <el-table-column prop="plantDate" label="种植日期" align="center">
                    <template slot-scope="scope">
                        {{ new Date(scope.row.plantDate).toLocaleString() }}
                    </template>
                </el-table-column>
                <el-table-column prop="variety" label="合约地址" align="center"></el-table-column>
                <el-table-column prop="remark" label="备注" align="center"></el-table-column>
                <el-table-column label="操作" width="180" align="center">
                    <template slot-scope="scope">
                        <div class="do-text">
                            <template v-if="!scope.row.processDate">
                                <el-link class="txt-btn" type="success"
                                    @click="handleProcess(scope.row.id, '加工管理')">加工管理</el-link>
                                <el-link class="txt-btn" type="success"
                                    @click="handleFinishProcess(scope.row.id)">是否完成加工</el-link>
                            </template>
                            <template v-else>
                                <el-link class="txt-btn" type="success"
                                    @click="handleProcessDetail(scope.row.id, '加工详情')">加工详情</el-link>
                                <el-link class="txt-btn" type="success" style="color: gray">是否完成加工</el-link>
                            </template>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
            <div class="page-block">
                <el-pagination @current-change="handlePlantCurrentChange" :current-page="plantPager.page"
                    :page-size="plantPager.pageSize" :page-count="plantPager.pages"
                    layout="total, prev, pager, next, jumper">
                </el-pagination>
            </div>
        </div>
        <!-- 加工管理 -->
        <el-dialog :title="processDialogTitle" :visible.sync="processDialogVisible" width="70%">

            <div style="display: flex; justify-content: flex-end; padding-bottom: 10px">
                <el-button v-if="processDialogTitle !== '加工详情'" type="success" size="mini" plain
                    @click="addProcess('新增')">&nbsp; 新增 &nbsp;</el-button>
            </div>

            <el-table :data="processData" :stripe="true" tooltip-effect="dark" border size="mini"
                :header-cell-style="{ background: 'rgba(239, 249, 243, 1)', color: '#000' }" class="table-content"
                @selection-change="handleProcessSelectionChange">
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column prop="id" label="ID溯源码"></el-table-column>
                <el-table-column prop="name" label="食品名称" align="center"></el-table-column>
                <el-table-column label="二维码" align="center">
                    <template slot-scope="scope">
                        <img :src="scope.row.barcode" style="width: 100%">
                    </template>
                </el-table-column>
                <el-table-column prop="date" label="加工日期"></el-table-column>
                <el-table-column prop="weight" label="重量" width="60" align="center"></el-table-column>
                <el-table-column prop="status" label="食品质量" width="100" align="center">
                    <template slot-scope="scope">
                        {{ statusDict[scope.row.status] }}
                    </template>
                </el-table-column>
                <el-table-column prop="iaPartitionId" label="分区ID"></el-table-column>
                <el-table-column prop="description" label="备注"></el-table-column>
                <el-table-column label="操作" v-if="processDialogTitle !== '加工详情'">
                    <template slot-scope="scope">
                        <div class="do-text">
                            <el-link class="txt-btn" type="success" @click="addProcess('修改', scope.row.id)">修改</el-link>
                            <el-link class="txt-btn" type="success"
                                @click="processDetail('详情', scope.row.id)">详情</el-link>
                            <el-link class="txt-btn" type="danger" @click="deleteProcess(scope.row.id)">删除</el-link>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
            <div class="page-block" v-if="processData.length">
                <el-pagination @current-change="handleProcessCurrentChange" :current-page="processPager.page"
                    :page-size="processPager.size" :page-count="processPager.pages"
                    layout="total, prev, pager, next, jumper">
                </el-pagination>
            </div>

            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="processDialogVisible = false">关闭</el-button>
            </div>
        </el-dialog>

        <!-- 新增/修改弹框 -->
        <el-dialog :title="processManagementDialogTitle" :visible.sync="processManagementDialogVisible" width="30%">
            <el-form :model="processManagementForm" :rules="rules" ref="form" label-width="120px"
                :disabled="processManagementDialogTitle === '详情'">
                <el-form-item label="食品名称" prop="name">
                    <el-input v-model="processManagementForm.name" required></el-input>
                </el-form-item>
                <el-form-item label="重量" prop="weight">
                    <el-input v-model="processManagementForm.weight" required></el-input>
                </el-form-item>
                <el-form-item label="日期" prop="date">
                    <el-date-picker v-model="processManagementForm.date" value-format="yyyy-MM-dd hh:mm:ss"
                        type="datetime" placeholder="选择日期时间"></el-date-picker>
                </el-form-item>
                <el-form-item label="食品质量" prop="status">
                    <el-select v-model="processManagementForm.status" placeholder="请选择">
                        <el-option label="不及格" :value="0"></el-option>
                        <el-option label="及格" :value="1"></el-option>
                        <el-option label="优秀" :value="2"></el-option>
                    </el-select>
                </el-form-item>
                <!--  <el-form-item label="分区" prop="iaPartitionId">
          <el-select v-model="processManagementForm.iaPartitionId" placeholder="请选择">
            <el-option
              v-for="(item,index) in plantData"
              :key="item.id"
              :label="item.name"
              :value="item.id">
            </el-option>
          </el-select>
        </el-form-item>-->
                <el-form-item label="备注信息" prop="description">
                    <el-input type="textarea" v-model="processManagementForm.description" :rows="3"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="processManagementDialogVisible = false">关闭</el-button>
                <el-button type="success" size="small" @click="processSaveBtn">保存</el-button>
            </div>
        </el-dialog>

        <!-- 分区成熟 -->
        <el-dialog title="分区成熟" :visible.sync="fqDialogVisible" width="70%">
            <el-table :data="fqData" :stripe="true" tooltip-effect="dark" border size="mini"
                :header-cell-style="{ background: 'rgba(239, 249, 243, 1)', color: '#000' }" class="table-content"
                @selection-change="handleFQSelectionChange">
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column prop="id" label="ID" align="center"></el-table-column>
                <el-table-column prop="plantName" label="分区名称"></el-table-column>
                <el-table-column prop="name" label="大棚名称" align="center"></el-table-column>
                <el-table-column prop="variety" label="品种" align="center"></el-table-column>
                <el-table-column prop="plantName" label="种植名称" align="center"></el-table-column>
                <el-table-column prop="plantDate" label="种植日期" align="center">
                    <template slot-scope="scope">
                        {{ new Date(scope.row.plantDate).toLocaleString() }}
                    </template>
                </el-table-column>
                <el-table-column prop="contractAddr" label="合约地址" align="center"></el-table-column>
                <el-table-column prop="remark" label="备注" align="center"></el-table-column>
            </el-table>
            <div class="page-block" v-if="fqData.length">
                <el-pagination @current-change="handleFQCurrentChange" :current-page="fqPager.page"
                    :page-size="fqPager.size" :page-count="fqPager.pages" layout="total, prev, pager, next, jumper">
                </el-pagination>
            </div>

            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="fqDialogVisible = false">关闭</el-button>
                <el-button type="success" size="small" @click="fqDoBtn">确定成熟</el-button>
            </div>
        </el-dialog>

        <!-- 大棚成熟 -->
        <el-dialog title="大棚成熟" :visible.sync="dpDialogVisible" width="70%">
            <el-table :data="dpData" :stripe="true" tooltip-effect="dark" border size="mini"
                :header-cell-style="{ background: 'rgba(239, 249, 243, 1)', color: '#000' }" class="table-content"
                @selection-change="handleDPSelectionChange">
                <el-table-column type="selection" width="55">
                </el-table-column>
                <el-table-column prop="id" label="ID" align="center"></el-table-column>
                <el-table-column prop="name" label="大棚名称" align="center"></el-table-column>
                <el-table-column prop="contractAddr" label="合约地址"></el-table-column>
                <el-table-column prop="area" label="大棚面积"></el-table-column>
                <el-table-column prop="temperature" label="温度"></el-table-column>
                <el-table-column prop="humidity" label="湿度"></el-table-column>
                <el-table-column prop="bigBreedingQuantity" label="最大区分数量/头"></el-table-column>
                <el-table-column prop="address" label="大棚位置"></el-table-column>
            </el-table>
            <div class="page-block" v-if="dpData.length">
                <el-pagination @current-change="handleDPCurrentChange" :current-page="dpPager.page"
                    :page-size="dpPager.size" :page-count="dpPager.pages" layout="total, prev, pager, next, jumper">
                </el-pagination>
            </div>

            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="dpDialogVisible = false">关闭</el-button>
                <el-button type="success" size="small" @click="dpDoBtn">确定成熟</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import http from "@/utils/request";

    export default {
        data() {
            return {
                processManagementForm: {
                    "date": "",
                    "description": "",
                    "iaPartitionId": "",
                    "name": "",
                    "status": "",
                    "weight": ""
                },
                rules: {
                    name: [{
                        required: true,
                        message: '食品名称不能为空',
                        trigger: 'blur'
                    }],
                    iaPartitionId: [{
                        required: true,
                        message: '分区不能为空',
                        trigger: 'blur'
                    }],
                    date: [{
                        required: true,
                        message: '日期不能为空',
                        trigger: 'blur'
                    }],
                    description: [{
                        required: true,
                        message: '备注不能为空',
                        trigger: 'blur'
                    }],
                    status: [{
                        required: true,
                        message: '食品质量不能为空',
                        trigger: 'blur'
                    }],
                    weight: [{
                        required: true,
                        message: '重量不能为空',
                        trigger: 'blur'
                    }],
                },
                processManagementDialogVisible: false,
                processManagementDialogTitle: '新增',

                processDialogTitle: '加工管理',
                processDialogVisible: false,
                processSearchForm: {
                    startTime: '',
                    endTime: '',
                    keyword: '',
                    url: '',
                    partitionId: ''
                },
                processPager: {
                    pageSize: 10,
                    currentPage: 1,
                    pages: 1,
                },
                processData: [],
                processMultipleSelection: [],

                // 种植列表
                plantData: [],
                plantPager: {
                    pageSize: 10,
                    page: 1,
                    pages: 1,
                },
                plantSearcher: {
                    id: '',
                    pastureName: ''
                },
                statusDict: {
                    '0': '不及格',
                    '1': '及格',
                    '2': '优秀',
                },
                // fq
                fqDialogVisible: false,
                fqData: [],
                fqMultipleSelection: [],
                fqPager: {
                    pageSize: 10,
                    page: 1,
                    pages: 1,
                },

                // dp
                dpDialogVisible: false,
                dpData: [],
                dpMultipleSelection: [],
                dpPager: {
                    pageSize: 10,
                    currentPage: 1,
                    pages: 1,
                },
            }
        },
        async created() {
            await this.getListData()
        },
        methods: {
            async getProcessList() {
                try {
                    const {
                        data
                    } = await http.post('/iaPartitionFood/page', {
                        ...this.processPager,
                        ...this.processSearchForm
                    })
                    this.processData = data.records
                    this.processPager = {
                        currentPage: data.current,
                        pageSize: data.size,
                        pages: data.pages,
                    }
                } catch (e) {
                    this.$message.error('网络错误请重试！');
                }
            },
            async handleProcess(id, title) {
                this.processSearchForm.partitionId = id
                await this.getProcessList()
                this.processDialogTitle = title
                this.processDialogVisible = true
            },
            async handleProcessCurrentChange(val) {
                this.processPager.currentPage = val;
                await this.getProcessList()
            },
            handleProcessSelectionChange(val) {
                this.processMultipleSelection = val;
            },
            async handleProcessDetail(id, title) {
                this.processSearchForm.partitionId = id
                await this.getProcessList()
                this.processDialogTitle = title
                this.processDialogVisible = true
            },
            async handleFinishProcess(id) {
                this.$confirm('确定要完成加工吗?', '提示', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    http.put('/partition/finishProcess', {}, {
                        params: {
                            id
                        }
                    }).then(res => {
                        this.$message({
                            type: 'info',
                            message: res.msg
                        });
                        this.getListData()
                    }).catch(err => {
                        this.$message({
                            type: 'error',
                            message: '操作失败'
                        });
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消'
                    });
                });
            },
            processDoBtn() {

            },

            // fq
            handleFQSelectionChange(val) {
                this.fqMultipleSelection = val;
            },
            async handleFQCurrentChange(val) {
                this.fqPager.page = val
                await this.getFQListData()
            },
            async fqDoBtn() {
                const ids = this.fqMultipleSelection.map(item => item.id)
                try {
                    const data = await http.post('/partition/harvestPartition', ids)
                    await this.getFQListData()
                    this.fqDialogVisible = false
                } catch (e) {
                    this.$message.error('网络错误请重试！');
                }
            },
            async getFQListData() {
                try {
                    const {
                        data
                    } = await http.get("/partition/list", {
                        params: {
                            ...this.fqPager,
                        }
                    })
                    this.fqData = data.records
                    this.fqPager = {
                        pageSize: data.size,
                        page: data.current,
                        pages: data.pages,
                    }
                } catch (e) {
                    this.$message.error('网络错误请重试！');
                }
            },
            async fqRipe() {
                await this.getFQListData()
                this.fqDialogVisible = true
            },
            // dp
            handleDPSelectionChange(val) {
                this.dpMultipleSelection = val
            },
            async handleDPCurrentChange(val) {
                this.dpPager.page = val
                await this.getDPListData()
            },
            async dpDoBtn() {
                const ids = this.dpMultipleSelection.map(item => item.id)
                try {
                    const data = await http.post('/partition/outPastures', ids)
                    await this.getDPListData()
                    this.dpDialogVisible = false
                } catch (e) {
                    this.$message.error('网络错误请重试！');
                }
            },
            async getDPListData() {
                try {
                    const {
                        data
                    } = await http.post("/iaPasture/page", {
                        ...this.dpPager
                    })
                    this.dpData = data.records
                    this.dpPager = {
                        pageSize: data.size,
                        currentPage: data.current,
                        pages: data.pages,
                    }
                } catch (e) {
                    this.$message.error('网络错误请重试！');
                }
            },
            async dpRipe() {
                await this.getDPListData()
                this.dpDialogVisible = true
            },
            async getListData() {
                try {
                    const {
                        data
                    } = await http.get("/partition/harvestList", {
                        params: {
                            ...this.plantPager,
                            ...this.plantSearcher
                        }
                    })
                    console.log(data)
                    this.plantData = data.records
                    this.plantPager = {
                        pageSize: data.size,
                        page: data.current,
                        pages: data.pages,
                    }
                } catch (e) {
                    this.$message.error('网络错误请重试！');
                }
            },
            plantSearch() {
                this.getListData()
            },
            resetPlantSearch() {
                this.plantSearcher = {
                    id: '',
                    pastureName: ''
                }
                this.getListData()
            },
            async handlePlantCurrentChange(val) {
                this.plantPager.page = val;
                await this.getListData()
            },


            // 新增按钮显示弹框
            async addProcess(title, id) {
                this.processManagementDialogTitle = title
                if (!id) {
                    // 新增重置
                    this.processManagementForm = {
                        "date": "",
                        "description": "",
                        "iaPartitionId": this.processSearchForm.partitionId,
                        "name": "",
                        "status": "",
                        "weight": ""
                    }
                } else {
                    // 修改回填
                    try {
                        const {
                            data
                        } = await http.post('/iaPartitionFood/detail', {}, {
                            params: {
                                id
                            }
                        })
                        this.processManagementForm = data
                    } catch (e) {
                        this.$message.error('网络错误请重试！');
                    }
                }
                this.processManagementDialogVisible = true
            },
            // 弹框确定按钮
            processSaveBtn() {
                if (!this.processManagementForm.id) {
                    this.$refs.form.validate((valid) => {
                        if (valid) {
                            http.post('/iaPartitionFood/create', {
                                ...this.processManagementForm
                            }).then(res => {
                                this.getProcessList()
                                this.processManagementDialogVisible = false
                            }).catch(err => {
                                this.$message.error('网络错误请重试！');
                            })
                        } else {
                            return false
                        }
                    })
                } else {
                    this.$refs.form.validate((valid) => {
                        if (valid) {
                            http.post('/iaPartitionFood/update', {
                                ...this.processManagementForm
                            }).then(res => {
                                this.getProcessList()
                                this.processManagementDialogVisible = false
                            }).catch(err => {
                                this.$message.error('网络错误请重试！');
                            })
                        } else {
                            return false
                        }
                    })
                }
            },
            // 详情
            async processDetail(title, id) {
                this.processManagementDialogTitle = title
                try {
                    const {
                        data
                    } = await http.post('/iaPartitionFood/detail', {}, {
                        params: {
                            id
                        }
                    })
                    this.processManagementForm = data
                } catch (e) {
                    this.$message.error('网络错误请重试！');
                }
                this.processManagementDialogVisible = true
            },
            deleteProcess(id) {
                this.$confirm('确定要删除该条数据吗?', '删除', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    http.post('/iaPartitionFood/delete', {}, {
                        params: {
                            id
                        }
                    }).then(res => {
                        if (res.code === 0) {
                            this.$message({
                                type: 'success',
                                message: '删除成功'
                            });
                            this.getProcessList()
                        }
                    }).catch(err => {
                        this.$message({
                            type: 'error',
                            message: '删除失败'
                        });
                    })
                }).catch(() => {
                    this.$message({
                        type: 'info',
                        message: '已取消删除'
                    });
                });
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
