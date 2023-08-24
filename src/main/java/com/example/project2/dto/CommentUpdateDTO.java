package com.example.project2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentUpdateDTO {

    @Schema(description = "댓글 내용")
    private String comment;
}
