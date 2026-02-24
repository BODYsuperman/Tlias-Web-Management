package com.alex.controller;

import com.alex.pojo.*;
import com.alex.service.ClassService;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/clazzs")
@RestController
public class ClassController {
    @Autowired
    private ClassService classService;

    // 分页查询
    @GetMapping
    public Result page(ClassQueryResult classQueryResult) {
        log.info("查询指定班级内容为:{}", classQueryResult);
        PageResult<Clazz> pageResult = classService.page(classQueryResult);
        return Result.success(pageResult);
    }

    //保存班级数据
    @PostMapping

    public Result saveClass(@RequestBody Clazz clazz){
        log.info("保存班级数据为:{}",clazz);
        classService.saveClass(clazz);
        return Result.success();
    }

    //根据ID查询班级
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        log.info("查询指定班级数据: {}",id);
        Clazz clazz = classService.getById(id);
        return Result.success(clazz);
    }

    //修改班级数据
    @PutMapping

    public Result update(@RequestBody Clazz clazz){
        log.info("修改班级数据为:{}",clazz);
        classService.update(clazz);
        return Result.success();
    }

    //删除班级数据
    @DeleteMapping("/{id}")

    public Result delete(@PathVariable Integer id){
        log.info("删除班级数据:{}",id);
        classService.delete(id);
        return Result.success();
    }

    //查询所有班级数据
    @GetMapping("/list")
    public Result listAll(){
        log.info("查询所有班级数据");
        List<Clazz> list = classService.listAll();
        return Result.success(list);
    }
}