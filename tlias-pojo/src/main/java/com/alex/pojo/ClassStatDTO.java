package com.alex.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * 班级人数统计DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class ClassStatDTO {
    private List<String> clazzList;    // 班级名称列表
    private List<Integer> dataList;     // 人数列表
}