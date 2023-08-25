package com.example.project2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FileReqDTO {

    @Schema(description = "파일 번호")
    private Long id;
    
    @Schema(description = "파일명")
    private String fileName;
    
    @Schema(description = "파일 타입")
    private String fileType;
    
    @Schema(description = "파일크기")
    private byte[] fileData;

}
