package com.example.project2.dto;

import com.example.project2.entity.File;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FileResDTO {

    private Long id;
    private String fileName;
    private String fileType;
    private byte[] fileData;

    public FileResDTO(File file){
        this.id = file.getId();
        this.fileName = file.getFileName();
        this.fileType = file.getFileType();
        this.fileData = file.getFileData();

    }
}
