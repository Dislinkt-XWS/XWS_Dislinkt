package com.example.postservice.Controller;

import com.example.postservice.DTO.PostDTO;
import com.example.postservice.Model.Post;
import com.example.postservice.Service.LikeService;
import com.example.postservice.Service.PostService;
import org.bson.types.ObjectId;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import javax.servlet.http.HttpServletRequest;
import javax.swing.text.html.parser.Entity;
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

    @GetMapping(value = "/newsfeed")
    public ResponseEntity<List<PostDTO>> getNewsFeed(@RequestHeader String authorization){
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();
        var userPostIds = restTemplate.exchange("http://user-service:8761/api/users/loggedinandfollowers",
                HttpMethod.GET, entity, List.class);
        System.out.println("Ovo dobijem iz user servisa " + userPostIds);
        List<String> userIds = userPostIds.getBody();
        System.out.println("Ovo je body koji dobijem iz user servisa " + userIds);

        List<Post> posts = postService.findAll();
        List<Post> postsToShow = new ArrayList<>();
        for(String userId : userIds){
            for(Post post : posts){
                System.out.println("Ovo je userId i post posebno: " + userId + " " + post);
                if(userId.equals(post.getUserId()))
                    postsToShow.add(post);
           }
       }

        List<PostDTO> postDTOS = postsToShow
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
