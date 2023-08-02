package com.example.project2.dto;


import com.example.project2.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResDTO {

    private Long id;
    private String comment;
    private String commentWriter;
    private Long boardId;

    public CommentResDTO(Comment comment){
        this.id = comment.getId();
        this.comment = comment.getComment();
        this.commentWriter = comment.getCommentWriter();
        this.boardId = comment.getBoard().getId();
    }
}
