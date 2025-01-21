package com.frog.agriculture.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.frog.IaAgriculture.model.entity.IaPartition;
import com.frog.IaAgriculture.vo.CommonContant;
import com.frog.agriculture.domain.CropBatch;
import com.frog.agriculture.mapper.CropBatchMapper;
import com.frog.common.utils.DateUtils;
import com.frog.common.utils.SecurityUtils;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.frog.agriculture.mapper.BatchTaskMapper;
import com.frog.agriculture.domain.BatchTask;
import com.frog.agriculture.service.IBatchTaskService;
import vip.blockchain.agriculture.model.bo.PlatformOffHarvestInputBO;
import vip.blockchain.agriculture.service.PlatformService;

import javax.annotation.Resource;

/**
 * 批次任务Service业务层处理
 *
 * @author xuweidong
 * @date 2023-05-24
 */
@Service
public class BatchTaskServiceImpl implements IBatchTaskService
{
    @Autowired
    private BatchTaskMapper batchTaskMapper;

    @Autowired
    private CropBatchMapper cropBatchMapper;

    @Resource
    PlatformService platformService;

    /**
     * 查询批次任务
     *
     * @param taskId 批次任务主键
     * @return 批次任务
     */
    @Override
    public BatchTask selectBatchTaskByTaskId(Long taskId)
    {
        return batchTaskMapper.selectBatchTaskByTaskId(taskId);
    }

    /**
     * 查询批次任务列表
     *
     * @param batchTask 批次任务
     * @return 批次任务
     */
    @Override
    public List<BatchTask> selectBatchTaskList(BatchTask batchTask)
    {
        Long userId = SecurityUtils.getUserId();
        if(!SecurityUtils.isAdmin(userId)){
            batchTask.getParams().put("batchHead", userId);
        }
        return batchTaskMapper.selectBatchTaskList(batchTask);
    }


    /**
     * 新增批次任务
     *
     * @param batchTask 批次任务
     * @return 结果
     */
    @Override
    public int insertBatchTask(BatchTask batchTask)
    {
        batchTask.setCreateBy(SecurityUtils.getUserId().toString());
        batchTask.setCreateTime(DateUtils.getNowDate());
        return batchTaskMapper.insertBatchTask(batchTask);
    }

    /**
     * 修改批次任务
     *
     * @param batchTask 批次任务
     * @return 结果
     */
    @Override
    public int updateBatchTask(BatchTask batchTask)
    {
        batchTask.setUpdateBy(SecurityUtils.getUserId().toString());
        batchTask.setUpdateTime(DateUtils.getNowDate());
        int i = batchTaskMapper.updateBatchTask(batchTask);
        //每次更新检查一下任务是否都已经完成
        HashMap<String,Long> hm = batchTaskMapper.selectFinishTask(batchTask.getBatchId());
        CropBatch cropBatch = cropBatchMapper.selectCropBatchByBatchId(batchTask.getBatchId());
        if(hm.get("num") == 0){
            cropBatch.setStatus("1");
            cropBatchMapper.updateCropBatch(cropBatch);
//            ####
            ArrayList<String> listContract = new ArrayList<>();
            CropBatch data = cropBatchMapper.selectCropBatchByBatchId(batchTask.getBatchId());
            listContract.add(data.getContractAddress());
//            for (IaPartition ivLivestock : ivLivestocks) {
//                listContract.add(ivLivestock.getContractAddr());
//            }
            //上链
            PlatformOffHarvestInputBO pohi = new PlatformOffHarvestInputBO();
            pohi.set_partitionsss(listContract);
            try {
                TransactionResponse ot = platformService.offHarvest(pohi);
                if (!ot.getReceiptMessages().equals(CommonContant.SUCCESS_MESSAGE)) {
                    throw new RuntimeException();
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }else {
            cropBatch.setStatus("0");
            cropBatchMapper.updateCropBatch(cropBatch);
        }
        return i;
    }

    /**
     * 批量删除批次任务
     *
     * @param taskIds 需要删除的批次任务主键
     * @return 结果
     */
    @Override
    public int deleteBatchTaskByTaskIds(Long[] taskIds)
    {
        return batchTaskMapper.deleteBatchTaskByTaskIds(taskIds);
    }

    /**
     * 删除批次任务信息
     *
     * @param taskId 批次任务主键
     * @return 结果
     */
    @Override
    public int deleteBatchTaskByTaskId(Long taskId)
    {
        return batchTaskMapper.deleteBatchTaskByTaskId(taskId);
    }

    /**
     * 给手机端的任务列表
     * @param batchTask
     * @return
     */
    @Override
    public List<BatchTask> selectBatchTaskListToMobile(BatchTask batchTask) {
        batchTask.setBatchHead(SecurityUtils.isAdmin(SecurityUtils.getUserId())?null:SecurityUtils.getUserId());
        return batchTaskMapper.selectBatchTaskListToMobile(batchTask);
    }
}
