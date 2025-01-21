package com.frog.agriculture.service.impl;

import java.math.BigInteger;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.json.JSONArray;
import cn.hutool.json.JSONUtil;
import com.frog.IaAgriculture.mapper.IaPastureMapper;
import com.frog.IaAgriculture.model.IaPasture;
import com.frog.IaAgriculture.vo.CommonContant;
import com.frog.IaAgriculture.vo.ResultVO;
import com.frog.agriculture.domain.BatchTask;
import com.frog.agriculture.domain.StandardJob;
import com.frog.agriculture.domain.TaskLog;
import com.frog.agriculture.mapper.BatchTaskMapper;
import com.frog.agriculture.mapper.TaskLogMapper;
import com.frog.agriculture.service.IGermplasmService;
import com.frog.common.utils.DateUtils;
import com.frog.common.utils.SecurityUtils;
import org.fisco.bcos.sdk.transaction.model.dto.TransactionResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.frog.agriculture.mapper.CropBatchMapper;
import com.frog.agriculture.mapper.StandardJobMapper;
import com.frog.agriculture.domain.CropBatch;
import com.frog.agriculture.service.ICropBatchService;
import org.springframework.transaction.annotation.Transactional;
import vip.blockchain.agriculture.model.bo.PlatformAddPartitionsInputBO;
import vip.blockchain.agriculture.service.PlatformService;
import vip.blockchain.agriculture.utils.BaseUtil;

import javax.annotation.Resource;

/**
 * 作物批次Service业务层处理
 * 
 * @author nealtsiao
 * @date 2023-05-13
 */
@Service
public class CropBatchServiceImpl implements ICropBatchService 
{
    @Autowired
    private CropBatchMapper cropBatchMapper;
    @Autowired
    private StandardJobMapper standardJobMapper;
    @Autowired
    private BatchTaskMapper batchTaskMapper;
    @Autowired
    private TaskLogMapper taskLogMapper;

    @Autowired
    private IGermplasmService germplasmService;

    @Resource
    IaPastureMapper iaPastureMapper;

    @Resource
    PlatformService platformService;

    /**
     * 查询作物批次
     * 
     * @param batchId 作物批次主键
     * @return 作物批次
     */
    @Override
    public CropBatch selectCropBatchByBatchId(Long batchId)
    {
        return cropBatchMapper.selectCropBatchByBatchId(batchId);
    }

    /**
     * 查询作物批次列表
     * 
     * @param cropBatch 作物批次
     * @return 作物批次
     */
    @Override
    public List<CropBatch> selectCropBatchList(CropBatch cropBatch)
    {
        Long userId = SecurityUtils.getUserId();
        if(!SecurityUtils.isAdmin(userId)) {
            cropBatch.setBatchHead(SecurityUtils.getUserId());
        }
        return cropBatchMapper.selectCropBatchList(cropBatch);
    }

