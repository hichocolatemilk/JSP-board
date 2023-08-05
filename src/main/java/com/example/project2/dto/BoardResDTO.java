package com.example.project2.dto;

import com.example.project2.entity.Board;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResDTO {

    @Schema(description = "글번호")
    private Long id;

    @Schema(description = "제목")
    private String title;

    @Schema(description = "내용")
    private String content;

    @Schema(description = "작성자")
    private String writer;

    @Schema(description = "조회수", defaultValue = "null")
    private int hit;




    @Schema(description = "댓글")
    private List<CommentResDTO> commentList;

    public BoardResDTO(Board board){
        this.id = board.getId();
        this.title = board.getTitle();
        this.content = board.getContent();
        this.writer = board.getWriter();
        this.hit = board.getHit();
        this.commentList = board.getCommentList().stream()
                .map(CommentResDTO:: new).collect(Collectors.toList());

    }
}
