<template>
    <div class="app-container-sm">
        <el-card class="card-margin-bottom">
            <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px"
                class="form-minus-bottom">
                <el-form-item label="批次名称" prop="batchName">
                    <el-input v-model="queryParams.batchName" placeholder="请输入批次名称" clearable size="small"
                        @keyup.enter.native="handleQuery" />
                </el-form-item>
                <el-form-item label="种质" prop="germplasmId">
                    <el-select v-model="queryParams.germplasmId" size="small" placeholder="请选择种质">
                        <el-option v-for="germplasm in germplasmList" :key="germplasm.germplasmId"
                            :label="germplasm.germplasmName" :value="germplasm.germplasmId"></el-option>
                    </el-select>
                </el-form-item>
                <!-- <el-form-item label="地块" prop="landId">
         <el-select v-model="queryParams.landId" size="small" placeholder="请选择地块">
            <el-option
              v-for="land in landList"
              :key="land.landId"
              :label="land.landName"
              :value="land.landId"
            ></el-option>
          </el-select>
      </el-form-item> -->
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                    <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                </el-form-item>
                <el-form-item class="fr">
                    <el-button type="primary" plain icon="el-icon-plus" size="mini" @click="handleAdd"
                        v-hasPermi="['agriculture:batch:add']">新增</el-button>
                    <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                        v-hasPermi="['agriculture:batch:export']">导出</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="card-padding-bottom">
            <el-table v-loading="loading" :data="batchList">
                <el-table-column type="index" label="序号"> </el-table-column>
                <el-table-column label="批次名称" align="center" prop="batchName" />
                <el-table-column label="种质" align="center" prop="germplasmId">
                    <template slot-scope="scope">
                        <data-tag :options="germplasmList" :value="scope.row.germplasmId" labelName="germplasmName"
                            valueName="germplasmId" type="" />
                    </template>
                </el-table-column>
                <el-table-column label="负责人" align="center" prop="batchHead">
                    <template slot-scope="scope">
                        <data-tag :options="userList" :value="scope.row.batchHead" labelName="userName"
                            valueName="userId" type="notag" />
                    </template>
                </el-table-column>
                <el-table-column label="种质图片" align="center" prop="introImg" width="180">
                    <template v-slot:default="{ row }">
                        <div class="image" @click="previewImage(`${image.baseUrl + row.germplasmImg}`, row)">
                            <img style="width:50px;height:50px;" :src="`${image.baseUrl + row.germplasmImg}`" />
                        </div>
                    </template>
                </el-table-column>
                <el-table-column label="所属大棚" align="center" prop="landId">
                    <template slot-scope="scope">
                        {{ getLabel(scope.row.landId) }}
                        <!-- <data-tag :options="landList" :value="scope.row.landId" labelName="landName" valueName="landId"
              type="warning" /> -->
                    </template>
                </el-table-column>
                <el-table-column label="状态" align="center" prop="status">
                    <template slot-scope="scope">
                        {{ scope.row.status ==1 ? '已成熟' : '已采摘' }}
                    </template>
                </el-table-column>
                <!-- :options="scope.row.status==0?'未完成':'已完成'" -->
                <el-table-column label="种植面积(亩)" align="center" prop="cropArea" />
                <el-table-column label="合约地址" align="center" prop="contractAddress" />
                <el-table-column label="开始时间" align="center" prop="startTime" width="180">
                    <template slot-scope="scope">
                        <span>{{ parseTime(scope.row.startTime, '{y}-{m}-{d}') }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="操作" align="center" class-name="small-padding fixed-width" width="300">
                    <template slot-scope="scope">
                        <el-button size="small" class="padding-5" type="primary" icon="el-icon-edit"
                            @click="handleProcess(scope.row.batchId, '采摘')">{{scope.row.status==1?"采摘":"采摘详情"}}</el-button>
                        <el-button size="small" class="padding-5" plain type="warning" icon="el-icon-s-claim"
                            @click="handleBatchTask(scope.row)"
                            v-hasPermi="['agriculture:batchTask:list']">批次任务</el-button>
                    </template>
                </el-table-column>
            </el-table>

            <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize" @pagination="getList" />
        </el-card>
        <!-- 添加或修改作物批次对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-alert class="margin-bottom-10" title="新建批次会根据选择种质的种植流程生成批次任务，新增完成可以到批次任务管理界面调整批次任务！" type="warning"
                effect="light" show-icon closable></el-alert>

            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
                <el-form-item label="批次名称" prop="batchName">
                    <el-input v-model="form.batchName" placeholder="请输入批次名称" />
                </el-form-item>
                <el-form-item label="大棚" prop="houeId">
                    <el-select v-model="form.landId" placeholder="请选择大棚">
                        <el-option v-for="houe in houeList" :key="houe.id" :label="houe.name"
                            :value="houe.id"></el-option>
                    </el-select>
                </el-form-item>
                <el-form-item label="种质" prop="germplasmId">
                    <el-select v-model="form.germplasmId" placeholder="请选择种质">
                        <el-option v-for="germplasm in germplasmList" :key="germplasm.germplasmId"
                            :label="germplasm.cropName" :value="germplasm.germplasmId"></el-option>
                    </el-select>
                </el-form-item>
                <!-- <el-form-item label="地块" prop="landId">
          <el-select v-model="form.landId" placeholder="请选择地块">
            <el-option
              v-for="land in landList"
              :key="land.landId"
              :label="land.landName"
              :value="land.landId"
            ></el-option>
          </el-select>
        </el-form-item> -->
                <el-form-item label="种植面积" prop="cropArea">
                    <el-input v-model="form.cropArea" placeholder="请输入种植面积">
                        <template slot="append">亩</template>
                    </el-input>
                </el-form-item>
                <el-form-item label="开始时间" prop="startTime">
                    <el-date-picker clearable size="medium" v-model="form.startTime" type="date"
                        value-format="yyyy-MM-dd" placeholder="选择开始时间">
                    </el-date-picker>
                </el-form-item>
                <el-form-item label="负责人" prop="batchHead">
                    <el-select v-model="form.batchHead" placeholder="请选择负责人">
                        <el-option v-for="user in userList" :key="user.userId" :label="user.nickName"
                            :value="user.userId"></el-option>
                    </el-select>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
        <!-- 图片预览对话框 -->
        <el-dialog :title="image.title" :visible.sync="image.open" width="240px">
            <img style="width:200px;height:200px;" :src="image.imgUrl" />
        </el-dialog>
        <!-- 种植计划对话框 -->
        <el-dialog v-if="batchTask.open" :title="batchTask.title" :visible.sync="batchTask.open" width="1300px">
            <div style="height:500px;width:100%;overflow:auto;">
                <task :batchId="this.batchTask.batchId" :tableBorder="true"></task>
            </div>
        </el-dialog>




        <el-dialog :title='processData.length?"采摘详情":"采摘"' :visible.sync="processDialogVisible" width="70%">

            <div style="display: flex; justify-content: flex-end; padding-bottom: 10px">
                <el-button v-if="!processData.length" type="success" size="mini" plain @click="addProcess('新增')">&nbsp;
                    新增
                    &nbsp;</el-button>
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
                <!-- <el-table-column label="操作" v-if="processDialogTitle !== '加工详情'">
                    <template slot-scope="scope">
                        <div class="do-text">
                            <el-link class="txt-btn" type="success"
                                @click="handleProcess(scope.row.id, '加工管理')">修改</el-link>
                            <el-link class="txt-btn" type="success"
                                @click="handleProcessDetail(scope.row.id, '加工详情')">详情</el-link>
                            <el-link class="txt-btn" type="danger" @click="deleteProcess(scope.row.id)">删除</el-link>
                        </div>
                    </template>
                </el-table-column> -->
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
                    <el-input v-model="processManagementForm.weight" required>
                        <template slot="append">kg
                        </template>
                    </el-input>
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
    </div>
</template>

<script>
    import {
        listBatch,
        getBatch,
        delBatch,
        addBatch,
        updateBatch
    } from "@/api/agriculture/batch";

    import {
        listGermplasm
    } from "@/api/agriculture/germplasm";
    import {
        listUser
    } from "@/api/system/user";
    import {
        listLand
    } from "@/api/agriculture/land";
    import Task from "../components/Task";
    import http from "@/utils/request";

    export default {
        name: "Batch",
        dicts: ['agriculture_batch_task_status'],
        data() {
            return {
                //图片预览
                image: {
                    baseUrl: window.location.origin + process.env.VUE_APP_BASE_API,
                    open: false,
                    imgUrl: '',
                    title: ''
                },
                //种植计划
                batchTask: {
                    open: false,
                    title: '',
                    batchId: null
                },
                houeList: [],
                // 遮罩层
                loading: true,
                // 选中数组
                ids: [],
                // 非单个禁用
                single: true,
                // 非多个禁用
                multiple: true,
                // 显示搜索条件
                showSearch: true,
                // 总条数
                total: 0,
                // 作物批次表格数据
                batchList: [],
                //地块下拉数据
                landList: [],
                //种质下拉数据
                germplasmList: [],
                //用户下拉数据
                userList: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    batchName: null,
                    germplasmId: null,
                    landId: null,
                    status: 10
                },
                // 表单参数
                form: {},
                // 表单校验
                rules: {
                    batchName: [{
                        required: true,
                        message: "批次名称不能为空",
                        trigger: "blur"
                    }],
                    germplasmId: [{
                        required: true,
                        message: "种质ID不能为空",
                        trigger: "blur"
                    }],
                    landId: [{
                        required: true,
                        message: "地块ID不能为空",
                        trigger: "blur"
                    }],
                    cropArea: [{
                        required: true,
                        message: "种植面积不能为空",
                        trigger: "blur"
                    }],
                    startTime: [{
                        required: true,
                        message: "开始时间不能为空",
                        trigger: "blur"
                    }],
                    batchHead: [{
                        required: true,
                        message: "负责人不能为空",
                        trigger: "blur"
                    }],
                },



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
                batchId: null
            };
        },
        created() {
            this.getList();
            this.getLandList();
            this.getGermplasmList();
            this.getUserList();
            this.$http.get("/dev-api/iaPasture/list").then(res => {
                this.houeList = res.data.data;
                console.log(this.houe)
            })
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
                console.log(id, '11111111111111');
                this.processSearchForm.id = id
                this.batchId = id
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
                                updateBatch({
                                    status: 2,
                                    batchId: this.batchId
                                }).then(response => {
                                    this.$modal.msgSuccess("修改成功");
                                    this.open = false;
                                    this.getList();
                                });
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





            /** 查询作物批次列表 */
            getLabel(landId) {
                const houe = this.houeList.find(item => item.id === landId);
                return houe ? houe.name : '未知大棚';
            },
            getList() {
                this.loading = true;
                listBatch(this.queryParams).then(response => {
                    this.batchList = response.rows;
                    this.total = response.total;
                    this.loading = false;
                });
            },
            /** 查询地块列表 */
            getLandList() {
                listLand().then(response => {
                    this.landList = response.rows;
                });
            },
            /** 查询种质列表 */
            getGermplasmList() {
                listGermplasm().then(response => {
                    this.germplasmList = response.rows;
                });
            },
            /** 查询用户列表 */
            getUserList() {
                listUser().then(response => {
                    this.userList = response.rows;
                });
            },
            // 取消按钮
            cancel() {
                this.open = false;
                this.reset();
            },
            // 表单重置
            reset() {
                this.form = {
                    batchId: null,
                    batchName: null,
                    germplasmId: null,
                    landId: null,
                    cropArea: null,
                    startTime: null,
                    batchHead: null,
                    remark: null,
                    status: "0",
                    orderNum: null,
                    createBy: null,
                    createTime: null,
                    updateBy: null,
                    updateTime: null,
                    delFlag: null
                };
                this.resetForm("form");
            },
            /** 搜索按钮操作 */
            handleQuery() {
                this.queryParams.pageNum = 1;
                this.getList();
            },
            /** 重置按钮操作 */
            resetQuery() {
                this.resetForm("queryForm");
                this.handleQuery();
            },
            /** 新增按钮操作 */
            handleAdd() {
                this.reset();
                this.open = true;
                this.title = "添加作物批次";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const batchId = row.batchId || this.ids
                getBatch(batchId).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改作物批次";
                });
            },
            /** 提交按钮 */
            submitForm() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.batchId != null) {
                            updateBatch(this.form).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addBatch(this.form).then(response => {
                                this.$modal.msgSuccess("新增成功");
                                this.open = false;
                                this.getList();
                            });
                        }
                    }
                });
            },
            /** 删除按钮操作 */
            handleDelete(row) {
                const batchIds = row.batchId || this.ids;
                this.$modal.confirm('是否确认删除作物批次编号为"' + batchIds + '"的数据项？').then(function() {
                    return delBatch(batchIds);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {});
            },
            /** 图片预览按钮操作 */
            previewImage(imgUrl, row) {
                this.image.title = row.version
                this.image.open = true;
                this.image.imgUrl = imgUrl;
            },
            /** 导出按钮操作 */
            handleExport() {
                this.download('agriculture/batch/export', {
                    ...this.queryParams
                }, `batch_${new Date().getTime()}.xlsx`)
            },
            /** 种植计划按钮操作 */
            handleBatchTask(row) {
                this.batchTask.open = true;
                this.batchTask.title = '种植计划';
                this.batchTask.batchId = row.batchId;
            }
        },
        components: {
            Task
        }
    };
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

    ::v-deep .el-select {
        width: 100%;
    }

    ::v-deep .el-date-editor.el-input {
        width: 100%;
    }
</style>
