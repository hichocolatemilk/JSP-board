package com.example.project2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentUpdateDTO {

    @Schema(description = "댓글 내용")
    @NotBlank
    private String comment;
}
