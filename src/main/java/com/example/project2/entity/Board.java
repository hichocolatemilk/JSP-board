package com.example.project2.entity;


import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.DynamicInsert;

import java.util.ArrayList;
import java.util.List;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@DynamicInsert
public class Board extends BaseTimeEntity {

    @Id
    @Column(name = "board_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

    @Column(nullable = true)
    @ColumnDefault("0")
    private Integer hit;

    @OneToMany(mappedBy = "board", cascade = CascadeType.REMOVE, fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Comment> commentList = new ArrayList<>();

    public void update(String title, String content){
        this.title = title;
        this.content = content;
    }
}
