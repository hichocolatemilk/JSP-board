package com.example.project2.entity;


import jakarta.persistence.*;
import lombok.*;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Comment extends BaseTimeEntity {

    @Id
    @Column(name = "comment_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String commentWriter;

    @ManyToOne
    @JoinColumn(name = "board_id")
    private Board board;

    public void update(String comment){
        this.comment = comment;
    }
}
