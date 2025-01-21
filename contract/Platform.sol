// SPDX-License-Identifier: MIT
pragma solidity ^0.6.10;
pragma experimental ABIEncoderV2;
import "./Collector.sol";
import "./Greenhouse.sol";
import "./Partitions.sol";

contract Platform {
    address owner;
    // 大棚合约
    mapping(address => bool) greenhouses;
    // 分区合约
    mapping(address => bool) partitionss;
    
    //设备合约
    Collector collectorManage;
    

    

    // 权限判断
    modifier isOwner() {
        require(msg.sender == owner, "no permission of Platform");
        _;
    }

    constructor() public {
        owner = msg.sender;
        collectorManage = new Collector();
    }

    /**
     * @dev 创建大棚
     * @param _greenhouseName 大棚名称
     * @param _greenhouseArea 大棚面积
     * @param _maxPartitionQuantity 最大分区数量
     * @param _greenhousePosition 大棚位置
     * @param _notes 备注
     */
    function addGreenhouseInfo(
        string memory _greenhouseName,
        string memory _greenhouseArea,
        uint  _maxPartitionQuantity,
        string memory _greenhousePosition,
        string memory _notes
    ) public isOwner returns (address) {
        // 创建牧场
        Greenhouse greenhouse = new Greenhouse(
            _greenhouseName,
            _greenhouseArea,
            _maxPartitionQuantity,
            _greenhousePosition,
            _notes
        );
        greenhouses[address(greenhouse)] = true;
        return (address(greenhouse));
    }

    /**
     * @dev 添加分区
     * @param _id 分区id
     * @param _partitionsName 分区名称
     * @param _plantingVarieties 分区种植品种
     * @param _plantingName 种植名称
     * @param _plantingDate 所属大棚
     * @param _notes 备注
     */
    function addPartitions(
        uint256 _id,
        string memory _partitionsName,
        string memory _plantingVarieties,
        string memory _plantingName,
        string memory _plantingDate,
        address _ofGreenhouse,
        string memory _notes
    ) public isOwner returns (address) {
        require(greenhouses[_ofGreenhouse], "non-existent");
        // 创建分区
        Partitions partitions = new Partitions(
            _id,
            _partitionsName,
            _plantingVarieties,
            _plantingName,
            _plantingDate,
            _ofGreenhouse,
            _notes
        );
        partitionss[address(partitions)] = true;
        // 将分区添加到大棚
        Greenhouse(_ofGreenhouse).addPartitions(address(partitions));
        return (address(partitions));
    }



    /**
     * @dev 分区采摘
     * @param _partitionsss 分区列表
     */
    function offHarvest(address[] memory _partitionsss) public isOwner {
        for (uint256 i = 0; i < _partitionsss.length; i++) {
            require(partitionss[_partitionsss[i]], "partitions do not exist");
            address greenhouse = Partitions(_partitionsss[i]).getofGreenhouse();
            require(greenhouses[greenhouse], "greenhouse do not exist");
            Greenhouse(greenhouse).removePartitions(_partitionsss[i]);
            Partitions(_partitionsss[i]).modifyoffHarvest(true);
        }
    }

    /**
     * @dev 添加采集器设备
     * @param _collectorId 采集器设备id
     */
    function addCollector(string memory _collectorId) public isOwner {
        collectorManage.addCollector(_collectorId);
    }

    /**
     * @dev 移除采集器设备
     * @param _collectorId 采集器设备id
     */
    function removeCollector(string memory _collectorId) public isOwner {
        collectorManage.removeCollector(_collectorId);
    }

    /**
     * @dev 认证采集器设备
     * @param _collectorId 采集器设备id
     * @return  结果
     */
    function check(string memory _collectorId) public view  returns (bool) {
        return collectorManage.check(_collectorId);
    }
    


}
