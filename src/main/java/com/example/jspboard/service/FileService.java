package com.example.jspboard.service;

import com.example.jspboard.dto.FileResDTO;
import com.example.jspboard.entity.Board;
import com.example.jspboard.entity.File;
import com.example.jspboard.repository.BoardRepository;
import com.example.jspboard.repository.FileRepository;
import com.example.jspboard.utils.FileUtils;
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
    private final BoardRepository boardRepository;

    //파일 업로드
    //REST POST(CREATE)
    public String uploadFile(MultipartFile file, Long id) throws IOException {
        log.info("upload file: {}", file);

        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            return "Invalid board ID";
        }
        File files = fileRepository.save(
                File.builder()
                        .fileName(file.getOriginalFilename())
                        .fileType(file.getContentType())
                        .fileData(FileUtils.compressFile(file.getBytes()))
                        .board(board)
                        .build());
        if (files != null) {
            log.info("fileData: {}", files);
            log.info("Successfully uploaded");
        }
        return "Successfully uploaded";
    }

    // 파일 다운로드
    //REST GET?(READ)
    public byte[] downloadFile(String fileName) {
        File files = fileRepository.findByFileName(fileName)
                .orElseThrow(RuntimeException::new);

        log.info("download Data: {}", files);

        return FileUtils.decompressFile(files.getFileData());
    }

    //파일 수정
    //REST PUT(UPDATE)
    public String updateFile(MultipartFile file, Long fileId, Long id) throws IOException {
        log.info("update file: {}", file);

        Board board = boardRepository.findById(id).orElse(null);
        File existingFile = fileRepository.findById(fileId).orElse(null);
        if (existingFile == null) {
            return "Invalid file ID";
        }

        File updatedFile = File.builder()
                .id(existingFile.getId())
                .fileName(file.getOriginalFilename())
                .fileType(file.getContentType())
                .fileData(FileUtils.compressFile(file.getBytes()))
                .board(board)
                .build();

        fileRepository.save(updatedFile);
        log.info("File updated: {}", updatedFile);

        return "File updated successfully";
    }

    //REST GET
    public FileResDTO getFileId(Long fileId){
        File file = fileRepository.findById(fileId).orElseThrow(
                () -> new IllegalArgumentException("파일을 찾을 수 없습니다." + fileId));
        return new FileResDTO(file);
    }

    //REST DELETE
    public void fileDelete(Long id,Long fileId){
        boardRepository.findById(id);
        File file = fileRepository.findById(fileId).orElseThrow(
                () -> new IllegalArgumentException("파일을 찾을 수 없습니다." + fileId));
        fileRepository.delete(file);
        log.info("file deleted successfully");
    }
}

