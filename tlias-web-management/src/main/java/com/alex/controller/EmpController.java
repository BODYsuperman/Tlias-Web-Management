package com.alex.controller;


import com.alex.pojo.Emp;
import com.alex.pojo.EmpQueryParam;
import com.alex.pojo.PageResult;
import com.alex.pojo.Result;
import com.alex.service.EmpService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RequestMapping("/emps")
@RestController
public class EmpController {

    @Autowired
    private EmpService empService;

//    @GetMapping
//    public Result page(@RequestParam(defaultValue = "1") Integer page ,
//                       @RequestParam(defaultValue = "10") Integer pageSize){
//
//        log.info("查询员工信息, page={}, pageSize={}", page, pageSize);
//        PageResult<Emp> pageResult = empService.page(page, pageSize);
//        return Result.success(pageResult);
//    }

    @GetMapping
    public Result page(EmpQueryParam empQueryParam){

        log.info("查询员工信息" +empQueryParam);
        PageResult<Emp> pageResult = empService.page(empQueryParam);
        return Result.success(pageResult);
    }

    @PostMapping
    public Result save(@RequestBody Emp emp){
        log.info("add new employee:{}" + emp);
        empService.save(emp);
        return Result.success();
    }

    @DeleteMapping
    public Result delete(@RequestParam List<Integer> ids){

        log.info("删除员工"+ ids);
        empService.delete(ids);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result getInfo(@PathVariable Integer id){

        log.info("根据id查询员工的详细信息");

        try {
            Emp emp = empService.getInfo(id);

            if (emp != null) {
                return Result.success(emp);
            } else {
                return Result.error("员工不存在，id: " + id);
            }

        } catch (Exception e) {
            log.error("查询员工信息失败", e);
            return Result.error("查询失败：" + e.getMessage());
        }
    }

    @PutMapping
    public Result update(@RequestBody Emp emp){
        log.info("修改员工信息, {}", emp);
        empService.update(emp);
        return Result.success();
    }
}
