<template>
  <div class="w100 h100">
    无
  </div>
</template>

<script>
import { listProduct } from "@/api/iot/product";
// import multiPlayer from '@/views/iot/device/device-multi-video';
import {getDevice,listDeviceShort } from "@/api/iot/device";
// import { chartOption } from '@/views/iot/device/components/CommonDeviceView/ChartOption';
import { getDeviceRunningStatusSingle} from "@/api/iot/device"


export default {
  name: 'Tree',
  dicts:["iot_device_status"],
  mixins: [],
//   components: {multiPlayer},
  props: {},
  data() {
    return{
        //产品列表
        productList:[],
        //弹窗1
        cameraDialogVisiable:false,
        //弹窗2
        deviceDialogVisiable:false,
        //非摄像头设备列表
        deviceList:[],
        //设备弹窗中选中的设备ID,只做监听使用
        curentDeviceId:null,
        //被选中的设备信息
        curentDevice:{
            //初始化值的目的是解决视图渲染的时候报错
            deviceName:'',
            monitorList:[]
        },
        //被选中的属性
        currentProperty:null,
        //设备监测属性图表查询参数
        // propertyChartQueryParams:{
        //     deviceId:null,
        //     identity:null,
        //     beginTime:null,
        //     endTime:null
        // }
        //图表数据
        propertyChartData:{times:[],values:[]}
    }
  },
  computed: {
        //在线判断
        isOnline(){
             const {status} = this.curentDevice;
             if(status == 3){
                 return {name:'在线',color:"#2b7"}
             }
             if(status == 4){
                 return {name:'离线',color:"#ffffff"}
             }else{
                  return {name:'',color:"#ffffff"}
             }
        },
        //影子判断
        isShadow(){
             const {isShadow} = this.curentDevice;
            if(isShadow==1){
                 return {name:'影子模式',color:"#2b7"}
            }else{
                 return {name:'非影子模式',color:"#ffffff"}
            }
        }
  },
  watch: {
    curentDeviceId:{
        handler: function(n){
            this.getDevice(n);
        },
        deep:true
    },
    propertyChartData:{
        handler:async function(){
            this.loadMap();
        },
        deep:true
    },
    deviceDialogVisiable(n,o){
        if(n==false){
            this.stopAllMonitor();
        }
    }
  },
  created() {},
  async mounted() {
      await this.getProductList();
      await this.getDeviceList();
      this.$nextTick(()=>{
          this.changePosition();
      });
  },
  destroyed() {},
  methods: {
    //获取产品列表
    async getProductList(){
        const {rows} = await listProduct();
        this.productList = rows.map(item=>({
            ...item,
            imgUrl:this.$baseUrl+item.imgUrl.split(',')[1]
        }));
    },

    //打开弹窗
    async openDialog(product){
        if (product.productId==98) {
            this.cameraDialogVisiable=true
        }else{
            this.deviceDialogVisiable=true;
            //根据产品ID获取产品列表
            await this.getDeviceList(product.productId);
            //给第一个设备作为默认选项
            this.curentDeviceId = this.deviceList[0].deviceId
        }
    },
    //获取非摄像头设备列表
    async getDeviceList(productId){
        const {rows} = await listDeviceShort({isCamera:0,productId});
        this.deviceList = rows;
    },

    //获取设备详情
    async getDevice(deviceId) {
        const device = await getDevice(deviceId);
        this.curentDevice =device.data;
        const {data} = await getDeviceRunningStatusSingle(deviceId,0);
        this.$set(this.curentDevice,'monitorList',data)

        //给图表查询初始化查询参数
        this.currentProperty = this.curentDevice.monitorList[0];
        //连接mqtt并且订阅主题和回调函数
        // console.log(this.curentDevice.monitorList)
        this.connectMqtt();
        //初始化开始监测
        this.monitor(true,device.data);
    },
    //处理属性点击事件
    handleClickProperty(item){
        //设置一下当前选择的属性
        this.currentProperty = item;
        //图表数据重置
        this.propertyChartData.times=[];
        this.propertyChartData.values=[];

    },

    //重新初始化图表
    loadMap(){
        chartOption.xAxis.data = this.propertyChartData.times;
        chartOption.series[0].data = this.propertyChartData.values;
        let chart = this.$echarts.init(this.$refs.propertyChart);
        chart.setOption(chartOption);
    },
    //mqtt连接
    async connectMqtt() {
        if (this.$mqttTool.client == null) {
            await this.$mqttTool.connect();
        }
        this.mqttSubscribe();
        this.mqttCallback();
    },
    //mqtt回调
    mqttCallback() {
        this.$mqttTool.client.on('message', (topic, message, buffer) => {
            let _message = JSON.parse(message.toString());
            const { monitorList } = this.curentDevice;
            console.log(monitorList)
                //修改monitorList里面的值
                if (monitorList.length>0) {
                    for (let i = 0; i < monitorList.length; i++) {
                        for (let j = 0; j < _message.length; j++) {
                            if (monitorList[i].id == _message[j].id) {
                                monitorList[i].value = _message[j].value/monitorList[i].dataType.step;
                            }
                        }
                    }
                }
                //把值放入propertyChartData
                for (let j = 0; j < _message.length; j++) {
                    if (this.currentProperty.id == _message[j].id) {
                        this.propertyChartData.times.push(this.parseTime(new Date()));
                        this.propertyChartData.values.push(_message[j].value/this.currentProperty.dataType.step);
                    }
                }
        });
    },
    mqttPublish(model,deviceDetail) {
            let topic = "";
            let message = ""
            switch (model.type) {
                case 1:
                    if (deviceDetail.status == 3) {
                        // 属性,在线模式
                        topic = "/" + deviceDetail.productId + "/" + deviceDetail.serialNumber + "/property-online/post";
                    } else if (deviceDetail.isShadow) {
                        // 属性,离线模式
                        topic = "/" + deviceDetail.productId + "/" + deviceDetail.serialNumber + "/property-offline/post";
                    }
                    message = '[{"id":"' + model.id + '","value":"' + model.shadow + '"}]';
                    break;
                case 2:
                    if (deviceDetail.status == 3) {
                        // 功能,在线模式
                        topic = "/" + deviceDetail.productId + "/" + deviceDetail.serialNumber + "/function-online/post";
                    } else if (deviceDetail.isShadow) {
                        // 功能,离线模式
                        topic = "/" + deviceDetail.productId + "/" + deviceDetail.serialNumber + "/function-offline/post";
                    }
                    message = '{"id":"' + model.id + '","value":"' + model.shadow + '"}';
                    break;
                case 3:
                    topic = "/" + deviceDetail.productId + "/" + deviceDetail.serialNumber + "/ota/get";
                    message = '{"version":' + this.firmware.version + ',"downloadUrl":"' + this.getDownloadUrl(this.firmware.filePath) + '"}';
                    break;
                case 4:
                    topic = "/" + deviceDetail.productId + "/" + deviceDetail.serialNumber + "/monitor/get";
                    message = model.message;
                    break;
                default:
                    break;
            }
            if (topic != "") {
                // 发布
                this.$mqttTool.publish(topic, message, model.name).then(res => {
                    this.$modal.notifySuccess(res);
                }).catch(res => {
                    this.$modal.notifyError(res);
                });
            }
        },
    //实时监测
    monitor(isMonitor,device) {
        if (isMonitor) {
            this.mqttPublish({name:'开启实时监测',type:4,message:'{"interval":1000}'},device);//毫秒
        } else {
            this.mqttPublish({name:'关闭实时监测',type:4,message:'{"interval":0}'},device);
        }
    },
    //弹窗关闭，批量关闭所有的监测
    stopAllMonitor(){
        this.deviceList.forEach((item,index)=>{
            console.log(index)
            this.monitor(false,item) ;
        })
    },
    //mqtt订阅
    mqttSubscribe() {
        // 设备状态主题
        let topicStatus =
            '/+' + '/+' + '/status/post';
        //设备属性上报主题
        let topicProperty =
            '/+' + '/+' +  '/property/post';
        //设备功能上报主题
        let topicFunction =
            '/+' + '/+' + '/function/post';
        //设备监测数据上报主题
        let topicMonitor =
            '/+' + '/+' +  '/monitor/post';
        let topics = [];
        topics.push(topicStatus);
        topics.push(topicProperty);
        topics.push(topicFunction);
        topics.push(topicMonitor);
        this.$mqttTool.subscribe(topics);
    },

    //随机位置
    changePosition() {
        let allDiv = this.$refs.product;
        let that = this
        function getMaxDimension(divArr) {
            var maxWidth = 0;
            for (var i = 0; i < divArr.length; i++) {
                if (divArr[i].offsetWidth > maxWidth) {
                    maxWidth = divArr[i].offsetWidth;
                }
            }
            var maxHeight = 0;
            for (var i = 0; i < divArr.length; i++) {
                if (divArr[i].offsetHeight > maxHeight) {
                    maxHeight = divArr[i].offsetHeight;
                }
            }
            var values = { maxWidth: maxWidth, maxHeight: maxHeight };
            return values;
        }

        function getRDivNumber(min, max) {
            return Math.random() * (max - min) + min;
        }

        function isCollision(a, b) {
            var a_l = a.offsetLeft; // a_l为a盒子左侧偏移量
            var a_t = a.offsetTop; // a_t为a盒子顶部偏移量
            var a_r = a_l + a.offsetWidth; // a_r为a盒子右侧距页面左侧的距离
            var a_b = a_t + a.offsetHeight; // a_b为a盒子底部距页面最顶端的距离
            var b_l = b.offsetLeft; // b_l等为b盒子 同上解释
            var b_t = b.offsetTop; // b_t为b盒子顶部偏移量
            var b_r = b_l + b.offsetWidth; // b_r为b盒子右侧距页面左侧的距离
            var b_b = b_t + b.offsetHeight; // b_b为b盒子底部距页面最顶端的距离

            if (b_r < a_l || b_b < a_t || a_r < b_l || a_b < b_t) {
            // 当满足此条件时，没有发生碰撞，此时返回值为false没有检测到碰撞
            return false;
            } else {
            // 否则为true，即发生碰撞
            return true;
            }
        }

        function computed(divArr) {
            var maxDimensions = getMaxDimension(divArr);
            var widthBoundary = maxDimensions.maxWidth;
            var heightBoundary = maxDimensions.maxHeight;
            for (var i = 0; i < divArr.length; i++) {
                let rDivLeft = getRDivNumber(
                    widthBoundary,
                    that.$refs.tree.offsetWidth - widthBoundary - (that.$refs.tree.offsetWidth*0.2)
                );
                let rDivTop = getRDivNumber(
                    heightBoundary,
                    that.$refs.tree.offsetHeight - heightBoundary - (that.$refs.tree.offsetHeight*0.2)
                );
                divArr[i].style.left = rDivLeft + "px";
                divArr[i].style.top = rDivTop + "px";
            }
            examineEach();
        }

        function examineEach() {
            let maxFrequency = 0
            maxFrequency += 1;
            for (var i = 0; i < allDiv.length; i++) {
                for (var j = i + 1; j < allDiv.length; j++) {
                    if (isCollision(allDiv[i], allDiv[j]) && maxFrequency < 9000) {
                        computed([allDiv[i], allDiv[j]]);
                    }
                }
            }
        }

        computed(allDiv);
    }
  },
};
</script>
<style lang="scss" scoped>
    //设备树的样式
   .tree {
        width: 100%;
        height: 100%;
        background: url("../img/tree.png") no-repeat;
        background-size: 100% auto;
        background-position: center center;
        position: relative;
        .product{
            width: 100px;
            height: 100px;
            position: absolute;
            border-radius: 50%;
            cursor: pointer;
            text-align: center;
            color: #fff;
            font-size: 14px;
            font-weight: 900;
            img{
                height: 70%;
                width: auto;
            }
        }
    }
    //弹窗颜色变量，深和浅
    $colorL1:#0c2438;
    $colorL2:#092944;
    $colorL4:#132f41;
    $colorL3:#0d3758;
    //播放弹窗的样式
    .cameraDialog ::v-deep {
         //弹窗头部
         .el-dialog__header{
             background:$colorL1;
             color:#fff;
         }
         //弹窗body
         .el-dialog__body{
             background:$colorL1;
         }
         //播放组建box
        .box{
            height:100%;
            background:$colorL1;
        }
        .search-menu{
            background:$colorL2;
            color:#fff;
        }
        .el-submenu__title{
            background:$colorL2;
            color:#fff;
        }
        .el-menu{
            background:$colorL2;
        }
        .el-menu-item{
            background:$colorL2;
            color:#fff;
            padding:0;
            min-width: 0;
        }
         //右侧搜索
         .search-menu-body{
            height:553px;
         }
         //播放器
         .player{
             height:650px;
         }
         //容器
         .box{
             border:none;
         }
         //播放器的div容器
        .box-right{
            background:#092944;
            height: 673px !important;
            border:none;
        }
        //分页样式
        .el-pagination .btn-prev{
             background:#092944;
            color:#fff;
        }
        .el-pagination .btn-next{
             background:#092944;
            color:#fff;
        }
        .el-pagination.is-background .el-pager li:not(.disabled).active{
            background:$colorL3;
            color:#fff;
        }
        //搜索按钮
        .el-input__inner{
            background:$colorL3;
            border:none;
            color:#fff;
        }
    }
    //设备兼容弹窗样式
    .deviceDialog{
        .propertyItem.active{
            border:1px solid green;
        }

        //穿透的样式
        ::v-deep{
         //弹窗头部
         .el-dialog__header{
             background:$colorL1;
         }
         .el-dialog__title{
             color:#fff;
         }
         //弹窗body
         .el-dialog__body{
             background:$colorL1;
             color:#fff;
         }

         .deviceChoose .el-input input{
             background: $colorL4 !important;
             border:none;
             color:#fff;
         }
         //tabs
         .el-tabs{
             background:$colorL2;
             border:none;
         }
         .el-tabs__header{
             background-color: $colorL3;
             border:none;
         }
         .el-tabs--border-card > .el-tabs__header .el-tabs__item{
             color:#fff !important;
             background-color: $colorL3;
             border:none;
         }
         .el-tabs--border-card > .el-tabs__header .el-tabs__item.is-active{
             color:#fff !important;
             background-color: $colorL2;
             border:none;
         }
         //functionItem
         .functionItem .el-input input{
             background: #585858 !important;
             border:none;
             color:#fff;
         }
         .functionItem .el-input-group__append{
             background-color: #585858 !important;
            border:none;
            border-left:1px solid #4b4b4b;
             color:#fff;
         }
    }
    }
</style>
