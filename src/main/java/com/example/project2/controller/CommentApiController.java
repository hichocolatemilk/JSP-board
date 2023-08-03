package com.example.project2.controller;

import com.example.project2.dto.CommentReqDTO;
import com.example.project2.dto.CommentResDTO;
import com.example.project2.dto.CommentUpdateDTO;
import com.example.project2.entity.Comment;
import com.example.project2.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/api")
@RestController
@Tag(name = "댓글 API", description = "댓글 API(CRUD)")
public class CommentApiController {

    private final CommentService commentService;

    @Operation(summary = "전체 조회", description = "게시글에 달린 댓글 전체를 조회한다.")
    @GetMapping("/comments/getall")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @Operation(summary = "선택 조회", description = "특정 댓글을 조회한다.")
    @GetMapping("/comments/{id}")
    public CommentResDTO getComment(@PathVariable("id") Long id) {
        return commentService.getComment(id);
    }

    @Operation(summary = "댓글 생성", description = "댓글을 생성한다.")
    @PostMapping("/comments")
    public Long commentPost(@RequestBody CommentReqDTO commentReqDTO){
        return commentService.commentSave(commentReqDTO);
    }

    @Operation(summary = "선택한 댓글 수정", description = "선택한 댓글을 수정한다.")
    @PutMapping("/comments/{id}")
    public Long commentUpdate(@PathVariable("id") Long id, CommentUpdateDTO commentUpdateDTO){
        return commentService.updateComment(id, commentUpdateDTO);
    }

    @Operation(summary = "선택한 댓글 삭제", description = "선택한 댓글을 삭제한다.")
    @DeleteMapping("/comments/{id}")
    public Long deleteComment(@PathVariable("id") Long id){
        commentService.deleteComment(id);
        return id;
    }
}
