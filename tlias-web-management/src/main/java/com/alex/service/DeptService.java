package com.alex.service;

import com.alex.pojo.Dept;

import java.util.List;

public interface DeptService {
    List<Dept> findAll();

     void deleteId(Integer id);

    void add(Dept dept);

    Dept getById(Integer id);

    void update(Dept dept);
}
