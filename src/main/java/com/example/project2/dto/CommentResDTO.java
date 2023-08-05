package com.example.project2.dto;


import com.example.project2.entity.Comment;
import lombok.Getter;

@Getter
public class CommentResDTO {

    private Long commentId;
    private String comment;
    private String commentWriter;
    private String date;
    private Long boardId;

    public CommentResDTO(Comment comment){
        this.commentId = comment.getCommentId();
        this.comment = comment.getComment();
        this.commentWriter = comment.getCommentWriter();
        this.date = comment.getModifiedDate();
        this.boardId = comment.getBoard().getId();
    }
}
