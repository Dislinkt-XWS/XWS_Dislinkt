package com.example.postservice.Controller;

import com.example.postservice.DTO.PostDTO;
import com.example.postservice.Model.Post;
import com.example.postservice.Service.PostService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/posts")
public class PostController {

    @Autowired
    PostService postService;
    @Autowired
    ModelMapper modelMapper;


    @PostMapping
    public ResponseEntity<PostDTO> create(@RequestBody @Validated PostDTO dto){
        var post = modelMapper.map(dto, Post.class);
        return new ResponseEntity<>(modelMapper.map(
                postService.save(post), PostDTO.class), HttpStatus.CREATED);
    }


    @GetMapping
    public ResponseEntity<List<PostDTO>> get(){
        var posts = postService.findAll();
        List<PostDTO> postsDtos = posts
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(postsDtos, HttpStatus.OK);
    }


}
