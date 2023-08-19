package com.example.project2.service;

import com.example.project2.entity.File;
import com.example.project2.repository.FileRepository;
import com.example.project2.utils.FileUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;


@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileService {

    private final FileRepository fileRepository;
    private final BoardService boardService;

    public String uploadImage(MultipartFile file) throws IOException {
        log.info("upload file: {}", file);
        File files = fileRepository.save(
                File.builder()
                        .fileName(file.getOriginalFilename())
                        .fileType(file.getContentType())
                        .fileData(FileUtils.compressFile(file.getBytes()))
                        .build());
        if (files != null) {
            log.info("fileData: {}", files);
            log.info("Successfully uploaded");
        }
        return null;
    }

    // 이미지 파일로 압축하기
    public byte[] downloadImage(String fileName) {
        File files = fileRepository.findByFileName(fileName)
                .orElseThrow(RuntimeException::new);

        log.info("download imageData: {}", files);

        return FileUtils.decompressFile(files.getFileData());
    }
}

