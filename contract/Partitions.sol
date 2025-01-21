// SPDX-License-Identifier: MIT
pragma solidity ^0.6.10;
pragma experimental ABIEncoderV2;

contract Partitions {
    // 管理地址
    address owner;

    // 分区的id
    uint256 id;
    // 种植品种
    string plantingVarieties;
    //分区名称
    string partitionsName;
    // 种植名称
    string plantingName;
    // 是否收获
    bool offHarvest;
    // 种植日期
    string plantingDate;
     
    // 收获时间
    uint harvestTimestamp;

    //是否加工
    bool process;
    // 所属大棚
    address ofGreenhouse;
    // 备注
    string notes;
    
    // 施肥结构体
    struct Fertilizer {
        uint256 id; // id
        string date; // 施肥日期
        string fertilizerNme; //肥料名称
        string fertilizerNumber; //施肥数量
        string notes; // 备注
        bool exist; //是否施肥
    }
    
    // 施肥记录
    Fertilizer[] fertilizers;
    mapping(uint256 => Fertilizer) fertilizerMapping;
    
    // 加工食品
    struct Food{
        string id; //溯源id
        string foodName; //食品名称
        uint quality; //食品质量（0 不及格、1 及格 2 优秀）
        uint processingTimestamp; //加工日期
        string weight; //食品重量
        string notes; //备注
        bool exist; //是否存在
    }
    
    mapping(string=>Food)foodMapping; //加工食品映射
    uint foodCount; //加工食品总数

    // 采集器值
    struct CollectorValue {
        uint256 id; // id
        string deviceId; //设备id
        string date; // 时间
        string temperature; // 温度
        string humidity; // 湿度
        string airquality; // 空气质量
        string pressure; // 大气压强
    }

    // 采集记录
    CollectorValue[] collectorValues;
    mapping(uint256 => CollectorValue) collectorValueMapping;

    // 权限判断
    modifier isOwner() {
        require(tx.origin == owner, "no permission of Partitions");
        _;
    }

    //收获判断
    modifier isOffHarvest() {
        require(offHarvest == false, "partitions offHarvest");
        _;
    }

    /**
     * @dev 构造函数，初始化
     * @param _id 初始化分区id
     * @param _partitionsName 初始化分区名称
     * @param _plantingVarieties 初始分区种植品种
     * @param _plantingName 初始化种植名称
     * @param _plantingDate 初始化所属大棚
     * @param _notes 初始化备注
     */
    constructor(
        uint256 _id,
        string memory _partitionsName,
        string memory _plantingVarieties,
        string memory _plantingName,
        string memory _plantingDate,
        address _ofGreenhouse,
        string memory _notes
    ) public {
        owner = tx.origin;
        id = _id;
        partitionsName = _partitionsName;
        plantingVarieties = _plantingVarieties;
        plantingName = _plantingName;
        plantingDate = _plantingDate;
        ofGreenhouse = _ofGreenhouse;
        notes = _notes;
    }

    /**
     * @dev 修改分区信息
     * @param _partitionsName 修改分区名称
     * @param _plantingVarieties 修改区种植品种
     * @param _plantingName 修改种植名称
     * @param _plantingDate 修改种植日期
     * @param _notes 修改备注
     */
    function modifyPartitionsInfo(
        string memory _partitionsName,
        string memory _plantingVarieties,
        string memory _plantingName,
        string memory _plantingDate,
        string memory _notes
    ) public isOwner isOffHarvest {
        // 不为空才修改
        if (keccak256(bytes(_partitionsName)) != keccak256(bytes(""))) {
            partitionsName = _partitionsName;
        }
        if (keccak256(bytes(_plantingVarieties)) != keccak256(bytes(""))) {
            plantingVarieties = _plantingVarieties;
        }
        if (keccak256(bytes(_plantingName)) != keccak256(bytes(""))) {
            plantingName = _plantingName;
        }
        if (keccak256(bytes(_plantingDate)) != keccak256(bytes(""))) {
            plantingDate = _plantingDate;
        }
        if (keccak256(bytes(_notes)) != keccak256(bytes(""))) {
            notes = _notes;
        }
    }

    /**
     * @dev 修改该分区的所属大棚
     * @param _newOfGreenhouse 大棚地址
     */
    function modifyOfGreenhouse(address _newOfGreenhouse) public isOwner isOffHarvest {
        ofGreenhouse = _newOfGreenhouse;
    }

    /**
     * @dev 修改该分区采摘状态
     * @param _offHarvest 是否采摘
     */
    function modifyoffHarvest(bool _offHarvest) public isOwner isOffHarvest {
        offHarvest = _offHarvest;
        harvestTimestamp= now;
    }
    

    

    /**
     * @dev 获取分区信息
     * @return _id 分区id
     * @return _plantingVarieties 种植种类
     * @return _partitionsName 分区名称
     * @return _plantingName 种植名称
     * @return _offHarvest 是否收获
     * @return _plantingDate 种植日期
     * @return _ofGreenhouse 所属大棚
     * @return _harvestTimestamp 收获时间
     * @return _notes 备注
     * @return _process 备注
     */
    function getPartitions()
        public
        view
        returns (
            uint256 _id,
            string memory _plantingVarieties,
            string memory _partitionsName,
            string memory _plantingName,
            bool _offHarvest,
            string memory _plantingDate,
            address _ofGreenhouse,
            uint _harvestTimestamp,
            string memory _notes,
            bool _process,
            uint _foodCount
        )
    {
        return (
            id,
            plantingVarieties,
            partitionsName,
            plantingName,
            offHarvest,
            plantingDate,
            ofGreenhouse,
            harvestTimestamp,
            notes,
            process,
            foodCount
        );
    }

    /**
     * @dev 获取大棚地址
     * @return  牧场地址
     */
    function getofGreenhouse() public view returns (address) {
        return ofGreenhouse;
    }

    /**
     * @dev 添加肥料
     * @param _id id
     * @param _date 日期
     * @param _fertilizerNme 肥料名称
     * @param _fertilizerNumber 肥料数量
     * @param _notes 备注
     */
    function addFertilizer(
        uint256 _id,
        string memory _date,
        string memory _fertilizerNme,
        string memory _fertilizerNumber,
        string memory _notes
    ) public isOffHarvest{
        Fertilizer memory fertilizer = Fertilizer(_id, _date, _fertilizerNme, _fertilizerNumber,_notes,true);
        fertilizers.push(fertilizer);
        fertilizerMapping[_id] = fertilizer;
    }
    
    
    /**
     * @dev 修改肥料
     * @param _id id
     * @param _date 日期
     * @param _fertilizerNme 肥料名称
     * @param _fertilizerNumber 肥料数量
     * @param _notes 备注
     */
    function modifyFertilizer(
        uint256 _id,
        string memory _date,
        string memory _fertilizerNme,
        string memory _fertilizerNumber,
        string memory _notes
    ) public isOffHarvest{
        Fertilizer storage fertilizer = fertilizerMapping[_id];
        require(fertilizer.exist,"id does not exist");
        for(uint index=0;index<fertilizers.length;index++ ){
            if(fertilizers[index].id==_id){
                  fertilizer.date=_date;
                  fertilizer.fertilizerNme=_fertilizerNme;
                  fertilizer.fertilizerNumber=_fertilizerNumber;
                  fertilizer.notes=_notes;
                  fertilizers[index]=fertilizer;
                  break;
            }
        }
      
      
    }
    

    /**
     * @dev 通过id获取施肥详情
     * @return  施肥信息
     */
    function getFertilizer(uint256[] memory _ids)
        public
        view
        returns (Fertilizer[] memory)
    {
        Fertilizer[] memory fertilizerList = new Fertilizer[](_ids.length);
        for (uint256 i = 0; i < _ids.length; i++) {
            fertilizerList[i] = fertilizerMapping[_ids[i]];
        }
        return fertilizerList;
    }

    /**
     * @dev 获取所有的施肥记录
     * @return  施肥记录
     */
    function getFertilizers() public view returns (Fertilizer[] memory) {
        return fertilizers;
    }

    
    /**
     * @dev 添加加工食品信息
     * @param _id id
     * @param _foodName 食品名称
     * @param _quality 食品质量
     * @param _weight 食品重量
     * @param _notes 备注
    */
    function addFood(
        string memory _id,
        string memory _foodName,
        uint _quality,
        string memory _weight,
        string memory _notes
    )public isOwner{
        require(offHarvest,"No harvest, no processing");
        require(!process,"Do not repeat processing");
        require(!foodMapping[_id].exist,"Do not repeat processing");
        Food memory food = Food(
            _id,
            _foodName,
            _quality,
            now,
            _weight,
            _notes,
            true
        );
        
        foodCount+=1;
        foodMapping[_id] = food;
    }
    
    
    /**
     * @dev 修改加工食品
     * @param _id id
     * @param _foodName 加工食品名称
     * @param _quality 加工食品质量
     * @param _weight  加工食品重量
     * @param _notes 备注
     */
    function modifyFood(
        string memory _id,
        string memory _foodName,
        uint _quality,
        string memory _weight,
        string memory _notes
    )public isOwner{
        require(!process,"Do not repeat processing");
        
        Food storage food=foodMapping[_id];
        require(food.exist,"The modified product does not exist");
        // 不为空才修改
        if(keccak256(bytes(_foodName))!= keccak256(bytes(""))){
            food.foodName=_foodName;
        }
        if(keccak256(bytes(_weight))!= keccak256(bytes(""))){
            food.weight=_weight;
        }
        if(keccak256(bytes(_notes))!= keccak256(bytes(""))){
            food.notes=_notes;
        }
        if(_quality>=0 && _quality<=2){
            food.quality=_quality;
        }
        
        food.processingTimestamp=now;
    }
    
    
    /**
     * @dev 移除加工食品
     * @param _id id
     */
    function removerFood(string memory _id)public isOwner{
        require(!process,"Do not repeat processing");
        Food storage food=foodMapping[_id];
        require(food.exist,"The modified product does not exist");
        food.exist=false;
        
        require(foodCount>0,"out of range");
        foodCount-=1;
        
    }
    
    /**
     * @dev 获取加工食品
     * @param _id id
     * @return 食品
     */
    function getFood(string memory _id) public view returns (Food memory) {
        require(foodMapping[_id].exist, "id does not exist");
        return foodMapping[_id];
    }

    /**
     * @dev 检查溯源id是否存在
     * @param _id id
     * @return 是否存在
     */
    function checkFood(string memory _id) public view returns (bool) {
        return foodMapping[_id].exist;
    }

    /**
     * @dev 完成加工食品
     */
    function completeProcessing() public  isOwner  {
        require(foodCount > 0, "unprocessed food");
        process = true;
    }
    
    
    
/**
     * @dev 添加传感器值
     */
    function addCollectorValue(
        uint256 _id,
        string memory _deviceId,
        string memory _date,
        string memory _temperature,
        string memory _humidity,
        string memory _airquality,
        string memory _pressure
    ) public isOwner isOffHarvest {
        CollectorValue memory sensorValue = CollectorValue(
            _id,
            _deviceId,
            _date,
            _temperature,
            _humidity,
            _airquality,
            _pressure
        );
        collectorValues.push(sensorValue);
        collectorValueMapping[_id] = sensorValue;
    }

    /**
     * @dev 获取指id传感器值
     * @return  传感器值
     */
    function getCollectorValue(uint256[] memory _ids)
        public
        view
        returns (CollectorValue[] memory)
    {
        CollectorValue[] memory list = new CollectorValue[](_ids.length);
        for (uint256 i = 0; i < _ids.length; i++) {
            list[i] = collectorValueMapping[_ids[i]];
        }
        return list;
    }


    /**
     * @dev 获取所有采集器记录
     * @return  获取所有采集器记录
     */
    function getCollectorValues() public view returns (CollectorValue[] memory) {
        return collectorValues;
    }
}
