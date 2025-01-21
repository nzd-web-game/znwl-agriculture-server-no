package com.frog.agriculture.iotDomain;

import com.frog.common.annotation.Excel;
import com.frog.common.core.domain.BaseEntity;
import org.apache.commons.lang3.builder.ToStringBuilder;
import org.apache.commons.lang3.builder.ToStringStyle;

/**
 * 设备告警对象 iot_alert_log
 * 
 * @author kerwincui
 * @date 2022-01-13
 */
public class AlertLog extends BaseEntity
{
    private static final long serialVersionUID = 1L;

    /** 告警ID */
    private Long alertLogId;

    /** 告警名称 */
    @Excel(name = "告警名称")
    private String alertName;

    /** 告警级别（1=提醒通知，2=轻微问题，3=严重警告，4=场景联动） */
    @Excel(name = "告警级别", readConverterExp = "1==提醒通知，2=轻微问题，3=严重警告，4=场景联动")
    private Long alertLevel;

    /** 处理状态(0=不需要处理,1=未处理,2=已处理) */
    @Excel(name = "处理状态(1=不需要处理,2=未处理,3=已处理)")
    private Integer status;

    /** 产品ID */
    @Excel(name = "产品ID")
    private Long productId;

    /** 设备编号 */
    private String serialNumber;

    /** 告警详情 */
    private String detail;

    private Long userId;

    private String deviceName;

    private Long deviceId;

    private Integer pageSize;

    private Integer offSet;

    public Integer getPageSize() {
        return pageSize;
    }

    public void setPageSize(Integer pageSize) {
        this.pageSize = pageSize;
    }


    public Integer getOffSet() {
        return offSet;
    }

    public void setOffSet(Integer offSet) {
        this.offSet = offSet;
    }

    public Long getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(Long deviceId) {
        this.deviceId = deviceId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public void setDeviceName(String deviceName) {
        this.deviceName = deviceName;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public void setAlertLogId(Long alertLogId)
    {
        this.alertLogId = alertLogId;
    }

    public Long getAlertLogId() 
    {
        return alertLogId;
    }
    public void setAlertName(String alertLogName)
    {
        this.alertName = alertLogName;
    }

    public String getAlertName()
    {
        return alertName;
    }
    public void setAlertLevel(Long alertLevel) 
    {
        this.alertLevel = alertLevel;
    }

    public Long getAlertLevel() 
    {
        return alertLevel;
    }
    public void setStatus(Integer status)
    {
        this.status = status;
    }

    public Integer getStatus()
    {
        return status;
    }
    public void setProductId(Long productId) 
    {
        this.productId = productId;
    }

    public Long getProductId() 
    {
        return productId;
    }




    @Override
    public String toString() {
        return new ToStringBuilder(this,ToStringStyle.MULTI_LINE_STYLE)
            .append("alertLogId", getAlertLogId())
            .append("alertName", getAlertName())
            .append("alertLevel", getAlertLevel())
            .append("status", getStatus())
            .append("productId", getProductId())
            .append("createBy", getCreateBy())
            .append("createTime", getCreateTime())
            .append("updateBy", getUpdateBy())
            .append("updateTime", getUpdateTime())
            .append("remark", getRemark())
            .toString();
    }
}
