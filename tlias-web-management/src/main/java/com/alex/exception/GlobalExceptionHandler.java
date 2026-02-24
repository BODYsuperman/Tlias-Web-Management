package com.alex.exception;

import com.alex.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Slf4j
@RestControllerAdvice
public class GlobalExceptionHandler {

    // 处理所有未指定的异常
    @ExceptionHandler(Exception.class)
    public Result handleException(Exception e) {
        log.error("系统异常：", e);  // 使用日志框架记录
        return Result.error("系统繁忙，请稍后重试");
    }

    private String extractUserFriendlyMessage(String errorMessage) {
        // MySQL 的错误信息格式
        if (errorMessage.contains("Duplicate entry")) {
            // 解析重复的值和索引名
            Pattern pattern = Pattern.compile("Duplicate entry '(.*?)' for key '(.*?)'");
            Matcher matcher = pattern.matcher(errorMessage);

            if (matcher.find()) {
                String duplicateValue = matcher.group(1);
                String keyName = matcher.group(2);

                // 根据索引名返回友好提示
                switch (keyName) {
                    case "emp.username":
                        return "用户名 '" + duplicateValue + "' 已被使用";
                    case "emp.phone":
                        return "手机号 '" + duplicateValue + "' 已被注册";
                    case "emp.id_card":
                        return "身份证号 '" + duplicateValue + "' 已存在";
                    case "dept.name":
                        return "部门名称 '" + duplicateValue + "' 已存在";
                    default:
                        return "数据重复，请检查后重试";
                }
            }
        }

        return "操作失败：数据重复";
    }

    // 处理参数校验异常
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Result handleValidationException(MethodArgumentNotValidException e) {
        String message = e.getBindingResult()
                .getAllErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.joining("；"));
        return Result.error(message);
    }

    // 处理 SQL 异常
    @ExceptionHandler(SQLException.class)
    public Result handleSQLException(SQLException e) {
        log.error("数据库异常：", e);
        return Result.error("数据库操作失败");
    }
}