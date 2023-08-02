package com.example.project2.dto;

import com.example.project2.entity.Board;
import com.example.project2.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CommentReqDTO {

    private Long id;
    private String comment;
    private String commentWriter;
    private Board board;

    public Comment toEntity(){
        return Comment.builder()
                .id(id)
                .comment(comment)
                .commentWriter(commentWriter)
                .board(board)
                .build();
    }
}
