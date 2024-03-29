package com.example.jspboard.service;

import com.example.jspboard.dto.BoardReqDTO;
import com.example.jspboard.dto.BoardResDTO;
import com.example.jspboard.dto.BoardUpdateDTO;
import com.example.jspboard.entity.Board;
import com.example.jspboard.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
@Slf4j
public class BoardService {

    private final BoardRepository boardRepository;


    //MVC PAGING
    public Page<Board> getList(Pageable pageable) {

        return boardRepository.findAll(pageable);
    }

    //MVC Search
    public Page<Board> search(String searchTitle, Pageable pageable) {
        return boardRepository.findByTitle(searchTitle, pageable);
    }


    /* ------------------------------------- */

    //REST CREATE
    public Long boardSave(BoardReqDTO boardReqDTO){
        log.info("board save: ", boardReqDTO);
        return boardRepository.save(boardReqDTO.toEntity()).getId();
    }

    //REST READ(전체)
    public List<Board> getTotal(){
        return boardRepository.findAll();

    }

    //REST READ(단일)
    public BoardResDTO getBoardId(Long id){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다." + id));
        return new BoardResDTO(board);
    }

    //REST UPDATE
    public Long boardUpdate(Long id, BoardUpdateDTO boardUpdateDTO){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다." + id));
        board.update(boardUpdateDTO.getTitle(), boardUpdateDTO.getContent());

        log.info("Board update:", boardUpdateDTO);
        return id;
    }

    //REST DELETE
    public void boardDelete(Long id){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다." + id));
        boardRepository.delete(board);
    }

    //조회수
    public int updateHit(Long id){
       return boardRepository.updateHit(id);
    }
}
