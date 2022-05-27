package com.example.postservice.Controller;

import com.example.postservice.DTO.NewPostDTO;
import com.example.postservice.DTO.PostDTO;
import com.example.postservice.Model.Post;
import com.example.postservice.Service.LikeService;
import com.example.postservice.Service.PostService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/api/posts")
public class PostController {
    @Autowired
    private PostService postService;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private LikeService likeService;

    @PostMapping(consumes = { MediaType.MULTIPART_FORM_DATA_VALUE })
    public ResponseEntity<?> create(@ModelAttribute NewPostDTO dto, @RequestHeader String authorization) {
        if ((dto.getTextContent() == null || dto.getTextContent().isEmpty())
            && dto.getImage() == null)
            return new ResponseEntity<>("Each post must have some type of content!", HttpStatus.BAD_REQUEST);

        var imagePath = "";
        if (dto.getImage() != null)
            imagePath = postService.uploadImages(dto.getImage());

        var postDto = new Post();
        postDto.setTextContent(dto.getTextContent());
        postDto.setImagePath(imagePath);
        Post newPost = null;
        try {
            newPost = postService.save(postDto, authorization);
        } catch (Exception e) {
            return new ResponseEntity<>("Unable to create post because there is no user defined!", HttpStatus.BAD_REQUEST);
        }

        return new ResponseEntity<>(modelMapper.map(postService.saveToDatabase(newPost), PostDTO.class), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<PostDTO>> get() {
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
    public ResponseEntity<List<PostDTO>> getNewsFeed(@RequestHeader String authorization) throws IOException {
        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authorization);
        HttpEntity<String> entity = new HttpEntity<>("", headers);
        RestTemplate restTemplate = new RestTemplate();
        var userPostIds = restTemplate.exchange("http://user-service:8761/api/users/loggedinandfollowers",
                HttpMethod.GET, entity, List.class);
        List<String> userIds = userPostIds.getBody();

        List<Post> posts = postService.findAll();
        List<Post> postsToShow = new ArrayList<>();
        for (String userId : userIds) {
            for (Post post : posts) {
                if (userId.equals(post.getUserId())) {
                    if (!post.getImagePath().isEmpty())
                        post.setImagePath(postService.getBase64(post));
                    postsToShow.add(post);
                }
            }
        }

        List<PostDTO> postDTOS = postsToShow
                .stream()
                .map(post -> modelMapper.map(post, PostDTO.class))
                .collect(Collectors.toList());
        return new ResponseEntity<>(postDTOS, HttpStatus.OK);
    }

    @PostMapping(value = "/like")
    public ResponseEntity<?> likePost(@RequestBody String postId, @RequestHeader String authorization) {
        if (postId == null || postId.equals(""))
            return new ResponseEntity<>("Cannot determine which post to like!", HttpStatus.BAD_REQUEST);
        Post post = likeService.likePost(postId, authorization);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }

    @PostMapping(value = "/dislike")
    public ResponseEntity<?> dislikePost(@RequestBody String postId, @RequestHeader String authorization) {
        if (postId == null || postId.equals(""))
            return new ResponseEntity<>("Cannot determine which post to dislike!", HttpStatus.BAD_REQUEST);
        Post post = likeService.disLikePost(postId, authorization);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }

    @PostMapping(value = "/unLike")
    public ResponseEntity<?> unLikePost(@RequestBody String postId, @RequestHeader String authorization) {
        if (postId == null || postId.equals(""))
            return new ResponseEntity<>("Cannot determine which post to unlike!", HttpStatus.BAD_REQUEST);
        Post post = likeService.unLikePost(postId, authorization);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }

    @PostMapping(value = "/unDislike")
    public ResponseEntity<?> unDislikePost(@RequestBody String postId, @RequestHeader String authorization) {
        if (postId == null || postId.equals(""))
            return new ResponseEntity<>("Cannot determine from which post the like should be removed!", HttpStatus.BAD_REQUEST);
        Post post = likeService.unDislikePost(postId, authorization);
        return new ResponseEntity<>(modelMapper.map(post, PostDTO.class), HttpStatus.OK);
    }
}
