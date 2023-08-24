package com.example.project2.controller;

import com.example.project2.dto.FileReqDTO;
import com.example.project2.service.BoardService;
import com.example.project2.service.FileService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequiredArgsConstructor
@RequestMapping(value = "/api")
@Tag(name = "파일 API", description = "파일 API(업로드, 다운로드, 삭제)")
@Slf4j
public class FileApiController {

    private final FileService fileService;
    @Operation(summary = "파일 생성", description = "파일을 생성한다.")
    @PostMapping("/{id}/fileSystem")
    public ResponseEntity<?> uploadFile(@PathVariable("id")Long id,@RequestParam("file") MultipartFile file) throws IOException {
        String uploadFile = fileService.uploadFile(file, id);
        log.info("===== POST =====");
        log.info("JSON: " + file);
        log.info("================");
        return ResponseEntity.status(HttpStatus.OK)
                .body(uploadFile);
    }
    // 다운로드
    @Operation(summary = "파일 다운로드", description = "파일을 다운로드한다.")
    @GetMapping("/fileSystem/{fileName}")
    public ResponseEntity<?> downloadFile(@PathVariable("fileName") String fileName) {
        byte[] downloadFile = fileService.downloadFile(fileName);
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.valueOf("image/png"))
                .body(downloadFile);
    }

    @Operation(summary = "파일 삭제", description = "파일을 삭제한다.")
    @DeleteMapping("/fileSystem/{fileId}")
    public Long deleteFile(@PathVariable("fileId") Long fileId){
        fileService.fileDelete(fileId);
        return fileId;
    }

}

