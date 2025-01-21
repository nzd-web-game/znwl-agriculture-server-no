<template>
  <div>
    <header-top></header-top>
    <div  class="origin-content">
        <el-form :inline="true" class="origin-form">
            <el-form-item>
                <el-input v-model="originName" placeholder="请输入合约地址/溯源码进行查询" class="origin-input"></el-input>
            </el-form-item>
            <el-button type="success" @click="originSearch">&nbsp; 查询 &nbsp;</el-button>
        </el-form>
    </div>
  </div>
</template>

<script>
import HeaderTop from "@/components/common/Header.vue"
export default {
    data(){
        return{
            originName:''
        }
    },
    components:{
        HeaderTop
    },
    methods:{
        originSearch(){
            this.$router.push({path:"/originresult",query:{info:this.originName}})
            localStorage.setItem("syInfo",this.originName)
        }
    }
}
</script>

<style lang="scss" scoped>
.origin-content{
    width: 100%;
    height: calc(100vh - 70px);
    overflow: hidden;
    background: url("C:\Users\tanmin\Desktop\区块链技术应用\若依农业\frog-smart-agriculture-master\frog-agriculture-admin\src\assets\sy.png") no-repeat;
    display: flex;
    justify-content: center;
}
.origin-form{
    margin-top: 150px;
    .origin-input{
        width: 450px;
        background-color: #fff;
    }
}

</style>