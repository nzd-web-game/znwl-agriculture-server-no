<template>
  <div>
    <header-top></header-top>
    <div class="container">
        <div class="left-menu">
            <el-menu default-active="$path.router" router class="el-menu-vertical-demo" background-color="#1B273A" text-color="#919DA9" active-text-color="#fff" :unique-opened="false">
                <el-menu-item index="/home" @click="choose(1)" :style="state==1? chooseCss:''">
                    <i class="el-icon-menu"></i>
                    <span slot="title">首页</span>
                </el-menu-item>
                
                <el-menu-item index="/equip" @click="choose(3)" :style="state==3? chooseCss:''">
                    <i class="el-icon-setting"></i>
                    <span slot="title">设备管理</span>
                </el-menu-item>
                <el-submenu index="3">
                    <template slot="title">
                        <i class="el-icon-location"></i>
                        <span>作物管理</span>
                    </template>
                    <el-menu-item-group>
                        <el-menu-item index="/greenhouse" @click="choose(41)" :style="state==41? chooseCss:''"> &nbsp;&nbsp;&nbsp; 大棚管理</el-menu-item>
                        <el-menu-item index="/plant" @click="choose(42)" :style="state==42? chooseCss:''"> &nbsp;&nbsp;&nbsp; 种植管理</el-menu-item>
                        <el-menu-item index="/manure" @click="choose(43)" :style="state==43? chooseCss:''"> &nbsp;&nbsp;&nbsp; 施肥管理</el-menu-item>
                    </el-menu-item-group>
                </el-submenu>
                <el-menu-item index="/process" @click="choose(2)" :style="state==2? chooseCss:''">
                    <i class="el-icon-setting"></i>
                    <span slot="title">加工管理</span>
                </el-menu-item>
                <el-menu-item   :style="state==90? chooseCss:''">
                    <i class="el-icon-orange"></i>
                    <a href="http://127.0.0.1:8000" class="custom-link" target="_blank">数据大屏</a >
                </el-menu-item>
            </el-menu>
        </div>
        <div class="right-content">
            <router-view></router-view>
        </div>
    </div>
  </div>
</template>

<script>
import HeaderTop from "./Header.vue"
import LeftMenu from "./Header.vue"
export default {
    data(){
        return{
            state:1,
            chooseCss:'background-image: linear-gradient(to right,#6EB92D,#6EB92D);color:#fff;'
        }
    },
    components:{
        HeaderTop,
        LeftMenu
    },
    methods:{
        choose(n){
            this.state = n
            // console.log(n)
        }
    }
}
</script>

<style lang="scss" scoped>

.container{
    width: 100%;
    display: flex;
    .left-menu{
        width: 240px;
        height: 100px;
        height: calc(100vh - 70px);
        background-color: #1B273A;
        .el-menu-vertical-demo>li{
            &:hover{
                color: #fff !important;
                background-color: #6EB92D !important;
            }

        }
        .el-menu-vertical-demo>li:hover .el-menu-vertical-demo>li>i{
            color: #fff !important;
        }
        .el-menu-item-group>ul>li{
            &:hover{
                color: #fff !important;
                background-color: #6EB92D !important;
            }
        }
    }
    .right-content{
        padding: 10px;
        flex: 1;
        height: calc(100vh - 90px);
        overflow: auto;
        background-color: #F0F2F5;
    }
    a.custom-link {

color: inherit;
text-decoration: none;
background: none;
}
}

</style>