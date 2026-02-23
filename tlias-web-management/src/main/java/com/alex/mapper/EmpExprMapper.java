package com.alex.mapper;

import com.alex.pojo.EmpExpr;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface EmpExprMapper {

    void deletByEmpId(List<Integer> empIds);
    void insertBatch(List<EmpExpr> exprList);
}
