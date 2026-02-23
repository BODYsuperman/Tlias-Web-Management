package com.alex.controller;

import com.alex.pojo.Dept;
import com.alex.pojo.Result;
import com.alex.service.DeptService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DeptController {

    @Autowired
    private DeptService deptService;
    @GetMapping(value = "/depts")
    public Result<List<Dept>> list(){

        System.out.println("find all depts data");

        List<Dept> deptList = deptService.findAll();
        return Result.success(deptList);

    }

    @DeleteMapping("/depts")
    public Result delete(Integer id){
        System.out.println("delete id" +id);

        deptService.deleteId(id);
        return  Result.success();
    }

    @PostMapping("/depts")
    public Result add(@RequestBody Dept dept){
        System.out.println("add dept" + dept);
        deptService.add(dept);
        return Result.success();

    }

    @GetMapping("/depts/{id}")
    public  Result getInfo(@PathVariable Integer id){
        Dept dept = deptService.getById(id);
        return Result.success(dept);
    }

    @PutMapping("/depts")
    public  Result update(@RequestBody Dept dept){
        System.out.println("update dept" + dept);
        deptService.update(dept);
        return Result.success();
    }


}
