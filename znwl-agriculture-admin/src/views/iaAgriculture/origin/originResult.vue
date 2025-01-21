<template>
  <div class="origin-content">
    <header-top></header-top>
    <div class="list-content">
      <div class="origin-top">
        <div class="list-title">追溯查询基础信息</div>
        <div style="margin-left: 10px; margin-top: 20px">
          <el-row :gutter="10">
            <el-col :span="8">
              <div class="base-item">
                <div class="base-item-title">大棚信息</div>
                <div class="base-item-list">
                  <p>大棚名称：{{ ivPastureInfo.name }}</p>
                  <p>合约地址：{{ ivPastureInfo.area }}</p>
                  <p>大棚位置：{{ ivPastureInfo.position }}</p>
                  <p></p>
                </div>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="base-item">
                <div class="base-item-title">分区信息</div>
                <div class="base-item-list">
                  <p>分区名称：{{ iaPartitionInfo.name }}</p>
                  <p>种植品种：{{ iaPartitionInfo.variety }}</p>
                  <p>种植日期：{{ iaPartitionInfo.dateT }}</p>
                  <p></p>
                </div>
              </div>
            </el-col>
            <el-col :span="8">
              <div class="base-item">
                <div class="base-item-title">商品信息</div>
                <div class="base-item-list">
                  <p>食品名称：{{ shopInfo.weight }}</p>
                  <p>加工日期：{{ shopInfo.datet }}</p>
                  <p>食品质量：{{ shopInfo.quality }}</p>
                  <p>食品重量：{{ shopInfo.weight }}kg</p>
                </div>
              </div>
            </el-col>
          </el-row>
        </div>
      </div>
      <div class="origin-info">
        <div class="list-title">追溯查询基础信息</div>
        <div style="margin-left: 10px; margin-top: 20px">
          <div class="origin-table">
            <div class="origin-table-top">
              <div :class="tabStatus == 1 ? 'tab-baseinfo tab-active' : 'tab-baseinfo'
                " @click="chooseTab(1)">
                施肥信息
              </div>
              <div :class="tabStatus == 2 ? 'tab-baseinfo tab-active' : 'tab-baseinfo'
                " @click="chooseTab(2)">
                环境信息
              </div>
            </div>
            <div class="origin-table-content">
              <div v-if="tabStatus == 1">
                <sf></sf>
              </div>
              <div v-if="tabStatus == 2">
                <environment></environment>
              </div>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
</template>

<script>
import HeaderTop from "@/components/common/Header.vue";
import sf from "@/components/origin/sf";
import environment from "@/components/origin/environment";
export default {
  data() {
    return {
      tabStatus: 1,
      paramsInfo: "",
      ivPastureInfo: {}, //大棚信息
      iaPartitionInfo: {}, //分区的信息
      shopInfo: {}, //商品信息
    };
  },
  components: {
    HeaderTop,
    sf,
    environment,
  },
  mounted() {
    this.paramsInfo = this.$route.query.info;
    if (this.paramsInfo) {
      this.baseInfo();
    }
  },
  methods: {
    chooseTab(n) {
      this.tabStatus = n;
    },
    // 获取溯源基础信息
    baseInfo() {
      this.$http
        .get("/dev-api/iaPartitionFoodSensorValue/getTraceability", {
          params: {
            id: this.paramsInfo,
          },
        })
        .then((res) => {
          console.log(res);
          if (res.data.code == 0) {
            // 大棚信息
            let dpData = res.data.data.ivPastureInfo;
            let fobj = {};
            dpData.forEach((el, index) => {
              if (el.name == "_greenhouseName") {
                fobj.name = el.stringValue.value;
              }
              if (el.name == "_greenhouseArea") {
                fobj.area = el.stringValue.value;
              }
              if (el.name == "_greenhousePosition") {
                fobj.position = el.stringValue.value;
              }
            });
            this.ivPastureInfo = fobj;
            // 分区信息
            let fqData = res.data.data.iaPartitionInfo;
            let fqbj = {};
            fqData.forEach((fq) => {
              if (fq.name == "_partitionsName") {
                fqbj.name = fq.stringValue.value;
              }
              if (fq.name == "_plantingVarieties") {
                fqbj.variety = fq.stringValue.value;
              }
              if (fq.name == "_plantingDate") {
                fqbj.dateT = fq.stringValue.value;
              }
            });
            this.iaPartitionInfo = fqbj;

            // 商品信息
            let shopData =
              res.data.data.iaPartitionFoodSensorValueInfo[0].listValues;
            let spObj = {};
            shopData.forEach((shop) => {
              if (shop.name == "foodName") {
                spObj.name = shop.stringValue.value;
              }
              if (shop.name == "processingTimestamp") {
                spObj.datet = new Date(
                  parseInt(shop.numericValue.value)
                ).toLocaleString();
              }
              if (shop.name == "quality") {
                console.log(shop.numericValue.value);
                spObj.quality =
                  shop.numericValue.value == "2"
                    ? "优秀"
                    : shop.numericValue.value == "1"
                      ? "及格"
                      : "不合格";
              }
              if (shop.name == "weight") {
                spObj.weight = shop.stringValue.value;
              }
            });
            this.shopInfo = spObj;
          } else {
            this.$message.error(res.data.msg);
          }
        });
    },
  },
};
</script>

<style lang="scss" scoped>
.origin-top,
.origin-info {
  margin: 10px;
}

.base-item {
  border: 1px solid #dddddd;
  font-size: 14px;

  .base-item-title {
    line-height: 35px;
    padding-left: 10px;
    border-bottom: 1px solid #dddddd;
    font-weight: bold;
  }

  .base-item-list {
    padding: 10px;
    color: #999;
    font-size: 12px;
    line-height: 25px;
    height: 100px;
  }
}

.origin-table {
  border: 1px solid #dddddd;

  .origin-table-top {
    border-bottom: 1px solid #dddddd;
    height: 40px;
    padding-left: 10px;
    display: flex;
    align-items: center;

    .tab-baseinfo {
      width: 80px;
      font-size: 14px;
      line-height: 30px;
      text-align: center;
      cursor: pointer;
    }

    .tab-active {
      background-color: #6eb92d;
      color: #fff;
    }
  }
}

.origin-table-content {
  padding: 10px 20px;
  // margin: 10px;
}
</style>