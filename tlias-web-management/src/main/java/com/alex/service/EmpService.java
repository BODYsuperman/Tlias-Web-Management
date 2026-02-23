package com.alex.service;


import com.alex.pojo.Emp;
import com.alex.pojo.EmpQueryParam;
import com.alex.pojo.PageResult;

import java.util.List;

public interface EmpService {
    PageResult<Emp> page(EmpQueryParam empQueryParam);

    void save(Emp emp);

    void delete(List<Integer> ids);

    Emp getInfo(Integer id);

    /**
     * 分页查询
     * @param page 页码
     * @param pageSize 每页记录数
     */
    //PageResult page(Integer page, Integer pageSize);
}