    /**
     * 新增作物批次
     * 
     * @param cropBatch 作物批次
     * @return 结果
     */
    @Override
    @Transactional
    public int insertCropBatch(CropBatch cropBatch)
    {
        IaPasture iaPasture = iaPastureMapper.selectById(cropBatch.getLandId().toString());
        String psContractAddr = iaPasture.getContractAddr();
        if (iaPasture == null || StrUtil.isBlank(psContractAddr)) {
            return  0;
        }
        String snowflakeId = BaseUtil.getSnowflakeId();
        long batchId = Long.parseLong(snowflakeId);  // 将String转为long类型
        cropBatch.setBatchId(batchId);
        cropBatch.setCreateTime(DateUtils.getNowDate());
        cropBatch.setCreateBy(SecurityUtils.getUserId().toString());

//        上链
        Date now = new Date();
        PlatformAddPartitionsInputBO partitionsInputBO = new PlatformAddPartitionsInputBO();
        partitionsInputBO.set_id(new BigInteger(String.valueOf(cropBatch.getBatchId())));
        partitionsInputBO.set_partitionsName(cropBatch.getBatchName());
        partitionsInputBO.set_notes(cropBatch.getRemark() == null ? " " : cropBatch.getRemark());
        partitionsInputBO.set_plantingName(germplasmService.selectGermplasmByGermplasmId(cropBatch.getGermplasmId()).getCropName());
        partitionsInputBO.set_plantingDate(DateUtil.format(now, "yyyy-MM-dd HH:mm:ss"));
        partitionsInputBO.set_plantingVarieties(cropBatch.getVariety()==null?"种类为空":cropBatch.getVariety());
        partitionsInputBO.set_ofGreenhouse(psContractAddr);
        try {
            TransactionResponse transactionResponse = platformService.addPartitions(partitionsInputBO);
            if (transactionResponse.getReceiptMessages().equals(CommonContant.SUCCESS_MESSAGE)) {
                String contractAddressArray = transactionResponse.getValues();
                JSONArray jsonArray = JSONUtil.parseArray(contractAddressArray);
                String contractAddress = jsonArray.getStr(0);
                cropBatch.setContractAddress(contractAddress);
            } else {
                throw new RuntimeException();
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

//        结束
        int i =cropBatchMapper.insertCropBatch(cropBatch);
        StandardJob queryPar = new StandardJob();
        queryPar.setGermplasmId(cropBatch.getGermplasmId());
        List<StandardJob> sjList = standardJobMapper.selectStandardJobList(queryPar);
        for(StandardJob sj : sjList){
            BatchTask bt = new BatchTask();
            bt.setBatchId(cropBatch.getBatchId());
            bt.setTaskHead(cropBatch.getBatchHead());
            bt.setTaskName(sj.getJobName());

            int mult = sj.getCycleUnit()=="0"?1:7;
            try {
                bt.setPlanStart(DateUtils.plusDay((int)(sj.getJobStart() * mult),cropBatch.getStartTime()));
                bt.setPlanFinish(DateUtils.plusDay((int)(sj.getJobFinish() * mult),cropBatch.getStartTime()));
            } catch (ParseException e) {
                throw new RuntimeException(e);
            }
            batchTaskMapper.insertBatchTask(bt);
            TaskLog tl = new TaskLog();
            tl.setTaskId(bt.getTaskId());
            tl.setOperName(SecurityUtils.getUsername());
            tl.setOperId(SecurityUtils.getUserId());
            tl.setOperDes("创建任务");
            tl.setCreateTime(DateUtils.getNowDate());
            taskLogMapper.insertTaskLog(tl);

        }


        return i;

    }

    /**
     * 修改作物批次
     * 
     * @param cropBatch 作物批次
     * @return 结果
     */
    @Override
    public int updateCropBatch(CropBatch cropBatch)
    {
        cropBatch.setUpdateTime(DateUtils.getNowDate());
        cropBatch.setUpdateBy(SecurityUtils.getUserId().toString());
        return cropBatchMapper.updateCropBatch(cropBatch);
    }

    /**
     * 批量删除作物批次
     * 
     * @param batchIds 需要删除的作物批次主键
     * @return 结果
     */
    @Override
    public int deleteCropBatchByBatchIds(Long[] batchIds)
    {
        return cropBatchMapper.deleteCropBatchByBatchIds(batchIds);
    }

    /**
     * 删除作物批次信息
     * 
     * @param batchId 作物批次主键
     * @return 结果
     */
    @Override
    public int deleteCropBatchByBatchId(Long batchId)
    {
        return cropBatchMapper.deleteCropBatchByBatchId(batchId);
    }

    /**
     * 给手机端批次列表查询数据
     * @param cropBatch
     * @return
     */
    @Override
    public List<CropBatch> selectCropBatchListToMobile(CropBatch cropBatch) {
        //非管理员只能看批次负责人为自己的批次
        if(!SecurityUtils.isAdmin(SecurityUtils.getUserId())){
            cropBatch.setBatchHead(SecurityUtils.getUserId());
        }
        return cropBatchMapper.selectCropBatchListToMobile(cropBatch);
    }
}
