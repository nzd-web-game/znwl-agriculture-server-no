package com.frog.agriculture.controller;

import com.frog.agriculture.domain.TraceRecord;
import com.frog.agriculture.service.IDataStatisticsService;
import com.frog.agriculture.service.ITraceRecordService;
import com.frog.common.core.controller.BaseController;
import com.frog.common.core.domain.AjaxResult;
import com.frog.common.core.page.TableDataInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/agriculture/statistics")
public class DataStatisticsController extends BaseController {

    @Autowired
    private IDataStatisticsService dataStatisticsService;
    @Autowired
    private ITraceRecordService traceRecordService;

    @GetMapping("/selectBaseInfo")
    public TableDataInfo selectBaseInfo(){
        List<HashMap> list = dataStatisticsService.selectBaseInfo();
        return getDataTable(list);
    }
    @GetMapping("/selectDeviceInfo")
    public TableDataInfo selectDeviceInfo(){
        List<HashMap> list = dataStatisticsService.selectDeviceInfo();
        return getDataTable(list);
    }
    @GetMapping("/selectDeviceJobInfo")
    public TableDataInfo selectDeviceJobInfo(){
        List<HashMap> list = dataStatisticsService.selectDeviceJobInfo();
        return getDataTable(list);
    }
    @GetMapping("/selectRecordGroupByMonth")
    public TableDataInfo selectRecordGroupByMonth(){
        List<HashMap> list = dataStatisticsService.selectRecordGroupByMonth();
        return getDataTable(list);
    }
    @GetMapping("/selectTaskInfo")
    public TableDataInfo selectTaskInfo(){
        List<HashMap> list = dataStatisticsService.selectTaskInfo();
        return getDataTable(list);
    }
    @GetMapping("/selectAreaInfo")
    public TableDataInfo selectAreaInfo(){
        List<HashMap> list = dataStatisticsService.selectAreaInfo();
        return getDataTable(list);
    }
    @GetMapping("/selectRecordGroupByCity")
    public TableDataInfo selectRecordGroupByCity(){
        List<HashMap> list = dataStatisticsService.selectRecordGroupByCity();
        return getDataTable(list);
    }
    @GetMapping("/selectRecordStatistics")
    public TableDataInfo selectRecordStatistics(){
        List<HashMap> list = dataStatisticsService.selectRecordStatistics();
        return getDataTable(list);
    }
    @GetMapping("/selectRecordGroupBySellpro")
    public TableDataInfo selectRecordGroupBySellpro(){
        List<HashMap> list = dataStatisticsService.selectRecordGroupBySellpro();
        return getDataTable(list);
    }
    @GetMapping("/selectRecord")
    public TableDataInfo selectRecord(TraceRecord traceRecord)
    {
        startPage();
        List<HashMap> list = dataStatisticsService.selectRecord(traceRecord);
        return getDataTable(list);
    }
    @GetMapping("/selectTraceInfo")
    public TableDataInfo selectTraceInfo(){
        List<HashMap> list = dataStatisticsService.selectTraceInfo();
        return getDataTable(list);
    }
    @GetMapping("/selectToadyTaskCountByTaskHead")
    public AjaxResult selectToadyTaskCountByTaskHead(){
        HashMap data = dataStatisticsService.selectToadyTaskCountByTaskHead();
        return AjaxResult.success(data);
    }
}
