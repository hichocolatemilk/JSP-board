package com.example.project2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class BoardUpdateDTO {

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;
}
