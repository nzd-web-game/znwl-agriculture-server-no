<template>
    <div>
        <!-- 顶部导航 -->
        <header-top></header-top>
        <el-card class="card-margin-bottom">
            <el-form :inline="true" class="origin-form">
                <el-form-item label="合约地址/溯源码">
                    <el-input v-model="originName" placeholder="请输入合约地址/溯源码进行查询"></el-input>
                </el-form-item>
                <el-button type="success" @click="originSearch">查询</el-button>
            </el-form>
        </el-card>
        <div class="app-container-gray">
            <el-row class="box" :gutter="10">
                <!-- 左侧：任务列表 -->
                <el-col :span="5" class="left">
                    <el-card class="box-card" shadow="hover">
                        <div slot="header" class="clearfix">
                            <span>任务列表</span>
                        </div>
                        <div class="tasks-list">
                            <el-empty v-if="!taskList.length" description="暂无任务数据"></el-empty>
                            <el-descriptions v-else :border="true" :column="1" class="task-descriptions">
                                <el-descriptions-item v-for="(task, index) in taskList" :key="task.taskId"
                                    :label="task.taskName">
                                    <p>计划开始时间：{{ task.planStart }}</p>
                                    <p>计划结束时间：{{ task.planFinish }}</p>
                                    <p v-if="task.actualStart">实际开始时间：{{ task.actualStart }}</p>
                                    <p v-if="task.actualFinish">实际结束时间：{{ task.actualFinish }}</p>
                                    <p>负责人：{{ task.taskHeadName || '未指定' }}</p>
                                    <p>批次：{{ task.batchName }}</p>
                                </el-descriptions-item>
                            </el-descriptions>
                        </div>
                    </el-card>
                </el-col>

                <!-- 中间：信息展示 -->
                <el-col :span="5" class="center">
                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span>大棚信息</span>
                        </div>
                        <el-empty v-if="!ivPastureInfo" description="暂无大棚信息"></el-empty>
                        <el-descriptions v-else column="1" border>
                            <el-descriptions-item label="大棚名称">{{ ivPastureInfo.name }}</el-descriptions-item>
                            <el-descriptions-item label="合约地址">{{ ivPastureInfo.area }}</el-descriptions-item>
                            <el-descriptions-item label="大棚位置">{{ ivPastureInfo.position }}</el-descriptions-item>
                        </el-descriptions>
                    </el-card>

                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span>分区信息</span>
                        </div>
                        <el-empty v-if="!iaPartitionInfo" description="暂无分区信息"></el-empty>
                        <el-descriptions v-else column="1" border>
                            <el-descriptions-item label="分区名称">{{ iaPartitionInfo.name }}</el-descriptions-item>
                            <el-descriptions-item label="种植品种">{{ iaPartitionInfo.variety }}</el-descriptions-item>
                            <el-descriptions-item label="种植日期">{{ iaPartitionInfo.dateT }}</el-descriptions-item>
                        </el-descriptions>
                    </el-card>

                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span>商品信息</span>
                        </div>
                        <el-empty v-if="!shopInfo" description="暂无商品信息"></el-empty>
                        <el-descriptions v-else column="1" border>
                            <el-descriptions-item label="食品名称">{{ shopInfo.name }}</el-descriptions-item>
                            <el-descriptions-item label="加工日期">{{ shopInfo.datet }}</el-descriptions-item>
                            <el-descriptions-item label="食品质量">{{ shopInfo.quality }}</el-descriptions-item>
                            <el-descriptions-item label="食品重量">{{ shopInfo.weight }}kg</el-descriptions-item>
                        </el-descriptions>
                    </el-card>
                </el-col>
                <!-- 右侧：环境信息 -->
                <el-col :span="14" class="right">
                    <el-card class="box-card">
                        <div slot="header" class="clearfix">
                            <span>环境信息</span>
                        </div>
                        <div class="sf-content">
                            <el-table :data="tableData" border style="width: 100%">
                                <el-table-column prop="day" label="日期">
                                    <template slot-scope="scope">
                                        <span>{{ parseTime(scope.row.day,"{y}-{m}-{d}") }}</span>
                                    </template>
                                </el-table-column>
                                <el-table-column prop="avg_temperature" label="平均温度"></el-table-column>
                                <el-table-column prop="avg_humidity" label="平均湿度"></el-table-column>
                                <el-table-column prop="avg_airquality" label="空气质量"></el-table-column>
                                <el-table-column prop="avg_pressure" label="大气压强"></el-table-column>
                                <el-table-column prop="avg_lux" label="光照"></el-table-column>
                                <el-table-column prop="avg_soil_temperature" label="土壤温度"></el-table-column>
                            </el-table>
                            <div class="page-block">
                                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                                    :current-page="currentPage" :page-size="pSize"
                                    layout="total, prev, pager, next, jumper" :total="totalPage">
                                </el-pagination>
                            </div>
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </div>

    </div>
