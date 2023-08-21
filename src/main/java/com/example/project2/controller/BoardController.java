package com.example.project2.controller;

import com.example.project2.dto.BoardResDTO;
import com.example.project2.dto.CommentResDTO;
import com.example.project2.dto.FileResDTO;
import com.example.project2.entity.Board;
import com.example.project2.entity.File;
import com.example.project2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
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
    public String board(Model model, @PageableDefault(size = 5) Pageable pageable, String searchTitle){
        
        Page<Board> boardPage = null;

        // 검색 관련
        if(searchTitle  == null){
            boardPage =  boardService.getList(pageable);
        }else{
            boardPage = boardService.search(searchTitle, pageable);
        }

        // ************* 중요 페이징 ****************//
        int pageNumber= boardPage.getPageable().getPageNumber(); //현재페이지
        int totalPages= boardPage.getTotalPages(); //총 페이지 수
        int pageBlock = 5; // 페이지 블럭
        int startBlockPage = ((pageNumber)/pageBlock)*pageBlock+1; //현재 페이지가 7이라면 1*5+1=6
        int endBlockPage = startBlockPage+pageBlock-1; //6+5-1=10. 6,7,8,9,10해서 10.
        endBlockPage= totalPages<endBlockPage? totalPages:endBlockPage;

        model.addAttribute("startBlockPage", startBlockPage);
        model.addAttribute("endBlockPage", endBlockPage);
        model.addAttribute("boardList",boardPage);
        return "/board/board";
    }

    @GetMapping("/board/view/update/{id}")
    public String update(@PathVariable("id") Long id, Model model){
        BoardResDTO boardResDTO = boardService.getBoardId(id);
        model.addAttribute("board", boardResDTO);
        return "/board/update";
    }

    @GetMapping(value = "/board/view/{id}")
    public String boardDtl(@PathVariable("id") Long id, Model model){
        BoardResDTO boardResDTO = boardService.getBoardId(id);
        List<CommentResDTO> commentList =  boardResDTO.getCommentList();
        List<FileResDTO> fileList = boardResDTO.getFileList();

        if (commentList != null && !commentList.isEmpty())
        {
            model.addAttribute("commentList", commentList); // 댓글
        }
        if (fileList != null && !fileList.isEmpty())
        {
            model.addAttribute("fileList", fileList); // 댓글
        }
        boardService.updateHit(id); //조회수
        model.addAttribute("board", boardResDTO);
        return "/board/boardDtl";
    }

    @GetMapping("/board/post")
    public String boardPost(){
        return  "/board/post";
    }
}
