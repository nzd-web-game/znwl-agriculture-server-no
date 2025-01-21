<template>
  <div class="w100 h100">
      <!-- 选择控件 -->
      <el-select class="select" size="mini" v-model="id" @change="handleChange"  placeholder="请选择通道" clearable filterable>
          <el-option v-for="item in channelList"
              :key="item.id"
              :label="item.channelName"
              :value="item.id">
          </el-option>
      </el-select>
      <!-- 播放器 -->
      <single-player :video-param="videoParam" :width="'100%'" class="h100 "></single-player>
  </div>
</template>

<script>
import SinglePlayer from "@/views/components/player/SinglePlayer";
// import { listCamera,getDevice,getDeviceRunningStatus,listDeviceShort } from "@/api/iot/device";
import { listChannel } from "@/api/iot/channel";

export default {
  name: 'Camera',
  mixins: [],
  components: {SinglePlayer},
  props: {},
  data() {
    return{
        //通道列表
        channelList:[],
        //被选中的channel的id
        id:null,
        //播放器播放参数
        videoParam:{
            //摄像头序列号
            deviceId:null,
            //频道序列号
            channelId:null
        }
    }
  },
  computed: {},
  watch: {},
  async created() {
      //查询离线或在线通道
      await this.getChannelList();
      //默认给一个能播放的通道
      this.getAOnlineChannel();
  },
  mounted() {},
  destroyed() {},
  methods: {
      //查找所有的在线和离线的通道
      async getChannelList(){
          const {rows} = await listChannel();
          this.channelList = rows;
      },
      //默认给一个在线的通道
      getAOnlineChannel(){
          let channel = this.channelList.filter(item=>item.status == 3);
          if(channel.length>0 && channel[0].deviceSipId && channel[0].channelSipId){
              this.videoParam.deviceId = channel[0].deviceSipId;
              this.videoParam.channelId = channel[0].channelSipId;
          }
      },
      //处理摄像头选择
      handleChange(){
          //根据被选中的channel的id，从channel列表中获取deviceSipId和channelSipId
          let channel = this.channelList.filter(item=>item.id == this.id);

          if(channel.length=1){
              this.videoParam.deviceId = channel[0].deviceSipId;
              this.videoParam.channelId = channel[0].channelSipId;
          }
      }
  },
};
</script>
<style lang="scss" scoped>
.select{
    position: absolute;
    top:50px;
    right: 15px;
    z-index:10;
    width:110px;
    ::v-deep .el-input__inner {
        background:#214247;
        border:none;
    }
}

</style>
