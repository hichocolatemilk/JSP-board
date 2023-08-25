package com.example.jspboard.dto;


import com.example.jspboard.entity.Comment;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

@Getter
public class CommentResDTO {

    @Schema(description = "댓글 ID(댓글 번호)")
    private Long commentId;
    
    @Schema(description = "댓글 내용")
    private String comment;
    
    @Schema(description = "댓글 작성자")
    private String commentWriter;
    
    @Schema(description = "댓글 수정시간")
    private String date;
    
    @Schema(description = "게시판 번호")
    private Long boardId;

    public CommentResDTO(Comment comment){
        this.commentId = comment.getCommentId();
        this.comment = comment.getComment();
        this.commentWriter = comment.getCommentWriter();
        this.date = comment.getModifiedDate();
        this.boardId = comment.getBoard().getId();
    }
}
