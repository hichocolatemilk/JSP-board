package com.example.project2.dto;


import com.example.project2.entity.Board;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BoardReqDTO {

    private Long id;
    private String title;
    private String content;
    private String writer;

    public Board toEntity() {
        return Board.builder()
                .title(title)
                .content(content)
                .writer(writer)
                .build();
    }

}
