package com.example.project2.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileReqDTO {

    private Long id;
    private String fileName;
    private String fileType;
    private byte[] fileData;



}
