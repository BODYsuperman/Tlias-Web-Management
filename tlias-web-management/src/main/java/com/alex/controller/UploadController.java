package com.alex.controller;


import com.alex.pojo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {


        @PostMapping("/upload")
        public Result upload(String name, Integer age, MultipartFile file) throws IOException {
            log.info("Receive params: {}, {}, {}", name, age, file);

            String originalFilename = file.getOriginalFilename();
            String extension = originalFilename.substring(originalFilename.lastIndexOf("."));

            String newFilename = UUID.randomUUID().toString() + extension;


            Path path = Paths.get("/Users/alexandarmay/Documents/files", newFilename);
            file.transferTo(path);

            return  Result.success();
        }
}
