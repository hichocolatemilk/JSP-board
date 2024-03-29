package com.example.jspboard.controller;

import com.example.jspboard.dto.BoardReqDTO;
import com.example.jspboard.dto.BoardResDTO;
import com.example.jspboard.dto.BoardUpdateDTO;
import com.example.jspboard.entity.Board;
import com.example.jspboard.service.BoardService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping(value = "/api")
@RestController
@Tag(name = "게시판 API", description = "게시판 API(CRUD)")
@Slf4j
public class BoardApiController {

    private final BoardService boardService;

    @Operation(summary = "전체 게시글 조회", description = " 게시글 전체 조회한다.")
    @GetMapping("/board/getall")
    public List<Board> boardList(){
      return boardService.getTotal();
    }

    @Operation(summary = "선택 조회", description = "선택한 게시글을 조회한다.")

    @GetMapping("/board/{id}")
    public BoardResDTO getBoard(@PathVariable("id") Long id){
        return boardService.getBoardId(id);
    }

    @Operation(summary = "게시글 생성", description = "게시글을 생성한다.")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "OK"),
            @ApiResponse(responseCode = "500", description = "JSON 오타 확인 요망")
    })
    @PostMapping("/board")
    public Long postBoard(@RequestBody @Valid BoardReqDTO boardReqDTO){
        log.info("===== POST =====");
        log.info("JSON: " + boardReqDTO);
        log.info("================");
        return boardService.boardSave(boardReqDTO);
    }

    @Operation(summary = "선택한 게시글 수정", description = "선택한 게시글을 수정한다.")
    @PutMapping("/board/{id}")
    public Long updateBoard(@PathVariable("id") Long id, @RequestBody @Valid BoardUpdateDTO boardUpdateDTO){
        log.info("===== PUT =====");
        log.info("JSON: " + boardUpdateDTO);
        log.info("================");
        return boardService.boardUpdate(id, boardUpdateDTO);
    }

    @Operation(summary = "선택한 게시글 삭제", description = "선택한 게시글을 삭제한다.")
    @DeleteMapping("/board/{id}")
    public Long deleteBoard(@PathVariable("id") Long id){
        boardService.boardDelete(id);
        return id;
    }
}
