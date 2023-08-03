package com.example.project2.dto;

import com.example.project2.entity.Board;
import com.example.project2.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentReqDTO {

    @Schema(description = "댓글 내용")
    private String comment;

    @Schema(description = "댓글 작성자")
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
