package com.example.project2.controller;

import com.example.project2.dto.BoardResDTO;
import com.example.project2.dto.CommentResDTO;
import com.example.project2.dto.FileResDTO;
import com.example.project2.service.BoardService;
import com.example.project2.service.FileService;
import lombok.RequiredArgsConstructor;
import org.eclipse.tags.shaded.org.apache.xpath.operations.Mod;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
@RequiredArgsConstructor
public class FileController {

    private final FileService fileService;
    private final BoardService boardService;

    @GetMapping(value = "/board/view/{id}/fileSystem")
    public String filePost(@PathVariable("id") Long id, Model model){
        BoardResDTO boardResDTO = boardService.getBoardId(id);
        model.addAttribute("board", boardResDTO);
        return "file/filePost";
    }

    @GetMapping(value = "/board/view/{id}/fileSystem/{fileId}")
    public String fileUpdate(@PathVariable("id") Long id, @PathVariable("fileId") Long fileId, Model model) {
        boardService.getBoardId(id);
        BoardResDTO boardResDTO = boardService.getBoardId(id);
        FileResDTO fileResDTO = fileService.getFileId(fileId);
        model.addAttribute("board", boardResDTO);
        model.addAttribute("file", fileResDTO);
        return "/file/fileUpdate";
    }
}