</template>


<script>
    import HeaderTop from "@/components/common/Header.vue";
    import sf from "@/components/origin/sf";
    import environment from "@/components/origin/environment";
    import {
        allBatchTask
    } from "@/api/agriculture/batchTask";
    import {
        listBatchTask
    } from "@/api/agriculture/batchTask";
    import http from "@/utils/request";
    export default {
        data() {
            return {
                originName: '',
                tabStatus: 1,
                ivPastureInfo: {},
                iaPartitionInfo: {},
                shopInfo: {},
                taskList: [],
                fertilizationSteps: [], // 存储施肥步骤
                activeStep: 0, // 当前激活的步骤索引
                tasks: [],
                envTime: "",
                entableData: [],
                tableData: [],
                currentPage: 1,
                pSize: 8,
                totalPage: 0,
                tableHeaderStyle: {
                    background: 'rgba(239, 249, 243, 1)',
                    color: '#000'
                }
            };
        },
        components: {
            HeaderTop,
            environment,
        },
        methods: {
            originSearch() {
                // 清理并设置新的溯源码
                localStorage.setItem("syInfo", this.originName);

                // 重置数据
                this.ivPastureInfo = {};
                this.iaPartitionInfo = {};
                this.shopInfo = {};
                this.iaPartitionFood
                // 发送请求获取追溯信息
                this.$http.get("/dev-api/iaPartitionFoodSensorValue/getTraceability", {
                        params: {
                            id: this.originName
                        },
                    })
                    .then((res) => {
                        if (res.data.code === 0) {
                            // 使用 mapInfo 方法映射数据
                            this.ivPastureInfo = this.mapInfo(res.data.data.ivPastureInfo, {
                                _greenhouseName: "name",
                                _greenhouseArea: "area",
                                _greenhousePosition: "position",
                            });

                            this.iaPartitionInfo = this.mapInfo(res.data.data.iaPartitionInfo, {
                                _partitionsName: "name",
                                _plantingVarieties: "variety",
                                _plantingDate: "dateT",
                                _id: "id",
                            });
                            this.getStepsList(this.originName)

                            this.tableData=res.data.data.map
                            // 处理 shopInfo 数据
                            const sensorValueInfo = res.data.data.iaPartitionFoodSensorValueInfo[0]?.listValues ||
                            [];
                            this.shopInfo = this.mapInfo(sensorValueInfo, {
                                foodName: "name",
                                processingTimestamp: "datet",
                                quality: "quality",
                                weight: "weight",
                            });

                            // 格式化日期和质量
                            if (this.shopInfo.datet) {
                                this.shopInfo.datet = new Date(parseInt(this.shopInfo.datet)).toLocaleString();
                            }

                            this.shopInfo.quality = this.shopInfo.quality === "2" ? "优秀" :
                                this.shopInfo.quality === "1" ? "及格" : "不合格";

                            // this.fetchData()
                        } else {
                            this.$message.error(res.data.msg);
                        }
                    })
                    .catch((error) => {
                        console.error("请求失败:", error);
                        this.$message.error("数据加载失败，请稍后再试");
                    });
            },
            async getProcessList(id) {
                try {
                    const {
                        data
                    } = await http.post('/iaPartitionFood/detail', {}, {
                        params: {
                            id
                        }
                    });
                    return data.iaPartitionId;
                } catch (e) {
                    this.$message.error('网络错误请重试！');
                    throw e; // 抛出异常，便于调用方捕获
                }
            },

            async getStepsList(id) {
                try {
                    const batchId = await this.getProcessList(id); // 等待获取批次ID

                    const response = await listBatchTask({
                        pageNum: 1,
                        pageSize: 100,
                        batchId,
                    });

                    const {
                        rows,
                        total
                    } = response;
                    this.taskList = rows;
                    this.total = total;
                    this.loading = false;
                    // 映射任务数据用于显示
                    this.tasks.data = this.taskList.map((item) => ({
                        text: item.taskName,
                        id: item.taskId,
                        start_date: new Date(`${item.planStart} 00:00:00`),
                        end_date: new Date(`${item.planFinish} 00:00:00`),
                        color: "#2b7",
                    }));
                } catch (error) {
                    console.error("获取任务列表失败:", error);
                    this.$message.error("任务列表加载失败，请稍后再试");
                }
            },

            getStatusText(status) {
                switch (status) {
                    case "1":
                        return "未开始";
                    case "2":
                        return "进行中";
                    case "3":
                        return "已完成";
                    default:
                        return "未知状态";
                }
            },
            mapInfo(data, fields) {
                const result = {};
                data.forEach((item) => {
                    const key = fields[item.name];
                    if (key) result[key] = item.stringValue ? item.stringValue.value : item.numericValue.value;
                });
                return result;
            },
            async fetchData(startTime = this.formatDate(new Date()), endTime = this.formatDate(new Date())) {
                const res = await this.$http.post("/dev-api/iaPartitionFoodSensorValue/getEnvironmentPage", {
                    iaPartitionFoodId: this.originName, // 使用搜索框中的溯源码
                    currentPage: this.currentPage,
                    pageSize: this.pSize,
                    startTime,
                    endTime
                });
                if (res.data.code === 0 && res.data.data.records.length > 0) {
                    this.entableData = this.transformData(res.data.data.records);
                    this.totalPage = res.data.data.total;
                } else {
                    this.$message.error("暂无数据");
                }
            },
            transformData(data) {
                return data.map(item => {
                    const obj = {};
                    item.listValues.forEach(t => {
                        if (t.name === "id") obj.id = t.numericValue.value;
                        if (t.name === "temperature") obj.temperature = t.stringValue.value;
                        if (t.name === "humidity") obj.humidity = t.stringValue.value;
                        if (t.name === "airquality") obj.airquality = t.stringValue.value;
                        if (t.name === "pressure") obj.pressure = t.stringValue.value;
                        if (t.name === "date") obj.dateT = t.stringValue.value;
                    });
                    return obj;
                });
            },
            handleSizeChange(val) {
                this.pSize = val;
                this.fetchData();
            },
            handleCurrentChange(val) {
                this.currentPage = val;
                this.fetchData();
            },
            formatDate(date) {
                const year = date.getFullYear();
                const month = (date.getMonth() + 1).toString().padStart(2, "0");
                const day = date.getDate().toString().padStart(2, "0");
                return `${year}-${month}-${day}`;
            },
            enSearch() {
                if (this.envTime && this.envTime.length === 2) {
                    const [start, end] = this.envTime.map(this.formatDate);
                    this.fetchData(start, end);
                } else {
                    this.$message.warning("请选择日期范围");
                }
            },
            resetFields() {
                this.envTime = "";
                this.fetchData();
            }

        },
    };
</script>

<style lang="scss" scoped>
    .box {
        height: calc(100vh - 84px);
        /* 计算主区域高度 */
        display: flex;
        /* 使用 Flex 布局 */
        gap: 20px;
        /* 左右列间距 */
    }

    .left {
        background: #fff;
        border-radius: 10px 0 0 10px;
        padding: 15px;
        overflow-y: auto;
        /* 保证内容溢出时可滚动 */
        box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
        /* 轻微阴影，避免视觉干扰 */
    }

    .right {
        background: #fff;
        height: 100%;
        /* 高度自适应父容器 */
        border-radius: 0 10px 10px 0;
        border-left: 1px solid #eee;
        padding: 15px;
        overflow-y: auto;
        /* 内容溢出滚动 */
    }

    .tasks-list {
        max-height: 80vh;
        /* 限制任务列表高度，防止超出页面 */
        overflow-y: auto;
        margin-top: 15px;
    }

    .task-card {
        margin-bottom: 15px;
        /* 任务卡片间距 */
        box-shadow: none;
        /* 去除卡片默认阴影 */
        border: 1px solid #eee;
        border-radius: 8px;
    }
</style>
