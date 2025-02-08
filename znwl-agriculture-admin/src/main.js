import Vue from 'vue'

import Cookies from 'js-cookie'

import Element from 'element-ui'
// import './assets/styles/element-variables.scss'
import _ from 'lodash'

import '@/assets/styles/index.scss' // global css
import '@/assets/styles/ruoyi.scss' // ruoyi css
import '@/assets/styles/layout.scss'
import App from './App'
import store from './store'
import router from './router'
import directive from './directive' // directive
import plugins from './plugins' // plugins
import {
    download
} from '@/utils/request'

import './assets/icons' // icon
import './permission' // permission control
import {
    getDicts
} from "@/api/system/dict/data";
import {
    getConfigKey
} from "@/api/system/config";
import {
    parseTime,
    resetForm,
    addDateRange,
    selectDictLabel,
    selectDictLabels,
    handleTree
} from "@/utils/ruoyi";
// 分页组件
import Pagination from "@/components/Pagination";
// 自定义表格工具组件
import RightToolbar from "@/components/RightToolbar"
// 富文本组件
import Editor from "@/components/Editor"
// 文件上传组件
import FileUpload from "@/components/FileUpload"
// 视频上传组件
import VideoUpload from "@/components/VideoUpload"
// 图片上传组件
import ImageUpload from "@/components/ImageUpload"
// 字典标签组件
import DictTag from '@/components/DictTag'
// 数据标签组件
import DataTag from '@/components/DataTag'
// 头部标签组件
import VueMeta from 'vue-meta'
// 字典数据组件
import DictData from '@/components/DictData'
import AUDia from '@/components/Bot/Dia.vue'
Vue.component('AUDia', AUDia)
//拖拽组件
import VueDraggableResizable from 'vue-draggable-resizable'
import 'vue-draggable-resizable/dist/VueDraggableResizable.css'
import axios from 'axios'
Vue.prototype.$http = axios

// Echart
import * as echarts from 'echarts'
// 一键复制粘贴板组件
import VueClipboard from 'vue-clipboard2'
// Mqtt工具
import mqttTool from '@/utils/mqttTool'
import ItemWrap from './views/bigScreen/components/item-wrap/item-wrap.vue'
import Message from './views/bigScreen/components/message/message.vue'
import Reacquire from './views/bigScreen/components/reacquire/reacquire.vue'
import {
    loading,
    borderBox13,
    digitalFlop,
    waterLevelPond,
    scrollBoard,
    capsuleChart,
    borderBox8,
    decoration12,
    decoration3,
    decoration9,
    activeRingChart
} from '@jiaminghi/data-view'

// datav组件 ,会导致生成文件无法打开
Vue.use(loading)
Vue.use(borderBox13)
Vue.use(borderBox8)
Vue.use(digitalFlop)
Vue.use(capsuleChart)
Vue.use(waterLevelPond)
Vue.use(scrollBoard)
Vue.use(decoration12)
Vue.use(activeRingChart)
Vue.use(decoration3)
Vue.use(decoration9)

// 自定义组件
Vue.component("ItemWrap", ItemWrap)
Vue.component("Message", Message)
Vue.component("Reacquire", Reacquire)


// 全局方法挂载
Vue.prototype.getDicts = getDicts
Vue.prototype.getConfigKey = getConfigKey
Vue.prototype.parseTime = parseTime
Vue.prototype.resetForm = resetForm
Vue.prototype.addDateRange = addDateRange
Vue.prototype.selectDictLabel = selectDictLabel
Vue.prototype.selectDictLabels = selectDictLabels
Vue.prototype.download = download
Vue.prototype.handleTree = handleTree
Vue.prototype.$echarts = echarts
Vue.prototype.$mqttTool = mqttTool
Vue.prototype.$baseUrl = process.env.VUE_APP_BASE_API
Vue.prototype.$colorList = ['#D68410', '#80BF2F', '#EBAC53', '#47B770', '#486FE1', '#5F3DBC', '#CFD94A', '#E9754F',
    '#2DD4D2', '#D68410', '#D68410', '#80BF2F', '#EBAC53', '#47B770', '#486FE1', '#5F3DBC', '#CFD94A', '#E9754F',
    '#2DD4D2', '#D68410'
]
Vue.prototype._ = _;

