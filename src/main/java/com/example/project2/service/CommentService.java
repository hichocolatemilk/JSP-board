package com.example.project2.service;

import com.example.project2.dto.CommentReqDTO;
import com.example.project2.dto.CommentResDTO;
import com.example.project2.dto.CommentUpdateDTO;
import com.example.project2.entity.Comment;
import com.example.project2.repository.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> getAllComments() {
        return commentRepository.findAll();
    }

    public CommentResDTO getComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다." + id));
        return new CommentResDTO(comment);
    }

    public Long commentSave(CommentReqDTO commentReqDTO){
        return commentRepository.save(commentReqDTO.toEntity()).getId();
    }

    public void deleteComment(Long id){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다." + id));
        commentRepository.delete(comment);
    }

    public Long updateComment(Long id, CommentUpdateDTO commentUpdateDTO){
        Comment comment = commentRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("댓글을 찾을 수 없습니다." + id));
        comment.update(commentUpdateDTO.getComment());
        return id;
    }
}
