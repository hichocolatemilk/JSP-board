package com.example.jspboard.dto;

import com.example.jspboard.entity.Board;
import com.example.jspboard.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class CommentReqDTO {

    @Schema(description = "댓글 내용")
    @NotBlank
    private String comment;

    @Schema(description = "댓글 작성자")
    @NotBlank
    private String commentWriter;

    @Schema(description = "게시판 글번호")
    private Board board;

    public Comment toEntity(){
        return Comment.builder()
                .comment(comment)
                .commentWriter(commentWriter)
                .board(board)
                .build();
    }
}
