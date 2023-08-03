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
    private Long id;

    @Column(nullable = false)
    private String comment;

    @Column(nullable = false)
    private String commentWriter;

    @JoinColumn(name = "board_id")
    @ManyToOne()
    private Board board;

    public void update(String comment){
        this.comment = comment;
    }
}