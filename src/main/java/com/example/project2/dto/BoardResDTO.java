package com.example.project2.dto;

import com.example.project2.entity.Board;
import lombok.Getter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
public class BoardResDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;
    private int hit;
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
