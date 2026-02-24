package com.alex.controller;

import com.alex.pojo.Result;
import com.alex.pojo.Student;
import com.alex.pojo.StudentQueryReuslt;
import com.alex.service.StudentService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/students")
@RestController
public class StudentController {
    @Autowired
    private StudentService studentService;

    @GetMapping
    public Result page(StudentQueryReuslt studentQueryReuslt){
        log.info("分页查询学生信息: {}",studentQueryReuslt);
        return Result.success(studentService.page(studentQueryReuslt));
    }

    @PostMapping

    public Result saveStudent(@RequestBody Student student){
        log.info("保存学生信息: {}",student);
        studentService.saveStudent(student);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInFo(@PathVariable Integer id){
        log.info("查询指定员工ID: {}",id);
        return Result.success(studentService.getInFo(id));
    }

    @PutMapping

    public Result update(@RequestBody Student student){
        log.info("修改员工信息: {}",student);
        studentService.update(student);
        return Result.success();
    }

    @DeleteMapping("/{ids}")

    public Result delete(@PathVariable List<Integer> ids){
        log.info("删除员工:{}",ids);
        studentService.delete(ids);
        return Result.success();
    }

    @PutMapping("/violation/{id}/{score}")

    public Result updateViolation(@PathVariable Integer id,@PathVariable Integer score){
        log.info("修改员工:{}",id);
        studentService.updateViolation(id,score);
        return Result.success();
    }
}
