package com.example.project2.service;

import com.example.project2.dto.CommentReqDTO;
import com.example.project2.dto.CommentResDTO;
import com.example.project2.dto.CommentUpdateDTO;
import com.example.project2.entity.Comment;
import com.example.project2.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {

    private final BoardService boardService;
    private final CommentRepository commentRepository;

    //REST CREATE
    public Long commentSave(Long id,CommentReqDTO commentReqDTO){
        boardService.getBoardId(id);
        return commentRepository.save(commentReqDTO.toEntity()).getCommentId();
    }

    //Rest READ(전체)
    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }


    //REST READ(단일)
    public CommentResDTO getCommentId(Long id) {
        Comment comment =  commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("없음"));

        return new CommentResDTO(comment);
    }

    //REST UPDATE
    public Long updateComment(Long id, Long commentId, CommentUpdateDTO commentUpdateDTO){
        boardService.getBoardId(id);
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다." + id));
        comment.update(commentUpdateDTO.getComment());
        return id;
    }

    //REST DELETE
    public void deleteComment(Long id, Long commentId){
        boardService.getBoardId(id);
        Comment comment = commentRepository.findById(commentId).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다." + id));
        commentRepository.delete(comment);
    }


}
