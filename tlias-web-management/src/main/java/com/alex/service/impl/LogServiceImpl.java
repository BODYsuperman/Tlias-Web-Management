package com.alex.service.impl;

import com.alex.mapper.OperateLogMapper;
import com.alex.pojo.OperateLog;
import com.alex.pojo.PageResult;
import com.alex.service.LogService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LogServiceImpl implements LogService {
    @Autowired
    private OperateLogMapper operateLogMapper;

    @Override
    public PageResult<OperateLog> page(Integer page, Integer pageSize) {
        PageHelper.startPage(page,pageSize);
        List<OperateLog> list = operateLogMapper.list();
        Page p = (Page)list;
        return new PageResult<OperateLog>(p.getTotal(),p);
    }
}