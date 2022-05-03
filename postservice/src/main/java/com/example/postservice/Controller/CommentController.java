package com.example.postservice.Controller;

import com.example.postservice.DTO.CommentDTO;
import com.example.postservice.Model.Comment;
import com.example.postservice.Service.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;
    @Autowired
    private ModelMapper modelMapper;

    @PostMapping
    public ResponseEntity<CommentDTO> save(@RequestBody @Validated Comment dto) {
        var comment = modelMapper.map(dto, Comment.class);
        return new ResponseEntity<>(modelMapper.map(commentService.save(comment), CommentDTO.class), HttpStatus.CREATED);
    }

    @GetMapping(value = "/post/{postId}")
    public ResponseEntity<List<CommentDTO>> findByPost(@PathVariable("postId") String postId) {
        var comments = commentService.findByPostId(postId);
        List<CommentDTO> commentDTOS = comments
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
    }
}
