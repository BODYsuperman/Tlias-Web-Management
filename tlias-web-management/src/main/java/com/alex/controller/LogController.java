package com.alex.controller;

import com.alex.pojo.Result;
import com.alex.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/log")
@Slf4j
public class LogController {
    @Autowired
    private LogService logService;

    @GetMapping("/page")
    public Result page(Integer page,Integer pageSize){
        log.info("分页查询数据:{},{}",page,pageSize);
        return Result.success(logService.page(page,pageSize));
    }
}