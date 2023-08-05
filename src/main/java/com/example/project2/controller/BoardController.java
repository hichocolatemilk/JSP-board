package com.example.project2.controller;

import com.example.project2.dto.BoardResDTO;
import com.example.project2.dto.CommentResDTO;
import com.example.project2.entity.Board;
import com.example.project2.service.BoardService;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class BoardController {

    private final BoardService boardService;

    @GetMapping("/")
    public String board(Model model){
        model.addAttribute("boardList",boardService.getTotal());
        return "/board/board";
    }

    @GetMapping("/post/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        BoardResDTO boardResDTO = boardService.getBoardId(id);
        model.addAttribute("board", boardResDTO);
        return "/board/update";
    }

    @GetMapping(value = "/post/view/{id}")
    public String boardDtl(@PathVariable("id") Long id, Model model){
        BoardResDTO boardResDTO = boardService.getBoardId(id);
        List<CommentResDTO> commentList =  boardResDTO.getCommentList();
        if (commentList != null && !commentList.isEmpty())
        {
            model.addAttribute("commentList", commentList); // 댓글
        }
        boardService.updateHit(id); //조회수
        model.addAttribute("board", boardResDTO);
        return "/board/boardDtl";
    }

    @GetMapping("/post")
    public String boardPost(){
        return  "/board/post";
    }
}
