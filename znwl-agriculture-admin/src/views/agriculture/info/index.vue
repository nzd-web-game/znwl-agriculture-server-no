<template>
    <div class="app-container-sm">
        <el-card class="card-margin-bottom">
            <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="68px"
                class="form-minus-bottom">
                <el-form-item label="创建时间">
                    <el-date-picker v-model="dateRange" size="small" style="width: 240px" value-format="yyyy-MM-dd"
                        type="daterange" range-separator="-" start-placeholder="开始日期"
                        end-placeholder="结束日期"></el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="primary" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                    <el-button icon="el-icon-refresh" size="mini" @click="resetQuery">重置</el-button>
                </el-form-item>
                <el-form-item class="fr">

                    <el-button type="warning" plain icon="el-icon-download" size="mini" @click="handleExport"
                        v-hasPermi="['agriculture:info:export']">导出</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card class="card-padding-bottom">
            <el-table v-loading="loading" :data="infoList">
                <el-table-column label="预警大棚" align="center" prop="greenhouse" />
                <el-table-column label="预警农作物批次" align="center" prop="cropBatch" />
                <el-table-column label="预警分区信息" align="center" prop="partitionInfo" />
                <el-table-column label="预警阈值" align="center" prop="thresholdValue" />
                <el-table-column label="预警状态" align="center" prop="warningStatus" />
                <el-table-column label="预警负责人" align="center" prop="responsiblePerson" />
                <el-table-column label="区块地址" align="center" prop="handler" />
                <el-table-column label="预警开始时间" align="center" prop="startTime" width="180">
                    <template slot-scope="scope">
                        <span>{{ parseTime(scope.row.startTime) }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="预警结束时间" align="center" prop="endTime" width="180">
                    <template slot-scope="scope">
                        <span>{{ parseTime(scope.row.endTime) }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="创建时间" align="center" prop="createdAt" width="180">
                    <template slot-scope="scope">
                        <span>{{ parseTime(scope.row.createdAt) }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="更新时间" align="center" prop="updatedAt" width="180">
                    <template slot-scope="scope">
                        <span>{{ parseTime(scope.row.updatedAt) }}</span>
                    </template>
                </el-table-column>
                <!-- <el-table-column label="操作" align="center" class-name="small-padding fixed-width">
                    <template slot-scope="scope">
                        <el-button size="mini" type="primary" class="padding-5" icon="el-icon-edit"
                            @click="handleUpdate(scope.row)" v-hasPermi="['agriculture:info:edit']">修改</el-button>
                        <el-button size="mini" type="danger" class="padding-5" icon="el-icon-delete"
                            @click="handleDelete(scope.row)" v-hasPermi="['agriculture:info:remove']">删除</el-button>
                    </template>
                </el-table-column> -->
            </el-table>

            <pagination v-show="total>0" :total="total" :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize" @pagination="getList" />
        </el-card>
        <!-- 添加或修改预警信息对话框 -->
        <el-dialog :title="title" :visible.sync="open" width="500px" append-to-body>
            <el-form ref="form" :model="form" :rules="rules" label-width="80px">
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button type="primary" @click="submitForm">确 定</el-button>
                <el-button @click="cancel">取 消</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    import {
        listInfo,
        getInfo,
        delInfo,
        addInfo,
        updateInfo
    } from "@/api/agriculture/info";

    export default {
        name: "Info",
        data() {
            return {
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
                // 预警信息表格数据
                infoList: [],
                dateRange: [],
                // 弹出层标题
                title: "",
                // 是否显示弹出层
                open: false,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    greenhouse: null,
                    cropBatch: null,
                    partitionInfo: null,
                },
                // 表单参数
                form: {},
                // 表单校验
                rules: {}
            };
        },
        created() {
            this.getList();
        },
        methods: {
            /** 查询预警信息列表 */
            getList() {
                this.loading = true;
                listInfo(this.queryParams).then(response => {
                    this.infoList = response.rows;
                    this.total = response.total;
                    this.loading = false;
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
                    id: null,
                    greenhouse: null,
                    cropBatch: null,
                    partitionInfo: null,
                    thresholdValue: null,
                    warningStatus: "0",
                    responsiblePerson: null,
                    handler: null,
                    startTime: null,
                    endTime: null,
                    createdAt: null,
                    updatedAt: null,
                    delFlag: null
                };
                this.resetForm("form");
            },
            /** 搜索按钮操作 */
            handleQuery() {
                this.dateRange = [];
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
                this.title = "添加预警信息";
            },
            /** 修改按钮操作 */
            handleUpdate(row) {
                this.reset();
                const id = row.id || this.ids
                getInfo(id).then(response => {
                    this.form = response.data;
                    this.open = true;
                    this.title = "修改预警信息";
                });
            },
            /** 提交按钮 */
            submitForm() {
                this.$refs["form"].validate(valid => {
                    if (valid) {
                        if (this.form.id != null) {
                            updateInfo(this.form).then(response => {
                                this.$modal.msgSuccess("修改成功");
                                this.open = false;
                                this.getList();
                            });
                        } else {
                            addInfo(this.form).then(response => {
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
                const ids = row.id || this.ids;
                this.$modal.confirm('是否确认删除预警信息编号为"' + ids + '"的数据项？').then(function() {
                    return delInfo(ids);
                }).then(() => {
                    this.getList();
                    this.$modal.msgSuccess("删除成功");
                }).catch(() => {});
            },
            /** 导出按钮操作 */
            handleExport() {
                this.download('agriculture/info/export', {
                    ...this.queryParams
                }, `info_${new Date().getTime()}.xlsx`)
            }
        }
    };
</script>