//判断手机端还是电脑端
const userAgent = navigator.userAgent.toLowerCase();
const isMobile = /iphone|ipad|ipod|android|blackberry|mini|windows\sce|palm/i.test(userAgent);
Vue.prototype.$isMobile = isMobile;

// 全局组件挂载
Vue.component('DictTag', DictTag)
Vue.component('DataTag', DataTag);
Vue.component('Pagination', Pagination)
Vue.component('RightToolbar', RightToolbar)
Vue.component('Editor', Editor)
Vue.component('FileUpload', FileUpload)
Vue.component('ImageUpload', ImageUpload)
Vue.component('VideoUpload', VideoUpload)
Vue.component('vue-draggable-resizable', VueDraggableResizable)
Vue.use(VueClipboard)
Vue.use(directive)
Vue.use(plugins)
Vue.use(VueMeta)
DictData.install()

/**
 * If you don't want to use mock-server
 * you want to use MockJs for mock api
 * you can execute: mockXHR()
 *
 * Currently MockJs will be used in the production environment,
 * please remove it before going online! ! !
 */

Vue.use(Element, {
    size: Cookies.get('size') || 'medium' // set element-ui default size
})

// 任务5：
// 功能描述：
// 初始化 MQTT 连接。
// 订阅指定的主题，并处理订阅结果。
// 设置消息处理回调函数，当接收到消息时，触发通知提醒功能。
// 使用 Element UI 的 Notification 组件来推送预警通知。


// 初始化 MQTT 连接
mqttTool.connect();

// 订阅主题 'qianduan'
mqttTool.subscribe('warning').then(() => {
        console.log('主题订阅成功');
    })
    .catch((err) => {
        console.error('订阅失败:', err);
    });

// 设置消息处理回调，接收到消息后推送通知
mqttTool.setMessageHandler((topic, message) => {
    console.log(`收到消息 - 主题: ${topic}, 内容: ${message}`);
    // 使用 Element UI Notification 推送通知
    Vue.prototype.$notify({
        title: '预警通知',
        message: message,
        type: 'warning',
        duration: 5000,
        showClose: true,
    });
});


Vue.config.productionTip = false


//高德地图
import AMapLoader from "@amap/amap-jsapi-loader";
// 检查是否离线状态
const isOnline = navigator.onLine;

if (isOnline) {
    // 高德地图配置
    window._AMapSecurityConfig = {
        securityJsCode: process.env.VUE_APP_GAO_DE_SECURITYJSCODE,
    };

    // 加载高德地图
    AMapLoader.load({
        key: process.env.VUE_APP_GAO_DE_KEY,
        version: "2.0",
        plugins: [
            "AMap.Scale",
            "AMap.ToolBar",
            "AMap.MapType",
            "AMap.PlaceSearch",
            "AMap.PolygonEditor",
            "AMap.Geocoder",
            "AMap.Geolocation",
            "AMap.RangingTool",
            "AMap.CitySearch"
        ],
        AMapUI: {
            version: "1.1",
            plugins: ["overlay/SimpleMarker"],
        },
        Loca: {
            version: "2.0.0",
        },
    }).then(map => {
        Vue.prototype.AMap = map;
    }).catch(error => {
        console.error("高德地图加载失败:", error);
    });
} else {
    console.warn("当前处于离线模式，高德地图未加载。");
    Vue.prototype.AMap = null;
}

new Vue({
    el: '#app',
    router,
    store,
    render: h => h(App)
})
// 表格带边框
Element.Table.props.border = {
    default: true,
    type: Boolean
}
