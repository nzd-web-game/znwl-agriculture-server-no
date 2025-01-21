// SPDX-License-Identifier: MIT
pragma solidity ^0.6.10;

contract Greenhouse {
    // 管理地址
    address owner;

    // 大棚名称
    string greenhouseName;

    // 大棚面积
    string greenhouseArea;

    // 最大分区数量
    uint maxPartitionQuantity;
    
    // 实际分区数量
    uint  actualNumberOfPartitions;
    
    // 大棚位置
    string greenhousePosition;

    // 备注
    string notes;

    // 该大棚存在的分区
    mapping(address => bool) partitionss;



    // 权限判断
    modifier isOwner() {
        require(tx.origin == owner, "no permission of Greenhouse");
        _;
    }

    /**
     * @dev 构造函数，合约初始化
     * @param _greenhouseName 初始化大棚名称
     * @param _greenhouseArea 初始化大棚面积
     * @param _maxPartitionQuantity 初始化最大分区数量
     * @param _greenhousePosition 初始化大棚位置
     * @param _notes 初始化备注
     */
    constructor(
        string memory _greenhouseName,
        string memory _greenhouseArea,
        uint  _maxPartitionQuantity,
        string memory _greenhousePosition,
        string memory _notes
    ) public {
        owner = tx.origin;
        greenhouseName = _greenhouseName;
        greenhouseArea = _greenhouseArea;
        maxPartitionQuantity = _maxPartitionQuantity;
        greenhousePosition = _greenhousePosition;
        notes = _notes;
    }

    /**
     * @dev 修改大棚信息
     * @param _greenhouseName 大棚名称
     * @param _greenhouseArea 大棚面积
     * @param _maxPartitionQuantity 最大分区数量
     * @param _greenhousePosition 大棚位置
     * @param _notes 备注
     */
    function modifyGreenhouseInfo(
        string memory _greenhouseName,
        string memory _greenhouseArea,
        uint  _maxPartitionQuantity,
        string memory _greenhousePosition,
        string memory _notes
    ) public isOwner {
        // 不为空才修改
        if (keccak256(bytes(_greenhouseName)) != keccak256(bytes(""))) {
            greenhouseName = _greenhouseName;
        }
        if (keccak256(bytes(_greenhouseArea)) != keccak256(bytes(""))) {
            greenhouseArea = _greenhouseArea;
        }
        
        if (_maxPartitionQuantity > 0) {
            maxPartitionQuantity = _maxPartitionQuantity;
        }
 
        if (keccak256(bytes(_greenhousePosition)) != keccak256(bytes(""))) {
            greenhousePosition = _greenhousePosition;
        }
        if (keccak256(bytes(_notes)) != keccak256(bytes(""))) {
            notes = _notes;
        }
    }

    /**
     * @dev 添加分区到该大棚
     * @param _partitions 大棚的区块链地址列表
     */
    function addPartitions(address _partitions) public isOwner {
        actualNumberOfPartitions+=1;
        require(actualNumberOfPartitions<=maxPartitionQuantity,"Insufficient greenhouse space");
        partitionss[_partitions] = true;
    }

    /**
     * @dev 从大棚移除分区
     * @param _partitions 分区的区块链地址列表
     */
    function removePartitions(address _partitions) public isOwner {
        require(actualNumberOfPartitions>0,"Please do not delete multiple times");
        if(actualNumberOfPartitions!=0){
            actualNumberOfPartitions-=1;
        }
        
        partitionss[_partitions] = false;
    }

    /**
     * @dev 获取大棚信息
     * @return _greenhouseName 大棚名称
     * @return _greenhouseArea 大棚面积
     * @return _maxPartitionQuantity 最大分区数量
     * @return _greenhousePosition 大棚位置
     * @return _notes 备注
     */
   
    function getGreenhouse()
        public
        view
        returns (
            string memory _greenhouseName,
            string memory _greenhouseArea,
            uint  _maxPartitionQuantity,
            string memory _greenhousePosition,
            string memory _notes
        )
    {
        return (
            greenhouseName,
            greenhouseArea,
            maxPartitionQuantity,
            greenhousePosition,
            notes
        );
    }


}
