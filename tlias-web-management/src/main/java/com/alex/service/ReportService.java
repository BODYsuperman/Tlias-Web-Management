package com.alex.service;

import com.alex.pojo.ClassStatDTO;
import com.alex.pojo.DegreeStatDTO;
import com.alex.pojo.JobOption;

import java.util.List;
import java.util.Map;

public interface ReportService {
    /**
     * 统计各个职位的员工人数
     *
     * @return
     */
    JobOption getEmpJobData();

    List<Map> getEmpGenderData();


    List<DegreeStatDTO> getDegreeStats();

    /**
     * 获取班级人数统计数据
     */
    ClassStatDTO getClassStats();

}