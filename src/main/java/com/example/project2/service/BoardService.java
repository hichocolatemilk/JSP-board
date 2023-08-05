package com.example.project2.service;

import com.example.project2.dto.BoardReqDTO;
import com.example.project2.dto.BoardResDTO;
import com.example.project2.dto.BoardUpdateDTO;
import com.example.project2.entity.Board;
import com.example.project2.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Service
@Transactional
public class BoardService {

    private final BoardRepository boardRepository;



    //READ
    public List<Board> getTotal(){
       return boardRepository.findAll();
    }

    public BoardResDTO getBoardId(Long id){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다." + id));
        return new BoardResDTO(board);
    }

    //CREATE
    public Long boardSave(BoardReqDTO boardReqDTO){
        return boardRepository.save(boardReqDTO.toEntity()).getId();
    }

    //DELETE
    public void boardDelete(Long id){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다." + id));
        boardRepository.delete(board);
    }

    //UPDATE
    public Long boardUpdate(Long id, BoardUpdateDTO boardUpdateDTO){
        Board board = boardRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("게시글을 찾을 수 없습니다." + id));
        board.update(boardUpdateDTO.getTitle(), boardUpdateDTO.getContent());

        return id;
    }

    //조회수
    public int updateHit(Long id){
       return boardRepository.updateHit(id);
    }
}
