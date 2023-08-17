package com.example.project2.dto;

import com.example.project2.entity.File;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {

    private Long id;
    private String origFilename;
    private String filename;
    private String filePath;

    public File toEntity(){
        return File.builder()
                .origFilename(origFilename)
                .filename(filename)
                .filePath(filePath)
                .build();
    }

}
