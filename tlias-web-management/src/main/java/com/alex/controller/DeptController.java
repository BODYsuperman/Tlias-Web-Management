package com.alex.controller;

import com.alex.anno.LogOperation;
import com.alex.pojo.Dept;
import com.alex.pojo.Result;
import com.alex.service.DeptService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Slf4j
public class DeptController {

    @Autowired
    private DeptService deptService;

    @GetMapping(value = "/depts")
    public Result<List<Dept>> list(){

        log.info("find all depts data");

        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);

    }

    @LogOperation
    @DeleteMapping("/depts")
    public Result delete(Integer id){

        deptService.deleteId(id);
        return  Result.success();
    }

    @LogOperation
    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        log.info("add dept" + dept);
        deptService.add(dept);
        return Result.success();

    }

    @GetMapping("/depts/{id}")
    public  Result getInfo(@PathVariable Integer id){
        log.info("Querying department by ID: {}", id);
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @LogOperation
    @PutMapping("/depts")
    public  Result update(@RequestBody Dept dept){
        log.info("update dept" + dept);
        deptService.update(dept);
        return Result.success();
    }


}
