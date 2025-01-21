import mqtt from 'mqtt';
import {
    Notification
} from 'element-ui'; // 引入 Element UI Notification

let mqttTool = {
    client: null,
    messageHandler: null, // 全局消息处理回调
};

/** 连接Mqtt */
mqttTool.connect = function() {
    let options = {
        username: "znwl@vue1",
        password: "znwl@vue1",
        cleanSession: true,
        keepAlive: 30,
        clientId: 'web-' + Math.random().toString(16).substr(2),
        connectTimeout: 10000
    }
    // 配置Mqtt地址
    let url = process.env.VUE_APP_EMQX_SERVER_URL || "ws://" + window.location.hostname + ":8083/mqtt";

    mqttTool.client = mqtt.connect(url, options);

    // 发生错误
    mqttTool.client.on('error', (error) => {
        mqttTool.client.end();
    });

    // 连接成功
    mqttTool.client.on('connect', () => {
        console.log('MQTT连接成功');
    });

    // 接收消息
    mqttTool.client.on('message', (topic, message) => {
        if (mqttTool.messageHandler) {
            mqttTool.messageHandler(topic, message.toString()); // 调用全局消息处理
        } else {
            console.warn('未定义消息处理函数');
        }
    });
}

/** 消息订阅 */
mqttTool.subscribe = function(topics) {
    return new Promise((resolve, reject) => {
        if (!mqttTool.client) {
            resolve('未连接');
            return;
        }
        mqttTool.client.subscribe(topics, {
            qos: 1
        }, function(err) {
            if (!err) {
                resolve('订阅成功');
            } else {
                reject('订阅失败');
            }
        });
    });
};

/** 设置全局消息处理回调 */
mqttTool.setMessageHandler = function(callback) {
    mqttTool.messageHandler = callback;
};

export default mqttTool;
