package com.example.project2.controller;

import com.example.project2.dto.BoardResDTO;
import com.example.project2.dto.CommentResDTO;
import com.example.project2.entity.Board;
import com.example.project2.service.BoardService;
import com.example.project2.service.CommentService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class commentController {

    private final CommentService commentService;
    private final BoardService boardService;


    @GetMapping(value = "/board/post/{id}/comment/{commentId}")
    public String commentUpdate(@PathVariable("id") Long id, @PathVariable("commentId") Long commentId, Model model) {
        BoardResDTO boardResDTO = boardService.getBoardId(id);
        CommentResDTO commentResDTO = commentService.getCommentId(commentId);
        model.addAttribute("board", boardResDTO);
        model.addAttribute("comment", commentResDTO);
        return "/comment/commentUpdate";
    }
}
