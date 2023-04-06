package com.miu.lab2.controllers;

import com.fasterxml.jackson.databind.node.ObjectNode;
import com.miu.lab2.dto.CommentDto;
import com.miu.lab2.service.ICommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/comments")
public class CommentController {

    @Autowired
    private ICommentService commentService;

    @GetMapping()
    public List<CommentDto> findAll() {
        return commentService.findAll();
    }

    @GetMapping("/{id}")
    public CommentDto findById(@PathVariable int id) {
        return commentService.findById(id);
    }


    @PostMapping("/{id}")
    public ResponseEntity<String> saveComment(@RequestBody CommentDto newCommentDto) {

        commentService.save(newCommentDto);
        return ResponseEntity.status(HttpStatus.CREATED).body("Successfully added.");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteById(@PathVariable int id) {
        return ResponseEntity.status(HttpStatus.ACCEPTED).body("deleted...");
    }

}
