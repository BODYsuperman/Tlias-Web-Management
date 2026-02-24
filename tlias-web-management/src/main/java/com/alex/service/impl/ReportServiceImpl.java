package com.alex.service.impl;

import com.alex.mapper.ClassMapper;
import com.alex.mapper.EmpMapper;
import com.alex.mapper.StudentMapper;
import com.alex.pojo.ClassStatDTO;
import com.alex.pojo.DegreeStatDTO;
import com.alex.pojo.JobOption;
import com.alex.service.ReportService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ReportServiceImpl implements ReportService {

    @Autowired
    private EmpMapper empMapper;

    @Autowired
    private ClassMapper clazzMapper;

    @Autowired
    private StudentMapper studentMapper;

    @Override
    public JobOption getEmpJobData() {
        List<Map<String,Object>> list = empMapper.countEmpJobData();
        List<Object> jobList = list.stream().map(dataMap -> dataMap.get("pos")).toList();
        List<Object> dataList = list.stream().map(dataMap -> dataMap.get("total")).toList();
        return new JobOption(jobList, dataList);
    }

    @Override
    public List<Map> getEmpGenderData() {
        return empMapper.countEmpGenderData();
    }

    /**
            * 获取学历统计数据
     */
    @Override
    public List<DegreeStatDTO> getDegreeStats() {
        //log.info("查询学历统计数据");

        // 方式1：直接从数据库统计
        List<DegreeStatDTO> list = studentMapper.countByDegree();

        // 方式2：如果数据库中没有数据，返回空列表（前端会显示"暂无数据"）
        // 实际开发中，需要确保数据库中有测试数据

        return list;
    }

    /**
     * 获取班级人数统计数据
     */
    @Override
    public ClassStatDTO getClassStats() {
        //log.info("查询班级人数统计数据");

        // 方式1：使用JOIN查询（推荐，性能更好）
        List<Map<String, Object>> stats = clazzMapper.countStudentByClazz();

        List<String> clazzList = new ArrayList<>();
        List<Integer> dataList = new ArrayList<>();

        for (Map<String, Object> item : stats) {
            clazzList.add((String) item.get("clazzName"));

            // MySQL中COUNT返回的是Long类型，需要转成Integer
            Long count = (Long) item.get("studentCount");
            dataList.add(count.intValue());
        }

        return new ClassStatDTO(clazzList, dataList);

        /* 方式2：分步查询（如果班级表和学生表分开查询）
        List<String> clazzNames = clazzMapper.getAllClazzNames();
        List<Integer> dataList = new ArrayList<>();

        for (String clazzName : clazzNames) {
            // 根据班级名称查询学生人数
            Integer count = studentMapper.selectCount(
                new LambdaQueryWrapper<Student>()
                    .eq(Student::getClazzName, clazzName)
            );
            dataList.add(count != null ? count : 0);
        }

        return new ClassStatDTO(clazzNames, dataList);
        */
    }
}