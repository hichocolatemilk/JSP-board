package com.example.project2.dto;

import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class FileReqDTO {

    private Long id;
    private String fileName;
    private String fileType;
    private byte[] fileData;
}
