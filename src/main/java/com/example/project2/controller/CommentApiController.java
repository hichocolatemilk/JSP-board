package com.example.project2.controller;

import com.example.project2.dto.CommentReqDTO;
import com.example.project2.dto.CommentResDTO;
import com.example.project2.dto.CommentUpdateDTO;
import com.example.project2.entity.Comment;
import com.example.project2.service.CommentService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/api")
@RestController
@Tag(name = "댓글 API", description = "댓글 API(CRUD)")
@Slf4j
public class CommentApiController {

    private final CommentService commentService;

    @Operation(summary = "전체 조회", description = "게시글에 달린 댓글 전체를 조회한다.")
    @GetMapping("/comment/getall")
    public List<Comment> getAllComments() {
        return commentService.getAllComments();
    }

    @Operation(summary = "선택 조회", description = "특정 댓글을 조회한다.")
    @GetMapping("/comment/{id}")
    public CommentResDTO getComment(@PathVariable("id") Long id) {
        return commentService.getCommentId(id);
    }

    @Operation(summary = "댓글 생성", description = "댓글을 생성한다.")
    @PostMapping("/board/{id}/comment")
    public Long commentPost(@PathVariable("id")Long id,@RequestBody CommentReqDTO commentReqDTO){
        log.info("===== POST =====");
        log.info("JSON: " + commentReqDTO);
        log.info("================");
        return commentService.commentSave(id,commentReqDTO);
    }

    @Operation(summary = "선택한 댓글 수정", description = "선택한 댓글을 수정한다.")
    @PutMapping("/board/{id}/comment/{commentId}")
    public Long commentUpdate(@PathVariable("id") Long id,@PathVariable("commentId") Long commentId, @RequestBody CommentUpdateDTO commentUpdateDTO){
        log.info("===== PUT =====");
        log.info("JSON: " + commentUpdateDTO);
        log.info("================");
        return commentService.updateComment(id,commentId ,commentUpdateDTO);
    }

    @Operation(summary = "선택한 댓글 삭제", description = "선택한 댓글을 삭제한다.")
    @DeleteMapping("/board/{id}/comment/{commentId}")
    public Long deleteComment(@PathVariable("id") Long id, @PathVariable("commentId") Long commentId){
        commentService.deleteComment(id, commentId);
        return id;
    }
}
