package com.frog.agriculture.service;

import java.util.HashMap;
import java.util.List;
import com.frog.agriculture.domain.TraceRecord;

/**
 * 溯源码查询记录Service接口
 * 
 * @author nealtsiao
 * @date 2023-08-07
 */
public interface ITraceRecordService 
{
    /**
     * 查询溯源码查询记录
     * 
     * @param recordId 溯源码查询记录主键
     * @return 溯源码查询记录
     */
    public TraceRecord selectTraceRecordByRecordId(Long recordId);

    /**
     * 查询溯源码查询记录列表
     * 
     * @param traceRecord 溯源码查询记录
     * @return 溯源码查询记录集合
     */
    public List<TraceRecord> selectTraceRecordList(TraceRecord traceRecord);

    /**
     * 新增溯源码查询记录
     * 
     * @param traceRecord 溯源码查询记录
     * @return 结果
     */
    public int insertTraceRecord(TraceRecord traceRecord);

    /**
     * 修改溯源码查询记录
     * 
     * @param traceRecord 溯源码查询记录
     * @return 结果
     */
    public int updateTraceRecord(TraceRecord traceRecord);

    /**
     * 批量删除溯源码查询记录
     * 
     * @param recordIds 需要删除的溯源码查询记录主键集合
     * @return 结果
     */
    public int deleteTraceRecordByRecordIds(Long[] recordIds);

    /**
     * 删除溯源码查询记录信息
     * 
     * @param recordId 溯源码查询记录主键
     * @return 结果
     */
    public int deleteTraceRecordByRecordId(Long recordId);
}
