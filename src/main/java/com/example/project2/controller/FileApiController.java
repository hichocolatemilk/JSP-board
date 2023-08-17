package com.example.project2.controller;

import com.example.project2.service.FileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
public class FileApiController {

    private final FileService fileService;

    @PostMapping("/fileSystem")
    public ResponseEntity<?> uploadImage(@RequestParam("file") MultipartFile file) throws IOException {
        String uploadImage = fileService.uploadImage(file);
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadImage);
    }

    // 다운로드
    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadImage(@PathVariable("fileName") String fileName) {
        byte[] downloadImage = fileService.downloadImage(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadImage);
    }

}

