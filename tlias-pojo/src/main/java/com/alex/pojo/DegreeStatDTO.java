package com.alex.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 学历统计DTO
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DegreeStatDTO {
    private String name;        // 学历名称
    private Integer value;       // 人数
}