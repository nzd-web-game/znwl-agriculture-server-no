package com.frog.IaAgriculture.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import com.frog.IaAgriculture.domain.Device;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

/**
 * 设备mapper
 */
@Mapper
public interface DeviceMapper extends BaseMapper<Device> {

    @Update(" UPDATE device SET device_id= #{device.deviceId}, pasture_id = #{device.pastureId}, device_name = #{device.deviceName}, status = #{device.status}, date = #{device.date}, update_time = #{device.updateTime}, remark = #{device.remark} WHERE id = #{device.id}")
    int updateDevice(@Param("device") Device device);

//    @Update("UPDATE device SET device_name = #{device.deviceName} WHERE id = #{device.id}")
//    int updateDevice(@Param("device") Device device);

}
