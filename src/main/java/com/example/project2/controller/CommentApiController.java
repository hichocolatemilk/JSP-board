package com.example.project2.controller;

import com.example.project2.dto.CommentReqDTO;
import com.example.project2.dto.CommentResDTO;
import com.example.project2.dto.CommentUpdateDTO;
import com.example.project2.entity.Comment;
import com.example.project2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/api")
@RestController
public class CommentApiController {

    private final CommentService commentService;

    @GetMapping("/comments/getall")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @GetMapping("/comments/{id}")
    public CommentResDTO getComment(@PathVariable("id") Long id) {
        return commentService.getComment(id);
    }

    @PostMapping("/comments")
    public Long commentPost(@RequestBody CommentReqDTO commentReqDTO){
        return commentService.commentSave(commentReqDTO);
    }

    @PutMapping("/comments/{id}")
    public Long commentUpdate(@PathVariable("id") Long id, CommentUpdateDTO commentUpdateDTO){
        return commentService.updateComment(id, commentUpdateDTO);
    }

    @DeleteMapping("/comments/{id}")
    public Long deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return id;
    }
}
