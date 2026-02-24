package com.alex.controller;

import com.alex.pojo.ClassStatDTO;
import com.alex.pojo.DegreeStatDTO;
import com.alex.pojo.JobOption;
import com.alex.pojo.Result;
import com.alex.service.ReportService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

@Slf4j
@RequestMapping("/report")
@RestController
public class ReportController {

    @Autowired
    private ReportService reportService;

    /**
     * 统计各个职位的员工人数
     */
    @GetMapping("/empJobData")
    public Result getEmpJobData(){
        log.info("统计各个职位的员工人数");
        JobOption jobOption = reportService.getEmpJobData();
        return Result.success(jobOption);
    }


    @GetMapping("/empGenderData")
    public Result getEmpGenderData(){
        log.info("统计员工性别信息");
        List<Map> genderList = reportService.getEmpGenderData();
        return Result.success(genderList);
    }
    /**
     * 学员学历统计接口
     * 请求路径：/report/studentDegreeData
     * 请求方式：GET
     */
    @GetMapping("/studentDegreeData")
    public Result<List<DegreeStatDTO>> getStudentDegreeData() {
        log.info("接收请求：学历统计");

        try {
            List<DegreeStatDTO> data = reportService.getDegreeStats();
            return Result.success(data);
        } catch (Exception e) {
            log.error("学历统计查询失败", e);
            return Result.error("学历统计查询失败：" + e.getMessage());
        }
    }

    /**
     * 班级人数统计接口
     * 请求路径：/report/studentCountData
     * 请求方式：GET
     */
    @GetMapping("/studentCountData")
    public Result<ClassStatDTO> getStudentCountData() {
        log.info("接收请求：班级人数统计");

        try {
            ClassStatDTO data = reportService.getClassStats();
            return Result.success(data);
        } catch (Exception e) {
            log.error("班级人数统计查询失败", e);
            return Result.error("班级人数统计查询失败：" + e.getMessage());
        }
    }
}
