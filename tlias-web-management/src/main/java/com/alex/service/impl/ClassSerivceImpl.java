package com.alex.service.impl;

import com.alex.mapper.ClassMapper;
import com.alex.pojo.ClassQueryResult;
import com.alex.pojo.Clazz;
import com.alex.pojo.PageResult;
import com.alex.service.ClassService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClassSerivceImpl implements ClassService {
    @Autowired
    private ClassMapper classMapper;

    @Override
    public PageResult<Clazz> page(ClassQueryResult classQueryResult){
        //pageHelper的引入，轻松实现分页
        PageHelper.startPage(classQueryResult.getPage(),classQueryResult.getPageSize());
        List<Clazz> list = classMapper.list(classQueryResult);
        // 根据时间，来设置课程状态
        list.forEach(clazz->{
            if ( LocalDate.now().compareTo(clazz.getEndDate()) > 0){
                clazz.setStatus("已结课");
            } else if ( LocalDate.now().compareTo(clazz.getBeginDate()) < 0 ){
                clazz.setStatus("未开班");
            } else {
                clazz.setStatus("在读中");
            }
        });
        Page<Clazz> page = (Page<Clazz>) list;
        return new PageResult<Clazz>(page.getTotal(),page.getResult());
    }

    @Override
    public void saveClass(Clazz clazz) {
        if ( clazz.getBeginDate().compareTo(clazz.getEndDate()) > 0){
            throw new RuntimeException("开始时间不能晚于结束时间");
        }
        clazz.setCreateTime(LocalDateTime.now());
        clazz.setUpdateTime(LocalDateTime.now());
        classMapper.insertClass(clazz);
    }

    @Override
    public Clazz getById(Integer id){
        return classMapper.getById(id);
    }

    @Override
    public void update(Clazz clazz){
        clazz.setUpdateTime(LocalDateTime.now());
        classMapper.update(clazz);
    }

    @Override
    public void delete(Integer id){
        //1.检测是否存在学生关联
        if ( classMapper.countStudent(id) != null){
            throw new RuntimeException("对不起, 该班级下有学生, 不能直接删除");
        }
        classMapper.delete(id);
    }

    @Override
    public List<Clazz> listAll() {
        return classMapper.listAll();
    }
}