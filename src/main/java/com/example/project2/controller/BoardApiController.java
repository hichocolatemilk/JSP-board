package com.example.project2.controller;

import com.example.project2.dto.BoardReqDTO;
import com.example.project2.dto.BoardResDTO;
import com.example.project2.dto.BoardUpdateDTO;
import com.example.project2.entity.Board;
import com.example.project2.service.BoardService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/api")
@RestController
public class BoardApiController {

    private final BoardService boardService;

    @GetMapping("/board/getall")
    public List<Board> boardList(){
      return boardService.getTotal();
    }

    @GetMapping("/board/{id}")
    public BoardResDTO getBoard(@PathVariable("id") Long id){
        return boardService.getBoardId(id);
    }

    @PostMapping("/board")
    public Long postBoard(@RequestBody BoardReqDTO boardReqDTO){
        return boardService.boardSave(boardReqDTO);
    }

    @PutMapping("/board/{id}")
    public Long updateBoard(@PathVariable("id") Long id, @RequestBody BoardUpdateDTO boardUpdateDTO){
        return boardService.boardUpdate(id, boardUpdateDTO);
    }

    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable("id") Long id){
        boardService.boardDelete(id);
        return id;
    }
}
