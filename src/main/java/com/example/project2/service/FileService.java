package com.example.project2.service;

import com.example.project2.dto.FileResDTO;
import com.example.project2.entity.Board;
import com.example.project2.entity.File;
import com.example.project2.repository.BoardRepository;
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
    private final BoardRepository boardRepository;

    //파일 업로드
    public String uploadFile(MultipartFile file, Long id) throws IOException {
        log.info("upload file: {}", file);

        Board board = boardRepository.findById(id).orElse(null);
        if (board == null) {
            // 해당 ID의 board가 없을 경우 처리 (에러 처리 등)
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
        return null;
    }

    // 파일 다운로드
    public byte[] downloadFile(String fileName) {
        File files = fileRepository.findByFileName(fileName)
                .orElseThrow(RuntimeException::new);

        log.info("download Data: {}", files);

        return FileUtils.decompressFile(files.getFileData());
    }

    //파일 수정
    public String updateFile(MultipartFile file, Long fileId, Long id) throws IOException {
        log.info("update file: {}", file);

        Board board = boardRepository.findById(id).orElse(null);
        File existingFile = fileRepository.findById(fileId).orElse(null);
        if (existingFile == null) {
            // 해당 ID의 파일이 없을 경우 처리 (에러 처리 등)
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

