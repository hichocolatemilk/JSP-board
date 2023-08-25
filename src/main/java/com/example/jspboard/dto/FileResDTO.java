package com.example.jspboard.dto;

import com.example.jspboard.entity.File;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.ToString;

@Getter
@ToString
public class FileResDTO {

    @Schema(description = "파일 번호")
    private Long id;

    @Schema(description = "파일명")
    private String fileName;

    @Schema(description = "파일 타입")
    private String fileType;

    @Schema(description = "파일크기")
    private byte[] fileData;

    public FileResDTO(File file){
        this.id = file.getId();
        this.fileName = file.getFileName();
        this.fileType = file.getFileType();
        this.fileData = file.getFileData();

    }
}
