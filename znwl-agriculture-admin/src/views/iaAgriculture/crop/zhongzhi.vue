<template>
    <div class="list-content">
        <div class="form-top">
            <el-form :inline="true" class="demo-form-inline">
                <el-form-item label="牧场名称">
                    <el-input v-model="mcName" placeholder="请输入名称" size="small" class="inpname"></el-input>
                </el-form-item>
                <el-form-item>
                    <el-button type="success" size="mini" @click="search">&nbsp;查询&nbsp;</el-button>
                    <el-button @click="resetName" size="mini">&nbsp;重置&nbsp;</el-button>
                </el-form-item>
            </el-form>
        </div>
        <div class="plant-do">
            <el-button type="danger" size="mini" plain>批量删除</el-button>
            <div class="do-right">
                <div class="desc-list">
                    <div class="desc-item">
                        <p>食品质量 </p>
                        <ul>
                            <li><img src="../../../assets/dot1.png" alt="" class="dot"> <span>不及格</span></li>
                            <li><img src="../../../assets/dot2.png" alt="" class="dot"> <span>优秀</span></li>
                            <li><img src="../../../assets/dot3.png" alt="" class="dot"> <span>及格</span></li>
                        </ul>
                    </div>
                    <div class="desc-item">
                        <p>设备状态</p>
                        <ul>
                            <li><img src="../../../assets/dot1.png" alt="" class="dot"> <span>离线中</span></li>
                            <li><img src="../../../assets/dot2.png" alt="" class="dot"> <span>在线</span></li>
                        </ul>
                    </div>
                    <div class="desc-item">
                        <p>健康状态</p>
                        <ul>
                            <li><img src="../../../assets/dot1.png" alt="" class="dot"> <span>生病</span></li>
                            <li><img src="../../../assets/dot2.png" alt="" class="dot"> <span>健康</span></li>
                        </ul>
                    </div>
                </div>
                <el-button type="success" size="mini" plain @click="addPlant">&nbsp; 新增 &nbsp;</el-button>
            </div>
        </div>
        <div class="plant-table">
            <el-table ref="multipleTable" :data="tableData" :stripe="true" tooltip-effect="dark" border size="mini"
                style="width: 100%" @selection-change="handleSelectionChange"
                :header-cell-style="{ background: 'rgba(239, 249, 243, 1)', color: '#000' }" class="table-content">
                <el-table-column type="selection" width="55"> </el-table-column>
                <el-table-column type="index" label="序号"> </el-table-column>
                <el-table-column prop="name" label="大棚名称">
                    <template slot-scope="scope">
                        <div class="dp-name"> {{ scope.row.name }}</div>
                    </template>
                </el-table-column>
                <el-table-column prop="name" label="合约地址"> </el-table-column>
                <el-table-column prop="name" label="最大区分数量/头"> </el-table-column>
                <el-table-column prop="name" label="大棚位置"> </el-table-column>
                <el-table-column prop="name" label="备注"> </el-table-column>

                <el-table-column prop="address" label="操作">
                    <template slot-scope="scope">
                        <div class="do-text">
                            <el-link class="txt-btn" type="success" @click="edit(scope.row.id)">修改</el-link>
                            <el-link class="txt-btn" type="success" @click="detail(scope.row.id)">详情</el-link>
                            <el-link class="txt-btn" type="danger" @click="deleteData(scope.row.id)">删除</el-link>
                        </div>
                    </template>
                </el-table-column>
            </el-table>
            <div class="page-block">
                <el-pagination @size-change="handleSizeChange" @current-change="handleCurrentChange"
                    :current-page="currentPage" :page-size="5" layout="total, prev, pager, next, jumper"
                    :total="totalPage">
                </el-pagination>
            </div>
        </div>
        <!-- 新增/修改弹框 -->
        <el-dialog title="新增/修改" :visible.sync="addEditDialog" width="40%">
            <el-form :model="doForm" label-width="100px">
                <el-form-item label="活动名称">
                    <el-input v-model="doForm.name" required placeholder="请输入名称"></el-input>
                </el-form-item>
                <el-form-item label="种植数量">
                    <el-input v-model="doForm.num" required placeholder="请输入数量"></el-input>
                </el-form-item>
                <el-form-item label="备注信息">
                    <el-input type="textarea" v-model="doForm.desc" :rows="3" placeholder="请输入备注信息"></el-input>
                </el-form-item>
            </el-form>
            <div slot="footer" class="dialog-footer">
                <el-button size="small" @click="addEditDialog = false">关闭</el-button>
                <el-button type="success" size="small" @click="addEditDialog = false">{{btnTxt}}</el-button>
            </div>
        </el-dialog>
    </div>
</template>

<script>
    export default {
        data() {
            return {
                btnTxt: '保存',
                doForm: {
                    name: '',
                    num: '',
                    desc: ''
                },
                addEditDialog: false,
                mcName: '',
                currentPage: 1,
                totalPage: 20,
                multipleSelection: [],
                tableData: [{
                    date: '2016-05-03',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-02',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-04',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-01',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-08',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-06',
                    name: '王小虎',
                    address: '上海市普陀区金沙江路 1518 弄'
                }, {
                    date: '2016-05-07',
                    name: '王小虎111',
                    address: '上海市普陀区金沙江路 1518 弄'
                }],
            }
        },
        methods: {
            search() {},
            resetName() {},
            handleSelectionChange(val) {
                this.multipleSelection = val;
                console.log(val);
            },
            handleSizeChange(val) {
                console.log(`每页 ${val} 条`);
            },
            handleCurrentChange(val) {
                console.log(`当前页: ${val}`);
            },
            // 新增按钮显示弹框
            addPlant() {
                this.addEditDialog = true
            },
            deleteData(id) {
                this.$confirm('确定要删除该条数据吗?', '删除', {
                    confirmButtonText: '确定',
                    cancelButtonText: '取消',
                    type: 'warning'
                }).then(() => {
                    this.$message({
                        type: 'success',
                        message: '删除成功!'
                    });
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
        border-bottom: 1px solid #d8d8d8;

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
        // height: calc(100vh - 300px);
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
