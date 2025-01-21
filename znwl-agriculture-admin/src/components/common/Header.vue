<template>
  <div>
    <header class="nav-top">
        <div class="left-info">
            <img src="../../assets/logo.png" alt="">
            <span>智农物链溯源平台</span>
            
        </div>
        <div class="userinfo">
            <img src="../../assets/avatar.png" alt="" class="avatar">
            <el-dropdown trigger="click"  @command="loginOut">
                <span class="el-dropdown-link">
                    {{username}}<i class="el-icon-caret-bottom el-icon--right"></i>
                </span>
                <el-dropdown-menu slot="dropdown">
                    <el-dropdown-item>资料与账号</el-dropdown-item>
                    <el-dropdown-item command="out">退出登录</el-dropdown-item>
                </el-dropdown-menu>
            </el-dropdown>
        </div>
    </header>
  </div>
</template>

<script>
export default {
    data(){
        return{
            username:localStorage.getItem("username")
        }
    },
    methods:{
        loginOut(command){
            console.log(command)
            if(command=="out"){
                this.$router.replace("/");
                this.$store.commit("setToken","")
                localStorage.setItem("token","")
            }
        }
    }

}
</script>

<style lang="scss" scoped>
.nav-top{
    padding: 10px 20px;
    height: 50px;
    background-color:#1B273A;
    display: flex;
    justify-content: space-between;
    align-items: center;
    .left-info{
        display: flex;
        align-items: center;
        color: #fff;
        font-size: 20px;
        font-weight: bold;
        font-family: PingFang SC, PingFang SC; 
        font-style: italic; 
        img{
            width: 25px;
            height: 25px;
            border-radius: 50%;
            border: 1px solid rgb(230, 230, 230);
            margin-right: 5px;
        }
    }
    .userinfo{
        display: flex;
        color: #fff;
        align-items: center;
        .avatar{
            width: 42px;
            height: 42px;
            border-radius: 50%;
            margin-right: 10px;

        }
        .el-dropdown-link{
            color: #fff;
        }
    }
}
</style>