package com.example.postservice.Controller;

import com.example.postservice.DTO.CommentDTO;
import com.example.postservice.Model.Comment;
import com.example.postservice.Service.CommentService;
import com.example.postservice.Service.PostService;
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
    @Autowired
    private PostService postService;

    @PostMapping
    public ResponseEntity<CommentDTO> save(@RequestBody @Validated Comment dto, @RequestHeader String authorization) {
        var comment = commentService.save(modelMapper.map(dto, Comment.class), authorization);
        var post = postService.get(comment.getPostId()).get();
        post.addComment(comment.getId());
        postService.update(post);
        return new ResponseEntity<>(modelMapper.map(comment, CommentDTO.class), HttpStatus.CREATED);
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

    @GetMapping
    public ResponseEntity<List<CommentDTO>> findAll() {
        var comments = commentService.findAll();
        List<CommentDTO> commentDTOS = comments
                .stream()
                .map(comment -> modelMapper.map(comment, CommentDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(commentDTOS, HttpStatus.OK);
    }

}
