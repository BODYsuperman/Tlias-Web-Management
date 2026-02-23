package com.alex.service.impl;

import com.alex.mapper.DeptMapper;
import com.alex.pojo.Dept;
import com.alex.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;


@Service
public class DeptServiceImpl implements DeptService {

    @Autowired
    private DeptMapper deptMapper;
    @Override
    public List<Dept> findAll() {
        return deptMapper.findAll();
    }

    @Override
    public void deleteId(Integer id) {
        deptMapper.deleteId(id);
    }

    @Override
    public void add(Dept dept) {
        dept.setCreateTime(LocalDateTime.now());  // 设置创建时间
        dept.setUpdateTime(LocalDateTime.now());  // 设置修改时间

        // 2. 调用mapper层插入数据
        deptMapper.insert(dept);

    }

    @Override
    public Dept getById(Integer id) {
        return deptMapper.getById(id);
    }

    @Override
    public void update(Dept dept) {
        dept.setUpdateTime(LocalDateTime.now());
        deptMapper.update(dept);
    }
}
