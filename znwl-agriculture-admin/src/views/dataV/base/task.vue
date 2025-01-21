<template>
    <div class="gantt-container">
        <div class="search">
            <el-form :model="queryParams" ref="queryForm" :inline="true" v-show="showSearch" label-width="100px">
                <el-form-item label="任务名称" prop="taskName">
                    <el-input v-model="queryParams.taskName" placeholder="请输入任务名称" clearable size="small"
                        @keyup.enter.native="handleQuery" />
                </el-form-item>
                <el-form-item label="计划开始日期" prop="psr">
                    <el-date-picker size="small" v-model="queryParams.psr" type="daterange" range-separator="-"
                        start-placeholder="时间范围开始" value-format="yyyy-MM-dd" end-placeholder="时间范围结束">
                    </el-date-picker>
                </el-form-item>
                <el-form-item>
                    <el-button type="info" icon="el-icon-search" size="mini" @click="handleQuery">搜索</el-button>
                    <el-button icon="el-icon-refresh" type="info" plain size="mini" @click="resetQuery">重置</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="table">
            <el-table v-if="!gantt" v-loading="loading" element-loading-background="#263840" :data="taskList"
                :border="tableBorder" :header-cell-style="{'background':'#092944','color':'#fff'}"
                :row-class-name="tableRowClassName" :cell-style="{color:'#fff'}">
                <el-table-column label="任务名称" align="center" prop="taskName" />
                <el-table-column label="计划开始日期" align="center" prop="planStart" width="180">
                    <template slot-scope="scope">
                        <span>{{ parseTime(scope.row.planStart, "{y}-{m}-{d}") }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="计划结束日期" align="center" prop="planFinish" width="180">
                    <template slot-scope="scope">
                        <span>{{ parseTime(scope.row.planFinish, "{y}-{m}-{d}") }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="实际开始日期" align="center" prop="actualStart" width="180">
                    <template slot-scope="scope">
                        <span>{{ parseTime(scope.row.actualStart, "{y}-{m}-{d}") }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="实际结束日期" align="center" prop="actualFinish" width="180">
                    <template slot-scope="scope">
                        <span>{{ parseTime(scope.row.actualFinish, "{y}-{m}-{d}") }}</span>
                    </template>
                </el-table-column>
                <el-table-column label="状态" align="center" prop="status">
                    <template slot-scope="scope">
                        <el-tag type="info"
                            effect="dark">{{scope.row.status | translateName(dict.type.agriculture_batch_task_status)}}</el-tag>
                    </template>
                </el-table-column>
            </el-table>
            <pagination v-show="total > 0" :total="total" :page.sync="queryParams.pageNum"
                :limit.sync="queryParams.pageSize" @pagination="getList" :background="false" />
        </div>
    </div>
</template>

<script>
    import {
        listBatchTask,
        delBatchTask
    } from "@/api/agriculture/batchTask";
    export default {
        name: "BatchTask",
        props: {
            batchId: [Number],
            tableBorder: {
                type: Boolean,
                default: false,
            },
        },
        dicts: ['agriculture_batch_task_status'],
        data() {
            return {
                //gantt
                gantt: false,
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
                // 批次任务表格数据
                taskList: [],
                open: false,
                title: '',
                //任务id
                taskId: null,
                //操作类型：add和update
                oprType: null,
                // 查询参数
                queryParams: {
                    pageNum: 1,
                    pageSize: 10,
                    taskName: null,
                    planStart: null,
                    planStartStart: null,
                    planStartEnd: null,
                    planFinish: null,
                    batchId: this.batchId,
                    psr: null,
                },
                tasks: {
                    data: [],
                },
            };
        },
        filters: {
            translateName(val, dict) {
                let data
                dict.forEach(item => {
                    if (val == item.value) {
                        data = item.label
                    }
                });
                return data
            }
        },
        watch: {
            batchId: {
                handler: function(n, o) {
                    this.queryParams.batchId = n;
                    this.getList();
                },
            },
        },
        created() {
            this.getList();
            // gantt.config.columns = [
            //   { name: "text", label: "任务名称", tree: true, width: "200" },
            // ];

            // gantt.attachEvent("onTaskDblClick",(id, e)=> {
            //   this.handleUpdate(Number(id))//TODO: 【项目】导致甘特图左移
            //   return false;
            // });
        },
        methods: {
            /** 查询批次任务列表 */
            getList() {
                this.loading = true;
                const {
                    queryParams: {
                        psr
                    },
                } = this;
                if (psr && Array.isArray(psr) && psr.length == 2) {
                    this.queryParams.params = {
                        planStartStart: psr[0],
                        planStartEnd: psr[1],
                    };
                }
                listBatchTask(this.queryParams).then((response) => {
                    this.taskList = response.rows;
                    this.total = response.total;
                    this.loading = false;
                    this.tasks.data = this.taskList.map((item) => ({
                        text: item.taskName,
                        id: item.taskId,
                        start_date: new Date(`${item.planStart} 00:00:00`),
                        end_date: new Date(`${item.planFinish} 00:00:00`),
                        color: "#2b7",
                    }));
                });
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
            /** 修改按钮操作 */
            handleUpdate(taskId) {
                this.taskId = taskId;
                this.oprType = 'update';
                this.open = true;
                this.title = "编辑任务";
            },
            /** 删除按钮操作 */
            handleDelete(row) {
                const taskIds = row.taskId || this.ids;
                this.$modal
                    .confirm('是否确认删除批次任务编号为"' + taskIds + '"的数据项？')
                    .then(function() {
                        return delBatchTask(taskIds);
                    })
                    .then(() => {
                        this.getList();
                        this.$modal.msgSuccess("删除成功");
                    })
                    .catch(() => {});
            },
            /** 导出按钮操作 */
            handleExport() {
                this.download(
                    "agriculture/task/export", {
                        ...this.queryParams,
                    },
                    `task_${new Date().getTime()}.xlsx`
                );
            },
            /** 处理切换按钮操作 */
            // handleChange(type) {
            //   if (type == "gantt") {
            //     this.gantt = true;
            //   } else {
            //     this.gantt = false;
            //   }
            // },

            cancel() {
                this.getList();
            },
            tableRowClassName({
                row,
                rowIndex
            }) {
                return "even"
            }
        },
    };
</script>
<style lang="scss" scoped>
    $colorL1: #0c2438;
    $colorL2: #092944;
    $colorL3: #0d3758;
    $colorL4: #132f41;

    .gantt-container {
        height: 100%;

        .table {
            background: #0B2439;
            padding: 0 20px;
            height: calc(100% - 70px - 70px);

            .gantt {
                overflow: hidden;
                position: relative;
                height: 100%;
            }

            ::v-deep .el-table {
                .even {
                    background: #132f41;
                }
            }

            ::v-deep .el-table--enable-row-hover .el-table__body tr:hover>td {
                background-color: rgba(0, 0, 0, 0) !important;
            }
        }

        .search {
            background: #0B2439;
            height: 70px;
            display: flex;
            align-items: center;
            position: sticky;
            top: 0px;
            z-index: 2;

            &::v-deep .el-form {
                color: #fff;

                .el-form-item__label {
                    color: #B9DBEA;
                }

                input {
                    border: none;
                    color: #fff
                }

                .el-date-editor {
                    border: none;
                    color: #fff
                }

                .el-input__inner,
                .el-range-input {
                    background: #132F42;
                }
            }
        }
    }

    .pagination-container ::v-deep {
        height: 50px;
        background: #0B2439;

        .el-pagination {

            .el-pagination__total,
            .el-pagination__jump {
                color: #fff;
            }

            .el-input__inner,
            button,
            .el-pager .number,
            .el-icon {
                background: #132F42;
                color: #fff;
            }

            .el-pager li.active {
                font-weight: 900;
                font-size: 16px;
            }

            input {
                border: none;
                color: #fff
            }
        }
    }

    .search ::v-deep .el-form-item {
        margin-bottom: 0;
    }

    .search ::v-deep .el-form {
        flex: 1;
    }
</style>
