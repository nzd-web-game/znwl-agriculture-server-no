// SPDX-License-Identifier: MIT
pragma solidity ^0.6.10;

//设备管理
contract Collector {
    address owner;

    mapping(string => bool) collectorMapping;

    // 权限判断
    modifier isOwner() {
        require(tx.origin == owner, "no permission of Platform");
        _;
    }

    constructor() public {
        owner = tx.origin;
    }

    /**
     * @dev 添加采集器
     * @param _collectorId 采集器id
     */
    function addCollector(string memory _collectorId) public isOwner {
        collectorMapping[_collectorId] = true;
    }

    /**
     * @dev 移除采集器
     * @param _collectorId 采集器id
     */
    function removeCollector(string memory _collectorId) public isOwner {
        collectorMapping[_collectorId] = false;
    }

    /**
     * @dev 认证采集器
     * @param _collectorId 采集器id
     * @return  结果
     */
    function check(string memory _collectorId) public view  returns (bool) {
        return collectorMapping[_collectorId];
    }
}
