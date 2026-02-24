package com.alex.service;

import com.alex.pojo.OperateLog;
import com.alex.pojo.PageResult;

public interface LogService {
    PageResult<OperateLog> page(Integer page, Integer pageSize);
}