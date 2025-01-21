package com.frog.IaAgriculture.controller;

import cn.hutool.core.util.StrUtil;
import com.frog.common.annotation.Anonymous;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import com.frog.IaAgriculture.dto.ErrorCodeEnum;
import com.frog.IaAgriculture.model.entity.IaPartition;
import com.frog.IaAgriculture.service.IaPartitionService;
import com.frog.IaAgriculture.vo.CommonContant;
import com.frog.IaAgriculture.vo.ResultVO;
import javax.annotation.Resource;
import java.util.List;

@Anonymous
@RestController
@RequestMapping("/partition")
@Api(tags = "分区管理")
public class IaPartitionController {

    @Resource
    private IaPartitionService iaPartitionService;

    @PostMapping(value = "/add")
    @ApiOperation("添加分区")
    public ResultVO addLivestock(@RequestBody @Validated IaPartition iaPartition) {

        return iaPartitionService.addPartition(iaPartition);
    }

    @DeleteMapping(value = "/delete")
    @ApiOperation("删除分区")
    public ResultVO deletePartition(@ApiParam(value = "id", required = true) @RequestParam String id) {
        if (StrUtil.isBlank(id)) {
            return ResultVO.failed(ErrorCodeEnum.PARAMETER_ERROR);
        }
        return iaPartitionService.deletePartition(id);
    }

    @PutMapping(value = "/update")
    @ApiOperation("修改分区")
    public ResultVO updatePartition(@RequestBody @Validated IaPartition iaPartition) {
        return iaPartitionService.updatePartition(iaPartition);
    }


    @GetMapping(value = "/list")
    @ApiOperation("分区列表（未成熟列表）")
    public ResultVO partitionList(@ApiParam(value = "牧场名称", required = false) String pastureName,
                                  @ApiParam(value = "id", required = false) String id,
                                  @ApiParam(value = "当前页数", required = false)Integer page,
                                  @ApiParam(value = "总页数", required = false)Integer pageSize
    ) {
        if (page == null) {
            page = CommonContant.DEFAULT_CURRENT_PAGE;
        }
        if (pageSize == null) {
            pageSize = CommonContant.DEFAULT_PAGE_SIZE;
        }
        return iaPartitionService.partitionList(pastureName,id,page,pageSize);
    }

    @GetMapping(value = "/getById")
    @ApiOperation("通过id查询分区详情")
    public ResultVO getById(@ApiParam(value = "分区id", required = true) String id) {
        if (StrUtil.isBlank(id)) {
            return ResultVO.failed(ErrorCodeEnum.PARAMETER_ERROR);
        }
        return ResultVO.succeed(iaPartitionService.getById(id));
    }


    @PostMapping(value = "/harvestPartition")
    @ApiOperation("成熟选定分区")
    public ResultVO harvestPartition(@ApiParam(value = "id列表", required = true) @RequestBody List<String> ids) {

        if (ids == null || ids.isEmpty()) {
            return ResultVO.failed(ErrorCodeEnum.PARAMETER_ERROR);
        }
        return iaPartitionService.harvestPartition(ids);
    }

    @PutMapping(value = "/harvestPasture")
    @ApiOperation("一次性成熟大棚所有分区")
    public ResultVO harvestPasture(@ApiParam(value = "大棚id", required = true) String pastureId) {

        if (StrUtil.isBlank(pastureId)) {
            return ResultVO.failed(ErrorCodeEnum.PARAMETER_ERROR);
        }
        return iaPartitionService.harvestPasture(pastureId);
    }

    @PostMapping(value = "/outPastures")
    @ApiOperation("成熟多个大棚，一次成熟多个大棚所有分区")
    public ResultVO outPastures(@ApiParam(value = "牧场ids", example = "[1232342343,234234234234]", required = true)@RequestBody List<String> ids) {

        if (ids == null || ids.isEmpty()) {
            return ResultVO.failed(ErrorCodeEnum.PARAMETER_ERROR);
        }
        return iaPartitionService.outPastures(ids);
    }

    @PutMapping(value = "/finishProcess")
    @ApiOperation("完成加工")
    public ResultVO finishProcess(@ApiParam(value = "分区id", required = true) String id) {

        if (StrUtil.isBlank(id)) {
            return ResultVO.failed(ErrorCodeEnum.PARAMETER_ERROR);
        }
        return iaPartitionService.finishProcess(id);
    }


    @GetMapping(value = "/harvestList")
    @ApiOperation(value = "成熟列表",notes = "已经成熟的列表展示（加工列表）")
    public ResultVO harvestList(@ApiParam(value = "大棚名称（模糊查询）", required = false) String pastureName,
                            @ApiParam(value = "id", required = false) String id,
                            @ApiParam(value = "当前页数", required = false)Integer page,
                            @ApiParam(value = "总页数", required = false)Integer pageSize
    ) {
        if (page == null) {
            page = CommonContant.DEFAULT_CURRENT_PAGE;
        }
        if (pageSize == null) {
            pageSize = CommonContant.DEFAULT_PAGE_SIZE;
        }
        return iaPartitionService.harvestList(pastureName,id,page,pageSize);
    }
    @GetMapping(value = "/getList")
    @ApiOperation("施肥下拉分区列表")
    public ResultVO getList(
    ) {

        return iaPartitionService.getList();
    }
}
