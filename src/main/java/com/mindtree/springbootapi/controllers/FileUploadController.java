package com.mindtree.springbootapi.controllers;

import com.kelloggs.promotionapi.models.ApiResponse;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

@RestController
@RequestMapping("/sample/api/files")
public class FileUploadController {

    @PostMapping
    public ApiResponse<String> uploadFile(@RequestAttribute("file") MultipartFile file) throws IOException {
        Files.write(Paths.get("C:/Work/Spring/"+file.getOriginalFilename()),file.getBytes());
        return new ApiResponse<>(201,"CREATED","File Uploaded successfully",file.getOriginalFilename());
    }
}
