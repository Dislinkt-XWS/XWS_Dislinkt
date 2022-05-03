package com.example.postservice.Controller;

import com.example.postservice.DTO.PostDTO;
import com.example.postservice.Model.Post;
import com.example.postservice.Service.LikeService;
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
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value="/api/posts")
public class PostController {

    @Autowired
    private PostService postService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LikeService likeService;

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

    @GetMapping(value = "/user/{userId}")
    public ResponseEntity<List<PostDTO>> findByUserId(@PathVariable("userId") String userId) {
        var posts = postService.findByUserId(userId);
        List<PostDTO> postDTOS = posts
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/like")
    public ResponseEntity<PostDTO> likePost(@RequestBody Map<String, String> ids) {
        String userId = ids.get("userId");
        String postId = ids.get("postId");
        Post post = likeService.likePost(userId, postId);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }

    @PostMapping(value = "/dislike")
    public ResponseEntity<PostDTO> dislikePost(@RequestBody Map<String, String> ids) {
        String userId = ids.get("userId");
        String postId = ids.get("postId");
        Post post = likeService.disLikePost(userId, postId);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }

    @PostMapping(value = "/unLike")
    public ResponseEntity<PostDTO> unLikePost(@RequestBody Map<String, String> ids) {
        String userId = ids.get("userId");
        String postId = ids.get("postId");
        Post post = likeService.unLikePost(userId, postId);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }

    @PostMapping(value = "/unDislike")
    public ResponseEntity<PostDTO> unDislikePost(@RequestBody Map<String, String> ids) {
        String userId = ids.get("userId");
        String postId = ids.get("postId");
        Post post = likeService.unDislikePost(userId, postId);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }
}
