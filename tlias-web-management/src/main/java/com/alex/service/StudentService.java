package com.alex.service;


import com.alex.pojo.PageResult;
import com.alex.pojo.Student;
import com.alex.pojo.StudentQueryReuslt;

import java.util.List;

public interface StudentService {
    public PageResult<Student> page(StudentQueryReuslt studentQueryReuslt);

    public void saveStudent(Student student);

    public Student getInFo(Integer id);

    public void update(Student student);

    public void delete(List<Integer> ids);

    public void updateViolation(Integer id,Integer score);
}