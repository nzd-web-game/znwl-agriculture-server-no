package com.frog.agriculture.mapper;

import java.util.List;
import com.frog.agriculture.domain.IotProductLayout;

/**
 * 产品布局Mapper接口
 * 
 * @author nealtsiao
 * @date 2023-08-11
 */
public interface IotProductLayoutMapper 
{

    /**
     * 查询产品布局列表
     * 
     * @param iotProductLayout 产品布局
     * @return 产品布局集合
     */
    public List<IotProductLayout> selectIotProductLayoutList(IotProductLayout iotProductLayout);

    /**
     * 新增产品布局
     * 
     * @param iotProductLayout 产品布局
     * @return 结果
     */
    public int insertIotProductLayout(IotProductLayout iotProductLayout);


    /**
     * 根据产品id和用户id删除产品布局
     * @param iotProductLayout
     * @return
     */
    public int deleteIotProductLayoutByProductIdAndUserId(IotProductLayout iotProductLayout);
}
