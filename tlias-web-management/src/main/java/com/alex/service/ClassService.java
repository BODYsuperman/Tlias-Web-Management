package com.alex.service;

import com.alex.pojo.ClassQueryResult;
import com.alex.pojo.Clazz;
import com.alex.pojo.PageResult;

import java.util.List;

public interface ClassService {

    public PageResult<Clazz> page(ClassQueryResult classQueryResult);

    public void saveClass(Clazz clazz);

    public Clazz getById(Integer id);

    public void update(Clazz clazz);

    public void delete(Integer id);

    public List<Clazz> listAll();
}
