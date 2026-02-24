package com.alex.service.impl;


import com.alex.mapper.ClassMapper;
import com.alex.mapper.StudentMapper;
import com.alex.mapper.StudentMapper;
import com.alex.pojo.PageResult;
import com.alex.pojo.Student;
import com.alex.pojo.StudentQueryReuslt;
import com.alex.pojo.ViolationData;
import com.alex.service.StudentService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class StudentSerivceImpl implements StudentService {
    @Autowired
    private StudentMapper studentMapper;




    @Override
    public PageResult<Student> page(StudentQueryReuslt studentQueryReuslt) {
        PageHelper.startPage(studentQueryReuslt.getPage(),studentQueryReuslt.getPageSize());
        List<Student> list = studentMapper.list(studentQueryReuslt);
        Page<Student> p = (Page) list;
        return new PageResult<Student>(p.getTotal(),p.getResult());
    }

    @Override
    public void saveStudent(Student student) {
        student.setCreateTime(LocalDateTime.now());
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.insert(student);
    }

    @Override
    public Student getInFo(Integer id) {
        return studentMapper.getById(id);
    }

    @Override
    public void update(Student student) {
        student.setUpdateTime(LocalDateTime.now());
        studentMapper.update(student);
    }

    @Override
    public void delete(List<Integer> ids) {
        studentMapper.delete(ids);
    }

    @Override
    public void updateViolation(Integer id, Integer score) {
        studentMapper.updateUpdateTime(LocalDateTime.now(),id);
        ViolationData vd = studentMapper.countViolation(id);
        studentMapper.updateViolation(vd.getCount()+1,vd.getScore()+score,id);
    }


}