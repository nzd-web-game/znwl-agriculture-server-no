// SPDX-License-Identifier: MIT
pragma solidity ^0.6.10;
pragma experimental ABIEncoderV2;
contract SmartWarning {
    // 事件：当预警触发时发布事件
    event WarningTriggered(string warningType, uint256 timestamp, string greenhouseName);

    // 数据结构：存储传感器数据记录
    struct SensorData {
        string greenhouseName;
        uint256 timestamp;
        int256 temp;
        uint256 hum;
        uint256 p;
        uint256 lux;
        uint256 soil;
        uint256 air;
        uint256 relay;
        uint256 fire;
    }

    // 状态变量：存储传感器数据
    mapping(uint256 => SensorData) public sensorDataRecords;
    uint256 public recordCounter;

    // 固定的阈值
    int256 public constant TEMP_THRESHOLD = 30;    // 温度阈值
    uint256 public constant HUM_THRESHOLD = 35;    // 湿度阈值
    uint256 public constant FIRE_THRESHOLD = 30;   // 火灾阈值

    // 添加传感器数据，并检查是否需要触发预警
    function addAndCheckSensorData(
        string memory _greenhouseName,
        int256 _temp,
        uint256 _hum,
        uint256 _p,
        uint256 _lux,
        uint256 _soil,
        uint256 _air,
        uint256 _relay,
        uint256 _fire
    ) public {
        sensorDataRecords[recordCounter] = SensorData(
            _greenhouseName, block.timestamp, _temp, _hum, _p, _lux, _soil, _air, _relay, _fire
        );
        recordCounter++;
         emit WarningTriggered("ThresholdExceeded", block.timestamp, _greenhouseName);
        // 检查是否触发预警
        // if (_temp > TEMP_THRESHOLD || _hum > HUM_THRESHOLD || _fire > FIRE_THRESHOLD) {
           
        // }
    }

    // 添加传感器数据（不检查阈值）
    function addSensorData(
        string memory _greenhouseName,
        int256 _temp,
        uint256 _hum,
        uint256 _p,
        uint256 _lux,
        uint256 _soil,
        uint256 _air,
        uint256 _relay,
        uint256 _fire
    ) public {
        sensorDataRecords[recordCounter] = SensorData(
            _greenhouseName, block.timestamp, _temp, _hum, _p, _lux, _soil, _air, _relay, _fire
        );
        recordCounter++;
    }

    // 获取指定温室的最后一条数据
    function getSensorDataByGreenhouse(uint256 _recordId)
        public
        view
        returns (
            string memory greenhouseName,
            uint256 timestamp,
            int256 temp,
            uint256 hum,
            uint256 p,
            uint256 lux,
            uint256 soil,
            uint256 air,
            uint256 relay,
            uint256 fire
        )
    {
        SensorData storage data = sensorDataRecords[_recordId];
        return (
            data.greenhouseName,
            data.timestamp,
            data.temp,
            data.hum,
            data.p,
            data.lux,
            data.soil,
            data.air,
            data.relay,
            data.fire
        );
    }
}
